package org.openehr.am.openehrprofile.datatypes.quantity;

import org.openehr.rm.datatypes.text.CodePhrase;

import junit.framework.TestCase;

public class OrdinalTest extends TestCase {
	public void testEquals() {
		Ordinal o1 = new Ordinal(1, new CodePhrase("local", "at0001"));
		Ordinal o2 = new Ordinal(1, new CodePhrase("local", "at0001"));
		assertTrue("equals expected", o1.equals(o2));
		assertTrue("equals expected", o2.equals(o1));
	}
	
	public void testCreateOrdinalWithNegativeValue() {
		try {
			new Ordinal(-1, new CodePhrase("local", "at0001"));
		} catch(Exception e) {
			fail("failed to create ordinal with negative value");
		}
	}
}
