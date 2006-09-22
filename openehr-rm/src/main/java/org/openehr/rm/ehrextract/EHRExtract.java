/*
 * component:   "openEHR Reference Implementation"
 * description: "Class EHRExtract"
 * keywords:    "ehr_extract"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004,2005 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/BRANCHES/RM-1.0-update/libraries/src/java/org/openehr/rm/ehrextract/EHRExtract.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 23:20:08 +0200 (Wed, 12 Oct 2005) $"
 */
package org.openehr.rm.ehrextract;

import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.support.identification.PartyReference;
import org.openehr.rm.common.generic.Participation;

import java.util.Set;

/**
 * The outer package for information extracted from an EHR by another
 * EHR system.
 *
 * @author Rong Chen
 * @version 1.0
 */
public class EHRExtract {

    /**
     * Constructs an EHRExtract
     *
     * @param timeCreated
     * @param ehrId
     * @param subjectOfCare
     * @param originator
     * @param otherParticipations
     * @param includeMultimedia
     * @param followLinks
     * @param directory
     * @param terminology
     * @param demographics
     * @param accessControl
     * @throws IllegalArgumentException
     */
    public EHRExtract(DvDateTime timeCreated, String ehrId,
                   PartyReference subjectOfCare, PartyReference originator,
                   Set<Participation> otherParticipations,
                   boolean includeMultimedia, int followLinks,
                   XFolder directory, XTerminology terminology,
                   XDemographics demographics, XAccessControl accessControl) {
        this.timeCreated = timeCreated;
        this.ehrId = ehrId;
        this.subjectOfCare = subjectOfCare;
        this.originator = originator;
        this.otherParticipations = otherParticipations;
        this.includeMultimedia = includeMultimedia;
        this.followLinks = followLinks;
        this.directory = directory;
        this.terminology = terminology;
        this.demographics = demographics;
        this.accessControl = accessControl;
    }

    public DvDateTime getTimeCreated() {
        return timeCreated;
    }

    public String getEhrId() {
        return ehrId;
    }

    public PartyReference getSubjectOfCare() {
        return subjectOfCare;
    }

    public PartyReference getOriginator() {
        return originator;
    }

    public Set<Participation> getOtherParticipations() {
        return otherParticipations;
    }

    public boolean isIncludeMultimedia() {
        return includeMultimedia;
    }

    public int getFollowLinks() {
        return followLinks;
    }

    public XFolder getDirectory() {
        return directory;
    }

    public XTerminology getTerminology() {
        return terminology;
    }

    public XDemographics getDemographics() {
        return demographics;
    }

    public XAccessControl getAccessControl() {
        return accessControl;
    }

    /* fields */
    private DvDateTime timeCreated;
    private String ehrId;
    private PartyReference subjectOfCare;
    private PartyReference originator;
    private Set<Participation> otherParticipations;
    private boolean includeMultimedia;
    private int followLinks;
    private XFolder directory;
    private XTerminology terminology;
    private XDemographics demographics;
    private XAccessControl accessControl;
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
 *  The Original Code is EHRExtract.java
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
