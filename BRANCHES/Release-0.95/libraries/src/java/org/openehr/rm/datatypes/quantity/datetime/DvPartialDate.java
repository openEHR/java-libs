/*
 * component:   "openEHR Reference Implementation"
 * description: "Class DvPartialDate"
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
 * Represents a partially known date. All partial dates have an
 * unknown day, by definition, else they would be represented as
 * normal dates. The month_known flag indicates whether the month is
 * also unknown.
 *
 * @author Rong Chen
 * @version 1.0
 */
public final class DvPartialDate extends DvDate {

    /**
     * Construct a WorldTime
     *
     * @param referenceRanges null if not specified
     * @param accuracy        0 if not specified
     * @param accuracyPercent
     * @param value
     * @throws IllegalArgumentException if value has wrong format
     */
    @FullConstructor
            public DvPartialDate(
            @Attribute(name = "referenceRanges") List<ReferenceRange<DvDate>> referenceRanges,
            @Attribute(name = "accuracy") double accuracy,
            @Attribute(name = "accuracyPercent") boolean accuracyPercent,
            @Attribute(name = "value", required = true) String value) {
        super(referenceRanges, accuracy, accuracyPercent, convert(value));
        this.monthKnown = value.indexOf("-") > 0;
    }

    private static Calendar convert(String value) {
        if(value == null) {
            throw new IllegalArgumentException("null value");
        }
        if(value.indexOf("-") > 0) {
            return convert(value, LONG_PATTERN);
        } else {
            return convert(value, SHORT_PATTERN);
        }
    }

    /**
     * Constructs a Date with timezone
     *
     * @param year
     * @param month starts with 0
     * @param timezone null if use default timezone
     * @param monthKnown
     */
    public DvPartialDate(int year, int month, TimeZone timezone,
                         boolean monthKnown) {
        super(year, monthKnown ? month : 0, 1, timezone);
        this.monthKnown = monthKnown;
    }

    /**
     * Indicates whether month in year is known. If so, the date is of the
     * form y/m/?, if not, it is of the form y/?/?
     *
     * @return true if month known
     */
    public boolean isMonthKnown() {
        return monthKnown;
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
     * Enclosing date range implied by this partial date.
     *
     * @return Interval<DvDate>
     */
    public DvInterval<DvDate> enclosingInterval() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(value());
        int year = calendar.get(Calendar.YEAR);
        int dayStart = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
        int dayEnd; // different from month to month
        DvDate start, end;
        if(monthKnown) {
            dayEnd = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            int month = calendar.get(Calendar.MONTH);
            start = new DvDate(year, month, dayStart, getTimezone());
            end = new DvDate(year, month, dayEnd, getTimezone());
        } else {
            int monthStart = calendar.getActualMinimum(Calendar.MONTH);
            int monthEnd = calendar.getActualMaximum(Calendar.MONTH);
            calendar.set(Calendar.MONTH, monthEnd);
            dayEnd = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            start = new DvDate(year, monthStart, dayStart, getTimezone());
            end = new DvDate(year, monthEnd, dayEnd, getTimezone());
        }
        return new DvInterval<DvDate>(start, end);
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

        final DvPartialDate dvPartialDate = (DvPartialDate) o;

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
        if(isMonthKnown()) {
            pattern = "yyyy-MM-??";
        } else {
            pattern = "yyyy-??-??";
        }
        return new SimpleDateFormat(pattern).format(value().getTime());
    }

    /* static fields */
    private static final String LONG_PATTERN = "yyyy-MM";
    private static final String SHORT_PATTERN = "yyyy";

    // POJO start
    private DvPartialDate() {
    }

    private void setMonthKnown(boolean monthKnown) {
        this.monthKnown = monthKnown;
    }
    // POJO end

    /* fields */
    private boolean monthKnown;
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
 *  The Original Code is DvPartialDate.java
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