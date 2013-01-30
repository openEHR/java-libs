
package br.com.zilics.archetypes.models.am.archetype.constraintmodel.primitive;

import java.util.List;

import br.com.zilics.archetypes.models.am.archetype.ValidityKind;
import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.datatypes.quantity.datetime.DvDateTime;
import br.com.zilics.archetypes.models.rm.support.basic.Interval;
import br.com.zilics.archetypes.models.rm.utils.validation.ValidationResult;

/**
 * ISO 8601-compatible constraint on instances of DateTime. There is no
 * validity flag for year, since it must always be by definitino mandatory
 * in order to have a sensible date/time at all. Syntax expressions of instances
 * of this class include YYYY-MM-DDT??:??:?? and YYYY-MM-DDTHH:MM:xx
 *
 * @author Humberto
 */
public class CDateTime extends CPrimitive {

	private static final long serialVersionUID = -3041656493396925002L;
	@EqualsField
	private String pattern;
	@EqualsField
    private Interval<DvDateTime> range;
	@EqualsField
    private List<DvDateTime> list;
	@EqualsField
    private ValidityKind timezoneValidity;
	@EqualsField
    private DvDateTime assumedValue;

	/**
	 * Default constructor
	 */
	public CDateTime() {}
	
	/**
	 * Another constructor
	 * @param pattern the pattern
	 * @param range range of values of this constraint
	 * @param list list of possible values
	 * @param assumedValue the assumed value
	 */
	public CDateTime(String pattern, Interval<DvDateTime> range, List<DvDateTime> list, DvDateTime assumedValue) {
		this.pattern = pattern;
		this.range = range;
		this.list = list;
		this.assumedValue = assumedValue;
	}

	/**
     * Get the pattern
     * @return The date-time pattern
     */
    public String getPattern() {
        return pattern;
    }

    /**
     * Set the pattern
     * @param pattern The date-time pattern
     */
    public void setPattern(String pattern) {
		assertMutable();
        this.pattern = pattern;
    }

    /**
     * Get the range
     * @return Interval of DateTimes specifying constraint
     */
    public Interval<DvDateTime> getRange() {
        return range;
    }

    /**
     * Set the range
     * @param range Interval of DateTimes specifying constraint
     */
    public void setRange(Interval<DvDateTime> range) {
		assertMutable();
        this.range = range;
    }

    /**
     * Get the list
     * @return List of possible values
     */
    public List<DvDateTime> getList() {
        return getList(list);
    }

    /**
     * Set the list
     * @param list List of possible values
     */
    public void setList(List<DvDateTime> list) {
		assertMutable();
        this.list = list;
    }


    /**
     * Get the timezoneValidity
     * @return Validity of timezone in constrained date-time.
     */
    public ValidityKind getTimezoneValidity() {
        return timezoneValidity;
    }

    /**
     * Set the timezoneValidity
     * @param timezoneValidity Validity of timezone in constrained date-time.
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
    public DvDateTime getAssumedValue() {
        return assumedValue;
    }

    /**
     * Set the assumedValue
     * @param assumedValue Value to be assumed if none sent in data
     */
    public void setAssumedValue(DvDateTime assumedValue) {
		assertMutable();
        this.assumedValue = assumedValue;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
	public final String getType() {
		return "DATE_TIME";
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
    	if (!(value instanceof DvDateTime)) return false;
    	return true;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValidType(Object value) {
    	return (value instanceof DvDateTime);
    }    
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
    	if (range != null)
    		return "C_DATE_TIME[" + range + "]";
    	else if (list != null)
    		return "C_DATE_TIME[" + list + "]";
    	else
    		return "C_DATE_TIME[" + pattern + "]";
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void performValidation(ValidationResult result) {
    	super.performValidation(result);
    }    
    

}
