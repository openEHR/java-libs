/*
 * Copyright (C) 2004 Rong Chen, Acode HB, Sweden
 * All rights reserved.
 *
 * The contents of this software are subject to the FSF GNU Public License 2.0;
 * you may not use this software except in compliance with the License. You may
 * obtain a copy of the License at http://www.fsf.org/licenses/gpl.html
 *
 * Software distributed under the License is distributed on an 'AS IS' basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 */
package org.openehr.rm.demographic;

import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.support.identification.ArchetypeID;
import org.openehr.rm.datastructure.itemstructure.ItemSingle;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.datatypes.quantity.DvInterval;
import org.openehr.rm.datatypes.quantity.datetime.DvDate;

import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;

/**
 * PartyTest
 *
 * @author Rong Chen
 * @version 1.0
 */
public class PartyTest extends DemographicTestBase {

    public PartyTest(String name) {
        super(name);
    }

    public void testValidPath() throws Exception {
        Person person = person();

        String[] validPathList = {
            "/",
            "/details",
            "/details/item single",
            "/identities[legal identity]/",
            "/identities[legal identity]/details",
            "/contacts[contact 1]/addresses[address 1]",
            "/contacts[contact 1]/addresses[address 2]",
            "/contacts[contact 1]/addresses[address 1]/details",
            "/contacts[contact 1]/addresses[address 2]/details",
            "/contacts[contact 1]",

            "/[person]",
            "/[person]/details",
            "/[person]/identities[legal identity]/",
            "/[person]/identities[legal identity]/details",
            "/[person]/contacts[contact 1]",
            "/[person]/contacts[contact 1]/addresses[address 1]",
            "/[person]/contacts[contact 1]/addresses[address 2]"
        };

        for (String path : validPathList) {
            assertTrue("unexpected invalid path: " + path,
                    person.validPath(path));
        }

        String[] invalidPathList = {
            "", null, "[person]", "/person", // bad root
            "/person]details", // incomplete
            "/[person]/items|item single", // unknown attribute
            "/[person]/details|list"            // unknown item
        };

        for (String path : invalidPathList) {
            assertFalse("unexpected valid path: " + path,
                    person.validPath(path));
        }
    }

    public void testItemAtPath() throws Exception {
        Person person = person();

        assertEquals("wrong root", person, person.itemAtPath("/"));
        assertEquals("wrong root", person, person.itemAtPath("/[person]"));

        assertEquals("wrong details", person.getDetails(),
                person.itemAtPath("/details"));
        assertEquals("wrong details", person.getDetails(),
                person.itemAtPath("/[person]/details"));
        assertEquals("wrong details element",
                ( (ItemSingle) person.getDetails() ).item(),
                person.itemAtPath("/[person]/details/item single"));

        PartyIdentity identity =
                (PartyIdentity) person.getIdentities().toArray()[ 0 ];
        assertEquals("wrong identity", identity,
                person.itemAtPath("/identities[legal identity]/"));
        assertEquals("wrong identity", identity,
                person.itemAtPath("/[person]/identities[legal identity]/"));

        assertEquals("wrong identity details", identity.getDetails(),
                person.itemAtPath("/identities[legal identity]/details"));
        assertEquals("wrong identity details", identity.getDetails(),
                person.itemAtPath(
                        "/[person]/identities[legal identity]/details"));

        Contact contact = (Contact) person.getContacts().toArray()[ 0 ];
        assertEquals("wrong contact", contact,
                person.itemAtPath("/[person]/contacts[contact 1]"));
        assertEquals("wrong contact", contact,
                person.itemAtPath("/contacts[contact 1]"));

        assertEquals("wrong address 1", contact.getAddresses().get(0),
                person.itemAtPath(
                        "/[person]/contacts[contact 1]/addresses[address 1]"));
        assertEquals("wrong address 1", contact.getAddresses().get(0),
                person.itemAtPath("/contacts[contact 1]/addresses[address 1]"));

        assertEquals("wrong address 2", contact.getAddresses().get(1),
                person.itemAtPath(
                        "/[person]/contacts[contact 1]/addresses[address 2]"));
        assertEquals("wrong address 2", contact.getAddresses().get(1),
                person.itemAtPath("/contacts[contact 1]/addresses[address 2]"));
    }

    // test person
    private Person person() {
        Set<PartyIdentity> identities = new HashSet<PartyIdentity>();
        ItemSingle legalName = new ItemSingle("at0002",
                text("legal name"), element("at0003", new DvText("value")));
        identities.add(new PartyIdentity(null, "at0000",
                text(Agent.LEGAL_IDENTITY), null, null, null, legalName));

        Set<Contact> contacts = new HashSet<Contact>();
        List<Address> addresses = new ArrayList<Address>();
        addresses.add(address("address 1", "element 1"));
        addresses.add(address("address 2", "element 2"));
        contacts.add(new Contact(oid("3454"), "at0010",
                new DvText("contact 1"), null, null, null,
                new DvInterval<DvDate>(new DvDate("2004-01-01"),
                        new DvDate("2005-01-01")), addresses));

        Archetyped archetypeDetails = new Archetyped(
                new ArchetypeID("openehr-dm_rm-person.person.v1"),
                null, "v1.0");
        ItemSingle details = new ItemSingle("at0001", text("item single"),
                element("at0003", new DvText("value")));

        return new Person(oid("123"), "at0000", new DvText("person"),
                archetypeDetails, null, null, identities, contacts, null, null,
                details, null, null);
    }

    private Address address(String addressName, String itemName) {
        ItemSingle item = new ItemSingle("at0004", text(itemName),
                element("at0005", new DvText("value")));
        return new Address(oid("2333"), "at0011", new DvText(addressName),
                null, null, null, item);
    }
}
