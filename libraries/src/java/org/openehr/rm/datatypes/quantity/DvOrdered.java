/*
 * component:   "openEHR Reference Implementation"
 * description: "Class DvOrdered"
 * keywords:    "datatypes"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/datatypes/quantity/DvOrdered.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 22:20:08 +0100 (Wed, 12 Oct 2005) $"
 */
package org.openehr.rm.datatypes.quantity;

import org.openehr.rm.Attribute;
import org.openehr.rm.datatypes.basic.DataValue;

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
     * Constructs a Ordered by referenceRanges and normalRange
     *
     * @param referenceRanges null if not specified
     * @param normalRange null if not specified
     * @throws IllegalArgumentException if referenceRanges not null
     *                                  and empty
     */
    protected DvOrdered (
    		@Attribute (name = "otherReferenceRanges")
            List<ReferenceRange<T>> otherReferenceRanges,
        @Attribute (name = "normalRange") DvInterval<T> normalRange) {

        if (otherReferenceRanges != null) {
            if (otherReferenceRanges.isEmpty()) {
                throw new IllegalArgumentException("empty referenceRanges");
            }
            this.otherReferenceRanges =
                    new ArrayList<ReferenceRange<T>>(otherReferenceRanges);
 /*           for (int i = 0, j = otherReferenceRanges.size(); i < j; i++) {
                ReferenceRange range = (ReferenceRange)
                        otherReferenceRanges.get(i);
                if (ReferenceRange.NORMAL.equals(range.getMeaning().getValue())) {
                    this.normalRangeIndex = i;
                    return;
                }
            }*/
        } else {
            this.otherReferenceRanges = null;
        }
        this.normalRange = normalRange;
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
     * Optimal normal range
     *
     * @return null if not specified
     */
    public DvInterval<T> getNormalRange() {
        return normalRange;
    }

    /**
     * Value is in the normal range if there is one, otherwise True
     *
     * @return true if normal
     */
    public boolean isNormal() {
        return (normalRange == null) || getNormalRange().has(this);
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

    /* fields */
    private final List<ReferenceRange<T>> otherReferenceRanges;
    private final DvInterval<T> normalRange;
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