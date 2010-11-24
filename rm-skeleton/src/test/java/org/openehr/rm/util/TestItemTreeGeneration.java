package org.openehr.rm.util;

/**
 * Testcases for leaf-node data types on item_tree level
 * 
 * @author rong.chen
 *
 */
public class TestItemTreeGeneration extends SkeletonGeneratorTestBase {
	
	public TestItemTreeGeneration() throws Exception {		
	}
	
	public void testSingleTextElementTree() throws Exception {
		archetype = loadArchetype("openEHR-EHR-ITEM_TREE.medication_test_one.v1.adl");
		expected = loadData("item_tree_medicataion_1.dadl");
		instance = generator.create(archetype);
		assertEquals(expected, instance);
	}
	
	public void testDoubleQuantityElementTree() throws Exception {
		archetype = loadArchetype("openEHR-EHR-ITEM_TREE.blood_pressure.v1.adl");
		expected = loadData("item_tree_blood_pressure_1.dadl");
		instance = generator.create(archetype);
		assertEquals(expected, instance);
	}
	
	public void testSingleDvCountElement() throws Exception {
		archetype = loadArchetype("openEHR-EHR-ITEM_TREE.medication_test_five.v1.adl");
		expected = loadData("item_tree_medicataion_6.dadl");
		instance = generator.create(archetype);
		assertEquals(expected, instance);
	}
	
	public void testSingleDvProportionElement() throws Exception {
		archetype = loadArchetype("openEHR-EHR-ITEM_TREE.medication_test_six.v1.adl");
		expected = loadData("item_tree_medicataion_7.dadl");
		instance = generator.create(archetype);
		assertEquals(expected, instance);
	}

}
