/*
 * ResourceTestBase.java
 *
 * Created on July 27, 2006, 2:25 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.openehr.rm.common.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import junit.framework.TestCase;
import org.openehr.rm.common.generic.RevisionHistory;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.support.identification.TestTerminologyID;
import org.openehr.rm.support.terminology.TerminologyService;
import org.openehr.rm.support.terminology.TestTerminologyService;

/**
 *
 * @author yinsulim
 */
public class ResourceTestBase extends TestCase {
    
    /** Creates a new instance of ResourceTestBase */
    public ResourceTestBase(String name) {
        super(name);
    }

    protected List<ResourceDescriptionItem> details(String[] languages, String[] purposes) {
        List<ResourceDescriptionItem> rdiList = new ArrayList<ResourceDescriptionItem>();
        for(int i = 0; i < languages.length; i++) {
            CodePhrase orgLang = new CodePhrase(TestTerminologyID.LANGUAGE, languages[i]);
            ResourceDescriptionItem rdi = new ResourceDescriptionItem(orgLang, purposes[i], 
                TestTerminologyService.getInstance());
            rdiList.add(rdi);
        }
        return rdiList;
    }

    protected HashMap<String, TranslationDetails> translations(String[] languages) {
        HashMap<String, TranslationDetails> translations = new HashMap<String, TranslationDetails>();
        for(int i = 0; i < languages.length; i++) {
            CodePhrase langC = new CodePhrase(TestTerminologyID.LANGUAGE, languages[i]);
            TranslationDetails td = new TranslationDetails(langC, hashMap("Tom Beale", "Thomas Beale"), 
		null, null, TestTerminologyService.getInstance());
            translations.put(languages[i], td);
        }
        return translations;
    }
        
    protected HashMap<String, String> hashMap(String key, String value) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put(key, value);
        return map;
    }
    
    protected class AuthoredResourceImpl extends AuthoredResource {

        AuthoredResourceImpl(CodePhrase originalLanguage, HashMap<String, 
                TranslationDetails> translations, ResourceDescription description,
                RevisionHistory revisionHistory, boolean isControlled, 
                TerminologyService terminologyService) {
        super(originalLanguage, translations, description, revisionHistory, isControlled,
                terminologyService);
    }
            
        AuthoredResourceImpl() {
            super();
        }
    }

}
