package org.openehr.am.validation;

public class SpecialisedArchetypeExistenceTest extends SpecialisedArchetypeValidationTestBase{
	
	public void testCheckArchetypesWithNonConformantExistence() throws Exception {
		checkSpecialization("adl-specialised-EVALUATION.existence-specialised.v1", "adl-specialised-EVALUATION.existence.v1");
		assertEquals("expected one validation error", 1, errors.size());	
		assertFirstErrorType(ErrorType.VSANCE);
	}
	
	
	private void checkSpecialization(String name, String parentName) throws Exception {
		archetype = loadArchetype(name);
		parentArchetype = loadArchetype(parentName);
		
		errors = validator.validate(archetype, parentArchetype, true);
	}	
}
