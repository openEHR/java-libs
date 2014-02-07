/*
 * component:   "openEHR Reference Implementation"
 * description: "Class IntervalEventTest"
 * keywords:    "unit test"
 *
 * author:      "Yin Su Lim <y.lim@chime.ucl.ac.uk>"
 * support:     "CHIME, UCL"
 * copyright:   "Copyright (c) 2006 UCL, UK"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/BRANCHES/RM-1.0-update/libraries/src/test/org/openehr/rm/datastructure/history/IntervalEventTest.java $"
 * revision:    "$LastChangedRevision: 50 $"
 * last_change: "$LastChangedDate: 2006-08-10 12:21:46 +0100 (Thu, 10 Aug 2006) $"
 */

/**
 * IntervalEventTest
 *
 * @author Yin Su Lim
 * @version 1.0 
 */
package org.openehr.rm.datastructure.history;

import java.util.ArrayList;
import java.util.List;
import junit.framework.*;
import java.util.Set;
import org.openehr.rm.Attribute;
import org.openehr.rm.common.archetyped.*;
import org.openehr.rm.datastructure.DataStructureTestBase;
import org.openehr.rm.datastructure.itemstructure.ItemSingle;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;
import org.openehr.rm.datastructure.itemstructure.representation.Element;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datatypes.quantity.datetime.DvDuration;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.support.identification.ObjectID;
import org.openehr.rm.support.terminology.TestTerminologyService;

public class IntervalEventTest extends DataStructureTestBase {
    
    public IntervalEventTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
        Element element = element("element name", "element value");
        item = new ItemSingle(null, "at0001", text("interval event item"),
                null, null, null, null, element);
        ie = new IntervalEvent<ItemSingle>(null, "at0002", text("point event"),
            null, null, null, null, new DvDateTime("2004-12-07T10:29:00"), item, null, 
            DvDuration.getInstance("PT1h"), codedText("mean", "meanCode"),
            0, TestTerminologyService.getInstance());
        ItemSingle summary = new ItemSingle(null, "at0001", text("summary item"),
                null, null, null, null, element("summary element", "summary content"));
        h = new History<ItemSingle>(null, "at0002", text("history"),
                null, null, null, null, new DvDateTime("2004-12-06T13:00:00"), null, DvDuration.getInstance("PT30m"),
                DvDuration.getInstance("PT3h"), summary);
    }

    protected void tearDown() throws Exception {
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(IntervalEventTest.class);
        
        return suite;
    }


    public void testIntervalStartTime() {
        assertEquals(null, ie.getParent());
        assertEquals(new DvDateTime("2004-12-07T09:29:00"), ie.intervalStartTime());
        ie = new IntervalEvent<ItemSingle>(null, "at0002", text("point event"),
            null, null, null, h, new DvDateTime("2004-12-07T10:29:00"), item, null, 
            DvDuration.getInstance("PT1h"), codedText("mean", "meanCode"),
            0, TestTerminologyService.getInstance());
        assertEquals(h, ie.getParent());
    }
    
    ItemSingle item;
    private History<ItemSingle> h;
    private IntervalEvent<ItemSingle> ie;
    private static final String NAME = "interval event"; 
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
 *  The Original Code is IntervalEventTest.java
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
