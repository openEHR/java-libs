/*
 * component:   "openEHR Reference Implementation"
 * description: "Class DvDuration"
 * keywords:    "datatypes"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/datatypes/quantity/datetime/DvDuration.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 22:20:08 +0100 (Wed, 12 Oct 2005) $"
 */
package org.openehr.rm.datatypes.quantity.datetime;

import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Instant;
import org.joda.time.MutablePeriod;
import org.joda.time.Period;
import org.joda.time.format.ISOPeriodFormat;
import org.joda.time.format.PeriodFormatter;
import org.openehr.rm.Attribute;
import org.openehr.rm.FullConstructor;
import org.openehr.rm.datatypes.basic.ReferenceModelName;
import org.openehr.rm.datatypes.quantity.DvAmount;
import org.openehr.rm.datatypes.quantity.DvInterval;
import org.openehr.rm.datatypes.quantity.DvOrdered;
import org.openehr.rm.datatypes.quantity.DvQuantified;
import org.openehr.rm.datatypes.quantity.DvQuantity;
import org.openehr.rm.datatypes.quantity.ReferenceRange;
import org.openehr.rm.datatypes.text.CodePhrase;

/**
 * Represents a period of time with respect to a notional point in time, which
 * is not specified. A sign may be used to indicate the duration is "backwards"
 * in time rather than forwards.
 *
 * @author Rong Chen
 * @version 1.0
 */
public final class DvDuration extends DvAmount<DvDuration> {

	/**
     *
     */
    private static final long serialVersionUID = -8172997416937095330L;

	/**
	 * Constructs a Duration with referenceRange and accuracy
	 *
	 * @param otherReferenceRanges
	 * @param normalRange
	 * @param normalStatus
	 * @param accuracy
	 * @param accuracyPercent
	 * @param magnitudeStatus
	 * @param value
	 * @throws IllegalArgumentException
	 *             if value has wrong format
	 */
	@FullConstructor
	public DvDuration(@Attribute(name = "otherReferenceRanges")
	List<ReferenceRange<DvDuration>> otherReferenceRanges,
			@Attribute(name = "normalRange")
			DvInterval<DvDuration> normalRange,
			@Attribute(name = "normalStatus")
			CodePhrase normalStatus, @Attribute(name = "accuracy")
			double accuracy, @Attribute(name = "accuracyPercent")
			boolean accuracyPercent, @Attribute(name = "magnitudeStatus")
			String magnitudeStatus, @Attribute(name = "value")
			String value) {

		super(otherReferenceRanges, normalRange, normalStatus, accuracy,
				accuracyPercent, magnitudeStatus);

		DvDuration d = DvDuration.getInstance(value);
		this.period = d.period;
		setValue(value);
	}

	/**
	 * Creates a simple DvDuration with string value
	 *
	 * @param value
	 */
	public DvDuration(String value) {
		this(null, null, null, 0.0, false, null, value);
	}

	/**
	 * Constructs a Duration with referenceRange and accuracy
	 *
	 * @param referenceRanges
	 * @param accuracy
	 * @param accuracyPercent
	 * @param days
	 * @param hours
	 * @param minutes
	 * @param seconds
	 * @param fractionalSeconds
	 */
	public DvDuration(List<ReferenceRange<DvDuration>> referenceRanges,
			DvInterval<DvDuration> normalRange, double accuracy,
			boolean accuracyPercent, int years, int months, int weeks,
			int days, int hours, int minutes, int seconds,
			double fractionalSeconds) {

		super(referenceRanges, normalRange, null, accuracy, accuracyPercent,
				null);

		if (Math.abs(fractionalSeconds) >= 1.0) {
			throw new IllegalArgumentException("invalid fraction seconds: "
					+ fractionalSeconds);
		}

		if (!isValidCombination(years, months, weeks, days, hours, minutes,
				seconds, fractionalSeconds)) {
			throw new IllegalArgumentException("invalid combination for period");
		}

		period = new Period(years, months, weeks, days, hours, minutes,
				seconds, (int) (fractionalSeconds * 1000));
		setValue(ISOPeriodFormat.standard().print(period).replace(".", ","));
	}

	/**
	 * Constructs a Duration without referenceRange and accuracy
	 *
	 * @param days
	 * @param hours
	 * @param minutes
	 * @param seconds
	 * @param fractionalSeconds
	 */
	public DvDuration(int years, int months, int weeks, int days, int hours,
			int minutes, int seconds, double fractionalSeconds) {
		this(null, null, 0.0, false, years, months, weeks, days, hours,
				minutes, seconds, fractionalSeconds);
	}

