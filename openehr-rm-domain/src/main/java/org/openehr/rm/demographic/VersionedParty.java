/*
 * component:   "openEHR Reference Implementation"
 * description: "Class VersionedParty"
 * keywords:    "demographic"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/demographic/VersionedParty.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 22:20:08 +0100 (Wed, 12 Oct 2005) $"
 */

package org.openehr.rm.demographic;

import java.util.Set;

import org.openehr.rm.common.changecontrol.OriginalVersion;
import org.openehr.rm.common.changecontrol.VersionedObject;
import org.openehr.rm.common.generic.AuditDetails;
import org.openehr.rm.support.identification.HierObjectID;
import org.openehr.rm.support.identification.ObjectRef;
import org.openehr.rm.support.identification.ObjectVersionID;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.support.terminology.TerminologyService;

/**
 * Version controlled party
 *
 * @author Rong Chen
 * @version 1.0
 */
public class VersionedParty extends VersionedObject<Party> {

	/**
	 * Constructs a VersionParty with first Party created locally
	 */
	public VersionedParty(HierObjectID uid, ObjectRef ownerID, 
			DvDateTime timeCreated, ObjectVersionID versionID, Party party, 
			DvCodedText lifecycleState, AuditDetails commitAudit, 
                        ObjectRef contribution, String signature,
                        TerminologyService terminologyService) {
		
        super(uid, ownerID, timeCreated, versionID, party, lifecycleState, commitAudit, contribution, 
                    signature, terminologyService);
	}
	
	/**
	 * Constructs a VersionParty with first imported Party
	 */
	public VersionedParty(HierObjectID uid, ObjectRef ownerID, 
			DvDateTime timeCreated, OriginalVersion<Party> item,
                        AuditDetails commitAudit, ObjectRef contribution,
			String signature) {

        super(uid, ownerID, timeCreated, item, commitAudit, contribution, signature);
        
	}

	/**
	 * Constructs a VersionParty with first merged Party
	 */
	public VersionedParty(HierObjectID uid, ObjectRef ownerID, 
			DvDateTime timeCreated, ObjectVersionID versionID,   
			ObjectVersionID precedingVersionID, Party party, DvCodedText lifecycleState,
                        AuditDetails commitAudit, ObjectRef contribution,  
			Set<ObjectVersionID> otherInputVersionUids, String signature,
                        TerminologyService terminologyService) {
		
		super(uid, ownerID, timeCreated, versionID, precedingVersionID, party, lifecycleState,
                    commitAudit, contribution, otherInputVersionUids, signature, terminologyService);
	}
	
    // POJO start
    VersionedParty() {
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
 *  The Original Code is VersionedParty.java
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