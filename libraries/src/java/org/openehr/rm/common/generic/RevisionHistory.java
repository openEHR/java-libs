/*
 * component:   "openEHR Reference Implementation"
 * description: "Class RevisionHistory"
 * keywords:    "common"
 *
 * author:      "Yin Su Lim <y.lim@chime.ucl.ac.uk>"
 * support:     "CHIME, UCL"
 * copyright:   ""
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/common/generic/RevisionHistory.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2006-03-08 22:20:08 +0100 (Wed, 12 Oct 2005) $"
 */
package org.openehr.rm.common.generic;

import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.openehr.rm.RMObject;

/**
 * The notion of a revision history of audit items, each associated with the version
 * for which which that audit was committed. Instances of this class are
 * immutable
 *
 * @author Yin Su Lim
 * @version 1.0
 */
public final class RevisionHistory extends RMObject {

	/**
     * Constructs a RevisionHistory
     *
     * @param items	not null
     * @throws IllegalArgumentException if items null or empty
     */
    public RevisionHistory(List<RevisionHistoryItem> items) {
        if (items == null || items.size() == 0) {
            throw new IllegalArgumentException("empty items");
        }
        this.items = items;
    }
    
    /**
     * All the history items in this history. The list
     * is in most-recent-first order
     * 
     * @param items
     */
    public List<RevisionHistoryItem> getItems() {
    		return items;
    }
  
    /**
     * The version id of the most recent item, as a String
     * 
     * @return versionID
     */
    public String mostRecentVersionId() {
    		return items.get(items.size()-1).getVersionID().getValue();
    }
    
    /**
     * The commit date/time of the most recent item, as a String
     * 
     * @return version time
     */
    public String mostRecentVersionTime() {
    		RevisionHistoryItem lastItem = items.get(items.size()-1);
    		int auditSize = lastItem.getAudits().size();
    		return lastItem.getAudits().get(auditSize - 1).getTimeCommitted().toString();
    }
    
    /**
     * Equals if have same values
     *
     * @param o
     * @return true if equals
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!( o instanceof RevisionHistory )) return false;

        final RevisionHistory rh = (RevisionHistory) o;

        return new EqualsBuilder()
                .append(items, rh.items)
                .isEquals();
    }

    /**
     * Hashcode of this object
     *
     * @return hashcode
     */
    public int hashCode() {
        return new HashCodeBuilder(13, 41)
                .append(items)
                .toHashCode();
    }
    
    //POJO start
    RevisionHistory() {
    }
    
    void setItems(List<RevisionHistoryItem> items) {
    		this.items = items;
    }
    //POJO end
    
    /* fields */
    private List<RevisionHistoryItem> items;
}
