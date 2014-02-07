/*
 * component:   "openEHR Reference Implementation"
 * description: "Class DvTemporal"
 * keywords:    "datatypes"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * copyright:   "Copyright (c) 2007 Cambio Healthcare Systems, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL$"
 * revision:    "$LastChangedRevision$"
 * last_change: "$LastChangedDate$"
 */
package org.openehr.rm.datatypes.quantity.datetime;

import java.util.List;

import org.joda.time.DateTime;
import org.openehr.rm.datatypes.quantity.DvAbsoluteQuantity;
import org.openehr.rm.datatypes.quantity.DvInterval;
import org.openehr.rm.datatypes.quantity.DvOrdered;
import org.openehr.rm.datatypes.quantity.ReferenceRange;
import org.openehr.rm.datatypes.text.CodePhrase;

/**
 * Specialised temporal variant of DV_ABSOLUTE_QUANTITY whose diff type is
 * DV_DURATION.
 * 
 * @author Rong Chen
 */
public abstract class DvTemporal<T extends DvTemporal> extends
		DvAbsoluteQuantity<T, DvDuration> {

	/**
	 * Construct a WorldTime string value
	 * 
	 * @param otherReferenceRanges
	 *            null if not specified
	 * @param normalReference
	 * @param accuracy
	 *            0 if not specified
	 * @param accuracyPercent
	 * @param value
	 * @param pattern
	 * @throws IllegalArgumentException
	 */
	public DvTemporal(List<ReferenceRange<T>> otherReferenceRanges,
			DvInterval<T> normalRange, CodePhrase normalStatus,
			DvDuration accuracy, String magnitudeStatus, String value) {

		super(otherReferenceRanges, normalRange, normalStatus, accuracy,
				magnitudeStatus);

		this.dateTime = parseStringValue(value);
		this.value = value;
		setBooleans(value);
	}
	
	protected DvTemporal(List<ReferenceRange<T>> referenceRanges,
            DvInterval<T> normalRange, CodePhrase normalStatus,
            DvDuration accuracy, String magnitudeStatus, DateTime datetime) {
        
        super(referenceRanges, normalRange, normalStatus, accuracy, 
                magnitudeStatus);
        this.dateTime = datetime;
    }
	
	/**
	 * Creates a DvTemporal only with datetime
	 * 
	 * @param datetime not null
	 */
	protected DvTemporal(DateTime datetime) {
		this(null, null, null, null, null, datetime);
	}

	@Override
	public DvDuration diff(T other) {
		return DvDuration.getDifference(this, other);
	}

	abstract DateTime parseStringValue(String value);

	abstract void setBooleans(String value);

	public DateTime getDateTime() {
		return dateTime;
	}

	/**
	 * Compares this object with the specified object for order. Returns a
	 * negative integer, zero, or a positive integer as this object is less
	 * than, equal to, or greater than the specified object.
	 * 
	 * @param o
	 *            the Object to be compared.
	 * @return a negative integer, zero, or a positive integer as this object is
	 *         less than, equal to, or greater than the specified object.
	 * @throws ClassCastException
	 *             if the specified object's type prevents it from being
	 *             compared to this Object.
	 */
	public int compareTo(DvOrdered o) {
		DvTemporal d = (DvTemporal) o;
		return getDateTime().compareTo(d.getDateTime());
	}

	/**
	 * Type of quantity which can be added or subtracted to this quantity.
	 * Usually the same type, but may be different as in the case of dates and
	 * times.
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
	 * Two DvTemporal are equivalent if both indicate the same point of
	 * datetime
	 * 
	 * 
	 * @param o
	 * @return true if equals
	 */
	public boolean isEquivalent(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof DvTemporal))
			return false;

		final DvTemporal wt = (DvTemporal) o;
		return this.dateTime.isEqual(wt.dateTime);
	}

	/**
	 * Two DvTemporal are equal if both indicate the same point of
	 * datetime. This function should be overwritten in subclass.
	 * 
	 * @param o
	 * @return true if equals
	 */
	public boolean equals(Object o) {
		return isEquivalent(o);
	}

	/**
	 * Gets string presentation of this object
	 * 
	 * @return string
	 */
	public String toString() {
		return value;
	}
	
	/**
	 * Gets the string value
	 * 
	 * @return value
	 */
	public String getValue() {
		return value;
	}

	protected String timeZoneString() {
		int offset = dateTime.getZone().getOffset(dateTime.getMillis()) / 60000; // timezone
		// in
		// minutes
		int minute = offset % 60 >= 0 ? offset % 60 : -(offset % 60);
		int hour = offset / 60;
		if (minute == 0 && hour == 0) {
			return "Z";
		} else {
			String mStr = Integer.toString(minute);
			mStr = mStr.length() > 1 ? mStr : "0" + mStr;
			String hStr = hour > 0 ? "+" + Integer.toString(hour) : Integer
					.toString(hour);
			if (hStr.length() < 3) {
				hStr = hStr.substring(0, 1) + "0" + hStr.substring(1);
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
	private DateTime dateTime;
	private String value;
}
/*
 * ***** BEGIN LICENSE BLOCK ***** Version: MPL 1.1/GPL 2.0/LGPL 2.1
 * 
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the 'License'); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at http://www.mozilla.org/MPL/
 * 
 * Software distributed under the License is distributed on an 'AS IS' basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 * 
 * The Original Code is DvTemporal.java
 * 
 * The Initial Developer of the Original Code is Rong Chen. Portions created by
 * the Initial Developer are Copyright (C) 2007 the Initial Developer. All
 * Rights Reserved.
 * 
 * Contributor(s): Yin Su Lim
 * 
 * Software distributed under the License is distributed on an 'AS IS' basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 * 
 * ***** END LICENSE BLOCK *****
 */