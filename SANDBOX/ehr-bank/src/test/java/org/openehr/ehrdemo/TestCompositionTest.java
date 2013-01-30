package org.openehr.ehrdemo;

import org.openehr.rm.composition.*;

import junit.framework.TestCase;

public class TestCompositionTest extends TestCase {
	
	public void setUp() {
		instance = new TestComposition();
	}
	
	public void testCreateTestComposition() throws Exception {
		comp = instance.glucoseComposition();
		assertNotNull("test composition null", comp);
	}
	
	public void testCeateVersionsWithComposition() throws Exception {
		comp = instance.glucoseComposition();
		String uid = "939cec48-d629-4a3f-89f1-28c57338100::10aec661-5458-4ff6-8e63-c2265537196d::1";
		assertNotNull("test version null", 
				instance.createVersionsWithComposition(uid, comp));
	}
	
	private TestComposition instance;
	private Composition comp;
}
