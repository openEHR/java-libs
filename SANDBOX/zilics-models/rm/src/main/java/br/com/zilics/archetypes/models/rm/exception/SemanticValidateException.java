package br.com.zilics.archetypes.models.rm.exception;

import br.com.zilics.archetypes.models.am.utils.validation.SemanticValidationResult;

/**
 * A semantic validation exception (used by {@link br.com.zilics.archetypes.models.am.utils.validation.SemanticValidationUtils#validateCObject(Object, br.com.zilics.archetypes.models.am.archetype.constraintmodel.CObject, br.com.zilics.archetypes.models.am.template.openehrprofile.TemplateConstraint, SemanticValidationResult)})
 * @author Humberto Naves
 *
 */
public class SemanticValidateException extends Exception {

	private static final long serialVersionUID = -6445401566108743543L;
	private final SemanticValidationResult result;

	/**
	 * Constructor
	 * @param result the list of validation errors
	 */
	public SemanticValidateException(SemanticValidationResult result) {
		super("\n" + result);
		this.result = result;
	}
	
	/**
	 * Get the validation issues 
	 * @return the list of all issues
	 */
	public SemanticValidationResult getResult() {
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
