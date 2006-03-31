/*
 * component:   "openEHR Reference Implementation"
 * description: "Class CTime"
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
import org.openehr.rm.datatypes.quantity.datetime.DvTime;
import org.openehr.rm.support.basic.Interval;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Constraint on instances of Time. Immutalbe.
 *
 * @author Rong Chen
 * @version 1.0
 */
public final class CTime extends CPrimitive {

    /**
     * Construct a TimeConstraint with an assumed value
     *
     * @param pattern
     * @param interval Interval<DvTime>
     * @param list     List<DvTime>
     * @param assumedValue
     * @throws IllegalArgumentException if both pattern and interval
     *                                  both null or both not null
     */
    public CTime(String pattern, Interval<DvTime> interval, List<DvTime> list,
    		DvTime assumedValue) {
        if (interval == null && pattern == null && list == null) {
            throw new IllegalArgumentException(
                    "pattern, interval and list can't be all null");
        }
        this.pattern = pattern;
        this.interval = interval;
        this.list = ( list == null ? null : new ArrayList<DvTime>(list) );
        this.assumedValue = assumedValue;
    }
    
    /**
     * Construct a TimeConstraint without assumed value
     *
     * @param pattern
     * @param interval Interval<DvTime>
     * @param list     List<DvTime>
     * @throws IllegalArgumentException if both pattern and interval
     *                                  both null or both not null
     */
    public CTime(String pattern, Interval<DvTime> interval, List<DvTime> list) {
    	this(pattern, interval, list, null);
    }
    	
    /**
     * Return the primitive type this constraint is applied on
     *
     * @return name of the type
     */
    public String getType() {
        return "DvTime";
    }

    /**
     * True if value is valid with respect to constraint
     *
     * @param value
     * @return true if valid
     */
    public boolean validValue(Object value) {
        // todo: validate by pattern
        final DvTime time = (DvTime) value;
        return ( interval != null && interval.has(time) )
                || ( list != null && list.contains(time) );
    }

    /**
     * Create an DvTime from a string value
     *
     * @param value
     * @return a DvTime
     */
    public DvTime createObject(String value)
            throws DVObjectCreationException {
        DvTime time = null;
        try {
            time = new DvTime(value);
        } catch (IllegalArgumentException e) {
            throw DVObjectCreationException.BAD_FORMAT;
        }
        if (interval != null && !interval.has(time)) {
            throw DVObjectCreationException.BAD_VALUE;
        }
        if (list != null && !list.contains(time)) {
            throw DVObjectCreationException.BAD_VALUE;
        }
        // todo: pattern?
        return time;
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
     * @return DvTime or null if not assigned
     */
    public DvTime assignedValue() {
        if (list == null || list.size() != 1) {
            return null;
        }
        return list.get(0);
    }

    /**
     * Syntactic pattern defining constraint on Times.
     *
     * @return pattern
     */
    public String getPattern() {
        return pattern;
    }

    /**
     * Interval of Times specifying constraint
     *
     * @return Interval<DvTime>
     */
    public Interval<DvTime> getInterval() {
        return interval;
    }

    /**
     * List of specified values
     *
     * @return unmodifiable List<DvTime> or null
     */
    public List<DvTime> getList() {
        return list == null ? null : Collections.unmodifiableList(list);
    }

    @Override
	public boolean hasAssumedValue() {
		return assumedValue != null;
	}

	@Override
	public Object assumedValue() {
		return assumedValue;
	}

    /* fields */
    private final String pattern;
    private final Interval<DvTime> interval;
    private final List<DvTime> list;
    private final DvTime assumedValue;
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
 *  The Original Code is CTime.java
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