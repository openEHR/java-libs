/*
 * component:   "openEHR Reference Implementation"
 * description: "Class DvDateTimeParser"
 * keywords:    "datatypes"
 *
 * author:      "Yin Su Lim <y.lim@chime.ucl.ac.uk>"
 * support:     "CHIME, UCL"
 * copyright:   "Copyright (c) 2006 UCL, UK"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/datatypes/quantity/datetime/DvDateTimeParser.java $"
 * revision:    "$LastChangedRevision: 53 $"
 * last_change: "$LastChangedDate: 2006-08-11 13:20:08 +0100 (Fri, 11 Aug 2006) $"
 */
package org.openehr.rm.datatypes.quantity.datetime;

import java.lang.IllegalArgumentException;
import java.util.TimeZone;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.joda.time.format.FormatUtils;
import org.joda.time.format.ISODateTimeFormat;
/**
 *
 * Parser class to parse parameters given to the constructors of DvDate, DvTime and DvDateTime
 *
 * @author Yin Su Lim
 * @version 1.0
 */
public class DvDateTimeParser {
  
    /* static field */
    //For parsing time element
    static final DateTimeFormatter timeFormatter = ISODateTimeFormat.basicTime().withOffsetParsed();
    static final DateTimeFormatter eTimeParser = ISODateTimeFormat.timeParser().withOffsetParsed(); //extended
    //static final DateTimeFormatter eTimePrinter = ISODateTimeFormat.time(); //extended printer
    private static final String EXT_TIME =
            "(\\d){2}(:(\\d){2}(:(\\d){2}((,|\\.)(\\d){1,})?)?)?(Z|(\\+|-)(\\d){2}((:)?(\\d){2})?)?";
    private static final String BTIME_COMPLETE = "(\\d){6}((,|\\.)(\\d){1,})?(Z|(\\+|-)(\\d){2,4})?";
    private static final String TIME_PARTIAL = "((\\d){2}|(\\d){4})(Z|(\\+|-)(\\d){2,4})?";
    private static final String EXT_DATE = "(\\d){4}(-(\\d){2}(-(\\d){2})?)?";
    private static final String BDATE = "(\\d){4}((\\d){2}((\\d){2})?)?";
    private static final String BDATETIME = "(\\d){8}T((\\d){2}|(\\d){4}|(\\d){6}((,|\\.)(\\d){1,})?)(Z|(\\+|-)(\\d){2,4})?";
    private static final String EXT_DATETIME = 
       "(\\d){4}-(\\d){2}-(\\d){2}T(\\d){2}(:(\\d){2}(:(\\d){2}((,|\\.)(\\d){1,})?)?)?(Z|(\\+|-)(\\d){2}((:)?(\\d){2})?)?";
    //For pasing date element
    
    
    /** Creates a new instance of DvDateTimeParser */
    public DvDateTimeParser() {
    }
    
    public static DateTime parseTime(String value) {
        if(value == null) {
            throw new IllegalArgumentException("null value");
        }
        DateTime dt = null;
        if (value.matches(EXT_TIME)) {
            try { 
                //dealing with extended format(complete and partial)               
                dt = eTimeParser.parseDateTime(value);
            } catch (Exception e) {
                throw new IllegalArgumentException("invalid value for time in extended format: " + value);
            }
        } else if(value.matches(BTIME_COMPLETE)) {
            value = value.replace(",", ".");
            try {
                dt = timeFormatter.parseDateTime(padTimeValue(value));
            } catch (Exception e) {
                throw new IllegalArgumentException("invalid value for time in basic format: " + value);
            }
        } else if(value.matches(TIME_PARTIAL)) {
            DateTimeFormatter partial = null;
            int zonePosition = tZonePresent(value);            
            if(zonePosition > 0) {
               String timeElem = value.substring(0, zonePosition);
               if(timeElem.length() > 2) {
                   partial = new DateTimeFormatterBuilder().appendHourOfDay(2)
                            .appendMinuteOfHour(2).appendTimeZoneOffset("Z", false, 1, 2)
                            .toFormatter().withOffsetParsed();
               } else {
                   partial = new DateTimeFormatterBuilder().appendHourOfDay(2)
                            .appendTimeZoneOffset("Z", false, 1, 2).toFormatter().withOffsetParsed(); 
               }
            } else {
                if(value.length() > 2) {
                   partial = new DateTimeFormatterBuilder().appendHourOfDay(2)
                            .appendMinuteOfHour(2).toFormatter().withOffsetParsed(); 
                } else {
                   partial = new DateTimeFormatterBuilder().appendHourOfDay(2)
                            .toFormatter().withOffsetParsed();
                }
            }
            try {
                dt = partial.parseDateTime(value);
            } catch (Exception e) {
                throw new IllegalArgumentException("invalid partial time value in basic format: " + value);
            }
        } else {
            throw new IllegalArgumentException("invalid format for time: " + value);
        }

        return dt;
    }
 
