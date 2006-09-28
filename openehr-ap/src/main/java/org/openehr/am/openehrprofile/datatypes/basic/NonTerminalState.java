/*
 * component:   "openEHR Reference Implementation"
 * description: "Class NonTerminalState"
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

import java.util.*;

public class NonTerminalState extends State {
	
	/**
	 * Creates a NonTerminalState by name and transitions
	 * 
	 * @param name not null or empty
	 * @param transitions not null or empty
	 * @throws IllegalArgumentException if name null or empty,
	 *                     or transitions null or empty
	 */
	public NonTerminalState(String name, Set<Transition> transitions) {
		super(name);
		if(transitions == null || transitions.isEmpty()) {
			throw new IllegalArgumentException("transitions null or empty");
		}
		this.transitions = new HashSet<Transition>(transitions);
	}
	
	/**
	 * Gets transitions of this state
	 * 
	 * @return an unmodifiable set of transitions
	 */
	public Set<Transition> getTransitions() {
		return Collections.unmodifiableSet(transitions);
	}
	
	/**
	 * Adds a transition to this state
	 * 
	 * @param transition not null
	 * @throws IllegalArgumentException if transition null
	 */
	public void addTransition(Transition transition) {
		if(transition == null) {
			throw new IllegalArgumentException("transition null");
		}
		transitions.add(transition);
	}
	
	private Set<Transition> transitions;
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
 *  The Original Code is NonTerminalState.java
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