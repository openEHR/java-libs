package org.openehr.am.validation;

public class SingleValueAttributeChildIdentifierCheckTest
	extends ArchetypeValidationTestBase {

	public void testCheckSingleAttributeChildIdentifierWithGoodIds() throws Exception {
		checkAttribute("adl-test-ENTRY.single_attribute_child_identifier.v1");
		assertEquals("expected no validation error", 0,	errors.size());	
	}
	
	public void testCheckSingleAttributeChildIdentifierWithDuplicatedIds() throws Exception {
		checkAttribute("adl-test-ENTRY.single_attribute_child_identifier.v2");
		assertEquals("expected validation error", 1, errors.size());
		assertFirstErrorType(ErrorType.VACSI);
	}
	
	private void checkAttribute(String name) throws Exception {
		archetype = loadArchetype(name);
		validator.checkObjectConstraints(archetype, errors);
	}
}
