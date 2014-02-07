/*
 * Copyright (C) 2004 Rong Chen, Acode HB, Sweden
 * All rights reserved.
 *
 * The contents of this software are subject to the FSF GNU Public License 2.0;
 * you may not use this software except in compliance with the License. You may
 * obtain a copy of the License at http://www.fsf.org/licenses/gpl.html
 *
 * Software distributed under the License is distributed on an 'AS IS' basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 */
package org.openehr.rm.composition;

import java.util.ArrayList;
import java.util.List;

import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.common.archetyped.Locatable;
import org.openehr.rm.common.generic.PartyIdentified;
import org.openehr.rm.common.generic.PartySelf;
import org.openehr.rm.composition.content.ContentItem;
import org.openehr.rm.composition.content.entry.Observation;
import org.openehr.rm.composition.content.navigation.Section;
import org.openehr.rm.datastructure.DataStructureTestBase2;
import org.openehr.rm.datastructure.history.Event;
import org.openehr.rm.datastructure.history.History;
import org.openehr.rm.datastructure.history.PointEvent;
import org.openehr.rm.datastructure.itemstructure.ItemList;
import org.openehr.rm.datastructure.itemstructure.ItemSingle;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;
import org.openehr.rm.datastructure.itemstructure.representation.Element;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datatypes.quantity.datetime.DvDuration;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.support.identification.ArchetypeID;
import org.openehr.rm.support.identification.HierObjectID;
import org.openehr.rm.support.identification.PartyRef;
import org.openehr.rm.support.terminology.TerminologyService;
import org.openehr.rm.support.terminology.TestCodeSetAccess;
import org.openehr.rm.support.terminology.TestTerminologyService;

/**
 * EntryTestBase
 * 
 * @author Rong Chen
 * @version 1.0
 */
public class CompositionTestBase extends DataStructureTestBase2 {

	public CompositionTestBase(String test) {
		super(test);
	}

	// test context
	protected EventContext context() throws Exception {
		DvCodedText setting = new DvCodedText("setting", lang, encoding,
				new CodePhrase("test", "setting_code"), ts);
		return new EventContext(null, time("2006-02-01T12:00:09"), null, null,
				null, setting, null, ts);
	}

	protected Section section(String name) throws Exception {
		List<ContentItem> items = new ArrayList<ContentItem>();
		items.add(observation());
		return new Section("at0000", new DvText(name), items);
	}

	protected Section section(String name, String observation) throws Exception {
		List<ContentItem> items = new ArrayList<ContentItem>();
		items.add(observation(observation));
		return new Section("at0000", new DvText(name), items);
	}

	protected Observation observation() throws Exception {
		return observation("test observation");
	}

	protected Observation observation(String name) throws Exception {
		DvText meaning = new DvText(name);
		Archetyped arch = new Archetyped(new ArchetypeID(
				"openehr-ehr_rm-observation.physical_examination.v3"), "1.1");
		return new Observation("at0001", meaning, arch, language("en"),
				language("en"), subject(), provider(), event("history"), ts);
	}

	protected ItemList list(String name) {
		String[] names = { "field 1", "field 2", "field 3" };
		String[] values = { "value 1", "value 2", "value 3" };
		List<Element> items = new ArrayList<Element>();
		for (int i = 0; i < names.length; i++) {
			items.add(element(names[i], values[i]));
		}
		return new ItemList("at0100", text(name), items);
	}

	public void assertItemAtPath(String path, Locatable target,
			Locatable expected) throws Exception {
		Locatable actual = (Locatable) target.itemAtPath(path);
		assertEquals("unexpected item: " + actual.getName().getValue()
				+ " for path: " + path, expected, actual);
	}

	protected History<ItemStructure> event(String name) {
		// element = element("element name", "value");
		String[] ITEMS = { "event one", "event two", "event three" };
		String[] CODES = { "code one", "code two", "code three" };
		List<Event<ItemStructure>> items = new ArrayList<Event<ItemStructure>>();
		for (int i = 0; i < ITEMS.length; i++) {
			Element element = element("element " + i, CODES[i]);
			ItemSingle item = new ItemSingle(null, "at0001", text(ITEMS[i]),
					null, null, null, null, element);
			items.add(new PointEvent<ItemStructure>(null, "at0003",
					text("point event"), null, null, null, null,
					new DvDateTime("2006-06-25T23:11:11"), item, null));
			// uid, archetypeNodeId, name, archetypeDetails, feederAudit, links,
			// parent, time, data, state
		}
		return new History<ItemStructure>(null, "at0002", text("history"),
				null, null, null, null, new DvDateTime("2006-06-25T23:11:11"),
				items, DvDuration.getInstance("PT1h"), DvDuration
						.getInstance("PT3h"), null);
	}

	// test territory
	protected CodePhrase territory() {
		return new CodePhrase("test", "se");
	}

	// test subject
	protected PartySelf subject() throws Exception {
		PartyRef party = new PartyRef(new HierObjectID("1.2.4.5.6.12.1"),
				"PARTY");
		return new PartySelf(party);
	}

	// test provider
	protected PartyIdentified provider() throws Exception {
		PartyRef performer = new PartyRef(new HierObjectID("1.3.3.1.2.42.1"),
				"ORGANISATION");
		return new PartyIdentified(performer, "provider's name", null);
	}

	protected DvDateTime time(String time) throws Exception {
		return new DvDateTime(time);
	}

	protected CodePhrase language(String language) throws Exception {
		return new CodePhrase("test", language);
	}

	/* field */
	protected static CodePhrase lang = TestCodeSetAccess.ENGLISH;
	protected static CodePhrase encoding = TestCodeSetAccess.LATIN_1;
	protected static TerminologyService ts = TestTerminologyService
			.getInstance();
	protected String path;
	protected Object value;
}
