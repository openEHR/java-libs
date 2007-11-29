/*
 * Copyright (C) 2005 Acode HB, Sweden.
 * All Rights Reserved.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You may obtain a copy of the License at
 * http://www.gnu.org/licenses/gpl.txt
 *
 */

package se.acode.openehr.parser;

import org.openehr.am.archetype.constraintmodel.CAttribute;
import org.openehr.am.archetype.constraintmodel.CComplexObject;
import org.openehr.rm.support.basic.Interval;
import org.openehr.rm.datatypes.quantity.datetime.DvDate;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datatypes.quantity.datetime.DvTime;

import java.util.*;

/**
 * Test case tests parsing of datetime types in ADL files.
 * 
 * @author rong.chen
 */

public class DateTimeTest extends ParserTestBase {

	/**
	 * Create new test case
	 * 
	 * @param test
	 * @throws Exception
	 */
	public DateTimeTest(String test) throws Exception {
		super(test);
		ADLParser parser = new ADLParser(loadFromClasspath(
			"adl-test-entry.datetime.test.adl"));
		attributeList = parser.parse().getDefinition().getAttributes();
	}

	private List getConstraints(int index) {
		CAttribute ca = (CAttribute) attributeList.get(index);
		return ((CComplexObject) ca.getChildren().get(0)).getAttributes();
	}

	/**
	 * Tests date and partial date constraints parsing
	 * 
	 * @throws Exception
	 */
	public void testDateConstraints() throws Exception {
		List list = getConstraints(0);

		assertCDate(list.get(0), "yyyy-mm-dd", null, null, null);

		assertCDate(list.get(1), "yyyy-??-??", null, null, null);

		assertCDate(list.get(2), "yyyy-mm-??", null, null, null);

		assertCDate(list.get(3), "yyyy-??-XX", null, null, null);

		assertCDate(list.get(4), null, null, new String[] { "1983-12-25" },
				null);

		assertCDate(list.get(5), null, null, new String[] { "2000-01-01" },
				null);

		assertCDate(list.get(6), null, new Interval<DvDate>(date("2004-09-20"),
				date("2004-10-20")), null, null);

		assertCDate(list.get(7), null, lessThan(date("2004-09-20")), null,
				null);

		assertCDate(list.get(8), null, lessEqual(date("2004-09-20")), null,
				null);

		assertCDate(list.get(9), null, greaterThan(date("2004-09-20")), null,
				null);

		assertCDate(list.get(10), null, greaterEqual(date("2004-09-20")), null,
				null);

		// test assumed values
		assertCDate(list.get(11), "yyyy-mm-dd", null, null, "2000-01-01");

		assertCDate(list.get(12), "yyyy-??-??", null, null, "2001-01-01");

		assertCDate(list.get(13), "yyyy-mm-??", null, null, "2002-01-01");

		assertCDate(list.get(14), "yyyy-??-XX", null, null, "2003-01-01");

		assertCDate(list.get(15), null, null, new String[] { "1983-12-25" },
				"2004-01-01");

		assertCDate(list.get(16), null, null, new String[] { "2000-01-01" },
				"2005-01-01");

		assertCDate(list.get(17), null, new Interval<DvDate>(
				date("2004-09-20"), date("2004-10-20")), null, 
				"2004-09-30");

		assertCDate(list.get(18), null, lessThan(date("2004-09-20")), null,
				"2004-09-01");

		assertCDate(list.get(19), null, lessEqual(date("2004-09-20")), null,
				"2003-09-20");

		assertCDate(list.get(20), null, greaterThan(date("2004-09-20")), null,
				"2005-01-02");

		assertCDate(list.get(21), null, greaterEqual(date("2004-09-20")), null,
				"2005-10-30");
	}

	/**
	 * Tests time and partial time constraints parsing
	 * 
	 * @throws Exception
	 */
	public void testTimeConstraints() throws Exception {
		List list = getConstraints(1);

		assertCTime(list.get(0), "hh:mm:ss", null, null, null);

		assertCTime(list.get(1), "hh:mm:XX", null, null, null);

		assertCTime(list.get(2), "hh:??:XX", null, null, null);

		assertCTime(list.get(3), "hh:??:??", null, null, null);

		assertCTime(list.get(4), null, null, new String[] { "22:00:05" },
				null);

		assertCTime(list.get(5), null, null, new String[] { "00:00:59" },
				null);

		assertCTime(list.get(6), null, null, new String[] { "12:35" }, 
				null);

		assertCTime(list.get(7), null, null, new String[] { "12:35:45.666" },
				null);

		assertCTime(list.get(8), null, null, new String[] { "12:35:45-0700" },
				null);

		assertCTime(list.get(9), null, null, new String[] { "12:35:45+0800" },
				null);

		assertCTime(list.get(10), null, null,
				new String[] { "12:35:45.999-0700" }, null);

		assertCTime(list.get(11), null, null,
				new String[] { "12:35:45.000+0800" }, null);

		assertCTime(list.get(12), null, null,
				new String[] { "12:35:45.000+0000" }, null);

		assertCTime(list.get(13), null, null,
				new String[] { "12:35:45.995-0700" }, null);

		assertCTime(list.get(14), null, null,
				new String[] { "12:35:45.001+0800" }, null);

		assertCTime(list.get(15), null, new Interval(time("12:35"),
				time("16:35")), null, null);

		assertCTime(list.get(16), null, lessThan(time("12:35")), null, null);

		assertCTime(list.get(17), null, lessEqual(time("12:35")), null, null);

		assertCTime(list.get(18), null, greaterThan(time("12:35")), null, null);

		assertCTime(list.get(19), null, greaterEqual(time("12:35")), null, null);

	}

