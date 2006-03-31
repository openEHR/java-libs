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
     * Create an datavalue from a string value
     *
     * @param value
     * @return a DataValue
     * @throws DVObjectCreationException if any error occurs during object
     *                                   creation
     */
    public Double createObject(String value) throws DVObjectCreationException {
        try {
            Double d = Double.valueOf(value);
            if (interval != null && !interval.has(d)) {
                throw DVObjectCreationException.BAD_VALUE;
            }
            if (list != null && !list.contains(d)) {
                throw DVObjectCreationException.BAD_VALUE;
            }
            return d;
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