/*
 * component:   "openEHR Reference Implementation"
 * description: "Class VersionedFolder"
 * keywords:    "common"
 *
 * author:      "Yin Su Lim <y.lim@chime.ucl.ac.uk>"
 * support:     "CHIME, UCL"
 * copyright:   "Copyright (c) 2006 UCL, UK"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/common/directory/VersionedFolder.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2006-03-08 22:20:08 +0100 (Wed, 12 Oct 2005) $"
 */
package org.openehr.rm.common.directory;

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

public class VersionedFolder extends VersionedObject<Folder> {

    /**
     * Constructs a VersionFolder with first Folder created locally
     */
    public VersionedFolder(HierObjectID uid, ObjectRef ownerID, 
                    DvDateTime timeCreated, ObjectVersionID versionID, Folder folder, 
                    DvCodedText lifecycleState, AuditDetails commitAudit,  
                    ObjectRef contribution, String signature, 
                    TerminologyService terminologyService) {

        super(uid, ownerID, timeCreated, versionID, folder, lifecycleState, commitAudit, 
             contribution, signature, terminologyService);
    }

    /**
     * Constructs a VersionFolder with first imported Folder
     */
    public VersionedFolder(HierObjectID uid, ObjectRef ownerID, 
                    DvDateTime timeCreated, OriginalVersion<Folder> item, 
                    AuditDetails commitAudit, ObjectRef contribution, 
                    String signature) {

        super(uid, ownerID, timeCreated, item, commitAudit, contribution, signature);

    }

    /**
     * Constructs a VersionFolder with first merged Folder
     */
    public VersionedFolder(HierObjectID uid, ObjectRef ownerID, 
            DvDateTime timeCreated, ObjectVersionID versionID,   
            ObjectVersionID precedingVersionID, Folder folder, DvCodedText lifecycleState,    
            AuditDetails commitAudit, ObjectRef contribution,    
            Set<ObjectVersionID> otherInputVersionUids, String signature, 
            TerminologyService terminologyService) {

            super(uid, ownerID, timeCreated, versionID, precedingVersionID, folder, lifecycleState, 
                commitAudit, contribution, otherInputVersionUids, signature, terminologyService);
    }

    //POJO start
    VersionedFolder() {
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
 *  The Original Code is VersionedFolder.java
 *
 *  The Initial Developer of the Original Code is Yin Su Lim.
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