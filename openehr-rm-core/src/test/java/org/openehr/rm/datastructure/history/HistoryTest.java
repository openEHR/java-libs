/*
 * component:   "openEHR Reference Implementation"
 * description: "Class HistoryTest"
 * keywords:    "unit test"
 *
 * author:      "Yin Su Lim <y.lim@chime.ucl.ac.uk>"
 * support:     "CHIME, UCL"
 * copyright:   "Copyright (c) 2006 UCL, UK"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/BRANCHES/RM-1.0-update/libraries/src/test/org/openehr/rm/datastructure/history/HistoryTest.java $"
 * revision:    "$LastChangedRevision: 50 $"
 * last_change: "$LastChangedDate: 2006-08-10 12:21:46 +0100 (Thu, 10 Aug 2006) $"
 */
/**
 * HistoryTest
 *
 * @author Yin Su Lim
 * @version 1.0 
 */

package org.openehr.rm.datastructure.history;

import java.util.ArrayList;
import java.util.List;

import org.openehr.rm.common.archetyped.Locatable;
import org.openehr.rm.datastructure.DataStructureTestBase;
import org.openehr.rm.datastructure.itemstructure.ItemList;
import org.openehr.rm.datastructure.itemstructure.ItemSingle;
import org.openehr.rm.datastructure.itemstructure.representation.Element;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datatypes.quantity.datetime.DvDuration;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.support.terminology.TestTerminologyService;

public class HistoryTest extends DataStructureTestBase {

	public HistoryTest(String testName) {
		super(testName);	
	}
	
	public void setUp() {
		history = initWithItemList();
	}

	private History<ItemSingle> initWithItemSingle() {
		element = element("element name", "value");

		List<Event<ItemSingle>> items = new ArrayList<Event<ItemSingle>>();
		for (int i = 0; i < ITEMS.length; i++) {
			Element element = element("element " + i, CODES[i]);
			ItemSingle item = new ItemSingle(null, "at0001", text(ITEMS[i]),
					null, null, null, null, element);
			items.add(new PointEvent<ItemSingle>(null, "at0003",
					text("point event"), null, null, null, null,
					new DvDateTime(TIME), item, null));
		}
		return new History<ItemSingle>(null, "at0002", text(NAME), null, null,
				null, null, new DvDateTime(TIME), items, DvDuration
						.getInstance("PT1h"), DvDuration.getInstance("PT3h"),
				null);

	}

	private History<ItemSingle> initWithSummary() {
		ItemSingle summary = new ItemSingle(null, "at0001", text(ELEMENT_NAME),
				null, null, null, null, element);
		return new History<ItemSingle>(null, "at0002", text(NAME), null, null,
				null, null, new DvDateTime(TIME), null, DvDuration
						.getInstance("PT1h"), DvDuration.getInstance("PT3h"),
				summary);
	}
	
	public void testGetParent() {
		History<ItemSingle> h1 = initWithItemSingle();
		for (Event<ItemSingle> event : h1.getEvents()) {
			assertEquals(h1, event.getParent());
		}
		History<ItemList> h2 = initWithItemList();
		for (Event<ItemList> event : h2.getEvents()) {
			assertEquals(h2, event.getParent());
		}
	}

	public void testHistoryContructorWithSharedParent() {
		element = element("element name", "value");
		History<ItemSingle> h1 = initWithSummary();
		List<Event<ItemSingle>> items = new ArrayList<Event<ItemSingle>>();
		for (int i = 0; i < ITEMS.length; i++) {
			Element element = element("element " + i, CODES[i]);
			ItemSingle item = new ItemSingle(null, "at0001", text(ITEMS[i]),
					null, null, null, null, element);
			items.add(new PointEvent<ItemSingle>(null, "at0003",
					text("point event"), null, null, null, h1, new DvDateTime(
							TIME), item, null));
		}
		try {
			new History<ItemSingle>(null, "at0002", text(NAME), null, null,
					null, null, new DvDateTime(TIME), items, DvDuration
							.getInstance("PT1h"), DvDuration
							.getInstance("PT3h"), null);
			fail("this construction of History should fail, events already "
					+ "have parent assigned");
		} catch (IllegalArgumentException e) {
		}
	}
	
	public void testHistoryContructorWithNullValues() {
		try {
			new History<ItemSingle>(null, "at0002", text(NAME), null, null,
					null, null, new DvDateTime(TIME), null, DvDuration
							.getInstance("PT1h"), DvDuration
							.getInstance("PT3h"), null);
			fail("this construction of History should fail, both events "
					+ "and summary are null");
		} catch (IllegalArgumentException iae) {
		}
	}

