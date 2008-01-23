package org.openehr.am.openehrprofile.datatypes.quantity;

import java.util.*;

import org.openehr.am.archetype.constraintmodel.CAttribute;
import org.openehr.rm.support.basic.Interval;
import org.openehr.rm.datatypes.quantity.DvQuantity;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.support.measurement.*;

import junit.framework.TestCase;

public class CDvQuantityTest extends TestCase {
	
	public void testCreateEmptyCDvQuantity() {
		String path = "/term_definitions[en]/items[at0001]/text/";
		Interval<Integer> occurrences = new Interval<Integer>(1,1);
		String nodeId = "at0010";
		CAttribute parent = null;
		List<CDvQuantityItem> list = null;
		CodePhrase property = null;
		
		CDvQuantity constraint = new CDvQuantity(path, occurrences, nodeId,
				parent, list, property, null, null);
		assertTrue("anyAllowed expected", constraint.isAnyAllowed());
	}
	
	public void testCreateWithAssumedValue() {
		String path = "/term_definitions[en]/items[at0001]/text/";
		Interval<Integer> occurrences = new Interval<Integer>(1,1);
		String nodeId = "at0010";
		CAttribute parent = null;
		List<CDvQuantityItem> list = null;
		CodePhrase property = null;
		MeasurementService ms = SimpleMeasurementService.getInstance();
		DvQuantity assumed = new DvQuantity("kg", 90.0, ms);
		
		CDvQuantity constraint = new CDvQuantity(path, occurrences, nodeId,
				parent, list, property, null, assumed);
		assertEquals("assumed wrong", assumed, constraint.getAssumedValue());
	}
	
	public void testCreateWithDefaultValue() {
		String path = "/term_definitions[en]/items[at0001]/text/";
		Interval<Integer> occurrences = new Interval<Integer>(1,1);
		String nodeId = "at0010";
		CAttribute parent = null;
		List<CDvQuantityItem> list = null;
		CodePhrase property = null;
		MeasurementService ms = SimpleMeasurementService.getInstance();
		DvQuantity defaultValue = new DvQuantity("kg", 90.0, ms);
		
		CDvQuantity constraint = new CDvQuantity(path, occurrences, nodeId,
				parent, list, property, defaultValue, null);
		assertEquals("default wrong", defaultValue, constraint.getDefaultValue());
	}
	
	public void testEqualsWithDifferentQuantityItems() {
		Interval<Integer> required = new Interval<Integer>(1,1);
		CDvQuantityItem item1 = new CDvQuantityItem(
				new Interval<Double>(0.0, 1.0), 
				new Interval<Integer>(2, 2), "mg");
		List<CDvQuantityItem> list1 = new ArrayList<CDvQuantityItem>();
		list1.add(item1);
		CDvQuantity cq1 = new CDvQuantity("/path", required, list1, null);
		
		CDvQuantityItem item2 = new CDvQuantityItem(
				new Interval<Double>(0.0, 2.0), 
				new Interval<Integer>(2, 2), "mg");
		List<CDvQuantityItem> list2 = new ArrayList<CDvQuantityItem>();
		list2.add(item2);
		CDvQuantity cq2 = new CDvQuantity("/path", required, list2, null);
		
		assertFalse("CDvQuantity with different items should not equal",
				cq1.equals(cq2));
		assertFalse("CDvQuantity with different items should not equal",
				cq2.equals(cq1));
	}
	
	public void testEqualsWithSameQuantityItems() {
		Interval<Integer> required = new Interval<Integer>(1,1);
		CDvQuantityItem item1 = new CDvQuantityItem(
				new Interval<Double>(0.0, 1.0), 
				new Interval<Integer>(2, 2), "mg");
		List<CDvQuantityItem> list1 = new ArrayList<CDvQuantityItem>();
		list1.add(item1);
		CDvQuantity cq1 = new CDvQuantity("/path", required, list1, null);
		
		CDvQuantityItem item2 = new CDvQuantityItem(
				new Interval<Double>(0.0, 1.0), 
				new Interval<Integer>(2, 2), "mg");
		List<CDvQuantityItem> list2 = new ArrayList<CDvQuantityItem>();
		list2.add(item2);
		CDvQuantity cq2 = new CDvQuantity("/path", required, list2, null);
		
		assertTrue("CDvQuantity with the same quantity items should equal",
				cq1.equals(cq2));
		assertTrue("CDvQuantity with the same quantity items should equal",
				cq2.equals(cq1));
	}
}
