/*
 * component:   "openEHR Reference Implementation"
 * description: "Class DvCustomaryQuantity"
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

import java.util.List;

/**
 * Abstract parent class of quantity types which are expressed in a
 * form other than the standard scientific, one value, one unit form
 *
 * @author Rong Chen
 * @version 1.0
 */
public abstract class DvCustomaryQuantity<T extends DvCustomaryQuantity>
        extends DvMeasurable<T> {

    /**
     * Constructs a CustomaryQuantity by referenceRanges and accuracy
     *
     * @param referenceRanges
     * @param accuracy
     * @param accuracyPercent
     */
    public DvCustomaryQuantity(
            List<ReferenceRange<T>> referenceRanges,
            double accuracy, boolean accuracyPercent) {
        super(referenceRanges, accuracy, accuracyPercent, UNITS);
    }

    /**
     * Constructs a CustomaryQuantity without referenceRanges
     * or accuracy
     */
    public DvCustomaryQuantity() {
        super(UNITS);
    }

    /**
     * Converts a customary quantity to a scientific one for
     * comparison or other purposes.
     *
     * @return quantity
     */
    public abstract DvQuantity toQuantity();

    private static final String UNITS = "s";
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
 *  The Original Code is DvCustomaryQuantity.java
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