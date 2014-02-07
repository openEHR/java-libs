/*
 * component:   "openEHR Java Reference Implementation"
 * description: "Class XMLBinding"
 * keywords:    "XML binding"
 *
 * author:      "Rong Chen <rong.acode@gmail.com>"
 * copyright:   "Copyright (c) 2008-2010 Cambio Healthcare Systems, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL$"
 * revision:    "$LastChangedRevision$"
 * last_change: "$LastChangedDate$"
 */
package org.openehr.binding;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.util.*;

import org.apache.log4j.Logger;
import org.apache.xmlbeans.XmlOptions;
import org.openehr.build.RMObjectBuilder;
import org.openehr.build.RMObjectBuildingException;
import org.openehr.build.SystemValue;
import org.openehr.rm.datatypes.quantity.ProportionKind;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.support.measurement.MeasurementService;
import org.openehr.rm.support.measurement.SimpleMeasurementService;
import org.openehr.rm.support.terminology.TerminologyService;
import org.openehr.terminology.SimpleTerminologyService;

/**
 * Bind data from XMLBeans class to openEHR RM classes
 *
 * @author Rong.Chen
 * @author minor modifications by Erik Sundvall, Linköping University
 */
public class XMLBinding {

	/**
	 * Constructor allowing use of a custom SystemValue Map
	 */
	public XMLBinding(Map<SystemValue, Object> values) throws XMLBindingException {	
		try {
			init(values);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("failed to start XMLBinding...");
		}
	}
		
	/**
	 * Default constructor starting the XML-binding using the following system values
	 * <ul> 
	 * 	<li> TERMINOLOGY_SERVICE = an instance of SimpleTerminologyService </li>
	 * 	<li> MEASUREMENT_SERVICE = an instance of SimpleMeasurementService </li>
	 * 	<li> TERMINOLOGY_SERVICE = an instance of CodePhrase("IANA_character-sets", "UTF-8"); </li>
	 * </ul>
	 */
	public XMLBinding() throws XMLBindingException {
		try {
			TerminologyService termServ = SimpleTerminologyService
					.getInstance();
			MeasurementService measureServ = SimpleMeasurementService
					.getInstance();
			CodePhrase charset = new CodePhrase("IANA_character-sets",
								"UTF-8");

			Map<SystemValue, Object> values = new HashMap<SystemValue, Object>();
			values.put(SystemValue.TERMINOLOGY_SERVICE, termServ);
			values.put(SystemValue.MEASUREMENT_SERVICE, measureServ);
			values.put(SystemValue.CHARSET, charset);						
			init(values);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("failed to start XMLBinding..");
		}
	}

