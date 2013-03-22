/*
 * component:   "openEHR Reference Implementation"
 * description: "Class SimpleMeasurementService"
 * keywords:    "support"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2007 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL$"
 * revision:    "$LastChangedRevision$"
 * last_change: "$LastChangedDate$"
 */
package org.openehr.rm.support.measurement;

import javax.measure.Measure;
import javax.measure.unit.Unit;

/**
 * Simple implementation of measurement information service
 *
 * @author Rong Chen
 * @version 1.0
 *
 */
public class  SimpleMeasurementService implements MeasurementService {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public static MeasurementService getInstance() {
	return soleInstance;
    }

    private static final SimpleMeasurementService soleInstance = 
	    new SimpleMeasurementService();

    private SimpleMeasurementService() {		
    }

    /**
     * Returns True if the units string according to
     * the HL7 UCUM specification.
     *
     * @param units
     * @return true if units valid
     * @throws IllegalArgumentException if units null
     */
    public boolean isValidUnitsString(String units) {
	if(units == null) {
	    throw new IllegalArgumentException("units null");
	} 
	// TODO fix it
	return true;
    }

    /**
     * Return True if two units strings correspond to the same
     * measured property.
     * 
     * @param units1
     * @param units2
     * @return true if two units equal
     * @throws IllegalArgumentException if units1 or units2 null
     */
    public boolean unitsEquivalent(String units1, String units2) {
	if(units1 == null) {
	    throw new IllegalArgumentException("units1 null");
	}
	if(units2 == null) {
	    throw new IllegalArgumentException("units2 null");
	}
	return units1.equalsIgnoreCase(units2);    	
    }

    /**
     * Return True if two units strings are comparable.
     * 
     * @param units1
     * @param units2
     * @return true if two units comparable
     * @throws IllegalArgumentException if units1 or units2 null
     */
    public boolean unitsComparable(String units1, String units2) {
	if(units1 == null) {
	    throw new IllegalArgumentException("units1 null");
	}
	if(units2 == null) {
	    throw new IllegalArgumentException("units2 null");
	}
	return Unit.valueOf(units1).isCompatible(Unit.valueOf(units2));
    }

    /**
     * Comparison between two measures.
     * 
     * @param units1
     * @param units2
     * @return a negative integer, zero, or a positive integer as this measure
     *         is less than, equal to, or greater than the specified measurable
     *         quantity.
     * @throws IllegalArgumentException if units1, units2 null or not comparable
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public int compare(String units1, Double value1, String units2, Double value2) {
	if(value1 == null) {
	    throw new IllegalArgumentException("value1 null");
	}
	if(value2 == null) {
	    throw new IllegalArgumentException("value2 null");
	}
	if (unitsEquivalent(units1, units2)){
	    return value1.compareTo(value2);
	}else{
	    Unit<?> unit1 = Unit.valueOf(units1);
	    Unit<?> unit2 = Unit.valueOf(units2);
	    if (!unit1.isCompatible(unit2)){
		throw new IllegalArgumentException("units '"+units1+"' is not comparable to '"+units2+"'");
	    }
	    Measure measure1 = Measure.valueOf(value1, unit1);
	    Measure measure2 = Measure.valueOf(value2, unit2);
	    return measure1.compareTo(measure2);
	}
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
 *  The Original Code is SimpleMeasurementService.java
 *
 *  The Initial Developer of the Original Code is Rong Chen.
 *  Portions created by the Initial Developer are Copyright (C) 2007
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