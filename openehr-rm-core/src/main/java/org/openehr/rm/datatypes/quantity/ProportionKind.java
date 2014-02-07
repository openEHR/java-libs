/*
 * component:   "openEHR Reference Implementation"
 * description: "Class ProportionKind"
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

import org.openehr.rm.Attribute;
import org.openehr.rm.FullConstructor;

/**
 * Class of enumeration constants defining types of proportion for the
 * DV_PROPORTION class.
 * 
 * @author Rong Chen
 */
public enum ProportionKind {
	/**
	 * Ratio type. Numerator and denominator may be any value
	 */
	RATIO(0),
	
	/**
	 * Denominator must be 1
	 */
	UNITARY(1),
	
	/**
	 * Denominator is 100, numerator is understood as a percentage value
	 */
	PERCENT(2),
	
	/**
	 * Numerator and denominator are integral, and the presentation
	 * method uses a slash, e.g. "1/2".
	 */
	FRACTION(3),
	
	/**
	 * Numerator and denominator are integral, and the presentation
	 * method uses a slash, e.g. "1/2"; if the numerator is greater than the
	 * denominator, e.g. n=3, d=2, the presentation is "1 1/2"
	 */
	INTEGER_FRACTION(4);
	
	/* 
	 * Constructor
	 * 
	 * @param value
	 */
	@FullConstructor
	private ProportionKind(
			@Attribute(name = "value", required=true) int value) {
		this.value = value;
	}
	
	/**
	 * Gets the integer value of this proportion kind
	 * 
	 * @return int value
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * Gets string presentation of this proportion kind
	 */
	public String toString() {
		return Integer.toString(value);
	}
	
	/**
	 * Gets proportion kind from integer value
	 * 
	 * @param value
	 * @return proportionKind of given value 
	 * @throws IllegalArgument if value is unknown
	 */
	public static ProportionKind fromValue(int value) {
		switch (value) {
		case 0:
			return RATIO;
		case 1:
			return UNITARY;
		case 2:
			return PERCENT;
		case 3:
			return FRACTION;
		case 4:
			return INTEGER_FRACTION;
		default:
			throw new IllegalArgumentException("unknown value");
		}
	}
	
	public static ProportionKind valueOf(int value) {
		return fromValue(value);
	}
	
	/* field */
	private int value;
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
 *  The Original Code is ProportionKind.java
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