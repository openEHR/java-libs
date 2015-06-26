/*
 * component:   "openEHR Reference Implementation"
 * description: "Class ItemListTest"
 * keywords:    "unit test"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/BRANCHES/RM-1.0-update/libraries/src/test/org/openehr/rm/datastructure/itemstructure/ItemListTest.java $"
 * revision:    "$LastChangedRevision: 50 $"
 * last_change: "$LastChangedDate: 2006-08-10 13:21:46 +0200 (Thu, 10 Aug 2006) $"
 */
/**
 * ItemListTest
 *
 * @author Rong Chen
 * @version 1.0 
 */
package org.openehr.rm.datastructure.itemstructure;

import org.openehr.rm.datastructure.DataStructureTestBase;
import org.openehr.rm.datastructure.itemstructure.representation.Element;
import org.openehr.rm.datatypes.text.DvText;

import java.util.*;

public class ItemListTest extends DataStructureTestBase {

    public ItemListTest(String test) {
        super(test);
    }

    /**
     * The fixture set up called before every test method.
     */
    protected void setUp() throws Exception {
        itemList = init();
    }

    /**
     * The fixture clean up called after every test method.
     */
    protected void tearDown() throws Exception {
        itemList = null;
    }

    public void testItemCount() throws Exception {
        assertEquals("itemCount", NAMES.length, itemList.itemCount());
    }

    public void testItems() throws Exception {
        assertEquals("items", elements, itemList.getItems());
    }

    public void testNames() throws Exception {
        List<DvText> expected = new ArrayList<DvText>();
        for (int i = 0; i < NAMES.length; i++) {
            expected.add(text(NAMES[ i ]));
        }
        assertEquals("names", expected, itemList.names());
    }

    public void testNamedItem() throws Exception {
        for (int i = 0; i < NAMES.length; i++) {
            assertEquals(elements.get(i), itemList.namedItem(NAMES[ i ]));
        }
        assertTrue(itemList.namedItem("unknown name") == null);
    }

    public void testIthItem() throws Exception {
        for (int i = 0; i < NAMES.length; i++) {
            assertEquals(elements.get(i), itemList.ithItem(i));
        }
        assertTrue(itemList.namedItem("unknown name") == null);
    }
    
	public void testEquals() {
		List<Element> elementsOne = new ArrayList<Element>();

		elementsOne.add(element(NAMES[0], VALUES[0]));
		elementsOne.add(element(NAMES[1], VALUES[1]));
		elementsOne.add(element(NAMES[2], VALUES[2]));

		ItemList itemListOne = new ItemList(null, "at001", text(NAME), null,
				null, null, null, elementsOne);

		List<Element> elementsTwo = new ArrayList<Element>();

		elementsTwo.add(element(NAMES[0], VALUES[0]));
		elementsTwo.add(element(NAMES[1], VALUES[1]));
		elementsTwo.add(element(NAMES[2], VALUES[2]));

		ItemList itemListTwo = new ItemList(null, "at001", text(NAME), null,
				null, null, null, elementsTwo);

		assertTrue(itemListOne.equals(itemListTwo));
	}
	
	public void testEqualsElementsInMixedOrder()
	{
		List<Element> elementsOne = new ArrayList<Element>();

		elementsOne.add(element(NAMES[0], VALUES[0]));
		elementsOne.add(element(NAMES[1], VALUES[1]));
		elementsOne.add(element(NAMES[2], VALUES[2]));

		ItemList itemListOne = new ItemList(null, "at001", text(NAME), null,
				null, null, null, elementsOne);

		List<Element> elementsTwo = new ArrayList<Element>();

		elementsTwo.add(element(NAMES[0], VALUES[0]));
		elementsTwo.add(element(NAMES[2], VALUES[2]));
		elementsTwo.add(element(NAMES[1], VALUES[1]));

		ItemList itemListTwo = new ItemList(null, "at001", text(NAME), null,
				null, null, null, elementsTwo);

		assertTrue(itemListOne.equals(itemListTwo));
	}
	
	public void testNotEqualSize()
	{
		List<Element> elementsOne = new ArrayList<Element>();

		elementsOne.add(element(NAMES[0], VALUES[0]));
		elementsOne.add(element(NAMES[1], VALUES[1]));
		elementsOne.add(element(NAMES[2], VALUES[2]));

		ItemList itemListOne = new ItemList(null, "at001", text(NAME), null,
				null, null, null, elementsOne);

		List<Element> elementsTwo = new ArrayList<Element>();

		elementsTwo.add(element(NAMES[0], VALUES[0]));
		elementsTwo.add(element(NAMES[1], VALUES[1]));

		ItemList itemListTwo = new ItemList(null, "at001", text(NAME), null,
				null, null, null, elementsTwo);

		assertFalse(itemListOne.equals(itemListTwo));
	}
	
	public void testNotEqual()
	{
		List<Element> elementsOne = new ArrayList<Element>();

		elementsOne.add(element(NAMES[0], VALUES[0]));
		elementsOne.add(element(NAMES[1], VALUES[1]));
		elementsOne.add(element(NAMES[2], VALUES[2]));

		ItemList itemListOne = new ItemList(null, "at001", text(NAME), null,
				null, null, null, elementsOne);

		List<Element> elementsTwo = new ArrayList<Element>();

		elementsTwo.add(element(NAMES[0], VALUES[0]));
		elementsTwo.add(element(NAMES[1], VALUES[1]));
		elementsTwo.add(element("Location", "LocationValue"));

		ItemList itemListTwo = new ItemList(null, "at001", text(NAME), null,
				null, null, null, elementsTwo);

		assertFalse(itemListOne.equals(itemListTwo));
	}

    // create a itemList as the example in the spec doc
    private ItemList init() {

        // save element in the array for comparison
        elements = new ArrayList<Element>();

        // elements
        for (int i = 0; i < NAMES.length; i++) {
            elements.add(element(NAMES[ i ], VALUES[ i ]));
        }
        return new ItemList(null, "at001", text(NAME), null, null, null, 
                null, elements);
    }

    /* static fields */
    private static final String NAME = "BP protocol";
    private static final String[] NAMES = {
        "device", "cuff", "position"
    };
    private static final String[] VALUES = {
        "sphygmomanometer", "wide", "seated"
    };

    /* field */
    private ItemList itemList;
    private List<Element> elements;
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
 *  The Original Code is ItemListTest.java
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