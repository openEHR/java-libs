/*
 * ResourceDescriptionTest.java
 * JUnit based test
 *
 * Created on July 24, 2006, 5:03 PM
 */

package org.openehr.rm.common.resource;

import junit.framework.*;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.openehr.rm.Attribute;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.support.identification.ObjectID;
import org.openehr.rm.support.identification.TestTerminologyID;
import org.openehr.rm.support.terminology.TestTerminologyService;

/**
 *
 * @author yinsulim
 */
public class ResourceDescriptionTest extends ResourceTestBase {
    
    public ResourceDescriptionTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(ResourceDescriptionTest.class);
        
        return suite;
    }

    public void test() {
        String[] fr = {"fr"};
        String[] en = {"en"};
        String[] purpose = {"purpose"};
        String[] languages = {"en", "fr"};
        String[] purposes = {"purpose", "but"};
        CodePhrase orgLang = new CodePhrase(TestTerminologyID.LANGUAGE, "en");
        AuthoredResource ar = new AuthoredResourceImpl(orgLang, translations(fr), null, null, false,
                TestTerminologyService.getInstance());
        
        ResourceDescription rd = new ResourceDescription(hashMap("Sam Heard", "Dr. Sam Heard"),
                null, "initial", details(languages, purposes), null, null, ar);
        assertEquals(ar, rd.getParentResource());
        assertEquals(rd, ar.getDescription());
        ar = new AuthoredResourceImpl(orgLang, null, null, null, false,
                TestTerminologyService.getInstance());
        rd = new ResourceDescription(hashMap("Sam Heard", "Dr. Sam Heard"),
                null, "initial", details(en, purpose), null, null, ar);
        
    }
    
    public void testFails() {
        CodePhrase orgLang = new CodePhrase(TestTerminologyID.LANGUAGE, "en");
        String[] languages = {"en", "fr"};
        String[] purposes = {"purpose", "but"};
        String[] fr = {"fr"};
        
        failConstructor(null, null, "initial", details(languages, purposes),
                 null, null, null, "original author missing");
        failConstructor(hashMap("Sam Heard", "Dr. Sam Heard"), null, null, details(languages, purposes),
                 null, null, null, "lifeCycleState missing");
        failConstructor(hashMap("Sam Heard", "Dr. Sam Heard"), null, "initial", null,
                 null, null, null, "details missing");
        
        AuthoredResource ar = new AuthoredResourceImpl(orgLang, null, null, null, false,
                TestTerminologyService.getInstance());
        ResourceDescription rd = new ResourceDescription(hashMap("Sam Heard", "Dr. Sam Heard"),
                null, "initial", details(languages, purposes), null, null, null);
        failConstructor(hashMap("Sam Heard", "Dr. Sam Heard"), null, "initial", details(languages, purposes),
                null, null, ar, "details has more languages than that in parent");
           
    }
    
    private void failConstructor(HashMap<String, String> orgAuthor, List<String> 
                otherContr, String lifeCycleState, List<ResourceDescriptionItem> details,
                String resourcePackageUri, HashMap<String, String> otherDetails, 
                AuthoredResource parentResource, String reason) {
        try {
            ResourceDescription rd = new ResourceDescription(orgAuthor, otherContr,
                    lifeCycleState, details, resourcePackageUri, otherDetails, parentResource);
            fail(reason);
        } catch (IllegalArgumentException iae) {
            //System.out.println(iae.getMessage());
        }
    }    
}
