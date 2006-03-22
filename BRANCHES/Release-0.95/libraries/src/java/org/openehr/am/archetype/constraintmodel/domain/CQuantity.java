/*
 * component:   "openEHR Reference Implementation"
 * description: "Class CQuantity"
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
import org.openehr.rm.datatypes.quantity.DvQuantity;
import org.openehr.rm.support.basic.Interval;
import org.openehr.rm.support.measurement.MeasurementService;
import org.openehr.rm.util.SystemValue;

import java.util.Map;

/**
 * Constraint on DvQuantity
 *
 * @author Rong Chen
 * @version 1.0
 */
public class CQuantity extends CDomainType {

    /**
     * Constructs a DomainTypeConstraint
     *
     * @param path
     * @param magnitude null if no limit
     * @param units
     */
    public CQuantity(String path, Interval<Double> magnitude, String units) {
        super(path, "DvQuantity");
        this.magnitude = magnitude;
        this.units = units;
    }

    /**
     * Return interval of magnitude
     *
     * @return null if no limit
     */
    public Interval<Double> getMagnitude() {
        return magnitude;
    }

    /**
     * Return units
     *
     * @return units
     */
    public String getUnits() {
        return units;
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
     * Create an DvQuantity from a string value
     *
     * @param value
     * @param systemValues
     * @return a DvQuantity
     * @throws DVObjectCreationException if value has wrong format
     *                                   or value out of range
     */
    public DvQuantity createObject(String value,
                                   Map<SystemValue, Object> systemValues,
                                   ArchetypeOntology ontology)
            throws DVObjectCreationException {
        double d = 0.0;
        try {
            d = Double.parseDouble(value);
        } catch (NumberFormatException e) {
            throw DVObjectCreationException.BAD_FORMAT;
        }
        if (magnitude != null && !magnitude.has(new Double(d))) {
            throw DVObjectCreationException.BAD_VALUE;
        }
        MeasurementService ms = (MeasurementService)
                systemValues.get(SystemValue.MEASUREMENT_SERVICE);
        return new DvQuantity(units, d, ms);
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
    private final Interval<Double> magnitude;
    private final String units;
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
 *  The Original Code is CQuantity.java
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