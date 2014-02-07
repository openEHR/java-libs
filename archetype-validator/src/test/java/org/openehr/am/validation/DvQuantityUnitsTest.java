package org.openehr.am.validation;

public class DvQuantityUnitsTest extends ArchetypeValidationTestBase {
	
	public void testCheckUnits() throws Exception {
	    CheckUnits("openEHR-EHR-CLUSTER.units_test.v1.adl");
		assertEquals("expected 10 wrong units", 10, errors.size());	
		assertFirstErrorType(ErrorType.VUI);
		assertSecondErrorType(ErrorType.VUI);
	}
	
	
	private void CheckUnits(String name) throws Exception {
		archetype = loadArchetype(name);
		validator.checkObjectConstraints(archetype, errors);
	}
}
