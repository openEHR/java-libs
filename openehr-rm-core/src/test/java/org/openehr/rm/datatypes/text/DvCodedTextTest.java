/**
 * DvCodedTextTest
 *
 * @author Rong Chen
 * @version 1.0 
 */
package org.openehr.rm.datatypes.text;

import junit.framework.TestCase;

public class DvCodedTextTest extends TestCase {

    public DvCodedTextTest(String test) {
        super(test);
    }

    /**
     * The fixture set up called before every test method.
     */
    protected void setUp() throws Exception {
    }

    /**
     * The fixture clean up called after every test method.
     */
    protected void tearDown() throws Exception {
    }

    /**
     * Tests creating a dvCodedText with minimum set of parameters
     *
     * @throws Exception
     */
    public void testCreateDvCodedTextWithMinimumParam() throws Exception {
        CodePhrase definingCode = new CodePhrase("test terms", "12345");
        new DvCodedText("coded text", definingCode);
    }
    
    public void testEquals() throws Exception {
    	DvCodedText t1 = new DvCodedText("some text", "icd10", "123");
    	DvCodedText t2 = new DvCodedText("some text", "icd10", "123");
    	assertEquals(t1, t2);
    }

}