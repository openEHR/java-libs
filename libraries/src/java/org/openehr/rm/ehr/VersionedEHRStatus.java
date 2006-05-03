/**
 * 
 */
package org.openehr.rm.ehr;

import java.util.List;
import java.util.Set;

import org.openehr.rm.Attribute;
import org.openehr.rm.FullConstructor;
import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.common.archetyped.FeederAudit;
import org.openehr.rm.common.archetyped.Link;
import org.openehr.rm.common.archetyped.Locatable;
import org.openehr.rm.common.changecontrol.OriginalVersion;
import org.openehr.rm.common.changecontrol.Version;
import org.openehr.rm.common.changecontrol.VersionedObject;
import org.openehr.rm.common.directory.Folder;
import org.openehr.rm.common.generic.AuditDetails;
import org.openehr.rm.common.generic.PartySelf;
import org.openehr.rm.composition.Composition;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;
import org.openehr.rm.datatypes.basic.DvState;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.support.identification.HierarchicalObjectID;
import org.openehr.rm.support.identification.ObjectID;
import org.openehr.rm.support.identification.ObjectReference;
import org.openehr.rm.support.identification.ObjectVersionID;
import org.openehr.rm.support.terminology.TerminologyService;

/**
 * Versioning container for EHRStatus instance
 *
 */
public class VersionedEHRStatus extends VersionedObject<EHRStatus> {
	
	/**
	 * Constructs a VersionEHRStatus with first EHRStatus created locally
	 */
	public VersionedEHRStatus(HierarchicalObjectID uid, ObjectReference ownerID, 
			DvDateTime timeCreated, ObjectVersionID versionID, EHRStatus ehrStatus, 
			AuditDetails commitAudit, ObjectReference contribution, 
			DvCodedText lifecycleState, TerminologyService terminologyService) {
		
        super(uid, ownerID, timeCreated, versionID, ehrStatus, commitAudit, contribution, 
        		lifecycleState, terminologyService);
	}
	
	/**
	 * Constructs a VersionEHRStatus with first imported EHRStatus
	 */
	public VersionedEHRStatus(HierarchicalObjectID uid, ObjectReference ownerID, 
			DvDateTime timeCreated, AuditDetails commitAudit, 
			ObjectReference contribution, OriginalVersion<EHRStatus> item) {
        super(uid, ownerID, timeCreated, commitAudit, contribution, item);
        
	}

	/**
	 * Constructs a VersionEHRStatus with first merged EHRStatus
	 */
	public VersionedEHRStatus(HierarchicalObjectID uid, ObjectReference ownerID, 
			DvDateTime timeCreated, ObjectVersionID versionID,   
			ObjectVersionID precedingVersionID, EHRStatus ehrStatus, AuditDetails commitAudit,    
			ObjectReference contribution, DvCodedText lifecycleState,   
			Set<ObjectVersionID> otherInputVersionUids, TerminologyService terminologyService) {
		
		super(uid, ownerID, timeCreated, versionID, precedingVersionID, ehrStatus, commitAudit,
				contribution, lifecycleState, otherInputVersionUids, terminologyService);
	}
    
	//POJO start
	VersionedEHRStatus() {
	}
	
}
