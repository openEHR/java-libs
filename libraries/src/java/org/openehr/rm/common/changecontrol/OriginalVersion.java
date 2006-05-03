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
			T data, AuditDetails commitAudit, ObjectReference contribution,
            Set<ObjectVersionID> otherInputVersionUids, List<Attestation> attestations,
            DvCodedText lifecycleState, boolean isMerged, 
            TerminologyService terminologyService) {
		super(uid, precedingVersionID, data, commitAudit, contribution);
        if (attestations != null && attestations.isEmpty()) {
            throw new IllegalArgumentException("empty attestations");
        }
        if (lifecycleState == null) {
            throw new IllegalArgumentException("null lifecycleState");
        }
        if ((otherInputVersionUids == null) == isMerged ) {
        		throw new IllegalArgumentException("breach of isMerged validity");
        }
        if (terminologyService == null) {
            throw new IllegalArgumentException("null terminologyService");
        }
        if (!terminologyService.terminology(TerminologyService.OPENEHR)
                .hasCodeForGroupName(
                        lifecycleState.getDefiningCode(),
                        "version lifecycle state", "en")) {
            throw new IllegalArgumentException(
                    "unknown lifecycleState: " + lifecycleState);
        }
	}

    /**
     * Lifecycle state of the content item in this version
     *
     * @return state
     */
    public DvCodedText getLifecycleState() {
        return lifeCycleState;
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

	void setLifeCycleState(DvCodedText lifeCycleState) {
		this.lifeCycleState = lifeCycleState;
	}

	void setOtherInputVersionUids(Set<ObjectVersionID> otherInputVersionUids) {
		this.otherInputVersionUids = otherInputVersionUids;
	}	
	// POJO end
	
	/* fields */
	private Set<ObjectVersionID> otherInputVersionUids;
	private T data;
	private List<Attestation> attestations;
	private DvCodedText lifeCycleState;
	private boolean isMerged;

}
