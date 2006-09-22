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
import org.openehr.rm.Attribute;
import org.openehr.rm.FullConstructor;
import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.common.archetyped.FeederAudit;
import org.openehr.rm.common.archetyped.Link;
import org.openehr.rm.common.archetyped.Locatable;
import org.openehr.rm.support.identification.ObjectID;
import org.openehr.rm.datastructure.itemstructure.representation.Cluster;
import org.openehr.rm.datastructure.itemstructure.representation.Element;
import org.openehr.rm.datastructure.itemstructure.representation.Item;
import org.openehr.rm.datatypes.text.DvText;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Logical list data structure, where each item has a value and can
 * be referred to by a name and a positional index in the list.
 * Instances of this class are immutable.
 *
 * @author Rong Chen
 * @version 1.0
 */
public final class ItemList extends ItemStructure {

    /**
     * Constructs an ItemList
     *
     * @param uid
     * @param archetypeNodeId
     * @param name
     * @param archetypeDetails
     * @param feederAudit
     * @param links
     * @param representation
     */
    @FullConstructor
            public ItemList(@Attribute(name = "uid") ObjectID uid,
                            @Attribute(name = "archetypeNodeId", required = true) String archetypeNodeId,
                            @Attribute(name = "name", required = true) DvText name,
                            @Attribute(name = "archetypeDetails") Archetyped archetypeDetails,
                            @Attribute(name = "feederAudit") FeederAudit feederAudit,
                            @Attribute(name = "links") Set<Link> links,
                            @Attribute(name = "parent") Locatable parent, 
                            @Attribute(name = "representation", required = true) Cluster representation) {

        super(uid, archetypeNodeId, name, archetypeDetails, feederAudit,
                links, parent, representation);
        this.items = convert(representation);
    }

    /**
     * Construct a ItemStructure
     *
     * @param archetypeNodeId
     * @param name
     * @param representation
     * @throws IllegalArgumentException if representation null
     */
    public ItemList(String archetypeNodeId, DvText name,
                    Cluster representation) {
        this(null, archetypeNodeId, name, null, null, null, null, representation);
    }

    private List<Element> convert(Cluster cluster) {
        List<Element> items = new ArrayList<Element>();
        for (Item item : cluster.getItems()) {
            items.add((Element) item);
        }
        return items;
    }

    /**
     * Count of all items
     *
     * @return item count
     */
    public int itemCount() {
        return items.size();
    }

    /**
     * Retrieve all items
     *
     * @return List of Element
     */
    public List<Element> items() {
        return items;
    }

    /**
     * Retrieve the names of all items
     *
     * @return List of Text
     */
    public List<DvText> names() {
        List<DvText> names = new ArrayList<DvText>();
        for (Element element : items) {
            names.add(element.getName());
        }
        return names;
    }

    /**
     * Retrieve the item with given name
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
        return items().get(index);
    }

    /**
     * Return the path to an item relative to the root of this
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
     * @return item
     * @throws IllegalArgumentException if path invalid
     */
    public Locatable itemAtPath(String path) {
        String whole = whole();
        if (path == null || path.indexOf(whole) < 0) {
            throw new IllegalArgumentException("invalid path: " + path);
        }
        // root path
        if (path.equals(whole)) {
            return this;
        }
        String subpart = path.substring(whole.length() + 1);

        // nth list item
        if (subpart.startsWith(ITEM_IS)) {
            String num = subpart.substring(ITEM_IS.length());
            int index = -1;
            try {
                index = Integer.parseInt(num);
            } catch (NumberFormatException ignored) {
            }
            if (index >= 0 && index < itemCount()) {
                return items.get(index);
            }
            throw new IllegalArgumentException("invalid path: " + path);
        }

        // named list item
        for (Element item : items) {
            if (subpart.equals(item.getName().getValue())) {
                return item;
            }
        }
        throw new IllegalArgumentException("invalid path: " + path);
    }

    /**
     * Return true if the path is valid with respect to the current
     * item.
     *
     * @param path
     * @return true if valid
     */
    public boolean validPath(String path) {
        try {
            itemAtPath(path);
            return true;
        } catch(IllegalArgumentException iae) {
            return false;
        }
    }

    /* token used in query path */
    public final static String ITEM_IS = "item=";

    /* calculated field */
    private List<Element> items;

    // POJO start
    ItemList() {
    }

    void setRepresentation(Item item) {
        super.setRepresentation(item);
        this.items = convert((Cluster) item);
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