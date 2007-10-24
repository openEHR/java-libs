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

import org.openehr.am.archetype.Archetype;
import org.openehr.am.archetype.ontology.ArchetypeTerm;
import org.openehr.am.archetype.ontology.DefinitionItem;

/**
 * This test case verifies that the parser supports the optional BOM (Byte Order Mark) of UTF-8.
* This BOM is introduced by many Windows based Texteditors and not treated correctly by Java without extra help.
 * 
 * It parses an ADL file (UTF-8 with BOM encoded)  to ascertain that it is parsable.  
 *
 * @author Sebastian Garde
 * @author Rong Chen
 * @version 1.0
 */
public class UnicodeBOMSupportTest extends ParserTestBase {

	/**
	 * Create new test case
	 *
	 * @param test
	 * @throws Exception
	 */
	public UnicodeBOMSupportTest(String test) throws Exception {
		super(test);
	}

	public void setUp() throws Exception {
		ADLParser parser = new ADLParser(loadFromClasspath(
				"adl-test-entry.unicode_BOM_support.test.adl"), "UTF-8");
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
