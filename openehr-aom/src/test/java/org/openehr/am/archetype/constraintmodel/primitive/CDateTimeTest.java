/*
 * component:   "openEHR Reference Implementation"
 * description: "Class CDateTimeTest"
 * keywords:    "archetype"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/test/org/openehr/am/archetype/constraintmodel/primitive/CDateTimeTest.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 23:20:08 +0200 (Wed, 12 Oct 2005) $"
 */
/**
 * CDateTimeTeset
 *
 * @author Rong Chen
 * @version 1.0 
 */
package org.openehr.am.archetype.constraintmodel.primitive;

import junit.framework.TestCase;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.support.basic.Interval;

import java.util.*;

public class CDateTimeTest extends TestCase {

    public CDateTimeTest(String test) {
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

    public void testValidDataWithDateInterval() throws Exception {

        CDateTime cd = new CDateTime(null, new Interval<DvDateTime>(dvDateTime(
                "1999-01-12T01:10:30"),
                        dvDateTime("2001-10-25T20:05:10")), null);

        String[] values = {
            "1999-01-12T01:10:30", "1999-01-12T01:10:31",
            "1999-01-12T01:11:30", "1999-01-12T02:10:30",
            "1999-01-13T01:10:30", "1999-02-12T01:10:30",
            "2000-01-12T01:10:30", "2001-10-25T20:05:10",
            "2001-10-25T20:05:09", "2001-10-25T20:04:10",
            "2001-10-25T19:05:10", "2001-10-24T20:05:10",
            "2001-09-25T20:05:10", "2000-10-25T20:05:10"
        };
        assertValues(cd, values, true);

        values = new String[]{
            "1999-01-12T01:10:29", "1999-01-12T01:09:30",
            "1999-01-12T00:10:30", "1999-01-11T01:10:30",
            "1998-12-12T01:10:30", "1998-01-12T01:10:30",
            "2001-10-25T20:05:11", "2001-10-25T20:06:10",
            "2001-10-25T21:05:10", "2001-10-26T20:05:10",
            "2001-11-25T20:05:10", "2002-10-25T20:05:10"
        };
        assertValues(cd, values, false);
    }

    public void testCreateCDateTimeWithPattern() {
    	CDateTime cdatetime = new CDateTime("yyyy-mm-ddTHH:MM:SS");
    	assertNotNull(cdatetime);
    }
    
    public void testValidDateWithDateList() throws Exception {
        String[] values = {
            "2000-10-25T00:00:00", "2001-10-26T20:05:10",
            "1998-01-01T23:10:15", "2003-12-31T23:59:59"
        };
        CDateTime cd = new CDateTime(null, null, dvDateList(values));
        assertValues(cd, values, true);

        values = new String[]{
            "2002-10-25T00:00:01", "2002-10-26T20:05:10",
            "1998-01-01T23:11:15", "2003-12-31T23:00:00"
        };
        assertValues(cd, values, false);
    }

    public void testAssignedValue() throws Exception {
        CDateTime cd = new CDateTime(null,
                new Interval<DvDateTime>(dvDateTime("2001-01-01T00:00:00"),
                        dvDateTime("2001-12-31T00:00:00")), null);
        assertFalse(cd.hasAssignedValue());
        assertTrue(cd.assignedValue() == null);

        cd = new CDateTime("yyyy-MM-ddTHH:mm:ss", null, null);
        assertFalse(cd.hasAssignedValue());
        assertTrue(cd.assignedValue() == null);

        List<DvDateTime> list = new ArrayList<DvDateTime>();
        list.add(new DvDateTime("1999-10-11T00:00:00"));
        cd = new CDateTime(null, null, list);
        assertTrue(cd.hasAssignedValue());
        assertEquals(new DvDateTime("1999-10-11T00:00:00"),
                cd.assignedValue());
    }


    private void assertValues(CDateTime cd, String[] values,
                              boolean expected) throws Exception {
        for (int i = 0; i < values.length; i++) {
            assertEquals(values[ i ] + " expected " + expected,
                    expected, cd.validValue(dvDateTime(values[ i ])));
        }
    }

    private DvDateTime dvDateTime(String value) throws Exception {
        return new DvDateTime(value);
    }

    private List<DvDateTime> dvDateList(String[] values) throws Exception {
        List<DvDateTime> list = new ArrayList<DvDateTime>();
        for (int i = 0; i < values.length; i++) {
            list.add(dvDateTime(values[ i ]));
        }
        return list;
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
 *  The Original Code is CDateTimeTest.java
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