/*
 * component:   "openEHR Reference Implementation"
 * description: "Class CDvState"
 * keywords:    "openehr archetype profile"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2006 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL$"
 * revision:    "$LastChangedRevision$"
 * last_change: "$LastChangedDate$"
 */

package org.openehr.am.openehrprofile.datatypes.basic;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.openehr.am.archetype.constraintmodel.ArchetypeConstraint;
import org.openehr.am.archetype.constraintmodel.CAttribute;
import org.openehr.am.archetype.constraintmodel.CComplexObject;
import org.openehr.am.archetype.constraintmodel.CDomainType;
import org.openehr.rm.datatypes.basic.DvState;
import org.openehr.rm.support.basic.Interval;

/**
 * Constraint type for DV_STATE instances. The attribute c_value defines a
 * state/event table which constrains the allowed values of the attribute 
 * value in a DV_STATE instance, as well as the order of transitions between
 * values.
 * 
 * @author Rong Chen
 * @version 1.0
 */
public class CDvState extends CDomainType<DvState> {

	/**
	 * Creates a CDvState with path and stateMachine 
	 * 
	 * @param path
	 * @param value not null
	 * @throws IllegalArgumentException if value null
	 */
	public CDvState(String path, Interval<Integer> occurrences, String nodeId,
            CAttribute parent, StateMachine value) {
		
		super(false, path, "DV_STATE", occurrences, nodeId, parent);
		
		if(value == null) {
			throw new IllegalArgumentException("value null");
		}
		this.value = value;
	}
	
	@Override
    public CDvState copy() {
		return new CDvState(path(), getOccurrences(), getNodeId(), getParent(), 
				value);
	}
	
	/**
     * Equals if two CObject has same values
     *
     * @param o
     * @return true if equals
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!( o instanceof CDvState )) {
            return false;
        }

        final CDvState cobj = (CDvState) o;

        return new EqualsBuilder()
        		.appendSuper(super.equals(o))
                .append(value, cobj.value)
                .isEquals();
    }

    /**
     * Return a hash code of this object
     *
     * @return hash code
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 31)
        		.appendSuper(super.hashCode())
                .append(value)
                .toHashCode();
    }
	
	/**
	 * Gets value
	 * 
	 * @return
	 */
	public StateMachine getValue() {
		return value;
	}

	@Override
	public CComplexObject standardEquivalent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isValid() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasPath(String path) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSubsetOf(ArchetypeConstraint constraint) {
		// TODO Auto-generated method stub
		return false;
	}
	
	private StateMachine value;

	@Override
	public boolean validValue(DvState arg0) {
		// TODO Auto-generated method stub
		return false;
	}	
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
 *  The Original Code is CDvState.java
 *
 *  The Initial Developer of the Original Code is Rong Chen.
 *  Portions created by the Initial Developer are Copyright (C) 2003-2006
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

