package org.openehr.am.serialize;

import java.util.*;

import org.openehr.rm.common.generic.RevisionHistory;
import org.openehr.rm.common.resource.AuthoredResource;
import org.openehr.rm.common.resource.ResourceDescription;
import org.openehr.rm.common.resource.TranslationDetails;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.support.terminology.TerminologyService;
import org.openehr.terminology.SimpleTerminologyService;

public class ArchetypeLanguageTest extends SerializerTestBase {

	public ArchetypeLanguageTest(String test) {
		super(test);
	}

	public void testPrintArchetypeLanguage() throws Exception {
		CodePhrase originalLanguage = new CodePhrase("ISO_639-1", "en");
		Map<String, TranslationDetails> translations = 
			new HashMap<String, TranslationDetails>();
		SortedMap<String, String> author = new TreeMap<String,String>();
		author.put("name", "Harry Potter");
		author.put("email", "harry@something.somewhere.co.uk");
		SortedMap<String, String> otherDetails = new TreeMap<String, String>();
		otherDetails.put("reviewer 1", "Ron Weasley");
		otherDetails.put("reviewer 2", "Rubeus Hagrid");	
		
		TranslationDetails td = new TranslationDetails(
				new CodePhrase("ISO_639-1", "de"), author, 
				"British Medical Translator id 00400595", otherDetails, null);
		translations.put("de", td);
		
		ResourceDescription description = null;
		RevisionHistory revisionHistory = null;
		boolean isControlled = false;
		TerminologyService service = SimpleTerminologyService.getInstance();
		
		AuthoredResource authored = new AuthoredResource(originalLanguage,
				translations, description, revisionHistory, isControlled, 
				service) {};
		
		clean();
		outputter.printLanguage(authored, out);
		verifyByFile("archetype-language.adl");		
	}
	
	
}
