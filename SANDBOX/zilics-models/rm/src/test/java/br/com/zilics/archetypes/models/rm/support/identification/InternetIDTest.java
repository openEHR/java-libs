package br.com.zilics.archetypes.models.rm.support.identification;

import br.com.zilics.archetypes.models.rm.exception.ImmutableException;
import br.com.zilics.archetypes.models.rm.exception.ValidateException;
import junit.framework.TestCase;

public class InternetIDTest extends TestCase {
	public InternetIDTest(String testName) {
		super(testName);
	}
	
	public void testEqualsAndHashCode() throws Exception {
        String value = "www.test.org";
        InternetID id1 = new InternetID();
        InternetID id2 = new InternetID();
        
        id1.setValue(value);
        id2.setValue(value);
        
        assertEquals(id1.hashCode(), id2.hashCode());

        assertTrue(id1.equals(id2));
        assertTrue(id2.equals(id1));

        assertTrue(id1.equals(id1));
        assertTrue(id2.equals(id2));

        id2.setValue("www.test2.org");

        assertFalse(id1.equals(id2));
        assertFalse(id2.equals(id1));
        
        assertFalse(id1.equals(null));
        assertFalse(id2.equals(null));
    }	


	public void testValidate() throws Exception {
        String []invalid = {null, "-", "hello@com.br", "", "9", "www.."};
        String []valid = {"www", "org.com", "test123.x2c", "test.com.br"};

        for(String value : invalid) {
        	InternetID id = new InternetID();
        	id.setValue(value);
        	try {
        		id.validate();
        		fail("Expecting an exception here!" + id);
        	} catch(ValidateException ex) {
        	}
        }
        for(String value : valid) {
        	InternetID id = new InternetID();
        	id.setValue(value);
       		id.validate();
        }
    }	

	public void testImmutable() throws Exception {
		InternetID id = new InternetID();
       	id.setValue("www1.com");
       	id.setImmutable(true);
       	try {
       		id.setValue("www2.com");
       		fail("Is really immutable?");
       	} catch(ImmutableException ex) {
       	}
       	id.setImmutable(false);
       	id.setValue("www2.com");
    }	
	
}
