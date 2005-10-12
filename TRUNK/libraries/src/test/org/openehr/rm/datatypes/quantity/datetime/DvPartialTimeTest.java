/*
 * component:   "openEHR Reference Implementation"
 * description: "Class DvPartialTimeTest"
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
 * DvPartialTimeTest
 *
 * @author Rong Chen
 * @version 1.0 
 */
package org.openehr.rm.datatypes.quantity.datetime;

import junit.framework.TestCase;
import org.openehr.rm.datatypes.quantity.DvInterval;

public class DvPartialTimeTest extends TestCase {

    public DvPartialTimeTest(String test) {
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
        DvPartialTime time = new DvPartialTime(20, 55, null, true);
        assertEquals("20:55:??", time.toString());

        // minute unknown
        time = new DvPartialTime(20, 0, null, false);
        assertEquals("20:??:??", time.toString());
    }

    public void testEnclosingInterval() throws Exception {

        // minute known
        DvPartialTime partialTime = new DvPartialTime(15, 45, null, true);
        DvTime start = new DvTime(15, 45, 0);
        DvTime end = new DvTime(15, 45, 59);
        DvInterval<DvTime> expected = new DvInterval<DvTime>(start, end);
        assertEquals("minute known", expected, partialTime.enclosingInterval());

        // minute unknown
        partialTime = new DvPartialTime(15, 0, null, false);
        start = new DvTime(15, 0, 0);
        end = new DvTime(15, 59, 59);
        expected = new DvInterval<DvTime>(start, end);
        assertEquals("minute unknown", expected, partialTime.enclosingInterval());
    }

    public void testFullConstructor() throws Exception {
        DvPartialTime time = new DvPartialTime(null, 0.0, false, "20:15");
        assertEquals("hour", 20, time.getHour());
        assertEquals("minute", 15, time.getMinute());
        assertEquals("minuteKnown", true, time.isMinuteKnown());

        time = new DvPartialTime(null, 0.0, false, "20");
        assertEquals("hour", 20, time.getHour());
        assertEquals("minuteKnown", false, time.isMinuteKnown());
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
 *  The Original Code is DvPartialTimeTest.java
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