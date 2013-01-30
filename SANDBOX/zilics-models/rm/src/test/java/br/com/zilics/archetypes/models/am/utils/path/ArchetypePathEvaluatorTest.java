package br.com.zilics.archetypes.models.am.utils.path;

import java.io.InputStream;

import br.com.zilics.archetypes.models.am.archetype.Archetype;
import br.com.zilics.archetypes.models.rm.utils.path.PathUtils;
import br.com.zilics.archetypes.models.rm.utils.path.context.PathEvaluationContext;
import br.com.zilics.archetypes.models.rm.utils.path.model.ListValue;
import br.com.zilics.archetypes.models.rm.utils.path.parsed.TreeNode;
import br.com.zilics.archetypes.models.rm.utils.xml.XmlParser;
import junit.framework.TestCase;

public class ArchetypePathEvaluatorTest extends TestCase {
	public ArchetypePathEvaluatorTest(String testName) {
		super(testName);
	}
	
	private ListValue parseAndEvaluate(String expr, ArchetypePathEvaluator evaluator) throws Exception {
		TreeNode parsed = PathUtils.parseArchPath(expr);
		ListValue result = parsed.evaluate(new PathEvaluationContext(evaluator));
		assertNotNull(result);
		return result;
	}
	
	public void testEvaluate() throws Exception {
		String directory = "/xml/archetypes"; 
		String fileName = "openEHR-EHR-OBSERVATION.observation_test.v1.xml";
		InputStream is = this.getClass().getResourceAsStream(directory + "/" + fileName);
		Archetype archetype = (Archetype) XmlParser.parseXml(is);
		archetype.validate();
		ArchetypePathEvaluator evaluator = new ArchetypePathEvaluator(archetype);
		parseAndEvaluate("//./@description", evaluator);
	}
}
