/*
 * component:   "openEHR Reference Implementation"
 * description: "Class DvParsable"
 * keywords:    "datatypes"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/BRANCHES/RM-1.0-update/libraries/src/java/org/openehr/rm/datatypes/encapsulated/DvParsable.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 23:20:08 +0200 (Wed, 12 Oct 2005) $"
 */
package org.openehr.rm.datatypes.encapsulated;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.openehr.rm.Attribute;
import org.openehr.rm.FullConstructor;
import org.openehr.rm.datatypes.basic.DataValue;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.support.terminology.TerminologyService;

/**
 * Encapsulated data expressed as a parsable string.
 * The internal model of the data item is not described in the
 * openEHR model in common with other encapsulated types, but in
 * this case, the form of the data is assumed to be plaintext,
 * rather than compressed or other types of large binary data.
 * <p/>
 * Instances of Parsable are immutable.
 *
 * @author Rong Chen
 * @version 1.0
 */
public final class DvParsable extends DvEncapsulated {

    /**
     * Constructs a Parsable by given components
     *
     * @param charset
     * @param language
     * @param size
     * @param value              not null
     * @param formalism          not null or empty
     * @param terminologyService
     * @throws IllegalArgumentException if any invalid arguement
     */
    @FullConstructor
            public DvParsable(@Attribute(name = "charset", system = true) CodePhrase charset,
                              @Attribute(name = "language", system = true) CodePhrase language,
                              @Attribute(name = "value", required = true) String value,
                              @Attribute(name = "formalism", required = true) String formalism,
                              @Attribute(name = "terminologyService", system = true)
            TerminologyService terminologyService) {
        
    	super(charset, language, terminologyService);
        
    	if (value == null) {
            throw new IllegalArgumentException("null value");
        }
        if (StringUtils.isEmpty(formalism)) {
            throw new IllegalArgumentException("null or empty formalism");
        }
        this.value = value;
        this.formalism = formalism;
    }

    /**
     * Create a parsable by value and formalism
     * 
     * @param value
     * @param formalism
     */
    public DvParsable(String value, String formalism) {
    	this(null, null, value, formalism, null);
    }
    
    /**
     * The string value, which may validly be empty in some syntaxes
     *
     * @return value
     */
    public String getValue() {
        return value;
    }

    /**
     * Name of the formalism
     *
     * @return formalism
     */
    public String getFormalism() {
        return formalism;
    }
    
    @Override
	public int getSize() {
		return value.length();
	}

    /**
     * Two Parsable equal if both has same value and
     * formalism
     *
     * @param o
     * @return true if equals
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!( o instanceof DvParsable )) return false;

        final DvParsable parsable = (DvParsable) o;

        return new EqualsBuilder()
                .append(value, parsable.value)
                .append(formalism, parsable.formalism)
                .isEquals();
    }

    /**
     * Return a hash code of this parsable
     *
     * @return hash code
     */
    public int hashCode() {
        return new HashCodeBuilder(17, 47)
                .append(value)
                .append(formalism)
                .toHashCode();
    }

    // POJO start
    private DvParsable() {
    }

    private void setValue(String value) {
        this.value = value;
    }

    private void setFormalism(String formalism) {
        this.formalism = formalism;
    }

    // POJO end

    /* fields */
    private String value;
    private String formalism;	
	@Override
	public String getReferenceModelName() {
		return "DV_PARSABLE";
	}

    public DataValue parse(String value) {
        if (!value.contains(",")){
            throw new IllegalArgumentException("failed to parse parsable["+ value + "]");
        }
        String parsableValue = StringUtils.substringBeforeLast(value, ",").trim();
        String formalism = StringUtils.substringAfterLast(value, ",").trim();
        return new DvParsable(parsableValue, formalism);
    }

	@Override
	public String serialise() {
		return getReferenceModelName() + "," + value + "," + formalism;
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
 *  The Original Code is DvParsable.java
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