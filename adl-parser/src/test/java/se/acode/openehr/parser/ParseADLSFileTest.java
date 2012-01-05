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
				loadFromClasspath("ADL_1.5_test/validity/basics/" +
						"openEHR-TEST_PKG-WHOLE.most_minimal.v1.adls"));
		archetype = parser.parse();
		assertNotNull(archetype);
	}
	
	private ParsedArchetype archetype;
	private ArchetypeConstraint node;
}
