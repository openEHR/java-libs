/*
 * component:   "openEHR Reference Implementation"
 * description: "Class AuditDetails"
 * keywords:    "archetype"
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
package org.openehr.am.archetype.description;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.openehr.rm.datatypes.text.DvCodedText;

import java.util.Calendar;

/**
 * Revision history information for one committal of the archetype to a
 * repository.
 *
 * @author Rong Chen
 * @version 1.0
 */
public class AuditDetails {

    /**
     * Constructs a AuditDetails
     *
     * @param committer
     * @param committerOrganisation
     * @param timeCommitted
     * @param revision
     * @param reason
     * @param changeType
     * @throws IllegalArgumentException
     */
    public AuditDetails(String committer, String committerOrganisation,
                        Calendar timeCommitted, String revision, String reason,
                        DvCodedText changeType) {
        // todo: fix invariants
        this.committer = committer;
        this.committerOrganisation = committerOrganisation;
        this.timeCommitted = timeCommitted;
        this.revision = revision;
        this.reason = reason;
        this.changeType = changeType;
    }

    /**
     * Identification of the author of the main content of this archetype.
     *
     * @return committer
     */
    public String getCommitter() {
        return committer;
    }

    /**
     * Identification of the committer s organisation.
     *
     * @return committer organisation
     */
    public String getCommitterOrganisation() {
        return committerOrganisation;
    }

    /**
     * Date/time of this change
     *
     * @return calendar
     */
    public Calendar getTimeCommitted() {
        return timeCommitted;
    }

    /**
     * Revision corresponding to this change. Various kinds of change cause
     * only a new revision, not a version change, for example, adding a new
     * translation of the ontology, changing meta-data, and certain changes to
     * the archetype definition itself.
     *
     * @return revision
     */
    public String getRevision() {
        return revision;
    }

    /**
     * Natural language reason for change.
     *
     * @return reason
     */
    public String getReason() {
        return reason;
    }

    /**
     * Type of change. Coded using the openEHR Terminology "audit change type"
     * group.
     *
     * @return change type
     */
    public DvCodedText getChangeType() {
        return changeType;
    }

    /**
     * String representation of this object
     *
     * @return string form
     */
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    /* fields */
    private String committer;
    private String committerOrganisation;
    private Calendar timeCommitted;
    private String revision;
    private String reason;
    private DvCodedText changeType;

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
 *  The Original Code is AuditDetails.java
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