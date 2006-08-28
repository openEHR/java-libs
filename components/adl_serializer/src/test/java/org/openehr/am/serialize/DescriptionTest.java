/*
 * component:   "openEHR Reference Implementation"
 * description: "Class DescriptionTest"
 * keywords:    "archetype"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004,2005,2006 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL:$"
 * revision:    "$LastChangedRevision: $"
 * last_change: "$LastChangedDate: $"
 */
package org.openehr.am.serialize;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openehr.rm.common.resource.ArchetypeDescription;
import org.openehr.rm.common.resource.ArchetypeDescriptionItem;

public class DescriptionTest extends SerializerTestBase {

	public DescriptionTest(String test) {
		super(test);
	}

	public void testPrintDescriptionItem() throws Exception {
		String purpose = "purpose";
		String use = "use";
		String misuse = "misuse";
		String copyright = "copyright";
		List<String> keywords = new ArrayList<String>();
		keywords.add("apple");
		keywords.add("pear");
		List<String> urls = new ArrayList<String>();
		urls.add("original_res_url");
		Map<String, String> others = new HashMap<String, String>();

		ArchetypeDescriptionItem item = new ArchetypeDescriptionItem(ENGLISH,
				purpose, use, misuse, copyright, keywords, urls, others);

		clean();
		outputter.printDescriptionItem(item, 0, out);

		verify("[\"en\"] = <\r\n" + "    language = <\"en\">\r\n"
				+ "    purpose = <\"purpose\">\r\n"
				+ "    keywords = <\"apple\",\"pear\">\r\n"
				+ "    copyright = <\"copyright\">\r\n"
				+ "    use = <\"use\">\r\n" + "    misuse = <\"misuse\">\r\n"
				+ "    original_resource_uri = <\"original_res_url\">\r\n"
				+ ">\r\n");
	}

	public void testPrintDescription() throws Exception {
		String author = "Jerry Mouse";
		String status = "draft";
		Map<String, String> authorMap = new HashMap<String, String>();
		authorMap.put("name", author);

		List<ArchetypeDescriptionItem> items = new ArrayList<ArchetypeDescriptionItem>();
		String[][] others = { { "revision", "1.1" }, { "adl_version", "0.9" },
				{ "rights", "all rights reserved" } };
		Map<String, String> otherDetails = new HashMap<String, String>();
		for (String[] pair : others) {
			otherDetails.put(pair[0], pair[1]);
		}
		ArchetypeDescriptionItem item = new ArchetypeDescriptionItem(ENGLISH,
				"purpose of this archetype");
		items.add(item);
		ArchetypeDescription description = new ArchetypeDescription(authorMap,
				null, status, items, null, null, null);

		clean();
		outputter.printDescription(description, out);

		verify("description\r\n" + "    original_author = <\r\n"
				+ "        [\"name\"] = <\"" + author + "\">\r\n" + "    >\r\n"
				+ "    lifecycle_state = <\"" + status + "\">\r\n"
				+ "    details = <\r\n" + "        [\"en\"] = <\r\n"
				+ "            language = <\"en\">\r\n"
				+ "            purpose = <\"purpose of this archetype\">\r\n"
				+ "        >\r\n" + "    >\r\n");
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
 *  The Original Code is DescriptionTest.java
 *
 *  The Initial Developer of the Original Code is Rong Chen.
 *  Portions created by the Initial Developer are Copyright (C) 2004-2006
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
