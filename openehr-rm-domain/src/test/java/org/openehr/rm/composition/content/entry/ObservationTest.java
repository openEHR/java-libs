/*
 * Copyright (C) 2004-2008 Rong Chen, Acode HB, Sweden
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
package org.openehr.rm.composition.content.entry;

import java.util.ArrayList;
import java.util.List;

import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.datastructure.history.Event;
import org.openehr.rm.datastructure.history.History;
import org.openehr.rm.datastructure.history.PointEvent;
import org.openehr.rm.datastructure.itemstructure.ItemList;
import org.openehr.rm.datastructure.itemstructure.ItemSingle;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;
import org.openehr.rm.datastructure.itemstructure.representation.Element;
import org.openehr.rm.datatypes.quantity.datetime.DvDate;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datatypes.quantity.datetime.DvDuration;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.composition.CompositionTestBase;
import org.openehr.rm.support.identification.ArchetypeID;
import org.openehr.rm.support.terminology.TerminologyService;
import org.openehr.rm.support.terminology.TestTerminologyService;
import org.openehr.terminology.SimpleTerminologyService;

/**
 * ObservationTest
 *
 * @author Rong Chen
 * @version 1.0
 */
public class ObservationTest extends CompositionTestBase {

    public ObservationTest(String test) {
        super(test);
    }
    
   /**
     * The fixture clean up called after every test method.
     */
    protected void tearDown() throws Exception {
        observation = null;
    }

    /**
     * The fixture set up called before every test method.
     */
    protected void setUp() throws Exception {
        state = event("state");
        Archetyped archetypeDetails = new Archetyped(
                new ArchetypeID("openehr-ehr_rm-observation.physical_examination.v3"),
                "1.1");
        
        List<Element> items = new ArrayList<Element>();
        items.add(new Element(("at0001"), "header", new DvText("date")));
        items.add(new Element(("at0002"), "value",	new DvDate("2008-05-17")));
        itemList = new ItemList("at0003", "item list", items); 
        
        event = new PointEvent<ItemList>("at0004", "point event", 
        		new DvDateTime("2008-05-17T10:00:00"), itemList);
        
        List<Event<ItemList>> events = new ArrayList<Event<ItemList>>();
        events.add(event);
        data = new History<ItemList>("at0005", "data",
        		new DvDateTime("2008-05-17T10:00:00"), events);
        
        items = new ArrayList<Element>();
        items.add(new Element(("at0004"), "protocol 1", new DvText("date")));
        items.add(new Element(("at0005"), "protocol 2",	new DvDate("2008-05-17")));
        protocol = new ItemList("at0006", "item list", items); 
        
        TerminologyService ts = TestTerminologyService.getInstance();
        observation = new Observation(null, "at0007", new DvText("observation"),
        		archetypeDetails, null, null, null, lang, encoding, subject(), 
                provider(), null, null, protocol, null, data, state, 
                TestTerminologyService.getInstance());
    }
    
    public void testCreateObservationWithSimpleTerminologyService() 
    		throws Exception {
    	
    	ItemStructure protocol = list("list protocol");
        History<ItemStructure> data = event("data");
        History<ItemStructure> state = event("state");
        Archetyped arch = new Archetyped(
                new ArchetypeID("openehr-ehr_rm-observation.physical_examination.v3"),
                "1.1");
        TerminologyService termServ = SimpleTerminologyService.getInstance();    	
    	
        CodePhrase language = new CodePhrase("ISO_639-1", "en");
		CodePhrase encoding = new CodePhrase("IANA_character-sets", "UTF-8");
		
    	observation = new Observation(null, "at000", text("observation"),
                arch, null, null, null, language, encoding, subject(), 
                provider(), null, null, protocol, null, data, state, termServ);
        
    }

    public void testItemAtPathWhole() throws Exception {
    	path = "/";
    	value = observation.itemAtPath(path);
    	assertEquals("unexpected resutl for path: " + path, observation, value);
    }
    
    public void testItemAtPathSubject() throws Exception {
    	path = "/subject";
    	value = observation.itemAtPath(path);
    	assertEquals("unexpected resutl for path: " + path, 
    			observation.getSubject(), value);
    }
    
    public void testItemAtPathData() throws Exception {
    	path = "/data";
    	value = observation.itemAtPath(path);
    	assertEquals("unexpected resutl for path: " + path,	data, value);
    }
 
    public void testItemAtPathDataEvent() throws Exception {
    	path = "/data/events[at0004]";
    	value = observation.itemAtPath(path);
    	assertEquals("unexpected resutl for path: " + path, 
    			data.getEvents().get(0), value);
    }
    
