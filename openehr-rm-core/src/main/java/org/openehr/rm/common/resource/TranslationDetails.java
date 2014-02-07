/*
 * component:   "openEHR Reference Implementation"
 * description: "Class TranslationDetails"
 * keywords:    "resource"
 *
 * author:      "Yin Su Lim <y.lim@chime.ucl.ac.uk>"
 * support:     "CHIME, UCL"
 * copyright:   "Copyright (c) 2006 UCL, UK"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/common/resource/TranslationDetails.java $"
 * revision:    "$LastChangedRevision: 53 $"
 * last_change: "$LastChangedDate: 2006-08-11 13:20:08 +0100 (Fri, 11 Aug 2006) $"
 */
package org.openehr.rm.common.resource;

import java.util.Map;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.openehr.rm.RMObject;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.support.terminology.OpenEHRCodeSetIdentifiers;
import org.openehr.rm.support.terminology.TerminologyService;

/**
 * Class providing details of a natural language translation
 * 
 * @author Yin Su Lim
 * @version 1.0
 */
public class TranslationDetails extends RMObject {

	/**
	 * Construct a TranslationDetails
	 * 
	 * @param language
	 * @param author
	 * @param accreditation
	 * @param otherDetails
	 */
	public TranslationDetails(CodePhrase language, Map<String, String> author,
			String accreditation, Map<String, String> otherDetails, 
			TerminologyService terminologyService) {
		if (language == null) {
			throw new IllegalArgumentException("null language");
		}
		if (author == null) {
			throw new IllegalArgumentException("null author");
		}		
		if (!terminologyService.codeSetForId(
        		OpenEHRCodeSetIdentifiers.LANGUAGES).hasCode(language)) {
			throw new IllegalArgumentException("unknown original language " + 
					language);
		}
		
		this.language = language;
		this.author = author;
		this.accreditation = accreditation;
		this.otherDetails = otherDetails;
	}

	/**
	 * Accreditation of translator, usuallly a national translator's 
	 * association id.
	 * 
	 * @return accreditation
	 */
	public String getAccreditation() {
		return accreditation;
	}
	
	/**
	 * Translator name and other demographic details
	 * 
	 * @return author
	 */
	public Map<String, String> getAuthor() {
		return author;
	}
	
	/**
	 * Language of translation
	 * 
	 * @return language
	 */
	public CodePhrase getLanguage() {
		return language;
	}
	
	/**
	 * Any other meta-data
	 * 
	 * @return otherDetails
	 */
	public Map<String, String> getOtherDetails() {
		return otherDetails;
	}	

    /**
     * Equals if two translation details has same values
     *
     * @param o
     * @return equals if true
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!( o instanceof TranslationDetails )) return false;

        final TranslationDetails td = (TranslationDetails) o;
        return new EqualsBuilder()
                .append(language, td.language)
                .append(author, td.author)
                .append(accreditation, td.accreditation)
                .append(otherDetails, td.otherDetails)
                .isEquals();
    }

    /**
     * Return a hash code of this translationDetails
     *
     * @return hash code
     */
    public int hashCode() {
        return new HashCodeBuilder(19, 37)
                .append(language)
                .append(author)
                .append(accreditation)
                .append(otherDetails)
                .toHashCode();
    }
    
	//POJO start
	TranslationDetails() {
	}
	
	void setAccreditation(String accreditation) {
		this.accreditation = accreditation;
	}

	void setAuthor(Map<String, String> author) {
		this.author = author;
	}

	void setLanguage(CodePhrase language) {
		this.language = language;
	}

	void setOtherDetails(Map<String, String> otherDetails) {
		this.otherDetails = otherDetails;
	}	
	//POJO end
	
	/* fields */
	private CodePhrase language;
	private Map<String, String> author;
	private String accreditation;
	private Map<String, String> otherDetails;
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
 *  The Original Code is TranslationDetails.java
 *
 *  The Initial Developer of the Original Code is Yin Su Lim.
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