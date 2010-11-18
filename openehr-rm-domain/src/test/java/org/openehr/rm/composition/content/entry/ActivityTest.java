/*
 * component:   "openEHR Reference Implementation"
 * description: "Class ActivityTest"
 * keywords:    "unit test"
 *
 * author:      "Yin Su Lim <y.lim@chime.ucl.ac.uk>"
 * support:     "CHIME, UCL"
 * copyright:   "Copyright (c) 2006 UCL, UK"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/BRANCHES/RM-1.0-update/libraries/src/test/org/openehr/rm/composition/content/entry/ActivityTest.java $"
 * revision:    "$LastChangedRevision: 50 $"
 * last_change: "$LastChangedDate: 2006-08-10 12:21:46 +0100 (Thu, 10 Aug 2006) $"
 */

/**
 * ActivityTest
 *
 * @author Yin Su Lim
 * @version 1.0 
 */

package org.openehr.rm.composition.content.entry;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.openehr.rm.composition.CompositionTestBase;
import org.openehr.rm.datatypes.encapsulated.DvParsable;

public class ActivityTest extends CompositionTestBase {

	public ActivityTest(String testName) {
		super(testName);
	}

	protected void setUp() throws Exception {
		DvParsable timing = new DvParsable("timing value", "fomalism");
		activity = new Activity("at0004", text("activity 1"),
				list("list activity"), timing,
				"openEHR-EHR-ITEM_TREE.intravenous_fluids.v1");
	}

	protected void tearDown() throws Exception {
	}

	public static Test suite() {
		TestSuite suite = new TestSuite(ActivityTest.class);

		return suite;
	}

	public void testItemAtPath() {
    	path = "/";
    	value = activity.itemAtPath(path);
    	assertEquals(activity, value);
    }

	private Activity activity;
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
 *  The Original Code is ActivityTest.java
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