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

import org.openehr.rm.common.generic.Attestation;
import org.openehr.rm.common.generic.AuditDetails;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.support.identification.ObjectReference;
import org.openehr.rm.support.identification.ObjectVersionID;
import org.openehr.rm.support.terminology.TerminologyService;

/**
 * Versionable object that has been copied from another location and imported 
 * into a local version container
 *
 * @author Yin Su Lim
 * @version 1.0
 */
public final class ImportedVersion<T> extends Version<T> {

	/**
	 * Constructs an ImportedVersion
	 * 
	 * @param uid
	 * @param precedingVersionID
	 * @param data
	 * @param attestations
	 * @param commitAudit
	 * @param contribution
	 * @param lifecycleState
	 * @param originalCreateAudit
	 * @param terminologyService
	 */
	public ImportedVersion(ObjectVersionID uid, ObjectVersionID precedingVersionID,
	          T data, List<Attestation> attestations,
              AuditDetails commitAudit, ObjectReference contribution,
              DvCodedText lifecycleState, AuditDetails originalCreateAudit,
              TerminologyService terminologyService) {
		super(uid, precedingVersionID, data, attestations, commitAudit,
				contribution, lifecycleState, terminologyService);
		if (originalCreateAudit == null) {
			throw new IllegalArgumentException("null originalCreateAudit");
		}
		this.originalCreateAudit = originalCreateAudit;
	}

	/**
	 * Audit trail corresponding to the creation and first-time
	 * committal of this version to the VersionedOjbect where it was
	 * first located.
	 * 
	 * @return originalCreateAudit
	 */
	public AuditDetails getOriginalCreateAudit() {
		return originalCreateAudit;
	}
	
	@Override
	public AuditDetails createAudit() {
		return originalCreateAudit;
	}
	
	
	//POJO start
	ImportedVersion() {
	}

	void setOriginalCreateAudit(AuditDetails originalCreateAudit) {
		this.originalCreateAudit = originalCreateAudit;
	}
	//POJO end
	
	/* fields */
	private AuditDetails originalCreateAudit;

}
