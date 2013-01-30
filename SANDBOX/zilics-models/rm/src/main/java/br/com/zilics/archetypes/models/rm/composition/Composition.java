
package br.com.zilics.archetypes.models.rm.composition;

import java.util.List;

import br.com.zilics.archetypes.models.rm.common.archetyped.Locatable;
import br.com.zilics.archetypes.models.rm.common.generic.PartyProxy;
import br.com.zilics.archetypes.models.rm.composition.content.ContentItem;
import br.com.zilics.archetypes.models.rm.datatypes.text.CodePhrase;
import br.com.zilics.archetypes.models.rm.datatypes.text.DvCodedText;


/**
 * A composition is considered the unit of modification of the record, the unit of
 * transmission in record extracts, and the unit of attestation by authorising
 * clinicians. In this latter sense, it may be considered equivalent to a signed
 * document.
 * @author Humberto
 */
public class Composition extends Locatable {

	private static final long serialVersionUID = 1219025144178411519L;
	private List<ContentItem> content;
    private EventContext context;
    private CodePhrase language;
    private CodePhrase territory;
    private DvCodedText category;
    private PartyProxy composer;

    /**
     * Get the content
     * @return the content of this Composition
     */
    public List<ContentItem> getContent() {
        return getList(content);
    }

    /**
     * Set the content
     * @param content the content of this Composition
     */
    public void setContent(List<ContentItem> content) {
		assertMutable();
        this.content = content;
    }

    /**
     * Get the contextual attributes of the clinical session
     * @return the clinical session context of this Composition
     */
    public EventContext getContext() {
        return context;
    }

    /**
     * Set the contextual attributes of the clinical session
     * @param context the clinical session context of this Composition
     */
    public void setContext(EventContext context) {
		assertMutable();
        this.context = context;
    }

    /**
     * Get the language
     * @return the language in which this Composition is written
     */
    public CodePhrase getLanguage() {
        return language;
    }

    /**
     * Set the language
     * @param language the language in which this Composition is written
     */
    public void setLanguage(CodePhrase language) {
		assertMutable();
        this.language = language;
    }

    /**
     * Get the name of territory
     * @return the name of territory in wich this Composition was written
     */
    public CodePhrase getTerritory() {
        return territory;
    }

    /**
     * Set the name of territory
     * @param territory the name of territory in wich this Composition was written
     */
    public void setTerritory(CodePhrase territory) {
		assertMutable();
        this.territory = territory;
    }

    /**
     * Get the categoty of this Composition
     * @return the broad category this Composition is belogs to
     */
    public DvCodedText getCategory() {
        return category;
    }

    /**
     * Set the categoty of this Composition
     * @param category the broad category this Composition is belogs to
     */
    public void setCategory(DvCodedText category) {
		assertMutable();
        this.category = category;
    }


    /**
     * Get the composer of this Composition
     * @return the person primarily responsible for the content of the Composition
     */
    public PartyProxy getComposer() {
        return composer;
    }

    /**
     * Get the composer of this Composition
     * @param composer the person primarily responsible for the content of the Composition
     */
    public void setComposer(PartyProxy composer) {
		assertMutable();
        this.composer = composer;
    }
    
}
