package org.openehr.am.template;

import org.openehr.am.archetype.constraintmodel.*;

/**
 * The cardinality of the parent container is calculated based on 
 * occurrences of the child elements
 * 
 * @author Rong.Chen
 */
public class SetCardinalityConstraintsTest extends TemplateTestBase {
	
	public void setUp() throws Exception {
		super.setUp();	
	}
	
	public void testWithTwoOptionalNodesAndOneProhibitedNode() throws Exception {
		flattenTemplate("test_set_cardinality_with_prohibited_node.oet");		
		path = "/items[openEHR-EHR-OBSERVATION.waist_hip.v2]/" +
			"data[at0001]/events[at0002]/data[at0003]";
		
		CMultipleAttribute itemsAttr = (CMultipleAttribute) ((CComplexObject) 
				flattened.node(path)).getAttribute("items");
		assertEquals(0, itemsAttr.getCardinality().getInterval().getLower().intValue());
	}
	
	public void testWithOneRequiredOneOptionalAndOneProhibitedNode() throws Exception {
		flattenTemplate("test_set_cardinality_with_prohibited_node3.oet");		
		path = "/items[openEHR-EHR-OBSERVATION.waist_hip.v2]/" +
			"data[at0001]/events[at0002]/data[at0003]";
		
		//printADL(flattened);
		
		CMultipleAttribute itemsAttr = (CMultipleAttribute) ((CComplexObject) 
				flattened.node(path)).getAttribute("items");
		assertEquals(1, itemsAttr.getCardinality().getInterval().getLower().intValue());
	}
	
	// Near IFK2 condition to trigger the issue
	public void testWithTwoOptionalNodesAndOneProhibitedNode2() throws Exception {
		flattenTemplate("test_set_cardinality_with_prohibited_node2.oet");		
		path = "/items[openEHR-EHR-OBSERVATION.heart_failure_stage.v2]/" +
			"data[at0001]/events[at0002]/data[at0003]";
		
		CMultipleAttribute itemsAttr = (CMultipleAttribute) ((CComplexObject) 
				flattened.node(path)).getAttribute("items");
		assertEquals(0, itemsAttr.getCardinality().getInterval().getLower().intValue());
	}
	
	private String path;
}

