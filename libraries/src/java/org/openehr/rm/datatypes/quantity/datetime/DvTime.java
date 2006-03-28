/*
 * component:   "openEHR Reference Implementation"
 * description: "Class DvTime"
 * keywords:    "datatypes"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL$"
 * revision:    "$LastChangedRevision$"
 * last_change: "$LastChangedDate$"
 */
package org.openehr.rm.datatypes.quantity.datetime;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.openehr.rm.Attribute;
import org.openehr.rm.FullConstructor;
import org.openehr.rm.datatypes.quantity.DvOrdered;
import org.openehr.rm.datatypes.quantity.DvQuantified;
import org.openehr.rm.datatypes.quantity.DvQuantity;
import org.openehr.rm.datatypes.quantity.ReferenceRange;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Represents an absolute point in time from an origin usually
 * interpreted as meaning the start of the current day, specified to
 * the second.
 *
 * @author Rong Chen
 * @version 1.0
 */
public class DvTime extends DvWorldTime<DvTime> {

    /* static field */
    public static final String PATTERN = "HH:mm:ss";

    /**
     * Construct a WorldTime
     *
     * @param referenceRanges null if not specified
     * @param accuracy        0 if not specified
     * @param accuracyPercent
     * @param value
     * @throws IllegalArgumentException
     */
    @FullConstructor
            public DvTime(
            @Attribute(name = "referenceRanges") List<ReferenceRange<DvTime>> referenceRanges,
            @Attribute(name = "accuracy") double accuracy,
            @Attribute(name = "accuracyPercent") boolean accuracyPercent,
            @Attribute(name = "value", required = true) String value) {
        super(referenceRanges, accuracy, accuracyPercent, value, PATTERN);
    }

    /**
     * Construct a WorldTime
     *
     * @param referenceRanges null if not specified
     * @param accuracy        0 if not specified
     * @param accuracyPercent
     * @param calendar
     * @throws IllegalArgumentException
     */
    public DvTime(List<ReferenceRange<DvTime>> referenceRanges,
                   double accuracy, boolean accuracyPercent,
                   Calendar calendar) {
        super(referenceRanges, accuracy, accuracyPercent, calendar);
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
        super(convert(hour, minute, second, timezone));
    }
    
    private static Calendar convert(int hour, int minute, int second,
                                    TimeZone timezone) {
        if(timezone == null) {
            timezone = TimeZone.getDefault();
        }
        Calendar calendar = Calendar.getInstance(timezone);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        return calendar;
    }

    /**
     * Constructs a DvTime from given date
     *
     * @param date
     * @throws IllegalArgumentException if date null
     */
    public DvTime(Date date) {
        super(date);
    }

    /**
     * Constructs a DvTime with default timezone
     *
     * @param hour
     * @param minute
     * @param second
     */
    public DvTime(int hour, int minute, int second) {
        this(hour, minute, second, TimeZone.getDefault());
    }

    /**
     * Constructs a DvTime from a string value
     *
     * @param value
     * @throws IllegalArgumentException if wrong date format
     */
    public DvTime(String value) {
        super(convert(value, PATTERN));
    }


    /**
     * Hour in day
     *
     * @return hour in day
     */
    public int getHour() {
        return getCalendar().get(Calendar.HOUR_OF_DAY);
    }

    /**
     * Minute in hour
     *
     * @return minute in hour
     */
    public int getMinute() {
        return getCalendar().get(Calendar.MINUTE);
    }

    /**
     * Second in minute
     *
     * @return second
     */
    public int getSecond() {
        return getCalendar().get(Calendar.SECOND);
    }

    /**
     * fractional seconds
     *
     * @return fractional seconds
     */
    public double getFractionalSecond() {
        return getCalendar().get(Calendar.MILLISECOND) / 10E3;
    }

    /**
     * If time is valid within 24h/60min/60sec system of time
     *
     * @param hour
     * @param minute
     * @param second
     * @return true if valid
     */
    public static boolean isValidTime(int hour, int minute, int second) {
        return ( hour >= 0 && hour < 24 && minute >= 0 && minute < 60
                && second >= 0 && second < 60 );
    }

    /**
     * Converts a customary quantity to a scientific one for
     * comparison or other purposes.
     *
     * @return quantity
     */
    public DvQuantity toQuantity() {
        return null;  // todo: implement this method
    }

    /**
     * numeric value of the time as seconds since the start of day
     *
     * @return getMagnitude
     */
    public Number getMagnitude() {
        return new Double(getHour() * 60 * 60 + getMinute() * 60 + getSecond()
                + getFractionalSecond());
    }

    /**
     * Sum of this quantity and another whose formal type must be the
     * difference type of this quantity.
     *
     * @param q
     * @return product of addition
     */
    public DvQuantified<DvTime> add(DvQuantified q) {
        return null;  // todo: implement this method
    }

    /**
     * Difference of this quantity and another whose formal type must
     * be the difference type of this quantity type.
     *
     * @param q
     * @return product of substraction
     */
    public DvQuantified<DvTime> subtract(DvQuantified q) {
        return null;  // todo: implement this method
    }

    /**
     * string form displayable for humans
     *
     * @return string presentation
     */
    public String toString() {
        return new SimpleDateFormat(PATTERN).format(getCalendar().getTime());
    }

    /**
     * Tests if two instances are strictly comparable.
     *
     * @param ordered
     * @return true if two instances are strictly comparable
     */
    public boolean isStrictlyComparableTo(DvOrdered ordered) {
        return false;  // todo: implement this method
    }

    /**
     * Return internal representation of this DvDate
     *
     * @return date
     */
    public Date value() {
        return getCalendar().getTime();
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

        final DvTime dvTime = (DvTime) o;

        return new EqualsBuilder()
                .append(getHour(), dvTime.getHour())
                .append(getMinute(), dvTime.getMinute())
                .append(getSecond(), dvTime.getSecond())
                .append(getTimezone(), dvTime.getTimezone())
                .isEquals();
    }

    // POJO start
    DvTime() {
    }
    // POJO end
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
 *  The Original Code is DvTime.java
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