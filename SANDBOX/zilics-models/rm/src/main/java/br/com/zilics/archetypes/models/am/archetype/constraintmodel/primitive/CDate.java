
package br.com.zilics.archetypes.models.am.archetype.constraintmodel.primitive;

import java.util.List;

import br.com.zilics.archetypes.models.am.archetype.ValidityKind;
import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.datatypes.quantity.datetime.DvDate;
import br.com.zilics.archetypes.models.rm.support.basic.Interval;
import br.com.zilics.archetypes.models.rm.utils.validation.ValidationResult;

/**
 * ISO 8601-compatible constraint on instances of Date in the form either of a
 * set of validity values, or an actual date range. There is no validity flag
 * for year, since it must always be by definition madatory in order to have a
 * sensible date at all. Syntax expressions of instance of this class include
 * YYYY-??-?? (date with optional month and day)
 *
 * @author Humberto
 */
public class CDate extends CPrimitive {

	private static final long serialVersionUID = -3093738428560537288L;
	@EqualsField
	private String pattern;
	@EqualsField
    private Interval<DvDate> range;
	@EqualsField
    private List<DvDate> list;
	@EqualsField
    private ValidityKind timezoneValidity;
	@EqualsField
    private DvDate assumedValue;

	/**
	 * Default constructor
	 */
	public CDate() {}
	
	/**
	 * Another constructor
	 * @param pattern the pattern
	 * @param range range of values of this constraint
	 * @param list list of possible values
	 * @param assumedValue the assumed value
	 */
	public CDate(String pattern, Interval<DvDate> range, List<DvDate> list, DvDate assumedValue) {
		this.pattern = pattern;
		this.range = range;
		this.list = list;
		this.assumedValue = assumedValue;
	}

	/**
     * Get the pattern
     * @return The date pattern
     */
    public String getPattern() {
        return pattern;
    }

    /**
     * Set the pattern
     * @param pattern The date pattern
     */
    public void setPattern(String pattern) {
		assertMutable();
        this.pattern = pattern;
    }

    /**
     * Get the range
     * @return Interval of Dates specifying constraint
     */
    public Interval<DvDate> getRange() {
        return range;
    }

    /**
     * Set the range
     * @param range Interval of Dates specifying constraint
     */
    public void setRange(Interval<DvDate> range) {
		assertMutable();
        this.range = range;
    }

    /**
     * Get the list
     * @return List of possible values
     */
    public List<DvDate> getList() {
        return getList(list);
    }

    /**
     * Set the list
     * @param list List of possible values
     */
    public void setList(List<DvDate> list) {
		assertMutable();
        this.list = list;
    }

    /**
     * Get the timezoneValidity
     * @return Validity of timezone in constrained date.
     */
    public ValidityKind getTimezoneValidity() {
        return timezoneValidity;
    }

    /**
     * Set the timezoneValidity
     * @param timezoneValidity Validity of timezone in constrained date.
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
    public DvDate getAssumedValue() {
        return assumedValue;
    }

    /**
     * Set the assumedValue
     * @param assumedValue Value to be assumed if none sent in data
     */
    public void setAssumedValue(DvDate assumedValue) {
		assertMutable();
        this.assumedValue = assumedValue;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
	public final String getType() {
		return "DATE";
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
    	if (!(value instanceof DvDate)) return false;
    	return true;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValidType(Object value) {
    	return (value instanceof DvDate);
    }    

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
    	if (range != null)
    		return "C_DATE[" + range + "]";
    	else if (list != null)
    		return "C_DATE[" + list + "]";
    	else
    		return "C_DATE[" + pattern + "]";
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void performValidation(ValidationResult result) {
    	super.performValidation(result);
    }    

    
}
