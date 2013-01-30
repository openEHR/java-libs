package br.com.zilics.archetypes.models.am.utils.validation;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import br.com.zilics.archetypes.models.am.archetype.constraintmodel.ArchetypeInternalRef;
import br.com.zilics.archetypes.models.am.archetype.constraintmodel.CAttribute;
import br.com.zilics.archetypes.models.am.archetype.constraintmodel.CComplexObject;
import br.com.zilics.archetypes.models.am.archetype.constraintmodel.CMultipleAttribute;
import br.com.zilics.archetypes.models.am.archetype.constraintmodel.CObject;
import br.com.zilics.archetypes.models.am.archetype.constraintmodel.CPrimitiveObject;
import br.com.zilics.archetypes.models.am.archetype.constraintmodel.CSingleAttribute;
import br.com.zilics.archetypes.models.am.archetype.constraintmodel.ConstraintRef;
import br.com.zilics.archetypes.models.am.openehrprofile.datatypes.basic.CDvState;
import br.com.zilics.archetypes.models.am.openehrprofile.datatypes.quantity.CDvOrdinal;
import br.com.zilics.archetypes.models.am.openehrprofile.datatypes.quantity.CDvQuantity;
import br.com.zilics.archetypes.models.am.openehrprofile.datatypes.text.CCodePhrase;
import br.com.zilics.archetypes.models.am.template.openehrprofile.TemplateConstraint;
import br.com.zilics.archetypes.models.rm.RMObject;
import br.com.zilics.archetypes.models.rm.common.archetyped.Locatable;
import br.com.zilics.archetypes.models.rm.exception.IntrospectorException;
import br.com.zilics.archetypes.models.rm.utils.introspect.IntrospectorData;
import br.com.zilics.archetypes.models.rm.utils.introspect.RmClassData;
import br.com.zilics.archetypes.models.rm.utils.introspect.RmFieldData;

/**
 * Utility class to perform semantic validation of Composition objects against Templates or Archetypes 
 * @author Humberto Naves
 */
public final class SemanticValidationUtils {
	
	private SemanticValidationUtils() {}

	/**
	 * Performs a semantic validation against a {@link CObject}
	 * @param instance the instance to be validated
	 * @param constraint the constraint {@link CObject}
	 * @param templateConstraint if the constraints comes from a template, this is a duplicated argument (templateConstraint.archetypeConstraint == constraint)
	 * @param result the result of the semantic validation
	 */
	public static void validateCObject(Object instance, CObject constraint, TemplateConstraint templateConstraint, SemanticValidationResult result) {
		if (constraint instanceof CPrimitiveObject)
			validateCPrimitiveObject(instance, (CPrimitiveObject) constraint, templateConstraint, result);
		else {
			// Should be a RMObject
			if (!( instance instanceof RMObject )) {
				result.addItem(constraint, instance, "Invalid instance");
			} else {
				RMObject obj = (RMObject) instance;
				if (constraint instanceof CComplexObject)
					validateCComplexObject(obj, (CComplexObject) constraint, templateConstraint, result);
				else if (constraint instanceof ArchetypeInternalRef)
					validateArchetypeInternalRef(obj, (ArchetypeInternalRef) constraint, templateConstraint, result);
				else if (constraint instanceof ConstraintRef)
					validateConstraintRef(obj, (ConstraintRef) constraint, result);
				else if (constraint instanceof CCodePhrase)
					validateCCodePhrase(obj, (CCodePhrase) constraint, result);
				else if (constraint instanceof CDvQuantity)
					validateCDvQuantity(obj, (CDvQuantity) constraint, result);
				else if (constraint instanceof CDvOrdinal)
					validateCDvOrdinal(obj, (CDvOrdinal) constraint, result);
				else if (constraint instanceof CDvState)
					validateCDvState(obj, (CDvState) constraint, result);
			}
		}		
	}

