package se.acode.openehr.parser;

import org.openehr.am.archetype.Archetype;
import org.openehr.am.archetype.ontology.*;
import java.util.List;

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
	
	/** Tests that term_bindings and constraint_bindings can be written with a tailing s (see http://www.openehr.org/issues/browse/SPEC-284)
	*/
	public void testBindings() throws Exception {
		ADLParser parser = new ADLParser(loadFromClasspath(
				"adl-test-entry.archetype_bindings.test.adl"));
		Archetype archetype = parser.parse();
        assertNotNull(archetype);
        ArchetypeOntology ontology = archetype.getOntology();
        List<OntologyBinding> termBindings = ontology.getTermBindingList();
		OntologyBinding termBinding = termBindings.get(0);
		assertEquals("term bindings wrong", "SNOMED-CT", termBinding.getTerminology());
		List<OntologyBindingItem> tbis = termBinding.getBindingList();
		TermBindingItem tbi = (TermBindingItem) tbis.get(0);
        assertEquals("term binding item wrong", "[SNOMED-CT::123456]", tbi.getTerms().get(0));
		
		List<OntologyBinding> constrBindings = ontology.getConstraintBindingList();
		OntologyBinding constrBinding = constrBindings.get(0);
		
		assertEquals("binding ontology wrong", "SNOMED-CT", constrBinding.getTerminology());
		List<OntologyBindingItem> cbis = constrBinding.getBindingList();
        QueryBindingItem qbi = (QueryBindingItem) cbis.get(0);
		assertEquals("query binding item wrong", "http://openEHR.org/testconstraintbinding", qbi.getQuery().getUrl());
		

	}
	
}
