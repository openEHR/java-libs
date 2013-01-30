
package br.com.zilics.archetypes.models.am.template.openehrprofile;

import java.util.List;
import java.util.Map;

import br.com.zilics.archetypes.models.am.AMObject;
import br.com.zilics.archetypes.models.am.archetype.Archetype;
import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.annotation.Ignore;
import br.com.zilics.archetypes.models.rm.annotation.MapItem;
import br.com.zilics.archetypes.models.rm.annotation.NotEmpty;
import br.com.zilics.archetypes.models.rm.annotation.RmField;

/**
 * Template Locatable specification
 * @author Humberto
 */
public abstract class TemplateLocatable<T extends TemplateLocatable<?>> extends AMObject {

	private static final long serialVersionUID = -6718705403055149143L;
	
	@EqualsField
	@RmField("path")
	@MapItem
	private String relativePath;

	@EqualsField	
	@MapItem
	private String name;

	@NotEmpty
	@EqualsField
	@MapItem
    private String archetypeId;

	@EqualsField
	@MapItem
	private Integer max;
	@EqualsField
	@MapItem
    private Integer min;
    
    @RmField("Rule")
    @MapItem(key="path", isXmlAttribute = false)
    private Map<String, TemplateStatement> rule;
    
    @Ignore
    TemplateLocatable<?> parent;
    
    @Ignore
    Template ownerTemplate;
    
    @Ignore
    Archetype resolvedArchetype;
    
    @Ignore
    String canonicalPath;
    
    @Ignore
    Integer counter;

    /**
     * Get the relative path
     * @return path to put the {@link br.com.zilics.archetypes.models.rm.common.archetyped.Locatable} on its parent
     */
    public String getRelativePath() {
        return relativePath;
    }

    /**
     * Set the relative path
     * @param relativePath path to put the {@link br.com.zilics.archetypes.models.rm.common.archetyped.Locatable} on its parent
     */
    public void setRelativePath(String relativePath) {
		assertMutable();
        this.relativePath = relativePath;
    }

    /**
     * Get the name
     * @return the new name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name
     * @param name the new name
     */
    public void setName(String name) {
		assertMutable();
        this.name = name;
    }

    /**
     * Get the archetypeId
     * @return the {@link br.com.zilics.archetypes.models.rm.common.archetyped.Locatable} archetype id
     */
    public String getArchetypeId() {
        return archetypeId;
    }

    /**
     * Set the archetypeId
     * @param archetypeId the {@link br.com.zilics.archetypes.models.rm.common.archetyped.Locatable} archetype id
     */
    public void setArchetypeId(String archetypeId) {
		assertMutable();
        this.archetypeId = archetypeId;
    }

    /**
     * Get the max
     * @return how many times (max occurrences) the {@link br.com.zilics.archetypes.models.rm.common.archetyped.Locatable} can occur
     */
    public Integer getMax() {
        return max;
    }

    /**
     * Set the max
     * @param max how many times (max occurrences) the {@link br.com.zilics.archetypes.models.rm.common.archetyped.Locatable} can occur
     */
    public void setMax(Integer max) {
		assertMutable();
        this.max = max;
    }

    /**
     * Get the min
     * @return minimum times (min occurrences) the {@link br.com.zilics.archetypes.models.rm.common.archetyped.Locatable} can occur
     */
    public Integer getMin() {
        return min;
    }

    /**
     * Set the min
     * @param min minimum times (min occurrences) the {@link br.com.zilics.archetypes.models.rm.common.archetyped.Locatable} can occur
     */
    public void setMin(Integer min) {
		assertMutable();
        this.min = min;
    }

    /**
     * Get the rule
     * @return other constraints
     */
    public Map<String, TemplateStatement> getRule() {
        return getMap(rule);
    }

    /**
     * Set the rule
     * @param rule other constraints
     */
    public void setRule(Map<String, TemplateStatement> rule) {
		assertMutable();
        this.rule = rule;
    }
    

    /**
     * Get the parent locatable
     * @return the parent template locatable
     */
    public TemplateLocatable<?> getParent() {
    	return parent;
    }
    
    
    /**
     * The owner of this locatable
     * @return the owner template of this locatable
     */
    public Template getOwnerTemplate() {
    	return ownerTemplate;
    }
    
    
    /**
     * The resolved {@link Archetype} from archetypeId
     * @return the resolved {@link Archetype}
     */
    public Archetype getResolvedArchetype() {
    	return resolvedArchetype;
    }
    
    /**
     * The canonical path of this {@link TemplateLocatable}
     * @return the canonical path
     */
    public String getCanonicalPath() {
    	return canonicalPath;
    }
    
    /**
     * Get the counter attribute (to differentiate siblings with the same archetypeId
     * @return The counter attribute
     */
    public Integer getCounter() {
    	return counter;
    }
    
    
    /**
     * Get the children
     * @return children elements
     */
    public abstract List<T> getChildren();
    /**
     * Set the children
     * @param children children elements
     */
    public abstract void setChildren(List<T> children);
    
}
