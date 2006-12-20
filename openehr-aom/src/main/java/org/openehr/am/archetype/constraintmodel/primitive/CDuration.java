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
	 */
	public CDuration(DvDuration value, Interval<DvDuration> interval,
			DvDuration assumedValue) {
		if ((interval != null && value != null)
				|| (interval == null && value == null)) {
			throw new IllegalArgumentException(
					"both value and interval null or not null");
		}
		this.value = value;
		this.interval = interval;
		this.assumedValue = assumedValue;
	}

	/**
	 * Constructs a DurationConstraint without assumed value
	 * 
	 * @param value
	 * @param interval
	 *            Interval<DvDuration>
	 */
	public CDuration(DvDuration value, Interval<DvDuration> interval) {
		this(value, interval, null);
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
	public Object assumedValue() {
		return assumedValue;
	}

	@Override
	public boolean hasAssignedValue() {
		return value != null;
	}

	@Override
	public Object assignedValue() {
		return value;
	}
	
	/* fields */
	private final DvDuration value;
	private final Interval<DvDuration> interval;
	private final DvDuration assumedValue;	
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