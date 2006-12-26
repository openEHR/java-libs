/*
 * component:   "openEHR Reference Implementation"
 * description: "Class ItemTableTest"
 * keywords:    "unit test"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/BRANCHES/RM-1.0-update/libraries/src/test/org/openehr/rm/datastructure/itemstructure/ItemTableTest.java $"
 * revision:    "$LastChangedRevision: 50 $"
 * last_change: "$LastChangedDate: 2006-08-10 13:21:46 +0200 (Thu, 10 Aug 2006) $"
 */
/**
 * ItemTableTest
 *
 * @author Rong Chen
 * @version 1.0 
 */
package org.openehr.rm.datastructure.itemstructure;

import org.openehr.rm.common.archetyped.Locatable;
import org.openehr.rm.datastructure.DataStructureTestBase;
import org.openehr.rm.datastructure.itemstructure.representation.Cluster;
import org.openehr.rm.datastructure.itemstructure.representation.Element;
import org.openehr.rm.datastructure.itemstructure.representation.Item;
import org.openehr.rm.datatypes.text.DvText;

import java.util.ArrayList;
import java.util.List;

public class ItemTableTest extends DataStructureTestBase {

    public ItemTableTest(String test) {
        super(test);
    }

    /**
     * The fixture set up called before every test method.
     */
    protected void setUp() throws Exception {
        itemTable = init();
    }

    /**
     * The fixture clean up called after every test method.
     */
    protected void tearDown() throws Exception {
        itemTable = null;
    }

    public void testColumnCount() throws Exception {
        assertEquals("column count", COLM_NAMES.length,
                itemTable.columnCount());
    }

    public void testRowCount() throws Exception {
        assertEquals("row count", ROW_NAMES.length,
                itemTable.rowCount());
    }

    public void testRowNames() throws Exception {
        List<DvText> expected = new ArrayList<DvText>();
        for (int i = 0; i < ROW_NAMES.length; i++) {
            expected.add(text(ROW_NAMES[ i ]));
        }
        assertEquals("row names", expected, itemTable.rowNames());
    }

    public void testRowAt() throws Exception {
        //List<Element> expected = new ArrayList<Element>();
        //for (int i = 0; i < COLM_NAMES.length; i++) {
         //   expected.add(elementArray[ i ][ 1 ]);
        //}
        Cluster expected = rowArray[1];
        assertEquals("row at", expected, itemTable.ithRow(1));
    }

    public void testHasRowWithName() throws Exception {
        for (int i = 0; i < ROW_NAMES.length; i++) {
            assertTrue(itemTable.hasRowWithName(ROW_NAMES[ i ]));
        }
        assertFalse(itemTable.hasColumnWithName("dummy"));
    }

    public void testHasColumnWithName() throws Exception {
        for (int i = 0; i < COLM_NAMES.length; i++) {
            assertTrue(itemTable.hasColumnWithName(COLM_NAMES[ i ]));
        }
        assertFalse(itemTable.hasColumnWithName("dummy"));
    }

    public void testElementAt() throws Exception {

        // test the content of whole table
        for (int i = 0; i < ROW_NAMES.length; i++) {
            for (int j = 0; j < COLM_NAMES.length; j++) {
                assertEquals(elementArray[ i ][ j ],
                        itemTable.elementAtCell(i, j));
            }
        }

        // invalid col, row index
        int[][] badPos = {
            {-1, -1}, {-1, 0}, {0, -1},
            {3, 4}, {3, 1}, {1, 4}
        };
        for (int i = 0; i < badPos.length; i++) {
            try {
                itemTable.elementAtCell(badPos[ i ][ 0 ],
                        badPos[ i ][ 1 ]);
                fail("bad pos: " + badPos[ i ][ 0 ] + "," +
                        badPos[ i ][ 1 ]);
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }
        }
    }

    public void testNamedRow() throws Exception {
        /*List<Element> expected = new ArrayList<Element>();
        for (int i = 0; i < ROW_NAMES.length; i++) {
            expected.add(elementArray[ i ][ 1 ]);
        }*/
        Cluster expected = rowArray[1];
        assertEquals(expected, itemTable.namedRow(ROW_NAMES[ 1 ]));
    }

    public void testElementAtNamedCell() throws Exception {
        // test the content of whole table
        for (int i = 0; i < ROW_NAMES.length; i++) {
            for (int j = 0; j < COLM_NAMES.length; j++) {
                assertEquals(elementArray[ i ][ j ],
                        itemTable.elementAtNamedCell(ROW_NAMES[ i ],
                                COLM_NAMES[ j ]));
            }
        }
    }

