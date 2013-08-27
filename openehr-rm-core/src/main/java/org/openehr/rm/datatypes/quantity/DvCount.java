/*
 * component:   "openEHR Reference Implementation"
 * description: "Class DvCount"
 * keywords:    "datatypes"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/datatypes/quantity/DvCount.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 22:20:08 +0100 (Wed, 12 Oct 2005) $"
 */
package org.openehr.rm.datatypes.quantity;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.openehr.rm.Attribute;
import org.openehr.rm.FullConstructor;
import org.openehr.rm.datatypes.basic.ReferenceModelName;
import org.openehr.rm.datatypes.text.CodePhrase;

import java.util.List;

/**
 * This class defines countable quantities.
 *
 * @author Rong Chen
 * @version 1.0
 */
public final class DvCount extends DvAmount<DvCount> {

	/**
     * 
     */
    private static final long serialVersionUID = 1800575426332796870L;

	/**
	 * Constructs a Countable by all components
	 *
	 * @param otherReferenceRanges
	 * @param normalRange 
	 * @param normalStatus
	 * @param accuracy
	 * @param accuracyPercent
	 * @param magnitudeStatus
	 * @param magnitude

	 */
	@FullConstructor
	public DvCount(
			@Attribute(name = "otherReferenceRanges") List<ReferenceRange<DvCount>> otherReferenceRanges,
			@Attribute(name = "normalRange") DvInterval<DvCount> normalRange, 
			@Attribute (name= "normalStatus") CodePhrase normalStatus,
			@Attribute(name = "accuracy") double accuracy, 
			@Attribute(name = "accuracyPercent") boolean accuracyPercent,
			@Attribute (name= "magnitudeStatus") String magnitudeStatus,
			@Attribute(name = "magnitude", required = true)	int magnitude) {
		super(otherReferenceRanges, normalRange, normalStatus, accuracy, 
				accuracyPercent, magnitudeStatus);
		this.magnitude = magnitude;
	}

	/**
	 * Constructs a Countable by magnitude
	 *
	 * @param magnitude
	 */
	public DvCount(int magnitude) {
		this.magnitude = magnitude;
	}

	/**
	 * Numeric value of the quantity in canonical (single value) form
	 *
	 * @return getMagnitude
	 */
	public Integer getMagnitude() {
		return new Integer(this.magnitude);
	}

	/**
	 * Sum of this quantity and another whose formal type must be the
	 * difference type of this quantity.
	 *
	 * @param q
	 * @return product of addition
	 * @throws ClassCastException if q not type of Countable
	 */
	public DvQuantified<DvCount> add(DvQuantified q) {
		final DvCount c = (DvCount) q;
		return new DvCount(getOtherReferenceRanges(), getNormalRange(),
				getNormalStatus(), getAccuracy(), isAccuracyPercent(), 
				getMagnitudeStatus(), magnitude + c.magnitude);
	}

	/**
	 * Difference of this quantity and another whose formal type must
	 * be the difference type of this quantity type.
	 *
	 * @param q
	 * @return product of subtraction
	 */
	public DvQuantified<DvCount> subtract(DvQuantified q) {
		final DvCount c = (DvCount) q;
		return new DvCount(getOtherReferenceRanges(), getNormalRange(),
				getNormalStatus(), getAccuracy(), isAccuracyPercent(), 
				getMagnitudeStatus(), magnitude - c.magnitude);
	}

	/**
	 * Type of quantity which can be added or subtracted to this
	 * quantity. Usually the same type, but may be different as in
	 * the case of dates and times.
	 *
	 * @return diff type
	 */
	public Class<DvCount> getDiffType() {
		return DvCount.class;
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
		final DvCount c = (DvCount) o;
		if (magnitude < c.magnitude)
			return -1;
		if (magnitude > c.magnitude)
			return 1;
		return 0;
	}

	/**
	 * Tests if two instances are strictly comparable.
	 *
	 * @param ordered
	 * @return true if two instances are strictly comparable
	 */
	public boolean isStrictlyComparableTo(DvOrdered ordered) {
		return ordered instanceof DvCount;
	}

	/**
	 * Two Counts equal if has same magnitude
	 *
	 * @param o
	 * @return true if equals
	 */
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof DvCount))
			return false;

		final DvCount dvCount = (DvCount) o;

		if (magnitude != dvCount.magnitude)
			return false;

		return true;
	}

	/**
	 * Return hash code of this Count
	 *
	 * @return hash code
	 */
	public int hashCode() {
		return new HashCodeBuilder(13, 37).append(magnitude).toHashCode();
	}

	public String toString() {
		return Integer.toString(magnitude);
	}
	
	public DvCount parse(String value) throws IllegalArgumentException {
		try {
			int i = Integer.parseInt(value);
			return new DvCount(i);
		} catch(NumberFormatException nfe) {
			throw new IllegalArgumentException("Wrong format", nfe);
		}
	}

	// POJO start
	DvCount() {
	}

	public void setMagnitude(int magnitude) {
		this.magnitude = magnitude;
	}

	// POJO end

	/* fields */
	private int magnitude;

	@Override
	public String getReferenceModelName() {
		return ReferenceModelName.DV_COUNT.getName();
	}

	@Override
	public String serialise() {
		return getReferenceModelName() + "," + toString();
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
 *  The Original Code is DvCount.java
 *
 *  The Initial Developer of the Original Code is Rong Chen.
 *  Portions created by the Initial Developer are Copyright (C) 2003-2007
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