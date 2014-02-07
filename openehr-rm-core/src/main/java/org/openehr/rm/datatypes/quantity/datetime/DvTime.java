/*
 * component:   "openEHR Reference Implementation"
 * description: "Class DvTime"
 * keywords:    "datatypes"
 *
 * author:      "Yin Su Lim <y.lim@chime.ucl.ac.uk>"
 * support:     "CHIME, UCL"
 * copyright:   "Copyright (c) 2006 UCL, UK"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/datatypes/quantity/datetime/DvTime.java $"
 * revision:    "$LastChangedRevision: 53 $"
 * last_change: "$LastChangedDate: 2006-08-11 13:20:08 +0100 (Fri, 11 Aug 2006) $"
 */
package org.openehr.rm.datatypes.quantity.datetime;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.joda.time.DateTime;
import org.joda.time.MutableDateTime;
import org.openehr.rm.Attribute;
import org.openehr.rm.FullConstructor;
import org.openehr.rm.datatypes.quantity.DvInterval;
import org.openehr.rm.datatypes.quantity.DvOrdered;
import org.openehr.rm.datatypes.quantity.ReferenceRange;
import org.openehr.rm.datatypes.text.CodePhrase;

import java.util.List;
import java.util.TimeZone;

/**
 * Represents an absolute point in time from an origin usually
 * interpreted as meaning the start of the current day, specified to
 * the second.
 *
 * @author Rong Chen
 * @version 1.0
 *
 * Warning: Current DvTime implementation only takes 3 decimal digits for fractional second, and 
 * does not take 24 for hour!
 *
 */
public class DvTime extends DvTemporal<DvTime> {

	/**
     * 
     */
    private static final long serialVersionUID = -6203478492778373210L;
	/**
	 * Construct a DvTime
	 *
	 * @param otherReferenceRanges null if not specified
	 * @param normalRange null if not specified
	 * @param normalStatus null if not specified
	 * @param accuracy        0 if not specified
	 * @param accuracyPercent
	 * @param value
	 * @throws IllegalArgumentException
	 */
	@FullConstructor
	public DvTime(
			@Attribute(name = "otherReferenceRanges") List<ReferenceRange<DvTime>> otherReferenceRanges,
			@Attribute(name = "normalRange") DvInterval<DvTime> normalRange, 
			@Attribute(name = "normalStatus") CodePhrase normalStatus, 
			@Attribute(name = "accuracy") DvDuration accuracy, 
			@Attribute(name = "magnitudeStatus") String magnitudeStatus, 
			@Attribute(name = "value", required = true)	String value) {
		super(otherReferenceRanges, normalRange, normalStatus, accuracy,
				magnitudeStatus, value);
	}

	/**
	 * Creates a DvTime with string value
	 * 
	 * @param value
	 */
	public DvTime(String value) {
		this(null, null, null, null, null, value);
	}

	/**
	 * Constructs a DvTime
	 *
	 * @param hour
	 * @param minute
	 * @param second
	 * @param fs
	 * @param timezone null if use default timezone
	 */
	public DvTime(int hour, int minute, int second, double fs, TimeZone timezone) {
		super(DvDateTimeParser.convertTime(hour, minute, second, fs, timezone));
		String format = timezone == null ? "HH:mm:ss,SSS" : "HH:mm:ss,SSSZZ";
		setValue(DvDateTimeParser.toTimeString(getDateTime(), format));
		setBooleans(false, true, true, true);
	}

	/**
	 * Constructs a DvTime
	 *
	 * @param hour
	 * @param minute
	 * @param second
	 * @param timezone null if use default timezone
	 */
	public DvTime(int hour, int minute, int second, TimeZone timezone) {
		super(DvDateTimeParser.convertTime(hour, minute, second, 0, timezone));
		String format = timezone == null ? "HH:mm:ss" : "HH:mm:ssZZ";
		setValue(DvDateTimeParser.toTimeString(getDateTime(), format));
		setBooleans(false, true, true, false);
	}

