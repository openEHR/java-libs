/*
 * component:   "openEHR Reference Implementation"
 * description: "Class CDomainType"
 * keywords:    "archetype"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/am/archetype/constraintmodel/CDomainType.java $"
 * revision:    "$LastChangedRevision: 43 $"
 * last_change: "$LastChangedDate: 2006-08-08 12:54:07 +0200 (Tue, 08 Aug 2006) $"
 */
package org.openehr.am.archetype.constraintmodel;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.openehr.rm.support.basic.Interval;
import org.openehr.rm.support.basic.MultiplicityInterval;

/**
 * Abstract parent type of domain-specific constrainer types, to be defined in
 * external packages.
 *
 * @author Rong Chen
 * @version 1.0
 */
public abstract class CDomainType<T> extends CObject {

	/**
	 * Constructs a DomainTypeConstraint without default value or assumed value
	 *
	 * @param path
	 * @param rmTypeName
	 * @param occurrences
	 * @param nodeID
	 */
	protected CDomainType(boolean anyAllowed, String path, String rmTypeName,
			MultiplicityInterval occurrences, String nodeID, CAttribute parent) {
		this(anyAllowed, path, rmTypeName, occurrences, nodeID, null, null,
				parent);
	}
	
	/**
	 * Constructs a DomainTypeConstraint without default value or assumed value
	 *
	 * @param path
	 * @param rmTypeName
	 * @param occurrences
	 * @param nodeID
	 * @param defaultValue
	 * @param assumedValue
	 */
	protected CDomainType(boolean anyAllowed, String path, String rmTypeName,
			MultiplicityInterval occurrences, String nodeID,		
			T defaultValue, T assumedValue, CAttribute parent) {
		
		super(anyAllowed, path, rmTypeName, occurrences, nodeID, parent);
		
		if(assumedValue != null && !validValue(assumedValue)) {
			throw new IllegalArgumentException("invalid assumedValue");
		}
		
		this.defaultValue = defaultValue;
		this.assumedValue = assumedValue;
	}
	
	/**
	 * Returns true if a_value is valid with respect to constraint expressed in 
	 * concrete instance of this type.
	 * 
	 * @param value
	 * @return
	 */
	public abstract boolean validValue(T value);

	/**
	 * Standard form of constraint
	 *
	 * @return Standard form of constraint
	 */
	public abstract CComplexObject standardEquivalent();
	
	/**
	 * Returns true if there is an assumed value
	 * 
	 * @return
	 */
	public boolean hasAssumedValue() {
		return assumedValue != null;
	}
	
	/**
	 * @return Returns the assumedValue.
	 */
	public T getAssumedValue() {
		return assumedValue;
	}

	/**
	 * @return Returns the defaultValue.
	 */
	public T getDefaultValue() {
		return defaultValue;
	}    
	
	/**
     * Returns true if fields are the same
     */
    public boolean equals(Object o) {
    	if (this == o) return true;
        if (!( o instanceof CDomainType )) return false;

        final CDomainType cdomain = (CDomainType) o;

        return new EqualsBuilder()
        		.appendSuper(super.equals(o))
                .append(assumedValue, cdomain.assumedValue)
                .append(defaultValue, cdomain.defaultValue)
                .isEquals();
    }
    
    /**
     * Returns the hashcode of this object
     * 
     * @return hashcode
     */
    public int hashCode() {
        return new HashCodeBuilder(7, 19)
        		.appendSuper(super.hashCode())
                .append(assumedValue)
                .append(defaultValue)
                .toHashCode();
    }
	
	private final T defaultValue;
	private final T assumedValue;
	
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
 *  The Original Code is CDomainType.java
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