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
        assertEquals("uid wrong", null, archetype.getUid());
    }
    
    public void testWithUid() throws Exception {
        ADLParser parser = new ADLParser(loadFromClasspath(
                "openEHR-EHR-ELEMENT.uid_test.v1.adl"));
        Archetype archetype = parser.parse();
        assertNotNull(archetype);
        assertEquals("adl_version wrong", "1.4", archetype.getAdlVersion());
        assertEquals("uid wrong", "1.2.456", archetype.getUid().toString());
    }

    public void testWithUidAndControlledFlag() throws Exception {
        ADLParser parser = new ADLParser(loadFromClasspath(
                "openEHR-EHR-ELEMENT.uid_test.v2.adl"));
        Archetype archetype = parser.parse();
        assertNotNull(archetype);
        assertEquals("adl_version wrong", "1.4", archetype.getAdlVersion());
        assertEquals("uid wrong", "1.2.456::22", archetype.getUid().toString());
        assertEquals("controlled flag wrong", false, archetype.isControlled());
    }

}

