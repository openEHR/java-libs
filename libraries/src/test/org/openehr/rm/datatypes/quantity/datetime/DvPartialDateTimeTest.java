/*
 * component:   "openEHR Reference Implementation"
 * description: "Class DvPartialDateTimeTest"
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
 * DvPartialDateTimeTest
 *
 * @author Rong Chen
 * @version 1.0 
 */
package org.openehr.rm.datatypes.quantity.datetime;

import junit.framework.TestCase;
import org.openehr.rm.datatypes.quantity.DvInterval;

public class DvPartialDateTimeTest extends TestCase {

    public DvPartialDateTimeTest(String test) {
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
        DvPartialDateTime date;

        // month unknown
        date = new DvPartialDateTime(1999, null);
        assertEquals("1999-??-?? ??:??:??", date.toString());

        // day unknown
        date = new DvPartialDateTime(1999, 0, null);
        assertEquals("1999-01-?? ??:??:??", date.toString());

        // hour unknown
        date = new DvPartialDateTime(1999, 11, 31, null);
        assertEquals("1999-12-31 ??:??:??", date.toString());

        // minute unknown
        date = new DvPartialDateTime(1999, 11, 31, 0, null);
        assertEquals("1999-12-31 00:??:??", date.toString());

        // minute known
        date = new DvPartialDateTime(1999, 11, 31, 0, 59, null);
        assertEquals("1999-12-31 00:59:??", date.toString());
    }

    public void testFullConstructor() throws Exception {
        DvPartialDateTime date;

        date = new DvPartialDateTime("1999");
        assertEquals("year", 1999, date.getYear());
        assertFalse(date.isMonthKnown());
        assertFalse(date.isDayKnown());
        assertFalse(date.isHourKnown());
        assertFalse(date.isMinuteKnown());

        date = new DvPartialDateTime("1999-10");
        assertEquals("year", 1999, date.getYear());
        assertEquals("month", 9, date.getMonth());
        assertTrue(date.isMonthKnown());
        assertFalse(date.isDayKnown());
        assertFalse(date.isHourKnown());
        assertFalse(date.isMinuteKnown());

        date = new DvPartialDateTime("1999-10-20");
        assertEquals("year", 1999, date.getYear());
        assertEquals("month", 9, date.getMonth());
        assertEquals("day", 20, date.getDay());
        assertTrue(date.isMonthKnown());
        assertTrue(date.isDayKnown());
        assertFalse(date.isHourKnown());
        assertFalse(date.isMinuteKnown());

        date = new DvPartialDateTime("1999-10-20 14");
        assertEquals("year", 1999, date.getYear());
        assertEquals("month", 9, date.getMonth());
        assertEquals("day", 20, date.getDay());
        assertEquals("hour", 14, date.getHour());
        assertTrue(date.isMonthKnown());
        assertTrue(date.isDayKnown());
        assertTrue(date.isHourKnown());
        assertFalse(date.isMinuteKnown());

        date = new DvPartialDateTime("1999-10-20 14:30");
        assertEquals("year", 1999, date.getYear());
        assertEquals("month", 9, date.getMonth());
        assertEquals("day", 20, date.getDay());
        assertEquals("minute", 30, date.getMinute());
        assertTrue(date.isMonthKnown());
        assertTrue(date.isDayKnown());
        assertTrue(date.isHourKnown());
        assertTrue(date.isMinuteKnown());
    }

    public void testEnclosingInterval() throws Exception {
        assertEnclosingInterval("2001", "2001-1-1 00:00:00",
                "2001-12-31 23:59:59");

        assertEnclosingInterval("2001-4", "2001-4-1 00:00:00",
                        "2001-4-30 23:59:59");

        assertEnclosingInterval("2001-4-10", "2001-4-10 00:00:00",
                        "2001-4-10 23:59:59");

        assertEnclosingInterval("2001-7-20 8", "2001-7-20 08:00:00",
                        "2001-7-20 08:59:59");

        assertEnclosingInterval("2001-7-20 8:30", "2001-7-20 08:30:00",
                        "2001-7-20 08:30:59");        
    }

    private void assertEnclosingInterval(String value, String start,
                                         String end) throws Exception {
        DvPartialDateTime date = new DvPartialDateTime(value);
        DvInterval<DvDateTime> interval = new DvInterval<DvDateTime>(
                new DvDateTime(start), new DvDateTime(end));
        assertEquals(interval, date.enclosingInterval());
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
 *  The Original Code is DvPartialDateTimeTest.java
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