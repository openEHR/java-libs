/*
 * component:   "openEHR Reference Implementation"
 * description: "Class VersionedComposition"
 * keywords:    "ehr"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/ehr/VersionedComposition.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 22:20:08 +0100 (Wed, 12 Oct 2005) $"
 */
package org.openehr.rm.ehr;

import java.util.Set;

import org.openehr.rm.common.changecontrol.OriginalVersion;
import org.openehr.rm.common.changecontrol.VersionedObject;
import org.openehr.rm.common.generic.AuditDetails;
import org.openehr.rm.support.identification.HierObjectID;
import org.openehr.rm.support.identification.ObjectRef;
import org.openehr.rm.support.identification.ObjectVersionID;
import org.openehr.rm.composition.Composition;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.support.terminology.TerminologyService;

/**
 * Version-controlled composition abstraction, defined by inheriting
 * VERSION_OBJECT<COMPOSITION>.
 *
 * @author Rong Chen
 * @version 1.0
 */
public class VersionedComposition extends VersionedObject<Composition> {
    
    /**
     * Constructs a VersionComposition with first Composition created locally
     */
    public VersionedComposition(HierObjectID uid, ObjectRef ownerID,
            DvDateTime timeCreated, ObjectVersionID versionID, Composition composition,
            DvCodedText lifecycleState, AuditDetails commitAudit,
            ObjectRef contribution, String signature,
            TerminologyService terminologyService) {
        
        super(uid, ownerIDCheck(ownerID), timeCreated, versionID, composition, lifecycleState, 
                commitAudit, contribution, signature, terminologyService);
    }
    
    /**
     * Constructs a VersionComposition with first imported Composition
     */
    public VersionedComposition(HierObjectID uid, ObjectRef ownerID,
            DvDateTime timeCreated, OriginalVersion<Composition> item,
            AuditDetails commitAudit, ObjectRef contribution, String signature) {
        super(uid, ownerIDCheck(ownerID), timeCreated, item, commitAudit, contribution, signature);
        
    }
    
    /**
     * Constructs a VersionComposition with first merged Composition
     */
    public VersionedComposition(HierObjectID uid, ObjectRef ownerID,
            DvDateTime timeCreated, ObjectVersionID versionID,
            ObjectVersionID precedingVersionID, Composition composition,
            DvCodedText lifecycleState, AuditDetails commitAudit,
            ObjectRef contribution, Set<ObjectVersionID> otherInputVersionUids,
            String signature, TerminologyService terminologyService) {
        
        super(uid, ownerIDCheck(ownerID), timeCreated, versionID, precedingVersionID, composition, 
                lifecycleState, commitAudit, contribution, otherInputVersionUids, signature, 
                terminologyService);
    }
    
    public boolean isPersistent() {
        Composition firstData = (Composition) allVersions().get(0).getData();
        return firstData.isPersistent();
    }
    
    public synchronized void commitImportedVersion(OriginalVersion<Composition> item, 
            AuditDetails commitAudit, ObjectRef contribution, String signature) {
        
        checkAgainstFirstData(item.getData());
        super.commitImportedVersion(item, commitAudit, contribution, signature);
    }
    
    public synchronized void commitOriginalMergedVersion(ObjectVersionID versionID,
            ObjectVersionID precedingVersionID, Composition data, DvCodedText lifecycleState,
            AuditDetails commitAudit, ObjectRef contribution,
            Set<ObjectVersionID> otherInputVersionUids, String signature,
            TerminologyService terminologyService) {
        
        checkAgainstFirstData(data);
        super.commitOriginalMergedVersion(versionID, precedingVersionID, data, lifecycleState,
                commitAudit, contribution, otherInputVersionUids, signature, terminologyService);
        
    }
    
    public synchronized void commitOriginalVersion(ObjectVersionID versionID,
            ObjectVersionID precedingVersionID, Composition data, AuditDetails commitAudit,
            ObjectRef contribution, DvCodedText lifecycleState, String signature,
            TerminologyService terminologyService) {
        
        checkAgainstFirstData(data);
        super.commitOriginalVersion(versionID, precedingVersionID, data, commitAudit, contribution,
                lifecycleState, signature, terminologyService);
    }
    
    protected void checkAgainstFirstData(Composition data) {
        /*if(versionCount() == 0) {
            firstArchetypeNodeID = data.getArchetypeNodeId();
        } else {
            if(!data.getArchetypeNodeId().equals(firstArchetypeNodeID)) {
                throw new IllegalArgumentException("different archetypedNodeID");
            }
        }*/
        if(versionCount() > 0) {
            Composition firstData = (Composition) allVersions().get(0).getData();
            if(! data.getArchetypeNodeId().equals(firstData.getArchetypeNodeId())) {
                throw new IllegalArgumentException("different archetypedNodeID");
            }
            if( data.isPersistent() != firstData.isPersistent()) {
                throw new IllegalArgumentException("different persistent state");
            }
        }
    }
    
    protected static ObjectRef ownerIDCheck(ObjectRef ownerID) {
        if(!ownerID.getType().equals("EHR")) {
            throw new IllegalArgumentException("type of ownerID is not EHR");
        }
        return ownerID;
    }
    
    // POJO start
    VersionedComposition() {
    }
    // POJO end

}

/*
 *  ***** BEGIN LICENSE BLOCK *****
 *  Version: MPL 1.1/GPL 2.0/LGPL 2.1
 *
 *  The contents of this file are subject to the Mozilla Public License Version
 *  1.1 (the 'License'); you may not use this file except in compliance with
 *  the License. You may obtain a copy of the License at
 *  http://www.mozilla.org/MPL/
 *
 *  Software distributed under the License is distributed on an 'AS IS' basis,
 *  WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 *  for the specific language governing rights and limitations under the
 *  License.
 *
 *  The Original Code is VersionedComposition.java
 *
 *  The Initial Developer of the Original Code is Rong Chen.
 *  Portions created by the Initial Developer are Copyright (C) 2003-2008
 *  the Initial Developer. All Rights Reserved.
 *
 *  Contributor(s):
 *
 * Software distributed under the License is distributed on an 'AS IS' basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 *  ***** END LICENSE BLOCK *****
 */