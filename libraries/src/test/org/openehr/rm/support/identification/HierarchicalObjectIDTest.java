/*
 * component:   "openEHR Reference Implementation"
 * description: "Class HierarchicalObjectIDTest"
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
 * HierarhicalObjectIDTest
 *
 * @author Rong Chen
 * @version 1.0 
 */
package org.openehr.rm.support.identification;

import junit.framework.TestCase;

public class HierarchicalObjectIDTest extends TestCase {

    public HierarchicalObjectIDTest(String test) {
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

    public void testConstructorTakesStringValue() throws Exception {

        for (int i = 0; i < STRING_VALUES.length; i++) {
            assertHOID(new HierarchicalObjectID(STRING_VALUES[i]), i);
        }
    }

    public void testConstructorTakesSections() throws Exception {

        for (int i = 0; i < STRING_VALUES.length; i++) {
            assertHOID(new HierarchicalObjectID(SECTIONS[i][0],
            SECTIONS[i][1],SECTIONS[i][2]), i);
        }
    }

    private void assertHOID(HierarchicalObjectID hoid, int i) throws Exception {
        assertEquals("value", STRING_VALUES[i], hoid.getValue());
        assertEquals("contextID", SECTIONS[i][0] == null ? null : new ISO_OID(SECTIONS[i][0]),
                hoid.contextID());
        assertEquals("localID", SECTIONS[i][1], hoid.localID());
        assertEquals("versionID", SECTIONS[i][2], hoid.versionID());
    }

    private static final String[][] SECTIONS = {
            {"1.2.840.113554.1.2.2", "1234", "456"},
            {"1.2.840.113554.1.2.2", "1234", null},
            new String[]{null, "1234", null},
            {"1.2.840.113554.1.2.2", "1234", "4.5.6"},
        };

    private static final String[] STRING_VALUES = {
            "1.2.840.113554.1.2.2.1234(456)",
            "1.2.840.113554.1.2.2.1234",
            "1234",
            "1.2.840.113554.1.2.2.1234(4.5.6)",
        };

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
 *  The Original Code is HierarchicalObjectIDTest.java
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