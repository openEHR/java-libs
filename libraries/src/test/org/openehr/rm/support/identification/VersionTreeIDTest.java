/*
 * VersionTreeIDTest.java
 * JUnit based test
 *
 * Created on July 10, 2006, 5:02 PM
 */

package org.openehr.rm.support.identification;

import junit.framework.*;
import org.apache.commons.lang.StringUtils;
import org.openehr.rm.RMObject;

/**
 *
 * @author yinsulim
 */
public class VersionTreeIDTest extends TestCase {
    
    public VersionTreeIDTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(VersionTreeIDTest.class);
        
        return suite;
    }


    public void testConstructors() throws Exception {
        String[] values = {
            "1.1.2", "2", "1.3.24", "10", "3.0.0"
        };
        int[][] intS = {
            {1, 1, 2},
            {2, 0, 0},
            {1, 3, 24},
            {10, 0, 0},
            {3, 0, 0}
        };
        boolean[] isB = {
            true, false, true, false, false
        };
                
        for(int i = 0 ; i < values.length; i++) {
            VersionTreeID v1 = toVersionTreeID(values[i]);
            VersionTreeID v2 = toVersionTreeID(intS[i][0], intS[i][1], intS[i][2]);
            assertEquals(v1, v2);
            String bN = intS[i][1] == 0 ? null: Integer.toString(intS[i][1]);
            String bV = intS[i][2] == 0 ? null: Integer.toString(intS[i][2]);
            assertEquals("trunk", Integer.toString(intS[i][0]), v2.trunkVersion());
            assertEquals("branchNo", bN, v2.branchNumber());
            assertEquals("branchVersion", bV, v2.branchVersion());
            assertEquals("isBranch", isB[i], v2.isBranch());
        }
        
    }
    
    public void testConstructorsFail() throws Exception {
        String[] values = {
            "1.0.2", "0", "0.3.24", "1.1.0", "0.0.0", "1.1"
        };
        int[][] intS = {
            {0, 1, 2},
            {0, 0, 0},
            {1, 3, 0},
            {10, 0, 1}, 
            {-1, 3, 2}, 
            {1, -2, 2}
        };
        for(int i = 0; i < values.length; i++) {
            assertNull(toVersionTreeID(values[i]));
        }
        for(int i = 0; i < intS.length; i++) {
            assertNull(toVersionTreeID(intS[i][0], intS[i][1], intS[i][2]));
        }
    }
    
    private VersionTreeID toVersionTreeID(String value) {
        VersionTreeID v = null;
        try {
            v = new VersionTreeID(value);
        } catch (IllegalArgumentException iae) {            
        }
        return v;
    }
    
    private VersionTreeID toVersionTreeID(int t, int bN, int bV) {
        VersionTreeID v = null;
        try {
            v = new VersionTreeID(t, bN, bV);
        } catch (IllegalArgumentException iae) {           
        }
        return v;
    }
    
    public void testIsFirst() {
        
        String[] values = {"1", "1.0.0", "1.1.1", "2"};
        boolean[] iF = {true, true, false, false};
        for(int i  = 0; i < values.length; i++) {
            assertEquals(iF[i], new VersionTreeID(values[i]).isFirst());
        }
         
    }

    public void testNext() {
        String[] values = {"1", "1.0.0", "1.1.1", "2"};
        String[] nextV = {"2", "2", "1.1.2", "3"};
        for(int i  = 0; i < values.length; i++) {
            assertEquals(new VersionTreeID(nextV[i]), new VersionTreeID(values[i]).next());
        }
    }

    public void testToString() {
        int[][] intS = {
            {1, 1, 2},
            {1, 0, 0},
            {1, 3, 1},
        };
        String[] values = {
            "1.1.2", "1", "1.3.1"
        };
        for(int i  = 0; i < values.length; i++) {
            assertEquals(values[i], toVersionTreeID(intS[i][0], intS[i][1], intS[i][2]).toString());
        }
        String[] tValues = {
            "1.1.2", "1", "1.0.0", "2.0.0"
        };
        String[] eValues = {
            "1.1.2", "1", "1", "2"
        };
        for(int i  = 0; i < values.length; i++) {
            assertEquals(eValues[i], toVersionTreeID(tValues[i]).toString());
        }
    }
    
}
