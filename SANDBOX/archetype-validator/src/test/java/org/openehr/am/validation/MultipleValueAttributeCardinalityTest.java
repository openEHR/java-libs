package org.openehr.am.validation;

public class MultipleValueAttributeCardinalityTest 
		extends ArchetypeValidationTestBase {

	public void testCheckMultiAttributeCardinalityNotContainingOccurence() throws Exception {
		checkAttribute("adl-test-ITEM_TREE.attribute_cardinality.v1");
		assertEquals("expected validation error", 1, errors.size());	
		assertFirstErrorType(ErrorType.VACMC);
	}
	
	public void testCheckMultiAttributeCardinalityNotConformingToReferenceModel() throws Exception {
		// testing that a cardinality constraint from the reference model is honoured in the actual archetype
		checkAttribute("adl-test-ITEM_TREE.attribute_cardinality.v2");
		assertEquals("expected validation error", 1, errors.size());	
		assertFirstErrorType(ErrorType.VCACA);
	}

	public void testCheckMultiAttributeCardinalityCredentialsNotConformingToReferenceModel() throws Exception {
		// testing that a cardinality constraint from the reference model is honoured in the actual archetype
		checkAttribute("adl-test-ITEM_TREE.attribute_cardinality.v3");
		assertEquals("expected validation error", 1, errors.size());	
		assertFirstErrorType(ErrorType.VCACA);
	}
	
	public void testCheckSumOfOccurrencesNotIntersectingWithCardinality () throws Exception {
		// testing whether sum of occurrences intersects with cardinality
		checkAttribute("openEHR-EHR-CLUSTER.cardinality_occurrences.v1.adl");
		assertEquals("expected validation error", 1, errors.size());	
		assertFirstErrorType(ErrorType.VACMC);
	}
	
	
	// As per now it is not clear how this should be handled - no error, VACMC or maybe a WACMC warning
	// We use the warning for now
	public void testCheckSumOfOccurrencesNotIntersectingWithCardinalityEdgeCase () throws Exception {
		checkAttribute("openEHR-EHR-CLUSTER.cardinality_occurrences2.v1.adl");
		assertEquals("expected validation error", 1, errors.size());	
		assertFirstErrorType(ErrorType.WACMC);
	}
		
		
	public void testCheckSumOfOccurrencesIntersectingWithCardinality () throws Exception {
		// testing whether sum of occurrences intersects with cardinality
		checkAttribute("openEHR-EHR-CLUSTER.cardinality_occurrences3.v1.adl");
		assertEquals("expected no validation errors", 0, errors.size());	
	}
			
	
	private void checkAttribute(String name) throws Exception {
		archetype = loadArchetype(name);
		validator.checkObjectConstraints(archetype, errors);
	}
}

