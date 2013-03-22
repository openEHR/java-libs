package org.openehr.rm.binding;

import java.io.File;
import java.io.InputStream;
import java.util.*;

import org.apache.commons.io.FileUtils;
import org.apache.commons.jxpath.JXPathContext;
import org.openehr.am.archetype.Archetype;
import org.openehr.rm.Attribute;
import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.composition.Composition;
import org.openehr.rm.datastructure.itemstructure.ItemTree;
import org.openehr.rm.datastructure.itemstructure.representation.Cluster;
import org.openehr.rm.datastructure.itemstructure.representation.Element;
import org.openehr.rm.datastructure.itemstructure.representation.Item;
import org.openehr.rm.datatypes.quantity.DvQuantity;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.support.measurement.MeasurementService;

public class XPathTest extends DADLBindingTestBase {
	
	public void testCreatePathMapWithOrderSet() throws Exception {
		Composition composition = (Composition) bind("order_set1.dadl");
		assertEquals(3, composition.getContent().size());
	}

	public void testGetValue() throws Exception {
		ItemTree tree = createTree();
		JXPathContext context = JXPathContext.newContext(tree);
		assertTrue(context.getValue("/items[2]") instanceof Cluster);
		assertEquals("LDL cholesterol",
				context.getValue("/items[2]/items[2]/name/value"));
	}
	
	public void testAttributeItemsExistsInCluster() throws Exception {
		RMInspector inspector = RMInspector.getInstance();
		Map<String, Attribute> attributeMap = inspector.attributeMap(Cluster.class);
		assertTrue(attributeMap.containsKey("items"));
	}

	public void testCreatePathMapWithDefaultTree() throws Exception {
		ItemTree tree = createTree();
		XPathUtil util = new XPathUtil();
		Map<String, Set<String>> actual = util.extractPaths(tree);

		assertEquals(5, actual.size());

		// first element
		Set<String> set = actual.get("/items[at0001]");
		assertEquals(1, set.size());
		assertEquals("/items[1]", set.toArray()[0]);

		// second element
		set = actual.get("/items[at0005]/items[at0002]");
		assertEquals(1, set.size());
		assertEquals("/items[2]/items[1]", set.toArray()[0]);

		// third element
		set = actual.get("/items[at0005]/items[at0003]");
		assertEquals(1, set.size());
		assertEquals("/items[2]/items[2]", set.toArray()[0]);

		// forth element
		set = actual.get("/items[at0005]/items[at0004]");
		assertEquals(1, set.size());
		assertEquals("/items[2]/items[3]", set.toArray()[0]);

		// fifth element
		set = actual.get("/items[at0006]");
		assertEquals(1, set.size());
		assertEquals("/items[3]", set.toArray()[0]);
	}
	
	public void testCreatePathMapWithRepeatedCluster() throws Exception {
		ItemTree tree = createTreeWithRepeatedCluster();
		XPathUtil util = new XPathUtil();
		Map<String, Set<String>> actual = util.extractPaths(tree);
		assertEquals(5, actual.size());

		// first element
		Set<String> set = actual.get("/items[at0001]");
		assertEquals(1, set.size());
		assertTrue(set.contains("/items[1]"));	

		// second element
		set = actual.get("/items[at0005]/items[at0002]");
		assertEquals(2, set.size());
		assertTrue(set.contains("/items[2]/items[1]"));
		assertTrue(set.contains("/items[3]/items[1]"));

		// third element
		set = actual.get("/items[at0005]/items[at0003]");
		assertEquals(2, set.size());
		assertTrue(set.contains("/items[2]/items[2]"));
		assertTrue(set.contains("/items[3]/items[2]"));

		// forth element
		set = actual.get("/items[at0005]/items[at0004]");
		assertEquals(2, set.size());
		assertTrue(set.contains("/items[2]/items[3]"));
		assertTrue(set.contains("/items[3]/items[3]"));

		// fifth element
		set = actual.get("/items[at0006]");
		assertEquals(1, set.size());
		assertTrue(set.contains("/items[4]"));		
	}

