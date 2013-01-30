
package br.com.zilics.archetypes.models.am.archetype.constraintmodel;

import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.annotation.NotEmpty;
import br.com.zilics.archetypes.models.rm.support.basic.Interval;
import br.com.zilics.archetypes.models.rm.utils.validation.ValidationResult;

/**
 * Reference to a constraint described in the same archetype, but outside the
 * main constraint structure. This is used to refer to constraints expressed
 * in terms of external resources, such as constraints on terminology value sets
 *
 * @author Humberto
 */
public class ConstraintRef extends CReferenceObject {

	private static final long serialVersionUID = 4635679852498268095L;
	@NotEmpty
	@EqualsField
	private String reference;
	
	/**
	 * Default constructor
	 */
	public ConstraintRef() {}
	
	/**
	 * Another constructor
     * @param rmTypeName the Reference Model type
     * @param occurrences how many times it occurs
     * @param nodeId the node id
	 * @param reference the reference
	 */
	public ConstraintRef(String rmTypeName, Interval<Integer> occurrences, String nodeId, String reference) {
		super(rmTypeName, occurrences, nodeId);
		this.reference = reference;
	}
	
    /**
     * Get the reference
     * @return Reference to a constraint in the archetype local ontology
     */
    public String getReference() {
        return reference;
    }

    /**
     * Set the reference
     * @param reference Reference to a constraint in the archetype local ontology
     */
    public void setReference(String reference) {
		assertMutable();
        this.reference = reference;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public ConstraintRef makeSimpleCopy() {
    	ConstraintRef result = new ConstraintRef();
    	ConstraintUtils.copyConstraintRefProperties(this, result);
    	return result;
    }    

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
    	return "CONSTRAINT_REF[" + reference + "]: " + getCanonicalPath();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void performValidation(ValidationResult result) {
    	super.performValidation(result);
    	if (getAnyAllowed() != null && getAnyAllowed())
    		result.addItem(this, "Any allowed invalid");
    }    
    
}
