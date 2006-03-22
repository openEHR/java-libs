/*
 * component:   "openEHR Reference Implementation"
 * description: "Class CCount"
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
package org.openehr.am.archetype.constraintmodel.domain;

import org.openehr.am.archetype.constraintmodel.ArchetypeConstraint;
import org.openehr.am.archetype.constraintmodel.CComplexObject;
import org.openehr.am.archetype.constraintmodel.CDomainType;
import org.openehr.am.archetype.constraintmodel.DVObjectCreationException;
import org.openehr.am.archetype.ontology.ArchetypeOntology;
import org.openehr.rm.datatypes.basic.DataValue;
import org.openehr.rm.datatypes.quantity.DvCount;
import org.openehr.rm.support.basic.Interval;
import org.openehr.rm.util.SystemValue;

import java.util.Map;

/**
 * Constraint for DvCount
 *
 * @author Rong Chen
 * @version 1.0
 */
public final class CCount extends CDomainType {

    /**
     * Creates an ObjectConstraint
     *
     * @param path
     * @param magnitude
     */
    public CCount(String path, Interval<Integer> magnitude) {
        super(path, "DvCount");
        if (magnitude == null) {
            throw new IllegalArgumentException("null magnitude");
        }
        this.magnitude = magnitude;
    }

    /**
     * Return range of magnitude
     *
     * @return null if no limit
     */
    public Interval<Integer> getMagnitude() {
        return magnitude;
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
     * Create an DvCount from a string value
     *
     * @param value        the magnitude of DvCount
     * @param systemValues
     * @return a DvCount
     * @throws DVObjectCreationException if value has wrong format or
     *                                   not within the range
     */
    public DvCount createObject(String value,
                                Map<SystemValue, Object> systemValues,
                                ArchetypeOntology ontology)
            throws DVObjectCreationException {

        int i = 0;
        try {
            i = Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw DVObjectCreationException.BAD_FORMAT;
        }
        if (!magnitude.has(new Integer(i))) {
            throw DVObjectCreationException.BAD_VALUE;
        }
        return new DvCount(i);
    }

    /**
     * Return true if the constraint has limit the possible value to
     * be only one, which means the value has been assigned by the archetype
     * author at design time
     *
     * @return true if has
     */
    public boolean hasAssignedValue() {
        return false;
    }

    /**
     * Return assigned value as data value instance
     *
     * @param systemValues
     * @param ontology
     * @return datavalue or null if not assigned
     */
    public DataValue assignedValue(Map<SystemValue, Object> systemValues,
                                   ArchetypeOntology ontology) {
        return null;
    }

    /* fields */
    private final Interval<Integer> magnitude;
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
 *  The Original Code is CCount.java
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