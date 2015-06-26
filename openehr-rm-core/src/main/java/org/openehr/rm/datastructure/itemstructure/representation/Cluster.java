/*
 * component:   "openEHR Reference Implementation"
 * description: "Class Cluster"
 * keywords:    "datastructure"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/datastructure/itemstructure/representation/Cluster.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 22:20:08 +0100 (Wed, 12 Oct 2005) $"
 */
package org.openehr.rm.datastructure.itemstructure.representation;

import org.openehr.rm.Attribute;
import org.openehr.rm.FullConstructor;
import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.common.archetyped.FeederAudit;
import org.openehr.rm.common.archetyped.Link;
import org.openehr.rm.common.archetyped.Pathable;
import org.openehr.rm.support.identification.UIDBasedID;
import org.openehr.rm.util.ItemUtil;
import org.openehr.rm.datatypes.text.DvText;

import java.util.*;
import java.util.Map.Entry;

/**
 * The grouping variant of Item, which may contain further instances
 * of ITEM, in an ordered list.
 *
 * @author Rong Chen
 * @version 1.0
 */
public final class Cluster extends Item {

    /**
     * Construct a Cluster
     *
     * @param uid
     * @param archetypeNodeId
     * @param name
     * @param archetypeDetails
     * @param feederAudit
     * @param links
     * @param items
     * @throws IllegalArgumentException if archetypeNodeId or name null,
     *                                  or items null or empty
     */
    @FullConstructor
            public Cluster(@Attribute(name = "uid") UIDBasedID uid,
                           @Attribute(name = "archetypeNodeId", required = true) String archetypeNodeId,
                           @Attribute(name = "name", required = true) DvText name,
                           @Attribute(name = "archetypeDetails") Archetyped archetypeDetails,
                           @Attribute(name = "feederAudit") FeederAudit feederAudit,
                           @Attribute(name = "links") Set<Link> links,
                           @Attribute(name = "parent") Pathable parent, 
	    @Attribute(name = "items") List<Item> items) {

        super(uid, archetypeNodeId, name, archetypeDetails, feederAudit,
                links, parent);

	if (items != null && items.isEmpty()) { //Skip items==null check to facilitate item skeleton generation
            throw new IllegalArgumentException("null or empty items");
        }
	if (items!=null){
        this.items = new ArrayList<Item>(items);
    }
    }

    /**
     * Constructs a cluster list of items
     *
     * @param archetypeNodeId
     * @param name
     * @param items
     * @throws IllegalArgumentException if archetypeNodeId or name null,
     *                                  or items null or empty
     */
    public Cluster(String archetypeNodeId, DvText name, List<Item> items) {
        this(null, archetypeNodeId, name, null, null, null, null, items);
    }
    
    /**
     * Constructs a cluster list of items
     *
     * @param archetypeNodeId
     * @param name
     * @param items
     * @throws IllegalArgumentException if archetypeNodeId or name null,
     *                                  or items null or empty
     */
    public Cluster(String archetypeNodeId, String name, List<Item> items) {
        this(archetypeNodeId, new DvText(name), items);
    }

    /**
     * Ordered list of items - CLUSTER or ELEMENT objects
     * - under this CLUSTER.
     *
     * @return items of this cluster
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * String The path to an item relative to the root of this
     * archetyped structure.
     *
     * @param item
     * @return path of given item
     */
    public String pathOfItem(Pathable item) {
        return null;  // todo: implement this method
    }

    // POJO start
    protected Cluster() {
    }

    void setItems(List<Item> items) {
        this.items = items;
    }
    // POJO end

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
    private List<Item> items;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((items == null) ? 0 : items.hashCode());
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
		Cluster other = (Cluster) obj;
		if (items == null) {
			if (other.items != null) {
				return false;
			}
		} else if (other.items == null) {
			return false;
		} else if (!ItemUtil.getInstance()
				.compareItems(items, other.getItems())) {
			return false;
		}
		return true;
	}
	
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
 *  The Original Code is Cluster.java
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