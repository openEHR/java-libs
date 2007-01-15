package org.openehr.am.serialize;

import java.util.Map;

import org.openehr.rm.common.generic.RevisionHistory;
import org.openehr.rm.common.resource.AuthoredResource;
import org.openehr.rm.common.resource.ResourceDescription;
import org.openehr.rm.common.resource.TranslationDetails;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.support.terminology.TerminologyService;

public class ArchetypeLanguageTest extends SerializerTestBase {

	public ArchetypeLanguageTest(String test) {
		super(test);
	}

	public void testPrintArchetypeLanguage() throws Exception {
		CodePhrase originalLanguage = new CodePhrase("iso639-2", "en");
		Map<String, TranslationDetails> translations = null;
		ResourceDescription description = null;
		RevisionHistory revisionHistory = null;
		boolean isControlled = false;
		TerminologyService service = null;
		
		AuthoredResource authored = new AuthoredResource(originalLanguage,
				translations, description, revisionHistory, isControlled, 
				service) {};
		
		clean();
		outputter.printLanguage(authored, out);
		verifyByFile("archetype-language.adl");		
	}
	
	
}
