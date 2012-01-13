package se.acode.openehr.parser;

import org.openehr.am.archetype.ArtefactType;
import org.openehr.am.parser.ParsedArchetype;

public class ArtefactTypeTest extends ParserTestBase {
	
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
		type = null;
	}	
	
	public void testParseArchetype() throws Exception {
		ADL15Parser parser = new ADL15Parser(loadFromClasspath(
						"openEHR-EHR-COMPOSITION.artefact_type_test.v1.adls"));
		archetype = parser.parse();
		assertNotNull(archetype);
		
		type = archetype.getArtefactType();
		assertTrue("unexpected artefact type: " + type,
				ArtefactType.ARCHETYPE.equals(type));
	}
	
	public void testParseTemplate() throws Exception {
		ADL15Parser parser = new ADL15Parser(loadFromClasspath(
						"openEHR-EHR-COMPOSITION.artefact_type_test.v2.adls"));
		archetype = parser.parse();
		assertNotNull(archetype);
		
		type = archetype.getArtefactType();
		assertTrue("unexpected artefact type: " + type,
				ArtefactType.TEMPLATE.equals(type));
	}
	
	private ParsedArchetype archetype;
	private ArtefactType type;
}
