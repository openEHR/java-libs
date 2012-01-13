package se.acode.openehr.parser;

import org.openehr.am.parser.ParsedArchetype;

public class MetaDataItemsTest extends ParserTestBase {
	
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
	
	public void testParseGeneratedADL15Archetype() throws Exception {
		ADL15Parser parser = new ADL15Parser(loadFromClasspath(
						"openEHR-EHR-COMPOSITION.meta_data_items_test.v1.adls"));
		archetype = parser.parse();
		assertNotNull(archetype);
		
		assertEquals("1.5", archetype.getAdlVersion());
		assertFalse("unexpected true value on is_controlled", archetype.isControlled());
		assertTrue("unexpected false value on is_generated", archetype.isGenerated());
	}
	
	public void testParseControlledADL14Archetype() throws Exception {
		ADL15Parser parser = new ADL15Parser(loadFromClasspath(
						"openEHR-EHR-COMPOSITION.meta_data_items_test.v2.adls"));
		archetype = parser.parse();
		assertNotNull(archetype);
		
		assertEquals("1.4", archetype.getAdlVersion());
		assertTrue("unexpected false value on is_controlled", archetype.isControlled());
		assertFalse("unexpected true value on is_generated", archetype.isGenerated());
	}
	
	private ParsedArchetype archetype;
}
