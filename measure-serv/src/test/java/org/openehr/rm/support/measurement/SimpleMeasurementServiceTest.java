package org.openehr.rm.support.measurement;

import junit.framework.TestCase;

public class SimpleMeasurementServiceTest extends TestCase {
	
	public void setUp() {
		service = SimpleMeasurementService.getInstance();
	}
	
	public void testunitsEquivalent() throws Exception {
		assertTrue(service.unitsEquivalent("mg", "MG"));
		assertFalse(service.unitsEquivalent("mg", "kg"));
	}
	
	private MeasurementService service;	
}
