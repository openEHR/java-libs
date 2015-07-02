/*
 * component:   "openEHR Reference Implementation"
 * description: "Class DvDurationTest"
 * keywords:    "unit test"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/BRANCHES/RM-1.0-update/libraries/src/test/org/openehr/rm/datatypes/quantity/datetime/DvDurationTest.java $"
 * revision:    "$LastChangedRevision: 50 $"
 * last_change: "$LastChangedDate: 2006-08-10 13:21:46 +0200 (Thu, 10 Aug 2006) $"
 */
/**
 * DurationTest
 *
 * @author Rong Chen
 * @version 1.0 
 */
package org.openehr.rm.datatypes.quantity.datetime;

import junit.framework.TestCase;

public class DvDurationTest extends TestCase {

    public DvDurationTest(String test) {
        super(test);
    }

    /**
     * The fixture set up called before every test method.
     */
    @Override
    protected void setUp() throws Exception {
    }

    /**
     * The fixture clean up called after every test method.
     */
    @Override
    protected void tearDown() throws Exception {
    }

    public void testConstructorTakesString() throws Exception {

        // test with wrong format
        String[] value = {
            null, "P10D9H8m7s", "10", "", "P10D11WT9h8M7s", "P10a8m7s", "T9H8m0,000s",
            "P-1y-4W", "-P0Y0DT-7H", "PT", "P0DT" //once T is there, must be followed by at least one time ele
        };
        for(int i = 0; i < value.length; i++) {
            assertExceptionThrownByConstructor(value[i]);
        }

        // test with expected values
        value = new String[] {
            "P0Y12M32W10DT9H8m7.898s", "P10DT9H8m7s",
            "P10D", "PT9H", "PT8m", "PT7s",
            "P10DT9H", "PT9H8m", "PT8m7s", "P10DT7s",
            "P10DT9H7s", "PT9H0M8S", "P1Y2M3W4D",
            "P9y6dT2.99s", "P35WT45H", "P20M33DT79m",
            "PT0,19s", "-P1Y2m3W4dT5H6m7,8s",
            "-PT0H0m56s", "P", "P0Y", "P0DT0s", "PT0H",
            "PT0s"
        };
        int[][] data = {
            {0, 12, 32, 10, 9, 8, 7, 898}, {0, 0, 0, 10, 9, 8, 7, 0},
            {0, 0, 0, 10, 0, 0, 0, 0}, {0, 0, 0, 0, 9, 0, 0, 0}, {0, 0, 0, 0, 0, 8, 0, 0},
            {0, 0, 0, 0, 0, 0, 7, 0}, {0, 0, 0, 10, 9, 0, 0, 0},
            {0, 0, 0, 0, 9, 8, 0, 0}, {0, 0, 0, 0, 0, 8, 7, 0},
            {0, 0, 0, 10, 0, 0, 7, 0}, {0, 0, 0, 10, 9, 0, 7, 0},
            {0, 0, 0, 0, 9, 0, 8, 0}, {1, 2, 3, 4, 0, 0, 0, 0},
            {9, 0, 0, 6, 0, 0, 2, 990}, {0, 0, 35, 0, 45, 0, 0, 0},
            {0, 20, 0, 33, 0, 79, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 190},
            {-1, -2, -3, -4, -5, -6, -7, -800}, {0, 0, 0, 0, 0, 0, -56, 0},
            {0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0}
        };
        for (int i = 0; i < value.length; i++) {
            assertDuration(value[ i ], data[ i ]);
        }
    }

    private void assertExceptionThrownByConstructor(String value) {
        try {
            DvDuration.getInstance(value);
            fail("Exception should be thrown by constructor");
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }
    }

    private void assertDuration(String value, int[] data) {
        DvDuration d = DvDuration.getInstance(value);
        assertEquals(value + ", years", data[ 0 ], d.getYears());
        assertEquals(value + ", months", data[ 1 ], d.getMonths());
        assertEquals(value + ", weeks", data[ 2 ], d.getWeeks());
        assertEquals(value + ", days", data[ 3 ], d.getDays());
        assertEquals(value + ", hours", data[ 4 ], d.getHours());
        assertEquals(value + ", minutes", data[ 5 ], d.getMinutes());
        assertEquals(value + ", seconds", data[ 6 ], d.getSeconds());
        assertEquals(value + ", fseconds", data[ 7 ], (int)(d.getFractionalSeconds()*1000.0));
    }

