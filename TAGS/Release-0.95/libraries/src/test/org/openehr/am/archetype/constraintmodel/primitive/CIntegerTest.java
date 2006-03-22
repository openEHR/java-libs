/*
 * component:   "openEHR Reference Implementation"
 * description: "Class CIntegerTest"
 * keywords:    "archetype"
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
 * CIntegerTest
 *
 * @author Rong Chen
 * @version 1.0 
 */
package org.openehr.am.archetype.constraintmodel.primitive;

import junit.framework.TestCase;
import org.openehr.rm.support.basic.Interval;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class CIntegerTest extends TestCase {

    public CIntegerTest(String test) {
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

    /**
     * Tests validValue() with a list of integer values or an interval
     */
    public void testValidValue() {
        CInteger ci = new CInteger(new Interval<Integer>(null, new Integer(10),
                false, false), null);
        assertTrue(ci.validValue(new Integer(9)));
        assertTrue(ci.validValue(new Integer(-10)));
        assertTrue(!ci.validValue(new Integer(10)));
        assertTrue(!ci.validValue(new Integer(11)));

        ci = new CInteger(new Interval<Integer>(new Integer(1), null, true,
                false), null);
        assertTrue(ci.validValue(new Integer(1)));
        assertTrue(ci.validValue(new Integer(2)));
        assertTrue(!ci.validValue(new Integer(0)));
        assertTrue(!ci.validValue(new Integer(-1)));

        Integer[] values = {new Integer(-2), new Integer(0), new Integer(2)};
        ci = new CInteger(null, Arrays.asList(values));
        assertTrue(ci.validValue(new Integer(-2)));
        assertTrue(ci.validValue(new Integer(2)));
        assertTrue(ci.validValue(new Integer(0)));
        assertTrue(!ci.validValue(new Integer(-1)));
        assertTrue(!ci.validValue(new Integer(1)));
    }

    /**
     * Tests create object with valid input values on CInteger with interval
     * of allowed values
     */
    public void testCreateObjectWithValidValuesOnInterval() {
        // create a CInteger with interval
        Interval<Integer> interval = new Interval<Integer>(new Integer(1),
                new Integer(10));
        CInteger cinteger = new CInteger(interval, null);

        assertIntegerCreated(cinteger, "1");
        assertIntegerCreated(cinteger, "2");
        assertIntegerCreated(cinteger, "8");
        assertIntegerCreated(cinteger, "10");
    }

    /**
     * Tests create object with valid input values on CInteger with list
     * of allowed values
     */
    public void testCreateObjectWithValidValuesOnList() {
        // create a CInteger with list of integer values
        List<Integer> list = new ArrayList<Integer>();
        list.add(new Integer(10));
        list.add(new Integer(11));
        list.add(new Integer(12));
        CInteger cinteger = new CInteger(null, list);

        assertIntegerCreated(cinteger, "10");
        assertIntegerCreated(cinteger, "11");
        assertIntegerCreated(cinteger, "12");
    }

    /**
     * Asserts an expected integer value is created for given CInteger constraint
     *
     * @param cinteger the CInteger to use for integer value creation
     * @param input the input value in string format
     */
    private void assertIntegerCreated(CInteger cinteger, String input) {
        try {
            Integer d = cinteger.createObject(input);
            assertEquals(Integer.valueOf(input), d);
        } catch (Exception e) {
            fail("unexpected exception caught during value creation, " + e);
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
 *  The Original Code is CIntegerTest.java
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