package org.openehr.am.validation;

public class TermBindingsValidityTest extends ArchetypeValidationTestBase {
	
	public void testTermBindingCorrectOnly() throws Exception {		
		checkTermBinding("adl-test-ENTRY.term_bindings.v1");		
		assertEquals("expected no error in term binding checking", 
				0, errors.size());
	}
	
	public void testTermBindingWrongPaths() throws Exception {		
		checkTermBinding("adl-test-ENTRY.term_bindings.v2");		
		assertEquals("expected validation errors in term binding checking", 4, 
			errors.size());		
		for(ValidationError error : errors) {
			assertEquals("validation error type wrong", ErrorType.WITB, 
					error.getType());
		}
	}
	
	private void checkTermBinding(String name) throws Exception {
		archetype = loadArchetype(name);
		validator.checkArchetypeTermBindingsValidity(archetype, errors);
	}
	

}
