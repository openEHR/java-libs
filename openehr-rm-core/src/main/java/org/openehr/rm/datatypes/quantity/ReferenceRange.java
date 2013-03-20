/*
 * component:   "openEHR Reference Implementation"
 * description: "Class ReferenceRange"
 * keywords:    "datatypes"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/BRANCHES/RM-1.0-update/libraries/src/java/org/openehr/rm/datatypes/quantity/ReferenceRange.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 23:20:08 +0200 (Wed, 12 Oct 2005) $"
 */
package org.openehr.rm.datatypes.quantity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.openehr.rm.Attribute;
import org.openehr.rm.FullConstructor;
import org.openehr.rm.datatypes.basic.DataValue;
import org.openehr.rm.datatypes.text.DvText;

/**
 * This class defines a named range to be associated with any Ordered
 * datum. Each such range is particular to the patient and context,
 * like sex, age, and any other factor which affects ranges.
 *
 * @author Rong Chen
 * @version 1.0
 */
public final class ReferenceRange <T extends DvOrdered> extends DataValue {

    /**
     * Constructs a ReferenceRange by meaning and range
     *
     * @param meaning
     * @param range
     * @throws IllegalArgumentException if meaning or range null,
     *                                  or range not simple
     */
    @FullConstructor
            public ReferenceRange(@Attribute (name = "meaning", required = true)
            DvText meaning,
                                  @Attribute (name = "range", required = true)
            DvInterval<T> range) {
        if (meaning == null) {
            throw new IllegalArgumentException("null meaning");
        }
        if (range == null) {
            throw new IllegalArgumentException("null range");
        }
        if (!isSimple(range)) {
            throw new IllegalArgumentException("range not simple");
        }
        this.meaning = meaning;
        this.range = range;
    }

    private static boolean isSimple(DvInterval range) {
        return ( range.isLowerUnbounded() ||
                range.getLower().isSimple() )
                &&
                ( range.isUpperUnbounded() ||
                range.getUpper().isSimple() );
    }

    /**
     * Term whose value indicates the meaning of this range,
     * like "normal", "critical", "therapeutic"
     *
     * @return meaning
     */
    public DvText getMeaning() {
        return meaning;
    }

    /**
     * The data range
     *
     * @return range
     */
    public DvInterval<T> getRange() {
        return range;
    }

    /**
     * Indicates if the value is inside the range
     *
     * @param value
     * @return true if has the value
     */
    public boolean isInRange(DvOrdered<T> value) {
        return range.has(value);
    }   

    /**
     * Two ReferenceRanges equal if both has same value for meaning
     * and range
     *
     * @param o
     * @return true if equals
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!( o instanceof ReferenceRange )) return false;

        final ReferenceRange rg = (ReferenceRange) o;

        return new EqualsBuilder()
                .append(meaning, rg.meaning)
                .append(range, rg.range)
                .isEquals();
    }

    /**
     * Return a hash code of this object
     *
     * @return hash code
     */
    public int hashCode() {
        return new HashCodeBuilder(13, 29)
                .append(meaning)
                .append(range)
                .toHashCode();
    }

    /**
     * String presentation of this range
     *
     * @return string presentation
     */
    public String toString() {
        return meaning + ", " + range;
    }

    /* static field */
    public static final String NORMAL = "normal";

    /* fields */
    private final DvText meaning;
    private final DvInterval<T> range;
	@Override
	public String getReferenceModelName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String serialise() {
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
 *  The Original Code is ReferenceRange.java
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