
package br.com.zilics.archetypes.models.am.archetype.constraintmodel.primitive;

import java.util.List;

import br.com.zilics.archetypes.models.am.archetype.ValidityKind;
import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.datatypes.quantity.datetime.DvTime;
import br.com.zilics.archetypes.models.rm.support.basic.Interval;
import br.com.zilics.archetypes.models.rm.utils.validation.ValidationResult;

/**
 * ISO 8601-compatible constraint on instances of Time. There is no validity
 * flag for hour, since it must always be by definition mandatory in order to
 * have a sensible time at all. Syntax expressions of instances of this
 * class include HH:??:xx (time with optional minutes and seconds not allowed)
 *
 * @author Humberto
 */
public class CTime extends CPrimitive {

	private static final long serialVersionUID = -5421134052187253060L;
	@EqualsField
	private String pattern;
	@EqualsField
    private Interval<DvTime> range;
	@EqualsField
    private List<DvTime> list;
	@EqualsField
    private ValidityKind timezoneValidity;
	@EqualsField
    private DvTime assumedValue;

	/**
	 * Default constructor
	 */
	public CTime() {}
	
	/**
	 * Another constructor
	 * @param pattern the pattern
	 * @param range range of values of this constraint
	 * @param list list of possible values
	 * @param assumedValue the assumed value
	 */
	public CTime(String pattern, Interval<DvTime> range, List<DvTime> list, DvTime assumedValue) {
		this.pattern = pattern;
		this.range = range;
		this.list = list;
		this.assumedValue = assumedValue;
	}

	/**
     * Get the pattern
     * @return The time pattern
     */
    public String getPattern() {
        return pattern;
    }

    /**
     * Set the pattern
     * @param pattern The time pattern
     */
    public void setPattern(String pattern) {
		assertMutable();
        this.pattern = pattern;
    }

    /**
     * Get the range
     * @return Interval of times specifying constraint
     */
    public Interval<DvTime> getRange() {
        return range;
    }

    /**
     * Set the range
     * @param range Interval of times specifying constraint
     */
    public void setRange(Interval<DvTime> range) {
		assertMutable();
        this.range = range;
    }

    /**
     * Get the list
     * @return List of possible values
     */
    public List<DvTime> getList() {
        return getList(list);
    }

    /**
     * Set the list
     * @param list List of possible values
     */
    public void setList(List<DvTime> list) {
		assertMutable();
        this.list = list;
    }


    /**
     * Get the timezoneValidity
     * @return Validity of timezone in constrained time.
     */
    public ValidityKind getTimezoneValidity() {
        return timezoneValidity;
    }

    /**
     * Set the timezoneValidity
     * @param timezoneValidity Validity of timezone in constrained time.
     */
    public void setTimezoneValidity(ValidityKind timezoneValidity) {
		assertMutable();
        this.timezoneValidity = timezoneValidity;
    }


    /**
     * Get the assumedValue
     * @return Value to be assumed if none sent in data
     */
    @Override
    public DvTime getAssumedValue() {
        return assumedValue;
    }

    /**
     * Set the assumedValue
     * @param assumedValue Value to be assumed if none sent in data
     */
    public void setAssumedValue(DvTime assumedValue) {
		assertMutable();
        this.assumedValue = assumedValue;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
	public final String getType() {
		return "TIME";
	}
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String defaultValue() {
    	return null;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValidValue(Object value) {
    	if (!(value instanceof DvTime)) return false;
    	return true;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValidType(Object value) {
    	return (value instanceof DvTime);
    }
    
    

    
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
    	if (range != null)
    		return "C_TIME[" + range + "]";
    	else if (list != null)
    		return "C_TIME[" + list + "]";
    	else
    		return "C_TIME[" + pattern + "]";
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void performValidation(ValidationResult result) {
    	super.performValidation(result);
    }    
    
    

}