	/**
	 * Check if the instance matches the constraint
	 * Auxiliary method for validation of multiple attributes (because we don't have a match for lists).
	 * @param instance the instance to be checked
	 * @param constraint the constraint to be checked against
	 * @param templateConstraint the template constraint
	 * @return true if it matches
	 */
	private static boolean isCObject(Object instance, CObject constraint, TemplateConstraint templateConstraint) {
		if (constraint instanceof CPrimitiveObject)
			return isCPrimitiveObject(instance, (CPrimitiveObject) constraint, templateConstraint);
		else {
			if (!( instance instanceof RMObject )) {
				return false;
			} else {
				RMObject obj = (RMObject) instance;
				if (constraint instanceof CComplexObject)
					return isCComplexObject(obj, (CComplexObject) constraint, templateConstraint);
				else if (constraint instanceof ArchetypeInternalRef)
					return isArchetypeInternalRef(obj, (ArchetypeInternalRef) constraint, templateConstraint);
				else if (constraint instanceof ConstraintRef)
					return isConstraintRef(obj, (ConstraintRef) constraint, templateConstraint);
				else if (constraint instanceof CCodePhrase)
					return isCCodePhrase(obj, (CCodePhrase) constraint, templateConstraint);
				else if (constraint instanceof CDvQuantity)
					return isCDvQuantity(obj, (CDvQuantity) constraint, templateConstraint);
				else if (constraint instanceof CDvOrdinal)
					return isCDvOrdinal(obj, (CDvOrdinal) constraint, templateConstraint);
				else if (constraint instanceof CDvState)
					return isCDvState(obj, (CDvState) constraint, templateConstraint);
			}
		}
		return false;
	}
	
	/**
	 * Check if the instance matches the constraint for primitive types
	 * @param instance the instance to be checked
	 * @param primitive the constraint to be checked against
	 * @param templateConstraint the template constraint
	 * @return true if it matches
	 */
	private static boolean isCPrimitiveObject(Object instance, CPrimitiveObject primitive, TemplateConstraint templateConstraint) {
		return Boolean.TRUE.equals(primitive.getAnyAllowed()) ? true : primitive.getItem().isValidType(instance);
	}
	
	/**
	 * Performs a semantic validation against a {@link CPrimitiveObject}
	 * @param instance the instance to be validated
	 * @param primitive the constraint
	 * @param templateConstraint the template constraint
	 * @param result the result of the semantic validation
	 */
	private static void validateCPrimitiveObject(Object instance, CPrimitiveObject primitive, TemplateConstraint templateConstraint, SemanticValidationResult result) {
		if(Boolean.TRUE.equals(primitive.getAnyAllowed())){
			return;
		}
		if (!primitive.getItem().isValidValue(instance)) {
			result.addItem(primitive, instance, "Invalid primitive value");
		}
	}

	/**
	 * Check if the instance matches the constraint for {@link CComplexObject}
	 * @param instance the instance to be checked
	 * @param constraint the constraint to be checked against
	 * @param templateConstraint the template constraint
	 * @return true if it matches
	 */
	private static boolean isCComplexObject(RMObject instance, CComplexObject constraint, TemplateConstraint templateConstraint) {
		RmClassData rmClassData = IntrospectorData.getRmClassDataByRmClassName(constraint.getRmTypeName());
		if (!rmClassData.getJavaClass().isInstance(instance)) return false;
		
		// Check the node id for locatables
		if (instance instanceof Locatable) {
			String nodeId = constraint.getNodeId();
			Locatable locatable = (Locatable) instance;
			if (constraint.getParent() == null) {
				nodeId = constraint.getOwnerArchetype().getArchetypeId().getValue();
				
				if (locatable.getArchetypeDetails() != null)
					return locatable.getArchetypeDetails().getArchetypeId().getValue().equals(nodeId);
			} else {
			
				if (locatable.getArchetypeNodeId() != null)
					return locatable.getArchetypeNodeId().equals(nodeId);
			}
		}
		return true;
	}

