/*
 * component:   "openEHR Reference Implementation"
 * description: "Class PartyProxy"
 * keywords:    "common"
 *
 * author:      "Yin Su Lim <y.lim@chime.ucl.ac.uk>"
 * support:     "CHIME, UCL"
 * copyright:   "Copyright (c) 2006 UCL, UK"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/common/generic/PartyProxy.java $"
 * revision:    "$LastChangedRevision: 53 $"
 * last_change: "$LastChangedDate: 2006-08-11 13:20:08 +0100 (Fri, 11 Aug 2006) $"
 */
package org.openehr.rm.common.generic;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.openehr.rm.Attribute;
import org.openehr.rm.FullConstructor;
import org.openehr.rm.RMObject;
import org.openehr.rm.support.identification.PartyRef;

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
     * @param externalRef null if not specified
     * @throws IllegalArgumentException if name null or archetypeNodeId null
     *                                  or links not null and empty
     */
	@FullConstructor
	public PartyProxy(@Attribute(name = "externalRef") PartyRef externalRef) { // need this constructor public for reflection to work properly within the archetype validator
    		this.externalRef = externalRef;	
	}
  
    /**
     * Reference to more detailed demographic or identification info for this party
     * 
     * @return externalRef
     */
    public PartyRef getExternalRef() {
    		return externalRef;
    }
 
    /**
     * Equals if two actors has same values
     *
     * @param o
     * @return equals if true
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!( o instanceof PartyProxy)) {
            return false;
        }

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
    @Override
    public int hashCode() {
        return new HashCodeBuilder(3, 23)
                .append(externalRef)
                .toHashCode();
    }
    
    //POJO
    protected PartyProxy() {}
    
    void setExternalRef(PartyRef externalRef) {
            this.externalRef = externalRef;
    }    
    //POJO end
	
    /* fields */
    private PartyRef externalRef;

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
 *  The Original Code is PartyProxy.java
 *
 *  The Initial Developer of the Original Code is Yin Su Lim.
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