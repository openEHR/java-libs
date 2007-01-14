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

import java.util.ArrayList;
import java.util.List;
import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;
import org.openehr.rm.composition.CompositionTestBase;
import org.openehr.rm.datatypes.encapsulated.DvParsable;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.support.identification.ArchetypeID;
import org.openehr.rm.support.identification.HierarchicalObjectID;

/**
 * InstructionTest
 *
 * @author Rong Chen
 * @version 1.0
 */
public class InstructionTest extends CompositionTestBase {

    public InstructionTest(String test) {
        super(test);
    }

    public void tearDown() throws Exception {
        instruction = null;
    }

    public void setUp() throws Exception {
        //ItemStructure action = list("list action");
        //ItemStructure data = list("list data");
        //ItemStructure profile = list("list profile");
        ItemStructure protocol = list("list protocol");
        //DvState state = TestCodeSetAccess.DRAFT;
        Archetyped arch = new Archetyped(
                new ArchetypeID("openehr-ehr_rm-instruction.physical_examination.v3"),
                "1.1");
        DvParsable timing = new DvParsable(new CodePhrase("test", "en"), new CodePhrase("test", "en"),
                 1, "timing value", "fomalism", ts);
        Activity activity = new Activity("at0004", text("activity 1"), list("list activity"), timing);
        List<Activity> activities = new ArrayList<Activity>();
        activities.add(activity);
        instruction = new Instruction(null, "at0001", text("instruction"),
                arch, null, null, null, language("en"), language("en"), subject(), provider(), 
                null, null, protocol, null, text("narrative"), activities, null, null, ts);
    }

    public void testValidPath() throws Exception {
        String[] validPathList = {
            "/",
            "/protocol",
            "/activities[activity 1]",
            "/activities[activity 1]/description",
            "/protocol/list protocol",
        };

        for (String path : validPathList) {
            assertTrue("unexpected invalid path: " + path,
                    instruction.validPath(path));
        }

        String[] invalidPathList = {
            "", null, "[instruction]", "/instruction",  "/[instruction]", // bad root
            "/[instruction]/state",                    // bad attribute
            "/activities"                              // incomplete
        };

        for (String path : invalidPathList) {
            assertFalse("unexpected valid path[" + path + "]",
                    instruction.validPath(path));
        }
    }

    public void testItemAtPath() throws Exception {
        assertItemAtPath("/", instruction, instruction);
        assertItemAtPath("/", instruction, instruction);

        assertItemAtPath("/protocol", instruction, instruction.getProtocol());

        assertItemAtPath("/protocol/list protocol", instruction, 
                instruction.getProtocol());
        assertItemAtPath("/activities[activity 1]", instruction, instruction.getActivities().get(0));
        assertItemAtPath("/activities[activity 1]/description", instruction, 
                instruction.getActivities().get(0).getDescription());
       
        String[] invalidPathList = {
            "", null, "instruction", "/instruction", // bad root
            "/[instruction]/state"                    // bad attribute
        };

        for (String path : invalidPathList) {
            try {
                instruction.itemAtPath(path);
                fail("exception should be thrown on invalid path[" + path
                        + "]");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }
        }
    }

    /* fields */
    private static Instruction instruction;
}
