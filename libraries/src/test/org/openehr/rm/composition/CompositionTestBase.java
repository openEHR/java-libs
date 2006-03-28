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

import org.openehr.rm.datastructure.DataStructureTestBase;
import org.openehr.rm.datastructure.history.SingleEvent;
import org.openehr.rm.datastructure.itemstructure.ItemList;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;
import org.openehr.rm.datastructure.itemstructure.representation.Item;
import org.openehr.rm.datastructure.itemstructure.representation.Cluster;
import org.openehr.rm.common.generic.RelatedParty;
import org.openehr.rm.common.generic.Participation;
import org.openehr.rm.common.archetyped.Locatable;
import org.openehr.rm.support.identification.PartyReference;
import org.openehr.rm.support.identification.HierarchicalObjectID;
import org.openehr.rm.support.terminology.TestCodeSet;
import org.openehr.rm.support.terminology.TerminologyService;
import org.openehr.rm.support.terminology.TestTerminologyService;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datatypes.quantity.DvInterval;
import org.openehr.rm.util.RMObjectBuilder;
import org.openehr.rm.composition.content.navigation.Section;
import org.openehr.rm.composition.content.ContentItem;
import org.openehr.rm.composition.content.entry.Observation;

import java.util.List;
import java.util.ArrayList;

/**
 * EntryTestBase
 *
 * @author Rong Chen
 * @version 1.0
 */
public class CompositionTestBase extends DataStructureTestBase {

    public CompositionTestBase(String test) {
        super(test);
    }

    // test context
    protected EventContext context() throws Exception {
        DvCodedText setting = new DvCodedText("setting", lang, charset,
                new CodePhrase("test", "setting_code"), ts);
        PartyReference composer = new PartyReference(
                new HierarchicalObjectID("333"));
        return new EventContext(null, time(), composer, null, null,
                setting, null, ts);
    }

    protected Section section(String name) throws Exception {
        List<ContentItem> items = new ArrayList<ContentItem>();
        items.add(observation());
        return new Section("at0000", new DvText(name), items);
    }

    protected Section section(String name, String observation)
            throws Exception {
        List<ContentItem> items = new ArrayList<ContentItem>();
        items.add(observation(observation));
        return new Section("at0000", new DvText(name), items);
    }

    protected Observation observation() throws Exception {
        return observation("test observation");
    }

    protected Observation observation(String name) throws Exception {
        DvText meaning = new DvText(name);
        return new Observation("at0000", meaning, subject(), provider(),
                event("single", list()));
    }

    protected ItemList list() {
        return list("list");
    }

    protected ItemList list(String name) {
        String[] names = {
            "field 1", "field 2", "field 3"
        };
        String[] values = {
            "value 1", "value 2", "value 3"
        };
        List<Item> items = new ArrayList<Item>();
        for (int i = 0; i < names.length; i++) {
            items.add(element(names[ i ], values[ i ]));
        }
        Cluster itemsCluster = cluster("items", "items", items);
        return new ItemList(null, "at0100", text(name), null, null, null,
                itemsCluster);
    }

    public void assertItemAtPath(String path, Locatable target,
                                 Locatable expected)
            throws Exception {
        Locatable actual = target.itemAtPath(path);
        assertEquals("unexpected item: " + actual.getName().getValue()
                + " for path: " + path, expected, actual);
    }

   protected SingleEvent<ItemStructure> event(String name, ItemStructure item) {
        return new SingleEvent<ItemStructure>("at1002", text(name),
                new DvDateTime("2000-10-15 10:00:00"), item);
    }

    // test  territory
    protected CodePhrase territory() {
        return new CodePhrase("test", "se");
    }

    // test subject
    protected RelatedParty subject() throws Exception {
        PartyReference party = new PartyReference(
                new HierarchicalObjectID("123"));
        DvCodedText relationship = new DvCodedText("family relationship", lang,
                charset, new CodePhrase("test", "family_code"), ts);
        return new RelatedParty(party, relationship, ts);
    }

    // test provider
    protected Participation provider() throws Exception {
        PartyReference performer = new PartyReference(
                new HierarchicalObjectID("333"));
        DvCodedText function = new DvCodedText("doctor", lang, charset,
                new CodePhrase("test", "doctor_code"), ts);
        DvCodedText mode = new DvCodedText("present", lang, charset,
                new CodePhrase("test", "present_code"), ts);
        return new Participation(performer, function, mode, time(), ts);
    }

    protected DvInterval<DvDateTime> time() throws Exception {
        return new DvInterval<DvDateTime>(
                new DvDateTime("2004-10-29 22:37:00"),
                new DvDateTime("2004-10-29 23:10:00"));
    }

    /* field */
    protected RMObjectBuilder builder;
    protected static CodePhrase lang = TestCodeSet.ENGLISH;
    protected static CodePhrase charset = TestCodeSet.LATIN_1;
    protected static TerminologyService ts = TestTerminologyService.getInstance();

}
