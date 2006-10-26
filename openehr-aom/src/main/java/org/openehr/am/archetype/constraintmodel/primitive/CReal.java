/*
 * component:   "openEHR Reference Implementation"
 * description: "Class CReal"
 * keywords:    "archetype"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/am/archetype/constraintmodel/primitive/CReal.java $"
 * revision:    "$LastChangedRevision: 23 $"
 * last_change: "$LastChangedDate: 2006-03-31 02:40:54 +0200 (Fri, 31 Mar 2006) $"
 */
package org.openehr.am.archetype.constraintmodel.primitive;

import org.openehr.rm.support.basic.Interval;

import java.util.*;

/**
 * Constraint on instances of Double number. Immutable.
 *
 * @author Rong Chen
 * @version 1.0
 */
public final class CReal extends CPrimitive {

    /**
     * Constructs a RealConstraint with an assumed value
     *
     * @param interval Interval<Double>
     * @param list     List<Double>
     * @param assumedValue
     * @throws IllegalArgumentException if boht null or bot not null
     */
    public CReal(Interval<Double> interval, List<Double> list,
    		Double assumedValue) {
        if (( interval != null && list != null )
                || ( interval == null && list == null )) {
            throw new IllegalArgumentException("both null or both not null");
        }
        this.interval = interval;
        this.list = ( list == null ? null : new ArrayList<Double>(list) );
        this.assumedValue = assumedValue;
    }

    /**
     * Constructs a RealConstraint without assumed value
     *
     * @param interval Interval<Double>
     * @param list     List<Double>
     */
    public CReal(Interval<Double> interval, List<Double> list) {
    	this(interval, list, null);
    }
    /**
     * Return the primitive type this constraint is applied on
     *
     * @return name of the type
     */
    public String getType() {
        return "Double";
    }

    /**
     * List of Reals specifying constraint
     *
     * @return unmodifiable List<Double> or null
     */
    public List<Double> getList() {
        return list == null ? null : Collections.unmodifiableList(list);
    }

    /**
     * True if value is valid with respect to constraint
     *
     * @param value
     * @return true if valid
     */
    public boolean validValue(Object value) {
        Double d = (Double) value;
        return ( interval != null && interval.has(d)
                || list != null && list.contains(d) );
    }    

    /**
     * Interval of Dates specifying constraint
     *
     * @return Interval of Double
     */
    public Interval<Double> getInterval() {
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

    /* fields */
    private final List<Double> list;
    private final Interval<Double> interval;
    private final Double assumedValue;
	@Override
	public boolean hasAssignedValue() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object assignedValue() {
		// TODO Auto-generated method stub
		return null;
	}
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
 *  The Original Code is CReal.java
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