package se.acode.openehr.parser;

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
	}	

	public void testParseMostMinimalV1() throws Exception{
		ADL15Parser parser = new ADL15Parser(
				loadFromClasspath(ADL15_TEST_PATH +
						"basics/openEHR-TEST_PKG-WHOLE.most_minimal.v1.adls"));
		archetype = parser.parse();
		assertNotNull(archetype);
	}
	
	public void testParseMostMinimalV2() throws Exception{
		ADL15Parser parser = new ADL15Parser(
				loadFromClasspath(ADL15_TEST_PATH +
						"basics/openEHR-TEST_PKG-WHOLE.most_minimal.v2.adls"));
		archetype = parser.parse();
		assertNotNull(archetype);
	}
		
	public void testParseMostMinimalV3() throws Exception{
		ADL15Parser parser = new ADL15Parser(
				loadFromClasspath(ADL15_TEST_PATH +
						"basics/openEHR-TEST_PKG-WHOLE.most_minimal.v3.adls"));
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
	
	public void testParseLegacyADL14Test() throws Exception{
		ADL15Parser parser = new ADL15Parser(
				loadFromClasspath(ADL15_TEST_PATH +
						"legacy_adl_1.4/openEHR-EHR-CLUSTER.aa.v1.adls"));
		archetype = parser.parse();
		assertNotNull(archetype);
	}
	
	public void testParseAddedNodesOrder() throws Exception{
		ADL15Parser parser = new ADL15Parser(
				loadFromClasspath(ADL15_TEST_PATH +
						"specialisation/openEHR-EHR-OBSERVATION.spec_test_obs-added_nodes_ordered.v1.adls"));
		archetype = parser.parse();
		assertNotNull(archetype);
	}
	
	public void testParseGeneratedKeyword() throws Exception{
		ADL15Parser parser = new ADL15Parser(
				loadFromClasspath(ADL15_TEST_PATH +
						"basics/openEHR-TEST_PKG-WHOLE.most_minimal.v4.adls"));
		archetype = parser.parse();
		assertNotNull(archetype);
	}
	
	public void testParseTemplate() throws Exception{
		ADL15Parser parser = new ADL15Parser(
				loadFromClasspath(ADL15_TEST_PATH +
						"templates/openEHR-EHR-COMPOSITION.ext_ref.v1.adls"));
		archetype = parser.parse();
		assertNotNull(archetype);
	}
	
	public void testMultipleAlternatives() throws Exception {
		ADL15Parser parser = new ADL15Parser(
				loadFromClasspath(ADL15_TEST_PATH +
						"structure/openEHR-EHR-OBSERVATION.multiple_alternatives.v1.adls"));
		archetype = parser.parse();
		assertNotNull(archetype);
	}
	
	private static final String ADL15_TEST_PATH = "ADL_1.5_test/validity/";
	private ParsedArchetype archetype;
}
