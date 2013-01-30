
package br.com.zilics.archetypes.models.am.template.openehrprofile;

import java.util.List;

import br.com.zilics.archetypes.models.rm.annotation.RmField;

/**
 * Template Section specification
 * @author Humberto
 */
public class TemplateSection extends TemplateContentItem<TemplateContentItem<?>> {

	private static final long serialVersionUID = -5803968776978882226L;

	@RmField("Item")
	private List<TemplateContentItem<?>> item;

    /**
     * Get the item
     * @return the {@link br.com.zilics.archetypes.models.rm.composition.content.navigation.Section} item
     */
    public List<TemplateContentItem<?>> getItem() {
        return getList(item);
    }

    /**
     * Set the item
     * @param item the {@link br.com.zilics.archetypes.models.rm.composition.content.navigation.Section} item
     */
    public void setItem(List<TemplateContentItem<?>> item) {
		assertMutable();
        this.item = item;
    }


    /**
     * Get the children
     * @return children elements
     */
    @Override
	public List<TemplateContentItem<?>> getChildren() {
        return item;
    }

    /**
     * Set the children
     * @param children children elements
     */
    @Override
	public void setChildren(List<TemplateContentItem<?>> children) {
		assertMutable();
        item = children;
    }

}
