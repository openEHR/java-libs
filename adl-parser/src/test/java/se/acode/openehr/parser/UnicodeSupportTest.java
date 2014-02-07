package se.acode.openehr.parser;

import org.openehr.am.archetype.Archetype;
import org.openehr.am.archetype.ontology.ArchetypeTerm;

/**
 * This test case verifies the unicode support of the parser. 
 * 
 * It parses an ADL file which contains Chinese and Swedish text 
 * encoded in UTF-8, then compare these text with pre-defined unicode 
 * escaped text strings in the code to check if they get parsed correctly.
 *
 * @author Rong Chen
 * @version 1.0
 */
public class UnicodeSupportTest extends ParserTestBase {

	/**
	 * Create new test case
	 *
	 * @param test
	 * @throws Exception
	 */
	public UnicodeSupportTest(String test) throws Exception {
		super(test);
	}

	public void setUp() throws Exception {
		ADLParser parser = new ADLParser(loadFromClasspath(
				"adl-test-entry.unicode_support.test.adl"), "UTF-8");
		archetype = parser.parse();
	}

	public void tearDown() {
		archetype = null;
	}

	public void testParse() {
		assertNotNull("parsing failed", archetype);
	}

	/**
	 * Tests parsing of Chinese text in the ADL file
	 * 
	 * @throws Exception
	 */
	public void testParsingWithChineseText() throws Exception {
		ArchetypeTerm term = archetype.getOntology().termDefinition("zh", "at0000");
		assertNotNull("definition in zh not found", term);

		// "\u6982\u5ff5" is "concept" in Chinese in escaped unicode format 
		assertEquals("concept text wrong", "\u6982\u5ff5", term.getItem("text"));

		// "\u63cf\u8ff0" is "description" in Chinese in escaped unicode format 
		assertEquals("concept description wrong", "\u63cf\u8ff0", 
				term.getItem("description"));
	}

	/**
	 * Tests parsing of Swedish text in the ADL file
	 * 
	 * @throws Exception
	 */
	public void testParsingWithSwedishText() throws Exception {
		ArchetypeTerm term = archetype.getOntology().termDefinition("sv", "at0000");
		assertNotNull("definition in sv not found", term);

		// "spr\u00e5k" is "language" in Swedish in escaped unicode format 
		assertEquals("concept text wrong", "spr\u00e5k", term.getItem("text"));

		// "Hj\u00e4lp" is "help" in Swedish in escaped unicode format 
		assertEquals("concept description wrong", "Hj\u00e4lp", 
				term.getItem("description"));
	}

	private Archetype archetype;

}
