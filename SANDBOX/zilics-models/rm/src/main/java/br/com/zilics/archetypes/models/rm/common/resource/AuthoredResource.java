
package br.com.zilics.archetypes.models.rm.common.resource;

import java.util.Set;

import br.com.zilics.archetypes.models.rm.RMObject;
import br.com.zilics.archetypes.models.rm.annotation.NotNull;
import br.com.zilics.archetypes.models.rm.annotation.RmField;
import br.com.zilics.archetypes.models.rm.common.generic.RevisionHistory;
import br.com.zilics.archetypes.models.rm.datatypes.text.CodePhrase;
import br.com.zilics.archetypes.models.rm.utils.validation.ValidationResult;

/**
 * Abstract idea of an online resource created by a human author.
 * @author Humberto
 */
public abstract class AuthoredResource extends RMObject {
	private static final long serialVersionUID = 7684051878495435665L;
	@NotNull
	private CodePhrase originalLanguage;
	@RmField("is_controlled")
    private boolean controlled;

    private Set<TranslationDetails> translations;
    private ResourceDescription description;
    private RevisionHistory revisionHistory;

    /**
     * Get the originalLanguage
     * @return Language in which this resource was initially
     * authored. Although there is no language primacy of resources overall, the language of
     * original authoring is required to ensure natural
     * language translations can preserve quality.
     * Language is relevant in both the description
     * and ontology sections.
     */
    public CodePhrase getOriginalLanguage() {
        return originalLanguage;
    }

    /**
     * Set the originalLanguage
     * @param originalLanguage Language in which this resource was initially
     * authored. Although there is no language primacy of resources overall, the language of
     * original authoring is required to ensure natural
     * language translations can preserve quality.
     * Language is relevant in both the description
     * and ontology sections.
     */
    public void setOriginalLanguage(CodePhrase originalLanguage) {
    	assertMutable();
        this.originalLanguage = originalLanguage;
    }

    /**
     * Get the controlled
     * @return True if this resource is under any kind of
     * change control (even file copying), in which
     * case revision history is created.
     */
    public boolean isControlled() {
        return controlled;
    }

    /**
     * Set the controlled
     * @param controlled True if this resource is under any kind of
     * change control (even file copying), in which
     * case revision history is created.
     */
    public void setControlled(boolean controlled) {
    	assertMutable();
        this.controlled = controlled;
    }

    /**
     * Get the translations
     * @return List of details for each natural translation made
     * of this resource, keyed by language. For each
     * translation listed here, there must be corresponding sections in all language-dependent
     * parts of the resource.
     */
    public Set<TranslationDetails> getTranslations() {
        return getSet(translations);
    }

    /**
     * Set the translations
     * @param translations List of details for each natural translation made
     * of this resource, keyed by language. For each
     * translation listed here, there must be corresponding sections in all language-dependent
     * parts of the resource.
     */
    public void setTranslations(Set<TranslationDetails> translations) {
    	assertMutable();
        this.translations = translations;
    }

    /**
     * Get the description
     * @return Description and lifecycle information of the
     * resource.
     */
    public ResourceDescription getDescription() {
        return description;
    }

    /**
     * Set the description
     * @param description Description and lifecycle information of the
     * resource.
     */
    public void setDescription(ResourceDescription description) {
    	assertMutable();
        this.description = description;
    }

    /**
     * Get the revisionHistory
     * @return The revision history of the resource. Only
     * required if is controlled (avoids large
     * revision histories for informal or private editing situations).
     */
    public RevisionHistory getRevisionHistory() {
        return revisionHistory;
    }

    /**
     * Set the revisionHistory
     * @param revisionHistory The revision history of the resource. Only
     * required if is controlled (avoids large
     * revision histories for informal or private editing situations).
     */
    public void setRevisionHistory(RevisionHistory revisionHistory) {
    	assertMutable();
        this.revisionHistory = revisionHistory;
    }
    
    /**
     * {@inheritDoc} 
     */
    @Override
    protected void performValidation(ValidationResult result) {
    	super.performValidation(result);
    	if (controlled && (revisionHistory == null))
    		result.addItem(this, "Required revision history");
    }
}
