/*
 * component:   "openEHR Reference Implementation"
 * description: "Class DvQuantityTest"
 * keywords:    "unit test"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/BRANCHES/RM-1.0-update/libraries/src/test/org/openehr/rm/datatypes/quantity/DvQuantityTest.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 23:20:08 +0200 (Wed, 12 Oct 2005) $"
 */
/**
 * QuantityTest
 *
 * @author Rong Chen
 * @version 1.0
 */
package org.openehr.rm.datatypes.quantity;

import junit.framework.TestCase;

import org.openehr.rm.datatypes.basic.DataValue;
import org.openehr.rm.support.measurement.MeasurementService;
import org.openehr.rm.support.measurement.TestMeasurementService;

import java.text.DecimalFormatSymbols;

public class DvQuantityTest extends TestCase {

    public DvQuantityTest(String test) {
        super(test);
    }

    // also test equals() from both Quantified and Measurable
    public void testEquals() throws Exception {
        DvQuantity q1 = new DvQuantity("mg", 10, 2, ms);
        DvQuantity q2 = new DvQuantity("mg", 10, 2, ms);
        assertTrue(q1 + " equals " + q2, q1.equals(q2));

        q1 = new DvQuantity("mg", 10, ms);
        q2 = new DvQuantity("mg", 10, ms);
        assertTrue(q1 + " equals " + q2, q1.equals(q2));

        q1 = new DvQuantity(10);
        q2 = new DvQuantity(10);
        assertTrue(q1 + " equals " + q2, q1.equals(q2));

        q1 = new DvQuantity(10.5);
        q2 = new DvQuantity(10.5);
        assertTrue(q1 + " equals " + q2, q1.equals(q2));

        // missing precision
        q1 = new DvQuantity("mg", 10, 2, ms);
        q2 = new DvQuantity("mg", 10, ms);
        assertFalse(q1 + " not equals " + q2, q1.equals(q2));

        // missing units
        q1 = new DvQuantity(10);
        q2 = new DvQuantity("mg", 10, ms);
        assertFalse(q1 + " not equals " + q2, q1.equals(q2));

        // diff precision
        q1 = new DvQuantity("mg", 10, 2, ms);
        q2 = new DvQuantity("mg", 10, 3, ms);
        assertFalse(q1 + " not equals " + q2, q1.equals(q2));

        // diff units
        q1 = new DvQuantity("kg", 10, 2, ms);
        q2 = new DvQuantity("mg", 10, 2, ms);
        assertFalse(q1 + " not equals " + q2, q1.equals(q2));

        // diff getMagnitude
        q1 = new DvQuantity("mg", 12, 2, ms);
        q2 = new DvQuantity("mg", 10, 2, ms);
        assertFalse(q1 + " not equals " + q2, q1.equals(q2));
    }

    public void testToString() throws Exception {
        DvQuantity q = new DvQuantity("kg", 78, 2, ms);
        String expected = "78.00,kg";
        assertEquals(expected, q.toString());

        q = new DvQuantity("kg", 78, ms);
        expected = "78,kg";
        assertEquals(expected, q.toString());

        q = new DvQuantity(78);
        expected = "78";
        assertEquals(expected, q.toString());

        q = new DvQuantity(78.9);
        expected = "79";
        assertEquals(expected, q.toString());

        q = new DvQuantity(78.5);
        expected = "78";
        assertEquals(expected, q.toString());

        q = new DvQuantity(78.5, 3, ms);
        expected = "78.500";
        assertEquals(expected, q.toString());
    }

    public void testParseQuantityWithPrecision() throws Exception {
    	String value = "78.500,kg";
    	DvQuantity expected = new DvQuantity("kg", 78.5, 3);
    	DvQuantity q = expected.parse(value);
    	assertEquals("failed to parse quantity with precision", expected, q);
    }

    public void testParseQuantityWithoutPrecision() throws Exception {
    	String value = "78,kg";
    	DvQuantity expected = new DvQuantity("kg", 78, 0);
    	DvQuantity q = expected.parse(value);
    	assertEquals("failed to parse quantity without precision", expected, q);
    }

    public void testParseQuantityWithoutUnit() throws Exception {
      String value = "78";
      DvQuantity expected = new DvQuantity(78);
      DataValue q = expected.parse(value);
      assertEquals("failed to parse quantity without unit", expected, q);
    }

    public void testDataValueParseQuantityWithoutPrecision() throws Exception {
    	String value = "78,kg";
    	DvQuantity expected = new DvQuantity("kg", 78, 0);
    	DataValue q = DataValue.parseValue("DV_QUANTITY," + value);
    	assertEquals("failed to parse quantity as DataValue without precision", expected, q);
    }

    public void testCreateWithUnlimitedPrecision() {
    	try {
    		new DvQuantity("mg", 12, -1, ms);
    	} catch(Exception e) {
    		fail("failed to create DvQuantity with unlimited precision");
    	}
    }

    private MeasurementService ms = new TestMeasurementService();
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
 *  The Original Code is DvQuantityTest.java
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