	/**
	 * Constructs a partial DvTime
	 *
	 * @param hour
	 * @param minute
	 * @param timezone null if use default timezone
	 */
	public DvTime(int hour, int minute, TimeZone timezone) {
		super(DvDateTimeParser.convertTime(hour, minute, 0, 0, timezone));
		String format = timezone == null ? "HH:mm" : "HH:mmZZ";
		setValue(DvDateTimeParser.toTimeString(getDateTime(), format));
		setBooleans(true, true, false, false);
	}

	/**
	 * Constructs a partial DvTime
	 *
	 * @param hour
	 * @param timezone null if use default timezone
	 */
	public DvTime(int hour, TimeZone timezone) {
		super(DvDateTimeParser.convertTime(hour, 0, 0, 0, timezone));
		String format = timezone == null ? "HH" : "HHZZ";
		setValue(DvDateTimeParser.toTimeString(getDateTime(), format));
		setBooleans(true, false, false, false);
	}

	/**
	 * Constructs a DvTime with current point of time
	 * The format of time value as the result of this constructor
	 * is HH:mm:ss.SSSZZ
	 *
	 */
	public DvTime() {
		super(DvDateTimeParser.defaultTime());
		setValue(DvDateTimeParser.toTimeString(getDateTime(), "HH:mm:ss,SSSZZ"));
		setBooleans(false, true, true, true);
	}

	/**
	 * Constructs a DvTime, mainly for use in addition and subtraction
	 */
	protected DvTime(List<ReferenceRange<DvTime>> referenceRanges,
			DvInterval<DvTime> normalRange, CodePhrase normalStatus,
			DvDuration accuracy, String magnitudeStatus, DateTime datetime,
			String pattern) {
		super(referenceRanges, normalRange, normalStatus, accuracy,
				magnitudeStatus, datetime);
		setValue(DvDateTimeParser.toTimeString(getDateTime(), pattern));
		setBooleans(pattern);
	}

	@Override
	public String getReferenceModelName() {
		return "DV_TIME";
	}

	/**
	 * Hour in day
	 *
	 * @return hour in day
	 */
	public int getHour() {
		return getDateTime().getHourOfDay();
	}

	/**
	 * Minute in hour
	 *
	 * @return minute in hour, -1 if minute unknown
	 */
	public int getMinute() {
		return minuteKnown() ? getDateTime().getMinuteOfHour() : -1;
	}

	/**
	 * Second in minute
	 *
	 * @return second, -1 if second unknown
	 */
	public int getSecond() {
		return secondKnown ? getDateTime().getSecondOfMinute() : -1;
	}

	/**
	 * Fractional seconds
	 *
	 * @return fractional seconds, -0.1 if fractional second unknown
	 */
	public double getFractionalSecond() {
		return fractionalSecKnown ? getDateTime().getMillisOfSecond() / 10E2
				: -0.1;
	}

	public boolean isPartial() {
		return isPartial;
	}

	public boolean minuteKnown() {
		return minuteKnown;
	}

	public boolean secondKnown() {
		return secondKnown;
	}

	public boolean hasFractionalSecond() {
		return fractionalSecKnown;
	}

	/**
	 * If date is valid ISO8601 format
	 *
	 * @param year
	 * @param month
	 * @param day
	 * @return true if valid
	 */
	public static boolean isValidISO8601Time(String value) {
		DateTime dt = null;
		try {
			dt = DvDateTimeParser.parseTime(value);
		} catch (Exception e) {
			return false;
		}
		return dt != null;
	}

	@Override
	public Number getMagnitude() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DvTime add(DvDuration q) {
		if (!getDiffType().isInstance(q)) {
			throw new IllegalArgumentException("invalid difference type");
		}
		DvDuration d = (DvDuration) q;
		MutableDateTime mdate = getDateTime().toMutableDateTimeISO();
		mdate.add(d.getPeriod());
		return new DvTime(getOtherReferenceRanges(), getNormalRange(),
				getNormalStatus(), getAccuracy(), getMagnitudeStatus(), mdate
						.toDateTimeISO(), this.toString());
	}

	@Override
	public DvTime subtract(DvDuration q) {
		if (!getDiffType().isInstance(q)) {
			throw new IllegalArgumentException("invalid difference type");
		}		
		return add(q.negate());
	}
	
