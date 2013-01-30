package br.com.zilics.archetypes.models.rm.utils.introspect;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.com.zilics.archetypes.models.rm.RMObject;
import br.com.zilics.archetypes.models.rm.annotation.Ignore;
import br.com.zilics.archetypes.models.rm.annotation.RmClass;
import br.com.zilics.archetypes.models.rm.exception.IntrospectorException;

/**
 * Represents an information about a class {@link RMObject}.
 * 
 * @author Humberto Naves
 */
public final class RmClassData implements Serializable {
	private static final long serialVersionUID = 3650892412954066131L;
	
	private final Class<?> javaClass;
	private final String rmClassName;
	private final RmClassData parentClassData;
	
	private final Map<String, RmFieldData> fieldsByRmName = new LinkedHashMap<String, RmFieldData>();
	private final List<RmClassData> children = new ArrayList<RmClassData>();
	
	/**
	 * Package protected constructor (used by {@link InstrospectorData})
	 * @param javaClass the java class
	 * @param parentClassData the Introspector data of the superclass
	 */
	RmClassData(Class<?> javaClass, RmClassData parentClassData) {
		if (javaClass == null) throw new NullPointerException("Class is null");
		if (!RMObject.class.isAssignableFrom(javaClass)) throw new IllegalArgumentException("Class is not an RMObject");

		this.javaClass = javaClass;
		this.parentClassData = parentClassData;
		
		if (javaClass != RMObject.class) {
			if (parentClassData == null || parentClassData.getJavaClass() != javaClass.getSuperclass())
				throw new IllegalArgumentException("Invalid parent");
			// Add a reference to a child class
			parentClassData.children.add(this);
		} else if (parentClassData != null)
			throw new IllegalArgumentException("Parent is null");
		
		if (javaClass.isAnnotationPresent(RmClass.class)) {
			// The official name is in the annotation
			RmClass annotation = javaClass.getAnnotation(RmClass.class);
			this.rmClassName = annotation.value();
		} else {
			// The official name is automatically generated
			this.rmClassName = IntrospectorUtils.camelCaseFormatToUnderlineFormat(javaClass.getSimpleName(), true);
		}
		
		// Put all fields known by parentClassData 
		if (parentClassData != null)
			fieldsByRmName.putAll(parentClassData.fieldsByRmName);
		
		// Search only declared fields (because the other fields are already in the parentClassData)
		for (Field javaField : javaClass.getDeclaredFields()) {
			// Static and ignored fields are ignored.
			if (Modifier.isStatic(javaField.getModifiers())) continue;
			if (javaField.isAnnotationPresent(Ignore.class)) continue;
			
			RmFieldData rmFieldData = new RmFieldData(javaField, this);
			fieldsByRmName.put(rmFieldData.getRmFieldName(), rmFieldData);
		}
		
	}
	
	/**
	 * Get java class
	 * @return the java class
	 */
	public Class<?> getJavaClass() {
		return javaClass;
	}
	
	/**
	 * Get the official name
	 * @return the official name
	 */
	public String getRmClassName() {
		return rmClassName;
	}
	
	/**
	 * Get the Introspector data of the superclass
	 * @return the super class data
	 */
	public RmClassData getParentClassData() {
		return parentClassData;
	}
	
	/**
	 * Get the Introspector data of a field by its official name
	 * @param rmFieldName official name of the field
	 * @return the Introspector data of the field
	 */
	public RmFieldData getRmFieldByRmName(String rmFieldName) {
		return fieldsByRmName.get(rmFieldName);
	}
	
	/**
	 * Get the Java field (Reflection API) by its official name
	 * @param rmFieldName the official name of the field
	 * @return the Java field
	 */
	public Field getJavaFieldByRmName(String rmFieldName) {
		RmFieldData rmField = getRmFieldByRmName(rmFieldName);
		if (rmField == null) return null;
		return rmField.getJavaField();
	}
	
	/**
	 * Get all official names
	 * @return all the official names of the fields
	 */
	public Set<String> getAllFieldRmNames() {
		return fieldsByRmName.keySet();
	}
	
	/**
	 * Get all the Introspector data about the fields
	 * @return the fields
	 */
	public Collection<RmFieldData> getAllRmFields() {
		return fieldsByRmName.values();
	}
	
	/**
	 * Is it an abstract class?
	 * @return is it an abstract class?
	 */
	public boolean isAbstractClass() {
		return Modifier.isAbstract(javaClass.getModifiers());
	}
	
	/**
	 * Returns a list of Introspector data containing all classes that are subclasses of it and are concrete
	 * @return the list of concrete subclasses
	 */
	public List<RmClassData> getConcreteSubclasses() {
		ArrayList<RmClassData> result = new ArrayList<RmClassData>();
		if (!isAbstractClass()) result.add(this);
		for(int i = 0; i < children.size(); i++) {
			RmClassData child = children.get(i);
			result.addAll(child.getConcreteSubclasses());
		}
		return result;
	}
	
	/**
	 * Create a new instance of the class
	 * @return the new instance
	 * @throws IntrospectorException any exception occurred will be thrown as an {@link IntrospectorException}
	 */
	public RMObject newInstance() throws IntrospectorException {
		RMObject result = null;
		try {
			result = (RMObject) javaClass.newInstance();
		} catch (Throwable t) {
			throw new IntrospectorException("Can't instantiate class: " + javaClass, t);
		}		
		return result;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return javaClass.hashCode();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object o) {
		if (o == this) return true;
		if (!(o instanceof RmClassData)) return false;
		RmClassData other = (RmClassData) o;
		return javaClass.equals(other.javaClass);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(rmClassName);
		sb.append("[").append(javaClass.getSimpleName()).append("]");
		sb.append(" {\n");
		for(RmFieldData rmField : getAllRmFields()) {
			sb.append("   ").append(rmField).append("\n");
		}
		sb.append("}\n");
		return sb.toString();
	}
}
