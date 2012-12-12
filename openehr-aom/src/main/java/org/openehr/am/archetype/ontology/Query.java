/*
 * component:   "openEHR Reference Implementation"
 * description: "Class Query"
 * keywords:    "archetype"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/am/archetype/ontology/Query.java $"
 * revision:    "$LastChangedRevision: 24 $"
 * last_change: "$LastChangedDate: 2006-03-31 17:08:58 +0200 (Fri, 31 Mar 2006) $"
 */
package org.openehr.am.archetype.ontology;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * The class represents a query to external terminology service. Immutable.
 *
 * @author Rong Chen
 * @version 1.1
 */
public class Query {

   /**
     * Constructor
     * 
     * @param url
     */
    public Query(String url) {
    	this.url = url;
    }
    
    /**
     * The url of this query
     * 
     * @return url
     */
    public String getUrl() {
    	return url;
    }

    /**
     * String representation of this object
     *
     * @return string form
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this,
                ToStringStyle.MULTI_LINE_STYLE);
    }

    /**
     * Equals if two has have same values
     *
     * @param o
     * @return true if equals
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!( o instanceof Query )) {
            return false;
        }

        final Query query = (Query) o;

        return new EqualsBuilder()
                .append(url, query.url)
                .isEquals();
    }

    /**
     * Return a hash code of this object
     *
     * @return hash code
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(7, 47)
        .append(url)
        .toHashCode();
    }
    
    
    /* fields */
    private String url;
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
 *  The Original Code is Query.java
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