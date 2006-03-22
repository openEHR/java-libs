/*
 * component:   "openEHR Reference Implementation"
 * description: "Class VersionRepositoryTest"
 * keywords:    "unit test"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL$"
 * revision:    "$LastChangedRevision$"
 * last_change: "$LastChangedDate$"
 */
/**
 * VersionRepositoryTest
 *
 * @author Rong Chen
 * @version 1.0 
 */
package org.openehr.rm.common.changecontrol;

import junit.framework.TestCase;
import org.openehr.rm.support.identification.HierarchicalObjectID;
import org.openehr.rm.support.identification.ObjectID;
import org.openehr.rm.support.identification.ObjectReference;
import org.openehr.rm.support.identification.PartyReference;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.support.terminology.TestCodeSet;
import org.openehr.rm.support.terminology.TestTerminologyService;

public class VersionRepositoryTest extends TestCase {

    public VersionRepositoryTest(String test) {
        super(test);
    }

    /**
     * The fixture set up called before every test method.
     */
    protected void setUp() throws Exception {
    }

    /**
     * The fixture clean up called after every test method.
     */
    protected void tearDown() throws Exception {
    }

    public void testCreateRepository() throws Exception {
        String firstData = "the first one";
        VersionRepository<String> repository = repository(firstData);

        // test count total number of versions
        assertEquals("versionCount", 1, repository.versionCount());

        // test has version id
        assertTrue(repository.hasVersionID(VersionRepository.FIRST));

        // test get first Version
        Version<String> version = repository.firstVersion();
        assertEquals("firstVersion", firstData, version.getData());

        // test get latest version
        version = repository.latestVersion();
        assertEquals("latestVersion", firstData, version.getData());

        // test has version at time
        assertTrue(repository.hasVersionAtTime(
                version.getAudit().getTimeCommitted()));

        // test all versions
        assertEquals(1, repository.allVersions().size());
        assertTrue(repository.allVersions().contains(version));

        // test all version ids
        assertEquals(1, repository.allVersionIDs().size());
        assertTrue(
                repository.allVersionIDs().contains(VersionRepository.FIRST));

        // test versionWithID
        version = repository.versionWithID(VersionRepository.FIRST);
        assertEquals("versionWithID", firstData, version.getData());
    }

    public void testCommit() throws Exception {
        String firstData = "the first one";
        String secondData = "the second one";
        VersionRepository<String> repository = repository(firstData);
        repository.commit(audit(TestCodeSet.AMENDMENT), secondData,
                contribution("30002"), TestCodeSet.DRAFT,
                TestTerminologyService.getInstance(),
                ObjectReference.Namespace.LOCAL,
                ObjectReference.Type.VERSION_REPOSITORY);

        // test count total number of versions
        assertEquals("versionCount", 2, repository.versionCount());

        // test has version id
        assertTrue(repository.hasVersionID(VersionRepository.FIRST));
        assertTrue(repository.hasVersionID("2"));

        // test get first Version
        Version<String> firstVersion = repository.firstVersion();
        assertEquals("firstVersion", firstData, firstVersion.getData());

        // test get latest version
        Version<String> secondVersion = repository.latestVersion();
        assertEquals("latestVersion", secondData, secondVersion.getData());

        // test has version at time
        assertTrue(repository.hasVersionAtTime(
                firstVersion.getAudit().getTimeCommitted()));

        assertTrue(repository.hasVersionAtTime(
                        secondVersion.getAudit().getTimeCommitted()));

        // test all versions
        assertEquals(2, repository.allVersions().size());
        assertTrue(repository.allVersions().contains(firstVersion));
        assertTrue(repository.allVersions().contains(secondVersion));

        // test all version ids
        assertEquals(2, repository.allVersionIDs().size());
        assertTrue(repository.allVersionIDs().contains(
                VersionRepository.FIRST));
        assertTrue(repository.allVersionIDs().contains("2"));

        // test versionWithID
        firstVersion = repository.versionWithID(VersionRepository.FIRST);
        assertEquals("versionWithID", firstData, firstVersion.getData());
        secondVersion = repository.versionWithID("2");
        assertEquals("versionWithID", secondData, secondVersion.getData());
    }

    // test repository
    VersionRepository<String> repository(String firstData) throws Exception {
        ObjectID id = new HierarchicalObjectID("20001");
        ObjectReference ehrRef = new ObjectReference(
                new HierarchicalObjectID("20001"),
                ObjectReference.Namespace.LOCAL,
                ObjectReference.Type.EHR);
        return new VersionRepository<String>(id, ehrRef,
                audit(TestCodeSet.CREATION), firstData, contribution("30001"),
                TestCodeSet.DRAFT, TestTerminologyService.getInstance(),
                ObjectReference.Namespace.LOCAL,
                ObjectReference.Type.VERSION_REPOSITORY);
    }

    // test audit
    AuditDetails audit(DvCodedText changeType) throws Exception {
        PartyReference committer = new PartyReference(
                new HierarchicalObjectID("10001"));
        return new AuditDetails("ehr system", committer, new DvDateTime(),
                changeType, new DvText("desc"),
                TestTerminologyService.getInstance());
    }

    // test contribution
    ObjectReference contribution(String id) throws Exception {
        return new ObjectReference(new HierarchicalObjectID(id),
                ObjectReference.Namespace.LOCAL,
                ObjectReference.Type.CONTRIBUTION);
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
 *  The Original Code is VersionRepositoryTest.java
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