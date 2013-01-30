
package br.com.zilics.archetypes.models.rm.datastructure.itemstructure;

import java.util.List;

import br.com.zilics.archetypes.models.rm.datastructure.itemstructure.representation.Item;

/**
 * Logical tree data structure.
 *
 * @author Humberto
 */
public class ItemTree extends ItemStructure {

	private static final long serialVersionUID = 4223188786676992135L;
	private List<Item> items;

    /**
     * Retrieve all items
     * @return List of Element
     */
    public List<Item> getItems() {
        return getList(items);
    }

    /**
     * Set the item list
     * @param items List of Element.
     */
    public void setItems(List<Item> items) {
		assertMutable();
        this.items = items;
    }

}
