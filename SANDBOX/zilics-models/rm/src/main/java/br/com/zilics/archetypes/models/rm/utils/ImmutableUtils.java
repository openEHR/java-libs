package br.com.zilics.archetypes.models.rm.utils;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Map;

import br.com.zilics.archetypes.models.rm.RMObject;
import br.com.zilics.archetypes.models.rm.RMObjectUtils;

/**
 * Utility class to turn {@link RMObject}s immutable using the Java Reflection API
 * directly (not using the Introspector).
 * 
 * @author Humberto Naves
 *
 */
public final class ImmutableUtils {
	private ImmutableUtils() {}

	/**
	 * Turn the object immutable. This method uses the {@link RMObjectUtils} class that is a "friend" class
	 * of {@link RMObject} is is recursive. Only non-primitive, non-static fields are considered while recursively turning
	 * objects immutable.
	 * 
	 * @param obj the object to turn immutable
	 * @param immutable true if immutable
	 */
	public static void setImmutable(RMObject obj, boolean immutable) {
		// Basic checks
		if (obj == null) return;
		if (obj.isImmutable() == immutable) return;

		// Use the friend class
		RMObjectUtils.setImmutable(obj, immutable);
		
		Class<?> clazz = obj.getClass();
		while(clazz != RMObject.class) {
			for(Field field : clazz.getDeclaredFields()) {
				if (field.getType().isPrimitive()) continue;  // Primitive? argh...
				if (Modifier.isStatic(field.getModifiers())) continue; // Static?
				
				field.setAccessible(true); // Prevents an exception of illegal access
				Object value;
				try {
					value = field.get(obj);
				} catch (Throwable t) {
					throw new RuntimeException("Can't get field value[field=" +field.getName() + ", obj=" + obj + "]" , t);
				}
				setImmutableNextStep(value, immutable);
				
			}
			clazz = clazz.getSuperclass();
		}
	}

	/**
	 * Go deep in value (if a List, Set, Map, or the very combinations of them) searching
	 * for {@link RMObject}'s to call again {@link #setImmutable(RMObject, boolean)}
	 * @param value an object that could be a Collection, a Map, an Array or a RMObject
	 * @param immutable is immutable?
	 */
	private static void setImmutableNextStep(Object value, boolean immutable) {
		if (value == null) return;
		if (value instanceof RMObject) {
			// call setImmutable
			setImmutable((RMObject) value, immutable);
		} else if (value instanceof Collection) {
			Collection<?> collection = (Collection<?>) value;
			for(Object element : collection) {
				// We are going down the structure
				setImmutableNextStep(element, immutable);
			}
		} else if (value.getClass().isArray()) {
			for(int i = 0; i < Array.getLength(value); i++) {
				// We are going down the structure
				setImmutableNextStep(Array.get(value, i), immutable);
			}
		} else if (value instanceof Map) {
			Map<?, ?> map = (Map<?, ?>) value;
			for(Object key : map.keySet()) {
				Object val = map.get(key);
				// We are going down the structure
				setImmutableNextStep(key, immutable);
				// We are going down the structure
				setImmutableNextStep(val, immutable);
			}
		}
	}



}
