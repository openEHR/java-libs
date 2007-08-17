package org.openehr.tutorial;

import org.openehr.rm.datastructure.history.History;
import org.openehr.rm.datastructure.history.PointEvent;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;
import org.openehr.rm.datastructure.itemstructure.ItemTree;
import org.openehr.rm.datastructure.itemstructure.representation.Element;

import junit.framework.TestCase;

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
	}
	
	public void testCreateItemTree() {
		ItemTree value =instance.createItemTree();
		assertNotNull("itemTree is null", value);
	}
	
	private DataStructureExercise instance =  new DataStructureExercise();
}
