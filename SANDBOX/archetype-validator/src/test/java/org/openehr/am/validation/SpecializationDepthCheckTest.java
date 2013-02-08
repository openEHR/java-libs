package org.openehr.am.validation;

import org.openehr.am.archetype.Archetype;

public class SpecializationDepthCheckTest extends ArchetypeValidationTestBase{
	
	public void testCheckAttributeNameWithRightLevel() throws Exception {
	    checkConceptNameSpecializationDepth("adl-test-ELEMENT.specialization-depth.v1.adl");
		assertEquals("expected no validation error", 0,	errors.size());	
	}
	
	public void testCheckAttributeNameWithWrongLevel() throws Exception {
	    checkConceptNameSpecializationDepth("adl-test-ELEMENT.specialization-depth.v2.adl");
		assertEquals("expected validation error", 1, errors.size());
		assertFirstErrorType(ErrorType.VACSD);
	}
	
	public void testCheckConceptNameWithLevelGenerallyToHighForTheLevelOfArchetypeSpecialisation() throws Exception {
        checkAll("adl-test-ELEMENT.specialization-depth.v3.adl", "adl-test-ELEMENT.specialization.v1.adl");
        assertEquals("expected validation errors", 5, errors.size());

        // These 5 errors are a little bit redundant or at least it may seem so.
        // The order of the errors is of course not important and might change in the future
        assertFirstErrorType(ErrorType.VACSD); // Especially for the concept specialisation depth
        assertSecondErrorType(ErrorType.VONSD); // an error for the at code in the ontology which is generally too high for the archetype.
        assertThirdErrorType(ErrorType.VATDF); // This is just a follow up error because the parent doesn't have the wrong(!) parent at code of at0000.1
        assertFourthErrorType(ErrorType.VSONCI); // Conformance of specialisation - again in a slightly different way.
        assertFifthErrorType(ErrorType.VATCD); // an error for the at code in the definition part of the archetype which is generally too high for the archetype.

          }
	
	public void testCheckTermWithLevelGenerallyToHighForTheLevelOfArchetypeSpecialisation() throws Exception {
	    checkAll("adl-test-ELEMENT.specialization-depth.v4.adl", "adl-test-ELEMENT.specialization.v1.adl");
        
        assertEquals("expected validation errors", 2, errors.size());
        assertFirstErrorType(ErrorType.VONSD); // an error for the at code in the ontology which is generally too high for the archetype.
        assertSecondErrorType(ErrorType.VATCD); // an error for the at code in the definition part of the archetype which is generally too high for the archetype.

    }
	
	public void testCheckConstraintWithLevelGenerallyToHighForTheLevelOfArchetypeSpecialisation() throws Exception {
        checkAll("adl-test-ELEMENT.specialization-depth.v5.adl", "adl-test-ELEMENT.specialization.v1.adl");
        assertEquals("expected validation errors", 2, errors.size());
        assertFirstErrorType(ErrorType.VONSD); // an error for the ac code in the ontology which is generally too high for the archetype.
        assertSecondErrorType(ErrorType.VATCD); // an error for the ac code in the definition part of the archetype which is generally too high for the archetype.

    }
	
	private void checkConceptNameSpecializationDepth(String name) throws Exception {
		archetype = loadArchetype(name);
		validator.checkConceptSpecializationDepth(archetype, errors);
	}
	
	private void checkAll(String name, String parentName) throws Exception {
        archetype = loadArchetype(name);
        Archetype parentArchetype = loadArchetype(parentName);
        errors =  new SpecialisedArchetypeValidator().validate(archetype, parentArchetype, true);
        
    }
}
