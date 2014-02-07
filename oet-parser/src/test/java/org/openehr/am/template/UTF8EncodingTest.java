package org.openehr.am.template;

import java.nio.charset.Charset;

import org.openehr.am.archetype.constraintmodel.ArchetypeConstraint;
import org.openehr.am.archetype.constraintmodel.primitive.CString;

public class UTF8EncodingTest extends TemplateTestBase {
	
	public void setUp() throws Exception {		
		super.setUp();
		
		archetype = loadArchetype(
			"openEHR-EHR-ITEM_TREE.medication_test_one.v1.adl");		
	}
	
	public void testSwedishSpecialChars() throws Exception {
		flattenTemplate("test_utf8_encoding.oet");

		constraint = flattened.node("/data[at0001]/items[at0002]/" +
				"items[at0003]/value/value");
		
		CString cs = assertCString(constraint);
		
		// compare to string value "åäö" in unicode 
		assertCStringWithDefaultValue(constraint, "\u00e5\u00e4\u00f6");
	}
	
	private ArchetypeConstraint constraint;
}
