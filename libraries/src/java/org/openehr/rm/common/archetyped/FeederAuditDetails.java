/*
 * component:   "openEHR Reference Implementation"
 * description: "Class FeederAuditDetails"
 * keywords:    "common"
 *
 * author:      "Yin Su Lim <y.lim@chime.ucl.ac.uk>"
 * support:     "CHIME, UCL"
 * copyright:   ""
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
     * @param systemID           not null
     * @param provider		   null if not present
     * @param timeCommitted      null if not present
     * @param location		   null if not present
     * @param time			   null if not present
     * @param subject			   null if not present
     * @param versionID		   null if not present
     * @throws IllegalArgumentException if systemID is null or empty
     */
    public FeederAuditDetails(String systemID, PartyIdentified provider,
    		PartyIdentified location, DvDateTime time, PartyProxy subject,
    		String versionID) {
        if (StringUtils.isEmpty(systemID)) {
            throw new IllegalArgumentException("empty systemID");
        }
        this.systemID = systemID;
        this.provider = provider;
        this.location = location;
        this.time = time;
        this.subject = subject;
        this.versionID = versionID;
    }

    /**
     * Identity of the system which handled the information item
     *
     * @return systemID
     */
    public String getSystemID() {
        return systemID;
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
     * @return versionID
     */
    public String getVersionID() {
        return versionID;
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
                .append(systemID, fad.systemID)
                .append(provider, fad.provider)
                .append(location, fad.location)
                .append(time, fad.time)
                .append(subject, fad.subject)
                .append(versionID, fad.versionID)
                .isEquals();
    }

    /**
     * Return a hashcode of this object
     *
     * @return hashcode
     */
    public int hashCode() {
        return new HashCodeBuilder(7, 23)
                .append(systemID)
                .append(provider)
                .append(location)
                .append(time)
                .append(subject)
                .append(versionID)
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

	void setSystemID(String systemID) {
		this.systemID = systemID;
	}

	void setTime(DvDateTime time) {
		this.time = time;
	}

	void setVersionID(String versionID) {
		this.versionID = versionID;
	}
	
	void setLocation(PartyIdentified location) {
		this.location = location;
	}
    //POJO end
	
    /* fields */
    private String systemID;
    private PartyIdentified provider;
    private PartyIdentified location;
    private DvDateTime time;
    private PartyProxy subject;
    private String versionID;
    
}
