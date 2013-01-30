
package br.com.zilics.archetypes.models.rm.support.identification;

import java.util.HashSet;
import java.util.Set;

import br.com.zilics.archetypes.models.rm.utils.validation.ValidationResult;

/**
 * Identifier for parties in a demographic or identity service.
 * There are typically a number of subtypes of the PARTY class,
 * including PERSON, ORGANISATION, etc. Abstract supertypes are allowed
 * if the referenced object is of a type not known by the current
 * implementation of this class (in other words, if the demographic model
 * is changed by the addition of a new PARTY or ACTOR subtypes, valid
 * {@link PartyRef} can still be constructed to them).
 *
 * @author Humberto
 */
public class PartyRef extends ObjectRef {

	private static final long serialVersionUID = 6736416125536712543L;
	private static final Set<String> validTypes;
	
	static {
		validTypes = new HashSet<String>();
		validTypes.add("PERSON");
		validTypes.add("ORGANISATION");
		validTypes.add("GROUP");
		validTypes.add("AGENT");
		validTypes.add("ROLE");
		validTypes.add("PARTY");
		validTypes.add("ACTOR");
	}
	
	@Override
    protected void performValidation(ValidationResult result) {
    	super.performValidation(result);
		if (!validTypes.contains(getType()))
			result.addItem(this, "Invalid type: " + getType());
		if (!"DEMOGRAPHIC".equals(getNamespace()))
			result.addItem(this, "Invalid namespace: " + getNamespace());		
	}

}
