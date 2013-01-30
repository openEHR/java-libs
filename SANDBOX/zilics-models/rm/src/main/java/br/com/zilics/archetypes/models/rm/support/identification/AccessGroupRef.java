
package br.com.zilics.archetypes.models.rm.support.identification;

import br.com.zilics.archetypes.models.rm.utils.validation.ValidationResult;

/**
 * Reference to access group in an access control service.
 *
 * @author Humberto
 */
public class AccessGroupRef extends ObjectRef {

	private static final long serialVersionUID = 1069695840884551384L;

	@Override
	protected void performValidation(ValidationResult result) {
		super.performValidation(result);
		if (!"ACCESS_GROUP".equals(getType()))
			result.addItem(this, "Invalid type: " + getType());
		if (!"ACCESS_CONTROL".equals(getNamespace()))
			result.addItem(this, "Invalid namespace: " + getNamespace());
	}
}
