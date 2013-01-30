
package br.com.zilics.archetypes.models.am.template.openehrprofile;

import java.util.Collections;
import java.util.HashMap;
import java.util.Set;
import java.util.Map;

import br.com.zilics.archetypes.models.am.archetype.Archetype;
import br.com.zilics.archetypes.models.am.archetype.constraintmodel.ArchetypeSlot;
import br.com.zilics.archetypes.models.am.archetype.constraintmodel.CAttribute;
import br.com.zilics.archetypes.models.am.archetype.constraintmodel.CObject;
import br.com.zilics.archetypes.models.am.utils.path.TemplatePathEvaluator;
import br.com.zilics.archetypes.models.am.utils.validation.SemanticValidationResult;
import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.annotation.Ignore;
import br.com.zilics.archetypes.models.rm.annotation.NotEmpty;
import br.com.zilics.archetypes.models.rm.common.resource.AuthoredResource;
import br.com.zilics.archetypes.models.rm.exception.SemanticValidateException;
import br.com.zilics.archetypes.models.rm.utils.path.context.PathEvaluationContext;
import br.com.zilics.archetypes.models.rm.utils.validation.ValidationResult;

/**
 * Template specification (based on the XSD)
 *
 * @author Humberto
 */
public class Template extends AuthoredResource {

	private static final long serialVersionUID = 7502057334064605947L;
	@NotEmpty
	@EqualsField
	private String id;

	@NotEmpty
	@EqualsField
    private String name;
	
	@NotEmpty	
    private TemplateLocatable<?> definition;
    
    @Ignore
    private Map<String, Archetype> allUsedArchetypes;
    
    @Ignore
    Map<String, TemplateLocatable<?>> allLocatables;
    
    @Ignore
    Map<String, TemplateConstraint> allConstraints;

    /**
     * Get the id
     * @return the id of the template
     */
    public String getId() {
        return id;
    }

    /**
     * Set the id
     * @param id the id of the template
     */
    public void setId(String id) {
		assertMutable();
        this.id = id;
    }

    /**
     * Get the name
     * @return the name of the template
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name
     * @param name the name of the template
     */
    public void setName(String name) {
		assertMutable();
        this.name = name;
    }

    /**
     * Get the definition
     * @return the template definition
     */
    public TemplateLocatable<?> getDefinition() {
        return definition;
    }

    /**
     * Set the definition
     * @param definition the template definition
     */
    public void setDefinition(TemplateLocatable<?> definition) {
		assertMutable();
        this.definition = definition;
    }
    
    /**
     * Get the set of string of all archetype ids used by this template. <br/>
     * Before using this method, you should validate this template
     * @return The set of all archetype ids used by this template
     */
    public Set<String> getAllUsedArchetypes() {
    	return getSet(allUsedArchetypes.keySet());
    }
    
    /**
     * Returns a map from archetype_id to {@link Archetype} of all used archetypes
     * of this template. 
     * @return The map of all archetypes
     */
    public Map<String, Archetype> getAllUsedArchetypesMap() {
    	return getMap(allUsedArchetypes);
    }
    
    /**
     * Get one {@link TemplateLocatable} by its canonical path
     * @param path the canonical path of the locatable
     * @return the locatable itself
     */
    public TemplateLocatable<?> getTemplateLocatableFromPath(String path) {
    	if (allLocatables != null)
    		return allLocatables.get(path);
    	return null;
    }
    
    /**
     * Get the root {@link TemplateConstraint} of this {@link Template}
     * @return the root constraint
     */
    public TemplateConstraint getRootConstraint() {
    	if (allConstraints != null)
    		return allConstraints.get("/");
    	return null;
    }
    

    /**
     * Get a {@link TemplateConstraint} by its canonical path
     * @param path the canonical path
     * @return the template constraint
     */
    public TemplateConstraint getConstraintFromPath(String path) {
    	if (allConstraints != null)
    		return allConstraints.get(path);
    	return null;
    }
    
    /**
     * Instantiate an {@link PathEvaluationContext} for performing A-path queries on this template
     * @return The {@link PathEvaluationContext}
     */
    public PathEvaluationContext getPathEvaluatorContext() {
    	return new PathEvaluationContext(new TemplatePathEvaluator(this));
    }
    
    /**
     * Performs the semantic validation based on this template (actually a delegate to {@link TemplateConstraint#performSemanticValidation(Object, SemanticValidationResult)}).
     * @param value The value (usually a RMObject) to be matched against the template constraints
     * @param result The Result of the semantic validation (out)
     */
	public void semanticValidation(Object value, SemanticValidationResult result) {
		if (this.getRootConstraint() == null) throw new NullPointerException("No root constraint");
		this.getRootConstraint().performSemanticValidation(value, result);
	}
	
