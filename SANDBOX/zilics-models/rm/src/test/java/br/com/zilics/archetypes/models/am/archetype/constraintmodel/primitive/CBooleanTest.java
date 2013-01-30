package br.com.zilics.archetypes.models.am.archetype.constraintmodel.primitive;

import br.com.zilics.archetypes.models.rm.exception.ImmutableException;
import br.com.zilics.archetypes.models.rm.exception.ValidateException;
import junit.framework.TestCase;

public class CBooleanTest extends TestCase {
	public CBooleanTest(String testName) {
		super(testName);
	}
	
	public void testImmutable() throws Exception {
		CBoolean constraint = new CBoolean();
		constraint.setTrueValid(false);
		constraint.setFalseValid(false);
		constraint.setImmutable(true);
		try {
			constraint.setTrueValid(true);
			fail("Should throw an exception here!");
		} catch(ImmutableException ex) {
			
		}		
		try {
			constraint.setFalseValid(true);
			fail("Should throw an exception here!");
		} catch(ImmutableException ex) {
			
		}		
	}
	
	public void testValidate() throws Exception {
		CBoolean constraint = new CBoolean();
		constraint.setTrueValid(false);
		constraint.setFalseValid(false);
		try {
			constraint.validate();
			fail("Should throw an exception here!");
		} catch(ValidateException ex) {
			
		}
	}
	
	public void testDefaultValue() throws Exception {
		CBoolean constraint = new CBoolean();
		constraint.setTrueValid(false);
		constraint.setFalseValid(true);
		assertFalse(constraint.defaultValue());
		constraint.setTrueValid(true);
		constraint.setFalseValid(false);
		assertTrue(constraint.defaultValue());
	}
	
	public void testIsValidValue() throws Exception {
		CBoolean constraint = new CBoolean();
		constraint.setTrueValid(false);
		constraint.setFalseValid(true);
		assertFalse(constraint.isValidValue(true));
		assertTrue(constraint.isValidValue(false));
		constraint.setTrueValid(true);
		constraint.setFalseValid(false);		
		assertFalse(constraint.isValidValue(false));
		assertTrue(constraint.isValidValue(true));
		constraint.setTrueValid(true);
		constraint.setFalseValid(true);		
		assertTrue(constraint.isValidValue(false));
		assertTrue(constraint.isValidValue(true));
	}
}
