/*
 * PointEventTest.java
 * JUnit based test
 *
 * Created on July 14, 2006, 12:34 PM
 */

package org.openehr.rm.datastructure.history;

import junit.framework.*;
import java.util.Set;
import org.openehr.rm.Attribute;
import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.common.archetyped.FeederAudit;
import org.openehr.rm.common.archetyped.Link;
import org.openehr.rm.datastructure.DataStructureTestBase;
import org.openehr.rm.datastructure.itemstructure.ItemSingle;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;
import org.openehr.rm.datastructure.itemstructure.representation.Element;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datatypes.quantity.datetime.DvDuration;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.support.identification.ObjectID;

/**
 *
 * @author yinsulim
 */
public class PointEventTest extends DataStructureTestBase {
    
    public PointEventTest(String testName) {
        super(testName);
    }

    protected void tearDown() throws Exception {
        element = null;
        pointEvent = null;
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(PointEventTest.class);
        
        return suite;
    }
    
    /**
     * The fixture set up called before every test method.
     */
    protected void setUp() throws Exception {
        element = element("element name", "value");
        item = new ItemSingle(null, "at0001", text("point event item"),
                null, null, null, null, element);
        pointEvent = new PointEvent<ItemSingle>(null, "at0002", text("point event"),
                null, null, null, null, new DvDateTime(TIME), item, null);
        ItemSingle summary = new ItemSingle(null, "at0001", text("summary item"),
                null, null, null, null, element("summary element", "summary content"));
        h = new History<ItemSingle>(null, "at0002", text("history"),
                null, null, null, null, new DvDateTime("2004-12-06T13:00:00"), null, DvDuration.getInstance("PT30m"),
                DvDuration.getInstance("PT3h"), summary);
    }
    
    public void testPointEvent() {
        assertEquals(null, pointEvent.offset());
        assertEquals(null, pointEvent.getParent());
        pointEvent = new PointEvent<ItemSingle>(null, "at0002", text("point event"),
                null, null, null, h, new DvDateTime(TIME), item, null);
        assertEquals(DvDuration.getInstance("PT10m"), pointEvent.offset());
        assertEquals(h, pointEvent.getParent());
    }
    
    private History<ItemSingle> h;
    private static final String TIME = "2004-12-06T13:10:00";
    private PointEvent<ItemSingle> pointEvent;
    private ItemSingle item;
    private Element element;
}
