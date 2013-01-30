package org.openehr.ehrdemo;

import org.openehr.rm.composition.Composition;
import org.openehr.schemas.v1.COMPOSITION;

import junit.framework.TestCase;

public class RMBindingTest extends TestCase {
	
	public void setUp() throws Exception {
		binding = new RMBinding();
		composition = TestComposition.compositionWithSingleEntry();
		xmlComposition = XMLBinding.convert(composition);
	}
	
	public void tearDown() throws Exception {
		composition = null;
		xmlComposition = null;
	}
	
	public void testBindComposition() throws Exception {
		newComposition = binding.bindToRM(xmlComposition);
		assertNotNull("newComposition null", newComposition);
		assertTrue(newComposition instanceof Composition);
		assertEquals(composition, newComposition);
	}
	
	private Composition composition;
	private Object newComposition;
	private COMPOSITION xmlComposition;
	private RMBinding binding;
}
