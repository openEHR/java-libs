/*
 * component:   "openEHR Reference Implementation"
 * description: "Class XFolder"
 * keywords:    "ehr_extract"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004,2005 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/BRANCHES/RM-1.0-update/libraries/src/java/org/openehr/rm/ehrextract/XFolder.java $"
 * revision:    "$LastChangedRevision: 30 $"
 * last_change: "$LastChangedDate: 2006-04-30 00:15:58 +0200 (Sun, 30 Apr 2006) $"
 */
package org.openehr.rm.ehrextract;

import org.openehr.rm.common.archetyped.Locatable;
import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.common.archetyped.FeederAudit;
import org.openehr.rm.common.archetyped.Link;
import org.openehr.rm.common.archetyped.Pathable;
import org.openehr.rm.support.identification.ObjectID;
import org.openehr.rm.datatypes.text.DvText;

import java.util.List;
import java.util.Set;

/**
 * XFolder
 *
 * @author Rong Chen
 * @version 1.0
 */
public class XFolder extends Locatable {

    /**
     * Constructs a Locatable
     *
     * @param uid              null if not specified
     * @param archetypeNodeId  not null
     * @param name             not null
     * @param archetypeDetails null if not specified
     * @param feederAudit      null if not specified
     * @param links            null if not specified
     * @param folders          null if not specified, not empty
     * @param compositions     null if not specified, not empty
     * @throws IllegalArgumentException if name null or archetypeNodeId null
     *                                  or links not null and empty
     *                                  or empty folders or empty compositions
     */
    public XFolder(ObjectID uid, String archetypeNodeId, DvText name,
                   Archetyped archetypeDetails, FeederAudit feederAudit,
                   Set<Link> links, Locatable parent, List<XFolder> folders,
                   List<XComposition> compositions) {

        super(uid, archetypeNodeId, name, archetypeDetails, feederAudit, links, parent);

        if (folders != null && folders.isEmpty()) {
            throw new IllegalArgumentException("empty folders");
        }
        if (compositions != null && compositions.isEmpty()) {
            throw new IllegalArgumentException("empty compositions");
        }
        this.folders = folders;
        this.compositions = compositions;
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
     * sub-folders of this folder, including distinct Folder trees,
     * which may be separately archetyped.
     *
     * @return folders or null if unspecified
     */
    public List<XFolder> getFolders() {
        return folders;
    }

    /**
     * X_COMPOSITIONs in this folder
     *
     * @return compositions or null if unspecified
     */
    public List<XComposition> getCompositions() {
        return compositions;
    }

    @Override
	public String pathOfItem(Pathable arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> itemsAtPath(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean pathExists(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pathUnique(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	
    /* fields */
    private List<XFolder> folders;
    private List<XComposition> compositions;	
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
 *  The Original Code is XFolder.java
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