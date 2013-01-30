package org.openehr.am.validation;

public class ArchetypeSlotCheckTest extends ArchetypeValidationTestBase {
	
	public void testCheckWithRightArchetypeIdsInSlot() throws Exception {
		checkSlot("openEHR-EHR-OBSERVATION.slot.v1.adl");
		assertEquals("unexpected validation error: " + errors, 0,	
				errors.size());	
	}
	
	public void testCheckWithInvalidSlot() throws Exception {
		checkSlot("openEHR-EHR-OBSERVATION.slot.v2.adl");
		assertEquals("wrong number of validation errors: " + errors, 4,	
				errors.size());	
		assertFirstErrorType(ErrorType.VDFAI);
		assertSecondErrorType(ErrorType.VDFAI);
	}
	
	
	private void checkSlot(String name) throws Exception {
		archetype = loadArchetype(name);
		validator.checkObjectConstraints(archetype, errors);
	}
}
