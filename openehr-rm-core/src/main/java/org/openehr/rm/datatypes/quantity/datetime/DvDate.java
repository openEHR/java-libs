/*
 * component:   "openEHR Reference Implementation"
 * description: "Class DvDate"
 * keywords:    "datatypes"
 *
 * author:      "Yin Su Lim <y.lim@chime.ucl.ac.uk>"
 * support:     "CHIME, UCL"
 * copyright:   "Copyright (c) 2006 UCL, UK"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/datatypes/quantity/datetime/DvDate.java $"
 * revision:    "$LastChangedRevision: 53 $"
 * last_change: "$LastChangedDate: 2006-08-11 13:20:08 +0100 (Fri, 11 Aug 2006) $"
 */
package org.openehr.rm.datatypes.quantity.datetime;

import java.util.List;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.joda.time.DateTime;
import org.joda.time.MutableDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.openehr.rm.Attribute;
import org.openehr.rm.FullConstructor;
import org.openehr.rm.datatypes.quantity.DvInterval;
import org.openehr.rm.datatypes.quantity.DvOrdered;
import org.openehr.rm.datatypes.quantity.ReferenceRange;
import org.openehr.rm.datatypes.text.CodePhrase;


/**
 * Represents a point in time, as measured on the Gregorian calendar,
 * and specified only to the day.
 *
 * @author Rong Chen
 * @version 1.0
 */
public class DvDate extends DvTemporal<DvDate> {

	/**
     * 
     */
    private static final long serialVersionUID = 4412140604570981489L;
	/**
	 * Construct a DvDate
	 *
	 * @param otherReferenceRanges null if not specified
	 * @param normalRange null if not specified
	 * @param normalStatus null if not specified
	 * @param mangnitudeStatus null if not specified
	 * @param accuracy        0 if not specified
	 * @param magnitudeStatus null if not specified
	 * @param value
	 * @throws IllegalArgumentException
	 */
	@FullConstructor
	public DvDate(@Attribute(name = "otherReferenceRanges")
	List<ReferenceRange<DvDate>> otherReferenceRanges,
			@Attribute(name = "normalRange")
			DvInterval<DvDate> normalRange, @Attribute(name = "normalStatus")
			CodePhrase normalStatus, @Attribute(name = "accuracy")
			DvDuration accuracy, @Attribute(name = "magnitudeStatus")
			String magnitudeStatus, @Attribute(name = "value", required = true)
			String value) {
		super(otherReferenceRanges, normalRange, normalStatus, accuracy,
				magnitudeStatus, value);
	}

	protected DvDate(List<ReferenceRange<DvDate>> referenceRanges,
			DvInterval<DvDate> normalRange, CodePhrase normalStatus,
			DvDuration accuracy, String magnitudeStatus, DateTime datetime,
			String pattern) {
		super(referenceRanges, normalRange, normalStatus, accuracy,
				magnitudeStatus, datetime);
		setValue(DvDateTimeParser.toDateString(getDateTime(), pattern));
		setBooleans(pattern);
	}

	/**
	 * Construct a DvDate of current date
	 *
	 */
	public DvDate() {
		super(DvDateTimeParser.defaultDate());
		setValue(DvDateTimeParser.toDateString(getDateTime(), "yyyy-MM-dd"));
		setBooleans(false, true, true);
	}

	/**
	 * Constructs a DvDate with timezone
	 *
	 * @param year
	 * @param month    starts with 0
	 * @param day
	 * @param timezone null if use default timezone
	 */
	public DvDate(int year, int month, int day) {
		super(DvDateTimeParser.convertDate(year, month, day));
		setValueByPattern("yyyy-MM-dd");
		setBooleans(false, true, true);
	}

	/**
	 * Contructs a partial DvDate
	 *
	 * @param year
	 * @param month
	 * @param monthKnown
	 * @param day
	 * @param dayKnown
	 * @param timezone
	 */
	public DvDate(int year, int month) {
		super(DvDateTimeParser.convertDate(year, month, 1));
		setValueByPattern("yyyy-MM");
		setBooleans(true, true, false);
	}

	public DvDate(int year) {
		super(DvDateTimeParser.convertDate(year, 1, 1));
		setValueByPattern("yyyy");
		setBooleans(true, false, false);
	}

