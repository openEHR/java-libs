/*
 * component:   "openEHR Reference Implementation"
 * description: "Class CRealTest"
 * keywords:    "archetype"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/test/org/openehr/am/archetype/constraintmodel/primitive/CRealTest.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 23:20:08 +0200 (Wed, 12 Oct 2005) $"
 */
/**
 * CRealTest
 *
 * @author Rong Chen
 * @version 1.0 
 */
package org.openehr.am.archetype.constraintmodel.primitive;

import junit.framework.TestCase;
import org.openehr.rm.support.basic.Interval;

import java.util.Arrays;

public class CRealTest extends TestCase {

    public CRealTest(String test) {
        super(test);
    }

    /**
     * The fixture set up called before every test method.
     */
    protected void setUp() throws Exception {
    }

    /**
     * The fixture clean up called after every test method.
     */
    protected void tearDown() throws Exception {
    }

    public void testValidValue() throws Exception {
        CReal cr = new CReal(new Interval<Double>(null, new Double(10),
                false, false), null);
        assertTrue(cr.validValue(new Double(9)));
        assertTrue(cr.validValue(new Double(-10)));
        assertTrue(!cr.validValue(new Double(10)));
        assertTrue(!cr.validValue(new Double(11)));

        cr = new CReal(new Interval<Double>(new Double(1),
                null, true, false), null);
        assertTrue(cr.validValue(new Double(1)));
        assertTrue(cr.validValue(new Double(2)));
        assertTrue(!cr.validValue(new Double(0)));
        assertTrue(!cr.validValue(new Double(-1)));

        Double[] values = {new Double(-2), new Double(0), new Double(2)};
        cr = new CReal(null, Arrays.asList(values));
        assertTrue(cr.validValue(new Double(-2)));
        assertTrue(cr.validValue(new Double(2)));
        assertTrue(cr.validValue(new Double(0)));
        assertTrue(!cr.validValue(new Double(-1)));
        assertTrue(!cr.validValue(new Double(1)));
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
 *  The Original Code is CRealTest.java
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