/*
 * component:   "openEHR Reference Implementation"
 * description: "Class UID"
 * keywords:    "common"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/BRANCHES/RM-1.0-update/libraries/src/java/org/openehr/rm/support/identification/UID.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 23:20:08 +0200 (Wed, 12 Oct 2005) $"
 */
package org.openehr.rm.support.identification;

import org.apache.commons.lang.StringUtils;

/**
 * Purpose Abstract parent of classes representing unique identifiers
 * which identify information entities in a durable way. UIDs only
 * ever identify one IE in time or space and are never re-used.
 *
 * Instances of this class are immutable
 *
 * @author Rong Chen
 * @version 1.0
 */
public abstract class UID {

    /**
     * Create an UID by value
     *
     * @param value
     * @throws IllegalArgumentException if value null
     */
    protected UID(String value) {
        if(StringUtils.isEmpty(value)) {
            throw new IllegalArgumentException("empty value");
        }
        this.value = value;
    }

    /**
     * The value of this id
     *
     * @return value
     */
    public String getValue() {
        return value;
    }

    /**
     * Return string presentation of this id
     *
     * @return string presentation
     */
    @Override
    public String toString() {
        return value;
    }

    /**
     * Two UID equal if both have same value
     *
     * @param o
     * @return true if equals
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!( o instanceof UID )) {
            return false;
        }

        final UID uid = (UID) o;

        return value.equals(uid.value);
    }

    /**
     * Return a hash code of this UID
     *
     * @return hash code
     */
    @Override
    public int hashCode() {
        return value.hashCode();
    }

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
 *  The Original Code is UID.java
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