package org.openehr.terminology;

import java.util.Set;

import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.support.terminology.*;

import junit.framework.TestCase;

public class OpenEHRTerminologyTest extends TestCase {
	
	public OpenEHRTerminologyTest() throws Exception {
		service = SimpleTerminologyService.getInstance();
	}
	
	public void setUp() throws Exception {
		
	}
	
	public void testHasOpenEHRSettingCode() {
		
		TerminologyAccess terminology = service.terminology(
				TerminologyService.OPENEHR);
		
		assertNotNull("openEHR terminology missing", terminology);
		
		Set<CodePhrase> codes = terminology.codesForGroupName("setting", "en");
		
		assertNotNull("setting(en) missing", codes);
		
		CodePhrase home = new CodePhrase("openehr", "225");
		
		assertTrue("code 225 (home) doesn't exist..", codes.contains(home));
	}
	
	public void testRubricForCode() throws Exception {
		TerminologyAccess terminology = service.terminology(
				TerminologyService.OPENEHR);
		
		assertEquals("event", terminology.rubricForCode("433", "en"));		
		assertEquals("initial", terminology.rubricForCode("524", "en"));		
	}
	
	public void testHasCountryCodes() throws Exception {
		CodeSetAccess codeSet = service.codeSetForId(
				OpenEHRCodeSetIdentifiers.COUNTRIES);
	
		assertNotNull("countries codeSet missing", codeSet);
		
		assertTrue("China missing", 
				codeSet.hasCode(new CodePhrase("ISO_3166-1", "CN")));
		assertTrue("Sweden missing", 
				codeSet.hasCode(new CodePhrase("ISO_3166-1", "SE")));
		assertTrue("United Kingdom missing", 
				codeSet.hasCode(new CodePhrase("ISO_3166-1", "GB")));
		assertTrue("Denmark missing", 
				codeSet.hasCode(new CodePhrase("ISO_3166-1", "DK")));
		assertTrue("France missing", 
				codeSet.hasCode(new CodePhrase("ISO_3166-1", "FR")));
	}
	
	public void testHasOpenEHRCode() throws Exception {
		TerminologyAccess terminology = service.terminology(
				TerminologyService.OPENEHR);
		assertTrue("code for signed missing", terminology.allCodes().contains(
				new CodePhrase(TerminologyService.OPENEHR, "240")));
	}
	
	// TODO some test isolation issue here.. 
	public void _testHasPlainTextMediaType() throws Exception {
		CodeSetAccess codeSet = service.codeSetForId(
        		OpenEHRCodeSetIdentifiers.MEDIA_TYPES);
		CodePhrase mediaType = new CodePhrase("IANA_media-types", "text/plain");
		assertTrue("media type: text/plain is missing", codeSet.hasCode(mediaType));
	}
	
	public void __testHasCompressionAlgorithmsOther() throws Exception {
		CodeSetAccess codeSet = service.codeSetForId(
        		OpenEHRCodeSetIdentifiers.COMPRESSION_ALGORITHMS);
		CodePhrase mediaType = new CodePhrase("openehr_compression_algorithms", "others");
		assertTrue("openehr_compression_algorithms is missing", codeSet.hasCode(mediaType));
	}
	
	TerminologyService service;
}
