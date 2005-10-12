/*
 * component:   "openEHR Reference Implementation"
 * description: "Class EventSeriesTest"
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
 * EventSeriesTest
 *
 * @author Rong Chen
 * @version 1.0 
 */
package org.openehr.rm.datastructure.history;

import org.openehr.rm.datastructure.DataStructureTestBase;
import org.openehr.rm.datastructure.itemstructure.ItemSingle;
import org.openehr.rm.datastructure.itemstructure.representation.Element;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datatypes.quantity.datetime.DvDuration;
import org.openehr.rm.datatypes.text.DvCodedText;

import java.util.ArrayList;
import java.util.List;

public class EventSeriesTest extends DataStructureTestBase {

    public EventSeriesTest(String test) {
        super(test);
    }

    /**
     * The fixture set up called before every test method.
     */
    protected void setUp() throws Exception {
        eventSeries = init();
    }

    private EventSeries<ItemSingle> init() {
        List<Event<ItemSingle>> items = new ArrayList<Event<ItemSingle>>();
        for (int i = 0; i < ITEMS.length; i++) {
            Element element = element("element " + i, CODES[i]);
            ItemSingle item = new ItemSingle(null, "at0001", text(ITEMS[i]),
                    null, null, null,  element);
            DvDuration offset = DvDuration.getInstance("P1h");
            items.add(new Event<ItemSingle>(item, offset));
        }
        return new EventSeries<ItemSingle>(null, "at0002", text(NAME),
                null, null, null, new DvDateTime(TIME), items, null);
    }

    /**
     * The fixture clean up called after every test method.
     */
    protected void tearDown() throws Exception {
        eventSeries = null;
    }

    public void testGetItems() throws Exception {
        List<Event<ItemSingle>> events = eventSeries.getItems();
        DvDuration duration = DvDuration.getInstance("P1h");
        for(int i = 0; i < ITEMS.length; i++) {
            ItemSingle item = (ItemSingle) events.get(i).getItem();
            DvCodedText text = (DvCodedText) item.item().getValue();
            assertEquals(ITEMS[i], item.getName().getValue());
            assertEquals(CODES[i], text.getDefiningCode().getCodeString());
            assertEquals(duration, events.get(i).getOffset());
        }
    }

    /* static fields */
    private static final String NAME = "event series";
    private static final String[] ITEMS = {
        "event one", "event two", "event three"
    };
    private static final String[] CODES = {
        "code one", "code two", "code three"
    };
    private static final String TIME = "2004-12-07 10:29:00";

    /* member */
    private EventSeries<ItemSingle> eventSeries;
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
 *  The Original Code is EventSeriesTest.java
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