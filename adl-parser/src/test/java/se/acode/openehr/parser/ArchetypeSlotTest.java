package se.acode.openehr.parser;

import org.openehr.am.archetype.*;

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
                "openEHR-EHR-COMPOSITION.archetype_slot.test.adl"));
        Archetype archetype = parser.parse();
        assertNotNull(archetype);        

    }
}
