
package br.com.zilics.archetypes.models.am.archetype.constraintmodel.primitive;

import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.utils.validation.ValidationResult;

/**
 * Constraint on instances of Boolean
 *
 * @author Humberto
 */
public class CBoolean extends CPrimitive {
	private static final long serialVersionUID = 3433127732034486155L;
	@EqualsField
	private boolean trueValid = true;
	@EqualsField
    private boolean falseValid = true;
	@EqualsField
    private Boolean assumedValue;

	/**
	 * Default constructor
	 */
	public CBoolean() {}
	
	/**
	 * Another constructor
	 * @param trueValid if true valid for this constraint
	 * @param falseValid if false valid for this constraint
	 * @param assumedValue the assumed value
	 */
	public CBoolean(boolean trueValid, boolean falseValid, Boolean assumedValue) {
		this.trueValid = trueValid;
		this.falseValid = falseValid;
		this.assumedValue = assumedValue;
	}

    /**
     * Get the trueValid
     * @return True if the value True is allowed
     */
    public boolean isTrueValid() {
        return trueValid;
    }

    /**
     * Set the trueValid
     * @param trueValid True if the value True is allowed
     */
    public void setTrueValid(boolean trueValid) {
		assertMutable();
        this.trueValid = trueValid;
    }

    /**
     * Get the falseValid
     * @return True if the value False is allowed
     */
    public boolean isFalseValid() {
        return falseValid;
    }

    /**
     * Set the falseValid
     * @param falseValid True if the value False is allowed
     */
    public void setFalseValid(boolean falseValid) {
		assertMutable();
        this.falseValid = falseValid;
    }

    /**
     * Get the assumedValue
     * @return Value to be assumed if none sent in data
     */
    @Override
    public Boolean getAssumedValue() {
        return assumedValue;
    }

    /**
     * Set the assumedValue
     * @param assumedValue Value to be assumed if none sent in data
     */
    public void setAssumedValue(Boolean assumedValue) {
		assertMutable();
        this.assumedValue = assumedValue;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
	public final String getType() {
		return "BOOLEAN";
	}
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean defaultValue() {
    	if (trueValid) return Boolean.TRUE;
    	else if (falseValid) return Boolean.FALSE;
    	return null;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValidValue(Object value) {
    	if (!(value instanceof Boolean)) return false;
    	Boolean b = (Boolean) value;
    	if (b) return trueValid;
    	else return falseValid;
    }
 
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValidType(Object value) {
    	return (value instanceof Boolean);
    }

    
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
    	return "C_BOOLEAN[trueValid = " + trueValid + " falseValid = " + falseValid + " assumed = " + assumedValue + "]";
    }

    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void performValidation(ValidationResult result) {
    	super.performValidation(result);
    	if (!trueValid && !falseValid)
    		result.addItem(this, "CBoolean not consistent");
    	if (assumedValue != null) {
    		if ((assumedValue && !trueValid) ||
    			(!assumedValue && !falseValid))
    			result.addItem(this, "Invalid assumed value");
    	}
    }
    
}
