
package br.com.zilics.archetypes.models.rm.datatypes.quantity.datetime;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.joda.time.Duration;
import org.joda.time.Instant;
import org.joda.time.MutablePeriod;
import org.joda.time.Period;
import org.joda.time.format.ISOPeriodFormat;
import org.joda.time.format.PeriodFormatter;

import br.com.zilics.archetypes.models.rm.annotation.Ignore;
import br.com.zilics.archetypes.models.rm.annotation.NotEmpty;
import br.com.zilics.archetypes.models.rm.datatypes.quantity.DvAmount;
import br.com.zilics.archetypes.models.rm.utils.validation.ValidationResult;

/**
 * Represents a period of time with respect to a notional point in time, which is not
 * specified. A sign may be used to indicate the duration is "backwards" in time rather
 * than "forwards".
 * @author Humberto
 */
public class DvDuration extends DvAmount<DvDuration> {

	private static final long serialVersionUID = -2139815176627104604L;

	private static final PeriodFormatter formatter = ISOPeriodFormat.standard();
	private static final String PATTERN = "(-)?P((\\d)+(y|Y))?((\\d)+(m|M))?((\\d)+(w|W))?((\\d)+(d|D))?"
			+ "(T((\\d)+(h|H))?((\\d)+(m|M))?((\\d)+((,|\\.)(\\d){1,3})?(s|S))?)?";
	
	@NotEmpty
	private String value;
	@Ignore
	private Period period;

	/**
	 * Default constructor
	 */
	public DvDuration() {}
	
	/**
	 * Another constructor 
	 * @param value string representation of this duration
	 */
	public DvDuration(String value) {
		this.value = value;
	}

    /**
     * Get the value
     * @return ISO8601 duration.
     */
    public String getValue() {
        return value;
    }

    /**
     * Set the value
     * @param value ISO8601 duration.
     */
    public void setValue(String value) {
		assertMutable();
        this.value = value;
    }

	/**
	 * number of years of nominal 365-day length
	 * 
	 * @return years
	 */
	public int years() {
		return period.getYears();
	}

	/**
	 * number of months of nominal 30 day length
	 * 
	 * @return months
	 */
	public int months() {
		return period.getMonths();
	}

	public int weeks() {
		return period.getWeeks();
	}

	/**
	 * number of 24 hour days
	 * 
	 * @return days
	 */
	public int days() {
		return period.getDays();
	}

	/**
	 * number of 60 minute hours
	 * 
	 * @return hours
	 */
	public int hours() {
		return period.getHours();
	}

	/**
	 * number of 60 second minutes
	 * 
	 * @return minutes
	 */
	public int minutes() {
		return period.getMinutes();
	}

	/**
	 * number of seconds
	 * 
	 * @return seconds
	 */
	public int seconds() {
		return period.getSeconds();
	}

	/**
	 * fractional seconds
	 * 
	 * @return fractional seconds
	 */
	public double fractionalSeconds() {
		return period.getMillis() / 10E2;
	}
    
    
	/**
	 * 
	 */
    /**
     * {@inheritDoc}
     */
    @Override
	public String toString(){
        return value;
    }

    /**
     * {@inheritDoc}
     */
	@Override
	public boolean isStrictlyComparableTo(DvDuration ordered) {
		return true;
	}


