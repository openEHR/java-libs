package org.openehr.am.parser;

public class StructureTest extends ParserTestBase {
	
	public StructureTest(String test) {
		super(test);
	}
	
	public void testParseSimpleDADL() throws Exception {
		DADLParser parser = new DADLParser(loadFromClasspath(
			"blood_pressure_001.dadl"));
		parser.parse();
	}
	
	public void testTypedObjectWithKeyedAttributes() throws Exception {
		DADLParser parser = new DADLParser(loadFromClasspath(
			"person_001.dadl"));
		parser.parse();
	}
}
