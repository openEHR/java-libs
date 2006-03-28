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
 * file:        "$URL$"
 * revision:    "$LastChangedRevision$"
 * last_change: "$LastChangedDate$"
 */
package org.openehr.rm.datatypes.quantity.datetime;

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
            @Attribute(name = "accuracy") double accuracy,
            @Attribute(name = "accuracyPercent") boolean accuracyPercent,
            @Attribute(name = "value") String value) {

        super(referenceRanges, accuracy, accuracyPercent);

        DvDuration d = DvDuration.getInstance(value);
        this.days = d.days;
        this.hours = d.hours;
        this.minutes = d.minutes;
        this.seconds = d.seconds;
        this.fractionalSeconds = d.fractionalSeconds;
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
            double accuracy, boolean accuracyPercent, int days, int hours,
            int minutes, int seconds, double fractionalSeconds) {

        super(referenceRanges, accuracy, accuracyPercent);

        if (!isValidDuration(days, hours, minutes, seconds)) {
            throw new IllegalArgumentException(
                    "invalid days, hours, minutes or seconds, "
            + "days: " + days + ", hours: " + hours + ", minutes: " + minutes
            + ", seconds: " + seconds);
        }
        if (fractionalSeconds < 0.0 || fractionalSeconds >= 1.0) {
            throw new IllegalArgumentException(
                    "invalid fraction seconds: " + fractionalSeconds);
        }
        this.days = days;
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
        this.fractionalSeconds = fractionalSeconds;
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
    public DvDuration(int days, int hours, int minutes, int seconds,
                      double fractionalSeconds) {
        this(null, 0, false, days, hours, minutes, seconds,
                fractionalSeconds);
    }


    /**
     * Create a Duration from a ISO8601 string presentation
     *
     * @param value
     * @throws IllegalArgumentException if value null or wrong format
     */
    // todo: how about fractional seconds?
    public static DvDuration getInstance(String value) {
        if (value == null) {
            throw new IllegalArgumentException("null value");
        }
        if (!value.matches(PATTERN)) {
            throw new IllegalArgumentException("Wrong format: " + value);
        }
        int days = 0;
        int hours = 0;
        int minutes = 0;
        int seconds = 0;
        int fractionalSeconds = 0;
        boolean negative = false;
        if (value.startsWith("-")) {
            negative = true;
            value = value.substring(1, value.length());
        }
        value = value.substring(1, value.length()); // skip P
        StringTokenizer tokens = new StringTokenizer(value, "dDhHmMsS", true);
        String[] values = new String[ tokens.countTokens() ];
        int index = 0;
        while (tokens.hasMoreTokens()) {
            values[ index++ ] = tokens.nextToken();
        }
        for (int j = 0; j < values.length / 2; j++) {
            String num = values[ j * 2 ];
            String seg = values[ j * 2 + 1 ];
            int i = 0;
            try {
                i = Integer.parseInt(num);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(
                        "wrong format " + value + "," + num);
            }
            if (negative) {
                i = -i;
            }
            if (seg.endsWith("d") || seg.endsWith("D")) {
                days = i;
            } else if (seg.endsWith("h") || seg.endsWith("H")) {
                hours = i;
            } else if (seg.endsWith("m") || seg.endsWith("M")) {
                minutes = i;
            } else {
                seconds = i;
            }
        }
        return new DvDuration(days, hours, minutes, seconds, fractionalSeconds);
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

    // convert duration to seconds
    private double toDouble() {
        return days * 24 * 3600 + hours * 3600
                + minutes * 60 + seconds + fractionalSeconds;
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
        return new DvDuration(d.getReferenceRanges(), d.getAccuracy(),
                d.isAccuracyPercent(), days + d.days,
                hours + d.hours, minutes + d.minutes,
                seconds + d.seconds,
                fractionalSeconds + d.fractionalSeconds);
    }

    /**
     * Negated version of current object
     *
     * @return negated version
     */
    public DvQuantified negate() {
        // todo: throws UnsupportedOperation ?
        return null;
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
        return new DvDuration(d.getReferenceRanges(), d.getAccuracy(),
                d.isAccuracyPercent(), days - d.days,
                hours - d.hours, minutes - d.minutes,
                seconds - d.seconds,
                fractionalSeconds - d.fractionalSeconds);
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
        return new StringBuffer("P")
                .append(days)
                .append("D")
                .append(hours)
                .append("H")
                .append(minutes)
                .append("M")
                .append(seconds)
                .append("S")
                .toString();
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

        if (days > d.days) {
            return 1;
        } else if (days < d.days) {
            return -1;
        }

        if (hours > d.hours) {
            return 1;
        } else if (hours < d.hours) {
            return -1;
        }

        if (minutes > d.minutes) {
            return 1;
        } else if (minutes < d.minutes) {
            return -1;
        }

        if (seconds > d.seconds) {
            return 1;
        } else if (seconds < d.seconds) {
            return -1;
        }

        if (fractionalSeconds > d.fractionalSeconds) {
            return 1;
        } else if (fractionalSeconds < d.fractionalSeconds) {
            return -1;
        }
        return 0;
    }

    /**
     * number of 24 hour days
     *
     * @return days
     */
    public int getDays() {
        return days;
    }

    /**
     * number of 60 minute hours
     *
     * @return hours
     */
    public int getHours() {
        return hours;
    }

    /**
     * number of 60 second minutes
     *
     * @return minutes
     */
    public int getMinutes() {
        return minutes;
    }

    /**
     * number of seconds
     *
     * @return seconds
     */
    public int getSeconds() {
        return seconds;
    }

    /**
     * fractional seconds
     *
     * @return fractional seconds
     */
    public double getFractionalSeconds() {
        return fractionalSeconds;
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
    public static boolean isValidDuration(int days, int hours,
                                          int minutes, int seconds) {
        return days < 365 && hours < 24 && minutes < 60
                && seconds < 60;
    }

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

        if (days != dvDuration.days) return false;
        if (fractionalSeconds != dvDuration.fractionalSeconds) return false;
        if (hours != dvDuration.hours) return false;
        if (minutes != dvDuration.minutes) return false;
        if (seconds != dvDuration.seconds) return false;

        return true;
    }

    public int hashCode() {
        int result = super.hashCode();
        long temp;
        result = 29 * result + days;
        result = 29 * result + hours;
        result = 29 * result + minutes;
        result = 29 * result + seconds;
        temp = fractionalSeconds != +0.0d ?
                Double.doubleToLongBits(fractionalSeconds) : 0l;
        result = 29 * result + (int) ( temp ^ ( temp >>> 32 ) );
        return result;
    }

    // POJO start
    DvDuration() {
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
    private int days;
    private int hours;
    private int minutes;
    private int seconds;
    private double fractionalSeconds;

    /* static value */
    private static String PATTERN =
            "(-)?P((\\d)*(d|D))?((\\d)*(h|H))?((\\d)*(m|M))?((\\d)*(s|S))?";
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