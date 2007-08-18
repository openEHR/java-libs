package org.openehr.tutorial;

import org.openehr.rm.datastructure.history.History;
import org.openehr.rm.datastructure.history.PointEvent;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;
import org.openehr.rm.datastructure.itemstructure.ItemTree;
import org.openehr.rm.datastructure.itemstructure.representation.Element;
import org.openehr.rm.datastructure.itemstructure.representation.Item;
import org.openehr.rm.datatypes.quantity.DvQuantity;
import org.openehr.rm.datatypes.text.DvText;

import junit.framework.TestCase;
import org.openehr.tutorial.answer.DataStructureAnswer;

public class DataStructureExerciseTest extends TestCase {
	
	public void testCreateHistory() {
		History<ItemStructure> value = instance.createHistory();
		assertNotNull("history is null", value);
	}
	
	public void testCreatePointEvent() {
		PointEvent value = instance.createPointEvent();
		assertNotNull("pointEvent is null", value);
	}
	
	public void testCreateElement() {
		Element value = instance.createElement();
		assertNotNull("element is null", value);
		assertEquals("element name wrong", new DvText("Blood glucose"), 
					value.getName());
		assertTrue("value wrong", value.getValue() instanceof DvQuantity);		
	}
	
	public void testCreateItemTree() {
		ItemTree value =instance.createItemTree();
		assertNotNull("itemTree is null", value);
		assertEquals("itmes size wrong", 1, value.getItems().size());
		
		Item item = value.getItems().get(0);
		assertTrue("item type wrong", item instanceof Element);		
	}
	
	private DataStructureExercise instance =  new DataStructureAnswer();
}
