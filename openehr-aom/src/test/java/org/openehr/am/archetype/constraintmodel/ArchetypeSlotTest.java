package org.openehr.am.archetype.constraintmodel;

import org.openehr.am.archetype.assertion.*;
import org.openehr.rm.support.basic.Interval;

import junit.framework.TestCase;

import java.util.*;

/**
 * Simple test for ArchetypeSlot 
 * 
 * @author Rong Chen
 */
public class ArchetypeSlotTest extends TestCase {
	
	public void testCreateEmptyArchetypeSlot() {
		ArchetypeSlot slot = new ArchetypeSlot("/path", "Entry", null, "at001",
				null, null, null);
		assertTrue("anyAllowed expected for empty slot", slot.isAnyAllowed());
	}
	
	/**
	 * Tests to create a simple archetype slot
	 */ 
	// Sample from ADL spec:
	// allow_archetype SECTION occurrences matches {0..*} matches {
	//		include
	//     		archetype_id matches {/.*\.iso-ehr\.section\..*\..*/}
	//		exclude
	// 			archetype_id matches {/.*\.iso-ehr\.section\.patient_details\..*/}
	// }		
	public void testCreateSimpleArchetypeSlot() {
		ExpressionItem iaid = new ExpressionLeaf(ExpressionItem.ARCHETYPE,
				"archetype_id", ExpressionLeaf.ReferenceType.ATTRIBUTE);
		ExpressionItem include_aid = new ExpressionLeaf(
				ExpressionItem.ARCHETYPE,
				"/.*\\.iso-ehr\\.section\\..*\\..*/", ExpressionLeaf.ReferenceType.CONSTANT);
		ExpressionItem includeExpr = new ExpressionBinaryOperator(
				ExpressionItem.BOOLEAN, OperatorKind.OP_MATCHES, false,
				iaid, include_aid);		
		String include_string = "archetype_id matches {/.*\\.iso-ehr\\.section\\..*\\..*/}";
		Assertion include = new Assertion(includeExpr, include_string);
		
		ExpressionItem eaid = new ExpressionLeaf(ExpressionItem.ARCHETYPE,
				"archetype_id", ExpressionLeaf.ReferenceType.ATTRIBUTE);
		ExpressionItem exclude_aid = new ExpressionLeaf(
				ExpressionItem.ARCHETYPE,
				"/.*\\.iso-ehr\\.section\\.patient_details\\..*/", 
				ExpressionLeaf.ReferenceType.CONSTANT);
		ExpressionItem excludeExpr = new ExpressionBinaryOperator(
				ExpressionItem.BOOLEAN, OperatorKind.OP_MATCHES, false,
				eaid, exclude_aid);
		String exclude_string = "archetype_id matches {/.*\\.iso-ehr\\.section\\.patient_details\\..*/}";		
		Assertion exclude = new Assertion(excludeExpr, exclude_string);
		
		Set<Assertion> includes = new HashSet<Assertion>();
		includes.add(include);
		Set<Assertion> excludes = new HashSet<Assertion>();
		excludes.add(exclude);
		ArchetypeSlot slot = new ArchetypeSlot("/[at001]", "SECTION", 
				new Interval<Integer>(0, null), "at001", null, includes, 
				excludes);		
	}	
}
