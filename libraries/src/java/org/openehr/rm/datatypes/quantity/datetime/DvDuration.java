/*
 * component:   "openEHR Reference Implementation"
 * description: "Class DvDuration"
 * keywords:    "datatypes"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/datatypes/quantity/datetime/DvDuration.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 22:20:08 +0100 (Wed, 12 Oct 2005) $"
 */
package org.openehr.rm.datatypes.quantity.datetime;

import org.joda.time.Instant;
import org.joda.time.MutablePeriod;
import org.joda.time.Period;
import org.joda.time.format.ISOPeriodFormat;
import org.joda.time.format.PeriodFormatter;
import org.openehr.rm.Attribute;
import org.openehr.rm.FullConstructor;
import org.openehr.rm.datatypes.quantity.*;

import java.util.List;
import java.util.StringTokenizer;

/**
 * Represents a period of time with respect to a notional point in
 * time, which is not specified. A sign may be used to indicate the
 * duration is "backwards" in time rather than forwards.
 *
 * @author Rong Chen
 * @version 1.0
 */
public final class DvDuration extends DvCustomaryQuantity<DvDuration> {

    /**
     * Constructs a Duration with referenceRange and accuracy
     *
     * @param referenceRanges
     * @param accuracy
     * @param accuracyPercent
     * @param value
     * @throws IllegalArgumentException if value has wrong format
     */
    @FullConstructor
            public DvDuration(
            @Attribute(name = "referenceRanges") List<ReferenceRange<DvDuration>> referenceRanges,
            @Attribute(name = "normalRange") DvInterval<DvDuration> normalRange,
            @Attribute(name = "accuracy") double accuracy,
            @Attribute(name = "accuracyPercent") boolean accuracyPercent,
            @Attribute(name = "value") String value) {
    	

        super(referenceRanges, normalRange, accuracy, accuracyPercent);

        DvDuration d = DvDuration.getInstance(value);
        this.period = d.period;
        this.years = getYears();
        this.months = getMonths();
        this.days = getDays();
        this.hours = getHours();
        this.minutes = getMinutes();
        this.seconds = getSeconds();
        this.fractionalSeconds = getFractionalSeconds();
    }

    /**
     * Constructs a Duration with referenceRange and accuracy
     *
     * @param referenceRanges
     * @param accuracy
     * @param accuracyPercent
     * @param days
     * @param hours
     * @param minutes
     * @param seconds
     * @param fractionalSeconds
     */
    public DvDuration(
            List<ReferenceRange<DvDuration>> referenceRanges,
            DvInterval<DvDuration> normalRange, double accuracy, 
            boolean accuracyPercent, int years, int months,
            int days, int hours, int minutes, int seconds, 
            double fractionalSeconds) {

        super(referenceRanges, normalRange, accuracy, accuracyPercent);
        if (Math.abs(fractionalSeconds) >= 1.0) {
            throw new IllegalArgumentException(
                    "invalid fraction seconds: " + fractionalSeconds);
        }
        
        if (!isValidCombination(years, months, days, hours, minutes, seconds, fractionalSeconds)) {
            throw new IllegalArgumentException(
                    "invalid days, hours, minutes or seconds, "
            + "days: " + days + ", hours: " + hours + ", minutes: " + minutes
            + ", seconds: " + seconds);
        }

        period = new MutablePeriod(years, months, 0, days, hours, minutes, seconds, 
        		(int)(fractionalSeconds*1000));

    }

    /**
     * Constructs a Duration without referenceRange and accuracy
     *
     * @param days
     * @param hours
     * @param minutes
     * @param seconds
     * @param fractionalSeconds
     */
    public DvDuration(int years, int months, int days, int hours, int minutes, int seconds,
                      double fractionalSeconds) {
        this(null, null, 0, false, years, months, days, hours, minutes, seconds,
                fractionalSeconds);
    }


