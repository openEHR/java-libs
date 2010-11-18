package org.openehr.am.archetype.constraintmodel;

import org.openehr.rm.support.basic.Interval;

import junit.framework.TestCase;

public class CObjectTest extends TestCase {
	
	public void testCreateCObjectWithNullOccurrences() {
		boolean anyAllowed = false;
		String path = "/[at001]";
		String rmType = "TEST_OBJECT";
		Interval<Integer> occurrences = null;
		String nodeID = "at001";
        CAttribute parent = null;
        
        try {
        	new TestCObject(anyAllowed, path, rmType, occurrences, nodeID, 
        			parent);
        	fail("should fail to create CObject with null occurrences");
        } catch(Exception e) {
        	// expected
        }
 	}
	
	public CObject copy() {
		return null;
	}
	
	public void testCreateCObjectWithDefaultOccurrences() {
		boolean anyAllowed = false;
		String path = "/[at001]";
		String rmType = "TEST_OBJECT";
		Interval<Integer> occurrences = new Interval<Integer>(1, 1);
		String nodeID = "at001";
        CAttribute parent = null;
        
        try {
        	new TestCObject(anyAllowed, path, rmType, occurrences, nodeID, 
        			parent);        	
        } catch(Exception e) {
        	fail("failed to create CObject with default occurrences");
        }
	}
	
	public void testIsRequiredWithDefaultOccurrences() throws Exception {
		boolean anyAllowed = false;
		String path = "/[at001]";
		String rmType = "TEST_OBJECT";
		Interval<Integer> occurrences = new Interval<Integer>(1, 1);
		String nodeID = "at001";
        CAttribute parent = null;
        
        CObject cobj = new TestCObject(anyAllowed, path, rmType, occurrences, 
        		nodeID,	parent);        	
        
        assertTrue("expected to be required", cobj.isRequired());
	}
	
	public void testIsRequiredWithOnlyLowerLimitOccurrences() throws Exception {
		boolean anyAllowed = false;
		String path = "/[at001]";
		String rmType = "TEST_OBJECT";
		Interval<Integer> occurrences = new Interval<Integer>(1, null);
		String nodeID = "at001";
        CAttribute parent = null;
        
        CObject cobj = new TestCObject(anyAllowed, path, rmType, occurrences, 
        		nodeID,	parent);        	
        
        assertTrue("expected to be required", cobj.isRequired());
	}
	
	private static class TestCObject extends CObject {
		public CObject copy() {
			return null;
		}
		
		TestCObject(boolean anyAllowed, String path, String rmTypeName,
                Interval<Integer> occurrences, String nodeID,
                CAttribute parent) {
			super(anyAllowed, path, rmTypeName, occurrences, nodeID, parent);
		}		

		@Override
		public boolean isValid() {
			return false;
		}

		@Override
		public boolean hasPath(String path) {
			return false;
		}

		@Override
		public boolean isSubsetOf(ArchetypeConstraint constraint) {
			return false;
		}
		
	}
}
