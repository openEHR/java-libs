/*
 * component:   "openEHR Reference Implementation"
 * description: "Class CAttributeTest"
 * keywords:    "archetype"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/test/org/openehr/am/archetype/constraintmodel/CAttributeTest.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 23:20:08 +0200 (Wed, 12 Oct 2005) $"
 */
/**
 * CAttributeTest
 *
 * @author Rong Chen
 * @version 1.0 
 */
package org.openehr.am.archetype.constraintmodel;

import junit.framework.TestCase;

public class CAttributeTest extends TestCase {

	public CAttributeTest(String test) {
		super(test);
	}

	/**
	 * The fixture set up called before every test method.
	 */
	protected void setUp() throws Exception {
	}

	/**
	 * The fixture clean up called after every test method.
	 */
	protected void tearDown() throws Exception {
	}

	public void testParentNodePath() throws Exception {
		CAttribute cattr = new TestCAttribute(
				"/[at0001]/content[at0003]/items", "items");

		assertEquals("/[at0001]/content[at0003]", cattr.parentNodePath());
	}

	public void testChildNodePathBase() throws Exception {
		CAttribute cattr = new TestCAttribute(
				"/[at0001]/content[at0003]/items", "items");
		assertEquals("/[at0001]/content[at0003]/items", 
				cattr.childNodePathBase());
	}

	private static final class TestCAttribute extends CAttribute {

		public TestCAttribute(String path, String rmAttributeName) {
			super(path, rmAttributeName, Existence.OPTIONAL, null);
		}

		/**
		 * True if this node is a valid archetype node.
		 *
		 * @return ture if valid
		 */
		public boolean isValid() {
			return false;
		}

		/**
		 * True if the relative path exists at this node.
		 *
		 * @param path
		 * @return ture if has
		 * @throws IllegalArgumentException if path null
		 */
		public boolean hasPath(String path) {
			return false;
		}		

		/**
		 * True if constraints represented by other are narrower than this node.
		 *
		 * @param constraint
		 * @return true if subset
		 * @throws IllegalArgumentException if constraint null
		 */
		public boolean isSubsetOf(ArchetypeConstraint constraint) {
			return false;
		}
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
 *  The Original Code is CAttributeTest.java
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