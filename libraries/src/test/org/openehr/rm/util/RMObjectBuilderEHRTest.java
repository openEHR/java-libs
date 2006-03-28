/*
 * component:   "openEHR Reference Implementation"
 * description: "Class RMObjectBuilderEHRTest"
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
 * RMObjectBuilderEHRTest
 *
 * @author Rong Chen
 * @version 1.0 
 */
package org.openehr.rm.util;

import org.openehr.rm.RMObject;
import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.support.identification.HierarchicalObjectID;
import org.openehr.rm.support.identification.PartyReference;
import org.openehr.rm.support.identification.ArchetypeID;
import org.openehr.rm.support.terminology.TestCodeSet;
import org.openehr.rm.composition.Composition;
import org.openehr.rm.composition.EventContext;
import org.openehr.rm.composition.content.ContentItem;
import org.openehr.rm.composition.content.entry.Evaluation;
import org.openehr.rm.composition.content.entry.Instruction;
import org.openehr.rm.composition.content.entry.Observation;
import org.openehr.rm.composition.content.navigation.Section;
import org.openehr.rm.datastructure.history.History;
import org.openehr.rm.datastructure.history.SingleEvent;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;
import org.openehr.rm.datatypes.basic.DvState;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.datatypes.text.DvText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RMObjectBuilderEHRTest extends RMObjectBuilderTestBase {

    /**
     * The fixture set up called before every test method.
     */
    protected void setUp() throws Exception {
        Map<SystemValue,Object> values = new HashMap<SystemValue,Object>();
        values.put(SystemValue.LANGUAGE, lang);
        values.put(SystemValue.CHARSET, charset);
        values.put(SystemValue.TERMINOLOGY_SERVICE, ts);
        values.put(SystemValue.SUBJECT, subject());
        values.put(SystemValue.PROVIDER, provider());
        values.put(SystemValue.TERRITORY, territory());
        values.put(SystemValue.CONTEXT, context());
        values.put(SystemValue.CATEGORY, TestCodeSet.EVENT);

        builder = new RMObjectBuilder(values);
    }

    public RMObjectBuilderEHRTest(String test) {
        super(test);
    }

    public void testBuildObservation() throws Exception {
        Map<String, Object> values = new HashMap<String, Object>();
        DvText name = new DvText("test observation", lang, charset, ts);
        String node = "at0001";
        History<ItemStructure> data = singleEvent();
        values.put("archetypeNodeId", node);
        values.put("name", name);
        values.put("data", data);
        RMObject obj = builder.construct("Observation", values);

        assertTrue(obj instanceof Observation);
        Observation observation = (Observation) obj;
        assertEquals("archetypeNodeId", node, observation.getArchetypeNodeId());
        assertEquals("name", name, observation.getName());
        assertEquals("data", data, observation.getData());
        assertEquals("subject", subject(), observation.getSubject());
        assertEquals("provider", provider(), observation.getProvider());
        assertEquals("state", null, observation.getState());
        assertEquals("protocol", null, observation.getProtocol());
        assertEquals("actID", null, observation.getActID());
        assertEquals("guidelineID", null, observation.getGuidelineID());
    }

    public void testBuildEvaluation() throws Exception {
        Map<String, Object> values = new HashMap<String, Object>();
        DvText name = new DvText("test evlauation", lang, charset, ts);
        String node = "at0001";
        ItemStructure data = itemSingle();
        values.put("archetypeNodeId", node);
        values.put("name", name);
        values.put("data", data);
        RMObject obj = builder.construct("Evaluation", values);

        assertTrue(obj instanceof Evaluation);
        Evaluation evaluation = (Evaluation) obj;
        assertEquals("archetypeNodeId", node, evaluation.getArchetypeNodeId());
        assertEquals("name", name, evaluation.getName());
        assertEquals("subject", subject(), evaluation.getSubject());
        assertEquals("provider", provider(), evaluation.getProvider());
        assertEquals("data", data, evaluation.getData());
        assertEquals("protocol", null, evaluation.getProtocol());
        assertEquals("actID", null, evaluation.getActID());
        assertEquals("guidelineID", null, evaluation.getGuidelineID());
    }

    public void testBuildInstruction() throws Exception {
        Map<String, Object> values = new HashMap<String, Object>();
        String node = "at0001";
        DvText name = new DvText("test instruction", lang, charset, ts);
        ItemStructure action = itemSingle();
        DvState state = new DvState(new DvCodedText("start", lang, charset,
                new CodePhrase("test", "start_code"), ts), false);
        values.put("archetypeNodeId", node);
        values.put("name", name);
        values.put("action", action);
        values.put("state", state);
        RMObject obj = builder.construct("Instruction", values);

        assertTrue(obj instanceof Instruction);
        Instruction instruction = (Instruction) obj;
        assertEquals("archetypeNodeId", node, instruction.getArchetypeNodeId());
        assertEquals("name", name, instruction.getName());
        assertEquals("subject", subject(), instruction.getSubject());
        assertEquals("provider", provider(), instruction.getProvider());
        assertEquals("action", action, instruction.getAction());
        assertEquals("state", state, instruction.getState());
        assertEquals("data", null, instruction.getData());
        assertEquals("profile", null, instruction.getProfile());
        assertEquals("protocol", null, instruction.getProtocol());
        assertEquals("actID", null, instruction.getActID());
        assertEquals("guidelineID", null, instruction.getGuidelineID());

        // test with class name in upper case
        builder.construct("INSTRUCTION", values);
    }

    public void testBuildSection() throws Exception {
        Map<String, Object> values = new HashMap<String, Object>();
        String node = "at0001";
        DvText name = new DvText("test section", lang, charset, ts);
        List<ContentItem> items = new ArrayList<ContentItem>();
        items.add(observation());
        values.put("archetypeNodeId", node);
        values.put("name", name);
        values.put("items", items);
        RMObject obj = builder.construct("Section", values);

        assertTrue(obj instanceof Section);
        Section section = (Section) obj;
        assertEquals("archetypeNodeId", node, section.getArchetypeNodeId());
        assertEquals("name", name, section.getName());
        assertEquals("items", items, section.getItems());
    }

    public void testBuildComposition() throws Exception {
        Map<String, Object> values = new HashMap<String, Object>();
        String node = "at0001";
        DvText name = new DvText("test instruction", lang, charset, ts);
        List<Section> content = new ArrayList<Section>();
        Archetyped archetypeDetails = new Archetyped(new ArchetypeID(
                "openehr-ehr_rm-Composition.physical_exam.v2"), null, "1.0");
        content.add(section());
        DvCodedText category = TestCodeSet.EVENT;
        values.put("archetypeNodeId", node);
        values.put("archetypeDetails", archetypeDetails);
        values.put("name", name);
        values.put("content", content);
        values.put("category", category);
        RMObject obj = builder.construct("Composition", values);

        assertTrue(obj instanceof Composition);
        Composition composition = (Composition) obj;
        assertEquals("archetypeNodeId", node, composition.getArchetypeNodeId());
        assertEquals("name", name, composition.getName());
        assertEquals("content", content, composition.getContent());
        assertEquals("context", context(), composition.getContext());
        assertEquals("category", category, composition.getCategory());
        assertEquals("territory", territory(), composition.getTerritory());
    }

    private EventContext context() throws Exception {
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
        return new Section("at0001", name, items);
    }

    private Observation observation() throws Exception {
        DvText name = new DvText("test observation", lang, charset, ts);
        return new Observation("at0001", name, subject(), provider(),
                singleEvent());
    }

    private SingleEvent<ItemStructure> singleEvent() throws Exception {
        DvText name = new DvText("test single event", lang, charset, ts);
        DvDateTime orgin = new DvDateTime("2004-10-29 22:37:00");
        return new SingleEvent<ItemStructure>("at0001", name, orgin,
                itemSingle());
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
 *  The Original Code is RMObjectBuilderEHRTest.java
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