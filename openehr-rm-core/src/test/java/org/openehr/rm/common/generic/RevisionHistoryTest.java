/*
 * component:   "openEHR Reference Implementation"
 * description: "Class RevisionHistorytest"
 * keywords:    "unit test"
 *
 * author:      "Yin Su Lim <y.lim@chime.ucl.ac.uk>"
 * support:     "CHIME, UCL"
 * copyright:   "Copyright (c) 2006 UCL, UK"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/BRANCHES/RM-1.0-update/libraries/src/test/org/openehr/rm/common/generic/RevisionHistorytest.java $"
 * revision:    "$LastChangedRevision: 50 $"
 * last_change: "$LastChangedDate: 2006-08-10 12:21:46 +0100 (Thu, 10 Aug 2006) $"
 */

/**
 * RevisionHistorytest
 *
 * @author Yin Su Lim
 * @version 1.0 
 */
package org.openehr.rm.common.generic;

import java.util.ArrayList;
import junit.framework.*;
import java.util.List;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.datatypes.text.TestCodePhrase;
import org.openehr.rm.support.identification.HierObjectID;
import org.openehr.rm.support.identification.ObjectVersionID;
import org.openehr.rm.support.identification.PartyRef;
import org.openehr.rm.support.identification.TestTerminologyID;
import org.openehr.rm.support.terminology.TestTerminologyService;

public class RevisionHistoryTest extends TestCase {
    
    public RevisionHistoryTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
        PartyRef pr = new PartyRef(new HierObjectID("1-2-3-4-5"), "PARTY");
        PartyIdentified pi = new PartyIdentified(pr, "party name", null);
        CodePhrase codePhrase =
                new CodePhrase(TestTerminologyID.SNOMEDCT, "revisionCode");
        DvCodedText codedText = new DvCodedText("code value", TestCodePhrase.ENGLISH,
                TestCodePhrase.LATIN_1, codePhrase,
                TestTerminologyService.getInstance());
        AuditDetails audit1 = new AuditDetails("12.3.4.5", pi, 
                new DvDateTime("2006-05-01T10:10:00"), codedText, null,
                TestTerminologyService.getInstance());
        AuditDetails audit2 = new AuditDetails("20.3.33.55", pi, 
                new DvDateTime("2006-06-01T10:10:00"), codedText, null,
                TestTerminologyService.getInstance());
        List<AuditDetails> audits1 = new ArrayList<AuditDetails>();
        audits1.add(audit1);
        List<AuditDetails> audits2 = new ArrayList<AuditDetails>();
        audits2.add(audit2);
        RevisionHistoryItem rhi1 = new RevisionHistoryItem(audits1, 
                new ObjectVersionID("1.4.4.5::1.2.840.114.1.2.2::123::1"));
        RevisionHistoryItem rhi2 = new RevisionHistoryItem(audits2, 
                new ObjectVersionID("1.4.4.5::1.2.840.114.1.2.2::123::2"));
        List<RevisionHistoryItem> rhiList = new ArrayList<RevisionHistoryItem>();
        rhiList.add(rhi1);
        rhiList.add(rhi2);
        rh = new RevisionHistory(rhiList);
        
    }

    protected void tearDown() throws Exception {
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(RevisionHistoryTest.class);
        
        return suite;
    }


    public void testMostRecentVersionId() {
        assertEquals("1.4.4.5::1.2.840.114.1.2.2::123::2", rh.mostRecentVersionId());
    }

    public void testMostRecentVersionTime() {
        assertEquals("2006-06-01T10:10:00", rh.mostRecentVersionTime());
    }

    private RevisionHistory rh;
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
 *  The Original Code is RevisionHistorytest.java
 *
 *  The Initial Developer of the Original Code is Rong Chen.
 *  Portions created by the Initial Developer are Copyright (C) 2003-2008
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