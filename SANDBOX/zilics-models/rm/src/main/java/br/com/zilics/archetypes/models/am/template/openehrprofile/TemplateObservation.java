
package br.com.zilics.archetypes.models.am.template.openehrprofile;

import java.util.List;

import br.com.zilics.archetypes.models.rm.annotation.RmField;

/**
 * Template Observation specification
 * @author Humberto
 */
public class TemplateObservation extends TemplateContentItem<TemplateEntrySubtype>  {

	private static final long serialVersionUID = 8318359426661601354L;

	@RmField("Items")
	private List<TemplateEntrySubtype> items;

    /**
     * Get the items
     * @return the {@link br.com.zilics.archetypes.models.rm.composition.content.entry.Observation} items
     */
    public List<TemplateEntrySubtype> getItems() {
        return getList(items);
    }

    /**
     * Set the items
     * @param items the {@link br.com.zilics.archetypes.models.rm.composition.content.entry.Observation} items
     */
    public void setItems(List<TemplateEntrySubtype> items) {
		assertMutable();
        this.items = items;
    }

    /**
     * Get the children
     * @return children elements
     */
    @Override
	public List<TemplateEntrySubtype> getChildren() {
        return items;
    }

    /**
     * Set the children
     * @param children children elements
     */
    @Override
	public void setChildren(List<TemplateEntrySubtype> children) {
		assertMutable();
        items = children;
    }

}
