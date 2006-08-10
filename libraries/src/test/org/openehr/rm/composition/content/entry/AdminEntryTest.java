/*
 * AdminEntryTest.java
 * JUnit based test
 *
 * Created on August 8, 2006, 10:59 PM
 */

package org.openehr.rm.composition.content.entry;

import junit.framework.*;
import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.composition.CompositionTestBase;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;
import org.openehr.rm.support.identification.ArchetypeID;

/**
 *
 * @author yinsulim
 */
public class AdminEntryTest extends CompositionTestBase {
    
    public AdminEntryTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
        ItemStructure data = list("list data");
        Archetyped arch = new Archetyped(
                new ArchetypeID("openehr-ehr_rm-adminEntry.XYZ.v2"),
                "1.1");
        adminEntry = new AdminEntry(null, "at0001", text("admin entry"),
                arch, null, null, null, language("en"), language("en"), subject(), provider(), 
                null, null, data, ts);
    }

    protected void tearDown() throws Exception {
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(AdminEntryTest.class);
        
        return suite;
    }

    public void testValidPath() throws Exception {
        String[] validPathList = {
            "/",
            "/data",
        };

        for (String path : validPathList) {
            assertTrue("unexpected invalid path: " + path,
                    adminEntry.validPath(path));
        }

        String[] invalidPathList = {
            "", null, "[]", "/adminEntry",  "/[adminEntry]", // bad root
            "/[adminEntry]/state",                    // bad attribute
        };

        for (String path : invalidPathList) {
            assertFalse("unexpected valid path[" + path + "]",
                    adminEntry.validPath(path));
        }
    }
    
    public void testItemAtPath() throws Exception {
        assertItemAtPath("/", adminEntry, adminEntry);
        assertItemAtPath("/", adminEntry, adminEntry);

        assertItemAtPath("/data", adminEntry, adminEntry.getData());
        
        String[] invalidPathList = {
            "", null, "adminEntry", "/adminEntry", // bad root
            "/[adminEntry]/state"                    // bad attribute
        };

        for (String path : invalidPathList) {
            try {
                adminEntry.itemAtPath(path);
                fail("exception should be thrown on invalid path[" + path
                        + "]");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }
        }
    }
    
    private AdminEntry adminEntry;
}
