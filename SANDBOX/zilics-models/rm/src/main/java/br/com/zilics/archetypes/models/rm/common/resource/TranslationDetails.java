
package br.com.zilics.archetypes.models.rm.common.resource;

import java.util.Map;

import br.com.zilics.archetypes.models.rm.RMObject;
import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.annotation.MapItem;
import br.com.zilics.archetypes.models.rm.annotation.NotNull;
import br.com.zilics.archetypes.models.rm.datatypes.text.CodePhrase;

/**
 * Class providing details of a natural language translation.
 * @author Humberto
 */
public class TranslationDetails extends RMObject {
	private static final long serialVersionUID = -373879439967660090L;
	@NotNull
	@EqualsField
	private CodePhrase language;
	@NotNull
	@EqualsField
	@MapItem(isXmlAttribute=true, key="id")
    private Map<String, String> author;
    private String accreditation;
    @MapItem(isXmlAttribute=true, key="id")
    private Map<String, String> otherDetails;

    /**
     * Default constructor
     */
    public TranslationDetails() {}
    
    /**
     * Another constructor
     * @param language the language that of this translation
     * @param author the author of the translation
     * @param accreditation the accreditation
     * @param otherDetails other details of this translation
     */
    public TranslationDetails(CodePhrase language, Map<String, String> author, String accreditation, Map<String, String> otherDetails) {
    	this.language = language;
    	this.author = author;
    	this.accreditation = accreditation;
    	this.otherDetails = otherDetails;
    }
    /**
     * Get the language
     * @return Language of translation
     */
    public CodePhrase getLanguage() {
        return language;
    }

    /**
     * Set the language
     * @param language Language of translation
     */
    public void setLanguage(CodePhrase language) {
    	assertMutable();
        this.language = language;
    }

    /**
     * Get the author
     * @return Translator name and other demographic
     * details
     */
    public Map<String, String> getAuthor() {
        return getMap(author);
    }

    /**
     * Set the author
     * @param author Translator name and other demographic
     * details
     */
    public void setAuthor(Map<String, String> author) {
    	assertMutable();
        this.author = author;
    }

    /**
     * Get the accreditation
     * @return Accreditation of translator, usually a national
     * translator’s association id
     */
    public String getAccreditation() {
        return accreditation;
    }

    /**
     * Set the accreditation
     * @param accreditation Accreditation of translator, usually a national
     * translator’s association id
     */
    public void setAccreditation(String accreditation) {
    	assertMutable();
        this.accreditation = accreditation;
    }

    /**
     * Get the otherDetails
     * @return Any other meta-data
     */
    public Map<String, String> getOtherDetails() {
        return getMap(otherDetails);
    }

    /**
     * Set the otherDetails
     * @param otherDetails Any other meta-data
     */
    public void setOtherDetails(Map<String, String> otherDetails) {
    	assertMutable();
        this.otherDetails = otherDetails;
    }

}
