/**
 * 
 */
package org.openehr.rm.datatypes.quantity.datetime;

import java.util.List;
import java.util.TimeZone;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.MutableDateTime;
import org.joda.time.TimeOfDay;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.openehr.rm.Attribute;
import org.openehr.rm.FullConstructor;
import org.openehr.rm.datatypes.quantity.DvInterval;
import org.openehr.rm.datatypes.quantity.DvOrdered;
import org.openehr.rm.datatypes.quantity.DvQuantified;
import org.openehr.rm.datatypes.quantity.DvQuantity;
import org.openehr.rm.datatypes.quantity.ReferenceRange;

/**
 * @author yinsulim
 *
 */
public class DvTime extends DvWorldTime<DvTime> {

    /* static field */
	static final DateTimeFormatter timeFormatter = ISODateTimeFormat.basicTime();
	static final DateTimeFormatter eTimeFormatter = ISODateTimeFormat.time(); //extended
	//static final String PATTERN = "((\\d){2}:){2}(\\d){2}.*";

    /**
     * Construct a DvTime
     *
     * @param referenceRanges null if not specified
     * @param normalRange null if not specified
     * @param accuracy        0 if not specified
     * @param accuracyPercent
     * @param value
     * @throws IllegalArgumentException
     */
    @FullConstructor
            public DvTime(
            @Attribute(name = "referenceRanges") List<ReferenceRange<DvTime>> referenceRanges,
            @Attribute (name = "normalRange") DvInterval<DvTime> normalRange,
            @Attribute(name = "accuracy") double accuracy,
            @Attribute(name = "accuracyPercent") boolean accuracyPercent,
            @Attribute(name = "value", required = true) String value) {
        super(referenceRanges, normalRange, accuracy, accuracyPercent, value);
    }

    /**
     * Construct a DvTime
     *
     * @param referenceRanges null if not specified
     * @param normalRange null if not specified
     * @param accuracy        0 if not specified
     * @param accuracyPercent
     * @param datetime
     * @throws IllegalArgumentException
     */
    protected DvTime(List<ReferenceRange<DvTime>> referenceRanges,
    				DvInterval<DvTime> normalRange, double accuracy, 
    				boolean accuracyPercent, DateTime datetime) {
        super(referenceRanges, normalRange, accuracy, accuracyPercent, datetime);
    }

    protected DvTime(DateTime datetime) {
		super(null, null, 0.0, false, datetime);
    }
    
    /**
     * Constructs a DvTime with timezone
     *
     * @param hour
     * @param minute
     * @param second
     * @param timezone null if use default timezone
     */
    public DvTime(int hour, int minute, int second, TimeZone timezone) { 		
    		super(null, null, 0.0, false, convert(hour, minute, second, timezone));
    }
 
    /**
     * Constructs a DvTime with value
     *
     * @param value
     */
    public DvTime(String value) { 		
    		this(null, null, 0.0, false, value);
    }
    
    protected static DateTime convert(int hour, int minute, int second, TimeZone timezone) {
    		TimeOfDay tod =  new TimeOfDay(hour, minute, second);
    		return tod.toDateTimeToday(DateTimeZone.forTimeZone(timezone));
    }

    protected static DateTime convert(int hour, int minute, int second, DateTimeZone timezone) {
		TimeOfDay tod =  new TimeOfDay(hour, minute, second);
		return tod.toDateTimeToday(timezone);
    }
    
    static DateTime parse(String value) {
    		if(value == null) {
            throw new IllegalArgumentException("null value");
        }
    		DateTime dt = null;
		if (value.indexOf(":") > 0) {
			try {
				dt = eTimeFormatter.parseDateTime(value);
			} catch (Exception e) {
				throw new IllegalArgumentException("invalid value");
			}
		} else {
			try {
				dt = timeFormatter.parseDateTime(value);
			} catch (Exception e) {
				throw new IllegalArgumentException("invalid value");
			}
		}		
		return dt;
    }

    /**
     * Hour in day
     *
     * @return hour in day
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
     * @return second
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

    /**
     * If date is valid ISO8601 format
     *
     * @param year
     * @param month
     * @param day
     * @return true if valid
     */
    public static boolean isValidTime(String value) {
        return parse(value) != null;
    }
     
	//@Override 
	/*DateTime parseValue(String value) {
		
	}*/

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
	public DvQuantified<DvTime> add(DvQuantified q) {
		if (!getDiffType().isInstance(q)) {
			throw new IllegalArgumentException("invalid difference type");
		}
		DvDuration d = (DvDuration) q;
		MutableDateTime mdate = getDateTime().toMutableDateTimeISO();
		mdate.add(d.getPeriod());
		return new DvTime(getOtherReferenceRanges(), getNormalRange(), 
				getAccuracy(), isAccuracyPercent() ,mdate.toDateTimeISO());	
	}

	@Override
	public DvQuantified<DvTime> subtract(DvQuantified q) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isStrictlyComparableTo(DvOrdered ordered) {
		// TODO Auto-generated method stub
		return false;
	}

    /**
     * Two DvTime equal if both have same value for hour, minute, second
     * and timezone
     *
     * @param o
     * @return true if equals
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!( o instanceof DvTime )) return false;
        return super.equals(o);   
    }

	@Override
	DateTime parseValue(String value) {
		return parse(value);
	}
}
