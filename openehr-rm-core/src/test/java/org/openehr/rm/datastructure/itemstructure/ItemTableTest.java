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

import org.openehr.rm.datastructure.DataStructureTestBase;
import org.openehr.rm.datastructure.itemstructure.representation.Cluster;
import org.openehr.rm.datastructure.itemstructure.representation.Element;
import org.openehr.rm.datastructure.itemstructure.representation.Item;
import org.openehr.rm.datatypes.quantity.*;
import org.openehr.rm.datatypes.text.DvText;

import java.util.*;

public class ItemTableTest extends DataStructureTestBase {

    public ItemTableTest(String test) {
        super(test);
    }

    /**
     * The fixture set up called before every test method.
     */
    protected void setUp() throws Exception {
    	createTable();       
    }
    
    private void createTable() throws Exception {
    	rows = new ArrayList<Cluster>();
    	
    	// 1st row
    	Element eyes = new Element("at0001", new DvText("eye(s)"),
    			new DvText("right"));
    	Element acuity = new Element("at0002", new DvText("visual acuity"),
    			new DvProportion(6, 6, ProportionKind.RATIO, 0));
    	List<Item> items = new ArrayList<Item>();
    	items.add(eyes);
    	items.add(acuity);
    	rows.add(new Cluster("at0003", new DvText("1"), items));
    	
    	// 2nd row
    	eyes = new Element("at0004", new DvText("eye(s)"),
     			new DvText("left"));
     	acuity = new Element("at0005", new DvText("visual acuity"),
     			new DvProportion(6, 18, ProportionKind.RATIO, 0));
     	items = new ArrayList<Item>();
     	items.add(eyes);
     	items.add(acuity);
     	rows.add(new Cluster("at0006", new DvText("2"), items));
    	
     	// 3rd row
     	eyes = new Element("at0007", new DvText("eye(s)"),
     			new DvText("both"));
     	acuity = new Element("at0008", new DvText("visual acuity"),
     			new DvProportion(6, 6, ProportionKind.RATIO, 0));
     	items = new ArrayList<Item>();
     	items.add(eyes);
     	items.add(acuity);
     	rows.add(new Cluster("at0009", new DvText("3"), items));    	
    	
        itemTable = new ItemTable("at0010", new DvText("vision"), rows);       
    }

    /**
     * The fixture clean up called after every test method.
     */
    protected void tearDown() throws Exception {
        itemTable = null;
    }

    public void testColumnCount() throws Exception {
        assertEquals("column count wrong", 2, itemTable.columnCount());
    }

    public void testRowCount() throws Exception {
        assertEquals("row count wrong", 3, itemTable.rowCount());
    }

    public void testRowNames() throws Exception {
        List<DvText> names = new ArrayList<DvText>();
        names.add(new DvText("1"));
        names.add(new DvText("2"));
        names.add(new DvText("3"));
        assertEquals("row names wrong", names, itemTable.rowNames());
    }

    public void testIthRow1() throws Exception {
        assertEquals(rows.get(0), itemTable.ithRow(1));
    }
    
    public void testIthRow2() throws Exception {
        assertEquals(rows.get(1), itemTable.ithRow(2));
    }
    
    public void testIthRow3() throws Exception {
        assertEquals(rows.get(2), itemTable.ithRow(3));
    }

    public void testHasRowWithNameOne() throws Exception {
        assertTrue(itemTable.hasRowWithName("1"));        
    }
    
    public void testHasRowWithNameTwo() throws Exception {
        assertTrue(itemTable.hasRowWithName("2"));        
    }
    
    public void testHasRowWithNameThree() throws Exception {
        assertTrue(itemTable.hasRowWithName("3"));        
    }
    
    public void testHasRowWithNameUnkown() throws Exception {
        assertFalse(itemTable.hasRowWithName("unknown"));        
    }

    public void testHasColumnWithNameEyes() throws Exception {
        assertTrue(itemTable.hasColumnWithName("eye(s)"));
    }
    
    public void testHasColumnWithNameAcuity() throws Exception {
        assertTrue(itemTable.hasColumnWithName("visual acuity"));
    }
    
    public void testElementAtColumn1Row1() throws Exception {
    	Element element = itemTable.elementAtCell(1, 1);
    	assertEquals("at0001", element.getArchetypeNodeId());
    }
    
    public void testElementAtColumn2Row1() throws Exception {
    	Element element = itemTable.elementAtCell(2, 1);
    	assertEquals("at0002", element.getArchetypeNodeId());
    }
    
