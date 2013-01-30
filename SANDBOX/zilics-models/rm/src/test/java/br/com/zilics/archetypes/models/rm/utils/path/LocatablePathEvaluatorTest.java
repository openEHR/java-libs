package br.com.zilics.archetypes.models.rm.utils.path;

import java.io.InputStream;

import br.com.zilics.archetypes.models.rm.composition.Composition;
import br.com.zilics.archetypes.models.rm.utils.path.context.PathEvaluationContext;
import br.com.zilics.archetypes.models.rm.utils.path.model.ListValue;
import br.com.zilics.archetypes.models.rm.utils.path.parsed.TreeNode;
import br.com.zilics.archetypes.models.rm.utils.xml.XmlParser;
import junit.framework.TestCase;

public class LocatablePathEvaluatorTest extends TestCase {
	public LocatablePathEvaluatorTest(String testName) {
		super(testName);
	}
	
	private ListValue parseAndEvaluate(String expr, LocatablePathEvaluator evaluator) throws Exception {
		TreeNode parsed = PathUtils.parseArchPath(expr);
		ListValue result = parsed.evaluate(new PathEvaluationContext(evaluator));
		assertNotNull(result);
		return result;
	}
	
	public void testEvaluate() throws Exception {
		String directory = "/xml/compositions"; 
		String fileName = "composition_1.xml";
		InputStream is = this.getClass().getResourceAsStream(directory + "/" + fileName);
		Composition composition = (Composition) XmlParser.parseXml(is);
		LocatablePathEvaluator evaluator = new LocatablePathEvaluator(composition, null);
		parseAndEvaluate("//.", evaluator);
	}
	
}
