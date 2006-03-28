/*
 * component:   "openEHR Reference Implementation"
 * description: "Class DvEHRURI"
 * keywords:    "datatypes"
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
package org.openehr.rm.datatypes.uri;

/**
 * A EHRURI is a URI which has the scheme name "ehr", and which can
 * only reference elements in EHRs.
 * 
 * @author Rong Chen
 * @version 1.0
 */
public final class DvEHRURI extends DvURI {

    /**
     * Constructs a URI by parsing the given string.
     *
     * @param str The string to be parsed into a URI
     * @throws IllegalArgumentException if str null or bad syntax
     */
    public DvEHRURI(String str) {
        super(str);
    }

    // todo: implement this class
    public String ehrID() {
        return null;
    }

    public String compositionID() {
        return null;
    }

    public String sectionID() {
        return null;
    }

    public String entryID() {
        return null;
    }

    public boolean targetIsEHR() {
        return false;
    }

    public boolean targetIsComposition() {
        return false;
    }

    public boolean targetIsSection() {
        return false;
    }

    public boolean targetIsEntry() {
        return false;
    }

    public String scheme() {
        return EHR_SCHEM;
    }

    // POJO start
    private DvEHRURI() {
        super();
    }
    // POJO end

    /* fields */
    private static final String EHR_SCHEM = "ehr";
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
 *  The Original Code is DvEHRURI.java
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