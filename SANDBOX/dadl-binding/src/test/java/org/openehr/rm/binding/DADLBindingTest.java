/*
 * component:   "openEHR Java Reference Implementation"
 * description: "Class DADLBindingTest"
 * keywords:    "binding"
 *
 * author:      "Rong Chen <rong.acode@gmail.com>"
 * copyright:   "Copyright (c) 2008 Cambio Healthcare Systems, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL$"
 * revision:    "$LastChangedRevision$"
 * last_change: "$LastChangedDate$"
 */
package org.openehr.rm.binding;

import java.io.InputStream;

import org.openehr.am.parser.*;
import org.openehr.rm.RMObject;
import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.common.generic.PartySelf;
import org.openehr.rm.composition.content.entry.Observation;
import org.openehr.rm.datastructure.history.Event;
import org.openehr.rm.datastructure.history.History;
import org.openehr.rm.datastructure.history.PointEvent;
import org.openehr.rm.datastructure.itemstructure.ItemList;
import org.openehr.rm.datastructure.itemstructure.representation.Element;
import org.openehr.rm.datatypes.quantity.DvQuantity;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.support.identification.*;

import junit.framework.TestCase;

public class DADLBindingTest extends TestCase {
	
	public void setUp() throws Exception {
		binding = new DADLBinding();
	}
	
	public void tearDown() throws Exception {
		rmObj = null;
	}
	
	public void testBindTypedDvQuantity() throws Exception {
		rmObj = bind("typed_dv_quantity.dadl");
		assertTrue("rmObject not DvQuantity", rmObj instanceof DvQuantity);
		
		DvQuantity dq = (DvQuantity) rmObj;
		assertEquals("DvQuantity magnitude wrong", 120.0, dq.getMagnitude(), 0);
		assertEquals("DvQuantity units wrong", "mmHg", dq.getUnits());
	}
	
	public void testBindUntypedDvQuantity() throws Exception {
		rmObj = bind("dv_quantity.dadl");
		assertTrue("rmObject not DvQuantity", rmObj instanceof DvQuantity);
		
		DvQuantity dq = (DvQuantity) rmObj;
		assertEquals("DvQuantity magnitude wrong", 120.0, dq.getMagnitude(), 0);
		assertEquals("DvQuantity units wrong", "mmHg", dq.getUnits());
	}

	public void testBindTypedDvText() throws Exception {
		rmObj = bind("typed_dv_text.dadl");
		assertTrue("rmObject not DvText", rmObj instanceof DvText);
		
		DvText dt = (DvText) rmObj;
		assertEquals("DvText value wrong", "sitting", dt.getValue());		
	}
	
	public void testBindUntypedElement() throws Exception {
		rmObj = bind("element.dadl");
		
		assertTrue("rmObject not Element", rmObj instanceof Element);		
		Element element = (Element) rmObj;
		assertEquals("element name wrong", "systolic", 
				element.getName().getValue());
		assertEquals("element archetypeNodeId wrong", "at0004", 
				element.getArchetypeNodeId());
		assertTrue("dataValue not DvQuantity", 
				element.getValue() instanceof DvQuantity);
		DvQuantity dq = (DvQuantity) element.getValue();
		assertEquals("dvQuantity magnitude wrong", 120.0, dq.getMagnitude(), 0);
		assertEquals("dvQuantity units wrong", "mmHg", dq.getUnits());
	}
	
	public void testBindUntypedItemList() throws Exception {
		rmObj = bind("item_list.dadl");
		
		assertNotNull("rmObject null", rmObj);
		assertTrue("rmObject not ItemList: " 
				+ rmObj.getClass().getSimpleName(), rmObj instanceof ItemList);
		
		ItemList itemList = (ItemList) rmObj;
		assertEquals("ItemList name wrong", "item list", 
				itemList.getName().getValue());
		assertEquals("ItemList.items size wrong", 2, itemList.getItems().size());
		assertEquals("itemList.items[0].value.magnitude wrong", 120.0,
				((DvQuantity) itemList.ithItem(0).getValue()).getMagnitude(), 0);
		assertEquals("itemList.items[1].value.magnitude wrong", 80.0,
				((DvQuantity) itemList.ithItem(1).getValue()).getMagnitude(), 0);
	}
	
