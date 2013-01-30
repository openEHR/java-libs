package org.openehr.am.validation;

public class SpecialisedArchetypeTermExistsTest extends SpecialisedArchetypeValidationTestBase{
	
		public void testCheckArchetypesWithCorrectTermsInParent() throws Exception {
		checkSpecialization("adl-specialised-EVALUATION.term_existence-specialised.v1", "adl-specialised-EVALUATION.term_existence.v1");
		assertEquals("expected no validation errors", 0, errors.size());	
	}
	
	
	public void testCheckArchetypesWithMissingTermInParent() throws Exception {
		checkSpecialization("adl-specialised-EVALUATION.term_existence-specialised.v2", "adl-specialised-EVALUATION.term_existence.v2");
		assertEquals("expected one validation error", 1, errors.size());	
		assertFirstErrorType(ErrorType.VATDF);
	}
	
	public void testCheckArchetypesWithSecondLevelNodeThatIsCorrectlyUsedInTheThirdLevel() throws Exception {
		checkSpecialization("openEHR-EHR-OBSERVATION.order1-order2-order3.v1.adl", "openEHR-EHR-OBSERVATION.order1-order2.v1.adl");
		assertEquals("expected no validation error", 0, errors.size());	
	}
	
	public void testCheckArchetypesWithSecondLevelNodeThatIsUsedInThirdLevelButNotPresentInSecondLevel() throws Exception {
		checkSpecialization("openEHR-EHR-OBSERVATION.order1-order2-order3a.v1.adl", "openEHR-EHR-OBSERVATION.order1-order2.v1.adl");
		assertEquals("expected no validation error", 1, errors.size());
		assertFirstErrorType(ErrorType.VATDF);
	}
	
	private void checkSpecialization(String name, String parentName) throws Exception {
		archetype = loadArchetype(name);
		parentArchetype = loadArchetype(parentName);
		
		errors = validator.validate(archetype, parentArchetype, true);
	}	
}
