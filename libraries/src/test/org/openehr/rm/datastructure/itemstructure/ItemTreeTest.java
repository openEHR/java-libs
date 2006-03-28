/*
 * component:   "openEHR Reference Implementation"
 * description: "Class ItemTreeTest"
 * keywords:    "unit test"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL$"
 * revision:    "$LastChangedRevision$"
 * last_change: "$LastChangedDate$"
 */
/**
 * ItemTreeTest
 *
 * @author Rong Chen
 * @version 1.0 
 */
package org.openehr.rm.datastructure.itemstructure;

import org.openehr.rm.datastructure.DataStructureTestBase;
import org.openehr.rm.datastructure.itemstructure.representation.Element;
import org.openehr.rm.datastructure.itemstructure.representation.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemTreeTest extends DataStructureTestBase {

    public ItemTreeTest(String test) {
        super(test);
    }

    /**
     * The fixture set up called before every test method.
     */
    protected void setUp() throws Exception {
        itemTree = init();
    }

    /**
     * The fixture clean up called after every test method.
     */
    protected void tearDown() throws Exception {
        itemTree = null;
    }

    public void testElementAtPath() throws Exception {

        // example path taken from spec doc
        String path = "/biochemistry/lipid studies/LDL cholesterol";
        assertTrue(itemTree.hasElementPath(path));

        Element element = itemTree.elementAtPath(path);
        assertTrue(element != null);
        assertEquals("element.name", "LDL cholesterol",
                element.getName().getValue());

        String[] badPaths = {
            "/bad root/lipid studies/LDL cholesterol",
            "/biochemistry/items/lipid studies/LDL cholesterol",
            "/biochemistry/bad node/LDL cholesterol",
            "/biochemistry/lipid studies/bad leaf"
        };
        for(int i = 0; i < badPaths.length; i++) {
            assertFalse(badPaths[i], itemTree.hasElementPath(badPaths[i]));
        }
    }

    // create an itemTree similar to the example in the spec doc
    private ItemTree init() {

        // battery group
        List<Item> batteryGroup = new ArrayList<Item>();
        batteryGroup.add(element("battery item", "total cholesterol", 99.9));
        batteryGroup.add(element("battery item", "LDL cholesterol", 66.6));
        batteryGroup.add(element("battery item", "HDL cholesterol", 33.3));

        // items
        List<Item> items = new ArrayList<Item>();
        items.add(element("sample", "sample", "serum", "12345"));
        items.add(cluster("batter group","lipid studies", batteryGroup));
        items.add(element("comment", "comment", "check these often"));

        // itemTree
        return new ItemTree(null, "at0001", text("biochemistry"), null,
                null, null, cluster("items", "items", items));
    }



    /* fields */
    private ItemTree itemTree;
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
 *  The Original Code is ItemTreeTest.java
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