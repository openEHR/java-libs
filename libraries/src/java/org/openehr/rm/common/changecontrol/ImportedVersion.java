/*
 * component:   "openEHR Reference Implementation"
 * description: "Class Version"
 * keywords:    "common"
 *
 * author:      "Yin Su Lim <y.lim@chime.ucl.ac.uk>"
 * support:     ""
 * copyright:   ""
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/common/changecontrol/ImportedVersion.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2006-03-21 22:20:08 +0100 (Wed, 12 Oct 2005) $"
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
