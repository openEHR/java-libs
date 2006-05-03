package org.openehr.rm.common.directory;

import java.util.Set;

import org.openehr.rm.common.changecontrol.OriginalVersion;
import org.openehr.rm.common.changecontrol.Version;
import org.openehr.rm.common.changecontrol.VersionedObject;
import org.openehr.rm.common.generic.AuditDetails;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.support.identification.HierarchicalObjectID;
import org.openehr.rm.support.identification.ObjectReference;
import org.openehr.rm.support.identification.ObjectVersionID;
import org.openehr.rm.support.terminology.TerminologyService;

public class VersionedFolder extends VersionedObject<Folder> {

	/**
	 * Constructs a VersionFolder with first Folder created locally
	 */
	public VersionedFolder(HierarchicalObjectID uid, ObjectReference ownerID, 
			DvDateTime timeCreated, ObjectVersionID versionID, Folder folder, 
			AuditDetails commitAudit, ObjectReference contribution, 
			DvCodedText lifecycleState, TerminologyService terminologyService) {
		
        super(uid, ownerID, timeCreated, versionID, folder, commitAudit, contribution, 
        		lifecycleState, terminologyService);
	}
	
	/**
	 * Constructs a VersionFolder with first imported Folder
	 */
	public VersionedFolder(HierarchicalObjectID uid, ObjectReference ownerID, 
			DvDateTime timeCreated, AuditDetails commitAudit, 
			ObjectReference contribution, OriginalVersion<Folder> item) {
        super(uid, ownerID, timeCreated, commitAudit, contribution, item);
        
	}

	/**
	 * Constructs a VersionFolder with first merged Folder
	 */
	public VersionedFolder(HierarchicalObjectID uid, ObjectReference ownerID, 
			DvDateTime timeCreated, ObjectVersionID versionID,   
			ObjectVersionID precedingVersionID, Folder folder, AuditDetails commitAudit,    
			ObjectReference contribution, DvCodedText lifecycleState,   
			Set<ObjectVersionID> otherInputVersionUids, TerminologyService terminologyService) {
		
		super(uid, ownerID, timeCreated, versionID, precedingVersionID, folder, commitAudit,
				contribution, lifecycleState, otherInputVersionUids, terminologyService);
	}
	
	//POJO start
	VersionedFolder() {
	}

}
