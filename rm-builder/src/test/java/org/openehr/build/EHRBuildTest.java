/*
 * component:   "openEHR Reference Implementation"
 * description: "Class EHRBuildTest"
 * keywords:    "unit test"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/BRANCHES/RM-1.0-update/libraries/src/test/org/openehr/rm/util/RMObjectBuilderEHRTest.java $"
 * revision:    "$LastChangedRevision: 50 $"
 * last_change: "$LastChangedDate: 2006-08-10 13:21:46 +0200 (Thu, 10 Aug 2006) $"
 */
package org.openehr.build;

import org.openehr.rm.RMObject;
import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.common.generic.PartyProxy;
import org.openehr.rm.composition.content.entry.Action;
import org.openehr.rm.composition.content.entry.ISMTransition;
import org.openehr.rm.support.identification.ArchetypeID;
import org.openehr.rm.composition.Composition;
import org.openehr.rm.composition.EventContext;
import org.openehr.rm.composition.content.ContentItem;
import org.openehr.rm.composition.content.entry.Evaluation;
import org.openehr.rm.composition.content.entry.Instruction;
import org.openehr.rm.composition.content.entry.Observation;
import org.openehr.rm.composition.content.navigation.Section;
import org.openehr.rm.datastructure.history.History;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.datatypes.text.DvText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openehr.rm.datastructure.history.Event;
import org.openehr.rm.datastructure.history.PointEvent;
import org.openehr.rm.datatypes.quantity.datetime.DvDuration;

/**
 * Test case for EHR objects building
 *
 * @author Rong Chen
 * @version 1.0 
 */

public class EHRBuildTest extends BuildTestBase {
	
	private static CodePhrase EVENT = new CodePhrase("openehr", "433");

    /**
     * The fixture set up called before every test method.
     */
    protected void setUp() throws Exception {
        Map<SystemValue,Object> values = new HashMap<SystemValue,Object>();
        values.put(SystemValue.LANGUAGE, lang);
        values.put(SystemValue.ENCODING, charset);
        values.put(SystemValue.TERMINOLOGY_SERVICE, ts);
        values.put(SystemValue.SUBJECT, subject());
        values.put(SystemValue.PROVIDER, provider());
        values.put(SystemValue.COMPOSER, provider());
        
        CodePhrase territory = new CodePhrase("ISO_3166-1", "SE");
        values.put(SystemValue.TERRITORY, territory);
        values.put(SystemValue.CONTEXT, context());
        
        DvCodedText category = new DvCodedText("event", lang, charset, EVENT, 
        		ts);
        values.put(SystemValue.CATEGORY, category);

        builder = new RMObjectBuilder(values);
    }

    
    public void testBuildObservation() throws Exception {
        Map<String, Object> values = new HashMap<String, Object>();
        DvText name = new DvText("test observation", lang, charset, ts);
        String node = "at0001";
        Archetyped archetypeDetails = new Archetyped(
                new ArchetypeID("openehr-ehr_rm-observation.physical_examination.v3"), "v1.0");
        History<ItemStructure> data = event();
        values.put("archetypeNodeId", node);
        values.put("archetypeDetails", archetypeDetails);
        values.put("name", name);
        values.put("language", lang);
        values.put("encoding", charset);
        values.put("subject", subject());
        values.put("provider", provider());
        values.put("data", data);
        RMObject obj = builder.construct("Observation", values);

        assertTrue(obj instanceof Observation);
        Observation observation = (Observation) obj;
        assertEquals("archetypeNodeId", node, observation.getArchetypeNodeId());
        assertEquals("archetypeDetails", archetypeDetails, observation.getArchetypeDetails());
        assertEquals("name", name, observation.getName());
        assertEquals("language", lang, observation.getLanguage());
        assertEquals("encoding", charset, observation.getEncoding());
        assertEquals("subject", subject(), observation.getSubject());
        assertEquals("provider", provider(), observation.getProvider());
        assertEquals("data", event(), observation.getData());
        assertEquals("state", null, observation.getState());
        assertEquals("protocol", null, observation.getProtocol());
        assertEquals("guidelineId", null, observation.getGuidelineId());
    }

