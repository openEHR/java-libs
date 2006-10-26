package org.openehr.am.archetype.ontology;

import junit.framework.TestCase;
import java.util.*;

public class ArchetypeOntologyTest extends TestCase {
	
	/**
	 * Verify a bug-fix to do with definitions loading in the constructor
	 * 
	 * @throws Exception
	 */
	public void testConstructor() throws Exception {
		String primaryLanguage = "en";
		List<String> languages = new ArrayList<String>();
		languages.add("en");
		
		List<String> terminologies = new ArrayList<String>();
		terminologies.add("SNOMED CT");
		
		List<OntologyDefinitions> termDefinitionsList = new ArrayList<OntologyDefinitions>();
		List<DefinitionItem> items = new ArrayList<DefinitionItem>();
		DefinitionItem item0001 = 
			new DefinitionItem("at0001", "blood pressure", "description of BP");
		items.add(item0001);
		termDefinitionsList.add(new OntologyDefinitions("en", items));
		
		List<OntologyDefinitions> constDefinitionsList = new ArrayList<OntologyDefinitions>();
		items = new ArrayList<DefinitionItem>();
		DefinitionItem item0002 = 
			new DefinitionItem("at0002", "systolic pressure", "description SP");
		items.add(item0002);
		constDefinitionsList.add(new OntologyDefinitions("en", items));
		
		ArchetypeOntology ontology = new ArchetypeOntology(primaryLanguage, languages,
				terminologies, termDefinitionsList, constDefinitionsList, null, null);
		
		// check if defnitions are still there
		DefinitionItem item = ontology.definition("at0001");
		assertNotNull("definition of [at0001] not found", item);
		assertEquals("item wrong", item0001, item);
		
		item = ontology.definition("at0002");
		assertNotNull("definition of [at0002] not found", item);
		assertEquals("item wrong", item0002, item);
	}

}
