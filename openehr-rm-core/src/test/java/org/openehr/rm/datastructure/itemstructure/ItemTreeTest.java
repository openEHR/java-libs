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
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/BRANCHES/RM-1.0-update/libraries/src/test/org/openehr/rm/datastructure/itemstructure/ItemTreeTest.java $"
 * revision:    "$LastChangedRevision: 50 $"
 * last_change: "$LastChangedDate: 2006-08-10 13:21:46 +0200 (Thu, 10 Aug 2006) $"
 */
/**
 * ItemTreeTest
 *
 * @author Rong Chen
 * @version 1.0 
 */
package org.openehr.rm.datastructure.itemstructure;

import org.openehr.rm.datastructure.DataStructureTestBase;
import org.openehr.rm.datastructure.itemstructure.representation.Cluster;
import org.openehr.rm.datastructure.itemstructure.representation.Element;
import org.openehr.rm.datastructure.itemstructure.representation.Item;
import org.openehr.rm.datatypes.quantity.DvQuantity;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.support.measurement.MeasurementService;
import org.openehr.rm.support.measurement.TestMeasurementService;

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
        init();
    }

    /**
     * The fixture clean up called after every test method.
     */
    protected void tearDown() throws Exception {
        itemTree = null;
    }

    private void init() {
    	
        // sample
    	sample = new Element("at0001", new DvText("sample"), 
    			new DvCodedText("serum", new CodePhrase("terminology", "111")));
    	
    	// lipid studies
    	totalCholesterol = new Element("at0002", new DvText("total cholesterol"),
    			new DvQuantity("mmol/L", 6.1, measureServ));
    	
    	ldlCholesterol = new Element("at0003", new DvText("LDL cholesterol"),
    			new DvQuantity("mmol/L", 0.9, measureServ));
    	
    	hdlCholesterol = new Element("at0004", new DvText("HDL cholesterol"),
    			new DvQuantity("mmol/L", 5.2, measureServ));
    	
    	List<Item> items = new ArrayList<Item>();
    	items.add(totalCholesterol);
    	items.add(ldlCholesterol);
    	items.add(hdlCholesterol);
    	lipidStudies = new Cluster("at0005", new DvText("lipid studies"), items);
    	
    	// comment
    	comment = new Element("at0006", new DvText("comment"),
    			new DvText("high cardiac risk"));
    	
    	items = new ArrayList<Item>();
    	items.add(sample);
    	items.add(lipidStudies);
    	items.add(comment);
    	itemTree = new ItemTree("at0007", new DvText("biochemstry result"), items);
    }
    
    public void testItemAtPathWhole() {
    	assertEquals(itemTree, itemTree.itemAtPath("/"));
    }
    
    public void testItemAtPathSample() {
    	assertEquals(sample, itemTree.itemAtPath("/items[at0001]"));
    }    
    
    public void testItemAtPathComment() {
    	assertEquals(comment, itemTree.itemAtPath("/items[at0006]"));
    }
    
    public void testItemAtPathSampleName() {
    	assertEquals(sample, itemTree.itemAtPath("/items['sample']"));
    }    
    
    public void testItemAtPathCommentName() {
    	assertEquals(comment, itemTree.itemAtPath("/items['comment']"));
    }
    
    public void testItemAtPathSampleBoth() {
    	assertEquals(sample, itemTree.itemAtPath("/items[at0001, 'sample']"));
    }    
    
    public void testItemAtPathCommentBoth() {
    	assertEquals(comment, itemTree.itemAtPath("/items[at0006, 'comment']"));
    }
    
    public void testItemAtPathTotalCholesterol() {
    	assertEquals(totalCholesterol, 
    			itemTree.itemAtPath("/items[at0005]/items[at0002]"));
    }
    
    public void testItemAtPathLDLCholesterol() {
    	assertEquals(ldlCholesterol, 
    			itemTree.itemAtPath("/items[at0005]/items[at0003]"));
    }
    
    public void testItemAtPathHDLCholesterol() {
    	assertEquals(hdlCholesterol, 
    			itemTree.itemAtPath("/items[at0005]/items[at0004]"));
    }
    
    public void testItemAtPathTotalCholesterolName() {
    	assertEquals(totalCholesterol, 
    			itemTree.itemAtPath(
    					"/items['lipid studies']/items['total cholesterol']"));
    }
    
    public void testItemAtPathLDLCholesterolName() {
    	assertEquals(ldlCholesterol, 
    			itemTree.itemAtPath(
    					"/items['lipid studies']/items['LDL cholesterol']"));
    }
    
    public void testItemAtPathHDLCholesterolName() {
    	assertEquals(hdlCholesterol, 
    			itemTree.itemAtPath(
    					"/items['lipid studies']/items['HDL cholesterol']"));
    }
    
    public void testItemAtPathTotalCholesterolBoth() {
    	assertEquals(totalCholesterol, 
    			itemTree.itemAtPath(
    					"/items[at0005, 'lipid studies']/items[at0002]"));
    }
    
    public void testItemAtPathLDLCholesterolBoth() {
    	assertEquals(ldlCholesterol, 
    			itemTree.itemAtPath(
    					"/items[at0005, 'lipid studies']/items[at0003]"));
    }
    
    public void testItemAtPathHDLCholesterolBoth() {
    	assertEquals(hdlCholesterol, 
    			itemTree.itemAtPath(
    					"/items[at0005, 'lipid studies']/items[at0004]"));
    }
    
    public void testItemAtPathTotalCholesterolValue() {
    	assertEquals(totalCholesterol.getValue(), 
    			itemTree.itemAtPath("/items[at0005]/items[at0002]/value"));
    }
    
    public void testItemAtPathLDLCholesterolValue() {
    	assertEquals(ldlCholesterol.getValue(), 
    			itemTree.itemAtPath("/items[at0005]/items[at0003]/value"));
    }
    
    public void testItemAtPathHDLCholesterolValue() {
    	assertEquals(hdlCholesterol.getValue(), 
    			itemTree.itemAtPath("/items[at0005]/items[at0004]/value"));
    }
    
    public void testCreateEmptyTree() {
    	ItemTree empty = new ItemTree("at0001", new DvText("tree"), null);
    	assertNotNull(empty);
    }
    
    /* fields */
    private ItemTree itemTree;
    private Element sample;
    private Element totalCholesterol;
    private Element ldlCholesterol;
    private Element hdlCholesterol;
    private Element comment;
    private Cluster lipidStudies;
    private MeasurementService measureServ = TestMeasurementService.getInstance();
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