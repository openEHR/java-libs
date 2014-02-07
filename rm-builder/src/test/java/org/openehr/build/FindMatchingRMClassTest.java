/*
 * component:   "openEHR Java Reference Implementation"
 * description: "Class FindMatchingRMClassTest"
 * keywords:    "builder"
 *
 * author:      "Rong Chen <rong.acode@gmail.com>"
 * copyright:   "Copyright (c) 2008 Cambio Healthcare Systems, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL$"
 * revision:    "$LastChangedRevision$"
 * last_change: "$LastChangedDate$"
 */
package org.openehr.build;

import java.util.*;

import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.common.generic.PartySelf;
import org.openehr.rm.datastructure.history.Event;
import org.openehr.rm.datastructure.history.History;
import org.openehr.rm.datastructure.history.PointEvent;
import org.openehr.rm.datastructure.itemstructure.ItemList;
import org.openehr.rm.datastructure.itemstructure.representation.Element;
import org.openehr.rm.datatypes.quantity.DvQuantity;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.support.identification.ArchetypeID;
import org.openehr.rm.support.identification.TerminologyID;
import org.openehr.rm.support.measurement.MeasurementService;
import org.openehr.rm.support.measurement.SimpleMeasurementService;
import org.openehr.rm.support.terminology.TerminologyService;
import org.openehr.terminology.SimpleTerminologyService;

import junit.framework.TestCase;

public class FindMatchingRMClassTest extends TestCase {
	
	public FindMatchingRMClassTest() throws Exception {
		builder = new RMObjectBuilder();
		ms =  SimpleMeasurementService.getInstance();
		ts = SimpleTerminologyService.getInstance();
	}
	
	public void setUp() {
		valueMap = new HashMap<String, Object>();
	}
	
	public void testMatchDvQuantityValues() {
		valueMap.put("units", "mmHg");
		valueMap.put("magnitude", 120.0);
		assertMatchedRMClass("DvQuantity"); // This is the Java class, not the rm_type_name
	}
	
	public void testMatchCodePhrase() {
		valueMap.put("terminologyId", new TerminologyID("openehr"));
		valueMap.put("codeString", "234");
		assertMatchedRMClass("CodePhrase"); // This is the Java class, not the rm_type_name
	}
	
	public void testMatchElement() {
		DvText name = new DvText("name");
		DvQuantity value = new DvQuantity("mmHg", 120.0, ms);
		valueMap.put("archetypeNodeId", "at0001");
		valueMap.put("name", name);
		valueMap.put("value", value);
		assertMatchedRMClass("Element");
	}
	
	public void testWithUnderscoreSeparatedAttributeName() {
		DvText name = new DvText("name");
		DvQuantity value = new DvQuantity("mmHg", 120.0, ms);
		valueMap.put("archetype_node_id", "at0001");
		valueMap.put("name", name);
		valueMap.put("value", value);
		assertMatchedRMClass("Element");
	}
	
	public void testMatchItemList() {
		DvText name = new DvText("BP measurement");
		DvQuantity systolicValue = new DvQuantity("mmHg", 120.0, ms);
		DvQuantity diastolicValue = new DvQuantity("mmHg", 80.0, ms);
		Element systolicElement = new Element("at0001", 
				new DvText("systolic"), systolicValue);
		Element diastolicElement = new Element("at0002", 
				new DvText("diastolic"), diastolicValue);
		List<Element> items = new ArrayList<Element>();
		items.add(systolicElement);
		items.add(diastolicElement);
		valueMap.put("name", name);
		valueMap.put("archetypeNodeId", "at0003");
		valueMap.put("items", items);
		assertMatchedRMClass("ItemList");
	}
	
	public void testMatchPointEvent() {
		DvQuantity systolicValue = new DvQuantity("mmHg", 120.0, ms);
		Element systolicElement = new Element("at0001", 
				new DvText("systolic"), systolicValue);
		List<Element> items = new ArrayList<Element>();
		items.add(systolicElement);
		ItemList itemList = new ItemList("at0003", new DvText("list"), items);
		valueMap.put("name", new DvText("point event"));
		valueMap.put("archetypeNodeId", "at0004");
		valueMap.put("data", itemList);
		valueMap.put("time", new DvDateTime("2005-12-03T09:22:00"));
		assertMatchedRMClass("PointEvent");
	}
	
	public void testMatchingHistory() {
		DvQuantity systolicValue = new DvQuantity("mmHg", 120.0, ms);
		Element systolicElement = new Element("at0001", 
				new DvText("systolic"), systolicValue);
		List<Element> items = new ArrayList<Element>();
		items.add(systolicElement);
		ItemList itemList = new ItemList("at0003", new DvText("list"), items);
		PointEvent event = new PointEvent("at0004", new DvText("event"),
				new DvDateTime("2005-12-03T09:22:00"), itemList);
		List<Event> events = new ArrayList<Event>();
		events.add(event);
		
		valueMap.put("name", new DvText("history"));
		valueMap.put("archetypeNodeId", "at0005");
		valueMap.put("origin", new DvDateTime("2005-12-03T10:22:00"));
		valueMap.put("events", events);
		assertMatchedRMClass("History");
	}
	
	public void testMatchObservation() {
		DvQuantity systolicValue = new DvQuantity("mmHg", 120.0, ms);
		Element systolicElement = new Element("at0001", 
				new DvText("systolic"), systolicValue);
		List<Element> items = new ArrayList<Element>();
		items.add(systolicElement);
		ItemList itemList = new ItemList("at0003", new DvText("list"), items);
		PointEvent event = new PointEvent("at0004", new DvText("event"),
				new DvDateTime("2005-12-03T09:22:00"), itemList);
		List<Event> events = new ArrayList<Event>();
		events.add(event);
		History<ItemList> history = new History("at0005", new DvText("history"),
				new DvDateTime("2005-12-03T09:22:00"), events);
		CodePhrase language = new CodePhrase("ISO_639-1", "en");
	    CodePhrase encoding = new CodePhrase("IANA_character-sets", "UTF-8");
		valueMap.put("name", new DvText("observation"));
		valueMap.put("archetypeNodeId", "at0006");
		valueMap.put("data", history);
		valueMap.put("language", language);
		valueMap.put("encoding", encoding);
		valueMap.put("subject", new PartySelf());
		valueMap.put("archetypeDetails", new Archetyped(new ArchetypeID(
				"openEHR-EHR-OBSERVATION.laboratory.v1"), "1.0"));
		assertMatchedRMClass("Observation");
	}
	
	public void testMatchArchetyped() throws Exception {
		ArchetypeID archetypeId = new ArchetypeID(
						"openEHR-EHR-OBSERVATION.blood_pressure.v1");
		valueMap.put("archetypeId", archetypeId);
		valueMap.put("rmVersion", "1.0.1");
		assertMatchedRMClass("Archetyped");
	}
	
	private void assertMatchedRMClass(String expectedRMClass) {
		String actualRMClass = builder.findMatchingRMClass(valueMap);
		assertEquals("failed to match " + expectedRMClass, expectedRMClass,
				actualRMClass);
	}
	
	private Map<String, Object> valueMap;
	private RMObjectBuilder builder;
	private MeasurementService ms;
	private TerminologyService ts;
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
 *  The Original Code is FindMatchingRMClassTest.java
 *
 *  The Initial Developer of the Original Code is Rong Chen.
 *  Portions created by the Initial Developer are Copyright (C) 2006-2008
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