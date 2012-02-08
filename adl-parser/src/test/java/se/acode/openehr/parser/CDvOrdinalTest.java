package se.acode.openehr.parser;

import org.openehr.am.parser.ParsedArchetype;

public class CDvOrdinalTest extends ParserTestBase {
	
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
	
	public void testParseOrdinals() throws Exception {
		String id = "openehr-test_pkg-SOME_TYPE.ordinals.v1";
		ADL15Parser parser = new ADL15Parser(loadFromClasspath(
				ADL15_TEST_PATH + "c_domain_types/" + id + ".adls"));
		archetype = parser.parse();
		assertNotNull(archetype);
	}
	
	private ParsedArchetype archetype;
}
