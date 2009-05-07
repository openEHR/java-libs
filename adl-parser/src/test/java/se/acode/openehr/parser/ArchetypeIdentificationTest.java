package se.acode.openehr.parser;

import org.openehr.am.archetype.Archetype;

/**
 * MostMinimalADLTest
 *
 * @author Rong Chen
 * @version 1.0
 */
public class ArchetypeIdentificationTest extends ParserTestBase {

    /**
     * Create new test case
     *
     * @param test
     * @throws Exception
     */
    public ArchetypeIdentificationTest(String test) throws Exception {
        super(test);
    }

    public void testParse() throws Exception {
        ADLParser parser = new ADLParser(loadFromClasspath(
                "adl-test-entry.archetype_identification.test.adl"));
        Archetype archetype = parser.parse();
        assertNotNull(archetype);
        assertEquals("adl_version wrong", "1.4", archetype.getAdlVersion());
    }
}

