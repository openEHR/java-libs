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
import org.openehr.rm.datastructure.itemstructure.representation.Cluster;
import org.openehr.rm.datastructure.itemstructure.representation.Element;
import org.openehr.rm.datastructure.itemstructure.representation.Item;
import org.openehr.rm.datatypes.text.DvText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

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
        assertEquals("items", Arrays.asList(elementArray),
                itemList.items());
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
            assertEquals(elementArray[ i ],
                    itemList.namedItem(NAMES[ i ]));
        }
        assertTrue(itemList.namedItem("unknown name") == null);
    }

    public void testIthItem() throws Exception {
        for (int i = 0; i < NAMES.length; i++) {
            assertEquals(elementArray[ i ], itemList.ithItem(i));
        }
        assertTrue(itemList.namedItem("unknown name") == null);
    }

    public void testValidPath() throws Exception {

        List<String> paths = new ArrayList<String>();

        // good paths
        String root = ItemStructure.PATH_SEPARATOR + NAME;
        paths.add(root);
        for (int i = 0; i < NAMES.length; i++) {
            paths.add(root + ItemStructure.PATH_SEPARATOR
                    + "item=" + i);
            paths.add(root + ItemStructure.PATH_SEPARATOR
                    + NAMES[ i ]);
        }
        for (Iterator it = paths.iterator(); it.hasNext();) {
            String path = (String) it.next();
            assertTrue(path, itemList.validPath(path));
        }

        // bad paths
        paths = new ArrayList<String>();
        paths.add(NAME);
        paths.add(NAME + ItemStructure.PATH_SEPARATOR);
        paths.add(ItemStructure.PATH_SEPARATOR + NAME +
                ItemStructure.PATH_SEPARATOR);
        paths.add(root + ItemStructure.PATH_SEPARATOR +
                "unknown element");
        paths.add(root + ItemStructure.PATH_SEPARATOR + "item=-1");
        paths.add(root + ItemStructure.PATH_SEPARATOR + "item="
                + NAMES.length);
        for (Iterator it = paths.iterator(); it.hasNext();) {
            String path = (String) it.next();
            assertFalse(path, itemList.validPath(path));
        }
    }

    public void testItemAtPath() throws Exception {
        assertEquals("whole list", itemList, itemList.itemAtPath(
                ItemStructure.PATH_SEPARATOR + NAME));

        // item by index
        for (int i = 0; i < NAMES.length; i++) {
            String path = ItemStructure.PATH_SEPARATOR + NAME +
                    ItemStructure.PATH_SEPARATOR + "item=" + i;
            assertEquals("item at " + i, NAMES[ i ],
                    itemList.itemAtPath(path).getName().getValue());
        }

        // item by name
        for (int i = 0; i < NAMES.length; i++) {
            String path = ItemStructure.PATH_SEPARATOR + NAME +
                    ItemStructure.PATH_SEPARATOR + NAMES[ i ];
            assertEquals("item of name " + i, NAMES[ i ],
                    itemList.itemAtPath(path).getName().getValue());
        }
    }

    // create a itemList as the example in the spec doc
    private ItemList init() {

        // save element in the array for comparison
        elementArray = new Element[ NAMES.length ];

        // elements
        for (int i = 0; i < NAMES.length; i++) {
            elementArray[ i ] = element(NAMES[ i ], VALUES[ i ]);
        }
        Cluster itemsCluster = cluster("items", "items",
                new ArrayList<Item>(Arrays.asList(elementArray)));

        return new ItemList(null, "at001", text(NAME), null, null, null, 
                null, itemsCluster);
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
    private Element[] elementArray;
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