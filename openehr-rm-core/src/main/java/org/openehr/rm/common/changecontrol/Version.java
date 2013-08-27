/*
 * component:   "openEHR Reference Implementation"
 * description: "Class Version"
 * keywords:    "common"
 *
 * author:      "Rong Chen <rong@acode.se>, Yin Su Lim <y.lim@chime.ucl.ac.uk>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/common/changecontrol/Version.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2006-03-21 22:20:08 +0100 (Wed, 12 Oct 2005) $"
 */
package org.openehr.rm.common.changecontrol;

import org.apache.commons.lang.NotImplementedException;
import org.openehr.rm.RMObject;
import org.openehr.rm.common.generic.AuditDetails;
import org.openehr.rm.support.identification.HierObjectID;
import org.openehr.rm.support.identification.ObjectRef;
import org.openehr.rm.support.identification.ObjectVersionID;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.support.terminology.TerminologyService;

/**
 * Versionable object, with an audit trail containing details of
 * change and list of attestations
 *
 * @author Rong Chen
 * @version 1.0
 */
public abstract class Version <T> extends RMObject {

    /**
     * Constructs a Version
     *
     * @param versionID           not null or empty
     * @param precedingVersionUid  not null or empty
     * @throws IllegalArgumentException
     */
    public Version(ObjectVersionID uid, ObjectVersionID precedingVersionID,
                   T data, DvCodedText lifeCycleState, AuditDetails commitAudit, 
                   ObjectRef contribution, String signature,
                   TerminologyService terminologyService) {

        if (uid == null) {
            throw new IllegalArgumentException("null uid");
        }
        if(lifeCycleState == null) {
            throw new IllegalArgumentException("null lifecycleState");
        }
        if (commitAudit == null) {
            throw new IllegalArgumentException("null audit");
        }
        if (contribution == null) {
            throw new IllegalArgumentException("null contribution");
        }
        if (uid.versionTreeID().isFirst() == (precedingVersionID != null)) {
            throw new IllegalArgumentException("breach of precedingVersionUid validity");
        }
        if(!terminologyService.terminology(TerminologyService.OPENEHR)
            .codesForGroupName("version lifecycle state", "en")
            .contains(lifeCycleState.getDefiningCode())) {
            throw new IllegalArgumentException("unknown lifecycleState");
        }
        setAttributes(uid, precedingVersionID, data, lifeCycleState, commitAudit, 
                   contribution, signature);
    }
    
    
	/**
     * Unique identifier of the owning version container.
     *
     * @return uid of owning version container
     */
    public HierObjectID ownerID() {
        //TODO check if correct, the extension bit at the back?
        return new HierObjectID(uid.objectID(), null);
    }

    /**
     * True if this Version represents a branch
     */
    public boolean isBranch() {
        return uid.versionTreeID().isBranch();
    }
    

    /**
     * Canonical form of Version object, created by 
     * serialising all attributes except signature
     */
    public String canonicalForm() {
        //return uid.toString() + "," + precedingVersionUid.toString() + 
          //      "," + data.toString() + "," + lifecycleState.toString() + 
            //    "," + commitAudit.toString() + "," + contribution.toString(); 
            //TODO:implement
		throw new NotImplementedException();
        //return "";
    }
    
    /**
     * Audit trail of this version
     *
     * @return version audit
     */
    public AuditDetails getCommitAudit() {
        return commitAudit;
    }

    /**
     * Unique identifier of this version
     *
     * @return versionID
     */
    public ObjectVersionID getUid() {
        return uid;
    }

    /**
     * Unique identifier of the version on which this version was
     * based. May be the pseudo-version "first"
     *
     * @return preceding versionID
     */
    public ObjectVersionID getPrecedingVersionUid() {
        return precedingVersionUid;
    }

    /**
     * Lifecycle state of this version; coded by openEHR
     * vocabulary "version lifecycle state"
     */
    public DvCodedText getLifecycleState() {
        return lifecycleState;
    }
    
    
    /**
     * Contribution in which this version was added
     *
     * @return contribution
     */
    public ObjectRef getContribution() {
        return contribution;
    }

    /**
     * Original content of this Version
     * 
     *@return data
     */
    public T getData() {
    		return data;
    }

    /**
     * OpenPGP digital signature or digest of content 
     * committed in this Version
     */
    public String getSignature() {
        return signature;
    }
    
    // POJO start
    Version() {
    }

    void setSignature(String signature) {
        this.signature = signature;
    }
    
    void setLifecycleState(DvCodedText lifeCycleState) {
        this.lifecycleState = lifeCycleState;
    }
    
    void setCommitAudit(AuditDetails audit) {
        this.commitAudit = audit;
    }

    void setUid(ObjectVersionID uid) {
        this.uid = uid;
    }

    void setPrecedingVersionUid(ObjectVersionID precedingVersionID) {
        this.precedingVersionUid = precedingVersionID;
    }

    void setContribution(ObjectRef contribution) {
        this.contribution = contribution;
    }

    void setData(T data) {
        this.data = data;
    }
        
    protected void setAttributes(ObjectVersionID uid, 
               ObjectVersionID precedingVersionID, T data, DvCodedText lifeCycleState,  
               AuditDetails commitAudit, ObjectRef contribution, 
               String signature) {
        this.uid = uid;
        this.precedingVersionUid = precedingVersionID;
        this.data = data;
        this.lifecycleState = lifeCycleState;
        this.commitAudit = commitAudit;      
        this.contribution = contribution;
        this.signature = signature;
    }

    // POJO end

    /* fields */
    private AuditDetails commitAudit;
    private ObjectVersionID uid; 
    private ObjectVersionID precedingVersionUid;
    private ObjectRef contribution;
    private T data;
    private DvCodedText lifecycleState;
    private String signature;

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
 *  The Original Code is Version.java
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