	/**
	 * Tests time and partial time constraints parsing
	 * 
	 * @throws Exception
	 */
	public void testTimeConstraintsWithAssumedValues() throws Exception {
		List list = getConstraints(1);

		assertCTime(list.get(20), "hh:mm:ss", null, null, "10:00:00");

		assertCTime(list.get(21), "hh:mm:XX", null, null, "10:00:00");

		assertCTime(list.get(22), "hh:??:XX", null, null, "10:00:00");

		assertCTime(list.get(23), "hh:??:??", null, null, "10:00:00");

		assertCTime(list.get(24), null, null, new String[] { "22:00:05" },
				"10:00:00");

		assertCTime(list.get(25), null, null, new String[] { "00:00:59" },
				"10:00:00");

		assertCTime(list.get(26), null, null, new String[] { "12:35" },
				"10:00:00");

		assertCTime(list.get(27), null, null, new String[] { "12:35:45.666" },
				"10:00:00");

		assertCTime(list.get(28), null, null, new String[] { "12:35:45-0700" },
				"10:00:00");

		assertCTime(list.get(29), null, null, new String[] { "12:35:45+0800" },
				"10:00:00");

		assertCTime(list.get(30), null, null,
				new String[] { "12:35:45.999-0700" }, "10:00:00");

		assertCTime(list.get(31), null, null,
				new String[] { "12:35:45.000+0800" }, "10:00:00");

		assertCTime(list.get(32), null, null,
				new String[] { "12:35:45.000+0000" }, "10:00:00");

		assertCTime(list.get(33), null, null,
				new String[] { "12:35:45.995-0700" }, "10:00:00");

		assertCTime(list.get(34), null, null,
				new String[] { "12:35:45.001+0800" }, "10:00:00");

		assertCTime(list.get(35), null, new Interval<DvTime>(time("12:35"),
				time("16:35")), null, "10:00:00");

		assertCTime(list.get(36), null, lessThan(time("12:35")), null,
				"10:00:00");

		assertCTime(list.get(37), null, lessEqual(time("12:35")), null,
				"10:00:00");

		assertCTime(list.get(38), null, greaterThan(time("12:35")), null,
				"10:00:00");

		assertCTime(list.get(39), null, greaterEqual(time("12:35")), null,
				"10:00:00");
	}

	/**
	 * Tests datetime and partial datetime constraints parsing
	 * 
	 * @throws Exception
	 */
	public void testDateTimeConstraints() throws Exception {
		List list = getConstraints(2);

		assertCDateTime(list.get(0), "yyyy-mm-dd hh:mm:ss", null, null, null);

		assertCDateTime(list.get(1), "yyyy-mm-dd hh:mm:??", null, null, null);

		assertCDateTime(list.get(2), "yyyy-mm-dd hh:mm:XX", null, null, null);

		assertCDateTime(list.get(3), "yyyy-mm-dd hh:??:XX", null, null, null);

		assertCDateTime(list.get(4), "yyyy-??-?? ??:??:??", null, null, null);		

		assertCDateTime(list.get(5), null, null,
				new String[] { "1983-12-25T22:00:05" }, null);

		assertCDateTime(list.get(6), null, null,
				new String[] { "2000-01-01T00:00:59" }, null);

		assertCDateTime(list.get(7), null, null,
				new String[] { "2000-01-01T00:00:59" },	null);

		assertCDateTime(list.get(8), null, null,
				new String[] { "2000-01-01T00:00:59.105" }, null);

		assertCDateTime(list.get(9), null, null,
				new String[] { "2000-01-01T00:00:59+0000" }, null);

		assertCDateTime(list.get(10), null, null,
				new String[] { "2000-01-01T00:00:59+1200" }, null);

		assertCDateTime(list.get(11), null, null,
				new String[] { "2000-01-01T00:00:59.500+0000" }, null);

		assertCDateTime(list.get(12), null, null,
				new String[] { "2000-01-01T00:00:59.500+1200" }, null);

		assertCDateTime(list.get(13), null, null,
				new String[] { "2000-01-01T00:00:59.000+0000" }, null);

		assertCDateTime(list.get(14), null, null,
				new String[] { "2000-01-01T00:00:59.000+1200" }, null);

		assertCDateTime(list.get(15), null, new Interval<DvDateTime>(
				dateTime("2000-01-01T00:00:00"),
				dateTime("2000-01-02T00:00:00")), null, null);

		assertCDateTime(list.get(16), null,
				lessThan(dateTime("2000-01-01T00:00:00")), null, null);

		assertCDateTime(list.get(17), null,
				lessEqual(dateTime("2000-01-01T00:00:00")), null, null);

		assertCDateTime(list.get(18), null,
				greaterThan(dateTime("2000-01-01T00:00:00")), null, null);

		assertCDateTime(list.get(19), null,
				greaterEqual(dateTime("2000-01-01T00:00:00")), null, null);
		
		assertCDateTime(list.get(40), "yyyy-??-??T??:??:??", null, null, null);
	}

