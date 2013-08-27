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

    public void testunitsComparable() throws Exception {
	assertTrue(service.unitsComparable("mg", "kg"));
	assertFalse(service.unitsComparable("mg", "ml"));
	assertTrue(service.unitsComparable("cm", "[in_i]"));
    }

    public void testunitsComparison() throws Exception {
	assertTrue(service.compare("kg", 1.0, "g", 1000.0)==0);
	assertTrue(service.compare("l", 1.0, "ml", 100.0)>0);
	assertTrue(service.compare("[in_i]", 2.0, "cm", 5.0)>0);
	assertFalse(service.compare("[in_i]", 1000.0, "m", 26.0)>0);
    }
    
    private MeasurementService service;	
}
