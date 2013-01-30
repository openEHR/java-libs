package br.com.zilics.archetypes.models.am.archetype.ontology;

import java.util.Map;

import br.com.zilics.archetypes.models.am.AMObject;
import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.annotation.MapItem;
import br.com.zilics.archetypes.models.rm.annotation.NotEmpty;
import br.com.zilics.archetypes.models.rm.annotation.NotNull;

/**
 * A set of {@link ArchetypeTerm}
 * @author Humberto Naves
 *
 */
public class CodeDefinitionSet extends AMObject {
	private static final long serialVersionUID = 7179243012382091102L;

	@NotEmpty
    @EqualsField
	private String language;
	
	@NotNull
    @EqualsField
	@MapItem(key="code")
    private Map<String, ArchetypeTerm> items;

	/**
	 * Default constructor
	 */
	public CodeDefinitionSet() {}
	
	/**
	 * Another constructor
	 * @param language the language of this set
	 * @param items the items that forms this set
	 */
	public CodeDefinitionSet(String language, Map<String, ArchetypeTerm> items) {
		this.language = language;
		this.items = items;
	}

	/**
	 * Get the language
	 * @return language
	 */
	public String getLanguage() {
        return language;
    }

	/**
	 * Set the language
	 * @param language language
	 */
    public void setLanguage(String language) {
    	assertMutable();
        this.language = language;
    }

    /**
     * Get the items of this set
     * @return the items of the set
     */
    public Map<String, ArchetypeTerm> getItems() {
        return getMap(items);
    }

    /**
     * Set the items of this set
     * @param items the items of the set
     */
    public void setItems(Map<String, ArchetypeTerm> items) {
    	assertMutable();
        this.items = items;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
    	return "CODE_DEFINITION_SET[" + language + ", " + items + "]";
    }


}
