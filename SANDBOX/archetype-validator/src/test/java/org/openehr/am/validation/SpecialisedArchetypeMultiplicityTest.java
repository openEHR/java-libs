package org.openehr.am.validation;

public class SpecialisedArchetypeMultiplicityTest extends SpecialisedArchetypeValidationTestBase{
	
	public void testCheckArchetypesWithNonConformantMultiplicity() throws Exception {
		checkSpecialization("adl-specialised-EVALUATION.multiplicity-specialised.v1", "adl-specialised-EVALUATION.multiplicity.v1");
		assertEquals("expected one validation error", 1, errors.size());	
		assertFirstErrorType(ErrorType.VSAM);
	}
	
	
	private void checkSpecialization(String name, String parentName) throws Exception {
		archetype = loadArchetype(name);
		parentArchetype = loadArchetype(parentName);
		
		errors = validator.validate(archetype, parentArchetype, true);
	}	
}
