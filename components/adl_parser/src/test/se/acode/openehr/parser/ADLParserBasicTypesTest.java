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
import org.openehr.rm.datatypes.quantity.datetime.DvDuration;

import java.io.File;
import java.util.*;

/**
 * Test case tests parsing basic data types in ADL files.
 *
 * @author Rong Chen (rong@acode.se)
 * @version 1.0
 */

public class ADLParserBasicTypesTest extends ADLParserTestBase {

    /**
     * Create new test case
     *
     * @param test
     * @throws Exception
     */
    public ADLParserBasicTypesTest(String test) throws Exception {
        super(test);
    }

    /**
     * The fixture set up called before every test method.
     */
    protected void setUp() throws Exception {
        ADLParser parser = new ADLParser(new File(dir,
                "adl-test-entry.basic_types.draft.adl"));
        attributeList = parser.parse().getDefinition().getAttributes();
        //System.out.println("list: " + attributeList);
    }

    /**
     * The fixture clean up called after every test method.
     */
    protected void tearDown() throws Exception {
        attributeList = null;
    }

    /**
     * Tests string constraints parsing and verify the resultant constraint
     *
     * @throws Exception
     */
    public void testStringConstraints() throws Exception {
        List list = getConstraints(0);

        assertCString(list.get(0), null, new String[]{"something"});

        assertCString(list.get(1), "this|that|something else", null);

        assertCString(list.get(2), "cardio.*", null);

        assertCString(list.get(3), "mg|mg/ml|mg/g", null);
    }

    private List getConstraints(int index) {
        CAttribute ca = (CAttribute) attributeList.get(index);
        return ( (CComplexObject) ca.getChildren().get(0) ).getAttributes();
    }

    /**
     * Tests boolean constraints parsing
     *
     * @throws Exception
     */
    public void testBooleanConstraints() throws Exception {
        List list = getConstraints(1);

        assertCBoolean(list.get(0), true, false);

        assertCBoolean(list.get(1), false, true);

        assertCBoolean(list.get(2), true, true);
    }

    /**
     * Tests integer constraints parsing
     *
     * @throws Exception
     */
    public void testIntegerConstraints() throws Exception {
        List list = getConstraints(2);

        assertCInteger(list.get(0), null, new int[]{55});

        assertCInteger(list.get(1), null, new int[]{10, 20, 30});

        assertCInteger(list.get(2),
                new Interval(new Integer(0), new Integer(100)), null);

        assertCInteger(list.get(3),
                greaterThan(new Integer(10)), null);

        assertCInteger(list.get(4),
                lessThan(new Integer(10)), null);

        assertCInteger(list.get(5),
                greaterEqual(new Integer(10)), null);

        assertCInteger(list.get(6),
                lessEqual(new Integer(10)), null);

        assertCInteger(list.get(7),
                new Interval(new Integer(-10), new Integer(-5)), null);
    }

    /**
     * Tests double constraints parsing
     *
     * @throws Exception
     */
    public void testDoubleConstraints() throws Exception {
        List list = getConstraints(3);

        assertCReal(list.get(0), null, new double[]{100.0});

        assertCReal(list.get(1), null, new double[]{10.0, 20.0, 30.0});

        assertCReal(list.get(2),
                new Interval(new Double(0.0), new Double(100.0)), null);

        assertCReal(list.get(3),
                greaterThan(new Double(10.0)), null);

        assertCReal(list.get(4),
                lessThan(new Double(10.0)), null);

        assertCReal(list.get(5),
                greaterEqual(new Double(10.0)), null);

        assertCReal(list.get(6),
                lessEqual(new Double(10.0)), null);

        assertCReal(list.get(7),
                new Interval(new Double(-10.0), new Double(-5.0)), null);
    }

    /**
     * Tests date and partial date constraints parsing
     *
     * @throws Exception
     */
    public void testDateConstraints() throws Exception {
        List list = getConstraints(4);

        assertCDate(list.get(0), "yyyy-mm-dd", null, null, null);

        assertCDate(list.get(1), "yyyy-??-??", null, null, null);

        assertCDate(list.get(2), "yyyy-mm-??", null, null, null);

        assertCDate(list.get(3), "yyyy-??-XX", null, null, null);

        assertCDate(list.get(4), null, null,
                new String[]{"1983-12-25"}, "yyyy-MM-dd");

        assertCDate(list.get(5), null, null,
                new String[]{"2000-01-01"}, "yyyy-MM-dd");

        assertCDate(list.get(6), null,
                new Interval(date("2004-09-20"), date("2004-10-20")),
                null, null);

        assertCDate(list.get(7), null,
                lessThan(date("2004-09-20")), null, null);

        assertCDate(list.get(8), null,
                lessEqual(date("2004-09-20")), null, null);

        assertCDate(list.get(9), null,
                greaterThan(date("2004-09-20")), null, null);

        assertCDate(list.get(10), null,
                greaterEqual(date("2004-09-20")), null, null);
    }

