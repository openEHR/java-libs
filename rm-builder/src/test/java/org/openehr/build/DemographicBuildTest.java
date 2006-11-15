/*
 * component:   "openEHR Reference Implementation"
 * description: "Class DemographicBuildTest"
 * keywords:    "unit test"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/BRANCHES/RM-1.0-update/libraries/src/test/org/openehr/rm/util/RMObjectBuilderDemographicTest.java $"
 * revision:    "$LastChangedRevision: 50 $"
 * last_change: "$LastChangedDate: 2006-08-10 13:21:46 +0200 (Thu, 10 Aug 2006) $"
 */

package org.openehr.build;

import org.openehr.rm.RMObject;
import org.openehr.rm.support.identification.ObjectID;
import org.openehr.rm.support.identification.ArchetypeID;
import org.openehr.rm.support.identification.ObjectReference;
import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.datastructure.DataStructure;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.datatypes.quantity.datetime.DvDate;
import org.openehr.rm.datatypes.quantity.DvInterval;
import org.openehr.rm.demographic.*;
import org.openehr.rm.support.identification.HierarchicalObjectID;
import org.openehr.rm.support.identification.PartyReference;

import java.util.*;

/**
 * Testcase for demographic object building
 *
 * @author Rong Chen
 * @version 1.0
 */
public class DemographicBuildTest extends BuildTestBase {

    public void testBuildAddress() throws Exception {
        String type = "Address";
        Map<String, Object> values = new HashMap<String, Object>();

        DvText text = text("addresss");
        String node = "at0001";
        DataStructure item = itemSingle("address details");
        values.put("name", text);
        values.put("archetypeNodeId", node);
        values.put("details", item);

        RMObject obj = builder.construct(type, values);
        assertTrue(obj instanceof Address);

        Address addr = (Address) obj;
        assertEquals("name wrong", text, addr.getName());
        assertEquals("archetypeNodeId wrong", node, addr.getArchetypeNodeId());
        assertEquals("details wrong", item, addr.getDetails());
    }

    public void testBuildPartyIdentity() throws Exception {
        String type = "PartyIdentity";
        Map<String, Object> values = new HashMap<String, Object>();

        DvText text = text("party identity");
        String node = "at0001";
        DataStructure item = itemSingle("party identity details");
        values.put("name", text);
        values.put("archetypeNodeId", node);
        values.put("details", item);

        RMObject obj = builder.construct(type, values);
        assertTrue(obj instanceof PartyIdentity);

        PartyIdentity pid = (PartyIdentity) obj;
        assertEquals("name wrong", text, pid.getName());
        assertEquals("archetypeNodeId wrong", node, pid.getArchetypeNodeId());
        assertEquals("details wrong", item, pid.getDetails());
    }

    public void testBuildContact() throws Exception {
        String type = "Contact";
        Map<String, Object> values = new HashMap<String, Object>();

        DvText text = text("contact");
        String node = "at0001";
        List<Address> addresses = new ArrayList<Address>();
        addresses.add(new Address(null, node, text("address"),
                null, null, null, null, itemSingle("address detail")));
        values.put("name", text);
        values.put("archetypeNodeId", node);
        values.put("addresses", addresses);

        RMObject obj = builder.construct(type, values);

        Contact contact = (Contact) obj;
        assertEquals("name wrong", text, contact.getName());
        assertEquals("archetypeNodeId wrong", node, contact.getArchetypeNodeId());
        assertEquals("addresses wrong", addresses, contact.getAddresses());

        // add timeValidity
        DvInterval<DvDate> timeValidity = new DvInterval<DvDate>(
                new DvDate("2001-10-20"), null);
        values.put("timeValidity", timeValidity);
        obj = builder.construct(type, values);
        contact = (Contact) obj;
        assertEquals("timeValidity wrong", timeValidity,
                contact.getTimeValidity());
    }

    public void testBuildPartyRelationship() throws Exception {
        String type = "PartyRelationship";
        Map<String, Object> values = new HashMap<String, Object>();

        ObjectID uid = hid("1.3.25.6");
        DvText text = text("PartyRelationship");
        String node = "at0001";
        DataStructure details = itemSingle("party relationship details");
        DvInterval<DvDate> timeValidity = new DvInterval<DvDate>(
                new DvDate("2001-10-30"), null);
        ObjectReference source = new ObjectReference(hid("1.8.3.4.3.6.1"),
                ObjectReference.Namespace.LOCAL, ObjectReference.Type.PARTY);
        ObjectReference target = new ObjectReference(hid("1.2.13.3.7.31.1"),
                ObjectReference.Namespace.LOCAL, ObjectReference.Type.PARTY);

        values.put("uid", uid);
        values.put("name", text);
        values.put("archetypeNodeId", node);
        values.put("timeValidity", timeValidity);
        values.put("details", details);
        values.put("source", source);
        values.put("target", target);

        RMObject obj = builder.construct(type, values);

        PartyRelationship pi = (PartyRelationship) obj;
        assertEquals("uid wrong", uid, pi.getUid());
        assertEquals("name wrong", text, pi.getName());
        assertEquals("archetypeNodeId wrong", node, pi.getArchetypeNodeId());
        assertEquals("details wrong", details, pi.getDetails());
        assertEquals("timeValidity wrong", timeValidity,
                pi.getTimeValidity());
        assertEquals("source wrong", source, pi.getSource());
        assertEquals("target wrong", target, pi.getTarget());
    }

