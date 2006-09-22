/*
 * component:   "openEHR Reference Implementation"
 * description: "Class DvEncapsulated"
 * keywords:    "datatypes"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/BRANCHES/RM-1.0-update/libraries/src/java/org/openehr/rm/datatypes/encapsulated/DvEncapsulated.java $"
 * revision:    "$LastChangedRevision: 50 $"
 * last_change: "$LastChangedDate: 2006-08-10 13:21:46 +0200 (Thu, 10 Aug 2006) $"
 */
package org.openehr.rm.datatypes.encapsulated;

import org.openehr.rm.datatypes.basic.DataValue;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.support.terminology.TerminologyAccess;
import org.openehr.rm.support.terminology.TerminologyService;

/**
 * Abstract class defining the common meta-data of all types of
 * encapsulated data.
 *
 * @author Rong Chen
 * @version 1.0
 */
public abstract class DvEncapsulated extends DataValue {

    /**
     * Construct a Encapsulated by charset, language and size
     *
     * @param charset            not null and valid
     * @param language           not null and valid
     * @param size               >= 0
     * @param terminologyService
     * @throws IllegalArgumentException if any argument is invalid
     */
    protected DvEncapsulated(CodePhrase charset, CodePhrase language,
                             int size, TerminologyService terminologyService) {
        if (charset == null) {
            throw new IllegalArgumentException("null charset");
        }
        if (language == null) {
            throw new IllegalArgumentException("null language");
        }
        if (terminologyService == null) {
            throw new IllegalArgumentException("null terminologyService");
        }
        TerminologyAccess terminogy = terminologyService.terminology(
                TerminologyService.OPENEHR);
        //if (!terminogy.codesForGroupName("languages", "en").contains(language)) {
            if (!terminologyService.codeSet("languages").hasLang(language)) {
            throw new IllegalArgumentException("unknown language: " + language);
            
        }
        //if (!terminogy.codesForGroupName("character sets", "en").contains(charset)) {
            if (!terminologyService.codeSet("character sets").hasCode(charset)) {
            throw new IllegalArgumentException(
                    "unknown character set: " + charset);
        }
        if (size < 0) {
            throw new IllegalArgumentException("negative size");
        }
        this.charset = charset;
        this.language = language;
        this.size = size;
    }

    /**
     * Returns IANA character set name if data is formatted text
     *
     * @return charset
     */
    public CodePhrase getCharset() {
        return charset;
    }

    /**
     * Returns name of language used if data is formatted text
     *
     * @return language
     */
    public CodePhrase getLanguage() {
        return language;
    }

    /**
     * Returns size in bytes of data of this Encapsulated
     *
     * @return size
     */
    public int getSize() {
        return size;
    }

    // POJO start
    protected DvEncapsulated() {
    }

    void setCharset(CodePhrase charset) {
        this.charset = charset;
    }

    void setLanguage(CodePhrase language) {
        this.language = language;
    }

    void setSize(int size) {
        this.size = size;
    }
    // POJO end

    /* fields */
    private CodePhrase charset;
    private CodePhrase language;
    private int size;
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
 *  The Original Code is DvEncapsulated.java
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