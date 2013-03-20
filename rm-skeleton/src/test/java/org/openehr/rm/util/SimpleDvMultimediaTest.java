package org.openehr.rm.util;

import org.openehr.rm.datatypes.encapsulated.DvMultimedia;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.datatypes.uri.DvURI;
import org.openehr.rm.support.terminology.TerminologyService;
import org.openehr.terminology.SimpleTerminologyService;

import junit.framework.TestCase;

public class SimpleDvMultimediaTest extends TestCase {
	
	public void testCreateSimpleDvMultimedia() throws Exception {
		CodePhrase charset = new CodePhrase("IANA_character-sets", "UTF-8"); 
		CodePhrase language = new CodePhrase("ISO_639-1","en");
		String alternateText = "alternative text";
        CodePhrase mediaType = new CodePhrase("IANA_media-types", "text/plain");
        CodePhrase compressionAlgorithm = new CodePhrase("openehr_compression_algorithms", "other");
        byte[] integrityCheck = new byte[0];
        CodePhrase integrityCheckAlgorithm = new CodePhrase("openehr_integrity_check_algorithms", "SHA-1");
        DvMultimedia thumbnail = null;
		DvURI uri = new DvURI("www.iana.org");
        byte[] data = new byte[0];
        TerminologyService terminologyService = SimpleTerminologyService.getInstance();
		DvMultimedia dm = new DvMultimedia(charset, language, alternateText,
				mediaType, compressionAlgorithm, integrityCheck, 
				integrityCheckAlgorithm, thumbnail, uri, data, terminologyService);
		
		assertNotNull(dm);		
	}
}