	public DvDate(String value) {
		this(null, null, null, null, null, value);
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
	 * @return month, 0 if month is unknown
	 */
	public int getMonth() {
		return monthKnown ? getDateTime().getMonthOfYear() : -1;
	}

	/**
	 * Day in month
	 *
	 * @return day
	 */
	public int getDay() {
		return dayKnown ? getDateTime().getDayOfMonth() : -1;
	}

	/**
	 * Indicate if this DvDate is partial
	 *
	 * @return true if is partial
	 *
	 */
	public boolean isPartial() {
		return isPartial;
	}

	/**
	 * Indicate if month is unknown 
	 *
	 * @return true if month is unknown
	 */
	public boolean monthKnown() {
		return monthKnown;
	}

	/**
	 * Indicate if day is unknown 
	 *
	 * @return true if day is unknown
	 */
	public boolean dayKnown() {
		return dayKnown;
	}

	/**
	 * If date is valid ISO8601 format
	 *
	 *@param value
	 * 
	 */
	public static boolean isValidISO8601Date(String value) {
		DateTime dt = null;
		try {
			dt = DvDateTimeParser.parseDate(value);
		} catch (Exception e) {
			return false;
		}
		return dt != null;
	}

	@Override
	DateTime parseStringValue(String value) {
		return DvDateTimeParser.parseDate(value);
	}

    /**
     * Parses a string value and return a DvDate
     */
    public DvDate parse(String value) {
        DateTime date = DvDateTimeParser.parseDate(value);
        return new DvDate(date.getYear(), date.getMonthOfYear(), date.getDayOfMonth());
    }

	@Override
	public Double getMagnitude() {
		// TODO
		return null;
	}

	@Override
	public boolean isStrictlyComparableTo(DvOrdered ordered) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean equals(Object o) {
		if (!super.equals(o))
			return false;

		final DvDate dDate = (DvDate) o;

		return new EqualsBuilder()
					.append(isPartial, dDate.isPartial)
					.append(monthKnown, dDate.monthKnown)
					.append(dayKnown, dDate.dayKnown)
					.isEquals();
	}

	/**
	 * Output DvDate in extended date format if a DvDate is not constructed 
	 * via the single String constructor.
	 */
	public String toString(boolean isExtended) {
		String date = super.toString();
		if (date == null) {
			date = "";
		} else {
			if (isExtended) {
				date = DvDateTimeParser.basicToExtendedDate(date);
			} else {
				date = date.replace("-", "");
			}
		}
		return date;
	}

	void setBooleans(boolean isPartial, boolean monthKnown, boolean dayKnown) {
		this.isPartial = isPartial;
		this.monthKnown = monthKnown;
		this.dayKnown = dayKnown;
	}

	void setBooleans(String value) {
		int ele = DvDateTimeParser.analyseDateString(value);
		//isPartial, monthKnown, dayKnown
		if (ele == 3) {
			setDayKnown(true);
			setMonthKnown(true);
		} else if (ele == 2) {
			setMonthKnown(true);
			setIsPartial(true);
		} else if (ele == 1) {
			setIsPartial(true);
		}
	}

	void setDayKnown(boolean dayKnown) {
		this.dayKnown = dayKnown;
	}

	void setMonthKnown(boolean monthKnown) {
		this.monthKnown = monthKnown;
	}

	void setIsPartial(boolean isPartial) {
		this.isPartial = isPartial;
	}

	@Override
	public DvDate add(DvDuration q) {
		if (!getDiffType().isInstance(q)) {
			throw new IllegalArgumentException("invalid difference type");
		}
		DvDuration d = (DvDuration) q;
		MutableDateTime mdate = getDateTime().toMutableDateTimeISO();
		mdate.add(d.getPeriod());
		return new DvDate(getOtherReferenceRanges(), getNormalRange(),
				getNormalStatus(), getAccuracy(), getMagnitudeStatus(),
				mdate.toDateTimeISO(), toString());
	}

	@Override
	public DvDate subtract(DvDuration q) {
		if (!getDiffType().isInstance(q)) {
			throw new IllegalArgumentException("invalid difference type");
		}		
		return add(q.negate());
	}
	
	/* set the string value by given pattern using datetime value */
	private void setValueByPattern(String pattern) {
		DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern);
		String value = fmt.print(getDateTime());
		setValue(value);		
	}

	/* private fields */
	private boolean dayKnown;
	private boolean monthKnown;
	private boolean isPartial;
	@Override
	public String getReferenceModelName() {
		return "DV_DATE";
	}

	@Override
	public String serialise() {
		return getReferenceModelName() + "," +toString(true);
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
 *  The Original Code is DvDate.java
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
