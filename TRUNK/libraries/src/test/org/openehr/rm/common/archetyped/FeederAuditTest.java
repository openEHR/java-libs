/*
 * component:   "openEHR Reference Implementation"
 * description: "Class FeederAuditTest"
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
package org.openehr.rm.common.archetyped;

import junit.framework.TestCase;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.support.terminology.TerminologyService;
import org.openehr.rm.support.terminology.TestCodeSet;
import org.openehr.rm.support.terminology.TestTerminologyService;

public class FeederAuditTest extends TestCase {

    public FeederAuditTest(String test) {
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

        // test with null or empty systemID
        assertExceptionThrown(null, "committer",
                new DvDateTime("2004-10-12 09:00:00"),
                TestCodeSet.AMENDMENT, new DvText("description"),
                TestTerminologyService.getInstance(), "systemID");

        assertExceptionThrown("", "committer",
                new DvDateTime("2004-10-12 09:00:00"),
                TestCodeSet.AMENDMENT, new DvText("description"),
                TestTerminologyService.getInstance(), "systemID");

        // test with null or empty committer
        assertExceptionThrown("systemID", null,
                new DvDateTime("2004-10-12 09:00:00"),
                TestCodeSet.AMENDMENT, new DvText("description"),
                TestTerminologyService.getInstance(), "committer");

        assertExceptionThrown("systemID", "",
                new DvDateTime("2004-10-12 09:00:00"),
                TestCodeSet.AMENDMENT, new DvText("description"),
                TestTerminologyService.getInstance(), "committer");

        // test with null changeType
        assertExceptionThrown("systemID", "committer",
                new DvDateTime("2004-10-12 09:00:00"),
                null, new DvText("description"),
                TestTerminologyService.getInstance(), "changeType");
    }

    public void testEquals() throws Exception {
        FeederAudit fa1 = new FeederAudit("systemID", "committer",
                new DvDateTime("2004-10-12 09:00:00"),
                TestCodeSet.AMENDMENT, new DvText("description"),
                TestTerminologyService.getInstance());

        FeederAudit fa2 = new FeederAudit("systemID", "committer",
                new DvDateTime("2004-10-12 09:00:00"),
                TestCodeSet.AMENDMENT, new DvText("description"),
                TestTerminologyService.getInstance());

        assertTrue(fa1.equals(fa2));
        assertTrue(fa2.equals(fa1));

        FeederAudit fa3 = new FeederAudit("systemID_2", "committer",
                new DvDateTime("2004-10-12 09:00:00"),
                TestCodeSet.AMENDMENT, new DvText("description"),
                TestTerminologyService.getInstance());
        assertFalse(fa1.equals(fa3));
        assertFalse(fa3.equals(fa1));
    }

    private void assertExceptionThrown(String systemID, String committer,
                                       DvDateTime timeCommitted,
                                       DvCodedText changeType,
                                       DvText description,
                                       TerminologyService terminologyService,
                                       String cause)
            throws Exception {
        try {
            new FeederAudit(systemID, committer, timeCommitted, changeType,
                    description, terminologyService);
            fail("exception should be thrown");
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
            assertTrue("expected cause: " + cause + ", got: " + e.getMessage(),
                    e.getMessage().contains(cause));
        }
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
 *  The Original Code is FeederAuditTest.java
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