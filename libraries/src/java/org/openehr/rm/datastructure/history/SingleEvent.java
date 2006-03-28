/*
 * component:   "openEHR Reference Implementation"
 * description: "Class SingleEvent"
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
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.support.terminology.TerminologyService;

import java.util.Set;

/**
 * A subtype of HISTORY<T> catering for the very common case of
 * single events. The motivation for this class is to reduce the
 * number of temporal objects associated with a datum to one.
 * <p/>
 * Instances of this class are immutable.
 *
 * @author Rong Chen
 * @version 1.0
 */
public final class SingleEvent <T extends ItemStructure> extends History<T> {

    /**
     * Construct a SingleEvent
     *
     * @param uid
     * @param archetypeNodeId
     * @param name
     * @param archetypeDetails
     * @param feederAudit
     * @param links
     * @param origin
     * @param item
     * @param width
     * @param mathFunction
     * @param terminologyService
     * @throws IllegalArgumentException if origin null
     */
    @FullConstructor
            public SingleEvent(@Attribute(name = "uid") ObjectID uid,
                               @Attribute(name = "archetypeNodeId", required = true) String archetypeNodeId,
                               @Attribute(name = "name", required = true) DvText name,
                               @Attribute(name = "archetypeDetails") Archetyped archetypeDetails,
                               @Attribute(name = "feederAudit") FeederAudit feederAudit,
                               @Attribute(name = "links") Set<Link> links,
                               @Attribute(name = "origin", required = true) DvDateTime origin,
                               @Attribute(name = "item", required = true) T item,
                               @Attribute(name = "width") DvDuration width,
                               @Attribute(name = "mathFunction") DvCodedText mathFunction,
                               @Attribute(name = "terminologyService", system = true) TerminologyService terminologyService) {

        super(uid, archetypeNodeId, name, archetypeDetails, feederAudit,
                links, origin);
        if (item == null) {
            throw new IllegalArgumentException("null itemEvent");
        }
        if (mathFunction != null) {
            if (terminologyService == null) {
                throw new IllegalArgumentException("null terminologyService");
            }
            if (!terminologyService.terminology(TerminologyService.OPENEHR)
                    .hasCodeForGroupName(mathFunction.getDefiningCode(),
                            "event math function", "en")) {
                throw new IllegalArgumentException(
                        "unknown mathFunction: " + mathFunction);
            }
        }
        this.itemEvent =
                new ItemEvent<T>(item, width, mathFunction, terminologyService);
    }

    /**
     * Construct a DataStructure
     *
     * @param archetypeNodeId
     * @param name
     * @param origin
     * @param item
     * @throws IllegalArgumentException if origin null
     */
    public SingleEvent(String archetypeNodeId, DvText name,
                       DvDateTime origin, T item) {
        this(null, archetypeNodeId, name, null, null, null, origin, item,
                null, null, null);
    }


    /**
     * Length of the interval during which the state was true
     *
     * @return null if an instantaneous event
     */
    public DvDuration getWidth() {
        return itemEvent.getWidth();
    }

    /**
     * Mathematical function for non-instantaneous events
     * - eg  maximum, mean etc. Coded using openEHR Terminology
     * group "event math function".
     *
     * @return null if not present
     */
    public DvCodedText getMathFunction() {
        return itemEvent.getMathFunction();
    }

    /**
     * True if this event is instantaneous
     *
     * @return true if width null
     */
    public boolean isInstantaneous() {
        return itemEvent.getWidth() == null;
    }

    /**
     * String The path to an itemEvent relative to the root of this
     * archetyped structure.
     *
     * @param item
     * @return string path
     */
    public String pathOfItem(Locatable item) {
        return null;  // todo: implement this method
    }

    /**
     * The itemEvent at a path that is relative to this itemEvent.
     *
     * @param path
     * @return itemEvent
     * @throws IllegalArgumentException if path invalid
     */
    public Locatable itemAtPath(String path) {
        Locatable item = super.itemAtPath(path);
        if (item != null) {
            return item;
        }
        String whole = whole();
        if (path.startsWith(whole)) {
            if (path.length() == whole().length()) {
                return this;
            } else {
                return itemEvent.getItem().itemAtPath(
                        path.substring(whole.length()));
            }
        }
        String tmp = PATH_SEPARATOR + ORIGIN_IS;
        if (!path.startsWith(tmp)) {
            throw new IllegalArgumentException("invalid path: " + path);
        }
        String subpart = path.substring(tmp.length());
        int index = subpart.indexOf(PATH_SEPARATOR);
        String dt_str = index < 0 ? subpart : subpart.substring(0, index);
        try {
            DvDateTime datetime = new DvDateTime(dt_str);
            if (getOrigin().equals(datetime)) {
                return index < 0 ?
                        this :
                        itemEvent.getItem().itemAtPath(
                                subpart.substring(index));
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("invalid path: " + path);
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

    /**
     * The data of this event
     *
     * @return itemEvent
     */
    public T getItem() {
        return itemEvent.getItem();
    }

    // POJO start
    SingleEvent() {
    }

    void setItemEvent(ItemEvent<T> itemEvent) {
        this.itemEvent = itemEvent;
    }

    ItemEvent<T> getItemEvent() {
        return itemEvent;
    }
    // POJO end

    /* fields */
    private ItemEvent<T> itemEvent; // add final
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
 *  The Original Code is SingleEvent.java
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