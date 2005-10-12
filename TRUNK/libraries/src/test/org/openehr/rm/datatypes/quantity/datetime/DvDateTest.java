/*
 * component:   "openEHR Reference Implementation"
 * description: "Class DvDateTest"
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
 * DvDateTest
 *
 * @author Rong Chen
 * @version 1.0 
 */
package org.openehr.rm.datatypes.quantity.datetime;

import junit.framework.TestCase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DvDateTest extends TestCase {

    public DvDateTest(String test) {
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
        assertTrue(dvDate("1999-12-31").compareTo(dvDate("2000-01-01")) < 0);
        assertTrue(dvDate("2001-01-31").compareTo(dvDate("2001-02-01")) < 0);
        assertTrue(dvDate("2001-02-11").compareTo(dvDate("2001-02-12")) < 0);

        assertTrue(dvDate("2003-02-01").compareTo(dvDate("2002-12-15")) > 0);
        assertTrue(dvDate("2003-02-02").compareTo(dvDate("2003-01-24")) > 0);
        assertTrue(dvDate("2003-02-16").compareTo(dvDate("2003-02-15")) > 0);

        assertTrue(dvDate("2002-02-01").compareTo(dvDate("2002-02-01")) == 0);
    }

    private DvDate dvDate(String value) throws Exception {
        return new DvDate(new SimpleDateFormat("yyyy-MM-dd").parse(value));
    }

    public void testToString() throws Exception {
        assertEquals(new DvDate("2001-01-15").toString(), "2001-01-15");
        assertEquals(new DvDate(2001, 9, 15, null).toString(), "2001-10-15");
        assertEquals(new DvDate(2001, 11, 15, null).toString(), "2001-12-15");
    }

    public void testEquals() throws Exception {
        DvDate dateOne = new DvDate("2004-01-31");
        DvDate dateTwo = new DvDate(2004, 0, 31, null);
        assertTrue("dateOne.equals(dateTwo): ", dateOne.equals(dateTwo));
        assertTrue("dateTWo.equals(dateOne): ", dateTwo.equals(dateOne));
    }

    public void testConstructorTakesString() throws Exception {
        String[] values = {
            "2004-12-31", "1999-01-01"
        };
        for (String value : values) {
            assertEquals(new DvDate(value), dvDate(value));
        }
    }

    public void testIsValidDate() throws Exception {
        assertFalse(DvDate.isValidDate(2000, -1, 1));
        assertFalse(DvDate.isValidDate(2000, 12, 1));
        assertFalse(DvDate.isValidDate(2000, 0, 0));
        assertFalse(DvDate.isValidDate(2000, 0, 32)); // january
        assertFalse(DvDate.isValidDate(2000, 3, 31)); // april
        assertFalse(DvDate.isValidDate(2004, 1, 30)); // feburary
        assertFalse(DvDate.isValidDate(2005, 1, 29)); // feburary

        assertTrue(DvDate.isValidDate(2000, 0, 1));
        assertTrue(DvDate.isValidDate(2000, 0, 31));
        assertTrue(DvDate.isValidDate(2000, 3, 1));
        assertTrue(DvDate.isValidDate(2004, 1, 29));  // feburary
        assertTrue(DvDate.isValidDate(2005, 1, 28)); // feburary
    }

    public void testCalendarSetMonth() throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1999, 10, 15);
        assertEquals(10, calendar.get(Calendar.MONTH));
    }

    public void testGetYearMonthDay() throws Exception {
        DvDate date = new DvDate(1999, 10, 20);
        assertEquals("year", 1999, date.getYear());
        assertEquals("month", 10, date.getMonth());
        assertEquals("day", 20, date.getDay());
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
 *  The Original Code is DvDateTest.java
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