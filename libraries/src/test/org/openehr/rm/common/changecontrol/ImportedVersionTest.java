/*
 * ImportedVersionTest.java
 * JUnit based test
 *
 * Created on July 19, 2006, 6:17 PM
 */

package org.openehr.rm.common.changecontrol;

import junit.framework.*;
import java.util.List;
import org.openehr.rm.common.generic.Attestation;
import org.openehr.rm.common.generic.AuditDetails;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.support.identification.ObjectReference;
import org.openehr.rm.support.identification.ObjectVersionID;
import org.openehr.rm.support.terminology.TerminologyService;
import org.openehr.rm.support.terminology.TestTerminologyService;

/**
 *
 * @author yinsulim
 */
public class ImportedVersionTest extends ChangeControlTestBase {
    
    public ImportedVersionTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(ImportedVersionTest.class);
        
        return suite;
    }

    public void test() {
        OriginalVersion<String> ov = new OriginalVersion<String>(
            new ObjectVersionID("1.4.4.5::1.2.840.114.1.2.2::1"), null, "a name", 
            lifeCycleState("complete"), audit("1-4-3-5-2", "comitter's name", "changeTypeCode",
            "2006-07-01T13:22:55"), 
            contribution("1.4.4.5::1.2.840.114.1.2.2::2", "path/morePath"), null, 
            null, null, false, TestTerminologyService.getInstance());
        ImportedVersion<String> iv = new ImportedVersion<String>(ov, 
            audit("adminc.nhs.uk", "comitter's name", "changeTypeCode", "2006-07-01T15:00:09"),
            contribution("1.4.4.15::1.2.840.114.1.2.2::1", "path/morePath"), 
            null);
        assertEquals("1.4.4.5::1.2.840.114.1.2.2::1", iv.getUid().toString());
        assertEquals(null, iv.getPrecedingVersionID());
        assertEquals(lifeCycleState("complete"), iv.getLifeCycleState());
        assertEquals("a name", iv.getData());
        assertEquals(ov, iv.getOriginalVersion());
        
    }
    
}
