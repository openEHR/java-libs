/*
 * component:   "openEHR Reference Implementation"
 * description: "Class DvCodedText"
 * keywords:    "datatypes"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/BRANCHES/RM-1.0-update/libraries/src/java/org/openehr/rm/datatypes/text/DvCodedText.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 23:20:08 +0200 (Wed, 12 Oct 2005) $"
 */
package org.openehr.rm.datatypes.text;

import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.openehr.rm.Attribute;
import org.openehr.rm.FullConstructor;
import org.openehr.rm.datatypes.basic.ReferenceModelName;
import org.openehr.rm.datatypes.uri.DvURI;
import org.openehr.rm.support.terminology.TerminologyService;

/**
 * A text item whose value must be the rubric from a controlled
 * terminology, the key (the code ) of which is the
 * defining code attribute.
 * Instances of this class are immutable.
 *
 * @author Rong Chen
 * @version 1.0
 */
public final class DvCodedText extends DvText {

    /**
     * 
     */
    private static final long serialVersionUID = 5514325220408145036L;

    /**
     * 
     */
    /**
     * Constructs a CodedText of given components
     *
     * @param value
     * @param mapping
     * @param formatting
     * @param hyperlink
     * @param language
     * @param charset
     * @param definingCode       not null
     * @param terminologyService not null
     * @throws IllegalArgumentException if
     */
    @FullConstructor
            public DvCodedText(
            @Attribute(name = "value", required = true) String value,
            @Attribute(name = "mappings") List<TermMapping> mapping,
            @Attribute(name = "formatting") String formatting,
            @Attribute(name = "hyperlink") DvURI hyperlink,
            @Attribute(name = "language") CodePhrase language,
            @Attribute(name = "charset") CodePhrase charset,
            @Attribute(name = "definingCode", required = true) CodePhrase definingCode,
            @Attribute(name = "terminologyService", system = true)
            TerminologyService terminologyService) {
        super(value, mapping, formatting, hyperlink, language,
                charset, terminologyService);
        if (definingCode == null) {
            throw new IllegalArgumentException("null defining code");
        }
        this.definingCode = definingCode;
    }

    /**
     * Constructs a Text by string value, language, charset and define code
     *
     * @param value
     * @param language
     * @param charset
     * @param terminologyService not null
     * @throws IllegalArgumentException if value, language or charset
     *                                  are empty or invalid
     */
    public DvCodedText(String value, CodePhrase language,
                       CodePhrase charset, CodePhrase definingCode,
                       TerminologyService terminologyService) {
        this(value, null, null, null, language, charset,
                definingCode, terminologyService);
    }

    /**
     * Constructs a DvText by string value and defining code
     *
     * @param value
     */
    public DvCodedText(String value, CodePhrase definingCode) {
        this(value, null, null, definingCode, null);
    }

    public DvCodedText(String value, String terminology, String code) {
    	this(value, new CodePhrase(terminology, code));
    }

    /**
     * Return defining code
     *
     * @return defining code
     */
    public CodePhrase getDefiningCode() {
        return definingCode;
    }

    @Override
	public String getReferenceModelName() {
		return ReferenceModelName.DV_CODED_TEXT.getName();
	}
    
    /**
     * Two CodedText euqual if super class equals and has same value
     * for defining code
     *
     * @param o
     * @return true if equals
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!( o instanceof DvCodedText )) return false;
        if (!super.equals(o)) return false;

        final DvCodedText codedText = (DvCodedText) o;

        return new EqualsBuilder()
        .append(definingCode, codedText.definingCode)
        .isEquals();
    }

    /**
     * Return a hash code of this CodedText
     *
     * @return hash code
     */
    public int hashCode() {
        int result = super.hashCode();
        result = 29 * result + definingCode.hashCode();
        return result;
    }

    /**
     * Return string presentation of this CodedText
     *
     * @return string presentation
     */
    public String toString() {
        return definingCode.toString() + "|" + getValue() + "|";
    }
    
    public String serialise() {
    	return getReferenceModelName() + "," + toString();
    }
    
    public DvCodedText parse(String value){
		String[] tokens = value.split("::");
		if (tokens.length!=2){
		    throw new IllegalArgumentException("failed to parse DvCodedText '"+value+"', wrong number of tokens.");
		}
		String[] tokens2 = tokens[1].split("\\|");
		if (tokens2.length!=2){
		    throw new IllegalArgumentException("failed to parse DvCodedText '"+value+"', wrong number of tokens.");
		}
		return new DvCodedText(tokens2[1], tokens[0], tokens2[0]);
    }

    public String getTerminologyId(){
	return getDefiningCode().getTerminologyId().getValue();
    }
    
    public String getCode(){
	return getDefiningCode().getCodeString();
    }

    // POJO start
    DvCodedText() {
    }

    public void setDefiningCode(CodePhrase definingCode) {
        this.definingCode = definingCode;
    }
    // POJO end

    /* fields */
    private CodePhrase definingCode;
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
 *  The Original Code is DvCodedText.java
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