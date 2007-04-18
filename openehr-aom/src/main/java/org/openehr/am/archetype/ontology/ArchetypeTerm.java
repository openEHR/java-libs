/*
 * component:   "openEHR Reference Implementation"
 * description: "Class ArchetypeTerm"
 * keywords:    "archetype"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2007 ACODE HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL$"
 * revision:    "$LastChangedRevision$"
 * last_change: "$LastChangedDate$"
 */

package org.openehr.am.archetype.ontology;

import java.util.*;

import org.apache.commons.lang.StringUtils;

public class ArchetypeTerm {

	/**
	 * Creates an ArchetypeTerm 
	 * 
	 * @param code not null or empty
	 * @throws IllegalArgumentException if null or empty code
	 */
	public ArchetypeTerm(String code) {
		if (StringUtils.isEmpty(code)) {
			throw new IllegalArgumentException("null or empty code");
		}
		this.code = code;
		items = new LinkedHashMap<String, String>();
	}

	/**
	 * Code of this term
	 * 
	 * @return Returns the code.
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Hash of keys (text, description etc) and corresponding values.
	 * 
	 * @return Returns the items
	 */
	public Map<String, String> getItems() {
		return items;
	}

	/**
	 * Adds an item (key, value) to this term
     * 
     * @param key not null or empty
     * @param value
     */
	public void addItem(String key, String value) {
		if(StringUtils.isEmpty(key)) {
			throw new IllegalArgumentException("empty key");
		}
		items.put(key, value);
	}
	
	/**
	 * Gets item for given key
	 * 
	 * @param key
	 * @return null if not found
	 */
	public String getItem(String key) {
		return items.get(key);
	}

	private String code;

	private Map<String, String> items;
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
 *  The Original Code is ArchetypeTerm.java
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
