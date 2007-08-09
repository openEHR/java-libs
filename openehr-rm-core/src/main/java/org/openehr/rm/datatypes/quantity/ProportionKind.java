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
	RATIO,
	
	/**
	 * Denominator must be 1
	 */
	UNITARY,
	
	/**
	 * Denominator is 100, numerator is understood as a percentage value
	 */
	PERCENT,
	
	/**
	 * Numerator and denominator are integral, and the presentation
	 * method uses a slash, e.g. "1/2".
	 */
	FRACTION,
	
	/**
	 * Numerator and denominator are integral, and the presentation
	 * method uses a slash, e.g. "1/2"; if the numerator is greater than the
	 * denominator, e.g. n=3, d=2, the presentation is "1 1/2"
	 */
	INTEGER_FRACTION
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