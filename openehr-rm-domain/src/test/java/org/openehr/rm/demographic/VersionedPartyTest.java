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
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/BRANCHES/RM-1.0-update/libraries/src/test/org/openehr/rm/demographic/VersionedPartyTest.java $"
 * revision:    "$LastChangedRevision: 50 $"
 * last_change: "$LastChangedDate: 2006-08-10 13:21:46 +0200 (Thu, 10 Aug 2006) $"
 */
package org.openehr.rm.demographic;

import java.util.HashSet;
import java.util.Set;

import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.common.changecontrol.Version;
import org.openehr.rm.common.generic.AuditDetails;
import org.openehr.rm.common.generic.PartyIdentified;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.support.identification.ArchetypeID;
import org.openehr.rm.support.identification.HierObjectID;
import org.openehr.rm.support.identification.UIDBasedID;
import org.openehr.rm.support.identification.ObjectRef;
import org.openehr.rm.support.identification.PartyRef;
import org.openehr.rm.support.identification.TerminologyID;
import org.openehr.rm.support.terminology.TerminologyService;
import org.openehr.rm.support.terminology.TestCodeSetAccess;
import org.openehr.rm.support.terminology.TestTerminologyAccess;
import org.openehr.rm.support.terminology.TestTerminologyService;

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

		vp.commitOriginalVersion(ovid("1.23.24.23::system.id::1.1.1"),
				ovid("1.23.24.23::system.id::1"), person,
				audit(TestCodeSetAccess.AMENDMENT), contribution("1.3.5.7"),
				TestCodeSetAccess.CREATION, null, ts);
		Version<Party> last = vp.latestVersion();

		assertEquals("size wrong", 2, vp.allVersions().size());
		assertEquals("data wrong", person, last.getData());
	}

	// test versioned party
	private VersionedParty versionedParty(String details) throws Exception {
		HierObjectID id = new HierObjectID("1.23.24.23");
		ObjectRef owner = new ObjectRef(new HierObjectID("1.20.0.1"), "LOCAL",
				"FOLDER");
		Person person = person(details);

		return new VersionedParty(id, owner, new DvDateTime(
				"2006-07-18T13:44:35"), ovid("1.23.24.23::system.id::1"),
				person, TestCodeSetAccess.CREATION,
				audit(TestCodeSetAccess.CREATION), contribution("1.4.6.7"),
				null, ts);
	}

	private Person person(String detailsText) throws Exception {
		UIDBasedID uid = oid("1.9.3.42::creating.system::1");
		DvText name = text("name");
		String meaning = "at0000";
		ItemStructure details = itemSingle(detailsText);

		Set<PartyIdentity> identities = new HashSet<PartyIdentity>();
		identities.add(new PartyIdentity(null, "at0000",
				text(Agent.LEGAL_IDENTITY), null, null, null, null,
				itemSingle(" identity value")));
		Archetyped archetypeDetails = new Archetyped(new ArchetypeID(
				"openehr-dm_rm-Person.person.v1"), "v1.0");

		return new Person(uid, meaning, name, archetypeDetails, null, null,
				identities, null, null, null, details, null, null);
	}

	// test audit
	private AuditDetails audit(DvCodedText changeType) throws Exception {
		PartyRef pr = new PartyRef(new HierObjectID("1-2-3-4-5"), "PARTY");
		PartyIdentified pi = new PartyIdentified(pr, "party name", null);
		CodePhrase codePhrase = new CodePhrase(TestTerminologyAccess.OPENEHR,
				"revisionCode");
		DvCodedText codedText = new DvCodedText("code value", ENGLISH, LATIN_1,
				codePhrase, TestTerminologyService.getInstance());
		return new AuditDetails("12.3.4.5", pi, new DvDateTime(
				"2006-05-01T10:10:00"), codedText, null, TestTerminologyService
				.getInstance());
	}

	// test contribution
	private ObjectRef contribution(String id) throws Exception {
		return new ObjectRef(new HierObjectID(id), "LOCAL", "CONTRIBUTION");
	}

	private TerminologyService ts = TestTerminologyService.getInstance();

	private static final CodePhrase ENGLISH = new CodePhrase(new TerminologyID(
			"iso-639-2"), "en");

	private static final CodePhrase LATIN_1 = new CodePhrase(new TerminologyID(
			"iana-character sets"), "ISO-8859-1");
}
/*
 * ***** BEGIN LICENSE BLOCK ***** Version: MPL 1.1/GPL 2.0/LGPL 2.1
 * 
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the 'License'); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at http://www.mozilla.org/MPL/
 * 
 * Software distributed under the License is distributed on an 'AS IS' basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 * 
 * The Original Code is VersionedPartyTest.java
 * 
 * The Initial Developer of the Original Code is Rong Chen. Portions created by
 * the Initial Developer are Copyright (C) 2003-2008 the Initial Developer. All
 * Rights Reserved.
 * 
 * Contributor(s):
 * 
 * Software distributed under the License is distributed on an 'AS IS' basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 * 
 * ***** END LICENSE BLOCK *****
 */