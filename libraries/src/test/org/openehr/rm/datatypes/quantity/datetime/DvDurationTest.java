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
 * file:        "$URL$"
 * revision:    "$LastChangedRevision$"
 * last_change: "$LastChangedDate$"
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
    protected void setUp() throws Exception {
    }

    /**
     * The fixture clean up called after every test method.
     */
    protected void tearDown() throws Exception {
    }

    public void testConstructorTakesString() throws Exception {

        // test with wrong format
        String[] value = {
            null, "10d9h8m7s", "10", "", "P10d9h8m7", "P10a8m7s", "P9h8m0h"
        };
        for(int i = 0; i < value.length; i++) {
            assertExceptionTrownByConstructor(value[i]);
        }

        // test with expected values
        value = new String[] {
            "P10d9h8m7s", "P10D9H8M7S",
            "P10d", "P9h", "P8m", "P7s",
            "P10d9h", "P9h8m", "P8m7s", "P10d7s",
            "P10d9h7s", "P9h0m8s"
        };
        int[][] data = {
            {10, 9, 8, 7}, {10, 9, 8, 7},
            {10, 0, 0, 0}, {0, 9, 0, 0}, {0, 0, 8, 0}, {0, 0, 0, 7},
            {10, 9, 0, 0}, {0, 9, 8, 0}, {0, 0, 8, 7}, {10, 0, 0, 7},
            {10, 9, 0, 7}, {0, 9, 0, 8}
        };
        for (int i = 0; i < value.length; i++) {
            assertDuration(value[ i ], data[ i ]);
        }
    }

    private void assertExceptionTrownByConstructor(String value) {
        try {
            DvDuration.getInstance(value);
            fail("Exception should be thrown by constructor");
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }
    }

    private void assertDuration(String value, int[] data) {
        DvDuration d = DvDuration.getInstance(value);
        assertEquals(value + ", days", data[ 0 ], d.getDays());
        assertEquals(value + ", hours", data[ 1 ], d.getHours());
        assertEquals(value + ", minutes", data[ 2 ], d.getMinutes());
        assertEquals(value + ", seconds", data[ 3 ], d.getSeconds());
    }

    public void testAdd() throws Exception {
        DvDuration one = new DvDuration(10, 20, 30, 40, .5);
        DvDuration two = new DvDuration(1, 2, 3, 4, .05);
        DvDuration d = (DvDuration) one.add(two);

        assertEquals("days", 11, d.getDays());
        assertEquals("hours", 22, d.getHours());
        assertEquals("mintues", 33, d.getMinutes());
        assertEquals("seconds", 44, d.getSeconds());
        assertEquals("fractionalSeconds", .55,
                d.getFractionalSeconds(), 0);
    }

    public void testSubtract() throws Exception {
        DvDuration one = new DvDuration(10, 20, 30, 40, .5);
        DvDuration two = new DvDuration(1, 2, 3, 4, .05);
        DvDuration d = (DvDuration) one.subtract(two);

        assertEquals("days", 9, d.getDays());
        assertEquals("hours", 18, d.getHours());
        assertEquals("mintues", 27, d.getMinutes());
        assertEquals("seconds", 36, d.getSeconds());
        assertEquals("fractionalSeconds", .45,
                d.getFractionalSeconds(), 0);
    }

    public void testToString() throws Exception {
        DvDuration d = new DvDuration(10, 20, 30, 40, 0);
        assertEquals("P10D20H30M40S", d.toString());
    }

    public void testCompareTo() throws Exception {
        int[] value = {10, 20, 30, 40, 5};
        DvDuration d1 = toDuration(value);
        DvDuration d2 = toDuration(value);
        assertTrue(d1.compareTo(d2) == 0);
        assertTrue(d2.compareTo(d1) == 0);

        int[][] array = {
            {10, 20, 30, 40, 4}, {10, 20, 30, 30, 6},
            {10, 20, 20, 40, 5}, {10, 10, 50, 40, 5},
            {7, 22, 30, 40, 5}
        };
        for (int i = 0; i < array.length; i++) {
            DvDuration d = toDuration(array[ i ]);
            assertTrue(d1.compareTo(d) > 0);
            assertTrue(d.compareTo(d1) < 0);
        }

    }

    // int[] of { days, hours, mintues, seconds, fractionalSeconds }
    private DvDuration toDuration(int[] values) {
        return new DvDuration(values[ 0 ], values[ 1 ], values[ 2 ],
                values[ 3 ], Double.parseDouble("0." + values[ 4 ]));
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