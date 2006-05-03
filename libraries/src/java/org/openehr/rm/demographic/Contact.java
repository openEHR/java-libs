/*
 * component:   "openEHR Reference Implementation"
 * description: "Class Attestation"
 * keywords:    "demographic"
 *
 * author:      "Goran Pestana <goran@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2005 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/demographic/Contact.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 22:20:08 +0100 (Wed, 12 Oct 2005) $"
 */
package org.openehr.rm.demographic;

import org.openehr.rm.common.archetyped.Locatable;
import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.common.archetyped.FeederAudit;
import org.openehr.rm.common.archetyped.Link;
import org.openehr.rm.support.identification.ObjectID;
import org.openehr.rm.datatypes.quantity.DvInterval;
import org.openehr.rm.datatypes.quantity.datetime.DvDate;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.FullConstructor;
import org.openehr.rm.Attribute;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.util.List;
import java.util.Set;

/**
 * Description of a means of contact of a party. Actual structure is archetyped.
 *
 * @author Goran Pestana
 * @author Rong Chen
 *
 * @version 1.0
 */
public class Contact extends Locatable {

    protected Contact() {
    }

    /**
     * Constructs a Contact
     *
     * @param uid              null if not specified
     * @param archetypeNodeId
     * @param name
     * @param archetypeDetails null if not specified
     * @param feederAudit      null if not specified
     * @param links            null if not specified
     * @param timeValidity     null if not specified
     * @param addresses        not null
     * @throws IllegalArgumentException if name null or archetypeNodeId null,
     *                                  or links not null and empty,
     *                                  or addresses null or empty
     */
    @FullConstructor
            public Contact(@Attribute(name = "uid") ObjectID uid,
                           @Attribute(name = "archetypeNodeId", required = true) String archetypeNodeId,
                           @Attribute(name = "name", required = true) DvText name,
                           @Attribute(name = "archetypeDetails") Archetyped archetypeDetails,
                           @Attribute(name = "feederAudit") FeederAudit feederAudit,
                           @Attribute(name = "links") Set<Link> links,
                           @Attribute(name = "parent") Locatable parent,
                           @Attribute(name = "timeValidity") DvInterval<DvDate> timeValidity,
                           @Attribute(name = "addresses", required = true) List<Address> addresses) {
        super(uid, archetypeNodeId, name, archetypeDetails, feederAudit, links, parent);
        if (addresses == null || addresses.size() == 0) {
            throw new IllegalArgumentException("null or empty addresses");
        }
        this.timeValidity = timeValidity;
        this.addresses = addresses;
    }

    /**
     * Purpose for which this contact is used, eg mail, daytime phone, etc.
     * Taken from value of inherited name attribute.
     *
     * @return purpose
     */
    public DvText purpose() {
        return this.getName();
    }

    /**
     * Return true if the path is valid with respect to the current
     * item.
     *
     * @param path
     * @return true if valid
     */
    public boolean validPath(String path) {
        try {
            itemAtPath(path);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * The item at a path that is relative to this item.
     *
     * @param path
     * @return relative path
     * @throws IllegalArgumentException if path invalid
     */
    public Object itemAtPath(String path) {
        Object ret = super.itemAtPath(path);
        if (ret != null) {
            return ret;
        }
        String whole = whole();
        String tmp = path;
        if (tmp.startsWith(whole)) {
            tmp = tmp.substring(whole.length());
        }
        ret = checkAttribute(tmp, "addresses", addresses);
        if (ret == null) {
            throw new IllegalArgumentException("invalid path: " + path);
        }
        return ret;
    }

    public String pathOfItem(Locatable item) {
        //todo: to be implemented
        return null;
    }

    /**
     * Valid time interval for this contact descriptor
     *
     * @return time validity or null if not specified
     */
    public DvInterval<DvDate> getTimeValidity() {
        return timeValidity;
    }

    protected void setTimeValidity(DvInterval<DvDate> timeValidity) {
        this.timeValidity = timeValidity;
    }

    /**
     * A set of address alternatives for this contact
     *
     * @return addresses
     */
    public List<Address> getAddresses() {
        return addresses;
    }

    protected void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    /**
     * Equals if two contacts has same values
     *
     * @param o
     * @return equals if true
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!( o instanceof Contact )) return false;
        if (!super.equals(o)) return false;

        final Contact contact = (Contact) o;
        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(timeValidity, contact.timeValidity)
                .append(addresses, contact.addresses)
                .isEquals();
    }

    /**
     * Return a hash code of this contact
     *
     * @return hash code
     */
    public int hashCode() {
        return new HashCodeBuilder(13, 29)
                .appendSuper(super.hashCode())
                .append(timeValidity)
                .append(addresses)
                .toHashCode();
    }


    /* fields */
    private DvInterval<DvDate> timeValidity;
    private List<Address> addresses;
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
 *  The Original Code is Contact.java
 *
 *  The Initial Developer of the Original Code is Goran Pestana.
 *  Portions created by the Initial Developer are Copyright (C) 2003-2005
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