    public void testElementAtColumn1Row2() throws Exception {
    	Element element = itemTable.elementAtCell(1, 2);
    	assertEquals("at0004", element.getArchetypeNodeId());
    }
    
    public void testElementAtColumn2Row2() throws Exception {
    	Element element = itemTable.elementAtCell(2, 2);
    	assertEquals("at0005", element.getArchetypeNodeId());
    }
    
    public void testElementAtColumn1Row3() throws Exception {
    	Element element = itemTable.elementAtCell(1, 3);
    	assertEquals("at0007", element.getArchetypeNodeId());
    }
    
    public void testElementAtColumn2Row3() throws Exception {
    	Element element = itemTable.elementAtCell(2, 3);
    	assertEquals("at0008", element.getArchetypeNodeId());
    }

    public void testNamedRow1() throws Exception {
        assertEquals(rows.get(0), itemTable.namedRow("1"));
    }
    
    public void testNamedRow2() throws Exception {
        assertEquals(rows.get(1), itemTable.namedRow("2"));
    }
    
    public void testNamedRow3() throws Exception {
        assertEquals(rows.get(2), itemTable.namedRow("3"));
    }

    public void testElementAtNamedCellOneEyes() throws Exception {
    	element = itemTable.elementAtNamedCell("1", "eye(s)");
        assertEquals("at0001", element.getArchetypeNodeId());
    }
    
    public void testElementAtNamedCellOneAcuity() throws Exception {
    	element = itemTable.elementAtNamedCell("1", "visual acuity");
        assertEquals("at0002", element.getArchetypeNodeId());
    }
    
    public void testElementAtNamedCellTwoEyes() throws Exception {
    	element = itemTable.elementAtNamedCell("2", "eye(s)");
        assertEquals("at0004", element.getArchetypeNodeId());
    }
    
    public void testElementAtNamedCellTwoAcuity() throws Exception {
    	element = itemTable.elementAtNamedCell("2", "visual acuity");
        assertEquals("at0005", element.getArchetypeNodeId());
    }
    
    public void testElementAtNamedCellThreeEyes() throws Exception {
    	element = itemTable.elementAtNamedCell("3", "eye(s)");
        assertEquals("at0007", element.getArchetypeNodeId());
    }
    
    public void testElementAtNamedCellThreeAcuity() throws Exception {
    	element = itemTable.elementAtNamedCell("3", "visual acuity");
        assertEquals("at0008", element.getArchetypeNodeId());
    }

    public void testItemAtPathWhole() throws Exception {
        assertEquals(itemTable, itemTable.itemAtPath("/"));
    }
    
    public void testItemAtPathRow1() throws Exception {
        assertEquals(rows.get(0), itemTable.itemAtPath("/rows[at0003]"));
    }
    
    public void testItemAtPathRow1Column1() throws Exception {
        assertEquals(rows.get(0).getItems().get(0), 
        		itemTable.itemAtPath("/rows[at0003]/items[at0001]"));
    }
    
    public void testItemAtPathRow1Column2() throws Exception {
        assertEquals(rows.get(0).getItems().get(1), 
        		itemTable.itemAtPath("/rows[at0003]/items[at0002]"));
    }
    
    public void testItemAtPathRow2() throws Exception {
        assertEquals(rows.get(1), 
        		itemTable.itemAtPath("/rows[at0006]"));
    }
    
    public void testItemAtPathRow2Column1() throws Exception {
        assertEquals(rows.get(1).getItems().get(0), 
        		itemTable.itemAtPath("/rows[at0006]/items[at0004]"));
    }
    
    public void testItemAtPathRow2Column2() throws Exception {
        assertEquals(rows.get(1).getItems().get(1), 
        		itemTable.itemAtPath("/rows[at0006]/items[at0005]"));
    }
    
    public void testItemAtPathRow3() throws Exception {
        assertEquals(rows.get(2), 
        		itemTable.itemAtPath("/rows[at0009]"));
    }
    
    public void testItemAtPathRow3Column1() throws Exception {
        assertEquals(rows.get(2).getItems().get(0), 
        		itemTable.itemAtPath("/rows[at0009]/items[at0007]"));
    }
    
    public void testItemAtPathRow3Column2() throws Exception {
        assertEquals(rows.get(2).getItems().get(1), 
        		itemTable.itemAtPath("/rows[at0009]/items[at0008]"));
    }
    
