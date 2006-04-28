/*
 * component:   "openEHR Reference Implementation"
 * description: "Class Version"
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
package org.openehr.rm.common.changecontrol;

import org.apache.commons.lang.StringUtils;
import org.openehr.rm.RMObject;
import org.openehr.rm.common.generic.Attestation;
import org.openehr.rm.common.generic.AuditDetails;
import org.openehr.rm.support.identification.HierarchicalObjectID;
import org.openehr.rm.support.identification.ObjectID;
import org.openehr.rm.support.identification.ObjectReference;
import org.openehr.rm.datatypes.basic.DvState;
import org.openehr.rm.support.terminology.TerminologyService;

import java.util.List;

/**
 * Versionable objects, with an audit trail containing details of
 * change and list of attestations
 *
 * @author Rong Chen
 * @version 1.0
 */
public class Version <T> extends RMObject {

    /**
     * Constructs a Version
     *
     * @param data
     * @param attestations        null if not specified, not empty
     * @param audit               not null
     * @param versionID           not null or empty
     * @param precedingVersionID  not null or empty
     * @param versionRepositoryID not null
     * @param contribution        not null
     * @param lifecycleState      not null and exists
     * @param terminologyService  not null
     * @throws IllegalArgumentException
     */
    public Version(T data, List<Attestation> attestations,
                   AuditDetails audit, String versionID,
                   String precedingVersionID,
                   ObjectReference versionRepositoryID,
                   ObjectReference contribution,
                   DvState lifecycleState,
                   TerminologyService terminologyService) {
        if (data == null) {
            throw new IllegalArgumentException("null data");
        }
        if (audit == null) {
            throw new IllegalArgumentException("null audit");
        }
        if (attestations != null && attestations.isEmpty()) {
            throw new IllegalArgumentException("empty attestations");
        }
        if (StringUtils.isEmpty(versionID)) {
            throw new IllegalArgumentException("empty versionID");
        }
        if (StringUtils.isEmpty(precedingVersionID)) {
            throw new IllegalArgumentException("empty precedingVersionID");
        }
        if (versionRepositoryID == null) {
            throw new IllegalArgumentException("null versionRepositoryID");
        }
        if (contribution == null) {
            throw new IllegalArgumentException("null contribution");
        }
        if (lifecycleState == null) {
            throw new IllegalArgumentException("null lifecycleState");
        }
        if (terminologyService == null) {
            throw new IllegalArgumentException("null terminologyService");
        }
        if (!terminologyService.terminology(TerminologyService.OPENEHR)
                .hasCodeForGroupName(
                        lifecycleState.getValue().getDefiningCode(),
                        "version lifecycle state", "en")) {
            throw new IllegalArgumentException(
                    "unknown lifecycleState: " + lifecycleState);
        }
        this.data = data;
        this.attestations = attestations;
        this.audit = audit;
        this.versionID = versionID;
        this.precedingVersionID = precedingVersionID;
        this.versionRepositoryID = versionRepositoryID;
        this.contribution = contribution;
        this.lifecycleState = lifecycleState;
    }

    /**
     * Unique identifier of this version, derived from version
     * repository id and version id.
     *
     * @return unique ID
     */
    public ObjectID uid() {
        return new HierarchicalObjectID(null,
                versionRepositoryID.getId().getValue(), versionID);
    }

    /**
     * The data being versioned
     *
     * @return data
     */
    public T getData() {
        return data;
    }

    /**
     * List of attestations relating this version.
     *
     * @return attestations or null if unspecified
     */
    public List<Attestation> getAttestations() {
        return attestations;
    }

    /**
     * Audit trail of this version
     *
     * @return version audit
     */
    public AuditDetails getAudit() {
        return audit;
    }

    /**
     * Unique identifier of this version
     *
     * @return versionID
     */
    public String getVersionID() {
        return versionID;
    }

    /**
     * Unique identifier of the version on which this version was
     * based. May be the pseudo-version "first"
     *
     * @return preceding versionID
     */
    public String getPrecedingVersionID() {
        return precedingVersionID;
    }

    /**
     * A copy of the uid of the version repository to which this
     * version was added
     *
     * @return version repositoryID
     */
    public ObjectReference getVersionRepositoryID() {
        return versionRepositoryID;
    }

    /**
     * Contribution in which this version was added
     *
     * @return contribution
     */
    public ObjectReference getContribution() {
        return contribution;
    }

    /**
     * Lifecycle state of the content item in this version
     *
     * @return state
     */
    public DvState getLifecycleState() {
        return lifecycleState;
    }

    // POJO start
    Version() {
    }

    void setData(T data) {
        this.data = data;
    }

    void setAttestations(List<Attestation> attestations) {
        this.attestations = attestations;
    }

    void setAudit(AuditDetails audit) {
        this.audit = audit;
    }

    void setVersionID(String versionID) {
        this.versionID = versionID;
    }

    void setPrecedingVersionID(String precedingVersionID) {
        this.precedingVersionID = precedingVersionID;
    }

    void setVersionRepositoryID(ObjectReference versionRepositoryID) {
        this.versionRepositoryID = versionRepositoryID;
    }

    void setContribution(ObjectReference contribution) {
        this.contribution = contribution;
    }

    void setLifecycleState(DvState lifecycleState) {
        this.lifecycleState = lifecycleState;
    }
    // POJO end

    /* fields */
    private T data;
    private List<Attestation> attestations;
    private AuditDetails audit;
    private String versionID;
    private String precedingVersionID;
    private ObjectReference versionRepositoryID;
    private ObjectReference contribution;
    private DvState lifecycleState;
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
 *  The Original Code is Version.java
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