	protected DvDuration(List<ReferenceRange<DvDuration>> referenceRanges,
			DvInterval<DvDuration> normalRange, CodePhrase normalStatus,
			double accuracy, boolean accuracyPercent, String magnitudeStatus,
			Period period) {
		super(referenceRanges, normalRange, normalStatus, accuracy,
				accuracyPercent, magnitudeStatus);
		this.period = period;
		setValue(ISOPeriodFormat.standard().print(period).replace(".", ","));
	}

	/**
	 * Create a Duration from two instances of DvWorldTime
	 *
	 * @param start
	 * @param end
	 */
	public static DvDuration getDifference(DvTemporal start, DvTemporal end) {
		Duration d = new Duration(start.getDateTime(), end.getDateTime());
		DvDateTime dt = (DvDateTime) end;
		return new DvDuration(null, null, null, 0.0, false, null,
				d.toPeriodFrom(start.getDateTime()));
	}

	@Override
	public String getReferenceModelName() {
		return ReferenceModelName.DV_DURATION.getName();
	}

	/**
	 * Create a Duration from a ISO8601 string presentation
	 *
	 * @param value
	 * @throws IllegalArgumentException
	 *             if value null or wrong format
	 */
	public static DvDuration getInstance(String value) {
		if (value == null) {
			throw new IllegalArgumentException("null value");
		}

		if (!value.matches(PATTERN)) {
			throw new IllegalArgumentException("Wrong duration format: "
					+ value);
		}
		Period period = null;
		final String suppliedValue = value;

		if (value.startsWith("-")) {
			value = value.substring(1, value.length()); // skip '-'
			period = ISOPeriodFormat.standard().parsePeriod(value);
			period = negatePeriod(period);
		} else {
			period = ISOPeriodFormat.standard().parsePeriod(value);
		}

		DvDuration duration = new DvDuration(null, null, null, 0.0, false, null, period);
		duration.setValue(suppliedValue); // If we don't set this we cannot reconstruct the original constraint if 0s or 0h etc if all the same period, but the constraint in the ADL / XML serialisation still looks different
		return duration;
	}

	@Override
    public DvDuration parse(String value) {
		return getInstance(value);
	}

	/**
	 * Helper method to negate a copy of period
	 * 
	 * @param mPeriod
	 * @return a negated copy of period
	 */
	static Period negatePeriod(Period period) {
		MutablePeriod mPeriod = period.toMutablePeriod();
		mPeriod.setYears(-mPeriod.getYears());
		mPeriod.setMonths(-mPeriod.getMonths());
		mPeriod.setWeeks(-mPeriod.getWeeks());
		mPeriod.setDays(-mPeriod.getDays());
		mPeriod.setHours(-mPeriod.getHours());
		mPeriod.setMinutes(-mPeriod.getMinutes());
		mPeriod.setSeconds(-mPeriod.getSeconds());
		mPeriod.setMillis(-mPeriod.getMillis());
		return mPeriod.toPeriod();
	}

	/**
	 * Converts a customary quantity to a scientific one for comparison or other
	 * purposes.
	 * 
	 * @return quantity
	 */
	public DvQuantity toQuantity() {
		return new DvQuantity("s", toDouble(), null);
	}

	/**
	 * numeric value of the duration as seconds
	 * 
	 * @return getMagnitude
	 */
	@Override
    public Number getMagnitude() {
		return new Double(toDouble());
	}

	// convert dvduration to seconds
	private double toDouble() {
		return Math
				.abs(period.toDurationFrom(new Instant()).getMillis() / 10E2);
	}

	/**
	 * Sum of this quantity and another whose formal type must be the difference
	 * type of this quantity.
	 * 
	 * @param q
	 * @return production of addition
	 * @throws ClassCastException
	 */
	@Override
    public DvQuantified<DvDuration> add(DvQuantified q) {
		final DvDuration d = (DvDuration) q;

		DateTime dt = new DateTime(0);
		Duration duration = period.toDurationFrom(dt);
		Duration result = duration.plus(d.period.toDurationFrom(dt));
		Period p = result.toPeriodFrom(dt);

		return new DvDuration(d.getOtherReferenceRanges(), d.getNormalRange(),
				d.getNormalStatus(), d.getAccuracy(), d.isAccuracyPercent(), 
				d.getMagnitudeStatus(), p);
	}

