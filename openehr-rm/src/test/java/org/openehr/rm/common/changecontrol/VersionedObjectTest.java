/*
 * component:   "openEHR Reference Implementation"
 * description: "Class VersionedObjectTest"
 * keywords:    "unit test"
 *
 * author:      "Yin Su Lim <y.lim@chime.ucl.ac.uk>"
 * support:     "CHIME, UCL"
 * copyright:   "Copyright (c) 2006 UCL, UK"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/BRANCHES/RM-1.0-update/libraries/src/test/org/openehr/rm/common/archetyped/VersionedObjectTest.java $"
 * revision:    "$LastChangedRevision: 50 $"
 * last_change: "$LastChangedDate: 2006-08-10 12:21:46 +0100 (Thu, 10 Aug 2006) $"
 */

/**
 * VersionedObjectTest
 *
 * @author Yin Su Lim
 * @version 1.0 
 */
package org.openehr.rm.common.changecontrol;

import junit.framework.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import org.openehr.rm.RMObject;
import org.openehr.rm.common.generic.Attestation;
import org.openehr.rm.common.generic.AuditDetails;
import org.openehr.rm.common.generic.RevisionHistory;
import org.openehr.rm.common.generic.RevisionHistoryItem;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.support.identification.HierarchicalObjectID;
import org.openehr.rm.support.identification.ObjectID;
import org.openehr.rm.support.identification.ObjectReference;
import org.openehr.rm.support.identification.ObjectVersionID;
import org.openehr.rm.support.identification.VersionTreeID;
import org.openehr.rm.support.terminology.TerminologyService;
import org.openehr.rm.support.terminology.TestCodeSetAccess;
import org.openehr.rm.support.terminology.TestTerminologyService;

public class VersionedObjectTest extends ChangeControlTestBase {
    
    public VersionedObjectTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(VersionedObjectTest.class);
        
