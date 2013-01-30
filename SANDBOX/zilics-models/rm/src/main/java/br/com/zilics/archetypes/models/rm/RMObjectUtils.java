package br.com.zilics.archetypes.models.rm;

import br.com.zilics.archetypes.models.rm.utils.validation.ValidationResult;

/**
 * Mimics a C friend class for RMObject 
 * @author Humberto Naves
 */
public final class RMObjectUtils {
	private RMObjectUtils() {}

	/**
	 * Accessor for the package protected field immutable of {@link RMObject}
	 * @param obj the {@link RMObject}
	 * @param immutable the value
	 */
	public static void setImmutable(RMObject obj, boolean immutable) {
		obj.immutable = immutable;
	}
	
	/**
	 * Accessor method for the protected method {@link RMObject#performValidation(ValidationResult)}
	 * @param instance the instance to call the method
	 * @param result the {@link ValidationResult}
	 */
	public static void performValidation(RMObject instance, ValidationResult result) {
		instance.performValidation(result);
	}	
	
}
