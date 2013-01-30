package org.openehr.am.validation;

public class CodeConstraintValidityTest extends ArchetypeValidationTestBase {
	
	public void testCodeConstraintCheckWithCompleteDef() throws Exception {		
		checkCodeConstraint("adl-test-ENTRY.code_constraint.v1");		
		assertEquals("expected no error in code constraint checking", 
				0, errors.size());
	}
	
	public void testCodeConstraintCheckWithMissingDef() throws Exception {
		
		checkCodeConstraint("adl-test-ENTRY.code_constraint.v2");
		assertEquals("expected 1 error in code constraint checking", 
				1, errors.size());
		assertFirstErrorType(ErrorType.VACDF);
	}
	
	public void testCodeConstraintCheckWithMissingDefInASecondaryLanguage() throws Exception {
		// code constraint exists in primary language, but not in a translation
		checkCodeConstraint("adl-test-ENTRY.code_constraint.v3");
		
		assertEquals("expected 1 error in code constraint checking", 
				1, errors.size());
		assertFirstErrorType(ErrorType.VONLC);
	}
	
	public void testCheckWithNoUnusedConstraints() throws Exception {
		checkCodeConstraint("adl-test-ENTRY.ontology-unusedcodes.v1.adl");
		assertEquals("unexpected validation error: " + errors, 0,	
				errors.size());	
	}
	
	public void testCheckWithUnusedConstraints() throws Exception {
		checkCodeConstraint("adl-test-ENTRY.ontology-unusedcodes.v2.adl");
			assertEquals("unexpected amount of validation errors: " + errors, 1,	
				errors.size());	
		assertFirstErrorType(ErrorType.WOUC);
	}
	
	
	private void checkCodeConstraint(String name) throws Exception {
		archetype = loadArchetype(name);
		validator.checkCodeConstraintValidity(archetype, errors);
	}
	

}
