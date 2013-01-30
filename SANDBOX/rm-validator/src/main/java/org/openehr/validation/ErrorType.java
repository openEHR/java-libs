/*
 * component:   "openEHR Reference Java Implementation"
 * description: "Class ErrorType"
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

/**
 * Enumeration of error types from archetype based reference model objects
 * validation
 * 
 * @author Rong Chen
 */
public enum ErrorType {
	// EXISTENCE
	ATTRIBUTE_MISSING,
	ATTRIBUTE_NOT_ALLOWED,
	// OCCURRENCES
	ITEMS_TOO_MANY,
	ITEMS_TOO_FEW,
	ITEMS_NOT_ORDERED,
	ITEMS_NOT_UNIQUE,
	OCCURRENCES_TOO_MANY,
	OCCURRENCES_TOO_FEW,
	// C_BOOLEAN
	TRUE_NOT_ALLOWED,
	FALSE_NOT_ALLOWED,
	// C_STRING
	UNMATCHED_STRING_PATTERN,
	UNKNOWN_STRING_VALUE,
	// C_INTEGER
	INTEGER_TOO_LARGE,
	INTEGER_TOO_SMALL,
	INTEGER_NOT_ALLOWED,
	// C_REAL
	REAL_TOO_LARGE,
	REAL_TOO_SMALL,
	REAL_NOT_ALLOWED,
	// C_DATE
	MONTH_NOT_ALLOWED,
	DAY_NOT_ALLOWED,
	TIMEZONE_NOT_ALLOWED,
	DATE_OUT_OF_RANGE,
	// C_TIME
	MINUTE_NOT_ALLOWED,
	SECOND_NOT_ALLOWED,
	MILLISECOND_NOT_ALLOWED,
	TIME_OUT_OF_RANGE,
	// C_DATE_TIME
	DATETIME_OUT_OF_RANGE,
	// C_DURATION
	YEARS_NOT_ALLOWED,
	MONTHS_NOT_ALLOWED,
	WEEKS_NOT_ALLOWED,
	DAYS_NOT_ALLOWED,
	HOURS_NOT_ALLOWED,
	MINTUES_NOT_ALLOWED,
	SECONDS_NOT_ALLOWED,
	FRACTIONAL_SECONDS_NOT_ALLOWED,
	DURATION_OUT_OF_RANGE,
	// C_DV_STATE
	UNKNOWN_STATE,
	UNKNOWN_TRANSITION,
	// C_CODE_PHRASE
	UNKNOWN_TERMINOLOGY,
	UNKNOW_CODE,
	// C_DV_ORDINAL
	UNKNOWN_ORDINAL,
	// C_DV_QUANTITY
	INVALID_MAGNITUDE,
	INVALID_PRECISION,
	INVALID_UNITS,
	// TERM DEFINITIONS
	INCORRECT_NAME,
	// TERM CONSTRAINTS
	INCORRECT_CODE,
	
	// TODO: temporary types
	PRIMITIVE_TYPE_VALUE_ERROR,
	DOMAIN_TYPE_VALUE_ERROR
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
 *  The Original Code is ErrorType.java
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