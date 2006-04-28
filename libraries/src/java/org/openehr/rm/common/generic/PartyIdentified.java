/*
 * component:   "openEHR Reference Implementation"
 * description: "Class PartyIdentified"
 * keywords:    "common"
 *
 * author:      "Yin Su Lim <y.lim@chime.ucl.ac.uk>"
 * support:     ""
 * copyright:   ""
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/common/generic/PartyIdentified.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 22:20:08 +0100 (Wed, 12 Oct 2005) $"
 */
package org.openehr.rm.common.generic;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.openehr.rm.datatypes.basic.DvIdentifier;
import org.openehr.rm.support.identification.PartyReference;

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
	public PartyIdentified(PartyReference externalRef, String name,
			List<DvIdentifier> identifiers) {
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
