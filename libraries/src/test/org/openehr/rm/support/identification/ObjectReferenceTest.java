/*
 * component:   "openEHR Reference Implementation"
 * description: "Class ObjectReferenceTest"
 * keywords:    "unit test"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL$"
 * revision:    "$LastChangedRevision$"
 * last_change: "$LastChangedDate$"
 */

/**
 * ObjectReferenceTest
 *
 * @author Rong Chen
 * @version 1.0
 */
package org.openehr.rm.support.identification;

import junit.framework.TestCase;

public class ObjectReferenceTest extends TestCase {

    public ObjectReferenceTest(String test) {
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

    public void testConstructor() throws Exception {
        assertExceptionThrown(null, ObjectReference.Namespace.LOCAL,
                ObjectReference.Type.EHR, "id");

        assertExceptionThrown(hid("50734"), null,
                ObjectReference.Type.EHR, "namespace");

        assertExceptionThrown(hid("50734"), ObjectReference.Namespace.LOCAL,
                null, "type");

        new ObjectReference(hid("50734"), ObjectReference.Namespace.LOCAL,
                ObjectReference.Type.EHR);
    }

    private ObjectID hid(String value) {
        return new HierarchicalObjectID(value);
    }

    public void testEquals() throws Exception {
        ObjectReference or1 = new ObjectReference(hid("50734"),
                ObjectReference.Namespace.LOCAL, ObjectReference.Type.EHR);
        ObjectReference or2 = new ObjectReference(hid("50734"),
                ObjectReference.Namespace.LOCAL, ObjectReference.Type.EHR);
        assertTrue(or1.equals(or2));
        assertTrue(or2.equals(or1));

        ObjectReference or3 = new ObjectReference(hid("974554"),
                ObjectReference.Namespace.LOCAL, ObjectReference.Type.EHR);
        assertFalse(or1.equals(or3));
        assertFalse(or3.equals(or1));

        or3 = new ObjectReference(hid("50734"),
                ObjectReference.Namespace.DEMOGRAPHIC,
                ObjectReference.Type.EHR);
        assertFalse(or1.equals(or3));
        assertFalse(or3.equals(or1));

        or3 = new ObjectReference(hid("50734"),
                ObjectReference.Namespace.LOCAL,
                ObjectReference.Type.PARTY);
        assertFalse(or1.equals(or3));
        assertFalse(or3.equals(or1));
    }

    private void assertExceptionThrown(ObjectID id,
                                       ObjectReference.Namespace namespace,
                                       ObjectReference.Type type,
                                       String cause) {
        try {
            new ObjectReference(id, namespace, type);
            fail("exception should be thrown");
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
            assertTrue(e.getMessage().contains(cause));
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
 *  The Original Code is ObjectReferenceTest.java
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