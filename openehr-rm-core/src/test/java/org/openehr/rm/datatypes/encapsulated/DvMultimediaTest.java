package org.openehr.rm.datatypes.encapsulated;

import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.datatypes.uri.DvURI;
import org.openehr.rm.support.terminology.TerminologyService;
import org.openehr.rm.support.terminology.TestTerminologyService;

import junit.framework.TestCase;

public class DvMultimediaTest extends TestCase {
	
	public void testCreateSimpleDvMultimedia() throws Exception {
		CodePhrase charset = new CodePhrase("IANA_character-sets", "UTF-8"); 
		CodePhrase language = new CodePhrase("ISO_3166-1","eN");
		String alternateText = "alternative text";
        CodePhrase mediaType = new CodePhrase("IANA_media-types", "text/plain");
        CodePhrase compressionAlgorithm = new CodePhrase("openehr_compression_algorithms", "others");
        byte[] integrityCheck = new byte[0];
        CodePhrase integrityCheckAlgorithm = new CodePhrase("openehr_integrity_check_algorithms", "SHA-1");
        DvMultimedia thumbnail = null;
		DvURI uri = new DvURI("www.iana.org");
        byte[] data = new byte[0];
        TerminologyService terminologyService = new TestTerminologyService();
		DvMultimedia dm = new DvMultimedia(charset, language, alternateText,
				mediaType, compressionAlgorithm, integrityCheck, 
				integrityCheckAlgorithm, thumbnail, uri, data, terminologyService);
		
		assertNotNull(dm);		
	}
}
