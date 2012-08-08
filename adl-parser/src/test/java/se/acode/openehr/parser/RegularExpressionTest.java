package se.acode.openehr.parser;

public class RegularExpressionTest extends ParserTestBase {	
	
	public RegularExpressionTest(String test) throws Exception {
		super(test);
	}
	
	public void testParseRegularExpressions() throws Exception {
		ADLParser parser = new ADLParser(loadFromClasspath(
			"adl-test-entry.regular_expression.test.adl"));
		assertNotNull(parser.parse().getDefinition());
	}	
}
