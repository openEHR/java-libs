/*
 * component:   "openEHR Reference Implementation"
 * description: "Class ObjectID"
 * keywords:    "common"
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
package org.openehr.rm.support.identification;

import org.apache.commons.lang.StringUtils;
import org.openehr.rm.RMObject;

/**
 * Ancestor class of identifiers of informational objects. Ids may be
 * completely meaningless, in which case their only job is to refer
 * to something, or may carry some information to do with the
 * identified object.
 * 
 * @author Rong Chen
 * @version 1.0
 */
public abstract class ObjectID extends RMObject {

    /**
     * Create objectID by string value
     *
     * @param value
     * @throws IllegalArgumentException if value is empty
     */
    public ObjectID(String value) {
        if (StringUtils.isEmpty(value)) {
            throw new IllegalArgumentException("empty value");
        }
        this.value = value;
    }

    /**
     * The value of this objectID
     *
     * @return value
     */
    public String getValue() {
        return value;
    }

    /**
     * Return version of information pointed to by this ID,
     * if versioning is supported.
     *
     * @return versionID null if not present
     */
    public abstract String versionID();

    /**
     * Return true if both have same value and
     *
     * @param o The object to which this object is to be compared
     * @return true if equals
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!( o instanceof ObjectID )) return false;

        final ObjectID casted = (ObjectID) o;

        return value.equals(casted.value);
    }

    /**
     * Return a hash code value of this objectID
     *
     * @return hash code
     */
    public int hashCode() {
        return value.hashCode();
    }

    /**
     * Return value as string presentation of this objectID
     *
     * @return string presentation
     */
    public String toString() {
        return value;
    }

    // POJO start
    protected ObjectID() {
    }

    void setValue(String value) {
        this.value = value;
    }
    // POJO end

    /* fields */
    private String value;
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
 *  The Original Code is ObjectID.java
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