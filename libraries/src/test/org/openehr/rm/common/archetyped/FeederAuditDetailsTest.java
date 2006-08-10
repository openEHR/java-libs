package org.openehr.rm.common.archetyped;

import org.openehr.rm.common.generic.PartyIdentified;
import org.openehr.rm.common.generic.PartyProxy;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.support.terminology.TerminologyService;
import org.openehr.rm.support.terminology.TestCodeSetAccess;
import org.openehr.rm.support.terminology.TestTerminologyService;

import junit.framework.TestCase;

public class FeederAuditDetailsTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

    public void testConstructor() throws Exception {

        // test with null or empty systemID
        assertExceptionThrown(null, null, null,
                new DvDateTime("2004-10-12T09:00:00"),
                null, "versionid12.1", "null or empty systemID");

        assertExceptionThrown("", null, null,
                new DvDateTime("2004-10-12T09:00:00"),
                null, "versionid12.1", "null or empty systemID");
    }
 
    public void testEquals() throws Exception {
        FeederAuditDetails fad1 = new FeederAuditDetails("systemID", null, null,
                new DvDateTime("2004-10-12T09:00:00"),
                null, "versionid12.1");

        FeederAuditDetails fad2 = new FeederAuditDetails("systemID", null, null,
                new DvDateTime("2004-10-12T09:00:00"),
                null, "versionid12.1");

        assertTrue(fad1.equals(fad2));
        assertTrue(fad2.equals(fad1));

        FeederAuditDetails fad3 = new FeederAuditDetails("systemID", null, null,
                new DvDateTime("2004-10-12T09:01:01"),
                null, "versionid12.2");
        assertFalse(fad1.equals(fad3));
        assertFalse(fad3.equals(fad1));
    }
   
    public void testHashCode() throws Exception {
    		FeederAuditDetails fad1 = new FeederAuditDetails("systemID", null, null,
                new DvDateTime("2004-10-12T09:00:00"),
                null, "versionid12.1");

        FeederAuditDetails fad2 = new FeederAuditDetails("systemID", null, null,
                new DvDateTime("2004-10-12T09:00:00"),
                null, "versionid12.1");
        assertEquals("hashcodes not equal", fad1.hashCode(), fad2.hashCode());
        
        FeederAuditDetails fad3 = new FeederAuditDetails("systemID", null, null,
                new DvDateTime("2004-10-12T09:01:01"),
                null, "versionid12.2");
        assertFalse(fad3.equals(fad1));
    }
    
    private void assertExceptionThrown(String systemID, PartyIdentified provider,
    		PartyIdentified location, DvDateTime time, PartyProxy subject,
    		String versionID, String cause) throws Exception {
		try {
			new FeederAuditDetails(systemID, provider, location, time,
					subject, versionID);
			fail("exception should be thrown");
		} catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
			assertTrue("expected cause: " + cause + ", got: " + e.getMessage(),
			e.getMessage().contains(cause));
		}
    }
}