    public void testBuildCapability() throws Exception {
        String type = "Capability";
        Map<String, Object> values = new HashMap<String, Object>();

        DvText text = text("Capability");
        String node = "at0001";
        DataStructure credentials = itemSingle("capbility credentials");
        DvInterval<DvDate> timeValidity = new DvInterval<DvDate>(
                new DvDate("2001-10-30"), null);
        values.put("name", text);
        values.put("archetypeNodeId", node);
        values.put("timeValidity", timeValidity);
        values.put("credentials", credentials);

        RMObject obj = builder.construct(type, values);

        Capability cap = (Capability) obj;
        assertEquals("name wrong", text, cap.getName());
        assertEquals("archetypeNodeId wrong", node, cap.getArchetypeNodeId());
        assertEquals("details wrong", credentials, cap.getCredentials());
        assertEquals("timeValidity wrong", timeValidity,
                cap.getTimeValidity());
    }

    public void testBuildRole() throws Exception {
        Map<String, Object> values = new HashMap<String, Object>();
        ObjectID uid = hid("1.13.23.9");
        DvText text = text("role");
        String node = "at0001";
        ItemStructure details = itemSingle("role details");
        Set<PartyIdentity> identities = new HashSet<PartyIdentity>();
        identities.add(new PartyIdentity(null, node,
                text(Agent.LEGAL_IDENTITY),
                null, null, null, null, itemSingle("legal name")));
        Archetyped archetypeDetails = new Archetyped(
                new ArchetypeID("openehr-dm_rm-Role.doctor.v2"), "v1.0");
        PartyReference performer = new PartyReference(
                new HierarchicalObjectID("1.2.4.5.6.12.1"), 
                ObjectReference.Type.PERSON);
        //Actor performer = new Person(hid("9807425345"), "at0002",
          //      text("doctor"), archetypeDetails, null, null, identities,
            //    null, null, null, details, null, null);
        values.put("uid", uid);
        values.put("name", text);
        values.put("archetypeNodeId", node);
        values.put("details", details);
        values.put("identities", identities);
        values.put("archetypeDetails", archetypeDetails);
        values.put("performer", performer);

        RMObject obj = builder.construct("Role", values);
        Role role = (Role) obj;
        assertEquals("uid wrong", uid, role.getUid());
        assertEquals("name wrong", text, role.getName());
        assertEquals("archetypeNodeId wrong", node, role.getArchetypeNodeId());
        assertEquals("details wrong", details, role.getDetails());
        assertEquals("archetypeDetails wrong", archetypeDetails,
                role.getArchetypeDetails());
        assertEquals("performer wrong", performer, role.getPerformer());
    }

    public void testBuildActorSubclasses() throws Exception {
        verityBuildActor("Agent");
        verityBuildActor("Group");
        verityBuildActor("Organisation");
        verityBuildActor("Person");
    }

    private void verityBuildActor(String subclass) throws Exception {
        Map<String, Object> values = new HashMap<String, Object>();
        ObjectID uid = hid("1.2.4.6.17.12.5");
        DvText text = text("actor");
        String node = "at0001";
        DataStructure item = itemSingle("actor details");
        Set<PartyIdentity> identities = new HashSet<PartyIdentity>();
        identities.add(new PartyIdentity(null, "at0003",
                text(Agent.LEGAL_IDENTITY),
                null, null, null, null, itemSingle("legal name")));
        Archetyped archetypeDetails = new Archetyped(
                new ArchetypeID("openehr-dm_rm-Actor.actor.v2"), "v1.0");
        values.put("uid", uid);
        values.put("name", text);
        values.put("archetypeNodeId", node);
        values.put("details", item);
        values.put("identities", identities);
        values.put("archetypeDetails", archetypeDetails);

        RMObject obj = builder.construct(subclass, values);
        Actor actor = (Actor) obj;
        assertEquals("uid wrong", uid, actor.getUid());
        assertEquals("name wrong", text, actor.getName());
        assertEquals("archetypeNodeId wrong", node, actor.getArchetypeNodeId());
        assertEquals("details wrong", item, actor.getDetails());
        assertTrue("subclass wrong",
                actor.getClass().getName().endsWith(subclass));
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
 *  The Original Code is DemographicBuildTest.java
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
}
