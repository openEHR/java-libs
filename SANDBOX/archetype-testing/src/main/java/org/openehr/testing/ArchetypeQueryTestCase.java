/*
 * component:   "openEHR Java Reference Implementation"
 * description: "Class ArchetypeQueryTestCase"
 * keywords:    "archetype testing"
 *
 * author:      "Rong Chen <rong.acode@gmail.com>"
 * copyright:   "Copyright (c) 2008 Cambio Healthcare Systems, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL$"
 * revision:    "$LastChangedRevision$"
 * last_change: "$LastChangedDate$"
 */
package org.openehr.testing;

import junit.framework.TestSuite;

import org.openehr.rm.common.archetyped.Pathable;
import org.openehr.schemas.v1.QueryTestSpecificationDocument.QueryTestSpecification;
import org.openehr.schemas.v1.TestDocument.Test;

/**
 * Test case for archetype path based queries
 * 
 * Steps:
 * 1. load the test specification
 * 2. load the rm instance as specified 
 * 3. execute the query on the instance
 * 4. compare the query result with expected result
 * 5. report the test result
 * 
 * @author Rong.Chen
 */
public class ArchetypeQueryTestCase extends ArchetypeTestCase {
	
	public ArchetypeQueryTestCase(String name) {
		super(name);
	}
	
	public void setUp() throws Exception {
		testSpec = loadQueryTestCase("query_testcase_1.xml");
		Object obj = loadRMInstance(testSpec.getInstance());
		
		assert(obj instanceof Pathable);
		
		instance = (Pathable) obj;		
	}
	
	public void tearDown() {
		instance = null;
		testSpec = null;
	}
	
	public Pathable getInstance() {
		return instance;
	}
	
	public QueryTestSpecification getTestSpecification() {
		return testSpec;
	}	
	
	/**
	 * Runs named test within the test specification
	 * 
	 * NOTE: overwrites the default Testcase.runTest()
	 */
	public void runTest() {
		Test[] tests = testSpec.getTests().getTestArray();
		for(Test test : tests) {
			if(getName().equals(test.getName())) {
				String path = test.getPath();
				String expected = test.getExpected();
				Object obj = instance.itemAtPath(path);
				assertNotNull("unexpected null for path: " + path, obj);
				assertEquals("unexpected value for path: " + path,
						expected, obj.toString());
				break;
			}
		}
	}
	
	/*
	 * ONLY for testing purpose
	 * 
	 * For intentional failed test in the test specification, 
	 * type "mvn package -Dmaven.test.skip=true" to proceed with packaging 
	 * 
	 */ 
	void executeAllTests() {
		Test[] tests = testSpec.getTests().getTestArray();
		
		for(Test test : tests) {
			String path = test.getPath();
			String expected = test.getExpected();
			Object obj = instance.itemAtPath(path);
			assertNotNull("unexpected null for path: " + path, obj);
			assertEquals("unexpected value for path: " + path, expected, 
					obj.toString());			
		}
	}
	
	public int countTests() {
		return testSpec.getTests().getTestArray().length;
	}
	
	static public junit.framework.Test suite() {
		try {
			testSpec = loadQueryTestCase("query_testcase_1.xml");
		} catch(Exception e) {
			throw new RuntimeException("failed to load test specification", e);
		}
        TestSuite newSuite = new TestSuite();
        Test[] tests = testSpec.getTests().getTestArray();
		for(Test test : tests) {
			newSuite.addTest(new ArchetypeQueryTestCase(test.getName()));
		}
        return newSuite;
    };
	
	/**
	 * Run the test case from the JUnit TestRunner in executable jar
	 */ 
	static public void main(String[] sa) {
        junit.swingui.TestRunner.run(ArchetypeQueryTestCase.class);        
    }
	
	private Pathable instance;
	private static QueryTestSpecification testSpec;
}
/*
 * ***** BEGIN LICENSE BLOCK ***** Version: MPL 1.1/GPL 2.0/LGPL 2.1
 * 
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the 'License'); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at http://www.mozilla.org/MPL/
 * 
 * Software distributed under the License is distributed on an 'AS IS' basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 * 
 * The Original Code is ArchetypeQueryTestCase.java
 * 
 * The Initial Developer of the Original Code is Rong Chen. Portions created by
 * the Initial Developer are Copyright (C) 2003-2008 the Initial Developer. All
 * Rights Reserved.
 * 
 * Contributor(s):
 * 
 * Software distributed under the License is distributed on an 'AS IS' basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 * 
 * ***** END LICENSE BLOCK *****
 */