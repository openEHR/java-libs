/*
 * component:   "openEHR Reference Implementation"
 * description: "Class CBoolean"
 * keywords:    "archetype"
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
package org.openehr.am.archetype.constraintmodel.primitive;

import org.openehr.am.archetype.constraintmodel.DVObjectCreationException;
import org.openehr.rm.datatypes.basic.DvBoolean;

/**
 * Constraint on instances of Boolean. Immutable.
 *
 * @author Rong Chen
 * @version 1.0
 */
public final class CBoolean extends CPrimitive {

    /**
     * Constructs a BooleanConstraint
     *
     * @param trueValid
     * @param falseValid
     * @throws IllegalArgumentException either trueValid or falseValid true
     */
    public CBoolean(boolean trueValid, boolean falseValid) {
        if (!trueValid && !falseValid) {
            throw new IllegalArgumentException(
                    "either of trueValid or falseValid, or both need to be true");
        }
/*
        if( (defaultValue && !trueValid)
                || (!defaultValue && !falseValid)) {
            throw new IllegalArgumentException("defaultValue must be valid");
        }
*/
        this.trueValid = trueValid;
        this.falseValid = falseValid;
    }

    /**
     * Return the primitive type this constraint is applied on
     *
     * @return name of the type
     */
    public String getType() {
        return "DvBoolean";
    }

    /**
     * True if value is valid with respect to constraint
     *
     * @param value
     * @return true if valid
     */
    public boolean validValue(Object value) {
        boolean b = ( (Boolean) value ).booleanValue();
        return ( ( b && isTrueValid() ) || !b && isFalseValid() );
    }

    /**
     * Create an DvBoolean from a string value
     *
     * @param value
     * @return a DvBoolean
     */
    // todo: change the return type to native boolean ?
    public DvBoolean createObject(String value)
            throws DVObjectCreationException {

        DvBoolean dvBoolean = DvBoolean.valueOf(value);
        if (( dvBoolean.value() && !trueValid )
                || ( !dvBoolean.value() && !falseValid )) {
            throw DVObjectCreationException.BAD_VALUE;
        }
        return dvBoolean;
    }

    /**
     * Return true if the constraint has limit the possible value to
     * be only one, which means the value has been assigned by the archetype
     * author at design time
     *
     * @return true if has
     */
    public boolean hasAssignedValue() {
        return !(trueValid && falseValid);
    }

    /**
     * Return assigned value as data value instance
     *
     * @return DvBoolean or null if not assigned
     */
    public DvBoolean assignedValue() {
        if ((trueValid && falseValid)) {
            return null;
        }
        if(trueValid) {
            return DvBoolean.TRUE;
        } else {
            return DvBoolean.FALSE;
        }
    }

    /**
     * True if the value True is allowed
     *
     * @return if true valid
     */
    public boolean isTrueValid() {
        return trueValid;
    }

    /**
     * True if the value False is allowed
     *
     * @return if false valid
     */
    public boolean isFalseValid() {
        return falseValid;
    }

    /* fields */
    private final boolean trueValid;
    private final boolean falseValid;
    //private final Boolean assumedValue;
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
 *  The Original Code is CBoolean.java
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