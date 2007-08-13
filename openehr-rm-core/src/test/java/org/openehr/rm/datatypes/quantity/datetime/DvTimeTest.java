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
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/BRANCHES/RM-1.0-update/libraries/src/test/org/openehr/rm/datatypes/quantity/datetime/DvTimeTest.java $"
 * revision:    "$LastChangedRevision: 52 $"
 * last_change: "$LastChangedDate: 2006-08-10 17:53:34 +0200 (Thu, 10 Aug 2006) $"
 */

/**
 * DvTimeTest
 *
 * @author Rong Chen
 * @version 1.0 
 */
package org.openehr.rm.datatypes.quantity.datetime;

import junit.framework.TestCase;
import java.util.TimeZone;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

public class DvTimeTest extends TestCase {

    public DvTimeTest(String test) {
        super(test);
    }

    /**
     * The fixture set up called before every test method.
     */
    protected void setUp() throws Exception {
        zoneStr = DvDateTimeParser.convertTimeZone(
                DateTimeZone.getDefault().getOffset(new DateTime()), false);
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
        assertTrue(dvTime("00:00:00.000").compareTo(dvTime("00:00:00.001")) < 0);
        
        assertTrue(dvTime("010000").compareTo(dvTime("000000")) > 0);
        assertTrue(dvTime("000100").compareTo(dvTime("000000")) > 0);
        assertTrue(dvTime("000001").compareTo(dvTime("000000")) > 0);
        assertTrue(dvTime("000000,001").compareTo(dvTime("000000,000")) > 0);
        
        assertTrue(dvTime("00:00:01").compareTo(dvTime("00:00:01")) == 0);
        
        assertTrue(dvTime("00:00:00Z").compareTo(dvTime("00:00:00+01:00")) > 0);
        assertTrue(dvTime("00:00:00Z").compareTo(dvTime("00:00:00-00:30")) < 0);
        assertTrue(dvTime("00:00:00Z").compareTo(dvTime("01:00:00+01:00")) == 0);
        assertTrue(dvTime("00:30:00Z").compareTo(dvTime("00:00:00-00:30")) == 0);
        assertTrue(dvTime("01:30:00.001Z").compareTo(dvTime("00:30:00.001-01:00")) == 0);
        
        assertTrue(new DvTime(19, 55, 45, 0.899, TimeZone.getTimeZone("GMT-02")).compareTo(dvTime("195545")) > 0);
        assertTrue(new DvTime(19, 55, 45, TimeZone.getTimeZone("GMT-02")).compareTo(dvTime("195545")) > 0);
        assertTrue(new DvTime(19, 55, 45, TimeZone.getTimeZone("GMT-02")).compareTo(dvTime("195545-03")) < 0);
        assertTrue(new DvTime(19, 55, TimeZone.getTimeZone("GMT-02")).compareTo(dvTime("195545-02")) < 0);
        assertTrue(new DvTime(19, TimeZone.getTimeZone("GMT-02")).compareTo(dvTime("195545-02")) < 0);
        
    }

    public void testConstructorTakeString() throws Exception {
        String[] values = {
            "20:10:55", "00:00:59Z", "12:12:30-01:00", "21:23:34.234",
            "01:02:03.009+02:30", "21:23:34,234", "08:10:33,001Z", "01:02:03,009+02:30", 
            "201055", "000059Z", "121230-0100", "212334.234", 
            "010203.009+0230", "212334,234", "081033,001Z", "010203,009+0230",
            "12:35:45.666", "12:35:45-0700"};

        for (String value : values) {
            assertEquals(new DvTime(value), dvTime(value));
            assertNotNull(new DvTime(value));
        }

    }
    
    public void testConstructorTakesIntegers() throws Exception {
        
        assertNotNull(new DvTime(20, 10, 55, 0.133, null));
        assertNotNull(new DvTime(20, 10, 55, null));
        assertNotNull(new DvTime(0, 0, 59, TimeZone.getTimeZone("UTC")));
        assertNotNull(new DvTime(9, 4, 59, TimeZone.getTimeZone("GMT+10")));  
        assertNotNull(new DvTime(5, 22, TimeZone.getTimeZone("GMT-3")));  
        assertNotNull(new DvTime(0, null));
        assertNotNull(new DvTime(23, 59, 50, TimeZone.getTimeZone("GMT-11")));		
    }  
    
