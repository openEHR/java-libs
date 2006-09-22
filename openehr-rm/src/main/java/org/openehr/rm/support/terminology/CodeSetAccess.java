/*
 * component:   "openEHR Reference Implementation"
 * description: "Class CodeSetAccess"
 * keywords:    "support"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/support/terminology/CodeSetAccess.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 22:20:08 +0100 (Wed, 12 Oct 2005) $"
 */
package org.openehr.rm.support.terminology;

import org.openehr.rm.datatypes.text.CodePhrase;

import java.util.Set;

/**
 * Defines interface to a code set.
 *
 * @author Rong Chen
 * @version 1.0
 */
public interface CodeSetAccess {

    /**
     * Returns identification of this code set
     *
     * @return ID not null or empty
     */
    public String id();

    /**
     * Returns all codes known in this code set
     *
     * @return Set of DvCodePhrase
     */
    public Set<CodePhrase> allCodes();

    /**
     * Return true if this codeSet contains given codePhrase
     *
     * @param code
     * @return true if has
     */
    public boolean hasCode(CodePhrase code);
    
    /**
     * Return true if this codeSet contains given 'lang'
     *
     * @param lang
     * @return true if has
     */
    public boolean hasLang(CodePhrase lang);
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
 *  The Original Code is CodeSetAccess.java
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