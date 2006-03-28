/*
 * component:   "openEHR Reference Implementation"
 * description: "Class CCountTest"
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
 * CCountTest
 *
 * @author Rong Chen
 * @version 1.0 
 */
package org.openehr.am.archetype.constraintmodel.domain;

import org.openehr.am.archetype.ConstraintTestBase;
import org.openehr.am.archetype.constraintmodel.DVObjectCreationException;
import org.openehr.rm.datatypes.quantity.DvCount;
import org.openehr.rm.support.basic.Interval;

public class CCountTest extends ConstraintTestBase {

    public CCountTest(String test) {
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
        Interval<Integer> magnitude = new Interval<Integer>(new Integer(0),
                new Integer(10));
        CCount ccount = new CCount("path", magnitude);

        // value of out range
        try {
            ccount.createObject("20", sysmap(), null);
            fail("exception should be thrown");
        } catch (Exception e) {
            assertTrue(e instanceof DVObjectCreationException);
        }

        // right value
        DvCount c = ccount.createObject("6", sysmap(), null);
        assertEquals("magnitude", new Integer(6), c.getMagnitude());
    }

    public void testAssignedValue() throws Exception {
        Interval<Integer> magnitude = new Interval<Integer>(new Integer(0),
                new Integer(10));
        CCount ccount = new CCount("path", magnitude);
        assertFalse(ccount.hasAssignedValue());
        assertTrue(ccount.assignedValue(sysmap(), null) == null);
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
 *  The Original Code is CCountTest.java
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