/*
 * component:   "openEHR Reference Implementation"
 * description: "Class ArchetypeSlot"
 * keywords:    "archetype"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/am/archetype/constraintmodel/ArchetypeSlot.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 23:20:08 +0200 (Wed, 12 Oct 2005) $"
 */
package org.openehr.am.archetype.constraintmodel;

import org.openehr.rm.support.basic.Interval;
import org.openehr.am.archetype.assertion.Assertion;

import java.util.Set;

/**
 * ArchetypeSlot
 *
 * @author Rong Chen
 * @version 1.0
 */
public class ArchetypeSlot extends CReferenceObject {

    /**
     * Constructs an ArchetyepSlot
     *
     * @param path
     * @param rmTypeName
     * @param occurrences
     * @param nodeID
     * @param parent
     * @param includes
     * @param excludes
     * @throws IllegalArgumentException if includes not null and empty
     *         or if excludes not null and empty or both are null
     */
    public ArchetypeSlot(String path, String rmTypeName,
                         Interval<Integer> occurrences,
                         String nodeID, CAttribute parent,
                         Set<Assertion> includes, Set<Assertion> excludes) {

    	super(includes == null && excludes == null, path, rmTypeName, 
    			occurrences, nodeID, parent);

        if(includes != null && includes.isEmpty()) {
            throw new IllegalArgumentException("empty includes");
        }
        if(excludes != null && excludes.isEmpty()) {
            throw new IllegalArgumentException("empty excludes");
        }
        this.includes = includes;
        this.excludes = excludes;
    }

    /**
     * List of constraints defining other archetypes which could be included
     * at this point.
     *
     * @return List of Assertion
     */
    public Set<Assertion> getIncludes() {
        return includes;
    }

    /**
     * List of constraints defining other archetypes which cannot be included
     * at this point.
     *
     * @return List of Assertion
     */
    public Set<Assertion> getExcludes() {
        return excludes;
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
    private Set<Assertion> includes;
    private Set<Assertion> excludes; 
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
 *  The Original Code is ArchetypeSlot.java
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