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
			DvCodedText lifecycleState, AuditDetails commitAudit, 
                        ObjectReference contribution, String signature,
                        TerminologyService terminologyService) {
		
        super(uid, ownerID, timeCreated, versionID, ehrStatus, lifecycleState, commitAudit, 
        	 contribution, signature, terminologyService);
	}
	
	/**
	 * Constructs a VersionEHRStatus with first imported EHRStatus
	 */
	public VersionedEHRStatus(HierarchicalObjectID uid, ObjectReference ownerID, 
			DvDateTime timeCreated, OriginalVersion<EHRStatus> item,
                        AuditDetails commitAudit, ObjectReference contribution, 
                        String signature) {
        super(uid, ownerID, timeCreated, item, commitAudit, contribution, signature);
        
	}

	/**
	 * Constructs a VersionEHRStatus with first merged EHRStatus
	 */
	public VersionedEHRStatus(HierarchicalObjectID uid, ObjectReference ownerID, 
			DvDateTime timeCreated, ObjectVersionID versionID,   
			ObjectVersionID precedingVersionID, EHRStatus ehrStatus, DvCodedText lifecycleState, 
                        AuditDetails commitAudit, ObjectReference contribution,   			  
			Set<ObjectVersionID> otherInputVersionUids, String signature,
                        TerminologyService terminologyService) {
		
		super(uid, ownerID, timeCreated, versionID, precedingVersionID, ehrStatus, lifecycleState, 
                        commitAudit, contribution, otherInputVersionUids, signature, terminologyService);
	}
    
	//POJO start
	VersionedEHRStatus() {
	}
	
}
