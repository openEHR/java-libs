/*
 * component:   "openEHR Reference Implementation"
 * description: "Class Element"
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
import org.openehr.rm.datatypes.basic.DataValue;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.support.terminology.TerminologyService;

import java.util.Set;

/**
 * The leaf variant of ITEM, to which a DATA_VALUE instance is
 * attached.
 *
 * @author Rong Chen
 * @version 1.0
 */
public final class Element extends Item {

    /**
     * Construct an Element
     *
     * @param uid
     * @param archetypeNodeId
     * @param name
     * @param archetypeDetails
     * @param feederAudit
     * @param links
     * @param value
     * @param nullFlavor       required if value is null
     * @throws IllegalArgumentException if both value and nullFlavor
     *                                  are null
     */
    @FullConstructor
            public Element(@Attribute(name = "uid") ObjectID uid,
                           @Attribute(name = "archetypeNodeId", required=true) String archetypeNodeId,
                           @Attribute(name = "name", required=true) DvText name,
                           @Attribute(name = "archetypeDetails") Archetyped archetypeDetails,
                           @Attribute(name = "feederAudit") FeederAudit feederAudit,
                           @Attribute(name = "links") Set<Link> links,
                           @Attribute(name = "value",  required=true) DataValue value,
                           @Attribute(name = "nullFlavor") DvCodedText nullFlavor,
                           @Attribute(name = "terminologyService",  system=true) TerminologyService terminologyService) {
        super(uid, archetypeNodeId, name, archetypeDetails, feederAudit,
                links);
        if (( value == null && nullFlavor == null )
                || ( value != null && nullFlavor != null )) {
            throw new IllegalArgumentException(
                    "null or unnecessary nullFlavor");
        }
        if (value == null) {
            if (terminologyService == null) {
                throw new IllegalArgumentException("null terminologyService");
            }
            if (!terminologyService.terminology(TerminologyService.OPENEHR)
                    .hasCodeForGroupName(nullFlavor.getDefiningCode(),
                            "null flavour", "en")) {
                throw new IllegalArgumentException(
                        "unknown nullFlavor: " + nullFlavor);
            }
        }
        this.value = value;
        this.nullFlavor = nullFlavor;
    }

    /**
     * Constructs an Element node by archetypeNodeId, name and non-null value
     *
     * @param archetypeNodeId
     * @param name
     * @param value
     * @throws IllegalArgumentException if name or value null
     */
    public Element(String archetypeNodeId, DvText name, DataValue value) {
        this(null, archetypeNodeId, name, null, null, null, value, null, null);
    }

    /**
     * data value of this leaf
     *
     * @return value of this leaf
     */
    public DataValue getValue() {
        return value;
    }

    /**
     * flavour of null value, like indeterminate, not asked etc
     *
     * @return null flavor
     */
    public DvCodedText getNullFlavor() {
        return nullFlavor;
    }


    /**
     * True if value logically not known, if indeterminate,
     * not asked etc.
     *
     * @return true if value null
     */
    public boolean isNull() {
        return value == null;
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

    /**
     * Return true if value equals
     *
     * @param o
     * @return true if equals
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!( o instanceof Element )) return false;

        final Element element = (Element) o;

        return value.equals(element.value);
    }

    /**
     * Return hash code
     *
     * @return hash code
     */
    public int hashCode() {
        return value.hashCode();
    }

    /* fields */
    private DataValue value;
    private DvCodedText nullFlavor;

    // POJO start
    Element() {
    }

    void setValue(DataValue value) {
        this.value = value;
    }

    void setNullFlavor(DvCodedText nullFlavor) {
        this.nullFlavor = nullFlavor;
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
 *  The Original Code is Element.java
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