	/**
	 * Performs a semantic validation against a {@link CComplexObject}
	 * @param instance the instance to be validated
	 * @param constraint the constraint
	 * @param templateConstraint the template constraint
	 * @param result the result of the semantic validation
	 */
	private static void validateCComplexObject(RMObject instance, CComplexObject constraint, TemplateConstraint templateConstraint, SemanticValidationResult result) {
		RmClassData rmClassData = IntrospectorData.getRmClassDataByRmClassName(constraint.getRmTypeName());
		if (!rmClassData.getJavaClass().isInstance(instance)) {
			result.addItem(constraint, instance, "Instance is not a " + constraint.getRmTypeName());
		}
		
		if (instance instanceof Locatable) {
			String nodeId = constraint.getNodeId();
			Locatable locatable = (Locatable) instance;
			if (constraint.getParent() == null) {
				nodeId = constraint.getOwnerArchetype().getArchetypeId().getValue();
				if (locatable.getArchetypeDetails() != null)
					if (!locatable.getArchetypeDetails().getArchetypeId().getValue().equals(nodeId))
						result.addItem(constraint, instance, "Invalid node id");
			} else {
			
				if (locatable.getArchetypeNodeId() != null)
					if (!locatable.getArchetypeNodeId().equals(nodeId))
						result.addItem(constraint, instance, "Invalid node id");
			}

		}

		if (constraint.getAttributes() != null) {
			for(String attributeName : constraint.getAttributes().keySet()) {
				CAttribute attribute = constraint.getAttributes().get(attributeName);
				if (attribute == null || attribute.getChildren() == null) continue;
				RmFieldData rmFieldData = rmClassData.getRmFieldByRmName(attributeName);
				Object val;
				try {
					val = rmFieldData.getValue(instance);
				} catch (IntrospectorException e) {
					result.addItem(constraint, instance, "Introspector exception", e);
					continue;
				}
				if (val == null) {
					if (attribute.getExistence().getLower() > 0) {
						result.addItem(constraint, instance, "Attribute " + attributeName + " is required");
					}
				} else
					validateCAttribute(val, attribute, templateConstraint, result);
			}
		}
	}
	
	/**
	 * Performs a semantic validation against a {@link CAttribute}
	 * @param instance the instance to be validated
	 * @param attribute the constraint
	 * @param templateConstraint the template constraint
	 * @param result the result of the semantic validation
	 */
	private static void validateCAttribute(Object val, CAttribute attribute, TemplateConstraint templateConstraint, SemanticValidationResult result) {
		if (attribute instanceof CSingleAttribute)
			validateCSingleAttribute(val, (CSingleAttribute) attribute, templateConstraint, result);
		else
			validateCMultipleAttribute(val, (CMultipleAttribute) attribute, templateConstraint, result);
	}
	
	/**
	 * Performs a semantic validation against a list of {@link CObject}
	 * @param instance the instance to be validated
	 * @param attribute the constraint that contains a list of {@link CObject}
	 * @param templateConstraint the template constraint
	 * @param result the result of the semantic validation
	 */
	private static void validateListCObject(Object val, CAttribute attribute, TemplateConstraint templateConstraint, SemanticValidationResult result) {
		if (templateConstraint != null) {
			List<TemplateConstraint> children = templateConstraint.getChildrenFromAttribute(attribute.getRmAttributeName());
			for(TemplateConstraint child : children) {
				if (isCObject(val, child.getArchetypeConstraint(), child)) {
					validateCObject(val, child.getArchetypeConstraint(), child, result);
					break;
				}
			}
		} else {
			for(CObject child : attribute.getChildren()) {
				if (isCObject(val, child, null)) {
					validateCObject(val, child, templateConstraint, result);
					break;
				}
			}
		}
	}

	/**
	 * Performs a semantic validation against a {@link CSingleAttribute}
	 * @param instance the instance to be validated
	 * @param attribute the constraint
	 * @param templateConstraint the template constraint
	 * @param result the result of the semantic validation
	 */
	private static void validateCSingleAttribute(Object val, CSingleAttribute attribute, TemplateConstraint templateConstraint, SemanticValidationResult result) {
		validateListCObject(val, attribute, templateConstraint, result);
	}

	/**
	 * Performs a semantic validation against a {@link CMultipleAttribute}
	 * @param instance the instance to be validated
	 * @param attribute the constraint
	 * @param templateConstraint the template constraint
	 * @param result the result of the semantic validation
	 */
	private static void validateCMultipleAttribute(Object val, CMultipleAttribute attribute, TemplateConstraint templateConstraint, SemanticValidationResult result) {
		if (val instanceof Collection) {
			Collection<?> collection = (Collection<?>) val;
			for(Object e : collection) {
				if (e == null) continue;
				validateListCObject(e, attribute, templateConstraint, result);
			}
		} else if (val instanceof Map) {
			Map<?, ?> map = (Map<?, ?>) val;
			for(Object k:  map.keySet()) {
				Object v = map.get(k);
				if (v == null) continue;
				validateListCObject(v, attribute, templateConstraint, result);
			}
		} else if (val.getClass().isArray()) {
			int len = Array.getLength(val);
			for(int i = 0; i < len; i++) {
				Object e = Array.get(val, i);
				if (e == null) continue;
				validateListCObject(e, attribute, templateConstraint, result);
			}
		}
	}
	
