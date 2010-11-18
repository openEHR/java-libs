/*
 * component:   "openEHR Reference Implementation"
 * description: "Class ImportedVersionTest"
 * keywords:    "unit test"
 *
 * author:      "Yin Su Lim <y.lim@chime.ucl.ac.uk>"
 * support:     "CHIME, UCL"
 * copyright:   "Copyright (c) 2006 UCL, UK"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/BRANCHES/RM-1.0-update/libraries/src/test/org/openehr/rm/common/changecontrol/ImportedVersionTest.java $"
 * revision:    "$LastChangedRevision: 50 $"
 * last_change: "$LastChangedDate: 2006-08-10 12:21:46 +0100 (Thu, 10 Aug 2006) $"
 */

/**
 * ImportedVersionTest
 *
 * @author Yin Su Lim
 * @version 1.0 
 */
package org.openehr.rm.common.changecontrol;

import junit.framework.*;
import java.util.List;
import org.openehr.rm.common.generic.Attestation;
import org.openehr.rm.common.generic.AuditDetails;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.support.identification.ObjectRef;
import org.openehr.rm.support.identification.ObjectVersionID;
import org.openehr.rm.support.terminology.TerminologyService;
import org.openehr.rm.support.terminology.TestTerminologyService;

public class ImportedVersionTest extends ChangeControlTestBase {
    
    public ImportedVersionTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(ImportedVersionTest.class);
        
        return suite;
    }

    public void test() {
        OriginalVersion<String> ov = new OriginalVersion<String>(
            new ObjectVersionID("1.4.4.5::1.2.840.114.1.2.2::1"), null, "a name", 
            lifeCycleState("complete"), audit("1-4-3-5-2", "comitter's name", "changeTypeCode",
            "2006-07-01T13:22:55"), 
            contribution("1.4.4.5::1.2.840.114.1.2.2::2", "path/morePath"), null, 
            null, null, // false, 
            TestTerminologyService.getInstance());
        ImportedVersion<String> iv = new ImportedVersion<String>(ov, 
            audit("adminc.nhs.uk", "comitter's name", "changeTypeCode", "2006-07-01T15:00:09"),
            contribution("1.4.4.15::1.2.840.114.1.2.2::1", "path/morePath"), 
            null);
        assertEquals("1.4.4.5::1.2.840.114.1.2.2::1", iv.getUid().toString());
        assertEquals(null, iv.getPrecedingVersionUid());
        assertEquals(lifeCycleState("complete"), iv.getLifecycleState());
        assertEquals("a name", iv.getData());
        assertEquals(ov, iv.getItem());
        
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
 *  The Original Code is ImportedVersionTest.java
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