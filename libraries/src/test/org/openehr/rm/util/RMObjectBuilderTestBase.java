/*
 * component:   "openEHR Reference Implementation"
 * description: "Class RMObjectBuilderTestBase"
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
 * RMObjectBuilderTestBase
 *
 * @author Rong Chen
 * @version 1.0 
 */
package org.openehr.rm.util;

import junit.framework.TestCase;
import org.openehr.rm.common.generic.PartyIdentified;
import org.openehr.rm.datastructure.history.Event;
import org.openehr.rm.datastructure.history.History;
import org.openehr.rm.datastructure.history.PointEvent;
import org.openehr.rm.datastructure.itemstructure.ItemList;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;
import org.openehr.rm.datatypes.quantity.datetime.DvDuration;
import org.openehr.rm.support.identification.HierarchicalObjectID;
import org.openehr.rm.support.identification.ObjectReference;
import org.openehr.rm.support.identification.PartyReference;
import org.openehr.rm.datastructure.itemstructure.ItemSingle;
import org.openehr.rm.datastructure.itemstructure.representation.Cluster;
import org.openehr.rm.datastructure.itemstructure.representation.Element;
import org.openehr.rm.datastructure.itemstructure.representation.Item;
import org.openehr.rm.datatypes.quantity.DvInterval;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.support.measurement.TestMeasurementService;
import org.openehr.rm.support.terminology.TerminologyService;
import org.openehr.rm.support.terminology.TestCodeSetAccess;
import org.openehr.rm.support.terminology.TestTerminologyService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openehr.rm.common.generic.PartySelf;
import org.openehr.rm.datatypes.text.TestCodePhrase;
import org.openehr.rm.support.identification.TestTerminologyID;

public class RMObjectBuilderTestBase extends TestCase {

    public RMObjectBuilderTestBase(String test) {
        super(test);
    }

    /**
     * The fixture set up called before every test method.
     */
    protected void setUp() throws Exception {
        Map<SystemValue,Object> values = new HashMap<SystemValue,Object>();
        values.put(SystemValue.LANGUAGE, lang);
        values.put(SystemValue.CHARSET, charset);
        values.put(SystemValue.TERMINOLOGY_SERVICE, ts);
        values.put(SystemValue.MEASUREMENT_SERVICE,
                TestMeasurementService.getInstance());
        builder = new RMObjectBuilder(values);
    }

    /**
     * The fixture clean up called after every test method.
     */
    protected void tearDown() throws Exception {
        builder = null;
    }

    // test cluster not exactly right for table or tree
    protected Cluster cluster() throws Exception {
        DvText name = new DvText("test element", lang, charset, ts);
        List<Item> items = new ArrayList<Item>();
        for (int i = 0; i < 6; i++) {
            DvText value = new DvText("test value" + i, lang, charset, ts);
            items.add(new Element("at0001", name, value));
        }
        name = new DvText("test cluster", lang, charset, ts);
        return new Cluster("at0002", name, items);
    }

    protected Cluster clusterTable() throws Exception {
        List<Item> items = new ArrayList<Item>();
        DvText name = new DvText("test clusterTable", lang, charset, ts);
        for (int i = 0; i < 3; i++) {
            items.add(cluster());
        }
        return new Cluster("at0004", name, items);
    }
    // test element
    protected Element element() throws Exception {
        DvText name = new DvText("test element", lang, charset, ts);
        DvText value = new DvText("test value", lang, charset, ts);
        return new Element("at0001", name, value);
    }

    protected ItemSingle itemSingle() throws Exception {
        return itemSingle("test item single");
    }

    protected ItemSingle itemSingle(String value) throws Exception {
        DvText name = new DvText(value, lang, charset, ts);
        return new ItemSingle("at0001", name, element());
    }

    protected ItemList itemList() throws Exception {
        return new ItemList(null, "at001", text("test itemList"), null, null, null, 
                null, cluster());
    }
    
    // test event
    /*protected Event<ItemStructure> event() throws Exception {
        ItemStructure item = itemSingle();
        DvDuration offset = DvDuration.getInstance("P1d");
        return new Event<ItemStructure>(item, offset);
    }*/
    
/*    protected History<? extends ItemStructure> event() throws Exception { 
        List<Event<ItemSingle>> items = new ArrayList<Event<ItemSingle>>();
        items.add(pointEvent());
        return new History<ItemSingle>(null, "at0002", text("history"),
                null, null, null, null, new DvDateTime("2006-07-12T09:22:00"), 
                items, DvDuration.getInstance("PT1h"), 
                DvDuration.getInstance("PT3h"), null);
    }

    protected PointEvent<ItemSingle> pointEvent() throws Exception {
        return new PointEvent<ItemSingle>(null, "at0003", text("point event"),  
                null, null, null, null, new DvDateTime("2006-07-12T08:00:00"), 
                itemSingle(), null);
    }
*/    
    // test subject
    protected PartySelf subject() throws Exception {
        PartyReference party = new PartyReference(
                new HierarchicalObjectID("1.2.4.5.6.12.1"), ObjectReference.Type.PARTY);
        return new PartySelf(party);
    }
    
    // test provider
    protected PartyIdentified provider() throws Exception {
        PartyReference performer = new PartyReference(
                new HierarchicalObjectID("1.3.3.1"), ObjectReference.Type.ORGANISATION);
        return new PartyIdentified(performer, "provider's name", null);
    }
    
    protected HierarchicalObjectID hid(String value) throws Exception {
        return new HierarchicalObjectID(value);
    }

    // test  territory
    public CodePhrase territory() {
        return new CodePhrase("test", "se");
    }

    protected DvInterval<DvDateTime> time() throws Exception {
        return new DvInterval<DvDateTime>(
                new DvDateTime("2004-10-29 22:37:00"),
                new DvDateTime("2004-10-29 23:10:00"));
    }

    protected DvText text(String value) throws Exception {
        return new DvText(value, lang, charset, ts);
    }

    protected DvCodedText codedText(String value, String code) throws Exception {
        CodePhrase codePhrase =
                new CodePhrase(TestTerminologyID.SNOMEDCT, code);
        return new DvCodedText(value, TestCodePhrase.ENGLISH,
                TestCodePhrase.LATIN_1, codePhrase,
                TestTerminologyService.getInstance());
         
        //return new DvCodedText(value, lang, charset,
          //       new CodePhrase("test", "se"), ts);
    }
    
    /* field */
    protected RMObjectBuilder builder;
    protected static CodePhrase lang = TestCodeSetAccess.ENGLISH;
    protected static CodePhrase charset = TestCodeSetAccess.LATIN_1;
    protected static TerminologyService ts = TestTerminologyService.getInstance();
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
 *  The Original Code is RMObjectBuilderTestBase.java
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