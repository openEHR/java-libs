/*
 * component:   "openEHR Reference Implementation"
 * description: "Class DvTimeSpecification"
 * keywords:    "datatypes"
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
package org.openehr.rm.datatypes.timespec;

import org.openehr.rm.datatypes.basic.DataValue;
import org.openehr.rm.datatypes.encapsulated.DvParsable;

/**
 * This is an abstract class of which all timing
 * specifications are specialisations. Specifies points in timespec,
 * possibly linked to the calendar, or a real world repeating event,
 * such as "breakfast".
 * 
 * @author Rong Chen
 * @version 1.0
 */
public abstract class DvTimeSpecification extends DataValue {

    /**
     * Constructs a TimeSpecification by given components
     *
     * @param value not null
     * @throws IllegalArgumentException if value null
     */
    public DvTimeSpecification(DvParsable value) {
        if(value == null) {
            throw new IllegalArgumentException("null value");
        }
        this.value = value;
    }

    /**
     * The specification, in the HL7v3 syntax for PIVL or EIVL types
     *
     * @return value
     */
    public DvParsable getValue() {
        return value;
    }

    /**
     * Indicates what prototypical point in the calendar the
     * specification is aligned to, like 5th of the month.
     * Extracted from the  value  attribute.
     *
     * @return calendar alignment
     */
    public abstract String calendarAlignment();

    /**
     * Indicates what real-world event the specification is aligned
     * to if any. Extracted from the  value  attribute.
     *
     * @return event alignment
     */
    public abstract String eventAlignment();

    /**
     * Indicates if the specification is aligned with institution
     * schedules, like a hospital nursing changeover or meal serving
     * times. Extracted from the  value  attribute.
     *
     * @return true if specified by institution
     */
    public abstract boolean institutionSpecified();

    /* fields */
    private final DvParsable value;
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
 *  The Original Code is DvTimeSpecification.java
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