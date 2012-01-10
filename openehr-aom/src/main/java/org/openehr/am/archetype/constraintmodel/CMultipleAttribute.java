/*
 * component:   "openEHR Reference Implementation"
 * description: "Class CMultipleAttribute"
 * keywords:    "archetype"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/am/archetype/constraintmodel/CMultipleAttribute.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 23:20:08 +0200 (Wed, 12 Oct 2005) $"
 */
package org.openehr.am.archetype.constraintmodel;

import java.util.*;

import org.openehr.rm.support.basic.MultiplicityInterval;

/**
 * Concrete model of constraint on a multiple-valued attribute node. Instances
 * of this class are immutable.
 *
 * @author Rong Chen
 * @version 1.0
 */
public final class CMultipleAttribute extends CAttribute {

    /**
     * Create a constraint for mulple-valued attribute node
     *
     * @param path
     * @param rmAttributeName
     * @param existence
     * @param children
     * @param cardinality
     */
    public CMultipleAttribute(String path, String rmAttributeName,
                              MultiplicityInterval existence, Cardinality cardinality,
                              List<CObject> children) {
        super(path, rmAttributeName, existence, children);
        this.cardinality = cardinality;
    }
    
    @Override
	public CAttribute copy() {
		return new CMultipleAttribute(path(), getRmAttributeName(), 
				getExistence(), cardinality, copyChildren());
	}

    /**
     * List of constraints representing members of the container value of this
     * attribute within the data. Semantics of the container uniqueness and
     * ordering given by the cardinality.
     *
     * @return unmodifiable list of CObject
     */
    public List<CObject> members() {
        return getChildren();
    }

    /**
     * The cardinality of this attribute
     *
     * @return not null
     */
    public Cardinality getCardinality() {
        return cardinality;
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

    /* fields */
    private final Cardinality cardinality;	    
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
 *  The Original Code is CMultipleAttribute.java
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