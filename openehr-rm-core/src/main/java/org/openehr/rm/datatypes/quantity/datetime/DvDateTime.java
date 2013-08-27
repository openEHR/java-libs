/*
 * component:   "openEHR Reference Implementation"
 * description: "Class DvDateTime"
 * keywords:    "datatypes"
 *
 * author:      "Yin Su Lim <y.lim@chime.ucl.ac.uk>"
 * support:     "CHIME, UCL"
 * copyright:   "Copyright (c) 2006 UCL, UK"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/datatypes/quantity/datetime/DvDateTime.java $"
 * revision:    "$LastChangedRevision: 53 $"
 * last_change: "$LastChangedDate: 2006-08-11 13:20:08 +0100 (Fri, 11 Aug 2006) $"
 */

package org.openehr.rm.datatypes.quantity.datetime;

import java.util.List;
import java.util.TimeZone;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.joda.time.DateTime;
import org.joda.time.MutableDateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.openehr.rm.Attribute;
import org.openehr.rm.FullConstructor;
import org.openehr.rm.datatypes.basic.ReferenceModelName;
import org.openehr.rm.datatypes.quantity.DvInterval;
import org.openehr.rm.datatypes.quantity.DvOrdered;

import org.openehr.rm.datatypes.quantity.ReferenceRange;
import org.openehr.rm.datatypes.text.CodePhrase;

/**
 * Represents a point in time, specified to the second.
 *
 * @author Yin Su Lim
 * @version 1.0
 */
public class DvDateTime extends DvTemporal<DvDateTime> {

	/**
     * 
     */
    private static final long serialVersionUID = -84308270373625815L;

	/* static fields */
	static final DateTimeFormatter eDTimeFormatter = ISODateTimeFormat
			.dateTime(); //extended formatter

	static final DateTimeFormatter dTimeFormatter = ISODateTimeFormat
			.basicDateTime();

	/**
	 * Construct a DvDateTime
	 *
	 * @param otherReferenceRanges null if not specified
	 * @param normalRange	null if not specified
	 * @param normalStatus	null if not specified
	 * @param accuracy        0 if not specified
	 * @param magnitudeStatus null if not specified
	 * @param value
	 * @throws IllegalArgumentException
	 */
	@FullConstructor
	public DvDateTime(@Attribute(name = "otherReferenceRanges")
	List<ReferenceRange<DvDateTime>> otherReferenceRanges,
			@Attribute(name = "normalRange") DvInterval<DvDateTime> normalRange,
			@Attribute(name = "normalStatus") CodePhrase normalStatus, 
			@Attribute(name = "accuracy") DvDuration accuracy, 
			@Attribute(name = "magnitudeStatus") String magnitudeStatus, 
			@Attribute(name = "value", required = true)	String value) {
		super(otherReferenceRanges, normalRange, normalStatus, accuracy,
				magnitudeStatus, value);
	}

	protected DvDateTime(List<ReferenceRange<DvDateTime>> referenceRanges,
			DvInterval<DvDateTime> normalRange, CodePhrase normalStatus,
			DvDuration accuracy, String magnitudeStatus, DateTime datetime,
			String pattern) {
		super(referenceRanges, normalRange, normalStatus, accuracy,
				magnitudeStatus, datetime);
		setValue(DvDateTimeParser.toDateTimeString(getDateTime(), pattern));
		setBooleans(pattern);
	}

	/*
	 * Convenience constructor
	 */
	private DvDateTime(DateTime datetime) {
		this(null, null, null, null, null, datetime, "yyyy-MM-dd'T'HH:mm:ss.SSSZZ");
	}

	/**
	 * Construct a DvDateTime by current date and time
	 * 
	 */
	public DvDateTime() {
		super(DvDateTimeParser.defaultDateTime());
		setValue(DvDateTimeParser.toDateTimeString(getDateTime(),
				"yyyy-MM-dd'T'HH:mm:ss.SSSZZ"));
		setBooleans(false, true, true, true);
	}

	/**
	 * Construct a DvDateTime by value
	 * 
	 * @param value
	 */
	public DvDateTime(String value) {
		this(null, null, null, null, null, value);
	}