    public void testItemAtPathRow1Name() throws Exception {
        assertEquals(rows.get(0), itemTable.itemAtPath("/rows['1']"));
    }
    
    public void testItemAtPathRow1Column1Name() throws Exception {
        assertEquals(rows.get(0).getItems().get(0), 
        		itemTable.itemAtPath("/rows['1']/items['eye(s)']"));
    }
    
    public void testItemAtPathRow1Column2Name() throws Exception {
        assertEquals(rows.get(0).getItems().get(1), 
        		itemTable.itemAtPath("/rows['1']/items['visual acuity']"));
    }
    
    public void testItemAtPathRow2Name() throws Exception {
        assertEquals(rows.get(1), 
        		itemTable.itemAtPath("/rows['2']"));
    }
    
    public void testItemAtPathRow2Column1Name() throws Exception {
        assertEquals(rows.get(1).getItems().get(0), 
        		itemTable.itemAtPath("/rows['2']/items['eye(s)']"));
    }
    
    public void testItemAtPathRow2Column2Name() throws Exception {
        assertEquals(rows.get(1).getItems().get(1), 
        		itemTable.itemAtPath("/rows['2']/items['visual acuity']"));
    }
    
    public void testItemAtPathRow3Name() throws Exception {
        assertEquals(rows.get(2), 
        		itemTable.itemAtPath("/rows['3']"));
    }
    
    public void testItemAtPathRow3Column1Name() throws Exception {
        assertEquals(rows.get(2).getItems().get(0), 
        		itemTable.itemAtPath("/rows['3']/items['eye(s)']"));
    }
    
    public void testItemAtPathRow3Column2Name() throws Exception {
        assertEquals(rows.get(2).getItems().get(1), 
        		itemTable.itemAtPath("/rows['3']/items['visual acuity']"));
    }

	public void testEquals() {
		List<Cluster> rows = new ArrayList<Cluster>();

		// 1st row
		Element eyes = new Element("at0001", new DvText("eye(s)"), new DvText(
				"right"));
		Element acuity = new Element("at0002", new DvText("visual acuity"),
				new DvProportion(6, 6, ProportionKind.RATIO, 0));
		List<Item> itemsOne = new ArrayList<Item>();
		itemsOne.add(eyes);
		itemsOne.add(acuity);

		// 2nd row
		eyes = new Element("at0004", new DvText("eye(s)"), new DvText("left"));
		acuity = new Element("at0005", new DvText("visual acuity"),
				new DvProportion(6, 18, ProportionKind.RATIO, 0));
		List<Item> itemsTwo = new ArrayList<Item>();
		itemsTwo.add(eyes);
		itemsTwo.add(acuity);

		rows.add(new Cluster("at0003", new DvText("1"), itemsOne));
		rows.add(new Cluster("at0006", new DvText("2"), itemsTwo));

		ItemTable itemTableOne = new ItemTable("at0010", new DvText("vision"),
				rows);
		ItemTable itemTableTwo = new ItemTable("at0010", new DvText("vision"),
				rows);

		assertTrue(itemTableOne.equals(itemTableTwo));
	}
	
	public void testNotEqualsDifferentNodeId() {
		List<Cluster> rows = new ArrayList<Cluster>();

		// 1st row
		Element eyes = new Element("at0001", new DvText("eye(s)"), new DvText(
				"right"));
		Element acuity = new Element("at0002", new DvText("visual acuity"),
				new DvProportion(6, 6, ProportionKind.RATIO, 0));
		List<Item> itemsOne = new ArrayList<Item>();
		itemsOne.add(eyes);
		itemsOne.add(acuity);

		// 2nd row
		eyes = new Element("at0004", new DvText("eye(s)"), new DvText("left"));
		acuity = new Element("at0005", new DvText("visual acuity"),
				new DvProportion(6, 18, ProportionKind.RATIO, 0));
		List<Item> itemsTwo = new ArrayList<Item>();
		itemsTwo.add(eyes);
		itemsTwo.add(acuity);

		rows.add(new Cluster("at0003", new DvText("1"), itemsOne));
		rows.add(new Cluster("at0006", new DvText("2"), itemsTwo));

		ItemTable itemTableOne = new ItemTable("at0010", new DvText("vision"),
				rows);
		ItemTable itemTableTwo = new ItemTable("at0011", new DvText("vision"),
				rows);

		assertFalse(itemTableOne.equals(itemTableTwo));
	}
    
    /* field */
    private List<Cluster> rows;
    private ItemTable itemTable;
    private Element element;
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