	@Override
	public boolean isStrictlyComparableTo(DvOrdered ordered) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Two DvTime equal if both have same value for hour, minute, second
	 * and timezone
	 *
	 * @param o
	 * @return true if equals
	 */
	public boolean equals(Object o) {
		if (!super.equals(o))
			return false;

		final DvTime dtime = (DvTime) o;
		//TODO: check if the following line still necessary
		//if(this.getDateTime().getZone().hashCode()!= this.getDateTime().getZone().hashCode()) {
		//    return false;
		//}

		return new EqualsBuilder().append(
				this.getDateTime().getZone().hashCode(),
				this.getDateTime().getZone().hashCode()).append(isPartial,
				dtime.isPartial).append(minuteKnown, dtime.minuteKnown).append(
				secondKnown, dtime.secondKnown).append(fractionalSecKnown,
				dtime.fractionalSecKnown).isEquals();
	}

	public String toString(boolean isExtended) {
		String time = super.toString();
		if (time == null) {
			time = "";
		} else {
			if (isExtended) {
				time = DvDateTimeParser.basicToExtendedTime(time);
			} else {
				time = time.replace(":", "");
			}
		}
		return time;
	}

	/**
	 * IsEquivalent for DvTime is valid only for point of time within one single day.
	 * For example, 00:00:01Z(for 1970-01-01) is supposed to be equivalent to the 
	 * point of datetime 23:00:01-01(on 1969-12-31). However, because no date is involved 
	 * in DvTime, 23:00:01-01 can only be treated as 23:00:01-01(of the same day as the other
	 * DvTime in comparison). So 23:00:01-01 is simply interpreted as a point of time on
	 * 1970-01-01 in a zone an hour behind UTC.
	 * 
	 */
	public boolean isEquivalent(Object o) {
		return super.isEquivalent(o);
	}

	@Override
	DateTime parseStringValue(String value) {
		return DvDateTimeParser.parseTime(value);
	}

    /**
     * Parses a string value and return a DvTime
     */
    public DvTime parse(String value) {
        DateTime time = DvDateTimeParser.parseTime(value);
        return new DvTime(time.getHourOfDay(), time.getMinuteOfHour(), time.getSecondOfMinute(), time.getMillisOfSecond(), time.getZone().toTimeZone());
    }

	void setBooleans(String value) {
		int ele = DvDateTimeParser.analyseTimeString(value);
		//isPartial, minuteKnown, secKnown
		if (ele > 3) {
			setSecondKnown(true);
			setMinuteKnown(true);
			setFractionalSecKnown(true);
		} else if (ele == 3) {
			setSecondKnown(true);
			setMinuteKnown(true);
		} else if (ele == 2) {
			setMinuteKnown(true);
			setIsPartial(true);
		} else if (ele == 1) {
			setIsPartial(true);
		}
	}

	private void setBooleans(boolean isPartial, boolean minuteKnown,
			boolean secondKnown, boolean fractionalSecKnown) {
		this.isPartial = isPartial;
		this.minuteKnown = minuteKnown;
		this.secondKnown = secondKnown;
		this.fractionalSecKnown = fractionalSecKnown;
	}

	void setIsPartial(boolean isPartial) {
		this.isPartial = isPartial;
	}

	void setMinuteKnown(boolean minuteKnown) {
		this.minuteKnown = minuteKnown;
	}

	void setSecondKnown(boolean secondKnown) {
		this.secondKnown = secondKnown;
	}

	void setFractionalSecKnown(boolean fractionalSecKnown) {
		this.fractionalSecKnown = fractionalSecKnown;
	}

	/* fields */
	private boolean isPartial; 
	private boolean minuteKnown;
	private boolean secondKnown;
	private boolean fractionalSecKnown;	
	@Override
	public String serialise() {
		return getReferenceModelName() + "," + toString(true);
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
 *  The Original Code is DvTime.java
 *
 *  The Initial Developer of the Original Code is Rong Chen.
 *  Portions created by the Initial Developer are Copyright (C) 2003-2007
 *  the Initial Developer. All Rights Reserved.
 *
 *  Contributor(s): Yin Su Lim
 *
 * Software distributed under the License is distributed on an 'AS IS' basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 *  ***** END LICENSE BLOCK *****
 */