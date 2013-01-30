package br.com.zilics.archetypes.models.am.archetype.constraintmodel;

import java.util.ArrayList;
import java.util.HashMap;

import br.com.zilics.archetypes.models.am.archetype.Archetype;

/**
 * Utility class for manipulating {@link ArchetypeConstraint} classes.
 * Some fields are package protected because we don't want the setter
 * method to be in the public API of the class. This utility class somehow mimics
 * the C "friend" class.
 * 
 * @author Humberto Naves
 */
public final class ConstraintUtils {
	private ConstraintUtils() {}
	
	/**
	 * Accessor method for the package protected field "ownerArchetype"
	 * @param constraint the constraint to set the field
	 * @param ownerArchetype the value of the field
	 */
	public static void setOwnerArchetype(ArchetypeConstraint constraint, Archetype ownerArchetype) {
		constraint.ownerArchetype = ownerArchetype;
	}

	/**
	 * Accessor method for the package protected field "canonicalPath"
	 * @param constraint the constraint to set the field
	 * @param canonicalPath the value of the field
	 */
	public static void setCanonicalPath(ArchetypeConstraint constraint, String canonicalPath) {
		constraint.canonicalPath = canonicalPath;
	}

	/**
	 * Accessor method for the package protected field "parent"
	 * @param obj the constraint to set the field
	 * @param parent the value of the field
	 */
	public static void setCObjectParent(CObject obj, CAttribute parent) {
		obj.parent = parent;
	}

	/**
	 * Accessor method for the package protected field "ownerConstraint"
	 * @param attribute the constraint to set the field
	 * @param ownerConstraint the value of the field
	 */
	public static void setCAttributeOwnerConstraint(CAttribute attribute, CComplexObject ownerConstraint) {
		attribute.ownerConstraint = ownerConstraint;
	}
	
	/**
	 * Accessor method for the package protected field "targetConstraint"
	 * @param ref the constraint to set the field
	 * @param targetConstraint the value of the field
	 */
	public static void setArchetypeInternalRefTargetConstraint(ArchetypeInternalRef ref, CObject targetConstraint) {
		ref.targetConstraint = targetConstraint;
	}
	
	/**
	 * Copy properties of an {@link CObject} to another.
	 * @see {@link ArchetypeConstraint#makeSimpleCopy()}
	 * @param source the source of the copy
	 * @param destination the destination of the copy
	 */
    final static void copyCObjectProperties(CObject source, CObject destination) {
    	destination.setOccurrences(source.getOccurrences());
    	destination.setNodeId(source.getNodeId());
    	destination.setAnyAllowed(source.getAnyAllowed());
    }
    
	/**
	 * Copy properties of an {@link CDefinedObject} to another.
	 * @see {@link ArchetypeConstraint#makeSimpleCopy()}
	 * @param source the source of the copy
	 * @param destination the destination of the copy
	 */
    final static void copyCDefinedObjectProperties(CDefinedObject source, CDefinedObject destination) {
    	copyCObjectProperties(source, destination);
    	destination.setAssumedValue(source.getAssumedValue());
    }    

	/**
	 * Copy properties of an {@link CReferenceObject} to another.
	 * @see {@link ArchetypeConstraint#makeSimpleCopy()}
	 * @param source the source of the copy
	 * @param destination the destination of the copy
	 */
    final static void copyCReferenceObjectProperties(CReferenceObject source, CReferenceObject destination) {
    	copyCObjectProperties(source, destination);
    	destination.setRmTypeName(source.getRmTypeName());
    }
    
	/**
	 * Copy properties of an {@link ArchetypeSlot} to another.
	 * @see {@link ArchetypeConstraint#makeSimpleCopy()}
	 * @param source the source of the copy
	 * @param destination the destination of the copy
	 */
    final static void copyArchetypeSlotProperties(ArchetypeSlot source, ArchetypeSlot destination) {
    	copyCReferenceObjectProperties(source, destination);
    	destination.setIncludes(source.getIncludes());
    	destination.setExcludes(source.getExcludes());
    }

