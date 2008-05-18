/*
 * component:   "openEHR Reference Implementation"
 * description: "Class GenericEntryPathTest"
 * keywords:    "unit test"
 *
 * author:      "Rong Chen <rong.acode@gmail.com>"
 * support:     "openEHR Java project, http://www.openehr.org/projects/java.html"
 * copyright:   "Copyright (c) 2008 Cambio Healthcare Systems, AB"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: $"
 * revision:    "$LastChangedRevision: $"
 * last_change: "$LastChangedDate: $"
 */
package org.openehr.rm.integration;

import java.util.ArrayList;
import java.util.List;

import org.openehr.rm.datastructure.itemstructure.ItemTree;
import org.openehr.rm.datastructure.itemstructure.representation.Element;
import org.openehr.rm.datastructure.itemstructure.representation.Item;
import org.openehr.rm.datatypes.quantity.DvQuantity;

import junit.framework.TestCase;

public class GenericEntryPathTest extends TestCase {
	
	public void setUp() {
		String archetypeNodeId = "at0001";
		String name = "test generic entry";
		element = new Element("at0002", "element",	new DvQuantity(12.0));
		List<Item> items = new ArrayList<Item>();
		items.add(element);
		itemTree = new ItemTree("at0003", "item tree", items);		
		entry = new GenericEntry(archetypeNodeId, name, itemTree);		
	}
	
	public void testItemAtPathWhole() {
		path = "/";
		value = entry.itemAtPath(path);
		assertEquals(entry, value);
	}
	
	public void testItemAtPathData() {
		path = "/data";
		value = entry.itemAtPath(path);
		assertEquals(itemTree, value);
	}
	
	public void testItemAtPathDataCode() {
		path = "/data[at0003]";
		value = entry.itemAtPath(path);
		assertEquals(itemTree, value);
	}
	
	public void testItemAtPathDataName() {
		path = "/data['item tree']";
		value = entry.itemAtPath(path);
		assertEquals(itemTree, value);
	}
	
	public void testItemAtPathElementAT() {
		path = "/data/items[at0002]";
		value = entry.itemAtPath(path);
		assertEquals(element, value);
	}
	
	public void testItemAtPathElementName() {
		path = "/data/items['element']";
		value = entry.itemAtPath(path);
		assertEquals(element, value);
	}
	
	public void testItemAtPathElementBoth() {
		path = "/data/items[at0002, 'element']";
		value = entry.itemAtPath(path);
		assertEquals(element, value);
	}
	
	public void testItemAtPathElementValueAT() {
		path = "/data/items[at0002]/value";
		value = entry.itemAtPath(path);
		assertEquals(element.getValue(), value);
	}
	
	public void testItemAtPathElementValueName() {
		path = "/data/items['element']/value";
		value = entry.itemAtPath(path);
		assertEquals(element.getValue(), value);
	}
	
	public void testItemAtPathElementValueBoth() {
		path = "/data/items[at0002, 'element']/value";
		value = entry.itemAtPath(path);
		assertEquals(element.getValue(), value);
	}
	
	public void testItemAtPathElementValueMagnitudeAT() {
		path = "/data/items[at0002]/value/magnitude";
		value = entry.itemAtPath(path);
		assertEquals(12.0, value);
	}
	
	public void testItemAtPathElementValueMagnitudeName() {
		path = "/data/items['element']/value/magnitude";
		value = entry.itemAtPath(path);
		assertEquals(12.0, value);
	}
	
	public void testItemAtPathElementValueMagnitudeBoth() {
		path = "/data/items[at0002, 'element']/value/magnitude";
		value = entry.itemAtPath(path);
		assertEquals(12.0, value);
	}
	
	private Element element;
	private ItemTree itemTree;
	private GenericEntry entry;
	private String path;
	private Object value;
}

/*
 *  ***** BEGIN LICENSE BLOCK *****
 *  Version: MPL 1.1/GPL 2.0/LGPL 2.1
 *
 *  The contents of this file are subject to the Mozilla Public License Version
 *  1.1 (the 'License'); you may not use this file except in compliance with
 *  the License. You may obtain a copy of the License at
 *  http://www.mozilla.org/MPL/
 *
 *  Software distributed under the License is distributed on an 'AS IS' basis,
 *  WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 *  for the specific language governing rights and limitations under the
 *  License.
 *
 *  The Original Code is GenericEntryPathTest.java
 *
 *  The Initial Developer of the Original Code is Rong Chen.
 *  Portions created by the Initial Developer are Copyright (C) 2003-2008
 *  the Initial Developer. All Rights Reserved.
 *
 *  Contributor(s):
 *
 * Software distributed under the License is distributed on an 'AS IS' basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 *  ***** END LICENSE BLOCK *****
 */