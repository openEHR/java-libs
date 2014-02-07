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
    
    public void setUp() {
    	init();
    }
    
    public void testItemAtPathWhole() {
    	path = "/";
    	value = person.itemAtPath(path);
    	assertEquals("wrong result for path: " + path, person, value);
    }
    
    public void testItemAtPathDetails() {
    	path = "/details";
    	value = person.itemAtPath(path);
    	assertEquals("wrong result for path: " + path, person.getDetails(), 
    			value);
    }
    
    public void testItemAtPathLegalIdentity() {
    	path = "/identities['legal identity']";
    	value = person.itemAtPath(path);
    	assertEquals("wrong result for path: " + path, identity, value);
    }
    
    public void testItemAtPathContact() {
    	path = "/contacts['contact 1']";
    	value = person.itemAtPath(path);
    	assertEquals("wrong result for path: " + path, contact, value);
    }
    
    public void testItemAtPathAddressOne() {
    	path = "/contacts['contact 1']/addresses['address 1']";
    	value = person.itemAtPath(path);
    	assertEquals("wrong result for path: " + path, addressOne, value);
    }
    
    public void testItemAtPathAddressTwo() {
    	path = "/contacts['contact 1']/addresses['address 2']";
    	value = person.itemAtPath(path);
    	assertEquals("wrong result for path: " + path, addressTwo, value);
    }
    

    private void init() {
        Set<PartyIdentity> identities = new HashSet<PartyIdentity>();
        ItemSingle legalName = new ItemSingle("at0002",
                text("legal name"), element("at0003", new DvText("value")));
        
        identity = new PartyIdentity(null, "at0000",
                text(Agent.LEGAL_IDENTITY), null, null, null, null, legalName);
        identities.add(identity);

        Set<Contact> contacts = new HashSet<Contact>();
        List<Address> addresses = new ArrayList<Address>();
        addressOne = address("address 1", "element 1");
        addressTwo = address("address 2", "element 2");
        addresses.add(addressOne);
        addresses.add(addressTwo);
        
        contact = new Contact(oid("1.3.4.5.4.6"), "at0010",
                new DvText("contact 1"), null, null, null, null,
                new DvInterval<DvDate>(new DvDate("2004-01-01"),
                        new DvDate("2005-01-01")), addresses);
        contacts.add(contact);

        Archetyped archetypeDetails = new Archetyped(
                new ArchetypeID("openehr-dm_rm-person.person.v1"), "v1.0");
        ItemSingle details = new ItemSingle("at0001", text("item single"),
                element("at0003", new DvText("value")));

        person = new Person(oid("1.2.33.2.3.6.4"), "at0000", 
        		new DvText("person"), archetypeDetails, null, null, identities,
        		contacts, null, null, details, null, null);
    }

    private Address address(String addressName, String itemName) {
        ItemSingle item = new ItemSingle("at0004", itemName,
                element("at0005", new DvText("value")));
        return new Address(oid("address.a1"), "at0011", new DvText(addressName),
                null, null, null, null, item);
    }
    
    private String path;
    private Object value;
    private Person person;
    private PartyIdentity identity;
    private Contact contact;
    private Address addressOne;
    private Address addressTwo;
}
