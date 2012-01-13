package se.acode.openehr.parser;

import org.openehr.am.parser.ParsedArchetype;

public class IdConceptTest extends ParserTestBase {
	
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
	
	public void testParseArchetypeId() throws Exception {
		String id = "openEHR-EHR-COMPOSITION.id_concept_test.v1";
		ADL15Parser parser = new ADL15Parser(loadFromClasspath(
						id + ".adls"));
		archetype = parser.parse();
		assertNotNull(archetype);
		
		assertEquals(id, archetype.getArchetypeId());
		assertNull("expected non-null conceptId", archetype.getConceptId());
	}
	
	public void testParseConceptId() throws Exception {
		String id = "openEHR-EHR-COMPOSITION.id_concept_test.v2";
		ADL15Parser parser = new ADL15Parser(loadFromClasspath(
						id + ".adls"));
		archetype = parser.parse();
		assertNotNull(archetype);
		
		assertEquals(id, archetype.getArchetypeId());
		assertEquals("at0000", archetype.getConceptId());
	}
	
	public void testParseParentArchetypeId() throws Exception {
		String id = "openEHR-EHR-COMPOSITION.id_concept_test.v3";
		ADL15Parser parser = new ADL15Parser(loadFromClasspath(
						id + ".adls"));
		archetype = parser.parse();
		assertNotNull(archetype);
		
		assertEquals("openEHR-EHR-COMPOSITION.parent_test.v1", 
				archetype.getParentArchetypeId());
	}
	
	private ParsedArchetype archetype;
}
