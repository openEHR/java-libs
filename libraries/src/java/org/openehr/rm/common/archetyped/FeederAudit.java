/*
 * component:   "openEHR Reference Implementation"
 * description: "Class FeederAudit"
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
package org.openehr.rm.common.archetyped;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.openehr.rm.RMObject;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.support.terminology.TerminologyService;

/**
 * Audit details for a feeder system. Instances of this class are
 * immutalbe
 *
 * @author Rong Chen
 * @version 1.0
 */
public final class FeederAudit extends RMObject {

    /**
     * Constrcuts a FeederAudit
     *
     * @param systemID           not null or empty
     * @param committer          if not null then not empty
     * @param timeCommitted      null if not present
     * @param changeType         must be a valid type
     * @param description        null if not present
     * @param terminologyService not null
     * @throws IllegalArgumentException
     */
    public FeederAudit(String systemID, String committer,
                       DvDateTime timeCommitted, DvCodedText changeType,
                       DvText description,
                       TerminologyService terminologyService) {
        if (StringUtils.isEmpty(systemID)) {
            throw new IllegalArgumentException("empty systemID");
        }
        if (StringUtils.isEmpty(committer)) {
            throw new IllegalArgumentException("empty committer");
        }
        if (changeType == null) {
            throw new IllegalArgumentException("null changeType");
        }
        if (terminologyService == null) {
            throw new IllegalArgumentException("null terminologyService");
        }
        if (!terminologyService.terminology(TerminologyService.OPENEHR)
                .hasCodeForGroupName(changeType.getDefiningCode(),
                        "audit change type", "en")) {
            throw new IllegalArgumentException(
                    "unknown changeType: " + changeType);
        }
        this.systemID = systemID;
        this.committer = committer;
        this.timeCommitted = timeCommitted;
        this.changeType = changeType;
        this.description = description;
    }


    /**
     * Identity of the system where the item was originally committed
     *
     * @return systemID
     */
    public String getSystemID() {
        return systemID;
    }

    /**
     * Identity of party who committed the item
     *
     * @return committer null if not present
     */
    public String getCommitter() {
        return committer;
    }

    /**
     * Time of committal of the item
     *
     * @return tiem of committal null if not present
     */
    public DvDateTime getTimeCommitted() {
        return timeCommitted;
    }

    /**
     * Type of change, eg "creation", "correction", "modification",
     * "synthesis" etc. Coded using the openEHR Terminology
     * "audit change type" group.
     *
     * @return change type
     */
    public DvCodedText getChangeType() {
        return changeType;
    }

    /**
     * Description of change from original system
     *
     * @return description
     */
    public DvText getDescription() {
        return description;
    }

    /**
     * Equals if have same values
     *
     * @param o
     * @return true if equals
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!( o instanceof FeederAudit )) return false;

        final FeederAudit fa = (FeederAudit) o;

        return new EqualsBuilder()
                .append(systemID, fa.systemID)
                .append(committer, fa.committer)
                .append(timeCommitted, fa.timeCommitted)
                .append(changeType, fa.changeType)
                .append(description, fa.description)
                .isEquals();
    }

    /**
     * Return a hashcode of this object
     *
     * @return hashcode
     */
    public int hashCode() {
        return new HashCodeBuilder(7, 23)
                .append(systemID)
                .append(committer)
                .append(timeCommitted)
                .append(changeType)
                .append(description)
                .toHashCode();
    }

    // POJO start
    FeederAudit() {
    }

    void setSystemID(String systemID) {
        this.systemID = systemID;
    }

    void setCommitter(String committer) {
        this.committer = committer;
    }

    void setTimeCommitted(DvDateTime timeCommitted) {
        this.timeCommitted = timeCommitted;
    }

    void setChangeType(DvCodedText changeType) {
        this.changeType = changeType;
    }

    void setDescription(DvText description) {
        this.description = description;
    }
    // POJO end

    /* fields */
    private String systemID;
    private String committer;
    private DvDateTime timeCommitted;
    private DvCodedText changeType;
    private DvText description;
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
 *  The Original Code is FeederAudit.java
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