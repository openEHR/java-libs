/*
 * component:   "openEHR Reference Implementation"
 * description: "Class ResourceDescriptionItem"
 * keywords:    "resource"
 *
 * author:      "Yin Su Lim <y.lim@chime.ucl.ac.uk>"
 * support:     "CHIME, UCL"
 * copyright:   "Copyright (c) 2006 UCL, UK"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/common/common/resource/ResourceDescriptionItem.java $"
 * revision:    "$LastChangedRevision: 53 $"
 * last_change: "$LastChangedDate: 2006-08-11 13:20:08 +0100 (Fri, 11 Aug 2006) $"
 */
package org.openehr.rm.common.resource;

import java.util.Map;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.openehr.rm.RMObject;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.support.terminology.OpenEHRCodeSetIdentifiers;
import org.openehr.rm.support.terminology.TerminologyService;

/**
 * Language specific detail of resource description. When a resource is
 * translated for use in another language environment, each 
 * ResourceDescriptionItem needs to be copied and trnaslated into the new
 * language
 *
 * @author Yin Su Lim
 * @version 1.0
 */
public class ResourceDescriptionItem extends RMObject {

	/**
	 * Construct ResourceDescriptionItem
	 */
	public ResourceDescriptionItem( CodePhrase language, String purpose,
			List<String> keywords, String use, String misuse, String copyright,
			Map<String, String> originalResourceUri, Map<String, String> otherDetails,
			TerminologyService terminologyService) {
		if (language == null) {
			//throw new IllegalArgumentException("null language");
			System.out.println("WARNING: Archetype parsed that has wrong language handling...language set to ISO_639-1::en");
		    language = new CodePhrase("ISO_639-1","en"); 
		}
		if (StringUtils.isEmpty(purpose)) {
		//	throw new IllegalArgumentException("null or empty purpose");
		System.out.println("WARNING: Archetype parsed that has null or empty purpose...");
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
		if (terminologyService == null) {
			throw new IllegalArgumentException("null terminologyService");
		}
		if (!terminologyService.codeSetForId(
						OpenEHRCodeSetIdentifiers.LANGUAGES).hasCode(language)) {
			throw new IllegalArgumentException("unknown language:" + language);
		}
		this.language = language;
		this.purpose = purpose;
		this.keywords = keywords;
		this.use = use;
		this.misuse = misuse;
		this.copyright = copyright;
		this.originalResourceUri = originalResourceUri;
		this.otherDetails = otherDetails;
	}
	
        public ResourceDescriptionItem(CodePhrase language, String purpose, 
                TerminologyService terminologyService) {
            this(language, purpose, null, null, null, null, null, null, terminologyService);
        }
        
	/**
	 * Optional copyright statement for the resource as a knowledge resource.
	 * 
	 * @return copyright
	 */
	public String getCopyright() {
		return copyright;
	}
	
	/**
	 * Keywords which characterise this resource, used e.g. for indexing 
	 * and searching.
	 * 
	 * @return keywords
	 */
	public List<String> getKeywords() {
		return keywords;
	}
	
	/**
	 * The localised language in which the items in this description item 
	 * are written. Coded frolm openEhr CodeSet "languages"
	 * 
	 * @return language
	 */
	public CodePhrase getLanguage() {
		return language;
	}
	
	/**
	 * Description of any misuses of the resource, i.e. contexts in 
	 * which it should not be used.
	 * 
	 * @return misuse
	 */
	public String getMisuse() {
		return misuse;
	}
	
	/**
	 * URIs of original clinical document(s) or description of which 
	 * resource is a fomalisation, in the language of this description 
	 * item; keyed by meaning.
	 * 
	 * @return originalResourceUri
	 */
	public Map<String, String> getOriginalResourceUri() {
		return originalResourceUri;
	}
	
	/**
	 * Additional language-sensitive resource meta-data, 
	 * as a list of name/value pairs
	 * 
	 * @return otherDetails
 	 */
	public Map<String, String> getOtherDetails() {
		return otherDetails;
	}
	
	/**
	 * Purpose of the resource
	 * 
	 * @return purpose
	 */
	public String getPurpose() {
		return purpose;
	}
	
	/**
	 * Description of the uses of the resource, i.e. contexts 
	 * in which it could be used.
	 * 
	 * @return use
	 */ 
	public String getUse() {
		return use;
	}	

	//POJO start
	ResourceDescriptionItem() {
	}
	
	void setCopyright(String copyright) {
		this.copyright = copyright;
	}
	void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}
	void setLanguage(CodePhrase language) {
		this.language = language;
	}
	void setMisuse(String misuse) {
		this.misuse = misuse;
	}
	void setOriginalResourceUri(Map<String, String> originalResourceUri) {
		this.originalResourceUri = originalResourceUri;
	}
	void setOtherDetails(Map<String, String> otherDetails) {
		this.otherDetails = otherDetails;
	}
	void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	void setUse(String use) {
		this.use = use;
	}
	//POJO end
	
	/* fields */
	private CodePhrase language;
	private String purpose;
	private List<String> keywords;
	private String use;
	private String misuse;
	private String copyright;
	private Map<String, String> originalResourceUri;
	private Map<String, String> otherDetails;
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((copyright == null) ? 0 : copyright.hashCode());
		result = prime * result
				+ ((keywords == null) ? 0 : keywords.hashCode());
		result = prime * result
				+ ((language == null) ? 0 : language.hashCode());
		result = prime * result + ((misuse == null) ? 0 : misuse.hashCode());
		result = prime
				* result
				+ ((originalResourceUri == null) ? 0 : originalResourceUri
						.hashCode());
		result = prime * result
				+ ((otherDetails == null) ? 0 : otherDetails.hashCode());
		result = prime * result + ((purpose == null) ? 0 : purpose.hashCode());
		result = prime * result + ((use == null) ? 0 : use.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResourceDescriptionItem other = (ResourceDescriptionItem) obj;
		if (copyright == null) {
			if (other.copyright != null)
				return false;
		} else if (!copyright.equals(other.copyright))
			return false;
		if (keywords == null) {
			if (other.keywords != null)
				return false;
		} else if (!keywords.equals(other.keywords))
			return false;
		if (language == null) {
			if (other.language != null)
				return false;
		} else if (!language.equals(other.language))
			return false;
		if (misuse == null) {
			if (other.misuse != null)
				return false;
		} else if (!misuse.equals(other.misuse))
			return false;
		if (originalResourceUri == null) {
			if (other.originalResourceUri != null)
				return false;
		} else if (!originalResourceUri.equals(other.originalResourceUri))
			return false;
		if (otherDetails == null) {
			if (other.otherDetails != null)
				return false;
		} else if (!otherDetails.equals(other.otherDetails))
			return false;
		if (purpose == null) {
			if (other.purpose != null)
				return false;
		} else if (!purpose.equals(other.purpose))
			return false;
		if (use == null) {
			if (other.use != null)
				return false;
		} else if (!use.equals(other.use))
			return false;
		return true;
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
 *  The Original Code is ResourceDescriptionItem.java
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