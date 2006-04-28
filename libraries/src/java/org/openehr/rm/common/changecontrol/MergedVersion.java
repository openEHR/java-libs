/*
 * component:   "openEHR Reference Implementation"
 * description: "Class Version"
 * keywords:    "common"
 *
 * author:      "Yin Su Lim <y.lim@chime.ucl.ac.uk>"
 * support:     ""
 * copyright:   ""
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/common/changecontrol/ImportedVersion.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2006-03-21 22:20:08 +0100 (Wed, 12 Oct 2005) $"
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
 * Merged Version, recording the list of input versions to the merge 
 * in addition to standard VErsion attributes
 *
 * @author Yin Su Lim
 * @version 1.0
 */
public final class MergedVersion<T> extends Version<T> {

	/**
	 * Contructs MergedVersion
	 * 
	 * @param uid
	 * @param precedingVersionID
	 * @param data
	 * @param attestations
	 * @param commitAudit
	 * @param contribution
	 * @param lifecycleState
	 * @param inputVersionIDs
	 * @param terminologyService
	 */
	public MergedVersion(ObjectVersionID uid, ObjectVersionID precedingVersionID, 
			T data, List<Attestation> attestations, AuditDetails commitAudit,
			ObjectReference contribution, DvCodedText lifecycleState,
			Set<ObjectVersionID> inputVersionIDs, 
			TerminologyService terminologyService) {
		super(uid, precedingVersionID, data, attestations, commitAudit,
				contribution, lifecycleState, terminologyService);
		if (inputVersionIDs == null || inputVersionIDs.size() == 0) {
			throw new IllegalArgumentException("null or empty inputVersionIDs");
		}
		this.inputVersionIDs = inputVersionIDs;
	}
	
	/**
	 * Identifiers of the Versions used as input into this 
	 * merged version. There is no guarantee that the identifiers 
	 * of all content Versions are included here.
	 * 
	 * @return uid of Versions used as input 
	 */
	public Set<ObjectVersionID> getInputVersionIDs() {
		return inputVersionIDs;
	}

	//POJO start
	MergedVersion() {
	}

	void setInputVersionIDs(Set<ObjectVersionID> inputVersionIDs) {
		this.inputVersionIDs = inputVersionIDs;
	}
	//POJO end
	
	/* fields */
	private Set<ObjectVersionID> inputVersionIDs;
}
