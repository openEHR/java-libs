/*
 * component:   "openEHR Reference Implementation"
 * description: "Class CDuration"
 * keywords:    "archetype"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004,2006 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/am/archetype/constraintmodel/primitive/CDuration.java $"
 * revision:    "$LastChangedRevision: 23 $"
 * last_change: "$LastChangedDate: 2006-03-31 02:40:54 +0200 (Fri, 31 Mar 2006) $"
 */
package org.openehr.am.archetype.constraintmodel.primitive;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
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
	 * Constructs a DurationConstraint with an assumed value
	 * 
	 * @param value
	 * @param interval
	 *            Interval<DvDuration>
	 * @param assumedValue
	 * @param pattern null if unspecified
	 */
	public CDuration(DvDuration value, Interval<DvDuration> interval,
			DvDuration assumedValue, String pattern, DvDuration defaultValue) {
		if (interval != null && value != null) {
			throw new IllegalArgumentException(
					"both value and interval not null");
		}		
		if (interval == null && value == null && pattern == null) {
			throw new IllegalArgumentException(
					"value, interval and pattern all null ");
		}
		this.value = value;
		this.interval = interval;
		this.assumedValue = assumedValue;
		this.pattern = pattern;
		this.defaultValue = defaultValue;
	}
	
	/**
	 * Constructs a DurationConstraint without assumed value
	 * 
	 * @param value
	 * @param interval
	 *            Interval<DvDuration>
	 */
	public CDuration(DvDuration value, Interval<DvDuration> interval) {
		this(value, interval, null, null);
	}
	
	public CDuration(DvDuration value, Interval<DvDuration> interval,
			DvDuration assumedValue, String pattern) {
		this(value, interval, assumedValue, pattern, null);
	}
	

	/**
	 * Return the primitive type this constraint is applied on
	 * 
	 * @return name of the type
	 */
	public String getType() {
		return "DV_DURATION";
	}

	/**
	 * True if value is valid with respect to constraint
	 * 
	 * @param value
	 * @return true if valid
	 */
	public boolean validValue(Object value) {
		return false; // todo: implement this method
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
	 * Gets the pattern of this duration constraint
	 * 
	 * @return null if unspecified
	 */
	public String getPattern() {
		return pattern;
	}

	/**
	 * Interval of Durations specifying constraint
	 * 
	 * @return Interval<Duration>
	 */
	public Interval<DvDuration> getInterval() {
		return interval;
	}

	@Override
	public boolean hasAssumedValue() {
		return assumedValue != null;
	}

	@Override
	public DvDuration assumedValue() {
		return assumedValue;
	}
	
	@Override
	public boolean hasDefaultValue() {
		return defaultValue != null;
	}

	@Override
	public DvDuration defaultValue() {
		return defaultValue;
	}

	@Override
	public boolean hasAssignedValue() {
		return value != null;
	}

	@Override
	public DvDuration assignedValue() {
		return value;
	}
	
	/**
     * Equals if two CObject has same values
     *
     * @param o
     * @return true if equals
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!( o instanceof CDuration )) return false;

        final CDuration cobj = (CDuration) o;

        return new EqualsBuilder()
                .append(pattern, cobj.pattern)
                .append(interval, cobj.interval)
                .append(value, cobj.value)
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
        return new HashCodeBuilder(5, 37)
                .append(pattern)
                .append(interval)
                .append(value)
                .append(assumedValue)
                .append(defaultValue)
                .toHashCode();
    }
	
	/* fields */
	private final DvDuration value;
	private final Interval<DvDuration> interval;
	private final DvDuration assumedValue;	
	private final String pattern;
	private DvDuration defaultValue;
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
 * The Original Code is CDuration.java
 * 
 * The Initial Developer of the Original Code is Rong Chen. Portions created by
 * the Initial Developer are Copyright (C) 2003-2006 the Initial Developer. All
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