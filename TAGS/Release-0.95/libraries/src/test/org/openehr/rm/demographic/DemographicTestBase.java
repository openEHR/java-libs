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
 * file:        "$URL$"
 * revision:    "$LastChangedRevision$"
 * last_change: "$LastChangedDate$"
 */

package org.openehr.rm.demographic;

import org.openehr.rm.datastructure.itemstructure.ItemStructure;
import org.openehr.rm.datastructure.itemstructure.ItemSingle;
import org.openehr.rm.datastructure.itemstructure.representation.Element;
import org.openehr.rm.datastructure.itemstructure.representation.Cluster;
import org.openehr.rm.datastructure.itemstructure.representation.Item;
import org.openehr.rm.datastructure.history.SingleEvent;
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
import org.openehr.rm.common.generic.RelatedParty;
import org.openehr.rm.composition.Composition;
import org.openehr.rm.composition.EventContext;
import org.openehr.rm.composition.content.navigation.Section;
import org.openehr.rm.composition.content.ContentItem;
import org.openehr.rm.composition.content.entry.Instruction;
import org.openehr.rm.composition.content.entry.Observation;
import org.openehr.rm.support.terminology.TerminologyService;
import org.openehr.rm.support.terminology.TestTerminologyService;
import org.openehr.rm.support.terminology.TestCodeSet;
import org.openehr.rm.support.measurement.MeasurementService;
import org.openehr.rm.support.measurement.TestMeasurementService;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

import junit.framework.TestCase;

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

    protected SingleEvent<ItemStructure> singleEvent(String value,
                                                  String datetime) {
        Element element = element("test element", text(value));
        ItemSingle item =  new ItemSingle(null, "at0001", text("item single"),
                null, null, null, element);
        return new SingleEvent<ItemStructure>("at0000",
                text("single event"), datetime(datetime), item);
    }

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
        return new DvCodedText(value, lang(), charset(),
                new CodePhrase(SNOMED_CT, code), ts);
    }

    protected DvText text(String value) {
        return new DvText(value, lang(), charset(), ts);
    }

    protected Element element(String meaning, DataValue value) {
        return new Element("at0000", text(meaning), value);
    }

    protected CodePhrase lang() {
        return new CodePhrase(LANGUAGE, "english");
    }

    protected CodePhrase charset() {
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

    protected PartyReference partyRef(String id) {
        return new PartyReference(oid(id));
    }

    protected ObjectReference contriRef(String id) {
        return new ObjectReference(oid(id),
                ObjectReference.Namespace.LOCAL,
                ObjectReference.Type.CONTRIBUTION);
    }

    protected ObjectID oid(String value) {
        return new HierarchicalObjectID(value);
    }

    protected Composition composition(String id) throws Exception {
            ObjectID uid = oid(id);
            String meaning = "at0000";
            DvText name = text("section name");
            List<Section> content = new ArrayList<Section>();
            content.add(section());
            return new Composition(uid, meaning, name, null,
                    null, links(3), content, eventContext(), TestCodeSet.EVENT,
                    new CodePhrase("test term", "4573466"), ts);
        }

    protected Set<Link> links(int num) {
        Set<Link> links = new HashSet<Link>();
        for (int i = 0; i < num; i++) {
            links.add(new Link(text("link meaning"), text("link type"),
                    new DvEHRURI("ehr://composition/section/entry" + i)));
        }
        return links;
    }

    protected EventContext eventContext() {
        List<Participation> participations = new ArrayList<Participation>();
        Participation part1 = new Participation(partyRef("324234"),
                coded("participation function", "23432423"),
                coded("participation mode", "242344"),
                new DvInterval<DvDateTime>(datetime("2000-10-10 10:00:00"),
                        datetime("2001-10-10 10:00:00")), ts);
        participations.add(part1);
        return new EventContext(partyRef("23423424"),
                new DvInterval<DvDateTime>(datetime("2000-10-10 10:00:00"),
                        datetime("2001-10-10 10:00:00")),
                partyRef("23423"), participations, "event context location",
                coded("event context setting", "2342342"),
                itemSingle("other context"), ts);
    }

    protected Section section() throws Exception {
        ObjectID uid = oid("423412441234");
        String meaning = "at0000";
        DvText name = text("section2 name");
        List<ContentItem> items = new ArrayList<ContentItem>();
        items.add(instruction());
        items.add(observation());
        return new Section(uid, meaning, name, null, null, null, items);
    }

    protected Instruction instruction() throws Exception {
        ObjectID uid = oid("348231-432");
        String meaning = "at0000";
        DvText name = text("instruction 2 name");
        Archetyped archetypeDetails = new Archetyped(
                new ArchetypeID("openehr-ehr_rm-instruction2.diabetes.v1"),
                new AccessGroupReference(oid("98542734")), "1.2");
        FeederAudit feederAudit = null;
        Set<Link> links = null;
        RelatedParty subject = subject("413241234");
        Participation provider = participation("43214321");
        ItemStructure protocol = itemSingle("instruction2 protocol");
        String actID = "instruction2 actId";
        ObjectReference guidelineId = guideline("543234");
        List<Participation> participations = participationList("4321", 1);
        DvState state = new DvState(coded("started", "141341234"), false);
        ItemStructure action = itemSingle("instruction2 action");
        ItemStructure profile = itemSingle("instruction2 profile");
        ItemStructure data = itemSingle("instruction2 data");

        return new Instruction(uid, meaning, name,
                archetypeDetails, feederAudit, links, subject, provider,
                protocol, actID, guidelineId, participations, state,
                action, profile, data);
    }

    protected Observation observation() throws Exception {
        ObjectID uid = oid("52345234523");
        String meaning = "at0000";
        DvText name = text("observation 2 name");
        Archetyped archetypeDetails = new Archetyped(
                new ArchetypeID("openehr-ehr_rm-observation2.diabetes.v1"),
                new AccessGroupReference(oid("1343124")), "1.2");
        FeederAudit feederAudit = null;
        Set<Link> links = null;
        RelatedParty subject = subject("1432");
        Participation provider = participation("413241");
        ItemStructure protocol = itemSingle("observation2 protocol");
        String actID = "observation2 actId";
        ObjectReference guidelineId = guideline("431241");
        List<Participation> participations = participationList("4312", 5);
        History<ItemStructure> data = singleEvent("observation2 data",
                "2003-10-30 19:10:00");
        History<ItemStructure> state = singleEvent("observation2 state",
                "2003-10-31 20:15:00");

        return new Observation(uid, meaning, name,
                archetypeDetails, feederAudit, links, subject, provider,
                protocol, actID, guidelineId, participations, data, state);
    }

    protected RelatedParty subject(String id) {
        return new RelatedParty(partyRef(id), coded("self", "4203281"), ts);
    }

    protected ObjectReference guideline(String id) {
        return new ObjectReference(oid(id), ObjectReference.Namespace.LOCAL,
                ObjectReference.Type.GUIDELINE);
    }

    protected List<Participation> participationList(String id,
                                                    int num) throws Exception {
        List<Participation> list = new ArrayList<Participation>();
        for (int i = 0; i < num; i++) {
            list.add(participation(id + i));
        }
        return list;
    }

    protected Participation participation(String id) {
        return new Participation(partyRef(id),
                coded("participation function", id + 1),
                coded("participation mode ", id + 1),
                new DvInterval<DvDateTime>(datetime("2000-10-10 10:00:00"),
                        datetime("2001-10-10 10:00:00")), ts);
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
        CHARSET = new TerminologyID("charset-test");
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