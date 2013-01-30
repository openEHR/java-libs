
package br.com.zilics.archetypes.models.am.archetype.constraintmodel.primitive;

import java.util.List;

import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.support.basic.Interval;
import br.com.zilics.archetypes.models.rm.utils.validation.ValidationResult;

/**
 * Constraint on instances of Real
 *
 * @author Humberto
 */
public class CReal extends CPrimitive {
	private static final long serialVersionUID = 3393528082092620415L;
	@EqualsField
	private List<Double> list;
	@EqualsField
    private Interval<Double> range;
	@EqualsField
    private Double assumedValue;

	/**
	 * Default constructor
	 */
	public CReal() {}
	
	/**
	 * Another constructor
	 * @param range range of values of this constraint
	 * @param list set of integer specifying constraint
	 * @param assumedValue the assumed value
	 */
	public CReal(Interval<Double> range, List<Double> list, Double assumedValue) {
		this.range = range;
		this.list = list;
		this.assumedValue = assumedValue;
	}

	/**
     * Get the list
     * @return Set of Reals specifying constraint
     */
    public List<Double> getList() {
        return getList(list);
    }

    /**
     * Set the list
     * @param list Set of Reals specifying constraint
     */
    public void setList(List<Double> list) {
		assertMutable();
        this.list = list;
    }

    /**
     * Get the range
     * @return Range of Real specifying constraint
     */
    public Interval<Double> getRange() {
        return range;
    }

    /**
     * Set the range
     * @param range Range of Real specifying constraint
     */
    public void setRange(Interval<Double> range) {
		assertMutable();
        this.range = range;
    }


    /**
     * Get the assumedValue
     * @return Value to be assumed if none sent in data
     */
    @Override
    public Double getAssumedValue() {
        return assumedValue;
    }

    /**
     * Set the assumedValue
     * @param assumedValue Value to be assumed if none sent in data
     */
    public void setAssumedValue(Double assumedValue) {
		assertMutable();
        this.assumedValue = assumedValue;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
	public final String getType() {
		return "REAL";
	}

    /**
     * {@inheritDoc}
     */
    @Override
    public Double defaultValue() {
    	if (range != null) {
    		if (range.isLowerUnbounded()) {
    			if (range.isUpperUnbounded())
    				return Double.valueOf(0.0);
   				return range.getUpper() - 1;
    		} else {
    			if (range.isUpperUnbounded()) {
    				return range.getLower() + 1;
    			}
    			return (range.getLower() + range.getUpper()) / 2;
    		}
    	} else if (list != null && list.size() > 0) {
    		return list.get(0);
    	}
    	return null;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValidValue(Object value) {
    	if (value instanceof Float)
    		value = Double.valueOf((Float) value);
    	if (!(value instanceof Double)) return false;
    	Double d = (Double) value;
    	if (range != null) {
    		return (range.has(d));
    	} else if (list != null) {
    		return list.contains(d);
    	}
    	return false;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValidType(Object value) {
    	return (value instanceof Float) || (value instanceof Double);
    }
    
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
    	if (range != null)
    		return "C_REAL[" + range + "]";
    	else
    		return "C_REAL[" + list + "]";
    }

    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void performValidation(ValidationResult result) {
    	super.performValidation(result);
    	if (!((range != null) ^ (list != null)))
    		result.addItem(this, "Double list or (exclusive) range should be defined");
    }    
    
}
