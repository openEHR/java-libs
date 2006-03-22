/*
 * component:   "openEHR Reference Implementation"
 * description: "Class DvWorldTime"
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

import org.apache.commons.lang.StringUtils;
import org.openehr.rm.datatypes.quantity.DvCustomaryQuantity;
import org.openehr.rm.datatypes.quantity.DvOrdered;
import org.openehr.rm.datatypes.quantity.ReferenceRange;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Abstract concept of time on the real world timeline.
 * All dates assumed to be in the Gregorian calendar.
 *
 * @author Rong Chen
 * @version 1.0
 */
public abstract class DvWorldTime <T extends DvWorldTime>
        extends DvCustomaryQuantity<T> {

    /**
     * Construct a WorldTime by calendar
     *
     * @param referenceRanges null if not specified
     * @param accuracy        0 if not specified
     * @param accuracyPercent
     * @param calendar
     * @throws IllegalArgumentException
     */
    protected DvWorldTime(List<ReferenceRange<T>> referenceRanges,
                          double accuracy, boolean accuracyPercent,
                          Calendar calendar) {
        super(referenceRanges, accuracy, accuracyPercent);
        this.calendar = calendar;
        calendar.set(Calendar.MILLISECOND, 0);
    }

    /**
     * Construct a WorldTime string value
     *
     * @param referenceRanges null if not specified
     * @param accuracy        0 if not specified
     * @param accuracyPercent
     * @param value
     * @param pattern
     * @throws IllegalArgumentException
     */
    protected DvWorldTime(List<ReferenceRange<T>> referenceRanges,
                          double accuracy, boolean accuracyPercent,
                          String value, String pattern) {
        super(referenceRanges, accuracy, accuracyPercent);
        this.calendar = convert(value, pattern);
    }

    /**
     * Construct a WorldTime by a calendar
     *
     * @param calendar
     * @throws IllegalArgumentException
     */
    protected DvWorldTime(Calendar calendar) {
        this(null, 0, false, calendar);
        calendar.set(Calendar.MILLISECOND, 0);
    }

    /**
     * Construct a WorldTime by a datae
     *
     * @param date
     * @throws IllegalArgumentException
     */
    protected DvWorldTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        this.calendar = calendar;
    }

    /**
     * Construct a WorldTime by default timezone
     *
     * @throws IllegalArgumentException
     */
    protected DvWorldTime() {
        this(null, 0, false, Calendar.getInstance());
    }

    /**
     * Offset from Universal Coordinated Time, in the range
     * -1200 - +1200 (note that this can affect the date even
     * if no time is recorded).
     *
     * @return timezone
     */
    public TimeZone getTimezone() {
        return calendar.getTimeZone();
    }

    /**
     * Convert a string value into calendar
     *
     * @param value
     * @param pattern
     * @return caldenar
     */
    protected static Calendar convert(String value, String pattern) {
        if (StringUtils.isEmpty(value) || StringUtils.isEmpty(pattern)) {
            throw new IllegalArgumentException("value or pattern null");
        }
        try {
            Date date = new SimpleDateFormat(pattern).parse(value);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return calendar;
        } catch (ParseException pe) {
            throw new IllegalArgumentException("wrong date format: " + value);
        }
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
        DvWorldTime d = (DvWorldTime) o;
        return getCalendar().getTime().compareTo(d.getCalendar().getTime());
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
     * Get the calendar value
     *
     * @return calendar
     */
    Calendar getCalendar() {
        return calendar;
    }

    /**
     * Return a hashcode of this DvDate
     *
     * @return hashcode
     */
    public int hashCode() {
        return getCalendar().hashCode();
    }

    // POJO start
    void setCalendar(Calendar calendar) {
        calendar.set(Calendar.MILLISECOND, 0);
        this.calendar = calendar;
    }
    // POJO end


    /* fields */
    private Calendar calendar;
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
 *  The Original Code is DvWorldTime.java
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