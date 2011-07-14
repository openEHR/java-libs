package org.openehr.am.archetype.assertion;

import junit.framework.TestCase;

/**
 * Testcases for toString function of expression items
 * 
 * @author rong.chen
 *
 */
public class ExpressionToStringTest extends TestCase {

	public void testSimpleInvariantToString() throws Exception {
		ExpressionLeaf num = new ExpressionLeaf(ExpressionItem.REAL,
				new Double(1.6), ExpressionLeaf.ReferenceType.CONSTANT);
		
		ExpressionItem miles = new ExpressionLeaf(ExpressionItem.REAL,
				"/[at0003]/speed[at0004]/miles/magnitude", 
				ExpressionLeaf.ReferenceType.ATTRIBUTE);
		
		ExpressionItem kilometres = new ExpressionLeaf(ExpressionItem.REAL,
				"/[at0001]/speed[at0002]/kilometres/magnitude", 
				ExpressionLeaf.ReferenceType.ATTRIBUTE);
		
		ExpressionItem calculated = new ExpressionBinaryOperator(
				ExpressionItem.REAL, OperatorKind.OP_MULTIPLY, false, 
				miles, num);
		
		ExpressionItem whole = new ExpressionBinaryOperator(
				ExpressionItem.BOOLEAN, OperatorKind.OP_EQ, false,
				kilometres, calculated);
		
		String expected = 
			"/[at0001]/speed[at0002]/kilometres/magnitude = " +
			"/[at0003]/speed[at0004]/miles/magnitude * 1.6"; 
		
		assertEquals("expressionItem.toString() fails, got " + whole.toString(),
				expected, whole.toString());		
	}
	
	public void testSimpleArchetypeSlotToString() {
		ExpressionItem concept = new ExpressionLeaf(ExpressionItem.ARCHETYPE,
				"domain_concept", ExpressionLeaf.ReferenceType.ATTRIBUTE);
		ExpressionItem aid = new ExpressionLeaf(ExpressionItem.ARCHETYPE,
				"{/medications.v1/}", ExpressionLeaf.ReferenceType.CONSTANT);
		ExpressionItem whole = new ExpressionBinaryOperator(
				ExpressionItem.BOOLEAN, OperatorKind.OP_MATCHES, false,
				concept, aid);
		
		String expected = "domain_concept matches {/medications.v1/}";
		
		assertEquals("toString fails, got: " + whole.toString(), 
				expected, whole.toString());
	}	
}
