package org.openehr.am.validation;

public class OntologyTranslationCheckTest extends ArchetypeValidationTestBase {
	
	public void testTranslationCheckWithNoTranslation() throws Exception {		
		checkTranslation("adl-test-ENTRY.ontology_translation.v1");		
		assertEquals("expected no validation error", 0,	errors.size());	
	}
	
	public void testTranslationCheckWithoutConstraintDef() throws Exception {		
		checkTranslation("adl-test-ENTRY.ontology_translation.v7");		
		assertEquals("expected no validation error", 0,	errors.size());	
	}
	
	public void testTranslationCheckWithMissingTermAndConstraintTranslation() 
			throws Exception {		
		checkTranslation("adl-test-ENTRY.ontology_translation.v2");		
		assertEquals("expected validation error", 2, errors.size());
		assertFirstErrorType(ErrorType.VOTM);
		assertSecondErrorType(ErrorType.VOTM);		
	}
	
	public void testTranslationCheckWithMissingTermTranslation() 
			throws Exception {		
		checkTranslation("adl-test-ENTRY.ontology_translation.v3");		
		assertEquals("expected validation error", 1, errors.size());
		assertFirstErrorType(ErrorType.VOTM);
	}
	
	public void testTranslationCheckWithMissingConstraintTranslation() 
			throws Exception {		
		checkTranslation("adl-test-ENTRY.ontology_translation.v4");		
		assertEquals("expected validation error", 1, errors.size());
		assertFirstErrorType(ErrorType.VOTM);
	}
	
	public void testTranslationCheckWithCompleteTranslation() throws Exception {		
		checkTranslation("adl-test-ENTRY.ontology_translation.v5");		
		assertEquals("expected no validation error", 0,	errors.size());	
	}
	
	public void testTranslationCheckWithTwoMissingTranslation() throws Exception {		
		checkTranslation("adl-test-ENTRY.ontology_translation.v6");		
		assertEquals("expected validation error", 2, errors.size());
		assertFirstErrorType(ErrorType.VOTM);
		assertSecondErrorType(ErrorType.VOTM);
	}
	
	private void checkTranslation(String name) throws Exception {
		archetype = loadArchetype(name);
		validator.checkOntologyTranslation(archetype, errors);
	}
}
