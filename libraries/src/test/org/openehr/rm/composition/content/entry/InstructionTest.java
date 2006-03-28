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

import org.openehr.rm.datastructure.itemstructure.ItemStructure;
import org.openehr.rm.datatypes.basic.DvState;
import org.openehr.rm.support.terminology.TestCodeSet;
import org.openehr.rm.composition.CompositionTestBase;

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
        ItemStructure action = list("list action");
        ItemStructure data = list("list data");
        ItemStructure profile = list("list profile");
        ItemStructure protocol = list("list protocol");
        DvState state = TestCodeSet.DRAFT;
        instruction = new Instruction(null, "at0001", text("instruction"),
                null, null, null, subject(), provider(), protocol,
                null, null, null, state, action, profile, data);
    }

    public void testValidPath() throws Exception {
        String[] validPathList = {
            "/",
            "/action",
            "/data",
            "/protocol",
            "/profile",
            "/action/list action",
            "/data/list data",
            "/protocol/list protocol",
            "/profile/list profile",
            "/[instruction]",
            "/[instruction]/action",
            "/[instruction]/data",
            "/[instruction]/protocol",
            "/[instruction]/profile",
            "/[instruction]/action/list action",
            "/[instruction]/data/list data",
            "/[instruction]/profile/list profile",
            "/[instruction]/protocol/list protocol"
        };

        for (String path : validPathList) {
            assertTrue("unexpected invalid path: " + path,
                    instruction.validPath(path));
        }

        String[] invalidPathList = {
            "", null, "[instruction]", "/instruction", // bad root
            "/[instruction]/state"                   // bad attribute
        };

        for (String path : invalidPathList) {
            assertFalse("unexpected valid path[" + path + "]",
                    instruction.validPath(path));
        }
    }

    public void testItemAtPath() throws Exception {
        assertItemAtPath("/", instruction, instruction);
        assertItemAtPath("/[instruction]", instruction, instruction);

        assertItemAtPath("/data", instruction, instruction.getData());
        assertItemAtPath("/[instruction]/data", instruction,
                instruction.getData());

        assertItemAtPath("/protocol", instruction, instruction.getProtocol());
        assertItemAtPath("/[instruction]/protocol", instruction,
                instruction.getProtocol());

        assertItemAtPath("/profile", instruction, instruction.getProfile());
        assertItemAtPath("/[instruction]/profile", instruction,
                instruction.getProfile());

        assertItemAtPath("/action", instruction, instruction.getAction());
        assertItemAtPath("/[instruction]/action", instruction,
                instruction.getAction());

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