    public static DateTime parseDate(String value) {
        if(value == null) {
            throw new IllegalArgumentException("null value for date");
        }
        if (!value.matches(EXT_DATE) && !value.matches(BDATE)) {
            throw new IllegalArgumentException("invalid pattern for date: " + value);
        }
        DateTime dt = null;
        if (value.indexOf("-") > 0 || value.length() == 4) {
            try {
                dt = ISODateTimeFormat.dateElementParser().withOffsetParsed().parseDateTime(value);
            } catch (Exception e) {
                throw new IllegalArgumentException("invalid value for extended date: " + value);
            }
        } else {
            int size = analyseDateString(value);
            DateTimeFormatter formatter = null;
            switch (size) {
                case 1: formatter = ISODateTimeFormat.year().withOffsetParsed(); break;
                case 2: formatter = DateTimeFormat.forPattern("yyyyMM").withOffsetParsed(); break;
                case 3: formatter = ISODateTimeFormat.basicDate().withOffsetParsed();break;
            }
            try {
                dt = formatter.parseDateTime(value);
            } catch (Exception e) {
                throw new IllegalArgumentException("invalid value for basic date: " + value);
            }
        }
        return dt;
    }
    
    public static DateTime parseDateTime(String value) {
        if(value == null) {
            throw new IllegalArgumentException("null value for datetime");
        }
        if (!value.matches(EXT_DATETIME) && !value.matches(BDATETIME)) {
            throw new IllegalArgumentException("invalid pattern for datetime: " + value);
        }
        DateTime dt = null;
        if(value.indexOf("-") > 0) {
            try {
                dt = ISODateTimeFormat.dateTimeParser().withOffsetParsed().parseDateTime(value);
            } catch (Exception e) {
                throw new IllegalArgumentException("invalid value for datetime: " + value);
            }
        } else {  
            value = value.replace(".", ",");
            int tEleSize = analyseTimeString(value.substring(9));
            String pattern = "";
            switch(tEleSize) {
                case 1: pattern = "yyyyMMdd'T'HH"; break;
                case 2: pattern = "yyyyMMdd'T'HHmm"; break;
                case 3: pattern = "yyyyMMdd'T'HHmmss"; break;
                case 4: pattern = "yyyyMMdd'T'HHmmss,SSSSSSSSS"; break;
            }
            if(tZonePresent(value) > 0) {
                pattern = pattern + "Z";
            }
            dt = DateTimeFormat.forPattern(pattern).withOffsetParsed().parseDateTime(value);
        }
        return dt;
    }
    
    private static int tZonePresent(String value) {
        if(value == null) {
            return -1;
        } else {           
            if(value.indexOf("Z") > 0 || value.indexOf("+") > 0 ||
               value.indexOf("-") > 0 ) {
                String zonePatt = "(Z|\\+|-)";
                String[] splitStr = value.split(zonePatt);
                return splitStr[0].length();
            }
            return -1;
        }
    }
    
   /**
     * Pad value time string with 000 (fractional seconds) and/or timezone, 
     * before passing the value to the Joda time format parser. 
     * The Joda basicTime formatter can only parse string in HHmmss.SSS(Z|+/-HHmm)
     * format. All element must be present.
     * 
     * @param value
     * @return
     */
    static String padTimeValue(String value) {
        
        //value = value.replace(",", ".");
//    	check if there's timezone element?...        
        int zonePst = tZonePresent(value);

        if ( zonePst > 0 ) {
            //check if fractional sec is present
            if (zonePst <= 6) {
                value = value.substring(0, zonePst) + ".000" + value.substring(zonePst);
            }
        } else {
            String zone = convertDefaultTimeZone(false);
            if (value.length() <= 6) {
                value = value + ".000" + zone;
            } else {
                value = value + zone;
            }
        }
        return value;
    }
 
    static String convertDefaultTimeZone(boolean isExtended) {
    		DateTime dt = new DateTime();
    		return convertTimeZone(dt.getZone().getOffset(dt), isExtended);   		
    }
    
