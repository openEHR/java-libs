package br.com.zilics.archetypes.models.am.archetype.constraintmodel.primitive;

import java.util.ArrayList;

import br.com.zilics.archetypes.models.rm.exception.ImmutableException;
import br.com.zilics.archetypes.models.rm.exception.ValidateException;
import br.com.zilics.archetypes.models.rm.support.basic.Interval;
import junit.framework.TestCase;

public class CRealTest extends TestCase {
	public CRealTest(String testName) {
		super(testName);
	}
	
	public void testImmutable() throws Exception {
		CReal constraint = new CReal();
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
		CReal constraint = new CReal();
		try {
			constraint.validate();
			fail("Should throw an exception here!");
		} catch(ValidateException ex) {
			
		}
	}
	
	public void testValidateWithListWithRange() throws Exception {
		CReal constraint = new CReal();
		constraint.setList(new ArrayList<Double>());
		constraint.setRange(new Interval<Double>(1.0, 10.0));
		constraint.getList().add(1.0);
		try {
			constraint.validate();
			fail("Should throw an exception here!");
		} catch(ValidateException ex) {
			
		}
	}

	public void testDefaultValueList() throws Exception {
		CReal constraint = new CReal();
		constraint.setList(new ArrayList<Double>());
		constraint.getList().add(1.0);
		assertEquals(1.0, constraint.defaultValue().doubleValue(), 0.0001);
	}
	
	public void testDefaultValueRange() throws Exception {
		CReal constraint = new CReal();
		constraint.setRange(new Interval<Double>(1.0, 10.0));
		assertTrue(constraint.getRange().has(constraint.defaultValue()));
	}

	public void testIsValidValueList() throws Exception {
		CReal constraint = new CReal();
		constraint.setList(new ArrayList<Double>());
		constraint.getList().add(1.0);
		constraint.getList().add(10.0);
		constraint.getList().add(5.0);
		constraint.getList().add(3.0);
		assertTrue(constraint.isValidValue(5.0));
		assertTrue(constraint.isValidValue(10.0));
		assertFalse(constraint.isValidValue(2.0));
	}	

	public void testIsValidValueRange() throws Exception {
		CReal constraint = new CReal();
		constraint.setRange(new Interval<Double>(1.0, 10.0));
		assertTrue(constraint.isValidValue(1.0));
		assertTrue(constraint.isValidValue(10.0));
		assertFalse(constraint.isValidValue(0.0));
		assertFalse(constraint.isValidValue(11.0));
	}	

	

}
