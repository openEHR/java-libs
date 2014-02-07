package org.openehr.am.validation;

public class AttributeNameCheckTest extends ArchetypeValidationTestBase {
	
	public void testCheckAttributeNameWithRightName() throws Exception {
		checkAttributeName("adl-test-ENTRY.attribute_name.v1");
		assertEquals("expected no validation error", 0,	errors.size());	
	}
	
	public void testCheckAttributeNameWithWrongName() throws Exception {
		checkAttributeName("adl-test-ENTRY.attribute_name.v2");
		assertEquals("expected validation error", 1, errors.size());
		assertFirstErrorType(ErrorType.VCARM);
	}
	
	public void testCheckAttributeNameWithFunctionalPropertyStrict() throws Exception {
		checkAttributeName("adl-test-ENTRY.attribute_name.v3");
		assertEquals("expected validation error", 1, errors.size());
		assertFirstErrorType(ErrorType.VCARM);
	}
	
	public void testCheckAttributeNameWithFunctionalPropertyInfo() throws Exception {
		validator.setReportConstraintsOnCommonFunctionalPropertiesAsInfo(true);
		checkAttributeName("adl-test-ENTRY.attribute_name.v3");
		validator.setReportConstraintsOnCommonFunctionalPropertiesAsInfo(false);
		assertEquals("expected validation error", 1, errors.size());
		assertFirstErrorType(ErrorType.ICARM);		
	}
	
	public void testCheckNullFlavourAttributeName() throws Exception {
		checkAttributeName("openEHR-EHR-EVALUATION.null_flavour.v1.adl");
		assertEquals("expected validation error", 0, errors.size());
	}
	
	public void testCheckNullFlavorAttributeName() throws Exception {
		checkAttributeName("openEHR-EHR-EVALUATION.null_flavor.v1.adl");
		assertEquals("expected validation error", 2, errors.size());
		assertFirstErrorType(ErrorType.VCARM);		
		assertSecondErrorType(ErrorType.VCARM);		
	}
	
	private void checkAttributeName(String name) throws Exception {
		archetype = loadArchetype(name);
		validator.checkObjectConstraints(archetype, errors);
	}
}