    public void testConstructorTakesIntegers() throws Exception {
        int[][] data = {
            {0, 10, 3, 1, 19, 8, 37, 857}, {0, 0, 0, 1, 0, 14, 2, 0},
            {0, 13, 56, 33, 25, 67, 77, 0}, {0, -1, -2, 0, -9, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 10, 9, 0, 0, 0},
        };
        for(int i = 0; i < data.length; i++) {
            DvDuration d = toDuration(data[i]);
            assertEquals("years", data[i][ 0 ], d.getYears());
            assertEquals("months", data[i][ 1 ], d.getMonths());
            assertEquals("weeks", data[i][ 2 ], d.getWeeks());
            assertEquals("days", data[i][ 3 ], d.getDays());
            assertEquals("hours", data[i][ 4 ], d.getHours());
            assertEquals("minutes", data[i][ 5 ], d.getMinutes());
            assertEquals("seconds", data[i][ 6 ], d.getSeconds());
            assertEquals("fseconds", data[i][ 7 ], (int)(d.getFractionalSeconds()*1000.0));
        }
        int[][] fData = {
            {0, 0, 3, 1, 0, 8, 37, 1857}, {0, 0, 0, -1, 0, 14, 2, 0},
        };
        for(int i = 0; i < fData.length; i++) {
            try {
                DvDuration d = toDuration(fData[i]);
                fail("Exception should be thrown by constructor");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }
        }

    }
    public void testAdd() throws Exception {
        DvDuration one = new DvDuration(0, 0, 0, 10, 20, 30, 40, .5);
        DvDuration two = new DvDuration(0, 0, 1, 1, 2, 3, 4, .05);
        DvDuration d = (DvDuration) one.add(two);

        assertEquals("years", 0, d.getYears());
        assertEquals("months", 0, d.getMonths());
        assertEquals("weeks", 2, d.getWeeks()); //10d + 1W1d = 18d
        assertEquals("days", 4, d.getDays()); //18d = 2W4d
        assertEquals("hours", 22, d.getHours());
        assertEquals("mintues", 33, d.getMinutes());
        assertEquals("seconds", 44, d.getSeconds());
        assertEquals("fractionalSeconds", .55,
                d.getFractionalSeconds());

        DvDuration three = new DvDuration("P1y2WT2H10m1,60S");
        d = (DvDuration)d.add(three);
        assertEquals("years", 1, d.getYears());
        assertEquals("months", 1, d.getMonths()); //33d = 1M2d
        assertEquals("weeks", 0, d.getWeeks());
        assertEquals("days", 2, d.getDays());
        assertEquals("hours", 0, d.getHours());
        assertEquals("mintues", 43, d.getMinutes());
        assertEquals("seconds", 46, d.getSeconds());
        assertEquals("fractionalSeconds", .15,
                d.getFractionalSeconds());

    }

    public void testSubtract() throws Exception {
        DvDuration one = new DvDuration(0, 0, 0, 10, 20, 30, 40, .5);
        DvDuration two = new DvDuration(0, 0, 1, 0, 2, 3, 4, .05);
        DvDuration d = (DvDuration) one.subtract(two);
        //System.out.println("after subtraction: " + d.toString());
        assertEquals("years", 0, d.getYears());
        assertEquals("months", 0, d.getMonths());
        assertEquals("weeks", 0, d.getWeeks());
        assertEquals("days", 3, d.getDays());
        assertEquals("hours", 18, d.getHours());
        assertEquals("mintues", 27, d.getMinutes());
        assertEquals("seconds", 36, d.getSeconds());
        assertEquals("fractionalSeconds", .45,
                d.getFractionalSeconds());
        DvDuration three = new DvDuration("P5D");
        d = (DvDuration) d.subtract(three);
        //System.out.println("after second subtraction: " + d.toString());
        assertEquals("years", 0, d.getYears());
        assertEquals("months", 0, d.getMonths());
        assertEquals("weeks", 0, d.getWeeks());
        assertEquals("days", -1, d.getDays());
        assertEquals("hours", -5, d.getHours());
        assertEquals("mintues", -32, d.getMinutes());
        assertEquals("seconds", -23, d.getSeconds());
        assertEquals("fractionalSeconds", -0.55,
                d.getFractionalSeconds());
        assertEquals("value", "-P1DT5H32M23,550S", d.toString());
    }

