/*
 * component:   "openEHR Reference Implementation"
 * description: "Class IntervalTest"
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
 * IntervalTest
 *
 * @author Rong Chen
 * @version 1.0 
 */
package org.openehr.rm.support.basic;

import junit.framework.TestCase;

public class IntervalTest extends TestCase {

    public IntervalTest(String test) {
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

    public void testConstructor() throws Exception {
        try {
            new Interval<Integer>(new Integer(10), new Integer(1));
            fail("should throw illegal argument exception");
        } catch (Exception ignored) {
        }
    }

    public void testHas() throws Exception {
        // array of { lower(0:unbounded), upper(0:unbounded),
        //            lowerInclusive, upperInclusive (1:true, 0:false)
        //              testValue, expected (1:true, 0:false) }
        int[][] data = {
            // inclusive boundaries
            {1, 8, 1, 1, 2, 1},
            {1, 8, 1, 1, 1, 1},
            {1, 8, 1, 1, 8, 1},
            {1, 8, 1, 1, 0, 0},
            {1, 8, 1, 1, 9, 0},
            {0, 8, 0, 1, 4, 1},
            {0, 8, 0, 1, -1, 1},
            {0, 8, 0, 1, 9, 0},
            {1, 0, 1, 0, 4, 1},
            {1, 0, 1, 0, 1, 1},
            {1, 0, 1, 0, -1, 0},
            // exclusive boundaries
            {1, 8, 0, 0, 2, 1},
            {1, 8, 0, 0, 1, 0},
            {1, 8, 0, 0, 8, 0},
            {1, 8, 0, 0, 0, 0},
            {1, 8, 0, 0, 9, 0},
            {0, 8, 0, 0, 4, 1},
            {0, 8, 0, 0, -1, 1},
            {0, 8, 0, 0, 9, 0},
            {1, 0, 0, 0, 4, 1},
            {1, 0, 0, 0, 1, 0},
            {1, 0, 0, 0, -1, 0}
        };
        for (int i = 0; i < data.length; i++) {
            Interval<Integer> iv = new Interval<Integer>(popInt(data[ i ][ 0 ]),
                    popInt(data[ i ][ 1 ]), data[i][2] == 1, data[i][3] == 1);
            boolean actual = iv.has(new Integer(data[ i ][ 4 ]));
            boolean expected = data[ i ][ 5 ] == 1;
            assertTrue("failed at " + testString(data[ i ]),
                    actual == expected);
        }
    }

    // return null if value 0
    private Integer popInt(int value) {
        if (value == 0) {
            return null;
        }
        return new Integer(value);
    }

    private String testString(int[] row) {
        return "(" + row[ 0 ] + ", " + row[ 1 ] + ") has " +
                row[ 2 ]
                + ": " + ( row[ 3 ] == 1 );
    }

    public void testEquals() throws Exception {
        Interval<Integer> interval = new Interval<Integer>(new Integer(-1), new Integer(10));
        Interval<Integer> interval2 = new Interval<Integer>(new Integer(-1), new Integer(10));
        assertEquals(interval, interval2);

        interval = new Interval<Integer>(new Integer(-1), new Integer(10), true, false);
        interval2 = new Interval<Integer>(new Integer(-1), new Integer(10), true, false);
        assertEquals(interval, interval2);

        interval = new Interval<Integer>(new Integer(-1), new Integer(10), true, false);
        interval2 = new Interval<Integer>(new Integer(-1), new Integer(10), true, true);
        assertFalse(interval.equals(interval2));

        // not equals expected
        int[][] data = {
            {-1, 9}, {2, 10}, {0, 10}, {-1, 0}, {0, 0}
        };

        for(int i = 0; i < data.length; i++) {
            interval2 = new Interval<Integer>(popInt(data[i][0]),
                    popInt(data[i][1]));
            assertFalse(interval2.toString(), interval.equals(interval2));
            assertFalse(interval2.toString(), interval2.equals(interval));
        }
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
 *  The Original Code is IntervalTest.java
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