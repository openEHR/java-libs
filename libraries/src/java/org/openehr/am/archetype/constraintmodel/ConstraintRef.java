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
 * file:        "$URL$"
 * revision:    "$LastChangedRevision$"
 * last_change: "$LastChangedDate$"
 */
package org.openehr.am.archetype.constraintmodel;

import org.apache.commons.lang.StringUtils;
import org.openehr.am.archetype.Archetype;
import org.openehr.rm.util.RMObjectBuilder;
import org.openehr.rm.common.archetyped.Archetyped;

import java.util.Map;
import java.util.Set;

/**
 * Reference to a constraint described in the same archetype, but outside
 * the main constraint structure. This is used to refer to constraints
 * expressed in terms of external resources, such as constraints on terminology
 * value sets.
 *
 * @author Rong Chen
 * @version 1.0
 */
public final class ConstraintRef extends CObject {

    /**
     * Constructs a ConstraintRef
     *
     * @param path
     * @param reference
     * @throws IllegalArgumentException if reference null
     */
    public ConstraintRef(String path, String rmTypeName, String reference) {

        super(false, path, rmTypeName);

        if(StringUtils.isEmpty(reference)) {
            throw new IllegalArgumentException("null reference");
        }
        this.reference = reference;
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
     * Create an object based on this object constraint
     *
     * @param objectMap
     * @param errorMap
     * @param archetype
     * @param builder
     * @param archetypeDetails
     * @return null if this node is optional
     */
    public Object createObject(Map<String, Object> objectMap,
                               Set<String> inputPaths,
                               Map<String, ErrorType> errorMap,
                               Archetype archetype,
                               RMObjectBuilder builder,
                               Archetyped archetypeDetails) {

        // todo: implement this method
        throw new UnsupportedOperationException();
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