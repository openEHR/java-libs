
package br.com.zilics.archetypes.models.am.archetype.ontology;

import java.util.Map;

import br.com.zilics.archetypes.models.am.AMObject;
import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.annotation.MapItem;
import br.com.zilics.archetypes.models.rm.annotation.NotEmpty;
import br.com.zilics.archetypes.models.rm.annotation.NotNull;

/**
 * Representation of any coded entity (term or constraint) in the archetype ontology
 *
 * @author Humberto
 */
public class ArchetypeTerm extends AMObject {

	private static final long serialVersionUID = -6689724690546197746L;
	@NotEmpty
    @EqualsField
	private String code;
	
	@NotNull
    @EqualsField
	@MapItem()
    private Map<String, String> items;

	/**
	 * Default constructor
	 */
	public ArchetypeTerm() {}

	/**
	 * Another constructor
	 * @param code Code of this term
	 * @param items Map of items
	 */
	public ArchetypeTerm(String code, Map<String, String> items) {
		this.code = code;
		this.items = items;
	}
	
    /**
     * Get the code
     * @return The term code
     */
    public String getCode() {
        return code;
    }

    /**
     * Set the code
     * @param code The term code
     */
    public void setCode(String code) {
		assertMutable();
        this.code = code;
    }

    /**
     * Get the items
     * @return Hash of keys ("text", "description" etc) and corresponding values
     */
    public Map<String, String> getItems() {
        return getMap(items);
    }

    /**
     * Set the items
     * @param items Hash of keys ("text", "description" etc) and corresponding values
     */
    public void setItems(Map<String, String> items) {
		assertMutable();
        this.items = items;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
    	return "ARCHETYPE_TERM[" + code + ", " + items + "]"; 
    }

}
