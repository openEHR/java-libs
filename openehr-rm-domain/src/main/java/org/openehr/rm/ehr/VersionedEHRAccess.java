/*
 * component:   "openEHR Reference Implementation"
 * description: "Class VersionedEHRAccess"
 * keywords:    "ehr"
 *
 * author:      "Yin Su Lim <y.lim@chime.ucl.ac.uk>"
 * support:     "CHIME, UCL"
 * copyright:   "Copyright (c) 2006 UCL, UK"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/ehr/VersionedEHRAccess.java $"
 * revision:    "$LastChangedRevision: 53 $"
 * last_change: "$LastChangedDate: 2006-08-11 13:20:08 +0100 (Fri, 11 Aug 2006) $"
 */
package org.openehr.rm.ehr;

import java.util.Set;
import org.openehr.rm.common.changecontrol.OriginalVersion;
import org.openehr.rm.common.changecontrol.VersionedObject;
import org.openehr.rm.common.generic.AuditDetails;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.support.identification.HierObjectID;
import org.openehr.rm.support.identification.ObjectRef;
import org.openehr.rm.support.identification.ObjectVersionID;
import org.openehr.rm.support.terminology.TerminologyService;

/**
 * Version container for EHRAcess instance.
 *
 * @author Yin Su Lim
 * @version 1.0
 */
public class VersionedEHRAccess extends VersionedObject<EHRAccess> {
    
    /** Creates a new instance of VersionedEHRAccess */
    VersionedEHRAccess() {
    }
    
    
    /**
     * Constructs a VersionEHRAccess with first EHRAccess created locally
     */
    public VersionedEHRAccess(HierObjectID uid, ObjectRef ownerID,
            DvDateTime timeCreated, ObjectVersionID versionID, EHRAccess ehrAccess,
            DvCodedText lifecycleState, AuditDetails commitAudit,
            ObjectRef contribution, String signature,
            TerminologyService terminologyService) {
        
        super(uid, ownerID, timeCreated, versionID, ehrAccess, lifecycleState, 
                commitAudit, contribution, signature, terminologyService);
    }
    
    /**
     * Constructs a VersionEHRAccess with first imported EHRAccess
     */
    public VersionedEHRAccess(HierObjectID uid, ObjectRef ownerID,
            DvDateTime timeCreated, OriginalVersion<EHRAccess> item,
            AuditDetails commitAudit, ObjectRef contribution, String signature) {
        super(uid, ownerID, timeCreated, item, commitAudit, contribution, signature);
        
    }
    
    /**
     * Constructs a VersionEHRAccess with first merged EHRAccess
     */
    public VersionedEHRAccess(HierObjectID uid, ObjectRef ownerID,
            DvDateTime timeCreated, ObjectVersionID versionID,
            ObjectVersionID precedingVersionID, EHRAccess ehrAccess,
            DvCodedText lifecycleState, AuditDetails commitAudit,
            ObjectRef contribution, Set<ObjectVersionID> otherInputVersionUids,
            String signature, TerminologyService terminologyService) {
        
        super(uid, ownerID, timeCreated, versionID, precedingVersionID, ehrAccess, 
                lifecycleState, commitAudit, contribution, otherInputVersionUids, signature, 
                terminologyService);
    } 
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
 *  The Original Code is VersionedEHRAccess.java
 *
 *  The Initial Developer of the Original Code is Rong Chen.
 *  Portions created by the Initial Developer are Copyright (C) 2003-2004
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