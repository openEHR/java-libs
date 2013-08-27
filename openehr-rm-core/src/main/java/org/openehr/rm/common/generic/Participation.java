/*
 * component:   "openEHR Reference Implementation"
 * description: "Class Participation"
 * keywords:    "common"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/common/generic/Participation.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 22:20:08 +0100 (Wed, 12 Oct 2005) $"
 */
package org.openehr.rm.common.generic;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.openehr.rm.Attribute;
import org.openehr.rm.FullConstructor;
import org.openehr.rm.RMObject;
import org.openehr.rm.datatypes.quantity.DvInterval;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.support.terminology.TerminologyService;

/**
 * Model of a participation of a Party (any Actor or Role) in an activity.
 * Instances of this object are immutable.
 *
 * @author Rong Chen
 * @version 1.0
 */
public final class Participation extends RMObject {

    /**
     * Constructs a Participation
     *
     * @param performer          not null
     * @param function           not null
     * @param mode               not null and exists
     * @param time
     * @param terminologyService not null
     * @throws IllegalArgumentException if function null
     *                                  or mode invalid or performer null
     */
	@FullConstructor
    public Participation(
    		@Attribute(name = "performer", required = true)PartyProxy performer,
    		@Attribute(name = "function", required = true)DvText function,
    		@Attribute(name = "mode", required = true)DvCodedText mode, 
    		@Attribute(name = "time")DvInterval<DvDateTime> time,
    		@Attribute(name = "terminologyService", system = true)TerminologyService terminologyService) {
        if (performer == null) {
            throw new IllegalArgumentException("null performer");
        }
        if (function == null) {
            throw new IllegalArgumentException("null function");
        }
        if (mode == null) {
            throw new IllegalArgumentException("null mode");
        }
        if (terminologyService == null) {
            throw new IllegalArgumentException("null terminologyService");
        }

        if (( function instanceof DvCodedText )
                && ( !terminologyService.terminology(
                        TerminologyService.OPENEHR)
                .codesForGroupName("participation function", "en")
                .contains(((DvCodedText) function).getDefiningCode()))) {
            throw new IllegalArgumentException("unkown function: " + function);
        }

        if (!terminologyService.terminology(TerminologyService.OPENEHR)
                .codesForGroupName("participation mode", "en")
                .contains(mode.getDefiningCode())) {
            throw new IllegalArgumentException("unkown mode: " + mode);
        }
        this.performer = performer;
        this.function = function;
        this.mode = mode;
        this.time = time;
    }

    /**
     * The party participating in the activity.
     *
     * @return performer
     */
    public PartyProxy getPerformer() {
        return performer;
    }

    /**
     * The function of the Party in this participation
     *
     * @return function
     */
    public DvText getFunction() {
        return function;
    }

    /**
     * The mode of the performer / activity interaction,
     * eg present, by telephone, by email etc.
     *
     * @return mode
     */
    public DvCodedText getMode() {
        return mode;
    }

    /**
     * The time interval during which the participation took place,
     * if it is used in an observational context (ie recording facts
     * about the past); or the intended time interval of the
     * participation when used in future contexts, such as EHR
     * Instructions.
     *
     * @return DvInterval<DvDateTime>
     */
    public DvInterval<DvDateTime> getTime() {
        return time;
    }

    /**
     * Equals if two participations have same values
     *
     * @param o
     * @return true if equals
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!( o instanceof Participation )) return false;

        final Participation p = (Participation) o;

        return new EqualsBuilder()
                .append(performer, p.performer)
                .append(function, p.function)
                .append(mode, p.mode)
                .append(time, p.time)
                .isEquals();
    }

    /**
     * Return a hash code of this object
     *
     * @return hash code
     */
    public int hashCode() {
        return new HashCodeBuilder(7, 19)
                .append(performer)
                .append(function)
                .append(mode)
                .append(time)
                .toHashCode();
    }

    // POJO start
    Participation() {
    }

    void setPerformer(PartyProxy performer) {
        this.performer = performer;
    }

    void setFunction(DvText function) {
        this.function = function;
    }

    void setMode(DvCodedText mode) {
        this.mode = mode;
    }

    void setTime(DvInterval<DvDateTime> time) {
        this.time = time;
    }
    // POJO end

    /* fields */
    private PartyProxy performer;
    private DvText function;
    private DvCodedText mode;
    private DvInterval<DvDateTime> time;
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
 *  The Original Code is Participation.java
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