package se.acode.openehr.parser;

import java.io.File;
import java.io.StringWriter;

import org.openehr.am.archetype.*;
import org.openehr.am.archetype.output.ADLOutputter;

import junit.framework.TestCase;

public class ArchetypeSlotTest extends ADLParserTestBase {

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
        ADLParser parser = new ADLParser(new File(dir,
                "openEHR-EHR-COMPOSITION.archetype_slot.test.adl"));
        Archetype archetype = parser.parse();
        assertNotNull(archetype);
        
        ADLOutputter outputter = new ADLOutputter();
        StringWriter writer = new StringWriter();
        outputter.output(archetype, writer);

        // System.out.println(writer.toString());
    }
}
