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
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/common/directory/Folder.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 22:20:08 +0100 (Wed, 12 Oct 2005) $"
 */
package org.openehr.rm.common.directory;

import java.util.List;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.common.archetyped.FeederAudit;
import org.openehr.rm.common.archetyped.Link;
import org.openehr.rm.common.archetyped.Locatable;
import org.openehr.rm.common.archetyped.Pathable;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.support.identification.UIDBasedID;
import org.openehr.rm.support.identification.ObjectRef;

public class Folder extends Locatable {

    /**
     * Construts a Folder
     *
     * @param uid              null if not specified
     * @param archetypeNodeId  not null
     * @param name             not null
     * @param archetypeDetails null if not specified
     * @param feederAudit      null if not specified
     * @param links            null if not specified
     * @param folders			 null if not specified
     * @param items			 null if not specified
     * @throws IllegalArgumentException if name null or archetypeNodeId null
     *                                  or links not null and empty
     */
    public Folder(UIDBasedID uid, String archetypeNodeId, DvText name,
            Archetyped archetypeDetails, FeederAudit feederAudit, Set<Link> links,
            Pathable parent, List<Folder> folders, List<ObjectRef> items) {
        super(uid, archetypeNodeId, name, archetypeDetails, feederAudit, links, parent);
        if(folders != null && folders.size() == 0) {
            throw new IllegalArgumentException("empty sub-folders");
        }
        this.folders = folders;
        this.items = items;
    }
    
    /**
     * Sub-folders of this FOLDER
     * @return folder  	A list of folders which are the sub-folders of this FOLDER
     */
    public List<Folder> getFolders() {
        return folders;
    }
    
    /**
     * The list of references to other versioned objects logically in this FOLDER
     * @return items 	List of versioned items in this FOLDER
     */
    public List<ObjectRef> getItems() {
        return items;
    }
    
    /**
     * Equals if two folders have the same values
     *
     * @param o
     * @return equals if true
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!( o instanceof Folder )) return false;
        if (!super.equals(o)) return false;
        
        final Folder folder = (Folder) o;
        return new EqualsBuilder()
//                .appendSuper(super.equals(o))
        .append(folders, folder.folders)
        .append(items, folder.items)
        .isEquals();
    }
    
    /**
     * Return a hash code of this folder
     *
     * @return hash code
     */
    public int hashCode() {
        return new HashCodeBuilder(41, 97)
        .appendSuper(super.hashCode())
        .append(folders)
        .append(items)
        .toHashCode();
    }
    @Override
            public String pathOfItem(Pathable item) {
        // TODO Auto-generated method stub
        return null;
    }
    
    //POJO starts
    Folder() {
    }
    
    void setFolders(List<Folder> folders) {
        this.folders = folders;
    }
    
    void setItems(List<ObjectRef> items) {
        this.items = items;
    }
    //POJO ends
    
    @Override
	public List<Object> itemsAtPath(String path) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean pathExists(String path) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pathUnique(String path) {
		// TODO Auto-generated method stub
		return false;
	}
	
    /* fields */
    private List<Folder> folders;
    private List<ObjectRef> items;	
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