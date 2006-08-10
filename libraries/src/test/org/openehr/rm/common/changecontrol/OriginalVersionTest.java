/*
 * OriginalVersionTest.java
 * JUnit based test
 *
 * Created on July 19, 2006, 6:17 PM
 */

package org.openehr.rm.common.changecontrol;

import java.util.HashSet;
import junit.framework.*;
import java.util.List;
import java.util.Set;
import org.openehr.rm.common.generic.Attestation;
import org.openehr.rm.common.generic.AuditDetails;
import org.openehr.rm.common.generic.PartyIdentified;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.datatypes.text.TestCodePhrase;
import org.openehr.rm.support.identification.HierarchicalObjectID;
import org.openehr.rm.support.identification.LocatableReference;
import org.openehr.rm.support.identification.ObjectReference;
import org.openehr.rm.support.identification.ObjectVersionID;
import org.openehr.rm.support.identification.PartyReference;
import org.openehr.rm.support.identification.TestTerminologyID;
import org.openehr.rm.support.terminology.TerminologyService;
import org.openehr.rm.support.terminology.TestTerminologyService;

/**
 *
 * @author yinsulim
 */
public class OriginalVersionTest extends TestCase {
    
    public OriginalVersionTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(OriginalVersionTest.class);
        
        return suite;
    }

    public void testConstructors() {
 
        ObjectVersionID uid = new ObjectVersionID("1.4.4.5::1.2.840.114.1.2.2::1");
        CodePhrase codePhrase =
                new CodePhrase(TestTerminologyID.SNOMEDCT, "revisionCode");
        DvCodedText codedText = new DvCodedText("complete", TestCodePhrase.ENGLISH,
                TestCodePhrase.LATIN_1, codePhrase,
                TestTerminologyService.getInstance());
        PartyIdentified pi = new PartyIdentified(new PartyReference(
                new HierarchicalObjectID("1-2-3-4-5"), 
                ObjectReference.Type.PARTY), "committer name", null);
        AuditDetails audit1 = new AuditDetails("12.3.4.5", pi, 
                new DvDateTime("2006-05-01T10:10:00"), codedText, null,
                TestTerminologyService.getInstance());
        ObjectReference lr = new LocatableReference(new ObjectVersionID(
                "1.23.51.66::1.2.840.114.1.2.2::2"), ObjectReference.Namespace.LOCAL,
                ObjectReference.Type.CONTRIBUTION, "");
        OriginalVersion<String> ov = new OriginalVersion<String>(uid, null, "A Party Info", 
                codedText, audit1, lr, null, null, null, false, 
                TestTerminologyService.getInstance());
        Set<ObjectVersionID> otherUids = new HashSet<ObjectVersionID>();
        otherUids.add(new ObjectVersionID("1.4.14.5::1.2.840.114.1.2.2::4.2.2"));
        OriginalVersion<String> ov2 = new OriginalVersion<String>(uid, null, "A Party Info", 
                codedText, audit1, lr, null, otherUids, null, true, 
                TestTerminologyService.getInstance());
        assertContructorError(uid, null, "A Party Info", 
                codedText, audit1, lr, null, otherUids, null, false, 
                TestTerminologyService.getInstance());
        assertContructorError(uid, null, "A Party Info", 
                codedText, audit1, lr, null, null, null, true, 
                TestTerminologyService.getInstance());
        
    }
    
    private void assertContructorError(ObjectVersionID uid, ObjectVersionID pVersionID,
            String data, DvCodedText codedText, AuditDetails audit, 
            ObjectReference contribution, String signature, 
            Set<ObjectVersionID> otherUids, List<Attestation> attestations,
            boolean isMerged, TerminologyService terminologyService) {

        try {
            OriginalVersion<String> ov = new OriginalVersion<String>(uid, pVersionID, data, 
                codedText, audit, contribution, signature, otherUids, attestations, 
                isMerged, TestTerminologyService.getInstance());
            fail("should throw exception, breach of isMerged validity");
        } catch (IllegalArgumentException iae) {
            
        }
    }



    
}
