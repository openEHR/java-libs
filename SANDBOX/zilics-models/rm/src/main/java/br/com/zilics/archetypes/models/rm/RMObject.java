package br.com.zilics.archetypes.models.rm;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.com.zilics.archetypes.models.rm.annotation.Ignore;
import br.com.zilics.archetypes.models.rm.exception.ImmutableException;
import br.com.zilics.archetypes.models.rm.exception.ValidateException;
import br.com.zilics.archetypes.models.rm.exception.XmlSerializerException;
import br.com.zilics.archetypes.models.rm.utils.CloneUtils;
import br.com.zilics.archetypes.models.rm.utils.EqualsAndHashcodeUtils;
import br.com.zilics.archetypes.models.rm.utils.ImmutableUtils;
import br.com.zilics.archetypes.models.rm.utils.path.model.ObjectValue;
import br.com.zilics.archetypes.models.rm.utils.validation.ValidationResult;
import br.com.zilics.archetypes.models.rm.utils.validation.ValidationUtils;
import br.com.zilics.archetypes.models.rm.utils.xml.XmlSerializer;

/**
 * Root class of all reference model classes
 * @author Humberto
 */
public abstract class RMObject implements Serializable, Cloneable, ObjectValue {

	private static final long serialVersionUID = -505225362776723345L;

	@Ignore
	boolean immutable = false;
    
	/**
	 * Turn this object immutable
	 * @param immutable
	 */
	public void setImmutable(boolean immutable) {
		ImmutableUtils.setImmutable(this, immutable);
	}
	
	/**
	 * Is the current {@link RMObject} immutable?
	 * @return true if immutable
	 */
	public boolean isImmutable() {
		return immutable;
	}

	/**
	 * Check if it is immutable and throw an exception when immutable=true
	 * @throws ImmutableException The exception thrown
	 */
	protected final void assertMutable() throws ImmutableException {
		if (immutable) throw new ImmutableException("Object is immutable", this);
	}


	
	/**
	 * Returns a immutable collection when immutable = true
	 * @param <T> the generic type
	 * @param collection the collection
	 * @return the immutable collection
	 */
	protected final <T> Collection<T> getCollection(Collection<T> collection) {
		if (immutable)
			return Collections.unmodifiableCollection(collection);
		return collection;
	}

	/**
	 * Returns a immutable list when immutable = true
	 * @param <T> the generic type
	 * @param list the list
	 * @return the immutable list
	 */
	protected final <T> List<T> getList(List<T> list) {
		if (immutable)
			return Collections.unmodifiableList(list);
		return list;
	}
	
	/**
	 * Returns a immutable set when immutable = true  
	 * @param <T> the generic type
	 * @param set the set
	 * @return the immutable set
	 */
	protected final <T> Set<T> getSet(Set<T> set) {
		if (immutable)
			return Collections.unmodifiableSet(set);
		return set;
	}

	/**
	 * Returns a immutable map when immutable = true
	 * @param <K> the first generic type
	 * @param <V> the second generic type
	 * @param map the map
	 * @return the immutable map
	 */
	protected final <K, V> Map<K, V> getMap(Map<K, V> map) {
		if (immutable)
			return Collections.unmodifiableMap(map);
		return map;
	}

	/**
	 * Auxiliary method for calling an object hashCode
	 * @param obj the object
	 * @return its hashCode
	 */
	protected int objectHashCode(Object obj) {
		return EqualsAndHashcodeUtils.objectHashCode(obj);
	}
	
	/**
	 * Auxiliary method for calling equals()
	 * @param o1 first object to compare
	 * @param o2 the second object to compare
	 * @return true if o1 and o2 are equals
	 */
	protected boolean objectEquals(Object o1, Object o2) {
		return EqualsAndHashcodeUtils.objectEquals(o1, o2);
	}
	
	/**
	 * Auxiliary method for calling toString().
	 * @param obj the object to call toString()
	 * @return the string form of the object
	 */
	protected String objectToString(Object obj) {
		if (obj == null) return "null";
		if (!obj.getClass().isArray())
			return obj.toString();
		StringBuilder sb = new StringBuilder("array[");
		int objLength = java.lang.reflect.Array.getLength(obj);
		for(int i = 0; i < objLength; i++) {
			if (i != 0) sb.append(", ");
			sb.append(objectToString(java.lang.reflect.Array.get(obj, i)));
		}
		sb.append("]");
		return sb.toString();
	}
	
	/**
	 * Check if a give string is empty (or null)
	 * @param str the string
	 * @return true if empty or null
	 */
	protected boolean isEmptyString(String str) {
		return (str == null || str.length() == 0);
	}
	
	/**
	 * Validate the given instance and throws an exception in case of an error
	 * @throws ValidateException the exception thrown
	 */
	public final void validate() throws ValidateException {
		ValidationResult result = new ValidationResult();
		validate(result);
		if (result.getItems().size() > 0)
			throw new ValidateException(result);
	}
	/**
	 * Validate the given instance (using {@link ValidationUtils#validateRmObject(RMObject, ValidationResult)})
	 * @param result the validation result
	 */
	public final void validate(ValidationResult result) {
		if (result == null)
			throw new NullPointerException("Result is null");
		ValidationUtils.validateRmObject(this, result);		
	}
	
	/**
	 * Override this method in case of a custom validation procedure
	 * that can't be performed only with @NotNull and @NotEmpty annotations
	 * @param result the validation result
	 */
	protected void performValidation(ValidationResult result) {
	}

	/**
	 * Transforms a {@link RMObject} into String using
	 * the {@link XmlSerializer}
	 * @return the XML form of this object
	 */
	public String toXml() {
		try {
			return XmlSerializer.serializeXml(this, null);
		} catch (XmlSerializerException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
     * {@inheritDoc}
     */
    @Override
    public Object clone() {
    	// calls the Cloneutils
        return CloneUtils.deepCloneSerializable(this);
    }
    
	/**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
    	// calls the EqualsAndHashcodeUtils
    	return EqualsAndHashcodeUtils.rmHashCode(this);
    }

	/**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
    	// calls the EqualsAndHashcodeUtils
    	return EqualsAndHashcodeUtils.rmEquals(this, obj);
    }

}