	/**
	 * Binds data from reference model instance to XML binding classes
	 *
	 * @param obj
	 * @return
	 * @throws XMLBindingException
	 */
	public Object bindToXML(Object obj) throws XMLBindingException {
		if(obj == null) {
			return null;
		}
		String className = obj.getClass().getSimpleName();
		Method[] methods = obj.getClass().getMethods();

		try {
			Class xmlClass = Class.forName(XML_BINDING_PACKAGE +
					className.toUpperCase());

			// debug code only
			if(xmlClass.getClasses().length != 1) {
				log.debug("XMLBinding: bindToXML(): xmlClass.getClass()=" + xmlClass.getClass());
				log.debug("XMLBinding: bindToXML(): xmlClass.toString()=" + xmlClass.toString());
				for(Class clazz : xmlClass.getClasses()) {
                    log.debug("\t clazz.getClass()=" + clazz.getClass());
                    log.debug("\t clazz.toString()=" + clazz.toString());
                }
			}

			Class factoryClass = xmlClass.getClasses()[0];

			// ES modification: Add xmlOptions containing openehr (default) and xsi namespaces
						
			// Method factoryMethod = factoryClass.getMethod(NEW_INSTANCE, null);
			// Changed to pick the method with an XmlOptions parameter instead
			Method factoryMethod = factoryClass.getMethod(NEW_INSTANCE, XmlOptions.class);
			
			// First prameter null because it's a static method, see:
			// http://java.sun.com/docs/books/tutorial/reflect/member/methodInvocation.html
			// Second parameter should be the parameter of XmlObject.Factory.newInstance(XmlOptions options) 
			// see: http://xmlbeans.apache.org/docs/2.2.0/reference/org/apache/xmlbeans/XmlObject.Factory.html
			//
			// Previous code was: Object xmlObj = factoryMethod.invoke(null, null);
			Object xmlObj = factoryMethod.invoke(null, xopt);

			Map<String, Class> attributes = builder.retrieveAttribute(className);
			Set<String> attributeNames = attributes.keySet();
			Object attributeValue = null;
			Method setterMethod = null;

			for (Method method : methods) {
				String name = method.getName();

				// cause dead-loop
				if("getParent".equals(name)) {
					continue; //
				}

				if (isGetter(name, attributeNames)) {

					log.debug("getter: " + name);

					if(method.getParameterTypes().length > 0) {
						continue;
					}

					attributeValue = method.invoke(obj, null);

					if (attributeValue == null) {
						continue;
					}

					log.debug("value.class: " + attributeValue.getClass());

					boolean isList = false;

					if (attributeValue.getClass().isArray()) {
						Object[] array = (Object[]) attributeValue;
						if(array.length == 0) {
							continue;
						}
						Object[] done = new Object[array.length];
						for (int i = 0; i < array.length; i++) {
							done[i] = bindToXML(array[i]);
						}
						attributeValue = done;

					} else if (ProportionKind.class.equals(
							attributeValue.getClass())) {

						ProportionKind kind = (ProportionKind) attributeValue;
						attributeValue = BigInteger.valueOf(kind.getValue());

					} else if (isOpenEHRRMClass(attributeValue)) {

						attributeValue = bindToXML(attributeValue);

					} else if(List.class.isAssignableFrom(
							attributeValue.getClass())) {

						isList = true;
						List list = (List) attributeValue;

						log.debug("list.size: " + list.size());

						String attributeName = getAttributeNameFromGetter(name);
						setterMethod = findSetter(attributeName, xmlClass, isList);

						Method addNew = findAddNew(attributeName, xmlClass);

						log.debug("setter: " + setterMethod.getName() + ", xmlClass: " + xmlClass);

						for(int i = 0, j = list.size() - 1; i <= j; i++) {
							Object value = list.get(i);

							Object[] array = new Object[2];

							addNew.invoke(xmlObj, null);

							array[0] = new Integer(i);
							array[1] = bindToXML(value);
							setterMethod.invoke(xmlObj, array);

							log.debug("list.member value set!!!!");
						}
					}

					if( ! isList) {
						String attributeName = getAttributeNameFromGetter(name);

						log.debug("attribute: " + attributeName + ", value(" + attributeValue + "), " +
								"type: " + attributeValue.getClass());

						// TODO fix for mismatched attribute name in XSD and RM 
						if("nullFlavor".equals(attributeName)) {
							attributeName = "nullFlavour";
						}
						
						// skip function according to specs
						if("isMerged".equals(attributeName)) {
							continue;
						}
						
						setterMethod = findSetter(attributeName, xmlClass, isList);

						if(setterMethod == null) {
							log.error("failed to find setterMethod for attribute: "
									+ attributeName + " with type: " + xmlClass);
							continue;
						}

						// special handling deals with 'real' typed
						// attributes in specs but typed 'float' in xsd
						String setter = setterMethod.getName();
						if("setAccuracy".equals(setter)
								|| "setDenominator".equals(setter)
								|| "setNumerator".equals(setter)) {

							Double d = (Double) attributeValue;
							attributeValue = d.floatValue();
						}
						
						log.debug("setter: " + setterMethod.getName() +
								", xmlClass: " + xmlClass +
								", attributeValue: " + attributeValue +
								", attributeValue.class: " + attributeValue.getClass());

						setterMethod.invoke(xmlObj, attributeValue);
					}
				}
			}

			return xmlObj;

		} catch(Exception e) {
			e.printStackTrace();
			throw new XMLBindingException("exception caught when bind obj to "
					+ className + ", " +  e.getMessage());
		}
	}

	Method findSetter(String attributeName, Class xmlClass, boolean isList) {
		Method[] methods = xmlClass.getMethods();
		String name = "set" + attributeName.substring(0, 1).toUpperCase() +
						attributeName.substring(1);

		if(isList) {
			name += "Array";
		}

		log.debug("search method of name '" + name + "'");

		for(Method method : methods) {
			if(method.getName().equals(name)) {
				Type[] paras = method.getParameterTypes();
				if(isList) {
					if(paras.length == 2) {
						return method;
					}
				} else if(paras.length == 1) {
					return method;
				}
			}
		}
		return null;
	}

