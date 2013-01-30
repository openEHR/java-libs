package org.openehr.am.validation;

public class ValidateOpenEHRTerminologyCodesTest 
		extends ArchetypeValidationTestBase {
	
	public void testCheckTypeNameDvIntervalWithWrongGenericType() throws Exception {
		CheckCodes("adl-test-ELEMENT.type_multimedia.v2.adl");
	//	assertFirstErrorType(ErrorType.VOTC);
	}
	
	private void CheckCodes(String name) throws Exception {
		archetype = loadArchetype(name);
		validator.checkObjectConstraints(archetype, errors);
	}
}
