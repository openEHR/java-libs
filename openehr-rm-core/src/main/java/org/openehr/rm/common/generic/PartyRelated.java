/*
 * component:   "openEHR Reference Implementation"
 * description: "Class PartyRelated"
 * keywords:    "common"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/common/generic/PartyRelated.java $"
 * revision:    "$LastChangedRevision: 53 $"
 * last_change: "$LastChangedDate: 2006-08-11 13:20:08 +0100 (Fri, 11 Aug 2006) $"
 */
package org.openehr.rm.common.generic;

import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.openehr.rm.Attribute;
import org.openehr.rm.FullConstructor;
import org.openehr.rm.support.identification.PartyRef;
import org.openehr.rm.datatypes.basic.DvIdentifier;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.support.terminology.TerminologyService;

/**
 * Party and relationship of the party. Immutable.
 *
 * @author Rong Chen
 * @version 1.0
 */
public final class PartyRelated extends PartyIdentified {

    /**
     * Construts a RelatedParty by party and relationsihp
     *
     * @param party
     * @param relationship
     * @param terminologyService not null
     * @throws IllegalArgumentException if relationship invalid
     */
	@FullConstructor
    public PartyRelated(
    		@Attribute(name = "externalRef")PartyRef externalRef, 
    		@Attribute(name = "name")String name,
    		@Attribute(name = "identifiers")List<DvIdentifier> identifiers, 
    		@Attribute(name = "relationship", required = true)DvCodedText relationship,
    		@Attribute(name = "terminologyService")TerminologyService terminologyService) {
    		super(externalRef, name, identifiers);
        if (relationship == null) {
            throw new IllegalArgumentException("null relationship");
        }
        if (!terminologyService.terminology(TerminologyService.OPENEHR)
                .codesForGroupName("related party relationship", "en")
                .contains(relationship.getDefiningCode())) {
            throw new IllegalArgumentException(
                    "unkown relationship: " + relationship);
        }
        this.relationship = relationship;
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
        if (!( o instanceof PartyRelated )) return false;
        if (!super.equals(o)) return false;

        final PartyRelated r = (PartyRelated) o;

        return new EqualsBuilder()
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
                .appendSuper(super.hashCode())
                .append(relationship)
                .toHashCode();
    }

    // POJO start
    PartyRelated() {
    }

    void setRelationship(DvCodedText relationship) {
        this.relationship = relationship;
    }
    // POJO end

    /* fields */
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
 *  The Original Code is PartyRelated.java
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
