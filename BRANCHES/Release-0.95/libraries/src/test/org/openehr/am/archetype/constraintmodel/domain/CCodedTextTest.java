/*
 * component:   "openEHR Reference Implementation"
 * description: "Class CCodedTextTest"
 * keywords:    "archetype"
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
 * CCodedTextTest
 *
 * @author Rong Chen
 * @version 1.0 
 */
package org.openehr.am.archetype.constraintmodel.domain;

import org.openehr.am.archetype.ConstraintTestBase;
import org.openehr.am.archetype.constraintmodel.DVObjectCreationException;
import org.openehr.am.archetype.ontology.ArchetypeOntology;
import org.openehr.am.archetype.ontology.TestArchetypeOntology;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.datatypes.text.DvCodedText;

import java.util.ArrayList;
import java.util.List;

public class CCodedTextTest extends ConstraintTestBase {

    public CCodedTextTest(String test) {
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

    public void testCreateDVObject() throws Exception {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 6; i++) {
            list.add("at000" + i);
        }
        CCodedText ccodedtext = new CCodedText("path", "local", list);

        // index wrong format
        try {
            ccodedtext.createObject("bad", sysmap(), ontology);
            fail("exception should be thrown");
        } catch (Exception e) {
            assertTrue(e instanceof DVObjectCreationException);
        }

        // index out of range
        try {
            ccodedtext.createObject("10", sysmap(), ontology);
            fail("exception should be thrown");
        } catch (Exception e) {
            assertTrue(e instanceof DVObjectCreationException);
        }

        // right value
        DvCodedText codedtext = ccodedtext.createObject("2", sysmap(), ontology);
        assertEquals("value", "text of code 2", codedtext.getValue());
        CodePhrase definingCode = new CodePhrase("local", "at0002");
        assertEquals("definingCode", definingCode, codedtext.getDefiningCode());
    }

    public void testAssignedValue() throws Exception {

        // not assigned
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 6; i++) {
            list.add("at000" + i);
        }
        CCodedText ccodedtext = new CCodedText("path", "local", list);
        assertFalse(ccodedtext.hasAssignedValue());
        assertTrue(ccodedtext.assignedValue(
                sysmap(), ontology) == null);

        // assigned
        list = new ArrayList<String>();
        list.add("at0001");
        ccodedtext = new CCodedText("path", "local", list);
        assertTrue(ccodedtext.hasAssignedValue());
        DvCodedText codedtext = (DvCodedText) ccodedtext.assignedValue(
                sysmap(), ontology);
        assertEquals("value", "text of code 1", codedtext.getValue());
        CodePhrase definingCode = new CodePhrase("local", "at0001");
        assertEquals("definingCode", definingCode, codedtext.getDefiningCode());

    }

    /* fields */
    private final ArchetypeOntology ontology = TestArchetypeOntology.getInstance();
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
 *  The Original Code is CCodedTextTest.java
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