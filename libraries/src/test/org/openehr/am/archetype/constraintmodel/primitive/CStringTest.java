/*
 * component:   "openEHR Reference Implementation"
 * description: "Class CStringTest"
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
 * CStringTest
 *
 * @author Rong Chen
 * @version 1.0 
 */
package org.openehr.am.archetype.constraintmodel.primitive;

import junit.framework.TestCase;

import java.util.Arrays;

public class CStringTest extends TestCase {

    public CStringTest(String test) {
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
        CString cs = new CString("file.*", null);
        assertTrue(cs.validValue("file.txt"));
        assertTrue(cs.validValue("file.exe"));
        assertTrue(cs.validValue("file.zip"));
        assertFalse(cs.validValue("read.zip"));

        cs = new CString("this|that|something else", null);
        assertTrue(cs.validValue("this"));
        assertTrue(cs.validValue("that"));
        assertTrue(cs.validValue("something else"));
        assertFalse(cs.validValue("something"));

        String[] values = {"apple", "pear", "orange"};
        cs = new CString(null, Arrays.asList(values));
        assertTrue(cs.validValue("apple"));
        assertTrue(cs.validValue("pear"));
        assertTrue(cs.validValue("orange"));
        assertFalse(cs.validValue("melon"));
    }

    public void testAssignedValue() throws Exception {

        // with pattern
        CString cs = new CString("file.*", null);
        assertFalse(cs.hasAssignedValue());
        assertTrue(cs.assignedValue() == null);

        // with multi item list
        String[] values = {"apple", "pear", "orange"};
        cs = new CString(null, Arrays.asList(values));
        assertFalse(cs.hasAssignedValue());
        assertTrue(cs.assignedValue() == null);

        // with single item list
        values = new String[] {"apple"};
        cs = new CString(null, Arrays.asList(values));
        assertTrue(cs.hasAssignedValue());
        String text = cs.assignedValue();
        assertEquals("wrong value", "apple", text);
    }
    
    public void testConstructorWithoutAssumedValue() throws Exception {
    	CString cs = new CString("file.*", null);
        assertFalse("shouldn't have an assumed value", cs.hasAssumedValue());        
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
 *  The Original Code is CStringTest.java
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