package br.com.zilics.archetypes.models.am.archetype.constraintmodel.primitive;

import java.util.ArrayList;

import br.com.zilics.archetypes.models.rm.exception.ImmutableException;
import br.com.zilics.archetypes.models.rm.exception.ValidateException;
import br.com.zilics.archetypes.models.rm.support.basic.Interval;
import junit.framework.TestCase;

public class CIntegerTest extends TestCase {
	public CIntegerTest(String testName) {
		super(testName);
	}
	
	public void testImmutable() throws Exception {
		CInteger constraint = new CInteger();
		constraint.setImmutable(true);
		try {
			constraint.setList(null);
			fail("Should throw an exception here!");
		} catch(ImmutableException ex) {
			
		}		
		try {
			constraint.setRange(null);
			fail("Should throw an exception here!");
		} catch(ImmutableException ex) {
			
		}		
	}

	
	public void testValidateNullListNullRange() throws Exception {
		CInteger constraint = new CInteger();
		try {
			constraint.validate();
			fail("Should throw an exception here!");
		} catch(ValidateException ex) {
			
		}
	}
	
	public void testValidateWithListWithRange() throws Exception {
		CInteger constraint = new CInteger();
		constraint.setList(new ArrayList<Integer>());
		constraint.setRange(new Interval<Integer>(1, 10));
		constraint.getList().add(1);
		try {
			constraint.validate();
			fail("Should throw an exception here!");
		} catch(ValidateException ex) {
			
		}
	}

	public void testDefaultValueList() throws Exception {
		CInteger constraint = new CInteger();
		constraint.setList(new ArrayList<Integer>());
		constraint.getList().add(1);
		assertEquals(Integer.valueOf(1), constraint.defaultValue());
	}
	
	public void testDefaultValueRange() throws Exception {
		CInteger constraint = new CInteger();
		constraint.setRange(new Interval<Integer>(1, 10));
		assertTrue(constraint.getRange().has(constraint.defaultValue()));
	}

	public void testIsValidValueList() throws Exception {
		CInteger constraint = new CInteger();
		constraint.setList(new ArrayList<Integer>());
		constraint.getList().add(1);
		constraint.getList().add(10);
		constraint.getList().add(5);
		constraint.getList().add(3);
		assertTrue(constraint.isValidValue(5));
		assertTrue(constraint.isValidValue(10));
		assertFalse(constraint.isValidValue(2));
	}	

	public void testIsValidValueRange() throws Exception {
		CInteger constraint = new CInteger();
		constraint.setRange(new Interval<Integer>(1, 10));
		assertTrue(constraint.isValidValue(1));
		assertTrue(constraint.isValidValue(10));
		assertFalse(constraint.isValidValue(0));
		assertFalse(constraint.isValidValue(11));
	}	

}