    /**
     * Tests time and partial time constraints parsing
     *
     * @throws Exception
     */
    public void testTimeConstraints() throws Exception {
        List list = getConstraints(5);

        assertCTime(list.get(0), "hh:mm:ss", null, null, null);

        assertCTime(list.get(1), "hh:mm:XX", null, null, null);

        assertCTime(list.get(2), "hh:??:XX", null, null, null);

        assertCTime(list.get(3), "hh:??:??", null, null, null);

        assertCTime(list.get(4), null, null,
                new String[]{"22:00:05"}, "HH:mm:ss");

        assertCTime(list.get(5), null, null,
                new String[]{"00:00:59"}, "HH:mm:ss");

        assertCTime(list.get(6), null, null,
                new String[]{"12:35"}, "HH:mm");

        assertCTime(list.get(7), null, null,
                new String[]{"12:35:45.66"}, "HH:mm:ss.SS");

        assertCTime(list.get(8), null, null,
                new String[]{"12:35:45-0700"}, "HH:mm:ssZ");

        assertCTime(list.get(9), null, null,
                new String[]{"12:35:45+0800"}, "HH:mm:ssZ");

        assertCTime(list.get(10), null, null,
                new String[]{"12:35:45.99-0700"}, "HH:mm:ss.SSZ");

        assertCTime(list.get(11), null, null,
                new String[]{"12:35:45.00+0800"}, "HH:mm:ss.SSZ");

        assertCTime(list.get(12), null, null,
                new String[]{"12:35:45.00+0000"}, "HH:mm:ss.SSZ");

        assertCTime(list.get(13), null, null,
                new String[]{"12:35:45.995-0700"}, "HH:mm:ss.SSSZ");

        assertCTime(list.get(14), null, null,
                new String[]{"12:35:45.001+0800"}, "HH:mm:ss.SSSZ");

        assertCTime(list.get(15), null,
                new Interval(time("12:35"), time("16:35")), null, null);

        assertCTime(list.get(16), null,
                lessThan(time("12:35")), null, null);

        assertCTime(list.get(17), null,
                lessEqual(time("12:35")), null, null);

        assertCTime(list.get(18), null,
                greaterThan(time("12:35")), null, null);

        assertCTime(list.get(19), null,
                greaterEqual(time("12:35")), null, null);

    }

    /**
     * Tests datetime and partial datetime constraints parsing
     *
     * @throws Exception
     */
    public void testDateTimeConstraints() throws Exception {
        List list = getConstraints(6);

        assertCDateTime(list.get(0), "yyyy-mm-dd hh:mm:ss", null, null, null);

        assertCDateTime(list.get(1), "yyyy-mm-dd hh:mm:??", null, null, null);

        assertCDateTime(list.get(2), "yyyy-mm-dd hh:mm:XX", null, null, null);

        assertCDateTime(list.get(3), "yyyy-mm-dd hh:??:XX", null, null, null);

        assertCDateTime(list.get(4), "yyyy-??-?? ??:??:??", null, null, null);

        assertCDateTime(list.get(5), null, null,
                new String[]{"1983-12-25 22:00:05"}, "yyyy-MM-dd HH:mm:ss");

        assertCDateTime(list.get(6), null, null,
                new String[]{"2000-01-01 00:00:59"}, "yyyy-MM-dd HH:mm:ss");

        assertCDateTime(list.get(7), null, null,
                new String[]{"2000-01-01 00:00:59.10"},
                "yyyy-MM-dd HH:mm:ss.SS");

        assertCDateTime(list.get(8), null, null,
                new String[]{"2000-01-01 00:00:59.105"},
                "yyyy-MM-dd HH:mm:ss.SSS");

        assertCDateTime(list.get(9), null, null,
                new String[]{"2000-01-01 00:00:59+0000"},
                "yyyy-MM-dd HH:mm:ssZ");

        assertCDateTime(list.get(10), null, null,
                new String[]{"2000-01-01 00:00:59+1200"},
                "yyyy-MM-dd HH:mm:ssZ");

        assertCDateTime(list.get(11), null, null,
                new String[]{"2000-01-01 00:00:59.50+0000"},
                "yyyy-MM-dd HH:mm:ss.SSZ");

        assertCDateTime(list.get(12), null, null,
                new String[]{"2000-01-01 00:00:59.50+1200"},
                "yyyy-MM-dd HH:mm:ss.SSZ");

        assertCDateTime(list.get(13), null, null,
                new String[]{"2000-01-01 00:00:59.506+0000"},
                "yyyy-MM-dd HH:mm:ss.SSSZ");

        assertCDateTime(list.get(14), null, null,
                new String[]{"2000-01-01 00:00:59.506+1200"},
                "yyyy-MM-dd HH:mm:ss.SSSZ");

        assertCDateTime(list.get(15), null,
                new Interval(dateTime("2000-01-01 00:00:00"),
                        dateTime("2000-01-02 00:00:00")),
                null, null);

        assertCDateTime(list.get(16), null,
                lessThan(dateTime("2000-01-01 00:00:00")),
                null, null);

        assertCDateTime(list.get(17), null,
                lessEqual(dateTime("2000-01-01 00:00:00")),
                null, null);

        assertCDateTime(list.get(18), null,
                greaterThan(dateTime("2000-01-01 00:00:00")),
                null, null);

        assertCDateTime(list.get(19), null,
                greaterEqual(dateTime("2000-01-01 00:00:00")),
                null, null);
    }

    /**
     * Tests duration constraints parsing
     *
     * @throws Exception
     */
    public void testDurationConstraint() throws Exception {
        List list = getConstraints(7);

        assertCDuration(list.get(0), "P0s", null);

        assertCDuration(list.get(1), "P1d", null);

        assertCDuration(list.get(2), "P2h5m0s",
                null);

        assertCDuration(list.get(3), null,
                new Interval(DvDuration.getInstance("P1h55m0s"),
                        DvDuration.getInstance("P2h5m0s")));

        assertCDuration(list.get(4), null,
                new Interval(null, DvDuration.getInstance("P1h"), false, true));
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