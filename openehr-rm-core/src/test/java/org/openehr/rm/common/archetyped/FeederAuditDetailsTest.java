/*
 * component:   "openEHR Reference Implementation"
 * description: "Class FeederAuditDetailsTest"
 * keywords:    "unit test"
 *
 * author:      "Yin Su Lim <y.lim@chime.ucl.ac.uk>"
 * support:     "CHIME, UCL"
 * copyright:   "Copyright (c) 2006 UCL, UK"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/BRANCHES/RM-1.0-update/libraries/src/test/org/openehr/rm/common/archetyped/FeederAuditDetailsTest.java $"
 * revision:    "$LastChangedRevision: 50 $"
 * last_change: "$LastChangedDate: 2006-08-10 12:21:46 +0100 (Thu, 10 Aug 2006) $"
 */
/**
 * FeederAuditDetailsTest
 *
 * @author Yin Su Lim
 * @version 1.0 
 */
package org.openehr.rm.common.archetyped;

import org.openehr.rm.common.generic.PartyIdentified;
import org.openehr.rm.common.generic.PartyProxy;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;

import junit.framework.TestCase;

public class FeederAuditDetailsTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

    public void testConstructor() throws Exception {

        // test with null or empty systemID
        assertExceptionThrown(null, null, null,
                new DvDateTime("2004-10-12T09:00:00"),
                null, "versionid12.1", "null or empty systemId");

        assertExceptionThrown("", null, null,
                new DvDateTime("2004-10-12T09:00:00"),
                null, "versionid12.1", "null or empty systemId");
    }
 
    public void testEquals() throws Exception {
        FeederAuditDetails fad1 = new FeederAuditDetails("systemId", null, null,
                new DvDateTime("2004-10-12T09:00:00"),
                null, "versionid12.1");

        FeederAuditDetails fad2 = new FeederAuditDetails("systemId", null, null,
                new DvDateTime("2004-10-12T09:00:00"),
                null, "versionid12.1");

        assertTrue(fad1.equals(fad2));
        assertTrue(fad2.equals(fad1));

        FeederAuditDetails fad3 = new FeederAuditDetails("systemID", null, null,
                new DvDateTime("2004-10-12T09:01:01"),
                null, "versionid12.2");
        assertFalse(fad1.equals(fad3));
        assertFalse(fad3.equals(fad1));
    }
   
    public void testHashCode() throws Exception {
    		FeederAuditDetails fad1 = new FeederAuditDetails("systemID", null, null,
                new DvDateTime("2004-10-12T09:00:00"),
                null, "versionid12.1");

        FeederAuditDetails fad2 = new FeederAuditDetails("systemID", null, null,
                new DvDateTime("2004-10-12T09:00:00"),
                null, "versionid12.1");
        assertEquals("hashcodes not equal", fad1.hashCode(), fad2.hashCode());
        
        FeederAuditDetails fad3 = new FeederAuditDetails("systemID", null, null,
                new DvDateTime("2004-10-12T09:01:01"),
                null, "versionid12.2");
        assertFalse(fad3.equals(fad1));
    }
    
    private void assertExceptionThrown(String systemID, PartyIdentified provider,
    		PartyIdentified location, DvDateTime time, PartyProxy subject,
    		String versionID, String cause) throws Exception {
		try {
			new FeederAuditDetails(systemID, provider, location, time,
					subject, versionID);
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
 *  The Original Code is FeederAuditDetailsTest.java
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