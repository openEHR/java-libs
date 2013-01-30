
package br.com.zilics.archetypes.models.rm.composition.content.entry;

import br.com.zilics.archetypes.models.rm.datastructure.itemstructure.ItemStructure;

/**
 * Entry type for evaluation statements.
 *
 * @author Humberto
 */
public class Evaluation extends CareEntry {
	private static final long serialVersionUID = 4301158273463102481L;
	private ItemStructure data;

    /**
     * Get the data
     * @return the data of this evaluation
     */
    public ItemStructure getData() {
        return data;
    }

    /**
     * Set the data
     * @param data the data of this evaluation
     */
    public void setData(ItemStructure data) {
		assertMutable();
        this.data = data;
    }
}
