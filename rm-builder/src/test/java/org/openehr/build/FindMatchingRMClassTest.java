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

import org.openehr.rm.datastructure.itemstructure.representation.Element;
import org.openehr.rm.datatypes.quantity.DvQuantity;
import org.openehr.rm.datatypes.text.DvText;
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
		assertMatch("DvQuantity");
	}
	
	public void testMatchDvText() {
		valueMap.put("value", "sitting");
		assertMatch("DvText");
	}
	
	public void testMatchCodePhrase() {
		valueMap.put("terminologyId", new TerminologyID("openehr"));
		valueMap.put("codeString", "234");
		assertMatch("CodePhrase");
	}
	
	public void testMatchElement() {
		DvText name = new DvText("name");
		DvQuantity value = new DvQuantity("mmHg", 120.0, ms);
		valueMap.put("archetypeNodeId", "at0001");
		valueMap.put("name", name);
		valueMap.put("value", value);
		assertMatch("Element");
	}
	
	public void testWithUnderscoreSeparatedAttributeName() {
		DvText name = new DvText("name");
		DvQuantity value = new DvQuantity("mmHg", 120.0, ms);
		valueMap.put("archetype_node_id", "at0001");
		valueMap.put("name", name);
		valueMap.put("value", value);
		assertMatch("Element");
	}
	
	public void testItemList() {
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
		assertMatch("ItemList");
	}
	
	private void assertMatch(String expectedRMClass) {
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