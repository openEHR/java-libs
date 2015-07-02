/*
 * component:   "openEHR Reference Implementation"
 * description: "Class ItemSingle"
 * keywords:    "datastructure"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/datastructure/itemstructure/ItemSingle.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 22:20:08 +0100 (Wed, 12 Oct 2005) $"
 */
package org.openehr.rm.datastructure.itemstructure;

import org.openehr.rm.Attribute;
import org.openehr.rm.FullConstructor;
import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.common.archetyped.FeederAudit;
import org.openehr.rm.common.archetyped.Link;
import org.openehr.rm.common.archetyped.Pathable;
import org.openehr.rm.support.identification.UIDBasedID;
import org.openehr.rm.datastructure.itemstructure.representation.Element;
import org.openehr.rm.datastructure.itemstructure.representation.Item;
import org.openehr.rm.datatypes.text.DvText;

import java.util.*;

/**
 * Logical single value data structure. Instances of this class are immutable.
 *
 * @author Rong Chen
 * @version 1.0
 */
public final class ItemSingle extends ItemStructure {

    /**
     * Construct a ItemSingle
     *
     * @param uid
     * @param archetypeNodeId
     * @param name
     * @param archetypeDetails
     * @param feederAudit
     * @param links
     * @param item not null
     * @throws IllegalArgumentException if item null
     */
    @FullConstructor
    	public ItemSingle(@Attribute(name = "uid") UIDBasedID uid,
    			@Attribute(name = "archetypeNodeId", required=true) String archetypeNodeId,
                @Attribute(name = "name", required=true) DvText name,
                @Attribute(name = "archetypeDetails") Archetyped archetypeDetails,
                @Attribute(name = "feederAudit") FeederAudit feederAudit,
                @Attribute(name = "links") Set<Link> links,
                @Attribute(name = "parent") Pathable parent, 
                @Attribute(name = "item", required=true) Element item) {
        super(uid, archetypeNodeId, name, archetypeDetails, feederAudit,
                links, parent);
        if(item == null) {
        	throw new IllegalArgumentException("item null");
        }
        this.item = item;
    }

    /**
     * Construct a ItemSingle
     *
     * @param archetypeNodeId
     * @param name
     * @param representation
     * @throws IllegalArgumentException if representation null
     */
    public ItemSingle(String archetypeNodeId, DvText name, Element item) {
        this(null, archetypeNodeId, name, null, null, null, null, item);
    }
    
    /**
     * Construct a ItemSingle
     *
     * @param archetypeNodeId
     * @param name
     * @param representation
     * @throws IllegalArgumentException if representation null
     */
    public ItemSingle(String archetypeNodeId, String name, Element item) {
        this(archetypeNodeId, new DvText(name), item);
    }

    /**
     * Retrieves the item.
     *
     * @return item
     */
    public Element getItem() {
        return item;
    }

    /**
     * Return the path to an item relative to the root of this
     * archetyped structure.
     *
     * @param item
     * @return path
     */
    public String pathOfItem(Pathable item) {
        return null;  // todo: implement this method
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
    ItemSingle() {
    }
    // POJO end	

	@Override
	public Item asHierarchy() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((item == null) ? 0 : item.hashCode());
		return result;
	}

	@Override
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

		ItemSingle other = (ItemSingle) obj;
		if (item == null) {
			if (other.item != null) {
				return false;
			}
		} else if (!item.equals(other.item)) {
			return false;
		}
		return true;
	}

	private Element item;
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
 *  The Original Code is ItemSingle.java
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