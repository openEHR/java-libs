package org.openehr.am.validation;

public class UniqueSiblingAttributesCheckTest extends ArchetypeValidationTestBase {
	
		public void testCheckUniqueSiblingAttribute() throws Exception {
		checkSiblings("adl-test-ENTRY.sibling_nodes.v1");
		assertEquals("encountered unexpected validation error", 0, 
				errors.size());		
	}
	
	public void testCheckNonUniqueSiblingAttributes() throws Exception {
		checkSiblings("adl-test-ENTRY.sibling_nodes.v2");
		assertEquals("wrong number of validation error in sibling node checking", 1, 
				errors.size());		
		for(ValidationError error : errors) {
			assertEquals("validation error type wrong", ErrorType.VCATU, 
					error.getType());
		}
	}
	
	private void checkSiblings(String name) throws Exception {
		archetype = loadArchetype(name);
		validator.checkObjectConstraints(archetype, errors);
	}
}
