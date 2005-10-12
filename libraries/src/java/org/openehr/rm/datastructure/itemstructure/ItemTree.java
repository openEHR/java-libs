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
 * file:        "$URL$"
 * revision:    "$LastChangedRevision$"
 * last_change: "$LastChangedDate$"
 */
package org.openehr.rm.datastructure.itemstructure;

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

import java.util.*;

/**
 * Logical tree data structure. Instances of this class are immutable.
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
     * @param representation
     */
    @FullConstructor
            public ItemTree(@Attribute(name = "uid") ObjectID uid,
                            @Attribute(name = "archetypeNodeId", required=true) String archetypeNodeId,
                            @Attribute(name = "name", required=true) DvText name,
                            @Attribute(name = "archetypeDetails") Archetyped archetypeDetails,
                            @Attribute(name = "feederAudit") FeederAudit feederAudit,
                            @Attribute(name = "links") Set<Link> links,
                            @Attribute(name = "representation", required=true) Cluster representation) {
        super(uid, archetypeNodeId, name, archetypeDetails, feederAudit,
                links, representation);
    }

    /**
     * Construct a ItemStructure
     *
     * @param archetypeNodeId
     * @param name
     * @param representation
     * @throws IllegalArgumentException if representation null
     */
    public ItemTree(String archetypeNodeId, DvText name,
                    Cluster representation) {
        this(null, archetypeNodeId, name, null, null, null, representation);
    }

    /**
     * True if given path is a valid leaf path
     *
     * @param path
     * @return ture if path is valid
     * @throws IllegalArgumentException if path null or empty
     */
    public boolean hasElementPath(String path) {
        List<String> pathList = checkAndParsePath(path);
        return validRootPath(pathList)
                && fetchElement(cluster().getItems(),
                        pathList, 1) != null;
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
        List<String> pathList = checkAndParsePath(path);
        if (!validRootPath(pathList)) {
            throw new IllegalArgumentException("invalid path");
        }
        Element element = fetchElement(cluster().getItems(),
                pathList, 1);
        if (element == null) {
            throw new IllegalArgumentException("invalid path");
        }
        return element;
    }

    private boolean validRootPath(List<String> paths) {
        return getName().getValue().equals(paths.get(0));
    }

    // return null if not found
    private Element fetchElement(List items, List path,
                                 int pathIndex) {
        if (pathIndex >= path.size()) {
            return null; // path exhausted
        }
        String name = (String) path.get(pathIndex);
        Element ret = null;
        for (Iterator it = items.iterator(); it.hasNext();) {
            Item item = (Item) it.next();
            if (item instanceof Element
                    && pathIndex == path.size() - 1
                    && item.getName().getValue().equals(name)) {
                return (Element) item; // leaf found
            } else if (item instanceof Cluster
                    && item.getName().getValue().equals(name)) {
                Cluster c = (Cluster) item;
                ret = fetchElement(c.getItems(), path,
                        pathIndex + 1);
            }
        }
        return ret;
    }

    private Cluster cluster() {
        return (Cluster) getRepresentation();
    }

    private List<String> checkAndParsePath(String path) {
        checkPath(path);
        StringTokenizer tokens = new StringTokenizer(path,
                PATH_SEPARATOR);
        if (tokens.countTokens() < 1) {
            throw new IllegalArgumentException("invalid path: " + path);
        }
        List<String> pathList = new ArrayList<String>();
        while (tokens.hasMoreTokens()) {
            pathList.add(tokens.nextToken());
        }
        return pathList;
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
    ItemTree() {
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
 *  The Original Code is ItemTree.java
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