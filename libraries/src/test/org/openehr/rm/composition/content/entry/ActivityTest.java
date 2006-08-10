/*
 * ActivityTest.java
 * JUnit based test
 *
 * Created on August 8, 2006, 3:42 PM
 */

package org.openehr.rm.composition.content.entry;

import junit.framework.*;
import java.util.Set;
import org.openehr.rm.Attribute;
import org.openehr.rm.common.archetyped.*;
import org.openehr.rm.composition.CompositionTestBase;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.datatypes.encapsulated.DvParsable;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;
import org.openehr.rm.support.identification.ObjectID;

/**
 *
 * @author yinsulim
 */
public class ActivityTest extends CompositionTestBase {
    
    public ActivityTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
                //ItemStructure action = list("list action");
        //ItemStructure data = list("list data");
        //ItemStructure profile = list("list profile");
        ItemStructure description = list("list description");
        DvParsable timing = new DvParsable(new CodePhrase("test", "en"), new CodePhrase("test", "en"),
                 1, "timing value", "fomalism", ts);
        activity = new Activity("at0004", text("activity 1"), list("list activity"), timing);
    }

    protected void tearDown() throws Exception {
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(ActivityTest.class);
        
        return suite;
    }

    public void testValidPath() throws Exception {
        String[] validPathList = {
            "/",
            "/description",
        };

        for (String path : validPathList) {
            assertTrue("unexpected invalid path: " + path,
                    activity.validPath(path));
        }

        String[] invalidPathList = {
            "", null, "[]", "/activity",  "/[activity]", // bad root
            "/[activity]/state",                    // bad attribute
        };

        for (String path : invalidPathList) {
            assertFalse("unexpected valid path[" + path + "]",
                    activity.validPath(path));
        }
    }
    
    public void testItemAtPath() throws Exception {
        assertItemAtPath("/", activity, activity);
        assertItemAtPath("/", activity, activity);

        //assertItemAtPath("/data", activity, activity.getData());
        //assertItemAtPath("/[activity]/data", activity,
          //      activity.getData());

        assertItemAtPath("/description", activity, activity.getDescription());
        
        String[] invalidPathList = {
            "", null, "activity", "/activity", // bad root
            "/[activity]/state"                    // bad attribute
        };

        for (String path : invalidPathList) {
            try {
                activity.itemAtPath(path);
                fail("exception should be thrown on invalid path[" + path
                        + "]");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }
        }
    }
    
    private Activity activity;
}
