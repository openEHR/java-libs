package br.com.zilics.archetypes.models.rm.utils.path.model;

import junit.framework.TestCase;

public class ListValueTest extends TestCase {
	public ListValueTest(String testName) {
		super(testName);
	}
	
	public void testIsEqual() throws Exception {
		ListValue v1 = new ListValue(new SingleValue("str"));
		ListValue v2 = new ListValue(new SingleValue("str"));
		
		assertEquals(v1, v2);
		assertTrue(v1.isEqual(v2).effectiveBooleanValue());
	}
}
