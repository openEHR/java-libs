package se.acode.openehr.parser;

import org.openehr.am.archetype.Archetype;

public class SpecialStringTest extends ParserTestBase {
	public void testParseSpecialStringLiterals() throws Exception {
		ADLParser parser = new ADLParser(
				loadFromClasspath("adl-test-entry.special_string.test.adl"),
				false, false);
		Archetype archetype = parser.parse();
		assertNotNull(archetype);
	}
}
