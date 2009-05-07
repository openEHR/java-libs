package se.acode.openehr.parser;

import org.openehr.am.archetype.Archetype;

import java.util.*;

/**
 * Test cases tests archetype validation after parsing.
 *
 * @author Rong Chen
 * @version 1.0
 */
public class ArchetypeValidatorTest extends ParserTestBase {

    public ArchetypeValidatorTest(String test) {
        super(test);
    }

    public void setUp() throws Exception {
        ADLParser parser = new ADLParser(loadFromClasspath(
                "adl-test-car.use_node.test.adl"));
        archetype = parser.parse();
        validator = new ArchetypeValidator(archetype);
    }

    /**
     * Tests validation logic for internal node reference.
     *
     * @throws Exception
     */
    public void testCheckInternalReferences() throws Exception {
        Map<String, String> expected = new HashMap<String, String>();

        // wrong target path
        expected.put("/wheels[at0005]/parts",
                "/engine[at0001]/parts[at0002]");

        // wrong type
        expected.put("/wheels[at0006]/parts",
                "/wheels[at0001]/parts[at0002]");

        assertEquals(expected, validator.checkInternalReferences());
    }

    private Archetype archetype;
    private ArchetypeValidator validator;
}
