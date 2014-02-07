/*
 * component:   "openEHR Java Reference Implementation"
 * description: "Class RMObjectBuilder"
 * keywords:    "builder"
 *
 * author:      "Rong Chen <rong.acode@gmail.com>"
 * copyright:   "Copyright (c) 2003-2008 ACODE HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL$"
 * revision:    "$LastChangedRevision$"
 * last_change: "$LastChangedDate$"
 */
package org.openehr.build;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.openehr.rm.*;
import org.openehr.rm.common.archetyped.*;
import org.openehr.rm.common.changecontrol.Contribution;
import org.openehr.rm.common.changecontrol.OriginalVersion;
import org.openehr.rm.common.generic.*;
import org.openehr.rm.composition.Composition;
import org.openehr.rm.composition.EventContext;
import org.openehr.rm.composition.content.entry.*;
import org.openehr.rm.composition.content.navigation.*;
import org.openehr.rm.datastructure.history.*;
import org.openehr.rm.datastructure.itemstructure.*;
import org.openehr.rm.datastructure.itemstructure.representation.*;
import org.openehr.rm.datatypes.basic.*;
import org.openehr.rm.datatypes.encapsulated.*;
import org.openehr.rm.datatypes.quantity.*;
import org.openehr.rm.datatypes.quantity.datetime.*;
import org.openehr.rm.datatypes.text.*;
import org.openehr.rm.datatypes.uri.*;
import org.openehr.rm.demographic.*;
import org.openehr.rm.support.identification.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.util.*;

/**
 * Reference model class instances builder
 * 
 * @author Rong Chen
 * @version 1.0
 */
public class RMObjectBuilder {
	
	/**
	 * Factory method to create an instance of the RM builder
	 * 
	 * @param systemValues
	 * @return
	 */
	public static RMObjectBuilder getInstance(Map<SystemValue, Object> systemValues) {
		return new RMObjectBuilder(systemValues);
	}

	/**
	 * Create a RMObjectBuilder
	 * 
	 * @param systemValues
	 */
	public RMObjectBuilder(Map<SystemValue, Object> systemValues) {

		this();

		if (systemValues == null) {
			throw new IllegalArgumentException("systemValues null");
		}
		this.systemValues = systemValues;
	}

	// for testing purpose
	RMObjectBuilder() {
		try {
			loadTypeMap();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("failed to load class, " + e
					+ " when starting rmObjectBuilder..");
		}
	}

	// load all reference types that are possible to instantiate
	// using FullConstructor annotation
	private Map<String, Class> loadTypeMap() throws ClassNotFoundException {
		Class[] classes = {

				// common classes
				PartySelf.class,
				Archetyped.class,
				Attestation.class,
				AuditDetails.class,
				Participation.class,
				PartyIdentified.class,
				PartyRelated.class,
				PartySelf.class,
				OriginalVersion.class,
				Contribution.class,

				// support classes
				TerminologyID.class,
				ArchetypeID.class,
				HierObjectID.class,
				AccessGroupRef.class,
				GenericID.class,
				InternetID.class,
				ISO_OID.class,
				LocatableRef.class,
				ObjectVersionID.class,
				ObjectRef.class,
				PartyRef.class,
				TemplateID.class,
				TerminologyID.class,
				org.openehr.rm.support.identification.UUID.class,
				//VersionTreeID.class,

				// datatypes classes
				DvBoolean.class,
				DvState.class,
				DvIdentifier.class,
				DvText.class,
				DvCodedText.class,
				DvParagraph.class,
				CodePhrase.class,
				DvCount.class,
				DvOrdinal.class,
				DvQuantity.class,
				DvInterval.class,
				DvProportion.class,
				DvDate.class,
				DvDateTime.class,
				DvTime.class,
				DvDuration.class,
				DvParsable.class, 
				DvURI.class,
				DvEHRURI.class,
				DvMultimedia.class,

				// datastructure classes
				Element.class, 
				Cluster.class, 
				ItemSingle.class, 
				ItemList.class,
				ItemTable.class,
				ItemTree.class,
				//ItemStructure.class,
				History.class,
				IntervalEvent.class,
				PointEvent.class,

				// ehr classes
				Action.class, Activity.class, Evaluation.class, ISMTransition.class,
				Instruction.class, InstructionDetails.class, Observation.class, AdminEntry.class,
				Section.class, Composition.class,
				EventContext.class, ISMTransition.class,

				// demographic classes
				Address.class, PartyIdentity.class, Agent.class, Group.class,
				Organisation.class, Person.class, Contact.class,
				PartyRelationship.class, Role.class, Capability.class };

		typeMap = new HashMap<String, Class>();
		upperCaseMap = new HashMap<String, Class>();
		for (Class klass : classes) {
			String name = klass.getSimpleName();
			typeMap.put(name, klass);
			upperCaseMap.put(name.toUpperCase(), klass);
		}
		
		return typeMap;
	}

