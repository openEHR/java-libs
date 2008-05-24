/*
 * component:   "openEHR Reference Implementation"
 * description: "Class RMObjectBuilderTestBase"
 * keywords:    "unit test"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2006 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL$"
 * revision:    "$LastChangedRevision$"
 * last_change: "$LastChangedDate$"
 */
package org.openehr.build;

import junit.framework.TestCase;
import org.openehr.rm.common.generic.PartyIdentified;
import org.openehr.rm.datastructure.itemstructure.ItemList;
import org.openehr.rm.support.identification.*;
import org.openehr.rm.support.measurement.*;
import org.openehr.rm.datastructure.itemstructure.ItemSingle;
import org.openehr.rm.datastructure.itemstructure.representation.Cluster;
import org.openehr.rm.datastructure.itemstructure.representation.Element;
import org.openehr.rm.datastructure.itemstructure.representation.Item;
import org.openehr.rm.datatypes.quantity.DvInterval;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.support.terminology.TerminologyService;
import org.openehr.rm.common.generic.PartySelf;
import org.openehr.terminology.SimpleTerminologyService;

import java.util.*;

/**
 * Base class for all RM object building test providing common test fixture
 * 
 * @author Rong Chen
 */
public class BuildTestBase extends TestCase {

	/**
	 * The fixture set up called before every test method.
	 */
	protected void setUp() throws Exception {
		Map<SystemValue, Object> values = new HashMap<SystemValue, Object>();
		values.put(SystemValue.LANGUAGE, lang);
		values.put(SystemValue.CHARSET, charset);
		values.put(SystemValue.ENCODING, charset);
		values.put(SystemValue.TERMINOLOGY_SERVICE, ts);
		values.put(SystemValue.MEASUREMENT_SERVICE, ms);
		builder = new RMObjectBuilder(values);
	}

	/**
	 * The fixture clean up called after every test method.
	 */
	protected void tearDown() throws Exception {
		builder = null;
	}

	// test cluster not exactly right for table or tree
	protected Cluster cluster() throws Exception {
		DvText name = new DvText("test element", lang, charset, ts);
		List<Item> items = new ArrayList<Item>();
		for (int i = 0; i < 6; i++) {
			DvText value = new DvText("test value" + i, lang, charset, ts);
			items.add(new Element("at0001", name, value));
		}
		name = new DvText("test cluster", lang, charset, ts);
		return new Cluster("at0002", name, items);
	}

	private List<Element> elements() {
		DvText name = new DvText("test element", lang, charset, ts);
		List<Element> items = new ArrayList<Element>();
		for (int i = 0; i < 6; i++) {
			DvText value = new DvText("test value" + i, lang, charset, ts);
			items.add(new Element("at0001", name, value));
		}
		return items;
	}

	List<Item> items() {
		DvText name = new DvText("test element", lang, charset, ts);
		List<Item> items = new ArrayList<Item>();
		for (int i = 0; i < 6; i++) {
			DvText value = new DvText("test value" + i, lang, charset, ts);
			items.add(new Element("at0001", name, value));
		}
		return items;
	}

	protected List<Cluster> tableRows() throws Exception {
		List<Cluster> rows = new ArrayList<Cluster>();
		for (int i = 0; i < 3; i++) {
			rows.add(cluster());
		}
		return rows;
	}

	// test element
	protected Element element() throws Exception {
		DvText name = new DvText("test element", lang, charset, ts);
		DvText value = new DvText("test value", lang, charset, ts);
		return new Element("at0001", name, value);
	}

	protected ItemSingle itemSingle() throws Exception {
		return itemSingle("test item single");
	}

	protected ItemSingle itemSingle(String value) throws Exception {
		DvText name = new DvText(value, lang, charset, ts);
		return new ItemSingle("at0001", name, element());
	}

	protected ItemList itemList() throws Exception {
		return new ItemList("at001", "test itemList", elements());
	}

	// test subject
	protected PartySelf subject() throws Exception {
		PartyRef party = new PartyRef(new HierObjectID("1.2.4.5.6.12.1"),
				"PARTY");
		return new PartySelf(party);
	}

	// test provider
	protected PartyIdentified provider() throws Exception {
		PartyRef performer = new PartyRef(new HierObjectID("1.3.3.1"),
				"ORGANISATION");
		return new PartyIdentified(performer, "provider's name", null);
	}

	protected HierObjectID hid(String value) throws Exception {
		return new HierObjectID(value);
	}

	// test territory
	public CodePhrase territory() {
		return new CodePhrase("test", "se");
	}

	protected DvInterval<DvDateTime> time() throws Exception {
		return new DvInterval<DvDateTime>(
				new DvDateTime("2004-10-29 22:37:00"), new DvDateTime(
						"2004-10-29 23:10:00"));
	}

	protected DvText text(String value) throws Exception {
		return new DvText(value, lang, charset, ts);
	}

	protected DvCodedText codedText(String value, String code) throws Exception {
		CodePhrase codePhrase = new CodePhrase(TerminologyService.OPENEHR, code);

		return new DvCodedText(value, lang, charset, codePhrase, ts);
	}

	/* field */
	protected RMObjectBuilder builder;
	protected static CodePhrase lang = new CodePhrase("ISO_639-1", "en");
	protected static CodePhrase charset = new CodePhrase("IANA_character-sets",
			"UTF-8");
	protected static TerminologyService ts;
	protected static MeasurementService ms;

	static {
		try {
			ts = SimpleTerminologyService.getInstance();
			ms = SimpleMeasurementService.getInstance();
		} catch (Exception e) {
			throw new RuntimeException(
					"failed to start terminology or measure service");
		}
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
 * The Original Code is BuildTestBase.java
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