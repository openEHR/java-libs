/*
 * VersionedEHRAccess.java
 *
 * Created on August 7, 2006, 1:59 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.openehr.rm.ehr;

import java.util.Set;
import org.openehr.rm.common.changecontrol.OriginalVersion;
import org.openehr.rm.common.changecontrol.VersionedObject;
import org.openehr.rm.common.generic.AuditDetails;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.support.identification.HierarchicalObjectID;
import org.openehr.rm.support.identification.ObjectReference;
import org.openehr.rm.support.identification.ObjectVersionID;
import org.openehr.rm.support.terminology.TerminologyService;

/**
 *
 * @author yinsulim
 */
public class VersionedEHRAccess extends VersionedObject<EHRAccess> {
    
    /** Creates a new instance of VersionedEHRAccess */
    VersionedEHRAccess() {
    }
    
    
    /**
     * Constructs a VersionEHRAccess with first EHRAccess created locally
     */
    public VersionedEHRAccess(HierarchicalObjectID uid, ObjectReference ownerID,
            DvDateTime timeCreated, ObjectVersionID versionID, EHRAccess ehrAccess,
            DvCodedText lifecycleState, AuditDetails commitAudit,
            ObjectReference contribution, String signature,
            TerminologyService terminologyService) {
        
        super(uid, ownerID, timeCreated, versionID, ehrAccess, lifecycleState, 
                commitAudit, contribution, signature, terminologyService);
    }
    
    /**
     * Constructs a VersionEHRAccess with first imported EHRAccess
     */
    public VersionedEHRAccess(HierarchicalObjectID uid, ObjectReference ownerID,
            DvDateTime timeCreated, OriginalVersion<EHRAccess> item,
            AuditDetails commitAudit, ObjectReference contribution, String signature) {
        super(uid, ownerID, timeCreated, item, commitAudit, contribution, signature);
        
    }
    
    /**
     * Constructs a VersionEHRAccess with first merged EHRAccess
     */
    public VersionedEHRAccess(HierarchicalObjectID uid, ObjectReference ownerID,
            DvDateTime timeCreated, ObjectVersionID versionID,
            ObjectVersionID precedingVersionID, EHRAccess ehrAccess,
            DvCodedText lifecycleState, AuditDetails commitAudit,
            ObjectReference contribution, Set<ObjectVersionID> otherInputVersionUids,
            String signature, TerminologyService terminologyService) {
        
        super(uid, ownerID, timeCreated, versionID, precedingVersionID, ehrAccess, 
                lifecycleState, commitAudit, contribution, otherInputVersionUids, signature, 
                terminologyService);
    } 
}
