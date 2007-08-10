/*
 * component:   "openEHR Reference Implementation"
 * description: "Class UIDBasedID"
 * keywords:    "support"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * copyright:   "Copyright (c) 2007 Cambio Healthcare Systems, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL$"
 * revision:    "$LastChangedRevision$"
 * last_change: "$LastChangedDate$"
 */
package org.openehr.rm.support.identification;

import org.apache.commons.lang.StringUtils;

/**
 * Abstract model of UID-based identifiers consisting of a root part and 
 * an optional extension; lexical form: root "::" extension
 * 
 * @author Rong Chen
 */
public abstract class UIDBasedID extends ObjectID {
	
	/**
	 * Creates a UIDBasedID
	 * 
	 * @param value
	 */
	public UIDBasedID(String value) {
		super(value);
	}
	
	protected UIDBasedID() {}
	
	/**
	 * The identifier of the conceptual namespace in which the object exists,
	 * within the identification scheme. 
	 * 
	 * @returns the part to the left of the first ‘::’ separator, if any, 
	 * or else the whole string
	 */
	public abstract UID root(); 
	
	/**
	 * Optional local identifier of the object within the context of the root
	 * identifier. 
	 * 
	 * @returns the part to the right of the first ‘::’ separator if any, 
	 * or else any empty String
	 */
	public abstract String extension();
	
	/**
	 * True if extension is not empty
	 * 
	 * @return true if extension not empty
	 */
	public boolean hasExtension() {
		return !StringUtils.isEmpty(extension());
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
 *  The Original Code is UIDBasedID.java
 *
 *  The Initial Developer of the Original Code is Rong Chen.
 *  Portions created by the Initial Developer are Copyright (C) 2003-2007
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