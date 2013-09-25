package org.openehr.am.validation;

public class ArchetypeDefinitionCodeCheckTest extends ArchetypeValidationTestBase {

	public void testCheckWithCorrectCode() throws Exception {
		checkCode("adl-test-ENTRY.definition_code.v1");
		assertEquals("unexpected validation error: " + errors, 0,	
				errors.size());	
	}
	
	public void testCheckWithWrongCode() throws Exception {
		checkCode("adl-test-ENTRY.definition_code.v2");
		assertFirstErrorType(ErrorType.VACCD);
	}
	
	private void checkCode(String name) throws Exception {
		archetype = loadArchetype(name);
		validator.checkArchetypeDefinitionCodeValidity(archetype, errors);
	}
}
