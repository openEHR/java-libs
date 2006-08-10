/*
 * EHRStatusTest.java
 * JUnit based test
 *
 * Created on August 9, 2006, 12:18 PM
 */

package org.openehr.rm.ehr;

import junit.framework.*;
import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.composition.CompositionTestBase;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;
import org.openehr.rm.support.identification.ArchetypeID;

/**
 *
 * @author yinsulim
 */
public class EHRStatusTest extends CompositionTestBase {
    
    public EHRStatusTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
        ItemStructure otherDetails = list("list otherDetails");
        Archetyped arch = new Archetyped(
                new ArchetypeID("openehr-ehr_rm-ehrstatus.XYZ.v2"),
                "1.1");
        ehrStatus = new EHRStatus(null, "at0001", text("EHR Status"),
                arch, null, null, null, subject(), true, true, otherDetails);
    }

    protected void tearDown() throws Exception {
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(EHRStatusTest.class);
        
        return suite;
    }

    public void testValidPath() throws Exception {
        String[] validPathList = {
            "/",
            "/otherDetails",
            "/otherDetails/list otherDetails",
        };

        for (String path : validPathList) {
            assertTrue("unexpected invalid path: " + path,
                    ehrStatus.validPath(path));
        }

        String[] invalidPathList = {
            "", null, "[ehrStatus]", "/ehrStatus",  "/[ehrStatus]", // bad root
            "/[ehrStatus]/state",                    // bad attribute
        };

        for (String path : invalidPathList) {
            assertFalse("unexpected valid path[" + path + "]",
                    ehrStatus.validPath(path));
        }
    }

    public void testItemAtPath() throws Exception {
        assertItemAtPath("/", ehrStatus, ehrStatus);
        assertItemAtPath("/", ehrStatus, ehrStatus);

        assertItemAtPath("/otherDetails", ehrStatus, ehrStatus.getOtherDetails());
        assertItemAtPath("/otherDetails/list otherDetails", ehrStatus, 
                ehrStatus.getOtherDetails());
       
        String[] invalidPathList = {
            "", null, "ehrStatus", "/ehrStatus", // bad root
            "/[ehrStatus]/state"                    // bad attribute
        };

        for (String path : invalidPathList) {
            try {
                ehrStatus.itemAtPath(path);
                fail("exception should be thrown on invalid path[" + path
                        + "]");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }
        }
    }
    
    private EHRStatus ehrStatus;
}
