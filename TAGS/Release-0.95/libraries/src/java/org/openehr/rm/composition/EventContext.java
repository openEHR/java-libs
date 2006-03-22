/*
 * component:   "openEHR Reference Implementation"
 * description: "Class EventContext"
 * keywords:    "composition"
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
package org.openehr.rm.composition;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.openehr.rm.RMObject;
import org.openehr.rm.common.generic.Participation;
import org.openehr.rm.support.identification.PartyReference;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;
import org.openehr.rm.datatypes.quantity.DvInterval;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.support.terminology.TerminologyService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Documents the clinical context of the clinical session (or
 * encounter). The context information recorded here are independent
 * of the attributes recorded in the version audit, which document
 * the  system interaction  context, ie the context of a user
 * interacting with the health record system. Clinical sessions
 * include patient contacts, and any other business activity, such
 * as pathology investigations which take place on behalf of the
 * patient.
 * <p/>
 * Instances of this class are immutable
 *
 * @author Rong Chen
 * @version 1.0
 */
public final class EventContext extends RMObject {

    /**
     * Constructs an EventContext
     *
     * @param healthCareFacility
     * @param time
     * @param composer
     * @param participations     null if not specified
     * @param location           null if not specified
     * @param setting
     * @param otherContext
     * @param terminologyService
     * @throws IllegalArgumentException
     */
    public EventContext(PartyReference healthCareFacility,
                        DvInterval<DvDateTime> time,
                        PartyReference composer,
                        List<Participation> participations,
                        String location,
                        DvCodedText setting,
                        ItemStructure otherContext,
                        TerminologyService terminologyService) {

        if (composer == null) {
            throw new IllegalArgumentException("null composer");
        }
        if (time == null) {
            throw new IllegalArgumentException("null time");
        }
        if (participations != null && participations.isEmpty()) {
            throw new IllegalArgumentException("empty participations");
        }
        if (location != null && StringUtils.isEmpty(location)) {
            throw new IllegalArgumentException("empty location");
        }
        if (terminologyService == null) {
            throw new IllegalArgumentException("null terminologyService");
        }
        if(setting == null) {
            throw new IllegalArgumentException("null setting");
        }
        if (!terminologyService.terminology(TerminologyService.OPENEHR)
                .hasCodeForGroupName(setting.getDefiningCode(),
                        "setting", "en")) {
            throw new IllegalArgumentException("unknown setting: " + setting);
        }
        this.healthCareFacility = healthCareFacility;
        this.time = time;
        this.composer = composer;
        this.participations = ( participations == null ?
                null : new ArrayList<Participation>(participations) );
        this.location = location;
        this.setting = setting;
        this.otherContext = otherContext;
    }

    /**
     * The health care facility under whose care the event took
     * place. This is the most specific workgroup or delivery unit
     * within a care delivery enterprise which has an official
     * identifier in the health system, and can be used to ensure
     * medicolegal accountability.
     *
     * @return healthcare facility
     */
    public PartyReference getHealthCareFacility() {
        return healthCareFacility;
    }

    /**
     * Start and end times of the clinical session
     *
     * @return interval of DvDateTime
     */
    public DvInterval<DvDateTime> getTime() {
        return time;
    }

    /**
     * The person primarily responsible for the content of the
     * Composition (not necessarily its entry into the EHR system).
     * This is the identifier which should appear on the screen.
     * It may or may not be the person who entered the data.
     *
     * @return composer
     */
    public PartyReference getComposer() {
        return composer;
    }

    /**
     * Parties involved in the clinical session. These would normally
     * include the physician( s) and often the patient (but not the
     * latter if the clinical session is a pathology test for
     * example).
     *
     * @return unmodifiable list of participations
     */
    public List<Participation> getParticipations() {
        return participations == null ? null :
                Collections.unmodifiableList(participations);
    }

    /**
     * The actual location where the session occurred,
     * eg  "microbiol lab 2", "home", "ward A3" and so on.
     *
     * @return location
     */
    public String getLocation() {
        return location;
    }

    /**
     * The setting in which the clinical session took place.
     * Coded using the openEHR Terminology, "setting" group.
     *
     * @return setting
     */
    public DvCodedText getSetting() {
        return setting;
    }

    /**
     * Other optional context which will be archetyped.
     *
     * @return other context
     */
    public ItemStructure getOtherContext() {
        return otherContext;
    }

    /**
     * Equals if values are the same
     *
     * @param o
     * @return true if equals
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!( o instanceof EventContext )) return false;

        final EventContext eventContext = (EventContext) o;

        return new EqualsBuilder()
                .append(healthCareFacility, eventContext.healthCareFacility)
                .append(time, eventContext.time)
                .append(composer, eventContext.composer)
                .append(participations, eventContext.participations)
                .append(location, eventContext.location)
                .append(setting, eventContext.setting)
                .append(otherContext, otherContext)
                .isEquals();
    }


    /**
     * Return a hash code of this event context
     *
     * @return hashcode
     */
    public int hashCode() {
        return new HashCodeBuilder()
                .append(healthCareFacility)
                .append(time)
                .append(composer)
                .append(participations)
                .append(location)
                .append(setting)
                .append(otherContext)
                .toHashCode();
    }

    // POJO start
    EventContext() {
    }

    void setHealthCareFacility(PartyReference healthCareFacility) {
        this.healthCareFacility = healthCareFacility;
    }

    void setTime(DvInterval<DvDateTime> time) {
        this.time = time;
    }

    void setComposer(PartyReference composer) {
        this.composer = composer;
    }

    void setParticipations(List<Participation> participations) {
        this.participations = participations;
    }

    void setLocation(String location) {
        this.location = location;
    }

    void setSetting(DvCodedText setting) {
        this.setting = setting;
    }

    void setOtherContext(ItemStructure otherContext) {
        this.otherContext = otherContext;
    }
    // POJO end

    /* fields */
    private PartyReference healthCareFacility;
    private DvInterval<DvDateTime> time;
    private PartyReference composer;
    private List<Participation> participations;
    private String location;
    private DvCodedText setting;
    private ItemStructure otherContext;
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
 *  The Original Code is EventContext.java
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