    public void testValidPath() throws Exception {
        // valid path
        List<String> pathList = new ArrayList<String>();
        pathList.add(sep + TABLE_NAME);
        for (String col : ROW_NAMES) {
            pathList.add(sep + TABLE_NAME + sep + col);
        }
        for (String col : ROW_NAMES) {
            for (String row : COLM_NAMES) {
                pathList.add(sep + TABLE_NAME + sep + ItemTable.COL_IS + col
                        + sep + ItemTable.ROW_IS + row);
            }
        }
        for (String path : pathList) {
            assertTrue("expected valid path: " + path,
                    itemTable.validPath(path));
        }

        // invalid path
        pathList.clear();
        pathList.add("|" + TABLE_NAME);
        pathList.add(sep + "unknown_table_name");
        pathList.add(sep + TABLE_NAME + sep + "unknown_column_name");
        pathList.add(sep + TABLE_NAME + sep + ItemTable.COL_IS
                + "unknown_column_name" + sep + ItemTable.ROW_IS
                + "unknown_row_name");

        for (String path : pathList) {
            assertFalse("expected invalid path: " + path,
                    itemTable.validPath(path));
        }
    }

    public void testItemAtPath() throws Exception {
        // fetch whole
        assertEquals("fetch whole table", itemTable,
                itemTable.itemAtPath(sep + TABLE_NAME));

        // fetch column
        for (int i = 0, ii = ROW_NAMES.length; i < ii; i++) {
            String path = sep + TABLE_NAME + sep + ROW_NAMES[i];
            Locatable item = itemTable.itemAtPath(path);
            assertEquals("fetch column[" + i + "]", rowArray[i],
                    item);
        }
        // fetch cell
        for (int i = 0, ii = ROW_NAMES.length; i < ii; i++) {
            for (int j = 0, jj = COLM_NAMES.length; j < jj; j++) {
                String path = sep + TABLE_NAME + sep + ItemTable.COL_IS
                        + ROW_NAMES[i] + sep + ItemTable.ROW_IS
                        + COLM_NAMES[j];
                Locatable item = itemTable.itemAtPath(path);
                assertEquals("fetch cell[" + i + "][" + j + "]",
                        elementArray[i][j], item);
            }
        }
    }

    // create a itemTable as the example in the spec doc
    private ItemTable init() {

        // save element in the array for comparison
        elementArray = new Element[ ROW_NAMES.length ][ 0 ];
        rowArray = new Cluster[ROW_NAMES.length];

        for (int i = 0; i < ROW_NAMES.length; i++) {
            elementArray[ i ] = new Element[ COLM_NAMES.length ];
        }
        // key row
        for (int i = 0; i < COLM_NAMES.length; i++) {
            elementArray[ 0 ][ i ] = element(COLM_NAMES[ i ]);
        }
        // data_col left eye
        elementArray[ 1 ][ 0 ] = element(COLM_NAMES[ 0 ], 6, 24);
        elementArray[ 1 ][ 1 ] = element(COLM_NAMES[ 1 ], 6, 16);
        elementArray[ 1 ][ 2 ] = element(COLM_NAMES[ 2 ], "xxxx1111");
        elementArray[ 1 ][ 3 ] = element(COLM_NAMES[ 3 ], "yyyy1111");

        // data_col right eye
        elementArray[ 2 ][ 0 ] = element(COLM_NAMES[ 0 ], 6, 18);
        elementArray[ 2 ][ 1 ] = element(COLM_NAMES[ 1 ], 6, 6);
        elementArray[ 2 ][ 2 ] = element(COLM_NAMES[ 2 ], "xxxx2222");
        elementArray[ 2 ][ 3 ] = element(COLM_NAMES[ 3 ], "yyyy2222");


        // key row aspect
        List<List<Item>> rows = new ArrayList<List<Item>>();
        for (int i = 0; i < ROW_NAMES.length; i++) {
            rows.add(new ArrayList<Item>());
            for (int j = 0; j < COLM_NAMES.length; j++) {
                rows.get(i).add(elementArray[ i ][ j ]);
            }
        }

        // items
        List<Item> items = new ArrayList<Item>();
        for (int i = 0; i < ROW_NAMES.length; i++) {
            rowArray[i] = cluster(i == 0 ? "key_col" : "data_col",
                    ROW_NAMES[ i ], rows.get(i));
            items.add(rowArray[i]);
        }
        Cluster itemsCluster = cluster("items", "items", items);
        return new ItemTable(null, "at0001", text(TABLE_NAME),
                null, null, null, null, itemsCluster);
    }

    /* static fields */
    private static final String TABLE_NAME = "vision";
    private static final String[] COLM_NAMES = {
        "acuity unaided", "acuity w/ glasses", "color", "shape"
    };

    private static final String[] ROW_NAMES = {
        "aspect", "left eye", "right eye"
    };

    /* field */
    private ItemTable itemTable;
    private Element[][] elementArray;
    private Cluster[] rowArray;
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
 *  The Original Code is ItemTableTest.java
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