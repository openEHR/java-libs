/*
 * component:   "openEHR Reference Implementation"
 * description: "Class CDateTest"
 * keywords:    "archetype"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/test/org/openehr/am/archetype/constraintmodel/primitive/CDateTest.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 23:20:08 +0200 (Wed, 12 Oct 2005) $"
 */
/**
 * CDateTest
 *
 * @author Rong Chen
 * @version 1.0 
 */
package org.openehr.am.archetype.constraintmodel.primitive;

import junit.framework.TestCase;
import org.openehr.rm.datatypes.quantity.datetime.DvDate;
import org.openehr.rm.support.basic.Interval;

import java.util.*;

public class CDateTest extends TestCase {

    public CDateTest(String test) {
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

    public void testValidValueWithDateInterval() throws Exception {
        CDate cd = new CDate(null, new Interval<DvDate>(dvDate(
                "2001-01-01"), dvDate("2001-12-31")), null);
        assertTrue(cd.validValue(dvDate("2001-01-01")));
        assertTrue(cd.validValue(dvDate("2001-12-31")));
        assertTrue(cd.validValue(dvDate("2001-03-10")));
        assertTrue(cd.validValue(dvDate("2001-10-30")));
        assertFalse(cd.validValue(dvDate("2000-01-01")));
        assertFalse(cd.validValue(dvDate("2002-01-01")));
    }
    
    public void testCreateCDateWithPattern() {
    	CDate cd = new CDate("yyyy-mm-dd");
    	assertNotNull(cd);
    }

    public void testValidValueWithDateList() throws Exception {

        DvDate[] dates = new DvDate[]{
            dvDate("2001-01-01"), dvDate("2002-10-15"), dvDate("2004-02-10")
        };

        CDate cd = new CDate(null, null, Arrays.asList(dates));

        for (int i = 0; i < dates.length; i++) {
            assertTrue(dates[ i ].toString(), cd.validValue(dates[ i ]));
        }
        assertFalse(cd.validValue(dvDate("2001-01-02")));
        assertFalse(cd.validValue(dvDate("2002-10-12")));
        assertFalse(cd.validValue(dvDate("2004-03-10")));
    }

    private DvDate dvDate(String value) throws Exception {
        return new DvDate(value);
    }

    public void testAssignedValue() throws Exception {
        CDate cd = new CDate(null, new Interval<DvDate>(dvDate(
                "2001-01-01"), dvDate("2001-12-31")), null);
        assertFalse(cd.hasAssignedValue());
        assertTrue(cd.assignedValue() == null);

        cd = new CDate("yyyy-MM-dd", null, null);
        assertFalse(cd.hasAssignedValue());
        assertTrue(cd.assignedValue() == null);

        List<DvDate> list = new ArrayList<DvDate>();
        list.add(new DvDate("1999-10-11"));
        cd = new CDate(null, null, list);
        assertTrue(cd.hasAssignedValue());
        assertEquals(new DvDate("1999-10-11"),
                cd.assignedValue());
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
 *  The Original Code is CDateTest.java
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