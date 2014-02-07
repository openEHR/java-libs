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
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/composition/EventContext.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 22:20:08 +0100 (Wed, 12 Oct 2005) $"
 */
package org.openehr.rm.composition;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.openehr.rm.Attribute;
import org.openehr.rm.FullConstructor;
import org.openehr.rm.RMObject;
import org.openehr.rm.common.generic.Participation;
import org.openehr.rm.common.generic.PartyIdentified;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;
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
     * @param healthCareFacility null if not specified
     * @param startTime          required
     * @param endTime            null if not specified
     * @param participations     null if not specified
     * @param location           null if not specified
     * @param setting            required
     * @param otherContext       null if not specified
     * @param terminologyService
     * @throws IllegalArgumentException
     */
	@FullConstructor
    public EventContext(
    		@Attribute(name = "healthCareFacility") PartyIdentified healthCareFacility,
    		@Attribute(name = "startTime", required = true) DvDateTime startTime,
    		@Attribute(name = "endTime") DvDateTime endTime,
    		@Attribute(name = "participations") List<Participation> participations,
    		@Attribute(name = "location") String location,
    		@Attribute(name = "setting", required = true) DvCodedText setting,
    		@Attribute(name = "otherContext") ItemStructure otherContext,
    		@Attribute(name = "terminologyService", system = true) TerminologyService terminologyService) {

        if (startTime == null) {
            throw new IllegalArgumentException("null startTime");
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
                .codesForGroupName("setting", "en")
                .contains(setting.getDefiningCode())) {
            throw new IllegalArgumentException("unknown setting: " + setting);
        }
        this.healthCareFacility = healthCareFacility;
        this.startTime = startTime;
        this.endTime = endTime;
        this.participations = ( participations == null ?
                null : new ArrayList<Participation>(participations) );
        this.location = location;
        this.setting = setting;
        this.otherContext = otherContext;
    }
    
    /**
     * Create an eventText with required values
     */
    public EventContext(DvDateTime startTime, DvCodedText setting, 
    		TerminologyService termServ) {
    	this(null, startTime, null, null, null, setting, null, termServ);
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
    public PartyIdentified getHealthCareFacility() {
        return healthCareFacility;
    }

    /**
     * Start time of the clinical session
     *
     * @return startTime
     */
    public DvDateTime getStartTime() {
        return startTime;
    }
    
    /**
     * Optional end time of the clinical session
     *
     * @return endTime
     */
    public DvDateTime getEndTime() {
        return endTime;
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
                .append(startTime, eventContext.startTime)
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
                .append(startTime)
                .append(endTime)
                .append(participations)
                .append(location)
                .append(setting)
                .append(otherContext)
                .toHashCode();
    }

    // POJO start
    EventContext() {
    }

    void setHealthCareFacility(PartyIdentified healthCareFacility) {
        this.healthCareFacility = healthCareFacility;
    }

    void setStartTime(DvDateTime startTime) {
        this.startTime = startTime;
    }

    void setEndTime(DvDateTime endTime) {
        this.endTime = endTime;
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
    private PartyIdentified healthCareFacility;
    private DvDateTime startTime;
    private DvDateTime endTime;
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