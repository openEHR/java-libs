/*
 * component:   "openEHR Reference Implementation"
 * description: "Class CBooleanTest"
 * keywords:    "archetype"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL$"
 * revision:    "$LastChangedRevision$"
 * last_change: "$LastChangedDate$"
 */
/**
 * CBooleanTest
 *
 * @author Rong Chen
 * @version 1.0 
 */
package org.openehr.am.archetype.constraintmodel.primitive;

import junit.framework.TestCase;
import org.openehr.rm.datatypes.basic.DvBoolean;

public class CBooleanTest extends TestCase {

	public CBooleanTest(String test) {
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

	public void testValidValue() throws Exception {
		CBoolean cb = new CBoolean(true, true);
		assertTrue(cb.validValue(Boolean.TRUE));
		assertTrue(cb.validValue(Boolean.FALSE));

		cb = new CBoolean(true, false);
		assertTrue(cb.validValue(Boolean.TRUE));
		assertTrue(!cb.validValue(Boolean.FALSE));

		cb = new CBoolean(false, true);
		assertTrue(!cb.validValue(Boolean.TRUE));
		assertTrue(cb.validValue(Boolean.FALSE));
	}

	public void testAssignedValue() throws Exception {
		CBoolean cb = new CBoolean(true, true);
		assertFalse(cb.hasAssignedValue());
		assertTrue(cb.assignedValue() == null);

		cb = new CBoolean(true, false);
		assertTrue(cb.hasAssignedValue());
		assertEquals(DvBoolean.TRUE, cb.assignedValue());

		cb = new CBoolean(false, true);
		assertTrue(cb.hasAssignedValue());
		assertEquals(DvBoolean.FALSE, cb.assignedValue());
	}

	public void testConstructorWithoutAssumedValue() throws Exception {
		CBoolean cb = new CBoolean(true, true);
		assertFalse("hasAssumedValue() should be false", cb.hasAssumedValue());
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
 * The Original Code is CBooleanTest.java
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