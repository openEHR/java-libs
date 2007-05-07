package se.acode.openehr.parser;

import org.openehr.am.archetype.Archetype;

public class BasicGenericTypeTest extends ParserTestBase {
	public void testParse() throws Exception {
        ADLParser parser = new ADLParser(loadFromClasspath(
                "adl-test-SOME_TYPE.generic_type_basic.draft.adl"));
        Archetype archetype = parser.parse();
        assertNotNull(archetype);
	}
}
