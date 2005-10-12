/*
 * component:   "openEHR Reference Implementation"
 * description: "Class ItemEvent"
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

import org.openehr.rm.common.archetyped.Locatable;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;
import org.openehr.rm.datatypes.quantity.datetime.DvDuration;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.support.terminology.TerminologyService;

/**
 * Abstract generic class modelling an event not anchored in time.
 *
 * @author Rong Chen
 * @version 1.0
 */
public class ItemEvent <T extends ItemStructure> extends Locatable {

    /**
     * Constructs an ItemEvent
     *
     * @param item
     * @param width              null if not present
     * @param mathFunction       null if not present
     * @param terminologyService required if mathFunction present
     * @throws IllegalArgumentException if item null
     *                                  or mathFunction not null and unkonwn
     */
    public ItemEvent(T item, DvDuration width,
                     DvCodedText mathFunction,
                     TerminologyService terminologyService) {
        if (item == null) {
            throw new IllegalArgumentException("null item");
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
        this.item = item;
        this.width = width;
        this.mathFunction = mathFunction;
    }

    /**
     * Create an instantaneous item event
     *
     * @param item
     */
    public ItemEvent(T item) {
        this(item, null, null, null);
    }

    /**
     * The data of this event
     *
     * @return item
     */
    public T getItem() {
        return item;
    }

    /**
     * Length of the interval during which the state was true
     *
     * @return null if an instantaneous event
     */
    public DvDuration getWidth() {
        return width;
    }

    /**
     * Mathematical function for non-instantaneous events
     * - eg  maximum, mean etc. Coded using openEHR Terminology
     * group "event math function".
     *
     * @return null if not present
     */
    public DvCodedText getMathFunction() {
        return mathFunction;
    }

    /**
     * True if this event is instantaneous
     *
     * @return true if width null
     */
    public boolean isInstantaneous() {
        return width == null;
    }

    /**
     * String The path to an item relative to the root of this
     * archetyped structure.
     *
     * @param item
     * @return string path
     */
    public String pathOfItem(Locatable item) {
        return null;
    }

    /**
     * The item at a path that is relative to this item.
     *
     * @param path
     * @return relative path
     */
    public Locatable itemAtPath(String path) {
        return null;
    }

    /**
     * Return true if the path is valid with respect to the current
     * item.
     *
     * @param path
     * @return true if valid
     */
    public boolean validPath(String path) {
        return false;
    }

    // POJO start
    ItemEvent() {
    }

    void setItem(T item) {
        this.item = item;
    }

    void setWidth(DvDuration width) {
        this.width = width;
    }

    void setMathFunction(DvCodedText mathFunction) {
        this.mathFunction = mathFunction;
    }
    // POJO end

    /* fields */
    private T item;
    private DvDuration width;
    private DvCodedText mathFunction;
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
 *  The Original Code is ItemEvent.java
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