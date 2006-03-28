/*
 * component:   "openEHR Reference Implementation"
 * description: "Class DvPartialDateTime"
 * keywords:    "datatypes"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004,2005 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL$"
 * revision:    "$LastChangedRevision$"
 * last_change: "$LastChangedDate$"
 */
package org.openehr.rm.datatypes.quantity.datetime;

import org.openehr.rm.FullConstructor;
import org.openehr.rm.Attribute;
import org.openehr.rm.datatypes.quantity.ReferenceRange;
import org.openehr.rm.datatypes.quantity.DvInterval;

import java.util.List;
import java.util.Calendar;
import java.util.TimeZone;
import java.text.SimpleDateFormat;

/**
 * Represents a partially known date/time. All partial date/times have a known
 * year, and an unknown second, by definition, else they would be represented
 * as normal dates. The month_known, day_known, hour_known and minute_known
 * flags indicates which of the other parts are unknown.
 *
 * @author Rong Chen
 * @version 1.0
 */
public class DvPartialDateTime extends DvDateTime {

    /**
     * Construct a DvPartialDateTime
     *
     * @param referenceRanges null if not specified
     * @param accuracy        0 if not specified
     * @param accuracyPercent
     * @param value
     * @throws IllegalArgumentException if value has wrong format
     */
    @FullConstructor
            public DvPartialDateTime(
            @Attribute(name = "referenceRanges") List<ReferenceRange<DvDateTime>> referenceRanges,
            @Attribute(name = "accuracy") double accuracy,
            @Attribute(name = "accuracyPercent") boolean accuracyPercent,
            @Attribute(name = "value", required = true) String value) {
        super(referenceRanges, accuracy, accuracyPercent, convert(value));
        this.minuteKnown = possiblyMinuteKnown(value);
        this.hourKnown = possiblyHourKnown(value);
        this.dayKnown = possiblyDayKnown(value);
        this.monthKnown = possiblyMonthKnown(value);
    }

    /**
     * Construct a DvPartialDateTime
     *
     * @param value
     * @throws IllegalArgumentException if value has wrong format
     */
    public DvPartialDateTime(String value) {
        super(null, 0.0, false, convert(value));
        this.minuteKnown = possiblyMinuteKnown(value);
        this.hourKnown = possiblyHourKnown(value);
        this.dayKnown = possiblyDayKnown(value);
        this.monthKnown = possiblyMonthKnown(value);
    }

    // convert string value to calendar using different patterns
    private static Calendar convert(String value) {
        if (value == null) {
            throw new IllegalArgumentException("null value");
        }
        if (possiblyMinuteKnown(value)) {

            return convert(value, PATTERNS[4]);

        } else if(value.indexOf(" ") > 0) {

            return convert(value, PATTERNS[3]);

        } else if (possiblyDayKnown(value)) {

            return convert(value, PATTERNS[2]);

        } else if (possiblyMonthKnown(value)) {

            return convert(value, PATTERNS[1]);

        } else {
            return convert(value, PATTERNS[0]);
        }
    }

    private static boolean possiblyMinuteKnown(String value) {
        return value.indexOf(":") > 0;
    }

    private static boolean possiblyHourKnown(String value) {
        return value.indexOf(" ") > 0;
    }

    private static boolean possiblyDayKnown(String value) {
        return value.indexOf("-") > 0 && value.length() > PATTERNS[1].length();
    }

    private static boolean possiblyMonthKnown(String value) {
        return value.indexOf("-") > 0;
    }

    /**
     * Constructs a DvPartialDateTime with year and timezone
     *
     * @param year
     * @param timezone null if use default timezone
     */
    public DvPartialDateTime(int year, TimeZone timezone) {
         this(year, -1, -1, -1, -1, timezone);
    }

    /**
     * Constructs a DvPartialDateTime with year, month and timezone
     *
     * @param year
     * @param month
     * @param timezone null if use default timezone
     */
    public DvPartialDateTime(int year, int month, TimeZone timezone) {
        this(year, month, -1, -1, -1, timezone);
    }

    /**
     * Constructs a DvPartialDateTime with year, month, day and timezone
     *
     * @param year
     * @param month
     * @param day
     * @param timezone null if use default timezone
     */
    public DvPartialDateTime(int year, int month, int day, TimeZone timezone) {
        this(year, month, day, -1, -1, timezone);
    }

    /**
     * Constructs a DvPartialDateTime with year, month, day, hour and timezone
     *
     * @param year
     * @param month
     * @param day
     * @param hour
     * @param timezone null if use default timezone
     */
    public DvPartialDateTime(int year, int month, int day, int hour,
                             TimeZone timezone) {
        this(year, month, day, hour, -1, timezone);
    }

    /**
     * Constructs a DvPartialDateTime with year and timezone
     *
     * @param year
     * @param month
     * @param day
     * @param hour
     * @param minute
     * @param timezone null if use default timezone
     */
    public DvPartialDateTime(int year, int month, int day, int hour,
                             int minute, TimeZone timezone) {
        super(year, month < 0 ? 0 : month, day < 0 ? 1 : day,
                hour < 0 ? 0 : hour, minute < 0 ? 0 : minute, 0, timezone);
        monthKnown = month >= 0;
        dayKnown = day >= 0;
        hourKnown = hour >= 0;
        minuteKnown = minute >= 0;
    }


