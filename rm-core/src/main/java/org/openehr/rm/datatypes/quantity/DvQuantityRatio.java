/*
 * component:   "openEHR Reference Implementation"
 * description: "Class DvQuantityRatio"
 * keywords:    "datatypes"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/BRANCHES/RM-1.0-update/libraries/src/java/org/openehr/rm/datatypes/quantity/DvQuantityRatio.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 23:20:08 +0200 (Wed, 12 Oct 2005) $"
 */
package org.openehr.rm.datatypes.quantity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.openehr.rm.Attribute;
import org.openehr.rm.datatypes.basic.DataValue;

/**
 * This class models a ratio of quantities.
 *
 * @author Rong Chen
 * @version 1.0
 */
public final class DvQuantityRatio extends DataValue {

    /**
     * Constructs a QuantityRatio by numerator and denominator
     *
     * @param numerator   not null
     * @param denominator not null
     * @throws IllegalArgumentException if either numerator or
     *                                  denominator is null
     */
    public DvQuantityRatio(@Attribute (name = "numerator", required = true)
            DvQuantified numerator,
                           @Attribute (name = "denominator", required = true)
            DvQuantified denominator) {
        if (numerator == null || denominator == null) {
            throw new IllegalArgumentException("null numerator or denominator");
        }
        this.numerator = numerator;
        this.denominator = denominator;
    }

    /**
     * Returns numerator of ratio
     *
     * @return numerator
     */
    public DvQuantified getNumerator() {
        return numerator;
    }

    /**
     * Returns denominator of ratio
     *
     * @return denominator
     */
    public DvQuantified getDenominator() {
        return denominator;
    }


    /**
     * string form displayable for humans
     *
     * @return string representation
     */
    public String toString() {
        return numerator + " / " + denominator;
    }

    /**
     * Equals if both numerator and denominator equals
     *
     * @param o
     * @return true if equals
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!( o instanceof DvQuantityRatio )) return false;

        final DvQuantityRatio dvQuantityRatio = (DvQuantityRatio) o;

        return new EqualsBuilder()
                .append(numerator, dvQuantityRatio.numerator)
                .append(denominator, dvQuantityRatio.denominator)
                .isEquals();
    }

    /**
     * Return hash code of this QuantityRatio
     *
     * @return hash code
     */
    public int hashCode() {
        return new HashCodeBuilder()
                .append(numerator)
                .append(denominator)
                .toHashCode();
    }

    // POJO start
    private DvQuantityRatio() {
    }

    private void setNumerator(DvQuantified numerator) {
        this.numerator = numerator;
    }

    private void setDenominator(DvQuantified denominator) {
        this.denominator = denominator;
    }    
    // POJO end

    /* fields */
    private DvQuantified numerator;
    private DvQuantified denominator;
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
 *  The Original Code is DvQuantityRatio.java
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