    static String convertTimeZone(int tzMillis, boolean isExtended) {
        if(tzMillis == 0) return "Z";
        int offset = Math.abs(tzMillis)/60000; //timezone in minutes
        int hour = offset/60;
        int minute = Math.abs(offset % 60);
        StringBuffer sb = new StringBuffer();
        FormatUtils.appendPaddedInteger(sb, hour, 2);
        if(isExtended) {
            sb.append(":");
        }
        FormatUtils.appendPaddedInteger(sb, minute, 2);
        String result = sb.toString();
        return tzMillis < 0 ? "-" + result : "+" + result;
    }
        
    public static DateTime convertTime(int hour, int minute, int second, double fractSec, 
            TimeZone timezone) {
        if(fractSec > 1 || fractSec < 0) {
            throw new IllegalArgumentException("invalid fractional second");
        }
        return new DateTime(1970, 1, 1, hour, minute, second, (int)(fractSec*1000), 
                DateTimeZone.forTimeZone(timezone));
    }
    
    public static DateTime convertDate(int year, int month, int day) {
        return new DateTime(year, month, day, 0, 0, 0, 0);
        
    }
    
    public static DateTime convertDateTime(int year, int month, int day, int hour, int minute,
            int second, double fractSec, TimeZone timezone) {
        if(fractSec > 1) {
            throw new IllegalArgumentException("invalid fractional second");
        }
        return new DateTime(year, month, day, hour, minute, second, (int)(fractSec*1000), 
                DateTimeZone.forTimeZone(timezone));
    }
    
    public static DateTime defaultTime() {
        DateTime time = new DateTime();
        return new DateTime(1970, 1, 1, time.getHourOfDay(), time.getMinuteOfHour(), 
                time.getSecondOfMinute(), time.getMillisOfSecond());
    }
    
    public static DateTime defaultDate() {
        DateTime date = new DateTime();
        return new DateTime(date.getYear(), date.getMonthOfYear(), date.getDayOfMonth(),
                0, 0, 0, 0);
    }
    
    public static DateTime defaultDateTime() {
        return new DateTime();
    }
    
    /**
     * Return dateTime a valid time format
     */
    public static String toTimeString(DateTime time, String pattern) {
        if(pattern == null) {
            throw new IllegalArgumentException("null pattern for time");
        }
        if(pattern.equals("")) return "";
        String patt = null;
        if(pattern.startsWith("HH")) {
            patt = pattern;
        } else {
            boolean isExtended = pattern.indexOf(":") > 0;
            boolean zoneExist = tZonePresent(pattern) > 0;
            int formatInt = analyseTimeString(pattern);
            switch (formatInt) {
                case 1: if(zoneExist) {
                            patt = isExtended ? "HHZZ" : "HHZ";
                        } else patt = "HH";
                        break;

                case 2: if(zoneExist) {
                            patt = isExtended ? "HH:mmZZ" : "HHmmZ";
                        } else patt = isExtended ? "HH:mm" : "HHmm";
                        break;
                       
                case 3: if(zoneExist) {
                            patt = isExtended ? "HH:mm:ssZZ" : "HHmmssZ";
                        } else patt = isExtended ? "HH:mm:ss" : "HHmmss";
                        break;
                        
                case 4: if(zoneExist) {
                            patt = isExtended ? "HH:mm:ss,SSSZZ" : "HHmmss,SSSZ";
                        } else patt = isExtended ? "HH:mm:ss,SSS" : "HHmmss,SSS";
                        break;
                        
            }
        }
        String result = time.toString(patt);
        if(result.matches("((.*\\+00:00)||(.*-00:00))")) {
            result = result.substring(0, result.length()- 6) + "Z";
        } else if (result.matches("((.*\\+0000)||(.*-0000))")) {
            result = result.substring(0, result.length()- 5) + "Z";
        }
        return result;
    }
    
    /**
     * Return dateTime in a valid date format
     */
    public static String toDateString(DateTime date, String pattern) {
        if(pattern == null) {
            throw new IllegalArgumentException("null pattern for date");
        }
        String patt = null;
        if(pattern.startsWith("yyyy")) {
            patt = pattern;
        } else {
            boolean isExtended = pattern.indexOf("-") > 0;
            int formatInt = analyseDateString(pattern);
            switch (formatInt) {
                case 1:  patt = "yyyy"; break;
                case 2: patt = isExtended ? "yyyy-MM" : "yyyyMM"; break;
                case 3: patt = isExtended ? "yyyy-MM-dd" : "yyyyMMdd"; break;               
            }
        }
        return date.toString(patt);
    }

