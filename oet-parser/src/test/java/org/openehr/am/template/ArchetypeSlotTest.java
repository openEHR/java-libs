package org.openehr.am.template;

import org.openehr.am.archetype.constraintmodel.ArchetypeConstraint;
import org.openehr.am.archetype.constraintmodel.CComplexObject;

public class ArchetypeSlotTest extends TemplateTestBase {
	
	/* 
	 * Essentially test the slot filling as the following order
	 * composition / section / instruction / item_tree
	 */
	public void testExapandNestedArchetypeSlots() throws Exception {
		flattenTemplate("prescription.oet");
		
		// unfortunately the parser cannot handle archetype_id as node_id
		// therefore impossible to perform a complete tree-based comparison
		archetype = loadArchetype(
				"openEHR-EHR-COMPOSITION.prescription_flattened.v1.adl");
		
		// verify major RM structure are in place
		ac = flattened.node("/");
		assertCompositionConstraint(ac);
		
		ac = flattened.node("/content[openEHR-EHR-SECTION.medications.v1]");
		assertSectionConstraint(ac);
		
		ac = flattened.node("/content[openEHR-EHR-SECTION.medications.v1]" +
				"/items[openEHR-EHR-INSTRUCTION.medication.v1]");
		assertInstructionConstraint(ac);
		
		ac = flattened.node("/content[openEHR-EHR-SECTION.medications.v1]" +
				"/items[openEHR-EHR-INSTRUCTION.medication.v1]/activities" +
				"[at0001]/description[openEHR-EHR-ITEM_TREE.medication_mod.v1]");
		assertItemTreeConstraint(ac);

		// verify details using hand-made archetype
		CComplexObject expected = (CComplexObject) archetype.node(
				"/content[at0000]/items[at0000]/activities[at0001]" +
				"/description[at0000]");
		
		CComplexObject details = (CComplexObject) ac;
		
		// needed modification before tree comparison
		details.setNodeId("at0000");
		assertCComplexObjectEquals("Unexpected flattening result", 
				expected, (CComplexObject) ac);		
	}	
	
	private ArchetypeConstraint ac;
}
