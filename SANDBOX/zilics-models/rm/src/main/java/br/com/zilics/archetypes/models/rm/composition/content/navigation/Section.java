
package br.com.zilics.archetypes.models.rm.composition.content.navigation;

import java.util.List;

import br.com.zilics.archetypes.models.rm.composition.content.ContentItem;

/**
 * Represents a heading in a heading structure, or "section tree".
 *
 * @author Humberto
 */
public class Section extends ContentItem {

	private static final long serialVersionUID = 5500216860463573954L;
	private List<ContentItem> items;

    /**
     * Get the items
     * @return the ordered list of content items under this section
     */
    public List<ContentItem> getItems() {
        return getList(items);
    }

    /**
     * Set the items
     * @param items the ordered list of content items under this section
     */
    public void setItems(List<ContentItem> items) {
		assertMutable();
        this.items = items;
    }
    
}
