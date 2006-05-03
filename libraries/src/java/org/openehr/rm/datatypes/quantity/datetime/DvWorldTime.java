/**
 * 
 */
package org.openehr.rm.datatypes.quantity.datetime;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.joda.time.DateTime;
import org.joda.time.format.FormatUtils;
import org.openehr.rm.datatypes.quantity.DvCustomaryQuantity;
import org.openehr.rm.datatypes.quantity.DvInterval;
import org.openehr.rm.datatypes.quantity.DvOrdered;
import org.openehr.rm.datatypes.quantity.ReferenceRange;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Abstract concept of time on the real world timeline.
 * All dates assumed to be in the Gregorian calendar.
 *
 * @author Rong Chen
 * @version 1.0
 */
public abstract class DvWorldTime <T extends DvWorldTime>
        extends DvCustomaryQuantity<T> {

    protected DvWorldTime(List<ReferenceRange<T>> referenceRanges,
			DvInterval<T> normalRange, double accuracy, 
			boolean accuracyPercent, DateTime datetime) {
		super(referenceRanges, normalRange, accuracy, accuracyPercent);
		this.dateTime = datetime;
    }
    
	/**
     * Construct a WorldTime string value
     *
     * @param referenceRanges null if not specified
     * @param normalReference
     * @param accuracy        0 if not specified
     * @param accuracyPercent
     * @param value
     * @param pattern
     * @throws IllegalArgumentException
     */
    public DvWorldTime(List<ReferenceRange<T>> referenceRanges,
    				DvInterval<T> normalRange,double accuracy, 
    				boolean accuracyPercent, String value) {
        super(referenceRanges, normalRange, accuracy, accuracyPercent);
        this.dateTime = parseValue(value);
        this.value = value;
    }

	/**
     * Construct a WorldTime by current dateTime & timezone
     *
     * @param referenceRanges null if not specified
     * @param normalReference
     * @param accuracy        0 if not specified
     * @param accuracyPercent
     * @param value
     * @param pattern
     * @throws IllegalArgumentException
     */
    public DvWorldTime(List<ReferenceRange<T>> referenceRanges,
    				DvInterval<T> normalRange, double accuracy, 
    				boolean accuracyPercent) {
        super(referenceRanges, normalRange, accuracy, accuracyPercent);
        this.dateTime = new DateTime();
    }
    
    abstract DateTime parseValue(String value);

    /**
     * Construct a WorldTime by an org.joda.time.DateTime
     *
     * @param date
     * @throws IllegalArgumentException
     */
 /*   protected NDvWorldTime(DateTime datetime) {
    		this(null, null, 0, false, datetime);
    }*/
 
    public DateTime getDateTime() {
		return dateTime;
    }
    
    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * @param o the Object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     *         is less than, equal to, or greater than the specified object.
     * @throws ClassCastException if the specified object's type prevents it
     *                            from being compared to this Object.
     */
    public int compareTo(DvOrdered o) {
        DvWorldTime d = (DvWorldTime) o;
        return getDateTime().compareTo(d.getDateTime());
    }

    /**
     * Type of quantity which can be added or subtracted to this
     * quantity. Usually the same type, but may be different as in
     * the case of dates and times.
     *
     * @return diff type
     */
    public Class<DvDuration> getDiffType() {
        return DvDuration.class;
    }

    /**
     * Return a hashcode of this DvDate
     *
     * @return hashcode
     */
    public int hashCode() {
        return dateTime.hashCode();
    }
    
    /**
     * Two DvDate equal if both have same year, month, day and timezone
     *
     * @param o
     * @return true if equals
     */
    public boolean equals(Object o) {
        //if (this == o) return true;
        //if (!( o instanceof NDvWorldTime )) return false;
        
        DvWorldTime wt = (DvWorldTime) o;
		return this.dateTime.equals(wt.dateTime);
    }
   
    public String toString() {
    		return value;
    }
    
/*    private DvDuration timeZone() {  		
    		int offset = dateTime.getZone().getOffset(dateTime.getMillis())/60000; //timezone in minutes
    		int minute = offset % 60;
    		int hour = offset/60;
		timezone = new DvDuration(0, 0, 0, hour, minute, 0, 0);
	}*/

    protected static String convertTimeZone(int tzMillis) {
    		int offset = tzMillis/60000; //timezone in minutes		
		int hour = offset/60;
		int minute = Math.abs(offset % 60);
		StringBuffer sb = new StringBuffer();
		FormatUtils.appendPaddedInteger(sb, hour, 2);
		FormatUtils.appendPaddedInteger(sb, minute, 2);
		String result = sb.toString();
		return tzMillis < 0 ? "-" + result : "+" + result;
    }
    
    // POJO start
    void setDateTime(DateTime dateTime) {
    		this.dateTime = dateTime;
    }
    
	protected static boolean isExtended(String value) {
		return value.indexOf("-") > 0 || value.indexOf(":") > 0;
	}
	
	
    // POJO end

    /* fields */
    //private DvDuration timezone;
    private DateTime dateTime;
    private String value;
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
 *  The Original Code is NDvWorldTime.java
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

