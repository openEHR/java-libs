package org.openehr.testing;

import junit.framework.TestCase;

public class ArchetypeTestCaseTest extends TestCase {
	
	public void setUp() throws Exception {
		instance = new ArchetypeTestCase("test") {
			
		};
	}
	
	public void testLoadRMInstance() throws Exception {
		assertNotNull("loaded rm instance null",
				instance.loadRMInstance("blood_pressure_001.dadl"));
	}
	
	public void testLoadArcheteype() throws Exception {
		assertNotNull("loaded archetype null", instance.loadArchetype(
				"openEHR-EHR-OBSERVATION.blood_pressure.v1.adl"));
	}
	
	public void testLoadQueryTestSpec() throws Exception {
		assertNotNull("loaded query test specification null",
				ArchetypeTestCase.loadQueryTestCase("query_testcase_1.xml"));
	}
	
	private ArchetypeTestCase instance;
}	
