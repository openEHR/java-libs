package se.acode.openehr.parser;

import org.openehr.am.archetype.Archetype;

/**
 * MostMinimalADLTest
 *
 * @author Rong Chen
 * @version 1.0
 */
public class MostMinimalADLTest extends ParserTestBase {

    /**
     * Create new test case
     *
     * @param test
     * @throws Exception
     */
    public MostMinimalADLTest(String test) throws Exception {
        super(test);
    }

    public void testParse() throws Exception {
        ADLParser parser = new ADLParser(loadFromClasspath(
                "adl-test-entry.most_minimal.test.adl"));
        Archetype archetype = parser.parse();
        
        assertNotNull(archetype);
        
        assertEquals("originalLanguage wrong", "en", 
        		archetype.getOriginalLanguage().getCodeString());      
        
    }    
}

