package org.openehr.am.validation;

public class SpecialisedArchetypeNonCComplexObjectTest extends SpecialisedArchetypeValidationTestBase{
	
	// Checks that there is no class cast exception anywhere for a C_DV_QUANTITY instead of a CComplexObject. 
	// Also checks that a loosening of constraints is being picked up where a choice construct is introduced. 
	public void testCheckArchetypesWithNonConformantMultiplicity() throws Exception {
		checkSpecialization("openEHR-EHR-CLUSTER.test_non_ccomplexobject-spec.v1.adl", "openEHR-EHR-CLUSTER.test_non_ccomplexobject.v1.adl");
		assertEquals("expected one validation error", 1, errors.size());	
		assertFirstErrorType(ErrorType.VSONCT);
	}
	
	
	private void checkSpecialization(String name, String parentName) throws Exception {
		archetype = loadArchetype(name);
		parentArchetype = loadArchetype(parentName);
		
		errors = validator.validate(archetype, parentArchetype, true);
	}	
}
