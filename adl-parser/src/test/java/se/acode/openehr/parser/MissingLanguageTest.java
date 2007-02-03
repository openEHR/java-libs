package se.acode.openehr.parser;

import org.openehr.am.archetype.Archetype;

/**
 * Testcase that verifies parser compatibility with archetypes 
 * that have missing language part
 * 
 * @author Rong Chen 
 */
public class MissingLanguageTest extends ParserTestBase {

	public void testMissingLanguageCompatibility() throws Exception {
		boolean missingLanguageCompatible = true;
		ADLParser parser = new ADLParser(loadFromClasspath(
				"adl-test-entry.missing_language.test.adl"), 
				missingLanguageCompatible);
		Archetype archetype = parser.parse();
		assertNotNull(archetype);
		
		assertEquals("originalLanguage wrong", "zh", 
				archetype.getOriginalLanguage().getCodeString());
	}
}
