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
package org.openehr.ehrdemo;

import org.openehr.rm.common.generic.PartyIdentified;
import org.openehr.rm.common.generic.PartyProxy;
import org.openehr.rm.datastructure.history.Event;
import org.openehr.rm.datastructure.history.History;
import org.openehr.rm.datastructure.history.PointEvent;
import org.openehr.rm.datastructure.itemstructure.ItemList;
import org.openehr.rm.datastructure.itemstructure.ItemSingle;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;
import org.openehr.rm.datastructure.itemstructure.ItemTree;
import org.openehr.rm.datastructure.itemstructure.representation.Element;
import org.openehr.rm.datastructure.itemstructure.representation.Item;
import org.openehr.rm.datastructure.itemstructure.representation.Cluster;
import org.openehr.rm.datatypes.quantity.DvQuantity;
import org.openehr.rm.datatypes.quantity.datetime.DvDuration;
import org.openehr.rm.support.identification.ObjectRef;
import org.openehr.rm.support.identification.PartyRef;
import org.openehr.rm.support.identification.HierObjectID;
import org.openehr.rm.support.identification.UIDBasedID;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.composition.Composition;
import org.openehr.rm.composition.EventContext;
import org.openehr.rm.composition.content.navigation.Section;
import org.openehr.rm.composition.content.ContentItem;
import org.openehr.rm.composition.content.entry.Observation;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;

import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.common.archetyped.FeederAudit;
import org.openehr.rm.common.archetyped.Link;
import org.openehr.rm.common.archetyped.Locatable;
import org.openehr.rm.common.generic.PartySelf;
import org.openehr.rm.support.identification.ArchetypeID;
import org.openehr.rm.support.measurement.MeasurementService;
import org.openehr.rm.support.terminology.TerminologyService;

/**
 * EntryTestBase
 *
 * @author Rong Chen
 * @version 1.0
 */
public class TestComposition extends TestDataStructure {

	public static Composition compositionWithTwoEntries() throws Exception {
		String archetypeId = "openEHR-EHR-COMPOSITION.encounter.v1";
        DvText name = new DvText("Physical Examination (2)");
        UIDBasedID id = new HierObjectID("1.11.2.3.4.5.0");
        List<ContentItem> content = new ArrayList<ContentItem>();
        content.add(section("section one"));
        content.add(section("section two", "observation"));
        DvCodedText category = TestCodeSetAccess.EVENT;
        Archetyped archetypeDetails = new Archetyped(new ArchetypeID(
                archetypeId), "1.0");
        
        Composition composition = new Composition(id, archetypeId, name, 
        		archetypeDetails, null, null, null, content, 
        		TestTerminologyAccess.ENGLISH, context(), provider(), 
                category, territory(), termServ);
        return composition; 
    }
	
	public static Composition compositionWithSingleEntry() throws Exception {
		String archetypeId = "openEHR-EHR-COMPOSITION.encounter.v1";        
        DvText name = new DvText("Physical Examination (1)");
        UIDBasedID id = new HierObjectID("1.11.2.3.4.5.0");
        List<ContentItem> content = new ArrayList<ContentItem>();
        content.add(section("section one"));
        DvCodedText category = TestCodeSetAccess.EVENT;
        Archetyped archetypeDetails = new Archetyped(new ArchetypeID(
        		archetypeId), "1.0");
        
        Composition composition = new Composition(id, archetypeId, name, 
        		archetypeDetails, null, null, null, content, 
        		TestTerminologyAccess.ENGLISH, context(), provider(), 
                category, territory(), termServ);
        return composition; 
    }
	
	public static Composition emptySmpleComposition() throws Exception {
        
        UIDBasedID id = new HierObjectID("1.11.2.3.4.5.0");
        String archetypeNodeId = "at0001";
        DvText name = new DvText("empty composition");
        Archetyped archetypeDetails = new Archetyped(new ArchetypeID(
        		"openehr-ehr_rm-Composition.physical_examination.v2"), "1.0");
        FeederAudit feederAudit = null;
        Set<Link> links = null;
        Locatable parent = null;
        List<ContentItem> content = null; // empty!!
        
        CodePhrase language = TestTerminologyAccess.ENGLISH;
        EventContext context = null;
        PartyProxy composer = composer();
        DvCodedText category = TestCodeSetAccess.EVENT;
        CodePhrase territory = territory();
        
        Composition composition = new Composition(id, archetypeNodeId, name, 
        		archetypeDetails, feederAudit, links, parent, content,
                language, context, composer, category, territory, termServ);
        return composition; 
    }
	
