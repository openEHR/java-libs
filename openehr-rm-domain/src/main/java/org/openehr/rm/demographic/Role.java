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
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/demographic/Role.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 22:20:08 +0100 (Wed, 12 Oct 2005) $"
 */
package org.openehr.rm.demographic;

import java.util.List;
import java.util.Set;

import org.openehr.rm.datatypes.quantity.datetime.DvDate;
import org.openehr.rm.datatypes.quantity.DvInterval;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.support.identification.UIDBasedID;
import org.openehr.rm.support.identification.LocatableRef;
import org.openehr.rm.support.identification.PartyRef;
import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.common.archetyped.FeederAudit;
import org.openehr.rm.common.archetyped.Link;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;
import org.openehr.rm.FullConstructor;
import org.openehr.rm.Attribute;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Generic description of a role performed by an actor. The role
 * corresponds to a competency of the party. Roles are used to define the
 * responsibilities undertaken by a party for a purpose. Roles should have
 * credentials qualifying the performer to perform the role.
 *
 * @author Goran Pestana
 * @version 1.0
 */
public class Role extends Party {

    protected Role() {
    }

    /**
     * Constructs a Role
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
     * @param capabilities         null if not specified
     * @param timeValidity         null if not specified
     * @param performer
     * @throws IllegalArgumentException if name null or archetypeNodeId null,
     *                                  or links not null and empty,
     *                                  or uid is null, or identities is null
     *                                  or empty, or contacts is empty,
     *                                  or capabilities is empty,
     *                                  or performer is null
     */
    @FullConstructor
            public Role(@Attribute(name = "uid", required = true) UIDBasedID uid,
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
                        @Attribute(name = "capabilities") List<Capability> capabilities,
                        @Attribute(name = "timeValidity") DvInterval<DvDate> timeValidity,
                        @Attribute(name = "performer", required = true) PartyRef performer) {

        super(uid, archetypeNodeId, name, archetypeDetails, feederAudit, links,
                identities, contacts, relationships, reverseRelationships,
                details);

        if (capabilities != null && capabilities.size() == 0) {
            throw new IllegalArgumentException("Empty capabilities");
        }
        if (performer == null) {
            throw new IllegalArgumentException("Null performer");
        }
        this.capabilities = capabilities;
        this.timeValidity = timeValidity;
        this.performer = performer;
    }

    public List<Capability> getCapabilities() {
        return capabilities;
    }

    protected void setCapabilities(List<Capability> capabilities) {
        this.capabilities = capabilities;
    }

    public DvInterval<DvDate> getTimeValidity() {
        return timeValidity;
    }

    protected void setTimeValidity(DvInterval<DvDate> timeValidity) {
        this.timeValidity = timeValidity;
    }

    public PartyRef getPerformer() {
        return performer;
    }

    protected void setPerformer(PartyRef performer) {
        this.performer = performer;
    }

    /**
     * Equals if two roles has same values
     *
     * @param o
     * @return equals if true
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!( o instanceof Role )) return false;
        if (!super.equals(o)) return false;

        final Role role = (Role) o;
        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(capabilities, role.capabilities)
                .append(timeValidity, role.timeValidity)
                .append(performer, role.performer)
                .isEquals();
    }

    /**
     * Return a hash code of this role
     *
     * @return hash code
     */
    public int hashCode() {
        return new HashCodeBuilder(17, 31)
                .appendSuper(super.hashCode())
                .append(capabilities)
                .append(timeValidity)
                .append(performer)
                .toHashCode();
    }


    private List<Capability> capabilities;
    private DvInterval<DvDate> timeValidity;
    private PartyRef performer;
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
 *  The Original Code is Role.java
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