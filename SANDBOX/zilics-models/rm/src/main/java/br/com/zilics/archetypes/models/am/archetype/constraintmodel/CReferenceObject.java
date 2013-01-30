
package br.com.zilics.archetypes.models.am.archetype.constraintmodel;

import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.annotation.NotEmpty;
import br.com.zilics.archetypes.models.rm.support.basic.Interval;

/**
 * Abstract parent type of {@link CObject} subtypes that are defined
 * by reference
 *
 * @author Humberto
 */
public abstract class CReferenceObject extends CObject {

	private static final long serialVersionUID = 394062499745523118L;
	
	@NotEmpty
	@EqualsField
	private String rmTypeName;

	/**
	 * Default constructor
	 */
	public CReferenceObject() {}
	
	/**
	 * Another constructor
     * @param rmTypeName the Reference Model type
     * @param occurrences how many times it occurs
     * @param nodeId the node id
	 */
	public CReferenceObject(String rmTypeName, Interval<Integer> occurrences, String nodeId) {
		super(occurrences, nodeId);
		this.rmTypeName = rmTypeName;
	}
	
    /**
     * {@inheritDoc}
     */
	@Override
    public String getRmTypeName() {
        return rmTypeName;
    }

    /**
     * Set the rmTypeName
     * @param rmTypeName Refence Model type that this node corresponds to
     */
    public void setRmTypeName(String rmTypeName) {
		assertMutable();
        this.rmTypeName = rmTypeName;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public abstract CReferenceObject makeSimpleCopy();


}
