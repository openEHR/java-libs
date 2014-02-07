package org.openehr.am.template;

import org.openehr.am.archetype.constraintmodel.CObject;

public class SetMaxOccurrencesWithoutMinTest extends TemplateTestBase {

	public void __testSetMaxOccurrencesOnInternalNode() throws Exception {
		flattenTemplate("test_set_occurrences_without_min.oet");

		String path = "/items[at0001]";
		
		CObject constraint = (CObject) flattened.node(path);
		
		assertEquals("unexpected min occurrences", 0, 
				constraint.getOccurrences().getLower().intValue());
		
		assertEquals("unexpected max occurrences", 1,  
				constraint.getOccurrences().getUpper().intValue());
		
	}
	
	public void testSetMaxOccurrencesOnArchetypeSlot() throws Exception {
		flattenTemplate("test_set_occurrences_without_min2.oet");

		String path = "/items[openEHR-EHR-EVALUATION.structured_summary.v1]";
		
		CObject constraint = (CObject) flattened.node(path);
		
		assertEquals("unexpected min occurrences", 0, 
				constraint.getOccurrences().getLower().intValue());
		
		assertEquals("unexpected max occurrences", 1,  
				constraint.getOccurrences().getUpper().intValue());
		
	}
}
