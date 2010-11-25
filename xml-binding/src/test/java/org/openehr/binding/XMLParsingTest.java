/*
 * component:   "openEHR Java Reference Implementation"
 * description: "Class XMLParsingTest"
 * keywords:    "XML binding testing"
 *
 * author:      "Rong Chen <rong.acode@gmail.com>"
 * copyright:   "Copyright (c) 2008 Cambio Healthcare Systems, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL$"
 * revision:    "$LastChangedRevision$"
 * last_change: "$LastChangedDate$"
 */
package org.openehr.binding;

import java.io.*;

import org.openehr.rm.datastructure.itemstructure.ItemTree;
import org.openehr.schemas.v1.*;
import junit.framework.TestCase;

/**
 * Verify XML parsing is OK
 * 
 * @author Rong.Chen
 */
public class XMLParsingTest extends TestCase {
	
	public void testParseItemTree() throws Exception {
		ItemsDocument xobj = ItemsDocument.Factory.parse(fromClasspath("item_tree.xml"));
		Object obj = xobj.getItems();
		assertTrue("expected ITEM_TREE, but got: " + obj.getClass(), 
				obj instanceof ITEMTREE);
		ITEMTREE tree = (ITEMTREE) obj;
		assertEquals("tree.name wrong", "Tree", tree.getName().getValue());
		
		ELEMENT element = (ELEMENT) tree.getItemsArray(0);
		assertEquals("element.name wrong", "Blood glucose", 
				element.getName().getValue());
		
		DVQUANTITY quantity = (DVQUANTITY) element.getValue();
		assertEquals("quantity.magnitude wrong", 100.0, quantity.getMagnitude());
	}
	
	public void testParseItemTree2() throws Exception {
		ItemsDocument xobj = ItemsDocument.Factory.parse(fromClasspath("item_tree_002.xml"));
		Object obj = xobj.getItems();
		assertTrue("expected ITEM_TREE, but got: " + obj.getClass(), 
				obj instanceof ITEMTREE);
		ITEMTREE tree = (ITEMTREE) obj;
		assertEquals("tree.name wrong", "data", tree.getName().getValue());
		
	}
	
	public void testParseCompositionAndVerifyTheContent() throws Exception {
		CompositionDocument compDoc = CompositionDocument.Factory.parse(
				fromClasspath("composition.xml"));	

		assertNotNull("compositionDocument null", compDoc);
		
		COMPOSITION comp = compDoc.getComposition();		
		
		// set of queries to make sure we get the data right		
		assertEquals("name wrong", "Lipids", comp.getName().getValue());
		assertEquals("territory wrong", "AU", comp.getTerritory().getCodeString());
		
		assertEquals("context startTime wrong", "20080522T200427,833+0930",
				comp.getContext().getStartTime().getValue());
		
		OBSERVATION obs = (OBSERVATION) ((SECTION)comp.getContentArray(0)).getItemsArray()[0];
		
		//assertTrue(((SECTION) comp.getContentArray(0)).getItemsArray()).getData()
		//		.getEventsArray(0)).getData() instanceof OBSERVATION);
		
		//assertTrue("observation.data.events[0].items[0]",
		//		(((OBSERVATION)((SECTION) comp.getContentArray(0))).getItemsArray()).getData()
		//		.getEventsArray(0).getData() instanceof ITEMTREE);
		
		ITEMTREE itemTree = (ITEMTREE) obs.getData().getEventsArray(0).getData();
		ELEMENT element = (ELEMENT) itemTree.getItemsArray(0);
		
		assertEquals("observation.data.events[0].items[0].data.value.magnitude",
				6.1, ((DVQUANTITY) element.getValue()).getMagnitude());		
	}

	public void testParseOriginalVersion() throws Exception {
		VERSION xobj = VersionDocument.Factory.parse(fromClasspath(
				"original_version_001.xml")).getVersion();
		
		assertTrue("expected originial_version, but got: " + xobj.getClass(),
				xobj instanceof ORIGINALVERSION);
		
		
		ORIGINALVERSION orgVer = (ORIGINALVERSION) xobj;
		
		assertEquals("originalVersion.uid wrong", 
				"c7ff23f4-6ad2-4ff9-bedf-fb60be37666e::10aec661-5458-4ff6-8e63-c2265537196d::1",
				orgVer.getUid().getValue());		
		
		assertTrue(orgVer.getData() instanceof COMPOSITION);
		
		COMPOSITION comp = (COMPOSITION) orgVer.getData();
		assertEquals(1, comp.getContentArray().length);
		
		Object item = comp.getContentArray()[0];
		assertTrue("unexpected class: " + item.getClass(), 
				item instanceof SECTION);
		
		SECTION section = (SECTION) item;
		OBSERVATION obser = (OBSERVATION) section.getItemsArray()[0];
		
		assertEquals("openEHR-EHR-OBSERVATION.laboratory-lipids.v1",
				obser.getArchetypeNodeId());
	}
	
	private InputStream fromClasspath(String filename) throws Exception {
    	return this.getClass().getClassLoader().getResourceAsStream(
    			filename);
    }	
}
/*
 * ***** BEGIN LICENSE BLOCK ***** Version: MPL 1.1/GPL 2.0/LGPL 2.1
 * 
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the 'License'); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at http://www.mozilla.org/MPL/
 * 
 * Software distributed under the License is distributed on an 'AS IS' basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 * 
 * The Original Code is XMLParsingTest.java
 * 
 * The Initial Developer of the Original Code is Rong Chen. Portions created by
 * the Initial Developer are Copyright (C) 2003-2008 the Initial Developer. All
 * Rights Reserved.
 * 
 * Contributor(s):
 * 
 * Software distributed under the License is distributed on an 'AS IS' basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 * 
 * ***** END LICENSE BLOCK *****
 */