package se.acode.openehr.parser;

import org.openehr.am.archetype.Archetype;
import org.openehr.am.archetype.ontology.ArchetypeOntology;
import org.openehr.am.archetype.ontology.ArchetypeTerm;

public class ArchetypeOntologyTest extends ParserTestBase {
	
	public void testParseTermDefinition() throws Exception {
		ADLParser parser = new ADLParser(loadFromClasspath(
				"adl-test-entry.archetype_ontology.test.adl"));
		Archetype archetype = parser.parse();
        assertNotNull(archetype);
        ArchetypeOntology ontology = archetype.getOntology();
        
        ArchetypeTerm term = ontology.termDefinition("en", "at0000");
        assertEquals("text wrong", "some text", term.getItem("text"));
        assertEquals("comment wrong", "some comment", term.getItem("comment"));
        assertEquals("description wrong", "some description", 
        		term.getItem("description"));
	}
}
