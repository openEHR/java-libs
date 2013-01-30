
package br.com.zilics.archetypes.models.am.archetype.constraintmodel.primitive;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.datatypes.quantity.datetime.DvDuration;
import br.com.zilics.archetypes.models.rm.support.basic.Interval;
import br.com.zilics.archetypes.models.rm.utils.validation.ValidationResult;

/**
 * ISO 8601-compatible constraint on instances of Duration. In ISO 8601 terms,
 * constraints might are of the form PWD (weeks and/or days), PDTHMS (days,
 * hours, minutes, seconds) and so on. In official ISO 8601:2004, the W (week)
 * designator cannot be mixed in
 *
 * @author Humberto
 */
public class CDuration extends CPrimitive {

	/**
	 * An unmodifiable list containing all duration units (Y, M, W, D, H, m, S)
	 */
	public static final List<String> ALL_UNITS = Collections.unmodifiableList(Arrays.asList(new String[]{"Y","M","W","D","H","m","S"}));
	
	private static final long serialVersionUID = 2477834457238799388L;
	@EqualsField
	private Interval<DvDuration> range;
	@EqualsField
    private String pattern;
	@EqualsField
    private DvDuration assumedValue;

	/**
	 * Default constructor
	 */
	public CDuration() {}
	
	/**
	 * Another constructor
	 * @param pattern the pattern of this constraint
	 * @param range the range of values of this constraint
	 * @param assumedValue the assumed value
	 */
	public CDuration(String pattern, Interval<DvDuration> range, DvDuration assumedValue) {
		this.pattern = pattern;
		this.range = range;
		this.assumedValue = assumedValue;
	}
	
    /**
     * Get the range
     * @return Interval of durations specifying constraint
     */
    public Interval<DvDuration> getRange() {
        return range;
    }

    /**
     * Set the range
     * @param range Interval of durations specifying constraint
     */
    public void setRange(Interval<DvDuration> range) {
		assertMutable();
        this.range = range;
    }

    /**
     * Get the pattern
     * @return The duration pattern
     */
    public String getPattern() {
        return pattern;
    }

    /**
     * Set the pattern
     * @param pattern The duration pattern
     */
    public void setPattern(String pattern) {
		assertMutable();
        this.pattern = pattern;
    }

    /**
     * Get the assumedValue
     * @return Value to be assumed if none sent in data
     */
    @Override
    public DvDuration getAssumedValue() {
        return assumedValue;
    }

    /**
     * Set the assumedValue
     * @param assumedValue Value to be assumed if none sent in data
     */
    public void setAssumedValue(DvDuration assumedValue) {
		assertMutable();
        this.assumedValue = assumedValue;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
	public final String getType() {
		return "DURATION";
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
    	if (!(value instanceof DvDuration)) return false;
    	return true;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValidType(Object value) {
    	return (value instanceof DvDuration);
    }    


    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
    	if (range != null)
    		return "C_DURATION[" + range + "]";
    	else
    		return "C_DURATION[" + pattern + "]";
    }

    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void performValidation(ValidationResult result) {
    	super.performValidation(result);
    	if (!((range != null) ^ (pattern != null)))
    		result.addItem(this, "Pattern or (exclusive) range should be defined");
    }

    /**
     * Return a list of strings containing the units (Y,M,W,D,H,m,S) - m and M is used to tell minutes from months.
     */
	public List<String> getUnits() {
		boolean time = false;
		List<String> result = new ArrayList<String>();
		for(char c: pattern.toCharArray()){
			if(c == 'P'){
				;//NOOP
			} else if(c == 'T'){
				time = true;
			} else if(c == 'M'){
				if(time){
					result.add("m");
				} else {
					result.add("M");
				}
			} else {
				result.add(c+"");
			}
		}
		return result;
	}
}
