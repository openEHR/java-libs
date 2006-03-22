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
 * file:        "$URL$"
 * revision:    "$LastChangedRevision$"
 * last_change: "$LastChangedDate$"
 */
package org.openehr.rm.ehr;

import org.openehr.rm.common.changecontrol.VersionRepository;
import org.openehr.rm.common.changecontrol.AuditDetails;
import org.openehr.rm.support.identification.ObjectID;
import org.openehr.rm.support.identification.ObjectReference;
import org.openehr.rm.composition.Composition;
import org.openehr.rm.datatypes.basic.DvState;
import org.openehr.rm.support.terminology.TerminologyService;

/**
 * Version-controlled composition abstraction, defined by inheriting
 * VERSION_REPOSITORY<COMPOSITION>.
 *
 * @author Rong Chen
 * @version 1.0
 */
public class VersionedComposition extends VersionRepository<Composition> {

    /**
     * Constructs a VersionedComposition with first version
     *
     * @param uid
     * @param ownerID
     * @param audit
     * @param data
     * @param contribution
     * @param lifecycleState
     * @param termServ
     * @param namespace
     */
    public VersionedComposition(ObjectID uid, ObjectReference ownerID,
                                AuditDetails audit, Composition data,
                                ObjectReference contribution,
                                DvState lifecycleState,
                                TerminologyService termServ,
                                ObjectReference.Namespace namespace) {

        super(uid, ownerID, audit, data, contribution, lifecycleState,
                termServ, namespace,
                ObjectReference.Type.VERSIONED_COMPOSITION);

        if (!ObjectReference.Type.EHR.equals(ownerID.getType())) {
            throw new IllegalArgumentException("ownerID is not type of EHR");
        }
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
                                    TerminologyService termServ,
                                    ObjectReference.Namespace namespace) {
        if (!allVersions().isEmpty()) {
            String archetypeNodeId = firstVersion().getData().getArchetypeNodeId();
            if (!archetypeNodeId.equals(data.getArchetypeNodeId())) {
                throw new IllegalArgumentException("invalid archetypeNodeId");
            }
            boolean persistent = allVersions().get(0).getData().isPersistent();
            if (persistent != data.isPersistent()) {
                throw new IllegalArgumentException("invalid persistent");
            }
        }
        super.commit(audit, data, contribution, lifecycleState, termServ,
                namespace, ObjectReference.Type.VERSIONED_COMPOSITION);
    }

    /**
     * Indicates whether this composition set is persistent;
     * derived from first version.
     *
     * @return true if persistent
     */
    public boolean isPersistent() {
        return firstVersion().getData().isPersistent();
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