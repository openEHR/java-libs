
package br.com.zilics.archetypes.models.am.archetype.constraintmodel;

import br.com.zilics.archetypes.models.rm.support.basic.Interval;

/**
 * Abstract parent type of domain-specific constrainer types,
 * to be defined in external packages
 *
 * @author Humberto
 */
public abstract class CDomainType extends CDefinedObject {

	private static final long serialVersionUID = -890722570826324929L;

	/**
	 * Default constructor
	 */
	public CDomainType() {}
	
    /**
     * Another constructor
     * @param occurrences how many times it occurs
     * @param nodeId the node id
     * @param assumedValue the assumed value
     */
    public CDomainType(Interval<Integer> occurrences, String nodeId, Object assumedValue) {
    	super(occurrences, nodeId, assumedValue);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public abstract CDomainType makeSimpleCopy();
    
    /**
     * A delegate method to access a package protected method of {@link ConstraintUtils}
     * @param destination the {@link CDomainType} that is the destination of the copy
     */
    protected void copyProperties(CDomainType destination) {
    	ConstraintUtils.copyCDefinedObjectProperties(this, destination);
    }


}
