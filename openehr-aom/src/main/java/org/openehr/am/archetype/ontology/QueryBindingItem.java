/*
 * component:   "openEHR Reference Implementation"
 * description: "Class QueryBindingItem"
 * keywords:    "archetype"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$Source: /usr/local/cvsroot/acode/openehr-kernel/src/java/org/openehr/am/archetype/ontology/QueryBindingItem.java,v $"
 * revision:    "$Revision: 10 $"
 * last_change: "$Date: 2005-11-22 22:26:12 +0100 (Tue, 22 Nov 2005) $"
 */
package org.openehr.am.archetype.ontology;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * OntologyBindingItem that defines the binding by a query to external service
 *
 * @author Rong Chen
 * @version 1.0
 */
public class QueryBindingItem extends OntologyBindingItem {

    /**
     * Creates a binding item with a qery
     *
     * @param code
     * @param query
     */
    public QueryBindingItem(String code, Query query) {
        super(code);
        this.query = query;
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
        if (!( o instanceof QueryBindingItem )) {
            return false;
        }

        final QueryBindingItem qbi = (QueryBindingItem) o;

        return new EqualsBuilder()
        .appendSuper(super.equals(o))
                .append(query, qbi.query)
                .isEquals();
    }

    /**
     * Return a hash code of this object
     *
     * @return hash code
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(19, 49)
        .appendSuper(super.hashCode())
        .append(query)
        .toHashCode();
    }
    
    
    
    /**
     * Query of this binding item
     *
     * @return the query
     */
    public Query getQuery() {
        return query;
    }

    /* fields */
    private Query query;
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
 *  The Original Code is QueryBindingItem.java
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