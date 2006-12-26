/*
 * component:   "openEHR Reference Implementation"
 * description: "Class CodePhrase"
 * keywords:    "datatypes"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/BRANCHES/RM-1.0-update/libraries/src/java/org/openehr/rm/datatypes/text/CodePhrase.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 23:20:08 +0200 (Wed, 12 Oct 2005) $"
 */
package org.openehr.rm.datatypes.text;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.openehr.rm.support.identification.TerminologyID;
import org.openehr.rm.datatypes.basic.DataValue;

/**
 * A fully coordinated (all coordination  has been performed)
 * term from a terminologyID service (as distinct from a particular
 * terminologyID). Instances of this class are immutable.
 * 
 * @author Rong Chen
 * @version 1.0
 */
public final class CodePhrase extends DataValue {

    /**
     * Constructs a CodePhrase by terminologyID and codeString
     *
     * @param terminologyID
     * @param codeString
     * @throws IllegalArgumentException if terminolgy null
     *          or codeString null or empty
     */
    public CodePhrase(TerminologyID terminologyID, String codeString) {
        if(terminologyID == null) {
            throw new IllegalArgumentException("null terminologyID");
        }
        if(StringUtils.isEmpty(codeString)) {
            throw new IllegalArgumentException("empty codeString");
        }
        this.terminologyID = terminologyID;
        this.codeString = codeString;
    }

    /**
     * Constructs a CodePhrase by terminologyID and codeString
     *
     * @param terminologyID
     * @param codeString
     * @throws IllegalArgumentException if terminolgy null
     *          or codeString null or empty
     */
    public CodePhrase(String terminologyID, String codeString) {
        if(terminologyID == null) {
            throw new IllegalArgumentException("null terminologyID");
        }
        if(StringUtils.isEmpty(codeString)) {
            throw new IllegalArgumentException("empty codeString");
        }
        this.terminologyID =  new TerminologyID(terminologyID);
        this.codeString = codeString;
    }

    /**
     * string form displayable for humans
     *
     * @return <code>String</code>
     */
    public String toString() {
        return terminologyID + ", " + codeString;
    }

    /**
     * Identifier of the distinct terminologyID from which the
     * code_string (or its elements) was extracted.
     *
     * @return <code>TerminologyID</code>
     */
    public TerminologyID getTerminologyID() {
        return terminologyID;
    }

    /**
     * The key used by the terminologyID service to identify a concept
     * or coordination of concepts. This string is most likely
     * parsable inside the terminologyID service, but nothing can be
     * assumed about its syntax outside that context.
     *
     * @return <code>String</code>
     */
    public String getCodeString() {
        return codeString;
    }

    /**
     * Two CodePhrases equal if both has same value for
     * terminologyID and codeString
     *
     * @param o
     * @return true if equals
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!( o instanceof CodePhrase )) return false;

        final CodePhrase codePhrase = (CodePhrase) o;

        return new EqualsBuilder()
        .append(terminologyID, codePhrase.terminologyID)
        .append(codeString, codePhrase.codeString)
        .isEquals();
    }

    /**
     * Return a hash code of this CodePhrase
     *
     * @return a hash code
     */
    public int hashCode() {
        return new HashCodeBuilder(13, 47)
        .append(terminologyID)
        .append(codeString)
        .toHashCode();
    }

    // POJO start
    CodePhrase() {
    }

    void setTerminologyID(TerminologyID terminologyID) {
        this.terminologyID = terminologyID;
    }

    void setCodeString(String codeString) {
        this.codeString = codeString;
    }
    // POJO end

    /* fields */
    private TerminologyID terminologyID;
    private String codeString;
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
 *  The Original Code is CodePhrase.java
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