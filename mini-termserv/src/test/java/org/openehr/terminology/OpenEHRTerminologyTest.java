package org.openehr.terminology;

import java.util.Set;

import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.support.terminology.TerminologyAccess;
import org.openehr.rm.support.terminology.TerminologyService;

import junit.framework.TestCase;

public class OpenEHRTerminologyTest extends TestCase {
	
	public void setUp() throws Exception {
		service = SimpleTerminologyService.getInstance();
	}
	
	public void testHasSettingCode() {
		
		TerminologyAccess terminology = service.terminology(
				TerminologyService.OPENEHR);
		
		assertNotNull("openEHR terminology missing", terminology);
		
		Set<CodePhrase> codes = terminology.codesForGroupName("setting", "en");
		
		assertNotNull("setting(en) missing", codes);
		
		CodePhrase home = new CodePhrase("openehr", "225");
		
		assertTrue("code 225 (home) doesn't exist..", codes.contains(home));
	}
	
	TerminologyService service;
}