    public void testItemAtPathDataEventName() throws Exception {
    	path = "/data/events['point event']";
    	value = observation.itemAtPath(path);
    	assertEquals("unexpected resutl for path: " + path, 
    			data.getEvents().get(0), value);
    }
    
    public void testItemAtPathDataEventData() throws Exception {
    	path = "/data/events[at0004]/data";
    	value = observation.itemAtPath(path);
    	assertEquals("unexpected resutl for path: " + path,	itemList, value);
    }
    
    public void testItemAtPathDataEventNameData() throws Exception {
    	path = "/data/events['point event']/data";
    	value = observation.itemAtPath(path);
    	assertEquals("unexpected resutl for path: " + path, itemList, value);
    }
    
    public void testItemAtPathDataEventDataItem1() throws Exception {
    	path = "/data/events[at0004]/data/items[at0001]";
    	value = observation.itemAtPath(path);
    	assertEquals("unexpected resutl for path: " + path,	
    			itemList.getItems().get(0), value);
    }
    
    public void testItemAtPathDataEventDataItem2() throws Exception {
    	path = "/data/events[at0004]/data/items[at0002]";
    	value = observation.itemAtPath(path);
    	assertEquals("unexpected resutl for path: " + path,	
    			itemList.getItems().get(1), value);
    }
    
    public void testItemAtPathDataEventDataItem1Value() throws Exception {
    	path = "/data/events[at0004]/data/items[at0001]/value";
    	value = observation.itemAtPath(path);
    	assertEquals("unexpected resutl for path: " + path,	
    			itemList.getItems().get(0).getValue(), value);
    }
    
    public void testItemAtPathDataEventDataItem2Value() throws Exception {
    	path = "/data/events[at0004]/data/items[at0002]/value";
    	value = observation.itemAtPath(path);
    	assertEquals("unexpected resutl for path: " + path,	
    			itemList.getItems().get(1).getValue(), value);
    }
    
    public void testItemAtPathDataEventDataItemHeader() throws Exception {
    	path = "/data/events['point event']/data/items['header']";
    	value = observation.itemAtPath(path);
    	assertEquals("unexpected resutl for path: " + path, 
    			itemList.getItems().get(0), value);
    }
    
    public void testItemAtPathDataEventDataItemValue() throws Exception {
    	path = "/data/events['point event']/data/items['value']";
    	value = observation.itemAtPath(path);
    	assertEquals("unexpected resutl for path: " + path, 
    			itemList.getItems().get(1), value);
    }
    
    public void testItemAtPathDataEventDataItemHeaderValue() throws Exception {
    	path = "/data/events['point event']/data/items['header']/value";
    	value = observation.itemAtPath(path);
    	assertEquals("unexpected resutl for path: " + path, 
    			itemList.getItems().get(0).getValue(), value);
    }
    
    public void testItemAtPathDataEventDataItemValueValue() throws Exception {
    	path = "/data/events['point event']/data/items['value']/value";
    	value = observation.itemAtPath(path);
    	assertEquals("unexpected resutl for path: " + path, 
    			itemList.getItems().get(1).getValue(), value);
    }
    
    public void testItemAtPathState() throws Exception {
    	path = "/state";
    	value = observation.itemAtPath(path);
    	assertEquals("unexpected resutl for path: " + path, 
    			observation.getState(), value);
    }
    
    public void testItemAtPathProtocol() throws Exception {
    	path = "/protocol";
    	value = observation.itemAtPath(path);
    	assertEquals("unexpected resutl for path: " + path, 
    			observation.getProtocol(), value);
    }
    
    public void testEquals() throws Exception
    {
    	ItemStructure protocol = list("list protocol");
        History<ItemStructure> data = event("data");
        History<ItemStructure> state = event("state");
        Archetyped arch = new Archetyped(
                new ArchetypeID("openehr-ehr_rm-observation.physical_examination.v3"),
                "1.1");
        TerminologyService termServ = SimpleTerminologyService.getInstance();    	
    	
        CodePhrase language = new CodePhrase("ISO_639-1", "en");
		CodePhrase encoding = new CodePhrase("IANA_character-sets", "UTF-8");
		
    	Observation observationOne = new Observation(null, "at000", text("observation"),
                arch, null, null, null, language, encoding, subject(), 
                provider(), null, null, protocol, null, data, state, termServ);
    	
    	Observation observationTwo = new Observation(null, "at000", text("observation"),
                arch, null, null, null, language, encoding, subject(), 
                provider(), null, null, protocol, null, data, state, termServ);
    	
    	assertTrue(observationOne.equals(observationTwo));
    }
    
