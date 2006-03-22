/*
 * component:   "openEHR Reference Implementation"
 * description: "Class VersionedPartyTest"
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
package org.openehr.rm.demographic;

import org.openehr.rm.support.terminology.TestCodeSet;
import org.openehr.rm.support.terminology.TestTerminologyService;
import org.openehr.rm.support.terminology.TerminologyService;
import org.openehr.rm.support.identification.*;
import org.openehr.rm.common.changecontrol.Version;
import org.openehr.rm.common.changecontrol.AuditDetails;
import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;

import java.util.Set;
import java.util.HashSet;

/**
 * VersionedPartyTest
 *
 * @author Rong Chen
 * @version 1.0
 */
public class VersionedPartyTest extends DemographicTestBase {

    public VersionedPartyTest(String test) {
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

    public void testConstructor() throws Exception {
        String details = "some data for the first version";
        VersionedParty vp = versionedParty(details);

        assertEquals("size wrong", 1, vp.allVersions().size());
    }

    public void testCommit() throws Exception {
        VersionedParty vp = versionedParty("first version");

        Person person = person("second version");
        vp.commit(audit(TestCodeSet.AMENDMENT), person,
                contribution("30002"), TestCodeSet.DRAFT, ts,
                ObjectReference.Namespace.LOCAL);
        Version<Party> last = vp.latestVersion();

        assertEquals("size wrong", 2, vp.allVersions().size());
        assertEquals("data wrong", person, last.getData());
    }

    // test versioned party
    private VersionedParty versionedParty(String details) throws Exception {
        ObjectID id = new HierarchicalObjectID("23242342");
        ObjectReference owner = new ObjectReference(
                new HierarchicalObjectID("20001"),
                ObjectReference.Namespace.LOCAL,
                ObjectReference.Type.FOLDER);
        Person person = person(details);
        return new VersionedParty(id, owner, audit(TestCodeSet.CREATION),
                person, contribution("30001"), TestCodeSet.DRAFT, ts,
                ObjectReference.Namespace.LOCAL);
    }

    private Person person(String detailsText) throws Exception {
        ObjectID uid = oid("93420753453");
        DvText name = text("name");
        String meaning = "at0000";
        ItemStructure details = itemSingle(detailsText);

        Set<PartyIdentity> identities = new HashSet<PartyIdentity>();
        identities.add(new PartyIdentity(null, "at0000",
                text(Agent.LEGAL_IDENTITY), null, null, null,
                itemSingle(" identity value")));
        Archetyped archetypeDetails = new Archetyped(
                new ArchetypeID("openehr-dm_rm-Person.person.v1"), null,
                "v1.0");

        return new Person(uid, meaning, name, archetypeDetails, null, null,
                identities, null, null, null, details, null, null);
    }

    // test audit
    private AuditDetails audit(DvCodedText changeType) throws Exception {
        PartyReference committer = new PartyReference(
                new HierarchicalObjectID("10001"));
        return new AuditDetails("/", committer, new DvDateTime(),
                changeType, new DvText("desc"),
                TestTerminologyService.getInstance());
    }

    // test contribution
    private ObjectReference contribution(String id) throws Exception {
        return new ObjectReference(new HierarchicalObjectID(id),
                ObjectReference.Namespace.LOCAL,
                ObjectReference.Type.CONTRIBUTION);
    }

    private TerminologyService ts = TestTerminologyService.getInstance();
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
 *  The Original Code is VersionedPartyTest.java
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