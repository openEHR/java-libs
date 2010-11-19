package se.acode.openehr.parser;

import org.openehr.am.archetype.constraintmodel.ArchetypeConstraint;
import org.openehr.am.archetype.Archetype;
import org.openehr.am.openehrprofile.datatypes.quantity.CDvOrdinal;
import org.openehr.am.openehrprofile.datatypes.quantity.Ordinal;
import org.openehr.rm.datatypes.text.CodePhrase;

import java.util.*;

/**
 * Test case tests parsing of domain type constraints extension.
 *
 * @author Rong Chen
 * @version 1.0
 */

public class CDvOrdinalTest extends ParserTestBase {

    public CDvOrdinalTest(String test) throws Exception {
        super(test);
    }

    /**
     * The fixture set up called before every test method.
     */
    protected void setUp() throws Exception {
        ADLParser parser = new ADLParser(loadFromClasspath(
                "adl-test-entry.c_dv_ordinal.test.adl"));
        archetype = parser.parse();
    }

    /**
     * The fixture clean up called after every test method.
     */
    protected void tearDown() throws Exception {
        archetype = null;
        node = null;
    }

    public void testCDvOrdinalWithoutAssumedValue() throws Exception {
        node = archetype.node("/types[at0001]/items[at10001]/value");
        String[] codes = {
            "at0003.0", "at0003.1", "at0003.2", "at0003.3", "at0003.4"
        };
        String terminology = "local";
        
        assertFalse("unexpected assumed value",  
        		((CDvOrdinal) node).hasAssumedValue());       
        
        assertCDvOrdinal(node, terminology, codes, null);
    }
    
    public void testCDvOrdinalWithAssumedValue() throws Exception {
        node = archetype.node("/types[at0001]/items[at10002]/value");
        String[] codes = {
            "at0003.0", "at0003.1", "at0003.2", "at0003.3", "at0003.4"
        };
        String terminology = "local";
        Ordinal assumed = new Ordinal(0, new CodePhrase(terminology, codes[0]));     
        
        assertTrue("expected to have assumed value",  
        		((CDvOrdinal) node).hasAssumedValue());
        
        assertCDvOrdinal(node, terminology, codes, assumed);
    }
    
    public void testEmptyCDvOrdinal() throws Exception {
    	node = archetype.node("/types[at0001]/items[at10003]/value");
    	assertTrue("CDvOrdinal expected", node instanceof CDvOrdinal);
        CDvOrdinal cordinal = (CDvOrdinal) node;
        assertTrue(cordinal.isAnyAllowed());
    }
    
    private void assertCDvOrdinal(ArchetypeConstraint node, String terminoloy,
    		String[] codes,	Ordinal assumedValue) {
    	
    	assertTrue("CDvOrdinal expected", node instanceof CDvOrdinal);
        CDvOrdinal cordinal = (CDvOrdinal) node;
        
        List codeList = Arrays.asList(codes);
        Set<Ordinal> list = cordinal.getList();
        assertEquals("codes.size", codes.length, list.size());
        for(Ordinal ordinal : list) {
            assertEquals("terminology", "local", 
            		ordinal.getSymbol().getTerminologyId().getValue());
            assertTrue("code missing", 
            		codeList.contains(ordinal.getSymbol().getCodeString()));
        }
        assertEquals("assumedValue wrong", assumedValue, 
        		cordinal.getAssumedValue());        
    }

    private Archetype archetype;
    private ArchetypeConstraint node;
}