	public void testCreatePathMapWithRepeatedSampleElement() throws Exception {
		ItemTree tree = createTreeWithRepeatedElement();
		XPathUtil util = new XPathUtil();
		Map<String, Set<String>> actual = util.extractPaths(tree);

		assertEquals(5, actual.size());

		// first element, and its copy
		Set<String> set = actual.get("/items[at0001]");
		assertEquals(2, set.size());
		assertTrue(set.contains("/items[1]"));
		assertTrue(set.contains("/items[2]"));

		// second element
		set = actual.get("/items[at0005]/items[at0002]");
		assertEquals(1, set.size());
		assertEquals("/items[3]/items[1]", set.toArray()[0]);

		// third element
		set = actual.get("/items[at0005]/items[at0003]");
		assertEquals(1, set.size());
		assertEquals("/items[3]/items[2]", set.toArray()[0]);

		// forth element
		set = actual.get("/items[at0005]/items[at0004]");
		assertEquals(1, set.size());
		assertEquals("/items[3]/items[3]", set.toArray()[0]);

		// fifth element
		set = actual.get("/items[at0006]");
		assertEquals(1, set.size());
		assertTrue(set.contains("/items[4]"));	
	}
	
	public void testExtratRootPathWithOneSlot() throws Exception {
		ItemTree tree = (ItemTree) bind("tree_slot.dadl"); 
		Set<String> paths = new XPathUtil().extractRootXPaths(tree);
		assertEquals(1, paths.size());
		assertTrue(paths.contains("/items[4]"));		
	}
	
	public void testExtratRootPathWithTwoSlots() throws Exception {
		ItemTree tree = (ItemTree) bind("tree_2_slots.dadl"); 
		Set<String> paths = new XPathUtil().extractRootXPaths(tree);
		assertEquals(2, paths.size());
		assertTrue(paths.contains("/items[4]"));
		assertTrue(paths.contains("/items[5]"));
	}
	
	public void testExtracRootPathWithNestedSlot() throws Exception {
		ItemTree tree = (ItemTree) bind("tree_nested_slot.dadl");
		Set<String> paths = new XPathUtil().extractRootXPaths(tree);
		assertEquals(1, paths.size());
		assertTrue(paths.contains("/items[4]"));
	}
	
	public void testExtracRootPathWithDoubleNestedSlot() throws Exception {
		ItemTree tree = (ItemTree) bind("tree_nested_slot2.dadl");
		Set<String> paths = new XPathUtil().extractRootXPaths(tree);
		assertEquals(1, paths.size());
		assertTrue(paths.contains("/items[4]"));
	}
	
	public void testCreatePathMapWithSlottedCluster() throws Exception {
		ItemTree tree = (ItemTree) bind("tree_slot.dadl"); 
		
		XPathUtil util = new XPathUtil();
		Map<String, Set<String>> actual = util.extractPaths(tree); 
		
		assertEquals(8, actual.size());		

		// first element
		Set<String> set = actual.get("/items[at0001]");
		
		assertEquals(1, set.size());
		assertTrue(set.contains("/items[1]"));

		// second element
		set = actual.get("/items[at0005]/items[at0002]");
		
		assertEquals(1, set.size());
		assertTrue(set.contains("/items[2]/items[1]"));

		// third element
		set = actual.get("/items[at0005]/items[at0003]");
		assertEquals(1, set.size());
		assertTrue(set.contains("/items[2]/items[2]"));

		// forth element
		set = actual.get("/items[at0005]/items[at0004]");
		assertEquals(1, set.size());
		assertTrue(set.contains("/items[2]/items[3]"));

		// fifth element
		set = actual.get("/items[at0006]");
		assertEquals(1, set.size());
		assertTrue(set.contains("/items[3]"));	
		
		// 6th element
		set = actual.get("/items[adl-test-CLUSTER.test_cluster.v1]/items[at0002]");
							     
		assertEquals(1, set.size());
		assertTrue(set.contains("/items[4]/items[1]"));
		
		// 7th element
		set = actual.get("/items[adl-test-CLUSTER.test_cluster.v1]/items[at0003]");
		assertEquals(1, set.size());
		assertTrue(set.contains("/items[4]/items[2]"));
		
		// 8th element
		set = actual.get("/items[adl-test-CLUSTER.test_cluster.v1]/items[at0004]");
		assertEquals(1, set.size());
		assertTrue(set.contains("/items[4]/items[3]"));
	}
	
