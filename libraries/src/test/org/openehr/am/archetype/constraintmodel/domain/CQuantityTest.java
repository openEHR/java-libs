/*
 * component:   "openEHR Reference Implementation"
 * description: "Class CQuantityTest"
 * keywords:    "archetype"
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
/**
 * CQuantityTest
 *
 * @author Rong Chen
 * @version 1.0 
 */
package org.openehr.am.archetype.constraintmodel.domain;

import org.openehr.am.archetype.ConstraintTestBase;
import org.openehr.am.archetype.constraintmodel.DVObjectCreationException;
import org.openehr.rm.datatypes.quantity.DvQuantity;
import org.openehr.rm.support.basic.Interval;

public class CQuantityTest extends ConstraintTestBase {

    public CQuantityTest(String test) {
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

    public void testCreateDVObject() throws Exception {
        Interval<Double> magnitude = new Interval<Double>(new Double(0.0),
                new Double(10.0));
        String units = "ml";
        CQuantity cq = new CQuantity("path", magnitude, units);

        // within range
        DvQuantity q = cq.createObject("5.0", sysmap(), null);
        assertEquals("units", "ml", q.getUnits());
        assertEquals("magnitude", new Double(5.0), q.getMagnitude());

        // out of range
        try {
            cq.createObject("20.0", sysmap(), null);
            fail("exception should be thrown");
        } catch (Exception e) {
            assertTrue(e instanceof DVObjectCreationException);
        }
    }

    public void testAssignedValue() throws Exception {
        Interval<Double> magnitude = new Interval<Double>(new Double(0.0),
                new Double(10.0));
        String units = "ml";
        CQuantity cq = new CQuantity("path", magnitude, units);

        assertFalse(cq.hasAssignedValue());
        assertTrue(cq.assignedValue(sysmap(), null) == null);
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
 *  The Original Code is CQuantityTest.java
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