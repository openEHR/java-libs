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
 * file:        "$URL$"
 * revision:    "$LastChangedRevision$"
 * last_change: "$LastChangedDate$"
 */
package org.openehr.rm.datastructure.itemstructure.representation;

import org.openehr.rm.Attribute;
import org.openehr.rm.FullConstructor;
import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.common.archetyped.FeederAudit;
import org.openehr.rm.common.archetyped.Link;
import org.openehr.rm.common.archetyped.Locatable;
import org.openehr.rm.support.identification.ObjectID;
import org.openehr.rm.datatypes.text.DvText;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

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
            public Cluster(@Attribute(name = "uid") ObjectID uid,
                           @Attribute(name = "archetypeNodeId", required = true) String archetypeNodeId,
                           @Attribute(name = "name", required = true) DvText name,
                           @Attribute(name = "archetypeDetails") Archetyped archetypeDetails,
                           @Attribute(name = "feederAudit") FeederAudit feederAudit,
                           @Attribute(name = "links") Set<Link> links,
                           @Attribute(name = "items", required=true) List<Item> items) {

        super(uid, archetypeNodeId, name, archetypeDetails, feederAudit,
                links);

        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("null or empty items");
        }
        this.items = new ArrayList<Item>(items);
    }

    /**
     * Constructs a Item node by archetypeNodeId and name
     *
     * @param archetypeNodeId
     * @param name
     * @throws IllegalArgumentException if archetypeNodeId or name null,
     *                                  or items null or empty
     */
    public Cluster(String archetypeNodeId, DvText name, List<Item> items) {
        this(null, archetypeNodeId, name, null, null, null, items);
    }

    /**
     * Ordered list of items - CLUSTER or ELEMENT objects
     * - under this CLUSTER.
     *
     * @return unmodifiable List of items
     */
    public List<Item> getItems() {
        return Collections.unmodifiableList(items);
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
     * @return item
     * @throws IllegalArgumentException if path invalid
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

    /* fields */
    private List<Item> items;

    // POJO start
    protected Cluster() {
    }

    void setItems(List<Item> items) {
        this.items = items;
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