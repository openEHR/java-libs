package br.com.zilics.archetypes.models.rm.support.identification;

import br.com.zilics.archetypes.models.rm.exception.ImmutableException;
import br.com.zilics.archetypes.models.rm.exception.ValidateException;
import junit.framework.TestCase;

public class VersionTreeIDTest extends TestCase {

	public VersionTreeIDTest(String name) {
		super(name);
	}

	public void testEqualsAndHashCode() throws Exception {
        String value = "1.2.1";
        VersionTreeID id1 = new VersionTreeID();
        VersionTreeID id2 = new VersionTreeID();
        
        id1.setValue(value);
        id2.setValue(value);
        
        assertEquals(id1.hashCode(), id2.hashCode());

        assertTrue(id1.equals(id2));
        assertTrue(id2.equals(id1));

        assertTrue(id1.equals(id1));
        assertTrue(id2.equals(id2));
        
        id2.setValue("1.2.3");

        assertFalse(id1.equals(id2));
        assertFalse(id2.equals(id1));

        assertFalse(id1.equals(null));
        assertFalse(id2.equals(null));
    }	


	public void testValidate() throws Exception {
        String []invalid = {null, "", "1.0.3", "1.1", "@", "-2.-3"};
        String []valid = {"1", "1.0.0", "2.3.1", "3.1.3"};
        boolean[]isFirst = {true, true, false, false};
        boolean[]isBranch = {false, false, true, true};

        for(String value : invalid) {
        	VersionTreeID id = new VersionTreeID();
        	id.setValue(value);
        	try {
        		id.validate();
        		fail("Expecting an exception here!" + id);
        	} catch(ValidateException ex) {
        	}
        }
        for(int i = 0; i < valid.length; i++) {
            String value = valid[i];
            VersionTreeID id = new VersionTreeID();
        	id.setValue(value);
       		id.validate();
       		assertEquals(isBranch[i], id.isBranch());
       		assertEquals(isFirst[i], id.isFirst());
        }
    }	

	public void testImmutable() throws Exception {
		VersionTreeID id = new VersionTreeID();
       	id.setValue("70.2.3");
       	id.setImmutable(true);
       	try {
       		id.setValue("70.2.4");
       		fail("Is really immutable?");
       	} catch(ImmutableException ex) {
       	}
       	id.setImmutable(false);
       	id.setValue("70.2.4");
    }	
}
