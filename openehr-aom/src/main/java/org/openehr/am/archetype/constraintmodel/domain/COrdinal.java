/*
 * component:   "openEHR Reference Implementation"
 * description: "Class COrdinal"
 * keywords:    "archetype"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/am/archetype/constraintmodel/domain/COrdinal.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 23:20:08 +0200 (Wed, 12 Oct 2005) $"
 */
package org.openehr.am.archetype.constraintmodel.domain;

import org.openehr.am.archetype.constraintmodel.ArchetypeConstraint;
import org.openehr.am.archetype.constraintmodel.CComplexObject;
import org.openehr.am.archetype.constraintmodel.CDomainType;

import java.util.*;

/**
 * This class represents a constraint of domain type ordinal. Instances of this
 * class are immutable.
 *
 * @author Rong Chen
 * @version 1.0
 * @deprecated Use CDvOrdinal from openehr-ap instead
 */
public final class COrdinal extends CDomainType {

    /**
     * Constructs a ordinal constraint by a list of ordinal
     *
     * @param path
     * @param list List<Ordinal>
     * @throws IllegalArgumentException if list null or empty
     */
    public COrdinal(String path, List<Ordinal> list) {

        super(path, "DvOrdinal");

        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("list null or empty");
        }
        this.list = new ArrayList<Ordinal>(list);
    }


    /**
     * Standard form of constraint
     *
     * @return Standard form of constraint
     */
    public CComplexObject standardRepresentation() {
        return null;  // todo: implement this method
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
     * List of ordinal
     *
     * @return unmodifiable List<Ordinal>
     */
    public List<Ordinal> getList() {
        return Collections.unmodifiableList(list);
    }

    /**
     * Return true if the constraint has limit the possible value to
     * be only one, which means the value has been assigned by the archetype
     * author at design time
     *
     * @return true if has
     */
    public boolean hasAssignedValue() {
        return list.size() == 1;
    }
   
    /* fields */
    private final List<Ordinal> list;
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
 *  The Original Code is COrdinal.java
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