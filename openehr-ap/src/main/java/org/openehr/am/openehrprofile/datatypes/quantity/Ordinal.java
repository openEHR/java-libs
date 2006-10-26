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

import org.openehr.rm.datatypes.text.CodePhrase;

/**
 * Ordinal item included in the list of ordinal constraint. Immutable.
 *
 * @author Rong Chen
 * @version 1.0
 */
public final class Ordinal {

    /**
     * Constructs an ordinal
     *
     * @param value no less than 0
     * @param symbol not null
     * @throws IllegalArgumentException if symbol null
     */
    public Ordinal(int value, CodePhrase symbol) {
    	if(value < 0) {
    		throw new IllegalArgumentException("value less than 0");
    	}
        if(symbol == null) {
            throw new IllegalArgumentException("symbo null");
        }
        this.value = value;
        this.symbol = symbol;
    }

    /**
     * The integer value of this ordinal
     *
     * @return no less than 0
     */
    public int getValue() {
        return value;
    }

    /**
     * The symbol of this ordinal
     *
     * @return symbol
     */
    public CodePhrase getSymbol() {
    	return symbol;
    }

    /**
     * Return string presentation of this ordinal
     *
     * @return string form
     */
    public String toString() {
        return "[" + symbol.getTerminologyID() + "] " + value 
        + "|" + symbol.getCodeString();    
    }

    /* fields */
    private final int value;
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