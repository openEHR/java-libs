/*
 * component:   "openEHR Reference Implementation"
 * description: "Class DvAmout"
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

import org.openehr.rm.Attribute;
import org.openehr.rm.datatypes.text.CodePhrase;

/**
 * Abstract class defining the concept of relative quantified 'amounts'. 
 * For relative quantities, the '+' and '-' operators are defined 
 * (unlike descendants of DV_ABSOLUTE_QUANTITY, such as the date/time types).
 *  
 * @author Rong Chen
 */
public abstract class DvAmount<T extends DvAmount> extends DvQuantified<T> {

	/**
     * Constructs a DvAmount with referenceRanges and accuracy
     *
     * @param referenceRanges   null if not specified
     * @param normalRange null if not specified
     * @param normalStatus null if not specified
     * @param accuracy          0 means not recorded      
     * @param accuracyPercent
     * @param magnitudeStatus null if not specified
     */
    protected DvAmount(
    		@Attribute (name = "referenceRanges") List<ReferenceRange<T>> referenceRanges,
    		@Attribute (name = "normalRange") DvInterval<T> normalRange,
    		@Attribute (name= "normalStatus") CodePhrase normalStatus,
    		@Attribute (name = "accuracy") double accuracy, 
    		@Attribute (name = "accuracyPercent") boolean accuracyPercent,
    		@Attribute (name= "magnitudeStatus") String magnitudeStatus) {
    	
        super(referenceRanges, normalRange, normalStatus , magnitudeStatus);
        
        this.accuracy = accuracy;
        this.accuracyPercent = accuracyPercent;
    }
    
    /**
     * Constructs a Quantified without referenceRanges
     *
     * @param accuracy          0 if not recorded
     * @param accuracyIsPercent true if accuracy is percent
     */
    protected DvAmount(double accuracy, boolean accuracyIsPercent) {
        this(null, null, null, accuracy, accuracyIsPercent, null);
    }
    
    /**
     * Default constructor
     */
    protected DvAmount() {
    	this(0.0, false);
    }
    
    /**
     * Accuracy of measurement instrument or method which applies
     * to this specific instance of Quantified, expressed either
     * as a half-range percent value (accuracy_is_percent = True)
     * or a half-range quantity.
     * <p/>
     * A value of 0 means that accuracy was not recorded.
     *
     * @return accuracy
     */
    public double getAccuracy() {
        return accuracy;
    }

    /**
     * If True, indicates that when this object was created,
     * accuracy was recorded as a percent value; if False, as an
     * absolute quantity value.
     *
     * @return true if accuracy percent value
     */
    public boolean isAccuracyPercent() {
        return accuracyPercent;
    }
    
    /**
     * Sum of this quantity and another whose formal type must be the
     * difference type of this quantity.
     *
     * @param q
     * @return product of addition
     */
    public abstract DvQuantified<T> add(DvQuantified<T> q);

    /**
     * Difference of this quantity and another whose formal type must
     * be the difference type of this quantity type.
     *
     * @param q
     * @return product of substration
     */
    public abstract DvQuantified<T> subtract(DvQuantified<T> q);

    /**
     * Type of quantity which can be added or subtracted to this
     * quantity. Usually the same type, but may be different as in
     * the case of dates and times.
     *
     * @return diff type
     */
    public abstract Class getDiffType();    
    
	/* fields */
	private final double accuracy;
    private final boolean accuracyPercent;
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
 *  The Original Code is DvAmount.java
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