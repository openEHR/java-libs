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
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/demographic/Party.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 22:20:08 +0100 (Wed, 12 Oct 2005) $"
 */
package org.openehr.rm.demographic;

import org.openehr.rm.common.archetyped.Locatable;
import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.common.archetyped.FeederAudit;
import org.openehr.rm.common.archetyped.Link;
import org.openehr.rm.common.archetyped.Pathable;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.support.identification.LocatableRef;
import org.openehr.rm.support.identification.UIDBasedID;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.util.List;
import java.util.Set;

/**
 * Ancestor of all party types, including real world entities and their
 * roles. A party is any entity which can participate in an activity. The
 * meaning attribute inherited from LOCATABLE is used to indicate the actual
 * type of party.
 *
 * @author Goran Pestana
 * @author Rong Chen
 * @version 1.0
 */
public abstract class Party extends Locatable {

    protected Party() {
    }

    /**
     * Constructs a Party
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
     * @throws IllegalArgumentException if name null or archetypeNodeId null
     *                                  or links not null and empty,
     *                                  or uid is null, or identities is null
     *                                  or empty, or contacts is empty
     */
    protected Party(UIDBasedID uid, String archetypeNodeId, DvText name,
                    Archetyped archetypeDetails, FeederAudit feederAudit,
                    Set<Link> links, Set<PartyIdentity> identities, 
                    Set<Contact> contacts, Set<PartyRelationship> relationships,
                    Set<LocatableRef> reverseRelationships,
                    ItemStructure details) {
        super(uid, archetypeNodeId, name, archetypeDetails, feederAudit, links, null);

        if (uid == null) {
            throw new IllegalArgumentException("null uid");
        }
        if (identities == null || identities.isEmpty()) {
            throw new IllegalArgumentException("null or empty identities");
        }
        if (contacts != null && contacts.isEmpty()) {
            throw new IllegalArgumentException("empty contacts");
        }
        if (relationships != null) {
            if (relationships.isEmpty()) {
                throw new IllegalArgumentException("empty relationships");
            }
            boolean hasThis = false;
            for (PartyRelationship relation : relationships) {
                if (relation.getSource().getId().equals(getUid())) {
                    hasThis = true;
                    break;
                }
            }
            if (!hasThis) {
                throw new IllegalArgumentException("invalid relationships");
            }
        }

        //todo: Reverse_releationships_validity

        if (!isArchetypeRoot()) {
            throw new IllegalArgumentException("not archetype root");
        }
        this.identities = identities;
        this.contacts = contacts;
        this.relationships = relationships;
        this.reverseRelationships = reverseRelationships;
        this.details = details;
    }

    /**
     * Type of party, such as PERSON, ORGANISATION, etc.
     * Role name, eg general practitioner, nurse, private citizen.
     * Taken from inherited name attribute.
     *
     * @return type
     */
    public DvText type() {
        return this.getName();
    }

    public String pathOfItem(Locatable item) {
        //todo: to be implemented
        return null;
    }

    public Set<PartyIdentity> getIdentities() {
        return identities;
    }

    protected void setIdentities(Set<PartyIdentity> identities) {
        this.identities = identities;
    }

    public Set<Contact> getContacts() {
        return contacts;
    }

    protected void setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
    }

    public Set<PartyRelationship> getRelationships() {
        return relationships;
    }

    protected void setRelationships(Set<PartyRelationship> relationships) {
        this.relationships = relationships;
    }

    public Set<LocatableRef> getReverseRelationships() {
        return reverseRelationships;
    }

    protected void setReverseRelationships(
            Set<LocatableRef> reverseRelationships) {
        this.reverseRelationships = reverseRelationships;
    }

    public ItemStructure getDetails() {
        return details;
    }

    protected void setDetails(ItemStructure details) {
        this.details = details;
    }

    /**
     * Equals if two parties have same values
     *
     * @param o
     * @return true if equals
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!( o instanceof Party )) return false;
        if (!super.equals(o)) return false;
        
        final Party party = (Party) o;

        return new EqualsBuilder()
                .append(identities, party.identities)
                .append(contacts, party.contacts)
                .append(relationships, party.relationships)
                .append(reverseRelationships, party.reverseRelationships)
                .append(details, party.details)
                .isEquals();
    }

    /**
     * Return a hash code of this party
     *
     * @return hash code
     */
    public int hashCode() {
        return new HashCodeBuilder(7, 19)
                .appendSuper(super.hashCode())
                .append(identities)
                .append(contacts)
                .append(relationships)
                .append(reverseRelationships)
                .append(details)
                .toHashCode();
    }
    
    @Override
	public String pathOfItem(Pathable arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> itemsAtPath(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean pathExists(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pathUnique(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}

    /* fields */
    private Set<PartyIdentity> identities;
    private Set<Contact> contacts;
    private Set<PartyRelationship> relationships;
    private Set<LocatableRef> reverseRelationships;
    private ItemStructure details;
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
 *  The Original Code is Party.java
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