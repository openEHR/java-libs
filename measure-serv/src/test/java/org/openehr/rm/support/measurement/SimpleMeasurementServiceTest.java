package org.openehr.rm.support.measurement;

import junit.framework.TestCase;

public class SimpleMeasurementServiceTest extends TestCase {

    @Override
    public void setUp() {
	service = SimpleMeasurementService.getInstance();
    }

    public void testunitsEquivalent() throws Exception {
	//assertTrue(service.unitsEquivalent("mg", "MG"));
    assertFalse(service.unitsEquivalent("mg", "MG"));
    assertFalse(service.unitsEquivalent("mg", "mG"));
    assertTrue(service.unitsEquivalent("A", "C/s"));
    
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
    
    public void testunitsValid() throws Exception {
        assertTrue(service.isValidUnitsString("mg"));
        assertTrue(service.isValidUnitsString("osm"));
        assertTrue(service.isValidUnitsString("mm[Hg]"));
        assertTrue(service.isValidUnitsString("ug"));
        assertTrue(service.isValidUnitsString("ug/L"));
        assertTrue(service.isValidUnitsString("ug/l"));
        assertTrue(service.isValidUnitsString("km/h"));
        assertTrue(service.isValidUnitsString("[iU]"));
        assertTrue(service.isValidUnitsString("[IU]"));
        assertTrue(service.isValidUnitsString("a")); // year        
        assertTrue(service.isValidUnitsString("eq"));
        assertFalse(service.isValidUnitsString("Eq"));
        
        assertFalse(service.isValidUnitsString("osmole"));
        assertFalse(service.isValidUnitsString("iU"));
        assertFalse(service.isValidUnitsString("yr")); // year is "a" 
        assertFalse(service.isValidUnitsString("milligm"));
        assertFalse(service.isValidUnitsString("mgm"));

    }
    
    private MeasurementService service;	
}
