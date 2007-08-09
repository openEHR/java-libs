/*
 * component:   "openEHR Reference Implementation"
 * description: "Class DvWorldTime"
 * keywords:    "datatypes"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/datatypes/quantity/datetime/DvWorldTime.java $"
 * revision:    "$LastChangedRevision: 53 $"
 * last_change: "$LastChangedDate: 2006-08-11 13:20:08 +0100 (Fri, 11 Aug 2006) $"
 */
package org.openehr.rm.datatypes.quantity.datetime;

import org.joda.time.DateTime;
import org.openehr.rm.datatypes.quantity.DvAbsoluteQuantity;
import org.openehr.rm.datatypes.quantity.DvAmount;
import org.openehr.rm.datatypes.quantity.DvInterval;
import org.openehr.rm.datatypes.quantity.DvOrdered;
import org.openehr.rm.datatypes.quantity.ReferenceRange;
import org.openehr.rm.datatypes.text.CodePhrase;

import java.util.List;

/**
 * Abstract concept of time on the real world timeline.
 * All dates assumed to be in the Gregorian calendar.
 *
 * @author Rong Chen
 * @version 1.0
 */
public abstract class DvWorldDateTime <T extends DvWorldDateTime>
        extends DvAbsoluteQuantity<T, DvDuration> {

    protected DvWorldDateTime(List<ReferenceRange<T>> referenceRanges,
			DvInterval<T> normalRange, CodePhrase normalStatus,
			double accuracy, String magnitudeStatus, DateTime datetime) {
    	
        super(referenceRanges, normalRange, normalStatus, accuracy, 
        		magnitudeStatus);
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
    public DvWorldDateTime(List<ReferenceRange<T>> referenceRanges,
    				DvInterval<T> normalRange, CodePhrase normalStatus,
    				double accuracy, String magnitudeStatus, String value) {
        super(referenceRanges, normalRange, normalStatus, accuracy, 
        		magnitudeStatus);
        this.dateTime = parseValue(value);
        this.value = value;
        setBooleans(value);
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
/*    public DvWorldDateTime(List<ReferenceRange<T>> referenceRanges,
    				DvInterval<T> normalRange, double accuracy, 
    				boolean accuracyPercent) {
        super(referenceRanges, normalRange, accuracy, accuracyPercent);
        this.dateTime = new DateTime();
    }*/
    
    abstract DateTime parseValue(String value);

    abstract void setBooleans(String value);
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
        DvWorldDateTime d = (DvWorldDateTime) o;
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
     * Two DvWorldDateTime are equivalent if both indicate the same point of datetime
     * 
     * 
     * @param o
     * @return true if equals
     */
    public boolean isEquivalent(Object o) {
        if (this == o) return true;
        if (!( o instanceof DvWorldDateTime )) return false;
        
        final DvWorldDateTime wt = (DvWorldDateTime) o;
		return this.dateTime.isEqual(wt.dateTime);
    }
   
    /**
     * Two DvWorldDateTime are equal if both indicate the same point of datetime.
     * This function should be overwritten in subclass.
     * 
     * @param o
     * @return true if equals
     */
    public boolean equals(Object o) {
        return isEquivalent(o);
    }
 
    
    public String toString() {
        //return value == null? getDateTime().toString() : value;
        return value;
    }
    
/*    private DvDuration timeZone() {  		
    		int offset = dateTime.getZone().getOffset(dateTime.getMillis())/60000; //timezone in minutes
    		int minute = offset % 60;
    		int hour = offset/60;
		timezone = new DvDuration(0, 0, 0, hour, minute, 0, 0);
	}*/

    protected String timeZoneString() {
        int offset = dateTime.getZone().getOffset(dateTime.getMillis())/60000; //timezone in minutes
        int minute = offset % 60 >= 0? offset % 60 : -(offset % 60);
        int hour = offset/60;
        if(minute == 0 && hour == 0) {
            return "Z";
        }else {
            String mStr = Integer.toString(minute);
            mStr = mStr.length() > 1? mStr : "0" + mStr;
            String hStr = hour > 0? "+" + Integer.toString(hour) : Integer.toString(hour);
            if(hStr.length() < 3) {
                hStr = hStr.substring(0,1) + "0" + hStr.substring(1);
            }
            return hStr + mStr; 
        }
    }
    

    
    // POJO start
    void setDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
    }
    
    void setValue(String value) {
        this.value = value;
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
 *  The Original Code is DvWorldTime.java
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

