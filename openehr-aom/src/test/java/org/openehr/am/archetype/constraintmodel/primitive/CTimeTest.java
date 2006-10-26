/*
 * component:   "openEHR Reference Implementation"
 * description: "Class CTimeTest"
 * keywords:    "archetype"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/test/org/openehr/am/archetype/constraintmodel/primitive/CTimeTest.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 23:20:08 +0200 (Wed, 12 Oct 2005) $"
 */
/**
 * CTimeTest
 *
 * @author Rong Chen
 * @version 1.0 
 */
package org.openehr.am.archetype.constraintmodel.primitive;

import junit.framework.TestCase;
import org.openehr.rm.datatypes.quantity.datetime.DvTime;
import org.openehr.rm.support.basic.Interval;

import java.util.*;

public class CTimeTest extends TestCase {

    public CTimeTest(String test) {
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

    public void testValidateByTimeInterval() throws Exception {
        CTime ct = new CTime(null, new Interval<DvTime>(dvTime(
                "10:20:30"), dvTime("12:10:45")), null);

        String[] values = {
            "10:20:30", "10:20:31", "10:21:30", "11:20:30",
            "12:10:45", "12:10:44", "12:09:45", "11:10:45"
        };
        assertValues(ct, values, true);

        values = new String[] {
            "10:20:29", "10:19:30", "09:20:30",
            "12:10:46", "12:11:45", "13:10:45"
        };
        assertValues(ct, values, false);
    }

    public void testValidateByTimeList() throws Exception {
        String[] values = {
            "00:01:10", "05:10:59", "06:20:50", "22:00:00",
            "23:59:59", "00:00:00"
        };
        CTime ct = new CTime(null, null, dvTimeList(values));
        assertValues(ct, values, true);

        values = new String[] {
            "00:01:11", "05:11:59", "07:20:50", "23:00:00",
            "23:59:58", "00:00:01"
        };
        assertValues(ct, values, false);
    }

    public void testAssignedValue() throws Exception {
        CTime cd = new CTime(null,
                new Interval<DvTime>(dvTime("00:00:00"),
                        dvTime("10:00:00")), null);
        assertFalse(cd.hasAssignedValue());
        assertTrue(cd.assignedValue() == null);

        cd = new CTime("HH:mm:ss", null, null);
        assertFalse(cd.hasAssignedValue());
        assertTrue(cd.assignedValue() == null);

        List<DvTime> list = new ArrayList<DvTime>();
        list.add(new DvTime("08:00:00"));
        cd = new CTime(null, null, list);
        assertTrue(cd.hasAssignedValue());
        assertEquals(new DvTime("08:00:00"),
                cd.assignedValue());
    }
    
    private void assertValues(CTime ct, String[] values,
                              boolean expected) throws Exception {
        for(int i = 0; i < values.length; i++) {
            assertEquals(values[i] + " expected " + expected, expected,
                    ct.validValue(dvTime(values[i])));
        }
    }

    private List<DvTime> dvTimeList(String[] values) throws Exception {
        List<DvTime> list = new ArrayList<DvTime>();
        for(int i = 0; i < values.length; i++) {
            list.add(dvTime(values[i]));
        }
        return list;
    }

    private DvTime dvTime(String value) throws Exception {
        return new DvTime(value);
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
 *  The Original Code is CTimeTest.java
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