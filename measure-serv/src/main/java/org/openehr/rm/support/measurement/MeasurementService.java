/*
 * component:   "openEHR Reference Implementation"
 * description: "Class MeasurementService"
 * keywords:    "support"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/BRANCHES/RM-1.0-update/libraries/src/java/org/openehr/rm/support/measurement/MeasurementService.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 23:20:08 +0200 (Wed, 12 Oct 2005) $"
 */
package org.openehr.rm.support.measurement;

import java.io.Serializable;

/**
 * Defines an interfaceto a measurement information service
 *
 * @author Rong Chen
 * @version 1.0
 *
 */
public interface MeasurementService extends Serializable{

    /**
     * Returns True if the units string according to
     * the HL7 UCUM specification.
     *
     * @param units
     * @return true if units valid
     * @throws IllegalArgumentException if units null
     */
    public boolean isValidUnitsString(String units);

    /**
     * Return True if two units strings correspond to the same
     * measured property.
     * 
     * @param units1
     * @param units2
     * @return true if two units equal
     * @throws IllegalArgumentException if units1 or units2 null
     */
    public boolean unitsEquivalent(String units1, String units2);
    
    /**
     * Return True if two units strings are comparable.
     * 
     * @param units1
     * @param units2
     * @return true if two units comparable
     * @throws IllegalArgumentException if units1 or units2 null
     */
    public boolean unitsComparable(String units1, String units2);
    
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
    public int compare(String units1, Double value1, String units2, Double value2);
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
 *  The Original Code is MeasurementService.java
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