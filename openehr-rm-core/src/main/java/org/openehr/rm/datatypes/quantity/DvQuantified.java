/*
 * component:   "openEHR Reference Implementation"
 * description: "Class DvQuantified"
 * keywords:    "datatypes"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/datatypes/quantity/DvQuantified.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 22:20:08 +0100 (Wed, 12 Oct 2005) $"
 */
package org.openehr.rm.datatypes.quantity;

import java.util.List;

import org.openehr.rm.Attribute;
import org.openehr.rm.datatypes.text.CodePhrase;

/**
 * Abstract class defining the concept of true quantified values,
 * like values which are not only ordered, but which have
 * a getMagnitude, and for which the addition and difference
 * operations can be defined.
 *
 * @author Rong Chen
 * @version 1.0
 */
public abstract class DvQuantified<T extends DvQuantified> extends DvOrdered<T> {

    /**
     * Constructs a Quantified with referenceRanges and accuracy
     *
     * @param otherReferenceRanges   null if not specified
     * @param normalRange null if no specified
     * @param normalStatus null if not specified
     * @param magnitudeStatus null if not specified
     */
    protected DvQuantified(
    		@Attribute (name = "otherReferenceRanges") List<ReferenceRange<T>> otherReferenceRanges,
    		@Attribute (name = "normalRange") DvInterval<T> normalRange,
    		@Attribute (name= "normalStatus") CodePhrase normalStatus,
    		@Attribute (name= "magnitudeStatus") String magnitudeStatus) {
        super(otherReferenceRanges, normalRange, normalStatus);
        this.magnitudeStatus = magnitudeStatus;
    }  

    /**
     * Test whether a number is a valid percentage
     *
     * @param num
     * @return true if given number is valid percentage
     */
    public boolean is_valid_percentage(Number num) {
        // todo: fix it
        return false;
    }

    /**
     * Numeric value of the quantity in canonical (single value) form
     *
     * @return getMagnitude
     */
    public abstract Number getMagnitude();

    /**
     * Optional status of magnitude with values:
     * <ul>
     * <li> "=" : magnitude is a point value</li>
     * <li> "<" : value is < magnitude</li>
     * <li> ">" : value is > magnitude</li>
     * <li> "<=" : value is <= magnitude</li>
     * <li> ">=" : value is >= magnitude</li>
     * <li> "~" : value is approximately magnitude</li>
     * <li> If not present, meaning is "="</li>
	 * </ul> 
     * @return null if unspecified
     */
    public String getMagnitudeStatus() {
    	return magnitudeStatus;
    }

    /**
     * Equals if getMagnitude equals
     *
     * @param o
     * @return true if equals
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!( o instanceof DvQuantified )) return false;

        final DvQuantified dvQuantified = (DvQuantified) o;

        return getMagnitude().equals(dvQuantified.getMagnitude());
    }

    /**
     * Return hash code of this Quantified
     *
     * @return hash code
     */
    public int hashCode() {
        return getMagnitude().hashCode();
    }

    /* fields */
    private final String magnitudeStatus;
    
    
    
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
 *  The Original Code is DvQuantified.java
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