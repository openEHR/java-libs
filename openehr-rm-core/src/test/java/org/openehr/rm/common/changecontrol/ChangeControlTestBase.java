/*
 * component:   "openEHR Reference Implementation"
 * description: "Class ChangeControlTestBase"
 * keywords:    "unit test"
 *
 * author:      "Yin Su Lim <y.lim@chime.ucl.ac.uk>"
 * support:     "CHIME, UCL"
 * copyright:   "Copyright (c) 2006 UCL, UK"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/BRANCHES/RM-1.0-update/libraries/src/test/org/openehr/rm/common/changecontrol/ChangeControlTestBase.java $"
 * revision:    "$LastChangedRevision: 50 $"
 * last_change: "$LastChangedDate: 2006-08-10 12:21:46 +0100 (Thu, 10 Aug 2006) $"
 */

/**
 * ChangeControlTestBase
 *
 * @author Yin Su Lim
 * @version 1.0 
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
import org.openehr.rm.support.identification.HierObjectID;
import org.openehr.rm.support.identification.LocatableRef;
import org.openehr.rm.support.identification.ObjectRef;
import org.openehr.rm.support.identification.ObjectVersionID;
import org.openehr.rm.support.identification.PartyRef;
import org.openehr.rm.support.identification.TestTerminologyID;
import org.openehr.rm.support.terminology.TestTerminologyService;

public class ChangeControlTestBase extends TestCase {

	public ChangeControlTestBase(String testName) {
		super(testName);
	}

	protected void setUp() throws Exception {
	}

	protected void tearDown() throws Exception {
	}

	protected DvCodedText lifeCycleState(String state) {
		CodePhrase codePhrase = new CodePhrase(TestTerminologyID.SNOMEDCT,
				"revisionCode");
		return new DvCodedText(state, TestCodePhrase.ENGLISH,
				TestCodePhrase.LATIN_1, codePhrase, TestTerminologyService
						.getInstance());
	}

	protected AuditDetails audit(String id, String name, String changeType,
			String dt) {
		PartyIdentified pi = new PartyIdentified(new PartyRef(new HierObjectID(
				id), "PARTY"), name, null);
		CodePhrase codePhrase = new CodePhrase(TestTerminologyID.SNOMEDCT,
				"revisionCode");
		DvCodedText codedText = new DvCodedText(changeType,
				TestCodePhrase.ENGLISH, TestCodePhrase.LATIN_1, codePhrase,
				TestTerminologyService.getInstance());
		return new AuditDetails("12.3.4.5", pi, new DvDateTime(dt), codedText,
				null, TestTerminologyService.getInstance());
	}

	protected LocatableRef contribution(String objectVersionID, String path) {
		return new LocatableRef(new ObjectVersionID(objectVersionID), "LOCAL",
				"CONTRIBUTION", path);
	}

	protected OriginalVersion<String> originalVersion(String data,
			boolean isMerged,
			String uidStr, String time) {
		ObjectVersionID uid = new ObjectVersionID(uidStr);
		CodePhrase codePhrase = new CodePhrase(TestTerminologyID.SNOMEDCT,
				"revisionCode");
		DvCodedText codedText = new DvCodedText("complete",
				TestCodePhrase.ENGLISH, TestCodePhrase.LATIN_1, codePhrase,
				TestTerminologyService.getInstance());
		PartyIdentified pi = new PartyIdentified(new PartyRef(new HierObjectID(
				"1-2-3-4-5"), "PARTY"), "committer name", null);
		AuditDetails audit1 = new AuditDetails("12.3.4.5", pi, new DvDateTime(
				time), codedText, null, TestTerminologyService.getInstance());
		ObjectRef lr = new LocatableRef(new ObjectVersionID(
				"1.23.51.66::1.2.840.114.1.2.2::2"), "LOCAL", "CONTRIBUTION",
				"");

		if (isMerged) {
			Set<ObjectVersionID> otherUids = new HashSet<ObjectVersionID>();
			otherUids.add(new ObjectVersionID(
					"1.4.14.5::1.2.840.114.1.2.2::4.2.2"));
			return new OriginalVersion<String>(uid, null, data, codedText,
					audit1, lr, null, otherUids, null, // isMerged,
					TestTerminologyService.getInstance());

		} else {
			return new OriginalVersion<String>(uid, null, data, codedText,
					audit1, lr, null, null, null, // isMerged,
					TestTerminologyService.getInstance());
		}
	}

	protected ImportedVersion<String> importedVersion(String data,
			boolean isMerged, String uidStr, String orgTime, String importTime) {
		ObjectRef lr = new LocatableRef(new ObjectVersionID(
				"1.20.51.60::1.2.840.114.1.2.2::1"), "LOCAL", "CONTRIBUTION",
				"");
		PartyIdentified pi = new PartyIdentified(new PartyRef(new HierObjectID(
				"1-2-5-4-1"), "PARTY"), "committer name", null);
		DvCodedText codedText = new DvCodedText("complete",
				TestCodePhrase.ENGLISH, TestCodePhrase.LATIN_1, new CodePhrase(
						TestTerminologyID.SNOMEDCT, "revisionCode"),
				TestTerminologyService.getInstance());
		AuditDetails audit = new AuditDetails("1.32.4.15", pi, new DvDateTime(
				importTime), codedText, null, TestTerminologyService
				.getInstance());
		return new ImportedVersion<String>(originalVersion(data, isMerged,
				uidStr, orgTime), audit, lr, "committer's signature");
	}
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
 * The Original Code is ChangeControlTestBase.java
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