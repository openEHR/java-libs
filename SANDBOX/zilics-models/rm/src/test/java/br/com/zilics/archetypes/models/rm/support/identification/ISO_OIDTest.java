package br.com.zilics.archetypes.models.rm.support.identification;

import br.com.zilics.archetypes.models.rm.exception.ImmutableException;
import br.com.zilics.archetypes.models.rm.exception.ValidateException;
import junit.framework.TestCase;

public class ISO_OIDTest extends TestCase {
	public ISO_OIDTest(String testName) {
		super(testName);
	}

	public void testEqualsAndHashCode() throws Exception {
        String value = "1.2.840.113554.1.2.2";
        ISO_OID id1 = new ISO_OID();
        ISO_OID id2 = new ISO_OID();
        
        id1.setValue(value);
        id2.setValue(value);
        
        assertEquals(id1.hashCode(), id2.hashCode());

        assertTrue(id1.equals(id2));
        assertTrue(id2.equals(id1));

        assertTrue(id1.equals(id1));
        assertTrue(id2.equals(id2));
        
        id2.setValue("1.2.840.113554.1.2.3");

        assertFalse(id1.equals(id2));
        assertFalse(id2.equals(id1));

        assertFalse(id1.equals(null));
        assertFalse(id2.equals(null));
    }	


	public void testValidate() throws Exception {
        String []invalid = {".", "", "1", "1.1.", "1..1", "11.1",  null};
        String []valid = {"1.3", "1.2", "1.22", "2.23"};

        for(String value : invalid) {
        	ISO_OID id = new ISO_OID();
        	id.setValue(value);
        	try {
        		id.validate();
        		fail("Expecting an exception here!" + id);
        	} catch(ValidateException ex) {
        	}
        }
        for(String value : valid) {
        	ISO_OID id = new ISO_OID();
        	id.setValue(value);
       		id.validate();
        }
    }	

	public void testImmutable() throws Exception {
       	ISO_OID id = new ISO_OID();
       	id.setValue("1.1");
       	id.setImmutable(true);
       	try {
       		id.setValue("1.2");
       		fail("Is really immutable?");
       	} catch(ImmutableException ex) {
       	}
       	id.setImmutable(false);
       	id.setValue("1.2");
    }	

}
