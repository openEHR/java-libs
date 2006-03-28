/*
 * component:   "openEHR Reference Implementation"
 * description: "Class History"
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
package org.openehr.rm.datastructure.history;

import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.common.archetyped.FeederAudit;
import org.openehr.rm.common.archetyped.Link;
import org.openehr.rm.support.identification.ObjectID;
import org.openehr.rm.datastructure.DataStructure;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datatypes.text.DvText;

import java.util.Set;

/**
 * The abstract parent class of various concrete historical
 * structures, currently including discrete series and series of
 * states, either of which may be periodic.
 *
 * @author Rong Chen
 * @version 1.0
 */
public abstract class History <T extends ItemStructure>
        extends DataStructure {

    /**
     * Construct a DataStructure
     *
     * @param uid
     * @param archetypeNodeId
     * @param name
     * @param archetypeDetails
     * @param feederAudit
     * @param links
     * @throws IllegalArgumentException if origin null
     */
    protected History(ObjectID uid, String archetypeNodeId, DvText name,
                      Archetyped archetypeDetails,
                      FeederAudit feederAudit, Set<Link> links,
                      DvDateTime origin) {
        super(uid, archetypeNodeId, name, archetypeDetails, feederAudit,
                links);
        if (origin == null) {
            throw new IllegalArgumentException("null origin");
        }
        this.origin = origin;
    }

    /**
     * Time origin of this event history. The first event is not
     * necessarily at the origin point.
     *
     * @return origin of this event history
     */
    public DvDateTime getOrigin() {
        return origin;
    }

    /**
     * Two History objects equal if both has same value of origin
     *
     * @param o
     * @return true if equals
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!( o instanceof History )) return false;

        final History history = (History) o;

        return origin.equals(history.origin);
    }

    /**
     * Return a hash code of this history
     *
     * @return hash code
     */
    public int hashCode() {
        return origin.hashCode();
    }    

    /* token used in query path */
    public static final String ORIGIN_IS = "origin=";

    /* fields */
    private DvDateTime origin;

    // POJO start
    protected History() {
    }

    void setOrigin(DvDateTime origin) {
        this.origin = origin;
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
 *  The Original Code is History.java
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