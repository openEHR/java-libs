/*
 * component:   "openEHR Reference Implementation"
 * description: "Class DemographicTestBase"
 * keywords:    "unit test"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/BRANCHES/RM-1.0-update/libraries/src/test/org/openehr/rm/demographic/DemographicTestBase.java $"
 * revision:    "$LastChangedRevision: 50 $"
 * last_change: "$LastChangedDate: 2006-08-10 13:21:46 +0200 (Thu, 10 Aug 2006) $"
 */

package org.openehr.rm.demographic;

import org.openehr.rm.common.generic.PartyIdentified;
import org.openehr.rm.common.generic.PartySelf;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;
import org.openehr.rm.datastructure.itemstructure.ItemSingle;
import org.openehr.rm.datastructure.itemstructure.representation.Element;
import org.openehr.rm.datastructure.itemstructure.representation.Cluster;
import org.openehr.rm.datastructure.itemstructure.representation.Item;
//import org.openehr.rm.datastructure.history.SingleEvent;
import org.openehr.rm.datastructure.history.History;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.datatypes.basic.DataValue;
import org.openehr.rm.datatypes.basic.DvState;
import org.openehr.rm.datatypes.quantity.datetime.DvDate;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datatypes.quantity.datetime.DvTime;
import org.openehr.rm.datatypes.quantity.DvCount;
import org.openehr.rm.datatypes.quantity.DvInterval;
import org.openehr.rm.datatypes.uri.DvEHRURI;
import org.openehr.rm.support.identification.*;
import org.openehr.rm.common.archetyped.Link;
import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.common.archetyped.FeederAudit;
import org.openehr.rm.common.generic.Participation;
import org.openehr.rm.composition.Composition;
import org.openehr.rm.composition.EventContext;
import org.openehr.rm.composition.content.navigation.Section;
import org.openehr.rm.composition.content.ContentItem;
import org.openehr.rm.composition.content.entry.Instruction;
import org.openehr.rm.composition.content.entry.Observation;
import org.openehr.rm.support.terminology.TerminologyService;
import org.openehr.rm.support.terminology.TestTerminologyService;
import org.openehr.rm.support.terminology.TestCodeSetAccess;
import org.openehr.rm.support.measurement.MeasurementService;
import org.openehr.rm.support.measurement.TestMeasurementService;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

import junit.framework.TestCase;
import org.openehr.rm.datastructure.history.Event;
import org.openehr.rm.datastructure.history.PointEvent;
import org.openehr.rm.datatypes.quantity.datetime.DvDuration;

/**
 * DemographicTestBase
 *
 * @author Rong Chen
 * @version 1.0
 */
public class DemographicTestBase extends TestCase {

    /**
     * Constructs a test case with the given name.
     */
    public DemographicTestBase(String name) {
        super(name);
    }

/*    protected SingleEvent<ItemStructure> singleEvent(String value,
                                                  String datetime) {
        Element element = element("test element", text(value));
        ItemSingle item =  new ItemSingle(null, "at0001", text("item single"),
                null, null, null, element);
        return new SingleEvent<ItemStructure>("at0000",
                text("single event"), datetime(datetime), item);
    }*/

    protected ItemSingle itemSingle(String value) {
        Element element = element("test element", text(value));
        return new ItemSingle("at0000", text("item single"), element);
    }

    protected Cluster testCluster() {
        List<Item> items = new ArrayList<Item>();
        for (int i = 0; i < 5; i++) {
            items.add(element("element no." + i, text("value " + i)));
        }
        return new Cluster("at0000", text("test cluster"), items);
    }

    protected DvCodedText coded(String value, String code) {
        return new DvCodedText(value, lang(), encoding(),
                new CodePhrase(SNOMED_CT, code), ts);
    }

    protected DvText text(String value) {
        return new DvText(value, lang(), encoding(), ts);
    }

    protected Element element(String meaning, DataValue value) {
        return new Element("at0000", text(meaning), value);
    }

    protected CodePhrase lang() {
        return new CodePhrase(LANGUAGE, "english");
    }

    protected CodePhrase encoding() {
        return new CodePhrase(CHARSET, "ISO-8859-1");
    }

    protected DvDate date(String value) {
        return new DvDate(value);
    }

    protected DvDateTime datetime(String value) {
        return new DvDateTime(value);
    }

    protected DvTime time(String value) {
        return new DvTime(value);
    }

    protected DvCount count(int value) {
        return new DvCount(value);
    }

    protected PartyRef partyRef(String id, ObjectRef.Type type) {
        return new PartyRef(oid(id), type);
    }

    protected ObjectRef contriRef(String id) {
        return new ObjectRef(oid(id),
                ObjectRef.Namespace.LOCAL,
                ObjectRef.Type.CONTRIBUTION);
    }

    protected UIDBasedID oid(String value) {
        return new HierObjectID(value);
    }

    protected ObjectVersionID ovid(String value) {
        return new ObjectVersionID(value);
    }
    
    protected Set<Link> links(int num) {
        Set<Link> links = new HashSet<Link>();
        for (int i = 0; i < num; i++) {
            links.add(new Link(text("link meaning"), text("link type"),
                    new DvEHRURI("ehr://composition/section/entry" + i)));
        }
        return links;
    }

