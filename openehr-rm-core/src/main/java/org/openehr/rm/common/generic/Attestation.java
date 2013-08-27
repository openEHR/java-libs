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
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/common/generic/Attestation.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 22:20:08 +0100 (Wed, 12 Oct 2005) $"
 */
package org.openehr.rm.common.generic;

import org.openehr.rm.Attribute;
import org.openehr.rm.FullConstructor;
import org.openehr.rm.datatypes.encapsulated.DvMultimedia;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.datatypes.text.DvText;
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
public class Attestation extends AuditDetails {

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
	@FullConstructor    
    public Attestation(
    		@Attribute(name = "systemId", required = true)String systemId, 
    		@Attribute(name = "committer", required = true)PartyProxy committer,
    		@Attribute(name = "timeCommitted", required = true)DvDateTime timeCommitted, 
    		@Attribute(name = "changeType", required = true)DvCodedText changeType,
    		@Attribute(name = "description")DvText description, 
            @Attribute(name = "terminologyService", system = true)TerminologyService terminologyService,
            @Attribute(name = "attestedView")DvMultimedia attestedView, 
            @Attribute(name = "proof")String proof, 
            @Attribute(name = "items")Set<DvEHRURI> items,
            @Attribute(name = "reason", required = true)DvText reason, 
            @Attribute(name = "isPending", required = true)boolean isPending) {
    		super(systemId, committer, timeCommitted, changeType, description, terminologyService);
        if (items != null && items.isEmpty()) {
            throw new IllegalArgumentException("empty items");
        }
        if (reason == null) {
            throw new IllegalArgumentException("null reason");
        }        
        if (reason instanceof DvCodedText) {
        		DvCodedText reasonCText = (DvCodedText) reason;
        		if (!terminologyService.terminology(TerminologyService.OPENEHR)
                .codesForGroupName("attestation reason", "en")
                .contains(reasonCText.getDefiningCode())) {
        			throw new IllegalArgumentException("unkown reason: " + reason);
        		}
        }
        this.attestedView = attestedView;
        this.proof = proof;
        this.items = new HashSet<DvEHRURI>(items);
        this.reason = reason;
        this.isPending = isPending;
    }

    /**
     * Optional visual representation of content attested
     * e.g. screen image
     */
    public DvMultimedia getAttestedView() {
        return attestedView;
    }
    
    /**
     * Proof of attestation
     *
     * @return proof
     */
    public String getProof() {
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
     * Reason of this attestation. Optionally coded by the openEHR Terminology
     * group "attestation reason".
     *
     * @return reason
     */
    public DvText getReason() {
        return reason;
    }

    /**
     * True if this attestation is outstanding; False meants it has been 
     * completed
     *
     * @return isPending
     */
    public boolean isPending() {
    	return isPending;
    }
    
    // POJO start
    Attestation() {
    }

    void setAttestedView(DvMultimedia attestedView) {
        this.attestedView = attestedView;
    }
    
    void setProof(String proof) {
        this.proof = proof;
    }

    void setItems(Set<DvEHRURI> items) {
        this.items = items;
    }

    void setReason(DvText status) {
        this.reason = status;
    }
    
    void setIsPending(boolean isPending) {
    		this.isPending = isPending;
    }
    // POJO end

    /* fields */
    private DvMultimedia attestedView;
    private String proof;
    private Set<DvEHRURI> items;
    private DvText reason;
    private boolean isPending;
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