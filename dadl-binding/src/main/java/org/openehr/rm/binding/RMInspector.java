package org.openehr.rm.binding;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.util.*;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import org.openehr.rm.Attribute;
import org.openehr.rm.FullConstructor;
import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.common.changecontrol.Contribution;
import org.openehr.rm.common.changecontrol.OriginalVersion;
import org.openehr.rm.common.generic.Attestation;
import org.openehr.rm.common.generic.AuditDetails;
import org.openehr.rm.common.generic.Participation;
import org.openehr.rm.common.generic.PartyIdentified;
import org.openehr.rm.common.generic.PartyRelated;
import org.openehr.rm.common.generic.PartySelf;
import org.openehr.rm.composition.Composition;
import org.openehr.rm.composition.EventContext;
import org.openehr.rm.composition.content.entry.Action;
import org.openehr.rm.composition.content.entry.Activity;
import org.openehr.rm.composition.content.entry.AdminEntry;
import org.openehr.rm.composition.content.entry.Evaluation;
import org.openehr.rm.composition.content.entry.ISMTransition;
import org.openehr.rm.composition.content.entry.Instruction;
import org.openehr.rm.composition.content.entry.InstructionDetails;
import org.openehr.rm.composition.content.entry.Observation;
import org.openehr.rm.composition.content.navigation.Section;
import org.openehr.rm.datastructure.history.Event;
import org.openehr.rm.datastructure.history.History;
import org.openehr.rm.datastructure.history.IntervalEvent;
import org.openehr.rm.datastructure.history.PointEvent;
import org.openehr.rm.datastructure.itemstructure.ItemList;
import org.openehr.rm.datastructure.itemstructure.ItemSingle;
import org.openehr.rm.datastructure.itemstructure.ItemTable;
import org.openehr.rm.datastructure.itemstructure.ItemTree;
import org.openehr.rm.datastructure.itemstructure.representation.Cluster;
import org.openehr.rm.datastructure.itemstructure.representation.Element;
import org.openehr.rm.datatypes.basic.DvBoolean;
import org.openehr.rm.datatypes.basic.DvIdentifier;
import org.openehr.rm.datatypes.basic.DvState;
import org.openehr.rm.datatypes.encapsulated.DvMultimedia;
import org.openehr.rm.datatypes.encapsulated.DvParsable;
import org.openehr.rm.datatypes.quantity.DvCount;
import org.openehr.rm.datatypes.quantity.DvInterval;
import org.openehr.rm.datatypes.quantity.DvOrdinal;
import org.openehr.rm.datatypes.quantity.DvProportion;
import org.openehr.rm.datatypes.quantity.DvQuantity;
import org.openehr.rm.datatypes.quantity.ProportionKind;
import org.openehr.rm.datatypes.quantity.datetime.DvDate;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datatypes.quantity.datetime.DvDuration;
import org.openehr.rm.datatypes.quantity.datetime.DvTime;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.datatypes.text.DvParagraph;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.datatypes.uri.DvURI;
import org.openehr.rm.demographic.Address;
import org.openehr.rm.demographic.Agent;
import org.openehr.rm.demographic.Capability;
import org.openehr.rm.demographic.Contact;
import org.openehr.rm.demographic.Group;
import org.openehr.rm.demographic.Organisation;
import org.openehr.rm.demographic.PartyIdentity;
import org.openehr.rm.demographic.PartyRelationship;
import org.openehr.rm.demographic.Person;
import org.openehr.rm.demographic.Role;
import org.openehr.rm.support.identification.AccessGroupRef;
import org.openehr.rm.support.identification.ArchetypeID;
import org.openehr.rm.support.identification.GenericID;
import org.openehr.rm.support.identification.HierObjectID;
import org.openehr.rm.support.identification.ISO_OID;
import org.openehr.rm.support.identification.InternetID;
import org.openehr.rm.support.identification.LocatableRef;
import org.openehr.rm.support.identification.ObjectRef;
import org.openehr.rm.support.identification.ObjectVersionID;
import org.openehr.rm.support.identification.PartyRef;
import org.openehr.rm.support.identification.TemplateID;
import org.openehr.rm.support.identification.TerminologyID;
import org.openehr.rm.support.identification.UUID;
import org.openehr.rm.support.identification.VersionTreeID;

public class RMInspector {
	
	public static RMInspector getInstance() {
		return soleInstance;
	}
	
	/**
	 * Create a RMInspector
	 */
	private RMInspector() {
		try {
			loadTypeMap();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("failed to load class, " + e
					+ " when starting RMInspector..");
		}
	}

