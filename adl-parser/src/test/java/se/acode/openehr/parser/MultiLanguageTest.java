package se.acode.openehr.parser;

import java.util.List;

import org.openehr.am.archetype.Archetype;
import org.openehr.am.archetype.ontology.OntologyDefinitions;

public class MultiLanguageTest extends ParserTestBase {

	/**
	 * Create new test case
	 *
	 * @param test
	 * @throws Exception
	 */
	public MultiLanguageTest(String test) throws Exception {
		super(test);
	}

	/**
	 * Verifies term definitions from multiple language
	 * 
	 * @throws Exception
	 */
	public void testMultiLanguageTermDefinitions() throws Exception {
		ADLParser parser = new ADLParser(loadFromClasspath(
				"adl-test-entry.multi_language.test.adl"));
		
		Archetype archetype = parser.parse();
		List<OntologyDefinitions> list = 
			archetype.getOntology().getTermDefinitionsList();
		
		assertEquals("expected number of termDefnitionsList", 2, list.size());
		
		OntologyDefinitions defs = list.get(0);
		assertEquals("unexpected language", "en", defs.getLanguage());
		
		defs = list.get(1);
		assertEquals("unexpected language", "sv", defs.getLanguage());
	}

	/**
	 * Verifies constraint definitions from multiple language
	 * 
	 * @throws Exception
	 */
	public void testMultiLanguageConstraintDefinitions() throws Exception {
		ADLParser parser = new ADLParser(loadFromClasspath(
				"adl-test-entry.multi_language.test.adl"));

		Archetype archetype = parser.parse();
		List<OntologyDefinitions> list = 
			archetype.getOntology().getConstraintDefinitionsList();
		assertEquals("expected number of constraintDefinitionsList", 2, list.size());
	
		OntologyDefinitions defs = list.get(0);
		assertEquals("unexpected language", "en", defs.getLanguage());
		
		defs = list.get(1);
		assertEquals("unexpected language", "sv", defs.getLanguage());
	}
}
