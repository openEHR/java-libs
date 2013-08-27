/*
 * component:   "openEHR Reference Implementation"
 * description: "Class TestTerminologyService"
 * keywords:    "unit test"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/BRANCHES/RM-1.0-update/libraries/src/test/org/openehr/rm/support/terminology/TestTerminologyService.java $"
 * revision:    "$LastChangedRevision: 50 $"
 * last_change: "$LastChangedDate: 2006-08-10 13:21:46 +0200 (Thu, 10 Aug 2006) $"
 */
package org.openehr.rm.support.terminology;

import java.util.List;
import java.util.Map;

/**
 * TestTerminologyService
 *
 * @author Rong Chen
 * @version 1.0
 */
public class TestTerminologyService implements TerminologyService {

    /**
     * Create a new instance of test terminology service
     *
     * @return
     */
    public static TestTerminologyService getInstance() {
        return new TestTerminologyService();
    }


    /**
     * Returns a TerminologyAccess of given name
     * 
     * @param name not empty and known to this service
     * @return terminology
     * @throws IllegalArgumentException if name null, empty
     *                                  or unknown to this terminology service
     */
    public TerminologyAccess terminology(String name) {
        return new TestTerminologyAccess();
    }

    /**
     * Returns a CodeSetAccess of given name
     * 
     * @param name not empty and known to this service
     * @return codeSet
     * @throws IllegalArgumentException if name is null, empty
     *                                  or unknown to this terminology service
     */
    public CodeSetAccess codeSet(String name) {
        return new TestCodeSetAccess();
    }

    /**
     * Returns ture if terminology of given name known by this service
     *
     * @param name not empty
     * @return true if has given terminology
     * @throws IllegalArgumentException if name is null or empty
     */
    public boolean hasTerminology(String name) {
        return false;  // todo: implement this method
    }

    /**
     * Returns true if code set of given name known by this service
     *
     * @param name not empty
     * @return true if has given codeset
     * @throws IllegalArgumentException if name is null or empty
     */
    public boolean hasCodeSet(String name) {
        return false;  // todo: implement this method
    }


	public CodeSetAccess codeSetForId(OpenEHRCodeSetIdentifiers name) {
		return new TestCodeSetAccess();
	}


	public List<String> terminologyIdentifiers() {
		// TODO Auto-generated method stub
		return null;
	}


	public List<String> codeSetIdentifiers() {
		// TODO Auto-generated method stub
		return null;
	}


	public Map<String, String> openehrCodeSets() {
		// TODO Auto-generated method stub
		return null;
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
 *  The Original Code is TestTerminologyService.java
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