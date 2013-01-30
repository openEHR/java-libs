package br.com.zilics.archetypes.models.am.archetype.ontology;

import java.util.Map;

import br.com.zilics.archetypes.models.am.AMObject;
import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.annotation.MapItem;
import br.com.zilics.archetypes.models.rm.annotation.NotEmpty;
import br.com.zilics.archetypes.models.rm.annotation.NotNull;

/**
 * A set of {@link TermBindingItem}
 * @author Humberto Naves
 *
 */
public class TermBindingSet extends AMObject {

	private static final long serialVersionUID = 4483765399738783076L;

	@NotEmpty
    @EqualsField
	private String terminology;

    @NotNull
    @MapItem(key="code")
    @EqualsField
    private Map<String, TermBindingItem> items;

    /**
     * Default constructor
     */
    public TermBindingSet() {}
    
    /**
     * Another constructor
     * @param terminology the terminology of this binding set
     * @param items the items of this set
     */
    public TermBindingSet(String terminology, Map<String, TermBindingItem> items) {
    	this.terminology = terminology;
    	this.items = items;
    }
    
    /**
     * Get the terminology
     * @return terminology
     */
    public String getTerminology() {
        return terminology;
    }

    /**
     * Set the terminology
     * @param terminology the terminology
     */
    public void setTerminology(String terminology) {
    	assertMutable();
        this.terminology = terminology;
    }

    /**
     * Get the items
     * @return the items
     */
    public Map<String, TermBindingItem> getItems() {
        return getMap(items);
    }

    /**
     * Set the items
     * @param items the items of this set
     */
    public void setItems(Map<String, TermBindingItem> items) {
    	assertMutable();
        this.items = items;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
    	return "TERM_BINDING_SET[" + terminology + ", " + items + "]";
    }
}