	/**
	 * Constructs a complete DvDateTime
	 *
	 * @param year
	 * @param month    starts with 0
	 * @param day   day of month
	 * @param hour  hour of day
	 * @param minute
	 * @param second
	 * @param timezone null if use default timezone
	 */
	public DvDateTime(int year, int month, int day, int hour, int minute,
			int second, double fractionalSec, TimeZone timezone) {
		super(DvDateTimeParser.convertDateTime(year, month, day, hour, minute, 
				second,	fractionalSec, timezone));
		String format = timezone == null ? "yyyy-MM-dd'T'HH:mm:ss.SSS"
				: "yyyy-MM-dd'T'HH:mm:ss.SSSZZ";
		setValue(DvDateTimeParser.toDateTimeString(getDateTime(), format));
		setBooleans(false, true, true, true);
	}

	public DvDateTime(int year, int month, int day, int hour, int minute,
			int second, TimeZone timezone) {
		super(DvDateTimeParser.convertDateTime(
				year, month, day, hour, minute, second, 0.0, timezone));
		String format = timezone == null ? "yyyy-MM-dd'T'HH:mm:ss"
				: "yyyy-MM-dd'T'HH:mm:ssZZ";
		setValue(DvDateTimeParser.toDateTimeString(getDateTime(), format));
		setBooleans(false, true, true, false);
	}

	public DvDateTime(int year, int month, int day, int hour, int minute,
			TimeZone timezone) {
		super(DvDateTimeParser.convertDateTime(
				year, month, day, hour, minute, 0, 0.0, timezone));
		String format = timezone == null ? "yyyy-MM-dd'T'HH:mm"
				: "yyyy-MM-dd'T'HH:mmZZ";
		setValue(DvDateTimeParser.toDateTimeString(getDateTime(), format));
		setBooleans(true, true, false, false);
	}

	public DvDateTime(int year, int month, int day, int hour, TimeZone timezone) {
		super(DvDateTimeParser.convertDateTime(
				year, month, day, hour, 0, 0, 0.0, timezone));
		String format = timezone == null ? "yyyy-MM-dd'T'HH" : "yyyy-MM-dd'T'HHZZ";
		setValue(DvDateTimeParser.toDateTimeString(getDateTime(), format));
		setBooleans(true, false, false, false);
	}

	/* (non-Javadoc)
	 * @see org.openehr.rm.datatypes.quantity.datetime.NDvWorldTime#parseValue(java.lang.String)
	 */
	@Override
	DateTime parseStringValue(String value) {
		return DvDateTimeParser.parseDateTime(value);
	}

	/**
	 * Parses a string value and return a DvDateTime
	 */
	public DvDateTime parse(String value) {
		DateTime datetime = DvDateTimeParser.parseDateTime(value);
		return new DvDateTime(datetime);
	}

	public static boolean isValidISO8601DateTime(String value) {
		DateTime dt = null;
		try {
			dt = DvDateTimeParser.parseDateTime(value);
		} catch (Exception e) {
			return false;
		}
		return dt != null;
	}

	@Override
	public String getReferenceModelName() {
		return ReferenceModelName.DV_DATE_TIME.getName();
	}

	/**
	 * Year
	 *
	 * @return year
	 */
	public int getYear() {
		return getDateTime().getYear();
	}

	/**
	 * Month in year
	 *
	 * @return month in year
	 */
	public int getMonth() {
		return getDateTime().getMonthOfYear();
	}

	/**
	 * Day in month
	 *
	 * @return day in month
	 */
	public int getDay() {
		return getDateTime().getDayOfMonth();
	}

	/**
	 * Hour in day
	 *
	 * @return hour
	 */
	public int getHour() {
		return getDateTime().getHourOfDay();
	}

	/**
	 * Minute in hour
	 *
	 * @return minute in hour
	 */
	public int getMinute() {
		return minuteKnown() ? getDateTime().getMinuteOfHour() : -1;
	}