    public void testBuildEvaluation() throws Exception {
        Map<String, Object> values = new HashMap<String, Object>();
        DvText name = new DvText("test evlauation", lang, charset, ts);
        String node = "at0001";
        Archetyped archetypeDetails = new Archetyped(
            new ArchetypeID("openehr-ehr_rm-evaluation.physical_examination.v3"), "v1.0");
        ItemStructure data = itemSingle();
        values.put("archetypeNodeId", node);
        values.put("archetypeDetails", archetypeDetails);
        values.put("name", name);
        values.put("language", lang);
        values.put("encoding", charset);
        values.put("subject", subject());
        values.put("provider", provider());
        values.put("data", data);
        RMObject obj = builder.construct("Evaluation", values);

        assertTrue(obj instanceof Evaluation);
        Evaluation evaluation = (Evaluation) obj;
        assertEquals("archetypeNodeId", node, evaluation.getArchetypeNodeId());
        assertEquals("archetypeDetails", archetypeDetails, evaluation.getArchetypeDetails());
        assertEquals("name", name, evaluation.getName());
        assertEquals("language", lang, evaluation.getLanguage());
        assertEquals("encoding", charset, evaluation.getEncoding());
        assertEquals("subject", subject(), evaluation.getSubject());
        assertEquals("provider", provider(), evaluation.getProvider());
        assertEquals("data", data, evaluation.getData());
        assertEquals("protocol", null, evaluation.getProtocol());
        assertEquals("guidelineID", null, evaluation.getGuidelineId());
    }

    public void testBuildInstruction() throws Exception {
        Map<String, Object> values = new HashMap<String, Object>();
        String node = "at0001";
        DvText name = new DvText("test instruction", lang, charset, ts);
        Archetyped archetypeDetails = new Archetyped(
            new ArchetypeID("openehr-ehr_rm-evaluation.physical_examination.v3"), "v1.0");
        DvText narrative = new DvText("medication instruction", lang, charset, ts);
        
        values.put("archetypeNodeId", node);
        values.put("archetypeDetails", archetypeDetails);
        values.put("name", name);
        values.put("language", lang);
        values.put("encoding", charset);
        values.put("subject", subject());
        values.put("provider", provider());
        values.put("narrative", narrative);
        RMObject obj = builder.construct("Instruction", values);

        assertTrue(obj instanceof Instruction);
        Instruction instruction = (Instruction) obj;
        assertEquals("archetypeNodeId", node, instruction.getArchetypeNodeId());
        assertEquals("archetypeDetails", archetypeDetails, instruction.getArchetypeDetails());
        assertEquals("name", name, instruction.getName());
        assertEquals("language", lang, instruction.getLanguage());
        assertEquals("encoding", charset, instruction.getEncoding());
        assertEquals("subject", subject(), instruction.getSubject());
        assertEquals("provider", provider(), instruction.getProvider());
        assertEquals("narrative", narrative, instruction.getNarrative());
        assertEquals("protocol", null, instruction.getProtocol());
        assertEquals("guidelineID", null, instruction.getGuidelineId());
        assertEquals("expiryTime", null, instruction.getExpiryTime());
        
        // test with class name in upper case
        builder.construct("INSTRUCTION", values);
    }
    
