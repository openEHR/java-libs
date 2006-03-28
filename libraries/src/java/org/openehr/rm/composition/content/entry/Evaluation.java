/*
 * component:   "openEHR Reference Implementation"
 * description: "Class Evaluation"
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
import org.openehr.rm.datatypes.text.DvText;

import java.util.List;
import java.util.Set;

/**
 * Entry type for evaluation statements. Instances of this class are immutable.
 *
 * @author Rong Chen
 * @version 1.0
 */
public final class Evaluation extends Entry {

    /**
     * Create an Evaluation
     *
     * @param uid
     * @param archetypeNodeId
     * @param name
     * @param subject
     * @param provider
     * @param protocol           null if unspecified
     * @param actID              null if unspecified
     * @param guidelineID        null if unspecified
     * @param otherParticipation null if unspecified
     * @param data
     * @throws IllegalArgumentException if data null
     */
    @FullConstructor
            public Evaluation(@Attribute(name = "uid") ObjectID uid,
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
                              @Attribute(name = "data", required = true) ItemStructure data) {

        super(uid, archetypeNodeId, name, archetypeDetails, feederAudit, links,
                subject, provider, protocol, actID, guidelineID,
                otherParticipation);

        if (data == null) {
            throw new IllegalArgumentException("null data");
        }
        this.data = data;
    }

    /**
     * The data of this evaluation, in the form of a spatial
     * data structure.
     *
     * @return data of this evaluation
     */
    public ItemStructure getData() {
        return data;
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
     * @return the item
     * @throws IllegalArgumentException if path invalid
     */
    public Locatable itemAtPath(String path) {
        Locatable item = super.itemAtPath(path);
        if (item != null) {
            return item;
        }
        String whole = whole();
        String tmp = path;
        if(tmp.startsWith(whole)) {
            tmp = path.substring(whole.length());
        }
        // check attributes
        String[] attributeNames = {
            DATA
        };
        Locatable[] attributes = {
            data
        };
        return locateAttribute(tmp, attributeNames, attributes);
    }

    // POJO start
    Evaluation() {
    }

    void setData(ItemStructure data) {
        this.data = data;
    }
    // POJO end

    /* fields */
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
 *  The Original Code is Evaluation.java
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