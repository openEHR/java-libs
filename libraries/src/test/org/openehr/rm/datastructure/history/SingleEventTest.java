/*
 * component:   "openEHR Reference Implementation"
 * description: "Class SingleEventTest"
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
 * SingleEventTest
 *
 * @author Rong Chen
 * @version 1.0 
 */
package org.openehr.rm.datastructure.history;

import org.openehr.rm.datastructure.DataStructureTestBase;
import org.openehr.rm.datastructure.itemstructure.ItemSingle;
import org.openehr.rm.datastructure.itemstructure.representation.Element;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SingleEventTest extends DataStructureTestBase {

    public SingleEventTest(String test) {
        super(test);
    }

    /**
     * The fixture set up called before every test method.
     */
    protected void setUp() throws Exception {
        element = element("element name", "value");
        ItemSingle item = new ItemSingle(null, "at0001", text(ELEMENT_NAME),
                null, null, null, element);
        singleEvent = new SingleEvent<ItemSingle>("at0002",
                text(EVENT_NAME), new DvDateTime(TIME), item);
    }

    /**
     * The fixture clean up called after every test method.
     */
    protected void tearDown() throws Exception {
        element = null;
        singleEvent = null;
    }

    public void testValidPath() throws Exception {
        List<String> pathList = new ArrayList<String>();
        pathList.addAll(Arrays.asList(VALID_PATHS_WHOLE));
        pathList.addAll(Arrays.asList(VALID_PATHS_ITEM));
        for (String path : pathList) {
            assertTrue("expected valid path: " + path,
                    singleEvent.validPath(path));
        }
        String[] bad_paths = {
            null,
            sep + "unknown event",
            sep + EVENT_NAME + sep + History.ORIGIN_IS + "2000-10-16 13:00:00",
            sep + EVENT_NAME + sep + "unknown item",
            sep + History.ORIGIN_IS + TIME + sep + "unknown item"
        };
        for (String path : bad_paths) {
            assertFalse("expected invalid path: " + path,
                    singleEvent.validPath(path));
        }
    }

    public void testItemAtPath() throws Exception {

        for(String path : VALID_PATHS_WHOLE) {
            assertEquals("whole history expected", singleEvent,
                    singleEvent.itemAtPath(path));
        }

        for(String path : VALID_PATHS_ITEM) {
            assertEquals("element expected", element,
                    singleEvent.itemAtPath(path));
        }
    }

    private static final String EVENT_NAME = "single event";
    private static final String ELEMENT_NAME = "element name";
    private static final String TIME = "2004-12-06 13:00:00";

    private static final String[] VALID_PATHS_WHOLE = {
        sep + EVENT_NAME,
        sep + History.ORIGIN_IS + TIME
    };
    private static final String[] VALID_PATHS_ITEM = {
        sep + EVENT_NAME + sep + ELEMENT_NAME,
        sep + History.ORIGIN_IS + TIME + sep + ELEMENT_NAME
    };

    private SingleEvent singleEvent;
    private Element element;
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
 *  The Original Code is SingleEventTest.java
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