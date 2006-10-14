package org.openehr.am.archetype.assertion;

import junit.framework.TestCase;

public class AssertionTest extends TestCase {
	
	/**
	 * Tests to create a simple invariant assertion from the ADL spec
	 * 
	 * invariant
	 *     validity: /[at0001]/speed[at0002]/kilometres/magnitude =
	 *               /[at0003]/speed[at0004]/miles/magnitude * 1.6
	 *
	 */
	public void testCreateSimpleInvariantAssertion() {
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
		
		String stringExpression = 
			"/[at0001]/speed[at0002]/kilometres/magnitude = " +
			"/[at0003]/speed[at0004]/miles/magnitude * 1.6"; 
		Assertion invariant = new Assertion("validity", whole,
				stringExpression, null);
		
		assertNotNull("failed to create invariant assertion", invariant);
		assertNotNull("null expression item of invariant assertion", 
				invariant.getExpression());		
		assertEquals("string expression wrong", stringExpression, 
				invariant.getStringExpression());
	}
	
	/**
	 * Tests to create a simple assertion used by archetype slot
	 * 
	 * include
	 *     domain_concept matches {/medications.v1/}
	 */
	public void testCreateSimpleArchetypeSlot() {
		ExpressionItem concept = new ExpressionLeaf(ExpressionItem.ARCHETYPE,
				"domain_concept", ExpressionLeaf.ReferenceType.ATTRIBUTE);
		ExpressionItem aid = new ExpressionLeaf(ExpressionItem.ARCHETYPE,
				"/medications.v1/", ExpressionLeaf.ReferenceType.CONSTANT);
		ExpressionItem whole = new ExpressionBinaryOperator(
				ExpressionItem.BOOLEAN, OperatorKind.OP_MATCHES, false,
				concept, aid);
		
		String stringExpression = "domain_concept matches {/medications.v1/}";
		
		Assertion include = new Assertion(whole, stringExpression);
		assertNotNull("failed to create invariant assertion", include);
		assertNotNull("null expression item of invariant assertion", 
				include.getExpression());		
		assertEquals("string expression wrong", stringExpression, 
				include.getStringExpression());
	}
}
