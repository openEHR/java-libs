/*
 * component:   "openEHR Reference Java Implementation"
 * description: "Class DataValidatorImpl"
 * keywords:    "validator"
 *
 * author:      "Rong Chen <rong.acode@gmail.com>"
 * support:     "openEHR Java Project <ref_impl_java@openehr.org>"
 * copyright:   "Copyright (c) 2008 Cambio Healthcare Systems, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL$"
 * revision:    "$LastChangedRevision$"
 * last_change: "$LastChangedDate$"
 */
package org.openehr.validation;

import java.lang.reflect.Method;
import java.util.*;

import org.apache.log4j.Logger;
import org.openehr.am.archetype.*;
import org.openehr.am.archetype.constraintmodel.*;
import org.openehr.am.archetype.ontology.ArchetypeOntology;
import org.openehr.am.archetype.ontology.ArchetypeTerm;
import org.openehr.rm.common.archetyped.Locatable;
import org.openehr.rm.support.basic.Interval;

/**
 * Implementation of an archetype-based data validator
 * 
 * @author rong.chen
 */
public class DataValidatorImpl implements DataValidator {

	/**
	 * Validates the data using given archetype
	 * 
	 * @param data 
	 * @param archetype
	 * @return errors empty if data pass validation without error
	 */
	public List<ValidationError> validate(Locatable data, Archetype archetype)
			throws Exception {
		
		log.debug("validate archetype: " + archetype.getArchetypeId());
		
		List<ValidationError> errors = new ArrayList<ValidationError>();		
		validateComplex(archetype.getDefinition(), data,  
				Locatable.PATH_SEPARATOR, errors, archetype);
		return errors;
	}
	
	void validateComplex(CComplexObject ccobj, Object object, String path, 
			List<ValidationError> errors, Archetype archetype) throws Exception {
		
		log.debug("validate CComplexObject of type: " + ccobj.getRmTypeName()
				+ " at path: " + path);		
		
		ValidationError error = null;
		String newPath = null;
		
		// loop through the attributes
		for(CAttribute cattr : ccobj.getAttributes()) {
			
			Object attribute = fetchAttribute(object, cattr);
			
			log.debug("attribute " + cattr.getRmAttributeName() + 
					" isRequired: " + cattr.isRequired() + 
					", attribute == null ? " + (attribute == null));
			
			newPath = path;
			if(! path.equals(Locatable.PATH_SEPARATOR)) {
				newPath += Locatable.PATH_SEPARATOR;
			}
			newPath += cattr.getRmAttributeName();
			
			if(cattr.isRequired() && attribute == null) {
				
				log.debug("ERROR --> attribute missing at " + path);
				
				error = new ValidationError(path, cattr.path(), 
						ErrorType.ATTRIBUTE_MISSING);
				errors.add(error);
			
			} else if(cattr.isNotAllowed() && attribute != null) {
				
				error = new ValidationError(path, cattr.path(), 
						ErrorType.ATTRIBUTE_NOT_ALLOWED);
				errors.add(error);
			
			} else if(attribute != null) {
				if(cattr instanceof CSingleAttribute) {
					validateSingleAttribute((CSingleAttribute) cattr, 
							attribute, newPath, errors, archetype);
				} else {
					validateMultipleAttribute((CMultipleAttribute) cattr,
							attribute, newPath, errors, archetype);
				}
				
			}
		}		
	}
	
	// TODO how to handle alternatives of single attribute
	void validateSingleAttribute(CSingleAttribute cattr, Object attribute, 
			String path, List<ValidationError> errors, Archetype archetype) throws Exception {
		
		log.debug("validateSingleAttribute..");
		
		if(cattr.alternatives().size() > 1) {
			List<ValidationError> newErrors = null;
			for(CObject cobj : cattr.alternatives()) {
				newErrors = new ArrayList<ValidationError>();
				validateObject(cobj, attribute, path, newErrors, archetype);
				if(newErrors.size() == 0) {
					return;
				}
			}
		} else if(cattr.alternatives().size() == 1) {
			CObject cobj = cattr.alternatives().get(0);
			validateObject(cobj, attribute, path, errors, archetype);
		}
		
	}
	
	void validateMultipleAttribute(CMultipleAttribute cattr, Object attribute, 
			String path, List<ValidationError> errors, Archetype archetype) 
				throws Exception {
		
		log.debug("validateMultipleAttribute..");
		
		Cardinality cardinality = cattr.getCardinality();
		List<CObject> children = cattr.getChildren();
		Collection<Object> values = null;
		
		if(cardinality.isList()) {
			values = (List<Object>) attribute;
		} else if(cardinality.isSet()) {
			values = (Set<Object>) attribute;
		} else {
			log.warn("bag unsupported, type: " + attribute.getClass());
			values = (List<Object>) attribute;
		}
		
		Interval<Integer> interval = cardinality.getInterval();
		
		log.debug("cardinality.interval: " + interval);
		
		ValidationError error = null;
		
		if(children == null) {
			return;
		}
		
		if(interval.getLower() != null
				&& interval.getLower() > values.size()) {
			
			error = new ValidationError(path, cattr.path(), 
					ErrorType.ITEMS_TOO_FEW);
			errors.add(error);
			return;
		
		} else if(interval.getUpper() != null
				&& interval.getUpper() < values.size()) {
			
			error = new ValidationError(path, cattr.path(), 
					ErrorType.ITEMS_TOO_MANY);
			errors.add(error);
			return;
		}
		
		// TODO if(cardinality.isOrdered()) ?!
		
		log.debug("validating total cobj: " + children.size() + 
				", values: " + values.size());
			
		List<Object> objects = null;
		String newPath = null;
		
		for(CObject cobj : children) {
		
			log.debug("validating sub-cobj at: " + cobj.path());			
			
			objects = findMatchingNodes(values, cobj.getNodeID());
			
			// TODO occurrences should be checked here
			// the difficult is to locate possible match 
			// for objects constrained by occurrences
			
			Interval<Integer> occurrences = cobj.getOccurrences();
			
			if(occurrences != null) {
				if(occurrences.getLower() != null
							&& occurrences.getLower() > objects.size()) {
					
					error = new ValidationError(path, cobj.path(), 
							ErrorType.OCCURRENCES_TOO_FEW);
					errors.add(error);
					return; 
				
				} else if(occurrences.getUpper() != null
							&& occurrences.getUpper() < objects.size()) {
				
					error = new ValidationError(path, cobj.path(), 
							ErrorType.OCCURRENCES_TOO_MANY);
					errors.add(error);
					return; 
				}
			}
			
			for(Object obj : objects) {
				newPath = path + "[" + cobj.getNodeID() + "]";
				validateObject(cobj, obj, newPath, errors, archetype);
			} 
		}
	}
	
