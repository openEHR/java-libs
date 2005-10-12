/*
 * component:   "openEHR Reference Implementation"
 * description: "Class CPrimitive"
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

/**
 * Super class of all primitive type constraint
 *
 * @author Rong Chen
 * @version 1.0
 */
public abstract class CPrimitive {

    /**
     * Return the primitive type this constraint is applied on
     *
     * @return name of the type
     */
    public abstract String getType();

    /**
     * True if value is valid with respect to constraint
     *
     * @param value
     * @return true if valid
     */
    public abstract boolean validValue(Object value);

    /**
     * Create an datavalue from a string value
     *
     * @param value
     * @return a DataValue
     */
    public abstract Object createObject(String value)
            throws DVObjectCreationException;

    /**
     * Return true if the constraint has limit the possible value to
     * be only one, which means the value has been assigned by the archetype
     * author at design time
     *
     * @return true if has
     */
    public abstract boolean hasAssignedValue();

    /**
     * Return assigned value as data value instance
     *
     * @return datavalue or null if not assigned
     */
    public abstract Object assignedValue();
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
 *  The Original Code is CPrimitive.java
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