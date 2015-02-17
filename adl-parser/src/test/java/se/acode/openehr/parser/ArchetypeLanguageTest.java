package se.acode.openehr.parser;

import java.util.Map;

import org.openehr.am.archetype.Archetype;
import org.openehr.rm.common.resource.TranslationDetails;

/**
 * Testcase for Archetype language section parsing
 * 
 * @author Rong Chen
 * @version 1.0
 */
public class ArchetypeLanguageTest extends ParserTestBase {

	/**
	 * Create new test case
	 * 
	 * @param test
	 * @throws Exception
	 */
	public ArchetypeLanguageTest(String test) throws Exception {
		super(test);
	}

	public void testParseLanguageSection() throws Exception {
		ADLParser parser = new ADLParser(
				loadFromClasspath("adl-test-entry.archetype_language.test.adl"));
		Archetype archetype = parser.parse();
		assertNotNull(archetype);

		Map<String, TranslationDetails> translations = archetype
				.getTranslations();
		assertNotNull("translations null");

		TranslationDetails td = translations.get("de");
		assertNotNull("translation de missing", td);
		Map<String, String> map = td.getAuthor();
		assertNotNull("author null", map);
		assertEquals("author.name wrong", "Harry Potter", map.get("name"));
		assertEquals("author.email wrong", "harry@something.somewhere.co.uk",
				map.get("email"));

		assertEquals("acrreditation wrong",
				"British Medical Translator id 00400595", td.getAccreditation());

		map = td.getOtherDetails();
		assertEquals("review 1 wrong", "Ron Weasley", map.get("review 1"));
		assertEquals("review 2 wrong", "Rubeus Hagrid", map.get("review 2"));
	}

	public void testParseLanguageWithoutAccreditation() throws Exception {
		ADLParser parser = new ADLParser(loadFromClasspath(
				"adl-test-entry.archetype_language_no_accreditation.test.adl"));
		Archetype archetype = parser.parse();
		assertNotNull(archetype);

		Map<String, TranslationDetails> translations = archetype
				.getTranslations();
		assertNotNull("translations null");

		TranslationDetails td = translations.get("de");
		assertNotNull("translation de missing", td);
		Map<String, String> map = td.getAuthor();
		assertNotNull("author null", map);
		assertEquals("author.name wrong", "Harry Potter", map.get("name"));
		assertEquals("author.email wrong", "harry@something.somewhere.co.uk",
				map.get("email"));

		assertEquals("accreditation wrong", null, td.getAccreditation());

		map = td.getOtherDetails();
		assertEquals("review 1 wrong", "Ron Weasley", map.get("review 1"));
		assertEquals("review 2 wrong", "Rubeus Hagrid", map.get("review 2"));
	}
	
	public void testParseLanguageWithAccreditationBeforeLanguage() throws Exception {
		ADLParser parser = new ADLParser(loadFromClasspath(
				"adl-test-entry.archetype_language_order_of_translation_details.test.adl"));
		Archetype archetype = parser.parse();
		assertNotNull(archetype);

		Map<String, TranslationDetails> translations = archetype
				.getTranslations();
		assertNotNull("translations null");

		TranslationDetails td = translations.get("de");
		assertNotNull("translation de missing", td);
		Map<String, String> map = td.getAuthor();
		assertNotNull("author null", map);
		assertEquals("author.name wrong", "Harry Potter", map.get("name"));
		assertEquals("author.email wrong", "harry@something.somewhere.co.uk",
				map.get("email"));

		assertEquals("accreditation wrong", "Seven OWLs at Hogwards", td.getAccreditation());
		assertEquals("language wrong", "de", td.getLanguage().getCodeString());
		map = td.getOtherDetails();
		assertEquals("review 1 wrong", "Ron Weasley", map.get("review 1"));
		assertEquals("review 2 wrong", "Rubeus Hagrid", map.get("review 2"));
	}
	
	public void testParseTranslationsLanguageAuthor() throws Exception {
		ADLParser parser = new ADLParser(loadFromClasspath(
				"adl-test-entry.translations_language_author.test.adl"));
		Archetype archetype = parser.parse();
		assertNotNull(archetype);

		Map<String, TranslationDetails> translations = archetype
				.getTranslations();
		assertNotNull("translations null");

		TranslationDetails td = translations.get("de");
		assertNotNull("translation de missing", td);
		Map<String, String> map = td.getAuthor();
		assertNotNull("author null", map);	
	}
	
	// test the reverse order of language, author in translations
	public void testParseTranslationsAuthorLanguage() throws Exception {
		ADLParser parser = new ADLParser(loadFromClasspath(
				"adl-test-entry.translations_author_language.test.adl"));
		Archetype archetype = parser.parse();
		assertNotNull(archetype);

		Map<String, TranslationDetails> translations = archetype
				.getTranslations();
		assertNotNull("translations null");

		TranslationDetails td = translations.get("de");
		assertNotNull("translation de missing", td);
		Map<String, String> map = td.getAuthor();
		assertNotNull("author null", map);	
	}

    public void testParseMultipleLanguageTerms() throws Exception {
        ADLParser parser = new ADLParser(loadFromClasspath(
                "adl-test-entry.archetype_language.test2.adl"));
        Archetype archetype = parser.parse();
        assertNotNull(archetype);

        assertEquals(2, archetype.getOntology().getLanguages().size());
    }
}
