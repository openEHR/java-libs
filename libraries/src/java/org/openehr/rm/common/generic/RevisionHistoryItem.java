/*
 * component:   "openEHR Reference Implementation"
 * description: "Class RevisionHistoryItem"
 * keywords:    "common"
 *
 * author:      "Yin Su Lim <y.lim@chime.ucl.ac.uk>"
 * support:     "CHIME, UCL"
 * copyright:   ""
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/common/generic/RevisionHistoryItem.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2006-03-08 22:20:08 +0100 (Wed, 12 Oct 2005) $"
 */
package org.openehr.rm.common.generic;

import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.openehr.rm.RMObject;
import org.openehr.rm.support.identification.ObjectVersionID;

/**
 * Represent an entry in revision history
 *
 * @author Yin Su Lim
 * @version 1.0
 */
public final class RevisionHistoryItem extends RMObject {

	/**
	 * Constructs a RevisionHistoryItem
	 * 
	 * @param audits		not null
	 * @param versionID	not null
	 * @throws IllegalArgumentException if audits null or empty, versionID null
	 */
	public RevisionHistoryItem(List<AuditDetails> audits, ObjectVersionID versionID) {
	       if (audits == null || audits.size() == 0) {
	            throw new IllegalArgumentException("empty audits");
	        }
	       if (versionID == null) {
	    	   	throw new IllegalArgumentException("null versionID");
	       }
	       this.audits = audits;
	       this.versionID = versionID;
	}
	
	/**
	 * The audits for this revision. There will always be at least one commit audit
	 * 
	 * @return audits
	 */
	public List<AuditDetails> getAudits() {
		return audits;
	}

	/**
	 * Version identifier for this revision
	 * 
	 * @return versionID
	 */
	public ObjectVersionID getVersionID() {
		return versionID;
	}	

    /**
     * Equals if two has same values
     *
     * @param o
     * @return true if equals
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!( o instanceof RevisionHistoryItem )) return false;

        final RevisionHistoryItem rhi = (RevisionHistoryItem) o;

        return new EqualsBuilder()
                .append(audits, rhi.audits)
                .append(versionID, rhi.versionID)
                .isEquals();
    }

    /**
     * Return a hash code of this object
     *
     * @return hash code
     */
    public int hashCode() {
        return new HashCodeBuilder(3, 97)
                .append(audits)
                .append(versionID)
                .toHashCode();
    }
	//POJO start
	RevisionHistoryItem() {
	}

	void setAudits(List<AuditDetails> audits) {
		this.audits = audits;
	}

	void setVersionID(ObjectVersionID versionID) {
		this.versionID = versionID;
	}
	//POJO end
	
	/* fields */
	private List<AuditDetails> audits;
	private ObjectVersionID versionID;
}
