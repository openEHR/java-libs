/*
 * component:   "openEHR Reference Implementation"
 * description: "Class Ordinal"
 * keywords:    "openehr archetype profile"
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

package org.openehr.am.openehrprofile.datatypes.quantity;


import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.openehr.rm.datatypes.text.CodePhrase;

/** Scale item included in the list of a scale constraint. Immutable.
 * @author Sebastian Garde
 * @version 1.0 */
public final class Scale implements Comparable, Serializable {

    /** Constructs an scale
     * @param value
     * @param symbol not null
     * @throws IllegalArgumentException if symbol null */
    public Scale(double value, CodePhrase symbol) {
        if(symbol == null) {
            throw new IllegalArgumentException("symbol null");
        }
        this.value = value;
        this.display = "" + value;
        this.symbol = symbol;
    }

    public Scale(String display, CodePhrase symbol) {
        if (symbol == null) {
            throw new IllegalArgumentException("symbol null");
        }
        this.value = Double.parseDouble(display);
        this.display = display;
        this.symbol = symbol;
    }

    /** The integer value of this scale
     * @return no less than 0 */
    public double getValue() {
        return value;
    }

    public String getDisplay() {
        return display;
    }

    /** The symbol of this scale
     * @return symbol */
    public CodePhrase getSymbol() {
        return symbol;
    }

    /**
     * Return string presentation of this ordinal
     *
     * @return string form
     */
    @Override
    public String toString() {
        return "[" + symbol.getTerminologyId() + "] " + value
                + "|" + symbol.getCodeString();
    }

    /**
     * Returns true if fields are the same
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Scale)) {
            return false;
        }

        final Scale scale = (Scale) o;

        return new EqualsBuilder()
                .append(value, scale.value).append(symbol, scale.symbol)
                .isEquals();
    }


    /**
     * @param o the Object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     *         is less than, equal to, or greater than the specified object.
     * @throws ClassCastException if the specified object's type prevents it
     *                            from being compared to this Object.
     */
    public int compareTo(Object o) {
        final Scale scale = (Scale) o;
        if (value > scale.value) {
            return 1;
        }
        if (value < scale.value) {
            return -1;
        }
        return 0;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(7, 17)
                .append(value)
                .append(symbol)
                .toHashCode();
    }

    /* fields */
    private final double value;
    private final String display; // The value as it is to be displayed (i.e. as it wasstated originally, e.g. 2 instead of 2.0
    private final CodePhrase symbol;
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
 *  The Original Code is Ordinal.java
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