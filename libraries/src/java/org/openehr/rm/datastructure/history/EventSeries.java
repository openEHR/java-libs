/*
 * component:   "openEHR Reference Implementation"
 * description: "Class EventSeries"
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

import org.openehr.rm.Attribute;
import org.openehr.rm.FullConstructor;
import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.common.archetyped.FeederAudit;
import org.openehr.rm.common.archetyped.Link;
import org.openehr.rm.common.archetyped.Locatable;
import org.openehr.rm.support.identification.ObjectID;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datatypes.quantity.datetime.DvDuration;
import org.openehr.rm.datatypes.text.DvText;

import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Purpose Defines the semantics of a time segment which consists of
 * events which occur at known instants of time, and which may be
 * periodically spaced. This class is generic, allowing types to be
 * generated which are locked to particular structure types, such as
 * EVENT_SERIES<ITEM_LIST>
 * <p/>
 * Instances of this class are immutable.
 *
 * @author Rong Chen
 * @version 1.0
 */
public final class EventSeries <T extends ItemStructure> extends History<T> {

    /**
     * Construct a DataStructure
     *
     * @param uid
     * @param archetypeNodeId
     * @param name
     * @param archetypeDetails
     * @param feederAudit
     * @param links
     * @param origin
     * @param items
     * @param period           null if not periodic
     * @throws IllegalArgumentException if origin null
     *                                  or items empty
     */
    @FullConstructor
            public EventSeries(@Attribute(name = "uid") ObjectID uid,
                               @Attribute(name = "archetypeNodeId", required = true) String archetypeNodeId,
                               @Attribute(name = "name", required = true) DvText name,
                               @Attribute(name = "archetypeDetails") Archetyped archetypeDetails,
                               @Attribute(name = "feederAudit") FeederAudit feederAudit,
                               @Attribute(name = "links") Set<Link> links,
                               @Attribute(name = "origin", required = true) DvDateTime origin,
                               @Attribute(name = "items", required = true) List<Event<T>> items,
                               @Attribute(name = "period") DvDuration period) {
        super(uid, archetypeNodeId, name, archetypeDetails, feederAudit,
                links, origin);
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("null or empty items");
        }
        this.items = items;
        this.period = period;
    }

    /**
     * Construct a non-periodic EventSeries
     *
     * @param archetypeNodeId
     * @param name
     * @param origin
     * @param items
     * @throws IllegalArgumentException if meaning or origin null,
     *                                  or items null or empty
     */
    public EventSeries(String archetypeNodeId, DvText name, DvDateTime origin,
                       List<Event<T>> items) {
        this(null, archetypeNodeId, name, null, null, null, origin, items,
                null);
    }


    /**
     * Return the items in the series.
     *
     * @return unmodifiable list of items
     */
    public List<Event<T>> getItems() {
        return Collections.unmodifiableList(items);
    }

    /**
     * Reriod between samples in this segment if periodic
     *
     * @return period
     */
    public DvDuration getPeriod() {
        return period;
    }

    /**
     * Indicates whether history is periodic.
     *
     * @return true if periodic
     */
    public boolean isPeriodic() {
        return period != null;
    }

    /**
     * String The path to an item relative to the root of this
     * archetyped structure.
     *
     * @param item
     * @return string path
     */
    public String pathOfItem(Locatable item) {
        return null;  // todo: implement this method
    }

    /**
     * The item at a path that is relative to this item.
     *
     * @param path
     * @return relative path
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
    private List<Event<T>> items;
    private DvDuration period;

    // POJO start
    EventSeries() {
    }

    void setItems(List<Event<T>> items) {
        this.items = items;
    }

    void setPeriod(DvDuration period) {
        this.period = period;
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
 *  The Original Code is EventSeries.java
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