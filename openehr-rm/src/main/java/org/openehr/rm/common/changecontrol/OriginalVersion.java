/*
 * component:   "openEHR Reference Implementation"
 * description: "Class OriginalVersion"
 * keywords:    "common"
 *
 * author:      "Yin Su Lim <y.lim@chime.ucl.ac.uk>"
 * support:     "CHIME, UCL"
 * copyright:   "Copyright (c) 2006 UCL, UK"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/common/changecontrol/OriginalVersion.java $"
 * revision:    "$LastChangedRevision: 53 $"
 * last_change: "$LastChangedDate: 2006-08-11 13:20:08 +0100 (Fri, 11 Aug 2006) $"
 */
package org.openehr.rm.common.changecontrol;

import java.util.List;
import java.util.Set;

import org.openehr.rm.common.generic.Attestation;
import org.openehr.rm.common.generic.AuditDetails;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.support.identification.ObjectReference;
import org.openehr.rm.support.identification.ObjectVersionID;
import org.openehr.rm.support.terminology.TerminologyService;

/**
 * @author yinsulim
 *
 */
public class OriginalVersion<T> extends Version<T> {

	/**
	 * @param uid
	 * @param precedingVersionID
	 * @param data
	 * @param attestations
	 * @param commitAudit
	 * @param contribution
	 * @param lifecycleState
	 * @param terminologyService
	 */
	public OriginalVersion(ObjectVersionID uid, ObjectVersionID precedingVersionID,
            T data, DvCodedText lifeCycleState, AuditDetails commitAudit, 
            ObjectReference contribution, String signature, 
            Set<ObjectVersionID> otherInputVersionUids, List<Attestation> attestations,
            boolean isMerged, TerminologyService terminologyService) {
            
            super(uid, precedingVersionID, data, lifeCycleState, commitAudit, 
                    contribution, signature, terminologyService);
            if (attestations != null && attestations.isEmpty()) {
                throw new IllegalArgumentException("empty attestations");
            }
            if ((otherInputVersionUids == null) == isMerged ) {
                throw new IllegalArgumentException("breach of isMerged validity");
            }
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
     * True if this Version was created from more than 
     * just the preceding (checked out) version.
     */
    public boolean getIsMerged() {
    		return isMerged;
    }
    
    /**
     * Identifiers of other versions whose content was merged 
     * into this version, if any.
     */
    public Set<ObjectVersionID> getOtherInputVersionUids() {
    		return otherInputVersionUids;
    }
    
    // POJO start
    OriginalVersion() {
    }

    void setAttestations(List<Attestation> attestations) {
            this.attestations = attestations;
    }

    void setIsMerged(boolean isMerged) {
            this.isMerged = isMerged;
    }

    void setOtherInputVersionUids(Set<ObjectVersionID> otherInputVersionUids) {
            this.otherInputVersionUids = otherInputVersionUids;
    }	
    // POJO end

    /* fields */
    private Set<ObjectVersionID> otherInputVersionUids;
    private List<Attestation> attestations;
    private boolean isMerged;

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
 *  The Original Code is OriginalVersion.java
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