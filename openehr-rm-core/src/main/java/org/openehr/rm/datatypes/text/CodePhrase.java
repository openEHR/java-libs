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
import org.openehr.rm.Attribute;
import org.openehr.rm.FullConstructor;
import org.openehr.rm.support.identification.TerminologyID;
import org.openehr.rm.datatypes.basic.DataValue;
import org.openehr.rm.datatypes.basic.ReferenceModelName;

/**
 * A fully coordinated (all coordination  has been performed)
 * term from a terminologyId service (as distinct from a particular
 * terminologyId). Instances of this class are immutable.
 * 
 * @author Rong Chen
 * @version 1.0
 */
public final class CodePhrase extends DataValue {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs a CodePhrase by terminologyId and codeString
     *
     * @param terminologyId
     * @param codeString
     * @throws IllegalArgumentException if terminolgy null
     *          or codeString null or empty
     */
	@FullConstructor
    		public CodePhrase(
    		@Attribute(name = "terminologyId", required = true) TerminologyID terminologyId, 
    		@Attribute(name = "codeString", required = true) String codeString) {
        
		if(terminologyId == null) {
            throw new IllegalArgumentException("null terminologyId");
        }
        if(StringUtils.isEmpty(codeString)) {
            throw new IllegalArgumentException("empty codeString");
        }
        this.terminologyId = terminologyId;
        this.codeString = codeString;
    }

    /**
     * Constructs a CodePhrase by terminologyId and codeString
     *
     * @param terminologyId
     * @param codeString
     * @throws IllegalArgumentException if terminolgy null
     *          or codeString null or empty
     */
    public CodePhrase(String terminologyID, String codeString) {
        if(terminologyID == null) {
            throw new IllegalArgumentException("null terminologyId");
        }
        if(StringUtils.isEmpty(codeString)) {
            throw new IllegalArgumentException("empty codeString");
        }
        this.terminologyId =  new TerminologyID(terminologyID);
        this.codeString = codeString;
    }

    /**
     * string form displayable for humans
     *
     * @return <code>String</code>
     */
    public String toString() {
        return terminologyId + "::" + codeString;
    }
    
    public CodePhrase parse(String value) {
    	if(value == null) {
    		throw new IllegalArgumentException("null value");
    	}
    	int i = value.indexOf("::");
    	if(i <= 0 || i >= value.length() -1) {
    		throw new IllegalArgumentException("wrong format of code phrase");
    	}
    	
    	String t = value.substring(0, i);
    	String c = value.substring(i + 2);
    	return new CodePhrase(t, c);
    }

    /**
     * Identifier of the distinct terminologyId from which the
     * code_string (or its elements) was extracted.
     *
     * @return <code>TerminologyID</code>
     */
    public TerminologyID getTerminologyId() {
        return terminologyId;
    }

    /**
     * The key used by the terminologyId service to identify a concept
     * or coordination of concepts. This string is most likely
     * parsable inside the terminologyId service, but nothing can be
     * assumed about its syntax outside that context.
     *
     * @return <code>String</code>
     */
    public String getCodeString() {
        return codeString;
    }

    /**
     * Two CodePhrases equal if both has same value for
     * terminologyId and codeString
     *
     * @param o
     * @return true if equals
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!( o instanceof CodePhrase )) return false;

        final CodePhrase codePhrase = (CodePhrase) o;

        return new EqualsBuilder()
        .append(terminologyId, codePhrase.terminologyId)
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
        .append(terminologyId)
        .append(codeString)
        .toHashCode();
    }

    // POJO start
    CodePhrase() {
    }

    public void setTerminologyId(TerminologyID terminologyID) {
        this.terminologyId = terminologyID;
    }

    public void setCodeString(String codeString) {
        this.codeString = codeString;
    }
    // POJO end

    /* fields */
    private TerminologyID terminologyId;
    private String codeString;
	@Override
	public String getReferenceModelName() {
		return ReferenceModelName.CODE_PHRASE.getName();
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