    protected DvDuration(List<ReferenceRange<DvDuration>> referenceRanges,
            DvInterval<DvDuration> normalRange, double accuracy, 
            boolean accuracyPercent, MutablePeriod period) {
    		super(referenceRanges, normalRange, accuracy, accuracyPercent);
    		this.period = period;
    }
    /**
     * Create a Duration from a ISO8601 string presentation
     *
     * @param value
     * @throws IllegalArgumentException if value null or wrong format
     */
    public static DvDuration getInstance(String value) {
        if (value == null) {
            throw new IllegalArgumentException("null value");
        }
        if (!value.matches(PATTERN)) {
            throw new IllegalArgumentException("Wrong format: " + value);
        }
        MutablePeriod period = null;
        if (value.startsWith("-")) {
            value = value.substring(1, value.length()); //skip '-' 
            period = formatter.parseMutablePeriod(value);
            period = negatePeriod(period);
        } else {
        		period = formatter.parseMutablePeriod(value);
        }

        return new DvDuration(null, null, 0.0, false, period);
    }

    /**
     * Helper method to negate a copy of period
     * 
     * @param mPeriod
     * @return a negated copy of period
     */
    static MutablePeriod negatePeriod(MutablePeriod mPeriod) {
    		MutablePeriod period = mPeriod.copy();
    		period.setYears(-period.getYears());
        period.setMonths(-period.getMonths());
        period.setDays(-period.getDays());
        period.setHours(-period.getHours());
        period.setSeconds(-period.getSeconds());
        period.setMillis(-period.getMillis());
        return period;
    }
    

	/**
     * Converts a customary quantity to a scientific one for
     * comparison or other purposes.
     *
     * @return quantity
     */
    public DvQuantity toQuantity() {
        return new DvQuantity("s", toDouble(), null);
    }

    /**
     * numeric value of the duration as seconds
     *
     * @return getMagnitude
     */
    public Number getMagnitude() {
        return new Double(toDouble());
    }

    // convert dvduration to seconds
    private double toDouble() {
        return Math.abs(period.toDurationFrom(new Instant()).getMillis()/1000);
    }

    /**
     * Sum of this quantity and another whose formal type must be the
     * difference type of this quantity.
     *
     * @param q
     * @return production of addition
     * @throws ClassCastException
     */
    public DvQuantified<DvDuration> add(DvQuantified q) {
        final DvDuration d = (DvDuration) q;
        period.add(d.period);
        return new DvDuration(d.getOtherReferenceRanges(), 
        		d.getNormalRange(), d.getAccuracy(),
                d.isAccuracyPercent(), period);
    }

    /**
     * Negated version of current object
     *
     * @return negated version
     */
    public DvQuantified negate() {
        return new DvDuration(getOtherReferenceRanges(), getNormalRange(), getAccuracy(),
        			isAccuracyPercent() ,negatePeriod(period));
    }

    /**
     * Difference of this quantity and another whose formal type must
     * be the difference type of this quantity type.
     *
     * @param q
     * @return product of substraction
     * @throws ClassCastException
     */
    public DvQuantified<DvDuration> subtract(DvQuantified q) {
        final DvDuration d = (DvDuration) q;
        period.setYears(period.getYears() - d.getPeriod().getYears());
        period.setMonths(period.getMonths() - d.getPeriod().getMonths());
        period.setDays(period.getDays() - d.getPeriod().getDays());
        period.setHours(period.getHours() - d.getPeriod().getHours());
        period.setMinutes(period.getMinutes() - d.getPeriod().getMinutes());
        period.setSeconds(period.getSeconds() - d.getPeriod().getSeconds());
        period.setMillis(period.getMillis() - d.getPeriod().getMillis());
        return new DvDuration(d.getOtherReferenceRanges(), 
        		d.getNormalRange(), d.getAccuracy(),
                d.isAccuracyPercent(), period);
    }

