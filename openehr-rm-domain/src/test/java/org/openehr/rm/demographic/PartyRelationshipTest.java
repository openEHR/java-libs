/*
 * component:   "openEHR Reference Implementation"
 * description: "Class PartyRelationshipTest"
 * keywords:    "unit test"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/BRANCHES/RM-1.0-update/libraries/src/test/org/openehr/rm/demographic/PartyRelationshipTest.java $"
 * revision:    "$LastChangedRevision: 50 $"
 * last_change: "$LastChangedDate: 2006-08-10 13:21:46 +0200 (Thu, 10 Aug 2006) $"
 */

package org.openehr.rm.demographic;

import org.openehr.rm.datatypes.quantity.datetime.DvDate;
import org.openehr.rm.datatypes.quantity.DvInterval;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.support.identification.ObjectRef;
import org.openehr.rm.support.identification.ObjectID;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;

/**
 * PartyRelationshipTest
 *
 * @author Rong Chen
 * @version 1.0
 */
public class PartyRelationshipTest extends DemographicTestBase {

    public PartyRelationshipTest(String name) {
        super(name);
    }

    public void testConstructor() throws Exception {
        ObjectID oid = oid("1.5.2.34.0.4.3");
        String meaning = "at0000";
        DvText name = text("father");
        DvInterval<DvDate> time = new DvInterval<DvDate>(
                        date("1980-05-13"), null);
        ItemStructure details = itemSingle("father to son");
        ObjectRef source = new ObjectRef(oid("1.9.0.8.57.34.25"),
                ObjectRef.Namespace.LOCAL, ObjectRef.Type.PARTY);
        ObjectRef target = new ObjectRef(oid("1.9.8.0.70.78.0"),
                ObjectRef.Namespace.LOCAL, ObjectRef.Type.PARTY);

        new PartyRelationship(oid, meaning, name, null, null, null, null, 
                details, time, source, target);

        // null time
        new PartyRelationship(oid, meaning, name, null, null, null, null,
                details, null, source, target);

        // null details
        new PartyRelationship(oid, meaning, name, null, null, null, null,
                null, null, source, target);

        assertException(oid, meaning, name, details, time, null, target);
        assertException(oid, meaning, name, details, time, source, null);
        assertException(oid, meaning, name, details, time, null, null);
    }

    private void assertException(ObjectID oid, String meaning,
                                 DvText name, ItemStructure details,
                                 DvInterval<DvDate> timeValidity,
                                 ObjectRef source,
                                 ObjectRef target)
            throws Exception {

        try {
            new PartyRelationship(oid, meaning, name, null, null, null, null,
                    details, timeValidity, source, target);

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
 *  The Original Code is PartyRelationshipTest.java
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