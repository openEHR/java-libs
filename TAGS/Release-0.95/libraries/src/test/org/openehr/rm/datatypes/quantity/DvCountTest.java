/*
 * component:   "openEHR Reference Implementation"
 * description: "Class DvCountTest"
 * keywords:    "unit test"
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
 * CountTest
 *
 * @author Rong Chen
 * @version 1.0 
 */
package org.openehr.rm.datatypes.quantity;

import junit.framework.TestCase;

public class DvCountTest extends TestCase {

    public DvCountTest(String test) {
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

    public void testAdd() throws Exception {
        DvCount c1 = new DvCount(3);
        DvCount c2 = new DvCount(5);
        DvCount expected = new DvCount(8);
        assertEquals(expected, c1.add(c2));
        assertEquals(expected, c2.add(c1));
    }

    public void testSubtract() throws Exception {
        DvCount c1 = new DvCount(3);
        DvCount c2 = new DvCount(5);
        DvCount expected = new DvCount(2);
        assertEquals(expected, c2.subtract(c1));
    }

    public void testCompareTo() throws Exception {
        DvCount c1 = new DvCount(3);
        DvCount c2 = new DvCount(5);
        DvCount c3 = new DvCount(3);

        assertTrue("c1 < c2", c1.compareTo(c2) < 0);
        assertTrue("c2 > c1", c2.compareTo(c1) > 0);
        assertTrue("c3 == c1", c3.compareTo(c1) == 0);
        assertTrue("c1 == c3", c1.compareTo(c3) == 0);
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
 *  The Original Code is DvCountTest.java
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