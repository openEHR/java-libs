/*
 * component:   "openEHR Reference Implementation"
 * description: "Class CDate"
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
import org.openehr.rm.datatypes.quantity.datetime.DvDate;
import org.openehr.rm.support.basic.Interval;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Constraint on instances of Date. Immutable.
 *
 * @author Rong Chen
 * @version 1.0
 */
public final class CDate extends CPrimitive {

    /**
     * Constructs a DateConstraint with an assumed value
     *
     * @param pattern
     * @param interval Interval<DvDate>
     * @param list     List<DvDate>
     * @param assumedValue
     * @throws IllegalArgumentException if both pattern and interval null
     *                                  or not null
     */
    public CDate(String pattern, Interval<DvDate> interval, List<DvDate> list,
    		DvDate assumedValue) {
        if (interval == null && pattern == null && list == null) {
            throw new IllegalArgumentException(
                    "pattern, interval and list can't be all null");
        }
        this.pattern = pattern;
        this.interval = interval;
        this.list = ( list == null ? null : new ArrayList<DvDate>(list) );
        this.assumedValue = assumedValue;
    }
    
    /**
     * Constructs a DateConstraint no assumed value
     *
     * @param pattern
     * @param interval Interval<DvDate>
     * @param list     List<DvDate>
     * @throws IllegalArgumentException if both pattern and interval null
     *                                  or not null
     */
    public CDate(String pattern, Interval<DvDate> interval, List<DvDate> list) {
    	this(pattern, interval, list, null);
    }

    /**
     * Return the primitive type this constraint is applied on
     *
     * @return name of the type
     */
    public String getType() {
        return "DvDate";
    }

    /**
     * True if value is valid with respect to constraint
     *
     * @param value
     * @return true if valid
     */
    public boolean validValue(Object value) {
        // todo: validate by pattern ?
        if (pattern != null && value instanceof String) {
            String str = (String) value;
            String pat = FULL_PATTERN;
            if (pattern.endsWith("XX")) {
                pat = SHORT_PATTERN;
            }
            try {
                new SimpleDateFormat(pat).parse(str);
                return true;
            } catch (ParseException pe) {
                return false;
            }
        } else {
            final DvDate date = (DvDate) value;
            return ( interval != null && interval.has(date) )
                    || ( list != null && list.contains(date) );
        }
    }

    /**
     * Create an DvDate from a string value
     *
     * @param value
     * @return a DvDate
     */
    public DvDate createObject(String value)
            throws DVObjectCreationException {
        DvDate date = null;
        try {
            date = new DvDate(value);
        } catch (IllegalArgumentException e) {
            throw DVObjectCreationException.BAD_FORMAT;
        }
        if (interval != null && !interval.has(date)) {
            throw DVObjectCreationException.BAD_VALUE;
        }
        if (list != null && !list.contains(date)) {
            throw DVObjectCreationException.BAD_VALUE;
        }
        // todo: pattern?
        return date;
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
     * @return DvDate or null if not assigned
     */
    public DvDate assignedValue() {
        if(list == null || list.size() != 1) {
            return null;
        }
        return list.get(0);
    }

    /**
     * Syntactic pattern defining constraint on dates.
     *
     * @return pattern
     */
    public String getPattern() {
        return pattern;
    }

    /**
     * Interval of Date specifying constraint
     *
     * @return Interval<DvDate> or null
     */
    public Interval<DvDate> getInterval() {
        return interval;
    }

    /**
     * List of specified values
     *
     * @return unmodifiable List<DvDate> or null
     */
    public List<DvDate> getList() {
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

    /* static fields */
    public final String FULL_PATTERN = "yyyy-MM-dd";
    public final String SHORT_PATTERN = "yyyy-MM";

    /* fields */
    private final String pattern;
    private final Interval<DvDate> interval; // Interval<DvDate>
    private final List<DvDate> list; // List<DvDate>
    private final DvDate assumedValue;
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
 *f
 *  The Original Code is CDate.java
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