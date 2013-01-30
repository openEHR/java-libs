package br.com.zilics.archetypes.models.rm.utils.introspect.generics;

import junit.framework.TestCase;

public class GenericsResolverTest extends TestCase {
	public GenericsResolverTest(String testName) {
		super(testName);
	}
	
	private GenericsResolver resolveTypeVariable(GenericsResolver resolver, String typeVariableName) throws Exception {
		return resolver.resolveTypeParameter(typeVariableName);
	}
	
	public void testGetInstanceSimpleClass() throws Exception {
		GenericsResolver resolver = BaseResolver.makeInstance(SimpleClass.class);
		assertEquals(Object.class, resolveTypeVariable(resolver, "T").getResolvedClass());
	}

	public void testGetInstanceAnotherClass() throws Exception {
		GenericsResolver resolver = BaseResolver.makeInstance(AnotherClass.class);
		assertEquals(Object.class, resolveTypeVariable(resolver, "T").getResolvedClass());
	}

	public void testGetInstanceUmmClass() throws Exception {
		GenericsResolver resolver = BaseResolver.makeInstance(UmmClass.class);
		assertNotNull(resolver);
	}

}
