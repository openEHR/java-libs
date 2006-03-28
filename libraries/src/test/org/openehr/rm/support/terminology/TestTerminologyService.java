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
 * file:        "$URL$"
 * revision:    "$LastChangedRevision$"
 * last_change: "$LastChangedDate$"
 */
package org.openehr.rm.support.terminology;

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
     * Returns a Terminology of given name
     *
     * @param name not empty and known to this service
     * @return terminology
     * @throws IllegalArgumentException if name null, empty
     *                                  or unknown to this terminology service
     */
    public Terminology terminology(String name) {
        return new TestTerminology();
    }

    /**
     * Returns a CodeSet of given name
     *
     * @param name not empty and known to this service
     * @return codeSet
     * @throws IllegalArgumentException if name is null, empty
     *                                  or unknown to this terminology service
     */
    public CodeSet codeSet(String name) {
        return new TestCodeSet();
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