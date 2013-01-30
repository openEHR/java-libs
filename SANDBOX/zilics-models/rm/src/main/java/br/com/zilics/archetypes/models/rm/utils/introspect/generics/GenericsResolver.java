package br.com.zilics.archetypes.models.rm.utils.introspect.generics;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import br.com.zilics.archetypes.models.rm.exception.IntrospectorException;

/**
 * Represents a resolver for generic types.
 * A major difficulty found while programming the new Introspector was the use of
 * Generics in Java. There is no way to know that an instance of List is actually
 * an instance of List&lt;Integer&gt; only by instance itself (supposing that you don't use cheap tricks :-)
 * ...  for that you must have access who declared that instance (either a field declaration, or a local variable declaration or a method parameter declaration, etc). So the GenericsResolver was created to navigate the fields of classes while keeping information on the TypeVariable of generic classes:
 * 
 * Example:
 * <pre>
 * public class A extends RMObject {
 *   private List &lt;Map&lt;String, A&gt;&gt; complexField;
 * }
 * </pre>
 * And let <code>x = BaseResolver.makeInstance (A.class);</code><br />
 * then:
 * <pre>
 *  x.getResolvedClass() == A.class
 *  x.resolveField(A.class.getDeclaredField("complexField")).getResolvedClass() == List.class
 *  x.resolveField(A.class.getDeclaredField("complexField")).resolveTypeParameter("E").getResolvedClass() == Map.class
 *  x.resolveField(A.class.getDeclaredField("complexField")).resolveTypeParameter("E").resolveTypeParameter("K") == String.class
 *  x.resolveField(A.class.getDeclaredField("complexField")).resolveTypeParameter("E").resolveTypeParameter("V") == A.class
 *  </pre> 
 * In other words, the resolver can browse generic types and generic fields.
 * 
 * @author Humberto Naves
 *
 */
public interface GenericsResolver extends Serializable {
	/**
	 * Get the resolved class for this resolver
	 * @return the resolved class
	 */
	public Class<?> getResolvedClass();
	
	/**
	 * Get the enclosing resolver (the previous resolver in the navigation chain)
	 * @return the enclosing resolver
	 */
	public GenericsResolver getEnclosingResolver();

	/**
	 * Navigate through a type parameter of the generic type (for example, in the class
	 * List<E>, <E> is a type variable that is also a type parameter of the class List)
	 * @param typeParameterName the name of the type variable
	 * @return the resulting resolver
	 * @throws IntrospectorException any exception raised will be converted to this type
	 */
	public GenericsResolver resolveTypeParameter(String typeParameterName) throws IntrospectorException;
	
	/**
	 * Resolve the field type (generic type)
	 * @param field the field to resolve
	 * @return the resulting resolver
	 * @throws IntrospectorException any exception raised will be converted to this type
	 */
	public GenericsResolver resolveField(Field field) throws IntrospectorException;
	
	/**
	 * Let's assume for example these two classes:
	 * <pre>
	 * public class A<T> {
	 *   private T field1;
	 * }
	 * 
	 * public class B extends A&lt;B&gt; {
	 * }</pre>
	 * 
	 * How to resolve the type of the field1 for the class B? First step is to navigate from the subclass to
	 * the superclass, and that is accomplished by this method.
	 * 
	 * @return the resolver for the superclass
	 * @throws IntrospectorException any exception raised will be converted to this type
	 */
	public GenericsResolver resolveSuperclass() throws IntrospectorException;
	
	/**
	 * Try to resolve any generic type
	 * @param type the generic type to resolve
	 * @return the resolver
	 * @throws IntrospectorException any exception raised will be converted to this type
	 */
	public GenericsResolver resolveType(Type type) throws IntrospectorException;
}
