/*
 * component:   "openEHR Reference Implementation"
 * description: "Class CRealTest"
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
 * CRealTest
 *
 * @author Rong Chen
 * @version 1.0 
 */
package org.openehr.am.archetype.constraintmodel.primitive;

import junit.framework.TestCase;
import org.openehr.rm.support.basic.Interval;
import org.openehr.am.archetype.constraintmodel.DVObjectCreationException;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class CRealTest extends TestCase {

    public CRealTest(String test) {
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

    public void testValidValue() throws Exception {
        CReal cr = new CReal(new Interval<Double>(null, new Double(10),
                false, false), null);
        assertTrue(cr.validValue(new Double(9)));
        assertTrue(cr.validValue(new Double(-10)));
        assertTrue(!cr.validValue(new Double(10)));
        assertTrue(!cr.validValue(new Double(11)));

        cr = new CReal(new Interval<Double>(new Double(1),
                null, true, false), null);
        assertTrue(cr.validValue(new Double(1)));
        assertTrue(cr.validValue(new Double(2)));
        assertTrue(!cr.validValue(new Double(0)));
        assertTrue(!cr.validValue(new Double(-1)));

        Double[] values = {new Double(-2), new Double(0), new Double(2)};
        cr = new CReal(null, Arrays.asList(values));
        assertTrue(cr.validValue(new Double(-2)));
        assertTrue(cr.validValue(new Double(2)));
        assertTrue(cr.validValue(new Double(0)));
        assertTrue(!cr.validValue(new Double(-1)));
        assertTrue(!cr.validValue(new Double(1)));
    }

    /**
     * Test create object with input values in bad format
     */
    public void testCreateObjectWithBadFormatInput() {
        assertExceptionThrownForBadFormat("", "empty input");
        assertExceptionThrownForBadFormat(null, "null input");
        assertExceptionThrownForBadFormat("a string", "wrong format input");
    }

    /**
     * Tests create object with invalid input values on CReal with interval
     * of allowed values
     */
    public void testCreateObjectWithInvalidValuesOnInterval() {
        // create a CReal with interval
        Interval<Double> interval = new Interval<Double>(new Double(1.0),
                new Double(12.5));
        CReal creal = new CReal(interval, null);

        assertExceptionThrownForBadValue(creal, "-1", "too less");
        assertExceptionThrownForBadValue(creal, "0.0", "too less");
        assertExceptionThrownForBadValue(creal, "13", "too great");
        assertExceptionThrownForBadValue(creal, "100", "too great");
    }


    /**
     * Test create object with invalid input values on CReal with a list
     * of double values
     */
    public void testCreateObjectWithInvalidValuesOnList() {
        // create a CReal with list of double values
        List<Double> list = new ArrayList<Double>();
        list.add(new Double(11.0));
        list.add(new Double(12.0));
        CReal creal = new CReal(null, list);

        assertExceptionThrownForBadValue(creal, "0.0", "not in the list");
        assertExceptionThrownForBadValue(creal, "10.0", "not in the list");
        assertExceptionThrownForBadValue(creal, "20.0", "not in the list");
    }
    
    /**
     * Tests create object with valid input values on CReal with interval
     * of allowed values
     */
    public void testCreateObjectWithValidValuesOnInterval() {
        // create a CReal with interval
        Interval<Double> interval = new Interval<Double>(new Double(1.0),
                new Double(12.5));
        CReal creal = new CReal(interval, null);

        assertDoubleCreated(creal, "1.0");
        assertDoubleCreated(creal, "2.0");
        assertDoubleCreated(creal, "12.0");
        assertDoubleCreated(creal, "12.5");
    }
    
    /**
     * Tests create object with valid input values on CReal with list
     * of allowed values
     */
    public void testCreateObjectWithValidValuesOnList() {
        // create a CReal with list of double values
        List<Double> list = new ArrayList<Double>();
        list.add(new Double(10.0));
        list.add(new Double(11.0));
        list.add(new Double(12.0));
        list.add(new Double(12.5));
        CReal creal = new CReal(null, list);

        assertDoubleCreated(creal, "10.0");
        assertDoubleCreated(creal, "11.0");
        assertDoubleCreated(creal, "12.0");
        assertDoubleCreated(creal, "12.5");
    }

    /**
     * Asserts that bad format exception is thrown on given input
     *
     * @param input
     * @param message
     */
    private void assertExceptionThrownForBadFormat(String input,
                                                   String message) {
        CReal cr = new CReal(new Interval<Double>(null, new Double(10),
                false, false), null);
        try {
            cr.createObject(input);
            fail("exception should be thrown for bad format: " + message);
        } catch (Exception e) {
            assertTrue("expected exception for bad format, but got " + e,
                    e.equals(DVObjectCreationException.BAD_FORMAT));
        }
    }

    /**
     * Asserts that bad format exception is thrown on given input
     *
     * @param creal   the real constraint to use
     * @param input   the input value
     * @param message the message to display in the error message
     */
    private void assertExceptionThrownForBadValue(CReal creal,
                                                  String input,
                                                  String message) {
        try {
            creal.createObject(input);
            fail("exception should be thrown for bad value: " + message);
        } catch (Exception e) {
            assertTrue("expected exception for bad value, but got " + e,
                    e.equals(DVObjectCreationException.BAD_VALUE));
        }
    }

    /**
     * Asserts an expected double value is created for given CReal constraint
     *
     * @param creal    the CReal to use for double value creation
     * @param input    the input value in string format
     */
    private void assertDoubleCreated(CReal creal, String input) {
        try {
            Double d = creal.createObject(input);
            assertEquals(Double.valueOf(input), d);
        } catch(Exception e) {
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
 *  The Original Code is CRealTest.java
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