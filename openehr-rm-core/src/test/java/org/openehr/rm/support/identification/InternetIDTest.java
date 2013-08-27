/*
 * component:   "openEHR Reference Implementation"
 * description: "Class InternetIDTest"
 * keywords:    "unit test"
 *
 * author:      "Yin Su Lim <y.lim@chime.ucl.ac.uk>"
 * support:     "CHIME, UCL"
 * copyright:   "Copyright (c) 2006 UCL, UK"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/BRANCHES/RM-1.0-update/libraries/src/test/org/openehr/rm/support/identification/InternetIDTest.java $"
 * revision:    "$LastChangedRevision: 50 $"
 * last_change: "$LastChangedDate: 2006-08-10 12:21:46 +0100 (Thu, 10 Aug 2006) $"
 */

/**
 * InternetIDTest
 *
 * @author Yin Su Lim
 * @version 1.0 
 */
package org.openehr.rm.support.identification;

import junit.framework.*;

public class InternetIDTest extends TestCase {
    
    public InternetIDTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(InternetIDTest.class);
        
        return suite;
    }
    
    public void testConstructorTakeString() throws Exception {
        assertNotNull(new InternetID("www.google.com"));
        assertNotNull(new InternetID("m-2.d2"));
        assertNotNull(new InternetID("a123.com.nz"));
        assertNotNull(new InternetID("openehr.org"));
        assertNotNull(new InternetID("openehr1-1.org"));
        assertNotNull(new InternetID("openehr1-1.com-2"));
        assertNull(toInternetID("128.17.13.1"));
        assertNull(toInternetID("123.com"));
        assertNull(toInternetID("open_ehr.org"));
        assertNull(toInternetID("openehr1-.org"));
        assertNull(toInternetID("openehr1-1.0"));
        assertNull(toInternetID("openehr1-1.com."));
        
    }
    
    private InternetID toInternetID(String value) {
        InternetID id = null;
        try {
            id = new InternetID(value);
            fail("should have failed this test");
        } catch (IllegalArgumentException iae) {
            
        }
        return id;
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
 *  The Original Code is InternetIDTest.java
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