    public void testToString() throws Exception {
        String[] values = {
            "19:55:45-02", "01:15:25.245", "000000Z", "235959", "10:19:33,029-09:00"
        };
        for (String value : values) {
            assertEquals(dvTime(value).toString(), value);
        }
        assertEquals(dvTime("19:55:45-02:00").toString(), 
                new DvTime(19, 55, 45, TimeZone.getTimeZone("GMT-02")).toString());
        assertEquals(dvTime("01:15:25,000+01:00").toString(), 
                new DvTime(1, 15, 25, 0.0, TimeZone.getTimeZone("GMT+1")).toString());
        assertEquals(dvTime("00:00Z").toString(), 
                new DvTime(0, 0, TimeZone.getTimeZone("UTC")).toString());       
        assertEquals(dvTime("01").toString(), new DvTime(1, null).toString());
        assertEquals(dvTime("01:02:03").toString(), new DvTime(1, 2, 3, null).toString());

    }

    public void testToStringTakesBoolean() throws Exception {
        String[] values = {
            "19:55:45-02", "01:15:25.245", "00:00:00Z", "23:59:59", "10:19:33,029-09:00",
            "12:22-04", "18Z"
        };
        for (String value : values) {
            assertEquals(dvTime(value).toString(false), value.replace(":", ""));
        }
        assertEquals(dvTime("195545-02").toString(true), "19:55:45-02");
        assertEquals(dvTime("101933,029-0900").toString(true), "10:19:33,029-09:00");
        assertEquals(dvTime("1223-0230").toString(true), "12:23-02:30");
        assertEquals(dvTime("02Z").toString(true), "02Z");
        
        DvTime dTime = new DvTime(19, 55, 45, TimeZone.getTimeZone("GMT-02"));
        assertEquals(dTime.toString(), dTime.toString(true));
        assertEquals("195545-0200", dTime.toString(false));
        dTime =  new DvTime(1, 15, 25, 0.0, TimeZone.getTimeZone("GMT+1"));
        assertEquals(dTime.toString(), dTime.toString(true));
        assertEquals("011525,000+0100", dTime.toString(false));
    }
 
    public void testEquals() throws Exception {

        assertEquals(new DvTime(0, 0, 59, 0.345, TimeZone.getTimeZone("UTC")), new DvTime("00:00:59.345Z"));
        assertEquals(new DvTime(0, 0, 59, TimeZone.getTimeZone("GMT-1030")), new DvTime("000059-1030"));
        assertEquals(new DvTime(2, 38, TimeZone.getTimeZone("GMT+0700")), new DvTime("02:38+07:00"));
        assertEquals(new DvTime(23, TimeZone.getTimeZone("GMT-0230")), new DvTime("23-0230"));
        
    }
        
    private DvTime dvTime(String value) throws Exception {
        return new DvTime(value);
    }
    
    public void testIsValidTime() throws Exception {
        assertFalse(DvTime.isValidISO8601Time("24:00:00"));//Joda specific, do anything?
        assertFalse(DvTime.isValidISO8601Time("240000"));
        assertFalse(DvTime.isValidISO8601Time("006000"));
        assertFalse(DvTime.isValidISO8601Time("00:00:60"));
        assertFalse(DvTime.isValidISO8601Time("256678"));
        assertFalse(DvTime.isValidISO8601Time("005500U"));
        assertFalse(DvTime.isValidISO8601Time("-000001"));
        assertFalse(DvTime.isValidISO8601Time("003001+02:30"));        
        assertFalse(DvTime.isValidISO8601Time("00:03:09.999+25:30"));
              
        assertTrue(DvTime.isValidISO8601Time("23:59:59"));
        assertTrue(DvTime.isValidISO8601Time("00:00:00+23:00"));
        assertTrue(DvTime.isValidISO8601Time("164530+0000"));
        assertTrue(DvTime.isValidISO8601Time("14:25:20-00:00"));
        assertTrue(DvTime.isValidISO8601Time("22:46:08-01"));
        assertTrue(DvTime.isValidISO8601Time("204724-11"));
        assertTrue(DvTime.isValidISO8601Time("12:30:10-0230"));
    }

    public void testIsEquivalent() throws Exception {
        assertTrue(new DvTime("00:00:59Z").isEquivalent(new DvTime("010059+01")));
        assertTrue(new DvTime("013059Z").isEquivalent(new DvTime("00:30:59-01")));
        assertTrue(new DvTime("00:00:59Z").isEquivalent(new DvTime("01:00:59+01")));
        //assertTrue(new DvTime("00:00:59Z").isEquivalent(new DvTime("01:00:59+01")));
        assertFalse(new DvTime("003059Z").isEquivalent(new DvTime("23:30:59-01")));
    }
        
