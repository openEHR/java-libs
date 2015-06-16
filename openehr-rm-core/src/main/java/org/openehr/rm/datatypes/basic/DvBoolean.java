/*
 * component:   "openEHR Reference Implementation"
 * description: "Class DvBoolean"
 * keywords:    "datatypes"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/BRANCHES/RM-1.0-update/libraries/src/java/org/openehr/rm/datatypes/basic/DvBoolean.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 23:20:08 +0200 (Wed, 12 Oct 2005) $"
 */
package org.openehr.rm.datatypes.basic;

import org.openehr.rm.Attribute;
import org.openehr.rm.FullConstructor;

/**
 * Items which are truly boolean data, such as true/false or yes/no
 * answers. It's simply a wrapper around java.lang.Boolean.
 *
 * @author Rong Chen
 * @version 1.0
 */
public final class DvBoolean extends DataValue {

    /**
     *
     */
    private static final long serialVersionUID = -5827013068177253709L;

    /**
     * Constructs a DvBoolean from a boolean value
     *
     * @param value
     */
    @FullConstructor public DvBoolean(@Attribute (name = "value",
            required = true) boolean value) {
        this.value = value;
    }

    /**
     * Constructs a DvBoolean from a string value
     *
     * @param value
     */
    public DvBoolean(String value) {
        this(Boolean.TRUE.toString().equals(value));
    }

    /**
     * Returns the value of this Boolean object as a boolean primitive
     *
     * @return boolean value
     */
    public boolean getValue() {
        return value;
    }



    public DvBoolean parse(String value) throws IllegalArgumentException {
        return valueOf(value);
    }

    /**
     * Returns an <code>Boolean</code> object holding the value of
     * the specified String. String not equals to "true" will get
     * false returned.
     *
     * @param value case insensitive
     * @return instance of Boolean
     */
    public static DvBoolean valueOf(String value) {
        return Boolean.TRUE.toString().equals(value) ?
                TRUE : FALSE;
    }

    /**
     * Return an instance of Boolean holding the value of
     * primitive boolean value
     *
     * @param value
     * @return instance of Boolean
     */
    public static DvBoolean valueOf(boolean value) {
        return value ? TRUE : FALSE;
    }

    /**
     * Two Boolean objects equal if both has same primitive boolean
     * value
     *
     * @param o
     * @return true if equals
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!( o instanceof DvBoolean )) {
            return false;
        }

        final DvBoolean aBoolean = (DvBoolean) o;

        if (value != aBoolean.value) {
            return false;
        }

        return true;
    }

    /**
     * Return a hash code of this object
     *
     * @return hash code
     */
    @Override
    public int hashCode() {
        return ( value ? 1 : 0 );
    }

    public String toString() {
        return value ? Boolean.TRUE.toString() : Boolean.FALSE.toString();
    }

    // POJO start
    private DvBoolean() {
    }

    public void setValue(boolean value) {
        this.value = value;
    }
    // POJO end

    /* fields */
    private boolean value;

    /**
     * True value
     */
    public static final DvBoolean TRUE = new DvBoolean(true);

    /**
     * False value
     */
    public static final DvBoolean FALSE = new DvBoolean(false);

    @Override
    public String getReferenceModelName() {
        return ReferenceModelName.DV_BOOLEAN.getName();
    }

    @Override
    public String serialise() {
        return getReferenceModelName() + "," + toString();
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
 *  The Original Code is DvBoolean.java
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