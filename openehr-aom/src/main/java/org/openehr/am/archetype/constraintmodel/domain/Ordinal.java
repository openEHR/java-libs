/*
 * component:   "openEHR Reference Implementation"
 * description: "Class Ordinal"
 * keywords:    "archetype"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/am/archetype/constraintmodel/domain/Ordinal.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 23:20:08 +0200 (Wed, 12 Oct 2005) $"
 */
package org.openehr.am.archetype.constraintmodel.domain;

import org.apache.commons.lang.StringUtils;

/**
 * Ordinal item included in the list of ordinal constraint. Immutable.
 *
 * @author Rong Chen
 * @version 1.0
 * @deprecated use Ordinal from openehr-ap instead
 */
public final class Ordinal {

    /**
     * Constructs an ordinal
     *
     * @param value
     * @param terminology
     * @param code
     * @throws IllegalArgumentException if value < 0 or
     *                    terminology empty or code emtpy
     */
    public Ordinal(int value, String terminology, String code) {
        if(StringUtils.isEmpty(terminology) || StringUtils.isEmpty(code)) {
            throw new IllegalArgumentException("terminology or code empty");
        }
        this.value = value;
        this.terminology = terminology;
        this.code = code;
    }

    /**
     * The integer value of this ordinal
     *
     * @return no less than 0
     */
    public int getValue() {
        return value;
    }

    /**
     * The terminology id
     *
     * @return terminology
     */
    public String getTerminology() {
        return terminology;
    }

    /**
     * The code of the term
     *
     * @return code
     */
    public String getCode() {
        return code;
    }

    /**
     * Return string presentation of this ordinal
     *
     * @return string form
     */
    public String toString() {
        return "[" + terminology + "] " + value + "|" + code;    
    }

    /* fields */
    private final int value;
    private final String terminology;
    private final String code;
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
 *  The Original Code is Ordinal.java
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