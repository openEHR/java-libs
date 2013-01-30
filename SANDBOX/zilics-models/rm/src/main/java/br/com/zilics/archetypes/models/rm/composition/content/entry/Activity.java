
package br.com.zilics.archetypes.models.rm.composition.content.entry;

import br.com.zilics.archetypes.models.rm.common.archetyped.Locatable;
import br.com.zilics.archetypes.models.rm.datastructure.itemstructure.ItemStructure;
import br.com.zilics.archetypes.models.rm.datatypes.encapsulated.DvParsable;

/**
 * Defines a single activity within an
 * {@link br.com.zilics.archetypes.models.rm.composition.content.entry.Instruction},
 * such as a medication administration.
 *
 * @author Humberto
 */
public class Activity extends Locatable {
	private static final long serialVersionUID = 4082165302638456517L;
	private ItemStructure description;
    private DvParsable timing;
    private String actionArchetypeId;

    /**
     * Get the description
     * @return the description of the activity
     */
    public ItemStructure getDescription() {
        return description;
    }

    /**
     * Set the description
     * @param description the description of the activity
     */
    public void setDescription(ItemStructure description) {
		assertMutable();
        this.description = description;
    }

    /**
     * Get the timing
     * @return the timing of the activity
     */
    public DvParsable getTiming() {
        return timing;
    }

    /**
     * Set the timing
     * @param timing the timing of the activity
     */
    public void setTiming(DvParsable timing) {
		assertMutable();
        this.timing = timing;
    }

    /**
     * Get the valid archetype identifiers of archetypes for Actions
     * @return a perl-compliant regular expression pattern, enclosed in '//' delimiters, indicating the valid archetype identifiers of archetypes for Actions corresponding to this Activity specification
     */
    public String getActionArchetypeId() {
        return actionArchetypeId;
    }

    /**
     * Set the valid archetype identifiers of archetypes for Actions
     * @param actionArchetypeId a perl-compliant regular expression pattern, enclosed in '//' delimiters, indicating the valid archetype identifiers of archetypes for Actions corresponding to this Activity specification
     */
    public void setActionArchetypeId(String actionArchetypeId) {
		assertMutable();
        this.actionArchetypeId = actionArchetypeId;
    }
}
