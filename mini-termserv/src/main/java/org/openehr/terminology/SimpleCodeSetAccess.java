/*
 * component:   "openEHR Reference Implementation"
 * description: "Class SimpleCodeSetAccess"
 * keywords:    "terminology"
 *
 * author:      "Rong Chen <rong.acode@gmail.com>"
 * copyright:   "Copyright (c) 2007 Rong Chen"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL$"
 * revision:    "$LastChangedRevision$"
 * last_change: "$LastChangedDate$"
 */
package org.openehr.terminology;

import java.util.*;

import org.apache.commons.lang.StringUtils;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.support.terminology.CodeSetAccess;

/**
 * A simple in-memory implementation of CodeSetAccess
 * 
 * @author Rong Chen
 */
public class SimpleCodeSetAccess implements CodeSetAccess {

	/**
	 * External identifier of this code set
	 */
	public String id() {
		return id;
	}

	/**
	 * Gets all codes known to this code set
	 * 
	 * @return unmodifiable view of all codes
	 */
	public Set<CodePhrase> allCodes() {
		return allCodes;
	}

	/**
	 * Returns true if has this code
	 * 
	 * @throws IllegalArgumentException if code null
	 */
	public boolean hasCode(CodePhrase code) {
		if(code == null) {
			throw new IllegalArgumentException("code null");
		}
		return allCodes.contains(code);
	}

	// TODO: seems to be impossible to implement
	//       code sets are language _independent_ by definition
	public boolean hasLang(CodePhrase lang) {
		return false;
	}
	
	/**
	 * Creates a simple code set
	 * 
	 * @param id	not null or empty
	 * @param codes not null or empty
	 * @throws IllegalArgumentException if id or codes empty
	 */
	SimpleCodeSetAccess(String id, Set<String> codes) {
		if(StringUtils.isEmpty(id)) {
			throw new IllegalArgumentException("null or empty id");
		}
		if(codes == null || codes.isEmpty()) {
			throw new IllegalArgumentException("null or empty codes");
		}
		this.id = id;
		this.allCodes = new HashSet<CodePhrase>();
		for(String code : codes) {
			CodePhrase cp = new CodePhrase(id, code);
			this.allCodes.add(cp);
		}
	}
	
	/* fields */
	private final String id;
	private final Set<CodePhrase> allCodes;
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
 *  The Original Code is SimpleCodeSetAccess.java
 *
 *  The Initial Developer of the Original Code is Rong Chen.
 *  Portions created by the Initial Developer are Copyright (C) 2007
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