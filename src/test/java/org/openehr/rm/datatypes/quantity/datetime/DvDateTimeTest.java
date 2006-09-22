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
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/BRANCHES/RM-1.0-update/libraries/src/test/org/openehr/rm/datatypes/quantity/datetime/DvDateTimeTest.java $"
 * revision:    "$LastChangedRevision: 50 $"
 * last_change: "$LastChangedDate: 2006-08-10 13:21:46 +0200 (Thu, 10 Aug 2006) $"
 */

/**
 * DvDateTimeTest
 *
 * @author Rong Chen
 * @version 1.0 
 */
package org.openehr.rm.datatypes.quantity.datetime;

import java.util.TimeZone;
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
        assertTrue(dvDate("1999-12-31T00:00:00").compareTo(dvDate("2000-01-01T00:00:00")) < 0);
        assertTrue(dvDate("2001-01-31T00:00:00").compareTo(dvDate("2001-02-01T00:00:00")) < 0);
        assertTrue(dvDate("20010211T000000").compareTo(dvDate("2001-02-12T00:00:00")) < 0);
        assertTrue(dvDate("2001-02-11T00:00").compareTo(dvDate("2001-02-11T00:00:01")) < 0);
        assertTrue(dvDate("2001-02-11T00").compareTo(dvDate("2001-02-11T00:01:00")) < 0);
        assertTrue(dvDate("2001-02-11T00+01").compareTo(dvDate("2001-02-11T01:00:00Z")) < 0);

        assertTrue(dvDate("2003-02-01T00:00:00").compareTo(dvDate("2002-12-15T00:00:00")) > 0);
        assertTrue(dvDate("2003-02-02T00:00").compareTo(dvDate("2003-01-24T00:00:00")) > 0);
        assertTrue(dvDate("2003-02-16T00:00:00").compareTo(dvDate("2003-02-15T23:59:00")) > 0);
        assertTrue(dvDate("2001-01-01T01:00:00").compareTo(dvDate("20010101T00")) > 0);
        assertTrue(dvDate("2001-01-01T00:01:00").compareTo(dvDate("2001-01-01T00:00:00")) > 0);
        assertTrue(dvDate("2001-01-01T00:00:01-03").compareTo(dvDate("2001-01-01T01:00:00Z")) > 0);

        assertTrue(dvDate("2002-02-01T00:00:00").compareTo(dvDate("2002-02-01T00:00:00")) == 0);
        assertTrue(dvDate("2002-02-01T00:00:00Z").compareTo(dvDate("20020201T010000+01")) == 0);
        
        
    }

    public void testToString() throws Exception {
        String[] values = {
            "2004-10-31T20:10:55", "2000-01-01T00:00:59"
        };
        for(String value : values) {
            assertEquals(dvDate(value).toString(), value);
        }
    }

    private DvDateTime dvDate(String value) throws Exception {
        return new DvDateTime(value);
    }

    public void testConstructorTakesString() throws Exception {
        String[] values = {
            "2004-10-31T20:10:55", "2000-01-01T00:00:59"
        };
        for(String value : values) {
            assertEquals(new DvDateTime(value), dvDate(value));
        }
    }

    public void testGetters() throws Exception {
        DvDateTime datetime = new DvDateTime("1999-10-20T18:15:45");
        assertEquals("year", 1999, datetime.getYear());
        assertEquals("month", 10, datetime.getMonth());
        assertEquals("day", 20, datetime.getDay());
        assertEquals("hour", 18, datetime.getHour());
        assertEquals("minute", 15, datetime.getMinute());
        assertEquals("second", 45, datetime.getSecond());
        assertEquals("fracSecond", -0.1, datetime.getFractionalSecond());
    }

    public void testEquals() throws Exception {
        DvDateTime datetime1 = new DvDateTime("2003-12-15T09:30:00Z");
        DvDateTime datetime2 = new DvDateTime(2003, 12, 15, 9, 30, 0, TimeZone.getTimeZone("UTC"));
        assertTrue(datetime1.equals(datetime2));
        assertTrue(datetime2.equals(datetime1));

        datetime1 = new DvDateTime("2003-12-15T09:30:00");
        datetime2 = new DvDateTime("2003-12-15T10:30:00");
        assertFalse(datetime1.equals(datetime2));
        assertFalse(datetime2.equals(datetime1));

    }
    
    public void testAdd() throws Exception {
        DvDateTime datetime = new DvDateTime("2003-12-15T09:30:00Z");
        assertEquals(new DvDateTime("2004-12-15T09:30:00Z"), datetime.add(new DvDuration("P1Y")));
        assertEquals(new DvDateTime("2004-12-16T09:30:00Z"), datetime.add(new DvDuration("P1Y1D")));
        assertEquals(new DvDateTime("2004-12-17T00:00:00Z"), datetime.add(new DvDuration("P1Y1DT14H30m")));
        assertEquals(new DvDateTime("2002-12-13T18:45:00Z"), datetime.add(new DvDuration("-P1Y1DT14H45m")));
    }
    
    public void testSubtract() throws Exception {
        DvDateTime datetime = new DvDateTime("2003-12-15T09:30:00Z");
        assertEquals(new DvDateTime("2002-12-13T18:45:00Z"), datetime.subtract(new DvDuration("P1Y1DT14H45m")));
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