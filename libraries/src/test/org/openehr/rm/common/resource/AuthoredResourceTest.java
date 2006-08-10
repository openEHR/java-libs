/*
 * AuthoredResourceTest.java
 * JUnit based test
 *
 * Created on July 24, 2006, 5:11 PM
 */

package org.openehr.rm.common.resource;

import java.util.ArrayList;
import java.util.List;
import junit.framework.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.openehr.rm.RMObject;
import org.openehr.rm.common.generic.RevisionHistory;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.support.identification.TestTerminologyID;
import org.openehr.rm.support.terminology.CodeSetAccess;
import org.openehr.rm.support.terminology.TerminologyService;
import org.openehr.rm.support.terminology.TestTerminologyService;

/**
 *
 * @author yinsulim
 */
public class AuthoredResourceTest extends ResourceTestBase {
    
    public AuthoredResourceTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(AuthoredResourceTest.class);
        
        return suite;
    }

    public void test() {
        CodePhrase orgLang = new CodePhrase(TestTerminologyID.LANGUAGE, "en");
        
        String[] fr = {"fr"};
        String[] languages = {"en", "fr"};
        String[] purposes = {"purpose", "but"};
        ResourceDescription rd = new ResourceDescription(hashMap("Sam Heard", "Dr. Sam Heard"),
                null, "initial", details(languages, purposes), null, null, null);
        AuthoredResource ar = new AuthoredResourceImpl(orgLang, translations(fr), rd, null, false,
                TestTerminologyService.getInstance());
        assertEquals(ar, rd.getParentResource());
        assertEquals("uncontrolled", ar.currentRevision());
        HashSet<String> lAvailable = new HashSet<String>();
        lAvailable.add("en");
        lAvailable.add("fr");
        assertEquals(lAvailable, ar.languagesAvailable());
        
    }


    
    public void testFails() {
        
        CodePhrase orgLang = new CodePhrase(TestTerminologyID.LANGUAGE, "en");
        String[] languages = {"en", "fr"};
        String[] fr = {"fr"};
        
        failConstructor(null, null, null, null, false, TestTerminologyService.getInstance(),
                "original language missing");
        failConstructor(orgLang, null, null, null, true, TestTerminologyService.getInstance(),
                "revision history missing");
        failConstructor(orgLang, translations(languages), null, null, true, TestTerminologyService.getInstance(),
                "translations contain org language");
        
        String[] languages2 = {"en", "fr", "us"};
        String[] purposes = {"purpose", "but", "purpose"};
        ResourceDescription rd = new ResourceDescription(hashMap("Sam Heard", "Dr. Sam Heard"),
                null, "initial", details(languages2, purposes), null, null, null);
        failConstructor(orgLang, translations(fr), rd, null, false, TestTerminologyService.getInstance(),
                "description contains more languages than translations");
    }
    
    private void failConstructor(CodePhrase orgLang, HashMap<String, 
                TranslationDetails> translations, ResourceDescription rd,
                RevisionHistory revisionHistory, boolean isControlled, 
                TerminologyService terminologyService, String reason) {
        try {
            AuthoredResource ar = new AuthoredResourceImpl(orgLang, translations, rd, revisionHistory,
                isControlled, terminologyService);
            fail(reason);
        } catch (IllegalArgumentException iae) {
            //System.out.println(iae.getMessage());
        }
    }
    
    /*private List<ResourceDescriptionItem> details(String[] languages, String[] purposes) {
        List<ResourceDescriptionItem> rdiList = new ArrayList<ResourceDescriptionItem>();
        for(int i = 0; i < languages.length; i++) {
            CodePhrase orgLang = new CodePhrase(TestTerminologyID.LANGUAGE, languages[i]);
            ResourceDescriptionItem rdi = new ResourceDescriptionItem(orgLang, purposes[i], 
                TestTerminologyService.getInstance());
            rdiList.add(rdi);
        }
        return rdiList;
    }
    
    private HashMap<String, String> hashMap(String key, String value) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put(key, value);
        return map;
    }
    
    private class AuthoredResourceImpl extends AuthoredResource {

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
    }*/
    
}