    public void testGetHourMinuteSecFSec() throws Exception {
        
        DvTime dTime = new DvTime(19, 55, 45, 0.829, TimeZone.getTimeZone("GMT-02"));       
        assertEquals(19, dTime.getHour());
        assertEquals(55, dTime.getMinute());
        assertEquals(45, dTime.getSecond());
        assertEquals(0.829, dTime.getFractionalSecond(), 0.000001);
        dTime = dvTime("17:49:08,679-02");
        assertEquals(17, dTime.getHour());
        assertEquals(49, dTime.getMinute());
        assertEquals(8, dTime.getSecond());
        assertEquals(0.679, dTime.getFractionalSecond());
        dTime = dvTime("142908-02");
        assertEquals(14, dTime.getHour());
        assertEquals(29, dTime.getMinute());
        assertEquals(8, dTime.getSecond());
        assertEquals(-0.1, dTime.getFractionalSecond());
        dTime = dvTime("1225-0200");
        assertEquals(12, dTime.getHour());
        assertEquals(25, dTime.getMinute());
        assertEquals(-1, dTime.getSecond());
        assertEquals(-0.1, dTime.getFractionalSecond());
        dTime = dvTime("22-0100");
        assertEquals(22, dTime.getHour());
        assertEquals(-1, dTime.getMinute());
        assertEquals(-1, dTime.getSecond());
        assertEquals(-0.1, dTime.getFractionalSecond());
        dTime = new DvTime(8, 0, 16, null);
        assertEquals(8, dTime.getHour());
        assertEquals(0, dTime.getMinute());
        assertEquals(16, dTime.getSecond());
        assertEquals(-0.1, dTime.getFractionalSecond());
        dTime = new DvTime(13, 39, TimeZone.getTimeZone("GMT-09"));
        assertEquals(13, dTime.getHour());
        assertEquals(39, dTime.getMinute());
        assertEquals(-1, dTime.getSecond());
        assertEquals(-0.1, dTime.getFractionalSecond());
        dTime = new DvTime(1, TimeZone.getTimeZone("UTC"));
        assertEquals(1, dTime.getHour());
        assertEquals(-1, dTime.getMinute());
        assertEquals(-1, dTime.getSecond());
        assertEquals(-0.1, dTime.getFractionalSecond());
    }
   
    public void testMinuteSecondFSecKnown() throws Exception {
        DvTime dTime = new DvTime(19, 55, 45, 0.829, TimeZone.getTimeZone("GMT-02"));       
        assertEquals(false, dTime.isPartial());
        assertEquals(true, dTime.minuteKnown());
        assertEquals(true, dTime.secondKnown());
        assertEquals(true, dTime.hasFractionalSecond());
        dTime = dvTime("17:49:08,679-02");
        assertEquals(false, dTime.isPartial());
        assertEquals(true, dTime.minuteKnown());
        assertEquals(true, dTime.secondKnown());
        assertEquals(true, dTime.hasFractionalSecond());
        dTime = dvTime("142908-02");
        assertEquals(false, dTime.isPartial());
        assertEquals(true, dTime.minuteKnown());
        assertEquals(true, dTime.secondKnown());
        assertEquals(false, dTime.hasFractionalSecond());
        dTime = dvTime("1225-0200");
        assertEquals(true, dTime.isPartial());
        assertEquals(true, dTime.minuteKnown());
        assertEquals(false, dTime.secondKnown());
        assertEquals(false, dTime.hasFractionalSecond());
        dTime = dvTime("22-0100");
        assertEquals(true, dTime.isPartial());
        assertEquals(false, dTime.minuteKnown());
        assertEquals(false, dTime.secondKnown());
        assertEquals(false, dTime.hasFractionalSecond());
        dTime = new DvTime(8, 0, 16, null);
        assertEquals(false, dTime.isPartial());
        assertEquals(true, dTime.minuteKnown());
        assertEquals(true, dTime.secondKnown());
        assertEquals(false, dTime.hasFractionalSecond());
        dTime = new DvTime(13, 39, TimeZone.getTimeZone("GMT-09"));
        assertEquals(true, dTime.isPartial());
        assertEquals(true, dTime.minuteKnown());
        assertEquals(false, dTime.secondKnown());
        assertEquals(false, dTime.hasFractionalSecond());
        dTime = new DvTime(1, TimeZone.getTimeZone("UTC"));
        assertEquals(true, dTime.isPartial());
        assertEquals(false, dTime.minuteKnown());
        assertEquals(false, dTime.secondKnown());
        assertEquals(false, dTime.hasFractionalSecond());
    }

    public void testAdd() throws Exception {
        //TODO: testAdd
    }
    
    private String zoneStr;
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