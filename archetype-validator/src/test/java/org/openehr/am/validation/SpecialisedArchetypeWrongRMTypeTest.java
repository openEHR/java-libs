package org.openehr.am.validation;

public class SpecialisedArchetypeWrongRMTypeTest extends SpecialisedArchetypeValidationTestBase{
	
    /*TODO 1.0.5-SNAPSHOT
	public void testCheckArchetypesWithConformantRMTypeForChoice() throws Exception {
		checkSpecialization("adl-specialised-EVALUATION.wrongrmtype-specialised.v1", "adl-specialised-EVALUATION.wrongrmtype.v1");
		assertEquals("expected no validation errors", 0, errors.size());	
	}
    */
	public void testCheckArchetypesWithNonConformantRMType() throws Exception {
		checkSpecialization("adl-specialised-EVALUATION.wrongrmtype-specialised.v2", "adl-specialised-EVALUATION.wrongrmtype.v2");
		assertEquals("expected one validation error", 1, errors.size());	
		assertFirstErrorType(ErrorType.VSONCT);
	}
	
	public void testCheckArchetypesWithRMTypeWithGenericsWrong() throws Exception {
		checkSpecialization("adl-specialised-EVALUATION.wrongrmtype-specialised.v3", "adl-specialised-EVALUATION.wrongrmtype.v3");
		assertEquals("expected one validation error", 1, errors.size());	
		assertFirstErrorType(ErrorType.VSONCT);
	}
	
	private void checkSpecialization(String name, String parentName) throws Exception {
		archetype = loadArchetype(name);
		parentArchetype = loadArchetype(parentName);
		
		errors = validator.validate(archetype, parentArchetype, true);
	}	
}
