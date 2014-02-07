package org.openehr.am.validation;

public class SingleValueAttributeChildOccurrencesCheckTest 
		extends ArchetypeValidationTestBase {
	
	public void testCheckChildOccurrencesWithNoOccurrences() throws Exception {
		checkAttributeName("adl-test-ENTRY.single_attribute_child_occurrences.v1");
		assertEquals("expected no validation error", 0,	errors.size());	
	}
	
	public void testCheckChildOccurrencesRightOccurrences() throws Exception {
		checkAttributeName("adl-test-ENTRY.single_attribute_child_occurrences.v2");
		assertEquals("expected no validation error", 0,	errors.size());	
	}
	
	public void testCheckChildOccurrencesWithWrongOccurrences() throws Exception {
		checkAttributeName("adl-test-ENTRY.single_attribute_child_occurrences.v3");
		assertEquals("expected validation error", 1, errors.size());
		assertFirstErrorType(ErrorType.VACSO);
	}
	
	private void checkAttributeName(String name) throws Exception {
		archetype = loadArchetype(name);
		validator.checkObjectConstraints(archetype, errors);
	}
}
