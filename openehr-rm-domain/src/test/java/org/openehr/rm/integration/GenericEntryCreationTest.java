/*
 * component:   "openEHR Reference Implementation"
 * description: "Class GenericEntryCreationTest"
 * keywords:    "unit test"
 *
 * author:      "Rong Chen <rong.acode@gmail.com>"
 * support:     "openEHR Java project, http://www.openehr.org/projects/java.html"
 * copyright:   "Copyright (c) 2008 Cambio Healthcare Systems, AB"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: $"
 * revision:    "$LastChangedRevision: $"
 * last_change: "$LastChangedDate: $"
 */
package org.openehr.rm.integration;

import java.util.*;

import org.openehr.rm.datastructure.itemstructure.ItemTree;
import org.openehr.rm.datastructure.itemstructure.representation.*;
import org.openehr.rm.datatypes.quantity.DvQuantity;

import junit.framework.TestCase;

public class GenericEntryCreationTest extends TestCase {
	
	public void testCreateGenericEntry() {
		String archetypeNodeId = "at0001";
		String name = "test generic entry";
		Element element = new Element("at0002", "te",	new DvQuantity(12.0));
		List<Item> items = new ArrayList<Item>();
		items.add(element);
		ItemTree itemTree = new ItemTree("at0003", "tree", items);
		
		GenericEntry entry = new GenericEntry(archetypeNodeId, name, itemTree);
		assertNotNull("created genericEntry null", entry);
		assertEquals("itemTree wrong", itemTree, entry.getData());
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
 *  The Original Code is GenericEntryCreationTest.java
 *
 *  The Initial Developer of the Original Code is Rong Chen.
 *  Portions created by the Initial Developer are Copyright (C) 2003-2008
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