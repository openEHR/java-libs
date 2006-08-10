/**
 * 
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
