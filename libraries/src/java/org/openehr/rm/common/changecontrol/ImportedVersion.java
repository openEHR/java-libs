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
	 * @param commitAudit
	 * @param contribution
	 * @param originalVersion
	 */
	public ImportedVersion(AuditDetails commitAudit, ObjectReference contribution,             
              OriginalVersion<T> originalVersion) {
		if (originalVersion == null) {
			throw new IllegalArgumentException("null original version");
		}
		if (commitAudit == null) {
			throw new IllegalArgumentException("null commitAudit");
		}
		if (contribution == null) {
			throw new IllegalArgumentException("null contribution");
		}
		setUid(originalVersion.getUid());
		setPrecedingVersionID(originalVersion.getPrecedingVersionID());
		setData(originalVersion.getData());
		setCommitAudit(commitAudit);
		setContribution(contribution);
		this.originalVersion = originalVersion;
	}

	public ObjectVersionID getUid() {
		return originalVersion.getUid();
	}

	public ObjectVersionID getPrecedingVersionID() {
		return originalVersion.getPrecedingVersionID();
	}
	
	public T getData() {
		return originalVersion.getData();
	}
	
	/**
	 * The original Version object that was imported.
	 * 
	 * @return item
	 */
	public OriginalVersion<T> getOriginalVersion() {
		return originalVersion;
	}
	
	/**
	 * Computed verions of inheritance precursor, derived as originalVersion.uid
	 *
	 */
	public ObjectVersionID uid() {
		return originalVersion.getUid();
	}
	
	/**
	 * Computed version of inheritance prevursor, derived as 
	 * originalVersion.precidingVersionUid
	 *
	 */
	public ObjectVersionID precedingVersionUid() {
		return originalVersion.getPrecedingVersionID();
	}
	
	//POJO start
	ImportedVersion() {
	}

	void setOriginalVersion(OriginalVersion<T> originalVersion) {
		this.originalVersion = originalVersion;
	}
	//POJO end
	
	/* fields */
	private OriginalVersion<T> originalVersion;

}
