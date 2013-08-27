package org.openehr.rm.common.archetyped;

import java.util.ArrayList;
import java.util.List;

import org.openehr.rm.datastructure.itemstructure.ItemList;
import org.openehr.rm.datastructure.itemstructure.representation.Element;
import org.openehr.rm.datatypes.text.DvText;

import junit.framework.TestCase;

public class AddChildTest extends TestCase {
	
	public void testAddItemToList() throws Exception {
		ItemList list = itemList();
		int count = list.itemCount();
		String path = "/items";
		
		DvText text = new DvText("new text");
		Element element = new Element("at0010", "New Element", text);
		
		list.addChild(path, element);
		
		assertTrue("child not added", list.itemCount() == count + 1);
		
		Element actual = (Element) list.itemAtPath("/items[at0010]");
		assertEquals(text, actual.getValue());
	}
	
	private ItemList itemList() {
		List<Element> items = new ArrayList<Element>();
		for(int i = 1; i <= 3; i++) {
			DvText text = new DvText("text " + i);
			Element element = new Element("at000" + i, "element " + i, text);		
			items.add(element);
		}		
		ItemList list = new ItemList("at0000", "list", items);
		return list;
	}
}