	/**
	 * Tests datetime and partial datetime constraints parsing
	 * 
	 * @throws Exception
	 */
	public void testDateTimeConstraintsWithAssumedValues() throws Exception {
		List list = getConstraints(2);

		assertCDateTime(list.get(20), "yyyy-mm-dd hh:mm:ss", null, null,
				"2006-03-31T01:12:00");

		assertCDateTime(list.get(21), "yyyy-mm-dd hh:mm:??", null, null,
				"2006-03-31T01:12:00");

		assertCDateTime(list.get(22), "yyyy-mm-dd hh:mm:XX", null, null,
				"2006-03-31T01:12:00");

		assertCDateTime(list.get(23), "yyyy-mm-dd hh:??:XX", null, null,
				"2006-03-31T01:12:00");

		assertCDateTime(list.get(24), "yyyy-??-?? ??:??:??", null, null,
				"2006-03-31T01:12:00");

		assertCDateTime(list.get(25), null, null,
				new String[] { "1983-12-25T22:00:05" },
				"2006-03-31T01:12:00");

		assertCDateTime(list.get(26), null, null,
				new String[] { "2000-01-01T00:00:59" },
				"2006-03-31T01:12:00");

		assertCDateTime(list.get(27), null, null,
				new String[] { "2000-01-01T00:00:59.000" },
				"2006-03-31T01:12:00");

		assertCDateTime(list.get(28), null, null,
				new String[] { "2000-01-01T00:00:59.105" },
				"2006-03-31T01:12:00");

		assertCDateTime(list.get(29), null, null,
				new String[] { "2000-01-01T00:00:59+0000" },
				"2006-03-31T01:12:00");

		assertCDateTime(list.get(30), null, null,
				new String[] { "2000-01-01T00:00:59+1200" },
				"2006-03-31T01:12:00");

		assertCDateTime(list.get(31), null, null,
				new String[] { "2000-01-01T00:00:59.500+0000" },
				"2006-03-31T01:12:00");

		assertCDateTime(list.get(32), null, null,
				new String[] { "2000-01-01T00:00:59.500+1200" },
				"2006-03-31T01:12:00");

		assertCDateTime(list.get(33), null, null,
				new String[] { "2000-01-01T00:00:59.000+0000" },
				"2006-03-31T01:12:00");

		assertCDateTime(list.get(34), null, null,
				new String[] { "2000-01-01T00:00:59.000+1200" },
				"2006-03-31T01:12:00");

		assertCDateTime(list.get(35), null, new Interval<DvDateTime>(
				dateTime("2000-01-01T00:00:00"),
				dateTime("2000-01-02T00:00:00")), null,
				"2006-03-31T01:12:00");

		assertCDateTime(list.get(36), null,
				lessThan(dateTime("2000-01-01T00:00:00")), null,
				"2006-03-31T01:12:00");

		assertCDateTime(list.get(37), null,
				lessEqual(dateTime("2000-01-01T00:00:00")), null,
				"2006-03-31T01:12:00");

		assertCDateTime(list.get(38), null,
				greaterThan(dateTime("2000-01-01T00:00:00")), null,
				"2006-03-31T01:12:00");

		assertCDateTime(list.get(39), null,
				greaterEqual(dateTime("2000-01-01T00:00:00")), null,
				"2006-03-31T01:12:00");
	}

	private Interval greaterThan(Comparable value) {
		return new Interval(value, null, false, false);
	}

	private Interval greaterEqual(Comparable value) {
		return new Interval(value, null, true, false);
	}

	private Interval lessThan(Comparable value) {
		return new Interval(null, value, false, false);
	}

	private Interval lessEqual(Comparable value) {
		return new Interval(null, value, false, true);
	}

	private List attributeList;
}
