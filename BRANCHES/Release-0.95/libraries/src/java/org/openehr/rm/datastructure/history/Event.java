/*
 * component:   "openEHR Reference Implementation"
 * description: "Class Event"
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
import org.openehr.rm.common.archetyped.Locatable;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;
import org.openehr.rm.datatypes.quantity.datetime.DvDuration;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.support.terminology.TerminologyService;

/**
 * Defines a single event in a series. Instances of this class are immutable.
 *
 * @author Rong Chen
 * @version 1.0
 */
public final class Event <T extends ItemStructure> extends ItemEvent<T> {

    /**
     * Constructs an Event
     *
     * @param item
     * @param width
     * @param mathFunction
     * @param terminologyService
     * @param offset
     * @throws IllegalArgumentException if offset null
     */
    @FullConstructor
            public Event(@Attribute(name = "item", required = true) T item,
                         @Attribute(name = "width") DvDuration width,
                         @Attribute(name = "mathFunction") DvCodedText mathFunction,
                         @Attribute(name = "terminologyService", system=true) TerminologyService terminologyService,
                         @Attribute(name = "offset", required = true) DvDuration offset) {
        super(item, width, mathFunction, terminologyService);
        if (offset == null) {
            throw new IllegalArgumentException("null offset");
        }
        this.offset = offset;
    }

    /**
     * Constructs an instantaneous event
     *
     * @param item
     * @param offset
     */
    public Event(T item, DvDuration offset) {
        this(item, null, null, null, offset);
    }

    /**
     * Offset of this sample from the origin of the history
     *
     * @return offset
     */
    public DvDuration getOffset() {
        return offset;
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

    // POJO start
    Event() {
    }

    void setOffset(DvDuration offset) {
        this.offset = offset;
    }
    // POJO end

    /* fields */
    private DvDuration offset;    
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
 *  The Original Code is Event.java
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