	/**
	 * Copy properties of an {@link ConstraintRef} to another.
	 * @see {@link ArchetypeConstraint#makeSimpleCopy()}
	 * @param source the source of the copy
	 * @param destination the destination of the copy
	 */
    final static void copyConstraintRefProperties(ConstraintRef source, ConstraintRef destination) {
    	copyCReferenceObjectProperties(source, destination);
    	destination.setReference(source.getReference());
    }

	/**
	 * Copy properties of an {@link CPrimitiveObject} to another.
	 * @see {@link ArchetypeConstraint#makeSimpleCopy()}
	 * @param source the source of the copy
	 * @param destination the destination of the copy
	 */
    final static void copyCPrimitiveObjectProperties(CPrimitiveObject source, CPrimitiveObject destination) {
    	copyCDefinedObjectProperties(source, destination);
    	destination.setItem(source.getItem());
    }
    
	/**
	 * Copy properties of an {@link ArchetypeInternalRef} to another.
	 * @see {@link ArchetypeConstraint#makeSimpleCopy()}
	 * @param source the source of the copy
	 * @param destination the destination of the copy
	 */
    final static void copyArchetypeInternalRefProperties(ArchetypeInternalRef source, ArchetypeInternalRef destination) {
    	copyCReferenceObjectProperties(source, destination);
    	destination.setTargetPath(source.getTargetPath());
    }

	/**
	 * Copy properties of an {@link CComplexObject} to another.
	 * @see {@link ArchetypeConstraint#makeSimpleCopy()}
	 * @param source the source of the copy
	 * @param destination the destination of the copy
	 */
    final static void copyCComplexObjectProperties(CComplexObject source, CComplexObject destination) {
    	copyCDefinedObjectProperties(source, destination);
    	destination.setRmTypeName(source.getRmTypeName());
    	if (source.getAttributes() != null) {
    		HashMap<String, CAttribute> attributes = new HashMap<String, CAttribute>(source.getAttributes().size());
    		for(String key : source.getAttributes().keySet()) {
    			CAttribute attribute = source.getAttributes().get(key);
    			if (attribute != null)
    				attributes.put(key, attribute.makeSimpleCopy());
    			else
    				attributes.put(key, null);
    		}
    		destination.setAttributes(attributes);
    	}
    }

	/**
	 * Copy properties of an {@link CAttribute} to another.
	 * @see {@link ArchetypeConstraint#makeSimpleCopy()}
	 * @param source the source of the copy
	 * @param destination the destination of the copy
	 */
    final static void copyCAttributeProperties(CAttribute source, CAttribute destination) {
    	destination.setExistence(source.getExistence());
    	destination.setRmAttributeName(source.getRmAttributeName());
    	if (source.getChildren() != null) {
    		ArrayList<CObject> children = new ArrayList<CObject>(source.getChildren().size());
    		for(CObject child : source.getChildren()) {
    			if (child != null)
    				children.add(child.makeSimpleCopy());
    			else
    				children.add(null);
    		}
    		destination.setChildren(children);
    	} else destination.setChildren(null);
    }

	/**
	 * Copy properties of an {@link CSingleAttribute} to another.
	 * @see {@link ArchetypeConstraint#makeSimpleCopy()}
	 * @param source the source of the copy
	 * @param destination the destination of the copy
	 */
    final static void copyCSingleAttributeProperties(CSingleAttribute source, CSingleAttribute destination) {
    	copyCAttributeProperties(source, destination);
    }

	/**
	 * Copy properties of an {@link CMultipleAttribute} to another.
	 * @see {@link ArchetypeConstraint#makeSimpleCopy()}
	 * @param source the source of the copy
	 * @param destination the destination of the copy
	 */
    final static void copyCMultipleAttributeProperties(CMultipleAttribute source, CMultipleAttribute destination) {
    	copyCAttributeProperties(source, destination);
    	destination.setCardinality(source.getCardinality());
    }
    
	/**
	 * Copy properties of an {@link CDomainType} to another.
	 * @see {@link ArchetypeConstraint#makeSimpleCopy()}
	 * @param source the source of the copy
	 * @param destination the destination of the copy
	 */
    final static void copyCDomainTypeProperties(CDomainType source, CDomainType destination) {
    	copyCDefinedObjectProperties(source, destination);
    }

}
