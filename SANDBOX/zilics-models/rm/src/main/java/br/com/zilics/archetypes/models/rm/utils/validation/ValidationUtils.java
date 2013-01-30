package br.com.zilics.archetypes.models.rm.utils.validation;

import java.util.Collection;
import java.util.Map;

import br.com.zilics.archetypes.models.rm.RMObject;
import br.com.zilics.archetypes.models.rm.RMObjectUtils;
import br.com.zilics.archetypes.models.rm.utils.introspect.IntrospectorData;
import br.com.zilics.archetypes.models.rm.utils.introspect.RmClassData;
import br.com.zilics.archetypes.models.rm.utils.introspect.RmFieldData;

/**
 * Main validation routines
 * 
 * @author Humberto Naves
 *
 */
public final class ValidationUtils {
	private ValidationUtils() {
	}

	/**
	 * Recursively go deep in value (if a List, Set, Map, or the very combinations of them) searching
	 * for {@link RMObject}'s to call again {@link #validateRmObject(RMObject, ValidationResult)}
	 * and also check for @NotNull and @NotEmpty fields
	 * @param obj the object owning value
	 * @param value the field value (or its descendant members in case of List, Map and Collections)
	 * @param field the containing value
	 * @param result the result of the validation
	 */
	private static void validateNextStep(RMObject obj, Object value, RmFieldData field, ValidationResult result) {
		if (value == null) {
			// Check for @NotNull and @NotEmpty
			if (field != null && field.isNotNull() || field.isNotEmpty()) {
				result.addItem(obj, "Field " + field.getJavaField().getName() + " of " + field.getJavaField().getDeclaringClass().getSimpleName() + " is null");
			}
		} else {
			if (value instanceof String) {
				// Check for @NotEmpty for strings
				if (field != null && field.isNotEmpty() && ((String) value).length() == 0)
					result.addItem(obj, "Field " + field.getJavaField().getName() + " of " + field.getJavaField().getDeclaringClass().getSimpleName() + " is empty");
			} else if (value instanceof RMObject) {
				validateRmObject((RMObject) value, result);
			} else if (value instanceof Collection) {
				Collection<?> collection = (Collection<?>) value;
				// Check for @NotEmpty for collections
				if (field != null && field.isNotEmpty() && collection.size() == 0)
					result.addItem(obj, "Field " + field.getJavaField().getName() + " of " + field.getJavaField().getDeclaringClass().getSimpleName() + " is empty");
				for (Object element : collection) {
					validateNextStep(obj, element, null, result);
				}
			} else if (value instanceof Map) {
				Map<?, ?> map = (Map<?, ?>) value;
				// Check for @NotEmpty for maps
				if (field != null && field.isNotEmpty() && map.size() == 0)
					result.addItem(obj, "Field " + field.getJavaField().getName() + " of " + field.getJavaField().getDeclaringClass().getSimpleName() + " is empty");
				for (Object key : map.keySet()) {
					Object val = map.get(key);
					validateNextStep(obj, key, null, result);
					validateNextStep(obj, val, null, result);
				}
			}
		}
	}

	/**
	 * Validate a given {@link RMObject} and put the results into result
	 * @param obj the object to validate
	 * @param result the validation result
	 */
	public static void validateRmObject(RMObject obj, ValidationResult result) {
		if (obj == null)
			return;

		RmClassData rmClass = IntrospectorData.getRmClassDataByJavaClass(obj.getClass());
		for (RmFieldData field : rmClass.getAllRmFields()) {
			if (field.getJavaField().getType().isPrimitive()) continue;
			Object value = null;
			try {
				value = field.getValue(obj);
			} catch (Throwable t) {
				result.addItem(obj, "Can't get field value[field=" + field.getJavaField().getName() + "]", t);
			}
			// Invoke recursively
			validateNextStep(obj, value, field, result);
		}
		// Call the custom validation method
		RMObjectUtils.performValidation(obj, result);
	}
}
