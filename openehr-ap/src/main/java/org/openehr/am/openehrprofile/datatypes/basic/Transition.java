/*
 * component:   "openEHR Reference Implementation"
 * description: "Class Transition"
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

import org.apache.commons.lang.StringUtils;

/**
 * Definition of a state machine transition.
 * 
 * @author Rong Chen
 * @version 1.0
 */
public class Transition {

	/**
	 * Creates a Transition
	 * 
	 * @param event not null or empty
	 * @param guard null if unspecified, not empty
	 * @param action null if unspecified, not empty
	 * @param nextState not null
	 * @throws IllegalArgumentException if event null or empty,
	 *             or guard empty, or action emtpy, or nextState null
	 */
	public Transition(String event, String guard, String action, 
			State nextState) {
		if(StringUtils.isEmpty(event)) {
			throw new IllegalArgumentException("event null or empty");
		}
		if(guard != null && StringUtils.isEmpty(guard)) {
			throw new IllegalArgumentException("guard empty");
		}
		if(action != null && StringUtils.isEmpty(action)) {
			throw new IllegalArgumentException("action empty");
		}
		if(nextState == null) {
			throw new IllegalArgumentException("nextState null");
		}
		this.event = event;
		this.guard = guard;
		this.action = action;
		this.nextState = nextState;
	}
	
	/**
	 * Creates a Transition with event and nextState
	 * 
	 */
	public Transition(String event, State nextState) {
		this(event, null, null, nextState);
	}

	/**
	 * Side-effect action to execute during the firing
	 * of this transition
	 * 
	 * @return action
	 */
	public String getAction() {
		return action;
	}

	/**
	 * Event which fires this transition
	 * 
	 * @return event
	 */
	public String getEvent() {
		return event;
	}

	/**
	 * Guard condition which must be true for this
	 * transition to fire
	 * 
	 * @return guard
	 */
	public String getGuard() {
		return guard;
	}

	/**
	 * Target state of transition
	 * 
	 * @return next state
	 */
	public State getNextState() {
		return nextState;
	}

	private String event;
	private String guard;
	private String action;
	private State nextState;
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
 *  The Original Code is Transition.java
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

