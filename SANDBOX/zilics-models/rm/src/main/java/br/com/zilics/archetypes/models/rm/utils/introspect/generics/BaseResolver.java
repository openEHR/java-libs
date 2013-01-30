package br.com.zilics.archetypes.models.rm.utils.introspect.generics;

/**
 * 
 * The base resolver
 * @see AbstractResolver
 * @author Humberto Naves
 *
 */
public final class BaseResolver extends AbstractResolver {

	private static final long serialVersionUID = -5242486008530828346L;

	private BaseResolver(Class<?> resolvedClass) {
		super(resolvedClass, null);
	}
	
	/**
	 * The start point
	 * @param clazz the base of the navigation
	 * @return the generics resolver
	 */
	public static BaseResolver makeInstance(Class<?> clazz) {
		if (clazz == null) throw new NullPointerException("Class is null");
		BaseResolver result = new BaseResolver(clazz);
		return result;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return " > Base[" + getResolvedClass().getName() + "]";
	}	
	
}
