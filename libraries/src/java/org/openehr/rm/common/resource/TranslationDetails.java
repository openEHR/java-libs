/*
 * component:   "openEHR Reference Implementation"
 * description: "Class AuditDetails"
 * keywords:    "common"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/common/changecontrol/AuditDetails.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 22:20:08 +0100 (Wed, 12 Oct 2005) $"
 */
package org.openehr.rm.common.resource;

import java.util.HashMap;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.openehr.rm.RMObject;
import org.openehr.rm.datatypes.text.CodePhrase;
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
	public TranslationDetails(CodePhrase language, HashMap<String, String> author,
			String accreditation, HashMap<String, String> otherDetails, 
			TerminologyService terminologyService) {
		if (language == null) {
			throw new IllegalArgumentException("null language");
		}
		if (author == null) {
			throw new IllegalArgumentException("null author");
		}
		if (terminologyService == null) {
			throw new IllegalArgumentException("null terminologyService");
		}
		if (!terminologyService.codeSet("languages").hasLang(language)) {
			throw new IllegalArgumentException("unknown language " + language);
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
	public HashMap<String, String> getAuthor() {
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
	public HashMap<String, String> getOtherDetails() {
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

	void setAuthor(HashMap<String, String> author) {
		this.author = author;
	}

	void setLanguage(CodePhrase language) {
		this.language = language;
	}

	void setOtherDetails(HashMap<String, String> otherDetails) {
		this.otherDetails = otherDetails;
	}	
	//POJO end
	
	/* fields */
	private CodePhrase language;
	private HashMap<String, String> author;
	private String accreditation;
	private HashMap<String, String> otherDetails;
}