	/**
	 * Negated version of current object
	 * 
	 * @return negated version
	 */
	public DvDuration negate() {
		return new DvDuration(getOtherReferenceRanges(), getNormalRange(),
				getNormalStatus(), getAccuracy(), isAccuracyPercent(), 
				getMagnitudeStatus(), negatePeriod(period));
	}

	/**
	 * Difference of this quantity and another whose formal type must be the
	 * difference type of this quantity type.
	 * 
	 * @param q
	 * @return product of substraction
	 * @throws ClassCastException
	 */
	@Override
    public DvQuantified<DvDuration> subtract(DvQuantified q) {
		final DvDuration d = (DvDuration) q;

		DateTime dt = new DateTime(0);
		Duration duration = period.toDurationFrom(dt);
		Duration result = duration.minus(d.period.toDurationFrom(dt));
		Period p = result.toPeriodFrom(dt);

		return new DvDuration(d.getOtherReferenceRanges(), d.getNormalRange(),
				d.getNormalStatus(), d.getAccuracy(), d.isAccuracyPercent(), 
				d.getMagnitudeStatus(), p);
	}

	/**
	 * Type of quantity which can be added or subtracted to this quantity.
	 * Usually the same type, but may be different as in the case of dates and
	 * times.
	 * 
	 * @return diff type
	 */
	@Override
    public Class<DvDuration> getDiffType() {
		return DvDuration.class;
	}

	/**
	 * Following ISO 8601, starts with "P", and is followed by a list of
	 * periods, each appended by a single letter designator: "D" for days, "H"
	 * for hours, "M" for minutes, and "S" for seconds.
	 * 
	 * @return string presentation
	 */
	@Override
    public String toString() {
		String str = value == null ? ISOPeriodFormat.standard().print(period) : value;
		// No DvDuration will be constructed in P-1y3M24W format, it's either
		// all negative or all positive values for each element. The only time
		// this format will exist is after addition or subtraction. However,
		// turning the Period to Duration then back to Period again will make
		// the elements all positive or negative again, because Duration is
		// based
		// on milli second

		return toPrefixFormat(str);
	}
	
	/**
	 * Gets the String value of this duration
	 */
	public String getValue() {
		return toString();
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
		final DvDuration d = (DvDuration) o;

		/*
		 * if (period.getYears() > d.period.getYears()) { return 1; } else if
		 * (period.getYears() < d.period.getYears()) { return -1; } if
		 * (period.getMonths() > d.period.getMonths()) { return 1; } else if
		 * (period.getMonths() < d.period.getMonths()) { return -1; } if
		 * (period.getWeeks() > d.period.getWeeks()) { return 1; } else if
		 * (period.getWeeks() < d.period.getWeeks()) { return -1; } if
		 * (period.getDays() > d.period.getDays()) { return 1; } else if
		 * (period.getDays() < d.period.getDays()) { return -1; }
		 * 
		 * if (period.getHours() > d.period.getHours()) { return 1; } else if
		 * (period.getHours() < d.period.getHours()) { return -1; }
		 * 
		 * if (period.getMinutes() > d.period.getMinutes()) { return 1; } else
		 * if (period.getMinutes() < d.period.getMinutes()) { return -1; }
		 * 
		 * if (period.getSeconds() > d.period.getSeconds()) { return 1; } else
		 * if (period.getSeconds() < d.period.getSeconds()) { return -1; }
		 * 
		 * if (period.getMillis() > d.period.getMillis()) { return 1; } else if
		 * (period.getMillis() < d.period.getMillis()) { return -1; }
		 */
		DateTime dt = new DateTime(0);
		Duration duration = period.toDurationFrom(dt);
		Duration otherD = d.period.toDurationFrom(dt);
		return duration.compareTo(otherD);
		// return 0;
	}

	/**
	 * number of years of nominal 365-day length
	 * 
	 * @return years
	 */
	public int getYears() {
		return period.getYears();
	}

	/**
	 * number of months of nominal 30 day length
	 * 
	 * @return months
	 */
	public int getMonths() {
		return period.getMonths();
	}

	public int getWeeks() {
		return period.getWeeks();
	}

	/**
	 * number of 24 hour days
	 * 
	 * @return days
	 */
	public int getDays() {
		return period.getDays();
	}

	/**
	 * number of 60 minute hours
	 * 
	 * @return hours
	 */
	public int getHours() {
		return period.getHours();
	}

	/**
	 * number of 60 second minutes
	 * 
	 * @return minutes
	 */
	public int getMinutes() {
		return period.getMinutes();
	}

	/**
	 * number of seconds
	 * 
	 * @return seconds
	 */
	public int getSeconds() {
		return period.getSeconds();
	}

