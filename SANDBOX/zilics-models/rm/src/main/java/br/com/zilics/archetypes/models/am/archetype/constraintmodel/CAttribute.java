
package br.com.zilics.archetypes.models.am.archetype.constraintmodel;

import java.util.List;

import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.annotation.Ignore;
import br.com.zilics.archetypes.models.rm.annotation.NotEmpty;
import br.com.zilics.archetypes.models.rm.annotation.NotNull;
import br.com.zilics.archetypes.models.rm.support.basic.Interval;
import br.com.zilics.archetypes.models.rm.utils.validation.ValidationResult;

/**
 * Abstract model of constraint on any kind of attribute node
 *
 * @author Humberto
 */
public abstract class CAttribute extends ArchetypeConstraint {
	private static final long serialVersionUID = -8056994924329254516L;
	@NotEmpty
	@EqualsField
	private String rmAttributeName;
	@NotNull
	@EqualsField
    private Interval<Integer> existence;
	@EqualsField
	@NotNull
    private List<CObject> children;
	
	@Ignore
	CComplexObject ownerConstraint;

	/**
	 * Default constructor
	 */
	public CAttribute() {}
	
	/**
	 * Another constructor
	 * @param rmAttributeName the name of the attribute
	 * @param existence its existence
	 * @param children its children
	 */
	public CAttribute(String rmAttributeName, Interval<Integer> existence, List<CObject> children) {
		this.rmAttributeName = rmAttributeName;
		this.existence = existence;
		this.children = children;
	}
	
    /**
     * Get the rmAttributeName
     * @return Reference Model attribute within the enclosing type represented
     * by a {@link CObject}
     */
    public String getRmAttributeName() {
        return rmAttributeName;
    }

    /**
     * Set the rmAttributeName
     * @param rmAttributeName Reference Model attribute within the enclosing type represented
     * by a {@link CObject}
     */
    public void setRmAttributeName(String rmAttributeName) {
		assertMutable();
        this.rmAttributeName = rmAttributeName;
    }

    /**
     * Get the existence
     * @return Constraint on every attribute, regardless of whether it is singular
     * or of a container type, which indicates whether its target object
     * exists or not (i. e. is mandatory or not)
     */
    public Interval<Integer> getExistence() {
        return existence;
    }

    /**
     * Set the existence
     * @param existence Constraint on every attribute, regardless of whether it is singular
     * or of a container type, which indicates whether its target object
     * exists or not (i. e. is mandatory or not)
     */
    public void setExistence(Interval<Integer> existence) {
		assertMutable();
        this.existence = existence;
    }

    /**
     * Get the children
     * @return Child {@link CObject} nodes. Each such node represents a constraint on
     * the type of this attribute in its reference model. Multiples occur both for
     * multiple items in the case of container attributes, and alternatives in
     * the case of singular attributes
     */
    public List<CObject> getChildren() {
        return getList(children);
    }

    /**
     * Set the children
     * @param children Child {@link CObject} nodes. Each such node represents a constraint on
     * the type of this attribute in its reference model. Multiples occur both for
     * multiple items in the case of container attributes, and alternatives in
     * the case of singular attributes
     */
    public void setChildren(List<CObject> children) {
		assertMutable();
        this.children = children;
    }
    
    /**
     * Get the owner ({@link CComplexObject}) of this attribute
     * @return the owner of this attribute
     */
    public CComplexObject getOwnerConstraint() {
    	return ownerConstraint;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public abstract CAttribute makeSimpleCopy();
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void performValidation(ValidationResult result) {
    	super.performValidation(result);
    	if (existence == null) return;
    	//Check the existence attribute
    	if (existence.isLowerUnbounded() || existence.isUpperUnbounded() ||
        		!existence.isLowerIncluded() || !existence.isUpperIncluded() ||
        	    (existence.getLower() != null && existence.getLower() < 0) || (existence.getUpper() != null && existence.getUpper() > 1))
        		result.addItem(this, "Invalid existence attribute");
    }    

}
