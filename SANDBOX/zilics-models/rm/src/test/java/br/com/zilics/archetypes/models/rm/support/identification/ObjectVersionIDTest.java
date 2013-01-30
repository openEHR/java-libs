package br.com.zilics.archetypes.models.rm.support.identification;

import br.com.zilics.archetypes.models.rm.exception.ImmutableException;
import br.com.zilics.archetypes.models.rm.exception.ValidateException;
import junit.framework.TestCase;

public class ObjectVersionIDTest extends TestCase {

	public ObjectVersionIDTest(String name) {
		super(name);
	}

	public void testEqualsAndHashCode() throws Exception {
        String value = "www.code.com::3F2504E0-4F89-11D3-9A0C-0305E82C3301::325::1.2.1";
        ObjectVersionID id1 = new ObjectVersionID();
        ObjectVersionID id2 = new ObjectVersionID();
        
        id1.setValue(value);
        id2.setValue(value);
        
        assertEquals(id1.hashCode(), id2.hashCode());

        assertTrue(id1.equals(id2));
        assertTrue(id2.equals(id1));

        assertTrue(id1.equals(id1));
        assertTrue(id2.equals(id2));
        
        id2.setValue("www.code.com::3F2504E0-4F89-11D3-9A0C-0305E82C3301::325::1.2.2");

        assertFalse(id1.equals(id2));
        assertFalse(id2.equals(id1));

        assertFalse(id1.equals(null));
        assertFalse(id2.equals(null));
    }	


	public void testValidate() throws Exception {
        String []invalid = {null, "", "www.com::12-34-56::1.2.1", "1.1", "@"};

        String []valid = {
        		"www.code.com::3F2504E0-4F89-11D3-9A0C-0305E82C3301::325::1.2.1",
        		"1.2.840.113554.1.2.2::another.org::311::1.0.0",
        		"3F2504E0-4F89-11D3-9A0C-0305E82C3301::1.2.840.113554.1.2.2::2.3.1"
        		};
        Class<?> []objecIdClasses = {
        		InternetID.class,
        		ISO_OID.class,
        		UUID.class
        };
        String []objectId = {
        		"www.code.com",
        		"1.2.840.113554.1.2.2", 
        		"3F2504E0-4F89-11D3-9A0C-0305E82C3301"
        		};
        String []creatingSystemId = {
        		"3F2504E0-4F89-11D3-9A0C-0305E82C3301::325",
        		"another.org::311",
        		"1.2.840.113554.1.2.2"
        		};
        String []versionTreeId = {"1.2.1", "1.0.0", "2.3.1"};

        for(String value : invalid) {
        	ObjectVersionID id = new ObjectVersionID();
        	id.setValue(value);
        	try {
        		id.validate();
        		fail("Expecting an exception here!" + id);
        	} catch(ValidateException ex) {
        	}
        }
        for(int i = 0; i < valid.length; i++) {
            String value = valid[i];
            ObjectVersionID id = new ObjectVersionID();
        	id.setValue(value);
       		id.validate();
       		assertEquals(objectId[i], id.objectId().getValue());
       		assertSame(objecIdClasses[i], id.objectId().getClass());
       		assertEquals(creatingSystemId[i], id.creatingSystemId().getValue());
       		assertEquals(versionTreeId[i], id.versionTreeId().getValue());
        }
    }	

	public void testImmutable() throws Exception {
		ObjectVersionID id = new ObjectVersionID();
       	id.setValue("www.code.com::3F2504E0-4F89-11D3-9A0C-0305E82C3301::325::1.2.1");
       	id.setImmutable(true);
       	try {
       		id.setValue("www.code.com::3F2504E0-4F89-11D3-9A0C-0305E82C3301::325::1.2.1");
       		fail("Is really immutable?");
       	} catch(ImmutableException ex) {
       	}
       	id.setImmutable(false);
       	id.setValue("www.code.com::3F2504E0-4F89-11D3-9A0C-0305E82C3301::325::1.2.1");
    }	
		
}
