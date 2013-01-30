
package br.com.zilics.archetypes.models.am.archetype.constraintmodel;

import java.util.List;

import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.annotation.NotNull;
import br.com.zilics.archetypes.models.rm.support.basic.Interval;

/**
 * Abstract model of constraint on any kind of attribute node
 *
 * @author Humberto
 */
public class CMultipleAttribute extends CAttribute {
	private static final long serialVersionUID = 1012134181907057841L;
	@NotNull
	@EqualsField
	private Cardinality cardinality;

	/**
	 * Default constructor
	 */
	public CMultipleAttribute() {}
	
	/**
	 * Another constructor
	 * @param rmAttributeName the name of the attribute
	 * @param existence its existence
	 * @param children its children
	 * @param cardinality the cardinality of the multiple attribute
	 */
	public CMultipleAttribute(String rmAttributeName, Interval<Integer> existence, List<CObject> children, Cardinality cardinality) {
		super(rmAttributeName, existence, children);
		this.cardinality = cardinality;
	}

	
    /**
     * Get the cardinality
     * @return Cardinality of this attribute constraint, if it
     * constraints a container attribute
     */
    public Cardinality getCardinality() {
        return cardinality;
    }

    /**
     * Set the cardinality
     * @param cardinality Cardinality of this attribute constraint, if it
     * constraints a container attribute
     */
    public void setCardinality(Cardinality cardinality) {
		assertMutable();
        this.cardinality = cardinality;
    }

    /**
     * Get the members
     * @return List of constraints representing members of the container value
     * of this attribute within the data. Semantics of the uniqueness and
     * ordering of items in the container are given by the cardinality
     */
    public List<CObject> getMembers() {
        return getList(getChildren());
    }

    /**
     * Set the members
     * @param members List of constraints representing members of the container value
     * of this attribute within the data. Semantics of the uniqueness and
     * ordering of items in the container are given by the cardinality
     */
    public void setMembers(List<CObject> members) {
		assertMutable();
        setChildren(members);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public CMultipleAttribute makeSimpleCopy() {
    	CMultipleAttribute result = new CMultipleAttribute();
    	ConstraintUtils.copyCMultipleAttributeProperties(this, result);
    	return result;
    }	
    
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
    	return "C_MULTIPLE_ATTRIBUTE[" + getRmAttributeName() + "]: " + getCanonicalPath();
    }    

}
