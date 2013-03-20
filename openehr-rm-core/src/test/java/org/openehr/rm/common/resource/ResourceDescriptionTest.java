/*
 * component:   "openEHR Reference Implementation"
 * description: "Class ResourceDescriptionTest"
 * keywords:    "unit test"
 *
 * author:      "Yin Su Lim <y.lim@chime.ucl.ac.uk>"
 * support:     "CHIME, UCL"
 * copyright:   "Copyright (c) 2006 UCL, UK"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/BRANCHES/RM-1.0-update/libraries/src/test/org/openehr/rm/common/resource/ResourceDescriptionTest.java $"
 * revision:    "$LastChangedRevision: 50 $"
 * last_change: "$LastChangedDate: 2006-08-10 12:21:46 +0100 (Thu, 10 Aug 2006) $"
 */

/**
 * ResourceDescriptionTest
 *
 * @author Yin Su Lim
 * @version 1.0 
 */
package org.openehr.rm.common.resource;

import junit.framework.*;
import java.util.HashMap;
import java.util.List;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.support.identification.TestTerminologyID;
import org.openehr.rm.support.terminology.TestTerminologyService;

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
       /* CodePhrase orgLang = new CodePhrase(TestTerminologyID.LANGUAGE, "en");
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
         */  
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

/*
 *  ***** BEGIN LICENSE BLOCK *****
 *  Version: MPL 1.1/GPL 2.0/LGPL 2.1
 *
 *  The contents of this file are subject to the Mozilla Public License Version
 *  1.1 (the 'License'); you may not use this file except in compliance with
 *  the License. You may obtain a copy of the License at
 *  http://www.mozilla.org/MPL/
 *
 *  Software distributed under the License is distributed on an 'AS IS' basis,
 *  WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 *  for the specific language governing rights and limitations under the
 *  License.
 *
 *  The Original Code is ResourceDescriptionTest.java
 *
 *  The Initial Developer of the Original Code is Rong Chen.
 *  Portions created by the Initial Developer are Copyright (C) 2003-2004
 *  the Initial Developer. All Rights Reserved.
 *
 *  Contributor(s):
 *
 * Software distributed under the License is distributed on an 'AS IS' basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 *  ***** END LICENSE BLOCK *****
 */