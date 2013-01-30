
package br.com.zilics.archetypes.models.am.template.openehrprofile;

import java.util.Collections;
import java.util.List;


/**
 * Template Evaluation Specification
 * @author Humberto
 */
public class TemplateEvaluation extends TemplateContentItem<TemplateEntrySubtype>  {


	private static final long serialVersionUID = -2931690381871185690L;

	/**
     * Get the children
     * @return children elements
     */
    @Override
	public List<TemplateEntrySubtype> getChildren() {
        return Collections.emptyList();
    }

    /**
     * Set the children
     * @param children children elements
     */
    @Override
	public void setChildren(List<TemplateEntrySubtype> children) {
		assertMutable();
    }

}
