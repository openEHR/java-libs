
package br.com.zilics.archetypes.models.am.archetype.constraintmodel;

import java.util.Map;

import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.annotation.MapItem;
import br.com.zilics.archetypes.models.rm.annotation.NotEmpty;
import br.com.zilics.archetypes.models.rm.support.basic.Interval;
import br.com.zilics.archetypes.models.rm.utils.validation.ValidationResult;

/**
 * Constraint on complex objects, i. e. any object that consists of other object
 * constraints
 *
 * @author Humberto
 */
public class CComplexObject extends CDefinedObject {

	private static final long serialVersionUID = 6256445669611414357L;

	@NotEmpty
	@EqualsField
	private String rmTypeName;

	@EqualsField
	@MapItem(key="rm_attribute_name", isXmlAttribute=false)
	private Map<String, CAttribute> attributes;

	/**
	 * Default constructor
	 */
	public CComplexObject() {}
	
    /**
     * Another constructor
     * @param rmTypeName the Reference Model type
     * @param occurrences how many times it occurs
     * @param nodeId the node id
	 * @param attributes the attributes
     */
	public CComplexObject(String rmTypeName, Interval<Integer> occurrences, String nodeId, Map<String, CAttribute> attributes) {
		super(occurrences, nodeId);
		this.rmTypeName = rmTypeName;
		this.attributes = attributes;
		if (attributes == null) setAnyAllowed(true);
	}
	
    /**
     * {@inheritDoc}
     */
	@Override
    public String getRmTypeName() {
        return rmTypeName;
    }

    /**
     * Set the rmTypeName
     * @param rmTypeName Refence Model type that this node corresponds to
     */
    public void setRmTypeName(String rmTypeName) {
		assertMutable();
        this.rmTypeName = rmTypeName;
    }
	
	
    /**
     * Get the attributes
     * @return List of constraints on attributes of the reference model type
     * represented by this object
     */
    public Map<String, CAttribute> getAttributes() {
        return getMap(attributes);
    }

    /**
     * Set the attributes
     * @param attributes List of constraints on attributes of the reference model type
     * represented by this object
     */
    public void setAttributes(Map<String, CAttribute> attributes) {
		assertMutable();
        this.attributes = attributes;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public CComplexObject makeSimpleCopy() {
    	CComplexObject result = new CComplexObject();
    	ConstraintUtils.copyCComplexObjectProperties(this, result);
    	return result;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
    	return "C_COMPLEX_OBJECT[" + getRmTypeName() + "]: " + getCanonicalPath();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void performValidation(ValidationResult result) {
    	super.performValidation(result);
    	if (this.getAnyAllowed() != null && this.getAnyAllowed())
    		if (attributes != null && attributes.size() > 0)
    			result.addItem(this, "Invalid any allowed");
    }    


}