	// load all reference types that are possible to instantiate
	// using FullConstructor annotation
	private Map<String, Class> loadTypeMap() throws ClassNotFoundException {
		Class[] classes = {
				
				// implied types
				Integer.class,
				String.class,
				Boolean.class,
				Double.class,

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
				UUID.class,
				VersionTreeID.class,

				// datatypes classes
				DvBoolean.class,
				DvURI.class,
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
				ProportionKind.class,
				DvDate.class,
				DvDateTime.class,
				DvTime.class,
				DvDuration.class,
				DvParsable.class, 
				DvMultimedia.class,

				// datastructure classes
				Element.class, Cluster.class, ItemSingle.class, ItemList.class,
				ItemTable.class,
				ItemTree.class,
				History.class,
				Event.class,
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

		typeMap = new LinkedHashMap<String, Class>();
		upperCaseMap = new LinkedHashMap<String, Class>();
		for (Class klass : classes) {
			String name = klass.getSimpleName();
			typeMap.put(name, klass);
			upperCaseMap.put(name.toUpperCase(), klass);
		}
		return typeMap;
	}

	/*
	 * Return a map with name as the key and class as the value for
	 * required parameters of the full constructor in the RMObject
	 * 
	 * @param rmClass 
	 * @return empty map if not found rm class
	 */
	private Map<String, Class> attributeType(Class rmClass) {

		Map<String, Class> map = new HashMap<String, Class>();
		Constructor constructor = fullConstructor(rmClass);
		if (constructor == null) {
			return map;
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
	 * Return a map with name as the key and attribute as the value for
	 * all parameters of the full constructor in the RMObject
	 * 
	 * @param rmClass
	 * @return
	 */
	public Map<String, Attribute> attributeMap(Class rmClass) {
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

	/**
	 * Retrieves RM type using given name try both the CamelCase and
	 * Underscore-separated ways
	 * 
	 * @param rmClassName
	 * @return null if not found
	 */
	public Class retrieveRMType(String rmClassName) {
		Class rmClass = typeMap.get(rmClassName);
		if (rmClass == null) {
			rmClass = upperCaseMap.get(rmClassName.replace("_", ""));
		}
		return rmClass;
	}
	
	/**
	 * Retrieves Map of attribute classes indexed by names of given class
	 * 
	 * @param rmClassName
	 * @return
	 * @throws RMObjectBuildingException
	 */
	public Map<String, Class> retrieveRMAttributes(String rmClassName) {
		Class rmClass = retrieveRMType(rmClassName);
	
		log.debug("----- rmClassName: "+ rmClassName);
		log.debug("rmClass: "+ rmClass.getSimpleName());
		
		
		Map<String, Class> map = attributeType(rmClass);
		Map<String, Class> ret = new HashMap<String, Class>();
		for(String name : map.keySet()) {
			ret.put(toUnderscoreSeparated(name), map.get(name));
			
			log.debug("rmattribute: " +name +": "+ map.get(name));			
		}
		return ret;
	}
	
	/**
	 * Retrieves list of attribute names of given class; each name is converted
	 * from camel case to underscore delimited form
	 * 
	 * @param rmClassName
	 * @return
	 * @throws RMObjectBuildingException
	 */
	public Set<String> retrieveRMAttributeNames(String rmClassName) {
		Class rmClass = retrieveRMType(rmClassName);
		
		log.debug("----- rmClassName: "+ rmClassName);
		log.debug("rmClass: "+ rmClass.getSimpleName());
		
		Map<String, Class> map = attributeType(rmClass);
		Set<String> names = new LinkedHashSet<String>();
		for(String name : map.keySet()) {
			names.add(toUnderscoreSeparated(name));
			
			log.debug("name: " +name);
		}
		return names;
	}

	public String toCamelCase(String underscoreSeparated) {
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
			"ObjectVersionID"
	};

	
	/* logger */
	private static final Logger log = Logger.getLogger(RMInspector.class);

	// loaded rm type map
	private Map<String, Class> typeMap;
	private Map<String, Class> upperCaseMap;
	private static final Set<String> stringParsingTypes;
	private static final RMInspector soleInstance = new RMInspector();

	static {
		// so far only types from quantity.datetime
		stringParsingTypes = new HashSet<String>();
		String[] types = { "DvDate", "DvDateTime", "DvTime", "DvDuration",
				"DvPartialDate", "DvPartialTime" };
		stringParsingTypes.addAll(Arrays.asList(types));
	}
}
