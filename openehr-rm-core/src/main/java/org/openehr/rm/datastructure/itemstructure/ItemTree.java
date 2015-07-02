/*
 * component:   "openEHR Reference Implementation"
 * description: "Class ItemTree"
 * keywords:    "datastructure"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/datastructure/itemstructure/ItemTree.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 22:20:08 +0100 (Wed, 12 Oct 2005) $"
 */
package org.openehr.rm.datastructure.itemstructure;

import java.util.List;
import java.util.Set;

import org.openehr.rm.Attribute;
import org.openehr.rm.FullConstructor;
import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.common.archetyped.FeederAudit;
import org.openehr.rm.common.archetyped.Link;
import org.openehr.rm.common.archetyped.Pathable;
import org.openehr.rm.datastructure.itemstructure.representation.Element;
import org.openehr.rm.datastructure.itemstructure.representation.Item;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.support.identification.UIDBasedID;
import org.openehr.rm.util.ItemUtil;

/**
 * Logical tree data structure.
 *
 * @author Rong Chen
 * @version 1.0
 */
public final class ItemTree extends ItemStructure {

    /**
     * Constructs an ItemTree
     *
     * @param uid
     * @param archetypeNodeId
     * @param name
     * @param archetypeDetails
     * @param feederAudit
     * @param links
     * @param items null if unspecified
     */
    @FullConstructor
    	public ItemTree(@Attribute(name = "uid") UIDBasedID uid,
    			@Attribute(name = "archetypeNodeId", required=true) String archetypeNodeId,
                @Attribute(name = "name", required=true) DvText name,
                @Attribute(name = "archetypeDetails") Archetyped archetypeDetails,
                @Attribute(name = "feederAudit") FeederAudit feederAudit,
                @Attribute(name = "links") Set<Link> links,
                @Attribute(name = "parent") Pathable parent,
                @Attribute(name = "items") List<Item> items) {
        super(uid, archetypeNodeId, name, archetypeDetails, feederAudit,
                links, parent);
        this.items = items;
    }

    /**
     * Constructs a ItemStructure
     *
     * @param archetypeNodeId
     * @param name
     * @param items null if unspecified
     */
    public ItemTree(String archetypeNodeId, DvText name, List<Item> items) {
        this(null, archetypeNodeId, name, null, null, null, null, items);
    }
    
    /**
     * Constructs a ItemStructure
     *
     * @param archetypeNodeId
     * @param name
     * @param items null if unspecified
     */
    public ItemTree(String archetypeNodeId, String name, List<Item> items) {
        this(archetypeNodeId, new DvText(name), items);
    }

    /**
     * True if given path is a valid leaf path
     *
     * @param path
     * @return ture if path is valid
     * @throws IllegalArgumentException if path null or empty
     */
    public boolean hasElementPath(String path) {
    	Object value = itemAtPath(path);
    	return value != null;
    
    }

    /**
     * Return the leaf element at the path
     *
     * @param path
     * @return element found
     * @throws IllegalArgumentException if path is not valid
     *                                  or element doesn't exist at given path
     */
    public Element elementAtPath(String path) {
        Object node = itemAtPath(path);
        if(node instanceof Element) {
        	return (Element) node;
        }
        throw new IllegalArgumentException("Invalid path: " + path);
    }

    /**
     * Gets the items
     * 
     * @return null if unspecified
     */
    public List<Item> getItems() {
    	return items;
    }
    
	/**
	 * Equals if two item_tree has same values
	 *
	 * @param o
	 * @return equals if true
	 */
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		if (!super.equals(obj)) {
			return false;
		}
		ItemTree other = (ItemTree) obj;
		if (items == null) {
			if (other.items != null) {
				return false;
			}
		} else if (other.items == null) {
			return false;
		} else if (!ItemUtil.getInstance().compareItems(this.items,
				other.getItems())) {
			return false;
		}
		return true;
	}

	/**
	 * Return a hash code of this actor
	 *
	 * @return hash code
	 */
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((items == null) ? 0 : items.hashCode());
		return result;
	}
    
    /**
     * Return the path to an item relative to the root of this
     * archetyped structure.
     *
     * @param item
     * @return path of given item
     */
    public String pathOfItem(Pathable item) {
        return null;  // todo: implement this method
    }

    @Override
	public Item asHierarchy() {
		// TODO Auto-generated method stub
		return null;
	}
    
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

    // POJO start
    ItemTree() { 
    }
    // POJO end

	private List<Item> items;
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
 *  The Original Code is ItemTree.java
 *
 *  The Initial Developer of the Original Code is Rong Chen.
 *  Portions created by the Initial Developer are Copyright (C) 2003-2008
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