/*
 * component:   "openEHR Reference Implementation"
 * description: "Class DvMeasurable"
 * keywords:    "datatypes"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/datatypes/quantity/DvMeasurable.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 22:20:08 +0100 (Wed, 12 Oct 2005) $"
 */
package org.openehr.rm.datatypes.quantity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.util.List;

/**
 * Abstract class defining the concept of true quantified values,
 * which are not only ordered, but whose getMagnitude is meaningful
 * as well.
 *
 * @author Rong Chen
 * @version 1.0
 */
public abstract class DvMeasurable<T extends DvMeasurable>
        extends DvQuantified<T> {
    
    /**
     * Constructs a Measurable by all components
     *
     * @param referenceRanges
     * @param normalRange 
     * @param accuracy
     * @param accuracyPercent
     * @param units             not null
     * @throws IllegalArgumentException if any component invalid
     */
    protected DvMeasurable(List<ReferenceRange<T>> referenceRanges,
                           DvInterval<T> normalRange,double accuracy,
                           boolean accuracyPercent, String units) {
        super(referenceRanges, normalRange, accuracy, accuracyPercent);
        if (units == null) {
            throw new IllegalArgumentException("null units");
        }
        this.units = units;
    }

    /**
     * Constructs a Measurable only by units
     *
     * @param units not null
     * @throws IllegalArgumentException if units is null
     */
    protected DvMeasurable(String units) {
        this(null, null, 0, false, units);
    }

    /**
     * Stringified units, expressed in UCUM unit syntax,
     * like "kg/m2", "mm[Hg]", "ms-1", "km/h".
     *
     * @return units
     */
    public String getUnits() {
        return units;
    }

    /**
     * Equals if super.equls() true and units equals
     *
     * @param o
     * @return true if equals
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!( o instanceof DvMeasurable )) return false;

        final DvMeasurable measurable = (DvMeasurable) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(units, measurable.units)
                .isEquals();
    }

    /**
     * Return hash code of this measurable
     *
     * @return hash code
     */
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(units)
                .toHashCode();
    }

    // POJO start
    DvMeasurable() {
    }

    void setUnits(String units) {
        this.units = units;
    }
    // POJO end

    /* fields */
    private String units;
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
 *  The Original Code is DvMeasurable.java
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