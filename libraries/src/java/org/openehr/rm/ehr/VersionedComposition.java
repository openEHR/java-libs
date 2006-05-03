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
import org.openehr.rm.common.changecontrol.Version;
import org.openehr.rm.common.changecontrol.VersionedObject;
import org.openehr.rm.common.directory.Folder;
import org.openehr.rm.common.generic.AuditDetails;
import org.openehr.rm.support.identification.HierarchicalObjectID;
import org.openehr.rm.support.identification.ObjectID;
import org.openehr.rm.support.identification.ObjectReference;
import org.openehr.rm.support.identification.ObjectVersionID;
import org.openehr.rm.composition.Composition;
import org.openehr.rm.datatypes.basic.DvState;
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
	public VersionedComposition(HierarchicalObjectID uid, ObjectReference ownerID, 
			DvDateTime timeCreated, ObjectVersionID versionID, Composition composition, 
			AuditDetails commitAudit, ObjectReference contribution, 
			DvCodedText lifecycleState, TerminologyService terminologyService) {
		
        super(uid, ownerID, timeCreated, versionID, composition, commitAudit, contribution, 
        		lifecycleState, terminologyService);
	}
	
	/**
	 * Constructs a VersionComposition with first imported Composition
	 */
	public VersionedComposition(HierarchicalObjectID uid, ObjectReference ownerID, 
			DvDateTime timeCreated, AuditDetails commitAudit, 
			ObjectReference contribution, OriginalVersion<Composition> item) {
        super(uid, ownerID, timeCreated, commitAudit, contribution, item);
        
	}

	/**
	 * Constructs a VersionComposition with first merged Composition
	 */
	public VersionedComposition(HierarchicalObjectID uid, ObjectReference ownerID, 
			DvDateTime timeCreated, ObjectVersionID versionID,   
			ObjectVersionID precedingVersionID, Composition composition, AuditDetails commitAudit,    
			ObjectReference contribution, DvCodedText lifecycleState,   
			Set<ObjectVersionID> otherInputVersionUids, TerminologyService terminologyService) {
		
		super(uid, ownerID, timeCreated, versionID, precedingVersionID, composition, commitAudit,
				contribution, lifecycleState, otherInputVersionUids, terminologyService);
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