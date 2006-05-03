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
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/datatypes/quantity/datetime/DvPartialDateTime.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 22:20:08 +0100 (Wed, 12 Oct 2005) $"
 */
package org.openehr.rm.datatypes.quantity.datetime;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.joda.time.format.ISODateTimeFormat;
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

	//Either yyyyMMDD, yyyyMM, yyyy, yyyy-MM or  yyyy-MM-DD, the MM and DD in
	//extended format can be 1 or 2 digits.
    private static String datePATTERN = "(\\d){4}(((-(\\d){1,2}){0,2})|\\d{2}|//d{4})";
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
            @Attribute(name = "normalRange") DvInterval<DvDateTime> normalRange,
            @Attribute(name = "accuracy") double accuracy,
            @Attribute(name = "accuracyPercent") boolean accuracyPercent,
            @Attribute(name = "value", required = true) String value) {
        super(referenceRanges, normalRange, accuracy, accuracyPercent, value);
        value = convertValueToExtended(value);
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
        super(null, null, 0.0, false, parse(value));
    }

    // convert string value to calendar using different patterns
    DateTime parseValue(String value) {
    		return parse(value);
    }

    static DateTime parse(String value) {
    		if (value == null) {
            throw new IllegalArgumentException("null value");
        }
    		String newValue = value;
    		if (value.indexOf(":") < 0 && value.indexOf("-") < 0) {
    			newValue = convertValueToExtended(value);
    		}
    		return ISODateTimeFormat.dateTimeParser().parseDateTime(newValue);
    }
    
    private static String convertValueToExtended(String value) {
 
    		if (value.indexOf("T") > 0) {
			return  convertDateToExtended(value.substring(0, value.indexOf("T"))) + "T" 
					+ DvPartialTime.convertValueToExtended(
							value.substring(value.indexOf("T") + 1));
		} else {
			return convertDateToExtended(value);
		}   		
	}

	private static String convertDateToExtended(String date) {
		if (!date.matches(datePATTERN )) {
			throw new IllegalArgumentException("wrong format for date part");
		}
		if (date.indexOf("-") > 0 || date.length() == 4) {
			return date;
		} else {
			//add '-' to yyyyMM or yyyyMMDD
			if (date.length() > 6) {
				date = date.substring(0, 4) + "-" + date.substring(4, 6) + "-"
				+ date.substring(6);
			} else {				
				date = date.substring(0, 4) + "-" + date.substring(4);
			}
			return date;
		}
	}

	//private DateTimeFormatter formatter;
    private static boolean possiblyMinuteKnown(String value) {
        return value.indexOf(":") > 0;
    }

    private static boolean possiblyHourKnown(String value) {
        return value.indexOf("T") > 0;
    }

    private static boolean possiblyDayKnown(String value) {
        //return value.indexOf("-") > 0 && value.length() > PATTERNS[1].length();
    		return value.lastIndexOf("-") > value.indexOf("-"); 
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
        super(year, month < 1 ? 1 : month, day < 1 ? 1 : day,
                hour < 0 ? 0 : hour, minute < 0 ? 0 : minute, 0, 0, timezone);

        monthKnown = month >= 1; 
        dayKnown = day >= 1;
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
        // Todo: implement this method, why 1/1/0001 instead of 1/1/1970
        return null;
    }

    /**
     * Enclosing date range implied by this partial date/time.
     *
     * @return DvInterval<DvDateTime>
     */
    public DvInterval<DvDateTime> enclosingInterval() {
        int year = getDateTime().getYear();
        int monthStart, monthEnd, dayStart, dayEnd;
        if (!monthKnown) {
            monthStart = getDateTime().monthOfYear().getMinimumValue();
            monthEnd = getDateTime().monthOfYear().getMaximumValue();
        } else {
            monthStart = getDateTime().getMonthOfYear();
            monthEnd = getDateTime().getMonthOfYear();
        }
        if (!dayKnown) {
            dayStart = getDateTime().dayOfMonth().getMinimumValue();
            dayEnd = getDateTime().dayOfMonth().getMaximumValue();
        } else {
            dayStart = getDateTime().getDayOfMonth();
            dayEnd = getDateTime().getDayOfMonth();
        }
        int hourStart, hourEnd;
        if (!hourKnown) {
            hourStart = getDateTime().hourOfDay().getMinimumValue();
            hourEnd = getDateTime().hourOfDay().getMaximumValue();
        } else {
            hourStart = getDateTime().getHourOfDay();
            hourEnd = getDateTime().getHourOfDay();
        }
        int minuteStart, minuteEnd;
        if (!minuteKnown) {
            minuteStart = getDateTime().minuteOfHour().getMinimumValue();
            minuteEnd = getDateTime().minuteOfHour().getMaximumValue();
        } else {
            minuteStart = getDateTime().getMinuteOfHour();
            minuteEnd = getDateTime().getMinuteOfHour();
        }

        int secondStart = getDateTime().secondOfMinute().getMinimumValue();
        int secondEnd = getDateTime().secondOfMinute().getMaximumValue();
        
        // necessary?...
        int splitSecStart = getDateTime().millisOfSecond().getMinimumValue();
        int splitSecEnd = getDateTime().millisOfSecond().getMaximumValue();
        
        DvDateTime start = new DvDateTime(convert(year, monthStart, dayStart,
                hourStart, minuteStart, secondStart, splitSecStart, getDateTime().getZone()));
        DvDateTime end = new DvDateTime(convert(year, monthEnd, dayEnd,
                hourEnd, minuteEnd, secondEnd, splitSecEnd, getDateTime().getZone()));
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
        if (!( o instanceof DvPartialDateTime )) return false;
        if (!super.equals(o)) return false;

        final DvPartialDateTime dvPDateTime = (DvPartialDateTime) o;

        if (monthKnown != dvPDateTime.monthKnown) return false;
        if (dayKnown != dvPDateTime.dayKnown) return false;
        if (hourKnown != dvPDateTime.hourKnown) return false;
        if (minuteKnown != dvPDateTime.minuteKnown) return false;
        
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
            pattern = "yyyy-MM-dd'T'HH:mm";
        } else if (hourKnown) {
            pattern = "yyyy-MM-dd'T'HH";
        } else if (dayKnown) {
            pattern = "yyyy-MM-dd";
        } else if (monthKnown) {
            pattern = "yyyy-MM";
        } else {
            pattern = "yyyy";
        }
        return DateTimeFormat.forPattern(pattern).print(getDateTime());
    }

    /* static fields */
   /* private static final String[] PATTERNS = {
        "yyyy", "yyyy-MM", "yyyy-MM-dd", "yyyy-MM-ddTHH", "yyyy-MM-ddTHH:mm"
    };*/

    // POJO start
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