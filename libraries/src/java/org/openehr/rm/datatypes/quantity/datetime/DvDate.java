/**
 * 
 */
package org.openehr.rm.datatypes.quantity.datetime;

//import static org.openehr.rm.datatypes.quantity.datetime.DvWorldTime.convert;

import java.util.List;
import java.util.TimeZone;

import org.openehr.rm.Attribute;
import org.openehr.rm.FullConstructor;
import org.openehr.rm.datatypes.quantity.DvInterval;
import org.openehr.rm.datatypes.quantity.DvOrdered;
import org.openehr.rm.datatypes.quantity.ReferenceRange;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.MutableDateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import org.openehr.rm.datatypes.quantity.DvQuantified;
import org.openehr.rm.datatypes.quantity.DvQuantity;

/**
 * @author yinsulim
 *
 */
public class DvDate extends DvWorldTime<DvDate> {


    /**
     * Construct a DvDate
     *
     * @param referenceRanges null if not specified
     * @param accuracy        0 if not specified
     * @param accuracyPercent
     * @param value
     * @throws IllegalArgumentException
     */
    @FullConstructor
            public DvDate(
            @Attribute(name = "referenceRanges") List<ReferenceRange<DvDate>> referenceRanges,
            @Attribute(name = "normalRange") DvInterval<DvDate> normalRange,
            @Attribute(name = "accuracy") double accuracy,
            @Attribute(name = "accuracyPercent") boolean accuracyPercent,
            @Attribute(name = "value", required = true) String value) {
        super(referenceRanges, normalRange, accuracy, accuracyPercent, value);
    }

    protected DvDate(List<ReferenceRange<DvDate>> referenceRanges,
			DvInterval<DvDate> normalRange, double accuracy, 
			boolean accuracyPercent, DateTime datetime) {
		super(referenceRanges, normalRange, accuracy, accuracyPercent, datetime);
    }

    protected DvDate(DateTime datetime) {
    		super(null, null, 0.0, false, datetime);
    }
    
	/**
	 * Construct a DvDate by current date
	 * 
	 * @param referenceRanges
	 * @param normalRange
	 * @param accuracy
	 * @param accuracyPercent
	 */
	public DvDate(List<ReferenceRange<DvDate>> referenceRanges,
			DvInterval<DvDate> normalRange, double accuracy, 
			boolean accuracyPercent) {
		super(referenceRanges, normalRange, accuracy, accuracyPercent);
	}

    /**
     * Constructs a DvDate with timezone
     *
     * @param year
     * @param month    starts with 0
     * @param day
     * @param timezone null if use default timezone
     */
    public DvDate(int year, int month, int day, TimeZone timezone) {
        super(null, null, 0.0, false, convert(year, month, day, timezone));
    }

    public DvDate(String value) {
    		this(null, null, 0.0, false, value);
    }
    
    protected static DateTime convert(int year, int month, int day, TimeZone timezone) {
		if (timezone == null) {
			return new DateTime(year, month, day, 0, 0, 0, 0);
		} else {
			return new DateTime(year, month, day, 0, 0, 0, 0, DateTimeZone.forTimeZone(timezone));
		}
    }

    protected static DateTime convert(int year, int month, int day, DateTimeZone timezone) {
		if (timezone == null) {
			return new DateTime(year, month, day, 0, 0, 0, 0);
		} else {
			return new DateTime(year, month, day, 0, 0, 0, 0, timezone);
		}
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
     * @return month
     */
    public int getMonth() {
        return getDateTime().getMonthOfYear();
    }

    /**
     * Day in month
     *
     * @return day
     */
    public int getDay() {
        return getDateTime().getDayOfMonth();
    }

    /**
     * If date is valid ISO8601 format
     *
     * @param year
     * @param month
     * @param day
     * @return true if valid
     */
    public static boolean isValidDate(String value) {
        return parse(value) != null;
    }
 
    static DateTime parse(String value) {
    		if(value == null) {
            throw new IllegalArgumentException("null value");
        }
    		DateTime dt = null;
		if (value.indexOf("-") > 0) {
			try {
				dt = eDateFormatter.parseDateTime(value);
			} catch (Exception e) {
				throw new IllegalArgumentException("invalid value");
			}
		} else {
			try {
				dt = dateFormatter.parseDateTime(value);
			} catch (Exception e) {
				throw new IllegalArgumentException("invalid value");
			}
		}		
		return dt;
    }
    
	@Override 
	DateTime parseValue(String value) {
		return parse(value);
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
	public DvQuantified<DvDate> add(DvQuantified q) {
		if (!getDiffType().isInstance(q)) {
			throw new IllegalArgumentException("invalid difference type");
		}
		DvDuration d = (DvDuration) q;
		MutableDateTime mdate = getDateTime().toMutableDateTimeISO();
		mdate.add(d.getPeriod());
		return new DvDate(getOtherReferenceRanges(), getNormalRange(), 
				getAccuracy(), isAccuracyPercent() ,mdate.toDateTimeISO());		
	}


	@Override
	public DvQuantified<DvDate> subtract(DvQuantified q) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean isStrictlyComparableTo(DvOrdered ordered) {
		// TODO Auto-generated method stub
		return false;
	}

    /**
     * Two DvDate equal if both have same year, month, day and timezone
     *
     * @param o
     * @return true if equals
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!( o instanceof DvDate )) return false;
        return super.equals(o);       
    }
    
    /* static field */
    static final DateTimeFormatter eDateFormatter = ISODateTimeFormat.date(); //yyyy-MM-dd
    static final DateTimeFormatter dateFormatter = ISODateTimeFormat.basicDate(); //yyyyMMdd
}
