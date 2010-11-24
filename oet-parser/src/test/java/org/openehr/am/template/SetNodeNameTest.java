package org.openehr.am.template;

import org.openehr.am.archetype.constraintmodel.ArchetypeConstraint;
import org.openehr.am.archetype.constraintmodel.CComplexObject;

public class SetNodeNameTest extends TemplateTestBase {

	/*
	 * Setting leaf-level node name doesn't involving copying nodes
	 */
	public void testSetLeafNodeNameConstraint() throws Exception {
		flattenTemplate("test_text_name.oet");

		String path = "/data[at0001]/items[at0002]/items" +
				"[at0003 and name/value='Typ']";
		
		ac = flattened.node(path);
		assertElementConstraint(ac);
		
		ac = flattened.node(path + "/name/value");
		assertCStringWithSingleValue(ac, "Typ");
	}

	/*
	 * Setting object-level node name involves copying the node and its entire
	 * tree and updating the path to [node_id and name/value='xxx'] for named
	 * path to work
	 */
	public void testSetSingleObjectNodeNameConstraint() throws Exception {
		String path = "/data[at0001]/items[at0002 and name/value='insats']"
				+ "/items[at0003]";

		flattenTemplate("test_named_path.oet");

		ac = flattened.node(path);
		assertElementConstraint(ac);

		ac = flattened.node(path + "/value");
		assertDvTextConstraint(ac);
	}
	
	/*
	 * Test named-node path following node name is set
	 */
	public void testRuleWithNamedNodePath() throws Exception {
		String path = "/data[at0001]/items[at0002 and name/value='one']" +
				"/items[at0003]";

		flattenTemplate("test_named_path3.oet");

		ac = flattened.node(path);
		assertElementConstraint(ac);

		CComplexObject ccobj = (CComplexObject) ac;
		assertEquals("expected max occurrences", 0, 
				ccobj.getOccurrences().getUpper().intValue());
	}
	
	private ArchetypeConstraint ac;
}
