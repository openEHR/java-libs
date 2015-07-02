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
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/am/archetype/constraintmodel/primitive/CInteger.java $"
 * revision:    "$LastChangedRevision: 23 $"
 * last_change: "$LastChangedDate: 2006-03-31 02:40:54 +0200 (Fri, 31 Mar 2006) $"
 */
package org.openehr.am.archetype.constraintmodel.primitive;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.openehr.rm.support.basic.Interval;

/**
 * Constraint on integer values. Immutable.
 * 
 * @author Rong Chen
 * @version 1.0
 */
public final class CInteger extends CPrimitive {

	/**
	 * Constructs an IntegerConstraint with an assumed value
	 * 
	 * @param interval
	 * @param list
	 * @param assumedValue
	 * @throws IllegalArgumentException
	 *             if both null or both non-null
	 */
	public CInteger(Interval<Integer> interval, List<Integer> list,
			Integer assumedValue, Integer defaultValue) {
		if ((interval != null && list != null)
				|| (interval == null && list == null)) {
			throw new IllegalArgumentException("both null or both not null");
		}
		this.interval = interval;
		this.list = (list == null ? null : new ArrayList<Integer>(list));
		this.assumedValue = assumedValue;
		this.defaultValue = defaultValue;
	}
	
	public CInteger(Interval<Integer> interval, List<Integer> list,
			Integer assumedValue) {
		this(interval, list, assumedValue, null);
	}

	/**
	 * Constructs an IntegerConstraint
	 * 
	 * @param interval
	 * @param list
	 * @throws IllegalArgumentException
	 *             if both null or both non-null
	 */
	public CInteger(Interval<Integer> interval, List<Integer> list) {
		this(interval, list, null);
	}

	/**
	 * Return the primitive type this constraint is applied on
	 * 
	 * @return name of the type
	 */
	@Override
    public String getType() {
		return "Integer";
	}

	/**
	 * True if value is valid with respect to constraint
	 * 
	 * @param value
	 * @return true if valid
	 */
	@Override
    public boolean validValue(Object value) {
		Integer integer = null;
		if ((value instanceof Integer))
			integer = (Integer) value;
		else {
			String strValue = value.toString();
			if (!strValue.isEmpty())
				integer = Integer.valueOf(strValue);
		}
		return ((this.interval != null) && (this.interval.has(integer)))
				|| ((this.list != null) && (this.list.contains(integer)));
	}

	/**
	 * Return true if the constraint has limit the possible value to be only
	 * one, which means the value has been assigned by the archetype author at
	 * design time
	 * 
	 * @return true if has
	 */
	@Override
    public boolean hasAssignedValue() {
		return list != null && list.size() == 1;
	}

	/**
	 * Return assigned value as data value instance
	 * 
	 * @return datavalue or null if not assigned
	 */
	@Override
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
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!( o instanceof CInteger )) {
            return false;
        }

        final CInteger cobj = (CInteger) o;

        return new EqualsBuilder()
                .append(list, cobj.list)
                .append(interval, cobj.interval)
                .append(assumedValue, cobj.assumedValue)
                .append(defaultValue, cobj.defaultValue)
                .isEquals();
    }

    /**
     * Return a hash code of this object
     *
     * @return hash code
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(5, 43)
                .append(list)
                .append(interval)
                .append(assumedValue)
                .append(defaultValue)
                .toHashCode();
    }
	
	/* fields */
	private final Interval<Integer> interval;
	private final List<Integer> list;
	private final Integer assumedValue;
	private Integer defaultValue;
}

/*
 * ***** BEGIN LICENSE BLOCK ***** Version: MPL 1.1/GPL 2.0/LGPL 2.1
 * 
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the 'License'); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at http://www.mozilla.org/MPL/
 * 
 * Software distributed under the License is distributed on an 'AS IS' basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 * 
 * The Original Code is CInteger.java
 * 
 * The Initial Developer of the Original Code is Rong Chen. Portions created by
 * the Initial Developer are Copyright (C) 2003-2004 the Initial Developer. All
 * Rights Reserved.
 * 
 * Contributor(s):
 * 
 * Software distributed under the License is distributed on an 'AS IS' basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 * 
 * ***** END LICENSE BLOCK *****
 */