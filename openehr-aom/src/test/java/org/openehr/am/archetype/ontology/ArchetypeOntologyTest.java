package org.openehr.am.archetype.ontology;

import junit.framework.TestCase;
import java.util.*;

public class ArchetypeOntologyTest extends TestCase {
	
	public void setUp() {
		String primaryLanguage = "en";
		List<String> languages = new ArrayList<String>();
		languages.add("en");
		languages.add("sv");
		
		List<String> terminologies = new ArrayList<String>();
		terminologies.add("SNOMED CT");
		
		List<OntologyDefinitions> termDefinitionsList = new ArrayList<OntologyDefinitions>();
		List<ArchetypeTerm> items = new ArrayList<ArchetypeTerm>();
		item0001 = new ArchetypeTerm("at0001"); 
		item0001.addItem("text", "blood pressure");
		item0001.addItem("description", "description of BP");
		items.add(item0001);
		item0003 = new ArchetypeTerm("at0003"); 
		item0003.addItem("text", "diastolic pressure");
		item0003.addItem("description", "description of DP");
		items.add(item0003);
		termDefinitionsList.add(new OntologyDefinitions("en", items));
		
		items = new ArrayList<ArchetypeTerm>();
		item0004 = new ArchetypeTerm("at0004"); 
		item0004.addItem("text", "diastolic pressure 2");
		item0004.addItem("description", "description of DP 2");
		items.add(item0004);
		termDefinitionsList.add(new OntologyDefinitions("sv", items));
		
		
		List<OntologyDefinitions> constDefinitionsList = new ArrayList<OntologyDefinitions>();
		items = new ArrayList<ArchetypeTerm>();
		item0002 = new ArchetypeTerm("at0002");
		item0002.addItem("text", "systolic pressure");
		item0002.addItem("description", "description SP");
		items.add(item0002);
		constDefinitionsList.add(new OntologyDefinitions("sv", items));
		
		ontology = new ArchetypeOntology(primaryLanguage, null,
				terminologies, termDefinitionsList, constDefinitionsList, null, null);
	}
	
	public void tearDown() {
		ontology = null;
		item0001 = null;
		item0002 = null;
		item0003 = null;
	}
	
	public void testFetchTermDefinitionWithGivenLanguage() {
		assertEquals("termDefinition wrong", item0001, 
				ontology.termDefinition("en", "at0001"));
		
		assertEquals("termDefinition wrong", item0003, 
				ontology.termDefinition("en", "at0003"));
		
		assertEquals("termDefinition of item00004 wrong", item0004, 
				ontology.termDefinition("sv", "at0004"));
	}
	
	public void testFetchTermDefinitionWithPrimaryLanguage() {
		assertEquals("termDefinition wrong", item0001, 
				ontology.termDefinition("at0001"));
	}
	
	/**
	 * Verify a bug-fix to do with definitions loading in the constructor
	 * 
	 * @throws Exception
	 */
	public void testCreateAndGetDefinitions() throws Exception {
		
		// check term definition
		ArchetypeTerm item = ontology.termDefinition("en", "at0001");
		assertNotNull("definition of [at0001] not found", item);
		assertEquals("item wrong", item0001, item);
		
		// test convenience methods getText and getDescription
		assertEquals("item text wrong", item0001.getText(), "blood pressure");
		assertEquals("item text wrong", item0001.getDescription(), "description of BP");
		
		assertNull(ontology.termDefinition("en", "at0002"));
		
		// check constraint definition
		item = ontology.constraintDefinition("sv", "at0002");
		assertNotNull("definition of [at0002] not found", item);
		assertEquals("item wrong", item0002, item);
		
		assertNull(ontology.constraintDefinition("en", "at0001"));
	}
	
	private ArchetypeOntology ontology;
	private ArchetypeTerm item0001;
	private ArchetypeTerm item0002;
	private ArchetypeTerm item0003;
	private ArchetypeTerm item0004;	
}
