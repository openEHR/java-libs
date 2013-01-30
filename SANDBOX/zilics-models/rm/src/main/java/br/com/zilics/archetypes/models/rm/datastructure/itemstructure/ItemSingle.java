package br.com.zilics.archetypes.models.rm.datastructure.itemstructure;

import br.com.zilics.archetypes.models.rm.annotation.NotNull;
import br.com.zilics.archetypes.models.rm.datastructure.itemstructure.representation.Element;

/**
 * Logical single value data structure.
 * @author Humberto
 */
public class ItemSingle extends ItemStructure {

	private static final long serialVersionUID = 4267594797145851441L;
	@NotNull
	private Element item;

    /**
     * Retrieve the item.
     * @return item
     */
    public Element getItem() {
        return item;
    }

    /**
     * Set the item
     * @param item The item
     */
    public void setItem(Element item) {
		assertMutable();
        this.item = item;
    }
}