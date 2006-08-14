/*
 * component:   "openEHR Reference Implementation"
 * description: "Class ActionTest"
 * keywords:    "unit test"
 *
 * author:      "Yin Su Lim <y.lim@chime.ucl.ac.uk>"
 * support:     "CHIME, UCL"
 * copyright:   "Copyright (c) 2006 UCL, UK"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/BRANCHES/RM-1.0-update/libraries/src/test/org/openehr/rm/composition/content/entry/ActionTest.java $"
 * revision:    "$LastChangedRevision: 50 $"
 * last_change: "$LastChangedDate: 2006-08-10 12:21:46 +0100 (Thu, 10 Aug 2006) $"
 */

/**
 * ActionTest
 *
 * @author Yin Su Lim
 * @version 1.0 
 */
package org.openehr.rm.composition.content.entry;

import junit.framework.*;
import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.composition.CompositionTestBase;
import org.openehr.rm.composition.content.entry.ISMTransition;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.support.identification.ArchetypeID;
import org.openehr.rm.support.terminology.TestCodeSetAccess;

public class ActionTest extends CompositionTestBase {
    
    public ActionTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
        ItemStructure description = list("list description");
        ItemStructure protocol = list("list protocol");
        //DvState state = TestCodeSetAccess.DRAFT;
        Archetyped arch = new Archetyped(
                new ArchetypeID("openehr-ehr_rm-action.XYZ.v2"),
                "1.1");
        ISMTransition ismT = new ISMTransition(TestCodeSetAccess.ISM_ACTIVE, null, null, ts);
        action = new Action(null, "at0001", text("action"),
                arch, null, null, null, language("en"), language("en"), subject(), provider(), 
                null, null, protocol, null, new DvDateTime(), description, ismT, null, ts);
    }

    protected void tearDown() throws Exception {
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(ActionTest.class);
        
        return suite;
    }

    public void testValidPath() throws Exception {
        String[] validPathList = {
            "/",
            "/protocol",
            "/description",
            "/protocol/list protocol",
            "/description/list description",
        };

        for (String path : validPathList) {
            assertTrue("unexpected invalid path: " + path,
                    action.validPath(path));
        }

        String[] invalidPathList = {
            "", null, "[action]", "/action",  "/[action]", // bad root
            "/[action]/state",                    // bad attribute
        };

        for (String path : invalidPathList) {
            assertFalse("unexpected valid path[" + path + "]",
                    action.validPath(path));
        }
    }

    public void testItemAtPath() throws Exception {
        assertItemAtPath("/", action, action);
        assertItemAtPath("/", action, action);

        assertItemAtPath("/protocol", action, action.getProtocol());

        assertItemAtPath("/protocol/list protocol", action, 
                action.getProtocol());
        assertItemAtPath("/description", action, action.getDescription());
        assertItemAtPath("/description/list description", action, 
                action.getDescription());
       
        String[] invalidPathList = {
            "", null, "action", "/action", // bad root
            "/[action]/state"                    // bad attribute
        };

        for (String path : invalidPathList) {
            try {
                action.itemAtPath(path);
                fail("exception should be thrown on invalid path[" + path
                        + "]");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }
        }
    }

    /* fields */
    private static Action action;
    
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
 *  The Original Code is ActionTest.java
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