    /**
     * Type of quantity which can be added or subtracted to this
     * quantity. Usually the same type, but may be different as in
     * the case of dates and times.
     *
     * @return diff type
     */
    public Class<DvDuration> getDiffType() {
        return DvDuration.class;
    }

    /**
     * Following ISO 8601, starts with "P", and is followed by a list
     * of periods, each appended by a single letter designator:
     * "D" for days, "H" for hours, "M" for minutes, and "S" for
     * seconds.
     *
     * @return string presentation
     */
    public String toString() {
        return formatter.print(period); 
    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * @param o the Object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     *         is less than, equal to, or greater than the specified object.
     * @throws ClassCastException if the specified object's type prevents it
     *                            from being compared to this Object.
     */
    public int compareTo(DvOrdered o) {
        final DvDuration d = (DvDuration) o;
        
        if (period.getYears() > d.period.getYears()) {
        		return 1;
        } else if (period.getYears() < d.period.getYears()) {
        		return -1;
        }
        if (period.getMonths() > d.period.getMonths()) {
    			return 1;
        } else if (period.getMonths() < d.period.getMonths()) {
    			return -1;
        }
        if (period.getDays() > d.period.getDays()) {
            return 1;
        } else if (period.getDays() < d.period.getDays()) {
            return -1;
        }

        if (period.getHours() > d.period.getHours()) {
            return 1;
        } else if (period.getHours() < d.period.getHours()) {
            return -1;
        }

        if (period.getMinutes() > d.period.getMinutes()) {
            return 1;
        } else if (period.getMinutes() < d.period.getMinutes()) {
            return -1;
        }

        if (period.getSeconds() > d.period.getSeconds()) {
            return 1;
        } else if (period.getSeconds() < d.period.getSeconds()) {
            return -1;
        }

        if (period.getMillis() > d.period.getMillis()) {
            return 1;
        } else if (period.getMillis() < d.period.getMillis()) {
            return -1;
        }
        return 0;
    }

    /**
     * number of years of nominal 365-day length
     * 
     * @return years
     */
	public int getYears() {
		return period.getYears();
	}

    /**
     * number of months of nominal 30 day length
     * 
     * @return months
     */
	public int getMonths() {
		return period.getMonths();
	}

	/**
     * number of 24 hour days
     *
     * @return days
     */
    public int getDays() {
        return period.getDays();
    }

    /**
     * number of 60 minute hours
     *
     * @return hours
     */
    public int getHours() {
        return period.getHours();
    }

    /**
     * number of 60 second minutes
     *
     * @return minutes
     */
    public int getMinutes() {
        return period.getMinutes();
    }

    /**
     * number of seconds
     *
     * @return seconds
     */
    public int getSeconds() {
        return period.getSeconds();
    }

    /**
     * fractional seconds
     *
     * @return fractional seconds
     */
    public double getFractionalSeconds() {
        return period.getMillis() / 10E3;
    }

    /**
     * If time is valid duration within 24h/60min/60sec system of time
     *
     * @param days
     * @param hours
     * @param minutes
     * @param seconds
     * @return true if value
     */
    static boolean isValidCombination(int years, int months, int days, int hours,
                             int minutes, int seconds, double fractionalSeconds) {   		
    		int total = Math.abs(years) + Math.abs(months) + Math.abs(days) + Math.abs(hours)
    				+ Math.abs(minutes) + Math.abs(seconds);
    		boolean zero = total == 0 && fractionalSeconds == 0.0;
    		if (!zero) {
    			int[] arr = {years, months, days, hours, minutes, seconds};
    	   		int max = 0;
        		int min = 0;
        		for (int i = 0; i < arr.length; i++) {       			
        			max = Math.max(arr[i], max);
        			min = Math.min(arr[i], min);
        		}
        		if (max > 0 ) {
        			if (min < 0 || fractionalSeconds < 0.0) {
        				return false;
        			}
        		}
    		} 
    		return true;
    }
    
    /**
     * If time is valid duration within 24h/60min/60sec system of time
     *
     * @param value
     * @return true if value is valid
     */
/*    public static boolean isValidDuration(String value) {
    		DvDuration dd = null;
        try {
        		dd = DvDuration.getInstance(value);
        } catch (IllegalArgumentException iae) {       	
        }
        return dd != null;
    }
*/
    
    /**
     * Tests if two instances are strictly comparable.
     *
     * @param ordered
     * @return true if two instances are strictly comparable
     */
    public boolean isStrictlyComparableTo(DvOrdered ordered) {
        return ( ordered instanceof DvDuration );
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!( o instanceof DvDuration )) return false;
        if (!super.equals(o)) return false;

        final DvDuration dvDuration = (DvDuration) o;
        
 /*       if (years !=  dvDuration.years) return false;
        if (months != dvDuration.months) return false;
        if (days != dvDuration.days) return false;
        if (fractionalSeconds != dvDuration.fractionalSeconds) return false;
        if (hours != dvDuration.hours) return false;
        if (minutes != dvDuration.minutes) return false;
        if (seconds != dvDuration.seconds) return false;*/

        return period.equals(dvDuration.period);
    }