    /**
     * Return dateTime in the pattern required
     */
    public static String toDateTimeString(DateTime time, String pattern) {
        if(pattern == null) {
            throw new IllegalArgumentException("null pattern for datetime");
        }
        if(pattern.startsWith("yyyy")) {
            String result = time.toString(pattern);
            if(result.matches("((.*\\+00:00)||(.*-00:00))")) {
                result = result.substring(0, result.length()- 6) + "Z";
            } else if (result.matches("((.*\\+0000)||(.*-0000))")) {
                result = result.substring(0, result.length()- 5) + "Z";
            }
            return result;
        } else {
            int t = pattern.indexOf("T");
            if( t > 0) {
                String dateEle = toDateString(time, pattern.substring(0, t));
                String timeEle = toTimeString(time, pattern.substring(t+1));
                return dateEle + "T" + timeEle;
            } else {
                return toDateString(time, pattern);
            }            
        }
    }
    
    
    public static String addExtension(String pattern) {
        
        return pattern.replace("yM", "y-M").replace("Md", "M-d")
                .replace("Hm", "H:m").replace("ms", "m:s");
    }
        
    /**
     * Return an integer value to indicate the level of completeness of 
     * a String value that represents a valid point of time.
     * -1 means invalid string value;
     * 1 means only HH component is present; 2 means HHmm;
     * 3 for HHmmss, 4 for HHmmss.SSS
     * These result is regardless of the format of the time string
     * (whether or not it is in extended format)
     */
    public static int analyseTimeString(String value) {
        
        if(value == null || value.equals("")) {
            return -1;
        }
        String timeComp = value;
        int zonePst = tZonePresent(value);
        if(zonePst > 0) {
            timeComp = value.substring(0, zonePst);
        }
        timeComp = timeComp.replace(":", "");
        return timeComp.length()/2 > 4? 4 : timeComp.length()/2;
    }
    
    /**
     * Return an integer value to indicate the level of completeness of 
     * a String value that represents a valid date.
     * -1 means invalid string value;
     * 1 means only yyyy component is present; 2 means yyyyMM;
     * 3 for yyyyMMdd
     * These result is regardless of the format of the date string
     * (whether or not it is in extended format)
     */
    public static int analyseDateString(String value) {
        
        if(value == null || value.equals("")) {
            //System.out.println("in exception");
            return -1;
        }
        String dateComp = value;
        //int zonePst = tZonePresent(value);//TODO have a look
        //if(zonePst > 0) {
        //    dateComp = value.substring(0, zonePst);
        //}
        dateComp = dateComp.replace("-", "");
        //System.out.println("string is " + dateComp);
        return dateComp.length()/2 - 1;
    }

    public static String basicToExtendedTime(String time) {
        if(time.indexOf(":") > 0 || time.length() <= 2) {
            return time;
        }
        int zonePst = tZonePresent(time);
        StringBuffer timeAsBuffer = null;
        StringBuffer zoneAsBuffer = null;
        if(zonePst > 0) {
            timeAsBuffer = new StringBuffer(time.substring(0, zonePst));
            zoneAsBuffer = new StringBuffer(time.substring(zonePst));
        } else {
            timeAsBuffer = new StringBuffer(time);
            zoneAsBuffer = new StringBuffer("");
        }
        
        int timeElemSize = analyseTimeString(time) > 3 ? 3 : analyseTimeString(time);
        //insert ':' for time elements
        for(int i = 0; i < timeElemSize -1; i++) {
            timeAsBuffer.insert(2*(i+1) + i, ":");
        }
        //now dealing with zone
        if(zoneAsBuffer.length() > 3) {
            zoneAsBuffer.insert(3, ":");
        }
        return timeAsBuffer.append(zoneAsBuffer).toString();
    }
    
    public static String basicToExtendedDate(String date) {
        if(date.indexOf("-") > 0 || date.length() <= 4) {
            return date;
        }
        if(date.length() > 6) {
            return date.substring(0,4) + "-" + date.substring(4,6) + "-" + date.substring(6);
        } else { //(date.length() <= 6) 
            return date.substring(0,4) + "-" + date.substring(4);
        } 
    }
    
    public static String basicToExtendedDateTime(String dtime) {
        String date = dtime.substring(0, dtime.indexOf("T"));
        String time = dtime.substring(dtime.indexOf("T")+1);
        if(time.indexOf(":") < 0 && time.length() > 2) {
           time = DvDateTimeParser.basicToExtendedTime(time);                
        }
        if(date.indexOf("-") < 0) {
           date = DvDateTimeParser.basicToExtendedDate(date);                
        }
        return date + "T" + time;
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
 *  The Original Code is DvDateTimeParser.java
 *
 *  The Initial Developer of the Original Code is Yin Su Lim.
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