	public void testCreatePathMapWithNestedClusters() throws Exception {
		ItemTree tree = (ItemTree) bind("tree_nested_slot3.dadl");		
		
		XPathUtil util = new XPathUtil();
		Map<String, Set<String>> actual = util.extractPaths(tree);
		assertEquals(4, actual.size());

		// first element
		Set<String> set = actual.get("/items[at0001]");
		
		assertEquals(1, set.size());
		assertTrue(set.contains("/items[1]"));

		// second element
		set = actual.get("/items[at0005]/items[at0002]");
		
		assertEquals(1, set.size());
		assertTrue(set.contains("/items[2]/items[1]"));

		// third element
		set = actual.get("/items[adl-test-CLUSTER.test_cluster.v1]/items[at0002]");
		assertEquals(1, set.size());
		assertTrue(set.contains("/items[3]/items[1]"));

		// forth element
		set = actual.get("/items[adl-test-CLUSTER.test_cluster.v1]/items[adl-test-CLUSTER.test_cluster.v1]/items[at0002]");
		assertEquals(1, set.size());
		assertTrue(set.contains("/items[3]/items[2]/items[1]"));
	}
	
	public void testCreatePathMapDoubleNestedClusters() throws Exception {
		ItemTree tree = (ItemTree) bind("tree_nested_slot4.dadl");			
		XPathUtil util = new XPathUtil();
		Map<String, Set<String>> actual = util.extractPaths(tree);

		assertEquals(4, actual.size());

		// first element
		Set<String> set = actual.get("/items[at0001]");
		
		assertEquals(1, set.size());
		assertTrue(set.contains("/items[1]"));

		// second element
		set = actual.get("/items[at0005]/items[at0002]");
		
		assertEquals(1, set.size());
		assertTrue(set.contains("/items[2]/items[1]"));

		// third element
		set = actual.get("/items[adl-test-CLUSTER.test_cluster.v1]/items[at0002]");
		assertEquals(2, set.size());
		assertTrue(set.contains("/items[3]/items[1]"));
		assertTrue(set.contains("/items[4]/items[1]"));

		// forth element
		set = actual.get("/items[adl-test-CLUSTER.test_cluster.v1]/items[adl-test-CLUSTER.test_cluster.v1]/items[at0002]");
		assertEquals(2, set.size());
		assertTrue(set.contains("/items[3]/items[2]/items[1]"));
		assertTrue(set.contains("/items[4]/items[2]/items[1]"));
	}
	
	public void testCreatePathMapWithTwoSlottedClusters() throws Exception {
		ItemTree tree = createRootTreeWithTwoSlottedClusters();	
		XPathUtil util = new XPathUtil();
		Map<String, Set<String>> actual = util.extractPaths(tree);

		assertEquals(8, actual.size());

		// first element
		Set<String> set = actual.get("/items[at0001]");
		
		assertEquals(1, set.size());
		assertTrue(set.contains("/items[1]"));

		// second element
		set = actual.get("/items[at0005]/items[at0002]");
		
		assertEquals(1, set.size());
		assertTrue(set.contains("/items[2]/items[1]"));

		// third element
		set = actual.get("/items[at0005]/items[at0003]");
		assertEquals(1, set.size());
		assertTrue(set.contains("/items[2]/items[2]"));

		// forth element
		set = actual.get("/items[at0005]/items[at0004]");
		assertEquals(1, set.size());
		assertTrue(set.contains("/items[2]/items[3]"));

		// fifth element
		set = actual.get("/items[at0006]");
		assertEquals(1, set.size());
		assertTrue(set.contains("/items[3]"));	
		
		// 6th element
		set = actual.get("/items[adl-test-CLUSTER.test_cluster.v1]/items[at0002]");
							     
		assertEquals(2, set.size());
		assertTrue(set.contains("/items[4]/items[1]"));
		assertTrue(set.contains("/items[5]/items[1]"));
		
		// 7th element
		set = actual.get("/items[adl-test-CLUSTER.test_cluster.v1]/items[at0003]");
		assertEquals(2, set.size());
		assertTrue(set.contains("/items[4]/items[2]"));
		assertTrue(set.contains("/items[5]/items[2]"));
		
		// 8th element
		set = actual.get("/items[adl-test-CLUSTER.test_cluster.v1]/items[at0004]");
		assertEquals(2, set.size());
		assertTrue(set.contains("/items[4]/items[3]"));
		assertTrue(set.contains("/items[5]/items[3]"));
	}

