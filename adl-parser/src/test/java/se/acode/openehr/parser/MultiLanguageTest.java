package se.acode.openehr.parser;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.openehr.am.archetype.Archetype;
import org.openehr.am.archetype.ontology.OntologyDefinitions;
import org.openehr.rm.common.resource.TranslationDetails;

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
	
	/**
	 * Verifies that translation details are parsed correctly if not all optional elements are present.
	 * 
	 * @throws Exception
	 */
	public void testTranslationDetails() throws Exception {
		ADLParser parser = new ADLParser(loadFromClasspath(
				"adl-test-entry.testtranslations.test.adl"));
		
		Archetype archetype = parser.parse();
		Map<String, TranslationDetails> translations = archetype.getTranslations();
		
		for (Entry<String, TranslationDetails> translation : translations.entrySet()) {
			TranslationDetails transDet = translation.getValue();
			String lang = transDet.getLanguage().getCodeString();
			if (lang.equals("de")) {
				assertEquals("wrong accreditation", "test Accreditation!", transDet.getAccreditation());
				assertEquals("wrong organisation", "test organisation", transDet.getAuthor().get("organisation"));
			} else if (lang.equals("es")) {
				//  They need to be null 
				assertEquals("wrong accreditation", null, transDet.getAccreditation());
				assertEquals("wrong organisation", null, transDet.getAuthor().get("organisation"));
			}
		}		
	}
}