    public int hashCode() {
        int result = super.hashCode();
        long temp;
        result = 29 * result + period.getYears();
        result = 29 * result + period.getMonths();
        result = 29 * result + period.getDays();
        result = 29 * result + period.getHours();
        result = 29 * result + period.getMinutes();
        result = 29 * result + period.getSeconds();
        temp = (period.getMillis()/10E3) != +0.0d ?
                Double.doubleToLongBits(fractionalSeconds) : 0l;
        result = 29 * result + (int) ( temp ^ ( temp >>> 32 ) );

        return result;
    }

    MutablePeriod getPeriod() {
    		return period;
    }
    
    // POJO start
    DvDuration() {
    }

	void setYears (int years) {
		MutablePeriod mp = period.toMutablePeriod();
		mp.setYears(years);
    		this.years = years;
    		
    }
    
    void setMonths (int months) {
    		this.months = months;
    }
    
    void setDays(int days) {
        this.days = days;
    }

    void setHours(int hours) {
        this.hours = hours;
    }

    void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    void setFractionalSeconds(double fractionalSeconds) {
        this.fractionalSeconds = fractionalSeconds;
    }
    // POJO end

    /* fields */
    //keep this temporarily for persistence purpose
    private int years;
    private int months;
    private int days;
    private int hours;
    private int minutes;
    private int seconds;
    private double fractionalSeconds;

    private MutablePeriod period;

    /* static value */
    private static String PATTERN =
            "(-)?P((\\d)*(y|Y))?((\\d)*(m|M))?((\\d)*(d|D))?T((\\d)*(h|H))?((\\d)*(m|M))?((\\d)*(s|S))?";
    //private static String PATTERN_DATE = "(-)?P((\\d)*(y|Y))?((\\d)*(m|M))?((\\d)*(d|D))";
    private static PeriodFormatter formatter = ISOPeriodFormat.standard();
}

/*
 *  ***** BEGIN LICENSE BLOCK *****
 *  Version: MPL 1.1/GPL 2.0/LGPL 2.1
 *
 *  The contents of this file are subject to the Mozilla Public License Version
 *  1.1 (the 'License'); you may not use this file except in compliance with
 *  the License. You may obtain a copy of the License at
 *  http://www.mozilla.org/MPL/
 *
 *  Software distributed under the License is distributed on an 'AS IS' basis,
 *  WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 *  for the specific language governing rights and limitations under the
 *  License.
 *
 *  The Original Code is DvDuration.java
 *
 *  The Initial Developer of the Original Code is Rong Chen.
 *  Portions created by the Initial Developer are Copyright (C) 2003-2004
 *  the Initial Developer. All Rights Reserved.
 *
 *  Contributor(s):
 *
 * Software distributed under the License is distributed on an 'AS IS' basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 *  ***** END LICENSE BLOCK *****
 */