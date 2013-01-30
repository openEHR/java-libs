
package br.com.zilics.archetypes.models.am.archetype.constraintmodel;

import br.com.zilics.archetypes.models.am.archetype.constraintmodel.primitive.CPrimitive;
import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.utils.validation.ValidationResult;

/**
 * Constraint on a primitive type
 *
 * @author Humberto
 */
public class CPrimitiveObject extends CDefinedObject {

	private static final long serialVersionUID = -6880423264261620149L;
	@EqualsField
	private CPrimitive item;

    /**
     * Get the item
     * @return Object actually defining the constraint
     */
    public CPrimitive getItem() {
        return item;
    }

    /**
     * Set the item
     * @param item Object actually defining the constraint
     */
    public void setItem(CPrimitive item) {
		assertMutable();
        this.item = item;
    }
    
    /**
     * {@inheritDoc}
     */
	@Override
    public String getRmTypeName() {
		if (item != null)
			return item.getType();
        return null;
    }
	
    /**
     * {@inheritDoc}
     */
    @Override
    public CPrimitiveObject makeSimpleCopy() {
    	CPrimitiveObject result = new CPrimitiveObject();
    	ConstraintUtils.copyCPrimitiveObjectProperties(this, result);
    	return result;
    }	

    
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
    	return "C_PRIMITIVE_OBJECT[" + item + "]: " + getCanonicalPath();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void performValidation(ValidationResult result) {
    	super.performValidation(result);
    	if (this.getAnyAllowed() != null && this.getAnyAllowed())
    		if (item != null) result.addItem(this, "Invalid any allowed");
    }    

}
