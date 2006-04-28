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
			DvDateTime timeCreated, EHRStatus folder, AuditDetails commitAudit,
			ObjectReference contribution, Set<ObjectVersionID> otherInputVersionUids,
			DvCodedText lifecycleState, boolean isMerged, 
			TerminologyService terminologyService) {
        super(uid, ownerID, timeCreated, folder, commitAudit, contribution, otherInputVersionUids,
        		lifecycleState, isMerged, terminologyService);
	}
	
	/**
	 * Constructs a VersionEHRStatus with first imported EHRStatus
	 */
	public VersionedEHRStatus(HierarchicalObjectID uid, ObjectReference ownerID, 
			DvDateTime timeCreated, AuditDetails commitAudit, 
			ObjectReference contribution, OriginalVersion<EHRStatus> item) {
        super(uid, ownerID, timeCreated, commitAudit, contribution, item);
	}

	public VersionedEHRStatus(HierarchicalObjectID uid, ObjectReference ownerID, 
			DvDateTime timeCreated, Version<EHRStatus> version) {
		super(uid, ownerID, timeCreated, version);
	}
	
    /**
     * Commit a new version of composition
     *
     * @param audit
     * @param data
     * @param contribution
     * @param lifecycleState
     * @throws IllegalArgumentException if archetypeNodeId and persistent of data
     *                                  is not the same as those of the first version
     */
    public synchronized void commit(AuditDetails audit, Composition data,
                                    ObjectReference contribution,
                                    DvState lifecycleState,
                                    TerminologyService termServ) {
        if (!allVersions().isEmpty()) {
            String archetypeNodeId = firstVersion().getData().getArchetypeNodeId();
            if (!archetypeNodeId.equals(data.getArchetypeNodeId())) {
                throw new IllegalArgumentException("invalid archetypeNodeId");
            }
            /*boolean persistent = allVersions().get(0).getData().isPersistent();
            if (persistent != data.isPersistent()) {
                throw new IllegalArgumentException("invalid persistent");
            }*/
        }
        super.commit(audit, data, contribution, lifecycleState, termServ);
    }
    
	//POJO start
	VersionedEHRStatus() {
	}



}
