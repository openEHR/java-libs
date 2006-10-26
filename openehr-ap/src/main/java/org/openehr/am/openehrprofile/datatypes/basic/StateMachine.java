/*
 * component:   "openEHR Reference Implementation"
 * description: "Class StateMachine"
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

import java.util.Set;
import java.util.HashSet;
import java.util.Collections;

/**
 * Definition of a state machine in terms of states, transition events and
 * outputs, and next states.
 * 
 * @author Rong Chen
 * @version 1.0
 */
public class StateMachine {
	
	/**
	 * Creates a StateMachine by a set of states
	 * 
	 * @param states not null or empty
	 * @throws IlleglArgumentException if states null or empty
	 */
	public StateMachine(Set<State> states) {
		if(states == null || states.isEmpty()) {
			throw new IllegalArgumentException("states null or empty");
		}
		this.states = new HashSet<State>(states);
	}
	
	/**
	 * Gets states of this StateMachine
	 * 
	 * @return an unmodifiable set of states
	 */
	public Set<State> getStates() {
		return Collections.unmodifiableSet(states);
	}
	
	private Set<State> states;
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
 *  The Original Code is StateMachine.java
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

