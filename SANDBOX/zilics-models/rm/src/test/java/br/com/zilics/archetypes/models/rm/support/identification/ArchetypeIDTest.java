package br.com.zilics.archetypes.models.rm.support.identification;

import br.com.zilics.archetypes.models.rm.exception.ImmutableException;
import br.com.zilics.archetypes.models.rm.exception.ValidateException;
import junit.framework.TestCase;

public class ArchetypeIDTest extends TestCase {

	public ArchetypeIDTest(String name) {
		super(name);
	}
	
	public void testEqualsAndHashCode() throws Exception {
        String value = "openehr-ehr_rm-section.physical_examination.v2";
        ArchetypeID id1 = new ArchetypeID();
        ArchetypeID id2 = new ArchetypeID();
        
        id1.setValue(value);
        id2.setValue(value);
        
        assertEquals(id1.hashCode(), id2.hashCode());

        assertTrue(id1.equals(id2));
        assertTrue(id2.equals(id1));

        assertTrue(id1.equals(id1));
        assertTrue(id2.equals(id2));
        
        id2.setValue("openehr-ehr_rm-section.physical_examination.v3");

        assertFalse(id1.equals(id2));
        assertFalse(id2.equals(id1));
        
        assertFalse(id1.equals(null));
        assertFalse(id2.equals(null));
    }	


	public void testValidate() throws Exception {
        String []invalid = {null,
        		"openehr",
        		"openehr-ehr",
        		"openehr-ehr-section",
        		"openehr-ehr-section.",
        		"openehr-ehr-section.concept",
        		"openehr-ehr-section.concept.",
        		"-ehr-section.concept.v1",
        		"openehr--section.concept.v1",
        		"openehr-ehr--section.concept.v1",
        		"openehr-ehr--section.concept.v1.",
        		"openehr-ehr-.concept.v1",
        		"openehr-ehr-section..v1",
        		"openehr-ehr-section.concept.v",
        		"openehr-ehr-section.concept-.v1",
        		"openehr-ehr-section.concept-spec1-.v1",
        		"openehr-ehr-section.concept--spec1.v1"
        		};
        String []valid = {
        		"openehr-ehr_rm-section.concept.v2",
        		"openehr-ehr_rm-section.concept-spec.v2",
        		"openehr-ehr_rm-section.none-spec1-spec2.v2",
        		"openehr-ehr_rm-section.concept.v2br",
        		"openehr-ehr_rm-section.concept-spec1.v2br"};
        String[][]tokens = {
        		{"openehr", "ehr_rm", "section", "concept", null, "v2"},	
        		{"openehr", "ehr_rm", "section", "concept", "spec", "v2"},	
        		{"openehr", "ehr_rm", "section", "none", "spec2", "v2"},	
        		{"openehr", "ehr_rm", "section", "concept", null, "v2br"},	
        		{"openehr", "ehr_rm", "section", "concept", "spec1", "v2br"}	
        };

        for(String value : invalid) {
        	ArchetypeID id = new ArchetypeID();
        	id.setValue(value);
        	try {
        		id.validate();
        		fail("Expecting an exception here! " + id);
        	} catch(ValidateException ex) {
        	}
        }
        
        for(int i = 0; i < valid.length; i++) {
        	String value = valid[i];
        	ArchetypeID id = new ArchetypeID();
        	id.setValue(value);
       		id.validate();
       		assertEquals(tokens[i][0], id.rmOriginator());
       		assertEquals(tokens[i][1], id.rmName());
       		assertEquals(tokens[i][2], id.rmEntity());
       		assertEquals(tokens[i][3], id.conceptName());
       		assertEquals(tokens[i][4], id.specialisation());
       		assertEquals(tokens[i][5], id.version());
        }
    }	

	public void testImmutable() throws Exception {
		ArchetypeID id = new ArchetypeID();
       	id.setValue("openehr-ehr_rm-section.physical_examination.v2");
       	id.setImmutable(true);
       	try {
       		id.setValue("openehr-ehr_rm-section.none.v2");
       		fail("Is really immutable?");
       	} catch(ImmutableException ex) {
       	}
       	id.setImmutable(false);
       	id.setValue("openehr-ehr_rm-section.none.v2");
    }	
	

}
