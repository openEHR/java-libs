/*
 * component:   "openEHR Reference Implementation"
 * description: "Class DvURI"
 * keywords:    "datatypes"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/BRANCHES/RM-1.0-update/libraries/src/java/org/openehr/rm/datatypes/uri/DvURI.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 23:20:08 +0200 (Wed, 12 Oct 2005) $"
 */
package org.openehr.rm.datatypes.uri;

import org.openehr.rm.Attribute;
import org.openehr.rm.FullConstructor;
import org.openehr.rm.datatypes.basic.DataValue;

import java.net.URISyntaxException;

/**
 * Purpose A reference to an object which conforms to the Universal
 * Resource Identifier (URI) standard, as defined by W3C RFC 2936.
 * <p>
 * See "Universal Resource Identifiers in WWW" by Tim Berners-Lee at
 * <a href="http://www.ietf.org/rfc/rfc2396.txt"><i>
 * http://www.ietf.org/rfc/rfc2396.txt</i></a>
 * This is a World-Wide Web RFC for global identification of resources.
 * </p>
 * <p>
 * See <a href="http://www.w3.org/Addressing"><i>
 * http://www.w3.org/Addressing</i><a> for a starting point on URIs.
 * </p>
 * <p>
 * See <a href="http://www.ietf.org/rfc/rfc2806.txt"><i>
 * http://www.ietf.org/rfc/rfc2806.txt</i></a>
 * for new URI types like telephone, fax and modem numbers.
 * </p>
 *
 * Instances of this class are immutable.
 *
 * @author Rong Chen
 * @version 1.0
 */
public class DvURI extends DataValue {

    /**
     * Constructs a URI by parsing the given string.
     *
     * @param str The string to be parsed into a URI
     * @throws IllegalArgumentException if str null or bad syntax
     */
	@FullConstructor public DvURI(@Attribute(name = "value", required = true) String value) {
        loadValue(value);
    }

    private void loadValue(String value) {
        try {
            this.value = new java.net.URI(value);
        } catch(NullPointerException e) {
            throw new IllegalArgumentException("null uri");
        } catch(URISyntaxException urise) {
            throw new IllegalArgumentException("bad syntax: " + value);
        }
    }

    // POJO start
    DvURI() {
    }

    private void setValue(String value) {
        loadValue(value);
    }
    // POJO end

    /**
     * string form displayable for humans
     *
     * @return string presentation
     */
    public String toString() {
        return value.toString();
    }

    /**
     * Returns the scheme component of this URI.
     *
     * @return scheme
     */
    public String scheme() {
        return value.getScheme();
    }

    /**
     * Returns the decoded path component of this URI.
     *
     * @return path
     */
    public String path() {
        return value.getPath();
    }

    /**
     * Returns the decoded fragment component of this URI.
     *
     * @return fragment
     */
    public String fragmentID() {
        return value.getFragment();
    }

    /**
     * Returns the decoded query component of this URI.
     *
     * @return query
     */
    public String query() {
        return value.getQuery();
    }

    /**
     * Return value of URI as a String.
     *
     * @return string value
     */
    public String getValue() {
        return value.toString();
    }

    /**
     * Equals if values are the same
     *
     * @param o
     * @return true if equals
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!( o instanceof DvURI )) return false;

        final DvURI dvURI = (DvURI) o;

        if (!value.equals(dvURI.value)) return false;

        return true;
    }

    /**
     * Return a hashcode of this object
     *
     * @return hashcode
     */
    public int hashCode() {
        return value.hashCode();
    }

    /* fields */
    private java.net.URI value;

	@Override
	public String getReferenceModelName() {
		return "DV_URI";
	}

	@Override
	public String serialise() {
		// TODO Auto-generated method stub
		return null;
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
 *  The Original Code is DvURI.java
 *
 *  The Initial Developer of the Original Code is Rong Chen.
 *  Portions created by the Initial Developer are Copyright (C) 2003-2004
 *  the Initial Developer. All Rights Reserved.
 *
 *  Contributor(s): Daniel Karlsson
 *
 * Software distributed under the License is distributed on an 'AS IS' basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 *  ***** END LICENSE BLOCK *****
 */