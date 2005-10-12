/*
 * component:   "openEHR Reference Implementation"
 * description: "Class PersonTest"
 * keywords:    "unit test"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004,2005 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL$"
 * revision:    "$LastChangedRevision$"
 * last_change: "$LastChangedDate$"
 */
package org.openehr.rm.demographic;

import org.openehr.rm.support.identification.ObjectID;
import org.openehr.rm.support.identification.ArchetypeID;
import org.openehr.rm.support.identification.ObjectReference;
import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.datatypes.quantity.datetime.DvDate;
import org.openehr.rm.datatypes.quantity.DvInterval;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;
import org.openehr.rm.datastructure.itemstructure.ItemSingle;

import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

/**
 * PersonTest
 *
 * @author Rong Chen
 * @version 1.0
 */
public class PersonTest extends DemographicTestBase {

    public PersonTest(String name) {
        super(name);
    }

    public void testConstructor() throws Exception {
        ObjectID uid = oid("93420753453");
        DvText name = text("person name");
        String meaning = "at0001";
        ItemStructure details = itemSingle("person details");

        Set<PartyIdentity> identities = new HashSet<PartyIdentity>();
        identities.add(new PartyIdentity(null, "at0000",
                text(Agent.LEGAL_IDENTITY), null, null, null,
                itemSingle(" identity value")));
        Archetyped archetypeDetails = new Archetyped(
                new ArchetypeID("openehr-dm_rm-person.person.v1"), null,
                "v1.0");

        Set<Contact> contacts = new HashSet<Contact>();
        DvInterval<DvDate> timeValidity = new DvInterval<DvDate>(
                date("2005-01-01"), date("2005-12-31"));
        List<Address> addresses = new ArrayList<Address>();
        addresses.add(new Address(oid("8702534253"), "at0000",
                text("telecom address"), null, null, null,
                itemSingle("telecom addresss details")));
        Contact contact = new Contact(null, "at0000",
                text("contact meaning"), null, null, null, timeValidity,
                addresses);
        contacts.add(contact);

        Set<PartyRelationship> relationships = new HashSet<PartyRelationship>();
        timeValidity = new DvInterval<DvDate>(date("1960-12-25"), null);
        ObjectReference source = new ObjectReference(uid,
                ObjectReference.Namespace.LOCAL, ObjectReference.Type.PARTY);
        ObjectReference target = new ObjectReference(oid("9873532"),
                ObjectReference.Namespace.LOCAL, ObjectReference.Type.PARTY);
        PartyRelationship relation = new PartyRelationship(oid("9870"),
                "at0000", text("mother"), null, null, null,
                itemSingle("mother to son"), timeValidity, source, target);
        relationships.add(relation);

        Set<ObjectReference> reverseRelationships =
                new HashSet<ObjectReference>();
        reverseRelationships.add(new ObjectReference(oid("987025"),
                ObjectReference.Namespace.LOCAL, ObjectReference.Type.PARTY));

        List<Capability> capabilities = new ArrayList<Capability>();
        capabilities.add(new Capability(null, "at0000",
                text("capability meaning"), null, null, null,
                timeValidity, itemSingle("capability credentials")));
        Set<DvText> languages = new HashSet<DvText>();
        languages.add(text("swedish"));

        // null roles
        new Person(uid, "at0000", name, archetypeDetails, null, null,
                identities, contacts, relationships, reverseRelationships,
                details, null, languages);

        // null contacts
        new Person(uid, "at0000", name, archetypeDetails, null, null,
                identities, null, relationships, reverseRelationships,
                details, null, languages);

        // null relationships and reverseRelationships
        new Person(uid, "at0000", name, archetypeDetails, null, null,
                identities, contacts, null, null, details, null, languages);

        // null languages
        new Person(uid, "at0000", name, archetypeDetails, null, null,
                identities, contacts, relationships, reverseRelationships,
                details, null, null);

        // null archetypeDetails
        assertExceptionThrow(uid, meaning, name, null, identities,
                contacts, relationships, reverseRelationships, details, null,
                languages);

        // null identities
        assertExceptionThrow(uid, meaning, name, archetypeDetails, null,
                contacts, relationships, reverseRelationships, details, null,
                languages);
    }    

    private void assertExceptionThrow(ObjectID uid, String meaning,
                                      DvText name, Archetyped archetypeDetails,
                                      Set<PartyIdentity> identities,
                                      Set<Contact> contacts,
                                      Set<PartyRelationship> relationships,
                                      Set<ObjectReference> revRelationships,
                                      ItemStructure details, Set<Role> roles,
                                      Set<DvText> languages) {
        try {
            new Person(uid, meaning, name, archetypeDetails, null, null,
                    identities, contacts, relationships, revRelationships,
                    details, roles, languages);
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
 *  The Original Code is PersonTest.java
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