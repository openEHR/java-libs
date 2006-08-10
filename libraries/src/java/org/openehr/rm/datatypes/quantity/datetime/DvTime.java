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
import org.openehr.rm.datatypes.quantity.DvCustomaryQuantity;
import org.openehr.rm.datatypes.quantity.DvInterval;
import org.openehr.rm.datatypes.quantity.DvOrdered;
import org.openehr.rm.datatypes.quantity.DvQuantified;
import org.openehr.rm.datatypes.quantity.DvQuantity;
import org.openehr.rm.datatypes.quantity.ReferenceRange;
import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * @author yinsulim
 * Current DvTime implementation only takes 3 decimal digits for fractional second, and 
 * does not take 24 for hour
 *
 */
public class DvTime extends DvWorldDateTime<DvTime> {

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

    public DvTime(String value) {
        super(null, null, 0, false, value);
    }
    
    /**
     * Constructs a DvTime
     *
     * @param hour
     * @param minute
     * @param second
     * @param fs
     * @param timezone null if use default timezone
     */
    public DvTime(int hour, int minute, int second, double fs, TimeZone timezone) { 		
    	super(null, null, 0.0, false, DvDateTimeParser.convertTime(hour, minute, second, fs, timezone));
        String format = timezone == null ? "HH:mm:ss,SSS" : "HH:mm:ss,SSSZZ";
        setValue(DvDateTimeParser.toTimeString(getDateTime(), format));
        setBooleans(false, true, true, true);
    }
    
    /**
     * Constructs a DvTime
     *
     * @param hour
     * @param minute
     * @param second
     * @param timezone null if use default timezone
     */
    public DvTime(int hour, int minute, int second, TimeZone timezone) { 		
    	super(null, null, 0.0, false, DvDateTimeParser.convertTime(hour, minute, second, 0, timezone));
        String format = timezone == null ? "HH:mm:ss" : "HH:mm:ssZZ";
        setValue(DvDateTimeParser.toTimeString(getDateTime(), format));                    
        setBooleans(false, true, true, false);
    }
 
    /**
     * Constructs a partial DvTime
     *
     * @param hour
     * @param minute
     * @param timezone null if use default timezone
     */
    public DvTime(int hour, int minute, TimeZone timezone) {            		
    	super(null, null, 0.0, false, DvDateTimeParser.convertTime(hour, minute, 0, 0, timezone));
        String format = timezone == null ? "HH:mm" : "HH:mmZZ";
        setValue(DvDateTimeParser.toTimeString(getDateTime(), format));                   
        setBooleans(true, true, false, false);
    }
    
    /**
     * Constructs a partial DvTime
     *
     * @param hour
     * @param timezone null if use default timezone
     */
    public DvTime(int hour, TimeZone timezone) {            		
    	super(null, null, 0.0, false, DvDateTimeParser.convertTime(hour, 0, 0, 0, timezone));
        String format = timezone == null ? "HH" : "HHZZ";
        setValue(DvDateTimeParser.toTimeString(getDateTime(), format));
        setBooleans(true, false, false, false);
    }
    
    /**
     * Constructs a DvTime with current point of time
     * The format of time value as the result of this constructor
     * is HH:mm:ss.SSSZZ
     *
     */
    public DvTime() { 		
        super(null, null, 0.0, false, DvDateTimeParser.defaultTime());
        setValue(DvDateTimeParser.toTimeString(getDateTime(), "HH:mm:ss,SSSZZ"));
        setBooleans(false, true, true, true);
    }
 
