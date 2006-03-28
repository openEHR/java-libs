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
 * file:        "$URL$"
 * revision:    "$LastChangedRevision$"
 * last_change: "$LastChangedDate$"
 */
package org.openehr.rm.composition.content.entry;

import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.common.archetyped.FeederAudit;
import org.openehr.rm.common.archetyped.Link;
import org.openehr.rm.common.archetyped.Locatable;
import org.openehr.rm.common.generic.Participation;
import org.openehr.rm.common.generic.RelatedParty;
import org.openehr.rm.support.identification.ObjectID;
import org.openehr.rm.support.identification.ObjectReference;
import org.openehr.rm.composition.content.ContentItem;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;
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
    public Entry(ObjectID uid, String archetypeNodeId, DvText name,
                 Archetyped archetypeDetails, FeederAudit feederAudit,
                 Set<Link> links, RelatedParty subject, Participation provider,
                 ItemStructure protocol, String actID,
                 ObjectReference guidelineID,
                 List<Participation> otherParticipations) {

        super(uid, archetypeNodeId, name, archetypeDetails, feederAudit, links);

        if (subject == null) {
            throw new IllegalArgumentException("null subject");
        }
        // todo: then provider.function. is_equal( DV_CODED_TEXT ))
        // todo: implies Terminology_id_Provider_functions.has (
        // todo: provider.function. terminology_id.value)
        if (provider == null) {
            throw new IllegalArgumentException("null provider");
        }
        if (otherParticipations != null && otherParticipations.isEmpty()) {
            throw new IllegalArgumentException("empty otherParticipations");
        }
        this.subject = subject;
        this.provider = provider;
        this.protocol = protocol;
        this.actID = actID;
        this.guidelineID = guidelineID;
        this.otherParticipations = ( otherParticipations == null ? null :
                new ArrayList<Participation>(otherParticipations) );
    }

    /**
     * Id of human subject of this ENTRY, usually the patient
     *
     * @return subject
     */
    public RelatedParty getSubject() {
        return subject;
    }

    /**
     * Id of provider of statement in this entry
     *
     * @return provider
     */
    public Participation getProvider() {
        return provider;
    }

    /**
     * Description of how and/or why the information in this entry
     * was arrived at.
     *
     * @return protocol or null if unspecified
     */
    public ItemStructure getProtocol() {
        return protocol;
    }

    /**
     * Optional act identifier for this Entry, used by eg a workflow
     * system for an act to which this ENTRY corresponds.
     *
     * @return act ID or null if unspecified
     */
    public String getActID() {
        return actID;
    }

    /**
     * Optional external identifier of guideline creating this
     * action if relevant
     *
     * @return guideline ID or null if unspecified
     */
    public ObjectReference getGuidelineID() {
        return guidelineID;
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
     * The item at a path that is relative to this item.
     *
     * @param path
     * @return the item
     * @throws IllegalArgumentException if path invalid
     */
    public Locatable itemAtPath(String path) {
        Locatable item = super.itemAtPath(path);
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
    }

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

    void setSubject(RelatedParty subject) {
        this.subject = subject;
    }

    void setProvider(Participation provider) {
        this.provider = provider;
    }

    void setProtocol(ItemStructure protocol) {
        this.protocol = protocol;
    }

    void setActID(String actID) {
        this.actID = actID;
    }

    void setGuidelineID(ObjectReference guidelineID) {
        this.guidelineID = guidelineID;
    }

    void setOtherParticipations(List<Participation> otherParticipations) {
        this.otherParticipations = otherParticipations;
    }
    // POJO end

    /* fields */
    private RelatedParty subject;
    private Participation provider;
    private ItemStructure protocol;
    private String actID;
    private ObjectReference guidelineID;
    private List<Participation> otherParticipations;

    /* static fields */
    public static final String DATA = "data";
    public static final String STATE = "state";
    public static final String PROTOCOL = "protocol";
    public static final String ACTION = "action";
    public static final String PROFILE = "profile";
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