        return suite;
    }

    public void testVersionedObjectWOrgVersion() throws Exception {
        String firstData = "the first one";
        String firstCSID = "1.2.40.14.1.2.2";
        String time = "2006-07-14T14:33:29";
        VersionedObject<String> repository = repository(firstData,
                firstCSID, time);
        ObjectVersionID fVUid = new ObjectVersionID(repository.getUid().root().toString(), 
                firstCSID, "1");
        // test count total number of versions
        assertEquals("versionCount", 1, repository.versionCount());

        // test has version id
        
        assertTrue(repository.hasVersionID(fVUid));

        // test isOriginalVersion
        assertTrue(repository.isOriginalVersion(fVUid));
        
        
        // test get latest version
        Version<String> version = repository.latestVersion();
        assertEquals("latestVersion", firstData, version.getData());

        // test get latest trunk version
        version = repository.latestTrunkVersion();
        assertEquals("latestTrunkVersion", firstData, version.getData());
        
        // test get latest trunk version lifecyclestate
        assertEquals(TestCodeSetAccess.CREATION, repository.latestTrunkLifeCycleSate());
        
        // test has version at time
        assertTrue(repository.hasVersionAtTime(
                version.getCommitAudit().getTimeCommitted()));

        // test all versions
        assertEquals(1, repository.allVersions().size());
        assertTrue(repository.allVersions().contains(version));

        // test all version ids
        assertEquals(1, repository.allVersionIDs().size());
        assertTrue(
                repository.allVersionIDs().contains(fVUid));

        // test versionWithID
        version = repository.versionWithID(fVUid);
        assertEquals("versionWithID", firstData, version.getData());
        
        // test versionAtTime
        version = repository.versionAtTime(
                new DvDateTime(time));
        assertEquals("versionWithID", firstData, version.getData());

        repository.commitOriginalVersion(new ObjectVersionID(fVUid.toString() + ".1.1"),
                version.getUid(), "the 1st one", audit("1-2-3-4-2", "committer's name", "revisionCode", 
                "2006-07-16T12:36:55"), contribution("contrib::2-3-5-3-2::1", "path/o/path"),
                lifeCycleState("complete"), null, TestTerminologyService.getInstance());
        
        version = repository.latestVersion();
        assertEquals("latestVersion", "the 1st one", version.getData());
        assertEquals("latestTrunkVersion", "the first one", repository.latestTrunkVersion().getData());
        
    }
    
    // test repository
    VersionedObject<String> repository(String firstData, String creatingSysID,
            String time) throws Exception {
        HierarchicalObjectID id = new HierarchicalObjectID("1-2-5-2-4");
        ObjectReference ehrRef = new ObjectReference(
                new HierarchicalObjectID("ehrdomain::1"), ObjectReference.Namespace.LOCAL,               
                ObjectReference.Type.EHR);
        ObjectVersionID vUid = new ObjectVersionID(id.root(), new HierarchicalObjectID(creatingSysID),
                new VersionTreeID("1"));
        return new VersionedObject<String>(id, ehrRef, new DvDateTime(time),
                vUid, firstData,
                TestCodeSetAccess.CREATION, audit("1-5-7-7-1", "Yinsu", "changeTypeCode", 
                "2006-07-14T14:33:29"),  
                contribution("1-6-2-2-4::1.2.40.14::1", "path"), "comitter's signature",
                TestTerminologyService.getInstance());
    }
    
    
    OriginalVersion<String> orgVersion(String dataStr, String dStrVersionID, String pVersionID, String time) {
        
        ObjectVersionID pUid = null;
        if(pVersionID != null && !pVersionID.equals("")) {
            pUid = new ObjectVersionID(pVersionID);
        }
        return new OriginalVersion<String>(new ObjectVersionID(dStrVersionID), pUid,
                dataStr, TestCodeSetAccess.CREATION, audit("1-6-7-7-2", "Yinsu", "changeTypeCode", 
                "2006-07-14T14:35:49"), contribution("1-6-2-2-4::1.2.42.14::1", "path"), null, null, null,
                false, TestTerminologyService.getInstance());
           
    }

    public void testVersionedObjectWImportVersion() throws Exception {
        String firstData = "the first imported data";
        String firstVID = "1.7.5.2::1.2.40.14.1.2.2::1";
        String time = "2006-07-14T14:33:29";
        VersionedObject<String> repository = repositoryImport(firstData,
                firstVID, null, time);

        // test count total number of versions
        assertEquals("versionCount", 1, repository.versionCount());

        // test has version id
        assertTrue(repository.hasVersionID(
                new ObjectVersionID(firstVID)));

        // test isOriginalVersion
        assertFalse(repository.isOriginalVersion(
                new ObjectVersionID(firstVID)));
        
        
        // test get latest version
        Version<String> version = repository.latestVersion();
        assertEquals("latestVersion", firstData, version.getData());

        // test get latest trunk version
        version = repository.latestTrunkVersion();
        assertEquals("latestTrunkVersion", firstData, version.getData());
        
        // test get latest trunk version lifecyclestate
        assertEquals(TestCodeSetAccess.CREATION, repository.latestTrunkLifeCycleSate());
        
        // test has version at time
        assertTrue(repository.hasVersionAtTime(
                version.getCommitAudit().getTimeCommitted()));

        // test all versions
        assertEquals(1, repository.allVersions().size());
        assertTrue(repository.allVersions().contains(version));

        // test all version ids
        assertEquals(1, repository.allVersionIDs().size());
        assertTrue(
                repository.allVersionIDs().contains(
                new ObjectVersionID(firstVID)));

        // test versionWithID
        version = repository.versionWithID(
                new ObjectVersionID(firstVID));
        assertEquals("versionWithID", firstData, version.getData());
        
        repository.commitOriginalVersion(new ObjectVersionID(firstVID + ".1.1"),
                version.getUid(), "the 1st original", audit("1-2-3-4-2", "committer's name", "revisionCode", 
                "2006-07-16T12:36:55"), contribution("contrib::2-3-5-3-2::1", "path/o/path"),
                lifeCycleState("complete"), null, TestTerminologyService.getInstance());
        
        version = repository.latestVersion();
        assertEquals("latestVersion", "the 1st original", version.getData());
        assertEquals("latestTrunkVersion", "the first imported data", 
                repository.latestTrunkVersion().getData());
        repository.commitOriginalVersion(new ObjectVersionID(firstVID + ".1.1"),
                version.getUid(), "the 1st original", audit("1-2-3-4-2", "committer's name", "revisionCode", 
                "2006-07-16T12:36:55"), contribution("contrib::2-3-5-3-2::1", "path/o/path"),
                lifeCycleState("complete"), null, TestTerminologyService.getInstance());
        
    }

    public void testCommitMergedVersion() throws Exception {
        String firstData = "the first imported data";
        String firstVID = "1.7.5.2::1.2.40.14.1.2.2::1";
        String time = "2006-07-14T14:33:29";
        VersionedObject<String> repository = repositoryImport(firstData,
                firstVID, null, time);
        Version<String> version = repository.latestVersion();
        ObjectVersionID orgUid = new ObjectVersionID(firstVID + ".1.1");
        repository.commitOriginalVersion(orgUid, version.getUid(), 
            "the 1st original", audit("1-2-3-4-2", "committer's name", "revisionCode", 
            "2006-07-16T12:36:55"), contribution("contrib::2-3-5-3-2::1", "path/o/path"),
            lifeCycleState("complete"), null, TestTerminologyService.getInstance());
        String mergeVUid = "1.7.5.2::1.2.40.14.1.2.2::2";
        Set<ObjectVersionID> otherUids = new HashSet<ObjectVersionID>();
        otherUids.add(orgUid);
        ObjectVersionID mergeUid = new ObjectVersionID(mergeVUid);
        repository.commitOriginalMergedVersion(mergeUid, version.getUid(),
               "the merged data", lifeCycleState("complete"), audit("1-2-3-4-2", "committer's name",  
                "revisionCode","2006-07-16T12:37:55"), contribution("contrib::2-3-5-3-3::1", "path/o/path"),
                otherUids, null, TestTerminologyService.getInstance());
        
        assertEquals("versionCount", 3, repository.versionCount());
        // test has version id
        assertTrue(repository.hasVersionID(orgUid));
        assertTrue(repository.hasVersionID(mergeUid));
        // test isOriginalVersion
        assertTrue(repository.isOriginalVersion(mergeUid));
                
        // test get latest version
        version = repository.latestVersion();
        assertEquals("latestVersion", "the merged data", version.getData());

        // test get latest trunk version
        version = repository.latestTrunkVersion();
        assertEquals("latestTrunkVersion", "the merged data", version.getData());
                
        // test all version ids
        assertEquals(3, repository.allVersionIDs().size());
        
        // test versionWithID
        version = repository.versionWithID(mergeUid);
        assertEquals("versionWithID", "the merged data", version.getData());     
        
    }
	
    //for debugging setVersions()
    public void testSetVersions() throws Exception {
        String firstData = "the first one";
        String firstCSID = "1.2.40.14.1.2.2";
        String time = "2006-07-14T14:33:29";
        VersionedObject<String> repository = repository(firstData,
                firstCSID, time);
        ObjectVersionID fVUid = new ObjectVersionID(repository.getUid().root().toString(), 
                firstCSID, "1");
        Set<Version<String>> newVersions = new HashSet<Version<String>>();
        newVersions.add(orgVersion("setVersion data", fVUid.toString(), null, time));
        repository.setVersions(newVersions);
        assertEquals("data after set", "setVersion data", repository.latestVersion().getData());
    }

    VersionedObject<String> repositoryImport(String orgData, String dStrVersionID, String pVersionID,
            String time) {
        
        OriginalVersion<String> data = orgVersion(orgData, dStrVersionID, pVersionID, 
                time);
        HierarchicalObjectID id = data.ownerID();
        ObjectReference ehrRef = new ObjectReference(
                new HierarchicalObjectID("ehrdomain::1"), ObjectReference.Namespace.LOCAL,               
                ObjectReference.Type.EHR);

        return new VersionedObject<String>(id, ehrRef, new DvDateTime(time),
            data, audit("1-6-7-7-2", "Yinsu", "changeTypeCode", time),
                contribution("1-6-2-2-4::1.2.42.14::1", "path"), "YLim");
    }
        
    OriginalVersion<String> orgMergedVersion(String dataStr, String dStrVersionID, String pVersionID, 
            String time, ObjectVersionID[] otherInputIDs) {
        
        Set<ObjectVersionID> ids = new HashSet<ObjectVersionID>();
        for(int i = 0; i <  otherInputIDs.length; i++) {
            ids.add(otherInputIDs[i]);
        }
        return new OriginalVersion<String>(new ObjectVersionID(dStrVersionID), new ObjectVersionID(pVersionID),
                dataStr, TestCodeSetAccess.CREATION, audit("1-6-7-7-2", "Yinsu", "changeTypeCode", 
                "2006-07-14T14:35:49"), contribution("1-6-2-2-4::1.2.42.14::1", "path"), "signature", 
                ids, null, true, TestTerminologyService.getInstance());           
    }    
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
 *  The Original Code is VersionedObjectTest.java
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
