package org.openehr.am.template;

public class SetConstraintOnNamedNodeWithoutNameTest  extends TemplateTestBase {
	
	public void setUp() throws Exception {
		super.setUp();	
	}
	
	/*
	 * Test to set a constraint on named node, at0002 in this case, 
	 * without using the actual name in the following rule
	 * <Rule path="/data[at0001]/items[at0002]" max="1" min="1" name="named" />
     * <Rule path="/data[at0001]/items[at0002]/items[at0005]" max="1" min="1" />
	 */
	public void testSeNodeOccurrencesAndNameConstraint() throws Exception {
		flattenTemplate("test_set_named_node_constraint_without_name.oet");		
		
		path = "/items[openEHR-EHR-EVALUATION.review_of_procedures.v1]/" +
			"data[at0001]/items[at0002 and name/value='named']/items[at0005]";
		
		assertRequired(path);		
	}
	
	private String path;
}