	/**
	 * Second in minute
	 *
	 * @return second in minute
	 */
	public int getSecond() {
		return secondKnown ? getDateTime().getSecondOfMinute() : -1;
	}

	/**
	 * fractional seconds
	 *
	 * @return fractional seconds
	 */
	public double getFractionalSecond() {
		return fractionalSecKnown ? getDateTime().getMillisOfSecond() / 10E2
				: -0.1;
	}

	public boolean minuteKnown() {
		return minuteKnown;
	}

	public boolean secondKnown() {
		return secondKnown;
	}

	public boolean fractionalSecKnown() {
		return fractionalSecKnown;
	}

	public boolean isPartial() {
		return isPartial;
	}

	@Override
	public Number getMagnitude() {
		//TODO
		return null;
	}

	public DvDuration differenceOf(DvDateTime dt) {
		return DvDuration.getDifference(this, dt);
	}

	@Override
	public boolean isStrictlyComparableTo(DvOrdered ordered) {
		// TODO Auto-generated method stub
		return false;
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
			dt = DvDateTimeParser.parseDateTime(value);
		} catch (Exception e) {
			return false;
		}
		return dt != null;
	}

	/**
	 * Two DvDateTime equal if both have same year, month, day and timezone
	 *
	 * @param o
	 * @return true if equals
	 */
	public boolean equals(Object o) {
		if (!super.equals(o))
			return false;

		final DvDateTime dt = (DvDateTime) o;

		return new EqualsBuilder().append(
				this.getDateTime().getZone().hashCode(),
				this.getDateTime().getZone().hashCode()).append(isPartial,
				dt.isPartial).append(minuteKnown, dt.minuteKnown).append(
				secondKnown, dt.secondKnown).append(fractionalSecKnown,
				dt.fractionalSecKnown).isEquals();
	}

	public String toString(boolean isExtended) {
		String dt = super.toString();
		if (dt == null) {
			dt = "";
		} else {
			if (isExtended) {
				dt = DvDateTimeParser.basicToExtendedDateTime(dt);
			} else {
				dt = dt.replace(":", "").replace("-", "");
			}
		}
		return dt;
	}

	void setBooleans(boolean isPartial, boolean minuteKnown,
			boolean secondKnown, boolean fractionalSecKnown) {
		this.isPartial = isPartial;
		this.minuteKnown = minuteKnown;
		this.secondKnown = secondKnown;
		this.fractionalSecKnown = fractionalSecKnown;
	}

	void setBooleans(String value) {
		int ele = DvDateTimeParser.analyseTimeString(value.substring(value
				.indexOf("T") + 1));
		//isPartial, monthKnown, dayKnown
		if (ele > 3) {
			setMinuteKnown(true);
			setSecondKnown(true);
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

	void setMinuteKnown(boolean minuteKnown) {
		this.minuteKnown = minuteKnown;
	}

	void setSecondKnown(boolean secondKnown) {
		this.secondKnown = secondKnown;
	}

	void setFractionalSecKnown(boolean fractionalSecKnown) {
		this.fractionalSecKnown = fractionalSecKnown;
	}

	void setIsPartial(boolean isPartial) {
		this.isPartial = isPartial;
	}

	@Override
	public DvDateTime add(DvDuration q) {
		if (!getDiffType().isInstance(q)) {
			throw new IllegalArgumentException("invalid difference type");
		}
		DvDuration d = (DvDuration) q;
		MutableDateTime mdate = getDateTime().toMutableDateTimeISO();
		mdate.add(d.getPeriod());

		return new DvDateTime(getOtherReferenceRanges(), getNormalRange(),
				getNormalStatus(), getAccuracy(), getMagnitudeStatus(), mdate
						.toDateTimeISO(), this.toString());
	}

	@Override
	public DvDateTime subtract(DvDuration q) {
		if (!getDiffType().isInstance(q)) {
			throw new IllegalArgumentException("invalid difference type");
		}		
		return add(q.negate());
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
 *  The Original Code is DvDateTime.java
 *
 *  The Initial Developer of the Original Code is Rong Chen.
 *  Portions created by the Initial Developer are Copyright (C) 2003-2004
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
