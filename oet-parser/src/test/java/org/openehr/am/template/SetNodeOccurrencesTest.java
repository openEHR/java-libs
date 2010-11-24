package org.openehr.am.template;

import java.math.BigInteger;

import openEHR.v1.template.Statement;

import org.openehr.am.archetype.constraintmodel.ArchetypeConstraint;
import org.openehr.am.archetype.constraintmodel.CObject;
import org.openehr.rm.support.basic.Interval;

public class SetNodeOccurrencesTest extends TemplateTestBase {
	
	public void setUp() throws Exception {
		
		super.setUp();
		
		archetype = loadArchetype(
			"openEHR-EHR-ITEM_TREE.medication_test_three.v1.adl");
		
		rule = Statement.Factory.newInstance();
		rule.setPath(PATH);
		rule.setMax(null);
		rule.setMin(null);
	}
	
	public void testSetMaxOccurrencesToZeroOnOptionalNode() throws Exception {
		constraint = archetype.node(PATH);
		CObject cobj = (CObject) constraint;
		cobj.setOccurrences(new Interval<Integer>(0, null));
		
		flattener.applyOccurrencesConstraint(archetype, cobj, BigInteger.valueOf(0), null);		
		constraint = archetype.node(PATH);
		
		assertTrue("unexpected constraint", constraint instanceof CObject);
		
		assertEquals("unexpected max occurrences", 0, 
				cobj.getOccurrences().getUpper().intValue());		
		
		assertEquals("unexpected min occurrences", 0, 
				cobj.getOccurrences().getLower().intValue());
	}
	
	// make optional node required
	public void testSetMinOccurrencesToOneOnOptionalNode() throws Exception {
		constraint = archetype.node(PATH);
		CObject cobj = (CObject) constraint;
		cobj.setOccurrences(new Interval<Integer>(0, 1));
		
		flattener.applyOccurrencesConstraint(archetype, cobj, null, BigInteger.valueOf(1));		
		constraint = archetype.node(PATH);
		
		assertTrue("unexpected constraint", constraint instanceof CObject);
		
		assertEquals("unexpected max occurrences", 1, 
				cobj.getOccurrences().getUpper().intValue());		
		
		assertEquals("unexpected min occurrences", 1, 
				cobj.getOccurrences().getLower().intValue());
	}
	
	// make optional node not allowed
	public void testSetMaxOccurrencesTozeroOnOptionalNode() throws Exception {
		constraint = archetype.node(PATH);
		CObject cobj = (CObject) constraint;
		cobj.setOccurrences(new Interval<Integer>(0, 1));
		
		flattener.applyOccurrencesConstraint(archetype, cobj, BigInteger.valueOf(0), null);		
		constraint = archetype.node(PATH);
		
		assertTrue("unexpected constraint", constraint instanceof CObject);
		
		assertEquals("unexpected max occurrences", 0, 
				cobj.getOccurrences().getUpper().intValue());		
		
		assertEquals("unexpected min occurrences", 0, 
				cobj.getOccurrences().getLower().intValue());
	}
	
	public void testSetMaxOccurrencesToZeroOnRequiredNode() throws Exception {
		constraint = archetype.node(PATH);
		CObject cobj = (CObject) constraint;
		cobj.setOccurrences(new Interval<Integer>(1, 1));
		
		try {
			flattener.applyOccurrencesConstraint(archetype, cobj, BigInteger.valueOf(0), null);
			fail("expect flattening exception on contradicting occurrences");
		} catch(FlatteningException e) {}
	}
	
	public void testSetMaxOccurrencesToTwoOnRequiredNode() throws Exception {
		constraint = archetype.node(PATH);
		CObject cobj = (CObject) constraint;
		cobj.setOccurrences(new Interval<Integer>(1, 1));
		
		try {
			flattener.applyOccurrencesConstraint(archetype, cobj, BigInteger.valueOf(2), null);
			fail("expect flattening exception on too permissive occurrences");
		} catch(FlatteningException e) {}
	}
	
	public void testSetMaxOccurrencesToOneOnRequiredNode() throws Exception {
		constraint = archetype.node(PATH);
		CObject cobj = (CObject) constraint;
		cobj.setOccurrences(new Interval<Integer>(1, 1));
		
		flattener.applyOccurrencesConstraint(archetype, cobj, BigInteger.valueOf(1), null);		
		constraint = archetype.node(PATH);
		
		assertTrue("unexpected constraint", constraint instanceof CObject);
		
		assertEquals("unexpected max occurrences", 1, 
				cobj.getOccurrences().getUpper().intValue());		
		
		assertEquals("unexpected min occurrences", 1, 
				cobj.getOccurrences().getLower().intValue());
	}
	
	public void testSetMinOccurrencesToZeroOnRequiredNode() throws Exception {
		constraint = archetype.node(PATH);
		CObject cobj = (CObject) constraint;
		cobj.setOccurrences(new Interval<Integer>(1, 1));
		
		try {
			flattener.applyOccurrencesConstraint(archetype, cobj, null, BigInteger.valueOf(0));
			fail("expect flattening exception on too permissive occurrences");
		} catch(FlatteningException e) {}
	}
	
	public void testSetMinOccurrencesToTwoOnRequiredNode() throws Exception {
		constraint = archetype.node(PATH);
		CObject cobj = (CObject) constraint;
		cobj.setOccurrences(new Interval<Integer>(1, 1));
		
		try {
			flattener.applyOccurrencesConstraint(archetype, cobj, null, BigInteger.valueOf(2));
			fail("expect flattening exception on contradicting occurrences");
		} catch(FlatteningException e) {}
	}
	
	public void testSetMinOccurrencesToOneOnOptionalNode2() throws Exception {
		constraint = archetype.node(PATH);
		CObject cobj = (CObject) constraint;
		cobj.setOccurrences(new Interval<Integer>(0, null));
		
		flattener.applyOccurrencesConstraint(archetype, cobj, null, BigInteger.valueOf(1));		
		constraint = archetype.node(PATH);
		
		assertTrue("unexpected constraint", constraint instanceof CObject);
		
		assertEquals("unexpected min occurrences", 1, 
				cobj.getOccurrences().getLower().intValue());
		
		assertNull("unexpected max occurrences",  
				cobj.getOccurrences().getUpper());
	}
	
	public void testSetMinOccurrencesToTwoOnOptionalNode() throws Exception {
		constraint = archetype.node(PATH);
		CObject cobj = (CObject) constraint;
		cobj.setOccurrences(new Interval<Integer>(0, null));
		
		flattener.applyOccurrencesConstraint(archetype, cobj, null, BigInteger.valueOf(2));		
		constraint = archetype.node(PATH);
		
		assertEquals("unexpected min occurrences", 2, 
				cobj.getOccurrences().getLower().intValue());
		
		assertNull("unexpected max occurrences", 
				cobj.getOccurrences().getUpper());
	}	
	
	public void testSetMaxOccurrencesToOneOnOptionalNodeWithoutMinimum() throws Exception {
		constraint = archetype.node(PATH);
		CObject cobj = (CObject) constraint;
		cobj.setOccurrences(new Interval<Integer>(0, null));
		
		flattener.applyOccurrencesConstraint(archetype, cobj, BigInteger.valueOf(1), null);		
		constraint = archetype.node(PATH);
		
		assertEquals("unexpected min occurrences", 0, 
				cobj.getOccurrences().getLower().intValue());
		
		assertEquals("unexpected max occurrences", 1,  
				cobj.getOccurrences().getUpper().intValue());
	}	
	
	private static final String PATH = "/items[at0001]";
	private Statement rule;
	private ArchetypeConstraint constraint;
}
