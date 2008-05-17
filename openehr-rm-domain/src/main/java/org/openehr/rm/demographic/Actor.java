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
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/demographic/Actor.java $"
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
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.util.Set;
import java.util.HashSet;

/**
 * Ancestor of all real-world types, including people and organisations.
 * An actor is any real-world entity capable of taking on a role.
 *
 * @author Goran Pestana
 * @author Rong Chen
 * @version 1.0
 */
public abstract class Actor extends Party {

    protected Actor() {
    }

    /**
     * Constructs a Actor
     *
     * @param uid                  null if not specified
     * @param archetypeNodeId
     * @param name
     * @param archetypeDetails     null if not specified
     * @param feederAudit          null if not specified
     * @param links                null if not specified
     * @param identities
     * @param contacts
     * @param relationships
     * @param reverseRelationships
     * @param details              null if not specified
     * @param roles
     * @param languages
     * @throws IllegalArgumentException if name null or archetypeNodeId null,
     *                                  or links not null and empty,
     *                                  or uid is null, or identities is null
     *                                  or empty, or contacts is empty,
     *                                  or no legal identity, or empty roles
     *                                  or empty languages
     */
    @FullConstructor
            protected Actor(
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
                details);

        boolean hasLegalIdentity = false;
        for (PartyIdentity identity : identities) {
            if (LEGAL_IDENTITY.equals(identity.purpose().getValue())) {
                hasLegalIdentity = true;
                break;
            }
        }
        if (!hasLegalIdentity) {
            throw new IllegalArgumentException("no legal identity");
        }
        if (roles != null && roles.isEmpty()) {
            throw new IllegalArgumentException("empty roles");
        }
        if (languages != null && languages.isEmpty()) {
            throw new IllegalArgumentException("empty languages");
        }

        this.roles = roles;
        this.languages = languages;
    }

    /**
     * Roles played by this party
     *
     * @return null if not specified
     */
    public Set<Role> getRoles() {
        return roles;
    }

    protected void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    /**
     * Add a role to this actor if it is not already present
     *
     * @param role
     * @return true if this agent did not already have this role
     */
    public boolean addRole(Role role) {
        if (roles == null) {
            roles = new HashSet<Role>();
        }
        return roles.add(role);
    }

    /**
     * Remove a role from this actor if it is present
     *
     * @param role
     * @return true if the actor has this role
     */
    public boolean removeRole(Role role) {
        if (roles == null) {
            return false;
        }
        return roles.remove(role);
    }

    /**
     * Languages which can be used to communicate with this actor.
     *
     * @return null if not specified
     */
    public Set<DvText> getLanguages() {
        return languages;
    }

    protected void setLanguages(Set<DvText> languages) {
        this.languages = languages;
    }

    /**
     * Equals if two actors has same values
     *
     * @param o
     * @return equals if true
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!( o instanceof Actor )) return false;
        if (!super.equals(o)) return false;

        final Actor actor = (Actor) o;
        return new EqualsBuilder()
                .append(roles, actor.roles)
                .append(languages, actor.languages)
                .isEquals();
    }

    /**
     * Return a hash code of this actor
     *
     * @return hash code
     */
    public int hashCode() {
        return new HashCodeBuilder(11, 29)
                .appendSuper(super.hashCode())
                .append(roles)
                .append(languages)
                .toHashCode();
    }

    /* fields */
    private Set<Role> roles;
    private Set<DvText> languages;

    /* static fields */
    public static final String LEGAL_IDENTITY = "legal identity";
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
 *  The Original Code is Actor.java
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