/*
 * component:   "openEHR Reference Implementation"
 * description: "Class DvQuantityRatioTest"
 * keywords:    "unit test"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/BRANCHES/RM-1.0-update/libraries/src/test/org/openehr/rm/datatypes/quantity/DvQuantityRatioTest.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 23:20:08 +0200 (Wed, 12 Oct 2005) $"
 */
/**
 * QuantityRatioTest
 *
 * @author Rong Chen
 * @version 1.0 
 */
package org.openehr.rm.datatypes.quantity;

import junit.framework.TestCase;
import org.openehr.rm.support.measurement.MeasurementService;
import org.openehr.rm.support.measurement.TestMeasurementService;

public class DvQuantityRatioTest extends TestCase {

    public DvQuantityRatioTest(String test) {
        super(test);
    }

    /**
     * The fixture set up called before every test method.
     */
    protected void setUp() throws Exception {
    }

    /**
     * The fixture clean up called after every test method.
     */
    protected void tearDown() throws Exception {
    }

    public void testEquals() throws Exception {

        MeasurementService ms = new TestMeasurementService();

        DvQuantityRatio ratio1 = new DvQuantityRatio(
                new DvQuantity("g", 2, ms), new DvQuantity("ml", 250, ms));
        DvQuantityRatio ratio2 = new DvQuantityRatio(
                new DvQuantity("g", 2, ms), new DvQuantity("ml", 250, ms));
        assertTrue(ratio1 + " equals " + ratio2,
                ratio1.equals(ratio2));

        // diff numerator
        ratio2 =
                new DvQuantityRatio(new DvQuantity("g", 3, ms),
                        new DvQuantity("ml", 250, ms));
        assertFalse(ratio1 + " not equals " + ratio2,
                ratio1.equals(ratio2));
        assertFalse(ratio2 + " not equals " + ratio1,
                ratio2.equals(ratio1));

        ratio2 =
                new DvQuantityRatio(new DvQuantity("kg", 2, ms),
                        new DvQuantity("ml", 250, ms));
        assertFalse(ratio1 + " not equals " + ratio2,
                ratio1.equals(ratio2));
        assertFalse(ratio2 + " not equals " + ratio1,
                ratio2.equals(ratio1));

        // diff denominator
        ratio2 =
                new DvQuantityRatio(new DvQuantity("g", 2, ms),
                        new DvQuantity("l", 250, ms));
        assertFalse(ratio1 + " not equals " + ratio2,
                ratio1.equals(ratio2));
        assertFalse(ratio2 + " not equals " + ratio1,
                ratio2.equals(ratio1));

        ratio2 =
                new DvQuantityRatio(new DvQuantity("g", 2, ms),
                        new DvQuantity("ml", 200, ms));
        assertFalse(ratio1 + " not equals " + ratio2,
                ratio1.equals(ratio2));
        assertFalse(ratio2 + " not equals " + ratio1,
                ratio2.equals(ratio1));


    }

    public void testToString() throws Exception {
        
        MeasurementService ms = new TestMeasurementService();

        DvQuantityRatio ratioDv = new DvQuantityRatio(new DvQuantity("g", 2, ms),
                new DvQuantity("ml", 250, ms));
        String expected = "2 g / 250 ml";
        assertEquals(expected, ratioDv.toString());
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
 *  The Original Code is DvQuantityRatioTest.java
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