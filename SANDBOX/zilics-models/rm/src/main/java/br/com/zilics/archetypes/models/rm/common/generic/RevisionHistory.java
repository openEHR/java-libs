
package br.com.zilics.archetypes.models.rm.common.generic;

import java.util.List;

import br.com.zilics.archetypes.models.rm.RMObject;
import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.annotation.NotNull;

/**
 * Defines the notion of a revision history of audit items, each associated with the
 * version for which that audit was committed. The list is in most-recent-first order.
 * @author Humberto
 */
public class RevisionHistory extends RMObject {
	private static final long serialVersionUID = -1829806022486888273L;
	@NotNull
	@EqualsField
	private List<RevisionHistoryItem> items;

	/**
	 * Get the items
	 * @return The items in this history in most-recent-last order.
	 */
    public List<RevisionHistoryItem> getItems() {
        return getList(items);
    }

    /**
     * Set the items
     * @param items The items in this history in most-recent-last order.
     */
    public void setItems(List<RevisionHistoryItem> items) {
    	assertMutable();
        this.items = items;
    }
}