	/**
	 * {@inheritDoc}
	 */
	public int compareTo(DvDuration o) {
		return period.toDurationFrom(new Instant()).compareTo(o.period.toDurationFrom(new Instant()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Number magnitude() {
		return Math.abs(period.toDurationFrom(new Instant()).getMillis() / 10E2);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DvDuration add(DvDuration q) {
		DvDuration r = new DvDuration();
		r.setAccuracy(getAccuracy());
		r.setAccuracyIsPercent(isAccuracyIsPercent());
		r.setMagnitudeStatus(getMagnitudeStatus());
		r.setNormalRange(getNormalRange());
		r.setNormalStatus(getNormalStatus());
		r.setOtherReferenceRanges(getOtherReferenceRanges());
	
		MutablePeriod mp = period.toMutablePeriod();
		mp.add(q.period);
		r.period = mp.toPeriod();
		r.setValue(asString(r.period));
		return r;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DvDuration negate() {
		DvDuration r = new DvDuration();
		r.setAccuracy(getAccuracy());
		r.setAccuracyIsPercent(isAccuracyIsPercent());
		r.setMagnitudeStatus(getMagnitudeStatus());
		r.setNormalRange(getNormalRange());
		r.setNormalStatus(getNormalStatus());
		r.setOtherReferenceRanges(getOtherReferenceRanges());
	
		r.period = negatePeriod(period);
		r.setValue(asString(r.period));
		return r;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DvDuration subtract(DvDuration q) {
		DvDuration r = new DvDuration();
		r.setAccuracy(getAccuracy());
		r.setAccuracyIsPercent(isAccuracyIsPercent());
		r.setMagnitudeStatus(getMagnitudeStatus());
		r.setNormalRange(getNormalRange());
		r.setNormalStatus(getNormalStatus());
		r.setOtherReferenceRanges(getOtherReferenceRanges());
	
		MutablePeriod mp = period.toMutablePeriod();
		mp.add(negatePeriod(q.period));
		r.period = mp.toPeriod();
		r.setValue(asString(r.period));
		return r;
	}

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
    protected void performValidation(ValidationResult result) {
    	super.performValidation(result);
		period = null;
		if (value == null) return;
		if (!value.matches(PATTERN)) {
			result.addItem(this, "Wrong duration format: " + value);
			return;
		}
		try {
			if (value.startsWith("-")) {
				value = value.substring(1, value.length()); // skip '-'
				period = formatter.parsePeriod(value);
				period = negatePeriod(period);
			} else {
				period = formatter.parsePeriod(value);
			}
		} catch(Throwable t) {
			result.addItem(this, "Error parsing DvDuration: " + value, t);
		}
		
	}
	
	
	
	/**
	 * Helper method to negate a copy of period
	 * 
	 * @param mPeriod
	 * @return a negated copy of period
	 */
	private static Period negatePeriod(Period period) {
		MutablePeriod mPeriod = period.toMutablePeriod();
		mPeriod.setYears(-mPeriod.getYears());
		mPeriod.setMonths(-mPeriod.getMonths());
		mPeriod.setWeeks(-mPeriod.getWeeks());
		mPeriod.setDays(-mPeriod.getDays());
		mPeriod.setHours(-mPeriod.getHours());
		mPeriod.setMinutes(-mPeriod.getMinutes());
		mPeriod.setSeconds(-mPeriod.getSeconds());
		mPeriod.setMillis(-mPeriod.getMillis());
		return mPeriod.toPeriod();
	}
	
	
	/**
	 * TODO: Buggy implementation - asString depends on the current date. 
	 * @param period
	 * @return
	 */
	private static String asString(Period period) {
		Duration d = period.toDurationFrom(new Instant());
		if (d.getMillis() < 0) {
			Instant instant = (new Instant()).plus(d.getMillis());
			d = d.minus(d.getMillis() * 2);
			
			return "-"  + d.toPeriodFrom(instant).toString();
		} else 
			return d.toPeriodFrom(new Instant()).toString();
	}

	/**
	 * Converts long and unit to a string value using the correct format.
	 * Examples: toDurationString(2, "Y") = "P2Y" / toDurationString(3, "m") = "PT3M"
	 * @param magnitude The duration magnitude
	 * @param unit The unit string (Y, M, W, D, H, m, S)
	 * @return The string value using the correct format.
	 */
	public static final String toDurationString(Long magnitude, String unit) {
		char u = unit.charAt(0);
		switch (u) {
			case 'm': 								u = 'M';
			case 'H': case 'S':						return "PT" + magnitude + u;
			case 'Y': case 'M': case 'W': case 'D':	return "P" + magnitude + u;
			default:  return null;
		}
	}
	
	/**
	 * Converts a duration value String in a two-element array containing the magnitude and unit
	 * Examples: fromDurationString("P2Y") = ["2","Y"] / fromDurationString("PT3M") = ["3","m"]
	 * @param value Duration value in String format
	 * @return A two-element array containing the magnitude and unit
	 */
	public static final String[] fromDurationString(String value){
		Pattern regex = Pattern.compile("\\d+");
		Matcher match = regex.matcher(value);
		String strDuration = match.find() ? match.group() : "";
		
		regex = Pattern.compile("[YMWDHS]");
		match = regex.matcher(value);
		String strUnit = match.find() ? match.group() : "";
		if(value.startsWith("PT") && "M".equals(strUnit)){
			strUnit = "m";
		}
		return new String[]{strDuration, strUnit};
	}
	
}
