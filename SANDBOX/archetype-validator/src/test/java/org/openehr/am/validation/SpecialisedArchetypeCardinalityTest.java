package org.openehr.am.validation;

public class SpecialisedArchetypeCardinalityTest extends SpecialisedArchetypeValidationTestBase{
	
	public void testCheckArchetypesWithCorrectCardinalitySpecialisation() throws Exception {
		checkSpecialization("adl-specialised-EVALUATION.cardinality-specialised.v1", "adl-specialised-EVALUATION.cardinality.v1");
		assertEquals("expected no validation error", 0,	errors.size());	
	}
	
	public void testCheckArchetypesWithIncorrectCardinalitySpecialisation() throws Exception {
		checkSpecialization("adl-specialised-EVALUATION.cardinality-specialised.v2", "adl-specialised-EVALUATION.cardinality.v1");
		assertEquals("expected one validation error", 1, errors.size());	
		assertFirstErrorType(ErrorType.VSANCC);
	}
	
	private void checkSpecialization(String name, String parentName) throws Exception {
		archetype = loadArchetype(name);
		parentArchetype = loadArchetype(parentName);
		
		errors = validator.validate(archetype, parentArchetype, true);
	}	
}
