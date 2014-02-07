package org.openehr.am.template;

import org.openehr.am.archetype.constraintmodel.*;

public class SetMultipleEvaluationNameTest extends TemplateTestBase {
	
	public void setUp() throws Exception {
		super.setUp();	
	}
	
	public void testTopNodeNameConstraint() throws Exception {
		flattenTemplate("test_set_multiple_evaluation_name.oet");		
		
		String path = "/items[openEHR-EHR-EVALUATION.review_of_procedures.v1" +
			" and name/value='eval_1']/name/value";		
		
		// name attribute
		ArchetypeConstraint nameConstraint = flattened.node(path);
		assertCStringWithSingleValue(nameConstraint, "eval_1");
		
		path = "/items[openEHR-EHR-EVALUATION.review_of_procedures.v1" +
			" and name/value='eval_2']/name/value";
		
		nameConstraint = flattened.node(path);
		assertCStringWithSingleValue(nameConstraint, "eval_2");
		
	}
}