	public static Composition encounterGlucose() throws Exception {
		UIDBasedID id = new HierObjectID("1.11.2.3.4.5.0");
		String archetypeId = "openEHR-EHR-COMPOSITION.encounter.v1";
        String archetypeNodeId = archetypeId;
        
        // make it diff from others
        DvText name = new DvText("Encounter-" + EhrServiceTester.retrieveCurrentUid());
        Archetyped archetypeDetails = new Archetyped(new ArchetypeID(
        		archetypeId), "1.0");
        FeederAudit feederAudit = null;
        Set<Link> links = null;
        Locatable parent = null;
        List<ContentItem> content = new ArrayList<ContentItem>();
        content.add(glucoseObservation());
        
        CodePhrase language = TestTerminologyAccess.ENGLISH;
        EventContext context = context();
        PartyProxy composer = composer();
        DvCodedText category = TestCodeSetAccess.EVENT;
        CodePhrase territory = territory();
        
        Composition composition = new Composition(id, archetypeNodeId, name, 
        		archetypeDetails, feederAudit, links, parent, content,
                language, context, composer, category, territory, termServ);
        return composition; 
	}

    // test context
    protected static EventContext context() throws Exception {
    	DvDateTime startTime = new DvDateTime();
        return new EventContext(null, startTime, null, null, 
        		null, TestCodeSetAccess.EVENT, null, termServ);        
    }

    protected static Section section(String name) throws Exception {
        List<ContentItem> items = new ArrayList<ContentItem>();
        items.add(observation());
        return new Section("at01000", new DvText(name), items);
    }

    protected static Section section(String name, String observation)
            throws Exception {
        List<ContentItem> items = new ArrayList<ContentItem>();
        items.add(observation(observation));
        return new Section("at02000", new DvText(name), items);
    }

    protected static Observation observation() throws Exception {
        return observation("physical_examination");
    }

    protected static Observation observation(String name) throws Exception {
        String archetypeId = "openehr-ehr_rm-observation.physical_examination.v1";
    	DvText meaning = new DvText(name);
        Archetyped arch = new Archetyped(new ArchetypeID(archetypeId), "1.1");
        return new Observation(archetypeId, meaning, arch, 
        		TestTerminologyAccess.ENGLISH, TestTerminologyAccess.LATIN_1, 
        		subject(), provider(), event("history"), termServ);
    }
    
    // simulate valid xml instance
    protected static Observation glucoseObservation() throws Exception {
        String archetypeId = "openEHR-EHR-OBSERVATION.laboratory-glucose.v1";
    	DvText meaning = new DvText("blood glucose");
        Archetyped arch = new Archetyped(new ArchetypeID(archetypeId), "1.1");
        return new Observation(archetypeId, meaning, arch, 
        		TestTerminologyAccess.ENGLISH, TestTerminologyAccess.LATIN_1, 
        		subject(), provider(), glucoseData(), termServ);
    }
    
    /*
     * <data archetype_node_id="at0001">
				<name>
					<value>data</value>
				</name>
				<origin>
					<value>2006-11-22T18:57:01</value>
				</origin>
				<events xsi:type="POINT_EVENT" archetype_node_id="at0002">
					<name>
						<value>Any event</value>
					</name>
					<time>
						<value>2006-11-22T18:57:01</value>
					</time>
					...
				</events>
			</data>
     */
    public static History<ItemStructure> glucoseData() {
    	UIDBasedID uid = null;
    	String archetypeNodeId = "at0001";
        DvText name = new DvText("data");
        Archetyped archetypeDetails = null;
        FeederAudit feederAudit = null;
        Set<Link> links = null;
        Locatable parent = null; 
        DvDateTime origin = new DvDateTime("2007-07-25T23:11:11");
        List<Event<ItemStructure>> events = new ArrayList<Event<ItemStructure>>();
        PointEvent<ItemStructure> point = eventGlucose(origin);
        events.add(point);
        
        DvDuration period = null;
        DvDuration duration = null;
        ItemStructure summary = null;
    	
    	return new History<ItemStructure>(uid, archetypeNodeId, name,
    			archetypeDetails, feederAudit, links, parent, origin, events, 
    			period, duration, summary);
    }
    
