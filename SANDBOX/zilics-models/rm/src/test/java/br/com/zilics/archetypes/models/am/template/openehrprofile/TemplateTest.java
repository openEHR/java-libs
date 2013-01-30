package br.com.zilics.archetypes.models.am.template.openehrprofile;

import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import br.com.zilics.archetypes.models.am.archetype.Archetype;
import br.com.zilics.archetypes.models.rm.utils.xml.XmlParser;
import br.com.zilics.archetypes.models.test.ResourceTestCase;

public class TemplateTest extends ResourceTestCase {
	public TemplateTest(String testName) {
		super(testName);
	}
	
	public void testGetAllUsedArchetypes() throws Exception {
		HashSet<String> expected = new HashSet<String>();
		expected.add("openEHR-EHR-COMPOSITION.composition_test.v1");
		expected.add("openEHR-EHR-OBSERVATION.observation_test.v1");
		expected.add("openEHR-EHR-CLUSTER.cluster_test.v1");
		expected.add("openEHR-EHR-ADMIN_ENTRY.admin_entry_test.v1");
		expected.add("openEHR-EHR-SECTION.section_test.v1");
		expected.add("openEHR-EHR-ELEMENT.element_test.v1");
		expected.add("openEHR-EHR-EVALUATION.evaluation_test.v1");
		expected.add("openEHR-EHR-INSTRUCTION.instruction_test.v1");

		Template template = (Template) XmlParser.parseXml(this.getClass().getResourceAsStream("/xml/templates/Template1.oet"));
		template.validate();

		assertEquals(expected, template.getAllUsedArchetypes());
	}
	
	public void testResolveArchetypesAndValidate() throws Exception {
		Map<String, Archetype> archetypes = new HashMap<String, Archetype>();
		String directory = "/xml/archetypes/"; 
		for(String fileName : listDirectory(directory)) {
			InputStream is = this.getClass().getResourceAsStream(directory + fileName);
			Archetype archetype = (Archetype) XmlParser.parseXml(is);
			assertNotNull(archetype);
			archetype.validate();
			archetypes.put(archetype.getArchetypeId().getValue(), archetype);
		}
		
		Template template = (Template) XmlParser.parseXml(this.getClass().getResourceAsStream("/xml/templates/Template1.oet"));
		template.resolveArchetypes(archetypes);

		template.validate();
		
	}
}
