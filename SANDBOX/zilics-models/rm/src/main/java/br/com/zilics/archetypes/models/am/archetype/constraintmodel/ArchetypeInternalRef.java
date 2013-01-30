
package br.com.zilics.archetypes.models.am.archetype.constraintmodel;


import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.annotation.Ignore;
import br.com.zilics.archetypes.models.rm.annotation.NotEmpty;
import br.com.zilics.archetypes.models.rm.support.basic.Interval;
import br.com.zilics.archetypes.models.rm.utils.validation.ValidationResult;

/**
 * A constraint defined by proxy, using a reference to an object
 * constraint defined elsewhere in the same archetype
 *
 * @author Humberto
 */
public class ArchetypeInternalRef extends CReferenceObject {

	private static final long serialVersionUID = 3351415220215510761L;
	@NotEmpty
	@EqualsField
	private String targetPath;
	
	@Ignore
	CObject targetConstraint;

	/**
	 * Default constructor
	 */
	public ArchetypeInternalRef() {}
	
	/**
	 * Another constructor
     * @param rmTypeName the Reference Model type
     * @param occurrences how many times it occurs
     * @param nodeId the node id
	 * @param targetPath the target path
	 */
	public ArchetypeInternalRef(String rmTypeName, Interval<Integer> occurrences, String nodeId, String targetPath) {
		super(rmTypeName, occurrences, nodeId);
		this.targetPath = targetPath;
	}

	/**
     * Get the targetPath
     * @return Reference to an object node using archetype path notation
     */
    public String getTargetPath() {
        return targetPath;
    }

    /**
     * Set the targetPath
     * @param targetPath Reference to an object node using archetype path notation
     */
    public void setTargetPath(String targetPath) {
		assertMutable();
        this.targetPath = targetPath;
    }
    
    /**
     * Get the referenced target constraint (actually a simple copy of the target constraint)
     * @return the referenced target constraint
     */
    public CObject getTargetConstraint() {
    	return targetConstraint;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public ArchetypeInternalRef makeSimpleCopy() {
    	ArchetypeInternalRef result = new ArchetypeInternalRef();
    	ConstraintUtils.copyArchetypeInternalRefProperties(this, result);
    	return result;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
    	return "ARCHETYPE_INTERNAL_REF[" + targetPath + "]: " + getCanonicalPath();
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
