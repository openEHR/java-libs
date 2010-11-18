/*
 * component:   "openEHR Reference Implementation"
 * description: "Class ConstraintRef"
 * keywords:    "archetype"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/am/archetype/constraintmodel/ConstraintRef.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 23:20:08 +0200 (Wed, 12 Oct 2005) $"
 */
package org.openehr.am.archetype.constraintmodel;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.openehr.am.archetype.constraintmodel.primitive.CDate;
import org.openehr.rm.support.basic.Interval;

/**
 * Reference to a constraint described in the same archetype, but outside
 * the main constraint structure. This is used to refer to constraints
 * expressed in terms of external resources, such as constraints on terminology
 * value sets.
 *
 * @author Rong Chen
 * @version 1.0
 */
public final class ConstraintRef extends CReferenceObject {

    /**
     * Constructs a ConstraintRef
     *
     * @param path
     * @param rmTypeName
     * @param occurrences
     * @param nodeId
     * @param parent
     * @param reference
     * @throws IllegalArgumentException if reference null
     */
    public ConstraintRef(String path, String rmTypeName,
            Interval<Integer> occurrences, String nodeId, CAttribute parent, 
            String reference) {

        super(false, path, rmTypeName, occurrences, nodeId, parent);

        if(StringUtils.isEmpty(reference)) {
            throw new IllegalArgumentException("null reference");
        }
        // TODO archetype.ontology.has_constraint(reference)
        this.reference = reference;
    }
    
    public CObject copy() {
    	return new ConstraintRef(path(), getRmTypeName(), getOccurrences(),
    			getNodeID(), getParent(), reference);
    }

    /**
     * Reference to a constraint in the archetype local ontology.
     *
     * @return reference
     */
    public String getReference() {
        return reference;
    }

    /**
     * True if this node is a valid archetype node.
     *
     * @return ture if valid
     */
    public boolean isValid() {
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
        return false;  // todo: implement this method
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
     * Equals if two CObject has same values
     *
     * @param o
     * @return true if equals
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!( o instanceof ConstraintRef )) return false;

        final ConstraintRef cobj = (ConstraintRef) o;

        return new EqualsBuilder()
                .append(reference, cobj.reference)
                .isEquals();
    }

    /**
     * Return a hash code of this object
     *
     * @return hash code
     */
    public int hashCode() {
        return new HashCodeBuilder(7, 47)
                .append(reference)
                .toHashCode();
    }

    private String reference;
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
 *  The Original Code is ConstraintRef.java
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