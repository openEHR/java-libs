/*
 * component:   "openEHR Reference Implementation"
 * description: "Class ArchetypeDescriptionItem"
 * keywords:    "archetype"
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
package org.openehr.am.archetype.description;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.openehr.rm.datatypes.text.CodePhrase;

import java.util.List;
import java.util.Map;

/**
 * Detail of archetype description
 *
 * @author Rong Chen
 * @version 1.0
 */
public class ArchetypeDescriptionItem {

    /**
     * Constructs an ArchetypeDescriptionItem
     *
     * @param language
     * @param purpose
     * @param use
     * @param misuse
     * @param copyright
     * @param keywords
     * @throws IllegalArgumentException if language null or not valid
     *                                  purpose null or empty;
     *                                  use, misuses and rights empty
     */
    public ArchetypeDescriptionItem(CodePhrase language, String purpose,
                                    String use, String misuse, 
                                    String copyright, List<String> keywords,
                                    List<String> originalResourceUri,
                                    Map<String,String> otherDetails) {
        if (language == null) {
            throw new IllegalArgumentException("null language");
        }

        // todo: code_set("languages").has(language)

        if (StringUtils.isEmpty(purpose)) {
            throw new IllegalArgumentException("purpose null or empty");
        }
        if (use != null && StringUtils.isEmpty(use)) {
            throw new IllegalArgumentException("empty use");
        }
        if (misuse != null && StringUtils.isEmpty(misuse)) {
            throw new IllegalArgumentException("empty misuse");
        }
        if (copyright != null && StringUtils.isEmpty(copyright)) {
            throw new IllegalArgumentException("empty copyright");
        }
        this.language = language;
        this.purpose = purpose;
        this.use = use;
        this.misuse = misuse;
        this.copyright = copyright;
        this.keywords = keywords;
        this.originalResourceUri = originalResourceUri;
        this.otherDetails = otherDetails;
    }

    /**
     * Creates an ArchetypeDescriptionItem
     *
     * @param language
     * @param purpose
     * @param use
     * @param misuse
     * @param copyright
     */
    public ArchetypeDescriptionItem(CodePhrase language, String purpose,
                                    String use, String misuse,
                                    String copyright) {
        this(language, purpose, use, misuse, copyright, null, null, null);
    }

    /**
     * Create an ArchetypeDescriptionItem only with language and purpose
     *
     * @param language
     * @param purpose
     */
    public ArchetypeDescriptionItem(CodePhrase language, String purpose) {
        this(language, purpose, null, null, null, null, null, null);
    }

    /**
     * The localised language in which the value is written. Coded from
     * openEHR Code Set "languages".
     *
     * @return language
     */
    public CodePhrase getLanguage() {
        return language;
    }

    /**
     * Purpose of the archetype.
     *
     * @return purpose
     */
    public String getPurpose() {
        return purpose;
    }

    /**
     * Description of the uses of the archetype, ie contexts in which it
     * could be used.
     *
     * @return use
     */
    public String getUse() {
        return use;
    }

    /**
     * Description of any misuses of the archetype, ie contexts in which it
     * should not be used.
     *
     * @return misuse
     */
    public String getMisuse() {
        return misuse;
    }

    /**
     * Rights over the archetype as a knowledge resource; usually copyright
     * and/or license to use.
     *
     * @return rights
     */
    public String getCopyright() {
        return copyright;
    }
    
    /**
     * List of keywords
     */
    public List<String> getKeywords() {
    	return keywords;
    }
    /**
     * URI of original clinical document(s) or description of which archetype
     * is a formalisation, in the language of this description item.
     *
     * @return original resource URI
     */
    public List<String> getOriginalResourceUri() {
        return originalResourceUri;
    }

    /**
     * Additional language-senstive archetype meta-data, as a list of
     * name/value pairs.
     *
     * @return other details
     */
    public Map<String, String> getOtherDetails() {
        return otherDetails;
    }

    /**
     * String representation of this item
     *
     * @return string form
     */
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    /* fields */
    private CodePhrase language;
    private String purpose;
    private String use;
    private String misuse;
    private String copyright;
    private List<String> keywords;
    private List<String> originalResourceUri;
    private Map<String,String> otherDetails;
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
 *  The Original Code is ArchetypeDescriptionItem.java
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