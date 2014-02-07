/*
 * component:   "openEHR Reference Implementation"
 * description: "Class DvDateTimeParserTest"
 * keywords:    "unit test"
 *
 * author:      "Yin Su Lim <y.lim@chime.ucl.ac.uk>"
 * support:     "CHIME, UCL"
 * copyright:   "Copyright (c) 2006 UCL, UK"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/BRANCHES/RM-1.0-update/libraries/src/test/org/openehr/rm/datatypes/quantity/datetime/DvDateTimeParserTest.java $"
 * revision:    "$LastChangedRevision: 50 $"
 * last_change: "$LastChangedDate: 2006-08-10 12:21:46 +0100 (Thu, 10 Aug 2006) $"
 */

/**
 * DvDateTimeParserTest
 *
 * @author Yin Su Lim
 * @version 1.0 
 */

package org.openehr.rm.datatypes.quantity.datetime;

import junit.framework.*;

import java.util.Locale;
import java.util.TimeZone;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.ISODateTimeFormat;

public class DvDateTimeParserTest extends TestCase {
	
	public DvDateTimeParserTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
        defZoneStr = DvDateTimeParser.convertTimeZone(
                DateTimeZone.getDefault().getOffset(new DateTime()), false);
    }

    protected void tearDown() throws Exception {
    }

    /**
     * Test of parseTime method, of class org.openehr.rm.datatypes.quantity.datetime.DvDateTimeParser.
     */
    public void testParseTime() {        
        String[] values = {
        		"23", "23:30", "23:30Z", "23+11", "2330", "1000Z", "11Z", "153722", 
                "225523.9", "102030.01", "191817.289",
                "23:59:59", "12:35:45.666", "12:35:45-0700"
                            
        };//strings that should pass
        for(int i = 0; i < values.length; i++) {
        	DateTime result = DvDateTimeParser.parseTime(values[i]);
        	assertNotNull(result);
        }
    }
    
    public void testParseTimeWithPtBrLocale() {
    	Locale defaultLocale = Locale.getDefault();
    	Locale.setDefault(new Locale("pt", "BR"));
    	
    	try {
    		DvDateTimeParser.parseTime("010000");
    	} catch(Exception e) {
    		fail("failed to parse 010000 in 'pt-BR' locale");
    	} 
    	Locale.setDefault(defaultLocale);
    }    
    
    /**
     * Test of parseDate method, of class org.openehr.rm.datatypes.quantity.datetime.DvDateTimeParser.
     */
    public void testParseDate() {        
        //String value = "199912";
        String[] values = {
        		"1333", "1982-02-19", "1982-02", "29491213", "199002"        		
        };//strings that pass
        DateTime result = null;
        for(int i = 0; i < values.length; i++) {
            result = DvDateTimeParser.parseDate(values[i]);
            assertNotNull(result);
        }         
    }

    /**
     * Test of parseDateTime method, of class org.openehr.rm.datatypes.quantity.datetime.DvDateTimeParser.
     */
    public void testParseDateTime() {
       
        String[] values = {
        		"1999-09-10T23:59:59.010-02:00", "2009-12-12T00:12", "2009-12-12T00:12-01:00", "2000-12-01T10Z", 
                "1900-01-04T11+02:00", "19990910T235959,000+0100", "19441116T005900.000", "19001217T205933", 
                "19441116T00+0900", "13440916T00", "2000-01-01T00:00:59+1200" 
        };//strings that pass
        for(int i = 0; i < values.length; i++) {
            DateTime result = DvDateTimeParser.parseDateTime(values[i]);
            assertNotNull(result);
        } 
    }
        
    /**
     * Test of padTimeValue method, of class org.openehr.rm.datatypes.quantity.datetime.DvDateTimeParser.
     */
    public void testPadValue() {
        assertEquals("232922.000" + defZoneStr, DvDateTimeParser.padTimeValue("232922"));
        assertEquals("232922,099" + defZoneStr, DvDateTimeParser.padTimeValue("232922,099"));
        assertEquals("131920.000Z", DvDateTimeParser.padTimeValue("131920Z"));
        assertEquals("011300.000-09", DvDateTimeParser.padTimeValue("011300-09"));      
        assertEquals("021530.123-09", DvDateTimeParser.padTimeValue("021530.123-09"));
    }

    /**
     * Test of convertTimeZone method, of class org.openehr.rm.datatypes.quantity.datetime.DvDateTimeParser.
     */
    public void testConvertTimeZone() {
     
        assertEquals("+0200", DvDateTimeParser.convertTimeZone(7200000, false));
        assertEquals("-00:30", DvDateTimeParser.convertTimeZone(-1800000, true));
        assertEquals("Z", DvDateTimeParser.convertTimeZone(0, false));
    }


    /**
     * Test of convertTime method, of class org.openehr.rm.datatypes.quantity.datetime.DvDateTimeParser.
     */
    public void testConvertTime() {        
        TimeZone timezone = TimeZone.getTimeZone("GMT+04");
        
        assertNotNull(DvDateTimeParser.convertTime(0, 0, 0, 0, null));
        assertNotNull(DvDateTimeParser.convertTime(2, 16, 35, 0, timezone));
        DateTime dt = null;
        try {
            dt = DvDateTimeParser.convertTime(2, 16, 35, 1.24, timezone);
        } catch (Exception e) {
            
        }
        assertNull(dt);//TODO: do try catch
    }

    /**
     * Test of convertDate method, of class org.openehr.rm.datatypes.quantity.datetime.DvDateTimeParser.
     */
    public void testConvertDate() {
        
        int year = 1900;
        int month = 1;
        int day = 2;
        TimeZone timezone = null;
        
        //DateTime expResult = null;
        DateTime result = DvDateTimeParser.convertDate(year, month, day);
        assertNotNull(result);
        
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(DvDateTimeParserTest.class);
        
        return suite;
    }

    /**
     * Test of convertDateTime method, of class org.openehr.rm.datatypes.quantity.datetime.DvDateTimeParser.
     */
    public void testConvertDateTime() {
        
        int year = 1000;
        int month = 1;
        int day = 1;
        int hour = 0;
        int mintue = 0;
        int second = 0;
        double fractSec = 0.0;
        TimeZone tzone = null;
        
        DateTime expResult = null;
        DateTime result = DvDateTimeParser.convertDateTime(year, month, day, hour, mintue, second, fractSec, tzone);
        assertNotNull(result);
                
    }

    /**
     * Test of defaultTime method, of class org.openehr.rm.datatypes.quantity.datetime.DvDateTimeParser.
     */
    public void testDefaultTime() {
        
        DateTime expResult = null;
        DateTime result = DvDateTimeParser.defaultTime();
        assertNotNull(result);
    }

    /**
     * Test of defaultDate method, of class org.openehr.rm.datatypes.quantity.datetime.DvDateTimeParser.
     */
    public void testDefaultDate() {
        
        DateTime expResult = null;
        DateTime result = DvDateTimeParser.defaultDate();
        assertNotNull(result);
       
    }

    /**
     * Test of defaultDateTime method, of class org.openehr.rm.datatypes.quantity.datetime.DvDateTimeParser.
     */
    public void testDefaultDateTime() {
        
        DateTime expResult = null;
        DateTime result = DvDateTimeParser.defaultDateTime();
        assertNotNull(result);
        
    }
        
    /**
     * Test of toTimeString method, 
     * of class org.openehr.rm.datatypes.quantity.datetime.DvDateTimeParser.
     */
    public void testToTimeString() {
        
        //int[] timeElems = {1, 23, 7, 12};
        TimeZone timezone = TimeZone.getTimeZone("GMT+04");
        DateTime time = new DateTime(1343, 3, 6 , 20, 22, 19, 0, DateTimeZone.forTimeZone(timezone));
        assertEquals("20:22:19.000+04:00", DvDateTimeParser.toTimeString(time, "HH:mm:ss.SSSZZ"));
        assertEquals("20:22+04:00", DvDateTimeParser.toTimeString(time, "HH:mmZZ"));
        assertEquals("20:22+04:00", DvDateTimeParser.toTimeString(time, "19:19Z"));
        assertEquals("20:22:19,000", DvDateTimeParser.toTimeString(time, "19:19:00,010"));
		// If the default timezone is London, say, then the following datetime will be
		// 2006-06-30T20:59:00.000+01:00, because at that point of datetime, the timezone offset
		// value will be +0100. In Joda, timezone and tzoffset value are different. tzOffset value 
		// always depends on a datetime and zone.
        time = new DateTime(1970, 2 , 1 , 20, 22, 19, 0); //constructor without timezone
        //no timeZone means default zone
        assertEquals("202219.000", DvDateTimeParser.toTimeString(time, "HHmmss.SSS"));
        assertEquals("2022", DvDateTimeParser.toTimeString(time, "HHmm"));
        assertEquals("2022" + getZoneString(time), DvDateTimeParser.toTimeString(time, "1000Z"));
        
        timezone = TimeZone.getTimeZone("GMT-02");
        time = new DateTime(1970, 4 , 1 , 12, 25, 29, 235, DateTimeZone.forTimeZone(timezone));
        assertEquals("122529.235-0200", DvDateTimeParser.toTimeString(time, "HHmmss.SSSZ")); 
        assertEquals("12-0200", DvDateTimeParser.toTimeString(time, "HHZ")); 
        assertEquals("12-02:00", DvDateTimeParser.toTimeString(time, "01-01:00")); 
        timezone = TimeZone.getTimeZone("UTC");
        time = new DateTime(1970, 4 , 1 , 12, 25, 29, 235, DateTimeZone.forTimeZone(timezone));
        //assertEquals("122529.235Z", DvDateTimeParser.toTimeString(time, "HHmmss.SSSZ"));
        assertEquals("12:25:29.235Z", DvDateTimeParser.toTimeString(time, "HH:mm:ss.SSSZZ"));
        assertEquals("12Z", DvDateTimeParser.toTimeString(time, "HHZZ"));
        assertEquals("12Z", DvDateTimeParser.toTimeString(time, "01+01:00"));
    }
    
    /**
     * Test of toDateString method, 
     * of class org.openehr.rm.datatypes.quantity.datetime.DvDateTimeParser.
     */
    public void testToDateString() {
        
        //int[] timeElems = {1, 23, 7, 12};
        DateTime time = new DateTime(1343, 3, 6 , 0, 22, 19, 0);
        assertEquals("1343-03-06", DvDateTimeParser.toDateString(time, "yyyy-MM-dd"));
        assertEquals("13430306", DvDateTimeParser.toDateString(time, "yyyyMMdd"));
        assertEquals("13430306", DvDateTimeParser.toDateString(time, "10000311"));
        time = new DateTime(1970, 2 , 1 , 20, 22, 19, 0);
        //no timeZone means default zone
        assertEquals("1970-02", DvDateTimeParser.toDateString(time, "yyyy-MM"));
        assertEquals("197002", DvDateTimeParser.toDateString(time, "yyyyMM"));
        assertEquals("197002", DvDateTimeParser.toDateString(time, "196911"));
        time = new DateTime(1960, 4 , 1 , 12, 25, 29, 235);
        assertEquals("1960", DvDateTimeParser.toDateString(time, "yyyy")); 
        assertEquals("1960", DvDateTimeParser.toDateString(time, "2000"));  
    }
    
    /**
     * Test of toDateTimeString method
     *
     */
    public void testToDateTimeString() {
        TimeZone timezone = TimeZone.getTimeZone("UTC");
        DateTime time = new DateTime(1343, 3, 6 , 0, 22, 19, 0, DateTimeZone.forTimeZone(timezone));
        assertEquals("1343-03-06T00:22:19.000Z", DvDateTimeParser.toDateTimeString(time, "yyyy-MM-dd'T'HH:mm:ss.SSSZZ"));
        assertEquals("13430306T002219.000", DvDateTimeParser.toDateTimeString(time, "yyyyMMdd'T'HHmmss.SSS"));
        assertEquals("13430306T002219,000Z", DvDateTimeParser.toDateTimeString(time, "20000101T122010,000-0100"));
        assertEquals("13430306T0022", DvDateTimeParser.toDateTimeString(time, "yyyyMMdd'T'HHmm"));
        assertEquals("1343-03-06T00", DvDateTimeParser.toDateTimeString(time, "yyyy-MM-dd'T'HH"));
        assertEquals("1343-03T00:22", DvDateTimeParser.toDateTimeString(time, "yyyy-MM'T'HH:mm"));
        assertEquals("134303T00:22:19", DvDateTimeParser.toDateTimeString(time, "100003T11:00:23"));
        time = ISODateTimeFormat.dateTimeParser().withOffsetParsed().parseDateTime("1997-09-01T19:09:29.789");
        //no timeZone means default zone
        //String ret = DvDateTimeParser.toDateTimeString(time, "yyyyMMdd'T'HHmmss,SSSZ");
        //String exp = "19970901T190929,789" + getZoneString(time);
        
        assertEquals("19970901T190929,789" + getZoneString(time), DvDateTimeParser.toDateTimeString(time, "yyyyMMdd'T'HHmmss,SSSZ"));
        assertEquals("19970901T1909", DvDateTimeParser.toDateTimeString(time, "20000101T1922"));
        assertEquals("1997-09T19:09", DvDateTimeParser.toDateString(time, "yyyy-MM'T'HH:mm"));

    }
 
   
    /**
     * Test of concatenateTimeElems method, 
     * of class org.openehr.rm.datatypes.quantity.datetime.DvDateTimeParser.
     */
    public void testAddExtension() {
        
        String pattern = "HHmmss.SSS";
        String result = DvDateTimeParser.addExtension(pattern);
        assertEquals("HH:mm:ss.SSS", result);
        assertEquals("HH", DvDateTimeParser.addExtension("HH"));
        assertEquals("HH:mm", DvDateTimeParser.addExtension("HHmm"));
        assertEquals("yyyy", DvDateTimeParser.addExtension("yyyy"));
        assertEquals("yyyy-MM", DvDateTimeParser.addExtension("yyyyMM"));
        assertEquals("yyyy-MM-dd", DvDateTimeParser.addExtension("yyyyMMdd"));
        assertEquals("yyyyTHH", DvDateTimeParser.addExtension("yyyyTHH"));
        assertEquals("yyyyTHH:mm", DvDateTimeParser.addExtension("yyyyTHHmm"));
        assertEquals("yyyy-MMTHH:mm", DvDateTimeParser.addExtension("yyyyMMTHHmm"));
  
    }
  
    /**
     * Test of concatenateTimeElems method, 
     * of class org.openehr.rm.datatypes.quantity.datetime.DvDateTimeParser.
     */
    public void testAnalyseTimeString() {
       
        assertEquals(4, DvDateTimeParser.analyseTimeString("13:29:40.345Z"));
        assertEquals(3, DvDateTimeParser.analyseTimeString("23:29:00"));
        assertEquals(2, DvDateTimeParser.analyseTimeString("23:29+09:00"));
        assertEquals(1, DvDateTimeParser.analyseTimeString("23"));       
        assertEquals(4, DvDateTimeParser.analyseTimeString("232900.809-0230"));       
        assertEquals(3, DvDateTimeParser.analyseTimeString("132940"));
        assertEquals(2, DvDateTimeParser.analyseTimeString("2329-0230")); 
        assertEquals(1, DvDateTimeParser.analyseTimeString("23+0100"));  
    }
    
    /**
     * Test of concatenateTimeElems method, 
     * of class org.openehr.rm.datatypes.quantity.datetime.DvDateTimeParser.
     */
    public void testAnalyseDateString() {
       
        assertEquals(3, DvDateTimeParser.analyseDateString("1990-07-23"));
        assertEquals(3, DvDateTimeParser.analyseDateString("1990-07-02"));
        assertEquals(2, DvDateTimeParser.analyseDateString("1990-07"));
        assertEquals(1, DvDateTimeParser.analyseDateString("1990"));     
        assertEquals(3, DvDateTimeParser.analyseDateString("20890912"));       
        assertEquals(2, DvDateTimeParser.analyseDateString("208909"));
        assertEquals(1, DvDateTimeParser.analyseDateString("2089")); 
    }
    
    /**
     * Test of basicToExtendedTime method, 
     * of class org.openehr.rm.datatypes.quantity.datetime.DvDateTimeParser.
     */
    public void testBasicToExtendedTime() {

        assertEquals("23:29:00.809-02:30", DvDateTimeParser.basicToExtendedTime("232900.809-0230"));
        assertEquals("22:22:10-10:30", DvDateTimeParser.basicToExtendedTime("222210-1030"));
        assertEquals("12:11Z", DvDateTimeParser.basicToExtendedTime("1211Z"));
        assertEquals("23+01:30", DvDateTimeParser.basicToExtendedTime("23+0130"));
        assertEquals("10Z", DvDateTimeParser.basicToExtendedTime("10Z"));
        assertEquals("10", DvDateTimeParser.basicToExtendedTime("10"));
        assertEquals("10:30", DvDateTimeParser.basicToExtendedTime("1030"));
        assertEquals("15:25:44", DvDateTimeParser.basicToExtendedTime("152544"));
        assertEquals("15:25:44,678", DvDateTimeParser.basicToExtendedTime("152544,678"));
    }
    
    /**
     * Test of basicToExtendedDate method, 
     * of class org.openehr.rm.datatypes.quantity.datetime.DvDateTimeParser.
     */
    public void testBasicToExtendedDate() {
       
        assertEquals("1999", DvDateTimeParser.basicToExtendedDate("1999"));
        assertEquals("2222-10", DvDateTimeParser.basicToExtendedDate("222210"));
        assertEquals("1700-09-01", DvDateTimeParser.basicToExtendedDate("17000901"));

    }
    
    public void testBasicToTextendedDateTime() {
        
        assertEquals("1999-09-09T21", DvDateTimeParser.basicToExtendedDateTime("19990909T21"));
        assertEquals("2002-01-05T03:36", DvDateTimeParser.basicToExtendedDateTime("20020105T0336"));
        assertEquals("2012-11-16T05:27Z", DvDateTimeParser.basicToExtendedDateTime("2012-11-16T05:27Z"));
        assertEquals("1626-08-31T02-01:00", DvDateTimeParser.basicToExtendedDateTime("16260831T02-0100"));
        assertEquals("1772-02-29T23", DvDateTimeParser.basicToExtendedDateTime("1772-02-29T23"));
    }
    
	String getZoneString(DateTime dt) {
		return DvDateTimeParser.convertTimeZone(DateTimeZone.getDefault().getOffset(dt), false);
	}
	
    private String defZoneStr;
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
 *  The Original Code is DvDateTimeParserTest.java
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