	/*
	 * Return a map with name as the key and index of position as the value for
	 * required parameters of the full constructor in the RMObject
	 * 
	 * @param rmClass @return
	 */
	private Map<String, Class> attributeType(Class rmClass) {

		Map<String, Class> map = new HashMap<String, Class>();
		Constructor constructor = fullConstructor(rmClass);
		if (constructor == null) {
			throw new IllegalArgumentException("no annotated constructor of "
					+ rmClass + ">");
		}
		Annotation[][] annotations = constructor.getParameterAnnotations();
		Class[] types = constructor.getParameterTypes();

		if (annotations.length != types.length) {
			throw new IllegalArgumentException("less annotations");
		}
		for (int i = 0; i < types.length; i++) {
			if (annotations[i].length == 0) {
				throw new IllegalArgumentException(
						"missing annotations of attribute " + i);
			}
			Attribute attribute = (Attribute) annotations[i][0];
			map.put(attribute.name(), types[i]);
		}
		return map;
	}

	/**
	 * Return a map with name as the key and index of position as the value for
	 * all parameters of the full constructor in the RMObject
	 * 
	 * @param rmClass
	 * @return
	 */
	private Map<String, Integer> attributeIndex(Class rmClass) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		Constructor constructor = fullConstructor(rmClass);
		Annotation[][] annotations = constructor.getParameterAnnotations();

