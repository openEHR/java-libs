package org.openehr.rm.util;

import org.openehr.rm.datastructure.itemstructure.ItemTree;

public class RootNodeNameTest extends SkeletonGeneratorTestBase {

	public RootNodeNameTest() throws Exception {
	}

	public void testDvOrdinalWithLocalCode() throws Exception {
		archetype = loadArchetype("openEHR-EHR-ITEM_TREE.medication_test_seven.v1.adl");
		instance = generator.create(archetype);
		
		assertTrue(instance instanceof ItemTree);
		ItemTree tree = (ItemTree) instance;
		assertEquals("Medication description", tree.getName().getValue());
	}	
}
