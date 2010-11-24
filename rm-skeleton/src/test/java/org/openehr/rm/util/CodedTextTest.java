package org.openehr.rm.util;

import org.openehr.rm.datastructure.itemstructure.ItemTree;
import org.openehr.rm.datastructure.itemstructure.representation.Element;
import org.openehr.rm.datatypes.text.DvCodedText;

public class CodedTextTest extends SkeletonGeneratorTestBase {
	
	public CodedTextTest() throws Exception {		
	}

	public void testSingleCodedTextElementTreeWithTermMap() throws Exception {
		archetype = loadArchetype("openEHR-EHR-ITEM_TREE.medication_test_two.v1.adl");
		expected = loadData("item_tree_medicataion_8.dadl");
		
		generator.loadTermMapFromClasspath("terms.txt");
		instance = generator.create(archetype);
		
		Element e1 = (Element) ((ItemTree) instance).getItems().get(0);
		Element e2 = (Element) ((ItemTree) expected).getItems().get(0);
		
		DvCodedText t1 = (DvCodedText) e1.getValue();
		DvCodedText t2 = (DvCodedText) e2.getValue();
		assertEquals(t1, t2);
		
		assertEquals(e2, e1);	
		assertEquals(expected, instance);
	}
	
	public void testCodedTextElementWithLocalTerm() throws Exception {
		archetype = loadArchetype("openEHR-EHR-ITEM_TREE.medication_test_nine.v1.adl");
		expected = loadData("item_tree_medicataion_11.dadl");
		
		generator.loadTermMapFromClasspath("terms.txt");
		instance = generator.create(archetype);
		
		Element e1 = (Element) ((ItemTree) instance).getItems().get(0);
		Element e2 = (Element) ((ItemTree) expected).getItems().get(0);
		
		DvCodedText t1 = (DvCodedText) e1.getValue();
		DvCodedText t2 = (DvCodedText) e2.getValue();
		assertEquals(t2, t1);
		
		assertEquals(e1, e2);	
		assertEquals(expected, instance);
	}
	
	public void testSingleCodedTextElementTree() throws Exception {
		archetype = loadArchetype("openEHR-EHR-ITEM_TREE.medication_test_two.v1.adl");
		expected = loadData("item_tree_medicataion_2.dadl");
		instance = generator.create(archetype);
		
		Element e1 = (Element) ((ItemTree) instance).getItems().get(0);
		Element e2 = (Element) ((ItemTree) expected).getItems().get(0);
		
		DvCodedText t1 = (DvCodedText) e1.getValue();
		DvCodedText t2 = (DvCodedText) e2.getValue();
		assertEquals(t1, t2);
		
		assertEquals(e1, e2);	
		assertEquals(expected, instance);
	}	
}
