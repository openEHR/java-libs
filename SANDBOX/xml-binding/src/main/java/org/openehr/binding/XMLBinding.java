/*
 * component:   "openEHR Java Reference Implementation"
 * description: "Class XMLBinding"
 * keywords:    "XML binding"
 *
 * author:      "Rong Chen <rong.acode@gmail.com>"
 * copyright:   "Copyright (c) 2008 Cambio Healthcare Systems, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL$"
 * revision:    "$LastChangedRevision$"
 * last_change: "$LastChangedDate$"
 */
package org.openehr.binding;

import java.lang.reflect.Method;
import java.util.*;

import org.apache.log4j.Logger;
import org.openehr.build.RMObjectBuilder;
import org.openehr.build.RMObjectBuildingException;
import org.openehr.build.SystemValue;
import org.openehr.rm.support.measurement.MeasurementService;
import org.openehr.rm.support.measurement.SimpleMeasurementService;
import org.openehr.rm.support.terminology.TerminologyService;
import org.openehr.terminology.SimpleTerminologyService;

/**
 * Bind data from XMLBeans class to openEHR RM classes
 * 
 * @author Rong.Chen
 */
public class XMLBinding {

	public XMLBinding() throws XMLBindingException {
		try {
			TerminologyService termServ = SimpleTerminologyService
					.getInstance();
			MeasurementService measureServ = SimpleMeasurementService
					.getInstance();

			Map<SystemValue, Object> values = new HashMap<SystemValue, Object>();
			values.put(SystemValue.TERMINOLOGY_SERVICE, termServ);
			values.put(SystemValue.MEASUREMENT_SERVICE, measureServ);
			builder = new RMObjectBuilder(values);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("failed to start DADLBinding..");
		}
	}

	/**
	 * Binds data from XML binding classes to RM classes using reflection
	 * 
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public Object bindToRM(Object object) throws Exception {
		Method[] methods = object.getClass().getMethods();
		Object value = null;
		Map<String, Object> valueMap = new HashMap<String, Object>();

		String className = object.getClass().getSimpleName();
		if (className.endsWith("Impl")) {
			className = className.substring(0, className.length() - 4);
		}
		
		Map<String, Class> attributes = builder.retrieveAttribute(className);
		Set<String> attributeNames = attributes.keySet();
		
		log.debug("attributeNames: " + attributeNames);

		for (Method method : methods) {
			String name = method.getName();
			
			if (isGetter(name, attributeNames)) {

				log.debug("getter: " + name);
				
				if(method.getParameterTypes().length > 0) {
					continue;
				}

				value = method.invoke(object, null);

				if (value == null) {
					continue;
				}

				log.debug("value.class: " + value.getClass() + ", "
						+ isXMLBindingClass(value));

				if (value.getClass().isArray()) {
					Object[] array = (Object[]) value;
					if(array.length == 0) {
						continue;
					}
					Object[] done = new Object[array.length];
					for (int i = 0; i < array.length; i++) {
						done[i] = bindToRM(array[i]);
					}
					value = done;

				} else if (isXMLBindingClass(value)) {

					value = bindToRM(value);

				}
				name = getAttributeNameFromGetter(name);

				log.debug("attribute: " + name + ", value(" + value + ")");

				valueMap.put(name, value);
			}
		}

		log.debug("building rm class: " + className 
				+ ", with valueMap: " + valueMap);

		Object rmObj = null;
		try {
			rmObj = builder.construct(className, valueMap);
		} catch (RMObjectBuildingException e) {
			log.warn(">>> FAILED to build instance of " + className
					+ ", exception: " + e.getMessage());
		}
		return rmObj;
	}

	/* checks if the given method is a known getter of attributes */
	private boolean isGetter(String method, Set<String> attributes) {
		if (!method.startsWith("get")) {
			return false;
		}
		String name = getAttributeNameFromGetter(method);
		return attributes.contains(name);
	}

	/* turns a getter's name into an attribute name */
	private String getAttributeNameFromGetter(String name) {
		name = name.substring(3, name.length());
		name = name.substring(0, 1).toLowerCase() + name.substring(1);
		if(name.endsWith("Array")) {
			name = name.substring(0, name.length() - 5);
		}		
		return name;
	}

	private boolean isXMLBindingClass(Object obj) {
		return obj.getClass().getName().contains(XML_BINDING_PACKAGE);
	}

	/* logger */
	private static Logger log = Logger.getLogger(XMLBinding.class);
	
	/* namespace for generated binding class */
	private static String XML_BINDING_PACKAGE = "org.openehr.schemas.v1";
	
	/* the builder used to create rm objects */
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
 * The Original Code is XMLBinding.java
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