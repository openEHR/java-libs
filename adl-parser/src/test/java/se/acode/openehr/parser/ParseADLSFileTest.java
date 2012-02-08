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
	
	private ParsedArchetype archetype;
}
