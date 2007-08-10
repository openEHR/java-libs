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
import org.openehr.rm.common.archetyped.Locatable;
import org.openehr.rm.support.identification.ObjectID;
import org.openehr.rm.datastructure.itemstructure.representation.Element;
import org.openehr.rm.datatypes.text.DvText;

import java.util.Set;

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
     * @param representation
     */
    @FullConstructor
            public ItemSingle(@Attribute(name = "uid") ObjectID uid,
                              @Attribute(name = "archetypeNodeId", required=true) String archetypeNodeId,
                              @Attribute(name = "name", required=true) DvText name,
                              @Attribute(name = "archetypeDetails") Archetyped archetypeDetails,
                              @Attribute(name = "feederAudit") FeederAudit feederAudit,
                              @Attribute(name = "links") Set<Link> links,
                              @Attribute(name = "parent") Locatable parent, 
                              @Attribute(name = "representation", required=true) Element representation) {
        super(uid, archetypeNodeId, name, archetypeDetails, feederAudit,
                links, parent, representation);
    }

    /**
     * Construct a ItemSingle
     *
     * @param archetypeNodeId
     * @param name
     * @param representation
     * @throws IllegalArgumentException if representation null
     */
    public ItemSingle(String archetypeNodeId, DvText name,
                      Element representation) {
        this(null, archetypeNodeId, name, null, null, null, null, 
                representation);
    }

    /**
     * Retrieve the item.
     *
     * @return item
     */
    public Element getItem() {
        return (Element) getRepresentation();
    }

    /**
     * Return the path to an item relative to the root of this
     * archetyped structure.
     *
     * @param item
     * @return path
     */
    public String pathOfItem(Locatable item) {
        return null;  // todo: implement this method
    }

    /**
     * The item at a path that is relative to this item.
     *
     * @param path
     * @return relative item
     * @throws IllegalArgumentException if path invalid
     */
    public Locatable itemAtPath(String path) {
        if(validPath(path)) {
            return getItem();
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
        return whole().equals(path);
    }

    // POJO start
    ItemSingle() {
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
 *  The Original Code is ItemSingle.java
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