    /**
     * Constructs a DvTime, mainly for use in addition and subtraction
     */
    protected DvTime(List<ReferenceRange<DvTime>> referenceRanges,
			DvInterval<DvTime> normalRange, double accuracy, 
			boolean accuracyPercent, DateTime datetime, String pattern) {
        super(referenceRanges, normalRange, accuracy, accuracyPercent, datetime);
        setValue(DvDateTimeParser.toTimeString(getDateTime(), pattern));
        setBooleans(pattern);
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
     * @return minute in hour, -1 if minute unknown
     */
    public int getMinute() {        
        return minuteKnown()? getDateTime().getMinuteOfHour(): -1;
    }

    /**
     * Second in minute
     *
     * @return second, -1 if second unknown
     */
    public int getSecond() {
        return secondKnown? getDateTime().getSecondOfMinute(): -1;
    }

    /**
     * fractional seconds
     *
     * @return fractional seconds, -0.1 if fractional seoncd unknown
     */
    public double getFractionalSecond() {
        return fractionalSecKnown? getDateTime().getMillisOfSecond() / 10E2: -0.1;
    }

    public boolean isPartial() {
        return isPartial;
    }
    
    public boolean minuteKnown() {
        return minuteKnown;
    }
    
    public boolean secondKnown() {
        return secondKnown;
    }
    
    public boolean hasFractionalSecond() {
        return fractionalSecKnown;
    }
    
    /**
     * If date is valid ISO8601 format
     *
     * @param year
     * @param month
     * @param day
     * @return true if valid
     */
    public static boolean isValidISO8601Time(String value) {
        DateTime dt = null;
        try {
            dt = DvDateTimeParser.parseTime(value);
        } catch (Exception e) {
            return false;
        }
        return dt != null;
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
            public DvQuantified<DvTime> add(DvQuantified q) {
        if (!getDiffType().isInstance(q)) {
            throw new IllegalArgumentException("invalid difference type");
        }
        DvDuration d = (DvDuration) q;
        MutableDateTime mdate = getDateTime().toMutableDateTimeISO();
        mdate.add(d.getPeriod());
        return new DvTime(getReferenceRanges(), getNormalRange(),
                getAccuracy(), isAccuracyPercent() ,mdate.toDateTimeISO(),
                this.toString());
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
        if (!super.equals(o)) return false;
        
        final DvTime dtime = (DvTime) o;
        //TODO: check if the following line still necessary
	//if(this.getDateTime().getZone().hashCode()!= this.getDateTime().getZone().hashCode()) {
        //    return false;
        //}
        
        return new EqualsBuilder()
            .append(this.getDateTime().getZone().hashCode(), this.getDateTime().getZone().hashCode())
            .append(isPartial, dtime.isPartial)
            .append(minuteKnown, dtime.minuteKnown)
            .append(secondKnown, dtime.secondKnown)
            .append(fractionalSecKnown, dtime.fractionalSecKnown)
            .isEquals();
    }
    
    public String toString(boolean isExtended) {
        String time = super.toString();
        if(time == null) {
            //TODO
            time = "";
        } else {
            if(isExtended) {
                time = DvDateTimeParser.basicToExtendedTime(time);                
            } else {
                time = time.replace(":", "");
            }            
        }
        return time;
    }
    
    /**
     * IsEquivalent for DvTime is valid only for point of time within one single day.
     * For example, 00:00:01Z(for 1970-01-01) is supposed to be equivalent to the 
     * point of datetime 23:00:01-01(on 1969-12-31). However, because no date is involved 
     * in DvTime, 23:00:01-01 can only be treated as 23:00:01-01(of the same day as the other
     * DvTime in comparison). So 23:00:01-01 is simply interpreted as a point of time on
     * 1970-01-01 in a zone an hour behind UTC.
     * 
     */
    public boolean isEquivalent(Object o) {
        return super.isEquivalent(o);
    }
    
    @Override
    DateTime parseValue(String value) {
        return DvDateTimeParser.parseTime(value);
    }

    void setBooleans(String value) {
        int ele = DvDateTimeParser.analyseTimeString(value);
        //isPartial, minuteKnown, secKnown
        if(ele > 3) {
            setSecondKnown(true);
            setMinuteKnown(true);
            setFractionalSecKnown(true);
        } else if (ele == 3) {
            setSecondKnown(true);
            setMinuteKnown(true);
        } else if (ele == 2) {
            setMinuteKnown(true);
            setIsPartial(true);
        } else if (ele == 1) {
            setIsPartial(true);
        }     
    }

    private void setBooleans(boolean isPartial, boolean minuteKnown, boolean secondKnown, 
            boolean fractionalSecKnown) {
        this.isPartial = isPartial;
        this.minuteKnown = minuteKnown;
        this.secondKnown = secondKnown;
        this.fractionalSecKnown = fractionalSecKnown;
    }
    
    void setIsPartial(boolean isPartial) {
        this.isPartial = isPartial;
    }
    
    void setMinuteKnown(boolean minuteKnown) {
        this.minuteKnown = minuteKnown;
    }
    
    void setSecondKnown(boolean secondKnown) {
        this.secondKnown = secondKnown;
    }
    
    void setFractionalSecKnown(boolean fractionalSecKnown) {
        this.fractionalSecKnown = fractionalSecKnown;
    }
    
    private boolean isPartial, minuteKnown, secondKnown, fractionalSecKnown;
}