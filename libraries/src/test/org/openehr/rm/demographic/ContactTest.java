/*
 * component:   "openEHR Reference Implementation"
 * description: "Class ContactTest"
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

import org.openehr.rm.datatypes.quantity.datetime.DvDate;
import org.openehr.rm.datatypes.quantity.DvInterval;

import java.util.List;
import java.util.ArrayList;

/**
 * ContactTest
 *
 * @author Rong Chen
 * @version 1.0
 */
public class ContactTest extends DemographicTestBase {

    public ContactTest(String name) {
        super(name);
    }

    public void testConstructor() throws Exception {
        DvInterval<DvDate> timeValidity = new DvInterval<DvDate>(
                date("2005-01-01"), date("2005-12-31"));
        List<Address> addresses = new ArrayList<Address>();
        addresses.add(new Address(oid("address.trial"), "at0000",
                text("post address"), null, null, null, null, 
                itemSingle("post address details")));
        addresses.add(new Address(oid("1.2.4.5.7.33.7"), "at0000",
                text("email address"), null, null, null, null, 
                itemSingle("email address details")));

        new Contact(null, "at0000", text("contact name"),
                null, null, null, null, timeValidity, addresses);

        // null addresses
        try {
            new Contact(null, "at0000", text("contact meaning"),
                    null, null, null, null, timeValidity, null);

            fail("exception should be thrown");

        } catch (Exception e) {
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
 *  The Original Code is ContactTest.java
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