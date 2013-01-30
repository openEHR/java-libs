package br.com.zilics.archetypes.models.rm.utils.path;

import java.util.ArrayList;
import java.util.List;

import br.com.zilics.archetypes.models.rm.utils.path.context.PathEvaluationContext;
import br.com.zilics.archetypes.models.rm.utils.path.model.ListValue;
import br.com.zilics.archetypes.models.rm.utils.path.model.ObjectValue;
import br.com.zilics.archetypes.models.rm.utils.path.model.SingleValue;
import br.com.zilics.archetypes.models.rm.utils.path.parsed.Child;
import br.com.zilics.archetypes.models.rm.utils.path.parsed.ExpressionPredicate;
import br.com.zilics.archetypes.models.rm.utils.path.parsed.Literal;
import br.com.zilics.archetypes.models.rm.utils.path.parsed.Merge;
import br.com.zilics.archetypes.models.rm.utils.path.parsed.PathFollow;
import br.com.zilics.archetypes.models.rm.utils.path.parsed.Root;
import br.com.zilics.archetypes.models.rm.utils.path.parsed.TreeNode;

import junit.framework.TestCase;

public class ArchPathParserTest extends TestCase {
	
	public ArchPathParserTest(String testName) {
		super(testName);
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testSimpleParse() throws Exception {
		String pathExpr = "/root[bb]/baca[1][2]/child::*";
		TreeNode result = PathUtils.parseArchPath(pathExpr);
		assertTrue(result instanceof Merge);
		Merge v1 = (Merge) result;
		assertEquals(1, v1.getOperands().size());
		assertTrue(v1.getOperands().get(0) instanceof PathFollow);
		PathFollow v2 = (PathFollow) v1.getOperands().get(0);
		assertTrue(v2.getNode() instanceof Root);
		assertTrue(v2.getFollowingNode() instanceof PathFollow);
		PathFollow v3 = (PathFollow) v2.getFollowingNode();
		assertTrue(v3.getNode() instanceof ExpressionPredicate);
		ExpressionPredicate v4 = (ExpressionPredicate) v3.getNode();
		assertTrue(v4.getNode() instanceof Child);
		Child v5 = (Child) v4.getNode();
		assertFalse(v5.isAnyAllowed());
		assertEquals("root", v5.getNodeTest());
		assertTrue(v4.getPredicateExpression() instanceof Merge);
		Merge v6 = (Merge) v4.getPredicateExpression();
		assertEquals(1, v6.getOperands().size());
		assertTrue(v6.getOperands().get(0) instanceof Child);
		Child v7 = (Child) v6.getOperands().get(0);
		assertFalse(v7.isAnyAllowed());
		assertEquals("bb", v7.getNodeTest());
		assertTrue(v3.getFollowingNode() instanceof PathFollow);
		PathFollow v8 = (PathFollow) v3.getFollowingNode();
		assertTrue(v8.getNode() instanceof ExpressionPredicate);
		ExpressionPredicate v9 = (ExpressionPredicate) v8.getNode();
		assertTrue(v9.getPredicateExpression() instanceof Merge);
		Merge v10 = (Merge) v9.getPredicateExpression();
		assertEquals(1, v10.getOperands().size());
		assertTrue(v10.getOperands().get(0) instanceof Literal);
		Literal v11 = (Literal) v10.getOperands().get(0);
		assertEquals(new SingleValue(2), v11.getValue());
		assertTrue(v9.getNode() instanceof ExpressionPredicate);
		ExpressionPredicate v12 = (ExpressionPredicate) v9.getNode();
		assertTrue(v12.getPredicateExpression() instanceof Merge);
		Merge v13 = (Merge) v12.getPredicateExpression();
		assertEquals(1, v13.getOperands().size());
		assertTrue(v13.getOperands().get(0) instanceof Literal);
		Literal v14 = (Literal) v13.getOperands().get(0);
		assertEquals(new SingleValue(1), v14.getValue());
		assertTrue(v12.getNode() instanceof Child);
		Child v15 = (Child) v12.getNode();
		assertFalse(v15.isAnyAllowed());
		assertEquals("baca", v15.getNodeTest());
		assertTrue(v8.getFollowingNode() instanceof Child);
		Child v16 = (Child) v8.getFollowingNode();
		assertTrue(v16.isAnyAllowed());
		assertEquals("*", v16.getNodeTest());
	}
	
	private ListValue parseAndEvaluate(String expr) throws Exception {
		TreeNode parsed = PathUtils.parseArchPath(expr);
		ListValue result = parsed.evaluate(new PathEvaluationContext(new MockEvaluator()));
		assertNotNull(result);
		return result;
	}

	public void testParseAndEvaluateFor() throws Exception {
		String expr = "for $i in (2, 3, 4), $j in (40, 50) return ($i + $j)";
		List<SingleValue> expectedValues = new ArrayList<SingleValue>();
		
		expectedValues.add(new SingleValue(42));
		expectedValues.add(new SingleValue(52));
		expectedValues.add(new SingleValue(43));
		expectedValues.add(new SingleValue(53));
		expectedValues.add(new SingleValue(44));
		expectedValues.add(new SingleValue(54));
		
		ListValue expected = ListValue.getInstanceFromList(expectedValues);
		ListValue result = parseAndEvaluate(expr);
		assertEquals(expected, result);
	}

	public void testParseAndEvaluatePredicate() throws Exception {
		String expr = "(2 to 10)[. mod 2 = 1]";
		List<SingleValue> expectedValues = new ArrayList<SingleValue>();
		
		expectedValues.add(new SingleValue(3));
		expectedValues.add(new SingleValue(5));
		expectedValues.add(new SingleValue(7));
		expectedValues.add(new SingleValue(9));
		
		ListValue expected = ListValue.getInstanceFromList(expectedValues);
		ListValue result = parseAndEvaluate(expr);
		assertEquals(expected, result);
	}

	public void testParseAndEvaluatePathFollow() throws Exception {
		String expr = "(2 to 5)/(. to 4)";
		List<SingleValue> expectedValues = new ArrayList<SingleValue>();
		
		expectedValues.add(new SingleValue(2));
		expectedValues.add(new SingleValue(3));
		expectedValues.add(new SingleValue(4));
		expectedValues.add(new SingleValue(3));
		expectedValues.add(new SingleValue(4));
		expectedValues.add(new SingleValue(4));
		
		ListValue expected = ListValue.getInstanceFromList(expectedValues);

		ListValue result = parseAndEvaluate(expr);
		assertEquals(expected, result);
	}

	public void testParseAndEvaluateFunction() throws Exception {
		String expr = "(2 to 5)/last()";
		List<SingleValue> expectedValues = new ArrayList<SingleValue>();
		
		expectedValues.add(new SingleValue(4));
		expectedValues.add(new SingleValue(4));
		expectedValues.add(new SingleValue(4));
		expectedValues.add(new SingleValue(4));
		
		ListValue expected = ListValue.getInstanceFromList(expectedValues);

		ListValue result = parseAndEvaluate(expr);
		assertEquals(expected, result);
	}

	public void testParseAndEvaluateDefineAndCall() throws Exception {
		String expr = "define((\"fatorial\", \"n\"), if ($n > 0) then ($n * fatorial($n-1)) else (1)), fatorial(5)";
		List<SingleValue> expectedValues = new ArrayList<SingleValue>();
		
		expectedValues.add(new SingleValue(120));
		
		ListValue expected = ListValue.getInstanceFromList(expectedValues);
		ListValue result = parseAndEvaluate(expr);
		assertEquals(expected, result);		
	}

	public void testParseAndEvaluateDefineAndCallWithContextItem() throws Exception {
		String expr = "define((\"double\"), (.*2)), if (()) then (3) else ((1 to 5)/double())";
		List<SingleValue> expectedValues = new ArrayList<SingleValue>();
		
		expectedValues.add(new SingleValue(2));
		expectedValues.add(new SingleValue(4));
		expectedValues.add(new SingleValue(6));
		expectedValues.add(new SingleValue(8));
		expectedValues.add(new SingleValue(10));
		
		ListValue expected = ListValue.getInstanceFromList(expectedValues);
		ListValue result = parseAndEvaluate(expr);
		
		assertEquals(expected, result);
	}

	public void testParseAndEvaluateNew() throws Exception {
		String expr = "(1 to 5)/new (\"br.com.zilics.archetypes.models.rm.utils.path.ArchPathParserTest$TestClass\", .)";
		ListValue result = parseAndEvaluate(expr);
		for(int i = 0; i < 5; i++)
			assertEquals(result.getValues().get(i).getValue(), new TestClass(i+1));
	}

	public static class TestClass implements ObjectValue {
		private final Integer i;
		public TestClass(Integer i) {
			this.i = i;
		}
		
		public Integer getI() {
			return this.i;
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((i == null) ? 0 : i.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (!(obj instanceof TestClass))
				return false;
			TestClass other = (TestClass) obj;
			if (i == null) {
				if (other.i != null)
					return false;
			} else if (!i.equals(other.i))
				return false;
			return true;
		}
		
		@Override
		public String toString() {
			return ""+i;
		}

	}

}

