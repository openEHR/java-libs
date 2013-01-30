
package br.com.zilics.archetypes.models.am.template.openehrprofile;

import java.util.List;

import br.com.zilics.archetypes.models.rm.annotation.RmField;

/**
 * Template Composition Specification
 * @author Humberto
 */
public class TemplateComposition extends TemplateLocatable<TemplateContentItem<?>> {

	private static final long serialVersionUID = -2116936998706387240L;
	@RmField("Content")
	private List<TemplateContentItem<?>> content;

    /**
     * Get the content
     * @return the {@link br.com.zilics.archetypes.models.rm.composition.Composition} content
     */
    public List<TemplateContentItem<?>> getContent() {
        return getList(content);
    }

    /**
     * Set the content
     * @param content the {@link br.com.zilics.archetypes.models.rm.composition.Composition} content
     */
    public void setContent(List<TemplateContentItem<?>> content) {
		assertMutable();
        this.content = content;
    }

    /**
     * Get the children
     * @return children elements
     */
    @Override
	public List<TemplateContentItem<?>> getChildren() {
        return content;
    }

    /**
     * Set the children
     * @param children children elements
     */
    @Override
	public void setChildren(List<TemplateContentItem<?>> children) {
		assertMutable();
        content = children;
    }

}
