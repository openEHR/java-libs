/*
 * component:   "openEHR Reference Implementation"
 * description: "Class TerminologyIDTest"
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
 * TerminologyIDTest
 *
 * @author Rong Chen
 * @version 1.0 
 */
package org.openehr.rm.support.identification;

import junit.framework.TestCase;

public class TerminologyIDTest extends TestCase {

    public TerminologyIDTest(String test) {
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

    public void testConstrcutorTakesStringValue() throws Exception {
        for(int i = 0; i < STRING_VALUE.length; i++) {
            assertTID(new TerminologyID(STRING_VALUE[i]), i);
        }
    }

    public void testConstrcutorTakesNameVersion() throws Exception {
        for(int i = 0; i < STRING_VALUE.length; i++) {
            assertTID(new TerminologyID(SECTIONS[i][0], SECTIONS[i][1]), i);
        }
    }

    private void assertTID(TerminologyID tid, int i) {
        assertEquals("value", STRING_VALUE[i], tid.getValue());
        assertEquals("name", SECTIONS[i][0], tid.name());
        assertEquals("version", SECTIONS[i][1], tid.versionID());
    }

    private static final String[] STRING_VALUE = {
        "snomed-ct",  "ICD9(1999)"
    };

    private static final String[][] SECTIONS = {
        {"snomed-ct", null},
        {"ICD9", "1999"}
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
 *  The Original Code is TerminologyIDTest.java
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