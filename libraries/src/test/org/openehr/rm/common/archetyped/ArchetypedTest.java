/*
 * component:   "openEHR Reference Implementation"
 * description: "Class ArchetypedTest"
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
 * ArchetypedTest
 *
 * @author Rong Chen
 * @version 1.0 
 */
package org.openehr.rm.common.archetyped;

import junit.framework.TestCase;
import org.openehr.rm.support.identification.ArchetypeID;
import org.openehr.rm.support.identification.HierarchicalObjectID;

public class ArchetypedTest extends TestCase {

    public ArchetypedTest(String test) {
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

    public void testConstructor() throws Exception {
        new Archetyped(aid("openehr-ehr_rm-section.physical_examination.v2"),
                "1.1");
        new Archetyped(aid("openehr-ehr_rm-section.physical_examination.v2"),
                "1.1");

        assertExceptionThrown(null, "1.1", "archetypeID");
        assertExceptionThrown(aid(
                "openehr-ehr_rm-section.physical_examination.v2"),
                null, "referenceModelVersion");
    }

    public void testEquals() {
        Archetyped a1 = new Archetyped(aid(
                "openehr-ehr_rm-section.physical_examination.v2"),
                "1.1");
        Archetyped a2 = new Archetyped(aid(
                "openehr-ehr_rm-section.physical_examination.v2"),
                "1.1");
        assertTrue(a1.equals(a2));
        assertTrue(a2.equals(a1));

        Archetyped a3 = new Archetyped(aid(
                "openehr-ehr_rm-section.physical_examination.v3"),
                "1.1");
        assertFalse(a1.equals(a3));
        assertFalse(a3.equals(a1));

        a3 = new Archetyped(
                aid("openehr-ehr_rm-section.physical_examination.v2"),
                "1.2");
        assertFalse(a1.equals(a3));
        assertFalse(a3.equals(a1));
    }

    private ArchetypeID aid(String value) {
        return new ArchetypeID(value);
    }

    private void assertExceptionThrown(ArchetypeID archetypeID,
                                       String referenceModelVersion,
                                       String cause) {
        try {
            new Archetyped(archetypeID, referenceModelVersion);
            fail("exception should be thrown here");
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
            assertTrue(e.getMessage().contains(cause));
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
 *  The Original Code is ArchetypedTest.java
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
