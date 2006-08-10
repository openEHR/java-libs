/*
 * ActionTest.java
 * JUnit based test
 *
 * Created on August 8, 2006, 5:49 PM
 */

package org.openehr.rm.composition.content.entry;

import junit.framework.*;
import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.composition.CompositionTestBase;
import org.openehr.rm.composition.content.entry.ISMTransition;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.support.identification.ArchetypeID;
import org.openehr.rm.support.terminology.TestCodeSetAccess;

/**
 *
 * @author yinsulim
 */
public class ActionTest extends CompositionTestBase {
    
    public ActionTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
        ItemStructure description = list("list description");
        ItemStructure protocol = list("list protocol");
        //DvState state = TestCodeSetAccess.DRAFT;
        Archetyped arch = new Archetyped(
                new ArchetypeID("openehr-ehr_rm-action.XYZ.v2"),
                "1.1");
        ISMTransition ismT = new ISMTransition(TestCodeSetAccess.ISM_ACTIVE, null, null, ts);
        action = new Action(null, "at0001", text("action"),
                arch, null, null, null, language("en"), language("en"), subject(), provider(), 
                null, null, protocol, null, new DvDateTime(), description, ismT, null, ts);
    }

    protected void tearDown() throws Exception {
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(ActionTest.class);
        
        return suite;
    }

    public void testValidPath() throws Exception {
        String[] validPathList = {
            "/",
            "/protocol",
            "/description",
            "/protocol/list protocol",
            "/description/list description",
        };

        for (String path : validPathList) {
            assertTrue("unexpected invalid path: " + path,
                    action.validPath(path));
        }

        String[] invalidPathList = {
            "", null, "[action]", "/action",  "/[action]", // bad root
            "/[action]/state",                    // bad attribute
        };

        for (String path : invalidPathList) {
            assertFalse("unexpected valid path[" + path + "]",
                    action.validPath(path));
        }
    }

    public void testItemAtPath() throws Exception {
        assertItemAtPath("/", action, action);
        assertItemAtPath("/", action, action);

        assertItemAtPath("/protocol", action, action.getProtocol());

        assertItemAtPath("/protocol/list protocol", action, 
                action.getProtocol());
        assertItemAtPath("/description", action, action.getDescription());
        assertItemAtPath("/description/list description", action, 
                action.getDescription());
       
        String[] invalidPathList = {
            "", null, "action", "/action", // bad root
            "/[action]/state"                    // bad attribute
        };

        for (String path : invalidPathList) {
            try {
                action.itemAtPath(path);
                fail("exception should be thrown on invalid path[" + path
                        + "]");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }
        }
    }

    /* fields */
    private static Action action;
    
}
