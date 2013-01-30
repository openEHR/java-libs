package br.com.zilics.archetypes.models.rm.utils;

import java.util.ArrayList;
import java.util.List;

import br.com.zilics.archetypes.models.rm.RMObject;
import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.annotation.Ignore;
import junit.framework.TestCase;

public class EqualsAndHashcodeUtilsTest extends TestCase {
	public EqualsAndHashcodeUtilsTest(String testName) {
		super(testName);
	}

	public void testIgnore() throws Exception {
		MockEqualsAndHashcodeUtilsTestClass v1 = new MockEqualsAndHashcodeUtilsTestClass();
		MockEqualsAndHashcodeUtilsTestClass v2 = new MockEqualsAndHashcodeUtilsTestClass();
		
		v1.ignoredStringValue = "some";
		assertEquals(v1, v2);
		assertEquals(v2, v1);
		assertEquals(v2.hashCode(), v1.hashCode());
	}

	public void testStringValue() throws Exception {
		MockEqualsAndHashcodeUtilsTestClass v1 = new MockEqualsAndHashcodeUtilsTestClass();
		MockEqualsAndHashcodeUtilsTestClass v2 = new MockEqualsAndHashcodeUtilsTestClass();
		
		v1.stringValue = "some";
		assertFalse(v1.equals(v2));
		assertFalse(v2.equals(v1));
		
		v2.stringValue = "other";
		assertFalse(v1.equals(v2));
		assertFalse(v2.equals(v1));

		v2.stringValue = "some";
		assertEquals(v1, v2);
		assertEquals(v2, v1);
		assertEquals(v1.hashCode(), v2.hashCode());
	}

	public void testIntValue() throws Exception {
		MockEqualsAndHashcodeUtilsTestClass v1 = new MockEqualsAndHashcodeUtilsTestClass();
		MockEqualsAndHashcodeUtilsTestClass v2 = new MockEqualsAndHashcodeUtilsTestClass();
		
		v1.intValue = 1;
		assertFalse(v1.equals(v2));
		assertFalse(v2.equals(v1));
		
		v2.intValue = 2;
		assertFalse(v1.equals(v2));
		assertFalse(v2.equals(v1));

		v2.intValue = 1;
		assertEquals(v1, v2);
		assertEquals(v2, v1);
		assertEquals(v1.hashCode(), v2.hashCode());
	}

	public void testListOfStringValue() throws Exception {
		MockEqualsAndHashcodeUtilsTestClass v1 = new MockEqualsAndHashcodeUtilsTestClass();
		MockEqualsAndHashcodeUtilsTestClass v2 = new MockEqualsAndHashcodeUtilsTestClass();
		
		v1.listOfStringValue = new ArrayList<String>();
		assertFalse(v1.equals(v2));
		assertFalse(v2.equals(v1));
		
		v2.listOfStringValue = new ArrayList<String>();
		assertEquals(v1, v2);
		assertEquals(v2, v1);
		assertEquals(v1.hashCode(), v2.hashCode());

		v1.listOfStringValue.add("oi");
		assertFalse(v1.equals(v2));
		assertFalse(v2.equals(v1));
		
		v2.listOfStringValue.add("oi");
		assertEquals(v1, v2);
		assertEquals(v2, v1);
		assertEquals(v1.hashCode(), v2.hashCode());
		
		v1.listOfStringValue.clear();
		v1.listOfStringValue.add("Tudo");
		assertFalse(v1.equals(v2));
		assertFalse(v2.equals(v1));
	}

	public void testArrayOfStringValue() throws Exception {
		MockEqualsAndHashcodeUtilsTestClass v1 = new MockEqualsAndHashcodeUtilsTestClass();
		MockEqualsAndHashcodeUtilsTestClass v2 = new MockEqualsAndHashcodeUtilsTestClass();
		
		v1.arrayOfStringValue = new String[] {};
		assertFalse(v1.equals(v2));
		assertFalse(v2.equals(v1));
		
		v2.arrayOfStringValue = new String[] {};
		assertEquals(v1, v2);
		assertEquals(v2, v1);
		assertEquals(v1.hashCode(), v2.hashCode());

		v1.arrayOfStringValue = new String[] {"oi"};
		assertFalse(v1.equals(v2));
		assertFalse(v2.equals(v1));
		
		v2.arrayOfStringValue = new String[] {"oi"};
		assertEquals(v1, v2);
		assertEquals(v2, v1);
		assertEquals(v1.hashCode(), v2.hashCode());
		
		v1.arrayOfStringValue = new String[] {"tudo"};
		assertFalse(v1.equals(v2));
		assertFalse(v2.equals(v1));
	}

	public void testOtherValue() throws Exception {
		MockEqualsAndHashcodeUtilsTestClass v1 = new MockEqualsAndHashcodeUtilsTestClass();
		MockEqualsAndHashcodeUtilsTestClass v2 = new MockEqualsAndHashcodeUtilsTestClass();
		MockEqualsAndHashcodeUtilsTestClass o1 = new MockEqualsAndHashcodeUtilsTestClass();
		MockEqualsAndHashcodeUtilsTestClass o2 = new MockEqualsAndHashcodeUtilsTestClass();
		
		assertEquals(v1, v2);
		assertEquals(v2, v1);
		assertEquals(v1.hashCode(), v2.hashCode());

		v1.otherValue = o1;
		assertFalse(v1.equals(v2));
		assertFalse(v2.equals(v1));

		v2.otherValue = o2;
		assertEquals(v1, v2);
		assertEquals(v2, v1);
		assertEquals(v1.hashCode(), v2.hashCode());

		o1.intValue = 2;
		assertFalse(v1.equals(v2));
		assertFalse(v2.equals(v1));
		
		o2.intValue = 2;
		assertEquals(v1, v2);
		assertEquals(v2, v1);
		assertEquals(v1.hashCode(), v2.hashCode());		
	}
	
	public void testInfiniteRecursion() throws Exception {
		MockEqualsAndHashcodeUtilsTestClass v1 = new MockEqualsAndHashcodeUtilsTestClass();
		v1.otherValue = v1;
		try {
			v1.hashCode();
			fail("Stack overflow should be raised!");
		} catch(StackOverflowError e) {
			
		}
	}

}

class MockEqualsAndHashcodeUtilsTestClass extends RMObject {

	private static final long serialVersionUID = 2975981960036408543L;

	@EqualsField
	String stringValue;
	
	@EqualsField
	int intValue;
	
	@EqualsField
	List<String> listOfStringValue;
	
	@EqualsField
	String[] arrayOfStringValue;
	
	@EqualsField
	MockEqualsAndHashcodeUtilsTestClass otherValue;
	
	@EqualsField
	@Ignore
	String ignoredStringValue;
	
}

