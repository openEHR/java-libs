package se.acode.openehr.parser;

import org.openehr.am.archetype.constraintmodel.ArchetypeConstraint;
import org.openehr.am.parser.ParsedArchetype;

public class ParseADLSFileTest extends ParserTestBase {
	
	/**
	 * The fixture set up called before every test method.
	 */
	protected void setUp() throws Exception {
		
	}

	/**
	 * The fixture clean up called after every test method.
	 */
	protected void tearDown() throws Exception {
		archetype = null;
		node = null;
	}
	
	public void testParseMostMinimalV1() throws Exception{
		ADL15Parser parser = new ADL15Parser(
				loadFromClasspath(ADL15_TEST_PATH +
						"basics/openEHR-TEST_PKG-WHOLE.most_minimal.v1.adls"));
		archetype = parser.parse();
		assertNotNull(archetype);
	}

	
	public void testParseStructureTest() throws Exception{
		ADL15Parser parser = new ADL15Parser(
				loadFromClasspath(ADL15_TEST_PATH +
						"basics/openEHR-TEST_PKG-BOOK.structure_test1.v1.adls"));
		archetype = parser.parse();
		assertNotNull(archetype);
	}
	
	public void testParseBasicTypesTest() throws Exception{
		ADL15Parser parser = new ADL15Parser(
				loadFromClasspath(ADL15_TEST_PATH +
						"basics/openehr-TEST_PKG-WHOLE.basic_types.v1.adls"));
		archetype = parser.parse();
		assertNotNull(archetype);
	}

	
	public void testParseAnnotationsTest() throws Exception{
		ADL15Parser parser = new ADL15Parser(
				loadFromClasspath(ADL15_TEST_PATH +
						"annotations/openEHR-EHR-EVALUATION.annotations_1st_child.v1.adls"));
		archetype = parser.parse();
		assertNotNull(archetype);
	}
	
	private static final String ADL15_TEST_PATH = "ADL_1.5_test/validity/";
	private ParsedArchetype archetype;
	private ArchetypeConstraint node;
}
