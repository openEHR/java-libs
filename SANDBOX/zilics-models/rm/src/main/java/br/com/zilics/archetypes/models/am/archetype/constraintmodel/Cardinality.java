
package br.com.zilics.archetypes.models.am.archetype.constraintmodel;

import br.com.zilics.archetypes.models.am.AMObject;
import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.annotation.NotNull;
import br.com.zilics.archetypes.models.rm.annotation.RmField;
import br.com.zilics.archetypes.models.rm.support.basic.Interval;
import br.com.zilics.archetypes.models.rm.utils.validation.ValidationResult;

/**
 * Express constraints on the cardinality of container objects which
 * are the values of multiple-valued attributes, including uniqueness and ordering,
 * providing the means to state that a container acts like a logical list,
 * set or bag. <p>The cardinality cannot contradict the cardinality of the
 * corresponding attribute within the relevant reference model</p>
 *
 * @author Humberto
 */
public class Cardinality extends AMObject {

	private static final long serialVersionUID = -2815266926287859850L;
	@RmField("is_ordered")
	@EqualsField
	private boolean ordered;
	@RmField("is_unique")
	@EqualsField
    private boolean unique;
	@NotNull
	@EqualsField
    private Interval<Integer> interval;

	/**
	 * Default constructor
	 */
	public Cardinality() {}
	
	/**
	 * Another constructor
	 * @param ordered if ordered cardinality
	 * @param unique  if unique cardinality
	 * @param interval the interval of this cardinality
	 */
	public Cardinality(boolean ordered, boolean unique, Interval<Integer> interval) {
		this.ordered = ordered;
		this.unique = unique;
		this.interval = interval;
	}
	
    /**
     * Get the ordered
     * @return True if the members of the container attribute
     * to which this cardinality refers are ordered
     */
    public boolean isOrdered() {
        return ordered;
    }

    /**
     * Set the ordered
     * @param ordered True if the members of the container attribute
     * to which this cardinality refers are ordered
     */
    public void setOrdered(boolean ordered) {
		assertMutable();
        this.ordered = ordered;
    }

    /**
     * Get the unique
     * @return True if the members of the container attribute
     * to which this cardinality refers are unique
     */
    public boolean isUnique() {
        return unique;
    }

    /**
     * Set the unique
     * @param unique True if the members of the container attribute
     * to which this cardinality refers are unique
     */
    public void setUnique(boolean unique) {
		assertMutable();
        this.unique = unique;
    }

    /**
     * Get the interval
     * @return The interval of this cardinality
     */
    public Interval<Integer> getInterval() {
        return interval;
    }

    /**
     * Get the interval
     * @param interval The interval of this cardinality
     */
    public void setInterval(Interval<Integer> interval) {
		assertMutable();
        this.interval = interval;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
    	return "CARDINALITY[" + interval + ", ordered " + ordered + ", unique " + unique + "]";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void performValidation(ValidationResult result) {
    	super.performValidation(result);
    	if (interval == null) return;
    	if (interval.isLowerUnbounded() || !interval.isLowerIncluded() ||
    			(interval.getLower() != null && interval.getLower() < 0))
    		result.addItem(this, "Invalid interval attribute");
    	if (!interval.isUpperUnbounded()) {
    		if (!interval.isUpperIncluded() || (interval.getLower() != null && interval.getUpper() != null && interval.getLower() > interval.getUpper()))
    			result.addItem(this, "Invalid upper of interval");
    	}
    }    
}
