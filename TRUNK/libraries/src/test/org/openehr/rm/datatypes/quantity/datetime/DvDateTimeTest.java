/*
 * component:   "openEHR Reference Implementation"
 * description: "Class DvDateTimeTest"
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
 * DvDateTimeTest
 *
 * @author Rong Chen
 * @version 1.0 
 */
package org.openehr.rm.datatypes.quantity.datetime;

import junit.framework.TestCase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DvDateTimeTest extends TestCase {

    public DvDateTimeTest(String test) {
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
        assertTrue(dvDate("1999-12-31 00:00:00").compareTo(dvDate("2000-01-01 00:00:00")) < 0);
        assertTrue(dvDate("2001-01-31 00:00:00").compareTo(dvDate("2001-02-01 00:00:00")) < 0);
        assertTrue(dvDate("2001-02-11 00:00:00").compareTo(dvDate("2001-02-12 00:00:00")) < 0);
        assertTrue(dvDate("2001-02-11 00:00:00").compareTo(dvDate("2001-02-11 00:00:01")) < 0);
        assertTrue(dvDate("2001-02-11 00:00:00").compareTo(dvDate("2001-02-11 00:01:00")) < 0);
        assertTrue(dvDate("2001-02-11 00:00:00").compareTo(dvDate("2001-02-11 01:00:00")) < 0);

        assertTrue(dvDate("2003-02-01 00:00:00").compareTo(dvDate("2002-12-15 00:00:00")) > 0);
        assertTrue(dvDate("2003-02-02 00:00:00").compareTo(dvDate("2003-01-24 00:00:00")) > 0);
        assertTrue(dvDate("2003-02-16 00:00:00").compareTo(dvDate("2003-02-15 00:00:00")) > 0);
        assertTrue(dvDate("2001-01-01 01:00:00").compareTo(dvDate("2001-01-01 00:00:00")) > 0);
        assertTrue(dvDate("2001-01-01 00:01:00").compareTo(dvDate("2001-01-01 00:00:00")) > 0);
        assertTrue(dvDate("2001-01-01 00:00:01").compareTo(dvDate("2001-01-01 00:00:00")) > 0);

        assertTrue(dvDate("2002-02-01 00:00:00").compareTo(dvDate("2002-02-01 00:00:00")) == 0);
    }

    public void testToString() throws Exception {
        String[] values = {
            "2004-10-31 20:10:55", "2000-01-01 00:00:59"
        };
        for(String value : values) {
            assertEquals(dvDate(value).toString(), value);
        }
    }

    private DvDateTime dvDate(String value) throws Exception {
        return new DvDateTime(new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss").parse(value));
    }

    public void testConstructorTakesString() throws Exception {
        String[] values = {
            "2004-10-31 20:10:55", "2000-01-01 00:00:59"
        };
        for(String value : values) {
            assertEquals(new DvDateTime(value), dvDate(value));
        }
    }

    public void testGetters() throws Exception {
        DvDateTime datetime = new DvDateTime("1999-10-20 18:15:45");
        assertEquals("year", 1999, datetime.getYear());
        assertEquals("month", 9, datetime.getMonth());
        assertEquals("day", 20, datetime.getDay());
        assertEquals("hour", 18, datetime.getHour());
        assertEquals("minute", 15, datetime.getMinute());
        assertEquals("second", 45, datetime.getSecond());
    }

    public void testEquals() throws Exception {
        DvDateTime datetime1 = new DvDateTime("2003-12-15 09:30:00");
        DvDateTime datetime2 = new DvDateTime("2003-12-15 09:30:00");
        assertTrue(datetime1.equals(datetime2));
        assertTrue(datetime2.equals(datetime1));

        datetime1 = new DvDateTime("2003-12-15 09:30:00");
        datetime2 = new DvDateTime("2003-12-15 10:30:00");
        assertFalse(datetime1.equals(datetime2));
        assertFalse(datetime2.equals(datetime1));

        // replicate a bug caused by rounded millisec
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(1109885309658L);
        datetime1 = new DvDateTime(calendar);
        calendar.setTimeInMillis(1109885309000L);
        datetime2 = new DvDateTime(calendar);
        assertTrue(datetime1.equals(datetime2));
        assertTrue(datetime2.equals(datetime1));

        // replicate a bug caused by rounded millisec
        datetime1 = new DvDateTime();
        long milliSec = datetime1.getCalendar().getTimeInMillis();
        calendar.setTimeInMillis(milliSec - (milliSec % 1000));
        datetime2 = new DvDateTime();
        datetime2.setCalendar(calendar);
        assertTrue("datetime1: " + datetime1.getCalendar().getTimeInMillis()
                + ", datetime2: " + datetime2.getCalendar().getTimeInMillis(),
                datetime1.equals(datetime2));
        assertTrue(datetime2.equals(datetime1));
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
 *  The Original Code is DvDateTimeTest.java
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