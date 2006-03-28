/*
 * component:   "openEHR Reference Implementation"
 * description: "Class Directory"
 * keywords:    "ehr"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL$"
 * revision:    "$LastChangedRevision$"
 * last_change: "$LastChangedDate$"
 */
package org.openehr.rm.ehr;

import org.openehr.rm.common.changecontrol.VersionRepository;
import org.openehr.rm.common.changecontrol.AuditDetails;
import org.openehr.rm.support.identification.ObjectID;
import org.openehr.rm.support.identification.ObjectReference;
import org.openehr.rm.datatypes.basic.DvState;
import org.openehr.rm.support.terminology.TerminologyService;

/**
 * A version-controlled hierarchy of Folders.
 *
 * @author Rong Chen
 * @version 1.0
 */
public class Directory extends VersionRepository<Folder> {

    /**
     * Constructs a Directory
     *
     * @param uid
     * @param ownerID
     * @param root
     * @param audit
     * @param contribution
     * @param lifecycleState
     * @param termServ
     * @throws IllegalArgumentException if root null
     */
    public Directory(ObjectID uid, ObjectReference ownerID,
                     Folder root, AuditDetails audit,
                     ObjectReference contribution, DvState lifecycleState,
                     TerminologyService termServ,
                     ObjectReference.Namespace namespace) {
        super(uid, ownerID, audit, root, contribution, lifecycleState,
                termServ, namespace, ObjectReference.Type.FOLDER);
        if (root == null) {
            throw new IllegalArgumentException("null root");
        }
        if (!ObjectReference.Type.EHR.equals(ownerID.getType())) {
            throw new IllegalArgumentException("ownerID is not type of EHR");
        }
        this.root = root;
    }

    /**
     * Root FOLDER of the directory
     *
     * @return root
     */
    public Folder getRoot() {
        return root;
    }

    // POJO start
    Directory() {
    }

    void setRoot(Folder root) {
        this.root = root;
    }
    // POJO end

    /* fields */
    private Folder root;
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
 *  The Original Code is Directory.java
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