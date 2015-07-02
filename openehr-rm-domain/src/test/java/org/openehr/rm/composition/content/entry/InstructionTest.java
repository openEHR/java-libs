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
import org.openehr.rm.composition.CompositionTestBase;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;
import org.openehr.rm.datatypes.encapsulated.DvParsable;
import org.openehr.rm.support.identification.ArchetypeID;

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
        ItemStructure protocol = list("list protocol");
        Archetyped arch = new Archetyped(
                new ArchetypeID("openehr-ehr_rm-instruction.physical_examination.v3"),
                "1.1");
        DvParsable timing = new DvParsable("timing value", "fomalism");
        Activity activity = new Activity("at0004", text("activity 1"), 
        		list("list activity"), timing,
        		"openEHR-EHR-ITEM_TREE.intravenous_fluids.v1draft");
        List<Activity> activities = new ArrayList<Activity>();
        activities.add(activity);
        instruction = new Instruction(null, "at0001", text("instruction"),
                arch, null, null, null, language("en"), language("en"), subject(), provider(), 
                null, null, protocol, null, text("narrative"), activities, null, null, ts);
    }
    
	public void testEquals() throws Exception {
		ItemStructure protocol = list("list protocol");
		Archetyped arch = new Archetyped(new ArchetypeID(
				"openehr-ehr_rm-instruction.physical_examination.v3"), "1.1");
		DvParsable timing = new DvParsable("timing value", "fomalism");
		Activity activity = new Activity("at0004", text("activity 1"),
				list("list activity"), timing,
				"openEHR-EHR-ITEM_TREE.intravenous_fluids.v1draft");
		List<Activity> activities = new ArrayList<Activity>();
		activities.add(activity);
		Instruction instruction1 = new Instruction(null, "at0001",
				text("instruction"), arch, null, null, null, language("en"),
				language("en"), subject(), provider(), null, null, protocol,
				null, text("narrative"), activities, null, null, ts);

		Instruction instruction2 = new Instruction(null, "at0001",
				text("instruction"), arch, null, null, null, language("en"),
				language("en"), subject(), provider(), null, null, protocol,
				null, text("narrative"), activities, null, null, ts);

		assertTrue(instruction1.equals(instruction2));
	}

	public void testNotEqualActivity() throws Exception {
		ItemStructure protocol = list("list protocol");
		Archetyped arch = new Archetyped(new ArchetypeID(
				"openehr-ehr_rm-instruction.physical_examination.v3"), "1.1");

		DvParsable timing = new DvParsable("timing value", "fomalism");
		Activity activity = new Activity("at0004", text("activity 1"),
				list("list activity"), timing,
				"openEHR-EHR-ITEM_TREE.intravenous_fluids.v1draft");
		List<Activity> activities = new ArrayList<Activity>();
		activities.add(activity);

		Activity activity2 = new Activity("at0004", text("activity 2"),
				list("list activity"), timing,
				"openEHR-EHR-ITEM_TREE.intravenous_fluids.v1draft");
		List<Activity> activities2 = new ArrayList<Activity>();
		activities2.add(activity2);

		Instruction instruction1 = new Instruction(null, "at0001",
				text("instruction"), arch, null, null, null, language("en"),
				language("en"), subject(), provider(), null, null, protocol,
				null, text("narrative"), activities, null, null, ts);

		Instruction instruction2 = new Instruction(null, "at0001",
				text("instruction"), arch, null, null, null, language("en"),
				language("en"), subject(), provider(), null, null, protocol,
				null, text("narrative"), activities2, null, null, ts);

		assertFalse(instruction1.equals(instruction2));
	}

	public void testNotEqualProtocol() throws Exception {
		ItemStructure protocol = list("list protocol");
		Archetyped arch = new Archetyped(new ArchetypeID(
				"openehr-ehr_rm-instruction.physical_examination.v3"), "1.1");
		DvParsable timing = new DvParsable("timing value", "fomalism");
		Activity activity = new Activity("at0004", text("activity 1"),
				list("list activity"), timing,
				"openEHR-EHR-ITEM_TREE.intravenous_fluids.v1draft");
		List<Activity> activities = new ArrayList<Activity>();
		activities.add(activity);
		Instruction instruction1 = new Instruction(null, "at0001",
				text("instruction"), arch, null, null, null, language("en"),
				language("en"), subject(), provider(), null, null, protocol,
				null, text("narrative"), activities, null, null, ts);

		ItemStructure protocol2 = list("list protocol2");
		Instruction instruction2 = new Instruction(null, "at0001",
				text("instruction"), arch, null, null, null, language("en"),
				language("en"), subject(), provider(), null, null, protocol2,
				null, text("narrative"), activities, null, null, ts);

		assertFalse(instruction1.equals(instruction2));
	}

	public void testNotEqualTiming() throws Exception {
		ItemStructure protocol = list("list protocol");
		Archetyped arch = new Archetyped(new ArchetypeID(
				"openehr-ehr_rm-instruction.physical_examination.v3"), "1.1");

		DvParsable timing = new DvParsable("timing value", "fomalism");
		Activity activity = new Activity("at0004", text("activity 1"),
				list("list activity"), timing,
				"openEHR-EHR-ITEM_TREE.intravenous_fluids.v1draft");
		List<Activity> activities = new ArrayList<Activity>();
		activities.add(activity);

		DvParsable timing2 = new DvParsable("timing valuess", "fomalism");
		Activity activity2 = new Activity("at0004", text("activity "),
				list("list activity"), timing2,
				"openEHR-EHR-ITEM_TREE.intravenous_fluids.v1draft");
		List<Activity> activities2 = new ArrayList<Activity>();
		activities2.add(activity2);

		Instruction instruction1 = new Instruction(null, "at0001",
				text("instruction"), arch, null, null, null, language("en"),
				language("en"), subject(), provider(), null, null, protocol,
				null, text("narrative"), activities, null, null, ts);

		Instruction instruction2 = new Instruction(null, "at0001",
				text("instruction"), arch, null, null, null, language("en"),
				language("en"), subject(), provider(), null, null, protocol,
				null, text("narrative"), activities2, null, null, ts);

		assertFalse(instruction1.equals(instruction2));
	}

	public void testNotEqualActivitySize() throws Exception {
		ItemStructure protocol = list("list protocol");
		Archetyped arch = new Archetyped(new ArchetypeID(
				"openehr-ehr_rm-instruction.physical_examination.v3"), "1.1");

		DvParsable timing = new DvParsable("timing value", "fomalism");
		Activity activity = new Activity("at0004", text("activity 1"),
				list("list activity"), timing,
				"openEHR-EHR-ITEM_TREE.intravenous_fluids.v1draft");
		List<Activity> activities = new ArrayList<Activity>();
		activities.add(activity);

		Activity activity2 = new Activity("at0004", text("activity 1"),
				list("list activity"), timing,
				"openEHR-EHR-ITEM_TREE.intravenous_fluids.v1draft");
		List<Activity> activities2 = new ArrayList<Activity>();
		activities2.add(activity2);
		activities2.add(activity);

		Instruction instruction1 = new Instruction(null, "at0001",
				text("instruction"), arch, null, null, null, language("en"),
				language("en"), subject(), provider(), null, null, protocol,
				null, text("narrative"), activities, null, null, ts);

		Instruction instruction2 = new Instruction(null, "at0001",
				text("instruction"), arch, null, null, null, language("en"),
				language("en"), subject(), provider(), null, null, protocol,
				null, text("narrative"), activities2, null, null, ts);

		assertFalse(instruction1.equals(instruction2));
	}

    public void testItemAtPath() {
    	path = "/";
    	value = instruction.itemAtPath(path);
    	assertEquals(instruction, value);
    }

    /* fields */
    private static Instruction instruction;
}
