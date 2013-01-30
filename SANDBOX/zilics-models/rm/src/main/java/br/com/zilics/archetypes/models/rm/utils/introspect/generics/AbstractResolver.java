package br.com.zilics.archetypes.models.rm.utils.introspect.generics;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.HashMap;
import java.util.Map;

import br.com.zilics.archetypes.models.rm.exception.IntrospectorException;

/**
 * The core engine of the Generics resolver.
 * Actually there exists two concrete types of {@link GenericsResolver}:<ol>
 * <li>{@link BaseResolver}</li>
 * <li>{@link NavigationResolver}</li></ol>
 * 
 * The base resolver is the start point of using resolver. Start with: {@link BaseResolver#makeInstance(Class)}
 * to get a instance and navigate through fields and type parameters (each navigation instantiates a new {@link NavigationResolver}).
 * 
 * @author Humberto Naves
 *
 */
public abstract class AbstractResolver implements GenericsResolver {

	private static final long serialVersionUID = -771417638296518709L;
	private final Class<?> resolvedClass;
	private final AbstractResolver enclosingResolver;

	private final Map<TypeVariable<?>, Type> variableValues = new HashMap<TypeVariable<?>, Type>();

	
	/**
	 * Constructor
	 * @param resolvedClass the resolved class
	 * @param enclosingResolver the enclosing resolver (or null)
	 */
	protected AbstractResolver(Class<?> resolvedClass, AbstractResolver enclosingResolver) {
		this.resolvedClass = resolvedClass;
		this.enclosingResolver = enclosingResolver;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final Class<?> getResolvedClass() {
		return resolvedClass;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final AbstractResolver getEnclosingResolver() {
		return enclosingResolver;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final NavigationResolver resolveSuperclass() throws IntrospectorException {
		return resolveType(this.getResolvedClass().getGenericSuperclass());
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final AbstractResolver resolveField(Field field) throws IntrospectorException {
		if (field == null)
			throw new IntrospectorException("Null field");
		
		if (!field.getDeclaringClass().isAssignableFrom(getResolvedClass()))
			throw new IntrospectorException("Field " + field + " is not of " + getResolvedClass());

		if (field.getDeclaringClass() == getResolvedClass())
			return resolveDeclaredField(field);
		
		return resolveSuperclass().resolveField(field);
	}

	private NavigationResolver resolveDeclaredField(Field field) throws IntrospectorException {
		return resolveType(field.getGenericType());
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final NavigationResolver resolveTypeParameter(String typeParameterName) throws IntrospectorException {
		if (typeParameterName == null)
			throw new IntrospectorException("Null variable name");
		
		for(TypeVariable<?> variable : getResolvedClass().getTypeParameters()) {
			if (typeParameterName.equals(variable.getName()))
				return resolveType(variable);
		}
		throw new IntrospectorException("Invalid type variable: " + typeParameterName);
	}

	/**
	 * {@inheritDoc}
	 */
	public final NavigationResolver resolveType(Type type) throws IntrospectorException {
		if (type == null)
			throw new IntrospectorException("Null type");
		return NavigationResolver.makeInstance(this, type);
	}

	/**
	 * The core of the resolver. This method transforms the generic type to a {@link Class}, based on the map variableValues. Each type
	 * this method encounter a ParameterizedType, it will output the parameters to the variable parametersOut.
	 * @param type the type to resolve
	 * @param parametersOut a map that will contains the new variable discovered during the process
	 * @return the resolved class 
	 * @throws IntrospectorException any exception raised will be converted to this type
	 */
	@SuppressWarnings("unchecked")
	protected final Class<?> getRawType(Type type, Map<TypeVariable<?>, Type> parametersOut) throws IntrospectorException {
		if (type instanceof TypeVariable) {
			TypeVariable<Class<?>> typeVariable = (TypeVariable<Class<?>>) type;
			// Lookup the variable
			Type value = lookup(typeVariable);
			return getRawType(value, parametersOut);
		} else if (type instanceof ParameterizedType) {
			ParameterizedType parameterizedType = (ParameterizedType) type;
			// A match between the ParameterizedType and the type parameters of the resolved class is constructed here
			Class<?> result = (Class<?>) parameterizedType.getRawType();
			if (parametersOut != null && parameterizedType.getActualTypeArguments().length > 0) {
				for(int i = 0; i < result.getTypeParameters().length; i++) {
					parametersOut.put(result.getTypeParameters()[i], parameterizedType.getActualTypeArguments()[i]);
				}
			}
			return result;
		} else if (type instanceof Class) {
			// Nothing to do now
			return (Class<?>) type;
		} else if (type instanceof GenericArrayType) {
			// Generic Array type, for example: List<String>[] (an array of lists of strings)
			GenericArrayType genericArrayType = (GenericArrayType) type;
			Class<?> componentClass = getRawType(genericArrayType.getGenericComponentType(), parametersOut);
			Object instance = Array.newInstance(componentClass, 0);
			return instance.getClass();
		} else if (type instanceof WildcardType) {
			// The wildcard type is resolved to the first upper bound
			// For example (? extends A) is resolved to A
			// ? only is equivalent to ? extends Object
			WildcardType wildcardType = (WildcardType) type;
			return getRawType(wildcardType.getUpperBounds()[0], parametersOut);
		} else throw new IntrospectorException("Unknown type: " + type);
	}
	

	/**
	 * Lookup a variable in the map of all known variables (also calling the enclosing resolver to do it)
	 * @param variable the variable to resolve
	 * @return the generic type of the variable
	 */
	protected final Type lookup(TypeVariable<Class<?>> variable) {
		Type value = lookupDirect(variable);
		if (value != null) return value;
		if (getEnclosingResolver() != null)
			return getEnclosingResolver().lookup(variable);
		return variable.getBounds()[0];
	}
	
	/**
	 * Lookup a variable without calling the enclosing resolver
	 * @param variable the variable to resolve
	 * @return the generic type of the variable
	 */
	private Type lookupDirect(TypeVariable<Class<?>> variable) {
		return variableValues.get(variable);
	}
	
	/**
	 * Set a variable value
	 * @param variable the type variable
	 * @param value the generic type of the variable
	 */
	protected final void setVariableValue(TypeVariable<Class<?>> variable, Type value) {
		variableValues.put(variable, value);
	}
	
	/**
	 * Auxiliary method
	 * @param values the values to set
	 */
	protected final void setVariableValues(Map<TypeVariable<?>, Type> values) {
		variableValues.putAll(values);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (!(obj instanceof AbstractResolver)) return false;
		if (obj.getClass() != this.getClass()) return false;
		
		AbstractResolver other = (AbstractResolver) obj;
		return (this.resolvedClass.equals(other.resolvedClass)) &&
			(enclosingResolver != null ? enclosingResolver.equals(other.enclosingResolver) : other.enclosingResolver == null);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int PRIME = 31;
		int hash = resolvedClass.hashCode();
		hash = hash * PRIME;
		if (enclosingResolver != null)
			hash += enclosingResolver.hashCode();
		return hash;
	}
	
}
