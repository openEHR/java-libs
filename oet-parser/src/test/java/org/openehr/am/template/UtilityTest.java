	package org.openehr.am.template;

import org.openehr.am.archetype.constraintmodel.CComplexObject;

public class UtilityTest extends TemplateTestBase {

	public void testNextNodeId() throws Exception {
		assertEquals("at0001", flattener.formatNodeId(1));
		assertEquals("at0010", flattener.formatNodeId(10));
		assertEquals("at0100", flattener.formatNodeId(100));
		assertEquals("at1000", flattener.formatNodeId(1000));
		assertEquals("at9999", flattener.formatNodeId(9999));
		assertEquals("at10000", flattener.formatNodeId(10000));
		assertEquals("at100000", flattener.formatNodeId(100000));
	}
	
	public void testParseNodeId() throws Exception {
		assertEquals(1, flattener.parseNodeId("at0001"));
		assertEquals(10, flattener.parseNodeId("at0010"));
		assertEquals(100, flattener.parseNodeId("at0100"));
		assertEquals(1000, flattener.parseNodeId("at1000"));
		assertEquals(9999, flattener.parseNodeId("at9999"));
		assertEquals(10000, flattener.parseNodeId("at10000"));
		assertEquals(1, flattener.parseNodeId("at0001.1"));
		assertEquals(100, flattener.parseNodeId("at0100.2"));
	}
	
	public void testFindLargestNodeId() throws Exception {
		archetype = loadArchetype("openEHR-EHR-SECTION.find_largest_node_id.v1.adl");
		assertEquals(4, flattener.findLargestNodeId(archetype));
	}
	
	public void testFindLargestNodeId2() throws Exception {
		archetype = loadArchetype("openEHR-EHR-SECTION.find_largest_node_id_2.v1.adl");
		assertEquals(10, flattener.findLargestNodeId(archetype));
	}
	
	public void testAdjustNodeIds() throws Exception {
		archetype = loadArchetype("openEHR-EHR-EVALUATION.structured_summary.v1.adl");
		expected = loadArchetype("openEHR-EHR-EVALUATION.adjusted_node_ids.v1.adl");
				
		CComplexObject root = archetype.getDefinition();
		long count = flattener.adjustNodeIds(root, 10);
		
		assertEquals("Unexpected total adjusted nodeIds", 15, count);
		assertCComplexObjectEquals("failed to adjust nodeIds",
				expected.getDefinition(), root);		
	}	
}
