package se.acode.openehr.parser;

import org.openehr.am.archetype.constraintmodel.ArchetypeConstraint;
import org.openehr.am.archetype.Archetype;
import org.openehr.am.openehrprofile.datatypes.quantity.CDvQuantity;
import org.openehr.am.openehrprofile.datatypes.quantity.CDvQuantityItem;
import org.openehr.rm.datatypes.quantity.DvQuantity;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.support.basic.Interval;
import org.openehr.rm.support.measurement.*;

import java.util.*;

/**
 * Test case tests parsing of domain type constraints extension.
 *
 * @author Rong Chen
 * @version 1.0
 */

public class CDvQuantityTest extends ParserTestBase {

    /**
     * The fixture clean up called after every test method.
     */
    protected void tearDown() throws Exception {
        archetype = null;
        node = null;
    }

    public void testParseFullCDvQuantityStartsWithProperty() throws Exception {
    	ADLParser parser = new ADLParser(loadFromClasspath(
        	"adl-test-entry.c_dv_quantity_full.test.adl"));
    	archetype = parser.parse();        
    	node = archetype.node("/types[at0001]/items[at10005]/value");
    	verifyCDvQuantityValue(node);      
    }
    
    public void testParseFullCDvQuantityStartsWithList() throws Exception {
    	ADLParser parser = new ADLParser(loadFromClasspath(
        	"adl-test-entry.c_dv_quantity_full2.test.adl"));
    	archetype = parser.parse();        
    	node = archetype.node("/types[at0001]/items[at10005]/value");
        verifyCDvQuantityValue(node);       
    }
    
    public void testParseFullCDvQuantityStartsWithAssumedValue() throws Exception {
    	ADLParser parser = new ADLParser(loadFromClasspath(
        	"adl-test-entry.c_dv_quantity_full3.test.adl"));
    	archetype = parser.parse();        
    	node = archetype.node(
                "/types[at0001]/items[at10005]/value");
        verifyCDvQuantityValue(node);       
    }
    
    private void verifyCDvQuantityValue(ArchetypeConstraint node) {
    	assertTrue("CDvQuantity expected", node instanceof CDvQuantity);
        
    	CDvQuantity cdvquantity = (CDvQuantity) node;
        
        // verify property 
        CodePhrase property = cdvquantity.getProperty();
        assertNotNull("property is null", property);
        assertEquals("openehr", property.getTerminologyId().name());
        assertEquals("128", property.getCodeString());
        
        // verify item list
        List<CDvQuantityItem> list = cdvquantity.getList();
        assertEquals("unexpected size of list", 2, list.size());
        assertCDvQuantityItem(list.get(0), "yr", 
        		new Interval<Double>(0.0, 200.0), new Interval<Integer>(2, 2));
        assertCDvQuantityItem(list.get(1), "mth", 
        		new Interval<Double>(1.0, 36.0), new Interval<Integer>(2, 2));
        
        MeasurementService ms = SimpleMeasurementService.getInstance();
        DvQuantity expected = new DvQuantity("yr", 8.0, 2, ms);
        assertEquals("assumed value wrong", expected, 
        		cdvquantity.getAssumedValue());        
    }
    
    private void assertCDvQuantityItem(CDvQuantityItem item, String units,
    		Interval<Double> magnitude, Interval<Integer> precision) {
    	assertEquals("unexpected units", units, item.getUnits());
    	assertEquals("unexpected magnitude interval", magnitude, 
    			item.getMagnitude());
    	assertEquals("unexpected precision interval", precision, 
    			item.getPrecision());    	
    }
    
    public void testParseCDvQuantityOnlyWithProperty() throws Exception {
    	ADLParser parser = new ADLParser(loadFromClasspath(
        	"adl-test-entry.c_dv_quantity_property.test.adl"));
    	archetype = parser.parse();
    }
    
    public void testParseCDvQuantityOnlyWithList() throws Exception {
    	ADLParser parser = new ADLParser(loadFromClasspath(
        	"adl-test-entry.c_dv_quantity_list.test.adl"));
    	archetype = parser.parse();
    }
    
    public void testParseCDvQuantityReversed() throws Exception {
    	ADLParser parser = new ADLParser(loadFromClasspath(
        	"adl-test-entry.c_dv_quantity_reversed.test.adl"));
    	archetype = parser.parse();
    }
    
    public void testParseCDvQuantityItemWithOnlyUnits() throws Exception {
    	ADLParser parser = new ADLParser(loadFromClasspath(
        	"adl-test-entry.c_dv_quantity_item_units_only.test.adl"));
    	archetype = parser.parse();
    }

    public void testParseEmptyCDvQuantity() throws Exception {
    	ADLParser parser = new ADLParser(loadFromClasspath(
        	"adl-test-entry.c_dv_quantity_empty.test.adl"));
    	archetype = parser.parse();
    	node = archetype.node("/types[at0001]/items[at10005]/value");
    	assertTrue("CDvQuantity expected", node instanceof CDvQuantity);
        
    	CDvQuantity cdvquantity = (CDvQuantity) node;
    	assertNull(cdvquantity.getList());
    	assertNull(cdvquantity.getProperty());
    	assertNull(cdvquantity.getAssumedValue());
    	assertTrue(cdvquantity.isAnyAllowed());
    }
    
    private Archetype archetype;
    private ArchetypeConstraint node;
}