    public void testToString() throws Exception {
        //constructor takes string, integers, after addition/subtraction
        //TODO!!!
        int[][] data = {
            {0, 0, 0, 10, 20, 30, 40, 0}, {0, 0, 0, 1, 0, 14, 2, 0},
            {0, 13, 56, 33, 25, 67, 77, 0}, {0, -1, -2, 0, -9, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0}, {0, 11, 23, 10, 9, 5, 0, 123}
        };
        String[] strings = {
           "P10DT20H30M40S", "P1DT14M2S", "P13M56W33DT25H67M77S",
           "-P1M2WT9H", "PT0S", "P11M23W10DT9H5M0,123S"
        };
        for(int i = 0; i < data.length; i++) {
            DvDuration d = toDuration(data[i]);
            assertEquals(strings[i], d.toString());
        }

        String[] strValues = {
           "P10DT20H30M40.66S", "P16M45DT60M2S", "P13M56W33DT2H6M5.0S",
           "-P1M2WT9H", "PT0S", "P11M23W10DT9H5M0,123S", "P", "P0Y"
        };
        for(int i = 0; i < strValues.length; i++) {
            DvDuration d = new DvDuration(strValues[i]);
            assertEquals(strValues[i], d.toString());

            DvDuration fromInstanceD = DvDuration.getInstance(strValues[i]); // This may be slightly different therefore we should test this as well.
            assertEquals(strValues[i], fromInstanceD.toString());
        }
    }

    public void testCompareTo() throws Exception {
        int[] value = {1, 13, 45, 10, 20, 30, 40, 500};//D=(2*365)+31+(45*7)+10
        DvDuration d1 = toDuration(value);
        DvDuration d2 = toDuration(value);
        assertTrue(d1.compareTo(d2) == 0);
        assertTrue(d2.compareTo(d1) == 0);

        int[][] array = {
            {1, 13, 45, 10, 20, 30, 40, 499}, //the next 3 sets are all equivalent to the first
            {2, 0, 0, 356, 20, 30, 40, 499}, {2, 11, 0, 21, 20, 0, 220, 499},            
            {2, 11, 3, 0, 20, 30, 40, 499},
            {-2, -13, -45, -10, -20, -20, 0, -5}, {0, 0, 0, 10, 10, 50, 40, 5},
        };
        for (int i = 0; i < array.length; i++) {
            DvDuration d = toDuration(array[ i ]);
            assertTrue(d1.compareTo(d) > 0);
            assertTrue(d.compareTo(d1) < 0);
        }

    }
    
    public void testEquals() throws Exception {
        
        assertEquals(new DvDuration("P1Y2M3W"), new DvDuration(1, 2, 3, 0, 0, 0, 0, 0.0));
        assertEquals(new DvDuration("-P1Y2M3DT25H6S"), new DvDuration(-1, -2, 0, -3, -25, 0, -6, 0.0));
        assertEquals(new DvDuration("PT6H220m89.719S"), new DvDuration(0, 0, 0, 0, 6, 220, 89, .719));
        assertEquals(new DvDuration("P"), new DvDuration(0, 0, 0, 0, 0, 0, 0, 0.0));
        
    }

    // int[] of { days, hours, mintues, seconds, fractionalSeconds }
    private DvDuration toDuration(int[] values) throws Exception {
        return new DvDuration(values[ 0 ], values[ 1 ], values[ 2 ],
                values[ 3 ], values[ 4 ], values[ 5 ], values[ 6 ],
                values[ 7 ]/10E2);
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
 *  The Original Code is DvDurationTest.java
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