	/*
	 * Finds objects by matching nodeId
	 */
	private List<CObject> findObjectsByNodeId(List<CObject> objects, 
			String nodeId) {
		
		List<CObject> list = new ArrayList<CObject>();
		for(CObject obj : objects) {
			if(nodeId.matches(obj.getNodeID())) {
				list.add(obj);
			}
		}
		return list;
	}
	
	/**
	 * Finds matching value node using AT code of CObject
	 * 
	 * @param values
	 * @param code
	 * @return empty list if not found
	 */
	List<Object> findMatchingNodes(Collection<Object> values, String code) {		
		List<Object> objects = new ArrayList<Object>(); 
		Locatable lo = null;		
		for(Object value : values) {
			if(value instanceof Locatable) {
				lo = (Locatable) value;
				if(code.equals(lo.getArchetypeNodeId())) {
					log.debug("value found for code: " + code);
					objects.add(lo);
				}
			} else {
				log.warn("trying to find matching value on un-pathable obj..");
			}
		}
		return objects;
	}
	
	void validateObject(CObject cobj, Object value, String path, 
			List<ValidationError> errors, Archetype archetype) throws Exception {
		
		log.debug("validate CObject..");
		
		if(cobj instanceof CComplexObject) {
		
			validateComplex((CComplexObject) cobj, value, path, errors, archetype);
		
		} else if(cobj instanceof CDomainType) {
			
			validateDomain((CDomainType) cobj, value, path, errors);
		
		} else if(cobj instanceof CPrimitiveObject) {
			
			validatePrimitive((CPrimitiveObject) cobj, value, path, errors);
			
		} else {
			log.error("Unknown CObject type..");
		}
	}
	
	void validateDomain(CDomainType cdomain, Object value, String path, 
			List<ValidationError> errors) {
		
		log.debug("validate CDomaingType..");
		
		if( ! cdomain.validValue(value)) {
			
			log.debug("error found at " + cdomain.path());
			
			errors.add(new ValidationError(path, cdomain.path(), 
					ErrorType.DOMAIN_TYPE_VALUE_ERROR)); // DUMMY ERROR TYPE
		}
	}
	
	void validatePrimitive(CPrimitiveObject cpo, Object value, String path,
			List<ValidationError> errors) {
		
		log.debug("validate CPrimitiveObject..");
		
		if(! cpo.getItem().validValue(value)) {
			errors.add(new ValidationError(path, cpo.path(), 
					ErrorType.PRIMITIVE_TYPE_VALUE_ERROR)); // DUMMY ERROR TYPE
		}
	}
	
	Object fetchAttribute(Object object, CAttribute cattr) throws Exception {
		String getter = "get" + upperFirstLetter(cattr.getRmAttributeName());
		Method method = null;
		method = object.getClass().getMethod(getter, null);
		return method.invoke(object, null);
	}
	
	String upperFirstLetter(String value) {
		return value.substring(0, 1).toUpperCase() + value.substring(1);
	}
	
	/**
	 * Fetches name of the archetype term of given AT code
	 * 
	 * @param code
	 * @param archetype
	 * @return
	 */
	String getTermText(String code, Archetype archetype) {
		ArchetypeOntology ont = archetype.getOntology();
		String lang = archetype.getOriginalLanguage().getCodeString();
		ArchetypeTerm term = ont.termDefinition(lang, code);
		if(term != null) {
			return term.getText();
		}
		return null;
		
	}
	
	private static Logger log = Logger.getLogger(DataValidator.class);

}

/*
 *  ***** BEGIN LICENSE BLOCK *****
 *  Version: MPL 1.1/GPL 2.0/LGPL 2.1
 *
 *  The contents of this file are subject to the Mozilla Public License Version
 *  1.1 (the 'License'); you may not use this file except in compliance with
 *  the License. You may obtain a copy of the License at
 *  http://www.mozilla.org/MPL/
 *
 *  Software distributed under the License is distributed on an 'AS IS' basis,
 *  WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 *  for the specific language governing rights and limitations under the
 *  License.
 *
 *  The Original Code is DataValidatorImpl.java
 *
 *  The Initial Developer of the Original Code is Rong Chen.
 *  Portions created by the Initial Developer are Copyright (C) 2008
 *  the Initial Developer. All Rights Reserved.
 *
 *  Contributor(s):
 *
 * Software distributed under the License is distributed on an 'AS IS' basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 *  ***** END LICENSE BLOCK *****
 */