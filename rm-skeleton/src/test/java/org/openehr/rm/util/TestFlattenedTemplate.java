package org.openehr.rm.util;

import org.openehr.am.archetype.Archetype;
import org.openehr.rm.composition.content.navigation.Section;
import org.openehr.rm.datastructure.itemstructure.ItemTree;

public class TestFlattenedTemplate extends SkeletonGeneratorTestBase {
	
	public TestFlattenedTemplate() throws Exception {		
	}
	
	public void testGenerateWithFlattenedTemplate() throws Exception {
		Archetype flattened = flattenTemplate("test_text_name");
		Object obj = generator.create(flattened, archetypeMap);
		assertTrue(obj instanceof ItemTree);
		
		ItemTree tree = (ItemTree) obj;
		assertEquals(tree.itemAtPath("/items[at0001]/name/value"), "Typ");
	}
	
	public void testSectionWithInstructionAndItemTree() throws Exception {
		Archetype flattened = flattenTemplate("test_section_instruction_tree");	
		Object obj = generator.create(flattened, archetypeMap);
		assertTrue(obj instanceof Section);		
		Section section = (Section) obj;
		
		// node.name from archetype
		String path = "/items[openEHR-EHR-INSTRUCTION.medication.v1]/name/value";
		assertEquals("Medication order", section.itemAtPath(path));
		
		// node.name from template
		path = "/items[openEHR-EHR-INSTRUCTION.medication.v1]/activities" +
				"[at0001]/description/name/value";
		assertEquals("medication details", section.itemAtPath(path));
	}
}