    protected EventContext eventContext() throws Exception {
        List<Participation> participations = new ArrayList<Participation>();
                PartyRef performer = new PartyRef(
                new HierObjectID("1.3.3.1.2.42.1"), ObjectRef.Type.ORGANISATION);
        Participation part1 = new Participation(provider("1.3.24.2.3.4.2", 
                ObjectRef.Type.ORGANISATION),
                coded("participation function", "23432423"),
                coded("participation mode", "242344"),
                new DvInterval<DvDateTime>(datetime("2000-10-10T10:00:00"),
                        datetime("2001-10-10T10:00:00")), ts);
        participations.add(part1);
        return new EventContext(provider("1.2.3.4.2.5",ObjectRef.Type.ORGANISATION ), 
                datetime("2000-10-10T10:00:00"), null, 
                participations, "event context location",
                coded("event context setting", "2342342"),
                itemSingle("other context"), ts);
    }

    protected Instruction instruction() throws Exception {
    	UIDBasedID uid = oid("1.34.8.2.3.1.4");
        String meaning = "at0000";
        DvText name = text("instruction 2 name");
        Archetyped archetypeDetails = new Archetyped(
                new ArchetypeID("openehr-ehr_rm-instruction2.diabetes.v1"), "1.2");
        FeederAudit feederAudit = null;
        Set<Link> links = null;

        ItemStructure protocol = itemSingle("instruction2 protocol");
        String actID = "instruction2 actId";
        ObjectRef guidelineId = guideline("543234");
        ObjectRef.Type[] types = { ObjectRef.Type.PARTY};
        List<Participation> participations = participationList("1.2.3.4.5.6.11", 1, types);
        DvState state = new DvState(coded("started", "141341234"), false);
        ItemStructure action = itemSingle("instruction2 action");
        ItemStructure profile = itemSingle("instruction2 profile");
        ItemStructure data = itemSingle("instruction2 data");

        return new Instruction(uid, meaning, name,
                archetypeDetails, feederAudit, links, null, language("en"), language("en"),
                subject("1.4.55.1.4.2.4"), provider("1.2.15.2.5.15.4", ObjectRef.Type.ORGANISATION),
                null, participations, protocol, guidelineId, text("narrative"), null, null, null, ts);
    }

/*    protected Observation observation(String name) throws Exception {
        DvText meaning = new DvText(name);
        Archetyped arch = new Archetyped(new ArchetypeID(
                "openehr-ehr_rm-observation.physical_examination.v3"), "1.1");
        return new Observation("at0001", meaning, arch, language("en"), language("en"), 
                subject("1.2.52.1.1.5.2"), provider("1.5.5.12.26.15.2", ObjectRef.Type.ORGANISATION), 
                event("history"), ts);
    }*/
    
   protected History<ItemSingle> event(String name) {
        //element = element("element name", "value");
        String[] ITEMS = {
            "event one", "event two", "event three"
        };
        String[] CODES = {
            "code one", "code two", "code three"
        };
        List<Event<ItemSingle>> items = new ArrayList<Event<ItemSingle>>();
        for (int i = 0; i < ITEMS.length; i++) {
            Element element = element("element " + i, text(CODES[i]));
            ItemSingle item = new ItemSingle(null, "at0001", text(ITEMS[i]),
                    null, null, null, null, element);
            items.add(new PointEvent<ItemSingle>(null, "at0003", text("point event"), null, 
                    null, null, null, new DvDateTime("2006-06-25T23:11:11"), item, null));
           // uid, archetypeNodeId, name, archetypeDetails, feederAudit, links,
		//		parent, time, data, state
        }
        return new History<ItemSingle>(null, "at0002", text("history"),
                null, null, null, null, new DvDateTime("2006-06-25T23:11:11"), items, 
                DvDuration.getInstance("PT1h"), DvDuration.getInstance("PT3h"), null);
    }
   
    protected PartySelf subject(String id) {
        return new PartySelf(partyRef(id, ObjectRef.Type.PERSON));
    }

    protected ObjectRef guideline(String id) {
        return new ObjectRef(oid(id), ObjectRef.Namespace.LOCAL,
                ObjectRef.Type.GUIDELINE);
    }

    protected List<Participation> participationList(String id,
                            int num, ObjectRef.Type[] types) throws Exception {
        List<Participation> list = new ArrayList<Participation>();
        for (int i = 0; i < num; i++) {
            list.add(participation(id + i, types[i]));
        }
        return list;
    }

    protected Participation participation(String id, ObjectRef.Type type) throws Exception {
        return new Participation(provider(id, type),
                coded("participation function", id + 1),
                coded("participation mode ", id + 1),
                new DvInterval<DvDateTime>(datetime("2000-10-10 10:00:00"),
                        datetime("2001-10-10 10:00:00")), ts);
    }
    
    protected PartyIdentified provider(String id, ObjectRef.Type type) throws Exception {
        PartyRef performer = new PartyRef(
                new HierObjectID(id), type);
        return new PartyIdentified(performer, "provider's name", null);
    }

    protected CodePhrase language(String language) throws Exception {
        return new CodePhrase("test", language);
    }
    
    /* fields */
    protected TerminologyService ts = TestTerminologyService.getInstance();
    protected MeasurementService ms = TestMeasurementService.getInstance();

    /* static fields */
    protected static final TerminologyID LANGUAGE;
    protected static final TerminologyID CHARSET;
    protected static final TerminologyID SNOMED_CT;

    static {
        LANGUAGE = new TerminologyID("language-test");
        CHARSET = new TerminologyID("encoding-test");
        SNOMED_CT = new TerminologyID("snomedct-test");
    }

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
 *  The Original Code is DemographicTestBase.java
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