package br.com.zilics.archetypes.models.rm.datastructure.itemstructure;

import java.util.List;

import br.com.zilics.archetypes.models.rm.annotation.NotNull;
import br.com.zilics.archetypes.models.rm.datastructure.itemstructure.representation.Element;

/**
 * Logical list data structure, where each item has a value and can be referred to
 * by a name and a positional index in the list.
 * @author Humberto
 */
public class ItemList extends ItemStructure {


	private static final long serialVersionUID = 3531372799468693638L;
	@NotNull
	private List<Element> items;

    /**
     * Retrieve all items
     * @return List of Element
     */
    public List<Element> getItems(){
        return getList(items);
    }

    /**
     * Set the item list
     * @param items item list
     */
    public void setItems(List<Element> items) {
		assertMutable();
        this.items = items;
    }

}