    /**
     * Indicates whether month in year is known
     *
     * @return true if month known
     */
    public boolean isMonthKnown() {
        return monthKnown;
    }

    /**
     * Indicates whether day in month is known
     *
     * @return true if day known
     */
    public boolean isDayKnown() {
        return dayKnown;
    }

    /**
     * Indicates whether hour in day is known
     *
     * @return true if hour known
     */
    public boolean isHourKnown() {
        return hourKnown;
    }

    /**
     * Indicates whether minute in hour is known
     *
     * @return true if minute known
     */
    public boolean isMinuteKnown() {
        return minuteKnown;
    }

    /**
     * Canonical value of enclosing_interval.midpoint.
     *
     * @return getMagnitude
     */
    public Long getMagnitude() {
        // todo: implement this method, why 1/1/0001 instead of 1/1/1970
        return new Long(value().getTime());
    }

    /**
     * Enclosing date range implied by this partial date/time.
     *
     * @return DvInterval<DvDateTime>
     */
    public DvInterval<DvDateTime> enclosingInterval() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(value());
        int year = calendar.get(Calendar.YEAR);
        int monthStart, monthEnd, dayStart, dayEnd;
        if (!monthKnown) {
            monthStart = calendar.getActualMinimum(Calendar.MONTH);
            monthEnd = calendar.getActualMaximum(Calendar.MONTH);
        } else {
            monthStart = calendar.get(Calendar.MONTH);
            monthEnd = calendar.get(Calendar.MONTH);
        }
        if (!dayKnown) {
            calendar.set(Calendar.MONTH, monthEnd);
            dayStart = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
            dayEnd = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        } else {
            dayStart = calendar.get(Calendar.DAY_OF_MONTH);
            dayEnd = calendar.get(Calendar.DAY_OF_MONTH);
        }
        int hourStart, hourEnd;
        if (!hourKnown) {
            hourStart = calendar.getActualMinimum(Calendar.HOUR_OF_DAY);
            hourEnd = calendar.getActualMaximum(Calendar.HOUR_OF_DAY);
        } else {
            hourStart = calendar.get(Calendar.HOUR_OF_DAY);
            hourEnd = calendar.get(Calendar.HOUR_OF_DAY);
        }
        int minuteStart, minuteEnd;
        if (!minuteKnown) {
            minuteStart = calendar.getActualMinimum(Calendar.MINUTE);
            minuteEnd = calendar.getActualMaximum(Calendar.MINUTE);
        } else {
            minuteStart = calendar.get(Calendar.MINUTE);
            minuteEnd = calendar.get(Calendar.MINUTE);
        }

        int secondStart = calendar.getActualMinimum(Calendar.SECOND);
        int secondEnd = calendar.getActualMaximum(Calendar.SECOND);
        DvDateTime start = new DvDateTime(year, monthStart, dayStart,
                hourStart, minuteStart, secondStart, getTimezone());
        DvDateTime end = new DvDateTime(year, monthEnd, dayEnd,
                hourEnd, minuteEnd, secondEnd, getTimezone());
        return new DvInterval<DvDateTime>(start, end);
    }

    /**
     * Equals if both have same values
     *
     * @param o
     * @return true if equals
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!( o instanceof DvPartialDate )) return false;
        if (!super.equals(o)) return false;

        final DvPartialDateTime dvPartialDate = (DvPartialDateTime) o;

        if (monthKnown != dvPartialDate.monthKnown) return false;

        return true;
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 29 * result + ( monthKnown ? 1 : 0 );
        return result;
    }

    /**
     * String representation of this partial date in the form of
     * "yyyy-MM-??", or "yyyy-??-??"
     *
     * @return string representation
     */
    public String toString() {
        String pattern;
        if (minuteKnown) {
            pattern = "yyyy-MM-dd HH:mm:??";
        } else if (hourKnown) {
            pattern = "yyyy-MM-dd HH:??:??";
        } else if (dayKnown) {
            pattern = "yyyy-MM-dd ??:??:??";
        } else if (monthKnown) {
            pattern = "yyyy-MM-?? ??:??:??";
        } else {
            pattern = "yyyy-??-?? ??:??:??";
        }
        return new SimpleDateFormat(pattern).format(value().getTime());
    }

    /* static fields */
    private static final String[] PATTERNS = {
        "yyyy", "yyyy-MM", "yyyy-MM-dd", "yyyy-MM-dd HH", "yyyy-MM-dd HH:mm"
    };

    // POJO start
    private DvPartialDateTime() {
    }

    private void setMonthKnown(boolean monthKnown) {
        this.monthKnown = monthKnown;
    }

    private void setDayKnown(boolean dayKnown) {
        this.dayKnown = dayKnown;
    }

    private void setHourKnown(boolean hourKnown) {
        this.hourKnown = hourKnown;
    }

    private void setMinuteKnown(boolean minuteKnown) {
        this.minuteKnown = minuteKnown;
    }
    // POJO end

    /* fields */
    private boolean monthKnown;
    private boolean dayKnown;
    private boolean hourKnown;
    private boolean minuteKnown;
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
 *  The Original Code is DvPartialDateTime.java
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