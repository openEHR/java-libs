package org.openehr.am.template;

public class SetOccurrencesAndNameTest extends TemplateTestBase {
	
	/*
	 * Setting leaf-level node name doesn't involving copying nodes
	 */
	public void testSeNodeOccurrencesAndNameConstraint() throws Exception {
		flattenTemplate("test_set_occurrences_and_name.oet");
	
		String path = "/description[openEHR-EHR-ITEM_TREE.medication.v1]/";
		
		assertNotAllowed(path + "items[at0003]");
		assertNotAllowed(path + "items[at0004]");
		
		path += "items[at0033 and name/value='dos']/items[at0035]"; 
		
		assertRequired(path);		
		assertNotAllowed(path + "/items[at0036]");		
		assertRequired(path + "/items[at0037]");		
	}
}
