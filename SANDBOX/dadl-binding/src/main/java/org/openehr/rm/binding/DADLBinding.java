/*
 * component:   "openEHR Java Reference Implementation"
 * description: "Class DADLBinding"
 * keywords:    "binding"
 *
 * author:      "Rong Chen <rong.acode@gmail.com>"
 * copyright:   "Copyright (c) 2008 Cambio Healthcare Systems, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL$"
 * revision:    "$LastChangedRevision$"
 * last_change: "$LastChangedDate$"
 */
package org.openehr.rm.binding;

import org.openehr.am.parser.*;
import org.openehr.build.RMObjectBuilder;
import org.openehr.build.RMObjectBuildingException;
import org.openehr.build.SystemValue;
import org.openehr.rm.RMObject;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.support.basic.Interval;
import org.openehr.rm.support.measurement.MeasurementService;
import org.openehr.rm.support.measurement.SimpleMeasurementService;
import org.openehr.rm.support.terminology.TerminologyService;
import org.openehr.terminology.SimpleTerminologyService;

import java.util.*;

/**
 * Utility class that binds data in DADL format to openEHR RM
 * 
 * @author rong.chen
 */
public class DADLBinding {

	public DADLBinding() {
		try {
			TerminologyService termServ;
			MeasurementService measureServ;
			termServ = SimpleTerminologyService.getInstance();
			measureServ = SimpleMeasurementService.getInstance();

			CodePhrase lang = new CodePhrase("ISO_639-1", "en");
			CodePhrase charset = new CodePhrase("IANA_character-sets", "UTF-8");

			Map<SystemValue, Object> values = new HashMap<SystemValue, Object>();
			values.put(SystemValue.LANGUAGE, lang);
			values.put(SystemValue.CHARSET, charset);
			values.put(SystemValue.ENCODING, charset);
			values.put(SystemValue.TERMINOLOGY_SERVICE, termServ);
			values.put(SystemValue.MEASUREMENT_SERVICE, measureServ);
			builder = new RMObjectBuilder(values);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("failed to start DADLBinding..");
		}
	}

	public Object bind(ContentObject co) throws DADLBindingException,
			RMObjectBuildingException {
		if (co.getAttributeValues() != null) {

			return bindAttributes(null, co.getAttributeValues());

		} else {
			ComplexObjectBlock complexObj = co.getComplexObjectBlock();
			return bindComplexBlock(complexObj);
		}
	}

	RMObject bindAttributes(String type, List<AttributeValue> attributes)
			throws DADLBindingException, RMObjectBuildingException {

		Map<String, Object> values = new HashMap<String, Object>();
		for (AttributeValue attr : attributes) {
			String id = attr.getId();
			Object value = bindObjectBlock(attr.getValue());
			values.put(id, value);
		}		
		return invokeRMObjectBuilder(type, values);
	}
	
	RMObject invokeRMObjectBuilder(String type, Map<String, Object> valueMap)
			throws DADLBindingException, RMObjectBuildingException {
		
		if(type == null) {
			type = builder.findMatchingRMClass(valueMap);
			if(type == null) {
				throw new DADLBindingException("failed untyped binding with - "
					+ valueMap); 
			}
		}
		RMObject rmObj = builder.construct(type, valueMap);
		return rmObj;
	}

	Object bindObjectBlock(ObjectBlock block) throws DADLBindingException,
			RMObjectBuildingException {
		if (block instanceof PrimitiveObjectBlock) {
			return bindPrimitiveBlock((PrimitiveObjectBlock) block);
		} else {
			return bindComplexBlock((ComplexObjectBlock) block);
		}
	}

	Object bindPrimitiveBlock(PrimitiveObjectBlock block)
			throws DADLBindingException {
		if (block.getSimpleValue() != null) {
			return block.getSimpleValue().getValue();
		} else if (block.getSimpleListValue() != null) {
			List<SimpleValue> values = block.getSimpleListValue();
			List list = new ArrayList(values.size());
			for (SimpleValue sv : values) {
				list.add(sv.getValue());
			}
			return list;
		} else if (block.getSimpleIntervalValue() != null) {
			Interval<Comparable> values = block.getSimpleIntervalValue();
			// TODO
			return null;
		} else if (block.getTermCode() != null) {
			return block.getTermCode();
		} else if (block.getTermCodeListValue() != null) {
			return block.getTermCodeListValue();
		} else {
			throw new DADLBindingException("empty block");
		}

	}

	Object bindComplexBlock(ComplexObjectBlock block)
			throws DADLBindingException, RMObjectBuildingException {
		
		if (block instanceof SingleAttributeObjectBlock) {
			SingleAttributeObjectBlock singleBlock = 
				(SingleAttributeObjectBlock) block;
			
			// a special case to deal with empty attribute list
			if("LIST".equalsIgnoreCase(singleBlock.getTypeIdentifier())
					&& singleBlock.getAttributeValues().isEmpty()) {
				
				return new ArrayList();
			} 

			return bindAttributes(singleBlock.getTypeIdentifier(), singleBlock
					.getAttributeValues());

		} else {
			MultipleAttributeObjectBlock multiBlock = 
				(MultipleAttributeObjectBlock) block;
			String type = multiBlock.getTypeIdentifier();
			List<KeyedObject> list = multiBlock.getKeyObjects();
			// TODO assume list?
			List<Object> valueList = new ArrayList<Object>();
			for(KeyedObject ko : list) {
				Object key = ko.getKey().getValue();
				Object value = bindObjectBlock(ko.getObject());
				valueList.add(value);
			}
			return valueList;
		}
	}

	private RMObjectBuilder builder;

}
/*
 * ***** BEGIN LICENSE BLOCK ***** Version: MPL 1.1/GPL 2.0/LGPL 2.1
 * 
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the 'License'); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at http://www.mozilla.org/MPL/
 * 
 * Software distributed under the License is distributed on an 'AS IS' basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 * 
 * The Original Code is DADLBinding.java
 * 
 * The Initial Developer of the Original Code is Rong Chen. Portions created by
 * the Initial Developer are Copyright (C) 2003-2008 the Initial Developer. All
 * Rights Reserved.
 * 
 * Contributor(s):
 * 
 * Software distributed under the License is distributed on an 'AS IS' basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 * 
 * ***** END LICENSE BLOCK *****
 */