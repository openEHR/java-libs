/*
 * component:   "openEHR Reference Implementation"
 * description: "Class DvDateTime"
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
 * Represents an absolute point in time, specified to the second.
 *
 * @author Rong Chen
 * @version 1.0
 */
public class DvDateTime extends DvWorldTime<DvDateTime> {

    /* static field */
    public static final String PATTERN = "yyyy-MM-dd HH:mm:ss";

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
            @Attribute(name = "accuracy") double accuracy,
            @Attribute(name = "accuracyPercent") boolean accuracyPercent,
            @Attribute(name = "value", required = true) String value) {
        super(referenceRanges, accuracy, accuracyPercent, value, PATTERN);
    }

    /**
     * Construct a DvDateTime
     *
     * @param referenceRanges
     * @param accuracy
     * @param accuracyPercent
     * @param value
     */
    public DvDateTime(List<ReferenceRange<DvDateTime>> referenceRanges,
                      double accuracy, boolean accuracyPercent,
                      Calendar value)  {
        super(referenceRanges, accuracy, accuracyPercent, value);
    }

    /**
     * Construct a DvDateTime by current date and time
     * with a calendar
     *
     * @param calendar
     */
    public DvDateTime(Calendar calendar) {
        super(calendar);
    }

    /**
     * Construct a DvDateTime by current date and time
     * with default timezone
     */
    public DvDateTime() {
        super();
    }

    /**
     * Constructs a DvDateTime from given date
     *
     * @param date
     * @throws IllegalArgumentException if date null
     */
    public DvDateTime(Date date) {
        super(date);
    }

    /**
     * Constructs a DvDateTime from a string value
     *
     * @param value
     * @throws IllegalArgumentException if wrong date format
     */
    public DvDateTime(String value) {
        super(convert(value, PATTERN));
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
    public DvDateTime(int year, int month, int day, int hour, int minute,
                      int second, TimeZone timezone) {
        super(convert(year, month, day, hour, minute, second, timezone));
    }

    private static Calendar convert(int year, int month, int day, int hour,
                                    int minute, int second, TimeZone timezone) {
        if(timezone == null) {
            timezone = TimeZone.getDefault();
        }
        Calendar calendar = Calendar.getInstance(timezone);

        // The first month of the year is JANUARY which is 0;
        calendar.set(year, month, day, hour, minute, second);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
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
     * If valid within Gregorian calendar and within 24h/60min/60sec system of
     * time
     *
     * @return true if valid
     */
    public static boolean isValidDateTime(int year, int month, int day,
                                          int hour, int minute, int second) {
        return DvDate.isValidDate(year, month, day)
                && DvTime.isValidTime(hour, minute, second);
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
     * Numeric value of the quantity in canonical (single value) form
     *
     * @return getMagnitude
     */
    public Long getMagnitude() {
        return new Long(getCalendar().getTimeInMillis());
    }

    /**
     * Sum of this quantity and another whose formal type must be the
     * difference type of this quantity.
     *
     * @param q
     * @return product of addition
     */
    public DvQuantified<DvDateTime> add(DvQuantified q) {
        return null;  // todo: implement this method
    }

    /**
     * Difference of this quantity and another whose formal type must
     * be the difference type of this quantity type.
     *
     * @param q
     * @return product of substraction
     */
    public DvQuantified<DvDateTime> subtract(DvQuantified q) {
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
     * Year
     *
     * @return year
     */
    public int getYear() {
        return getCalendar().get(Calendar.YEAR);
    }

    /**
     * Month in year
     *
     * @return month in year
     */
    public int getMonth() {
        return getCalendar().get(Calendar.MONTH);
    }

    /**
     * Day in month
     *
     * @return day in month
     */
    public int getDay() {
        return getCalendar().get(Calendar.DAY_OF_MONTH);
    }

    /**
     * Hour in day
     *
     * @return hour
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
     * @return second in minute
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
     * Return internal representation of this DvDate
     *
     * @return date
     */
    public Date value() {
        return getCalendar().getTime();
    }
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
 *  The Original Code is DvDateTime.java
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