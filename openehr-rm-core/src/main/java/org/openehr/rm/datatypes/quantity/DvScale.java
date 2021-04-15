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
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/datatypes/quantity/DvOrdinal.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 22:20:08 +0100 (Wed, 12 Oct 2005) $"
 */
package org.openehr.rm.datatypes.quantity;

import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.openehr.rm.Attribute;
import org.openehr.rm.FullConstructor;
import org.openehr.rm.datatypes.basic.DataValue;
import org.openehr.rm.datatypes.basic.ReferenceModelName;
import org.openehr.rm.datatypes.text.DvCodedText;

/** Purpose Models rankings and scores, like Borg score , etc,
 * where there is a) implied ordering, b) no implication that the
 * distance between each value is constant, and c) the total number
 * of values is finite. Instead of Integer uses Real.
 * @author Sebastian Garde
 * @version 1.0 */
public final class DvScale extends DvOrdered<DvScale> {

    /**
     *
     */
    private static final long serialVersionUID = 4901266284988836885L;

    /**
     * Constructs an Ordinal by referenceRanges, value and symbol
     *
     * @param otherReferenceRanges
     * @param normalRange
     * @param value
     * @param symbol
     * @throws IllegalArgumentException
     */
    @FullConstructor
    public DvScale(
            @Attribute(name = "otherReferenceRanges") List<ReferenceRange<DvScale>> otherReferenceRanges,
            @Attribute(name = "normalRange") DvInterval<DvScale> normalRange,
            @Attribute(name = "value", required = true) double value,
            @Attribute(name = "symbol", required = true) DvCodedText symbol) {
        super(otherReferenceRanges, normalRange);
        if (symbol == null) {
            throw new IllegalArgumentException("null symbol");
        }
        int index = -1;
        if (otherReferenceRanges != null) {
            for (int i = 0, j = otherReferenceRanges.size(); i < j; i++) {
                ReferenceRange range = otherReferenceRanges.get(i);
                if ("limits".equals(range.getMeaning().getValue())) {
                    index = i;
                    break;
                }
            }
            if (index < 0) {
                throw new IllegalArgumentException(
                        "no limits in otherReferenceRanges");
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
    public DvScale(double value, DvCodedText symbol) {
        this(null, null, value, symbol);
    }

    /**
     * Constructs an Ordinal by value and symbol
     *
     * @param value
     * @param dvCodedTextValue
     * @param dvCodedTextTerminology
     * @param dvCodedTextCode
     * @throws IllegalArgumentException
     */
    public DvScale(double value, String dvCodedTextValue, String dvCodedTextTerminology, String dvCodedTextCode) {
        this(null, null, value, new DvCodedText(dvCodedTextValue, dvCodedTextTerminology, dvCodedTextCode));
    }

    /**
     * string form displayable for humans
     *
     * @return string presentation
     */
    @Override
    public String toString() {
        return value + "|" + symbol.toString();
    }

    /**
     * @param o the Object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws ClassCastException if the specified object's type prevents it
     *                            from being compared to this Object.
     */
    public int compareTo(DvOrdered o) {
        final DvScale dvOrdinal = (DvScale) o;
        if (value > dvOrdinal.value) {
            return 1;
        }
        if (value < dvOrdinal.value) {
            return -1;
        }
        return 0;
    }

    /**
     * Ordinal position in enumeration of values
     *
     * @return value
     */
    public double getValue() {
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
    public ReferenceRange<DvScale> limits() {
        return getOtherReferenceRanges().get(limitsIndex);
    }

    /**
     * Equals if value and symbol equal in value
     *
     * @param o
     * @return true if equals
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DvScale)) {
            return false;
        }

        final DvScale ord = (DvScale) o;

        return new EqualsBuilder()
                .append(value, ord.value)
                .append(symbol, ord.symbol)
                .isEquals();
    }

    /**
     * Return hash code of this DvOrdinal
     *
     * @return hash code
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(3, 31)
                .append(value)
                .append(symbol)
                .toHashCode();
    }

    /**
     * Tests if two instances are strictly comparable.
     *
     * @param ordered
     * @return true if two instances are strictly comparable
     */
    @Override
    public boolean isStrictlyComparableTo(DvOrdered ordered) {
        if (!(ordered instanceof DvScale)) {
            return false;
        }
        final DvScale dvOrdinal = (DvScale) ordered;

        if (!symbol.getDefiningCode().getTerminologyId().equals(
                dvOrdinal.symbol.getDefiningCode().getTerminologyId())) {
            return false;
        }
        // todo: check if symbols are from same subset or value range in the same vocabulary
        return true;
    }

    public String getTerminologyId() {
        return getSymbol().getTerminologyId();
    }

    public String getCode() {
        return getSymbol().getCode();
    }

    public String getSymbolValue() {
        return getSymbol().getValue();
    }

    // POJO start
    private DvScale() {
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setSymbol(DvCodedText symbol) {
        this.symbol = symbol;
    }

    private void setLimitsIndex(int limitsIndex) {
        this.limitsIndex = limitsIndex;
    }
    // POJO end

    /* fields */
    private double value;
    private DvCodedText symbol;
    private int limitsIndex;

    @Override
    public String getReferenceModelName() {
        return "DV_SCALE";
    }


    @Override
    public String serialise() {
        return getReferenceModelName() + "," + toString();
    }

    @Override
    public DataValue parse(String value) {
        return valueOf(value);
    }

    public static DvScale valueOf(String value) {
        int i = value.indexOf("|");
        if (i < 0) {
            throw new IllegalArgumentException("failed to parse DvScale '" + value + "', wrong number of tokens.");
        }
        double ordinalValue = 0;
        try {
            ordinalValue = Double.parseDouble(value.substring(0, i));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("failed to parse DvScale '" + value + "', invalid double/real value.");
        }
        String str = ReferenceModelName.DV_CODED_TEXT.getName() + "," + value.substring(i + 1);
        return new DvScale(ordinalValue, (DvCodedText) DataValue.parseValue(str));
    }
}

/* ***** BEGIN LICENSE BLOCK *****
 * Version: MPL 1.1/GPL 2.0/LGPL 2.1
 *
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the 'License'); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an 'AS IS' basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 * The Original Code is DvScale.java
 *
 * The Initial Developer of the Original Code is Sebastian Garde.
 * Portions created by the Initial Developer are Copyright (C) 2020
 * the Initial Developer. All Rights Reserved.
 *
 * Contributor(s): Rong Chen (for DvOrdinal)
 *
 * Software distributed under the License is distributed on an 'AS IS' basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 * ***** END LICENSE BLOCK ***** */
