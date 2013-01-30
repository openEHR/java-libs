package br.com.zilics.archetypes.models.am.template.openehrprofile;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import br.com.zilics.archetypes.models.am.archetype.Archetype;
import br.com.zilics.archetypes.models.rm.utils.xml.XmlParser;
import br.com.zilics.archetypes.models.test.ResourceTestCase;

public class TemplateConstraintTest extends ResourceTestCase {
	public TemplateConstraintTest(String testName) {
		super(testName);
	}
	
	public void testNavigation() throws Exception {
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
