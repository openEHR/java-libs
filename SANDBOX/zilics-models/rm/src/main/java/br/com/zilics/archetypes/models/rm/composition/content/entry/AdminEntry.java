
package br.com.zilics.archetypes.models.rm.composition.content.entry;

import br.com.zilics.archetypes.models.rm.datastructure.itemstructure.ItemStructure;

/**
 * Entry subtype for administrative information, i.e. information about setting up
 * the clinical process, but not itself clinically relevant. Archetypes will define
 * contained information
 *
 * @author Humberto
 */
public class AdminEntry extends Entry {
	private static final long serialVersionUID = -36561734494708073L;
	private ItemStructure data;

    /**
     * Get the data
     * @return the data of the Entry
     */
    public ItemStructure getData() {
        return data;
    }

    /**
     * Set the data
     * @param data the data of the Entry
     */
    public void setData(ItemStructure data) {
		assertMutable();
        this.data = data;
    }
    
}
