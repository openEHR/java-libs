
package br.com.zilics.archetypes.models.am.template.openehrprofile;

import java.util.List;


/**
 * Template Instruction specification
 * @author Humberto
 */
public class TemplateInstruction extends TemplateContentItem<TemplateItemTree>  {

	private static final long serialVersionUID = 1610704458126452625L;
	private List<TemplateItemTree> activityDescription;

    /**
     * Get the activityDescrition
     * @return {@link br.com.zilics.archetypes.models.rm.composition.content.entry.Instruction} activity description
     */
    public List<TemplateItemTree> getActivityDescription() {
        return getList(activityDescription);
    }

    /**
     * Set the activityDescrition
     * @param activityDescription {@link br.com.zilics.archetypes.models.rm.composition.content.entry.Instruction} activity description
     */
    public void setActivityDescription(List<TemplateItemTree> activityDescription) {
		assertMutable();
        this.activityDescription = activityDescription;
    }

    /**
     * Get the children
     * @return children elements
     */
    @Override
	public List<TemplateItemTree> getChildren() {
        return activityDescription;
    }

    /**
     * Set the children
     * @param children children elements
     */
    @Override
	public void setChildren(List<TemplateItemTree> children) {
		assertMutable();
        activityDescription = children;
    }

}
