/*
 * component:   "openEHR Reference Implementation"
 * description: "Class Contribution"
 * keywords:    "common"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/BRANCHES/RM-1.0-update/libraries/src/java/org/openehr/rm/common/changecontrol/Contribution.java $"
 * revision:    "$LastChangedRevision: 29 $"
 * last_change: "$LastChangedDate: 2006-04-29 00:34:13 +0200 (Sat, 29 Apr 2006) $"
 */
package org.openehr.rm.common.changecontrol;

import org.openehr.rm.common.generic.AuditDetails;
import org.openehr.rm.support.identification.ObjectID;
import org.openehr.rm.support.identification.ObjectReference;
import org.openehr.rm.RMObject;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.util.Set;

/**
 * Documents a contribution of one or more versions added to a
 * change-controlled repository.
 *
 * @author Rong Chen
 * @version 1.0
 */
public class Contribution extends RMObject {

    /**
     * Constructs a contribution
     *
     * @param uid      not null
     * @param versions not null or empty
     * @param audit    not null
     */
    public Contribution(ObjectID uid, Set<ObjectReference> versions,
                        AuditDetails audit) {
        if (uid == null) {
            throw new IllegalArgumentException("null uid");
        }
        if (audit == null) {
            throw new IllegalArgumentException("null audit");
        }
        if (audit.getDescription() == null) {
            throw new IllegalArgumentException("null audit description");
        }
        if (versions == null || versions.isEmpty()) {
            throw new IllegalArgumentException("invalid versions");
        }
        this.uid = uid;
        this.versions = versions;
        this.audit = audit;
    }

    /**
     * Unique identifier for this contribution.
     *
     * @return uid
     */
    public ObjectID getUid() {
        return uid;
    }

    /**
     * Audit trail of this Contribution as a whole
     *
     * @return audit details
     */
    public AuditDetails getAudit() {
        return audit;
    }

    /**
     * Set of references to versions causing changes to this EHR.
     * Each contribution contains a list of versions, which may
     * include paths pointing to any number of VERSION items,
     * ie items of type COMPOSITION and FOLDER.
     *
     * @return <code>Set</code> of <code>ObjectReference</code>
     */
    public Set<ObjectReference> getVersions() {
        return versions;
    }

    /**
     * Equals if have same values
     *
     * @param o
     * @return true if equals
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!( o instanceof Contribution )) return false;

        final Contribution contribution = (Contribution) o;

        return new EqualsBuilder()
                .append(uid, contribution.uid)
                .append(versions, contribution.versions)
                .append(audit, contribution.audit)
                .isEquals();
    }

    /**
     * Return a hashcode of this object
     *
     * @return hashcode
     */
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(uid)
                .append(versions)
                .append(audit)
                .toHashCode();
    }

    // POJO start
    Contribution() {
    }

    void setUid(ObjectID uid) {
        this.uid = uid;
    }

    void setAudit(AuditDetails audit) {
        this.audit = audit;
    }

    void setVersions(Set<ObjectReference> versions) {
        this.versions = versions;
    }
    // POJO end

    /* fields */
    private ObjectID uid;
    private Set<ObjectReference> versions;
    private AuditDetails audit;
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
 *  The Original Code is Contribution.java
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