		for (int i = 0; i < annotations.length; i++) {
			if (annotations[i].length == 0) {
				throw new IllegalArgumentException(
						"missing annotation at position " + i);
			}
			Attribute attribute = (Attribute) annotations[i][0];
			map.put(attribute.name(), i);
		}
		return map;
	}

	/**
	 * Return a map with name as the key and index of position as the value for
	 * all parameters of the full constructor in the RMObject
	 * 
	 * @param rmClass
	 * @return
	 */
	private Map<String, Attribute> attributeMap(Class rmClass) {
		Map<String, Attribute> map = new HashMap<String, Attribute>();
		Constructor constructor = fullConstructor(rmClass);
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
		Constructor[] array = klass.getConstructors();
		for (Constructor constructor : array) {
			if (constructor.isAnnotationPresent(FullConstructor.class)) {
				return constructor;
			}
		}
		return null;
	}

	/**
	 * Return system value for given id
	 * 
	 * @param id
	 * @return null if not there
	 */
	public Object getSystemValue(SystemValue id) {
		return systemValues.get(id);
	}

	/**
	 * Construct an instance of RM class of given name and values. <p/> If the
	 * input is a string, and the required attribute is some other types
	 * (integer, double etc), it will be converted into right type. if there is
	 * any error during conversion, AttributeFormatException will be thrown.
	 * 
	 * @param rmClassName
	 * @param valueMap
	 * @return created instance
	 * @throws RMObjectBuildingException
	 */
	public RMObject construct(String rmClassName, Map<String, Object> valueMap)
			throws RMObjectBuildingException {

		Class rmClass = retrieveRMType(rmClassName);
      
		// replace underscore separated names with camel case
		Map<String, Object> filteredMap = new HashMap<String, Object>();
		for (String name : valueMap.keySet()) {
			filteredMap.put(toCamelCase(name), valueMap.get(name));
		}
		Constructor constructor = fullConstructor(rmClass);
		Map<String, Class> typeMap = attributeType(rmClass);
		Map<String, Integer> indexMap = attributeIndex(rmClass);
		Map<String, Attribute> attributeMap = attributeMap(rmClass);
		Object[] valueArray = new Object[indexMap.size()];

		for (String name : typeMap.keySet()) {
			
			Object value = filteredMap.get(name);
			
			if (!typeMap.containsKey(name) || !attributeMap.containsKey(name)) {
				throw new RMObjectBuildingException("unknown attribute " + name);
			}
			
			Class type = typeMap.get(name);
			Integer index = indexMap.get(name);

			Attribute attribute = attributeMap.get(name);
			if (index == null || type == null) {
				throw new RMObjectBuildingException("unknown attribute \""
						+ name + "\"");
			}

			// system supplied value
			if (attribute.system()) {
				SystemValue sysvalue = SystemValue.fromId(name);
				if (sysvalue == null) {
					throw new RMObjectBuildingException("unknonw system value"
							+ "\"" + name + "\"");
				}
				value = systemValues.get(sysvalue);
				if (value == null) {
					throw new AttributeMissingException("missing value for "
							+ "system attribute \"" + name + "\" in class: "
							+ rmClass + ", with valueMap: " + valueMap);
				}
			}

			// check required attributes
			if (value == null && attribute.required()) {
				log.info(attribute);
				throw new AttributeMissingException("missing value for "
						+ "required attribute \"" + name + "\" of type " + type
						+ " while constructing " + rmClass + " with valueMap: " + valueMap);
			}

			// enum
			else if (type.isEnum() && !value.getClass().isEnum()) {
				// OG added
				if (type.equals(ProportionKind.class)) 
					value = ProportionKind.fromValue(Integer.parseInt(value.toString()));			
				else 
					value = Enum.valueOf(type, value.toString());
			}

			// in case of null, create a default value
			else if (value == null) {
				value = defaultValue(type);
			}

			// in case of string value, convert to right type if necessary
			else if (value instanceof String) {
				String str = (String) value;
				try {

					// for DvCount
					if (type.equals(int.class)) {
						value = Integer.parseInt(str);

						// for DvQuantity
					} else if (type.equals(double.class)) {
						value = Double.parseDouble(str);
					
						// for DvProportion.precision
					} else if (type.equals(Integer.class)) {
						value = new Integer(str);
					}
					
				} catch (NumberFormatException e) {
					throw new AttributeFormatException("wrong format of "
							+ "attribute " + name + ", expect " + type);
				}

				// deal with mismatch between array and list
			} else if (type.isAssignableFrom(List.class)
					&& value.getClass().isArray()) {

				Object[] array = (Object[]) value;
				List list = new ArrayList();
				for (Object o : array) {
					list.add(o);
				}
				value = list;

				// deal with mismatch between array and set
			} else if (type.isAssignableFrom(Set.class)
					&& value.getClass().isArray()) {

				Object[] array = (Object[]) value;
				Set set = new HashSet();
				for (Object o : array) {
					set.add(o);
				}
				value = set;
			}
			// check type
			else if (value != null && !type.isPrimitive()) {
				try {
					type.cast(value);
				} catch (ClassCastException e) {
					throw new RMObjectBuildingException("Failed to construct: " 
							+ rmClassName + ", value for attribute '"
							+ name + "' has wrong type, expected \"" + type
							+ "\", but got \"" + value.getClass() + "\"");
				}
			}
			valueArray[index] = value;
		}

		Object ret = null;
      
		try {
			// OG added hack
			if (rmClassName.equalsIgnoreCase("DVCOUNT")) {
				log.debug("Fixing DVCOUNT...");
				for (int i = 0; i < valueArray.length; i++) {
					Object value = valueArray[i];
					if (value != null && value.getClass().equals(Float.class))
						valueArray[i] = Double.parseDouble(value.toString());
					else if (value != null && value.getClass().equals(Long.class))
						valueArray[i] = Integer.parseInt(value.toString());
				}
			}	
			ret = constructor.newInstance(valueArray);
		} catch (Exception e) {
      
			if (log.isDebugEnabled()) {
				e.printStackTrace();
			}

			log.debug("failed in constructor.newInstance()", e);

			if (stringParsingTypes.contains(rmClassName)) {
				throw new AttributeFormatException("wrong format for type "
						+ rmClassName);
			}

			throw new RMObjectBuildingException(
					"failed to create new instance of  " + rmClassName
							+ " with valueMap: " + toString(valueMap) + ", cause: "
							+ e.getMessage());
		}
		return (RMObject) ret;
	}

	private String toString(Map<String,Object> map) {
		StringBuffer buf = new StringBuffer();
		for(String key : map.keySet()) {
			buf.append(key);
			buf.append("=");
			Object value = map.get(key);
			if(value != null) { 
				buf.append(value.getClass().getName());
				buf.append(":");
				buf.append(value.toString());
			} else {
				buf.append("null");
			}
			buf.append(", ");
		}		
		return buf.toString();
	}
	
	/**
	 * Retrieves RM type using given name try both the CamelCase and
	 * Underscore-separated ways
	 * 
	 * @param rmClassName
	 * @return
	 * @throws Exception
	 */
	public Class retrieveRMType(String rmClassName)
			throws RMObjectBuildingException {
		int index = rmClassName.indexOf("<");
		if (index>0){
		    rmClassName = rmClassName.substring(0,index);
		}
		Class rmClass = typeMap.get(rmClassName);
		if (rmClass == null) {
			rmClass = upperCaseMap.get(rmClassName.replace("_", ""));
		}
		if (rmClass == null) {
			throw new RMObjectBuildingException("RM type unknown: \""
					+ rmClassName + "\"");
		}
		return rmClass;
	}
	
	/**
	 * Retrieves list of attribute names of given class
	 * 
	 * @param rmClassName
	 * @return
	 * @throws RMObjectBuildingException
	 */
	public Map<String, Class> retrieveAttribute(String rmClassName)
			throws RMObjectBuildingException {
		Class rmClass = retrieveRMType(rmClassName);
		Map<String, Class> map = attributeType(rmClass);
		return map;
	}

	private String toCamelCase(String underscoreSeparated) {
		StringTokenizer tokens = new StringTokenizer(underscoreSeparated, "_");
		StringBuffer buf = new StringBuffer();
		while (tokens.hasMoreTokens()) {
			String word = tokens.nextToken();
			if (buf.length() == 0) {
				buf.append(word);
			} else {
				buf.append(word.substring(0, 1).toUpperCase());
				buf.append(word.substring(1));
			}
		}
		return buf.toString();
	}

	private String toUnderscoreSeparated(String camelCase) {
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

	/**
	 * Finds the matching RM class that can be used to create RM object for
	 * given value map
	 * 
	 * @param valueMap
	 * @return null if no match RM class is found
	 */
	public String findMatchingRMClass(Map<String, Object> valueMap) {
		List simpleTypes = Arrays.asList(SKIPPED_TYPES_IN_MATCHING);

		for (Class rmClass : typeMap.values()) {

			log.debug("matching rmClass: " + rmClass.getName());

			if (simpleTypes.contains(rmClass.getSimpleName())) {
				continue; // skip simple value types
			}

			// replace underscore separated names with camel case
			Map<String, Object> filteredMap = new HashMap<String, Object>();
			for (String name : valueMap.keySet()) {
				filteredMap.put(toCamelCase(name), valueMap.get(name));
			}

			Constructor constructor = fullConstructor(rmClass);
			if (constructor == null) {
				throw new RuntimeException("annotated constructor missing for "
						+ rmClass);
			}
			Annotation[][] annotations = constructor.getParameterAnnotations();
			if (annotations == null || annotations.length == 0) {
				throw new RuntimeException("attribute annotations missing for "
						+ rmClass);
			}
			Class[] types = constructor.getParameterTypes();
			boolean matched = true;
			Set<String> attributes = new HashSet<String>();

			for (int i = 0; i < types.length; i++) {
				if (annotations[i].length == 0) {
					throw new RuntimeException(
							"attribute annotation missing for" + rmClass);
				}
				Attribute attribute = (Attribute) annotations[i][0];
				attributes.add(attribute.name());

				log.debug("checking attribute: " + attribute.name());

				String attrName = attribute.name();
				Object attrValue = filteredMap.get(attrName);

				if (attribute.required() && attrValue == null) {

					log.debug("missing required attribute..");

					matched = false;
					break;

				} else if (attrValue != null) {
					if (((attrValue instanceof Boolean) && types[i] != boolean.class)
							|| ((attrValue instanceof Integer) && types[i] != Integer.class)
							|| ((attrValue instanceof Double) && types[i] != double.class)) {

						log.debug("wrong primitive value type for attribute..");
						matched = false;
						break;

					} else if (!types[i].isPrimitive()
							&& !types[i].isInstance(attrValue)) {
						log.debug("wrong value type for attribute..");
						matched = false;
						break;

					}
				}
			}

			for (String attr : filteredMap.keySet()) {
				if (!attributes.contains(attr)) {

					log.debug("unknown attribute: " + attr);

					matched = false;
				}
			}

			// matching found
			if (matched) {
				String className = rmClass.getSimpleName();
				
				log.debug(">>> MATCHING FOUND: " + className);
				
				return className;
			}
		}
		return null;
	}

	// todo: isn't there any support from java api on this?
	private Object defaultValue(Class type) {
		if (type == boolean.class) {
			return Boolean.FALSE;
		} else if (type == double.class) {
			return new Double(0);
		} else if (type == float.class) {
			return new Float(0);
		} else if (type == int.class) {
			return new Integer(0);
		} else if (type == short.class) {
			return new Short((short) 0);
		} else if (type == long.class) {
			return new Long(0);
		} else if (type == char.class) {
			return new Character((char) 0);
		} else if (type == byte.class) {
			return new Byte((byte) 0);
		}
		return null;
	}

	/*
	 * Skipped types during matching: 1. Simple value types in DADL 2. Cluster
	 * due to clash with ItemList
	 */
	private static final String[] SKIPPED_TYPES_IN_MATCHING = { "DvDateTime",
			"DvDate", "DvTime", "DvDuration", "Cluster",
			// due to clash with DvText
			"TerminologyID", "ArchetypeID", "TemplateID", "ISO_OID",
			"HierObjectID", "DvBoolean", "InternetID", "UUID",
			"ObjectVersionID", "DvURI", "DvEHRURI"
	};

	/* logger */
	private static final Logger log = Logger.getLogger(RMObjectBuilder.class);

	/* fields */
	private Map<SystemValue, Object> systemValues;

	// loaded rm type map
	private Map<String, Class> typeMap;
	private Map<String, Class> upperCaseMap;
	private static final Set<String> stringParsingTypes; // These should be rm_type_names not Java class names.

	static {
		// so far only types from quantity.datetime
		stringParsingTypes = new HashSet<String>();
		//String[] types = { "DvDate", "DvDateTime", "DvTime", "DvDuration", "DvPartialDate", "DvPartialTime" };
      String[] types = { "DV_DATE", "DV_DATE_TIME", "DV_TIME", "DV_DURATION", "DV_PARTIAL_DATE", "DV_PARTIAL_TIME" };
		stringParsingTypes.addAll(Arrays.asList(types));
	}
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
 * The Original Code is RMObjectBuilder.java
 * 
 * The Initial Developer of the Original Code is Rong Chen. Portions created by
 * the Initial Developer are Copyright (C) 2003-2008 the Initial Developer. All
 * Rights Reserved.
 * 
 * Contributor(s): Daniel Karlsson
 * 
 * Software distributed under the License is distributed on an 'AS IS' basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 * 
 * ***** END LICENSE BLOCK *****
 */