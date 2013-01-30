
package br.com.zilics.archetypes.models.am.archetype.constraintmodel.primitive;

import java.util.List;

import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.support.basic.Interval;
import br.com.zilics.archetypes.models.rm.utils.validation.ValidationResult;

/**
 * Constraint on instances of Integer
 *
 * @author Humberto
 */
public class CInteger extends CPrimitive {
	private static final long serialVersionUID = -903116966098798823L;
	@EqualsField
	private List<Integer> list;
	@EqualsField
    private Interval<Integer> range;
	@EqualsField
    private Integer assumedValue;

	/**
	 * Default constructor
	 */
	public CInteger() {}
	
	/**
	 * Another constructor
	 * @param range range of values of this constraint
	 * @param list set of integer specifying constraint
	 * @param assumedValue the assumed value
	 */
	public CInteger(Interval<Integer> range, List<Integer> list, Integer assumedValue) {
		this.range = range;
		this.list = list;
		this.assumedValue = assumedValue;
	}
    /**
     * Get the list
     * @return Set of Integers specifying constraint
     */
    public List<Integer> getList() {
        return getList(list);
    }

    /**
     * Set the list
     * @param list Set of Integers specifying constraint
     */
    public void setList(List<Integer> list) {
		assertMutable();
        this.list = list;
    }

    /**
     * Get the range
     *
     * @return Range of Integers specifyings constraint
     */
    public Interval<Integer> getRange() {
        return range;
    }

    /**
     * Set the range
     *
     * @param range Range of Integers specifyings constraint
     */
    public void setRange(Interval<Integer> range) {
		assertMutable();
        this.range = range;
    }


    /**
     * Get the assumedValue
     * @return Value to be assumed if none sent in data
     */
    @Override
    public Integer getAssumedValue() {
        return assumedValue;
    }

    /**
     * Set the assumedValue
     * @param assumedValue Value to be assumed if none sent in data
     */
    public void setAssumedValue(Integer assumedValue) {
		assertMutable();
        this.assumedValue = assumedValue;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
	public final String getType() {
		return "INTEGER";
	}

    
    /**
     * {@inheritDoc}
     */
    @Override
    public Integer defaultValue() {
    	if (range != null) {
    		if (range.isLowerUnbounded()) {
    			if (range.isUpperUnbounded())
    				return Integer.valueOf(0);
   				return range.getUpper() - 1;
    		} else {
    			if (range.isUpperUnbounded()) {
    				return range.getLower() + 1;
    			}
    			if (range.isLowerIncluded()) return range.getLower();
    			if (range.isUpperIncluded()) return range.getUpper();
    			if (range.getUpper() - range.getLower() > 1)
    				return range.getLower() + 1;
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
    	if (!(value instanceof Integer)) return false;
    	Integer i = (Integer) value;
    	if (range != null) {
    		return (range.has(i));
    	} else if (list != null) {
    		return list.contains(i);
    	}
    	return false;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValidType(Object value) {
    	return (value instanceof Integer);
    }
    

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
    	if (range != null)
    		return "C_INTEGER[" + range + "]";
    	else
    		return "C_INTEGER[" + list + "]";
    }

    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void performValidation(ValidationResult result) {
    	super.performValidation(result);
    	if (!((range != null) ^ (list != null)))
    		result.addItem(this, "Integer list or (exclusive) range should be defined");
    }    
    
}
