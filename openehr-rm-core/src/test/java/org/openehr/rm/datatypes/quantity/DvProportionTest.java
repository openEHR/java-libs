/*
 * component:   "openEHR Reference Implementation"
 * description: "Class DvProportionTest"
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

public class DvProportionTest extends TestCase {
	
	public void testIsIntegeralWithFraction() {
		DvProportion p = new DvProportion(1, 2, ProportionKind.FRACTION, 0);
		assertTrue("fraction expected to be integral", p.isIntegral());
	}
	
	public void testIsIntegeralWithPercent() {
		DvProportion p = new DvProportion(1.2, 100, ProportionKind.PERCENT, 1);
		assertFalse("percent expected not to be integral", p.isIntegral());
	}
	
	public void testCreateFractionProportionWithNonZeroPrecision() {
		try {
			new DvProportion(1, 10, ProportionKind.FRACTION, 1);
			fail("should fail to create integral fraction with non-zero precision");
		} catch(Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}
	}
	
	public void testCreateProportionWithZeroPrecisionAndNonIntegral() {
		try {
			new DvProportion(1.3, 10, ProportionKind.RATIO, 0);
			fail("should fail to create non-integral with zero precision");
		} catch(Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}
	}
	
	public void testCreateIntegralProportionWithNonIntegralNumer() {
		try {
			new DvProportion(1.3, 10, ProportionKind.FRACTION, 0);
			fail("should fail to create integral with non-integral num");
		} catch(Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}		
	}
	
	public void testCreateUnitaryProportionWithBadDenominator() {
		try {
			new DvProportion(1.3, 2, ProportionKind.UNITARY, 1);
			fail("should fail to create unitary with bad denominator");
		} catch(Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}
	}
	
	public void testCreateUnitaryProportionWithRightDenominator() {
		new DvProportion(1.3, 1, ProportionKind.UNITARY, 1);		
	}
	
	public void testCreateIngegerProportionWithoutPricision() {
		try {
			new DvProportion(1.0, 1.0, ProportionKind.RATIO, null);
		} catch(Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}
	}
	
	public void testCreateDoubleProportionWithoutPricision() {
		try {
			new DvProportion(0.5, 1.0, ProportionKind.RATIO, null);
		} catch(Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}
	}
	
	public void testCreatePercentProportionWithBadDenominator() {
		try {
			new DvProportion(1.25, 10, ProportionKind.PERCENT, 2);
			fail("should fail to create percent with bad denominator");
		} catch(Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}
	}
	
	public void testCreatePercentProportionWithRightDenominator() {
		new DvProportion(1.25, 100, ProportionKind.PERCENT, 2);		
	}
	
	public void testCreateUnitaryProportionUsingFactoryMethod() {
		DvProportion dp = DvProportion.createUnitaryProportion(1.2, 1);
		assertEquals(1.2, dp.getNumerator(), 0);
	}
	
	public void testParsingDvProportion() {
		DvProportion dp = new DvProportion(25.3, 100, ProportionKind.PERCENT, 1);
		assertEquals(DvProportion.parseValue("DV_PROPORTION,25.3,100,2"), dp);
	}
	
	public void testParsingDvProportion2() {
		DvProportion dp = new DvProportion(21, 24, ProportionKind.FRACTION, 0);
		assertEquals(DvProportion.parseValue("DV_PROPORTION,21,24,3"), dp);
	}
	public void testParsingDvProportion3() {
		DvProportion dp = new DvProportion(29, 24, ProportionKind.INTEGER_FRACTION, 0);
		assertEquals(DvProportion.parseValue("DV_PROPORTION,29,24,3"), dp);
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
 *  The Original Code is DvProportionTest.java
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
