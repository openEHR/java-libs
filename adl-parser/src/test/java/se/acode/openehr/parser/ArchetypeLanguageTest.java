/*
 * Copyright (C) 2004 Rong Chen, Acode HB, Sweden
 * All rights reserved.
 *
 * The contents of this software are subject to the FSF GNU Public License 2.0;
 * you may not use this software except in compliance with the License. You may
 * obtain a copy of the License at http://www.fsf.org/licenses/gpl.html
 *
 * Software distributed under the License is distributed on an 'AS IS' basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 */
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

		assertEquals("acrreditation wrong", null, td.getAccreditation());

		map = td.getOtherDetails();
		assertEquals("review 1 wrong", "Ron Weasley", map.get("review 1"));
		assertEquals("review 2 wrong", "Rubeus Hagrid", map.get("review 2"));
	}
}
