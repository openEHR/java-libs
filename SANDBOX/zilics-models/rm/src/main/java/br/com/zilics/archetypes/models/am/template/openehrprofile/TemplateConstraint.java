package br.com.zilics.archetypes.models.am.template.openehrprofile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import br.com.zilics.archetypes.models.am.archetype.Archetype;
import br.com.zilics.archetypes.models.am.archetype.constraintmodel.ArchetypeInternalRef;
import br.com.zilics.archetypes.models.am.archetype.constraintmodel.ArchetypeSlot;
import br.com.zilics.archetypes.models.am.archetype.constraintmodel.CAttribute;
import br.com.zilics.archetypes.models.am.archetype.constraintmodel.CComplexObject;
import br.com.zilics.archetypes.models.am.archetype.constraintmodel.CObject;
import br.com.zilics.archetypes.models.am.utils.validation.SemanticValidationResult;
import br.com.zilics.archetypes.models.am.utils.validation.SemanticValidationUtils;
import br.com.zilics.archetypes.models.rm.support.basic.Interval;
import br.com.zilics.archetypes.models.rm.utils.path.model.ObjectValue;

/**
 * Utility class for navigating through the template constraints. <br/> 
 * A template constraint is a pair of one {@link TemplateLocatable} and
 * one {@link CObject} (inside the corresponding {@link TemplateLocatable#getResolvedArchetype()})
 * indicating an element on the expanded constraints of the {@link Template}.
 * @author Humberto Naves
 */
public final class TemplateConstraint implements Serializable, ObjectValue {

	private static final long serialVersionUID = 7600018161610697803L;
	
	private final TemplateLocatable<?> templateLocatable;
	private final CObject archetypeConstraint;
	
	/**
	 * The package protected constructor
	 * @param templateLocatable the {@link TemplateLocatable}
	 * @param archetypeConstraint the {link CObject} inside the resolved archetype
	 */
	TemplateConstraint(TemplateLocatable<?> templateLocatable, CObject archetypeConstraint) {
		if (templateLocatable == null)
			throw new NullPointerException("Null locatable");		
		if (archetypeConstraint == null)
			throw new NullPointerException("Null constraint");
		this.templateLocatable = templateLocatable;
		this.archetypeConstraint = archetypeConstraint;
	}
	
	/**
	 * Getter method for the archetype constraint
	 * @return the archetype constraint
	 */
	public CObject getArchetypeConstraint() {
		return archetypeConstraint;
	}
	
	/**
	 * Getter method for the {@link TemplateLocatable} 
	 * @return the template locatable
	 */
	public TemplateLocatable<?> getTemplateLocatable() {
		return templateLocatable;
	}
	
	/**
	 * For which template this constraints is a member of
	 * @return the owner template
	 */
	public Template getOwnerTemplate() {
		return templateLocatable.getOwnerTemplate();
	}
	
	/**
	 * Get the full path of this constraint. <br/>
	 * @return the full path of the {@link TemplateConstraint}
	 */
	public String getFullPath() {
		return getFullPath(this.templateLocatable, this.archetypeConstraint);
	}

	/**
	 * Get the parent constraint in the tree of {@link TemplateConstraint}s.
	 * @see #getParentCAttribute()
	 * @return the parent constraint in the tree of constraints
	 */
	public TemplateConstraint getParentConstraint() {
		CAttribute parentAttribute = getParentCAttribute();
		// is the parent in the same locatable?
		TemplateLocatable<?> locatable = templateLocatable;
		if (archetypeConstraint.getParent() == null) {
			locatable = templateLocatable.getParent();
		}
		if (parentAttribute == null) return null;
		return resolveConstraint(locatable, parentAttribute.getOwnerConstraint());
	}
	
	/**
	 * Returns the parent {@link CAttribute} of the {@link TemplateConstraint}s.<br/>
	 * Note that is <b>NOT</b> the same as <pre>getArchetypeConstraint().getParent()</pre>
	 * because if your template constraint represents a root constraint of one {@link TemplateLocatable}
	 * the parent constraint in that case is the corresponding {@link CAttribute} of the
	 * {@link br.com.zilics.archetypes.models.am.archetype.constraintmodel.ArchetypeSlot}
	 * @return the parent {@link CAttribute}
	 */
	public CAttribute getParentCAttribute() {
		if (archetypeConstraint.getParent() == null) {
			if (templateLocatable.getParent() != null) {
				TemplateLocatable<?> parentLocatable = templateLocatable.getParent();
				Archetype resolvedArchetype = parentLocatable.getResolvedArchetype();
				if (resolvedArchetype == null)
					throw new NullPointerException("Null resolved archetype");
				return resolvedArchetype.getCAttributeFromPath(templateLocatable.getRelativePath());
			} else {
				return null;
			}
		} else {
			return archetypeConstraint.getParent();
		}		
	}
	
