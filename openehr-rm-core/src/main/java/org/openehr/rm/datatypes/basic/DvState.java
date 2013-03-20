/*
 * component:   "openEHR Reference Implementation"
 * description: "Class DvState"
 * keywords:    "datatypes"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/BRANCHES/RM-1.0-update/libraries/src/java/org/openehr/rm/datatypes/basic/DvState.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 23:20:08 +0200 (Wed, 12 Oct 2005) $"
 */
package org.openehr.rm.datatypes.basic;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.openehr.rm.Attribute;
import org.openehr.rm.FullConstructor;
import org.openehr.rm.datatypes.text.DvCodedText;

/**
 * For representing state values which obey a defined state machine,
 * such as a variable representing the states of an instruction or
 * care process. Instances of this class are immutable.
 *
 * @author Rong Chen
 * @version 1.0
 */
public final class DvState extends DataValue {

    /**
     * Constructs a state
     *
     * @param value
     * @param terminal
     * @throws IllegalArgumentException if value null
     */
    @FullConstructor
            public DvState(@Attribute(name = "value", required = true) DvCodedText value,
                           @Attribute(name = "terminal") String terminal) {
        this(value, Boolean.parseBoolean(terminal));
    }

    /**
     * Constructs a state
     *
     * @param value
     * @param terminal
     * @throws IllegalArgumentException if value null
     */
    public DvState(DvCodedText value, boolean terminal) {
        if (value == null) {
            throw new IllegalArgumentException("null value");
        }
        this.value = value;
        this.terminal = terminal;
    }

    /**
     * The state name. State names are determined by a state/event
     * table defined in archetypes, and coded using openEHR
     * Terminology or local archetype terms, as specified by the
     * archetype.
     *
     * @return state name
     */
    public DvCodedText getValue() {
        return value;
    }

    /**
     * Indicates whether this state is a terminal state, such as
     * "aborted", "completed" etc from which no further transitions
     * are possible.
     *
     * @return true if a terminal state
     */
    public boolean isTerminal() {
        return terminal;
    }

    /**
     * Two state equal if both has same value and terminal
     *
     * @param o
     * @return true if equals
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!( o instanceof DvState )) return false;

        final DvState state = (DvState) o;

        return new EqualsBuilder()
                .append(value, state.value)
                .append(terminal, state.terminal)
                .isEquals();
    }

    /**
     * Return a hash code of this state object
     *
     * @return hash code
     */
    public int hashCode() {
        return new HashCodeBuilder(7, 29)
                .append(value)
                .append(terminal)
                .toHashCode();
    }

    // POJO start
    DvState() {
    }

    void setValue(DvCodedText value) {
        this.value = value;
    }

    void setTerminal(boolean terminal) {
        this.terminal = terminal;
    }
    // POJO end

    /* fields */
    private DvCodedText value;
    private boolean terminal;
	@Override
	public String getReferenceModelName() {
		return "DV_STATE";
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
 *  The Original Code is DvState.java
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