    /*
     * <data xsi:type="ITEM_TREE" archetype_node_id="at0003">
						<name>
							<value>Tree</value>
						</name>
						<items xsi:type="ELEMENT" archetype_node_id="at0013.1">
							<name>
								<value>Blood glucose</value>
							</name>
							<value xsi:type="DV_QUANTITY">
								<magnitude>100</magnitude>
								<units>mmol/l</units>
								<precision>0</precision>
							</value>
						</items>
					</data>
     */    
    private static PointEvent<ItemStructure> eventGlucose(DvDateTime time) {
    	DvQuantity quantity = new DvQuantity("mmol/l", 100.0, 0, measServ);
    	Element element = new Element("at0013.1", new DvText("Blood glucose"),
    			quantity);
    	List<Item> items = new ArrayList<Item>();
    	items.add(element);
    	Cluster cluster = new Cluster("at0004", new DvText("cluster"), items);    	
    	ItemTree data = new ItemTree("at0004", new DvText("Tree"), cluster);    	
    	return new PointEvent<ItemStructure>(null, "at0002", 
    			new DvText("any event"), null, null, null, null, time, data, 
    			null);    			
    }
    
    protected static ItemList list(String name) {
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
                null, itemsCluster);
    }

   protected static History<ItemStructure> event(String name) {
        //element = element("element name", "value");
        String[] ITEMS = {
            "event one", "event two", "event three"
        };
        String[] CODES = {
            "code one", "code two", "code three"
        };
        List<Event<ItemStructure>> items = new ArrayList<Event<ItemStructure>>();
        for (int i = 0; i < ITEMS.length; i++) {
            Element element = element("element " + i, CODES[i]);
            ItemSingle item = new ItemSingle(null, "at0001", text(ITEMS[i]),
                    null, null, null, null, element);
            items.add(new PointEvent<ItemStructure>(null, "at0003", 
            		text("point event"), null, null, null, null, 
            		new DvDateTime("2006-06-25T23:11:11"), item, null));            		
        }
        return new History<ItemStructure>(null, "at0002", text("history"),
                null, null, null, null, new DvDateTime("2006-06-25T23:11:11"),
                items, DvDuration.getInstance("PT1h"), 
                DvDuration.getInstance("PT3h"), null);
    }

    // test  territory
    protected static CodePhrase territory() {
        return new CodePhrase("ISO_3166-1", "se");
    }

    // test subject
    protected static PartySelf subject() throws Exception {
        PartyRef party = new PartyRef(
                new HierObjectID("1.2.4.5.6.12.1"), ObjectRef.Type.PARTY);
        return new PartySelf(party);
    }

    // test provider
    protected static PartyIdentified provider() throws Exception {
        PartyRef performer = new PartyRef(new HierObjectID(
        		"1.3.3.1.2.42.1"), ObjectRef.Type.ORGANISATION);
        return new PartyIdentified(performer, "Dr Chen", null);
    }
    
    protected static PartyIdentified composer() throws Exception {
        PartyRef performer = new PartyRef(new HierObjectID(
        		"1.3.3.1.2.42.1.199"), ObjectRef.Type.PERSON);
        return new PartyIdentified(performer, "Dr Chen", null);
    }

    protected static DvDateTime time(String time) throws Exception {
        return new DvDateTime(time);
    }

    protected static CodePhrase language(String language) throws Exception {
        return new CodePhrase("ISO_639-1", language);
    }

    /* field */
    protected static CodePhrase lang = TestCodeSetAccess.ENGLISH;
    protected static CodePhrase encoding = TestCodeSetAccess.LATIN_1;
    protected static TerminologyService termServ = 
    	TestTerminologyService.getInstance();
    protected static MeasurementService measServ =
    	TestMeasurementService.getInstance();

}
