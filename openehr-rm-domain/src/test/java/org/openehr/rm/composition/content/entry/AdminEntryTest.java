/*
 * component:   "openEHR Reference Implementation"
 * description: "Class AdminEntryTest"
 * keywords:    "unit test"
 *
 * author:      "Yin Su Lim <y.lim@chime.ucl.ac.uk>"
 * support:     "CHIME, UCL"
 * copyright:   "Copyright (c) 2006 UCL, UK"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/BRANCHES/RM-1.0-update/libraries/src/test/org/openehr/rm/composition/content/entry/AdminEntryTest.java $"
 * revision:    "$LastChangedRevision: 50 $"
 * last_change: "$LastChangedDate: 2006-08-10 12:21:46 +0100 (Thu, 10 Aug 2006) $"
 */

package org.openehr.rm.composition.content.entry;

import java.util.*;

import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.composition.CompositionTestBase;
import org.openehr.rm.datastructure.itemstructure.ItemList;
import org.openehr.rm.datastructure.itemstructure.representation.Element;
import org.openehr.rm.datatypes.quantity.datetime.DvDate;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.support.identification.ArchetypeID;

public class AdminEntryTest extends CompositionTestBase {
    
    public AdminEntryTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
        Archetyped archetypeDetails = new Archetyped(
                new ArchetypeID("openehr-ehr_rm-adminEntry.date.v2"),
                "1.0.2");        
        List<Element> items = new ArrayList<Element>();
        items.add(new Element(("at0001"), "header", new DvText("date")));
        items.add(new Element(("at0002"), "value",	new DvDate("2008-05-17")));
        itemList = new ItemList("at0003", "item list", items);        
        adminEntry = new AdminEntry(null, "at0004", new DvText("admin entry"),
        		archetypeDetails, null, null, null, lang, encoding, 
        		subject(), provider(), null, null, itemList, ts);
    }

    protected void tearDown() throws Exception {
    	itemList = null;
    	adminEntry = null;
    }

    public void testItemAtPathWhole() throws Exception {
    	path = "/";
    	value = adminEntry.itemAtPath(path);
        assertEquals("unexpected result for path: " + path, adminEntry, value); 
    }
    
    public void testItemAtPathSubject() throws Exception {
    	path = "/subject";
    	value = adminEntry.itemAtPath(path);
        assertEquals("unexpected result for path: " + path, 
        		adminEntry.getSubject(), value); 
    }
    
    public void testItemAtPathProvider() throws Exception {
    	path = "/provider";
    	value = adminEntry.itemAtPath(path);
        assertEquals("unexpected result for path: " + path, 
        		adminEntry.getProvider(), value); 
    }
    
    public void testItemAtPathData() throws Exception {
    	path = "/data";
    	value = adminEntry.itemAtPath(path);
    	assertEquals("unexpected result for path: " + path,	itemList, value); 
    }
    
    public void testItemAtPathDataATCode() throws Exception {
    	path = "/data[at0003]";
    	value = adminEntry.itemAtPath(path);
        assertEquals("unexpected result for path: " + path,	itemList, value); 
    }
    
    public void testItemAtPathDataItemOne() throws Exception {
    	path = "/data/items[at0001]";
    	value = adminEntry.itemAtPath(path);
        assertEquals("unexpected result for path: " + path, 
        		itemList.getItems().get(0), value); 
    }
    
    public void testItemAtPathDataItemTwo() throws Exception {
    	path = "/data/items[at0002]";
    	value = adminEntry.itemAtPath(path);
        assertEquals("unexpected result for path: " + path, 
        		itemList.getItems().get(1), value); 
    }
    
    public void testItemAtPathDataItemOneValue() throws Exception {
    	path = "/data/items[at0001]/value";
    	value = adminEntry.itemAtPath(path);
        assertEquals("unexpected result for path: " + path, 
        		itemList.getItems().get(0).getValue(), value); 
    }
    
    public void testItemAtPathDataItemTwoValue() throws Exception {
    	path = "/data/items[at0002]/value";
    	value = adminEntry.itemAtPath(path);
        assertEquals("unexpected result for path: " + path, 
        		itemList.getItems().get(1).getValue(), value); 
    }
    
    public void testItemAtPathDataItemHeader() throws Exception {
    	path = "/data/items['header']";
    	value = adminEntry.itemAtPath(path);
        assertEquals("unexpected result for path: " + path, 
        		itemList.getItems().get(0), value); 
    }
    
    public void testItemAtPathDataItemValue() throws Exception {
    	path = "/data/items['value']";
    	value = adminEntry.itemAtPath(path);
        assertEquals("unexpected result for path: " + path, 
        		itemList.getItems().get(1), value); 
    }
    
    public void testItemAtPathDataItemHeaderValue() throws Exception {
    	path = "/data/items['header']/value";
    	value = adminEntry.itemAtPath(path);
        assertEquals("unexpected result for path: " + path, 
        		itemList.getItems().get(0).getValue(), value); 
    }
    
    public void testItemAtPathDataItemValueValue() throws Exception {
    	path = "/data/items['value']/value";
    	value = adminEntry.itemAtPath(path);
        assertEquals("unexpected result for path: " + path, 
        		itemList.getItems().get(1).getValue(), value); 
    }
    
    private ItemList itemList;
    private AdminEntry adminEntry;
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
 *  The Original Code is AdminEntryTest.java
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