	/**
	 * Acts like {@link #semanticValidation(Object, SemanticValidationResult)}, but throws
	 * an exception instead of passing a {@link SemanticValidationResult}
	 * @param value The value to be semantic validated
	 * @throws SemanticValidateException An exception containing the {@link SemanticValidationResult}
	 */
	public void semanticValidation(Object value) throws SemanticValidateException {
		SemanticValidationResult result = new SemanticValidationResult();
		semanticValidation(value, result);
		if (result.getItems().size() > 0)
			throw new SemanticValidateException(result);
	}

    /**
     * {@inheritDoc}
     */
    @Override
    protected void performValidation(ValidationResult result) {
    	super.performValidation(result);
    	allUsedArchetypes = new HashMap<String, Archetype>();
    	allLocatables = new HashMap<String, TemplateLocatable<?>>();
    	allConstraints = new HashMap<String, TemplateConstraint>();
    	
    	//Now visit all locatables
    	if (definition != null) {
    		definition.canonicalPath = "";
    		visitTemplateLocatable(definition, result);
    		definition.canonicalPath = "/";
        	if (definition.getResolvedArchetype() != null) {
        		//Now build up the entire tree of TemplateConstraints
        		TemplateConstraint root = new TemplateConstraint(definition, definition.getResolvedArchetype().getDefinition());
        		root.visitAllChildren();
            	allConstraints.put("/", root);
        	}
    	}
    	
    	allUsedArchetypes = Collections.unmodifiableMap(allUsedArchetypes);
    }
    
    
    /**
     * Recursively visit all locatables of this template, validating each one
     * @param locatable the current locatable
     * @param result the validation result
     */
    private void visitTemplateLocatable(TemplateLocatable<?> locatable, ValidationResult result) {
    	locatable.ownerTemplate = this;
    	allUsedArchetypes.put(locatable.getArchetypeId(), locatable.getResolvedArchetype());
    	if (locatable.getResolvedArchetype() != null && locatable.getRule() != null) {
    		for(TemplateStatement statement : locatable.getRule().values()) {
    			if (locatable.getResolvedArchetype().getCObjectFromPath(statement.getPath()) == null)
    				result.addItem(this, "Invalid path: " + statement.getPath() + " in archetype: " + locatable.getArchetypeId());
    		}
    	}
    	
    	if (locatable.getChildren() != null) {
    		Map<String, Integer> counter = new HashMap<String, Integer>();
    		for(TemplateLocatable<?> child : locatable.getChildren()) {
    			int c = 1;
    			String key = child.getRelativePath() + "[" +  child.getArchetypeId() + "]";

    			if (counter.containsKey(key)) c = counter.get(key) + 1;
    			
    			counter.put(key, c);
    			child.canonicalPath = locatable.canonicalPath + key + "[" + c + "]"; 
    			child.counter = c; // to differentiate siblings in the canonical path
    			child.parent = locatable;
    			allLocatables.put(child.canonicalPath, child);

    			if (locatable.getResolvedArchetype() != null) {
    				CAttribute attribute = locatable.getResolvedArchetype().getCAttributeFromPath(child.getRelativePath());
    				boolean ok = false;
    				for(CObject cobj : attribute.getChildren()) {
    					if (cobj instanceof ArchetypeSlot) {
    						//ArchetypeSlot slot = (ArchetypeSlot) cobj;
    						ok = true;
    						break;
    					}
    				}
    				// We must have an slot to fit a template locatable
					if (!ok) result.addItem(this, "Path " + child.getRelativePath() + " should have a slot in archetype "
							+ locatable.getArchetypeId());
    			}
    			visitTemplateLocatable(child, result);
    		}
    	}
    }
    
    /**
     * Set the {@link TemplateLocatable#resolvedArchetype} on all instances of this template. 
     * @param archetypes the map containing all archetypes indexed by its archetype_id.
     */
    public void resolveArchetypes(Map<String, Archetype> archetypes) {
    	resolveTemplateLocatable(definition, archetypes);
    }
    
    /**
     * Auxiliary method to recursively resolve all archetypes of the locatables of this template.
     * @param locatable the current locatable
     * @param archetypes the map containing all archetypes indexed by its archetype_id.
     */
    private void resolveTemplateLocatable(TemplateLocatable<?> locatable, Map<String, Archetype> archetypes) {
    	locatable.resolvedArchetype = archetypes.get(locatable.getArchetypeId());
    	if (locatable.resolvedArchetype == null)
    		throw new NullPointerException("Archetype map doesn't containt archetype: " + locatable.getArchetypeId());
    	if (locatable.getChildren() != null) {
    		for(TemplateLocatable<?> child : locatable.getChildren()) {
    			resolveTemplateLocatable(child, archetypes);
    		}
    	}
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
    	return "Template[" + getName() + "]";
    }
}
