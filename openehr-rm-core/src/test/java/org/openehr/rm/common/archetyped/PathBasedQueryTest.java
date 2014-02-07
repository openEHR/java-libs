package org.openehr.rm.common.archetyped;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.openehr.rm.datastructure.itemstructure.ItemList;
import org.openehr.rm.datastructure.itemstructure.representation.Element;
import org.openehr.rm.datatypes.text.*;

public class PathBasedQueryTest extends TestCase {

	public void testPathWithCommaInNamePredicate() throws Exception {
		DvText text = new DvText("yes");
		Element element = new Element("at0002", "Status, 2nd", text);
		List<Element> items = new ArrayList<Element>();
		items.add(element);
		ItemList list = new ItemList("at0001", "list", items);
		
		obj = list.itemAtPath("/items[at0002 and name/value='Status, 2nd']");
		assertNotNull("failed to get with comma in name predicate", obj);
	}
	
	public void testPathWithCommaInNamePredicateSymplified() throws Exception {
		DvText text = new DvText("yes");
		Element element = new Element("at0002", "Status, 2nd", text);
		List<Element> items = new ArrayList<Element>();
		items.add(element);
		ItemList list = new ItemList("at0001", "list", items);
		
		obj = list.itemAtPath("/items[at0002, 'Status, 2nd']");
		assertNotNull("failed to get with comma in name predicate", obj);
	}
	
	public void testPathWithSpaceInNamePredicate() throws Exception {
		DvText text = new DvText("yes");
		Element element = new Element("at0002", "Status 2nd", text);
		List<Element> items = new ArrayList<Element>();
		items.add(element);
		ItemList list = new ItemList("at0001", "list", items);
		
		obj = list.itemAtPath("/items[at0002 and name/value='Status 2nd']");
		assertNotNull("failed to get with space in name predicate", obj);
	}
	
	public void testPathWithSpaceInNamePredicateSymplified() throws Exception {
		DvText text = new DvText("yes");
		Element element = new Element("at0002", "Status 2nd", text);
		List<Element> items = new ArrayList<Element>();
		items.add(element);
		ItemList list = new ItemList("at0001", "list", items);
		
		obj = list.itemAtPath("/items[at0002, 'Status 2nd']");
		assertNotNull("failed to get with space in name predicate", obj);
	}
	
	public void testPathWithPhraseANDInNamePredicate() throws Exception {
		DvText text = new DvText("yes");
		Element element = new Element("at0002", "MEDICINSK BEHANDLING", text);
		List<Element> items = new ArrayList<Element>();
		items.add(element);
		ItemList list = new ItemList("at0001", "list", items);
		
		obj = list.itemAtPath("/items[at0002 and name/value='MEDICINSK BEHANDLING']");
		assertNotNull("failed to get with 'AND' in name predicate", obj);
	}
	
	public void testPathWithPhraseANDInNamePredicateSimplified() throws Exception {
		DvText text = new DvText("yes");
		Element element = new Element("at0002", "MEDICINSK BEHANDLING", text);
		List<Element> items = new ArrayList<Element>();
		items.add(element);
		ItemList list = new ItemList("at0001", "list", items);
		
		obj = list.itemAtPath("/items[at0002, 'MEDICINSK BEHANDLING']");
		assertNotNull("failed to get with 'AND' in name predicate", obj);
	}
	
	public void testPathWithSimpleNamePredicate() throws Exception {
		DvText text = new DvText("yes");
		Element element = new Element("at0002", "Status", text);
		List<Element> items = new ArrayList<Element>();
		items.add(element);
		ItemList list = new ItemList("at0001", "list", items);
		
		obj = list.itemAtPath("/items[at0002 and name/value='Status']");
		assertNotNull("failed to get with plain name predicate", obj);
	}
	
	public void testPathWithSimpleNamePredicateSimplified() throws Exception {
		DvText text = new DvText("yes");
		Element element = new Element("at0002", "Status", text);
		List<Element> items = new ArrayList<Element>();
		items.add(element);
		ItemList list = new ItemList("at0001", "list", items);
		
		obj = list.itemAtPath("/items[at0002, 'Status']");
		assertNotNull("failed to get with plain name predicate", obj);
	}
	
	public void testPathPredicateWithAtCode() throws Exception {
		DvText text = new DvText("yes");
		Element element = new Element("at0002", "Status, CABG", text);
		List<Element> items = new ArrayList<Element>();
		items.add(element);
		ItemList list = new ItemList("at0001", "list", items);
		
		// plain path without name predicate
		obj = list.itemAtPath("/items[at0002]");
		assertNotNull("failed to get with at-code as predicate", obj);
	}
	
	private Object obj;
}
