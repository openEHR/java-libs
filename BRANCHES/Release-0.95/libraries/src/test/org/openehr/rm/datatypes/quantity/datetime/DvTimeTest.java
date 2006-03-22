/*
 * component:   "openEHR Reference Implementation"
 * description: "Class DvTimeTest"
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
 * DvTimeTest
 *
 * @author Rong Chen
 * @version 1.0 
 */
package org.openehr.rm.datatypes.quantity.datetime;

import junit.framework.TestCase;

import java.text.SimpleDateFormat;

public class DvTimeTest extends TestCase {

    public DvTimeTest(String test) {
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

    public void testCompareTo() throws Exception {
        assertTrue(dvTime("00:00:00").compareTo(dvTime("01:00:00")) < 0);
        assertTrue(dvTime("00:00:00").compareTo(dvTime("00:01:00")) < 0);
        assertTrue(dvTime("00:00:00").compareTo(dvTime("00:00:01")) < 0);

        assertTrue(dvTime("01:00:00").compareTo(dvTime("00:00:00")) > 0);
        assertTrue(dvTime("00:01:00").compareTo(dvTime("00:00:00")) > 0);
        assertTrue(dvTime("00:00:01").compareTo(dvTime("00:00:00")) > 0);

        assertTrue(dvTime("00:00:01").compareTo(dvTime("00:00:01")) == 0);
    }

    public void testToString() throws Exception {
        String[] values = {
            "19:55:45", "01:15:25", "00:00:00", "23:59:59"
        };
        for (String value : values) {
            assertEquals(dvTime(value).toString(), value);
        }
    }

    private DvTime dvTime(String value) throws Exception {
        return new DvTime(new SimpleDateFormat("HH:mm:ss").parse(value));
    }

    public void testConstructorTakesString() throws Exception {
        String[] values = {
            "20:10:55", "00:00:59"
        };
        for (String value : values) {
            assertEquals(new DvTime(value), dvTime(value));
        }
        // verify bug fix
        assertTrue("24 hour fix", new DvTime(20, 45, 10).equals(
                new DvTime("20:45:10")));
    }

    public void testIsValidTime() throws Exception {
        assertFalse(DvTime.isValidTime(24, 0, 0));
        assertFalse(DvTime.isValidTime(0, 60, 0));
        assertFalse(DvTime.isValidTime(0, 0, 60));
        assertFalse(DvTime.isValidTime(-1, 0, 0));
        assertFalse(DvTime.isValidTime(0, -1, 0));
        assertFalse(DvTime.isValidTime(0, 0, -1));

        assertTrue(DvTime.isValidTime(23, 59, 59));
        assertTrue(DvTime.isValidTime(0, 0, 0));
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
 *  The Original Code is DvTimeTest.java
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