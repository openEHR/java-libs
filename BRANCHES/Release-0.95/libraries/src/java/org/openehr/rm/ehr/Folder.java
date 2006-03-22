/*
 * component:   "openEHR Reference Implementation"
 * description: "Class Folder"
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

import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.common.archetyped.FeederAudit;
import org.openehr.rm.common.archetyped.Link;
import org.openehr.rm.common.archetyped.Locatable;
import org.openehr.rm.support.identification.ObjectID;
import org.openehr.rm.support.identification.ObjectReference;
import org.openehr.rm.datatypes.text.DvText;

import java.util.List;
import java.util.Set;

/**
 * This class represents the concept of a named folder.
 *
 * @author Rong Chen
 * @version 1.0
 */
public class Folder extends Locatable {

    /**
     * Constructs a Folder
     *
     * @param uid
     * @param archetypeNodeId
     * @param name
     * @param archetypeDetails
     * @param feederAudit
     * @param links
     * @param folders          null if unspecified
     * @param compositions     null if unspecified
     * @throws IllegalArgumentException if folders or compositions
     *                                  empty
     */
    public Folder(ObjectID uid, String archetypeNodeId, DvText name,
                  Archetyped archetypeDetails,
                  FeederAudit feederAudit, Set<Link> links,
                  List<Folder> folders, List<ObjectReference> compositions) {
        super(uid, archetypeNodeId, name, archetypeDetails, feederAudit,
                links);
        if (folders != null && folders.isEmpty()) {
            throw new IllegalArgumentException("empty folders");
        }
        if (compositions != null && compositions.isEmpty()) {
            throw new IllegalArgumentException("empty compositions");
        }
        if (compositions != null) {
            for (ObjectReference ref : compositions) {
                if (!ObjectReference.Type.VERSIONED_COMPOSITION.equals(
                        ref.getType())) {
                    throw new IllegalArgumentException(
                            "non-versioned_composition type object reference");
                }
            }
        }
        this.folders = folders;
        this.compositions = compositions;
    }

    /**
     * Sub-folders of this Folder
     *
     * @return unmodifiable list of folders
     */
    public List<Folder> getFolders() {
        return folders;
    }

    /**
     * The list of references to versioned compositions in this
     * folder. Since more than one folder can include the same
     * composition, this relationship is an association.
     *
     * @return unmodifiable list of compositions
     */
    public List<ObjectReference> getCompositions() {
        return compositions;
    }

    /**
     * String The path to an item relative to the root of this
     * archetyped structure.
     *
     * @param item
     * @return path of given item
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

    // POJO start
    Folder() {
    }

    void setFolders(List<Folder> folders) {
        this.folders = folders;
    }

    void setCompositions(List<ObjectReference> compositions) {
        this.compositions = compositions;
    }    
    // POJO end

    /* fields */
    private List<Folder> folders;
    private List<ObjectReference> compositions;
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
 *  The Original Code is Folder.java
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