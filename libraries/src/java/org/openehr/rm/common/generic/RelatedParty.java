/*
 * component:   "openEHR Reference Implementation"
 * description: "Class RelatedParty"
 * keywords:    "common"
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
package org.openehr.rm.common.generic;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.openehr.rm.RMObject;
import org.openehr.rm.support.identification.PartyReference;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.support.terminology.TerminologyService;

/**
 * Party and relationship of the party. Immutable.
 *
 * @author Rong Chen
 * @version 1.0
 */
public final class RelatedParty extends RMObject {

    /**
     * Construts a RelatedParty by party and relationsihp
     *
     * @param party
     * @param relationship
     * @param terminologyService not null
     * @throws IllegalArgumentException if relationship invalid
     */
    public RelatedParty(PartyReference party, DvCodedText relationship,
                        TerminologyService terminologyService) {
        if (relationship == null) {
            throw new IllegalArgumentException("null relationship");
        }
        if (!terminologyService.terminology(TerminologyService.OPENEHR)
                .hasCodeForGroupName(relationship.getDefiningCode(),
                        "related party relationship", "en")) {
            throw new IllegalArgumentException(
                    "unkown relationship: " + relationship);
        }
        this.party = party;
        this.relationship = relationship;
    }

    /**
     * Id of Party
     *
     * @return party null if not present
     */
    public PartyReference getParty() {
        return party;
    }

    /**
     * Relationship of subject of this ENTRY to the subject of the
     * record. May be coded. If it is the patient, coded as "self".
     *
     * @return relationship
     */
    public DvCodedText getRelationship() {
        return relationship;
    }

    /**
     * Equals if two has same values
     *
     * @param o
     * @return true if equals
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!( o instanceof RelatedParty )) return false;

        final RelatedParty r = (RelatedParty) o;

        return new EqualsBuilder()
                .append(party, r.party)
                .append(relationship, r.relationship)
                .isEquals();
    }

    /**
     * Return a hash code of this object
     *
     * @return hash code
     */
    public int hashCode() {
        return new HashCodeBuilder(13, 59)
                .append(party)
                .append(relationship)
                .toHashCode();
    }

    // POJO start
    RelatedParty() {
    }

    void setParty(PartyReference party) {
        this.party = party;
    }

    void setRelationship(DvCodedText relationship) {
        this.relationship = relationship;
    }
    // POJO end

    /* fields */
    private PartyReference party;
    private DvCodedText relationship;
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
 *  The Original Code is RelatedParty.java
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