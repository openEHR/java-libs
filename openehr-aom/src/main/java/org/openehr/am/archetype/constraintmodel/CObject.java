/*
 * component:   "openEHR Reference Implementation"
 * description: "Class CObject"
 * keywords:    "archetype"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/am/archetype/constraintmodel/CObject.java $"
 * revision:    "$LastChangedRevision: 43 $"
 * last_change: "$LastChangedDate: 2006-08-08 12:54:07 +0200 (Tue, 08 Aug 2006) $"
 */
package org.openehr.am.archetype.constraintmodel;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.openehr.rm.support.basic.Interval;

import java.util.Set;

/**
 * Abstract model of constraint on any kind of object node.
 *
 * @author Rong Chen
 * @version 1.0
 */
public abstract class CObject extends ArchetypeConstraint {

    /**
     * Creates an ObjectConstraint
     *
     * @param anyAllowed
     * @param path
     * @param rmTypeName
     * @param occurrences not null
     * @param nodeID
     * @param parent	null if no parent
     * @throws IllegalArgumentException if rmTypeName null or empty,
     *                                  or occurrences null, 
     *                                  or nodeID null or empty
     */
    protected CObject(boolean anyAllowed, String path, String rmTypeName,
                      Interval<Integer> occurrences, String nodeID,
                      CAttribute parent) {

        super(anyAllowed, path);

        if (StringUtils.isEmpty(rmTypeName)) {
            throw new IllegalArgumentException("null or empty rmTypeName");
        }
        if (nodeID != null && StringUtils.isEmpty(nodeID)) {
            throw new IllegalArgumentException("empty nodeID");
        }
        if(occurrences == null) {
        	throw new IllegalArgumentException("null occurrences");
        }
        this.rmTypeName = rmTypeName;
        this.occurrences = occurrences;
        this.nodeID = nodeID;
        this.parent = parent;
    }

    /**
     * Reference model type which this node corresponds to.
     *
     * @return reference model type name
     */
    public String getRmTypeName() {
        return rmTypeName;
    }

    /**
     * Occurrences of this object node in the data, under the owning attribute.
     * Upper limit can only be greater than 1 if owning attribute has a
     * cardinality of more than 1).
     *
     * @return Interval<Integer>, null indicates {1..1}
     */
    public Interval<Integer> getOccurrences() {
        return occurrences;
    }

    /**
     * Semantic id of this node, used to differentiate sibling nodes of the
     * same type. [Previously called  meaning ].
     *
     * @return node ids null if unspecified
     */
    public String getNodeID() {
        return nodeID;
    }
    
    /**
     * Gets CAttribute that owns this CObject
     * 
     * @return the parent of this CObject
     */
    public CAttribute getParent() {
    	return parent;
    }

    /**
     * Check if this object node is required by occurrences
     * and path of nodes for which values are submitted
     *
     * @param paths a set of path of nodes for which values submitted
     * @return ture if this node is required
     */
    public boolean isRequired(Set<String> paths) {

        if (isRequired()) {
            return true;
        }

        // if any submitted value for descendant nodes
        for (String path : paths) {
            if (path.startsWith(path())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Equals if two CObject has same values
     *
     * @param o
     * @return true if equals
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!( o instanceof CObject )) return false;

        final CObject cobj = (CObject) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(rmTypeName, cobj.rmTypeName)
                .append(occurrences, cobj.occurrences)
                .append(nodeID, cobj.nodeID)
                .isEquals();
    }

    /**
     * Return a hash code of this object
     *
     * @return hash code
     */
    public int hashCode() {
        return new HashCodeBuilder(5, 23)
                .appendSuper(super.hashCode())
                .append(rmTypeName)
                .append(occurrences)
                .append(nodeID)
                .append(parent)
                .toHashCode();
    }

    /**
     * Return true if this object node is required
     *
     * @return true if required
     */
    public boolean isRequired() {
        if (occurrences == null) { // default {1..1}
            return true;
        }
        if (occurrences.getLower() != null && occurrences.getLower() > 0
                && occurrences.getUpper() != null
                && occurrences.getLower() < occurrences.getUpper()) {
            return true;
        }
        return false; // {0..N}
    }

    /* fields */
    private final String rmTypeName;
    private final Interval<Integer> occurrences;
    private final String nodeID;
    private final CAttribute parent;
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
 *  The Original Code is CObject.java
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