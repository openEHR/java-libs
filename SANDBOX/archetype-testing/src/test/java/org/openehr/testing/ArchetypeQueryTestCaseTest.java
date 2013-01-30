/*
 * component:   "openEHR Java Reference Implementation"
 * description: "Class ArchetypeTestCase"
 * keywords:    "archetype testing"
 *
 * author:      "Rong Chen <rong.acode@gmail.com>"
 * copyright:   "Copyright (c) 2008 Cambio Healthcare Systems, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL$"
 * revision:    "$LastChangedRevision$"
 * last_change: "$LastChangedDate$"
 */
package org.openehr.testing;

import junit.framework.TestCase;

public class ArchetypeQueryTestCaseTest extends TestCase {
	
	public void setUp() throws Exception {
		testcase = new ArchetypeQueryTestCase("test");
		testcase.setUp();
	}
	
	public void testLoadQueryTestSpecification() throws Exception {
		assertNotNull("testcase null", testcase);
		assertNotNull("testcase.instance null", testcase.getInstance());
		assertNotNull("testcase.testSpec null", testcase.getTestSpecification());		
	}
	
	/**
	 * Make sure all queries are OK
	 */
	public void testAllQueriesRun() {
		testcase.executeAllTests();
	}
	
	private ArchetypeQueryTestCase testcase;
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
 * The Original Code is ArchetypeQueryTestCaseTest.java
 * 
 * The Initial Developer of the Original Code is Rong Chen. Portions created by
 * the Initial Developer are Copyright (C) 2003-2008 the Initial Developer. All
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