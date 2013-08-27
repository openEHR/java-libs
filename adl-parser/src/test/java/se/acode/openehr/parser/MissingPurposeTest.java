package se.acode.openehr.parser;

import org.openehr.am.archetype.Archetype;

public class MissingPurposeTest extends ParserTestBase {
	public void testMissingLanguageCompatibility() throws Exception {
		boolean missingLanguageCompatible = false;
		boolean emptyPurposeCompatible = true;
		ADLParser parser = new ADLParser(loadFromClasspath(
				"adl-test-entry.archetype_desc_missing_purpose.test.adl"), 
				missingLanguageCompatible, emptyPurposeCompatible);
		Archetype archetype = parser.parse();
		assertNotNull(archetype);
		
		assertNotNull("purpose null", 
				archetype.getDescription().getDetails().get(0).getPurpose());
	}
}
