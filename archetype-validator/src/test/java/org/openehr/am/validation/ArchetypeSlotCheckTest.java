package org.openehr.am.validation;

public class ArchetypeSlotCheckTest extends ArchetypeValidationTestBase {

    public void testCheckWithRightArchetypeIdsInSlot() throws Exception {
        checkSlot("openEHR-EHR-OBSERVATION.slot.v1.adl");
        assertEquals("unexpected validation error: " + errors, 0,
                errors.size());
    }

    public void testCheckWithInvalidSlot() throws Exception {
        checkSlot("openEHR-EHR-OBSERVATION.slot.v2.adl");
        assertEquals("wrong number of validation errors: " + errors, 4,	errors.size());
        assertFirstErrorType(ErrorType.VDFAI);
        assertSecondErrorType(ErrorType.VDFAI);
    }

    public void testCheckWithValidSlotAllAllowed() throws Exception {
        checkSlot("openEHR-EHR-OBSERVATION.slot.v3.adl");
        // SG: This slot is not giving an error  * is the same as unconstrained
        assertEquals("wrong number of validation errors: " + errors, 0, errors.size());

    }

    public void testCheckWithValidInclude() throws Exception {
        checkSlot("openEHR-EHR-OBSERVATION.slot.v4.adl");
        assertEquals("wrong number of validation errors: " + errors, 0, errors.size());
    }

    public void testCheckWithInvalidInclude() throws Exception {
        checkSlot("openEHR-EHR-OBSERVATION.slot.v5.adl");
        assertEquals("wrong number of validation errors: " + errors, 1, errors.size());
        assertFirstErrorType(ErrorType.VDFAI);
    }

    public void testCheckWithValidPipeInclude() throws Exception {
        checkSlot("openEHR-EHR-OBSERVATION.slot.v6.adl");
        assertEquals("wrong number of validation errors: " + errors, 0, errors.size());
    }
	
	  public void testCheckWithUnparsableRegexInclude() throws Exception {
        checkSlot("openEHR-EHR-OBSERVATION.slot.v7.adl");
        assertEquals("wrong number of validation errors: " + errors, 1, errors.size());
        assertFirstErrorType(ErrorType.VDFAI);
    }
	
    private void checkSlot(String name) throws Exception {
        archetype = loadArchetype(name);
        validator.checkObjectConstraints(archetype, errors);
    }
}
