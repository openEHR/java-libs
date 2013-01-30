package org.openehr.am.validation;

public class SpecializationDepthCheckTest extends ArchetypeValidationTestBase{
	
	public void testCheckAttributeNameWithRightLevel() throws Exception {
		checkSpecializationDepth("adl-test-ELEMENT.specialization-depth.v1.adl");
		assertEquals("expected no validation error", 0,	errors.size());	
	}
	
	public void testCheckAttributeNameWithWrongLevel() throws Exception {
		checkSpecializationDepth("adl-test-ELEMENT.specialization-depth.v2.adl");
		assertEquals("expected validation error", 1, errors.size());
		assertFirstErrorType(ErrorType.VACSD);
	}
	
	private void checkSpecializationDepth(String name) throws Exception {
		archetype = loadArchetype(name);
		validator.checkSpecializationDepth(archetype, errors);
	}	
}
