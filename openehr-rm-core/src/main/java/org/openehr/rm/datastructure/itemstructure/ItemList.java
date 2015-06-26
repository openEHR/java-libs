/*
 * component:   "openEHR Reference Implementation"
 * description: "Class ItemList"
 * keywords:    "datastructure"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/datastructure/itemstructure/ItemList.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 22:20:08 +0100 (Wed, 12 Oct 2005) $"
 */
package org.openehr.rm.datastructure.itemstructure;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.openehr.rm.Attribute;
import org.openehr.rm.FullConstructor;
import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.common.archetyped.FeederAudit;
import org.openehr.rm.common.archetyped.Link;
import org.openehr.rm.common.archetyped.Pathable;
import org.openehr.rm.support.identification.UIDBasedID;
import org.openehr.rm.util.ItemUtil;
import org.openehr.rm.datastructure.itemstructure.representation.Element;
import org.openehr.rm.datastructure.itemstructure.representation.Item;
import org.openehr.rm.datatypes.text.DvText;

import java.util.*;
import java.util.Map.Entry;

/**
 * Logical list data structure, where each item has a value and can
 * be referred to by a name and a positional index in the list.
 *
 * @author Rong Chen
 * @version 1.0
 */
public final class ItemList extends ItemStructure {

    /**
     * Constructs an ItemList with a list of items
     *
     * @param uid
     * @param archetypeNodeId
     * @param name
     * @param archetypeDetails
     * @param feederAudit
     * @param links
     * @param items	null if unspecified
     */
    @FullConstructor
    	public ItemList(@Attribute(name = "uid") UIDBasedID uid,
    			@Attribute(name = "archetypeNodeId", required = true) String archetypeNodeId,
                @Attribute(name = "name", required = true) DvText name,
                @Attribute(name = "archetypeDetails") Archetyped archetypeDetails,
                @Attribute(name = "feederAudit") FeederAudit feederAudit,
                @Attribute(name = "links") Set<Link> links,
                @Attribute(name = "parent") Pathable parent, 
                @Attribute(name = "items") List<Element> items) {
        super(uid, archetypeNodeId, name, archetypeDetails, feederAudit,
                links, parent);
        this.items = items;
    }
    
    /**
     * Construct a ItemList with list of elements
     *
     * @param archetypeNodeId
     * @param name
     * @param items
     */
    public ItemList(String archetypeNodeId, DvText name, List<Element> items) {
        this(null, archetypeNodeId, name, null, null, null, null, items);
    }
    
    /**
     * Construct a ItemList with list of elements
     *
     * @param archetypeNodeId
     * @param name as string
     * @param items
     */
    public ItemList(String archetypeNodeId, String name, List<Element> items) {
        this(archetypeNodeId, new DvText(name), items);
    }

    /**
     * Returns the count of all items
     *
     * @return item count
     */
    public int itemCount() {
        return items.size();
    }

    /**
     * Retrieves all items
     *
     * @return List of Element
     */
    public List<Element> getItems() {
        return items;
    }

    /**
     * Retrieves the names of all items as a list
     *
     * @return list of names
     */
    public List<DvText> names() {
        List<DvText> names = new ArrayList<DvText>();
        for (Element element : items) {
            names.add(element.getName());
        }
        return names;
    }

    /**
     * Retrieves the item with given name
     *
     * @param name
     * @return null if item of given name not found
     * @throws IllegalArgumentException if name is null or empty
     */
    public Element namedItem(String name) {
        if (StringUtils.isEmpty(name)) {
            throw new IllegalArgumentException("empty name");
        }
        for (Element element : items) {
            if (name.equals(element.getName().getValue())) {
                return element;
            }
        }
        return null;
    }

    /**
     * Retrieve item at specified position
     *
     * @param index starts with 0
     * @return element at given position
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   (index < 0 || index >= size()).
     */
    public Element ithItem(int index) {
        return getItems().get(index);
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
		ItemList other = (ItemList) obj;
		if (items == null) {
			if (other.items != null) {
				return false;
			}
		} else if (other.items == null) {
			return false;
		} else if (!ItemUtil.getInstance().compareElements(this.items,
				other.getItems())) {
			return false;
		}
		return true;
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
	
	@Override
	public Item asHierarchy() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
    /* calculated field */
    private List<Element> items;

    // POJO start
    ItemList() {
    }
    // POJO end
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
 *  The Original Code is ItemList.java
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