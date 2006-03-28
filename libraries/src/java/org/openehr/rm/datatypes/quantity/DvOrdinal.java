/*
 * component:   "openEHR Reference Implementation"
 * description: "Class DvOrdinal"
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
package org.openehr.rm.datatypes.quantity;

import org.openehr.rm.Attribute;
import org.openehr.rm.FullConstructor;
import org.openehr.rm.datatypes.text.DvCodedText;

import java.util.List;

/**
 * Purpose Models rankings and scores, like pain, Apgar values, etc,
 * where there is a) implied ordering, b) no implication that the
 * distance between each value is constant, and c) the total number
 * of values is finite.
 *
 * @author Rong Chen
 * @version 1.0
 */
public final class DvOrdinal extends DvOrdered<DvOrdinal> {

    /**
     * Constructs an Ordinal by referenceRanges, value and symbol
     *
     * @param referenceRanges
     * @param value
     * @param symbol
     * @throws IllegalArgumentException
     */
    @FullConstructor
            public DvOrdinal(
            @Attribute (name = "referenceRanges") List<ReferenceRange<DvOrdinal>> referenceRanges,
            @Attribute (name = "value", required = true) int value,
            @Attribute (name = "symbol", required = true) DvCodedText symbol) {
        super(referenceRanges);
        if (value <= 0) {
            throw new IllegalArgumentException("bad value: " + value);
        }
        if (symbol == null) {
            throw new IllegalArgumentException("null symbol");
        }
        int index = -1;
        if (referenceRanges != null) {
            for (int i = 0, j = referenceRanges.size(); i < j; i++) {
                ReferenceRange range = (ReferenceRange)
                        referenceRanges.get(i);
                if ("limits".equals(range.getMeaning().getValue())) {
                    index = i;
                    break;
                }
            }
            if (index < 0) {
                throw new IllegalArgumentException(
                        "no limits in referenceRanges");
            }
        }
        this.limitsIndex = index;
        this.value = value;
        this.symbol = symbol;
    }

    /**
     * Constructs an Ordinal by value and symbol
     *
     * @param value
     * @param symbol
     * @throws IllegalArgumentException
     */
    public DvOrdinal(int value, DvCodedText symbol) {
        this(null, value, symbol);
    }

    /**
     * string form displayable for humans
     *
     * @return string presentation
     */
    public String toString() {
        return symbol.toString();
    }

    /**
     * @param o the Object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     *         is less than, equal to, or greater than the specified object.
     * @throws ClassCastException if the specified object's type prevents it
     *                            from being compared to this Object.
     */
    public int compareTo(DvOrdered o) {
        final DvOrdinal dvOrdinal = (DvOrdinal) o;
        if (value > dvOrdinal.value) return 1;
        if (value < dvOrdinal.value) return -1;
        return 0;
    }

    /**
     * Ordinal position in enumeration of values
     *
     * @return value
     */
    public int getValue() {
        return value;
    }

    /**
     * Coded textual representation of this value in the enumeration,
     * which may be strings made from  +  symbols, or other
     * enumerations of terms such as "mild", "moderate", "severe",
     * or even the same number series as the values.
     *
     * @return ssymbol
     */
    public DvCodedText getSymbol() {
        return symbol;
    }

    /**
     * Limits of the ordinal enumeration, to allow comparison of an
     * ordinal value to its limits.
     *
     * @return reference range
     */
    public ReferenceRange<DvOrdinal> limits() {
        return getReferenceRanges().get(limitsIndex);
    }

    /**
     * Tests if two instances are strictly comparable.
     *
     * @param ordered
     * @return true if two instances are strictly comparable
     */
    public boolean isStrictlyComparableTo(DvOrdered ordered) {
        if (!( ordered instanceof DvOrdinal )) {
            return false;
        }
        final DvOrdinal dvOrdinal = (DvOrdinal) ordered;

        if (!symbol.getDefiningCode().getTerminologyID().equals(
                dvOrdinal.symbol.getDefiningCode().getTerminologyID())) {
            return false;
        }
        // todo: chevk if symbols are from same subset or value range in the same vocabulary
        return true;
    }

    // POJO start
    private DvOrdinal() {
    }

    private void setValue(int value) {
        this.value = value;
    }

    private void setSymbol(DvCodedText symbol) {
        this.symbol = symbol;
    }

    private void setLimitsIndex(int limitsIndex) {
        this.limitsIndex = limitsIndex;
    }
    // POJO end

    /* fields */
    private int value;
    private DvCodedText symbol;
    private int limitsIndex;
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
 *  The Original Code is DvOrdinal.java
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