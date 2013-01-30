
package br.com.zilics.archetypes.models.am.template.openehrprofile;

import java.util.Collections;
import java.util.List;

/**
 * Template Item Tree specification
 * @author Humberto
 */
public class TemplateItemTree extends TemplateLocatable<TemplateEntrySubtype> {

	private static final long serialVersionUID = 4158893605087697571L;

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