    public void testNotEquals() throws Exception
    {
    	ItemStructure protocol = list("list protocol");
        History<ItemStructure> data = event("data");
        History<ItemStructure> state = event("state");
        Archetyped arch = new Archetyped(
                new ArchetypeID("openehr-ehr_rm-observation.physical_examination.v3"),
                "1.1");
        TerminologyService termServ = SimpleTerminologyService.getInstance();    	
    	
        CodePhrase language = new CodePhrase("ISO_639-1", "en");
		CodePhrase encoding = new CodePhrase("IANA_character-sets", "UTF-8");
		
    	Observation observationOne = new Observation(null, "at001", text("observation"),
                arch, null, null, null, language, encoding, subject(), 
                provider(), null, null, protocol, null, data, state, termServ);
    	
    	Observation observationTwo = new Observation(null, "at000", text("observation"),
                arch, null, null, null, language, encoding, subject(), 
                provider(), null, null, protocol, null, data, state, termServ);
    	
    	assertFalse(observationOne.equals(observationTwo));
    }
    
    public void testNotEqualsProtocol() throws Exception
    {
    	ItemStructure protocol = list("list protocol");
        History<ItemStructure> data = event("data");
        History<ItemStructure> state = event("state");
        Archetyped arch = new Archetyped(
                new ArchetypeID("openehr-ehr_rm-observation.physical_examination.v3"),
                "1.1");
        TerminologyService termServ = SimpleTerminologyService.getInstance();    	
    	
        CodePhrase language = new CodePhrase("ISO_639-1", "en");
		CodePhrase encoding = new CodePhrase("IANA_character-sets", "UTF-8");
		
    	Observation observationOne = new Observation(null, "at001", text("observation"),
                arch, null, null, null, language, encoding, subject(), 
                provider(), null, null, protocol, null, data, state, termServ);
    	
    	Observation observationTwo = new Observation(null, "at000", text("observation"),
                arch, null, null, null, language, encoding, subject(), 
                provider(), null, null, null, null, data, state, termServ);
    	
    	assertFalse(observationOne.equals(observationTwo));
    }
    
    public void testNotEqualsState() throws Exception
    {
    	ItemStructure protocol = list("list protocol");
        History<ItemStructure> data = event("data");
        History<ItemStructure> state = event("state");
        Archetyped arch = new Archetyped(
                new ArchetypeID("openehr-ehr_rm-observation.physical_examination.v3"),
                "1.1");
        TerminologyService termServ = SimpleTerminologyService.getInstance();    	
    	
        CodePhrase language = new CodePhrase("ISO_639-1", "en");
		CodePhrase encoding = new CodePhrase("IANA_character-sets", "UTF-8");
		
    	Observation observationOne = new Observation(null, "at001", text("observation"),
                arch, null, null, null, language, encoding, subject(), 
                provider(), null, null, protocol, null, data, state, termServ);
    	
    	Observation observationTwo = new Observation(null, "at000", text("observation"),
                arch, null, null, null, language, encoding, subject(), 
                provider(), null, null, protocol, null, data, null, termServ);
    	
    	assertFalse(observationOne.equals(observationTwo));
    }
    
    public void testNotEqualsData() throws Exception
    {
    	ItemStructure protocol = list("list protocol");
        History<ItemStructure> data = event("data");
        History<ItemStructure> state = event("state");
        Archetyped arch = new Archetyped(
                new ArchetypeID("openehr-ehr_rm-observation.physical_examination.v3"),
                "1.1");
        TerminologyService termServ = SimpleTerminologyService.getInstance();    	
    	
        CodePhrase language = new CodePhrase("ISO_639-1", "en");
		CodePhrase encoding = new CodePhrase("IANA_character-sets", "UTF-8");
		
    	Observation observationOne = new Observation(null, "at001", text("observation"),
                arch, null, null, null, language, encoding, subject(), 
                provider(), null, null, protocol, null, data, state, termServ);
    	
    	Observation observationTwo = new Observation(null, "at000", text("observation"),
                arch, null, null, null, language, encoding, subject(), 
                provider(), null, null, protocol, null, eventDiff("data"), state, termServ);
    	
    	assertFalse(observationOne.equals(observationTwo));
    }
    
    
	protected History<ItemStructure> eventDiff(String name) {
		// element = element("element name", "value");
		String[] ITEMS = { "event one" };
		String[] CODES = { "code one" };
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
    
    /* field */
    private ItemList itemList;
    private PointEvent<ItemList> event;
    private History<ItemList> data;
    private History<ItemStructure> state;
    private ItemList protocol;
    private Observation observation;
}