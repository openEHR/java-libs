/*
 * component:   "openEHR Reference Implementation"
 * description: "Class Interval"
 * keywords:    "support"
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
package org.openehr.rm.support.basic;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.openehr.rm.RMObject;

/**
 * Interval of comparable items. Instances of this class
 * are immutable.
 *
 * @author Rong Chen
 * @version 1.0
 */
public final class Interval<T extends Comparable> extends RMObject {

    /**
     * Constructs an Interval
     *
     * @param lower          null if unbounded
     * @param upper          null if unbounded
     * @param lowerInclusive if lower boundary inclusive
     * @param upperInclusive if upper boundary inclusive
     * @throws IllegalArgumentException if lower > upper
     */
    public Interval(T lower, T upper,
                    boolean lowerInclusive, boolean upperInclusive) {
        if (lower != null && upper != null
                && upper.compareTo(lower) < 0) {
            throw new IllegalArgumentException("lower > upper");
        }
        this.lower = lower;
        this.upper = upper;
        this.lowerInclusive = (lower == null ? false : lowerInclusive);
        this.upperInclusive = (upper == null ? false : upperInclusive);
    }

    /**
     * Constructs an inclusive Interval
     *
     * @param lower null if unbounded
     * @param upper null if unbounded
     * @throws IllegalArgumentException if lower > upper
     */
    public Interval(T lower, T upper) {
        this(lower, upper, true, true);
    }

    /**
     * Returns the lower boundary of this Interval
     *
     * @return null if unbounded
     */
    public T getLower() {
        return lower;
    }

    /**
     * Returns the upper boundary of this Interval
     *
     * @return null if unbounded
     */
    public T getUpper() {
        return upper;
    }

    /**
     * Returns true if lower boundary open
     *
     * @return true if has lower boundary
     */
    public boolean isLowerUnbounded() {
        return lower == null;
    }

    /**
     * Returns true if upper boundary open
     *
     * @return true if has upper boundary
     */
    public boolean isUpperUnbounded() {
        return upper == null;
    }

    /**
     * Return true if lower boundary inclusive
     *
     * @return true if inclusive
     */
    public boolean isLowerInclusive() {
        return lowerInclusive;
    }

    /**
     * Return true if upper boundary inclusive
     *
     * @return true if inclusive
     */
    public boolean isUpperInclusive() {
        return upperInclusive;
    }

    /**
     * Returns true if (lower >= value and value <= upper)
     *
     * @param value to compare to
     * @return ture if given value is included in this Interval
     * @throws IllegalArgumentException if value is null
     */
    public boolean has(T value) {
        if (value == null) {
            throw new IllegalArgumentException("null value");
        }
        return ( lower == null
                || value.compareTo(lower) > 0
                || ( lowerInclusive && value.compareTo(lower) == 0 ) )
                && ( upper == null
                || value.compareTo(upper) < 0
                || ( upperInclusive && value.compareTo(upper) == 0 ) );
    }

    /**
     * Equals if two Intervals have same values for lower and
     * upper boundaries
     *
     * @param o the object to compare with
     * @return true if equals
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!( o instanceof Interval )) return false;

        final Interval interval = (Interval) o;

        return new EqualsBuilder()
                .append(lower, interval.lower)
                .append(upper, interval.upper)
                .append(lowerInclusive, interval.lowerInclusive)
                .append(upperInclusive, interval.upperInclusive)
                .isEquals();
    }

    /**
     * Return a hash code of this Interval
     *
     * @return hash code
     */
    public int hashCode() {
        return new HashCodeBuilder()
                .append(lower)
                .append(upper)
                .append(lowerInclusive)
                .append(upperInclusive)
                .toHashCode();
    }

    /**
     * Return string presentation of this Interval. The string
     * consists of both lower and upper boundary, if any of them
     * is not specified, "unbounded" is provided.
     *
     * @return string presentation
     */
    public String toString() {
        return new ToStringBuilder(this)
                .append("lower", lower)
                .append("lowerInclusive", lowerInclusive)
                .append("upper", upper)
                .append("upperInclusive", upperInclusive)
                .toString();
    }

    // POJO start
    private Interval() {
    }

    private void setLower(T lower) {
        this.lower = lower;
    }

    private void setUpper(T upper) {
        this.upper = upper;
    }

    private void setLowerInclusive(boolean lowerInclusive) {
        this.lowerInclusive = lowerInclusive;
    }

    private void setUpperInclusive(boolean upperInclusive) {
        this.upperInclusive = upperInclusive;
    }
    // POJO end

    /* fields */
    private T lower;
    private T upper;
    private boolean lowerInclusive;
    private boolean upperInclusive;
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
 *  The Original Code is Interval.java
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