	/**
	 * fractional seconds
	 * 
	 * @return fractional seconds
	 */
	public double getFractionalSeconds() {
		return period.getMillis() / 10E2;
	}

	/**
	 * If time is valid duration within 24h/60min/60sec system of time
	 * 
	 * @param days
	 * @param hours
	 * @param minutes
	 * @param seconds
	 * @return true if value
	 */
	static boolean isValidCombination(int years, int months, int weeks,
			int days, int hours, int minutes, int seconds,
			double fractionalSeconds) {
		int total = Math.abs(years) + Math.abs(months) + Math.abs(weeks)
				+ Math.abs(days) + Math.abs(hours) + Math.abs(minutes)
				+ Math.abs(seconds);
		boolean zero = total == 0 && fractionalSeconds == 0.0;
		if (!zero) {
			int[] arr = { years, months, weeks, days, hours, minutes, seconds };
			int max = 0;
			int min = 0;
			for (int i = 0; i < arr.length; i++) {
				max = Math.max(arr[i], max);
				min = Math.min(arr[i], min);
			}
			if (max > 0) {
				if (min < 0 || fractionalSeconds < 0.0) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * If time is valid duration within 24h/60min/60sec system of time
	 * 
	 * @param value
	 * @return true if value is valid
	 */
	/*
	 * public static boolean isValidDuration(String value) { DvDuration dd =
	 * null; try { dd = DvDuration.getInstance(value); } catch
	 * (IllegalArgumentException iae) { } return dd != null; }
	 */

	/**
	 * Tests if two instances are strictly comparable.
	 * 
	 * @param ordered
	 * @return true if two instances are strictly comparable
	 */
	@Override
    public boolean isStrictlyComparableTo(DvOrdered ordered) {
		return (ordered instanceof DvDuration);
	}

	@Override
    public boolean equals(Object o) {
		if (this == o) {
            return true;
        }
		if (!(o instanceof DvDuration)) {
            return false;
        }
		if (!super.equals(o)) {
            return false;
        }

		final DvDuration dvDuration = (DvDuration) o;

		/*
		 * if (years != dvDuration.years) return false; if (months !=
		 * dvDuration.months) return false; if (days != dvDuration.days) return
		 * false; if (fractionalSeconds != dvDuration.fractionalSeconds) return
		 * false; if (hours != dvDuration.hours) return false; if (minutes !=
		 * dvDuration.minutes) return false; if (seconds != dvDuration.seconds)
		 * return false;
		 */

		return period.equals(dvDuration.period);
	}

	@Override
    public int hashCode() {
		int result = super.hashCode();
		long temp;
		result = 29 * result + period.getYears();
		result = 29 * result + period.getMonths();
		result = 29 * result + period.getDays();
		result = 29 * result + period.getHours();
		result = 29 * result + period.getMinutes();
		result = 29 * result + period.getSeconds();
		temp = (period.getMillis() / 10E2) != +0.0d ? Double
				.doubleToLongBits(fractionalSeconds) : 0l;
		result = 29 * result + (int) (temp ^ (temp >>> 32));

		return result;
	}

	Period getPeriod() {
		return period;
	}

	void setValue(String value) {
		this.value = toPrefixFormat(value);
	}

	private String toPrefixFormat(String value) {
		String str = value;
		if (str.indexOf("-") > 0) {
			str = "-" + str.replace("-", "");
		}
		return str;
	}

	// POJO end

	/* fields */
	// keep this temporarily for persistence purpose
	// private int years, months, weeks, days, hours, minutes, seconds;
	private double fractionalSeconds;

	private String value;

	private Period period;

	/* static value */
	private static final String PATTERN = "(-)?P((\\d)+(y|Y))?((\\d)+(m|M))?((\\d)+(w|W))?((\\d)+(d|D))?"
			+ "(T((\\d)+(h|H))?((\\d)+(m|M))?((\\d)+((,|\\.)(\\d){1,3})?(s|S))?)?";

	// private static String PATTERN_DATE =
	// "(-)?P((\\d)*(y|Y))?((\\d)*(m|M))?((\\d)*(d|D))";
	//private static PeriodFormatter formatter = ISOPeriodFormat.standard();

	@Override
	public String serialise() {
		return getReferenceModelName() + "," + toString();
	}
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
 * The Original Code is DvDuration.java
 * 
 * The Initial Developer of the Original Code is Rong Chen. Portions created by
 * the Initial Developer are Copyright (C) 2003-2007 the Initial Developer. All
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