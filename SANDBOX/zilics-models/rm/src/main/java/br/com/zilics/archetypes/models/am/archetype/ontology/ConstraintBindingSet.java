package br.com.zilics.archetypes.models.am.archetype.ontology;

import java.util.Map;

import br.com.zilics.archetypes.models.am.AMObject;
import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.annotation.MapItem;
import br.com.zilics.archetypes.models.rm.annotation.NotEmpty;
import br.com.zilics.archetypes.models.rm.annotation.NotNull;

/**
 * A set of {@link ConstraintBindingItem}
 * @author Humberto Naves
 */
public class ConstraintBindingSet extends AMObject {
	private static final long serialVersionUID = -5545262469155369261L;
	
	@NotEmpty
    @EqualsField
	private String terminology;

	@NotNull
    @EqualsField
	@MapItem(key="code")
    private Map<String, ConstraintBindingItem> items;

    /**
     * Default constructor
     */
    public ConstraintBindingSet() {}
    
    /**
     * Another constructor
     * @param terminology the terminology of this binding set
     * @param items the items of this set
     */
    public ConstraintBindingSet(String terminology, Map<String, ConstraintBindingItem> items) {
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
    public Map<String, ConstraintBindingItem> getItems() {
        return getMap(items);
    }

    /**
     * Set the items
     * @param items the items of this set
     */
    public void setItems(Map<String, ConstraintBindingItem> items) {
    	assertMutable();
        this.items = items;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
    	return "CONSTRAINT_BINDING_SET[" + terminology + ", " + items + "]";
    }

}
