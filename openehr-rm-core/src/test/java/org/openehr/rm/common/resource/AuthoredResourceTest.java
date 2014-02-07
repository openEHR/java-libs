/*
 * component:   "openEHR Reference Implementation"
 * description: "Class AuthoredResourceTest"
 * keywords:    "unit test"
 *
 * author:      "Yin Su Lim <y.lim@chime.ucl.ac.uk>"
 * support:     "CHIME, UCL"
 * copyright:   "Copyright (c) 2006 UCL, UK"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/BRANCHES/RM-1.0-update/libraries/src/test/org/openehr/rm/common/resource/AuthoredResourceTest.java $"
 * revision:    "$LastChangedRevision: 50 $"
 * last_change: "$LastChangedDate: 2006-08-10 12:21:46 +0100 (Thu, 10 Aug 2006) $"
 */

/**
 * AuthoredResourceTest
 *
 * @author Yin Su Lim
 * @version 1.0 
 */

package org.openehr.rm.common.resource;

import junit.framework.*;
import java.util.HashMap;
import java.util.HashSet;
import org.openehr.rm.common.generic.RevisionHistory;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.support.identification.TestTerminologyID;
import org.openehr.rm.support.terminology.TerminologyService;
import org.openehr.rm.support.terminology.TestTerminologyService;

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
 *  The Original Code is AuthoredResourceTest.java
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