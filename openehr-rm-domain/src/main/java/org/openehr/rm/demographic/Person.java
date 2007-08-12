/*
 * component:   "openEHR Reference Implementation"
 * description: "Class Attestation"
 * keywords:    "demographic"
 *
 * author:      "Goran Pestana <goran@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2005 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/demographic/Person.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 22:20:08 +0100 (Wed, 12 Oct 2005) $"
 */
package org.openehr.rm.demographic;

import org.openehr.rm.support.identification.UIDBasedID;
import org.openehr.rm.support.identification.LocatableRef;
import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.common.archetyped.FeederAudit;
import org.openehr.rm.common.archetyped.Link;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;
import org.openehr.rm.FullConstructor;
import org.openehr.rm.Attribute;

import java.util.Set;


/**
 * Generic description of persons. Provides a dedicated type to which Person
 * archetypes can be targeted.
 *
 * @author Goran Pestana
 * @version 1.0
 */
public class Person extends Actor {

    protected Person() {
    }

    /**
     * Constructs a Person
     *
     * @param uid              null if not specified
     * @param archetypeNodeId
     * @param name
     * @param archetypeDetails null if not specified
     * @param feederAudit      null if not specified
     * @param links            null if not specified
     * @param identities
     * @param contacts
     * @param relationships
     * @param reverseRelationships
     * @param details           null if not specified
     * @param roles
     * @param languages
     * @throws IllegalArgumentException if name null or archetypeNodeId null
     *                                  or links not null and empty,
     *                                  or uid is null, or identities is null,
     *                                  or empty, or contacts is empty,
     *                                  or no legal identity, or empty roles
     *                                  or empty languages
     */
    @FullConstructor
            public  Person(
            @Attribute(name = "uid", required = true) UIDBasedID uid,
            @Attribute(name = "archetypeNodeId", required = true) String archetypeNodeId,
            @Attribute(name = "name", required = true) DvText name,
            @Attribute(name = "archetypeDetails", required = true) Archetyped archetypeDetails,
            @Attribute(name = "feederAudit") FeederAudit feederAudit,
            @Attribute(name = "links") Set<Link> links,
            @Attribute(name = "identities", required = true) Set<PartyIdentity> identities,
            @Attribute(name = "contacts") Set<Contact> contacts,
            @Attribute(name = "relationships") Set<PartyRelationship> relationships,
            @Attribute(name = "reverseRelationships") Set<LocatableRef> reverseRelationships,
            @Attribute(name = "details") ItemStructure details,
            @Attribute(name = "roles") Set<Role> roles,
            @Attribute(name = "languages") Set<DvText> languages) {
        super(uid, archetypeNodeId, name, archetypeDetails, feederAudit, links,
                identities, contacts, relationships, reverseRelationships,
                details, roles, languages);
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
 *  The Original Code is Persion.java
 *
 *  The Initial Developer of the Original Code is Goran Pestana.
 *  Portions created by the Initial Developer are Copyright (C) 2003-2005
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