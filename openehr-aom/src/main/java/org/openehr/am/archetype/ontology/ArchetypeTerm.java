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
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class ArchetypeTerm {

	/**
	 * Constant added for convenience for commonly occuring key value in
	 * the item map. Actual string content is "text" (without quotes).
	 */
	public final static String TEXT = "text";

	/**
	 * Constant added for convenience for commonly occuring key value in
	 * the item map. Actual string content is "description" (without quotes).
	 */
	public final static String DESCRIPTION = "description";

	
	/**
	 * Creates an ArchetypeTerm 
	 * 
	 * @param code not null or empty (atNNNN or acNNNN codes depening on usage context) 
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
	 * Convenience constructor that calls the other constructor [ArchetypeTerm(String code)]
	 * and then adds two items to the hashmap using the keys described in parameters below.
	 * @param code not null or empty (atNNNN or acNNNN codes depending on usage context)
	 * @param text the String that will be stored in the item map under the key "text"
	 * @param description the String that will be stored in the item map under the key "description"
	 */
	public ArchetypeTerm(String code, String text, String description){
		this(code);
		addItem(TEXT, text);
		addItem(DESCRIPTION, description);
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

	/** 
	 * Convenience method to fetch the text of the archetype term.
	 *  
	 * @return the text of the archetype term
	 */ 
	public String getText() {
	   return this.getItem(ArchetypeTerm.TEXT);
	}
	 
	/** Convenience method to fetch the description of the archetype term.
	 *  
	 *  @return the description of the archetype term
	 */ 
	public String getDescription() {
	   return this.getItem(ArchetypeTerm.DESCRIPTION);
	}
	
	/**
     * Equals if two has have same values
     *
     * @param o
     * @return true if equals
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!( o instanceof ArchetypeTerm )) return false;

        final ArchetypeTerm at = (ArchetypeTerm) o;

        return new EqualsBuilder()
                .append(code, at.code)
                .append(items, at.items)
                .isEquals();
    }

    /**
     * Return a hash code of this object
     *
     * @return hash code
     */
    public int hashCode() {
        return new HashCodeBuilder(7, 47)
                .append(code)
                .append(items)
                .toHashCode();
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
 *  Portions created by the Initial Developer are Copyright (C) 2003-2008
 *  the Initial Developer. All Rights Reserved.
 *
 *  Contributor(s): Erik Sundvall
 *
 * Software distributed under the License is distributed on an 'AS IS' basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 *  ***** END LICENSE BLOCK *****
 */
