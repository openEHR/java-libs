package se.acode.openehr.parser;

import org.openehr.am.archetype.Archetype;

/**
 * Testcase verifies parsing of abnormal archetype structures 
 * to ensure backwards compatibility on 'old' archetypes
 * 
 * @author Rong Chen
 *
 */
public class EmptyOtherContributorsTest extends ParserTestBase {

    public EmptyOtherContributorsTest(String test) throws Exception {
        super(test);
    }
    
    public void setUp() throws Exception {
    	ADLParser parser = new ADLParser(loadFromClasspath(
        	"adl-test-entry.empty_other_contributors.test.adl"));
    	archetype = parser.parse();
    }
    
    public void testParseEmptyOtherContributors() throws Exception {
    	assertNotNull(archetype);
    	assertNull("other_contributors not null", 
    			archetype.getDescription().getOtherContributors());
    }

    private Archetype archetype;
}