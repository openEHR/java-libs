
package br.com.zilics.archetypes.models.am.archetype.constraintmodel;

import br.com.zilics.archetypes.models.rm.support.basic.Interval;

/**
 * Abstract parent type of {@link CObject} subtypes that are
 * defined by value, i. e. whose definitions are actually in the archetype
 * rather than being by reference
 *
 * @author Humberto
 */
public abstract class CDefinedObject extends CObject {
	private static final long serialVersionUID = -66419546971278544L;
	private Object assumedValue;

	/**
	 * Default Constructor
	 */
	public CDefinedObject() {
	}
	
    /**
     * Another constructor
     * @param occurrences how many times it occurs
     * @param nodeId the node id
     */
    public CDefinedObject(Interval<Integer> occurrences, String nodeId) {
    	super(occurrences, nodeId);
    }
    
    /**
     * Another constructor
     * @param occurrences how many times it occurs
     * @param nodeId the node id
     * @param assumedValue the assumed value
     */
    public CDefinedObject(Interval<Integer> occurrences, String nodeId, Object assumedValue) {
    	super(occurrences, nodeId);
    	this.assumedValue = assumedValue;
    }    

	
	
    /**
     * Get the assumedValue
     * @return Value to be assumed if none sent in data
     */
    public Object getAssumedValue() {
        return assumedValue;
    }

    /**
     * Set the assumedValue
     * @param assumedValue Value to be assumed if none sent in data
     */
    public void setAssumedValue(Object assumedValue) {
		assertMutable();
        this.assumedValue = assumedValue;
    }
    
	/**
	 * If this constraint has an assumed value
	 * @return true if it has an assumed value
	 */
	public boolean hasAssumedValue() {
		return (getAssumedValue() != null);
	}    
    
    
    /**
     * {@inheritDoc}
     */
    @Override
    public abstract CDefinedObject makeSimpleCopy();

}
