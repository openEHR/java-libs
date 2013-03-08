/*
 * component:   "openEHR Reference Implementation"
 * description: "Class GenericID"
 * keywords:    "support"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * copyright:   "Copyright (c) 2007 Cambio Healthcare Systems, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL$"
 * revision:    "$LastChangedRevision$"
 * last_change: "$LastChangedDate$"
 */
package org.openehr.rm.support.identification;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.openehr.rm.Attribute;
import org.openehr.rm.FullConstructor;

/**
 * Generic identifier type for identifiers whose format is otherwise unknown 
 * to openEHR. Includes an attribute for naming the identification scheme 
 * (which may well be local).
 * 
 * @author Rong Chen
 */
public class GenericID extends ObjectID {
	
	@FullConstructor
	public GenericID(
			@Attribute(name = "value", required = true)String value, 
			@Attribute(name = "scheme", required = true)String scheme) {
		super(value);
		if(StringUtils.isEmpty(scheme)) {
			throw new IllegalArgumentException("empty scheme");
		}
		this.scheme = scheme;
	}
	
	/**
	 * Gets scheme of this id
	 * 
	 * @return scheme
	 */
	public String getScheme() {
		return this.scheme;
	}
	
    /**
     * Equals if value and scheme equals
     *
     * @param o
     * @return true if equals
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!( o instanceof GenericID )) {
            return false;
        }

        final GenericID gid = (GenericID) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(scheme, gid.scheme)
                .isEquals();
    }
    
    /**
     * Return hash code of this id
     *
     * @return hash code
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(scheme)
                .toHashCode();
    }
	
	/* fields */
	private final String scheme;
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
 *  The Original Code is GenericID.java
 *
 *  The Initial Developer of the Original Code is Rong Chen.
 *  Portions created by the Initial Developer are Copyright (C) 2003-2007
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
