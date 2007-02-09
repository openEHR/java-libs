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
		Interval<Integer> occurrences = null;
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
		Interval<Integer> occurrences = null;
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
		Interval<Integer> occurrences = null;
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
}
