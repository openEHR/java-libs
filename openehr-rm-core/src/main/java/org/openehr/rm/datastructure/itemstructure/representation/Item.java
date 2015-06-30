/*
 * component:   "openEHR Reference Implementation"
 * description: "Class Item"
 * keywords:    "datastructure"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/datastructure/itemstructure/representation/Item.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 22:20:08 +0100 (Wed, 12 Oct 2005) $"
 */
package org.openehr.rm.datastructure.itemstructure.representation;

import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.common.archetyped.FeederAudit;
import org.openehr.rm.common.archetyped.Link;
import org.openehr.rm.common.archetyped.Locatable;
import org.openehr.rm.common.archetyped.Pathable;
import org.openehr.rm.support.identification.UIDBasedID;
import org.openehr.rm.datatypes.text.DvText;

import java.util.Set;

/**
 * The abstract parent of CLUSTER and ELEMENT representation classes.
 *
 * @author Rong Chen
 * @version 1.0
 */
public abstract class Item extends Locatable {

    /**
     * Constructs an Item
     *
     * @param uid
     * @param archetypeNodeId
     * @param name
     * @param archetypeDetails
     * @param feederAudit
     * @param links
     * @throws IllegalArgumentException if archetypeNodeId, name null
     */
    protected Item(UIDBasedID uid, String archetypeNodeId, DvText name,
                   Archetyped archetypeDetails, FeederAudit feederAudit, 
                   Set<Link> links, Pathable parent) {
        super(uid, archetypeNodeId, name, archetypeDetails, feederAudit,
                links, parent);
    }

    /**
     * Constructs a Item node by archetypeNodeId and name
     *
     * @param archetypeNodeId
     * @param name
     * @throws IllegalArgumentException if archetypeNodeId or name null
     */
    protected Item(String archetypeNodeId, DvText name) {
        super(archetypeNodeId, name);
    }

    // POJO start
    protected Item() {
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
 *  The Original Code is Item.java
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