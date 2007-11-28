/*
 * component:   "openEHR Reference Implementation"
 * description: "Class DvOrdered"
 * keywords:    "datatypes"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004,2007 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/datatypes/quantity/DvOrdered.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 22:20:08 +0100 (Wed, 12 Oct 2005) $"
 */
package org.openehr.rm.datatypes.quantity;

import org.openehr.rm.Attribute;
import org.openehr.rm.datatypes.basic.DataValue;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.support.terminology.OpenEHRCodeSetIdentifiers;
import org.openehr.rm.support.terminology.TerminologyService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Abstract class defining the concept of ordered values, which
 * includes ordinals as well as true quantities.
 *
 * @author Rong Chen
 * @version 1.0
 */
public abstract class DvOrdered<T extends DvOrdered> extends DataValue
        implements Comparable<DvOrdered> {

    /**
     * Constructs an Ordered without reference ranges
     */
    protected DvOrdered() {
        this(null, null);
    }

    /**
     * Constructs a Ordered by otherReferenceRanges, normalRange and 
     * normalStatus
     *
     * @param otherReferenceRanges null if not specified
     * @param normalRange null if not specified
     * @param normalStatus null if not specified
     * @throws IllegalArgumentException if otherReferenceRanges not null
     *                                  and empty
     */
    protected DvOrdered (
    		@Attribute (name = "otherReferenceRanges")
            List<ReferenceRange<T>> otherReferenceRanges,
            @Attribute (name = "normalRange") DvInterval<T> normalRange,
            @Attribute (name= "normalStatus") CodePhrase normalStatus,
            @Attribute(name = "terminologyService", system = true) 
            	TerminologyService terminologyService) {     

        if (otherReferenceRanges != null) {
            if (otherReferenceRanges.isEmpty()) {
                throw new IllegalArgumentException("empty otherReferenceRanges");
            }
            this.otherReferenceRanges =
                    new ArrayList<ReferenceRange<T>>(otherReferenceRanges); 
        } else {
            this.otherReferenceRanges = null;
        }
        
        if(normalStatus != null) {
        	if(terminologyService == null) {
        		throw new IllegalArgumentException("null terminologyService");
        	}
        	if (!terminologyService.codeSetForId(
        			OpenEHRCodeSetIdentifiers.NORMAL_STATUSES).hasCode(
        					normalStatus)) {
                throw new IllegalArgumentException(
                        "unknown normal status: " + normalStatus);
            }            
        }
        if(normalStatus != null && normalRange != null) {
        	
        }
        
        this.normalRange = normalRange;
        this.normalStatus = normalStatus;
    }
    
    /**
     * Constructs a Ordered by otherReferenceRanges, normalRange and 
     * normalStatus
     *
     * @param otherReferenceRanges null if not specified
     * @param normalRange null if not specified
     * @param normalStatus null if not specified
     * @throws IllegalArgumentException if otherReferenceRanges not null
     *                                  and empty
     */
    protected DvOrdered (
    		List<ReferenceRange<T>> otherReferenceRanges,
            DvInterval<T> normalRange, CodePhrase normalStatus) {

        if (otherReferenceRanges != null) {
            if (otherReferenceRanges.isEmpty()) {
                throw new IllegalArgumentException("empty otherReferenceRanges");
            }
            this.otherReferenceRanges =
                    new ArrayList<ReferenceRange<T>>(otherReferenceRanges); 
        } else {
            this.otherReferenceRanges = null;
        }
        this.normalRange = normalRange;
        this.normalStatus = normalStatus;
    }
    
    /**
     * Constructs a Ordered by otherReferenceRanges and normalRange
     *
     * @param otherReferenceRanges null if not specified
     * @param normalRange null if not specified
     * @throws IllegalArgumentException if otherReferenceRanges not null
     *                                  and empty
     */
    protected DvOrdered (List<ReferenceRange<T>> otherReferenceRanges,
            DvInterval<T> normalRange) {

        this(otherReferenceRanges, normalRange, null);
    }

    /**
     * Optional ranges for this value in its particular measurement
     * context
     *
     * @return unmodifiable list of ReferenceRange
     *         null if not specified
     */
    public List<ReferenceRange<T>> getOtherReferenceRanges() {
        return otherReferenceRanges == null ?
                null : Collections.unmodifiableList(otherReferenceRanges);
    }

    /**
     * Optional normal range
     *
     * @return null if not specified
     */
    public DvInterval<T> getNormalRange() {
        return normalRange;
    }

    /**
     * Optional normal status
     * 
     * @return null if not specified
     */
    public CodePhrase getNormalStatus() {
    	return normalStatus;
    }
    
    /**
     * Value is in the normal range if there is one, otherwise True
     *
     * @return true if normal
     * @throws IllegalStateException if both normalRange and normalStatus null
     */
    public boolean isNormal() throws IllegalStateException {
    	if(normalRange == null && normalStatus == null) {
    		throw new IllegalStateException(
    				"both normalRange and normalStatus null");
    	}
    	if(normalRange != null) {
    		return getNormalRange().has(this);
    	} else {
    		return normalStatus.getCodeString().equals("N");
    	}
    }

    /**
     * True if this quantity has no reference ranges
     *
     * @return true if has no reference range
     */
    public boolean isSimple() {
        return otherReferenceRanges == null;
    }

    /**
     * Tests if two instances are strictly comparable.
     *
     * @param ordered
     * @return true if two instances are strictly comparable
     */
    public abstract boolean isStrictlyComparableTo(DvOrdered ordered);
    
    //  POJO start
    /**
	 * @param normalRange The normalRange to set.
	 */
	public void setNormalRange(DvInterval<T> normalRange) {
		this.normalRange = normalRange;
	}

	/**
	 * @param otherReferenceRanges The otherReferenceRanges to set.
	 */
	public void setOtherReferenceRanges(List<ReferenceRange<T>> referenceRanges) {
		this.otherReferenceRanges = referenceRanges;
	}
	
	/**
	 * @param normalStatus The normalStatus to set.
	 */
	public void setNormalStatus(CodePhrase normalStatus) {
		this.normalStatus = normalStatus;
	}
    // POJO end

    /* fields */
    private List<ReferenceRange<T>> otherReferenceRanges;
    private DvInterval<T> normalRange;	
    private CodePhrase normalStatus;
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
 *  The Original Code is DvOrdered.java
 *
 *  The Initial Developer of the Original Code is Rong Chen.
 *  Portions created by the Initial Developer are Copyright (C) 2003-2007
 *  the Initial Developer. All Rights Reserved.
 *
 *  Contributor(s): Bert Verhees, Sergio Miranda Freire
 *
 * Software distributed under the License is distributed on an 'AS IS' basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 *  ***** END LICENSE BLOCK *****
 */