package br.com.zilics.archetypes.models.rm.utils.introspect.generics;

import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.HashMap;
import java.util.Map;

import br.com.zilics.archetypes.models.rm.exception.IntrospectorException;

/**
 * Resolver instantiated for each navigation
 * 
 * @author Humberto Naves
 *
 */
public class NavigationResolver extends AbstractResolver {

	private static final long serialVersionUID = 1428330941918748382L;
	
	private final Type navigation;

	private NavigationResolver(Class<?> resolvedClass, AbstractResolver enclosingResolver, Type navigation) {
		super(resolvedClass, enclosingResolver);
		this.navigation = navigation;
	}
	
	/**
	 * Auxiliary method called by the {@link AbstractResolver#resolveType(Type)}
	 * @param enclosingResolver the enclosing resolver
	 * @param navigation the type to resolver
	 * @return the new formed resolver
	 * @throws IntrospectorException any exception will be converted to this type
	 */
	static NavigationResolver makeInstance(AbstractResolver enclosingResolver, Type navigation) throws IntrospectorException {
		Map<TypeVariable<?>, Type> variables = new HashMap<TypeVariable<?>, Type>();
		Class<?> resolvedClass = enclosingResolver.getRawType(navigation, variables);
		NavigationResolver result = new NavigationResolver(resolvedClass, enclosingResolver, navigation);
		result.setVariableValues(variables);
		return result;
	}
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (!super.equals(obj)) return false;
		
		NavigationResolver other = (NavigationResolver) obj;
		return navigation.equals(other.navigation);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int PRIME = 31;
		int hash = super.hashCode();
		hash = hash * PRIME + navigation.hashCode();
		return hash;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return getEnclosingResolver().toString() + " > Navigation[" + navigation + "]";
	}	
	

}
