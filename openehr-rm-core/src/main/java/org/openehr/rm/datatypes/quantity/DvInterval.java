/*
 * component:   "openEHR Reference Implementation"
 * description: "Class DvInterval"
 * keywords:    "datatypes"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/BRANCHES/RM-1.0-update/libraries/src/java/org/openehr/rm/datatypes/quantity/DvInterval.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 23:20:08 +0200 (Wed, 12 Oct 2005) $"
 */
package org.openehr.rm.datatypes.quantity;

import org.openehr.rm.Attribute;
import org.openehr.rm.FullConstructor;
import org.openehr.rm.datatypes.basic.DataValue;
import org.openehr.rm.support.basic.Interval;

/**
 * Generic class defining an interval (ie range) of a comparable type.
 * An interval is a contiguous subrange of a comparable base type.
 * Instancese of this class are immutable.
 *
 * @author Rong Chen
 * @version 1.0
 */
public final class DvInterval <T extends DvOrdered> extends DataValue {

    /**
     * Constructs an Interval
     *
     * @param lower null if unbounded
     * @param upper null if unbounded
     * @throws IllegalArgumentException if lower > upper
     */
    @FullConstructor
            public DvInterval(@Attribute (name = "lower") T lower,
                              @Attribute (name = "upper") T upper) {
        if (lower != null && upper != null
                && upper.compareTo(lower) < 0) {
            throw new IllegalArgumentException("lower > upper");
        }
        interval = new Interval<T>(lower, upper);
    }

    /**
     * Returns lower boundary
     *
     * @return null if not specified
     */
    public T getLower() {
        return interval.getLower();
    }

    /**
     * Returns upper boundary
     *
     * @return null if not specified
     */
    public T getUpper() {
        return interval.getUpper();
    }

    /**
     * Returns true if lower boundary open
     *
     * @return true is unbounded
     */
    public boolean isLowerUnbounded() {
        return interval.getLower() == null;
    }

    /**
     * Returns true if upper boundary open
     *
     * @return true is unbounded
     */
    public boolean isUpperUnbounded() {
        return interval.getUpper() == null;
    }
    
    /**
     * Checks if lower boundary valude included in range
     * 
     * @return true if included
     */    
    public boolean isLowerIncluded() {
    	return interval.isLowerIncluded();
    }
    
    /**
     * Checks if upper boundary valude included in range
     * 
     * @return true if included
     */
    public boolean isUpperIncluded() {
    	return interval.isUpperIncluded();
    }

    /**
     * Returns true if lower >= value and value <= upper
     *
     * @param value not null
     * @return true if given value is within this interval
     * @throws IllegalArgumentException if value is null
     */
    public boolean has(DvOrdered<T> value) {
        if (value == null) {
            throw new IllegalArgumentException("null value");
        }

        return ( interval.isLowerUnbounded() ||
                value.compareTo(interval.getLower()) >= 0 )
                && ( interval.isUpperUnbounded() ||
                value.compareTo(interval.getUpper()) <= 0 );
    }

    /**
     * Equals if both has same value for lower and upper boundaries
     *
     * @param o
     * @return true if equals
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!( o instanceof DvInterval )) return false;

        final DvInterval interval1 = (DvInterval) o;

        if (!interval.equals(interval1.interval)) return false;

        return true;
    }

    /**
     * Return a hash code of this interval
     *
     * @return hash code
     */
    public int hashCode() {
        return interval.hashCode();
    }


    // POJO start
    public void setInterval(Interval<T> interval) {
        this.interval = interval;
    }

    private DvInterval() {
    }

    public Interval<T> getInterval() {
        return interval;
    }
    // POJO end

    /* fields */
    private Interval<T> interval;

	@Override
	public String getReferenceModelName() {
		return "DV_INTERVAL";
	}

	@Override
	public String serialise() {
		// TODO Auto-generated method stub
		return null;
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
 *  The Original Code is DvInterval.java
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