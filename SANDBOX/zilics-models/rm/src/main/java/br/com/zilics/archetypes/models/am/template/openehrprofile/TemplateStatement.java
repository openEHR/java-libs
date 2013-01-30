
package br.com.zilics.archetypes.models.am.template.openehrprofile;

import java.util.Map;

import br.com.zilics.archetypes.models.am.AMObject;
import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.annotation.MapItem;
import br.com.zilics.archetypes.models.rm.annotation.NotEmpty;
import br.com.zilics.archetypes.models.rm.annotation.RmField;

/**
 * Template Statement specification
 *
 * <p>Other constraint to apply on some {@link br.com.zilics.archetypes.models.rm.common.archetyped.Locatable}
 * @author Humberto
 */
public class TemplateStatement extends AMObject {

	private static final long serialVersionUID = 9179156619665086050L;
	
	@EqualsField
	@NotEmpty
	@MapItem
	private String path;

	@EqualsField
	@MapItem
	private String name;
	
	@EqualsField
	@MapItem
	private Integer max;
	@EqualsField
	@MapItem
	private Integer min;
    
	@MapItem
	private Boolean exclusive;
	@MapItem
    private String defaultValue;
    
    @MapItem(key="id")
    @RmField("Metadata")
    private Map<String, String> metadata;

    /**
     * Get the path
     * @return the path of the {@link br.com.zilics.archetypes.models.rm.common.archetyped.Locatable}
     */
    public String getPath() {
        return path;
    }

    /**
     * Set the path
     * @param path the path of the {@link br.com.zilics.archetypes.models.rm.common.archetyped.Locatable}
     */
    public void setPath(String path) {
		assertMutable();
        this.path = path;
    }
    
    /**
     * To override the name of the archetype
     * @return the new name of the constraint
     */
    public String getName() {
    	return name;
    }
    
    /**
     * To override the name of the archetype
     * @param name the new name of the constraint
     */
    public void setName(String name) {
    	assertMutable();
    	this.name = name;
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
     * Is this path exclusive?
     * @return true if exclusive
     */
    public Boolean isExclusive() {
        return exclusive;
    }

    /**
     * Set this path as exclusive
     * @param exclusive true if exclusive
     */
    public void setExclusive(Boolean exclusive) {
		assertMutable();
        this.exclusive = exclusive;
    }

    /**
     * Get the default value of this path
     * @return the default value
     */
    public String getDefaultValue() {
        return defaultValue;
    }

    /**
     * Set the default value of this path
     * @param defaultValue the default value
     */
    public void setDefaultValue(String defaultValue) {
		assertMutable();
        this.defaultValue = defaultValue;
    }

    /**
     * Get metadata associated with this path
     * @return the metadata associated with this path
     */
    public Map<String, String> getMetadata() {
    	return getMap(metadata);
    }
    
    /**
     * set the metadata associated with this path
     * @param metadata the metadata associated with this path
     */
    public void setMetadata(Map<String, String> metadata) {
    	assertMutable();
    	this.metadata = metadata;
    }

}
