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

import org.apache.commons.lang.StringUtils;
import org.openehr.rm.RMObject;
import org.openehr.rm.common.generic.Attestation;
import org.openehr.rm.common.generic.AuditDetails;
import org.openehr.rm.support.identification.HierarchicalObjectID;
import org.openehr.rm.support.identification.ObjectID;
import org.openehr.rm.support.identification.ObjectReference;
import org.openehr.rm.support.identification.ObjectVersionID;
import org.openehr.rm.datatypes.basic.DvState;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.support.terminology.TerminologyService;

import java.util.List;

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
     * @param precedingVersionID  not null or empty
     * @throws IllegalArgumentException
     */
    public Version(ObjectVersionID uid, ObjectVersionID precedingVersionID,
                   T data, AuditDetails commitAudit, ObjectReference contribution) {

        if (uid == null) {
            throw new IllegalArgumentException("null uid");
        }
        if (data == null) {
        		throw new IllegalArgumentException("null data");
        }
        if (commitAudit == null) {
            throw new IllegalArgumentException("null audit");
        }
        if (contribution == null) {
            throw new IllegalArgumentException("null contribution");
        }
        if (!contribution.getType().equals(ObjectReference.Type.CONTRIBUTION)) {
        		throw new IllegalArgumentException("contribution not of type CONTRIBUTION");
        }
        if (uid.versionTreeID().isFirst() == (precedingVersionID != null)) {
        		throw new IllegalArgumentException("breach of precedingVersionUid validity");
        }
        this.uid = uid;
        this.precedingVersionID = precedingVersionID;
        this.data = data;
        this.commitAudit = commitAudit;      
        this.contribution = contribution;
    }

	/**
     * Unique identifier of the owning version container.
     *
     * @return uid of owning version container
     */
    public HierarchicalObjectID ownerID() {
    		//TODO check if correct, the extension bit at the back?
        return new HierarchicalObjectID(uid.objectID(), null);
    }

    /**
     * True if this Version represents a branch
     */
    public boolean isBranch() {
    		return uid.versionTreeID().isBranch();
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
    public ObjectVersionID getPrecedingVersionID() {
        return precedingVersionID;
    }

    /**
     * Contribution in which this version was added
     *
     * @return contribution
     */
    public ObjectReference getContribution() {
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

    // POJO start
    Version() {
    }

    void setCommitAudit(AuditDetails audit) {
        this.commitAudit = audit;
    }

    void setUid(ObjectVersionID uid) {
        this.uid = uid;
    }

    void setPrecedingVersionID(ObjectVersionID precedingVersionID) {
        this.precedingVersionID = precedingVersionID;
    }

    void setContribution(ObjectReference contribution) {
        this.contribution = contribution;
    }

	void setData(T data) {
		this.data = data;
	}

    // POJO end

    /* fields */
    private AuditDetails commitAudit;
    private ObjectVersionID uid; 
    private ObjectVersionID precedingVersionID;
    private ObjectReference contribution;
    private T data;

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