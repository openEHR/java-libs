package br.com.zilics.archetypes.models.rm.utils.path.parsed;

import java.util.ArrayList;
import java.util.List;

import br.com.zilics.archetypes.models.rm.utils.path.MockEvaluator;
import br.com.zilics.archetypes.models.rm.utils.path.context.PathEvaluationContext;
import br.com.zilics.archetypes.models.rm.utils.path.model.ListValue;
import br.com.zilics.archetypes.models.rm.utils.path.model.SingleValue;

import junit.framework.TestCase;

public class TreeNodeTest extends TestCase {
	public TreeNodeTest(String testName) {
		super(testName);
	}
	
	public void testEvaluate() throws Exception {
		List<TreeNode> mergeNodes = new ArrayList<TreeNode>();
		mergeNodes.add(new Literal("oi"));
		mergeNodes.add(new Literal("tudo"));
		Merge m = new Merge(mergeNodes);
		List<SingleValue> expectedValues = new ArrayList<SingleValue>();
		expectedValues.add(new SingleValue("oi"));
		expectedValues.add(new SingleValue("tudo"));
		ListValue expected = ListValue.getInstanceFromList(expectedValues);
		ListValue result = m.evaluate(new PathEvaluationContext(new MockEvaluator()));
		assertEquals(expected, result);
	}
}
