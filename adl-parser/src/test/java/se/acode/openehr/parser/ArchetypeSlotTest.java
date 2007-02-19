package se.acode.openehr.parser;

import org.openehr.am.archetype.*;
import org.openehr.am.archetype.assertion.Assertion;
import org.openehr.am.archetype.constraintmodel.ArchetypeSlot;
import org.openehr.am.archetype.constraintmodel.CObject;
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
        
        CObject node = archetype.node("/content");
        assertTrue("ArchetypeSlot expected", node instanceof ArchetypeSlot);
        
        ArchetypeSlot slot = (ArchetypeSlot) node;
        assertEquals("nodeId wrong", "at0001", slot.getNodeID());
        assertEquals("rmTypeName wrong", "SECTION", slot.getRmTypeName());
        assertEquals("occurrences wrong", new Interval<Integer>(0, 1), 
        		slot.getOccurrences());
        
        assertEquals("includes total wrong", 1, slot.getIncludes().size());
        assertEquals("Excludes total wrong", 2, slot.getExcludes().size());
        
        Assertion assertion = slot.getIncludes().iterator().next();        
    }
}
