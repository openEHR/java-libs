package org.openehr.am.template;

import org.openehr.am.archetype.constraintmodel.ArchetypeConstraint;

public class SectionInstructionTest extends TemplateTestBase {
	
	/**
	 * Verify flattened section with a single evaluation
	 * 
	 * @throws Exception
	 */
	public void testSectionWithSingleInstruction() throws Exception {
		flattenTemplate("test_section_instruction.oet");	
		
		// section level
		ac = flattened.node("/");
		assertSectionConstraint(ac);
		
		ac = flattened.node("/name/value");
		assertCStringWithSingleValue(ac, "medication");
		
		// instruction level
		ac = flattened.node("/items[openEHR-EHR-INSTRUCTION.medication.v1" +
				" and name/value='medication instruction']");
		assertInstructionConstraint(ac);
		
		ac = flattened.node("/items[openEHR-EHR-INSTRUCTION.medication.v1" +
				" and name/value='medication instruction']/name/value");
		assertCStringWithSingleValue(ac, "medication instruction");
	}
	
	/**
	 * Verify flattened section with a single evaluation and
	 * an embedded ItemTree archetype
	 * 
	 * @throws Exception
	 */
	public void testSectionWithInstructionAndItemTree() throws Exception {
		flattenTemplate("test_section_instruction_tree.oet");	
		
		// section level
		ac = flattened.node("/");
		assertSectionConstraint(ac);
		
		ac = flattened.node("/name/value");
		assertCStringWithSingleValue(ac, "medication");
		
		// instruction level
		ac = flattened.node("/items[openEHR-EHR-INSTRUCTION.medication.v1" +
				" and name/value='medication instruction']");
		assertInstructionConstraint(ac);
		
		ac = flattened.node("/items[openEHR-EHR-INSTRUCTION.medication.v1" +
				" and name/value='medication instruction']/name/value");
		assertCStringWithSingleValue(ac, "medication instruction");
		
		// item_tree level
		ac = flattened.node("/items[openEHR-EHR-INSTRUCTION.medication.v1" +
				" and name/value='medication instruction']" +
				"/activities[at0001]/description" +
				"[openEHR-EHR-ITEM_TREE.medication.v1" +
				" and name/value='medication details']");
		assertItemTreeConstraint(ac);
		
		ac = flattened.node("/items[openEHR-EHR-INSTRUCTION.medication.v1" +
				" and name/value='medication instruction']" +
				"/activities[at0001]/description" +
				"[openEHR-EHR-ITEM_TREE.medication.v1" +
				" and name/value='medication details']/name/value");
		
		assertCStringWithSingleValue(ac, "medication details");
	}
	
	ArchetypeConstraint ac;
}
