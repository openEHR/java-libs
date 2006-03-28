/*
 * component:   "openEHR Reference Implementation"
 * description: "Class LeafConstraint"
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

import org.openehr.am.archetype.ontology.ArchetypeOntology;
import org.openehr.rm.support.basic.Interval;
import org.openehr.rm.util.SystemValue;

import java.util.Map;

/**
 * Interface for leaf node in archetype model, which can be used to create
 * and validate data_types RM object
 *
 * @author Rong Chen
 * @version 1.0
 */
public abstract class LeafConstraint extends CObject {

    /**
     * Creates an ObjectConstraint without occurrences or nodeID
     *
     * @param anyAllowed
     * @param path
     * @param rmTypeName
     */
    protected LeafConstraint(boolean anyAllowed, String path,
                             String rmTypeName) {
        super(anyAllowed, path, rmTypeName);
    }

    /**
     * Creates an ObjectConstraint
     *
     * @param anyAllowed
     * @param path
     * @param rmTypeName
     * @param occurrences null indicates {1..1}
     * @param nodeID
     * @throws IllegalArgumentException if rmTypeName null or empty,
     *                                  or nodeID null or empty
     */
    public LeafConstraint(boolean anyAllowed, String path, String rmTypeName,
                          Interval<Integer> occurrences, String nodeID) {
        super(anyAllowed, path, rmTypeName, occurrences, nodeID);
    }

    /**
     * Create an datavalue from a string value
     *
     * @param value
     * @param systemValues
     * @return a DataValue
     */
    public abstract Object createObject(String value,
                                        Map<SystemValue, Object> systemValues,
                                        ArchetypeOntology ontology)
            throws DVObjectCreationException;

    /**
     * Return true if the constraint has limit the possible value to
     * be only one, which means the value has been assigned by the archetype
     * author at design time
     *
     * @return true if has
     */
    public abstract boolean hasAssignedValue();

    /**
     * Return assigned value as data value instance
     *
     * @param systemValues
     * @param ontology
     * @return object or null if not assigned
     */
    public abstract Object assignedValue(Map<SystemValue, Object> systemValues,
                                         ArchetypeOntology ontology);
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
 *  The Original Code is LeafConstraint.java
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