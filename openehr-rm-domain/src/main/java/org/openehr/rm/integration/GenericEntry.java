/*
 * component:   "openEHR Reference Implementation"
 * description: "Class ContentItem"
 * keywords:    "unit test"
 *
 * author:      "Rong Chen <rong.acode@gmail.com>"
 * support:     "openEHR Java project, http://www.openehr.org/projects/java.html"
 * copyright:   "Copyright (c) 2008 Cambio Healthcare Systems, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: $"
 * revision:    "$LastChangedRevision: $"
 * last_change: "$LastChangedDate: $"
 */
package org.openehr.rm.integration;

import java.util.List;
import java.util.Set;

import org.openehr.rm.Attribute;
import org.openehr.rm.FullConstructor;
import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.common.archetyped.FeederAudit;
import org.openehr.rm.common.archetyped.Link;
import org.openehr.rm.common.archetyped.Locatable;
import org.openehr.rm.common.archetyped.Pathable;
import org.openehr.rm.composition.content.ContentItem;
import org.openehr.rm.datastructure.itemstructure.ItemTree;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.support.identification.UIDBasedID;

/**
 * This class is used to create intermediate representations of data from 
 * sources not otherwise conforming to openEHR classes, such as HL7 messages, 
 * relational databases and so on.
 */
public final class GenericEntry extends ContentItem {

	/**
     * Constructs a GenericEntry
     *
     * @param uid
     * @param archetypeNodeId
     * @param name
     * @param archetypeDetails
     * @param feederAudit
     * @param links
     * @param data
     */
	@FullConstructor
    public GenericEntry(
    		@Attribute(name = "uid") UIDBasedID uid, 
    		@Attribute(name = "archetypeNodeId", required = true) String archetypeNodeId, 
    		@Attribute(name = "name", required = true) DvText name,
    		@Attribute(name = "archetypeDetails", required = true) Archetyped archetypeDetails,
    		@Attribute(name = "feederAudit") FeederAudit feederAudit,
    		@Attribute(name = "links") Set<Link> links, 
    		@Attribute(name = "parent") Pathable parent,
    		@Attribute(name = "data", required = true) ItemTree data) {
        super(uid, archetypeNodeId, name, archetypeDetails, feederAudit, links, 
        		parent);
        this.data = data;
    }
	
	/**
	 * Constructs a GenericEntry with only required fields 
	 * 
	 * @param archetypeNodeId
	 * @param name
	 * @param data
	 */
	public GenericEntry(String archetypeNodeId, DvText name, ItemTree data) {
		this(null, archetypeNodeId, name, null, null, null, null, data);
	}
	
	/**
	 * Convenient constructor to create a GenericEntry with only required fields 
	 * 
	 * @param archetypeNodeId
	 * @param name	in string
	 * @param data
	 */
	public GenericEntry(String archetypeNodeId, String name, ItemTree data) {
		this(archetypeNodeId, new DvText(name), data);
	}
	
	/**
	 * The data from the source message or record.
	 * 
	 * @return data
	 */
	public ItemTree getData() {
		return data;
	}

	@Override
	public String pathOfItem(Pathable item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> itemsAtPath(String path) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean pathExists(String path) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pathUnique(String path) {
		// TODO Auto-generated method stub
		return false;
	}

	private final ItemTree data;
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
 *  The Original Code is GenericEntry.java
 *
 *  The Initial Developer of the Original Code is Rong Chen.
 *  Portions created by the Initial Developer are Copyright (C) 2008
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