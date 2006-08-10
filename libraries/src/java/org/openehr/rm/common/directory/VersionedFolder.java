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
                    DvCodedText lifecycleState, AuditDetails commitAudit,  
                    ObjectReference contribution, String signature, 
                    TerminologyService terminologyService) {

        super(uid, ownerID, timeCreated, versionID, folder, lifecycleState, commitAudit, 
             contribution, signature, terminologyService);
    }

    /**
     * Constructs a VersionFolder with first imported Folder
     */
    public VersionedFolder(HierarchicalObjectID uid, ObjectReference ownerID, 
                    DvDateTime timeCreated, OriginalVersion<Folder> item, 
                    AuditDetails commitAudit, ObjectReference contribution, 
                    String signature) {

        super(uid, ownerID, timeCreated, item, commitAudit, contribution, signature);

    }

    /**
     * Constructs a VersionFolder with first merged Folder
     */
    public VersionedFolder(HierarchicalObjectID uid, ObjectReference ownerID, 
            DvDateTime timeCreated, ObjectVersionID versionID,   
            ObjectVersionID precedingVersionID, Folder folder, DvCodedText lifecycleState,    
            AuditDetails commitAudit, ObjectReference contribution,    
            Set<ObjectVersionID> otherInputVersionUids, String signature, 
            TerminologyService terminologyService) {

            super(uid, ownerID, timeCreated, versionID, precedingVersionID, folder, lifecycleState, 
                commitAudit, contribution, otherInputVersionUids, signature, terminologyService);
    }

    //POJO start
    VersionedFolder() {
    }

}
