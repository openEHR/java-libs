package br.com.zilics.archetypes.models.rm.exception;

import br.com.zilics.archetypes.models.rm.utils.validation.ValidationResult;

/**
 * A static validation exception (used by {@link br.com.zilics.archetypes.models.rm.RMObject#validate()})
 * @author Humberto Naves
 *
 */
public class ValidateException extends Exception {

	private static final long serialVersionUID = -4728783233999104819L;
	private final ValidationResult result;
	
	/**
	 * Constructor
	 * @param result the list of validation errors
	 */
	public ValidateException(ValidationResult result) {
		super("\n" + result);
		this.result = result;
	}
	
	/**
	 * Get the validation issues 
	 * @return the list of all issues
	 */
	public ValidationResult getResult() {
		return result;
	}
	
    /**
     * {@inheritDoc}
     */
    @Override
	public String toString() {
    	if (result != null)
    		return result.toString() + "\n" + super.toString();
    	else
    		return super.toString();
    }


}
