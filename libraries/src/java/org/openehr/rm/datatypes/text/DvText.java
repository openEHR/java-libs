/*
 * component:   "openEHR Reference Implementation"
 * description: "Class DvText"
 * keywords:    "datatypes"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/datatypes/text/DvText.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 22:20:08 +0100 (Wed, 12 Oct 2005) $"
 */
package org.openehr.rm.datatypes.text;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.openehr.rm.Attribute;
import org.openehr.rm.FullConstructor;
import org.openehr.rm.datatypes.basic.DataValue;
import org.openehr.rm.datatypes.uri.DvURI;
import org.openehr.rm.support.terminology.TerminologyService;

import java.util.ArrayList;
import java.util.List;

/**
 * The Text class represents any kind of atomic text item, coded or
 * uncoded.
 * Instances of this class are immutable.
 *
 * @author Rong Chen
 * @version 1.0
 */
public class DvText extends DataValue {

    /**
     * Constructs a Text from the given components.
     *
     * @param value              not null or empty or contains
     * @param mappings           null if unspecified
     * @param formatting         null if unspecified
     * @param hyperlink          null if unspecifed
     * @param language           not null and valid language code
     * @param charset            not null and valid charset code
     * @param terminologyService not null
     * @throws IllegalArgumentException if any of the components
     *                                  is invalid
     */
    @FullConstructor public DvText(
            @Attribute(name = "value", required = true) String value,
            @Attribute(name = "mappings") List<TermMapping> mappings,
            @Attribute(name = "formatting") String formatting,
            @Attribute(name = "hyperlink") DvURI hyperlink,
            @Attribute(name = "language") CodePhrase language,
            @Attribute(name = "charset") CodePhrase charset,
            @Attribute(name = "terminologyService", system = true) TerminologyService terminologyService) {
        if (!validValue(value)) {
            throw new IllegalArgumentException("invalid value: " + value);
        }
        if (mappings != null && mappings.isEmpty()) {
            throw new IllegalArgumentException("empty mapping");
        }
        if (formatting != null && StringUtils.isEmpty(formatting)) {
            throw new IllegalArgumentException("empty formatting");
        }
        if (language != null || charset != null) {
            if (terminologyService == null) {
                throw new IllegalArgumentException("null terminologyService");
            }
            if (!terminologyService.codeSet("languages").hasLang(language)) {
                throw new IllegalArgumentException(
                        "unknown language: " + language);
            }
            if (!terminologyService.codeSet("character sets").hasCode(charset)) {
                throw new IllegalArgumentException(
                        "unknown character set: " + charset);
            }
        }
        this.value = value;
        this.mappings = ( mappings == null ?
                null : new ArrayList<TermMapping>(mappings) );
        this.formatting = formatting;
        this.hyperlink = hyperlink;
        this.language = language;
        this.charset = charset;
    }

    /**
     * Constructs a DvText by string value, language and charset
     *
     * @param value
     * @param language
     * @param charset
     * @param terminologyService
     * @throws IllegalArgumentException if value, language or charset
     *                                  are empty or invalid
     */
    public DvText(String value, CodePhrase language,
                  CodePhrase charset, TerminologyService terminologyService) {
        this(value, null, null, null, language, charset,
                terminologyService);
    }

    /**
     * Constructs a DvText by required value
     *
     * @param value
     */
    public DvText(String value) {
        this(value, null, null, null, null, null, null);
    }

    static boolean validValue(String value) {
        return StringUtils.isNotEmpty(value)
                && StringUtils.containsNone(value, "\n\r");
    }

    /**
     * string form displayable for humans
     *
     * @return <code>String</code>
     */
    public String toString() {
        return value;
    }

    /**
     * Returns value of this Text
     *
     * @return string value
     */
    public String getValue() {
        return value;
    }

    /**
     * Return mappings of this Text
     *
     * @return null if unspecified
     */
    public List<TermMapping> getMappings() {
        return mappings;
    }

    /**
     * Returns formatting of this Text
     *
     * @return null if unspecified
     */
    public String getFormatting() {
        return formatting;
    }

    /**
     * Returns hyperlink of this Text
     *
     * @return null if unspecified
     */
    public DvURI getHyperlink() {
        return hyperlink;
    }

    /**
     * Optional indicator of the localised language in which the value is
     * written. Coded from openEHR Code Set languages. Only used when either
     * the text object is in a different language from the enclosing ENTRY,
     * or else the text object is being used outside of an ENTRY or other
     * enclosing structure which indicates the language.
     *
     * @return language or null if not specified
     */
    public CodePhrase getLanguage() {
        return language;
    }

    /**
     * Name of character set in which this value is encoded. Coded from
     * openEHR Code Set character sets.
     *
     * @return character set or null if not specified
     */
    public CodePhrase getCharset() {
        return charset;
    }

    /**
     * Returns true if both has the same values for string
     * value, language and charset, all optional attributes are not
     * included in comparision
     *
     * @param o
     * @return true if equals
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!( o instanceof DvText )) return false;

        final DvText dvText = (DvText) o;

        return new EqualsBuilder()
                .append(value, dvText.value)
                .append(language, dvText.language)
                .append(charset, dvText.charset)
                .isEquals();
    }

    /**
     * Returns a hash code of this Text
     *
     * @return hashcode
     */
    public int hashCode() {
        return new HashCodeBuilder(17, 47)
                .append(value)
                .append(language)
                .append(charset)
                .toHashCode();
    }

    // POJO start
    protected DvText() {
    }

    protected void setValue(String value) {
        this.value = value;
    }

    protected void setMappings(List<TermMapping> mappings) {
        this.mappings = mappings;   // todo: add unmodifiable
    }

    protected void setFormatting(String formatting) {
        this.formatting = formatting;
    }

    protected void setHyperlink(DvURI hyperlink) {
        this.hyperlink = hyperlink;
    }

    protected void setLanguage(CodePhrase language) {
        this.language = language;
    }

    protected void setCharset(CodePhrase charset) {
        this.charset = charset;
    }
    // POJO end

    /* fields */
    private String value;
    private List<TermMapping> mappings;
    private String formatting;
    private DvURI hyperlink;
    private CodePhrase language;
    private CodePhrase charset;
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
 *  The Original Code is DvText.java
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