package br.com.zilics.archetypes.models.am.template.openehrprofile;

import java.util.ArrayList;
import java.util.List;

/**
 * Template Action specification
 * @author Humberto
 */
public class TemplateAction extends TemplateContentItem<TemplateItemTree> {

	private static final long serialVersionUID = 1924774597020698947L;
	private TemplateItemTree description;

    /**
     * Get the description
     * @return the description of that {@link br.com.zilics.archetypes.models.rm.composition.content.entry.Action}
     */
    public TemplateItemTree getDescription() {
        return description;
    }

    /**
     * Set the description
     * @param description the description of that {@link br.com.zilics.archetypes.models.rm.composition.content.entry.Action}
     */
    public void setDescription(TemplateItemTree description) {
		assertMutable();
        this.description = description;
    }

    /**
     * Set the children
     * @param children children elements
     */
    @Override
	public void setChildren(List<TemplateItemTree> children) {
		assertMutable();
        description = (TemplateItemTree) children.iterator().next();
    }

    /**
     * Get the children
     * @return children elements
     */
	@Override
	public List<TemplateItemTree> getChildren() {
        List<TemplateItemTree> result = new ArrayList<TemplateItemTree>();
        result.add(description);
        return result;
    }

}
