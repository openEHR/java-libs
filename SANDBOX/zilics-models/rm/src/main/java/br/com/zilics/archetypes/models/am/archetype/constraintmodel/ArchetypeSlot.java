
package br.com.zilics.archetypes.models.am.archetype.constraintmodel;

import java.util.List;

import br.com.zilics.archetypes.models.am.archetype.assertion.Assertion;
import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.support.basic.Interval;
import br.com.zilics.archetypes.models.rm.utils.validation.ValidationResult;

/**
 * Constraint describing a slot where another archetype can occur
 *
 * @author Humberto
 */
public class ArchetypeSlot extends CReferenceObject {

	private static final long serialVersionUID = -2970278385598165446L;
	@EqualsField
	private List<Assertion> includes;
	@EqualsField
    private List<Assertion> excludes;

	/**
	 * Default constructor
	 */
	public ArchetypeSlot() {}
	
	/**
	 * Another constructor
     * @param rmTypeName the Reference Model type
     * @param occurrences how many times it occurs
     * @param nodeId the node id
	 * @param includes positive assertions
	 * @param excludes negative assertions
	 */
	public ArchetypeSlot(String rmTypeName, Interval<Integer> occurrences, String nodeId, List<Assertion> includes, List<Assertion> excludes) {
		super(rmTypeName, occurrences, nodeId);
		this.includes = includes;
		this.excludes = excludes;
	}
	
    /**
     * Get the includes
     * @return Constraints defining other archetypes that could be
     * included at this point
     */
    public List<Assertion> getIncludes() {
        return getList(includes);
    }

    /**
     * Set the includes
     * @param includes Constraints defining other archetypes that could be
     * included at this point
     */
    public void setIncludes(List<Assertion> includes) {
		assertMutable();
        this.includes = includes;
    }

    /**
     * Get the excludes
     * @return Constraints defining other archetypes that cannot be
     * included at this point
     */
    public List<Assertion> getExcludes() {
        return getList(excludes);
    }

    /**
     * Set the excludes
     * @param excludes Constraints defining other archetypes that cannot be
     * included at this point
     */
    public void setExcludes(List<Assertion> excludes) {
		assertMutable();
        this.excludes = excludes;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public ArchetypeSlot makeSimpleCopy() {
    	ArchetypeSlot result = new ArchetypeSlot();
    	ConstraintUtils.copyArchetypeSlotProperties(this, result);
    	return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
    	return "ARCHETYPE_SLOT: " + getCanonicalPath();
    }    

    /**
     * {@inheritDoc}
     */
    @Override
    protected void performValidation(ValidationResult result) {
    	super.performValidation(result);
    	if (getAnyAllowed() != null && getAnyAllowed()) {
    		if (includes != null || excludes != null)
    			result.addItem(this, "Invalid any allowed");
    	}
    }

}