	private InputStream loadFromClasspath(String adl) throws Exception {
		return this.getClass().getClassLoader().getResourceAsStream(adl);
	}

	public void testExtractXPaths() throws Exception {
		ItemTree tree = createTree();
		XPathUtil util = new XPathUtil();
		Set<String> actual = util.extractXPaths(tree);
		assertEquals(5, actual.size());
		assertTrue(actual.contains("/items[1]"));
		assertTrue(actual.contains("/items[2]/items[1]"));
		assertTrue(actual.contains("/items[2]/items[2]"));
		assertTrue(actual.contains("/items[2]/items[3]"));
		assertTrue(actual.contains("/items[3]"));
	}
	
	// the nested structure shouldn't produce more xpath of elements
	public void testExtractXPathsWithNestedSlot() throws Exception {
		ItemTree tree = (ItemTree) bind("tree_nested_slot.dadl");
		XPathUtil util = new XPathUtil();
		Set<String> actual = util.extractXPaths(tree);
		assertEquals(5, actual.size());
		assertTrue(actual.contains("/items[1]"));
		assertTrue(actual.contains("/items[2]/items[1]"));
		assertTrue(actual.contains("/items[2]/items[2]"));
		assertTrue(actual.contains("/items[2]/items[3]"));
		assertTrue(actual.contains("/items[3]"));
	}

	/**
	 * /sample 
	 * /lipid studies/total cholesterol 
	 * /lipid studies/ldl cholesterol 
	 * /lipid studies/hdl cholesterol
	 * /comment
	 */
	private ItemTree createTree() {
		List<Item> items = new ArrayList<Item>();
		items.add(sampleElement());
		items.add(cluster());
		items.add(commentElement());
		return new ItemTree("at0007", new DvText("biochemstry result"),
				items);
	}
	
	/**
	 * /items[at0001, 'sample'] 
	 * /items[at0005, 'lipid studies']/items[at0002, 'total cholesterol'] 
	 * /items[at0005, 'lipid studies']/items[at0003, 'ldl cholesterol'] 
	 * /items[at0005, 'lipid studies']/items[at0004, 'hdl cholesterol']
	 * /items[at0006, 'comment']
	 * /items[adl-test-CLUSTER.test_cluster.v1, 'test cluster']/items[at0002, 'element 1']
	 * /items[adl-test-CLUSTER.test_cluster.v1, 'test cluster']/items[at0003, 'element 2']
	 * /items[adl-test-CLUSTER.test_cluster.v1, 'test cluster']/items[at0004, 'element 3']
	 */
	private ItemTree createRootTreeWithTwoSlottedClusters() throws Exception {
		List<Item> items = new ArrayList<Item>();
		items.add(sampleElement());
		items.add(cluster());
		items.add(commentElement());
		items.add(slottedCluster());
		items.add(slottedCluster());
		String aid = "adl-test-ITEM_TREE.test_tree.v1";
		Archetyped details = new Archetyped(aid, "1.0.1");
		ItemTree tree = new ItemTree(null, aid, 
				new DvText("biochemstry result"), details, null, null, null, items);
		
		writeDADL("tree_2_slots.dadl", tree);		
		
		return tree;
	}
	
		
	private void writeDADL(String name, Object obj) throws Exception {
		DADLBinding binding = new DADLBinding();
		List<String> lines = binding.toDADL(obj);
		FileUtils.writeLines(new File(name), lines);
	}

	/**
	 * /sample 
	 * /sample (repeated) 
	 * /lipid studies/total cholesterol 
	 * /lipid studies/ldl cholesterol 
	 * /lipid studies/hdl cholesterol
	 * /comment
	 */
	private ItemTree createTreeWithRepeatedElement() {
		Element comment = new Element("at0006", new DvText("comment"), new DvText(
				"high cardiac risk"));
		
		List<Item> items = new ArrayList<Item>();
		items.add(sampleElement());

		// repeated sample element
		items.add(sampleElement());
		items.add(cluster());
		items.add(comment);
		return new ItemTree("at0007", new DvText("biochemstry result"),
				items);
	}
	
