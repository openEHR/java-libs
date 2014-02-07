package org.openehr.am.validation;

public class SpecializationArchetypeIdTest extends ArchetypeValidationTestBase{
	
	public void testCheckArchetypeWithCorrectParentIdentifier() throws Exception {
		checkSpecializationParentIdentifierValidity("adl-test-ELEMENT.specialization-archetypeid.v1.adl");
		assertEquals("expected no validation error", 0,	errors.size());	
	}
	
	public void testCheckArchetypeWithWrongParentIdentifier() throws Exception {
		checkSpecializationParentIdentifierValidity("adl-test-ELEMENT.specialization-archetypeid.v2.adl");
		assertEquals("expected validation error", 1, errors.size());
		assertFirstErrorType(ErrorType.VASID);
	}
	
	public void testCheckArchetypeWithDraftParentIdentifier() throws Exception {
		checkSpecializationParentIdentifierValidity("adl-test-ELEMENT.specialization-archetypeid.v3.adl");
		assertEquals("expected validation error", 1, errors.size());
		assertFirstErrorType(ErrorType.VASID);
	}
	
	public void testCheckArchetypeWithoutSpecialization() throws Exception {
		checkSpecializationParentIdentifierValidity("openEHR-EHR-EVALUATION.adverse.v1.adl");
		assertEquals("expected no validation error", 0,	errors.size());	
	}
	
	private void checkSpecializationParentIdentifierValidity(String name) throws Exception {
		archetype = loadArchetype(name);
		validator.checkSpecializationParentIdentifierValidity(archetype, errors);
	}	
}
