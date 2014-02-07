package org.openehr.rm.util;

import org.openehr.rm.datastructure.itemstructure.ItemTree;
import org.openehr.rm.datastructure.itemstructure.representation.Element;
import org.openehr.rm.datatypes.quantity.DvOrdinal;

public class OrdinalTest extends SkeletonGeneratorTestBase {

	public OrdinalTest() throws Exception {
	}

	public void testDvOrdinalWithLocalCode() throws Exception {
		archetype = loadArchetype("openEHR-EHR-ITEM_TREE.medication_test_seven.v1.adl");
		expected = loadData("item_tree_medicataion_9.dadl");
		instance = generator.create(archetype);
		
		Element e1 = (Element) ((ItemTree) instance).getItems().get(0);
		Element e2 = (Element) ((ItemTree) expected).getItems().get(0);
		
		DvOrdinal t1 = (DvOrdinal) e1.getValue();
		DvOrdinal t2 = (DvOrdinal) e2.getValue();
		assertEquals(t2, t1);			
		assertEquals(expected, instance);
	}
	
	public void testDvOrdinalWithExternalCode() throws Exception {
		archetype = loadArchetype("openEHR-EHR-ITEM_TREE.medication_test_eight.v1.adl");
		expected = loadData("item_tree_medicataion_10.dadl");
		
		generator.loadTermMapFromClasspath("terms.txt");
		instance = generator.create(archetype);
		
		Element e1 = (Element) ((ItemTree) instance).getItems().get(0);
		Element e2 = (Element) ((ItemTree) expected).getItems().get(0);
				
		DvOrdinal t1 = (DvOrdinal) e1.getValue();
		DvOrdinal t2 = (DvOrdinal) e2.getValue();
		assertEquals(t2, t1);		
			
		assertEquals(expected, instance);
	}
}