	public void testBindEmptyItemList() throws Exception {
		rmObj = bind("empty_item_list.dadl");
		
		assertNotNull("rmObject null", rmObj);
		assertTrue("rmObject not ItemList: " 
				+ rmObj.getClass().getSimpleName(), rmObj instanceof ItemList);
		
		ItemList itemList = (ItemList) rmObj;
		assertEquals("ItemList name wrong", "item list", 
				itemList.getName().getValue());
		assertEquals("ItemList.items size wrong", 0, itemList.getItems().size());
	}
	
	public void testBindPointEvent() throws Exception {
		rmObj = bind("point_event.dadl");
		
		assertNotNull("rmObject null", rmObj);
		assertTrue("rmObject not PointEvent: " + rmObj.getClass().getName(), 
				rmObj instanceof PointEvent);
		
		PointEvent event = (PointEvent) rmObj;
		assertEquals("pointEvent.time wrong", 
				new DvDateTime("2005-12-03T09:22:00"), event.getTime());
		assertEquals("pointEvent.name.value wrong", "sitting",
				event.getName().getValue());
	}
	
	public void testBindHistory() throws Exception {
		rmObj = bind("history.dadl");
		
		assertNotNull("rmObject null", rmObj);
		assertTrue("rmObject not History: "	+ rmObj.getClass().getSimpleName(), 
				rmObj instanceof History);
		
		History history = (History) rmObj;
		assertEquals("history.name.value wrong", "history", 
				history.getName().getValue());
		assertEquals("history.origin wrong", 
				new DvDateTime("2007-10-30T09:22:00"), history.getOrigin());
		assertEquals("history.event.size wrong", 1, history.getEvents().size());
		assertEquals("history.events[0].time wrong", 
				new DvDateTime("2005-12-03T09:22:00"),
				((Event) history.getEvents().get(0)).getTime());
	}
	
	public void testBindTypedArchetypID() throws Exception {
		rmObj = bind("typed_archetype_id.dadl");
		assertNotNull("rmObject null", rmObj);
		assertTrue("rmObject not ArchetypeID: "	+ rmObj.getClass().getName(), 
				rmObj instanceof ArchetypeID);
		ArchetypeID archetypeId = (ArchetypeID) rmObj;
		assertEquals("archetypeId.value wrong", 
				"openEHR-EHR-OBSERVATION.blood_pressure.v1", 
				archetypeId.getValue());
	}
	
	public void testBindTypedTerminologyID() throws Exception {
		rmObj = bind("typed_terminology_id.dadl");
		assertNotNull("rmObject null", rmObj);
		assertTrue("rmObject not ArchetypeID: "	+ rmObj.getClass().getName(), 
				rmObj instanceof TerminologyID);
		TerminologyID id = (TerminologyID) rmObj;
		assertEquals("archetypeId.value wrong", "openehr", id.getValue());
	}
	
	public void testBindArchetyped() throws Exception {
		rmObj = bind("archetyped.dadl");
		assertNotNull("rmObject null", rmObj);
		assertTrue("rmObject not Archetyped: "	+ rmObj.getClass().getName(), 
				rmObj instanceof Archetyped);
		Archetyped archetyped = (Archetyped) rmObj;
		assertEquals("archtyped.archetypeId wrong", 
				"openEHR-EHR-OBSERVATION.blood_pressure.v1", 
				archetyped.getArchetypeId().getValue());
		assertEquals("archtyped.rmVersion wrong", "1.0.1", 
				archetyped.getRmVersion());
	}
	
	public void testBindCodePhrase() throws Exception {
		rmObj = bind("code_phrase.dadl");
		assertNotNull("rmObject null", rmObj);
		assertTrue("rmObject not CodePhrase: "	+ rmObj.getClass().getName(), 
				rmObj instanceof CodePhrase);
		CodePhrase cp = (CodePhrase) rmObj;
		assertEquals("codePhrase.terminologyId wrong", "ISO_639-1", 
				cp.getTerminologyId().getValue());
		assertEquals("codePhrase.codeString", "en", cp.getCodeString());		
	}
	
