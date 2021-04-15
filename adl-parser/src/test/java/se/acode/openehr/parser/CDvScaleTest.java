package se.acode.openehr.parser;

import org.openehr.am.archetype.constraintmodel.ArchetypeConstraint;
import org.openehr.am.archetype.constraintmodel.CComplexObject;
import org.openehr.am.archetype.Archetype;
import org.openehr.am.openehrprofile.datatypes.quantity.CDvScale;
import org.openehr.am.openehrprofile.datatypes.quantity.Scale;
import org.openehr.rm.datatypes.text.CodePhrase;

import java.util.*;

/**
 * Test case tests parsing of domain type constraints extension.
 *
 * @author Sebastian Garde
 * @version 1.0
 */

public class CDvScaleTest extends ParserTestBase {

    public CDvScaleTest(String test) throws Exception {
        super(test);
    }

    /**
     * The fixture set up called before every test method.
     */
    protected void setUp() throws Exception {
        ADLParser parser = new ADLParser(loadFromClasspath(
                "adl-test-ENTRY.c_dv_scale.test.adl"));
        archetype = parser.parse();
    }

    /**
     * The fixture clean up called after every test method.
     */
    protected void tearDown() throws Exception {
        archetype = null;
        node = null;
    }

    public void testCDvScaleWithoutAssumedValue() throws Exception {
        node = archetype.node("/types[at0001]/items[at10001]/value");
        String[] codes = {
            "at0003.0", "at0003.1", "at0003.2", "at0003.3", "at0003.4"
        };
		
        double[] values = { 0.0, 1.0, 2.5, 3.8, 4.4 };
        String terminology = "local";
        
        assertFalse("unexpected assumed value",  
        		((CDvScale) node).hasAssumedValue());
        
        assertCDvScale(node, terminology, codes, values, null);
    }

    public void testCDvScaleWithAssumedValueAndIntegers() throws Exception {
        node = archetype.node("/types[at0001]/items[at10002]/value");
        String[] codes = {
            "at0003.0", "at0003.1", "at0003.2", "at0003.3", "at0003.4"
        };
        double[] values = { 0, 1, 2, 3, 4.1 };
        String terminology = "local";
        Scale assumed = new Scale(2, new CodePhrase(terminology, codes[2]));
        
        assertTrue("expected to have assumed value",  
        		((CDvScale) node).hasAssumedValue());
        
        assertCDvScale(node, terminology, codes, values, assumed);
    }
    
    public void testEmptyDvScale() throws Exception {
    	node = archetype.node("/types[at0001]/items[at10003]/value");
    	
		assertTrue("CComplexObject expected", node instanceof CComplexObject );
		assertTrue("DV_SCALE RM Type expected", ((CComplexObject) node).getRmTypeName().equals("DV_SCALE"));

        assertTrue("any allowed expected", node.isAnyAllowed());
    }
  
  public void testCDvScaleWithDuplicatedValues() throws Exception {
        node = archetype.node("/types[at0001]/items[at10004]/value");
        String[] codes = {
                "at0003.0", "at0003.1", "at0003.2", "at0003.3", "at0003.4"
        };
        double[] values = { 0, 1.5, 1.5, 3.5, 4 };
        String terminology = "local";

        assertFalse("unexpected assumed value",
                ((CDvScale) node).hasAssumedValue());

        assertCDvScale(node, terminology, codes, values, null);
    }
  
   public void testDvScaleNormal() throws Exception {
        node = archetype.node("/types[at0001]/items[at10005]/value");
        String[] codes = {
                "at0003.0", "at0003.2" 
        };
        double[] values = { 0, 1.5 };
        String terminology = "local";

        // DV_SCALE tests
        //assertFalse("unexpected assumed value",
        //        ((DvScale) node).hasAssumedValue());

        //assertCDvScale(node, terminology, codes, values, null);
    }
  
    private void assertCDvScale(ArchetypeConstraint node, String terminoloy,
                                  String[] codes, double[] values, Scale assumedValue) {

        assertTrue("CDvScale expected", node instanceof CDvScale);
        CDvScale cordinal = (CDvScale) node;

        List<Scale> list = cordinal.getList();
        assertEquals("codes.size", codes.length, list.size());
        for(int i = 0; i < codes.length; i++) {
            assertEquals("terminology", terminoloy,
                    list.get(i).getSymbol().getTerminologyId().getValue());
            assertEquals("code missing", codes[i], list.get(i).getSymbol().getCodeString());
            assertEquals("value wrong", values[i], list.get(i).getValue());
			// could also add a test about the exact display here
        }
        assertEquals("assumedValue wrong", assumedValue,
                cordinal.getAssumedValue());
    }

    private Archetype archetype;
    private ArchetypeConstraint node;
}
