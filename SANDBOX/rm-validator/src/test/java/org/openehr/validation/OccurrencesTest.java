/*
 * component:   "openEHR Reference Java Implementation"
 * description: "Class OccurrencesTest"
 * keywords:    "validator"
 *
 * author:      "Rong Chen <rong.acode@gmail.com>"
 * support:     "openEHR Java Project <ref_impl_java@openehr.org>"
 * copyright:   "Copyright (c) 2008 Cambio Healthcare Systems, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL$"
 * revision:    "$LastChangedRevision$"
 * last_change: "$LastChangedDate$"
 */
package org.openehr.validation;

public class OccurrencesTest extends ValidationTestBase {
	
	public void setUp() throws Exception {
		super.setUp();
		loadArchetype("openEHR-EHR-OBSERVATION.blood_pressure_test4.v1.adl");		
	}
	
	public void testValidateWithValidOccurrences() throws Exception {
		loadDataAndValidate("blood_pressure_0008.dadl");
		assertValidationPassed();
	}
	
	public void testValidateWithTooFewOccurrences() throws Exception {
		loadDataAndValidate("blood_pressure_0007.dadl");
		assertLastValidationError(
				"/data/events[at0006]/data/items",
				"/data[at0001]/events[at0006]/data[at0003]/items[at0004]",
				ErrorType.OCCURRENCES_TOO_FEW);
	}
	
	public void testValidateWithTooManyOccurrences() throws Exception {
		loadDataAndValidate("blood_pressure_0009.dadl");
		assertLastValidationError(
				"/data/events[at0006]/data/items",
				"/data[at0001]/events[at0006]/data[at0003]/items[at0004]",
				ErrorType.OCCURRENCES_TOO_MANY);
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
 *  The Original Code is OccurrencesTest.java
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