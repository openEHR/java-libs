/*
 * component:   "openEHR Reference Implementation"
 * description: "Class Attestation"
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

import org.openehr.rm.RMObject;
import org.openehr.rm.datatypes.encapsulated.DvEncapsulated;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.datatypes.uri.DvEHRURI;
import org.openehr.rm.support.terminology.TerminologyService;

import java.util.*;

/**
 * Record of one or more Parties attesting something.
 * Instances of this class are immutable
 *
 * @author Rong Chen
 * @version 1.0
 */
public class Attestation extends RMObject {

    /**
     * Constructs an Attestation
     *
     * @param participations     not null or empty
     * @param time               not null
     * @param proof
     * @param items              not null or empty
     * @param status             not null and exits
     * @param terminologyService not null
     * @throws IllegalArgumentException
     */
    public Attestation(List<Participation> participations, DvDateTime time,
                       DvEncapsulated proof, Set<DvEHRURI> items,
                       DvCodedText status,
                       TerminologyService terminologyService) {
        if (participations == null || participations.isEmpty()) {
            throw new IllegalArgumentException("null or empty participations");
        }
        if (time == null) {
            throw new IllegalArgumentException("null time");
        }
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("null or empty items");
        }
        if (status == null) {
            throw new IllegalArgumentException("null status");
        }
        if (terminologyService == null) {
            throw new IllegalArgumentException("null terminologyService");
        }
        if (!terminologyService.terminology(TerminologyService.OPENEHR)
                .hasCodeForGroupName(status.getDefiningCode(),
                        "attestation status", "en")) {
            throw new IllegalArgumentException("unkown status: " + status);
        }
        this.participations = new ArrayList<Participation>(participations);
        this.time = time;
        this.proof = proof;
        this.items = new HashSet<DvEHRURI>(items);
        this.status = status;
    }

    /**
     * Pariticipations in this attestation, eg witness,
     * primary authority etc
     *
     * @return unmodifiable list of participations
     */
    public List<Participation> getParticipations() {
        return Collections.unmodifiableList(participations);
    }

    /**
     * Time at which attestation was made
     *
     * @return time
     */
    public DvDateTime getTime() {
        return time;
    }

    /**
     * Proof of attestation
     *
     * @return proof
     */
    public DvEncapsulated getProof() {
        return proof;
    }

    /**
     * Items attested. Although not recommended, these may include
     * fine-grained items which have been attested in some other
     * system. Otherwise it is assumed to be for the entire VERSION
     * with which it is associated.
     *
     * @return unmodifiable set of EHR_URI
     */
    public Set<DvEHRURI> getItems() {
        return Collections.unmodifiableSet(items);
    }

    /**
     * Status of this attestation. Coded by the openEHR Terminology
     * group "attestation status".
     *
     * @return status
     */
    public DvCodedText getStatus() {
        return status;
    }

    // POJO start
    Attestation() {
    }

    void setParticipations(List<Participation> participations) {
        this.participations = participations;
    }

    void setTime(DvDateTime time) {
        this.time = time;
    }

    void setProof(DvEncapsulated proof) {
        this.proof = proof;
    }

    void setItems(Set<DvEHRURI> items) {
        this.items = items;
    }

    void setStatus(DvCodedText status) {
        this.status = status;
    }
    // POJO end

    /* fields */
    private List<Participation> participations;
    private DvDateTime time;
    private DvEncapsulated proof;
    private Set<DvEHRURI> items;
    private DvCodedText status;
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
 *  The Original Code is Attestation.java
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