/*
 * component:   "openEHR Reference Implementation"
 * description: "Class FeederAuditDetails"
 * keywords:    "common"
 *
 * author:      "Yin Su Lim <y.lim@chime.ucl.ac.uk>"
 * support:     "CHIME, UCL"
 * copyright:   "Copyright (c) 2006 UCL, UK"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/common/archetyped/FeederAuditDetails.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2006-03-08 22:20:08 +0100 (Wed, 12 Oct 2005) $"
 */
package org.openehr.rm.common.archetyped;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.openehr.rm.RMObject;
import org.openehr.rm.common.generic.PartyIdentified;
import org.openehr.rm.common.generic.PartyProxy;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;

/**
 * Audit details for a feeder system. Instances of this class are
 * immutable
 *
 * @author Yin Su Lim
 * @version 1.0
 */
public final class FeederAuditDetails extends RMObject {

    /**
     * Constrcuts a FeederAuditDetails
     *
     * @param systemId           not null
     * @param provider		   null if not present
     * @param timeCommitted      null if not present
     * @param location		   null if not present
     * @param time			   null if not present
     * @param subject			   null if not present
     * @param versionId		   null if not present
     * @throws IllegalArgumentException if systemId is null or empty
     */
    public FeederAuditDetails(String systemID, PartyIdentified provider,
    		PartyIdentified location, DvDateTime time, PartyProxy subject,
    		String versionID) {
        if (StringUtils.isEmpty(systemID)) {
            throw new IllegalArgumentException("null or empty systemId");
        }
        this.systemId = systemID;
        this.provider = provider;
        this.location = location;
        this.time = time;
        this.subject = subject;
        this.versionId = versionID;
    }

    /**
     * Identity of the system which handled the information item
     *
     * @return systemId
     */
    public String getSystemId() {
        return systemId;
    }

    /**
     * Identity of optional provider who created/committed/forwarded/handled the item
     *
     * @return provider
     */
    public PartyIdentified getProvider() {
        return provider;
    }

    /**
     * Identity of site/facility within an organisation which handled the item
     *
     * @return location
     */
    public PartyIdentified getLocation() {
        return location;
    }
    
    /**
     * Time of handling of the item
     *
     * @return time
     */
    public DvDateTime getTime() {
        return time;
    }

    /**
     * Identity for subject of the received information item
     *
     * @return subject
     */
    public PartyProxy getSubject() {
        return subject;
    }

    /**
     * Any identifier used in the system if available
     *
     * @return versionId
     */
    public String getVersionId() {
        return versionId;
    }

    /**
     * Equals if have same values
     *
     * @param o
     * @return true if equals
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!( o instanceof FeederAuditDetails )) return false;

        final FeederAuditDetails fad = (FeederAuditDetails) o;

        return new EqualsBuilder()
                .append(systemId, fad.systemId)
                .append(provider, fad.provider)
                .append(location, fad.location)
                .append(time, fad.time)
                .append(subject, fad.subject)
                .append(versionId, fad.versionId)
                .isEquals();
    }

    /**
     * Return a hashcode of this object
     *
     * @return hashcode
     */
    public int hashCode() {
        return new HashCodeBuilder(7, 23)
                .append(systemId)
                .append(provider)
                .append(location)
                .append(time)
                .append(subject)
                .append(versionId)
                .toHashCode();
    }

    //POJO start
    FeederAuditDetails() {}
    
	void setProvider(PartyIdentified provider) {
		this.provider = provider;
	}

	void setSubject(PartyProxy subject) {
		this.subject = subject;
	}

	void setSystemId(String systemID) {
		this.systemId = systemID;
	}

	void setTime(DvDateTime time) {
		this.time = time;
	}

	void setVersionId(String versionID) {
		this.versionId = versionID;
	}
	
	void setLocation(PartyIdentified location) {
		this.location = location;
	}
    //POJO end
	
    /* fields */
    private String systemId;
    private PartyIdentified provider;
    private PartyIdentified location;
    private DvDateTime time;
    private PartyProxy subject;
    private String versionId;
    
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
 *  The Original Code is FeederAuditDetails.java
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