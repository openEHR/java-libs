/*
 * component:   "openEHR Reference Implementation"
 * description: "Class DvPeriodicTimeSpecification"
 * keywords:    "datatypes"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/BRANCHES/RM-1.0-update/libraries/src/java/org/openehr/rm/datatypes/timespec/DvPeriodicTimeSpecification.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 23:20:08 +0200 (Wed, 12 Oct 2005) $"
 */
package org.openehr.rm.datatypes.timespec;

import org.openehr.rm.datatypes.encapsulated.DvParsable;
import org.openehr.rm.datatypes.quantity.datetime.DvDuration;

/**
 * Specifies periodic points in time, linked to the calendar
 * (phase-linked), or a real world repeating event, such as
 * "breakfast" (event-linked). Based on the HL7v3 data types
 * PIVL<T> and EIVL<T>.
 *
 * @author Rong Chen
 * @version 1.0
 */
public final class DvPeriodicTimeSpecification
        extends DvTimeSpecification {

    /**
     * Constructs a PeriodicTimeSpecification by given value
     *
     * @param value not null and value.formalism is either
     *              "HL7:PIVL" or "HL7:EIVL"
     * @throws IllegalArgumentException if value is null or not valid
     *                                  HL7 PIVL or EIVL
     */
    public DvPeriodicTimeSpecification(DvParsable value) {

        super(value);

        if (!"HL7:PIVL".equals(value.getFormalism())
                && !"HL7:EIVL".equals(value.getFormalism())) {

            throw new IllegalArgumentException("unknown formalism: "
                    + value.getFormalism());
        }
    }

    /**
     * The period of the repetition, computationally derived from the
     * syntax representation. Extracted from the "value" attribute.
     *
     * @return period
     */
    public DvDuration period() {
        return null; // todo: fix it
    }

    /**
     * Indicates what prototypical point in the calendar the
     * specification is aligned to, like 5th of the month.
     * Extracted from the  value  attribute.
     *
     * @return calendar alignment
     */
    public String calendarAlignment() {
        return null; // todo: implement this
    }

    /**
     * Indicates what real-world event the specification is aligned
     * to if any. Extracted from the  value  attribute.
     *
     * @return event alignment
     */
    public String eventAlignment() {
        return null;  // todo: implement this
    }

    /**
     * Indicates if the specification is aligned with institution
     * schedules, like a hospital nursing changeover or meal serving
     * times. Extracted from the  value  attribute.
     *
     * @return true if specified by institution
     */
    public boolean institutionSpecified() {
        return false;  // todo: implement this
    }

	@Override
	public String getReferenceModelName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String serialise() {
		// TODO Auto-generated method stub
		return null;
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
 *  The Original Code is DvPeriodicTimeSpecification.java
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