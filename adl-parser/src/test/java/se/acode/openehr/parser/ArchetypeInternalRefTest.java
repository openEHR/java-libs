package se.acode.openehr.parser;

import org.openehr.am.archetype.Archetype;
import org.openehr.am.archetype.constraintmodel.ArchetypeInternalRef;
import org.openehr.am.archetype.constraintmodel.CObject;
import org.openehr.rm.support.basic.Interval;

public class ArchetypeInternalRefTest extends ParserTestBase {

	/**
     * Create new test case
     *
     * @param test
     * @throws Exception
     */
    public ArchetypeInternalRefTest(String test) throws Exception {
        super(test);
    }

    public void testParse() throws Exception {
        ADLParser parser = new ADLParser(loadFromClasspath(
                "adl-test-entry.archetype_internal_ref.test.adl"));
        Archetype archetype = parser.parse();
        assertNotNull(archetype);
        
        CObject node = archetype.node("/attribute2");
        assertTrue("ArchetypeInternalRef expected, actual: " + node.getClass(), 
        		node instanceof ArchetypeInternalRef);
        
        ArchetypeInternalRef ref = (ArchetypeInternalRef) node;
        assertEquals("rmType wrong", "SECTION", ref.getRmTypeName());
        assertEquals("path wrong",  "/attribute1", ref.getTargetPath());
        
        Interval<Integer> occurrences = new Interval<Integer>(1, 2);
        assertEquals("overwriting occurrences wrong", occurrences,
        		ref.getOccurrences());
    }
}