	/**
	 * Visit all children to build up the entire tree of {@link TemplateConstraint}s.
	 */
	void visitAllChildren() {
		if (archetypeConstraint instanceof CComplexObject) {
			final CComplexObject complex = (CComplexObject) archetypeConstraint;
			if (complex.getAttributes() != null) {
				ArrayList<TemplateConstraint> children = new ArrayList<TemplateConstraint>();
				for(CAttribute attribute : complex.getAttributes().values()) {
					if (attribute != null)
						getChildrenFromAttribute(attribute, children);
				}
				for(TemplateConstraint child : children) {
					if (child != null)
						child.visitAllChildren();
				}
			}
		}
	}
	
	/**
	 * Get root constraint of this {@link TemplateLocatable}.
	 * This corresponds to a {@link CObject} that is the {@link br.com.zilics.archetypes.models.am.archetype.Archetype#getDefinition()}
	 * of the {@link TemplateLocatable#resolvedArchetype}
	 * @return the root constraint of template locatable
	 */
	public TemplateConstraint getTemplateLocatableRootConstraint() {
		return getTemplateLocatableRootConstraint(templateLocatable);
	}
	
	/**
	 * Get all children template constraints inside the attribute whose name is attributeName 
	 * @param attributeName the name of the attribute
	 * @return the list of children
	 */
	public List<TemplateConstraint> getChildrenFromAttribute(String attributeName) {
		if (attributeName == null) throw new NullPointerException("Null attribute name");
		List<TemplateConstraint> result = Collections.emptyList();
		
		if (archetypeConstraint instanceof CComplexObject) {
			final CComplexObject complex = (CComplexObject) archetypeConstraint;
			if (complex.getAttributes() != null) {
				CAttribute attribute = complex.getAttributes().get(attributeName);
				if (attribute != null) {
					result = new ArrayList<TemplateConstraint>();
					getChildrenFromAttribute(attribute, result);
				}
			}
		}
		return result;
	}
	
	/**
	 * Get all children template constraints inside the attribute whose name is attributeName and with non zero occurrences 
	 * @param attributeName the name of the attribute
	 * @return the list of children
	 */
	public List<TemplateConstraint> getNonZeroOccurrencesChildrenFromAttribute(String attributeName) {
		List<TemplateConstraint> result = getChildrenFromAttribute(attributeName);
		Iterator<TemplateConstraint> it = result.iterator();
		while(it.hasNext()) {
			TemplateConstraint tc = it.next();
			if (tc.isZeroOccurrences()) it.remove();
		}
		return result;
	}

	public String getNodeName(String language) {
		if(archetypeConstraint.getParent() == null){
			if (templateLocatable.getName() != null)
				return templateLocatable.getName();
		} else {
			if(templateLocatable.getRule() != null){
				TemplateStatement st = templateLocatable.getRule().get(archetypeConstraint.getCanonicalPath());
				if(st != null && st.getName() != null)
					return st.getName();
			}
		}
		return archetypeConstraint.getNodeName(language);
	}
	
	/**
	 * Get the modified occurrences.<br/>
	 * This is somehow a delegate method to {@link CObject#getOccurrences()} of the
	 * {@link #archetypeConstraint}, but if there is a {@link TemplateStatement} rule
	 * changing the "max" and "min" attributes, this method changes the value
	 * from the {@link CObject}
	 * @return the occurrences attribute
	 */
	public Interval<Integer> getOccurrences() {
		if (templateLocatable.getRule() != null) {
			TemplateStatement st = templateLocatable.getRule().get(archetypeConstraint.getCanonicalPath());
			if (st != null) {
				Interval<Integer> occurrences = new Interval<Integer>();
				occurrences.setLower(archetypeConstraint.getOccurrences().getLower());
				occurrences.setUpper(archetypeConstraint.getOccurrences().getUpper());
				if (st.getMin() != null) occurrences.setLower(st.getMin());
				if (st.getMax() != null) occurrences.setUpper(st.getMax());
				return occurrences;
			}
		}
		return archetypeConstraint.getOccurrences();
	}
	
    /**
     * Check if is zero occurrences
     * @return true if zero occurrences
     */
    public boolean isZeroOccurrences() {
    	Interval<Integer> occurrences = getOccurrences();
    	if (occurrences != null) {
    		if (occurrences.isUpperUnbounded())
    			return false;
    		return (occurrences.getUpper() == 0);
    	}
    	return false;
    }
    
    /**
     * Check if constraint is required (ocurrences.min > 0)
     * @return true if required, false otherwise
     */
    public boolean isRequired(){
    	Interval<Integer> occurrences = getOccurrences();
    	if (occurrences != null) {
    		if(occurrences.isLowerUnbounded())
    			return false;
    		return occurrences.isLowerIncluded() ? occurrences.getLower() > 0 : occurrences.getLower() >= 0;
    	}
    	return false;
    }
    
    /**
     * Check if is multiple occurrences
     * @return true if multiple occurrences
     */
    public boolean isMultipleOccurrences() {
    	Interval<Integer> occurrences = getOccurrences();
    	if (occurrences != null) {
    		if (occurrences.isUpperUnbounded())
    			return true;
    		return (occurrences.getUpper() > 1);
    	}
    	return false;
    }
    
