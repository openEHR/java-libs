/*
 * component:   "openEHR Reference Implementation"
 * description: "Class DvQuantity"
 * keywords:    "datatypes"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/datatypes/quantity/DvQuantity.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 22:20:08 +0100 (Wed, 12 Oct 2005) $"
 */
package org.openehr.rm.datatypes.quantity;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.openehr.rm.Attribute;
import org.openehr.rm.FullConstructor;
import org.openehr.rm.support.measurement.MeasurementService;

import java.text.NumberFormat;
import java.util.List;

/**
 * Quantitified type representing  scientific  quantities,
 * expressed as a single value and optional units. Instances of this class
 * are immutable.
 *
 * @author Rong Chen
 * @version 1.0
 */
public class DvQuantity extends DvMeasurable<DvQuantity> {

    /**
     * Constructs a Quantity by all components
     *
     * @param referenceRanges
     * @param normalRange
     * @param accuracy
     * @param accuracyPercent
     * @param units
     * @param magnitude
     * @param precision          >= 0
     * @param measurementService null if not specified only if units
     *                           not specified as well
     * @throws IllegalArgumentException
     */
    @FullConstructor
            public DvQuantity(@Attribute (name = "referenceRanges") List<ReferenceRange<DvQuantity>> referenceRanges,
                              @Attribute (name = "normalRange") DvInterval<DvQuantity> normalRange,
            					@Attribute (name = "accuracy") double accuracy,
                              @Attribute (name = "accuracyPercent") boolean accuracyPercent,
                              @Attribute (name = "units", required = true) String units,
                              @Attribute (name = "magnitude", required = true) double magnitude,
                              @Attribute (name = "precision") int precision,
                              @Attribute (name = "measurementService", system = true) MeasurementService measurementService) {
        
        super(referenceRanges, normalRange, accuracy, accuracyPercent, units);

        if (precision < 0) {
            throw new IllegalArgumentException("negative precision");
        }
        if (StringUtils.isNotEmpty(units)
                && measurementService == null) {
            throw new IllegalArgumentException("null measurementService");
        }
        this.magnitude = magnitude;
        this.precision = precision;
        this.measurementService = measurementService;
    }

    /**
     * Constructs a Quantity by units, getMagnitude and precision
     *
     * @param units
     * @param magnitude
     * @param precision >= 0
     * @throws IllegalArgumentException
     */
    public DvQuantity(String units, double magnitude, int precision,
                      MeasurementService measurementService) {
        this(null, null, 0, false, units, magnitude, precision,
                measurementService);
    }

    /**
     * Constructs a integer Quantity by double value
     *
     * @param magnitude
     * @throws IllegalArgumentException
     */
    public DvQuantity(double magnitude) {
        this("", magnitude, 0, null);
    }

    /**
     * Constructs a Measurable only by units
     *
     * @param units not null
     * @throws IllegalArgumentException if units is null
     */
    public DvQuantity(String units, double magnitude,
                      MeasurementService measurementService) {
        this(units, magnitude, 0, measurementService);
    }

    /**
     * Constructs a Measurable only by units
     *
     * @param magnitude
     * @param precision
     * @throws IllegalArgumentException if units is null
     */
    public DvQuantity(double magnitude, int precision,
                      MeasurementService measurementService) {
        this("", magnitude, precision, measurementService);
    }

    /**
     * True if precision = 0; quantity represents an integral number
     *
     * @return ture if integral
     */
    public boolean isIntegral() {
        return precision == 0;
    }

    /**
     * Magnitude of this Quantity instance
     *
     * @return getMagnitude
     */
    public Double getMagnitude() {
        return new Double(magnitude);
    }

