/*
 * IntervalEventTest.java
 * JUnit based test
 *
 * Created on July 17, 2006, 2:26 PM
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
//import org.openehr.rm.support.terminology.TerminologyService;
import org.openehr.rm.support.terminology.TestTerminologyService;

/**
 *
 * @author yinsulim
 */
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
