package org.openehr.rm.util;

import org.openehr.am.archetype.constraintmodel.CObject;

public class TestStrategy extends SkeletonGeneratorTestBase {
	
	public TestStrategy() throws Exception {		
		archetype = loadArchetype("openEHR-EHR-ITEM_TREE.medication_test_four.v1.adl");
	}
	
	public void testWithDefaultStrategy() throws Exception {
		expected = loadData("item_tree_medicataion_4.dadl");
		instance = generator.create(archetype);
		assertEquals(expected, instance);
	}
	
	public void testWithExplicitMinimumStrategy() throws Exception {
		expected = loadData("item_tree_medicataion_4.dadl");
		instance = generator.create(archetype, GenerationStrategy.MINIMUM);
		assertEquals(expected, instance);
	}
	
	public void testWithMaximumStrategy() throws Exception {
		expected = loadData("item_tree_medicataion_5.dadl");
		instance = generator.create(archetype, GenerationStrategy.MAXIMUM);
		assertEquals(expected, instance);
	}

	public void testWithMaximumEmptyStrategy() throws Exception {
		expected = loadData("item_tree_medicataion_12.dadl");
		
		// required for max_empty strategy to differentiate input node from
		// the rest of nodes
		setInputAnnotation("/items[at0001]");
		setInputAnnotation("/items[at0002]");
		setInputAnnotation("/items[at0003]");		
		
		instance = generator.create(archetype, GenerationStrategy.MAXIMUM_EMPTY);
		assertEquals(expected, instance);
	}
	
	private void setInputAnnotation(String path) {
		CObject cobj = (CObject) archetype.node(path);
		cobj.setAnnotation("INPUT");
	}
	
}
