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
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/BRANCHES/RM-1.0-update/libraries/src/test/org/openehr/rm/datatypes/quantity/datetime/DvDateTest.java $"
 * revision:    "$LastChangedRevision: 50 $"
 * last_change: "$LastChangedDate: 2006-08-10 13:21:46 +0200 (Thu, 10 Aug 2006) $"
 */

/**
 * DvDateTest
 *
 * @author Rong Chen
 * @version 1.0 
 */
package org.openehr.rm.datatypes.quantity.datetime;

import junit.framework.TestCase;

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

        assertTrue(dvDate("20030201").compareTo(dvDate("20021215")) > 0);
        assertTrue(dvDate("20030202").compareTo(dvDate("20030124")) > 0);
        assertTrue(dvDate("20030216").compareTo(dvDate("20030215")) > 0);

        assertTrue(dvDate("2002-02").compareTo(dvDate("2002-02-01")) == 0);
        assertTrue(dvDate("2002-02").compareTo(dvDate("2002-02-02")) < 0);
        assertTrue(dvDate("2002").compareTo(dvDate("2002-02-02")) < 0);
        assertTrue(dvDate("2002-02").compareTo(dvDate("2002-01")) > 0);
        assertTrue(dvDate("2002").compareTo(dvDate("2001-12")) > 0);
        
    }

    private DvDate dvDate(String value) throws Exception {
        return new DvDate(value);
    }

    public void testToString() throws Exception {
        assertEquals(new DvDate("2001-01-15").toString(), "2001-01-15");
        assertEquals(new DvDate("20011015").toString(), "20011015");
        assertEquals(new DvDate("2001-10").toString(), "2001-10");
        assertEquals(new DvDate("2001").toString(), "2001");
        assertEquals(new DvDate(2001, 12, 15).toString(), "2001-12-15");
        assertEquals(new DvDate(2000, 10).toString(), "2000-10");
        assertEquals(new DvDate(2000).toString(), "2000");
    }

    public void testToStringTakesBoolean() {
        assertEquals(new DvDate("2001-01-15").toString(false), "20010115");
        assertEquals(new DvDate("20011015").toString(true), "2001-10-15");
        assertEquals(new DvDate("2001-10").toString(false), "200110");
        assertEquals(new DvDate("2001").toString(true), "2001");
        assertEquals(new DvDate(2001, 12, 15).toString(false), "20011215");
        assertEquals(new DvDate(2000, 10).toString(true), "2000-10");
        assertEquals(new DvDate(2000).toString(false), "2000");
    }
    
    public void testEquals() throws Exception {
        //Two DvDate are equal if both indicate the same date.
        DvDate dateOne = new DvDate("2004-01-31");
        DvDate dateTwo = new DvDate(2004, 1, 31);
        DvDate dateThree = new DvDate("20040131");
        assertTrue("dateOne.equals(dateTwo): ", dateOne.equals(dateTwo));
        assertTrue("dateTWo.equals(dateOne): ", dateTwo.equals(dateOne));
        assertTrue("dateOne.equals(dateThree): ", dateOne.equals(dateThree));
        assertTrue("dateThree.equals(dateOne): ", dateThree.equals(dateOne));
        assertTrue("dateTwo.equals(dateThree): ", dateTwo.equals(dateThree));
        assertTrue("dateThree.equals(dateTwo): ", dateThree.equals(dateTwo));
        dateOne = new DvDate("1999-09");
        dateTwo = new DvDate(1999, 9);
        dateThree = new DvDate("199909");
        assertTrue("dateOne.equals(dateTwo): ", dateOne.equals(dateTwo));
        assertTrue("dateTWo.equals(dateOne): ", dateTwo.equals(dateOne));
        assertTrue("dateOne.equals(dateThree): ", dateOne.equals(dateThree));
        assertTrue("dateThree.equals(dateOne): ", dateThree.equals(dateOne));
        assertTrue("dateTwo.equals(dateThree): ", dateTwo.equals(dateThree));
        assertTrue("dateThree.equals(dateTwo): ", dateThree.equals(dateTwo));
    }
    
    public void testConstructorTakesString() throws Exception {
        String[] values = {
            "2004-12-31", "1999-01-01", "18990102", "1789-09", "166612", "2002"
        };
        for (String value : values) {
            assertEquals(new DvDate(value), dvDate(value));
        }
    }

    public void testConstructorTakesIntegers() throws Exception {
        assertNotNull(new DvDate(1000));
        assertNotNull(new DvDate(1980, 11));
        assertNotNull(new DvDate(2000, 11, 30));	
    }    

    public void testGetYearMonthDay() throws Exception {
        DvDate date = new DvDate(1999, 10, 20);
        assertEquals("year", 1999, date.getYear());
        assertEquals("month", 10, date.getMonth());
        assertEquals("day", 20, date.getDay());
        date = new DvDate("2002-09-20");
        assertEquals("year", 2002, date.getYear());
        assertEquals("month", 9, date.getMonth());
        assertEquals("day", 20, date.getDay());
        date = new DvDate("20060107");
        assertEquals("year", 2006, date.getYear());
        assertEquals("month", 1, date.getMonth());
        assertEquals("day", 7, date.getDay());
        date = new DvDate("204611");
        assertEquals("year", 2046, date.getYear());
        assertEquals("month", 11, date.getMonth());
        assertEquals("day", -1, date.getDay());
        date = new DvDate(1988, 3);
        assertEquals("year", 1988, date.getYear());
        assertEquals("month", 3, date.getMonth());
        assertEquals("day", -1, date.getDay());
        date = new DvDate("2020");
        assertEquals("year", 2020, date.getYear());
        assertEquals("month", -1, date.getMonth());
        assertEquals("day", -1, date.getDay());
    }

    public void testMonthDayKnown() throws Exception {
        DvDate date = new DvDate(1999, 10, 20);
        assertEquals("isPartial", false, date.isPartial());
        assertEquals("month known", true, date.monthKnown());
        assertEquals("day known", true, date.dayKnown());
        date = new DvDate("204611");
        assertEquals("isPartial", true, date.isPartial());
        assertEquals("month known", true, date.monthKnown());
        assertEquals("day known", false, date.dayKnown());
        date = new DvDate(2020);
        assertEquals("isPartial", true, date.isPartial());
        assertEquals("month known", false, date.monthKnown());
        assertEquals("day known", false, date.dayKnown());       
    }
    
    public void testSetValueInConstructor() throws Exception {
    	assertEquals(new DvDate(2).getValue(), "0002");    	
    	assertEquals(new DvDate(20).getValue(), "0020");
    	assertEquals(new DvDate(200).getValue(), "0200");
    	assertEquals(new DvDate(2000).getValue(), "2000");
    	
    	assertEquals(new DvDate(2000, 9).getValue(), "2000-09");
    	assertEquals(new DvDate(2000, 10).getValue(), "2000-10");    	
    	
    	assertEquals(new DvDate(2000, 10, 1).getValue(), "2000-10-01");
    	assertEquals(new DvDate(2000, 10, 10).getValue(), "2000-10-10");
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