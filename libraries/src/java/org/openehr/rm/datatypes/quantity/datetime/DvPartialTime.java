/*
 * component:   "openEHR Reference Implementation"
 * description: "Class DvPartialTime"
 * keywords:    "datatypes"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/datatypes/quantity/datetime/DvPartialTime.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 22:20:08 +0100 (Wed, 12 Oct 2005) $"
 */
package org.openehr.rm.datatypes.quantity.datetime;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.openehr.rm.Attribute;
import org.openehr.rm.FullConstructor;
import org.openehr.rm.datatypes.quantity.ReferenceRange;
import org.openehr.rm.datatypes.quantity.DvInterval;

import java.util.List;
import java.util.TimeZone;

/**
 * Represents a partially known time. All partial time have an unknown
 * second, by definition, else they would be represented as normal
 * times. The minute_known flag indicates whether the minute is also
 * unknown.
 *
 * @author Rong Chen
 * @version 1.0
 */
public final class DvPartialTime extends DvTime {

    /**
     * Construct a WorldTime
     *
     * @param referenceRanges null if not specified
     * @param accuracy        0 if not specified
     * @param accuracyPercent
     * @throws IllegalArgumentException
     */
    @FullConstructor
            public DvPartialTime(
            @Attribute(name = "referenceRanges") List<ReferenceRange<DvTime>> referenceRanges,
            @Attribute(name = "normalRange") DvInterval<DvTime> normalRange,
            @Attribute(name = "accuracy") double accuracy,
            @Attribute(name = "accuracyPercent") boolean accuracyPercent,
            @Attribute(name = "value", required = true) String value) {
        super(referenceRanges, normalRange, accuracy, accuracyPercent, value);
        this.minuteKnown = value.indexOf(":") > 0;
    }

    DateTime parseValue(String value) {
    		return parse(value);
    }

    protected static DateTime parse(String value) {
    		if(value == null) {
            throw new IllegalArgumentException("null value");
        }
    		//1. Has to do loose checking separately because the 
    		//org.Joda.time.ISODateTimeFormat.timeParser also takes 
    		//incomplete format which should not be accepted in DvTime.	
    		if (!value.matches(PATTERN)) {
    			throw new IllegalArgumentException("wrong format");
    		}
		DateTime dt = null;
		try {
			dt = timePARSER.parseDateTime(convertValueToExtended(value));
		} catch (Exception e) {
			throw new IllegalArgumentException("invalid value");
		}
		return dt;
    }
 
    protected static String convertValueToExtended(String value) {
    		//6 cases to deal with: HHmm, HHmmZ, HH, HHZ, HH:mmZ, HH:mm
    		if (value.indexOf(":") > 0 || value.matches(HHPATTERN)) {
    			return value;
    		} else {
    			//HHmm & HHmmZ left...try to add ':'
    			return value.substring(0, 2) + ":" + value.substring(2);
    		}
    }
    
    /**
     * Constructs a Time with timezone
     *
     * @param hour
     * @param minute
     * @param timezone null if use default timezone
     * @param minuteKnown
     */
    public DvPartialTime(int hour, int minute, TimeZone timezone,
                         boolean minuteKnown) {
        super(hour, minuteKnown ? minute : 0, 0, timezone);
        this.minuteKnown = minuteKnown;
    }

    /**
     * Return true if minute is known
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
        return new Long(getDateTime().getMillis() / 1000);
    }

    /**
     * Enclosing time range implied by this partial time.
     *
     * @return Interval<DvTime>
     */
    public DvInterval<DvTime> enclosingInterval() {
        /*Calendar calendar = Calendar.getInstance();
        calendar.setTime(value());
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int secondStart = calendar.getMinimum(Calendar.SECOND);
        int secondEnd = calendar.getMaximum(Calendar.SECOND);
        DvTime start, end;
        if(minuteKnown) {
            int minute = calendar.get(Calendar.MINUTE);
            start = new DvTime(hour, minute, secondStart, getTimezone());
            end = new DvTime(hour, minute, secondEnd, getTimezone());
        } else {
            int minuteStart = calendar.getMinimum(Calendar.MINUTE);
            int minuteEnd = calendar.getMaximum(Calendar.MINUTE);
            start = new DvTime(hour, minuteStart, secondStart, getTimezone());
            end = new DvTime(hour, minuteEnd, secondEnd, getTimezone());
        }*/
        int hour = getDateTime().getHourOfDay();
		int secStart = getDateTime().secondOfMinute().getMinimumValue();
		int secEnd = getDateTime().secondOfMinute().getMaximumValue();
		DvTime start, end;
		if(minuteKnown) {
			int minute = getDateTime().getSecondOfMinute();
			start = new DvTime(convert(hour, minute, secStart, getDateTime().getZone()));
             end = new DvTime(convert(hour, minute, secEnd, getDateTime().getZone()));
        } else {
            int minStart = getDateTime().minuteOfHour().getMinimumValue();
            int minEnd = getDateTime().minuteOfHour().getMaximumValue();             
            start = new DvTime(convert(hour, minStart, secStart, getDateTime().getZone()));
            end = new DvTime(convert(hour, minEnd, secEnd, getDateTime().getZone()));
        }
        return new DvInterval<DvTime>(start, end);
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

        final DvPartialTime dvPTime = (DvPartialTime) o;

        if (minuteKnown != dvPTime.minuteKnown) return false;

        return true;
    }
    
    public int hashCode() {
        int result = super.hashCode();
        result = 29 * result + ( minuteKnown ? 1 : 0 );
        return result;
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
    
    /* static fields */
    //  timeParser can take care of HH, HHZ, HH:mmZ, HH:mm
    static final DateTimeFormatter timePARSER = ISODateTimeFormat.timeParser();//for extended format
	static final String PATTERN = "(\\d){2}(:?(\\d){2})?(Z|\\+(\\d){2,4}|-(\\d){2,4})?";
//	Pattern for HH and HHZ that do not require ':'
	static final String HHPATTERN = "(\\d){2}(Z|\\+(\\d){2,4}|-(\\d){2,4})?";
    // POJO start
    private void setMinuteKnown(boolean minuteKnown) {
        this.minuteKnown = minuteKnown;
    }
 
    // POJO end

    /* fields */
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
 *  The Original Code is DvPartialTime.java
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