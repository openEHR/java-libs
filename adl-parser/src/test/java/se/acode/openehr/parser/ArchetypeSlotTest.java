package se.acode.openehr.parser;

import org.openehr.am.archetype.*;
import org.openehr.am.archetype.assertion.Assertion;
import org.openehr.am.archetype.assertion.ExpressionBinaryOperator;
import org.openehr.am.archetype.assertion.ExpressionItem;
import org.openehr.am.archetype.assertion.ExpressionLeaf;
import org.openehr.am.archetype.constraintmodel.ArchetypeConstraint;
import org.openehr.am.archetype.constraintmodel.ArchetypeSlot;
import org.openehr.am.archetype.constraintmodel.primitive.CString;
import org.openehr.rm.support.basic.Interval;

public class ArchetypeSlotTest extends ParserTestBase {

	/**
     * Create new test case
     *
     * @param test
     * @throws Exception
     */
    public ArchetypeSlotTest(String test) throws Exception {
        super(test);
    }

    public void testParse() throws Exception {
        ADLParser parser = new ADLParser(loadFromClasspath(
                "adl-test-entry.archetype_slot.test.adl"));
        Archetype archetype = parser.parse();
        assertNotNull(archetype);
        
        ArchetypeConstraint node = archetype.node("/content[at0001]");
        assertTrue("ArchetypeSlot expected", node instanceof ArchetypeSlot);
        
        ArchetypeSlot slot = (ArchetypeSlot) node;
        assertEquals("nodeId wrong", "at0001", slot.getNodeID());
        assertEquals("rmTypeName wrong", "SECTION", slot.getRmTypeName());
        assertEquals("occurrences wrong", new Interval<Integer>(0, 1), 
        		slot.getOccurrences());
        
        assertEquals("path wrong", "/content[at0001]", slot.path());
        
        assertEquals("includes total wrong", 1, slot.getIncludes().size());
        assertEquals("Excludes total wrong", 2, slot.getExcludes().size());
        
        Assertion assertion = slot.getIncludes().iterator().next();       
        
        ExpressionItem item = assertion.getExpression();
        
        assertTrue("expressionItem type wrong", 
        		item instanceof ExpressionBinaryOperator);
        ExpressionBinaryOperator bo = (ExpressionBinaryOperator) item;
        ExpressionItem leftOp = bo.getLeftOperand();
        ExpressionItem rightOp = bo.getRightOperand();
        
        assertTrue("left operator type wrong", 
        		leftOp instanceof ExpressionLeaf);
        ExpressionLeaf left = (ExpressionLeaf) leftOp;
        assertEquals("left value wrong", "domain_concept", left.getItem());
        
        assertTrue("right operator type wrong", 
        		rightOp instanceof ExpressionLeaf);
        ExpressionLeaf right = (ExpressionLeaf) rightOp;
        assertTrue("right item type wrong", right.getItem() instanceof CString);
        CString cstring = (CString) right.getItem();
        assertEquals("right value wrong", "blood_pressure.v1", 
        		cstring.getPattern());       
    }
}