    public void testBuildAction() throws Exception {
        Map<String, Object> values = new HashMap<String, Object>();
        String node = "at0001";
        DvText name = new DvText("test instruction", lang, charset, ts);
        ItemStructure description = itemSingle();
        ISMTransition ismTransition = ismTransition();
        DvDateTime time = new DvDateTime("2006-07-23T23:00:00");
        Archetyped archetypeDetails = new Archetyped(
            new ArchetypeID("openehr-ehr_rm-evaluation.physical_examination.v3"), "v1.0");
        
        values.put("archetypeNodeId", node);
        values.put("archetypeDetails", archetypeDetails);
        values.put("name", name);
        values.put("language", lang);
        values.put("encoding", charset);
        values.put("subject", subject());
        values.put("provider", provider());
        values.put("time", time);
        values.put("description", description);
        values.put("ismTransition", ismTransition);
        RMObject obj = builder.construct("Action", values);

        assertTrue(obj instanceof Action);
        Action action = (Action) obj;
        assertEquals("archetypeNodeId", node, action.getArchetypeNodeId());
        assertEquals("archetypeDetails", archetypeDetails, action.getArchetypeDetails());
        assertEquals("name", name, action.getName());
        assertEquals("language", lang, action.getLanguage());
        assertEquals("encoding", charset, action.getEncoding());
        assertEquals("subject", subject(), action.getSubject());
        assertEquals("provider", provider(), action.getProvider());
        assertEquals("protocol", null, action.getProtocol());
        assertEquals("guidelineID", null, action.getGuidelineId());
        assertEquals("time", time, action.getTime());
        assertEquals("description", description, action.getDescription());
        assertEquals("ismTransition", ismTransition, action.getIsmTransition());
        assertEquals("instructionDetails", null, action.getInstructionDetails());
        
        // test with class name in upper case
        builder.construct("ACTION", values);
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
        DvText name = new DvText("test composition", lang, charset, ts);
        List<Section> content = new ArrayList<Section>();
        content.add(section());
        PartyProxy composer = provider();
        Archetyped archetypeDetails = new Archetyped(new ArchetypeID(
                "openehr-ehr_rm-Composition.physical_exam.v2"), "1.0");
        DvCodedText category = new DvCodedText("event", lang, charset, EVENT, 
        		ts);
        CodePhrase territory = new CodePhrase("ISO_3166-1", "SE");
        
        values.put("archetypeNodeId", node);
        values.put("archetypeDetails", archetypeDetails);
        values.put("name", name);
        values.put("content", content);
        values.put("context", context());
        values.put("composer", composer);
        values.put("category", category);
        values.put("territory", territory);
        RMObject obj = builder.construct("Composition", values);
        assertTrue(obj instanceof Composition);

        Composition composition = (Composition) obj;
        assertEquals("archetypeNodeId", node, composition.getArchetypeNodeId());
        assertEquals("archetypeDetails", archetypeDetails, composition.getArchetypeDetails());
        assertEquals("name", name, composition.getName());
        assertEquals("content", content, composition.getContent());
        assertEquals("context", context(), composition.getContext());
        assertEquals("composer", composer, composition.getComposer());
        assertEquals("category", category, composition.getCategory());
        assertEquals("territory", territory, composition.getTerritory());
    }

    private EventContext context() throws Exception {
    	CodePhrase home = new CodePhrase("openehr", "225");
        DvCodedText homeSetting = new DvCodedText("home setting", lang, charset,
                home, ts);
        return new EventContext(null, new DvDateTime("2006-02-01T12:00:09"), null, null, 
                null, homeSetting, null, ts);
    }

    private Section section() throws Exception {
        DvText name = new DvText("test section", lang, charset, ts);
        List<ContentItem> items = new ArrayList<ContentItem>();
        items.add(observation());
        return new Section("at0001", name, items);
    }

    private Observation observation() throws Exception {
        DvText name = new DvText("test observation", lang, charset, ts);
        Archetyped archetypeDetails = new Archetyped(
            new ArchetypeID("openehr-ehr_rm-observation.physical_examination.v3"), "v1.0");
        return new Observation("at0001", name, archetypeDetails, lang,
                charset, subject(), provider(), event(), ts);
    }

    private ISMTransition ismTransition() throws Exception {
    	DvCodedText planned = new DvCodedText("planned e state", lang, charset, 
    			new CodePhrase("openehr", "526"), ts);
        return new ISMTransition(planned, null, null, ts);       
    }
    
    private History<ItemStructure> event() throws Exception { 
        List<Event<ItemStructure>> items = new ArrayList<Event<ItemStructure>>();
        items.add(pointEvent());
        return new History<ItemStructure>(null, "at0002", text("history"),
                null, null, null, null, new DvDateTime("2006-07-12T09:22:00"), 
                items, DvDuration.getInstance("PT1h"), 
                DvDuration.getInstance("PT3h"), null);
    }

    private Event<ItemStructure> pointEvent() throws Exception {
        return new PointEvent<ItemStructure>(null, "at0003", text("point event"),  
                null, null, null, null, new DvDateTime("2006-07-12T08:00:00"), 
                itemSingle(), null);
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
 *  The Original Code is EHRBuildTest.java
 *
 *  The Initial Developer of the Original Code is Rong Chen.
 *  Portions created by the Initial Developer are Copyright (C) 2003-2008
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