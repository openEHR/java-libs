/*
 * component:   "openEHR Reference Implementation"
 * description: "Class PartyProxy"
 * keywords:    "common"
 *
 * author:      "Yin Su Lim <y.lim@chime.ucl.ac.uk>"
 * support:     ""
 * copyright:   ""
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/common/generic/PartyProxy.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 22:20:08 +0100 (Wed, 12 Oct 2005) $"
 */
package org.openehr.rm.common.generic;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.openehr.rm.RMObject;
import org.openehr.rm.support.identification.PartyReference;

/**
* Abstract concept of a proxy description of a party
*
* @author Yin Su Lim
* @version 1.0
*/
public abstract class PartyProxy extends RMObject {

   /**
     * Constructs a PartyProxy
     *
     * @param uid              null if not specified
     * @throws IllegalArgumentException if name null or archetypeNodeId null
     *                                  or links not null and empty
     */
    protected PartyProxy(PartyReference externalRef) {
    		this.externalRef = externalRef;	
	}
  
    /**
     * Reference to more detailed demographic or identification info for this party
     * 
     * @return externalRef
     */
    public PartyReference getExternalRef() {
    		return externalRef;
    }
 
    /**
     * Equals if two actors has same values
     *
     * @param o
     * @return equals if true
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!( o instanceof PartyProxy)) return false;

        final PartyProxy pp = (PartyProxy) o;
        return new EqualsBuilder()
                .append(externalRef, pp.externalRef)
                .isEquals();
    }

    /**
     * Return a hash code of this actor
     *
     * @return hash code
     */
    public int hashCode() {
        return new HashCodeBuilder(3, 23)
                .append(externalRef)
                .toHashCode();
    }
    
    //POJO
    protected PartyProxy() {}
    
    void setExternalRef(PartyReference externalRef) {
            this.externalRef = externalRef;
    }    
    //POJO end
	
    /* fields */
    private PartyReference externalRef;

}
