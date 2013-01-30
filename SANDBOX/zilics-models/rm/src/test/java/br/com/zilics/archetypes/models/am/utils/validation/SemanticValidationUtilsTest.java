package br.com.zilics.archetypes.models.am.utils.validation;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import br.com.zilics.archetypes.models.am.archetype.Archetype;
import br.com.zilics.archetypes.models.am.template.openehrprofile.Template;
import br.com.zilics.archetypes.models.rm.composition.Composition;
import br.com.zilics.archetypes.models.rm.utils.xml.XmlParser;
import br.com.zilics.archetypes.models.test.ResourceTestCase;

public class SemanticValidationUtilsTest extends ResourceTestCase {
	public SemanticValidationUtilsTest(String testName) {
		super(testName);
	}
	
	public void testValidate() throws Exception {
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

		Composition composition = (Composition) XmlParser.parseXml(this.getClass().getResourceAsStream("/xml/compositions/composition_1.xml"));
		composition.validate();
		
		SemanticValidationResult result = new SemanticValidationResult();
		SemanticValidationUtils.validateCObject(composition, template.getRootConstraint().getArchetypeConstraint(), template.getRootConstraint(), result);
		System.out.println(result);
	}
}
