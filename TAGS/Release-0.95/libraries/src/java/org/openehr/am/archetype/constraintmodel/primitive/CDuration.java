/*
 * component:   "openEHR Reference Implementation"
 * description: "Class CDuration"
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
import org.openehr.rm.datatypes.basic.DataValue;
import org.openehr.rm.datatypes.quantity.datetime.DvDuration;
import org.openehr.rm.support.basic.Interval;

/**
 * Constraint on instances of Duration. Immutable.
 *
 * @author Rong Chen
 * @version 1.0
 */
public final class CDuration extends CPrimitive {

    /**
     * Constructs a DurationConstraint
     *
     * @param value
     * @param interval Interval<DvDuration>
     */
    public CDuration(DvDuration value, Interval<DvDuration> interval) {
        if (( interval != null && value != null )
                || ( interval == null && value == null )) {
            throw new IllegalArgumentException(
                    "both value and interval null or not null");
        }
        this.value = value;
        this.interval = interval;
    }

    /**
     * Return the primitive type this constraint is applied on
     *
     * @return name of the type
     */
    public String getType() {
        return "DvDuration";
    }

    /**
     * True if value is valid with respect to constraint
     *
     * @param value
     * @return true if valid
     */
    public boolean validValue(Object value) {
        return false;  // todo: implement this method
    }

    /**
     * Create an DvDuration from a string value
     *
     * @param value
     * @return a DvDuration
     */
    public DvDuration createObject(String value)
            throws DVObjectCreationException {
        DvDuration duration = null;
        try {
            duration = DvDuration.getInstance(value);
        } catch (IllegalArgumentException e) {
            throw DVObjectCreationException.BAD_FORMAT;
        }
        if (interval != null && !interval.has(duration)) {
            throw DVObjectCreationException.BAD_VALUE;
        }
        return duration;
    }

    /**
     * Return true if the constraint has limit the possible value to
     * be only one, which means the value has been assigned by the archetype
     * author at design time
     *
     * @return true if has
     */
    public boolean hasAssignedValue() {
        return value != null;
    }

    /**
     * Return assigned value as data value instance
     *
     * @return datavalue or null if not assigned
     */
    public DataValue assignedValue() {
        return value;
    }


    /**
     * Syntactic value defining constraint on Times.
     *
     * @return value
     */
    public DvDuration getValue() {
        return value;
    }

    /**
     * Interval of Durations specifying constraint
     *
     * @return Interval<Duration>
     */
    public Interval<DvDuration> getInterval() {
        return interval;
    }

    /* fields */
    private final DvDuration value;
    private final Interval<DvDuration> interval;
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
 *  The Original Code is CDuration.java
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