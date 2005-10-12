/*
 * component:   "openEHR Reference Implementation"
 * description: "Class ConstraintTestBase"
 * keywords:    "archetype"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL$"
 * revision:    "$LastChangedRevision$"
 * last_change: "$LastChangedDate$"
 */
/**
 * ConstraintTestBase
 *
 * @author Rong Chen
 * @version 1.0 
 */
package org.openehr.am.archetype;

import junit.framework.TestCase;
import org.openehr.am.archetype.ontology.ArchetypeOntology;
import org.openehr.am.archetype.ontology.DefinitionItem;
import org.openehr.am.archetype.ontology.OntologyDefinitions;
import org.openehr.rm.common.generic.Participation;
import org.openehr.rm.common.generic.RelatedParty;
import org.openehr.rm.support.identification.HierarchicalObjectID;
import org.openehr.rm.support.identification.PartyReference;
import org.openehr.rm.composition.EventContext;
import org.openehr.rm.datastructure.history.Event;
import org.openehr.rm.datastructure.itemstructure.ItemSingle;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;
import org.openehr.rm.datastructure.itemstructure.representation.Cluster;
import org.openehr.rm.datastructure.itemstructure.representation.Element;
import org.openehr.rm.datastructure.itemstructure.representation.Item;
import org.openehr.rm.datatypes.quantity.DvInterval;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datatypes.quantity.datetime.DvDuration;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.support.measurement.TestMeasurementService;
import org.openehr.rm.support.terminology.TerminologyService;
import org.openehr.rm.support.terminology.TestCodeSet;
import org.openehr.rm.support.terminology.TestTerminologyService;
import org.openehr.rm.util.SystemValue;

import java.util.*;

public class ConstraintTestBase extends TestCase {

    public ConstraintTestBase(String test) {
        super(test);
    }

    /**
     * The fixture set up called before every test method.
     */
    protected void setUp() throws Exception {
    }

    /**
     * The fixture clean up called after every test method.
     */
    protected void tearDown() throws Exception {
    }

    // test system values
    public Map<SystemValue, Object> sysmap() {
        Map<SystemValue, Object> map = new HashMap<SystemValue, Object>();

        // datatypes and structures
        map.put(SystemValue.LANGUAGE, lang);
        map.put(SystemValue.CHARSET, charset);
        map.put(SystemValue.TERMINOLOGY_SERVICE, ts);
        map.put(SystemValue.MEASUREMENT_SERVICE,
                TestMeasurementService.getInstance());

        // for ehr classes
        map.put(SystemValue.SUBJECT, subject());
        map.put(SystemValue.PROVIDER, provider());
        map.put(SystemValue.TERRITORY, territory());
        map.put(SystemValue.CONTEXT, context());
        map.put(SystemValue.CATEGORY, TestCodeSet.EVENT);
        return map;
    }

    private EventContext context() {
        DvCodedText setting = new DvCodedText("setting", lang, charset,
                new CodePhrase("test", "setting_code"), ts);
        PartyReference composer = new PartyReference(
                new HierarchicalObjectID("333"));
        return new EventContext(null, time(), composer, null, null,
                setting, null, ts);
    }

    // test subject
    private RelatedParty subject() {
        PartyReference party = new PartyReference(
                new HierarchicalObjectID("123"));
        DvCodedText relationship = new DvCodedText("family relationship", lang,
                charset, new CodePhrase("test", "family_code"), ts);
        return new RelatedParty(party, relationship, ts);
    }

    // test provider
    private Participation provider() {
        PartyReference performer = new PartyReference(
                new HierarchicalObjectID("333"));
        DvCodedText function = new DvCodedText("doctor", lang, charset,
                new CodePhrase("test", "doctor_code"), ts);
        DvCodedText mode = new DvCodedText("present", lang, charset,
                new CodePhrase("test", "present_code"), ts);
        return new Participation(performer, function, mode, time(), ts);
    }

    // test  territory
    private CodePhrase territory() {
        return new CodePhrase("test", "se");
    }

    private DvInterval<DvDateTime> time() {
        return new DvInterval<DvDateTime>(
                new DvDateTime("2004-10-29 22:37:00"),
                new DvDateTime("2004-10-29 23:10:00"));
    }

    // test cluster not exactly right for table or tree
    Cluster cluster() throws Exception {
        DvText name = new DvText("test element", lang, charset, ts);
        List<Item> items = new ArrayList<Item>();
        for (int i = 0; i < 6; i++) {
            DvText value = new DvText("test value" + i, lang, charset, ts);
            items.add(new Element("at001" + i, name, value));
        }
        name = new DvText("test cluster", lang, charset, ts);
        return new Cluster("at0001", name, items);
    }

    // test element
    Element element() throws Exception {
        DvText name = new DvText("test element", lang, charset, ts);
        DvText value = new DvText("test value", lang, charset, ts);
        return new Element("at0001", name, value);
    }

    ItemSingle itemSingle() throws Exception {
        DvText name = new DvText("test item single", lang, charset, ts);
        return new ItemSingle("at0001", name, element());
    }

    // test event
    Event<ItemStructure> event() throws Exception {
        ItemStructure item = itemSingle();
        DvDuration offset = DvDuration.getInstance("P1d");
        return new Event<ItemStructure>(item, offset);
    }

    // test ontology
    ArchetypeOntology ontology(int num) {
        List<DefinitionItem> items = new ArrayList<DefinitionItem>();
        for (int i = 0; i < num; i++) {
            items.add(new DefinitionItem("at000" + i, "text" + i, "desc" + i));
        }
        String lang = TestCodeSet.ENGLISH.getCodeString();
        OntologyDefinitions defs = new OntologyDefinitions(lang, items);
        List<OntologyDefinitions> defsList = new ArrayList<OntologyDefinitions>();
        defsList.add(defs);
        return new ArchetypeOntology(lang, Arrays.asList(new String[]{lang}),
                null, defsList, null, null, null);
    }

    private CodePhrase lang = TestCodeSet.ENGLISH;
    private CodePhrase charset = TestCodeSet.LATIN_1;
    private TerminologyService ts = TestTerminologyService.getInstance();


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
 *  The Original Code is ConstraintTestBase.java
 *
 *  The Initial Developer of the Original Code is Rong Chen.
 *  Portions created by the Initial Developer are Copyright (C) 2003-2004
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