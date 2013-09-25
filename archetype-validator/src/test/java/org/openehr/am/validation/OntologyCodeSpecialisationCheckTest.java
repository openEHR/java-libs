package org.openehr.am.validation;

public class OntologyCodeSpecialisationCheckTest extends ArchetypeValidationTestBase {
	
	public void testCheckWithNoCodeSpecialisation() throws Exception {
		checkCodeSpecialisation("adl-test-ENTRY.ontology-specialisation.v1.adl");
		assertEquals("unexpected validation error: " + errors, 0,	
				errors.size());	
	}
	
	public void testCheckWithCorrectCodeSpecialisation() throws Exception {
		checkCodeSpecialisation("adl-test-ENTRY.ontology-specialisation.v2.adl");
		assertEquals("unexpected validation error: " + errors, 0,	
				errors.size());	
	}
	
	public void testCheckWithCorrectThreeLevelSpecialisation() throws Exception {
		checkCodeSpecialisation("adl-test-ENTRY.ontology-specialisation.v5.adl");
		assertEquals("unexpected validation error: " + errors, 0,	
				errors.size());	
	}
	
	public void testCheckWithGreaterSpecialisationLevelInTermDef() throws Exception {
		checkCodeSpecialisation("adl-test-ENTRY.ontology-specialisation.v3.adl");
		assertFirstErrorType(ErrorType.VONSD);
	}

	public void testCheckWithGreaterSpecialisationLevelInConstraintDef() throws Exception {
		checkCodeSpecialisation("adl-test-ENTRY.ontology-specialisation.v4.adl");
		assertFirstErrorType(ErrorType.VONSD);
	}
	
	private void checkCodeSpecialisation(String name) throws Exception {
		archetype = loadArchetype(name);
		validator.checkOntologyCodeSpecialisationLevelValidity(archetype, errors);
	}
}
