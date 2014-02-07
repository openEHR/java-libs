package se.acode.openehr.parser;

import java.util.List;

import org.openehr.am.archetype.constraintmodel.*;

public class SpecialStringTest extends ParserTestBase {	
	
	public SpecialStringTest(String test) throws Exception {
		super(test);
		ADLParser parser = new ADLParser(loadFromClasspath(
			"adl-test-entry.special_string.test.adl"));
		attributeList = parser.parse().getDefinition().getAttributes();
	}
	
	public void testParseEscapedDoubleQuote() throws Exception {
		list = getConstraints(0);
		assertCString(list.get(0), null, new String[] { "some\"thing" }, null);
	}
	
	public void testParseEscapedBackslash() throws Exception {
		list = getConstraints(0);
		assertCString(list.get(1), null, new String[] { "any\\thing" }, null);
	}
	
	private List getConstraints(int index) {
		CAttribute ca = (CAttribute) attributeList.get(index);
		return ((CComplexObject) ca.getChildren().get(0)).getAttributes();
	}
	
	private List attributeList;
	private List list;
}
