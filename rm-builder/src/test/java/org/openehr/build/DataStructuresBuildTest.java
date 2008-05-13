/*
 * component:   "openEHR Reference Implementation"
 * description: "Class DataStructuresBuildTest"
 * keywords:    "unit test"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/BRANCHES/RM-1.0-update/libraries/src/test/org/openehr/rm/util/RMObjectBuilderDataStructuresTest.java $"
 * revision:    "$LastChangedRevision: 50 $"
 * last_change: "$LastChangedDate: 2006-08-10 13:21:46 +0200 (Thu, 10 Aug 2006) $"
 */
/**
 * RMObjectBuilderDataStructuresTest
 *
 * @author Rong Chen
 * @version 1.0 
 */
package org.openehr.build;

import org.openehr.rm.RMObject;
import org.openehr.rm.datastructure.history.Event;
import org.openehr.rm.datastructure.history.History;
import org.openehr.rm.datastructure.history.IntervalEvent;
import org.openehr.rm.datastructure.history.PointEvent;
import org.openehr.rm.datastructure.itemstructure.*;
import org.openehr.rm.datastructure.itemstructure.representation.Cluster;
import org.openehr.rm.datastructure.itemstructure.representation.Element;
import org.openehr.rm.datastructure.itemstructure.representation.Item;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datatypes.quantity.datetime.DvDuration;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.datatypes.text.DvText;

import java.util.*;

public class DataStructuresBuildTest extends BuildTestBase {
    
    public void testBuildElement() throws Exception {
        Map<String, Object> values = new HashMap<String, Object>();
        String node = "at0001";
        DvText name = new DvText("test element", lang, charset, ts);
        DvText value = new DvText("test value", lang, charset, ts);
        values.put("archetypeNodeId", node);
        values.put("name", name);
        values.put("value", value);
        RMObject obj = builder.construct("Element", values);

        assertTrue(obj instanceof Element);
        Element element = (Element) obj;
        assertEquals("archetypeNodeId", node, element.getArchetypeNodeId());
        assertEquals("name", name, element.getName());
        assertEquals("value", value, element.getValue());
    }

    public void testBuildCluster() throws Exception {
        Map<String, Object> values = new HashMap<String, Object>();
        String archetypeNodeId = "at0001";
        DvText name = new DvText("test element", lang, charset, ts);
        List<Item> items = new ArrayList<Item>();
        for (int i = 0; i < 6; i++) {
            DvText value = new DvText("test value" + i, lang, charset, ts);
            items.add(new Element(archetypeNodeId, name, value));
        }
        values.put("archetypeNodeId", archetypeNodeId);
        values.put("name", name);
        values.put("items", items);
        RMObject obj = builder.construct("Cluster", values);

        assertTrue(obj instanceof Cluster);
        Cluster cluster = (Cluster) obj;
        assertEquals("archetypeNodeId", archetypeNodeId,
                cluster.getArchetypeNodeId());
        assertEquals("name", name, cluster.getName());
        assertEquals("items", items, cluster.getItems());
    }

    public void testBuildItemList() throws Exception {
        Map<String, Object> values = new HashMap<String, Object>();
        String archetypeNodeId = "at0001";
        DvText name = new DvText("test item list", lang, charset, ts);
        List<Element> items = new ArrayList<Element>();

        values.put("archetypeNodeId", archetypeNodeId);
        values.put("name", name);
        values.put("items", items);
        RMObject obj = builder.construct("ItemList", values);

        assertTrue(obj instanceof ItemList);
        ItemList itemList = (ItemList) obj;
        assertEquals("archetypeNodeId", archetypeNodeId,
                itemList.getArchetypeNodeId());
        assertEquals("name", name, itemList.getName());
        assertEquals("items", items, itemList.getItems());
    }

    public void testBuildItemSingle() throws Exception {
        Map<String, Object> values = new HashMap<String, Object>();

        Element element = element();
        String archetypeNodeId = "at0001";
        DvText name = new DvText("test item single", lang, charset, ts);
        values.put("archetypeNodeId", archetypeNodeId);
        values.put("name", name);
        values.put("representation", element);
        RMObject obj = builder.construct("ItemSingle", values);

        assertTrue(obj instanceof ItemSingle);
        ItemSingle itemSingle = (ItemSingle) obj;
        assertEquals("archetypeNodeId", archetypeNodeId,
                itemSingle.getArchetypeNodeId());
        assertEquals("name", name, itemSingle.getName());
        assertEquals("representation", element, itemSingle.getRepresentation());

        // test with clas
        builder.construct("ITEM_SINGLE", values);
    }

