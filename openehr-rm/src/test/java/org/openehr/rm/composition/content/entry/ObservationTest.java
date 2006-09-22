/*
 * Copyright (C) 2004 Rong Chen, Acode HB, Sweden
 * All rights reserved.
 *
 * The contents of this software are subject to the FSF GNU Public License 2.0;
 * you may not use this software except in compliance with the License. You may
 * obtain a copy of the License at http://www.fsf.org/licenses/gpl.html
 *
 * Software distributed under the License is distributed on an 'AS IS' basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 */
package org.openehr.rm.composition.content.entry;

import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.datastructure.history.History;
import org.openehr.rm.datastructure.itemstructure.ItemSingle;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;
import org.openehr.rm.composition.CompositionTestBase;
import org.openehr.rm.support.identification.ArchetypeID;
import org.openehr.rm.support.identification.HierarchicalObjectID;
import org.openehr.rm.support.terminology.TestTerminologyService;

/**
 * ObservationTest
 *
 * @author Rong Chen
 * @version 1.0
 */
public class ObservationTest extends CompositionTestBase {

    public ObservationTest(String test) {
        super(test);
    }

    /**
     * The fixture clean up called after every test method.
     */
    protected void tearDown() throws Exception {
        observation = null;
    }

    /**
     * The fixture set up called before every test method.
     */
    protected void setUp() throws Exception {
        ItemStructure protocol = list("list protocol");
        History<ItemStructure> data = event("data");
        History<ItemStructure> state = event("state");
        Archetyped arch = new Archetyped(
                new ArchetypeID("openehr-ehr_rm-observation.physical_examination.v3"),
                "1.1");
        observation = new Observation(null, "at000", text("observation"),
                arch, null, null, null, language("en"), language("en"), subject(), 
                provider(), null, null, protocol, null, data, state, 
                TestTerminologyService.getInstance());
    }

    public void testValidPath() throws Exception {
        String[] validPathList = {
            "/", //"/[observation]", // root
            "/protocol", //"/[observation]/protocol", // protocol
            "/data", //"/[observation]/data", // data
            "/state", //"/[observation]/state"           // state
        };

        for (String path : validPathList) {
            assertTrue("unexpected invalid path: " + path,
                    observation.validPath(path));
        }

        String[] invalidPathList = {
            "", null, "[observation]", "/observation", "/[observation]", // bad root
            "/[observation]/action", "/action"                   // unknown attribute
        };

        for (String path : invalidPathList) {
            assertFalse("unexpected valid path: " + path,
                    observation.validPath(path));
        }
    }

    public void testItemAtPath() throws Exception {
        assertItemAtPath("/", observation, observation);
        assertItemAtPath("/", observation, observation);

        assertItemAtPath("/data", observation, observation.getData());
        assertItemAtPath("/data", observation,
                observation.getData());

        assertItemAtPath("/protocol", observation, observation.getProtocol());
        assertItemAtPath("/protocol", observation,
                observation.getProtocol());

        assertItemAtPath("/state", observation, observation.getState());
        assertItemAtPath("/state", observation,
                observation.getState());

        String[] invalidPathList = {
            "", null, "observation", "/observation", "/[observation]", // bad root
            "/[observation]/action", "/action"                    // unknown attribute
        };

        for (String path : invalidPathList) {
            try {
                observation.itemAtPath(path);
                fail("exception should be thrown on invalid path[" + path
                        + "]");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }
        }
    }

    /* field */
    private Observation observation;
}
