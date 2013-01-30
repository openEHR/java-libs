
package br.com.zilics.archetypes.models.am.template.openehrprofile;

import java.util.List;

import br.com.zilics.archetypes.models.rm.annotation.RmField;

/**
 * Template Admin Entry specification
 * @author Humberto
 */
public class TemplateAdminEntry extends TemplateContentItem<TemplateEntrySubtype>  {

	private static final long serialVersionUID = 5177411411432569896L;
	@RmField("Items")
	private List<TemplateEntrySubtype> items;

    /**
     * Get the items
     * @return {@link br.com.zilics.archetypes.models.rm.composition.content.entry.AdminEntry} items
     */
    public List<TemplateEntrySubtype> getItems() {
        return getList(items);
    }

    /**
     * Set the items
     * @param items {@link br.com.zilics.archetypes.models.rm.composition.content.entry.AdminEntry} items
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