    public void testBuildItemTable() throws Exception {
        Map<String, Object> values = new HashMap<String, Object>();
        String archetypeNodeId = "at0001";
        DvText name = new DvText("test item talbe", lang, charset, ts);
        Cluster cluster = clusterTable();

        values.put("archetypeNodeId", archetypeNodeId);
        values.put("name", name);
        values.put("representation", cluster);
        RMObject obj = builder.construct("ItemTable", values);

        assertTrue(obj instanceof ItemTable);
        ItemTable itemTable = (ItemTable) obj;
        assertEquals("archetypeNodeId", archetypeNodeId, itemTable.getArchetypeNodeId());
        assertEquals("name", name, itemTable.getName());
        assertEquals("representation", cluster, itemTable.getRepresentation());
    }

    public void testBuildItemTree() throws Exception {
        Map<String, Object> values = new HashMap<String, Object>();
        String archetypeNodeId = "at0001";
        DvText name = new DvText("test item tree", lang, charset, ts);
        Cluster cluster = cluster();

        values.put("archetypeNodeId", archetypeNodeId);
        values.put("name", name);
        values.put("representation", cluster);
        RMObject obj = builder.construct("ItemTree", values);

        assertTrue(obj instanceof ItemTree);
        ItemTree itemTree = (ItemTree) obj;
        assertEquals("archetypeNodeId", archetypeNodeId,
                itemTree.getArchetypeNodeId());
        assertEquals("name", name, itemTree.getName());
        assertEquals("representation", cluster, itemTree.getRepresentation());
    }

    public void testBuildPointEvent() throws Exception {
        Map<String, Object> values = new HashMap<String, Object>();
        String archetypeNodeId = "at0001";
        DvText name = new DvText("test point event", lang, charset, ts);
        ItemStructure data = itemSingle();
        DvDateTime time = new DvDateTime("2006-07-12T12:00:03");
        values.put("archetypeNodeId", archetypeNodeId);
        values.put("name", name);
        values.put("data", data);
        values.put("time", time);
        RMObject obj = builder.construct("PointEvent", values);
        assertTrue(obj instanceof PointEvent);
        PointEvent event = (PointEvent) obj;
        assertEquals("archetypeNodeId", archetypeNodeId, event.getArchetypeNodeId());
        assertEquals("name", name, event.getName());
        assertEquals("data", data, event.getData());
        assertEquals("time", time, event.getTime());
    }

    public void testBuildIntervalEvent() throws Exception {
        Map<String, Object> values = new HashMap<String, Object>();
        String archetypeNodeId = "at0001";
        DvText name = new DvText("test event series", lang, charset, ts);
        ItemStructure data = itemList();
        DvDateTime time = new DvDateTime("2004-10-30T14:22:00");
        DvDuration width = DvDuration.getInstance("P1d");
        CodePhrase mathMean = new CodePhrase("openehr", "146");
        DvCodedText mathFunction = new DvCodedText("mean", lang, charset, 
        		mathMean, ts);
        values.put("archetypeNodeId", archetypeNodeId);
        values.put("name", name);
        values.put("time", time);
        values.put("data", data);
        values.put("width", width);
        values.put("mathFunction", mathFunction);
        RMObject obj = builder.construct("IntervalEvent", values);

        assertTrue(obj instanceof IntervalEvent);
        IntervalEvent intEvent = (IntervalEvent) obj;
        assertEquals("archetypeNodeId", archetypeNodeId,
                intEvent.getArchetypeNodeId());
        assertEquals("name", name, intEvent.getName());
        assertEquals("data", data, intEvent.getData());
        assertEquals("time", time, intEvent.getTime());
        assertEquals("width", width, intEvent.getWidth());
        assertEquals("mathFunction", mathFunction, intEvent.getMathFunction());
        assertEquals("intervalStartTime", new DvDateTime("2004-10-29T14:22:00"), 
                intEvent.intervalStartTime());
    }

    public void testBuildHistoy() throws Exception {
        Map<String, Object> values = new HashMap<String, Object>();
        String archetypeNodeId = "at0001";
        DvText name = new DvText("test history", lang, charset, ts);
        DvDateTime origin = new DvDateTime("2004-10-30T14:22:00");
        List<Event<ItemSingle>> events = new ArrayList<Event<ItemSingle>>();
        events.add(new PointEvent<ItemSingle>(null, "at0003", text("point event"), null, 
               null, null, null, new DvDateTime("2004-10-31T08:00:00"), itemSingle(), null));
        values.put("archetypeNodeId", archetypeNodeId);
        values.put("name", name);
        values.put("origin", origin);
        values.put("events", events);
        RMObject obj = builder.construct("History", values);

        assertTrue(obj instanceof History);
        History history = (History) obj;
        assertEquals("archetypeNodeId", archetypeNodeId,
                history.getArchetypeNodeId());
        assertEquals("name", name, history.getName());
        assertEquals("origin", origin, history.getOrigin());
        assertEquals("eventd", events, history.getEvents());
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
 *  The Original Code is DataStructuresBuildTest.java
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