	/**
	 * 
	 * @param instance
	 * @param constraint
	 * @param templateConstraint
	 * @param result
	 */
	private static void validateArchetypeInternalRef(RMObject instance, ArchetypeInternalRef constraint, TemplateConstraint templateConstraint, SemanticValidationResult result) {
		validateCObject(instance, constraint.getTargetConstraint(), templateConstraint, result);
	}
	
	/**
	 * Check if the instance matches the constraint for internal references
	 * @param instance the instance to be checked
	 * @param constraint the constraint to be checked against
	 * @param templateConstraint the template constraint
	 * @return true if it matches
	 */
	private static boolean isArchetypeInternalRef(RMObject instance, ArchetypeInternalRef constraint, TemplateConstraint templateConstraint) {
		return isCObject(instance, constraint.getTargetConstraint(), templateConstraint);
	}
	
	private static void validateConstraintRef(RMObject instance, ConstraintRef ref, SemanticValidationResult result) {
		result.addItem(ref, instance, "Not implemented yet");
	}
	
	/**
	 * Check if the instance matches the constraint for constraint references
	 * @param instance the instance to be checked
	 * @param constraint the constraint to be checked against
	 * @param templateConstraint the template constraint
	 * @return true if it matches
	 */
	private static boolean isConstraintRef(RMObject instance, ConstraintRef ref, TemplateConstraint templateConstraint) {
		return false;
	}
	
	/**
	 * 
	 * @param instance
	 * @param code
	 * @param result
	 */
	private static void validateCCodePhrase(RMObject instance, CCodePhrase code, SemanticValidationResult result) {
		result.addItem(code, instance, "Not implemented yet");		
	}
	
	/**
	 * Check if the instance matches the constraint for {@link CCodePhrase}
	 * @param instance the instance to be checked
	 * @param constraint the constraint to be checked against
	 * @param templateConstraint the template constraint
	 * @return true if it matches
	 */
	private static boolean isCCodePhrase(RMObject instance, CCodePhrase code, TemplateConstraint templateConstraint) {
		return false;
	}

	/**
	 * 
	 * @param instance
	 * @param quantity
	 * @param result
	 */
	private static void validateCDvQuantity(RMObject instance, CDvQuantity quantity, SemanticValidationResult result) {
		result.addItem(quantity, instance, "Not implemented yet");		
	}
	
	/**
	 * Check if the instance matches the constraint for {@link CDvQuantity}
	 * @param instance the instance to be checked
	 * @param constraint the constraint to be checked against
	 * @param templateConstraint the template constraint
	 * @return true if it matches
	 */
	private static boolean isCDvQuantity(RMObject instance, CDvQuantity quantity, TemplateConstraint templateConstraint) {
		return false;
	}

	/**
	 * 
	 * @param instance
	 * @param ordinal
	 * @param result
	 */
	private static void validateCDvOrdinal(RMObject instance, CDvOrdinal ordinal, SemanticValidationResult result) {
		result.addItem(ordinal, instance, "Not implemented yet");		
	}
	
	/**
	 * Check if the instance matches the constraint for {@link CDvOrdinal}
	 * @param instance the instance to be checked
	 * @param constraint the constraint to be checked against
	 * @param templateConstraint the template constraint
	 * @return true if it matches
	 */
	private static boolean isCDvOrdinal(RMObject instance, CDvOrdinal ordinal, TemplateConstraint templateConstraint) {
		return false;
	}

	/**
	 * 
	 * @param instance
	 * @param state
	 * @param result
	 */
	private static void validateCDvState(RMObject instance, CDvState state, SemanticValidationResult result) {
		result.addItem(state, instance, "Not implemented yet");		
	}

	/**
	 * Check if the instance matches the constraint for {@link CDvState}
	 * @param instance the instance to be checked
	 * @param constraint the constraint to be checked against
	 * @param templateConstraint the template constraint
	 * @return true if it matches
	 */
	private static boolean isCDvState(RMObject instance, CDvState state, TemplateConstraint templateConstraint) {
		return false;
	}

}
