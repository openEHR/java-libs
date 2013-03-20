/*
 * component:   "openEHR Reference Implementation"
 * description: "Class Settable"
 * keywords:    "common"
 *
 * author:      "Rong Chen <rong.acode@gmail.com>"
 * copyright:   "Copyright (c) 2010 Cambio Healthcare Systems, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL$"
 * revision:    "$LastChangedRevision$"
 * last_change: "$LastChangedDate$"
 */
package org.openehr.rm.common.archetyped;

/**
 * Interface for setting values in reference model classes based on paths
 * 
 * @author rong.chen
 */
public interface Settable {
	
	/**
	 * Sets value based on path
	 * 
	 * @param path the path to reach the RM instance relatively from this object
	 * @param value the value to set
	 */
	public void set(String path, Object value);
	
	/**
	 * Removes a child identified by the path from a container attribute
	 * 
	 * @param path
	 * @exception IllegalArgumentException if the parent attribute is not a container attribute
	 */
	public void removeChild(String path);
	
	/**
	 * Adds a child to a container attributed identified by the path
	 * 
	 * @param path the path to locate the container
	 * @param child the child object to add to the container
	 * @exception IllegalArgumentException if the parent attribute is not a container attribute
	 */
	public void addChild(String path, Object child);
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
 *  The Original Code is Pathable.java
 *
 *  The Initial Developer of the Original Code is Rong Chen.
 *  Portions created by the Initial Developer are Copyright (C) 2010
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