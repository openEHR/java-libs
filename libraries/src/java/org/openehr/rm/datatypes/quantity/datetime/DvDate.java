/*
 * component:   "openEHR Reference Implementation"
 * description: "Class DvDate"
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
 * Represents an absolute point in time, as measured on the Gregorian calendar,
 * and specified only to the day.
 *
 * @author Rong Chen
 * @version 1.0
 */
public class DvDate extends DvWorldTime<DvDate> {

    /* static field */
    public static final String PATTERN = "yyyy-MM-dd";


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
            @Attribute(name = "accuracy") double accuracy,
            @Attribute(name = "accuracyPercent") boolean accuracyPercent,
            @Attribute(name = "value", required = true) String value) {
        super(referenceRanges, accuracy, accuracyPercent, value, PATTERN);
    }

    /**
     * Construct a DvDate
     *
     * @param referenceRanges null if not specified
     * @param accuracy        0 if not specified
     * @param accuracyPercent
     * @param calendar
     * @throws IllegalArgumentException
     */
    public DvDate(List<ReferenceRange<DvDate>> referenceRanges,
                  double accuracy, boolean accuracyPercent,
                  Calendar calendar) {
        super(referenceRanges, accuracy, accuracyPercent, calendar);
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
        super(convert(year, month, day, timezone));
    }

    private static Calendar convert(int year, int month, int day,
                                    TimeZone timezone) {
        if(timezone == null) {
            timezone = TimeZone.getDefault();
        }
        Calendar calendar = Calendar.getInstance(timezone);

        // The first month of the year is JANUARY which is 0;
        calendar.set(year, month, day, 0, 0, 0);
        return calendar;
    }

    /**
     * Constructs a DvDate with default timezone
     *
     * @param year
     * @param month
     * @param day
     */
    public DvDate(int year, int month, int day) {
        this(year, month, day, TimeZone.getDefault());
    }

    /**
     * Constructs a DvDate from a date value
     *
     * @param date
     * @throws IllegalArgumentException if date null
     */
    public DvDate(Date date) {
        super(date);
    }

    /**
     * Construct a DvDate by default timezone
     *
     * @throws IllegalArgumentException
     */
    public DvDate() {
        super();
    }

    /**
     * Constructs a DvDate from a string value
     *
     * @param value
     * @throws IllegalArgumentException if wrong date format
     */
    public DvDate(String value) {
        super(convert(value, PATTERN));
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
     * @return month
     */
    public int getMonth() {
        return getCalendar().get(Calendar.MONTH);
    }

    /**
     * Day in month
     *
     * @return day
     */
    public int getDay() {
        return getCalendar().get(Calendar.DAY_OF_MONTH);
    }

    /**
     * If date is valid in Gregorian calendar
     *
     * @param year
     * @param month
     * @param day
     * @return true if valid
     */
    public static boolean isValidDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        if (month < calendar.getMinimum(Calendar.MONTH)
                || month > calendar.getMaximum(Calendar.MONTH)) {
            return false;
        }
        calendar.set(Calendar.MONTH, month);
        return day >= calendar.getMinimum(Calendar.DAY_OF_MONTH)
                && day <= calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
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
     * numeric value of the date as seconds since the calendar origin point 1/1/0001
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
    public DvQuantified<DvDate> add(DvQuantified q) {
        return null;  // todo: implement this method
    }

    /**
     * Difference of this quantity and another whose formal type must
     * be the difference type of this quantity type.
     *
     * @param q
     * @return product of substration
     */
    public DvQuantified<DvDate> subtract(DvQuantified q) {
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
     * Two DvDate equal if both have same year, month, day and timezone
     *
     * @param o
     * @return true if equals
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!( o instanceof DvDate )) return false;

        final DvDate dvDate = (DvDate) o;

        return new EqualsBuilder()
                .append(getYear(), dvDate.getYear())
                .append(getMonth(), dvDate.getMonth())
                .append(getDay(), dvDate.getDay())
                .append(getTimezone(), dvDate.getTimezone())
                .isEquals();
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
 *  The Original Code is DvDate.java
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