/*
 * component:   "openEHR Reference Implementation"
 * description: "Class DvPartialDateTest"
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
 * DvPartialDateTest
 *
 * @author Rong Chen
 * @version 1.0 
 */
package org.openehr.rm.datatypes.quantity.datetime;

import junit.framework.TestCase;
import org.openehr.rm.datatypes.quantity.DvInterval;

public class DvPartialDateTest extends TestCase {

    public DvPartialDateTest(String test) {
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

    public void testToString() throws Exception {

        // month known
        DvPartialDate date = new DvPartialDate(1999, 11, null, true);
        assertEquals("Got: " + date.toString(), "1999-12-??", date.toString());
        date = new DvPartialDate(2004, 0, null, true);
        assertEquals("Got: " + date.toString(), "2004-01-??", date.toString());

        // month unknown
        date = new DvPartialDate(1999, 0, null, false);
        assertEquals("Got: " + date.toString(), "1999-??-??", date.toString());
    }

    public void testEnclosingInterval() throws Exception {

        // month known
        DvPartialDate partialDate = new DvPartialDate(2004, 0, null, true);
        DvDate start = new DvDate("2004-01-1");
        DvDate end = new DvDate("2004-01-31");
        DvInterval<DvDate> expected = new DvInterval<DvDate>(start, end);
        assertEquals("month known 31 days", expected,
                partialDate.enclosingInterval());

        partialDate = new DvPartialDate(2004, 10, null, true);
        start = new DvDate("2004-11-1");
        end = new DvDate("2004-11-30");
        expected = new DvInterval<DvDate>(start, end);
        assertEquals("month known 30 days", expected,
                partialDate.enclosingInterval());

        // month unknown
        partialDate = new DvPartialDate(2004, 0, null, false);
        start = new DvDate("2004-1-1");
        end = new DvDate("2004-12-31");
        expected = new DvInterval<DvDate>(start, end);
        assertEquals("month unknown", expected,
                partialDate.enclosingInterval());
    }

    public void testFullConstructor() throws Exception {
        DvPartialDate date = new DvPartialDate(null, 0.0, true, "1999-10");
        assertEquals("year", 1999, date.getYear());
        assertEquals("month", 9, date.getMonth());
        assertEquals("monthKnonw", true, date.isMonthKnown());

        date = new DvPartialDate(null, 0.0, true, "1999");
        assertEquals("year", 1999, date.getYear());
        assertEquals("monthKnonw", false, date.isMonthKnown());
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
 *  The Original Code is DvPartialDateTest.java
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