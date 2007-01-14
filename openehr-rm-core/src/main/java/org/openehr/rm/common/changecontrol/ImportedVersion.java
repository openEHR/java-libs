/*
 * component:   "openEHR Reference Implementation"
 * description: "Class ImportedVersion"
 * keywords:    "common"
 *
 * author:      "Yin Su Lim <y.lim@chime.ucl.ac.uk>"
 * support:     "CHIME, UCL" 
 * copyright:   "Copyright (c) 2006 UCL, UK"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/common/changecontrol/ImportedVersion.java $"
 * revision:    "$LastChangedRevision: 53 $"
 * last_change: "$LastChangedDate: 2006-08-11 13:20:08 +0100 (Fri, 11 Aug 2006) $"
 */
package org.openehr.rm.common.changecontrol;

import java.util.List;

import org.openehr.rm.common.generic.Attestation;
import org.openehr.rm.common.generic.AuditDetails;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.support.identification.ObjectReference;
import org.openehr.rm.support.identification.ObjectVersionID;
import org.openehr.rm.support.terminology.TerminologyService;

/**
 * Versionable object that has been copied from another location and imported 
 * into a local version container
 *
 * @author Yin Su Lim
 * @version 1.0
 */
public final class ImportedVersion<T> extends Version<T> {
    
    /**
     * Constructs an ImportedVersion
     *
     * @param commitAudit
     * @param contribution
     * @param originalVersion
     */
    public ImportedVersion(OriginalVersion<T> original, AuditDetails commitAudit,
            ObjectReference contribution, String signature) {
        if (original == null) {
            throw new IllegalArgumentException("null original version");
        }
        if (commitAudit == null) {
            throw new IllegalArgumentException("null audit");
        }
        if (contribution == null) {
            throw new IllegalArgumentException("null contribution");
        }
        this.original = original;
        setAttributes(original.getUid(), original.getPrecedingVersionID(),
            original.getData(), original.getLifeCycleState(), commitAudit, 
            contribution, signature);
    }
    
    /**
     * The original Version object that was imported.
     *
     * @return item
     */
    public OriginalVersion<T> getOriginalVersion() {
        return original;
    }
        
    //POJO start
    ImportedVersion() {
    }
    
    void setOriginalVersion(OriginalVersion<T> original) {
        if(original != null) {
            this.original = original;
        } else {
            throw new IllegalArgumentException("null original version");
        }
    }
    
    //POJO end
    
    /* fields */
    private OriginalVersion<T> original;

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
 *  The Original Code is ImportedVersion.java
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