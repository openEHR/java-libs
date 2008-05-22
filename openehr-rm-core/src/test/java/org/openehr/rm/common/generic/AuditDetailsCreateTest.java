package org.openehr.rm.common.generic;

import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.support.identification.HierObjectID;
import org.openehr.rm.support.identification.ObjectRef;
import org.openehr.rm.support.identification.PartyRef;
import org.openehr.rm.support.terminology.*;
import junit.framework.TestCase;

public class AuditDetailsCreateTest extends TestCase {
	 
	public void setUp() throws Exception {
		termServ = TestTerminologyService.getInstance();		
	}
	
	public void testCreateAuditDetailsWithOpenehrCode() throws Exception {
		CodePhrase lang = new CodePhrase("ISO_639-1", "en");
	    CodePhrase encoding = new CodePhrase("IANA_character-sets", "UTF-8");	    
		CodePhrase creationCode = new CodePhrase("openehr", "249");		
		DvCodedText codedText = new DvCodedText("creation", 
				lang, encoding, creationCode, termServ);
		PartyIdentified pi = new PartyIdentified(new PartyRef(new HierObjectID(
				"1-2-3-4-5"), ObjectRef.Type.PARTY), "committer name", null);
		
		audit = new AuditDetails("12.3.4.5", pi, new DvDateTime(
		"2007-08-14T10:10:00"), codedText, null, termServ);
		
		assertNotNull("audit null", audit);
	}
	
	private TerminologyService termServ;
	private AuditDetails audit;
}
