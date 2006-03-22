/*
 * component:   "openEHR Reference Implementation"
 * description: "Class Instruction"
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

import org.openehr.rm.Attribute;
import org.openehr.rm.FullConstructor;
import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.common.archetyped.FeederAudit;
import org.openehr.rm.common.archetyped.Link;
import org.openehr.rm.common.archetyped.Locatable;
import org.openehr.rm.common.generic.Participation;
import org.openehr.rm.common.generic.RelatedParty;
import org.openehr.rm.support.identification.ObjectID;
import org.openehr.rm.support.identification.ObjectReference;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;
import org.openehr.rm.datatypes.basic.DvState;
import org.openehr.rm.datatypes.text.DvText;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * The root of an action specification.  Instances of this class are immutable.
 *
 * @author Rong Chen
 * @version 1.0
 */
public final class Instruction extends Entry {

    /**
     * Constructs an Instruction
     *
     * @param uid
     * @param archetypeNodeId
     * @param name
     * @param archetypeDetails
     * @param feederAudit
     * @param links
     * @param subject
     * @param provider
     * @param protocol
     * @param actID
     * @param guidelineID
     * @param otherParticipation
     * @param state
     * @param action
     * @param profile
     * @param data
     * @throws IllegalArgumentException if state or action null
     */
    @FullConstructor
            public Instruction(@Attribute(name = "uid") ObjectID uid,
                               @Attribute(name = "archetypeNodeId", required = true) String archetypeNodeId,
                               @Attribute(name = "name", required = true) DvText name,
                               @Attribute(name = "archetypeDetails") Archetyped archetypeDetails,
                               @Attribute(name = "feederAudit") FeederAudit feederAudit,
                               @Attribute(name = "links") Set<Link> links,
                               @Attribute(name = "subject", system = true) RelatedParty subject,
                               @Attribute(name = "provider", system = true) Participation provider,
                               @Attribute(name = "protocol") ItemStructure protocol,
                               @Attribute(name = "actID") String actID,
                               @Attribute(name = "guidelineID") ObjectReference guidelineID,
                               @Attribute(name = "otherParticipation") List<Participation> otherParticipation,
                               @Attribute(name = "state", required = true) DvState state,
                               @Attribute(name = "action", required = true) ItemStructure action,
                               @Attribute(name = "profile") ItemStructure profile,
                               @Attribute(name = "data") ItemStructure data) {
        super(uid, archetypeNodeId, name, archetypeDetails, feederAudit,
                links, subject, provider, protocol, actID,
                guidelineID, otherParticipation);
        if (state == null) {
            throw new IllegalArgumentException("null state");
        }
        if (action == null) {
            throw new IllegalArgumentException("null action");
        }
        this.state = state;
        this.action = action;
        this.profile = profile;
        this.data = data;
    }

    /**
     * current state of the action in a state machine description
     *
     * @return state
     */
    public DvState getState() {
        return state;
    }

    /**
     * description of the action to be executed
     *
     * @return action
     */
    public ItemStructure getAction() {
        return action;
    }

    /**
     * configuration data mappings from archetyped model of action.
     *
     * @return profile, null if unspecified
     */
    public ItemStructure getProfile() {
        return profile;
    }

    /**
     * state data of action execution
     *
     * @return date of action null if unspecified
     */
    public ItemStructure getData() {
        return data;
    }

    /**
     * Next actions in chain, derived from links attribute - any Link
     * instance with name = "next actions".
     *
     * @return list of instructions, empty list if no next action
     */
    public List<Instruction> nextActions() {
        List<Instruction> list = new ArrayList<Instruction>();
        if (getLinks() == null) {
            return list;
        }
        for (Link link : getLinks()) {
            if ("next actions".equals(link.getMeaning().getValue())) {
                // todo: how to find Instruction from link ?
                //list.add(link)
            }
        }
        return list;
    }

    /**
     * Overall status, derived from the state values of all linked
     * Instructions in the chain.
     *
     * @return status
     */
    public DvState status() {
        // todo: fix it
        return null;
    }

    /**
     * String The path to an item relative to the root of this
     * archetyped structure.
     *
     * @param item
     * @return string path
     */
    public String pathOfItem(Locatable item) {
        return null;  // todo: implement this method
    }

    /**
     * The item at a path that is relative to this item.
     *
     * @param path
     * @return the item or null if not found
     */
    public Locatable itemAtPath(String path) {
        Locatable item = super.itemAtPath(path);
        if (item != null) {
            return item;
        }
        String whole = whole();
        String tmp = path;
        if (tmp.startsWith(whole)) {
            tmp = path.substring(whole.length());
        }

        // check attributes
        String[] attributeNames = {
            DATA, ACTION, PROFILE
        };
        Locatable[] attributes = {
            data, action, profile
        };
        return locateAttribute(tmp, attributeNames, attributes);
    }
    
    // POJO start
    Instruction() {
    }

    void setState(DvState state) {
        this.state = state;
    }

    void setAction(ItemStructure action) {
        this.action = action;
    }

    void setProfile(ItemStructure profile) {
        this.profile = profile;
    }

    void setData(ItemStructure data) {
        this.data = data;
    }
    // POJO end

    /* fields */
    private DvState state;
    private ItemStructure action;
    private ItemStructure profile;
    private ItemStructure data;
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
 *  The Original Code is Instruction.java
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