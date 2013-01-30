package br.com.zilics.archetypes.models.rm.utils.path.model;

import junit.framework.TestCase;

public class SingleValueTest extends TestCase {
	public SingleValueTest(String testName) {
		super(testName);
	}
	
	public void testIsEqual() throws Exception {
		SingleValue v1 = new SingleValue("vv");
		SingleValue v2 = new SingleValue("vv");
		assertEquals(v1, v2);
		
		assertTrue(v1.compare(v2, true) == 0);
	}
}
