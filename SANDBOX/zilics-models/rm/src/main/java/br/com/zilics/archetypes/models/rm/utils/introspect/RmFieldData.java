package br.com.zilics.archetypes.models.rm.utils.introspect;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.com.zilics.archetypes.models.rm.annotation.MapItem;
import br.com.zilics.archetypes.models.rm.annotation.NotEmpty;
import br.com.zilics.archetypes.models.rm.annotation.NotNull;
import br.com.zilics.archetypes.models.rm.annotation.RmField;
import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.exception.IntrospectorException;

/**
 * The Introspector data about a field
 * 
 * @author Humberto Naves
 *
 */
public final class RmFieldData implements Serializable {

	private static final long serialVersionUID = 311885929541702106L;
	
	private final Field javaField;
	private final String rmFieldName;
	private final RmClassData ownerRmClass;
	private final boolean equalsField;
	private final boolean notNull, notEmpty;
	private final String mapKey;
	private final boolean xmlAttribute;
	
	/**
	 * Package protected constructor (used by {@link RmClassData})
	 * @param javaField the Java field
	 * @param ownerRmClass the Introspector data of the owner class
	 */
	RmFieldData(Field javaField, RmClassData ownerRmClass) {
		if (javaField == null)
			throw new NullPointerException("Field is null");
		if (ownerRmClass == null)
			throw new NullPointerException("Owner class is null");
		this.javaField = javaField;
		this.ownerRmClass = ownerRmClass;
		// Check for some annotations
		if (javaField.isAnnotationPresent(RmField.class)) {
			RmField annotation = javaField.getAnnotation(RmField.class);
			this.rmFieldName = annotation.value();
		} else {
			this.rmFieldName = IntrospectorUtils.camelCaseFormatToUnderlineFormat(javaField.getName(), false);
		}
		if (javaField.isAnnotationPresent(NotEmpty.class)) {
			this.notEmpty = this.notNull = true;
		} else {
			if (javaField.isAnnotationPresent(NotNull.class)) {
				this.notNull = true;
			} else {
				this.notNull = false;
			}
			this.notEmpty = false;
		}
		if (javaField.isAnnotationPresent(MapItem.class)) {
			MapItem annotation = javaField.getAnnotation(MapItem.class);
			this.mapKey = annotation.key();
			this.xmlAttribute = annotation.isXmlAttribute();
		} else {
			this.mapKey = null;
			this.xmlAttribute = false;
		}
		if (javaField.isAnnotationPresent(EqualsField.class)) {
			this.equalsField = true;
		} else {
			this.equalsField = false;
		}
	}

	/**
	 * Get the Java API reflection field
	 * @return the field
	 */
	public Field getJavaField() {
		return javaField;
	}

	/**
	 * Get the official name
	 * @return the official name
	 */
	public String getRmFieldName() {
		return rmFieldName;
	}

	/**
	 * Get the owner class data
	 * @return the owner data
	 */
	public RmClassData getOwnerRmClass() {
		return ownerRmClass;
	}
	
	/**
	 * Is this field annotated with the @EqualsField
	 * @return is it an equals field
	 */
	public boolean isEqualsField() {
		return equalsField;
	}
	
	/**
	 * Is this field annotated with the @NotNull
	 * @return is it an not null field
	 */
	public boolean isNotNull() {
		return notNull;
	}

	/**
	 * Is this field annotated with the @NotEmpty
	 * @return is it an not empty field
	 */
	public boolean isNotEmpty() {
		return notEmpty;
	}
	
	/**
	 * Get the name of the map key
	 * @see MapItem
	 * @return the name of the map key
	 */
	public String getMapKey() {
		return mapKey;
	}
	
	/**
	 * Is this field an XML attribute?
	 * @return Is this field an XML attribute?
	 */
	public boolean isXmlAttribute() {
		return xmlAttribute;
	}

	/**
	 * Get the value of a field by reflection
	 * @param instance the object (from which we will recover the field value)
	 * @return the field value
	 * @throws IntrospectorException any exception occurred will be thrown as an {@link IntrospectorException}
	 */
	public Object getValue(Object instance) throws IntrospectorException {
		try {
			javaField.setAccessible(true);
			return javaField.get(instance);
		} catch (Throwable t) {
			throw new IntrospectorException("Can't get field value for " + rmFieldName + "/" + ownerRmClass + " in instance " + instance, t);
		}
	}

	/**
	 * Set the value of a field by reflection 
	 * @param instance the object
	 * @param value the value to set
	 * @throws IntrospectorException any exception occurred will be thrown as an {@link IntrospectorException}
	 */
	public void setValue(Object instance, Object value) throws IntrospectorException {
		try {
			javaField.setAccessible(true);
			javaField.set(instance, value);
		} catch (Throwable t) {
			throw new IntrospectorException("Can't set field value for " + rmFieldName + "/" + ownerRmClass + " in instance " + instance + " to value " + value, t);
		}
	}
	
	/**
	 * Utility method to check if the type is a collection
	 * @return is it a collection field?
	 */
	public boolean isCollectionField() {
		return (Collection.class.isAssignableFrom(javaField.getType()));
	}

	/**
	 * Utility method to check if the type is a map
	 * @return is it a map field?
	 */
	public boolean isMapField() {
		return (Map.class.isAssignableFrom(javaField.getType()));
	}

	/**
	 * Utility method to check if the type is a set
	 * @return is it a set field?
	 */
	public boolean isSetField() {
		return (Set.class.isAssignableFrom(javaField.getType()));
	}
	
	/**
	 * Utility method to check if the type is a list
	 * @return is it a list field?
	 */
	public boolean isListField() {
		return (List.class.isAssignableFrom(javaField.getType()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return javaField.hashCode();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object o) {
		if (o == this) return true;
		if (!(o instanceof RmFieldData)) return false;
		RmFieldData other = (RmFieldData) o;
		return javaField.equals(other.javaField);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(rmFieldName);
		sb.append("[").append(javaField.getName()).append("]");
		sb.append(": ");
		if (equalsField) sb.append(" [equals]");
		if (notNull) sb.append(" required");
		if (notEmpty) sb.append(" not empty");
		if (mapKey != null) sb.append(" Map[key = \"")
		                      .append(mapKey).append("\", isXmlAttribute=")
		                      .append(xmlAttribute).append("]");
		return sb.toString();
	}

}
