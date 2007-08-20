/*
 * component:   "openEHR Reference Implementation"
 * description: "Class CComplexObject"
 * keywords:    "archetype"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2006 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/am/archetype/constraintmodel/CComplexObject.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 23:20:08 +0200 (Wed, 12 Oct 2005) $"
 */
package org.openehr.am.archetype.constraintmodel;

import org.openehr.rm.support.basic.Interval;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.util.*;

/**
 * Constraint on complex objects, ie any object which consists of other object
 * constraints. Instances of this class are immutable.
 *
 * @author Rong Chen
 * @version 1.0
 */
public final class CComplexObject extends CDefinedObject {

    /**
     * Constructs a complexObjectConstraint
     *
     * @param path
     * @param rmTypeName
     * @param occurrences
     * @param nodeID
     * @param attributes
     * @param invariants
     */
    public CComplexObject(String path, String rmTypeName,
                          Interval<Integer> occurrences, String nodeID, 
                          List<CAttribute> attributes, CAttribute parent) {

    	// TODO probably need to inherit from CObject directly
        super(attributes == null, path, rmTypeName, occurrences, nodeID, 
        		parent, null);

        if (attributes != null && attributes.isEmpty()) {
            throw new IllegalArgumentException("empty attributes");
        }
        this.attributes = ( attributes == null
                ? null : new ArrayList<CAttribute>(attributes) );        
    }

    /**
     * List of constraints on attributes of the reference model type
     * represented by this object.
     *
     * @return Unmodifiable List<CAttribute>, null if allow any
     */
    public List<CAttribute> getAttributes() {
        return attributes == null ?
                null : Collections.unmodifiableList(attributes);
    }

    /**
     * True if this node is a valid archetype node.
     *
     * @return ture if valid
     */
    public boolean isValid() {
        if (attributes == null) {
            return true;
        }
        return false;  // todo: implement this method
    }

    /**
     * True if the relative path exists at this node.
     *
     * @param path
     * @return ture if has
     * @throws IllegalArgumentException if path null
     */
    public boolean hasPath(String path) {
        return false; // todo fix it
    }

    /**
     * True if constraints represented by other are narrower than this node.
     *
     * @param constraint
     * @return true if subset
     * @throws IllegalArgumentException if constraint null
     */
    public boolean isSubsetOf(ArchetypeConstraint constraint) {
        return false;  // todo: implement this method
    }
    
    /**
     * Equals if two CComplexObject have same values
     *
     * @param o
     * @return true if equals
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!( o instanceof CComplexObject )) return false;

        final CComplexObject ccobj = (CComplexObject) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(attributes, ccobj.attributes)
                .isEquals();
    }

    /**
     * Return a hash code of this object
     *
     * @return hash code
     */
    public int hashCode() {
        return new HashCodeBuilder(13, 29)
                .appendSuper(super.hashCode())
                .append(attributes)
                .toHashCode();
    }

    /* fields */
    private final List<CAttribute> attributes;    
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
 *  The Original Code is CComplexObject.java
 *
 *  The Initial Developer of the Original Code is Rong Chen.
 *  Portions created by the Initial Developer are Copyright (C) 2003-2006
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