	private Element sampleElement() {
		return new Element("at0001", new DvText("sample"), new DvCodedText(
				"serum", new CodePhrase("terminology", "111")));
	}
	
	private Element commentElement() {
		return new Element("at0006", new DvText("comment"), new DvText(
				"high cardiac risk"));
	}

	/**
	 * /sample 
	 * /lipid studies/total cholesterol 
	 * /lipid studies/ldl cholesterol 
	 * /lipid studies/hdl cholesterol
	 * /lipid studies (repeated)/total cholesterol 
	 * /lipid studies (repeated)/ldl cholesterol 
	 * /lipid studies (repeated)/hdl cholesterol
	 * /comment
	 */
	private ItemTree createTreeWithRepeatedCluster() {
		ItemTree itemTree;
		Element sample;		
		Element comment;
		sample = new Element("at0001", new DvText("sample"), new DvCodedText(
				"serum", new CodePhrase("terminology", "111")));		
				// comment
		comment = new Element("at0006", new DvText("comment"), new DvText(
				"high cardiac risk"));

		List<Item> items = new ArrayList<Item>();
		items.add(sample);
		items.add(cluster());
		
		// repeated cluster
		items.add(cluster());		
		items.add(comment);
		itemTree = new ItemTree("at0007", new DvText("biochemstry result"),
				items);
		return itemTree;
	}
	
	private Cluster cluster() {
		Element totalCholesterol;
		Element ldlCholesterol;
		Element hdlCholesterol;
		MeasurementService measureServ = TestMeasurementService.getInstance();
		totalCholesterol = new Element("at0002",
				new DvText("total cholesterol"), new DvQuantity("mmol/L", 6.1,
						measureServ));
		ldlCholesterol = new Element("at0003", new DvText("LDL cholesterol"),
				new DvQuantity("mmol/L", 0.9, measureServ));
		hdlCholesterol = new Element("at0004", new DvText("HDL cholesterol"),
				new DvQuantity("mmol/L", 5.2, measureServ));
		List<Item> items = new ArrayList<Item>();
		items.add(totalCholesterol);
		items.add(ldlCholesterol);
		items.add(hdlCholesterol);
		return new Cluster("at0005", new DvText("lipid studies"), items);
	}
	
	private Cluster slottedCluster() {
		String archetypeId = "adl-test-CLUSTER.test_cluster.v1";
		Element e1 = new Element("at0002",
				new DvText("element 1"), new DvText("element 1"));
		Element e2 = new Element("at0003", 
				new DvText("element 2"), new DvText("element 2"));
		Element e3 = new Element("at0004", 
				new DvText("element 3"), new DvText("element 3"));
		List<Item> items = new ArrayList<Item>();
		items.add(e1);
		items.add(e2);
		items.add(e3);	
		Archetyped details = new Archetyped("adl-test-CLUSTER.test_cluster.v1",
				"1.0.1");
		Cluster cluster =  new Cluster(null, archetypeId, 
				new DvText("test cluster"), details, null, null, null, items);
		cluster.setOriginalArchetypeNodeId("at1025");		
		return cluster;
	}	
	
	private static class TestMeasurementService implements MeasurementService {

		/* fields */
		/**
		 * Returns True if the units string according to the HL7 UCUM
		 * specification.
		 * 
		 * @param units
		 * @return true if units valid
		 * @throws IllegalArgumentException
		 *             if units null
		 */
		public boolean isValidUnitsString(String units) {
			return true;
		}

		/**
		 * Return True if two units strings correspond to the same measured
		 * property.
		 * 
		 * @param units1
		 * @param units2
		 * @return true if two units equal
		 * @throws IllegalArgumentException
		 *             if units1 or units2 null
		 */
		public boolean unitsEquivalent(String units1, String units2) {
			return true;
		}

		/**
		 * Return a new instance of test measurement service
		 * 
		 * @return
		 */
		public static MeasurementService getInstance() {
			return new TestMeasurementService();
		}

		@Override
		public boolean unitsComparable(String units1, String units2) {
		    return true;
		}

		@Override
		public int compare(String units1, Double value1, String units2,
			Double value2) {
		    return 0;
		}
	}
}
