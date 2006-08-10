/*
 * ChangeControlTestBase.java
 * JUnit based test
 *
 * Created on July 20, 2006, 12:31 PM
 */

package org.openehr.rm.common.changecontrol;

import java.util.HashSet;
import java.util.Set;
import junit.framework.*;
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
import org.openehr.rm.support.terminology.TestTerminologyService;

/**
 *
 * @author yinsulim
 */
public class ChangeControlTestBase extends TestCase {
    
    public ChangeControlTestBase(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }
    
    
    protected DvCodedText lifeCycleState(String state) {
        CodePhrase codePhrase =
                new CodePhrase(TestTerminologyID.SNOMEDCT, "revisionCode");
        return new DvCodedText(state, TestCodePhrase.ENGLISH,
                TestCodePhrase.LATIN_1, codePhrase,
                TestTerminologyService.getInstance());
    }
    
    protected AuditDetails audit(String id, String name, String changeType, String dt) {
        PartyIdentified pi = new PartyIdentified(new PartyReference(
                new HierarchicalObjectID(id), 
                ObjectReference.Type.PARTY), name, null);
        CodePhrase codePhrase =
                new CodePhrase(TestTerminologyID.SNOMEDCT, "revisionCode");
        DvCodedText codedText = new DvCodedText(changeType, TestCodePhrase.ENGLISH,
                TestCodePhrase.LATIN_1, codePhrase,
                TestTerminologyService.getInstance());
        return new AuditDetails("12.3.4.5", pi, 
                new DvDateTime(dt), codedText, null,
                TestTerminologyService.getInstance());
    }
    
    protected LocatableReference contribution(String objectVersionID, String path) {
        return new LocatableReference(new ObjectVersionID(
                objectVersionID), ObjectReference.Namespace.LOCAL,
                ObjectReference.Type.CONTRIBUTION, path);
    }
    
    protected OriginalVersion<String> originalVersion(String data, boolean isMerged,
            String uidStr, String time) {
        ObjectVersionID uid = new ObjectVersionID(uidStr);
        CodePhrase codePhrase =
                new CodePhrase(TestTerminologyID.SNOMEDCT, "revisionCode");
        DvCodedText codedText = new DvCodedText("complete", TestCodePhrase.ENGLISH,
                TestCodePhrase.LATIN_1, codePhrase,
                TestTerminologyService.getInstance());
        PartyIdentified pi = new PartyIdentified(new PartyReference(
                new HierarchicalObjectID("1-2-3-4-5"), 
                ObjectReference.Type.PARTY), "committer name", null);
        AuditDetails audit1 = new AuditDetails("12.3.4.5", pi, 
                new DvDateTime(time), codedText, null,
                TestTerminologyService.getInstance());
        ObjectReference lr = new LocatableReference(new ObjectVersionID(
                "1.23.51.66::1.2.840.114.1.2.2::2"), ObjectReference.Namespace.LOCAL,
                ObjectReference.Type.CONTRIBUTION, "");
        
        if(isMerged) {
            Set<ObjectVersionID> otherUids = new HashSet<ObjectVersionID>();
            otherUids.add(new ObjectVersionID("1.4.14.5::1.2.840.114.1.2.2::4.2.2"));
            return new OriginalVersion<String>(uid, null, data, 
                codedText, audit1, lr, null, otherUids, null, isMerged, 
                TestTerminologyService.getInstance());

        } else {
            return new OriginalVersion<String>(uid, null, data, 
                codedText, audit1, lr, null, null, null, isMerged, 
                TestTerminologyService.getInstance());
        }
    }
    
    protected ImportedVersion<String> importedVersion(String data, boolean isMerged,
            String uidStr, String orgTime, String importTime) {
        ObjectReference lr = new LocatableReference(new ObjectVersionID(
                "1.20.51.60::1.2.840.114.1.2.2::1"), ObjectReference.Namespace.LOCAL,
                ObjectReference.Type.CONTRIBUTION, "");
        PartyIdentified pi = new PartyIdentified(new PartyReference(
                new HierarchicalObjectID("1-2-5-4-1"), 
                ObjectReference.Type.PARTY), "committer name", null);
        DvCodedText codedText = new DvCodedText("complete", TestCodePhrase.ENGLISH,
                TestCodePhrase.LATIN_1, new CodePhrase(TestTerminologyID.SNOMEDCT, "revisionCode"),
                TestTerminologyService.getInstance());
        AuditDetails audit = new AuditDetails("1.32.4.15", pi, 
                new DvDateTime(importTime), codedText, null,
                TestTerminologyService.getInstance());
        return new ImportedVersion<String>(originalVersion(data, isMerged, uidStr, orgTime), 
                audit, lr, "committer's signature");
    }
}
