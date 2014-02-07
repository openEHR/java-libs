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
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/am/archetype/constraintmodel/primitive/CTime.java $"
 * revision:    "$LastChangedRevision: 23 $"
 * last_change: "$LastChangedDate: 2006-03-31 02:40:54 +0200 (Fri, 31 Mar 2006) $"
 */
package org.openehr.am.archetype.constraintmodel.primitive;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.openehr.rm.datatypes.quantity.datetime.DvTime;
import org.openehr.rm.support.basic.Interval;

import java.util.*;

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
    		DvTime assumedValue, DvTime defaultValue) {
        if (interval == null && pattern == null && list == null) {
            throw new IllegalArgumentException(
                    "pattern, interval and list can't be all null");
        }
        this.pattern = pattern;
        this.interval = interval;
        this.list = ( list == null ? null : new ArrayList<DvTime>(list) );
        this.assumedValue = assumedValue;
        this.defaultValue = defaultValue;
    }
    
    public CTime(String pattern, Interval<DvTime> interval, List<DvTime> list,
    		DvTime assumedValue) {
    	this(pattern, interval, list, assumedValue, null);
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
     * Convenient constructor to create CTime with a pattern
     * 
     * @param pattern not null
     * @throws IllegalArgumentException if pattern null
     */
    public CTime(String pattern) {
    	this(pattern, null, null);
    }
    	
    /**
     * Return the primitive type this constraint is applied on
     *
     * @return name of the type
     */
    public String getType() {
        return "DV_TIME";
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
	
	@Override
	public boolean hasDefaultValue() {
		return defaultValue != null;
	}

	@Override
	public Object defaultValue() {
		return defaultValue;
	}
	
	/**
     * Equals if two CObject has same values
     *
     * @param o
     * @return true if equals
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!( o instanceof CTime )) return false;

        final CTime cobj = (CTime) o;

        return new EqualsBuilder()
                .append(pattern, cobj.pattern)
                .append(interval, cobj.interval)
                .append(list, cobj.list)
                .append(assumedValue, cobj.assumedValue)
                .append(defaultValue, cobj.defaultValue)
                .isEquals();
    }

    /**
     * Return a hash code of this object
     *
     * @return hash code
     */
    public int hashCode() {
        return new HashCodeBuilder(11, 31)
                .append(pattern)
                .append(interval)
                .append(list)
                .append(assumedValue)
                .append(defaultValue)
                .toHashCode();
    }
    
    /* fields */
    private final String pattern;
    private final Interval<DvTime> interval;
    private final List<DvTime> list;
    private final DvTime assumedValue;
    private DvTime defaultValue;
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