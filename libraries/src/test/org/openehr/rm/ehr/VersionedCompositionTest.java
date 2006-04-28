/*
 * component:   "openEHR Reference Implementation"
 * description: "Class VersionedCompositionTest"
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
/**
 * VersionedCompositionTest
 *
 * @author Rong Chen
 * @version 1.0 
 */
package org.openehr.rm.ehr;

import junit.framework.TestCase;

import org.openehr.rm.common.generic.AuditDetails;
import org.openehr.rm.common.generic.Participation;
import org.openehr.rm.common.generic.RelatedParty;
import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.support.identification.*;
import org.openehr.rm.composition.Composition;
import org.openehr.rm.composition.EventContext;
import org.openehr.rm.composition.content.ContentItem;
import org.openehr.rm.composition.content.entry.Observation;
import org.openehr.rm.composition.content.navigation.Section;
import org.openehr.rm.datastructure.history.SingleEvent;
import org.openehr.rm.datastructure.itemstructure.ItemSingle;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;
import org.openehr.rm.datastructure.itemstructure.representation.Element;
import org.openehr.rm.datatypes.quantity.DvInterval;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.support.terminology.TerminologyService;
import org.openehr.rm.support.terminology.TestCodeSet;
import org.openehr.rm.support.terminology.TestTerminologyService;

import java.util.ArrayList;
import java.util.List;

public class VersionedCompositionTest extends TestCase {

    public VersionedCompositionTest(String test) {
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

    public void testCreateVersionedComposition() throws Exception {
        VersionedComposition vc = versionedComposition("at0001", "first");
        assertEquals("size", 1, vc.allVersions().size());
    }

    public void testCommit() throws Exception {
        VersionedComposition vc = versionedComposition("at0001", "first");

        assertExceptionThrown(vc, composition("at0002", "second"));

        // todo: need to test different category

        vc.commit(audit(TestCodeSet.AMENDMENT), composition("at0001", "first"),
                contribution("30002"), TestCodeSet.DRAFT, ts,
                ObjectReference.Namespace.LOCAL);
    }

    private void assertExceptionThrown(VersionedComposition vc,
                                       Composition data) throws Exception {
         try {
            vc.commit(audit(TestCodeSet.AMENDMENT), data, contribution("30002"),
                    TestCodeSet.DRAFT, ts, ObjectReference.Namespace.LOCAL);
            fail("exception should be thrown");
        } catch(Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }
    }

    // test versioned composition
    private VersionedComposition versionedComposition(String node, String text)
            throws Exception {
        ObjectID id = new HierarchicalObjectID("20001");
        Composition firstData = composition(node, text);
        ObjectReference ehrRef = new ObjectReference(
                new HierarchicalObjectID("20001"),
                ObjectReference.Namespace.LOCAL,
                ObjectReference.Type.EHR);
        return new VersionedComposition(id, ehrRef,
                audit(TestCodeSet.CREATION), firstData, contribution("30001"),
                TestCodeSet.DRAFT, ts, ObjectReference.Namespace.LOCAL);
    }

    // test audit
    private AuditDetails audit(DvCodedText changeType) throws Exception {
        PartyReference committer = new PartyReference(
                new HierarchicalObjectID("10001"));
        return new AuditDetails("/", committer, new DvDateTime(),
                changeType, new DvText("desc"),
                TestTerminologyService.getInstance());
    }

    // test contribution
    private ObjectReference contribution(String id) throws Exception {
        return new ObjectReference(new HierarchicalObjectID(id),
                ObjectReference.Namespace.LOCAL,
                ObjectReference.Type.CONTRIBUTION);
    }

    // test composition
    private Composition composition(String node, String text)
            throws Exception {
        DvText name = new DvText(text, lang, charset, ts);
        ObjectID id = new HierarchicalObjectID("111");
        List<Section> content = new ArrayList<Section>();
        content.add(section());
        DvCodedText category = TestCodeSet.EVENT;
        Archetyped archetypeDetails = new Archetyped(new ArchetypeID(
                "openehr-ehr_rm-Composition.physical_examination.v2"), null,
                "1.0");
        return new Composition(id, node, name, archetypeDetails, null, null,
                content, context(), category, territory(), ts);
    }

    // test context
    private EventContext context() {
        DvCodedText setting = new DvCodedText("setting", lang, charset,
                new CodePhrase("test", "setting_code"), ts);
        PartyReference composer = new PartyReference(
                new HierarchicalObjectID("333"));
        return new EventContext(null, time(), composer, null, null,
                setting, null, ts);
    }

    private Section section() throws Exception {
        DvText name = new DvText("test section", lang, charset, ts);
        List<ContentItem> items = new ArrayList<ContentItem>();
        items.add(observation());
        return new Section("at0000", name, items);
    }

    private Observation observation() throws Exception {
        DvText meaning = new DvText("test observation", lang, charset, ts);
        return new Observation("at0000", meaning, subject(), provider(),
                singleEvent());
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

    private SingleEvent<ItemStructure> singleEvent() throws Exception {
        DvText name = new DvText("test single event", lang, charset, ts);
        DvDateTime orgin = new DvDateTime("2004-10-29 22:37:00");
        return new SingleEvent<ItemStructure>("at0000", name, orgin, itemSingle());
    }

    // test element
    private Element element() throws Exception {
        DvText name = new DvText("test element", lang, charset, ts);
        DvText value = new DvText("test value", lang, charset, ts);
        return new Element("at0000", name, value);
    }

    private ItemSingle itemSingle() throws Exception {
        DvText name = new DvText("test item single", lang, charset, ts);
        return new ItemSingle("at0000", name, element());
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
 *  The Original Code is VersionedCompositionTest.java
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