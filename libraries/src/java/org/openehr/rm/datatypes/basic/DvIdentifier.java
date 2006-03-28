/*
 * component:   "openEHR Reference Implementation"
 * description: "Class DvIdentifier"
 * keywords:    "datatypes"
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
package org.openehr.rm.datatypes.basic;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.openehr.rm.FullConstructor;
import org.openehr.rm.Attribute;

/**
 * Type for representing identifiers of real-world entities. Typical
 * identifiers include drivers licence number, social security number,
 * vertans affairs number, prescription id, order id, and so on.
 *
 * @author Rong Chen
 * @version 1.0
 */
public class DvIdentifier extends DataValue {

    /**
     * Construct a DvIdentifier
     *
     * @param issuer
     * @param id
     * @param type
     * @throws IllegalArgumentException if issuer or id or type is null or empty
     */
    @FullConstructor
            public DvIdentifier(@Attribute(name = "issuer", required=true) String issuer,
                                @Attribute(name = "id", required=true) String id,
                                @Attribute(name = "type", required=true) String type) {
        if (StringUtils.isEmpty(issuer)) {
            throw new IllegalArgumentException("empty or null issuer");
        }
        if (StringUtils.isEmpty(id)) {
            throw new IllegalArgumentException("empty or null id");
        }
        if (StringUtils.isEmpty(type)) {
            throw new IllegalArgumentException("empty or null type");
        }
        this.issuer = issuer;
        this.id = id;
        this.type = type;
    }

    /**
     * Issuing agency of these kind of ids
     *
     * @return issuer
     */
    public String getIssuer() {
        return issuer;
    }

    /**
     * The identifier value. Often structured, according to the definition of
     * the issuing authority s rules.
     *
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * The identifier type, such as prescription, or SSN. One day a
     * controlled vocabulary might be possible for this.
     *
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * Equals if two DvIdentifiers have same values
     *
     * @param o
     * @return true if equals
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!( o instanceof DvIdentifier )) return false;

        final DvIdentifier dvid = (DvIdentifier) o;

        return new EqualsBuilder()
                .append(issuer, dvid.issuer)
                .append(id, dvid.id)
                .append(type, dvid.type)
                .isEquals();
    }

    /**
     * Return a hash code of this object
     *
     * @return hash code
     */
    public int hashCode() {
        return new HashCodeBuilder(3, 17)
                .append(issuer)
                .append(id)
                .append(type)
                .toHashCode();
    }

    // POJO start
    protected DvIdentifier() {
    }

    protected void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    protected void setId(String id) {
        this.id = id;
    }

    protected void setType(String type) {
        this.type = type;
    }
    // POJO end

    /* fields */
    private String issuer;
    private String id;
    private String type;
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
 *  The Original Code is DvIdentifier.java
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