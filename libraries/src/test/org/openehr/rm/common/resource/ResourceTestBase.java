/*
 * component:   "openEHR Reference Implementation"
 * description: "Class ResourceTestBase"
 * keywords:    "unit test"
 *
 * author:      "Yin Su Lim <y.lim@chime.ucl.ac.uk>"
 * support:     "CHIME, UCL"
 * copyright:   "Copyright (c) 2006 UCL, UK"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/BRANCHES/RM-1.0-update/libraries/src/test/org/openehr/rm/common/resource/ResourceTestBase.java $"
 * revision:    "$LastChangedRevision: 50 $"
 * last_change: "$LastChangedDate: 2006-08-10 12:21:46 +0100 (Thu, 10 Aug 2006) $"
 */

/**
 * ResourceTestBase
 *
 * @author Yin Su Lim
 * @version 1.0 
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
 *  The Original Code is ResourceTestBase.java
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