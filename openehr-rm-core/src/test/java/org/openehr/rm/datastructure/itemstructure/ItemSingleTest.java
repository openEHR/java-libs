/*
 * component:   "openEHR Reference Implementation"
 * description: "Class ItemSingleTest"
 * keywords:    "unit test"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/BRANCHES/RM-1.0-update/libraries/src/test/org/openehr/rm/datastructure/itemstructure/ItemSingleTest.java $"
 * revision:    "$LastChangedRevision: 50 $"
 * last_change: "$LastChangedDate: 2006-08-10 13:21:46 +0200 (Thu, 10 Aug 2006) $"
 */
/**
 * ItemSingleTest
 *
 * @author Rong Chen
 * @version 1.0 
 */
package org.openehr.rm.datastructure.itemstructure;

import org.openehr.rm.datastructure.DataStructureTestBase;
import org.openehr.rm.datastructure.itemstructure.representation.Element;

public class ItemSingleTest extends DataStructureTestBase {

    public ItemSingleTest(String test) {
        super(test);
    }

    /**
     * The fixture set up called before every test method.
     */
    protected void setUp() throws Exception {
        Element element = element("element name", "value");
        itemSingle = new ItemSingle(null, "at0001", text("item name"),
                null, null, null, null, element);
    }

    /**
     * The fixture clean up called after every test method.
     */
    protected void tearDown() throws Exception {
        itemSingle = null;
    }

    public void testValidPath() throws Exception {
        assertTrue(itemSingle.validPath(ItemStructure.PATH_SEPARATOR + "item name"));
        assertFalse(itemSingle.validPath(ItemStructure.PATH_SEPARATOR + "element name"));
    }

    public void testItemAtPath() throws Exception {
        assertEquals("whole", "element name", itemSingle.itemAtPath(
                ItemStructure.PATH_SEPARATOR + "item name").getName().getValue());
    }

    private ItemSingle itemSingle;
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
 *  The Original Code is ItemSingleTest.java
 *
 *  The Initial Developer of the Original Code is Rong Chen.
 *  Portions created by the Initial Developer are Copyright (C) 2003-2004
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