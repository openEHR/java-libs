/*
 * component:   "openEHR Reference Implementation"
 * description: "Class CPrimitiveObjectTest"
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
 * CPrimitiveObjectTest
 *
 * @author Rong Chen
 * @version 1.0 
 */
package org.openehr.am.archetype.constraintmodel;

import org.openehr.am.archetype.ConstraintTestBase;
import org.openehr.am.archetype.constraintmodel.primitive.*;
import org.openehr.rm.datatypes.basic.DvBoolean;
import org.openehr.rm.datatypes.quantity.datetime.DvDate;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datatypes.quantity.datetime.DvDuration;
import org.openehr.rm.datatypes.quantity.datetime.DvTime;
import org.openehr.rm.support.basic.Interval;

import java.util.Arrays;

public class CPrimitiveObjectTest extends ConstraintTestBase {

    public CPrimitiveObjectTest(String test) {
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

    public void testCreateString() throws Exception {

        // test with pattern constraint
        CString cstring = new CString("this|that|anything", null);
        CPrimitiveObject cpo = new CPrimitiveObject("path", cstring);

        // bad value
        try {
            cpo.createObject("nothing", sysmap(), null);
            fail("exception should be thrown");
        } catch (Exception e) {
            assertTrue("got " + e, e instanceof DVObjectCreationException);
        }

        // right value
        String text = (String) cpo.createObject("that", sysmap(), null);
        assertEquals("value", "that", text);

        // test value list
        cstring = new CString(null,
                Arrays.asList(new String[]{"this", "that", "anything"}));
        // bad value
        try {
            cpo.createObject("nothing", sysmap(), null);
            fail("exception should be thrown");
        } catch (Exception e) {
            assertTrue("got " + e, e instanceof DVObjectCreationException);
        }

        // right value
        text = (String) cpo.createObject("that", sysmap(), null);
        assertEquals("value", "that", text);
    }

    public void testCreateDvDate() throws Exception {

        // test with interval
        Interval<DvDate> interval = new Interval<DvDate>(
                new DvDate("2000-01-01"), new DvDate("2002-10-31"));
        CDate cdate = new CDate(null, interval, null);
        CPrimitiveObject cpo = new CPrimitiveObject("path", cdate);

        // wrong format
        try {
            cpo.createObject("aaaaa", sysmap(), null);
            fail("exception should be thrown");
        } catch (Exception e) {
            assertTrue(e instanceof DVObjectCreationException);
        }

        // value out of range
        try {
            cpo.createObject("2004-05-10", sysmap(), null);
            fail("exception should be thrown");
        } catch (Exception e) {
            assertTrue(e instanceof DVObjectCreationException);
        }

        // right value
        DvDate date = (DvDate) cpo.createObject("2001-01-20", sysmap(), null);
        assertEquals(new DvDate("2001-01-20"), date);
    }

    public void testCreateDvTime() throws Exception {

        // test with interval
        Interval<DvTime> interval = new Interval<DvTime>(
                new DvTime("10:00:00"), new DvTime("20:00:00"));
        CTime ctime = new CTime(null, interval, null);
        CPrimitiveObject cpo = new CPrimitiveObject("path", ctime);

        // wrong format
        try {
            cpo.createObject("aaaaa", sysmap(), null);
            fail("exception should be thrown");
        } catch (Exception e) {
            assertTrue(e instanceof DVObjectCreationException);
        }

        // value out of range
        try {
            cpo.createObject("22:00:00", sysmap(), null);
            fail("exception should be thrown");
        } catch (Exception e) {
            assertTrue(e instanceof DVObjectCreationException);
        }

        // right value
        DvTime time = (DvTime) cpo.createObject("16:00:00", sysmap(), null);
        assertEquals(new DvTime("16:00:00"), time);
    }

    public void testCreateDvDateTime() throws Exception {

        // test with interval
        Interval<DvDateTime> interval = new Interval<DvDateTime>(
                new DvDateTime("2000-01-01 10:00:00"),
                new DvDateTime("2002-10-31 23:59:59"));
        CDateTime cdatetime = new CDateTime(null, interval, null);
        CPrimitiveObject cpo = new CPrimitiveObject("path", cdatetime);

        // wrong format
        try {
            cpo.createObject("aaaaa", sysmap(), null);
            fail("exception should be thrown");
        } catch (Exception e) {
            assertTrue(e instanceof DVObjectCreationException);
        }

        // value out of range
        try {
            cpo.createObject("2004-05-10 01:00:00", sysmap(),
                    null);
            fail("exception should be thrown");
        } catch (Exception e) {
            assertTrue(e instanceof DVObjectCreationException);
        }

        // right value
        DvDateTime datetime = (DvDateTime) cpo.createObject(
                "2001-01-20 00:00:00", sysmap(), null);
        assertEquals(new DvDateTime("2001-01-20 00:00:00"), datetime);
    }

    public void testCreateDvDuration() throws Exception {

         // test with interval
         Interval<DvDuration> interval = new Interval<DvDuration>(
                 DvDuration.getInstance("P1h"),
                 DvDuration.getInstance("P3h"));
         CDuration cduration = new CDuration(null, interval);
         CPrimitiveObject cpo = new CPrimitiveObject("path", cduration);

         // wrong format
         try {
             cpo.createObject("aaaaa", sysmap(), null);
             fail("exception should be thrown");
         } catch (Exception e) {
             assertTrue(e instanceof DVObjectCreationException);
         }

         // value out of range
         try {
             cpo.createObject("P4h", sysmap(),
                     null);
             fail("exception should be thrown");
         } catch (Exception e) {
             assertTrue(e instanceof DVObjectCreationException);
         }

         // right value
         DvDuration duration = (DvDuration) cpo.createObject(
                 "P2h", sysmap(), null);
         assertEquals(DvDuration.getInstance("P2h"), duration);
     }

    public void testDvBoolean() throws Exception {

        // both allowed
        CBoolean cboolean = new CBoolean(true, true);
        CPrimitiveObject cpo = new CPrimitiveObject("path", cboolean);
        DvBoolean b = (DvBoolean) cpo.createObject("true", sysmap(), null);
        assertTrue(b.value());
        b = (DvBoolean) cpo.createObject("false", sysmap(), null);
        assertFalse(b.value());

        // true not allowed
        cboolean = new CBoolean(false, true);
        cpo = new CPrimitiveObject("path", cboolean);
        try {
            cpo.createObject("true", sysmap(), null);
            fail("exception should be thrown");
        } catch(Exception e) {
            assertTrue(e instanceof DVObjectCreationException);
        }
        b = (DvBoolean) cpo.createObject("false", sysmap(),
                null);
        assertFalse(b.value());

        // false not allowed
        cboolean = new CBoolean(false, true);
        cpo = new CPrimitiveObject("path", cboolean);
        try {
            cpo.createObject("true", sysmap(), null);
            fail("exception should be thrown");
        } catch(Exception e) {
            assertTrue(e instanceof DVObjectCreationException);
        }
        b = (DvBoolean) cpo.createObject("false", sysmap(),
                null);
        assertFalse(b.value());

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
 *  The Original Code is CPrimitiveObjectTest.java
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