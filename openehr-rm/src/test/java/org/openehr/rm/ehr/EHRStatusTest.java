/*
 * component:   "openEHR Reference Implementation"
 * description: "Class EHRStatusTest"
 * keywords:    "unit test"
 *
 * author:      "Yin Su Lim <y.lim@chime.ucl.ac.uk>"
 * support:     "CHIME, UCL"
 * copyright:   "Copyright (c) 2006 UCL, UK"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/BRANCHES/RM-1.0-update/libraries/src/test/org/openehr/rm/ehr/EHRStatusTest.java $"
 * revision:    "$LastChangedRevision: 50 $"
 * last_change: "$LastChangedDate: 2006-08-10 12:21:46 +0100 (Thu, 10 Aug 2006) $"
 */

/**
 * EHRStatusTest
 *
 * @author Yin Su Lim
 * @version 1.0 
 */
package org.openehr.rm.ehr;

import junit.framework.*;
import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.composition.CompositionTestBase;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;
import org.openehr.rm.support.identification.ArchetypeID;

public class EHRStatusTest extends CompositionTestBase {
    
    public EHRStatusTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
        ItemStructure otherDetails = list("list otherDetails");
        Archetyped arch = new Archetyped(
                new ArchetypeID("openehr-ehr_rm-ehrstatus.XYZ.v2"),
                "1.1");
        ehrStatus = new EHRStatus(null, "at0001", text("EHR Status"),
                arch, null, null, null, subject(), true, true, otherDetails);
    }

    protected void tearDown() throws Exception {
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(EHRStatusTest.class);
        
        return suite;
    }

    public void testValidPath() throws Exception {
        String[] validPathList = {
            "/",
            "/otherDetails",
            "/otherDetails/list otherDetails",
        };

        for (String path : validPathList) {
            assertTrue("unexpected invalid path: " + path,
                    ehrStatus.validPath(path));
        }

        String[] invalidPathList = {
            "", null, "[ehrStatus]", "/ehrStatus",  "/[ehrStatus]", // bad root
            "/[ehrStatus]/state",                    // bad attribute
        };

        for (String path : invalidPathList) {
            assertFalse("unexpected valid path[" + path + "]",
                    ehrStatus.validPath(path));
        }
    }

    public void testItemAtPath() throws Exception {
        assertItemAtPath("/", ehrStatus, ehrStatus);
        assertItemAtPath("/", ehrStatus, ehrStatus);

        assertItemAtPath("/otherDetails", ehrStatus, ehrStatus.getOtherDetails());
        assertItemAtPath("/otherDetails/list otherDetails", ehrStatus, 
                ehrStatus.getOtherDetails());
       
        String[] invalidPathList = {
            "", null, "ehrStatus", "/ehrStatus", // bad root
            "/[ehrStatus]/state"                    // bad attribute
        };

        for (String path : invalidPathList) {
            try {
                ehrStatus.itemAtPath(path);
                fail("exception should be thrown on invalid path[" + path
                        + "]");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }
        }
    }
    
    private EHRStatus ehrStatus;
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
 *  The Original Code is EHRStatusTest.java
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