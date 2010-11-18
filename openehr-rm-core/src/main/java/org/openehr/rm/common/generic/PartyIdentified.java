/*
 * component:   "openEHR Reference Implementation"
 * description: "Class PartyIdentified"
 * keywords:    "common"
 *
 * author:      "Yin Su Lim <y.lim@chime.ucl.ac.uk>"
 * support:     "CHIME, UCL"
 * copyright:   "Copyright (c) 2006 UCL, UK"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/common/generic/PartyIdentified.java $"
 * revision:    "$LastChangedRevision: 53 $"
 * last_change: "$LastChangedDate: 2006-08-11 13:20:08 +0100 (Fri, 11 Aug 2006) $"
 */
package org.openehr.rm.common.generic;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.openehr.rm.Attribute;
import org.openehr.rm.FullConstructor;
import org.openehr.rm.datatypes.basic.DvIdentifier;
import org.openehr.rm.support.identification.PartyRef;

/**
* Represent proxy data for an identified party.
*
* @author Yin Su Lim
* @version 1.0
*/
public class PartyIdentified extends PartyProxy {

	/**
	 * Constructs a PartyIdentified
	 * 
	 *@param externalRef
	 *@param name
	 *@param identifiers
	 *@throws IllegalArgumentException if identifiers is empty
	 */
	@FullConstructor    
	public PartyIdentified(
			@Attribute(name = "externalRef")PartyRef externalRef, 
			@Attribute(name = "name")String name,
			@Attribute(name = "identifiers")List<DvIdentifier> identifiers) {
		super(externalRef);
		if(externalRef == null && name == null && identifiers == null) {
			throw new IllegalArgumentException("externalRef, name, identifiers all empty");
		}
		if(name != null && StringUtils.isEmpty(name)) {
			throw new IllegalArgumentException("empty name");
		}
		if(identifiers != null && identifiers.size() == 0) {
			throw new IllegalArgumentException("empty identifiers");
		}
		this.name = name;
		this.identifiers = identifiers;
	}
	
	/**
	 * Created a PartyIdentified by given name
	 * 
	 * @param name
	 * @return
	 */
	public static PartyIdentified named(String name) {
		return new PartyIdentified(null, name, null);
	}

	/**
	 * Human-readable name
	 * 
	 * @return name null if not present
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * One or more formal identifiers
	 * 
	 * @return identifiers null if not present
	 */
	public List<DvIdentifier> getIdentifiers() {
		return identifiers;
	}
	
    /**
     * Equals if two actors has same values
     *
     * @param o
     * @return equals if true
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!( o instanceof PartyIdentified )) return false;
        if (!super.equals(o)) return false;

        final PartyIdentified pi = (PartyIdentified) o;
        return new EqualsBuilder()
                .append(name, pi.name)
                .append(identifiers, pi.identifiers)
                .isEquals();
    }

    /**
     * Return a hash code of this actor
     *
     * @return hash code
     */
    public int hashCode() {
        return new HashCodeBuilder(17, 83)
                .appendSuper(super.hashCode())
                .append(name)
                .append(identifiers)
                .toHashCode();
    }
    
    //POJO start
    PartyIdentified() {}

	void setIdentifiers(List<DvIdentifier> identifiers) {
		this.identifiers = identifiers;
	}

	void setName(String name) {
		this.name = name;
	}
	//POJO end
    
	/* fields */
	private String name;
	private List<DvIdentifier> identifiers;

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
 *  The Original Code is PartyIdentified.java
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