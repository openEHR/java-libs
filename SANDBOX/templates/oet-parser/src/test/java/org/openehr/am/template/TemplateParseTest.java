package org.openehr.am.template;


import java.io.InputStream;

import openEHR.v1.template.ArchetypeIntegrity;
import openEHR.v1.template.Archetyped;
import openEHR.v1.template.COMPOSITION;
import openEHR.v1.template.INSTRUCTION;
import openEHR.v1.template.ITEMTREE;
import openEHR.v1.template.OBSERVATION;
import openEHR.v1.template.ObjectIntegrity;
import openEHR.v1.template.SECTION;
import openEHR.v1.template.TEMPLATE;
import openEHR.v1.template.TemplateDocument;

public class TemplateParseTest extends TemplateTestBase {
	
	public void testParseSimpleTemplate() throws Exception {
		InputStream templIS = fromClasspath("templates/prescription.oet");
		assertNotNull("passed input stream null", templIS);
		TemplateDocument templateDoc = parser.parseTemplate(templIS);
		
		assertNotNull("passed template null", templateDoc);
		assertTrue("template type wrong", templateDoc instanceof TemplateDocument);
				
		TEMPLATE template = templateDoc.getTemplate();
		
		assertEquals("template name wrong", "Prescription", template.getName());
		
		Archetyped def = template.getDefinition();
		
		assertTrue("type wrong", def instanceof COMPOSITION);
		
		COMPOSITION composition = (COMPOSITION) def;
		
		assertEquals("composition archetypeId wrong", 
				"openEHR-EHR-COMPOSITION.prescription.v1", 
				composition.getArchetypeId());
		
		assertTrue("type wrong", composition.getContentArray()[0] instanceof SECTION);
		
		SECTION section = (SECTION) composition.getContentArray()[0];
		
		assertEquals("section archetypeId wrong", 
				"openEHR-EHR-SECTION.medications.v1",
				section.getArchetypeId());
		
		assertEquals("section path wrong", "/content", section.getPath());		
		assertTrue("type wrong", section.getItemArray()[0] instanceof INSTRUCTION);
		
		INSTRUCTION instruction = (INSTRUCTION) section.getItemArray()[0];
		
		assertEquals("instruction archetypeId wrong",
				"openEHR-EHR-INSTRUCTION.medication.v1", instruction.getArchetypeId());
		
		assertEquals("instruction path wrong", "/items", instruction.getPath());
		
		assertTrue("itemTree type wrong", 
				instruction.getActivityDescriptionArray()[0] instanceof ITEMTREE);
		
		ITEMTREE itemtree = (ITEMTREE) instruction.getActivityDescriptionArray()[0];
		
		assertEquals("itemTree archetypeId wrong", 
				"openEHR-EHR-ITEM_TREE.medication.v1", itemtree.getArchetypeId());
		
		assertEquals("itemTree path wrong", "/activities[at0001]/description",
				itemtree.getPath());		
	}	
	
	
	public void testParseSimpleObsTemplateWithIntegrityChecks() throws Exception {
		InputStream templIS = fromClasspath("templates/SampleSimpleBloodPressure.oet");
		assertNotNull("passed input stream null", templIS);
		TemplateDocument templateDoc = parser.parseTemplate(templIS);
		
		assertNotNull("passed template null", templateDoc);
		assertTrue("template type wrong", templateDoc instanceof TemplateDocument);
				
		TEMPLATE template = templateDoc.getTemplate();
		
		assertEquals("template name wrong", "Blood pressure", template.getName());
		
		Archetyped def = template.getDefinition();
		
		assertTrue("type wrong", def instanceof OBSERVATION);
		
		OBSERVATION observation = (OBSERVATION) def;
		
		assertEquals("observation archetypeId wrong", 
				"openEHR-EHR-OBSERVATION.sample_blood_pressure.v1", 
				observation.getArchetypeId());
		
		assertEquals("Incorrect number of integrities", 3, template.getIntegrityChecksArray().length);
		ObjectIntegrity oi = template.getIntegrityChecksArray(0);
		assertNotNull("Object Integrity null", oi);
		assertTrue("type wrong", oi instanceof ArchetypeIntegrity);
		assertEquals ("integrity digest id wrong", "MD5-CAM-1.0.1", oi.getDigest().getId().getStringValue());
		assertEquals ("integrity digest wrong", "DF7B1532856EC51A95483307F6654441", oi.getDigest().getStringValue());
	}	
	
	
	public void testParseTemplateWithSubtemplate() throws Exception {
		InputStream templIS = fromClasspath("templates/templateWithSubtemplate.oet");
		assertNotNull("passed input stream null", templIS);
		TemplateDocument templateDoc = parser.parseTemplate(templIS);
		
		assertNotNull("passed template null", templateDoc);
		assertTrue("template type wrong", templateDoc instanceof TemplateDocument);
				
		TEMPLATE template = templateDoc.getTemplate();
		
		assertEquals("template name wrong", "templateWithSubtemplate", template.getName());
		
		Archetyped def = template.getDefinition();
		
		assertTrue("type wrong", def instanceof COMPOSITION);
		COMPOSITION composition = (COMPOSITION) def;
		assertEquals("composition archetypeId wrong", 
				"openEHR-EHR-COMPOSITION.sample_encounter.v1", 
				composition.getArchetypeId());
		
		assertTrue("type wrong", composition.getContentArray()[0] instanceof OBSERVATION);
		
		OBSERVATION observation = (OBSERVATION) composition.getContentArray()[0];
		
		assertEquals("observation archetypeId wrong", 
				"openEHR-EHR-OBSERVATION.sample_blood_pressure.v1",
				observation.getArchetypeId());
		assertEquals("observation templateId wrong", 
				"53f0d4fa-a00b-493b-a9cb-223e6247c4ae",
				observation.getTemplateId());
		
		
	}	
}

