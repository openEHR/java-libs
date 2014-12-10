package org.openehr.am.validation;

public class SectionCardinalityTest 
		extends ArchetypeValidationTestBase {

	public void testCheckSectionItemsCardinality() throws Exception {
		checkObjectConstraints("openEHR-EHR-SECTION.testitemscardinality.v1.adl");
		assertEquals("expected NO validation error", 0, errors.size());			
	}
	
	
	private void checkObjectConstraints(String name) throws Exception {
		archetype = loadArchetype(name);
		validator.checkObjectConstraints(archetype, errors);
	}
}

