
package br.com.zilics.archetypes.models.am.archetype.constraintmodel;

import java.util.Map;

import br.com.zilics.archetypes.models.am.archetype.ontology.ArchetypeTerm;
import br.com.zilics.archetypes.models.am.archetype.ontology.CodeDefinitionSet;
import br.com.zilics.archetypes.models.am.utils.validation.SemanticValidationResult;
import br.com.zilics.archetypes.models.am.utils.validation.SemanticValidationUtils;
import br.com.zilics.archetypes.models.rm.annotation.MapItem;
import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.annotation.Ignore;
import br.com.zilics.archetypes.models.rm.annotation.NotNull;
import br.com.zilics.archetypes.models.rm.datatypes.basic.DataValue;
import br.com.zilics.archetypes.models.rm.support.basic.Interval;
import br.com.zilics.archetypes.models.rm.utils.validation.ValidationResult;

/**
 * Abstract model of constraint on any kind of object node
 *
 * @author Humberto
 */
public abstract class CObject extends ArchetypeConstraint {
	private static final long serialVersionUID = -7990215798762303477L;
	
	@NotNull
	@EqualsField
    private Interval<Integer> occurrences;
    @EqualsField
    private String nodeId;
    @EqualsField
    private Boolean anyAllowed = Boolean.FALSE;
    
    @MapItem
    private Map<String, String> metadata;
    
    @Ignore
    CAttribute parent;

    /**
     * Default constructor
     */
    public CObject() {}
    
    /**
     * Another constructor
     * @param occurrences how many times it occurs
     * @param nodeId the node id
     */
    public CObject(Interval<Integer> occurrences, String nodeId) {
    	this.occurrences = occurrences;
    	this.nodeId = nodeId;
    }
    
    /**
     * Get the rmTypeName
     * @return Refence Model type that this node corresponds to
     */
    public abstract String getRmTypeName();


    /**
     * Get the occurrences
     * @return Occurrences of this object node in the data, under the owning attribute.
     * Upper limit can only be greater than 1 if owning attribute has
     * a cardinality of more than 1.
     */
    public Interval<Integer> getOccurrences() {
        return occurrences;
    }

    /**
     * Set the occurrences
     * @param occurrences Occurrences of this object node in the data, under the owning attribute.
     * Upper limit can only be greater than 1 if owning attribute has
     * a cardinality of more than 1.
     */
    public void setOccurrences(Interval<Integer> occurrences) {
		assertMutable();
        this.occurrences = occurrences;
    }

    /**
     * Get the nodeId
     * @return Semantic id of this node, used to differentiate sibling nodes of the same
     * type. Each <i>nodeId</i> must be defined in the archetype ontology as a term
     * code
     */
    public String getNodeId() {
        return nodeId;
    }

    /**
     * Set the nodeId
     * @param nodeId Semantic id of this node, used to differentiate sibling nodes of the same
     * type. Each <i>nodeId</i> must be defined in the archetype ontology as a term
     * code
     */
    public void setNodeId(String nodeId) {
		assertMutable();
        this.nodeId = nodeId;
    }

    /**
     * Allows any object to match this constraint
     * @return any allowed
     */
    public Boolean getAnyAllowed() {
    	return anyAllowed;
    }
    
    /**
     * Allows any object to match this constraint
     * @param anyAllowed any allowed
     */
    public void setAnyAllowed(Boolean anyAllowed) {
    	assertMutable();
    	this.anyAllowed = anyAllowed;
    }
    
    /**
     * Get the metadata
     * @return the metadata
     */
    public Map<String, String> getMetadata() {
    	return getMap(metadata);
    }
    
    /**
     * Set the metadata
     * @param metadata the metadata
     */
    public void setMetadata(Map<String, String> metadata) {
    	assertMutable();
    	this.metadata = metadata;
    }

    /**
     * Get a metadata by its name
     * @param key the name of the metadata
     * @return the metadata value
     */
    public String getMetadata(String key) {
    	if (metadata != null)
    		return metadata.get(key);
    	return null;
    }

    /**
     * Attribute that owns this CObject
     * @return the owner attribute of this CObject
     */
    public CAttribute getParent() {
    	return parent;
    }

    /**
     * Get the corresponding definition from this term
     * @param language the language defining this term
     * @return the corresponding {@link ArchetypeTerm}
     */
    public ArchetypeTerm getDefinition(String language) {
    	if (getOwnerArchetype() != null) {
    		ArchetypeTerm term = getOwnerArchetype().getTerm(language, getCanonicalPath());
    		if(term == null){
    			term = getOwnerArchetype().getTerm(language, getNodeId());
    		}
    		return term;
    	}
    	return null;    	
    }
    
	/**
     * Get the node name in the given language
     * @param language the language of the node
     * @return the name of the node in the give language
     */
    public String getNodeName(String language) {
    	ArchetypeTerm term = getDefinition(language);
    	if (term == null || term.getItems() == null) return null;
    	return term.getItems().get("text");
    }
    
    /**
     * Check if is zero occurrences
     * @return true if zero occurrences
     */
    public boolean isZeroOccurrences() {
    	if (occurrences != null) {
    		if (occurrences.isUpperUnbounded())
    			return false;
    		return (occurrences.getUpper() == 0);
    	}
    	return false;
    }
    
    /**
     * Check if is multiple occurrences
     * @return true if multiple occurrences
     */
    public boolean isMultipleOccurrences() {
    	if (occurrences != null) {
    		if (occurrences.isUpperUnbounded())
    			return true;
    		return (occurrences.getUpper() > 1);
    	}
    	return false;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public abstract CObject makeSimpleCopy();
    
    
    /**
     * A delegate method to perform semantic validation directly from the {@link CObject}
     * @see br.com.zilics.archetypes.models.am.archetype.Archetype#semanticValidation(Object, SemanticValidationResult) 
     * @param value the value to validate
     * @param result the semantic validation result
     */
	public void performSemanticValidation(Object value, SemanticValidationResult result) {
		SemanticValidationUtils.validateCObject(value, this, null, result);
	}

    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void performValidation(ValidationResult result) {
    	super.performValidation(result);
    	if (occurrences == null) return;
    	
    	if (occurrences.isLowerUnbounded() ||
        		!occurrences.isLowerIncluded() ||
        		(occurrences.getLower() != null && occurrences.getLower() < 0))
        		result.addItem(this, "Invalid lower attribute of occurrences");
        	if (parent != null) {
        		if (parent instanceof CSingleAttribute) {
        			if (occurrences.isUpperUnbounded() ||
        				!occurrences.isUpperIncluded() ||
        			    (occurrences.getUpper() != null && occurrences.getUpper() > 1))
        				result.addItem(this, "Invalid occurrences attribute");
        		} else {
        			if (!occurrences.isUpperUnbounded()) {
        				if (!occurrences.isUpperIncluded())
        					result.addItem(this, "Invalid occurrences attribute");
        			}
        		}
        	}
    }

	public int findFirstChildIndexOfType(DataValue datavalue) {
		// TODO Auto-generated method stub
		return 0;
	}    
    

}
