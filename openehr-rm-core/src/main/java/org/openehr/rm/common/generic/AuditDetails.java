/*
 * component:   "openEHR Reference Implementation"
 * description: "Class AuditDetails"
 * keywords:    "common"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/BRANCHES/RM-1.0-update/libraries/src/java/org/openehr/rm/common/generic/AuditDetails.java $"
 * revision:    "$LastChangedRevision: 50 $"
 * last_change: "$LastChangedDate: 2006-08-10 13:21:46 +0200 (Thu, 10 Aug 2006) $"
 */
package org.openehr.rm.common.generic;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.openehr.rm.FullConstructor;
import org.openehr.rm.RMObject;
import org.openehr.rm.support.terminology.TerminologyService;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.datatypes.text.DvText;

/**
 * The set of attributes required to document a new version of
 * something. This class can be inherited or used in a client/supplier
 * relationship to provide audit trail details to another class.
 *
 * @author Rong Chen
 * @version 1.0
 */
public class AuditDetails extends RMObject {

    /**
     * Constructs a AudiDetails
     *
     * @param systemId           not null or empty
     * @param committer          not null
     * @param timeCommitted      not null
     * @param changeType
     * @param description
     * @param terminologyService
     * @throws IllegalArgumentException
     */
	@FullConstructor
    public AuditDetails(String systemId, PartyProxy committer,
                        DvDateTime timeCommitted, DvCodedText changeType,
                        DvText description,
                        TerminologyService terminologyService) {
        if (StringUtils.isEmpty(systemId)) {
            throw new IllegalArgumentException("empty systemId");
        }
        if (committer == null) {
            throw new IllegalArgumentException("null comitter");
        }
        if (timeCommitted == null) {
            throw new IllegalArgumentException("null timeCommitted");
        }
        if (changeType == null) {
            throw new IllegalArgumentException("null changeType");
        }
        if (terminologyService == null) {
            throw new IllegalArgumentException("null terminologyService");
        }
        if (!terminologyService.terminology(TerminologyService.OPENEHR)
        		.codesForGroupName("audit change type", "en")
                .contains(changeType.getDefiningCode())) {
            throw new IllegalArgumentException("unknown change type: "
                    + changeType.getDefiningCode());
        }
        this.systemId = systemId;
        this.committer = committer;
        this.timeCommitted = timeCommitted;
        this.changeType = changeType;
        this.description = description;
    }

    /**
     * Identity of the system where the change was committed
     *
     * @return systemId
     */
    public String getSystemId() {
        return systemId;
    }

    /**
     * Identity of party who committed the item
     *
     * @return committer
     */
    public PartyProxy getCommitter() {
        return committer;
    }

    /**
     * Time of committal of the item
     *
     * @return time of committal
     */
    public DvDateTime getTimeCommitted() {
        return timeCommitted;
    }

    /**
     * Type of change. Coded using the openEHR Terminology
     * audit change type group.
     *
     * @return change type
     */
    public DvCodedText getChangeType() {
        return changeType;
    }

    /**
     * Reason for committal
     *
     * @return description or null if unspecified
     */
    public DvText getDescription() {
        return description;
    }

    // POJO start
    protected AuditDetails() {
    }

    void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    void setChangeType(DvCodedText changeType) {
        this.changeType = changeType;
    }

    void setDescription(DvText description) {
        this.description = description;
    }

    void setCommitter(PartyProxy committer) {
        this.committer = committer;
    }

    void setTimeCommitted(DvDateTime timeCommitted) {
        this.timeCommitted = timeCommitted;
    }
    // POJO end

    /**
     * Equals if have same values
     *
     * @param o
     * @return true if equals
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!( o instanceof AuditDetails )) return false;

        final AuditDetails ad = (AuditDetails) o;

        return new EqualsBuilder()
                .append(systemId, ad.systemId)
                .append(committer, ad.committer)
                .append(timeCommitted, ad.timeCommitted)
                .append(changeType, ad.changeType)
                .append(description, ad.description)
                .isEquals();
    }

    /**
     * Return a hashcode of this object
     *
     * @return hashcode
     */
    public int hashCode() {
        return new HashCodeBuilder(5, 23)
                .append(systemId)
                .append(committer)
                .append(timeCommitted)
                .append(changeType)
                .append(description)
                .toHashCode();
    }

    /* fields */
    private String systemId;
    private PartyProxy committer;
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