package br.com.zilics.archetypes.models.rm.support.identification;

import br.com.zilics.archetypes.models.rm.exception.ImmutableException;
import br.com.zilics.archetypes.models.rm.exception.ValidateException;
import junit.framework.TestCase;

public class UUIDTest extends TestCase {

	public UUIDTest(String name) {
		super(name);
	}

	public void testEqualsAndHashCode() throws Exception {
        String value = "3F2504E0-4F89-11D3-9A0C-0305E82C3301";
        UUID id1 = new UUID();
        UUID id2 = new UUID();
        
        id1.setValue(value);
        id2.setValue(value);
        
        assertEquals(id1.hashCode(), id2.hashCode());

        assertTrue(id1.equals(id2));
        assertTrue(id2.equals(id1));

        assertTrue(id1.equals(id1));
        assertTrue(id2.equals(id2));
        
        id2.setValue("3F2504E0-4F89-11D3-9A0C-0305E82C3302");

        assertFalse(id1.equals(id2));
        assertFalse(id2.equals(id1));

        assertFalse(id1.equals(null));
        assertFalse(id2.equals(null));
    }	


	public void testValidate() throws Exception {
        String []invalid = {"1--", "2--b", "1.1", "@", "1--2",  null};
        String []valid = {"3F2504E0-4F89-11D3-9A0C-0305E82C3301"};

        for(String value : invalid) {
        	UUID id = new UUID();
        	id.setValue(value);
        	try {
        		id.validate();
        		fail("Expecting an exception here!" + id);
        	} catch(ValidateException ex) {
        	}
        }
        for(String value : valid) {
        	UUID id = new UUID();
        	id.setValue(value);
       		id.validate();
        }
    }	

	public void testImmutable() throws Exception {
		UUID id = new UUID();
       	id.setValue("3F2504E0-4F89-11D3-9A0C-0305E82C3301");
       	id.setImmutable(true);
       	try {
       		id.setValue("3F2504E0-4F89-11D3-9A0C-0305E82C3302");
       		fail("Is really immutable?");
       	} catch(ImmutableException ex) {
       	}
       	id.setImmutable(false);
       	id.setValue("3F2504E0-4F89-11D3-9A0C-0305E82C3302");
    }	
	
}
