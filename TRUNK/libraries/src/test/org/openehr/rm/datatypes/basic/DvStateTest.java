/*
 * component:   "openEHR Reference Implementation"
 * description: "Class DvStateTest"
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
 * DvStateTest
 *
 * @author Rong Chen
 * @version 1.0 
 */
package org.openehr.rm.datatypes.basic;

import junit.framework.TestCase;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.support.terminology.TestCodeSet;
import org.openehr.rm.support.terminology.TestTerminologyService;

public class DvStateTest extends TestCase {

    public DvStateTest(String test) {
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

    public void testEquals() throws Exception {
        DvCodedText codeOne = new DvCodedText("some text",
                TestCodeSet.ENGLISH, TestCodeSet.LATIN_1,
                new CodePhrase("test terms", "00001"),
                TestTerminologyService.getInstance());
        DvState stateOne = new DvState(codeOne, false);

        DvCodedText codeTwo = new DvCodedText("some text",
                TestCodeSet.ENGLISH, TestCodeSet.LATIN_1,
                new CodePhrase("test terms", "00001"),
                TestTerminologyService.getInstance());
        DvState stateTwo = new DvState(codeTwo, false);

        assertTrue(stateOne.equals(stateTwo));
        assertTrue(stateTwo.equals(stateOne));

        DvState stateThree = new DvState(codeTwo, true);
        assertFalse(stateOne.equals(stateThree));
        assertFalse(stateThree.equals(stateOne));
        assertFalse(stateTwo.equals(stateThree));
        assertFalse(stateThree.equals(stateTwo));
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
 *  The Original Code is DvStateTest.java
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