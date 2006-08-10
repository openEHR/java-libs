/*
 * InternetIDTest.java
 * JUnit based test
 *
 * Created on July 10, 2006, 2:39 PM
 */

package org.openehr.rm.support.identification;

import junit.framework.*;

/**
 *
 * @author yinsulim
 */
public class InternetIDTest extends TestCase {
    
    public InternetIDTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(InternetIDTest.class);
        
        return suite;
    }
    
    public void testConstructorTakeString() throws Exception {
        assertNotNull(new InternetID("www.google.com"));
        assertNotNull(new InternetID("m-2.d2"));
        assertNotNull(new InternetID("a123.com.nz"));
        assertNotNull(new InternetID("openehr.org"));
        assertNotNull(new InternetID("openehr1-1.org"));
        assertNotNull(new InternetID("openehr1-1.com-2"));
        assertNull(toInternetID("128.17.13.1"));
        assertNull(toInternetID("123.com"));
        assertNull(toInternetID("open_ehr.org"));
        assertNull(toInternetID("openehr1-.org"));
        assertNull(toInternetID("openehr1-1.0"));
        assertNull(toInternetID("openehr1-1.com."));
        
    }
    
    private InternetID toInternetID(String value) {
        InternetID id = null;
        try {
            id = new InternetID(value);
            fail("should have failed this test");
        } catch (IllegalArgumentException iae) {
            
        }
        return id;
    }
    
}
