package br.com.zilics.archetypes.models.rm.utils;

import br.com.zilics.archetypes.models.rm.RMObject;
import br.com.zilics.archetypes.models.rm.exception.IntrospectorException;
import br.com.zilics.archetypes.models.rm.utils.introspect.IntrospectorData;
import br.com.zilics.archetypes.models.rm.utils.introspect.RmClassData;
import br.com.zilics.archetypes.models.rm.utils.introspect.RmFieldData;

/**
 * Utility class to evaluate an {@link RMObject} hashCode
 * and to check if two instances of {@link RMObject} are equals.
 * 
 * @author Humberto Naves
 *
 */
public final class EqualsAndHashcodeUtils {
	private EqualsAndHashcodeUtils() {}
	
	/**
	 * Auxiliary method to invoke an object hashCode() method. It deals with
	 * the null case and the case when obj is an array.
	 * @param obj the object to hash
	 * @return the hash code of the object
	 */
	public static int objectHashCode(Object obj) {
		final int PRIME = 1000003;
		if (obj == null) return 0;
		if (!obj.getClass().isArray())
			return obj.hashCode();
		int result = 0;
		int objLength = java.lang.reflect.Array.getLength(obj);
		for(int i = 0; i < objLength; i++) {
			result = PRIME * result + objectHashCode(java.lang.reflect.Array.get(obj, i));
		}
		return result;		
	}
	
	/**
	 * Auxiliary method to check if 2 objects are equals. It deals with
	 * the null case and the array case
	 * @param o1 the first object to compare
	 * @param o2 the second object to compare
	 * @return true if o1 and o2 are equals
	 */
	public static boolean objectEquals(Object o1, Object o2) {
		if (o1 == o2) return true;
		if (o1 == null || o2 == null) return false;
		if (!o1.getClass().isArray() || !o2.getClass().isArray())
			return o1.equals(o2);
		int o1Length = java.lang.reflect.Array.getLength(o1);
		if (o1Length != java.lang.reflect.Array.getLength(o2))
			return false;
		for(int i = 0; i < o1Length; i++) {
			if (!objectEquals(java.lang.reflect.Array.get(o1, i), java.lang.reflect.Array.get(o2, i)))
				return false;
		}
		return true;
	}
	
	/**
	 * Evaluate an {@link RMObject} hash code using the Introspector. All fields
	 * annotated with <pre><b>@EqualsField</b></pre> are used by this method.
	 * @param obj the {@link RMObject} to hash
	 * @return the hash code
	 */
	public static int rmHashCode(RMObject obj) {
		if (obj == null) return 0;
		final int PRIME = 31;
		int hash = 0;
		RmClassData rmClass = IntrospectorData.getRmClassDataByJavaClass(obj.getClass());
		for(RmFieldData rmField : rmClass.getAllRmFields()) {
			if (rmField.isEqualsField()) {
				try {
					Object val = rmField.getValue(obj);
					hash = hash * PRIME + objectHashCode(val);
				} catch (IntrospectorException e) {
					throw new RuntimeException("Exception while hashing", e);
				}
				
			}
		}
		return hash;
	}


	/**
	 * Compare two {@link RMObject}s using the Introspector. All fields
	 * annotated with <pre><b>@EqualsField</b></pre> are compared by this method.
	 * @param obj1 the first object to compare
	 * @param obj2 the second object to compare
	 * @return true if obj1 and obj2 are equals
	 */
	public static boolean rmEquals(RMObject obj1, Object obj2) {
		if (obj1 == obj2) return true;
		if (obj1 == null || obj2 == null) return false;
		if (obj1.getClass() != obj2.getClass()) return false;

		RmClassData rmClass = IntrospectorData.getRmClassDataByJavaClass(obj1.getClass());
		for(RmFieldData rmField : rmClass.getAllRmFields()) {
			if (rmField.isEqualsField()) {
				try {
					Object val1 = rmField.getValue(obj1);
					Object val2 = rmField.getValue(obj2);
					if (!objectEquals(val1, val2))
						return false;
				} catch (IntrospectorException e) {
					throw new RuntimeException("Exception while comparing", e);
				}
				
			}
		}
		return true;
	}

}
