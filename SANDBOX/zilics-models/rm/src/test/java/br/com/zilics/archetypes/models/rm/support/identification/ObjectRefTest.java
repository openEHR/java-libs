package br.com.zilics.archetypes.models.rm.support.identification;

import br.com.zilics.archetypes.models.rm.exception.ImmutableException;
import br.com.zilics.archetypes.models.rm.exception.ValidateException;
import junit.framework.TestCase;

public class ObjectRefTest extends TestCase {

	public ObjectRefTest(String name) {
		super(name);
	}
	
	public void testEqualsAndHashCode() throws Exception {
        String namespace = "namespace";
        String type = "type";
        TemplateID id = new TemplateID();
        id.setValue("template1");

        ObjectRef ref1 = new ObjectRef();
        ObjectRef ref2 = new ObjectRef();
        
        assertTrue(ref1.equals(ref2));
        assertTrue(ref2.equals(ref1));
        
        assertTrue(ref1.equals(ref1));
        assertTrue(ref2.equals(ref2));

        assertEquals(ref1.hashCode(), ref2.hashCode());

        ref1.setNamespace(namespace);
        ref2.setNamespace(namespace);

        assertTrue(ref1.equals(ref2));
        assertTrue(ref2.equals(ref1));
        
        assertTrue(ref1.equals(ref1));
        assertTrue(ref2.equals(ref2));

        assertEquals(ref1.hashCode(), ref2.hashCode());

        ref1.setType(type);
        ref2.setType(type);

        assertTrue(ref1.equals(ref2));
        assertTrue(ref2.equals(ref1));
        
        assertTrue(ref1.equals(ref1));
        assertTrue(ref2.equals(ref2));

        assertEquals(ref1.hashCode(), ref2.hashCode());

        ref1.setId(id);
        ref2.setId(id);

        assertTrue(ref1.equals(ref2));
        assertTrue(ref2.equals(ref1));
        
        assertTrue(ref1.equals(ref1));
        assertTrue(ref2.equals(ref2));

        assertEquals(ref1.hashCode(), ref2.hashCode());


        TerminologyID anotherId = new TerminologyID();
        anotherId.setValue("template1");
        ref1.setId(anotherId);

        assertFalse(ref1.equals(ref2));
        assertFalse(ref2.equals(ref1));

        
        ref1.setId(ref2.getId());
        assertTrue(ref1.equals(ref1));
        assertTrue(ref2.equals(ref2));
        
        ref1.setType("...");
        assertFalse(ref1.equals(ref2));
        assertFalse(ref2.equals(ref1));

        ref1.setType(ref2.getType());
        assertTrue(ref1.equals(ref1));
        assertTrue(ref2.equals(ref2));
        
        ref1.setNamespace("...");
        assertFalse(ref1.equals(ref2));
        assertFalse(ref2.equals(ref1));

        assertFalse(ref1.equals(null));
        assertFalse(ref2.equals(null));

	}	


	public void testValidate() throws Exception {
		ObjectRef ref = new ObjectRef();
		TemplateID id = new TemplateID();
		id.setValue("template2");
		
       	ref.setNamespace("namespace");
       	ref.setType("type");
       	ref.setId(id);
       	
       	ref.validate();
       	
       	try {
       		ref.setNamespace("");
       		ref.validate();
       		fail("Ops... not validating correctly");
       	} catch (ValidateException ex) {
       		ref.setNamespace("namespace");
       	}
       	
       	try {
       		ref.setType("");
       		ref.validate();
       		fail("Ops... not validating correctly");
       	} catch (ValidateException ex) {
       		ref.setType("type");
       	}
       	
       	try {
       		ref.setId(null);
       		ref.validate();
       		fail("Ops... not validating correctly");
       	} catch (ValidateException ex) {
       		
       	}

	}	

	public void testImmutable() throws Exception {
		ObjectRef ref = new ObjectRef();
		TemplateID id = new TemplateID();
		id.setValue("template2");
		
       	ref.setNamespace("namespace");
       	ref.setType("type");
       	ref.setId(id);
       	
       	ref.setImmutable(true);
       	try {
       		ref.setNamespace("another");
       		fail("Is really immutable?");
       	} catch(ImmutableException ex) {
       	}
       	try {
       		ref.setType("type");
       		fail("Is really immutable?");
       	} catch(ImmutableException ex) {
       	}
       	try {
       		ref.setId(id);
       		fail("Is really immutable?");
       	} catch(ImmutableException ex) {
       	}

       	assertTrue(ref.getId().isImmutable());
       	ref.setImmutable(false);
       	assertFalse(ref.getId().isImmutable());
       	ref.setId(id);
       	ref.setNamespace(null);
       	ref.setType(null);
    }	
	

}
