/*
 * component:   "openEHR Reference Implementation"
 * description: "Class Cardinality"
 * keywords:    "archetype"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/am/archetype/constraintmodel/Cardinality.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 23:20:08 +0200 (Wed, 12 Oct 2005) $"
 */
package org.openehr.am.archetype.constraintmodel;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.openehr.rm.support.basic.Interval;

/**
 * This class represents the semantic of a container type. Two pre-defined
 * types are List and Set. Instances of this class are immutable.
 *
 * @author Rong Chen
 * @version 1.0
 */
public final class Cardinality {

    /**
     * Creates a cardinality
     *
     * @param ordered
     * @param unique
     * @param interval Interval<Integer> null indicates from 0 to many
     * @throws IllegalArgumentException if lower boundary of interval is < 0
     */
    public Cardinality(boolean ordered, boolean unique,
                       Interval<Integer> interval) {

        if (interval != null && interval.getLower() != null
                && ( (Integer) interval.getLower() ).intValue() < 0) {
            throw new IllegalArgumentException("lower boundary less than 0");
        }
        this.ordered = ordered;
        this.unique = unique;
        this.interval = interval;
    }

    /**
     * True if ordered
     *
     * @return ordered
     */
    public boolean isOrdered() {
        return ordered;
    }

    /**
     * True if unique
     *
     * @return unique
     */
    public boolean isUnique() {
        return unique;
    }
    
    /**
     * Interval of this cardinality
     *
     * @return Interval<Integer> null indicates from 0 to many
     */
    public Interval<Integer> getInterval() {
        return interval;
    }
    
    /**
     * Returns true if the semantics of this cardinality represents 
     * a bag, i.e. unordered, non-unique membership.
     */
    public boolean isBag() {
    	return !ordered && !unique;
    }
    
    /**
     * Returns true if the semantics of this cardinality represents
	 * a list, i.e. ordered, non-unique membership.
     */
    public boolean isList() {
        return ordered && !unique;
    }
   
    /**
     * Returns return if the semantics of this cardinality represents
     * a set, i.e. unordered, unique membership.
     */
    public boolean isSet() {
    	return !ordered && unique;
    }
    
    /**
     * Return ture if two Cardinality has same value
     *
     * @param o
     * @return true if equals
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!( o instanceof Cardinality )) return false;

        final Cardinality cardinality = (Cardinality) o;

        return new EqualsBuilder()
        .append(ordered, cardinality.ordered)
        .append(unique, cardinality.unique)
        .append(interval, cardinality.interval)
        .isEquals();
    }

    /**
     * Return a hash code of this cardinality
     *
     * @return hash code
     */
    public int hashCode() {
        int result;
        result = ( ordered ? 1 : 0 );
        result = 29 * result + ( unique ? 1 : 0 );
        result = 29 * result + ( interval != null ? interval.hashCode() : 0 );
        return result;
    }
    
    public String toString() {
    	return new ToStringBuilder(this).
        append("ordered", ordered).
        append("unique", unique).
        append("interval", interval).
        toString();

    }

    /* fields */
    private final boolean ordered;
    private final boolean unique;
    private final Interval<Integer> interval;

    /**
     * Pre-defined List cardinality, whoes members are ordered and non-unique
     */
    public static final Cardinality LIST = 
    	new Cardinality(true, false, new Interval<Integer>(0, null));

    /**
     * Pre-defined Set cardinality, whoes members are unordered and unique
     */
    public static final Cardinality SET = new Cardinality(false, true, 
    		new Interval<Integer>(0, null));
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
 *  The Original Code is Cardinality.java
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