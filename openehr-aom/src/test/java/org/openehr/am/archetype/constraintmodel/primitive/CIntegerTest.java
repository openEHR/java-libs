/*
 * component:   "openEHR Reference Implementation"
 * description: "Class CIntegerTest"
 * keywords:    "archetype"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/test/org/openehr/am/archetype/constraintmodel/primitive/CIntegerTest.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 23:20:08 +0200 (Wed, 12 Oct 2005) $"
 */
/**
 * CIntegerTest
 *
 * @author Rong Chen
 * @version 1.0 
 */
package org.openehr.am.archetype.constraintmodel.primitive;

import junit.framework.TestCase;
import org.openehr.rm.support.basic.Interval;

import java.util.Arrays;

public class CIntegerTest extends TestCase {

    public CIntegerTest(String test) {
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

    /**
     * Tests validValue() with a list of integer values or an interval
     */
    public void testValidValue() {
        CInteger ci = new CInteger(new Interval<Integer>(null, new Integer(10),
                false, false), null);
        assertTrue(ci.validValue(new Integer(9)));
        assertTrue(ci.validValue(new Integer(-10)));
        assertTrue(!ci.validValue(new Integer(10)));
        assertTrue(!ci.validValue(new Integer(11)));

        ci = new CInteger(new Interval<Integer>(new Integer(1), null, true,
                false), null);
        assertTrue(ci.validValue(new Integer(1)));
        assertTrue(ci.validValue(new Integer(2)));
        assertTrue(!ci.validValue(new Integer(0)));
        assertTrue(!ci.validValue(new Integer(-1)));

        Integer[] values = {new Integer(-2), new Integer(0), new Integer(2)};
        ci = new CInteger(null, Arrays.asList(values));
        assertTrue(ci.validValue(new Integer(-2)));
        assertTrue(ci.validValue(new Integer(2)));
        assertTrue(ci.validValue(new Integer(0)));
        assertTrue(!ci.validValue(new Integer(-1)));
        assertTrue(!ci.validValue(new Integer(1)));
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
 *  The Original Code is CIntegerTest.java
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