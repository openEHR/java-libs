package br.com.zilics.archetypes.models.rm.support.identification;

import br.com.zilics.archetypes.models.rm.exception.ImmutableException;
import br.com.zilics.archetypes.models.rm.exception.ValidateException;
import junit.framework.TestCase;


public class HierObjectIDTest extends TestCase {
	public HierObjectIDTest(String testName) {
		super(testName);
	}
	
	public void testEqualsAndHashCode() throws Exception {
        String value = "1.2.840.113554.1.2.2::345";
        HierObjectID id1 = new HierObjectID();
        HierObjectID id2 = new HierObjectID();
        
        id1.setValue(value);
        id2.setValue(value);
        
        assertEquals(id1.hashCode(), id2.hashCode());

        assertTrue(id1.equals(id2));
        assertTrue(id2.equals(id1));

        assertTrue(id1.equals(id1));
        assertTrue(id2.equals(id2));

        id2.setValue("1.2.840.113554.1.2.2::346");

        assertFalse(id1.equals(id2));
        assertFalse(id2.equals(id1));
        
        assertFalse(id1.equals(null));
        assertFalse(id2.equals(null));        
    }	


	public void testValidate() throws Exception {
        String []invalid = {null,
        		"",
        		"1openehr.a",
        		"1...",
        		"::132",
        		"22::132",
        		};
        String []valid = {
                "1.2.840.113554.1.2.2::345",
                "1-2-840-113554-1::789",
                "w123.com::123",
                "1.2.840.113554.1.2.2",
                "1-2-840-113554-1",
                "w123.com"};
        Class<?>[]classes = {
        		ISO_OID.class,
        		UUID.class,
        		InternetID.class,
        		ISO_OID.class,
        		UUID.class,
        		InternetID.class     		
        };

        for(String value : invalid) {
        	HierObjectID id = new HierObjectID();
        	id.setValue(value);
        	try {
        		id.validate();
        		fail("Expecting an exception here! " + id);
        	} catch(ValidateException ex) {
        	}
        }
        
        for(int i = 0; i < valid.length; i++) {
        	String value = valid[i];
        	HierObjectID id = new HierObjectID();
        	id.setValue(value);
       		id.validate();
       		assertSame(classes[i], id.root().getClass());
       		assertEquals(value, id.root().getValue() + ((id.extension() != null) ? "::" + id.extension() : ""));
        }
    }	

	public void testImmutable() throws Exception {
		HierObjectID id = new HierObjectID();
       	id.setValue("1.2.840.113554.1.2.2::345");
       	id.validate();
       	id.setImmutable(true);
       	try {
       		id.setValue("w123.com::123");
       		fail("Is really immutable?");
       	} catch(ImmutableException ex) {
       	}
       	assertTrue(id.root().isImmutable());
       	id.setImmutable(false);
       	assertFalse(id.root().isImmutable());
       	id.setValue("w123.com::123");
    }	
	
}
