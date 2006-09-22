/*
 * component:   "openEHR Reference Implementation"
 * description: "Class EvaluationTest"
 * keywords:    "unit test"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/BRANCHES/RM-1.0-update/libraries/src/test/org/openehr/rm/composition/content/entry/EvaluationTest.java $"
 * revision:    "$LastChangedRevision: 50 $"
 * last_change: "$LastChangedDate: 2006-08-10 13:21:46 +0200 (Thu, 10 Aug 2006) $"
 */
/**
 * EvaluationTest
 *
 * @author Rong Chen
 * @version 1.0 
 */
package org.openehr.rm.composition.content.entry;

import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;
import org.openehr.rm.composition.CompositionTestBase;
import org.openehr.rm.support.identification.ArchetypeID;
import org.openehr.rm.support.identification.HierarchicalObjectID;

public class EvaluationTest extends CompositionTestBase {

    public EvaluationTest(String test) {
        super(test);
    }

    /**
     * The fixture clean up called after every test method.
     */
    protected void tearDown() throws Exception {
        evaluation = null;
    }

    /**
     * The fixture set up called before every test method.
     */
    protected void setUp() throws Exception {
        ItemStructure protocol = list("list protocol");
        ItemStructure data = list("list data");
        Archetyped arch = new Archetyped(
                new ArchetypeID("openehr-ehr_rm-evaluation.physical_examination.v3"),
                                "1.1");
        evaluation = new Evaluation(null, "at000", text("evaluation"), arch,
                null, null, null, language("en"), language("en"), subject(), provider(), 
                null, null, protocol, null, data, ts);
    }

    public void testValidPath() throws Exception {
        String[] validPathList = {
            "/",                   // root
            "/protocol", //"/[evaluation]/protocol",  // protocol
            "/data", //"/[evaluation]/data"           // data
        };

        for(String path : validPathList) {
            assertTrue("unexpected invalid path: " + path,
                    evaluation.validPath(path));
        }

        String[] invalidPathList = {
            "", null, "evaluation", "/evaluation", "/[evaluation]",  // bad root
            "/[evaluation]/state"                   // unknown attribute
        };

        for (String path : invalidPathList) {
            assertFalse("unexpected valid path: " + path,
                    evaluation.validPath(path));
        }
    }

    public void testItemAtPath() throws Exception {
        assertItemAtPath("/", evaluation, evaluation);
        //assertItemAtPath("/[evaluation]", evaluation, evaluation);

        assertItemAtPath("/data", evaluation, evaluation.getData());
        //assertItemAtPath("/[evaluation]/data", evaluation,
          //      evaluation.getData());

        assertItemAtPath("/protocol", evaluation, evaluation.getProtocol());
        //assertItemAtPath("/[evaluation]/protocol", evaluation,
          //      evaluation.getProtocol());

        String[] invalidPathList = {
            "", null, "evaluation", "/evaluation",   // bad root
            "/[evaluation]/state"                   // unknown attribute
        };

        for (String path : invalidPathList) {
            try {
                evaluation.itemAtPath(path);
                fail("exception should be thrown on invalid path[" + path
                        + "]");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }
        }

    }

    /* field */
    private Evaluation evaluation;


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
 *  The Original Code is EvaluationTest.java
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