	Method findAddNew(String attributeName, Class xmlClass) {
		Method[] methods = xmlClass.getMethods();
		String name = "addNew" + attributeName.substring(0, 1).toUpperCase() +
						attributeName.substring(1);

		log.debug("search method of name '" + name + "'");

		for(Method method : methods) {
			if(method.getName().equals(name)) {
				return method;
			}
		}
		return null;
	}

	Class findXMLAbstractClass(Class xmlClass) throws ClassNotFoundException {

		if( ! xmlClass.getName().contains(XML_BINDING_PACKAGE)) {
			return xmlClass; // primitive class
		}

		String className = xmlClass.getSimpleName();
		if (className.endsWith("Impl")) {
			className = className.substring(0, className.length() - 4);
		}
		Class abstractClass = Class.forName(XML_BINDING_PACKAGE  + className);
		return abstractClass;
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
				
				if(method.getParameterTypes().length > 0) {
					continue;
				}

				String attribute = getAttributeNameFromGetter(name);
				
				value = method.invoke(object, null);

				log.info("getter: " + name + ", attribute: " + attribute +
						", value: " + value);
				
				if (value == null) {
					
					continue;
				}

				log.debug("value.class: " + value.getClass() + ", "
						+ isXMLBindingClass(value));

				if (value.getClass().isArray()) {
					Object[] array = (Object[]) value;
					if(array.length == 0) {
						
						// special fix for item_structure.items
						if("items".equals(attribute)) {
							valueMap.put(attribute, new ArrayList());
						}
						continue;
						
					} else {
					
						Object[] done = new Object[array.length];
						for (int i = 0; i < array.length; i++) {
							done[i] = bindToRM(array[i]);
						}
						value = done;
					}

				} else if (isXMLBindingClass(value)) {

					value = bindToRM(value);

				}
				
				log.debug("attribute: " + attribute + ", value(" + value + ")");
				
				valueMap.put(attribute, value);
			}
		}

		log.info("building rm class: " + className
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

	private boolean isOpenEHRRMClass(Object obj) {
		return obj.getClass().getName().contains(OPENEHR_RM_PACKAGE);
	}
	

	protected void init(Map<SystemValue, Object> values) throws XMLBindingException{
			
		// Set up xml defaults
		xopt = new XmlOptions();
		
		HashMap<String, String> uriToPrefixMap = new HashMap<String, String>();
		uriToPrefixMap.put(SCHEMA_XSI, "xsi");
	    uriToPrefixMap.put(SCHEMA_OPENEHR_ORG_V1, "v1");
		xopt.setSaveSuggestedPrefixes(uriToPrefixMap);
	
		xopt.setSaveAggressiveNamespaces();
		xopt.setSavePrettyPrint(); 
		xopt.setCharacterEncoding("UTF-8");
		
		try {
			builder = new RMObjectBuilder(values);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("failed to start XMLBinding...");
		}		
	}

	/* logger */
	private static Logger log = Logger.getLogger(XMLBinding.class);

	/* namespace for generated binding class */
	private static String XML_BINDING_PACKAGE = "org.openehr.schemas.v1.";

	/* namespace for rm class */
	private static String OPENEHR_RM_PACKAGE = "org.openehr.rm.";

	/* factory method name */
	private static String NEW_INSTANCE = "newInstance";
	
	public static final String SCHEMA_XSI = "http://www.w3.org/2001/XMLSchema-instance";
	public static final String SCHEMA_OPENEHR_ORG_V1 = "http://schemas.openehr.org/v1";

	/* the builder used to create rm objects */
	private RMObjectBuilder builder;
	
	/* ES: XMLOptions to make nicer XML */
	private XmlOptions xopt;
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
 * the Initial Developer are Copyright (C) 2003-2010 the Initial Developer. All
 * Rights Reserved.
 *
 * Contributor(s): Erik Sundvall
 *
 * Software distributed under the License is distributed on an 'AS IS' basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 *
 * ***** END LICENSE BLOCK *****
 */