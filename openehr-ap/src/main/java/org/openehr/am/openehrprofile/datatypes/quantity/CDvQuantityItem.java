/*
 * component:   "openEHR Reference Implementation"
 * description: "Class CDvQuantityItem"
 * keywords:    "openehr archetype profile"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2006 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL$"
 * revision:    "$LastChangedRevision$"
 * last_change: "$LastChangedDate$"
 */

package org.openehr.am.openehrprofile.datatypes.quantity;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.openehr.rm.support.basic.Interval;

/**
 * Constrain instances of DV_QUANTITY.
 * 
 * @author Rong Chen
 */
public class CDvQuantityItem {	
	
	/**
	 * Constructor 
	 * 
	 * @param magnitude 
	 * @param units not null or empty
	 * @throws IllegalArgumentException if units null or empty
	 */
	public CDvQuantityItem(Interval<Double> magnitude, String units) {
		this(magnitude, null, units);
	}
	
	/**
	 * Constructor using only units
	 * 
	 * @param units not null or empty
	 * @throws IllegalArgumentException if units null or empty
	 */
	public CDvQuantityItem(String units) {
		this(null, null, units);
	}
	
	/**
	 * Constructor
	 * 
	 * @param magnitude 
	 * @param precision null if unspecified
	 * @param units not null or empty
	 * @throws IllegalArgumentException if units null or empty
	 */
	public CDvQuantityItem(Interval<Double> magnitude, Interval<Integer> precision,
			String units) {
		if(StringUtils.isEmpty(units)) {
			throw new IllegalArgumentException("units null or empty");
		}
		this.magnitude = magnitude;
		this.precision = precision;
		this.units = units;
	}
	
	/**
	 * Constraint on units
	 * 
	 * @return units
	 */
	public String getUnits() {
		return units;
	}
	
	/**
	 * Magnitude must be inside the supplied interval.
	 * 
	 * @return magnitude interval
	 */
	public Interval<Double> getMagnitude() {
		return magnitude;
	}
	
	/**
	 * Gets precision of this item
	 * 
	 * @return null if unspecified
	 */
	public Interval<Integer> getPrecision() {
		return precision;
	}
	
	/**
     * Returns true if fields are the same
     */
    public boolean equals(Object o) {
    	if (this == o) return true;
        if (!( o instanceof CDvQuantityItem )) return false;

        final CDvQuantityItem item = (CDvQuantityItem) o;

        return new EqualsBuilder()
                .append(magnitude, item.magnitude)
                .append(precision, item.precision)
                .append(units, item.units)
                .isEquals();
    }
    
    /**
     * Returns hashcode
     */
    public int hashCode() {
        return new HashCodeBuilder(7, 23)
                .append(magnitude)
                .append(precision)
                .append(units)
                .toHashCode();
    }
    
    public String toString() {
    	return new ToStringBuilder(this).
        append("magnitude", magnitude).
        append("precision", precision).
        append("units", units).
        toString();

    }
	
	/* fields */
	private Interval<Double> magnitude;
	private Interval<Integer> precision;
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
 *  The Original Code is CDvQuantity.java
 *
 *  The Initial Developer of the Original Code is Rong Chen.
 *  Portions created by the Initial Developer are Copyright (C) 2003-2008
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