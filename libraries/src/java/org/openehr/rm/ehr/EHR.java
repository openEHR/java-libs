/*
 * component:   "openEHR Reference Implementation"
 * description: "Class EHR"
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

import org.openehr.rm.Attribute;
import org.openehr.rm.FullConstructor;
import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.common.archetyped.FeederAudit;
import org.openehr.rm.common.archetyped.Link;
import org.openehr.rm.common.archetyped.Locatable;
import org.openehr.rm.support.identification.ObjectID;
import org.openehr.rm.support.identification.ObjectReference;
import org.openehr.rm.support.identification.PartyReference;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datatypes.text.DvText;

import java.util.List;
import java.util.Set;

/**
 * The EHR class is the centre node of the EHR  repository  for a
 * subject of care.
 *
 * @author Rong Chen
 * @version 1.0
 */
public class EHR extends Locatable {

    /**
     * Constructs a Locatable
     *
     * @param uid              null if not specified
     * @param archetypeNodeId
     * @param name
     * @param archetypeDetails null if not specified
     * @param feederAudit      null if not specified
     * @param links            null if not specified
     * @param subject
     * @param timeCreated
     * @param contributions
     * @param directory
     * @param allCompositions
     * @throws IllegalArgumentException if name null or archetypeNodeId null
     *                                  or links not null and empty,
     *                                  or subject, timeCreated, contributions,
     *                                  allCompositions or directory invalid
     */
    @FullConstructor public EHR(@Attribute(name = "uid") ObjectID uid,
                                @Attribute(name = "archetypeNodeId", required=true) String archetypeNodeId,
                                @Attribute(name = "name", required=true) DvText name,
                                @Attribute(name = "archetypeDetails", required=true) Archetyped archetypeDetails,
                                @Attribute(name = "feederAudit") FeederAudit feederAudit,
                                @Attribute(name = "links") Set<Link> links,
                                @Attribute(name = "subject", required=true) PartyReference subject,
                                @Attribute(name = "timeCreated", required=true) DvDateTime timeCreated,
                                @Attribute(name = "contributions", required=true) List<ObjectReference> contributions,
                                @Attribute(name = "directory") Directory directory,
                                @Attribute(name = "allCompositions", required=true) List<ObjectReference> allCompositions) {
        super(uid, archetypeNodeId, name, archetypeDetails, feederAudit,
                links);
        if(archetypeDetails == null) {
            throw new IllegalArgumentException("null archetypeDetails");
        }
        if (subject == null) {
            throw new IllegalArgumentException("null subject");
        }
        if (timeCreated == null) {
            throw new IllegalArgumentException("null timeCreated");
        }
        if (directory != null &&
                !directory.latestVersion().getData().isArchetypeRoot()) {
            throw new IllegalArgumentException("invalid directory");
        }
        if (contributions == null || contributions.isEmpty()) {
            throw new IllegalArgumentException("null or empty contributions");
        }
        for (ObjectReference ref : contributions) {
            if (!ObjectReference.Type.CONTRIBUTION.equals(ref.getType())) {
                throw new IllegalArgumentException(
                        "non-contribution type object reference");
            }
        }
        if (allCompositions == null || allCompositions.isEmpty()) {
            throw new IllegalArgumentException("null or empty allCompositions");
        }
        for (ObjectReference ref : allCompositions) {
            if (!ObjectReference.Type.VERSIONED_COMPOSITION.equals(
                    ref.getType())) {
                throw new IllegalArgumentException(
                        "non-versioned_composition type object reference");
            }
        }

        this.subject = subject;
        this.timeCreated = timeCreated;
        this.contributions = contributions;
        this.directory = directory;
        this.allCompositions = allCompositions;
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
     * @return relative path
     */
    public Locatable itemAtPath(String path) {
        return null;  // todo: implement this method
    }

    /**
     * Return true if the path is valid with respect to the current
     * item.
     *
     * @param path
     * @return true if valid
     */
    public boolean validPath(String path) {
        return false;  // todo: implement this method
    }

    /**
     * Always return true
     *
     * @return true
     */
    public boolean isArchetypeRoot() {
        return true;
    }

    /**
     * Return the subject of this EHR.
     *
     * @return subject
     */
    public PartyReference getSubject() {
        return subject;
    }

    /**
     * Time of creation of the repository
     *
     * @return time of creation
     */
    public DvDateTime getTimeCreated() {
        return timeCreated;
    }

    /**
     * List of contributions causing changes to this EHR. Each
     * contribution contains a list of versions, which may include
     * references to any number of VERSION instances, ie items of
     * type VERSIONED_COMPOSITION and DIRECTORY.
     *
     * @return List of ObjectReference
     */
    public List<ObjectReference> getContributions() {
        return contributions;
    }

    /**
     * Optional directory structure for this EHR.
     *
     * @return directory
     */
    public Directory getDirectory() {
        return directory;
    }

    /**
     * Master list of all composition references in this EHR
     *
     * @return list of objectReference
     */
    public List<ObjectReference> getAllCompositions() {
        return allCompositions;
    }

    // POJO start
    EHR() {
    }

    private Long id;

    Long getId() {
        return id;
    }

    void setId(Long id) {
        this.id = id;
    }

    void setSubject(PartyReference subject) {
        this.subject = subject;
    }

    void setTimeCreated(DvDateTime timeCreated) {
        this.timeCreated = timeCreated;
    }

    void setContributions(List<ObjectReference> contributions) {
        this.contributions = contributions;
    }

    void setDirectory(Directory directory) {
        this.directory = directory;
    }

    void setAllCompositions(List<ObjectReference> allCompositions) {
        this.allCompositions = allCompositions;
    }
    // POJO end

    /* fields */
    private PartyReference subject;
    private DvDateTime timeCreated;
    private List<ObjectReference> contributions;
    private Directory directory; // optional
    private List<ObjectReference> allCompositions;
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
 *  The Original Code is EHR.java
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