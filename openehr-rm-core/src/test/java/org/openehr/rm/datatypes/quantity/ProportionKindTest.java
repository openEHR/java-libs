/*
 * component:   "openEHR Reference Implementation"
 * description: "Class ProportionKindTest"
 * keywords:    "datatypes"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * copyright:   "Copyright (c) 2007 Cambio Healthcare Systems, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL$"
 * revision:    "$LastChangedRevision$"
 * last_change: "$LastChangedDate$"
 */
package org.openehr.rm.datatypes.quantity;

import junit.framework.TestCase;

/**
 * Testcase for ProportionKind
 * 
 * @author Rong Chen 
 */
public class ProportionKindTest extends TestCase {	
	
	public void testGetRatioFromValue() {
		assertEquals("Ratio kind expected", ProportionKind.RATIO,
				ProportionKind.fromValue(0));
	}
	
	public void testGetUnitaryFromValue() {
		assertEquals("Unitar kind expected", ProportionKind.UNITARY,
				ProportionKind.fromValue(1));
	}
	
	public void testGetPercentFromValue() {
		assertEquals("Percent kind expected", ProportionKind.PERCENT, 
				ProportionKind.fromValue(2));
	}
	
	public void testGetFractionFromValue() {
		assertEquals("Fraction kind expected", ProportionKind.FRACTION, 
				ProportionKind.fromValue(3));
	}
	
	public void testGetIntegerFractionFromValue() {
		assertEquals("Integer fraction kind expected", 
				ProportionKind.INTEGER_FRACTION, ProportionKind.fromValue(4));
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
 *  The Original Code is ProportionKindTest.java
 *
 *  The Initial Developer of the Original Code is Rong Chen.
 *  Portions created by the Initial Developer are Copyright (C) 2007
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