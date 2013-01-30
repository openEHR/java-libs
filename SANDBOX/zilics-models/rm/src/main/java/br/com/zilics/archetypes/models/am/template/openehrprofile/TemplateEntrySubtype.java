
package br.com.zilics.archetypes.models.am.template.openehrprofile;

import java.util.Collections;
import java.util.List;

/**
 * Template Entry Subtype specification
 * @author Humberto
 */
public class TemplateEntrySubtype extends TemplateLocatable<TemplateEntrySubtype>  {

	private static final long serialVersionUID = -2811555082726039845L;

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
