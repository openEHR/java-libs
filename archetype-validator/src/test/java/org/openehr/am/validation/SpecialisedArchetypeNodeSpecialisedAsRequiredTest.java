package org.openehr.am.validation;

public class SpecialisedArchetypeNodeSpecialisedAsRequiredTest extends SpecialisedArchetypeValidationTestBase{
	
	public void testCheckArchetypesWithNodeThatShouldBeSpecialised() throws Exception {
		checkSpecialization("adl-specialised-EVALUATION.node_specialised_as_required-specialised.v1", "adl-specialised-EVALUATION.node_specialised_as_required.v1");
		assertEquals("expected one validation error", 1, errors.size());	
		assertFirstErrorType(ErrorType.VSONIR);
	}

	
	private void checkSpecialization(String name, String parentName) throws Exception {
		archetype = loadArchetype(name);
		parentArchetype = loadArchetype(parentName);
		
		errors = validator.validate(archetype, parentArchetype, true);
	}	
}