	/*
	 * <
	 *   archetype_node_id = <"at0005">
	 *   name = <
	 *       value = <"history">
	 *   >
	 *   origin = <2006-07-07T10:29:00>
	 *   events = <
	 *       [1] = <
	 *         archetype_node_id = <"at0004">
	 *         name = <
	 *           value = <"interval event">
	 *         >
	 *         time = <2006-07-07T10:59:00>
	 *         width = <PT30m>
	 *         mathFunction = <
	 *             value = <"mean">
	 *             defining_code = <
	 *                 terminology_id = 
	 *                     value = <"openehr">
	 *                 >
	 *                 code_string = <"123">
	 *             >
	 *         >
	 *         data = <
	 *             archetype_node_id = <"at0001">
	 *             name = <
	 *                 value = <"element name">
	 *             >
	 *             items = <
	 *             	  1] = <
                        name = <
                        	value = <"element 1">
                        >
                        archetype_node_id = <"at0014">
                        value = <
                            value = <"text 1">
                        >
                    >
                    [2] = <
                        name = <
                        	value = <"element 2">
                        >
                        archetype_node_id = <"at0015">
                        value = <
                            value = <"text 1">
                        >
                    >
                    [3] = <
                        name = <
                        	value = <"element 3">
                        >
                        archetype_node_id = <"at0016">
                        value = <
                            value = <"text 1">
                        >
                    >
	 *             
	 *         >
	 *       >
	 *     >
	 *   >
	 * >
	 */
	private History<ItemList> initWithItemList() {
		element = element("element name", "value");

		List<Element> items = new ArrayList<Element>();
		items.add(new Element("at0014", new DvText("element 1"), new DvText("text 1")));
		items.add(new Element("at0015", new DvText("element 2"), new DvText("text 2")));
		items.add(new Element("at0016", new DvText("element 3"), new DvText("text 3")));
		
		ItemList itemList = new ItemList(null, "at0001", text(ELEMENT_NAME),
				null, null, null, null, items);
		List<Event<ItemList>> intEvent = new ArrayList<Event<ItemList>>();
		intEvent.add(new IntervalEvent<ItemList>(null, "at0004",
				text("interval event"), null, null, null, null, new DvDateTime(
						"2006-07-07T10:59:00"), itemList, null, DvDuration
						.getInstance("PT30m"), codedText("mean", "meanCode"),
				0, TestTerminologyService.getInstance()));
		return new History<ItemList>(null, "at0005", text(NAME), null, null,
				null, null, new DvDateTime(TIME), intEvent, DvDuration
						.getInstance("PT1h"), DvDuration.getInstance("PT3h"),
				null);
	}
	
	public void testItemAtPathWithRoot() throws Exception {
		assertEquals("/ return wrong", history, history.itemAtPath("/"));
	}
	
	public void testItemAtPathWithArchetypePredicateWithMatch() throws Exception {
		expression = "/events[at0004]";
		ret = history.itemAtPath(expression);
		assertNotNull(expression + " should return event", ret);
		assertTrue(expression +  " should return event, but got: " + ret.getClass(),	
				ret instanceof IntervalEvent);
		Locatable locatable = (Locatable) ret;
		assertEquals(expression + " return wrong", "at0004",	locatable.getArchetypeNodeId());
	}
	
	public void testItemAtPathWithNamePredicateWithMatch() throws Exception {
		expression = "/events['interval event']";
		ret = history.itemAtPath(expression);
		assertNotNull(expression + " should return event", ret);
		assertTrue(expression +  " should return event, but got: " + ret.getClass(),	
				ret instanceof IntervalEvent);
		Locatable locatable = (Locatable) ret;
		assertEquals(expression + " return wrong", "at0004",	locatable.getArchetypeNodeId());
	}
	
	public void testItemAtPathWithArchetypeNamePredicateWithMatch() throws Exception {
		expression = "/events[at0004, 'interval event']";
		ret = history.itemAtPath(expression);
		assertNotNull(expression + " should return event", ret);
		assertTrue(expression +  " should return event, but got: " + ret.getClass(),	
				ret instanceof IntervalEvent);
		Locatable locatable = (Locatable) ret;
		assertEquals(expression + " return wrong", "at0004",	locatable.getArchetypeNodeId());
	}
	
	public void testItemAtPathWithPredicatesAndTailingPart() throws Exception {
		expression = "/events[at0004, 'interval event']/data/items[at0014, 'element 1']";
		ret = history.itemAtPath(expression);
		
		assertNotNull(expression + " expected Element, but got null", ret);
		assertTrue(expression +  " should return Element, but got: " + ret.getClass(),	
				ret instanceof Element);
		Element e = (Element) ret;
		assertEquals("element name wrong", "element 1", e.getName().getValue());
	}
	
	public void testItemAtPathWithPredicatesAndTailingPartValue() throws Exception {
		expression = "/events[at0004, 'interval event']/data/items[at0014, 'element 1']/value";
		ret = history.itemAtPath(expression);
		
		assertNotNull(expression + " should return a DvText", ret);
		assertTrue(expression +  " should return DvText, but got: " + ret.getClass(),	
				ret instanceof DvText);
		DvText dt = (DvText) ret;
		assertEquals("dvText value wrong", "text 1", dt.getValue());
	}
	
	public void testItemAtPathWithPredicatesAndTailingPartValueValue() throws Exception {
		expression = "/events[at0004, 'interval event']/data/items[at0014, 'element 1']/value/value";
		ret = history.itemAtPath(expression);
		
		assertNotNull(expression + " return a null", ret);
		assertTrue("expected a string, but got: " + ret.getClass(), 
				ret instanceof String);
		assertEquals("dvText value wrong", "text 1", ret);
	}

	/* test fixtures */
	private History history;
	private String expression;
	private Object ret;
	
	/* static fields */
	private static final String NAME = "history";

	private Element element;

	private static final String ELEMENT_NAME = "element name";

	private static final String[] ITEMS = { "event one", "event two",
			"event three" };

	private static final String[] CODES = { "code one", "code two",
			"code three" };

	private static final String TIME = "2006-07-07T10:29:00";

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
 * The Original Code is HistoryTest.java
 * 
 * The Initial Developer of the Original Code is Rong Chen. Portions created by
 * the Initial Developer are Copyright (C) 2003-2006 the Initial Developer. All
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