/**
 * 
 */
package org.openehr.rm.common.resource;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.openehr.rm.Attribute;
import org.openehr.rm.RMObject;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.support.terminology.TerminologyService;

/**
 * Language specific detail of resource description. When a resource is
 * translated for use in another language environment, each 
 * ResourceDescriptionItem needs to be copied and trnaslated into the new
 * language
 */
public class ResourceDescriptionItem extends RMObject {

	/**
	 * Construct ResourceDescriptionItem
	 */
	public ResourceDescriptionItem( CodePhrase language, String purpose,
			List<String> keywords, String use, String misuse, String copyright,
			HashMap<String, String> originalResourceUri, HashMap<String, String> otherDetails,
			TerminologyService terminologyService) {
		if (language == null) {
			throw new IllegalArgumentException("null language");
		}
		if (StringUtils.isEmpty(purpose)) {
			throw new IllegalArgumentException("null or empty purpose");
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
		if (!terminologyService.codeSet("languages").hasLang(language)) {
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
	public HashMap<String, String> getOriginalResourceUri() {
		return originalResourceUri;
	}
	
	/**
	 * Additional language-sensitive resource meta-data, 
	 * as a list of name/value pairs
	 * 
	 * @return otherDetails
 	 */
	public HashMap<String, String> getOtherDetails() {
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
	void setOriginalResourceUri(HashMap<String, String> originalResourceUri) {
		this.originalResourceUri = originalResourceUri;
	}
	void setOtherDetails(HashMap<String, String> otherDetails) {
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
	private HashMap<String, String> originalResourceUri;
	private HashMap<String, String> otherDetails;
		
}
