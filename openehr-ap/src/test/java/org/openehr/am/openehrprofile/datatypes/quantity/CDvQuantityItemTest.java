package org.openehr.am.openehrprofile.datatypes.quantity;


import org.openehr.rm.support.basic.Interval;

import junit.framework.TestCase;

/**
 * Testcase for CDvQuantityItem
 * 
 * @author Rong Chen
 *
 */
public class CDvQuantityItemTest extends TestCase {
	
	public void testCreateCDvQuantityItem() {
		Interval<Double> value = new Interval<Double>(20.0, 160.0);
		Interval<Integer> precision = new Interval<Integer>(0, null);
		String units = "Kg";
		CDvQuantityItem item = new CDvQuantityItem(value, precision, units);
		
		assertEquals("magnitude wrong", value, item.getMagnitude());
		assertEquals("precision wrong", precision, item.getPrecision());
		assertEquals("units wrong", units, item.getUnits());
	}
}
