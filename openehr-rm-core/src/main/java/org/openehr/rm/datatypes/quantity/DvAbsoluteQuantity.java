/*
 * component:   "openEHR Reference Implementation"
 * description: "Class DvAbsoluteQuantity"
 * keywords:    "datatypes"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * copyright:   "Copyright (c) 2007 Cambio Healthcare Systems, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL$"
 * revision:    "$LastChangedRevision$"
 * last_change: "$LastChangedDate$"
 */
package org.openehr.rm.datatypes.quantity;

import java.util.List;

import org.openehr.rm.datatypes.text.CodePhrase;

/**
 * Abstract class defining the concept of quantified entities whose values 
 * are absolute with respect to an origin. Dates and Times are the main example.
 * 
 * @author Rong Chen
 */
public abstract class DvAbsoluteQuantity<T extends DvAbsoluteQuantity, 
			S extends DvAmount> extends DvQuantified<T> {

	/**
	 * Creates a DvAbsoluteQuantity
	 * 
	 * @param otherReferenceRanges
	 * @param normalRange
	 * @param normalStatus
	 * @param magnitudeStatus
	 * @param accuracy
	 */
	protected DvAbsoluteQuantity(List<ReferenceRange<T>> otherReferenceRanges, 
			DvInterval<T> normalRange, CodePhrase normalStatus, 
			S accuracy, String magnitudeStatus) {
		
		super(otherReferenceRanges, normalRange, normalStatus, magnitudeStatus);

		this.accuracy = accuracy;
	}

	/**
     * Sum of this quantity and another whose formal type must be the
     * difference type of this quantity.
     *
     * @param s
     * @return product of addition
     */
    public abstract T add(S s);

    /**
     * Difference of this quantity and another whose formal type must
     * be the difference type of this quantity type.
     *
     * @param s
     * @return product of substration
     */
    public abstract T subtract(S s);

    /**
     * Difference of two quantities.
     *
     * @return diff type
     */
    public abstract DvAmount diff(T other);   
    
    /**
     * Accuracy of measurement, expressed as a half-range value 
     * of the diff type for this quantity (i.e. an accuracy of
     * x means +/−x).  
     *
     * @return accuracy
     */
    public S getAccuracy() {
    	return accuracy;
    }
    
    
    /* fields */
    private final S accuracy;    
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
 *  The Original Code is DvAbsoluteQuantity.java
 *
 *  The Initial Developer of the Original Code is Rong Chen.
 *  Portions created by the Initial Developer are Copyright (C) 2007
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