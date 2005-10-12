/*
 * component:   "openEHR Reference Implementation"
 * description: "Class PartyIdentityTest"
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

/**
 * PartyIdentityTest
 *
 * @author Rong Chen
 * @version 1.0
 */
public class PartyIdentityTest extends DemographicTestBase {

    public PartyIdentityTest(String name) {
        super(name);
    }

    public void testConstructor() throws Exception {

        new PartyIdentity(null, "at0000",
                text("person name"), null, null, null,
                itemSingle("Neo"));

        // null details
        try {
            new PartyIdentity(null, "at0000",
                text("person name"), null, null, null, null);

            fail("exception should be thrown");
        } catch(Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
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
 *  The Original Code is PartyIdentityTest.java
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