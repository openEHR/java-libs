/*
 * component:   "openEHR Reference Implementation"
 * description: "Class Entry"
 * keywords:    "composition"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/composition/content/entry/Entry.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 22:20:08 +0100 (Wed, 12 Oct 2005) $"
 */
package org.openehr.rm.composition.content.entry;

import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.common.archetyped.FeederAudit;
import org.openehr.rm.common.archetyped.Link;
import org.openehr.rm.common.archetyped.Locatable;
import org.openehr.rm.common.generic.Participation;
import org.openehr.rm.common.generic.PartyProxy;
import org.openehr.rm.common.generic.PartyRelated;
import org.openehr.rm.common.generic.PartySelf;
import org.openehr.rm.support.identification.ObjectID;
import org.openehr.rm.support.identification.ObjectRef;
import org.openehr.rm.support.terminology.TerminologyService;
import org.openehr.rm.composition.content.ContentItem;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.datatypes.text.DvText;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * The abstract parent of all Entry subtypes. An Entry is the root of
 * a logical item of  hard  clinical information created in the
 * "clinical statement" context, within a clinical session.
 * <p/>
 * Instances of this class are immutable.
 *
 * @author Rong Chen
 * @version 1.0
 */
public abstract class Entry extends ContentItem {

    /**
     * Construct an Entry
     *
     * @param archetypeNodeId
     * @param name
     * @param subject
     * @param provider
     * @param protocol            null if unspecified
     * @param actID               null if unspecified
     * @param guidelineID         null if unspecified
     * @param otherParticipations null if unspecified
     * @throws IllegalArgumentException if archetypeNodeId or name null,
     *                                  or subject or provider null or invalid
     */
    protected Entry(ObjectID uid, String archetypeNodeId, DvText name,
                 Archetyped archetypeDetails, FeederAudit feederAudit,
                 Set<Link> links, Locatable parent, CodePhrase language,
                 CodePhrase charset, PartyProxy subject, 
                 PartyProxy provider, ObjectRef workflowID,
                 List<Participation> otherParticipations,
                 TerminologyService terminologyService) {

        super(uid, archetypeNodeId, name, archetypeDetails, feederAudit, links, parent);

        if (language == null) {
        		throw new IllegalArgumentException("null language");
        }
        if (charset == null) {
    			throw new IllegalArgumentException("null charset");
        }
        if (subject == null) {
            throw new IllegalArgumentException("null subject");
        }
        if (!isArchetypeRoot()) {
            throw new IllegalArgumentException("not archetype root");
        }
        if (otherParticipations != null && otherParticipations.isEmpty()) {
            throw new IllegalArgumentException("empty otherParticipations");
        }
        if (terminologyService == null) {
        		throw new IllegalArgumentException("null terminologyService");
        }
        if (!terminologyService.codeSet("languages").hasLang(language)) {
        		throw new IllegalArgumentException("unknown language:" + language);
        }
        this.language = language;
        this.charset = charset;
        this.subject = subject;
        this.provider = provider;
        this.workflowID = workflowID;
        this.otherParticipations = ( otherParticipations == null ? null :
                new ArrayList<Participation>(otherParticipations) );
    }

    /**
     * Id of human subject of this ENTRY, usually the patient
     *
     * @return subject
     */
    public PartyProxy getSubject() {
        return subject;
    }

    /**
     * Id of provider of statement in this entry
     *
     * @return provider
     */
    public PartyProxy getProvider() {
        return provider;
    }

    /**
     * Name of charater set in which text values in this Entry 
     * are encoded. Coded from openEHR code set "character sets".
     * 
     * @return charset
     */
    public CodePhrase getCharset() {
		return charset;
	}

    /**
     * Mandatory iindicator of the localised language in which 
     * this Entry is written. Coded from openEHR code set "languages".
     * @return
     */
    public CodePhrase getLanguage() {
		return language;
	}

	/**
     * Identifier of externally held workflow engine data for 
     * this workflow execution, for this subject of care.
     *
     * @return workflow ID or null if unspecified
     */
    public ObjectRef getWorkflowID() {
        return workflowID;
    }

    /**
     * Other participations at ENTRY level - archetypable.
     *
     * @return unmodifiable List of other participation or null if unspecified
     */
    public List<Participation> getOtherParticipations() {
        return otherParticipations == null ? null :
                Collections.unmodifiableList(otherParticipations);
    }

    /**
     * Returns True if this Entry is about the subject of the EHR, in which
     * case the subject attribute is of type PartySelf
     */
    public boolean subjectIsSelf() {
        return (subject instanceof PartySelf);
    }
    
    /**
     * The item at a path that is relative to this item.
     *
     * @param path
     * @return the item
     * @throws IllegalArgumentException if path invalid
     */
   /* public Object itemAtPath(String path) {
        Object item = super.itemAtPath(path);
        if(item != null) {
            return item;
        }
        String whole = whole();
        String tmp = path;
        if(tmp.startsWith(whole)) {
            tmp = tmp.substring(whole.length());
        }
        String attr = ROOT + PROTOCOL;
        if (tmp.equals(attr)) {
            return protocol;
        }
        if(tmp.startsWith(attr)) {
            return protocol.itemAtPath(tmp.substring(attr.length()));
        }
        return null;    // path needs to be further processed by sub-class
        //TODO: re-implement
    }*/

    /**
     * Return true if the path is valid with respect to the current
     * item.
     *
     * @param path
     * @return true if valid
     */
    public boolean validPath(String path) {
        if(super.validPath(path)) {
            return true;
        }
        try {
            itemAtPath(path);
            return true;
        } catch(IllegalArgumentException e) {
            return false;
        }
    }    

    // POJO start
    protected Entry() {
    }

    void setSubject(PartyProxy subject) {
        this.subject = subject;
    }

    void setProvider(PartyProxy provider) {
        this.provider = provider;
    }

    void setCharset(CodePhrase charset) {
		this.charset = charset;
	}

	void setLanguage(CodePhrase language) {
		this.language = language;
	}

	void setWorkflowID(ObjectRef guidelineID) {
        this.workflowID = guidelineID;
    }

    void setOtherParticipations(List<Participation> otherParticipations) {
        this.otherParticipations = otherParticipations;
    }
    // POJO end

    /* fields */
    private CodePhrase language;
    private CodePhrase charset;
    private PartyProxy subject;
    private PartyProxy provider;
    private ObjectRef workflowID;
    private List<Participation> otherParticipations;

    /* static fields */
    
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
 *  The Original Code is Entry.java
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
