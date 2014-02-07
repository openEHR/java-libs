/*
 * component:   "openEHR Reference Implementation"
 * description: "Class DataStructure"
 * keywords:    "datastructure"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/datastructure/DataStructure.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 22:20:08 +0100 (Wed, 12 Oct 2005) $"
 */
package org.openehr.rm.datastructure;

import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.common.archetyped.FeederAudit;
import org.openehr.rm.common.archetyped.Link;
import org.openehr.rm.common.archetyped.Locatable;
import org.openehr.rm.common.archetyped.Pathable;
import org.openehr.rm.support.identification.UIDBasedID;
import org.openehr.rm.datastructure.itemstructure.representation.Item;
import org.openehr.rm.datatypes.text.DvText;

import java.util.Set;

/**
 * Abstract parent class of all data structure types
 *
 * @author Rong Chen
 * @version 1.0
 */
public abstract class DataStructure extends Locatable {

    /**
     * Construct a DataStructure
     *
     * @param uid
     * @param archetypeNodeId
     * @param name
     * @param archetypeDetails
     * @param feederAudit
     * @param links
     */
    protected DataStructure(UIDBasedID uid, String archetypeNodeId, DvText name,
                            Archetyped archetypeDetails, FeederAudit feederAudit, 
                            Set<Link> links, Pathable parent) {
        super(uid, archetypeNodeId, name, archetypeDetails, feederAudit,
                links, parent);
    }

    /**
     * Constructs a Locatable node by archetypeNodeId and name
     *
     * @param archetypeNodeId
     * @param name
     * @throws IllegalArgumentException if archetypeNodeId or name null
     */
    protected DataStructure(String archetypeNodeId, DvText name) {
        super(archetypeNodeId, name);
    }
    
    /**
     * Returns a hierarchical equivalent of the physical representation 
     * of each subtype, compatible with CEN EN 13606 structures.
     * 
     * @return item
     */
    public abstract Item asHierarchy();

    /**
     * Path that represents whole node
     *
     * @return path for whole node
     */
    public String whole() {
        return PATH_SEPARATOR + getName().getValue();
    }

    // POJO start
    protected DataStructure() {
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
 *  The Original Code is DataStructure.java
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