/*
 * PartyIdentifiedTest.java
 * JUnit based test
 *
 * Created on July 18, 2006, 5:37 PM
 */

package org.openehr.rm.common.generic;

import junit.framework.*;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.openehr.rm.datatypes.basic.DvIdentifier;
import org.openehr.rm.support.identification.HierarchicalObjectID;
import org.openehr.rm.support.identification.ObjectReference;
import org.openehr.rm.support.identification.PartyReference;

/**
 *
 * @author yinsulim
 */
public class PartyIdentifiedTest extends TestCase {
    
    public PartyIdentifiedTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(PartyIdentifiedTest.class);
        
        return suite;
    }

    public void test() {
        PartyReference pr = new PartyReference(new HierarchicalObjectID("1-2-3-4-5"), 
                ObjectReference.Type.PARTY);
        PartyIdentified pi = new PartyIdentified(pr, "party name", null);
        PartyIdentified pi2 = new PartyIdentified(pr, "party name", null);
        assertEquals(pi, pi2);
        assertEquals(pi.hashCode(), pi2.hashCode());
    }

    
}
