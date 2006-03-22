/*
 * component:   "openEHR Reference Implementation"
 * description: "Class RMObjectBuilderDataStructuresTest"
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
 * RMObjectBuilderDataStructuresTest
 *
 * @author Rong Chen
 * @version 1.0 
 */
package org.openehr.rm.util;

import org.openehr.rm.RMObject;
import org.openehr.rm.datastructure.history.Event;
import org.openehr.rm.datastructure.history.EventSeries;
import org.openehr.rm.datastructure.history.SingleEvent;
import org.openehr.rm.datastructure.itemstructure.*;
import org.openehr.rm.datastructure.itemstructure.representation.Cluster;
import org.openehr.rm.datastructure.itemstructure.representation.Element;
import org.openehr.rm.datastructure.itemstructure.representation.Item;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datatypes.quantity.datetime.DvDuration;
import org.openehr.rm.datatypes.text.DvText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RMObjectBuilderDataStructuresTest extends RMObjectBuilderTestBase {

    public RMObjectBuilderDataStructuresTest(String test) {
        super(test);
    }

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
        Cluster cluster = cluster();

        values.put("archetypeNodeId", archetypeNodeId);
        values.put("name", name);
        values.put("representation", cluster);
        RMObject obj = builder.construct("ItemList", values);

        assertTrue(obj instanceof ItemList);
        ItemList itemList = (ItemList) obj;
        assertEquals("archetypeNodeId", archetypeNodeId,
                itemList.getArchetypeNodeId());
        assertEquals("name", name, itemList.getName());
        assertEquals("representation", cluster, itemList.getRepresentation());
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
        Cluster cluster = cluster();

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

    public void testBuildEvent() throws Exception {
        Map<String, Object> values = new HashMap<String, Object>();
        ItemStructure item = itemSingle();
        DvDuration offset = DvDuration.getInstance("P1d");
        values.put("item", item);
        values.put("offset", offset);
        RMObject obj = builder.construct("Event", values);

        assertTrue(obj instanceof Event);
        Event event = (Event) obj;
        assertEquals("item", item, event.getItem());
        assertEquals("width", null, event.getWidth());
        assertEquals("mathFunction", null, event.getMathFunction());
        assertEquals("offset", offset, event.getOffset());
    }

    public void testBuildEventSeries() throws Exception {
        Map<String, Object> values = new HashMap<String, Object>();
        String archetypeNodeId = "at0001";
        DvText name = new DvText("test event series", lang, charset, ts);
        List<Event<ItemStructure>> items = new ArrayList<Event<ItemStructure>>();
        items.add(event());
        items.add(event());
        DvDateTime origin = new DvDateTime("2004-10-30 14:22:00");
        values.put("archetypeNodeId", archetypeNodeId);
        values.put("name", name);
        values.put("items", items);
        values.put("origin", origin);
        RMObject obj = builder.construct("EventSeries", values);

        assertTrue(obj instanceof EventSeries);
        EventSeries eventSeries = (EventSeries) obj;
        assertEquals("archetypeNodeId", archetypeNodeId,
                eventSeries.getArchetypeNodeId());
        assertEquals("name", name, eventSeries.getName());
        assertEquals("period", null, eventSeries.getPeriod());
        assertEquals("origin", origin, eventSeries.getOrigin());
        assertEquals("items", items, eventSeries.getItems());
    }

    public void testBuildSingleEvent() throws Exception {
        Map<String, Object> values = new HashMap<String, Object>();
        String archetypeNodeId = "at0001";
        DvText name = new DvText("test event series", lang, charset, ts);
        ItemStructure item = itemSingle();
        DvDateTime origin = new DvDateTime("2004-10-30 14:22:00");
        values.put("archetypeNodeId", archetypeNodeId);
        values.put("name", name);
        values.put("item", item);
        values.put("origin", origin);
        RMObject obj = builder.construct("SingleEvent", values);

        assertTrue(obj instanceof SingleEvent);
        SingleEvent singleEvent = (SingleEvent) obj;
        assertEquals("archetypeNodeId", archetypeNodeId,
                singleEvent.getArchetypeNodeId());
        assertEquals("name", name, singleEvent.getName());
        assertEquals("origin", origin, singleEvent.getOrigin());
        assertEquals("item", item, singleEvent.getItem());
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
 *  The Original Code is RMObjectBuilderDataStructuresTest.java
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