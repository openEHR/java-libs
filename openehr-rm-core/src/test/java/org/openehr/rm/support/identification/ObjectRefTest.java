/*
 * component:   "openEHR Reference Implementation"
 * description: "Class ObjectReferenceTest"
 * keywords:    "unit test"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/BRANCHES/RM-1.0-update/libraries/src/test/org/openehr/rm/support/identification/ObjectReferenceTest.java $"
 * revision:    "$LastChangedRevision: 50 $"
 * last_change: "$LastChangedDate: 2006-08-10 13:21:46 +0200 (Thu, 10 Aug 2006) $"
 */

/**
 * ObjectReferenceTest
 *
 * @author Rong Chen
 * @version 1.0
 */
package org.openehr.rm.support.identification;

import junit.framework.TestCase;

public class ObjectRefTest extends TestCase {

	public ObjectRefTest(String test) {
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

	public void testConstructor() throws Exception {
		assertExceptionThrown(null, "LOCAL", "EHR", "id");

		assertExceptionThrown(hid("1.2.40.11.1.2.2::2"), null, "EHR", 
				"namespace");

		assertExceptionThrown(hid("1.2.40.11.1.2.2::2"), "LOCAL", null, "type");

		new ObjectRef(hid("openehr.org::23"), "LOCAL", "EHR");
	}

	private ObjectID hid(String value) {
		return new HierObjectID(value);
	}

	public void testEquals() throws Exception {
		ObjectRef or1 = new ObjectRef(hid("1-2-80-11-1"), "LOCAL", "EHR");
		ObjectRef or2 = new ObjectRef(hid("1-2-80-11-1"), "LOCAL", "EHR");
		assertTrue(or1.equals(or2));
		assertTrue(or2.equals(or1));

		ObjectRef or3 = new ObjectRef(hid("openehr.org::23"), "LOCAL", "EHR");
		assertFalse(or1.equals(or3));
		assertFalse(or3.equals(or1));

		or3 = new ObjectRef(hid("1-2-80-11-1"), "DEMOGRAPHIC", "EHR");
		assertFalse(or1.equals(or3));
		assertFalse(or3.equals(or1));

		or3 = new ObjectRef(hid("1-2-80-11-1"), "LOCAL", "PARTY");
		assertFalse(or1.equals(or3));
		assertFalse(or3.equals(or1));
	}

	private void assertExceptionThrown(ObjectID id, String namespace,
			String type, String cause) {
		try {
			new ObjectRef(id, namespace, type);
			fail("exception should be thrown");
		} catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
			assertTrue(e.getMessage().contains(cause));
		}

	}
}

/*
 * ***** BEGIN LICENSE BLOCK ***** Version: MPL 1.1/GPL 2.0/LGPL 2.1
 * 
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the 'License'); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at http://www.mozilla.org/MPL/
 * 
 * Software distributed under the License is distributed on an 'AS IS' basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 * 
 * The Original Code is ObjectReferenceTest.java
 * 
 * The Initial Developer of the Original Code is Rong Chen. Portions created by
 * the Initial Developer are Copyright (C) 2003-2004 the Initial Developer. All
 * Rights Reserved.
 * 
 * Contributor(s):
 * 
 * Software distributed under the License is distributed on an 'AS IS' basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 * 
 * ***** END LICENSE BLOCK *****
 */