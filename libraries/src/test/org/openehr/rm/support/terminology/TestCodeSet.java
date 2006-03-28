/*
 * component:   "openEHR Reference Implementation"
 * description: "Class TestCodeSet"
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

import org.openehr.rm.datatypes.basic.DvState;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.datatypes.text.DvCodedText;

import java.util.Set;

/**
 * TestCodeSet
 *
 * @author Rong Chen
 * @version 1.0
 */
public class TestCodeSet implements CodeSet {

    /* fields */
    /**
     * Returns identification of this Terminology
     *
     * @return ID not null or empty
     */
    public String id() {
        return null;  // todo: implement this method
    }

    /**
     * Returns all codes known in this terminology
     *
     * @return Set of DvCodePhrase
     */
    public Set<CodePhrase> allCodes() {
        return null;  // todo: implement this method
    }

    /**
     * Return true if this codeset contains given codePhrase
     *
     * @param code
     * @return true if has
     */
    public boolean has(CodePhrase code) {
        return true;
    }

    public static final CodePhrase ENGLISH = new CodePhrase("test", "en");
    public static final CodePhrase LATIN_1 = new CodePhrase("test",
            "iso-8859-1");
    public static final CodePhrase NULL_FLAVOUR = new CodePhrase("test",
            "unanswered");

    // change type
    public static final DvCodedText CREATION = new DvCodedText("creation",
            ENGLISH, LATIN_1, new CodePhrase("test", "creation"),
            TestTerminologyService.getInstance());

    public static final DvCodedText AMENDMENT = new DvCodedText("creation",
            ENGLISH, LATIN_1, new CodePhrase("test", "creation"),
            TestTerminologyService.getInstance());

    // composition category
    public static final DvCodedText EVENT = new DvCodedText("event",
            ENGLISH, LATIN_1, new CodePhrase("test", "event"),
            TestTerminologyService.getInstance());


    // lifecycle state
    public static final DvState DRAFT = new DvState(new DvCodedText("draft",
            new CodePhrase("test", "draft"), ENGLISH, LATIN_1,
            TestTerminologyService.getInstance()), false);
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
 *  The Original Code is TestCodeSet.java
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