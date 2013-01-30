
package br.com.zilics.archetypes.models.rm.common.resource;

import java.util.List;
import java.util.Map;

import br.com.zilics.archetypes.models.rm.RMObject;
import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.annotation.MapItem;
import br.com.zilics.archetypes.models.rm.annotation.NotEmpty;
import br.com.zilics.archetypes.models.rm.annotation.NotNull;
import br.com.zilics.archetypes.models.rm.datatypes.text.CodePhrase;

/**
 * Language-specific detail of resource description. When a resource is translated
 * for use in another language environment, each  description item
 * needs to be copied and translated into the new language.
 * @author Humberto
 */
public class ResourceDescriptionItem extends RMObject {
	private static final long serialVersionUID = -2456637597143038024L;
	@NotNull
	@EqualsField
	private CodePhrase language;
	@NotEmpty
	@EqualsField
    private String purpose;
    private List<String> keywords;
    private String use;
    private String misuse;
    private String copyright;
    @MapItem
    private Map<String, String> originalResourceUri;
    @MapItem
    private Map<String, String> otherDetails;

    /**
     * Default constructor
     */
    public ResourceDescriptionItem() {}
    
    /**
     * Another constructor
     * @param language the language
     * @param purpose the purpose
     * @param keywords some keywords
     * @param use the correct use
     * @param misuse the incorrect use
     * @param copyright copyright notice
     * @param originalResourceUri Resource Identifier of this resource
     * @param otherDetails other details
     */
    public ResourceDescriptionItem(CodePhrase language, String purpose, List<String> keywords, String use, String misuse, String copyright, Map<String, String> originalResourceUri, Map<String, String> otherDetails) {
    	this.language = language;
    	this.purpose = purpose;
    	this.keywords = keywords;
    	this.use = use;
    	this.misuse = misuse;
    	this.copyright = copyright;
    	this.originalResourceUri = originalResourceUri;
    	this.otherDetails = otherDetails;
    }
    
    /**
     * Get the language
     * @return The localised language in which the items in
     * this description item are written. Coded from
     * openEHR Code Set "languages".
     */
    public CodePhrase getLanguage() {
        return language;
    }

    /**
     * Set the language
     * @param language The localised language in which the items in
     * this description item are written. Coded from
     * openEHR Code Set "languages".
     */
    public void setLanguage(CodePhrase language) {
    	assertMutable();
        this.language = language;
    }

    /**
     * Get the purpose
     * @return Purpose of the resource.
     */
    public String getPurpose() {
        return purpose;
    }

    /**
     * Set the purpose
     * @param purpose Purpose of the resource.
     */
    public void setPurpose(String purpose) {
    	assertMutable();
        this.purpose = purpose;
    }

    /**
     * Get the keywords
     * @return Keywords which characterise this resource,
     * used e.g. for indexing and searching.
     */
    public List<String> getKeywords() {
        return getList(keywords);
    }

    /**
     * Set the keywords
     * @param keywords Keywords which characterise this resource,
     * used e.g. for indexing and searching.
     */
    public void setKeywords(List<String> keywords) {
    	assertMutable();
        this.keywords = keywords;
    }

    /**
     * Get the use
     * @return Description of the uses of the resource, i.e.
     * contexts in which it could be used.
     */
    public String getUse() {
        return use;
    }

    /**
     * Set the use
     * @param use Description of the uses of the resource, i.e.
     * contexts in which it could be used.
     */
    public void setUse(String use) {
    	assertMutable();
        this.use = use;
    }

    /**
     * Get the misuse
     * @return Description of any misuses of the resource,
     * i.e. contexts in which it should not be used.
     */
    public String getMisuse() {
        return misuse;
    }

    /**
     * Set the misuse
     * @param misuse Description of any misuses of the resource,
     * i.e. contexts in which it should not be used.
     */
    public void setMisuse(String misuse) {
    	assertMutable();
        this.misuse = misuse;
    }

    /**
     * Get the copyright
     * @return Optional copyright statement for the resource
     * as a knowledge resource.
     */
    public String getCopyright() {
        return copyright;
    }

    /**
     * Set the copyright
     * @param copyright Optional copyright statement for the resource
     * as a knowledge resource.
     */
    public void setCopyright(String copyright) {
    	assertMutable();
        this.copyright = copyright;
    }

    /**
     * Get the originalResourceUri
     * @return URIs of original clinical document(s) or
     * description of which resource is a formalisation, in the language of this description item;
     * keyed by meaning.
     */
    public Map<String, String> getOriginalResourceUri() {
        return getMap(originalResourceUri);
    }

    /**
     * Set the originalResourceUri
     * @param originalResourceUri URIs of original clinical document(s) or
     * description of which resource is a formalisation, in the language of this description item;
     * keyed by meaning.
     */
    public void setOriginalResourceUri(Map<String, String> originalResourceUri) {
    	assertMutable();
        this.originalResourceUri = originalResourceUri;
    }

    /**
     * Get the otherDetails
     * @return Additional language-senstive resource metadata, as a list of name/value pairs
     */
    public Map<String, String> getOtherDetails() {
        return getMap(otherDetails);
    }

    /**
     * Set the otherDetails
     * @param otherDetails Additional language-senstive resource metadata, as a list of name/value pairs
     */
    public void setOtherDetails(Map<String, String> otherDetails) {
    	assertMutable();
        this.otherDetails = otherDetails;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
    	return "ResourceDescriptionItem[purpose=" + purpose + ", language=" + language + "]";
    }
    
}
