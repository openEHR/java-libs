package org.openehr.testing;

import junit.framework.TestSuite;

import org.openehr.am.archetype.Archetype;
import org.openehr.rm.common.archetyped.Pathable;
import org.openehr.schemas.v1.TestDocument.Test;

public class ArchetypeValidationTestCase extends ArchetypeTestCase {

	public ArchetypeValidationTestCase(String name) {
		super(name);
	}

	public void runTest() {
		// TODO implement this for archetype-based validation
	}
	
	// TODO 
	static public junit.framework.Test suite() {
		try {
			testSpec = loadValidationTestCase("validate_testcase_1.xml");
		} catch(Exception e) {
			throw new RuntimeException("failed to load test specification", e);
		}
        TestSuite newSuite = new TestSuite();
        // Test[] tests = testSpec.getTests().getTestArray();
		// for(Test test : tests) {
			// newSuite.addTest(new ArchetypeQueryTestCase(test.getName()));
		// }
        return newSuite;
    };
	
	/* archetype used for validation */
	private Archetype archetype;
	
	/* rm instance to be validated against */
	private Pathable instance;
	
	/* validation test case specification */
	private static Object testSpec;
}
