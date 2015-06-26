/*
 * component:   "openEHR Reference Implementation"
 * description: "Class ItemSingleTest"
 * keywords:    "unit test"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/BRANCHES/RM-1.0-update/libraries/src/test/org/openehr/rm/datastructure/itemstructure/ItemSingleTest.java $"
 * revision:    "$LastChangedRevision: 50 $"
 * last_change: "$LastChangedDate: 2006-08-10 13:21:46 +0200 (Thu, 10 Aug 2006) $"
 */
/**
 * ItemSingleTest
 *
 * @author Rong Chen
 * @version 1.0 
 */
package org.openehr.rm.datastructure.itemstructure;


import org.openehr.rm.datastructure.DataStructureTestBase;
import org.openehr.rm.datastructure.itemstructure.representation.Element;
import org.openehr.rm.datatypes.text.DvText;

public class ItemSingleTest extends DataStructureTestBase {

    public ItemSingleTest(String test) {
        super(test);
    }

    /**
     * The fixture set up called before every test method.
     */
    protected void setUp() throws Exception {
        element = new Element("at0002", new DvText("test element"), 
        		new DvText("text value"));
        itemSingle = new ItemSingle(null, "at0001", text("single item"),
                null, null, null, null, element);
    }

    /**
     * The fixture clean up called after every test method.
     */
    protected void tearDown() throws Exception {
    	path = null;
    	value = null;
    	element = null;
    	itemSingle = null;
    }

    public void testItemAtPathOnItem() throws Exception {
    	path = "/item";
    	value = itemSingle.itemAtPath(path);
    	assertEquals("unexpected item", element, value);
    }
    
    public void testItemAtPathOnItemWithName() throws Exception {
    	path = "/item['test element']";
    	value = itemSingle.itemAtPath(path);
    	assertEquals("unexpected item", element, value);
    }
    
    public void testItemAtPathOnItemWithATCode() throws Exception {
    	path = "/item[at0002]";
    	value = itemSingle.itemAtPath(path);
    	assertEquals("unexpected item", element, value);
    }
    
    public void testItemAtPathOnItemWithNameAndATCode() throws Exception {
    	path = "/item[at0002, 'test element']";
    	value = itemSingle.itemAtPath(path);
    	assertEquals("unexpected item", element, value);
    }
    
    public void testItemAtPathOnItemValue() throws Exception {
    	path = "/item/value";
    	value = itemSingle.itemAtPath(path);
    	assertEquals("unexpected item.value", element.getValue(), value);
    }
    
    public void testItemAtPathOnItemValueWithName() throws Exception {
    	path = "/item['test element']/value";
    	value = itemSingle.itemAtPath(path);
    	assertEquals("unexpected item.value", element.getValue(), value);
    }
    
    public void testItemAtPathOnItemValueWithATCode() throws Exception {
    	path = "/item[at0002]/value";
    	value = itemSingle.itemAtPath(path);
    	assertEquals("unexpected item.value", element.getValue(), value);
    }
    
    public void testItemAtPathOnItemValueWithNameAndATCode() throws Exception {
    	path = "/item[at0002, 'test element']/value";
    	value = itemSingle.itemAtPath(path);
    	assertEquals("unexpected item.value", element.getValue(), value);
    }
    
    public void testItemAtPathOnItemUnmatched() throws Exception {
    	path = "/item2";
    	value = itemSingle.itemAtPath(path);
    	assertNull("expected null", value);
    }
    
    public void testItemAtPathOnItemWithNameUnmatched() throws Exception {
    	path = "/item['test element 2']";
    	value = itemSingle.itemAtPath(path);
    	assertNull("expected null", value);
    }
    
    public void testItemAtPathOnItemWithATCodeUnmatched() throws Exception {
    	path = "/item[at0222]";
    	value = itemSingle.itemAtPath(path);
    	assertNull("expected null", value);
    }
    
    public void testItemAtPathOnItemWithNameAndATCodeUnmatched() throws Exception {
    	path = "/item[at3333, 'test element']";
    	value = itemSingle.itemAtPath(path);
    	assertNull("expected null", value);
    }
    
    public void testItemAtPathOnWhole() throws Exception {
    	path = "/";
    	value = itemSingle.itemAtPath(path);
    	assertEquals("unexpected whole", itemSingle, value);
    }
    
	public void testEquals() {
		Element elementOne = new Element("at0002", new DvText("test element"),
				new DvText("text value"));
		ItemSingle itemSingleOne = new ItemSingle(null, "at0001",
				text("single item"), null, null, null, null, elementOne);

		Element elementTwo = new Element("at0002", new DvText("test element"),
				new DvText("text value"));
		ItemSingle itemSingleTwo = new ItemSingle(null, "at0001",
				text("single item"), null, null, null, null, elementTwo);

		assertTrue(itemSingleOne.equals(itemSingleTwo));
	}

	public void testNotEquals() {
		Element elementOne = new Element("at0002", new DvText("test element"),
				new DvText("text value"));
		ItemSingle itemSingleOne = new ItemSingle(null, "at0001",
				text("single item"), null, null, null, null, elementOne);

		Element elementTwo = new Element("at0003", new DvText("test element"),
				new DvText("text value"));
		ItemSingle itemSingleTwo = new ItemSingle(null, "at0001",
				text("single item"), null, null, null, null, elementTwo);

		assertFalse(itemSingleOne.equals(itemSingleTwo));
	}
    
    
    
    private String path;
    private Object value;
    private Element element;
    private ItemSingle itemSingle;
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
 *  The Original Code is ItemSingleTest.java
 *
 *  The Initial Developer of the Original Code is Rong Chen.
 *  Portions created by the Initial Developer are Copyright (C) 2003-2004
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