/*
 * component:   "openEHR Reference Implementation"
 * description: "Class CInteger"
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

import org.openehr.rm.support.basic.Interval;
import org.openehr.am.archetype.constraintmodel.DVObjectCreationException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Constraint on integer values. Immutable.
 *
 * @author Rong Chen
 * @version 1.0
 */
public final class CInteger extends CPrimitive {

    /**
     * Constrcts an IntegerConstraint
     *
     * @param interval
     * @param list
     * @throws IllegalArgumentException if both null or both non-null
     */
    public CInteger(Interval<Integer> interval, List<Integer> list) {
        if (( interval != null && list != null )
                || ( interval == null && list == null )) {
            throw new IllegalArgumentException("both null or both not null");
        }
        this.interval = interval;
        this.list = ( list == null ? null : new ArrayList<Integer>(list) );
    }

    /**
     * Return the primitive type this constraint is applied on
     *
     * @return name of the type
     */
    public String getType() {
        return "Integer";
    }

    /**
     * True if value is valid with respect to constraint
     *
     * @param value
     * @return true if valid
     */
    public boolean validValue(Object value) {
        Integer integer = (Integer) value;
        return ( interval != null && interval.has(integer)
                || list != null && list.contains(integer) );
    }

    /**
     * Create an datavalue from a string value
     *
     * @param value
     * @return a DataValue
     * @throws DVObjectCreationException if value in bad format or invalid
     */
    public Integer createObject(String value) throws DVObjectCreationException {
        try {
            Integer i = Integer.valueOf(value);
            if (interval != null && !interval.has(i)) {
                throw DVObjectCreationException.BAD_VALUE;
            }
            if (list != null && !list.contains(i)) {
                throw DVObjectCreationException.BAD_VALUE;
            }
            return i;
        } catch (NumberFormatException nfe) {
            throw DVObjectCreationException.BAD_FORMAT;
        } catch (NullPointerException npe) {
            throw DVObjectCreationException.BAD_FORMAT;
        }
    }

    /**
     * Return true if the constraint has limit the possible value to
     * be only one, which means the value has been assigned by the archetype
     * author at design time
     *
     * @return true if has
     */
    public boolean hasAssignedValue() {
        return list != null && list.size() == 1;
    }

    /**
     * Return assigned value as data value instance
     *
     * @return datavalue or null if not assigned
     */
    public Object assignedValue() {
        if (!hasAssignedValue()) {
            return null;
        }
        return list.get(0);
    }

    /**
     * Interval of Integers specifying constraint
     *
     * @return Interval<Integer>
     */
    public Interval<Integer> getInterval() {
        return interval;
    }

    /**
     * List of Integers specifying constraint
     *
     * @return unmodifiable List<Integer> or null if not used
     */
    public List<Integer> getList() {
        return list == null ? null : Collections.unmodifiableList(list);
    }

    /* fields */
    private final Interval<Integer> interval;
    private final List<Integer> list;

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
 *  The Original Code is CInteger.java
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