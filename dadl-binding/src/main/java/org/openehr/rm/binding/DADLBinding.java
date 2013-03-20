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

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.openehr.am.parser.AttributeValue;
import org.openehr.am.parser.ComplexObjectBlock;
import org.openehr.am.parser.ContentObject;
import org.openehr.am.parser.KeyedObject;
import org.openehr.am.parser.MultipleAttributeObjectBlock;
import org.openehr.am.parser.ObjectBlock;
import org.openehr.am.parser.PrimitiveObjectBlock;
import org.openehr.am.parser.SimpleValue;
import org.openehr.am.parser.SingleAttributeObjectBlock;
import org.openehr.build.RMObjectBuilder;
import org.openehr.build.RMObjectBuildingException;
import org.openehr.build.SystemValue;
import org.openehr.rm.Attribute;
import org.openehr.rm.FullConstructor;
import org.openehr.rm.RMObject;
import org.openehr.rm.datatypes.quantity.ProportionKind;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.support.basic.Interval;
import org.openehr.rm.support.measurement.MeasurementService;
import org.openehr.rm.support.measurement.SimpleMeasurementService;
import org.openehr.rm.support.terminology.TerminologyService;
import org.openehr.terminology.SimpleTerminologyService;

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
	
	public List<String> toDADL(Object obj) throws Exception {
		List<String> lines = new ArrayList<String>();
		return toDADL(obj, 1, lines);
	}
	
	public List<String> toDADL(Object obj, int indent, List<String> lines) throws Exception {		
		
		
		log.debug("toDADL on obj.getClass: " + obj.getClass().getCanonicalName()
				+ ", indent: " + indent + ", line.size: "  + lines.size());
		
		Class klass = obj.getClass();		
		
		String className = klass.getSimpleName();
		String rmName = toUnderscoreSeparated(className).toUpperCase();
	
		String typeHeader = "(" + rmName + ") <";
		int size = lines.size();
		if(size == 0) {
			lines.add(typeHeader); 
		} else {
			String l = lines.get(size - 1);
			l += typeHeader;
			lines.set(size -1, l);
		}	
		
		SortedMap<String, Attribute> attributes = attributeMap(obj.getClass());
		String name = null;
		Object value = null;
		StringBuffer buf = null;
		for(Iterator<String> names = attributes.keySet().iterator(); names.hasNext();) {	
			name = names.next();
			
			Attribute attribute = attributes.get(name);
			if(attribute.system()) {
				continue;
			}
			
			if("parent".equals(attribute.name())) {
				continue; // causing dead-loops
			}
			
			Method getter = getter(name, obj.getClass());
			if(getter != null) { 
				value = getter.invoke(obj, null);
				buf = new StringBuffer();
				if(value != null ) {
					for(int i = 0; i < indent; i++) {
						buf.append("\t");
					}
					buf.append(toUnderscoreSeparated(name));
					buf.append(" = ");
					
					if(isOpenEHRRMClass(value) && !(value instanceof ProportionKind)) {
					
						lines.add(buf.toString());
						
						log.debug("fetching attribute: " + name);
						
						toDADL(value, indent + 1, lines);						
				
					} else if(value instanceof List) {
						
						buf.append("<");
						lines.add(buf.toString());
						
						List list = (List) value;
						for(int i = 0, j = list.size(); i < j; i++) {
							buf = new StringBuffer();
							for(int k = 0; k < indent + 1; k++) {
								buf.append("\t");
							}
							lines.add(buf.toString() + "[" + (i+1) + "] = ");
							toDADL(list.get(i), indent + 2, lines);
						}
						
						buf = new StringBuffer();
						for(int i = 0; i < indent; i++) {
							buf.append("\t");
						}
						buf.append(">");
						lines.add(buf.toString());
						
					} else {
						
						buf.append("<");
						if(value instanceof String || value instanceof Boolean) {							
							buf.append("\"");
							buf.append(value);
							buf.append("\"");						
						} else {
							buf.append(value.toString());
						}
						buf.append(">");
						lines.add(buf.toString());
					}
					
				}
			}
		}
		buf = new StringBuffer();
		for(int i = 0; i < indent - 1; i++) {
			buf.append("\t");
		}
		buf.append(">");
		lines.add(buf.toString());		
		return lines;
	}
	
	private Method getter(String attributeName, Class klass) {
		Method[] methods = klass.getMethods();
		String name = "get" + attributeName.substring(0, 1).toUpperCase() +
						attributeName.substring(1);

		log.debug("search getter method of name '" + name + "'");

		for(Method method : methods) {
			if(method.getName().equals(name)) {
				Type[] paras = method.getParameterTypes();
				if(paras.length == 0) {
					return method;
				}
			}
		}
		return null;
	}
	
	/**
	 * Return a map with name as the key and index of position as the value for
	 * all parameters of the full constructor in the RMObject
	 * 
	 * @param rmClass
	 * @return
	 */
	private SortedMap<String, Attribute> attributeMap(Class rmClass) {
		SortedMap<String, Attribute> map = new TreeMap<String, Attribute>();
		Constructor constructor = fullConstructor(rmClass);
		
		if(constructor == null) {
			throw new IllegalArgumentException("Unknown RM Class: " + 
					rmClass.getClass().getCanonicalName());
		}
		
		Annotation[][] annotations = constructor.getParameterAnnotations();

		for (int i = 0; i < annotations.length; i++) {
			if (annotations[i].length == 0) {
				throw new IllegalArgumentException(
						"missing annotation at position " + i);
			}
			Attribute attribute = (Attribute) annotations[i][0];
			map.put(attribute.name(), attribute);
		}
		return map;
	}

	private static Constructor fullConstructor(Class klass) {
		if(klass == null) {
			return null;
		}
		Constructor[] array = klass.getConstructors();
		for (Constructor constructor : array) {
			if (constructor.isAnnotationPresent(FullConstructor.class)) {
				return constructor;
			}
		}
		return null;
	}	
	
	public String toUnderscoreSeparated(String camelCase) {
		String[] array = StringUtils.splitByCharacterTypeCamelCase(camelCase);
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < array.length; i++) {
			String s = array[i];
			buf.append(s.substring(0, 1).toLowerCase());
			buf.append(s.substring(1));
			if (i != array.length - 1) {
				buf.append("_");
			}
		}
		return buf.toString();
	}

	private boolean isOpenEHRRMClass(Object obj) {
		return obj.getClass().getName().contains(OPENEHR_RM_PACKAGE);
	}
	

	private RMObjectBuilder builder;
	private static Logger log = Logger.getLogger(DADLBinding.class);
	private static final String OPENEHR_RM_PACKAGE = "org.openehr.rm.";
	private static final String LINE_RETURN = "\r\n";

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