
package br.com.zilics.archetypes.models.am.archetype.constraintmodel;

import java.util.List;

import br.com.zilics.archetypes.models.rm.support.basic.Interval;

/**
 * Concrete model of constraint on a single-valued attribute node.
 * <p>The meaning of the inherited children attribute is that they are
 * alternatives</p>
 *
 * @author Humberto
 */
public class CSingleAttribute extends CAttribute {

	private static final long serialVersionUID = 8195682654871198362L;

	/**
	 * Default constructor
	 */
	public CSingleAttribute() {}
	
	/**
	 * Another constructor
	 * @param rmAttributeName the name of the attribute
	 * @param existence its existence
	 * @param children its children
	 */
	public CSingleAttribute(String rmAttributeName, Interval<Integer> existence, List<CObject> children) {
		super(rmAttributeName, existence, children);
	}
	
	/**
     * Get the alternatives
     * @return List of alternative constraints for the single
     * child of this attribute within the data
     */
    public List<CObject> getAlternatives() {
        return getList(getChildren());
    }

    /**
     * Set the alternatives
     * @param alternatives List of alternative constraints for the single
     * child of this attribute within the data
     */
    public void setAlternatives(List<CObject> alternatives) {
		assertMutable();
        setChildren(alternatives);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public CSingleAttribute makeSimpleCopy() {
    	CSingleAttribute result = new CSingleAttribute();
    	ConstraintUtils.copyCSingleAttributeProperties(this, result);
    	return result;
    }	
    
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
    	return "C_SINGLE_ATTRIBUTE[" + getRmAttributeName() + "]: " + getCanonicalPath();
    }

}
