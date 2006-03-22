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
 * file:        "$URL$"
 * revision:    "$LastChangedRevision$"
 * last_change: "$LastChangedDate$"
 */
package org.openehr.rm.datatypes.quantity.datetime;

import org.openehr.rm.Attribute;
import org.openehr.rm.FullConstructor;
import org.openehr.rm.datatypes.quantity.ReferenceRange;
import org.openehr.rm.datatypes.quantity.DvInterval;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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
            @Attribute(name = "accuracy") double accuracy,
            @Attribute(name = "accuracyPercent") boolean accuracyPercent,
            @Attribute(name = "value", required = true) String value) {
        super(referenceRanges, accuracy, accuracyPercent, convert(value));
        this.minuteKnown = value.indexOf(":") > 0;
    }

    private static Calendar convert(String value) {
        if(value == null) {
            throw new IllegalArgumentException("null value");
        }
        if(value.indexOf(":") > 0) {
            return convert(value, LONG_PATTERN);
        } else {
            return convert(value, SHORT_PATTERN);
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
        super(hour, minute, 0, timezone);
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
        return new Long(value().getTime());
    }

    /**
     * Enclosing time range implied by this partial time.
     *
     * @return Interval<DvTime>
     */
    public DvInterval<DvTime> enclosingInterval() {
        Calendar calendar = Calendar.getInstance();
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
        }
        return new DvInterval<DvTime>(start, end);
    }

    /**
     * String representation of this partial time in the form of
     * "HH:mm:??", or "HH:??:??"
     *
     * @return string representation
     */
    public String toString() {
        String pattern;
        if(isMinuteKnown()) {
            pattern = "HH:mm:??";
        } else {
            pattern = "HH:??:??";
        }
        return new SimpleDateFormat(pattern).format(value().getTime());
    }

    /* static fields */
    private static final String LONG_PATTERN = "HH:mm";
    private static final String SHORT_PATTERN = "HH";

    // POJO start
    private DvPartialTime() {
    }

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