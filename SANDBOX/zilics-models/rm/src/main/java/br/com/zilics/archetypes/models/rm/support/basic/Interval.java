
package br.com.zilics.archetypes.models.rm.support.basic;

import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.datatypes.basic.DataValue;
import br.com.zilics.archetypes.models.rm.utils.validation.ValidationResult;

/**
 * Generic class defining an interval(e.g. range) of a comparable type. An interval is a contiguous
 * subrange of a comparable class base type.
 *
 * @author Humberto
 * @param <T> Type of Interval
 */
public class Interval<T extends Comparable<? super T>> extends DataValue {
	private static final long serialVersionUID = 898651377567669825L;
	@EqualsField
	private T lower;
	@EqualsField
    private T upper;
	@EqualsField
    private boolean lowerIncluded = true;
	@EqualsField
    private boolean upperIncluded = true;
	@EqualsField
    private boolean lowerUnbounded = false;
	@EqualsField
    private boolean upperUnbounded = false;

	/**
	 * Default constructor
	 */
	public Interval() {}
	
	/**
	 * Another constructor
	 * @param lower Lower bound
	 * @param upper Upper bound
	 */
	public Interval(T lower, T upper) {
		this.lower = lower;
		this.upper = upper;
		if (lower == null) lowerUnbounded = true;
		if (upper == null) upperUnbounded = true;
	}

	/**
	 * Another constructor
	 * @param lower Lower bound
	 * @param upper Upper bound
	 * @param lowerIncluded If lower bound is included
	 * @param upperIncluded If upper bound is included
	 */
	public Interval(T lower, T upper, boolean lowerIncluded, boolean upperIncluded) {
		this.lower = lower;
		this.upper = upper;
		this.lowerIncluded = lowerIncluded;
		this.upperIncluded = upperIncluded;
		if (lower == null) lowerUnbounded = true;
		if (upper == null) upperUnbounded = true;
	}
	
    /**
     * Get the lower
     * @return Lower bound of this interval
     */
    public T getLower() {
        return lower;
    }

    /**
     * Set the lower
     * @param lower Lower bound of this interval
     */
    public void setLower(T lower) {
		assertMutable();
        this.lower = lower;
    }

    /**
     * Get the upper
     * @return Upper bound of this interval
     */
    public T getUpper() {
        return upper;
    }

    /**
     * Set the upper
     * @param upper Upper bound of this interval
     */
    public void setUpper(T upper) {
		assertMutable();
        this.upper = upper;
    }


    /**
     * Get the lowerIncluded
     * @return Is lower included in this interval?
     */
    public boolean isLowerIncluded() {
        return lowerIncluded;
    }

    /**
     * Set the lowerIncluded
     * @param lowerIncluded Is lower included in this interval?
     */
    public void setLowerIncluded(boolean lowerIncluded) {
		assertMutable();
        this.lowerIncluded = lowerIncluded;
    }

    /**
     * Get the upperIncluded
     * @return Is upper included in this interval?
     */
    public boolean isUpperIncluded() {
        return upperIncluded;
    }

    /**
     * Set the upperIncluded
     * @param upperIncluded Is upper included in this interval?
     */
    public void setUpperIncluded(boolean upperIncluded) {
		assertMutable();
        this.upperIncluded = upperIncluded;
    }

    /**
     * Get the lowerUnbounded
     * @return The interval has no lower bound?
     */
    public boolean isLowerUnbounded() {
        return lowerUnbounded;
    }

    /**
     * Set the lowerUnbounded
     * @param lowerUnbounded The interval has no lower bound?
     */
    public void setLowerUnbounded(boolean lowerUnbounded) {
		assertMutable();
        this.lowerUnbounded = lowerUnbounded;
    }

    /**
     * Get the upperUnbounded
     * @return The interval has no upper bound?
     */
    public boolean isUpperUnbounded() {
        return upperUnbounded;
    }

    /**
     * Set the upperUnbounded
     * @param upperUnbounded The interval has no upper bound?
     */
    public void setUpperUnbounded(boolean upperUnbounded) {
		assertMutable();
        this.upperUnbounded = upperUnbounded;
    }

    /**
     * Returns true if value is in the interval
     *
     * @param value to compare to
     * @return true if given value is included in this Interval
     * @throws NullPointerException if value is null
     */
    public boolean has(T value) {
        if (value == null) {
            throw new NullPointerException("Null value");
        }
        return ((lowerUnbounded)
                || value.compareTo(lower) > 0
                || ( lowerIncluded && value.compareTo(lower) == 0 ) )
                && ( upperUnbounded
                || value.compareTo(upper) < 0
                || ( upperIncluded && value.compareTo(upper) == 0 ) );
    }
 
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
    	StringBuilder sb = new StringBuilder();
    	if (lowerUnbounded) sb.append("]-inf");
    	else {
    		if (lowerIncluded) sb.append("[");
    		else sb.append("]");
    		sb.append(lower);
    	}
    	sb.append("; ");
    	if (upperUnbounded) sb.append("inf[");
    	else {
    		sb.append(upper);
    		if (upperIncluded) sb.append("]");
    		else sb.append("[");
    	}
    	
    	return sb.toString();
    }
    

    /**
     * {@inheritDoc}
     */
    @Override
    protected void performValidation(ValidationResult result) {
    	super.performValidation(result);
    	if (lowerUnbounded ^ (lower == null))
    		result.addItem(this, "Lower is null");
    	if (upperUnbounded ^ (upper == null))
    		result.addItem(this, "Upper is null");
    	try {
    		if (!lowerUnbounded && !upperUnbounded)
    			if (lower != null && upper != null)
    				if (lower.compareTo(upper) > 0)
    					result.addItem(this, "Upper should be grater than lower");
    	} catch(Throwable t) {
    		result.addItem(this, "Exception while comparing", t);
    	}
    }
}
