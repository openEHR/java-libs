package br.com.zilics.archetypes.models.am.utils.path;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import br.com.zilics.archetypes.models.am.archetype.Archetype;
import br.com.zilics.archetypes.models.am.template.openehrprofile.Template;
import br.com.zilics.archetypes.models.rm.utils.path.PathUtils;
import br.com.zilics.archetypes.models.rm.utils.path.context.PathEvaluationContext;
import br.com.zilics.archetypes.models.rm.utils.path.model.ListValue;
import br.com.zilics.archetypes.models.rm.utils.path.parsed.TreeNode;
import br.com.zilics.archetypes.models.rm.utils.xml.XmlParser;
import br.com.zilics.archetypes.models.test.ResourceTestCase;

public class TemplatePathEvaluatorTest extends ResourceTestCase {
	public TemplatePathEvaluatorTest(String testName) {
		super(testName);
	}
	
	private ListValue parseAndEvaluate(String expr, TemplatePathEvaluator evaluator) throws Exception {
		TreeNode parsed = PathUtils.parseArchPath(expr);
		ListValue result = parsed.evaluate(new PathEvaluationContext(evaluator));
		assertNotNull(result);
		System.out.println(result);
		return result;
	}
	
	public void testEvaluate() throws Exception {
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
		TemplatePathEvaluator evaluator = new TemplatePathEvaluator(template);
		parseAndEvaluate("/content/ancestor-or-self::*", evaluator);
	}
}