	public void testBindTypedPartySelf() throws Exception {
		rmObj = bind("typed_party_self.dadl");
		assertNotNull("rmObject null", rmObj);
		assertTrue("rmObject not PartySelf: "	+ rmObj.getClass().getName(), 
				rmObj instanceof PartySelf);
	}
	
	public void testBindSimpleObservation() throws Exception {
		rmObj = bind("observation.dadl");
		assertNotNull("rmObject null", rmObj);
		assertTrue("rmObject not Observation: "	+ rmObj.getClass().getName(), 
				rmObj instanceof Observation);
		
		Observation observation = (Observation) rmObj;
		Event event = observation.getData().getEvents().get(0);
		ItemList list = (ItemList) event.getData();
		assertEquals("observatoin.events[0].data.items.size wrong", 2,
				list.getItems().size());
		DvQuantity dq = (DvQuantity) list.getItems().get(0).getValue();
		assertEquals("items[0].value.magnitude wrong", 120.0, 
				dq.getMagnitude(), 0);
		dq = (DvQuantity) list.getItems().get(1).getValue();
		assertEquals("items[0].value.magnitude wrong", 80.0, 
				dq.getMagnitude(), 0);
		
		assertNull("data.events[0].state not null", event.getState());
	}
	
	public void testBindObservationWithEventState() throws Exception {
		rmObj = bind("observation2.dadl");
		assertNotNull("rmObject null", rmObj);
		assertTrue("rmObject not Observation: "	+ rmObj.getClass().getName(), 
				rmObj instanceof Observation);
		
		Observation observation = (Observation) rmObj;
		Event event = observation.getData().getEvents().get(0);
		ItemList list = (ItemList) event.getData();
		assertEquals("observatoin.data.events[0].data.items.size wrong", 2,
				list.getItems().size());
		DvQuantity dq = (DvQuantity) list.getItems().get(0).getValue();
		assertEquals("items[0].value.magnitude wrong", 120.0, 
				dq.getMagnitude(), 0);
		dq = (DvQuantity) list.getItems().get(1).getValue();
		assertEquals("items[0].value.magnitude wrong", 80.0, 
				dq.getMagnitude(), 0);
		
		assertNotNull("data.events[0].state null", event.getState());
		list = (ItemList) event.getState();
		assertEquals("event.state.size wrong", 1, list.getItems().size());
		DvCodedText codedText = (DvCodedText) list.getItems().get(0).getValue();
		assertEquals("event.state.items[0].value.value wrong", "Sitting", 
				codedText.getValue());
		assertEquals("event.state.items[0].value.definingCode.terminologyId wrong", 
				"local", codedText.getDefiningCode().getTerminologyId().getValue());
		assertEquals("event.state.items[0].value.definingCode.codeString wrong", 
				"at1001", codedText.getDefiningCode().getCodeString());
		
		// test with paths
		String path = "/data/events[0]/data/items[0]/value/magnitude";		
	}
	
	/*
	 * Loads dadl from file and binds data to RM object
	 */
	Object bind(String filename) throws Exception {
		DADLParser parser = new DADLParser(fromClasspath(filename));
		ContentObject contentObj = parser.parse();
		return binding.bind(contentObj);
	}
	
	/* 
	 * Loads given resource from classpath
	 * 
	 * @param adl
	 * @return
	 * @throws Exception
	 */
	InputStream fromClasspath(String res) throws Exception {
    	return this.getClass().getClassLoader().getResourceAsStream(res);
    }
	
	private Object rmObj;
	private DADLBinding binding;
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
 * The Original Code is DADLBindingTest.java
 * 
 * The Initial Developer of the Original Code is Rong Chen. Portions created by
 * the Initial Developer are Copyright (C) 2008 the Initial Developer. All
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