	/**
	 * Get a metadata by its name
	 * @param key the name of the metadata
	 * @return its value
	 */
	public String getMetadata(String key) {
		if (templateLocatable.getRule() != null) {
			TemplateStatement st = templateLocatable.getRule().get(archetypeConstraint.getCanonicalPath());
			if (st != null && st.getMetadata() != null) {
				if (st.getMetadata().containsKey(key))
					return st.getMetadata().get(key);
			}
		}
		return archetypeConstraint.getMetadata(key);
	}
	
    /**
     * A delegate method to perform semantic validation directly from the {@link TemplateConstraint}
     * @param value the value to validate
     * @param result the semantic validation result
     */
	public void performSemanticValidation(Object value, SemanticValidationResult result) {
		SemanticValidationUtils.validateCObject(value, this.getArchetypeConstraint(), this, result);
	}


	/**
	 * Auxiliary method for getting the template constraint representing the root constraint of a locatable.
	 * @param templateLocatable the {@link TemplateLocatable}
	 * @return the template constraint representing the root of this locatable
	 */
	private TemplateConstraint getTemplateLocatableRootConstraint(TemplateLocatable<?> templateLocatable) {
		if (templateLocatable.getResolvedArchetype() == null)
			throw new NullPointerException("Null resolved archetype");
		return resolveConstraint(templateLocatable, templateLocatable.getResolvedArchetype().getDefinition());		
	}


	/**
	 * Auxiliary method to get the children template constraints of the {@link CAttribute}
	 * @param attribute the {@link CAttribute} in question
	 * @param result the list
	 */
	private void getChildrenFromAttribute(CAttribute attribute, List<TemplateConstraint> result) {
		if (attribute.getChildren() != null) {
			boolean hasSlot = false;
			for(CObject child : attribute.getChildren()) {
				if (child instanceof ArchetypeSlot) {
					hasSlot = true;
				} else {
					if (child instanceof ArchetypeInternalRef) {
						ArchetypeInternalRef ref = (ArchetypeInternalRef) child;
						child = ref.getTargetConstraint();
						if (child == null) continue;
					}
					result.add(resolveConstraint(templateLocatable, child));
				}
			}
			// Check for the slot
			if (hasSlot)
				if (templateLocatable.getChildren() != null && attribute.getCanonicalPath() != null)
					for(TemplateLocatable<?> locatableChild : templateLocatable.getChildren()) {
						if (attribute.getCanonicalPath().equals(locatableChild.getRelativePath())) {
							result.add(getTemplateLocatableRootConstraint(locatableChild));
						}
					}
		}
	}
	
	/**
	 * Check if the owner {@link Template} already has one {@link TemplateConstraint} with
	 * these properties. If it already contains, then return the cached constraint. Or else
	 * instantiate a new one. 
	 * @param templateLocatable the {@link TemplateLocatable}
	 * @param archetypeConstraint the {@link CObject}
	 * @return the new {@link TemplateConstraint} (or the cached one)
	 */
	private TemplateConstraint resolveConstraint(TemplateLocatable<?> templateLocatable, CObject archetypeConstraint) {
		String fullPath = getFullPath(templateLocatable, archetypeConstraint);
		Template owner = getOwnerTemplate();
		if (owner == null) throw new NullPointerException("Null owner template");
		if (fullPath == null) throw new NullPointerException("Null path");
		if (!owner.allConstraints.containsKey(fullPath)) {
			TemplateConstraint result = new TemplateConstraint(templateLocatable, archetypeConstraint);
			owner.allConstraints.put(fullPath, result);
		}
		return owner.allConstraints.get(fullPath);
	}
	

	/**
	 * Get the full path of a template constraint whose locatable and archetype constraint are defined
	 * by the parameters bellow.
	 * @param templateLocatable the {@link TemplateLocatable}
	 * @param archetypeConstraint the {@CObject}
	 * @return the full canonical path
	 */
	private static String getFullPath(TemplateLocatable<?> templateLocatable, CObject archetypeConstraint) {
		if (templateLocatable == null)
			throw new NullPointerException("Null locatable");		
		if (archetypeConstraint == null)
			throw new NullPointerException("Null constraint");
		if ("/".equals(archetypeConstraint.getCanonicalPath()))
			return templateLocatable.getCanonicalPath();
		else if ("/".equals(templateLocatable.getCanonicalPath()))
			return archetypeConstraint.getCanonicalPath();
		else
			return "" + templateLocatable.getCanonicalPath() + archetypeConstraint.getCanonicalPath();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (!(obj instanceof TemplateConstraint)) return false;
		
		TemplateConstraint other = (TemplateConstraint) obj;
		
		return this.templateLocatable.equals(other.templateLocatable) &&
			this.archetypeConstraint.equals(other.archetypeConstraint);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int PRIME = 31;
		int hash = templateLocatable.hashCode();
		hash = hash * PRIME + archetypeConstraint.hashCode();
		return hash;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return getFullPath(templateLocatable, archetypeConstraint);
	}
	

}
