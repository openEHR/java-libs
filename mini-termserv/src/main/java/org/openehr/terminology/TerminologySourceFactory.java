/*
 * component:   "openEHR Reference Implementation"
 * description: "Class TerminologySourceFactory"
 * keywords:    "terminology"
 *
 * author:      "Rong Chen <rong.acode@gmail.com>"
 * copyright:   "Copyright (c) 2007 Rong Chen"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL$"
 * revision:    "$LastChangedRevision$"
 * last_change: "$LastChangedDate$"
 */
package org.openehr.terminology;

/**
 * Factory for concrete terminology source implementation
 * 
 * @author rong.chen
 */
public class TerminologySourceFactory {
	
	private static final String OPENEHR_TERMINOLOGY = "/openehr_terminology_en.xml";
	private static final String EXTERNAL_TERMINOLOGIES = "/external_terminologies_en.xml";
	
	/**
	 * Gets an instance of openEHR terminology source
	 * 
	 * @return terminology source instance
	 */
	public static TerminologySource getOpenEHRTerminology() throws Exception {
		return XMLTerminologySource.getInstance(OPENEHR_TERMINOLOGY);
	}
	
	/**
	 * Gets an instance of external terminologies source
	 * 
	 * @return terminology source instance
	 */
	public static TerminologySource getExternalTerminologies() throws Exception {
		return XMLTerminologySource.getInstance(EXTERNAL_TERMINOLOGIES);
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
 *  The Original Code is TerminologySourceFactory.java
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