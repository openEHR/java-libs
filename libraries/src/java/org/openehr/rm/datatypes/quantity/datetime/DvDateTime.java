/**
 * 
 */
package org.openehr.rm.datatypes.quantity.datetime;

import java.util.List;
import java.util.TimeZone;

import org.openehr.rm.Attribute;
import org.openehr.rm.FullConstructor;
import org.openehr.rm.datatypes.quantity.DvInterval;
import org.openehr.rm.datatypes.quantity.DvOrdered;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.MutableDateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.openehr.rm.datatypes.quantity.DvQuantified;
import org.openehr.rm.datatypes.quantity.DvQuantity;
import org.openehr.rm.datatypes.quantity.ReferenceRange;

/**
 * @author yinsulim
 *
 */
public class DvDateTime extends DvWorldTime <DvDateTime> {

	/* static fields */
	static final DateTimeFormatter eDTimeFormatter = ISODateTimeFormat.dateTime(); //extended formatter
	static final DateTimeFormatter dTimeFormatter = ISODateTimeFormat.basicDateTime();
	
    /**
     * Construct a DvDateTime
     *
     * @param referenceRanges null if not specified
     * @param accuracy        0 if not specified
     * @param accuracyPercent
     * @param value
     * @throws IllegalArgumentException
     */
    @FullConstructor
            public DvDateTime(
            @Attribute(name = "referenceRanges") List<ReferenceRange<DvDateTime>> referenceRanges,
            @Attribute(name = "normalRange") DvInterval<DvDateTime> normalRange,
            @Attribute(name = "accuracy") double accuracy,
            @Attribute(name = "accuracyPercent") boolean accuracyPercent,
            @Attribute(name = "value", required = true) String value) {
        super(referenceRanges, normalRange, accuracy, accuracyPercent, value);
    }

    protected DvDateTime(List<ReferenceRange<DvDateTime>> referenceRanges,
			DvInterval<DvDateTime> normalRange, double accuracy, 
			boolean accuracyPercent, DateTime datetime) {
		super(referenceRanges, normalRange, accuracy, accuracyPercent, datetime);
    }
 
    protected DvDateTime(DateTime datetime) {
		super(null, null, 0.0, false, datetime);
    }
    
	/**
	 * Construct a DvDateTime by current date and time
	 * 
	 * @param referenceRanges
	 * @param normalRange
	 * @param accuracy
	 * @param accuracyPercent
	 */
	public DvDateTime(List<ReferenceRange<DvDateTime>> referenceRanges,
			DvInterval<DvDateTime> normalRange, double accuracy, 
			boolean accuracyPercent) {
		super(referenceRanges, normalRange, accuracy, accuracyPercent);
	}

	/**
	 * Construct a DvDateTime by value
	 * 
	 * @param value
	 */
	public DvDateTime(String value) {
		this(null, null, 0.0, false, value);
	}
	
    /**
     * Constructs a DvDateTime with timezone
     *
     * @param year
     * @param month    starts with 0
     * @param day   day of month
     * @param hour  hour of day
     * @param minute
     * @param second
     * @param timezone null if use default timezone
     */
    public DvDateTime(int year, int month, int day, int hour, int minute, int second, 
    		int fractionalSec, TimeZone timezone) {
    		super(null, null, 0.0, false, 
    				convert(year, month, day, hour, minute, second, fractionalSec, timezone));
    }
    
    protected static DateTime convert(int year, int month, int day, int hour, int minute,
            int second, int fractionalSec, TimeZone timezone) {
    		if (timezone == null) {
			return new DateTime(year, month, day, hour, minute, second, fractionalSec);
		} else {
			return new DateTime(year, month, day, hour, minute, second, fractionalSec, 
					DateTimeZone.forTimeZone(timezone));
		}
    }
    
    protected static DateTime convert(int year, int month, int day, int hour, int minute,
            int second, int fractionalSec, DateTimeZone timezone) {
    		if (timezone == null) {
			return new DateTime(year, month, day, hour, minute, second, fractionalSec);
		} else {
			return new DateTime(year, month, day, hour, minute, second, fractionalSec, 
					timezone);
		}
    }
	
	/* (non-Javadoc)
	 * @see org.openehr.rm.datatypes.quantity.datetime.NDvWorldTime#parseValue(java.lang.String)
	 */
	@Override DateTime parseValue(String value) {
		return parse(value);
	}

    static DateTime parse(String value) {
    		if(value == null) {
            throw new IllegalArgumentException("null value");
        }
    		DateTime dt = null;
		if (value.indexOf("-") > 0) {
			try {
				dt = eDTimeFormatter.parseDateTime(value);
			} catch (Exception e) {
				throw new IllegalArgumentException("invalid value");
			}
		} else {
			try {
				dt = dTimeFormatter.parseDateTime(value);
			} catch (Exception e) {
				throw new IllegalArgumentException("invalid value");
			}
		}		
		return dt;
    }
    
    public static boolean isValidDateTime(String value) {
    		return parse(value) != null;
    }
 
    /**
     * Year
     *
     * @return year
     */
    public int getYear() {
        return getDateTime().getYear();
    }

    /**
     * Month in year
     *
     * @return month in year
     */
    public int getMonth() {
        return getDateTime().getMonthOfYear();
    }

    /**
     * Day in month
     *
     * @return day in month
     */
    public int getDay() {
        return getDateTime().getDayOfMonth();
    }

    /**
     * Hour in day
     *
     * @return hour
     */
    public int getHour() {
        return getDateTime().getHourOfDay();
    }

    /**
     * Minute in hour
     *
     * @return minute in hour
     */
    public int getMinute() {
        return getDateTime().getMinuteOfHour();
    }

    /**
     * Second in minute
     *
     * @return second in minute
     */
    public int getSecond() {
        return getDateTime().getSecondOfMinute();
    }

    /**
     * fractional seconds
     *
     * @return fractional seconds
     */
    public double getFractionalSecond() {
        return getDateTime().getMillisOfSecond() / 10E3;
    }

	@Override
	public DvQuantity toQuantity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Number getMagnitude() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DvQuantified<DvDateTime> add(DvQuantified q) {
		if (!getDiffType().isInstance(q)) {
			throw new IllegalArgumentException("invalid difference type");
		}
		DvDuration d = (DvDuration) q;
		MutableDateTime mdate = getDateTime().toMutableDateTimeISO();
		mdate.add(d.getPeriod());
		return new DvDateTime(getOtherReferenceRanges(), getNormalRange(), 
				getAccuracy(), isAccuracyPercent() ,mdate.toDateTimeISO());		
	}

	@Override
	public DvQuantified<DvDateTime> subtract(DvQuantified q) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isStrictlyComparableTo(DvOrdered ordered) {
		// TODO Auto-generated method stub
		return false;
	}

    /**
     * Two DvDateTime equal if both have same year, month, day and timezone
     *
     * @param o
     * @return true if equals
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!( o instanceof DvDateTime )) return false;
        return super.equals(o);       
    }
    
}
