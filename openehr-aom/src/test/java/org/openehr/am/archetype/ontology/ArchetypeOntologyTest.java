package org.openehr.am.archetype.ontology;

import junit.framework.TestCase;
import java.util.*;

public class ArchetypeOntologyTest extends TestCase {
	
	/**
	 * Verify a bug-fix to do with definitions loading in the constructor
	 * 
	 * @throws Exception
	 */
	public void testCreateAndGetDefinitions() throws Exception {
		String primaryLanguage = "en";
		List<String> languages = new ArrayList<String>();
		languages.add("en");
		
		List<String> terminologies = new ArrayList<String>();
		terminologies.add("SNOMED CT");
		
		List<OntologyDefinitions> termDefinitionsList = new ArrayList<OntologyDefinitions>();
		List<ArchetypeTerm> items = new ArrayList<ArchetypeTerm>();
		ArchetypeTerm item0001 = new ArchetypeTerm("at0001"); 
		item0001.addItem("text", "blood pressure");
		item0001.addItem("description", "description of BP");
		items.add(item0001);
		termDefinitionsList.add(new OntologyDefinitions("en", items));
		
		List<OntologyDefinitions> constDefinitionsList = new ArrayList<OntologyDefinitions>();
		items = new ArrayList<ArchetypeTerm>();
		ArchetypeTerm item0002 = new ArchetypeTerm("at0002");
		item0002.addItem("text", "systolic pressure");
		item0002.addItem("description", "description SP");
		items.add(item0002);
		constDefinitionsList.add(new OntologyDefinitions("en", items));
		
		ArchetypeOntology ontology = new ArchetypeOntology(primaryLanguage, languages,
				terminologies, termDefinitionsList, constDefinitionsList, null, null);
		
		// check term defnition
		ArchetypeTerm item = ontology.termDefinition("en", "at0001");
		assertNotNull("definition of [at0001] not found", item);
		assertEquals("item wrong", item0001, item);
		
		assertNull(ontology.termDefinition("en", "at0002"));
		
		// check constraint definition
		item = ontology.constraintDefinition("en", "at0002");
		assertNotNull("definition of [at0002] not found", item);
		assertEquals("item wrong", item0002, item);
		
		assertNull(ontology.constraintDefinition("en", "at0001"));
	}

}