    /**
     * Sum of this quantity and another whose formal type must be the
     * difference type of this quantity.
     *
     * @param q
     * @return result of addition
     * @throws ClassCastException if the specified object's type
     *                            prevents it from being added to this Object.
     */
    public DvQuantified<DvQuantity> add(DvQuantified<DvQuantity> q) {
        DvQuantity qt = (DvQuantity) q;
        return new DvQuantity(getReferenceRanges(), getNormalRange(),
        		getAccuracy(), isAccuracyPercent(), getUnits(),
                magnitude + qt.magnitude, precision,
                measurementService);
    }

    /**
     * Difference of this quantity and another whose formal type must
     * be the difference type of this quantity type.
     *
     * @param q
     * @return result of subtraction
     * @throws ClassCastException if the specified object's type
     *                            prevents it from being subtracted to this Object.
     */
    public DvQuantified<DvQuantity> subtract(DvQuantified<DvQuantity> q) {
        DvQuantity qt = (DvQuantity) q;
        return new DvQuantity(getReferenceRanges(), getNormalRange(),
        		   getAccuracy(), isAccuracyPercent(), getUnits(),
                magnitude - qt.magnitude, precision,
                measurementService);
    }

    /**
     * Type of quantity which can be added or subtracted to this
     * quantity. Usually the same type, but may be different as in
     * the case of dates and times.
     *
     * @return diff type
     */
    public Class getDiffType() {
        return DvQuantity.class;
    }

    /**
     * Negated version of current object, such as used for
     * representing a difference, like a weight loss.
     *
     * @return negated version
     */
    public DvQuantity negate() {
        return new DvQuantity(getReferenceRanges(), getNormalRange(), 
        		   getAccuracy(), isAccuracyPercent(), getUnits(), -magnitude,
                precision, measurementService);
    }

    /**
     * Precision of this Quantity instance
     *
     * @return precision
     */
    public int getPrecision() {
        return precision;
    }

    /**
     * string form displayable for humans
     *
     * @return string presentation
     */
    public String toString() {
        NumberFormat format = NumberFormat.getInstance();
        format.setMinimumFractionDigits(precision);
        format.setMaximumFractionDigits(precision);
        return format.format(magnitude)
                + ( StringUtils.isEmpty(getUnits()) ? "" : " " +
                getUnits() );
    }

    /**
     * Compares this object with the specified object for order.
     *
     * @return a negative integer, zero, or a positive integer as
     *         this object is less than, equal to, or greater than
     *         the specified object
     * @throws ClassCastException if the specified object's type
     *                            prevents it from being compared to this Object.
     */
    public int compareTo(DvOrdered o) {
        DvQuantity q = (DvQuantity) o;
        if (magnitude > q.magnitude) {
            return 1;
        } else if (magnitude < q.magnitude) {
            return -1;
        }
        return 0;
    }

    /**
     * Tests if two instances are strictly comparable.
     *
     * @param other
     * @return true if two instances are strictly comparable
     * @throws IllegalArgumentException if other null or wrong type
     */
    public boolean isStrictlyComparableTo(DvOrdered other) {
        if (!( other instanceof DvQuantity )) {
            throw new IllegalArgumentException("other not Quantity");
        }
        final DvQuantity dvQuantity = (DvQuantity) other;
        return measurementService.unitsEquivalent(getUnits(),
                dvQuantity.getUnits());
    }

    /**
     * Equals if getMagnitude, units and precision equals
     *
     * @param o
     * @return true if equals
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!( o instanceof DvQuantity )) return false;

        final DvQuantity dvQuantity = (DvQuantity) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(precision, dvQuantity.precision)
                .isEquals();
    }

    /**
     * Return hash code of this Quantity
     *
     * @return hash code
     */
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(precision)
                .toHashCode();
    }

    // POJO start
    DvQuantity() {
    }

    void setMagnitude(double magnitude) {
        this.magnitude = magnitude;
    }

    void setPrecision(int precision) {
        this.precision = precision;
    }    
    // POJO end

    /* fields */
    private double magnitude; // add final
    private int precision;    // add final
    private MeasurementService measurementService; // add final
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
 *  The Original Code is DvQuantity.java
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