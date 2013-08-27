package org.openehr.build;

import java.util.HashMap;
import java.util.Map;

import org.openehr.rm.RMObject;
import org.openehr.rm.datatypes.encapsulated.DvMultimedia;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.datatypes.uri.DvURI;
import org.openehr.rm.support.terminology.TerminologyService;
import org.openehr.terminology.SimpleTerminologyService;

public class CreateDvMultimediaTest extends BuildTestBase {

	public void testCreateSimpleDvMultiMedia() throws Exception {
		
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
		
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("charset", charset);
        values.put("language", language);
        values.put("alternateText", alternateText);
        values.put("mediaType", mediaType);
        values.put("compressionAlgorithm", compressionAlgorithm);
        values.put("integrityCheckAlgorithm", integrityCheckAlgorithm);
        values.put("uri", uri);
        //values.put("thumbnail", thumbnail);
        //values.put("integrityCheck", integrityCheck);
        //values.put("data", null);
        
        RMObject obj = builder.construct("DV_MULTIMEDIA", values);
        assertTrue(obj instanceof DvMultimedia);
	}

}
